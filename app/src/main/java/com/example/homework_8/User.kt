package com.example.homework_8

import android.os.Parcel
import android.os.Parcelable

data class User(var firstName: String, var lastName: String, var email: String) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "-",
        parcel.readString() ?: "-",
        parcel.readString() ?: "-"
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(email)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}