package com.example.examen02

import android.os.Parcel
import android.os.Parcelable

class EMusico(
    var id: String?=null,
    var name: String?=null,
    var birthday: String?=null,
    var p_awards: String?=null,
    var ocup: String?=null,
    var activity_m: String?=null,
    var latitud: Double?= null,
    var longitud: Double?= null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
    ){

    }
    override fun toString(): String {
        return super.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(birthday)
        parcel.writeString(p_awards)
        parcel.writeString(ocup)
        parcel.writeString(activity_m)
        parcel.writeDouble(latitud!!)
        parcel.writeDouble(longitud!!)
    }

    companion object CREATOR : Parcelable.Creator<EMusico> {
        override fun createFromParcel(parcel: Parcel): EMusico {
            return EMusico(parcel)
        }

        override fun newArray(size: Int): Array<EMusico?> {
            return arrayOfNulls(size)
        }
    }
}