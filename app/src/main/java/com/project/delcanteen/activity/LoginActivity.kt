package com.project.delcanteen.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.helper.SharedPref
import com.project.delcanteen.model.ResponUser
import com.project.delcanteen.R
import com.project.delcanteen.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s: SharedPref
    lateinit var progerssProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)

        btn_login.setOnClickListener {
            progerssProgressDialog = ProgressDialog(this)
            progerssProgressDialog.setTitle("Loading")
            progerssProgressDialog.setCancelable(false)
            progerssProgressDialog.show()
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
        ApiClient.getClient.login(email.text.toString(), password.text.toString())
            .enqueue(object :
                Callback<ResponUser> {
                override fun onResponse(call: Call<ResponUser>, response: Response<ResponUser>) {
                    progerssProgressDialog.dismiss()
                    val respon = response.body()!!
                    Log.i("Login response: ", respon.success.toString())
                    if (respon.success) {
                        s.setStatusLogin(true)
                        val intents = Intent(this@LoginActivity, HomeFragment::class.java)
                        intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intents)
                        finish()
                        Toast.makeText(
                            this@LoginActivity,
                            "Selamat Datang " + respon.data.name,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error: " + respon.error_message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponUser>, t: Throwable) {
                    progerssProgressDialog.dismiss()
                    Toast.makeText(this@LoginActivity, "Error: " + t.message, Toast.LENGTH_SHORT)
                        .show()

                }

            })

    }
}