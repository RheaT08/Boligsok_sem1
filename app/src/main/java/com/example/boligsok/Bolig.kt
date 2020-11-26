package com.example.boligsok


//Her opprettes instanser av bolig-objekter
class Bolig(val boligType: String, val adresse: String, val pris : Double, val storrelse :  Int, val soverom: Int){


    fun getType() : String{
        return boligType
    }

    fun getInformation(){

        println("Type: $boligType")
        println("Adresse: $adresse")
        println("Pris: $pris")
        println("Areal: $storrelse kvadratmeter")
        println("Antall Soverom: $soverom")
    }

}