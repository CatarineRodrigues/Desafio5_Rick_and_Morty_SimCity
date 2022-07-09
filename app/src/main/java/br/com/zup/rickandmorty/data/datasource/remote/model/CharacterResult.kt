package br.com.zup.rickandmorty.data.datasource.remote.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "character")
data class CharacterResult(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("gender")
    var gender: String = "",
    @SerializedName("image")
    var image: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("species")
    var species: String = "",
    @SerializedName("status")
    var status: String = "",
) : Parcelable