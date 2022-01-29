package com.siler.criminalintent

import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CrimeListFragment : Fragment(R.layout.fragment_crime_list) {

    private val crimeListViewModel : CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }
    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        crimeRecyclerView = view.findViewById(R.id.crimeRecyclerView)
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()
    }

    private fun updateUI() {
        adapter = CrimeAdapter(crimeListViewModel.crimes)
        crimeRecyclerView.adapter = adapter
    }

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val titleTextView: TextView = itemView.findViewById(R.id.itemCrimeTitle)
        private val dateTextView: TextView = itemView.findViewById(R.id.itemCrimeDate)
        private val crimeSolvedImageView: ImageView = itemView.findViewById(R.id.crimeSolved)

        private lateinit var crime: Crime

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            this.crime = crime
            titleTextView.text = crime.title
            dateTextView.text = (DateFormat.getLongDateFormat(context).format(crime.date)).toString()
            crimeSolvedImageView.visibility = if(crime.isSolved) View.VISIBLE else View.GONE
        }

        override fun onClick(p0: View?) {
            Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            /*val view = if(viewType == REQUIRES_POLICE_CRIME) {
                layoutInflater.inflate(R.layout.list_item_crime_requires_polcie, parent, false)
            } else {
                layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            }
            return CrimeHolder(view)*/
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
        }

        override fun getItemCount() = crimes.size

        /*override fun getItemViewType(position: Int): Int {
            return if(crimes[position].requiresPolice) {
                REQUIRES_POLICE_CRIME
            } else {
                super.getItemViewType(position)
            }
        }*/

    }

    companion object {
        private const val TAG = "CrimeListFragment"
        //private const val REQUIRES_POLICE_CRIME = 1

        fun newInstance() : CrimeListFragment {
            return CrimeListFragment()
        }
    }
}