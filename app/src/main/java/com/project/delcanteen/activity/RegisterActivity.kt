package com.project.delcanteen.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.delcanteen.R
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.helper.SharedPref
import com.project.delcanteen.model.ResponUser
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var s: SharedPref
    lateinit var progerssProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            progerssProgressDialog = ProgressDialog(this)
            progerssProgressDialog.setTitle("Loading")
            progerssProgressDialog.setCancelable(false)
            progerssProgressDialog.show()
            register()

        }
    }

    fun register() {
        if (nama.text.isEmpty()) {
            nama.error = "Kolom Nama tidak boleh kosong"
            nama.requestFocus()
            return
        }else if (no_ktp.text.isEmpty()) {
            no_ktp.error = "Kolom KTP tidak boleh kosong"
            no_ktp.requestFocus()
            return
        }else if (no_hp.text.isEmpty()) {
            no_hp.error = "Kolom KTP tidak boleh kosong"
            no_hp.requestFocus()
            return
        }else if (email.text.isEmpty()) {
            email.error = "Kolom Email tidak boleh kosong"
            email.requestFocus()
            return
        } else if (password.text.isEmpty()) {
            password.error = "Kolom Password tidak boleh kosong"
            password.requestFocus()
            return
        }

        ApiClient.getClient.register(nama.text.toString(), no_hp .text.toString(),no_ktp.text.toString(), email.text.toString(),password.text.toString()).enqueue(object : Callback<ResponUser>{

            override fun onFailure(call: Call<ResponUser>, t: Throwable) {
                progerssProgressDialog.dismiss()
                Toast.makeText(this@RegisterActivity,"Error:"+t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponUser>, response: Response<ResponUser>) {
                progerssProgressDialog.dismiss()
                val respon = response.body()!!
                if (respon.success) {
                    s.setStatusLogin(true)

                    val intents = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intents)
                    finish()
                    Toast.makeText(
                        this@RegisterActivity,
                        "Register Berhasil Dilakukan",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error: " + respon.error_message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })


    }
}