package buu.informatics59160953.drivingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import buu.informatics59160953.drivingtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //ลูกศรย้อนกลับ
        val navController = this.findNavController(R.id.navController)
        NavigationUI.setupActionBarWithNavController(this,navController)


    }
        //ลูกศรย้อนกลับ
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navController)
        return navController.navigateUp()
    }
}
