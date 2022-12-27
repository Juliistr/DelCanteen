package com.project.delcanteen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.project.delcanteen.R
import com.project.delcanteen.app.ApiConfig
import com.project.delcanteen.model.ResponModel
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register.setOnClickListener {
            register()
        }
    }

    fun register() {
        if (edt_nama.text.isEmpty()) {
            edt_nama.error = "Kolom Nama tidak boleh kosong"
            edt_nama.requestFocus()
            return
        }else if (edt_KTP.text.isEmpty()) {
            edt_KTP.error = "Kolom KTP tidak boleh kosong"
            edt_KTP.requestFocus()
            return
        }else if (edt_email.text.isEmpty()) {
            edt_email.error = "Kolom Email tidak boleh kosong"
            edt_email.requestFocus()
            return
        } else if (edt_phone.text.isEmpty()) {
            edt_phone.error = "Kolom Nomor Telepon tidak boleh kosong"
            edt_phone.requestFocus()
            return
        } else if (edt_password.text.isEmpty()) {
            edt_password.error = "Kolom Password tidak boleh kosong"
            edt_password.requestFocus()
            return
        }

        ApiConfig.instanceRetrofit.register(edt_nama.text.toString(),edt_KTP.text.toString(), edt_email.text.toString(),edt_password.text.toString()).enqueue(object : Callback<ResponModel>{

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@RegisterActivity,"Error:"+t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respon = response.body()!!
                if (respon.success == 1){
                    Toast.makeText(this@RegisterActivity, "Selamat datang "+respon.user.name, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@RegisterActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
}