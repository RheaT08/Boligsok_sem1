package com.example.boligsok

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

//TASKS
//  Sette i fragments?
//  Gjøre UI pent
//  Sette filter på pris
//  Skriv refleksjonsgreia

class MainActivity : AppCompatActivity() {

    //Adapter, and greier for display av info om boligene. Håndtering av RecyclerView layouten
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    companion object{
        //5 pre-definerte boliger i en mutable list.
        val boligListe : MutableList<Bolig> = mutableListOf(
            Bolig("Leilighet", "Ottoveien 68, 0863 Oslo", 4500000.00, 56, 2),
            Bolig("Enebolig", "Ottoveien 34A, 3456 Furuset", 6000000.00, 125, 5),
            Bolig("Leilighet", "Kjøleveien 23B, 3245 Gamle Oslo", 3440000.00, 42, 2),
            Bolig("Rekkehus", "Roseveien 6A, 9857 Trondheim Øst", 48200000.00, 34, 2),
            Bolig("Hybel", "Ottoveien 12, 1345 Sandvika", 3200000.00, 35, 1)
        )

    }


    //Main-Activity page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Display boliger med recyclerview
        displayBoliger(boligListe)


        //SEARCH
        searchButton.setOnClickListener {
            searchBoliger()
        }



        //FILTERS - filter fungerer kun en boks om gangen, selvom du krysser flere bokser. Nyeste boks krysset vil gjelde
        //rekkehus
        checkBox_Type1.setOnClickListener{
            if(checkBox_Type1.isChecked){
                filterTypeBoliger("rekkehus")
            }else{displayBoliger(boligListe)}
        }

        //Leilighet
        checkBox_Type2.setOnClickListener{
            if(checkBox_Type2.isChecked){
                filterTypeBoliger("leilighet")
            }else{displayBoliger(boligListe)}
        }

        //Hybel
        checkBox_Type3.setOnClickListener{
            if(checkBox_Type3.isChecked){
                filterTypeBoliger("hybel")
            }else{displayBoliger(boligListe)}
        }

        //Enebolig
        checkBox_Type4.setOnClickListener{
            if(checkBox_Type4.isChecked){
                filterTypeBoliger("enebolig")
            }else{displayBoliger(boligListe)}
        }

        //DisplayAll
        checkBox5.setOnClickListener{
            displayBoliger(boligListe)
        }



        //NYE BOLIGER - Floating action button
        val addFab = findViewById<FloatingActionButton>(R.id.fab_add)
        addFab.setOnClickListener {
            val intent = Intent(this, Activity2_AddBoliger::class.java)
            startActivity(intent);
        }


        //SLETTE BOLIGER
        slett_btn.setOnClickListener {
            val slettBolig = slettInput.text.toString()
            slettBolig(slettBolig)

        }





    }//onCREATE

    //ONRESUME - til activity 2, vil act1 stoppe. Ved å gå tilbake til act1, onResume() vil starte.
    override fun onResume() {
        super.onResume()
            // fetch updated data
            displayBoliger(boligListe)
    }


    //Displayer de 5 predefinerte bolig-objektene in en cardview på forsiden.
    fun displayBoliger(list: MutableList<Bolig>){

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(list)  //oppretter en objekt av MyAdapter klassen min. Parameter en mutablelist.


        recyclerView = recyclerView1.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            //adapter = viewAdapter
            recyclerView1.adapter = viewAdapter
        }
    }



    fun searchBoliger(){

        var userInput = searchInput.text
        var updateBoliger : MutableList<Bolig> = mutableListOf()

        var foundSwitch = false

            for(bolig in boligListe){

                var addressen = searchInput.text.toString().toLowerCase()
                if(foundSwitch){break}

                if(bolig.adresse.toLowerCase().split(" ")[0] == addressen.split(" ")[0]){  //Viser alle med samme gatenavn
                    updateBoliger.add(bolig)

                    if(bolig.adresse.toLowerCase() == addressen){ //spesifikk akkurat en addresse. Når hele addressen blir oppgitt.
                        updateBoliger.clear()
                        updateBoliger.add(bolig)
                        foundSwitch = true
                    }
                }

            }

        displayBoliger(updateBoliger)

        if(userInput.isNullOrEmpty()){     //hvis feltet er tomt, og bruker trykker søk. Hele listen kommer opp igjen.
                displayBoliger(boligListe)
        }
    }


    fun filterTypeBoliger(type: String){

        var updateBoliger : MutableList<Bolig> = mutableListOf()

            for(bolig in boligListe){
                if(bolig.boligType.toLowerCase() == type){
                    updateBoliger.add(bolig)
                }
            }
            displayBoliger(updateBoliger)
    }


    fun prisLavHoy(){}
    fun prisHoyLav(){}

    fun slettBolig(boligAddressen: String){

        boligListe.forEach{
            if(it.adresse == boligAddressen){
                boligListe.remove(it)
                viewAdapter.notifyDataSetChanged()
            }
        }
    }


    //Andre features
    //Cardviews, fancy


}