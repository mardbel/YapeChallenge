package repositories

import model.RecipeServiceResponse

interface ReceiptRepository {

    suspend fun getRecipes(): RecipeServiceResponse
}