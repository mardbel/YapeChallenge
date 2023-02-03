package repositories

import api.ReceiptService
import model.RecipeServiceResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReceiptRepositoryImp @Inject constructor(
    private val recipeService: ReceiptService
) : ReceiptRepository {

    override suspend fun getRecipes(): RecipeServiceResponse = recipeService.getReceipts()
}
