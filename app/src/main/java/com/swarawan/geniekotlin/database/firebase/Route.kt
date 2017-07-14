package com.swarawan.geniekotlin.database.firebase

import com.swarawan.geniekotlin.database.local.GlobalPreference
import com.swarawan.geniekotlin.database.local.Key

/**
 * Created by rioswarawan on 7/13/17.
 */
class Route {

    /**
     * Database format will be like this:
     *  {uid} : {
     *      notes : [
     *          {key-1} : {
     *              title : <string>,
     *              content : <string>,
     *              locked : <boolean>
     *          },
     *          {key-2} : {
     *              title : <string>,
     *              content : <string>,
     *              locked : <boolean>
     *          }
     *      ]
     *  }
     */

    companion object {
        private val uid: String = GlobalPreference.read(Key.googleUserId, String::class.java).toString()

        val key: String = DataManager.key("$uid/notes/")

        val notes: String = "$uid"

        val addNotes: String = "$uid/notes/$key"
    }
}