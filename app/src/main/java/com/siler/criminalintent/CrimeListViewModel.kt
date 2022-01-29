package com.siler.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        for(i in 0 until 100) {
            val crime = Crime()
            with(crime) {
                title = "Crime $i"
                isSolved = i % 2 == 0
                //requiresPolice = i % 5 == 0
            }
            crimes.add(crime)
        }
    }
}