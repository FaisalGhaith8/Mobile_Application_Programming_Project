package com.example.sell_it
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HuaweiAct : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_huawei)

//        val mate50: Button = findViewById(R.id.mate50)
//        mate50.setOnClickListener { view ->
//            var hmate50 = PhoneDialog("Mate 50 Pro", "6.74", "128 , 256", 345, "mate50",this)
//            hmate50.show(supportFragmentManager, "Custom Dialog")
//
//        }
//        val p30: Button = findViewById(R.id.p30)
//        p30.setOnClickListener { view ->
//            var hp30 = PhoneDialog("P30 Pro", "6.47", "128 , 256", 350, "p30",this)
//            hp30.show(supportFragmentManager, "Custom Dialog")
//
//        }
//        val nova10: Button = findViewById(R.id.nova10)
//        nova10.setOnClickListener { view ->
//            var hnova10 = PhoneDialog("Nova 10 SE", "6.67", "128 , 256", 289, "nova10",this)
//            hnova10.show(supportFragmentManager, "Custom Dialog")
//        }

        var mate50_dial = PhoneDialog("Mate 50 Pro", "6.74", arrayOf(128 , 256), 345, "mate50pro",this)
        val mate50_frag = phone_option("Mate 50 Pro", "mate50pro",mate50_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i1, mate50_frag)
            commit()
        }

        var p30_dial = PhoneDialog("P30 Pro", "6.47", arrayOf(128 , 256), 350, "p30pro",this)
        val p30_frag = phone_option("P30 Pro", "p30pro",p30_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i2, p30_frag)
            commit()
        }

        var nova10_dial = PhoneDialog("Nova 10 SE", "6.67", arrayOf(128 , 256), 289, "nova10se",this)
        val nova10_frag = phone_option("Nova 10 SE", "nova10se",nova10_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i3, nova10_frag)
            commit()
        }

    }
}