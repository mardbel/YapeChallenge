package model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Receipt (
    @SerializedName("name") val name: String,
    @SerializedName("id")val id: String,
    @SerializedName("latitud")val latitude: Double,
    @SerializedName("longitud")val longitude: Double,
    @SerializedName("image")val image: String
): Parcelable
