package bry1337.github.io.deliveryman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    setupNavigation()
  }

  private fun setupNavigation() {
    val navController = findNavController(R.id.nav_host_fragment)
    setupActionBarWithNavController(this, navController, null)
    setupWithNavController(toolbar, navController)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navigateUp(findNavController(R.id.nav_host_fragment), null)
  }
}
