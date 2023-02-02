package model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReceiptServiceResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("receipt") val receipt: List<Receipt>,
    @SerializedName("message") val message: String
): Parcelable
