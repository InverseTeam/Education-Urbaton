package ramble.sokol.inverseeducation.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ramble.sokol.inverseeducation.presentation.fragment.LoginFragment
import ramble.sokol.inverseeducation.R

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginFragment = LoginFragment()
        val fragment : Fragment? =

        supportFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

        if (fragment !is LoginFragment){
            supportFragmentManager.beginTransaction()
                .add(R.id.linear_fragment, loginFragment, LoginFragment::class.java.simpleName)
                .commit()
        }

//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        setContentView(R.layout.activity_login)

    }

}