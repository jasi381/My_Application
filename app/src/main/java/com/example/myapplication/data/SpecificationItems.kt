package com.example.myapplication.data

import android.os.Parcel
import android.os.Parcelable

data class SpecificationItems (
    val _id: String,
    val is_default_selected: Boolean,
    val name: List<String>,
    val price: Int,
    val sequence_number: Int,
    val specification_group_id: String,
    val unique_id: Int
)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.createStringArrayList()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeByte(if (is_default_selected) 1 else 0)
        parcel.writeStringList(name)
        parcel.writeInt(price)
        parcel.writeInt(sequence_number)
        parcel.writeString(specification_group_id)
        parcel.writeInt(unique_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SpecificationItems> {
        override fun createFromParcel(parcel: Parcel): SpecificationItems {
            return SpecificationItems(parcel)
        }

        override fun newArray(size: Int): Array<SpecificationItems?> {
            return arrayOfNulls(size)
        }
    }
}