package ug.ac.myivanfarm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ug.ac.myivanfarm.R
import ug.ac.myivanfarm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    var _binding: FragmentHomeBinding  ?= null
    val binding get() = _binding!!

    lateinit var bottomNav : BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val navFragment = view.supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
//        view.
//        val controller = navFragment.navController
//
//        //bottom navigation
//        bottomNav = binding.navView
//        bottomNav = findViewById(R.id.nav_view) as BottomNavigationView
//
//
//        val  appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.dasboardNav,R.id.workerNav, R.id.liveStockNav, R.id.purchaseNav, R.id.salesNav
//            )
//        )
//
//        setupActionBarWithNavController(controller, appBarConfiguration)
//        bottomNav.setupWithNavController(controller)

    }


}