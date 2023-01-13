package com.example.sell_it

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class phone_option(n: String, img_n: String, pd: PhoneDialog) : Fragment(R.layout.fragment_phone_option) {

    lateinit var rootView: View
    lateinit var nameTV: TextView
    lateinit var img: ImageView
    val name = n
    val img_name = img_n
    val pd = pd



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_phone_option, container, false)


        nameTV = rootView.findViewById(R.id.name) as TextView

        img = rootView.findViewById(R.id.image) as ImageView
        val drawableId = resources.getIdentifier(img_name, "drawable", "com.example.sell_it")
        val imageDRAWABLE = resources.getDrawable(drawableId, null)
        img.setImageDrawable(imageDRAWABLE)


        nameTV.text = name

        img.setOnClickListener(){
            pd.show(parentFragmentManager, "Custom Dialog")
        }
        nameTV.setOnClickListener(){
            pd.show(parentFragmentManager, "Custom Dialog")
        }


        return rootView
    }

}






