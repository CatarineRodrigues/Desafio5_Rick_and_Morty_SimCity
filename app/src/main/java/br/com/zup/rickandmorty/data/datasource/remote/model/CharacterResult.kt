package br.com.zup.rickandmorty.data.datasource.remote.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResult(
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("species")
    val species: String = "",
    @SerializedName("status")
    val status: String = "",
) : Parcelable