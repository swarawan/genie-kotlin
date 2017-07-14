package com.swarawan.geniekotlin.database.firebase

import com.google.firebase.database.*
import java.util.*

/**
 * Created by rioswarawan on 7/13/17.
 */
class DataManager {

    companion object {
        val reference: DatabaseReference = FirebaseDatabase.getInstance().reference

        fun key(route: String): String {
            return reference.child(route).push().key
        }

        fun addOrUpdate(route: String, value: HashMap<String, Any>) {
            val childs: HashMap<String, Any> = HashMap()

            childs.put(route, value)
            reference.updateChildren(childs)
        }

        fun syncronize(route: String, valueEventListener: ValueEventListener) {
            FirebaseDatabase.getInstance()
                    .getReference(route)
                    .addValueEventListener(valueEventListener)
        }

        fun retrieve(route: String, valueEventListener: ValueEventListener) {
            FirebaseDatabase.getInstance()
                    .getReference(route)
                    .addListenerForSingleValueEvent(valueEventListener)
        }
    }
}