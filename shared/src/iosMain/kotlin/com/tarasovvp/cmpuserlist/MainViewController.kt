import androidx.compose.ui.window.ComposeUIViewController
import com.tarasovvp.cmpuserlist.presentation.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App()
    }
}
