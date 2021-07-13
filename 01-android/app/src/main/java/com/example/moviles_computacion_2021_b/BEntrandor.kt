package com.example.moviles_computacion_2021_b

import android.os.Parcel
import android.os.Parcelable

class BEntrandor(
    val nombre: String?,
    val descripcion: String?,
    val liga: DLiga? = null
) :Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(nombre)
        parcel?.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrandor> {
        override fun createFromParcel(parcel: Parcel): BEntrandor {
            return BEntrandor(parcel)
        }

        override fun newArray(size: Int): Array<BEntrandor?> {
            return arrayOfNulls(size)
        }
    }
}