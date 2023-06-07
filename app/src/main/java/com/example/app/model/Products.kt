package com.example.app.model

import android.os.Parcel
import android.os.Parcelable

data class Products(
    var imagem: String = "",
    var codigo: String = "",
    var nome: String = "",
    var valorunitario: Double?,
    var grupo: String = "",
    var ncm: Double?,
    var ipi: Double?,
    var icmssp: Double?,
    var icmsforasp: Double?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString()!!,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imagem)
        parcel.writeString(codigo)
        parcel.writeString(nome)
        parcel.writeValue(valorunitario)
        parcel.writeString(grupo)
        parcel.writeValue(ncm)
        parcel.writeValue(ipi)
        parcel.writeValue(icmssp)
        parcel.writeValue(icmsforasp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Products> {
        override fun createFromParcel(parcel: Parcel): Products {
            return Products(parcel)
        }

        override fun newArray(size: Int): Array<Products?> {
            return arrayOfNulls(size)
        }
    }
}