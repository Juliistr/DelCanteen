package com.project.delcanteen.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.project.delcanteen.R
import com.project.delcanteen.activity.ProdukKantinActivity
import com.project.delcanteen.adapter.AdapterProduk
import com.project.delcanteen.adapter.AdapterSlider
import com.project.delcanteen.adapter.ProdukKantinAdapter
import com.project.delcanteen.app.ApiClient
import com.project.delcanteen.app.ApiConfig
import com.project.delcanteen.model.ProdukKantin
import com.project.delcanteen.model.ResponProdukKantin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvProduct: RecyclerView
    lateinit var rvProductPopular: RecyclerView
    lateinit var rvProductOther: RecyclerView
    var dataList = ArrayList<ProdukKantin>()
    lateinit var recyclerView: RecyclerView
    lateinit var produkKantinIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvProduct = view.findViewById(R.id.rv_product)
        rvProductPopular = view.findViewById(R.id.rv_product_popular)
        rvProductOther = view.findViewById(R.id.rv_product_other)

        var arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.slider4)
        arrSlider.add(R.drawable.slider5)
        arrSlider.add(R.drawable.slider6)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        val layoutManager1 = LinearLayoutManager(activity)
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView = view.findViewById(R.id.rv_product_popular)
        recyclerView.adapter = ProdukKantinAdapter(dataList, recyclerView.context);
        recyclerView.layoutManager = layoutManager1
        getProdukKantinData();

        produkKantinIcon = view.findViewById(R.id.icon_kantin);


        produkKantinIcon.setOnClickListener { view ->
            val intent = Intent(view.context, ProdukKantinActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun getProdukKantin() {
        ApiConfig.instanceRetrofit.getProdukKantin().enqueue(object :
            Callback<ResponProdukKantin> {

            override fun onResponse(
                call: Call<ResponProdukKantin>,
                response: Response<ResponProdukKantin>
            ) {
                Log.i("Data produk kantin: ", response.body().toString())
                response.body()?.data?.forEach {
                    dataList.add(it);
                }
//                displayProdukKantin();
            }

            override fun onFailure(call: Call<ResponProdukKantin>, t: Throwable) {
                Log.d("Error ", t.message.toString())
            }

        })
    }
//
//    fun displayProdukKantin() {
//        val layoutManager1 = LinearLayoutManager(activity)
//        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
//
//        rvProduct.adapter = AdapterProduct(listProduk);
//        rvProduct.layoutManager = layoutManager1
//    }

    private fun getProdukKantinData() {
        val call: Call<ResponProdukKantin> =
            ApiClient.getClient.getProdukKantin()
        call.enqueue(object : Callback<ResponProdukKantin> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<ResponProdukKantin>?,
                response: Response<ResponProdukKantin>?
            ) {
                dataList.addAll(response!!.body()!!.data)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<ResponProdukKantin>?, t:
                Throwable?
            ) {
            }
        })
    }
}