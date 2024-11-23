package com.example.dz12datetime

import java.io.Serializable


class Persons(
    var name:String?,
    var family:String?,
    var besdata: String?,
    var image: String?,
    var telefon:String
): Serializable
{
    override fun toString(): String {
        return "Персона $name, Фамилия $family"
    }
}
