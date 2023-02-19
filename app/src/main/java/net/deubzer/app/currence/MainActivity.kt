package net.deubzer.app.currence

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import net.deubzer.app.currence.navigation.Navigation
import net.deubzer.app.currence.navigation.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private fun getBranchInfo(): String {
    return "Build from branch: " +
        getString(R.string.gitBranch) +
        ", Version: " +
        BuildConfig.VERSION_NAME
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    Log.i("CURRENCE:BRANCH", getBranchInfo())
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent { Navigation(startDestination = Screen.MainScreen.route) }
  }
}
