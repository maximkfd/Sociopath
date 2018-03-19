package com.repp.max.sociopath

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.mirrajabi.rxcontacts.Contact
import ir.mirrajabi.rxcontacts.RxContacts
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var adapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        becomeSociopathButton.setOnClickListener(this::onBecomeButtonClicked)
    }

    private fun onBecomeButtonClicked(view: View) {
        val permissions = arrayOf(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 0)
        } else {
            fetchContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fetchContacts()
    }

    private fun fetchContacts() {
        RxContacts.fetch(this)
                .filter { m -> m.inVisibleGroup == 1 }
                .toSortedList { obj, other -> obj.compareTo(other) }
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onContactsLoaded)
    }

    private fun onContactsLoaded(list: MutableList<Contact>) {
        this.runOnUiThread {
            becomeSociopathButton.visibility = View.GONE
            adapter = ContactAdapter(list)
            contactsList.layoutManager = LinearLayoutManager(this)
            contactsList.setHasFixedSize(false)
            contactsList.adapter = adapter
            adapter?.notifyDataSetChanged()
        }
    }
}
