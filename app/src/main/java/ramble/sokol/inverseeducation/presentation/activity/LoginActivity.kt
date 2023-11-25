package ramble.sokol.inverseeducation.presentation.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ramble.sokol.inverseeducation.presentation.fragment.LoginFragment
import ramble.sokol.inverseeducation.R
import ramble.sokol.inverseeducation.presentation.FirstEntryManager
import ramble.sokol.inverseeducation.presentation.fragment.BottomNavBarFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var firstEntryManager: FirstEntryManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        firstEntryManager = FirstEntryManager(this)

        if (firstEntryManager.getFirstEntry() == true){
            val bottomNavBarFragment = BottomNavBarFragment()
            val fragment : Fragment? =

                supportFragmentManager.findFragmentByTag(BottomNavBarFragment::class.java.simpleName)

            if (fragment !is BottomNavBarFragment){
                supportFragmentManager.beginTransaction()
                    .add(R.id.linear_fragment, bottomNavBarFragment, BottomNavBarFragment::class.java.simpleName)
                    .commit()
            }
        }else {

            val loginFragment = LoginFragment()
            val fragment: Fragment? =

                supportFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

            if (fragment !is LoginFragment) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.linear_fragment, loginFragment, LoginFragment::class.java.simpleName)
                    .commit()
            }
        }

//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        setContentView(R.layout.activity_login)

    }

}