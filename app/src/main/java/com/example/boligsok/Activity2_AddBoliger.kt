package com.example.boligsok

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main_activiity2__add_boliger.*

class Activity2_AddBoliger : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activiity2__add_boliger)
        setSupportActionBar(findViewById(R.id.toolbar))

        //tilbake til home knapp
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        addBolig_btn.setOnClickListener {

            val nyAdresse = input_Addresse.text.toString()
            val nyType = input_Type.text.toString()
            val nyPris = input_Pris.text.toString().toDouble()
            val nyAreal = input_areal.text.toString().toInt()
            val nyRom = input_antallRom.text.toString().toInt()

            val nyBolig = Bolig(nyType,nyAdresse,nyPris,nyAreal,nyRom)
            MainActivity.boligListe.add(nyBolig)
            Toast.makeText(this, "Bolig er registrert!", Toast.LENGTH_SHORT).show()

        }

    }


}