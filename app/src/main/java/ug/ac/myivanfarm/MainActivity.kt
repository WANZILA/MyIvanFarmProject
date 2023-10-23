package ug.ac.myivanfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
//import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ug.ac.myivanfarm.databinding.ActivityMainBinding
import ug.ac.myivanfarm.ui.DasboardFragment
import ug.ac.myivanfarm.ui.LiveStockFragment
import ug.ac.myivanfarm.ui.PurchaseFragment
import ug.ac.myivanfarm.ui.SalesFragment
import ug.ac.myivanfarm.ui.WorkerAddFragment
import ug.ac.myivanfarm.ui.WorkerFragment
import ug.ac.myivanfarm.ui.WorkerViewFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
//        val controller = navFragment.navController
//
//        //bottom navigation
//        bottomNav = binding.navView
//        bottomNav = findViewById(R.id.nav_view) as BottomNavigationView
//
//
//       val  appBarConfiguration = AppBarConfiguration(
//           setOf(
//               R.id.dasboardNav,R.id.workerNav, R.id.liveStockNav, R.id.purchaseNav, R.id.salesNav
//           )
//        )
//
//        setupActionBarWithNavController(controller, appBarConfiguration)
//        bottomNav.setupWithNavController(controller)


    }


//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.top_menu_navigation, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//
//        when(id){
//            R.id.workerNav-> {
////                supportFragmentManager.beginTransaction().replace(R.id.container, WorkerFragment()).commit()
////                Toast.makeText(this@MainActivity,"in view nave", Toast.LENGTH_LONG).show()
//                loadFragment(WorkerFragment())
//            }
//            R.id.workerViewNav-> {
//               supportFragmentManager.beginTransaction().replace(R.id.container, WorkerViewFragment()).commit()
////                Toast.makeText(this@MainActivity,"in view nave", Toast.LENGTH_LONG).show()
//
//            }
//            R.id.workerAddNav-> {
//                loadFragment(WorkerAddFragment())
////                Toast.makeText(this@MainActivity,"in view add", Toast.LENGTH_LONG).show()
//            }
//            R.id.salesNav ->{
//                loadFragment(SalesFragment())
//            }
//            R.id.purchaseNav -> {
//                loadFragment(PurchaseFragment())
//            }
//
//        }
//        return super.onOptionsItemSelected(item)
//
//
//    }
//
////    private  fun loadFragment(fragment: Fragment){
////        val transaction = supportFragmentManager.beginTransaction()
////        transaction.replace(R.id.container,fragment)
////        transaction.commit()
////    }
}