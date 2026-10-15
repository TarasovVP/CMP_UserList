# CMP UserList

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.0-7F52FF.svg?logo=kotlin&logoColor=white)](https://kotlinlang.org/docs/multiplatform.html)
[![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.10.0-4285F4.svg)](https://www.jetbrains.com/compose-multiplatform/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

A Kotlin Multiplatform app for displaying a list of users. One codebase — four platforms: **Android**, **iOS**, **Desktop** (JVM), and **Web**.

## About the project

The app fetches a list of users from the public [DummyJSON](https://dummyjson.com/users) API, stores them in a local SQLite database, and displays them in a list with avatars and contact details. On launch, cached data is shown first (if available), then data is refreshed from the server — enabling fast startup and offline use from cache.

## Platforms

| Module       | Platform        |
|-------------|-----------------|
| `androidApp` | Android         |
| `iosApp`     | iOS             |
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
- **Gradle:** 8.13
- **Android Gradle Plugin:** 8.11.2

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

## Related implementations

The same user-list scenario is used to compare different cross-platform approaches:

| Project | Approach |
|---------|----------|
| [KMP_UserList](https://github.com/TarasovVP/KMP_UserList) | Kotlin Multiplatform with platform-specific UI |
| **CMP_UserList** | Kotlin Multiplatform with shared Compose Multiplatform UI |
| [flutter_user_list](https://github.com/TarasovVP/flutter_user_list) | Flutter and Dart |
| [react-native-user-list](https://github.com/TarasovVP/react-native-user-list) | React Native and TypeScript |

## Related articles

- [KMP: Network Requests, Database, and Dependency Injection](https://medium.com/@vptarasov/kmp-network-requests-database-and-dependency-injection-5888ffe48e1e)
- [KMP: A Single Project for All Platforms](https://medium.com/@vptarasov/kmp-a-single-project-for-all-platforms-ed30820522b3)
- [KMP: Unified UI with Compose Multiplatform](https://medium.com/@vptarasov/kmp-unified-ui-with-compose-multiplatform-a8c13a52162b)
- [Flutter from a KMP Developer’s Perspective](https://levelup.gitconnected.com/flutter-from-a-kmp-developers-perspective-c858644c3179)
- [React Native from a KMP Developer’s Perspective](https://levelup.gitconnected.com/react-native-from-a-kmp-developers-perspective-346c356f7457)

## License

Licensed under the [Apache License 2.0](LICENSE). The DummyJSON API has its own terms of use.
