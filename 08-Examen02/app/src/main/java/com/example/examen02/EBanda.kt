package com.example.examen02

import android.os.Parcel
import android.os.Parcelable

class EBanda(
    //var id: Int?,
    var id: String?=null,
    var name: String?=null,
    var genre: String?=null,
    var awards: String?=null,
    var year: String?=null,
    var funds: String?=null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
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
        parcel.writeString(genre)
        parcel.writeString(awards)
        parcel.writeString(year)
    }

    companion object CREATOR : Parcelable.Creator<EBanda> {
        override fun createFromParcel(parcel: Parcel): EBanda {
            return EBanda(parcel)
        }

        override fun newArray(size: Int): Array<EBanda?> {
            return arrayOfNulls(size)
        }
    }
}