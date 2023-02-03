package model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeServiceResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("receipt") val recipe: List<Recipe>,
    @SerializedName("message") val message: String
): Parcelable
