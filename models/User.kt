package com.s.ajrami.firestore.models

import java.io.Serializable


data class User (var id :String,var img:String ,var first:String,var last:String, var born:String): Serializable {
    constructor() : this("","","","")
    constructor(img:String,first:String,last: String,born: String) : this("",img,first,last,born)

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result["img"]= img
        result["first"] = first
        result["last"] = last
        result["born"] = born

        return result
    }
}
