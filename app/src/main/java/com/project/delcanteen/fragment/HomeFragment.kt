package com.project.delcanteen.fragment

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.project.delcanteen.R
import com.project.delcanteen.activity.*
import com.project.delcanteen.adapter.AdapterSlider
import com.project.delcanteen.adapter.ProdukKantinAdapter
import com.project.delcanteen.adapter.ProdukKoperasiAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.model.ProdukKantin
import com.project.delcanteen.model.ProdukKoperasi
import com.project.delcanteen.model.ResponProdukKantin
import com.project.delcanteen.model.ResponProdukKoperasi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var progerssProgressDialog: ProgressDialog
    lateinit var vpSlider: ViewPager
    lateinit var rvProductPopular: RecyclerView
    lateinit var rvProductOther: RecyclerView
    var dataList = ArrayList<ProdukKantin>()
    var dataListKoperasi = ArrayList<ProdukKoperasi>()
    lateinit var recyclerView: RecyclerView
    lateinit var produkKantinIcon: ImageView
    lateinit var produkKopersiIcon: ImageView
    lateinit var pulsaIcon: ImageView
    lateinit var peminjamanRuanganIcon: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvProductPopular = view.findViewById(R.id.produk_kantin)
        rvProductOther = view.findViewById(R.id.produk_koperasi)

        var arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider4)
        arrSlider.add(R.drawable.slider5)
        arrSlider.add(R.drawable.slider6)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView = view.findViewById(R.id.produk_kantin)
        recyclerView.adapter = ProdukKantinAdapter(dataList, recyclerView.context);
        recyclerView.layoutManager = layoutManager1
//        progerssProgressDialog = ProgressDialog(this.context)
//        progerssProgressDialog.setTitle("Loading")
//        progerssProgressDialog.setCancelable(false)
//        progerssProgressDialog.show()
        getProdukKantinTerbaruData();

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView = view.findViewById(R.id.produk_koperasi)
        recyclerView.adapter = ProdukKoperasiAdapter(dataListKoperasi, recyclerView.context);
        recyclerView.layoutManager = layoutManager2
        progerssProgressDialog = ProgressDialog(this.context)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        getProdukKoperasiTerbaruData();

        produkKantinIcon = view.findViewById(R.id.icon_kantin);
        produkKopersiIcon = view.findViewById(R.id.icon_koperasi);
        pulsaIcon = view.findViewById(R.id.icon_pulsa);
        peminjamanRuanganIcon = view.findViewById(R.id.icon_ruangan);

        produkKantinIcon.setOnClickListener { view ->
            val intent = Intent(view.context, ProdukKantinActivity::class.java)
            startActivity(intent)
        }
        produkKopersiIcon.setOnClickListener { view ->
            val intent = Intent(view.context, ProdukKoperasiActivity::class.java)
            startActivity(intent)
        }

        pulsaIcon.setOnClickListener { view ->
            val intent = Intent(view.context, PulsaActivity::class.java)
            startActivity(intent)
        }

        peminjamanRuanganIcon.setOnClickListener { view ->
            val intent = Intent(view.context, RuanganActivity::class.java)
            startActivity(intent)
        }
        return view
    }


    private fun getProdukKantinTerbaruData() {
        val call: Call<ResponProdukKantin> =
            ApiClient.getClient.getProdukKantinTerbaru()
        call.enqueue(object : Callback<ResponProdukKantin> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponProdukKantin>?,
                response: Response<ResponProdukKantin>?
            ) {
                progerssProgressDialog.dismiss()
                dataList.addAll(response!!.body()!!.data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<ResponProdukKantin>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }

    private fun getProdukKoperasiTerbaruData() {
        val call: Call<ResponProdukKoperasi> =
            ApiClient.getClient.getProdukKoperasi()
        call.enqueue(object : Callback<ResponProdukKoperasi> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponProdukKoperasi>?,
                response: Response<ResponProdukKoperasi>?
            ) {
                progerssProgressDialog.dismiss()
                dataListKoperasi.addAll(response!!.body()!!.data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<ResponProdukKoperasi>?, t:
                Throwable?
            ) {
                progerssProgressDialog.dismiss()
            }
        })
    }
}