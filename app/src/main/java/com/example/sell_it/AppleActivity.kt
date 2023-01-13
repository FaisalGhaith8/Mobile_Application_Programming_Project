package com.example.sell_it
import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class AppleActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apple)

//        val iphone14BT: Button = findViewById(R.id.iPhone14)
//        iphone14BT.setOnClickListener { view ->
//            var iPhone14_dial = PhoneDialog("iPhone14","6.6","128 , 256",500,"iphone14",this)
//            iPhone14_dial.show(supportFragmentManager, "Custom Dialog")
//
//        }
//
//        val iphone13BT: Button = findViewById(R.id.iPhone13)
//        iphone13BT.setOnClickListener { view ->
//            var iPhone13_dial = PhoneDialog("iPhone13", "6.6", "128 , 256", 300, "iphone13",this)
//            iPhone13_dial.show(supportFragmentManager, "Custom Dialog")
//
//        }
//        val iphone12BT: Button = findViewById(R.id.iPhone12)
//        iphone12BT.setOnClickListener { view ->
//            var iPhone12_dial = PhoneDialog("iPhone12", "5.1", "128 , 256", 100, "iphone12",this)
//            iPhone12_dial.show(supportFragmentManager, "Custom Dialog")
//        }

        var iPhone14_dial = PhoneDialog("iPhone14","6.6", arrayOf(128 , 256),500,"iphone14",this)
        val iPhone14_frag = phone_option("iPhone 14", "iphone14",iPhone14_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i1, iPhone14_frag)
            commit()
        }

        var iPhone13_dial = PhoneDialog("iPhone13", "6.6", arrayOf(128 , 256), 300, "iphone13",this)
        val iPhone13_frag = phone_option("iPhone 13", "iphone13",iPhone13_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i2, iPhone13_frag)
            commit()
        }

        var iPhone12_dial = PhoneDialog("iPhone12", "5.1", arrayOf(128 , 256), 100, "iphone12",this)
        val iPhone12_frag = phone_option("iPhone 12", "iphone12",iPhone12_dial)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.i3, iPhone12_frag)
            commit()
        }


    }











//    fun onClickAddName(view: View?, d: PhoneDialog) {
//        val values = ContentValues()
//        values.put(
//            CartProvider.PHONE_NAME,
//            d.name
//        )
//        values.put(
//            CartProvider.PRICE,
//            d.price
//        )
//
//        val uri = contentResolver.insert(
//            CartProvider.CONTENT_URI, values
//        )
//        Toast.makeText(baseContext, uri.toString(), Toast.LENGTH_LONG).show()
//    }

}




//val values = ContentValues()
//values.put(CartProvider.PHONE_NAME, name)
//values.put(CartProvider.PRICE, price.toString())
//
//val uri = actt.contentResolver.insert(CartProvider.CONTENT_URI, values)