import androidx.compose.ui.window.ComposeUIViewController
import com.tarasovvp.cmpuserlist.di.initKoin
import com.tarasovvp.cmpuserlist.presentation.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin()
    return ComposeUIViewController {
        App()
    }
}
