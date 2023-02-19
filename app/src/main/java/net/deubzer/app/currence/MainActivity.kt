package net.deubzer.app.currence

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import net.deubzer.app.currence.viewmodel.CurrencyViewModel
import net.deubzer.app.currence.viewmodel.CurrencyViewModelFactory

class MainActivity : ComponentActivity() {


    private val viewModel: CurrencyViewModel by viewModels { CurrencyViewModelFactory() }

    private fun getBranchInfo(): String {
        return "Build from branch: " + getString(R.string.gitBranch) + ", Version: " + BuildConfig.VERSION_NAME
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("CURRENCE:BRANCH", getBranchInfo())
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(viewModel = viewModel)
        }
    }
}










