package com.example.boligsok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Klassen vil håndtere hvordan info vil bli displayed i egne views fra boligListen.
//Tar imot min boligListe
class MyAdapter(private val boligData: MutableList<Bolig>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(val boligerListItem: View) : RecyclerView.ViewHolder(boligerListItem)   //boligerListItem er en constraintlayout

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // create a new view
        val boligerListItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_cardviews, parent, false) as View  //textView endret til View. Tar i mot alt som er View.
        // set the view's size, margins, paddings and layout parameters
        //...
        return MyViewHolder(boligerListItem)
    }


    //Hva som skal vises på recyclerViewet
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val boligTextView = holder.boligerListItem.findViewById<TextView>(R.id.bolig_textView)   //findViewbyId finner spesifikk view innenfor en layout,
        boligTextView.text = "Type: " +
            boligData[position].boligType                                                      //hente ut fra recyclerview_boliger_card, de spesifikke viewobjektene du ønsker.

        val addresseText = holder.boligerListItem.findViewById<TextView>(R.id.adresse_textview)
        addresseText.text = "Pris: " + boligData[position].pris + " kr"

        val prisText = holder.boligerListItem.findViewById<TextView>(R.id.pris_textview)
        prisText.text = "Adresse: " + boligData[position].adresse.toString()

        val storrelseText = holder.boligerListItem.findViewById<TextView>(R.id.storrelse_textview)
        storrelseText.text = "Størrelse: " + boligData[position].storrelse.toString()

        val romText = holder.boligerListItem.findViewById<TextView>(R.id.rom_textview)
        romText.text = "Antall rom: " + boligData[position].soverom.toString()


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = boligData.size
}
