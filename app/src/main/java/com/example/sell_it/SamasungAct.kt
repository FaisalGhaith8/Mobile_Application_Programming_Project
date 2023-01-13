package com.example.sell_it
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SamasungAct : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_samsung)


        var note10_dial = PhoneDialog("Galaxy Note 10", "6.3", arrayOf(128 , 256), 640, "galaxynote10",this)
        val note10_frag = phone_option("Galaxy Note 10", "galaxynote10",note10_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i1, note10_frag)
            commit()
        }

        var s21_dial = PhoneDialog("Galaxy S21", "6.2", arrayOf(128 , 256), 560, "galaxys21",this)
        val s21_frag = phone_option("Galaxy S21", "galaxys21",s21_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i2, s21_frag)
            commit()
        }

        var a51_dial = PhoneDialog("Galaxy A51", "6.5", arrayOf(128 , 256), 250, "galaxya51",this)
        val a51_frag = phone_option("Galaxy A51", "galaxya51",a51_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i3, a51_frag)
            commit()
        }

    }

}
