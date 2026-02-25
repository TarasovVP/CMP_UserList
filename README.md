# CMP UserList

A Kotlin Multiplatform app for displaying a list of users. One codebase — four platforms: **Android**, **iOS**, **Desktop** (JVM), and **Web**.

## About the project

The app fetches a list of users from the public [DummyJSON](https://dummyjson.com/users) API, stores them in a local SQLite database, and displays them in a list with avatars and contact details. On launch, cached data is shown first (if available), then data is refreshed from the server — enabling fast startup and offline use from cache.

## Platforms

| Module       | Platform        |
|-------------|-----------------|
| `androidApp` | Android         |
| `shared`     | Shared logic & UI |
| `desktopApp` | Desktop (JVM)   |
| `webApp`     | Browser         |

The iOS app uses the `shared` module via the compiled framework (the repo includes the `iosApp` folder).

## Tech stack

- **UI:** Compose Multiplatform (shared UI across all platforms)
- **Networking:** Ktor Client + Kotlin Serialization (JSON)
- **Local DB:** SQLDelight (user cache in SQLite)
- **DI:** Koin (including Koin Compose ViewModel)
- **Images:** Coil 3 (loading avatars from URLs)
- **Architecture:** ViewModel + Use Case + Repository, offline-first flow

## Shared module structure

```
shared/src/commonMain/kotlin/com/tarasovvp/cmpuserlist/
├── data/
│   ├── database/          # SQLDelight, drivers, SharedDatabase
│   ├── network/           # Ktor, RemoteUser, RemoteUserDataSource
│   ├── repository/        # UserRepositoryImpl
│   └── mapper/            # UserMapper (Remote → Domain)
├── domain/
│   ├── model/             # User
│   ├── repository/        # UserRepository
│   └── usecase/           # GetUserListUseCase
├── presentation/
│   ├── composable/        # App, Content, UserScreen, ErrorView, AppImage
│   ├── viewmodel/         # UsersViewModel
│   ├── state/             # UiState
│   └── theme/             # AppTheme
├── di/                    # Koin (CommonModule, platform init)
└── Constants.kt           # BASE_URL (dummyjson.com), DB name, etc.
```

## Requirements

- **JDK:** 21  
- **Android:** minSdk 28, compileSdk 36  
- **Kotlin:** 2.3.0  
- **Gradle:** version from wrapper (AGP 8.x)

## Build and run

### Android

```bash
./gradlew :androidApp:installDebug
```

Or open the project in Android Studio and run the `androidApp` configuration.

### Desktop (JVM)

```bash
./gradlew :desktopApp:run
```

### Web

```bash
./gradlew :webApp:jsBrowserDevelopmentRun
```

The built app will be available at the URL shown in the log (usually localhost).

### iOS

1. Open `iosApp/iosApp.xcodeproj` in Xcode.
2. Build and run on a simulator or device.

Before the first iOS build, it’s recommended to build the shared framework:

```bash
./gradlew :shared:linkDebugFrameworkIosSimulatorArm64
```

(Or the target you need: `IosArm64`, `IosX64`.)

## API and data

- **Source:** `https://dummyjson.com/users`  
- **User model:** first name, last name, age, gender, email, phone, birth date, avatar URL.  
- **Cache:** SQLite via SQLDelight; the app shows cache first, then updates from the API. On network errors, the last cached list is kept.

## License

This project is for educational purposes. The DummyJSON API has its own terms of use.
