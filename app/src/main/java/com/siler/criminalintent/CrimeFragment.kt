package com.siler.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class CrimeFragment : Fragment(R.layout.fragment_crime) {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedChekBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crime = Crime()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleField = view.findViewById(R.id.crimeTitle)
        dateButton = view.findViewById(R.id.crimeDate)
        solvedChekBox = view.findViewById(R.id.crimeSolved)

        dateButton.apply {
            text = crime.date.toString()
            isEnabled = false
        }
    }

    override fun onStart() {
        super.onStart()

        titleField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        solvedChekBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }
    }
}