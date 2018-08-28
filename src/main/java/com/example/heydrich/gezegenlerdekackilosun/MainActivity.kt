package com.example.heydrich.gezegenlerdekackilosun

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {


    val kilo_to_pound = 2.2045
    val MARS = 0.38
    val VENUS = 0.91
    val JUPITER = 2.34
    val pound_to_kilo = 0.458645

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load(R.drawable.arifv216).into(imageView)
        tvSonuc.text=savedInstanceState?.getString("sonuc")
        cb_venus.setOnClickListener(this)
        cb_jupiter.setOnClickListener(this)
        cb_mars.setOnClickListener(this)
      /*  btn_hesap.setOnClickListener {


            var kullaniciAgirlikPound = kiloToPound(kullaniciKilo.toString().toDouble())
            var marstakiAgirlikPound = kullaniciAgirlikPound * MARS
            var marstakiAgirlikKilo = poundToKilo(marstakiAgirlikPound)




            tvSonuc.text = marstakiAgirlikKilo.formatla(2).toString()
        }*/


    }

    fun kiloToPound(kilo:Double) : Double{
        return  kilo*kilo_to_pound
    }

    fun poundToKilo(pound : Double) : Double{
        return pound * pound_to_kilo
    }

    override fun onClick(v: View?) {
        var isCheck:Boolean = (v as CheckBox).isChecked

        if(!TextUtils.isEmpty(etKilo.text.toString())) {
            var kullaniciKilo = etKilo.text.toString().toDouble()
            var kullaniciPound = kiloToPound(kullaniciKilo)
            when(v.id){
                R.id.cb_mars -> if (isCheck){
                    cb_jupiter.isChecked=false
                    cb_venus.isChecked=false
                    hesaplaAgirlikPound(kullaniciPound,v)
                }
                R.id.cb_venus -> if (isCheck){
                    cb_jupiter.isChecked=false
                    cb_mars.isChecked=false
                    hesaplaAgirlikPound(kullaniciPound,v)
                }
                R.id.cb_jupiter -> if (isCheck){
                    cb_venus.isChecked=false
                    cb_mars.isChecked=false
                    hesaplaAgirlikPound(kullaniciPound,v)
                }
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("sonuc",tvSonuc.text.toString())


    }

    fun hesaplaAgirlikPound(pound : Double,checkBox: CheckBox){
        var sonuc : Double = 0.0
        when(checkBox.id){
            R.id.cb_mars -> sonuc = pound*MARS
            R.id.cb_jupiter -> sonuc = pound*JUPITER
            R.id.cb_venus -> sonuc = pound*VENUS
            else -> sonuc = 0.0
        }

        var sonucToKilo = poundToKilo(sonuc)

        tvSonuc.text=sonucToKilo.formatla(2).toString()

    }

    fun Double.formatla(kactaneRakam:Int) = java.lang.String.format("%.${kactaneRakam}f",this)
}
