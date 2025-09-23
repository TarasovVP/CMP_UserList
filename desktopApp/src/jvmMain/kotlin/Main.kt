import com.tarasovvp.cmpuserlist.di.initKoin
import presentation.App
import javax.swing.SwingUtilities

fun main() = SwingUtilities.invokeLater {
    initKoin()
    App()
}