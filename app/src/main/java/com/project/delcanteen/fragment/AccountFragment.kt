package com.project.delcanteen.fragment

import android.content.Intent
import android.os.Bundle
import android.os.SharedMemory
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.project.delcanteen.R
import com.project.delcanteen.activity.LoginActivity
import com.project.delcanteen.activity.PulsaActivity
import com.project.delcanteen.helper.SharedPref

class AccountFragment : Fragment() {
    lateinit var s:SharedPref
    lateinit var btnLogout:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_account, container, false)
        btnLogout = view.findViewById(R.id.btn_logout)

        s = SharedPref(requireActivity())

        btnLogout.setOnClickListener { view ->
            val intent = Intent(view.context, LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}