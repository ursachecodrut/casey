package com.codrutursache.casey.util.mock

import com.codrutursache.casey.data.remote.response.AnalyzedInstructionResponse
import com.codrutursache.casey.data.remote.response.EquipmentResponse
import com.codrutursache.casey.data.remote.response.ExtendedIngredientResponse
import com.codrutursache.casey.data.remote.response.IngredientResponse
import com.codrutursache.casey.data.remote.response.MeasuresResponse
import com.codrutursache.casey.data.remote.response.MetricResponse
import com.codrutursache.casey.data.remote.response.RecipeInformationResponse
import com.codrutursache.casey.data.remote.response.RecipeListResponse
import com.codrutursache.casey.data.remote.response.RecipeResponse
import com.codrutursache.casey.data.remote.response.StepResponse
import com.codrutursache.casey.data.remote.response.UsResponse
import com.codrutursache.casey.data.remote.response.WinePairingResponse

object Mocks {

    // write me a recipeDto with some actual real values
    val recipeResponse = RecipeResponse(
        id = 1,
        title = "Chicken Pasta",
        image = "https://spoonacular.com/recipeImages/715497-312x231.jpg",
        imageType = "jpg"
    )

    // create a list of recipeDtos using a range
    val recipeListDto = RecipeListResponse(
        offset = 0,
        number = 10,
        totalResults = 100,
        results = (1..100).map { recipeResponse }
    )

    val recipeInfoMock = RecipeInformationResponse(
        cheap = false,
        aggregateLikes = 15,
        analyzedInstructionResponses = listOf(
            AnalyzedInstructionResponse(
                name = "",
                stepResponses = listOf(
                    StepResponse(
                        number = 1,
                        step = "Heat the oven to 400.",
                        ingredientResponses = emptyList(),
                        equipmentResponses = listOf(
                            EquipmentResponse(
                                id = 1,
                                name = "oven",
                                localizedName = "oven",
                                image = "oven.jpg"
                            )
                        ),
                        lengthResponse = null,
                    )
                )
            ),
            AnalyzedInstructionResponse(
                name = "",
                stepResponses = listOf(
                    StepResponse(
                        number = 2,
                        step = "Halve the squash, scoop and clean the seeds inside.",
                        ingredientResponses = listOf(
                            IngredientResponse(
                                id = 1,
                                name = "squash",
                                localizedName = "squash",
                                image = "buttnernut-squash.jpg"
                            ),
                            IngredientResponse(
                                id = 2,
                                name = "seeds",
                                localizedName = "seeds",
                                image = "sunflower-seeds.jpg"
                            )
                        ),
                        equipmentResponses = emptyList(),
                        lengthResponse = null,
                    )
                )
            ),
        ),
        cookingMinutes = 30,
        creditsText = "Spoonacular",
        cuisines = emptyList(),
        dairyFree = false,
        diets = listOf(
            "gluten free",
            "dairy free",
            "paleolithic",
            "primal",
            "whole 30"
        ),
        dishTypes = listOf(
            "side dish",
            "main course",
            "lunch",
            "dinner",
            "main dish",
        ),
        extendedIngredientResponses = listOf(
            ExtendedIngredientResponse(
                id = 1,
                aisle = "Spices and Seasonings",
                image = "pepper.jpg",
                consistency = "SOLID",
                name = "pepper",
                nameClean = "black pepper",
                original = "1/2 tsp black pepper",
                originalName = "black pepper",
                amount = 1.0,
                unit = "tsp",
                meta = listOf("black"),
                measuresResponse = MeasuresResponse(
                    usResponse = UsResponse(
                        amount = 1.0,
                        unitShort = "tsp",
                        unitLong = "teaspoon"
                    ),
                    metricResponse = MetricResponse(
                        amount = 1.0,
                        unitShort = "tsp",
                        unitLong = "teaspoon"

                    )
                ),
            ),
            ExtendedIngredientResponse(
                id = 2,
                aisle = "Canned and Jarred",
                image = "tomatoes-canned.png",
                consistency = "SOLID",
                name = "canned tomatoes",
                nameClean = "crushed tomatoes",
                original = "16 oz can crushed tomatoes",
                originalName = "crushed tomatoes",
                amount = 16.0,
                unit = "oz",
                meta = listOf("crushed", "canned"),
                measuresResponse = MeasuresResponse(
                    usResponse = UsResponse(
                        amount = 16.0,
                        unitShort = "oz",
                        unitLong = "grams"
                    ),
                    metricResponse = MetricResponse(
                        amount = 453.592,
                        unitShort = "g",
                        unitLong = "grams"
                    )
                ),
            ),
            ExtendedIngredientResponse(
                id = 3,
                aisle = "Oil, Vinegare, Salad Dressing",
                image = "olive-oild.jpg",
                consistency = "LIQUID",
                name = "extra virgin olive oil",
                nameClean = "extra virgin olive oil",
                original = "extra virgin olive oil",
                originalName = "extra virgin olive oil",
                amount = 2.0,
                unit = "servings",
                meta = emptyList(),
                measuresResponse = MeasuresResponse(
                    usResponse = UsResponse(
                        amount = 2.0,
                        unitShort = "servings",
                        unitLong = "servings"
                    ),
                    metricResponse = MetricResponse(
                        amount = 2.0,
                        unitShort = "servings",
                        unitLong = "servings"

                    )
                ),
            )
        ),
        gaps = "no",
        glutenFree = false,
        healthScore = 66,
        id = 1,
        image = "https://spoonacular.com/recipeImages/715497-312x231.jpg",
        imageType = "jpg",
        instructions = "<ol><li><span></span>Heat the oven to 400. </li><li>Halve the squash, scoop and clean the seeds inside. </li><li>Drizzle with a tsp of extra virgin olive oil in each half, add a little salt. </li><li>Take each half and turn face down on a prepared cookie sheet and bake in the oven for 50 minutes. </li><li>Remove and allow to cool for 5-10 minutes. Take a fork and start to shred the insides.</li><li>While roasting the spaghetti squash, brown the lean ground beef. </li><li>Saute the onion and garlic if using fresh veggies. </li><li>Drain the meat sauce and then mix the onion, garlic, black pepper, and meat and place on low to medium heat. </li><li>Add the crushed tomatoes, basil, and pepper, stir. </li><li>Allow to simmer while spaghetti squash is roasting, stirring frequently.</li><li>Scoop the spaghetti squash onto a bowl, cover with meat sauce, and add a pinch of parmesan if desired. </li></ol>",
        lowFodmap = false,
        occasions = emptyList(),
        originalId = null,
        preparationMinutes = 10,
        pricePerServing = 100.0,
        readyInMinutes = 40,
        servings = 4,
        sourceName = "Spoonacular",
        sourceUrl = "https://spoonacular.com/recipeImages/715497-312x231.jpg",
        spoonacularScore = 100.0,
        spoonacularSourceUrl = "https://spoonacular.com/recipeImages/715497-312x231.jpg",
        summary = "Spaghetti Squash & Tomato Basil Meat Sauce takes approximately <b>1 hour and 15 minutes</b> from beginning to end. This recipe serves 2 and costs \$5.07 per serving. One portion of this dish contains around <b>56g of protein</b>, <b>29g of fat</b>, and a total of <b>669 calories</b>. This recipe from Pink When has 15 fans. If you have pepper, canned tomatoes, ground beef, and a few other ingredients on hand, you can make it. It is a good option if you're following a <b>gluten free, dairy free, paleolithic, and primal</b> diet. It works well as a main course. Overall, this recipe earns an <b>amazing spoonacular score of 94%</b>. Try <a href=\\\"https://spoonacular.com/recipes/spaghetti-squash-tomato-basil-meat-sauce-1422837\\\">Spaghetti Squash & Tomato Basil Meat Sauce</a>, <a href=\\\"https://spoonacular.com/recipes/spaghetti-squash-tomato-basil-meat-sauce-1407627\\\">Spaghetti Squash & Tomato Basil Meat Sauce</a>, and <a href=\\\"https://spoonacular.com/recipes/spaghetti-squash-tomato-basil-meat-sauce-1399705\\\">Spaghetti Squash & Tomato Basil Meat Sauce</a> for similar recipes.",
        sustainable = false,
        title = "Spaghetti Squash & Tomato Basil Meat Sauce",
        vegan = false,
        vegetarian = false,
        veryHealthy = true,
        veryPopular = false,
        weightWatcherSmartPoints = 10,
        winePairingResponse = WinePairingResponse(
            pairedWines = emptyList(),
            pairingText = "",
            productMatches = emptyList()
        )
    )
}