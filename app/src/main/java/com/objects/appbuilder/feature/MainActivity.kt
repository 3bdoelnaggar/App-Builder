package com.objects.appbuilder.feature

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.objects.appbuilder.R
import com.objects.appbuilder.databinding.MainActivityBinding
import com.objects.appbuilder.feature.ui.MainState
import com.objects.appbuilder.feature.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAppConfiguration()
        viewModel.stateLiveData.observe(this) {
            render(it)

        }


        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun render(state: MainState) {
        when (state) {
            MainState.Error -> {
            }
            is MainState.Success -> {
                binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor(state.data.appColor.headerBg))
                binding.sideMenuRecyclerView.setBackgroundColor(Color.parseColor(state.data.appColor.menuBg))
                binding.appBarMain.toolbar.setTitleTextColor(Color.parseColor(state.data.appColor.headerText))
                binding.appBarMain.contentMain.mainConstraintLayout.setBackgroundColor(
                    Color.parseColor(
                        state.data.appColor.pageBg
                    )
                )

            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}