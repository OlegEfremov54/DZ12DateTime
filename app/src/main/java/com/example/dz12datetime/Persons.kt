package com.example.dz12datetime

import java.io.Serializable
import java.time.LocalDateTime

class Persons(
    var name:String?,
    var family:String?,
    var besdata: String?,
    var image: String?,
    var telef:String
): Serializable
{
    override fun toString(): String {
        return "Персона $name, Фамилия $family"
    }
}
