package model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Receipt (
    @SerializedName("name") val name: String,
    @SerializedName("id")val id: String,
    @SerializedName("description")val description: String,
    @SerializedName("latitud")val latitude: String,
    @SerializedName("longitud")val longitude: String,
    @SerializedName("image")val image: String
): Parcelable
