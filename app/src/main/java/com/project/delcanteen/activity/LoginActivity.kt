package com.project.delcanteen.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.delcanteen.MainActivity
import com.project.delcanteen.R
import com.project.delcanteen.app.ApiConfig
import com.project.delcanteen.helper.SharedPref
import com.project.delcanteen.model.ResponUser
import com.project.delcanteen.model.ResponModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        btn_login.setOnClickListener {
            login()
        }
    }

    fun login() {
        if (email.text.isEmpty()) {
            email.error = "Kolom Email tidak boleh kosong"
            email.requestFocus()
            return
        } else if (password.text.isEmpty()) {
            password.error = "Kolom Password tidak boleh kosong"
            password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(email.text.toString(),password.text.toString()).enqueue(object :
            Callback<ResponUser> {
            override fun onFailure(call: Call<ResponUser>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponUser>, response: Response<ResponUser>) {
                pb.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success){
                    s.setStatusLogin(true)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
//                    Toast.makeText(this@LoginActivity, "Selamat datang "+respon.data.name, Toast.LENGTH_SHORT).show()
//                } else{
//                    Toast.makeText(this@LoginActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}