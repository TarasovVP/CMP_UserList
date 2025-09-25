import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.tarasovvp.cmpuserlist.di.initKoin
import com.tarasovvp.cmpuserlist.presentation.App
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    onWasmReady {
        ComposeViewport(
            content = {
                App()
            }
        )
    }
}

private const val APP_NAME = "CMP User List"