package com.repp.max.sociopath

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ir.mirrajabi.rxcontacts.Contact

/**
 * Created by Max on 07.01.2018.
 */
class ContactAdapter(private val data: List<Contact>) : RecyclerView.Adapter<ContactAdapter.Holder>() {
//    private val data = ArrayList<Contact>()

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val contact = data[position]
        holder?.contactName?.text = contact.displayName
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_contact_list, parent)
        return Holder(view)
    }

    override fun getItemCount() = data.size

    class Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        val contactName: TextView = view.findViewById(R.id.contactName)

    }
}