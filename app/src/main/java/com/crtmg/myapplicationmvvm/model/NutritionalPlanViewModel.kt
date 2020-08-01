package com.crtmg.myapplicationmvvm.model

import com.crtmg.myapplicationmvvm.data.Food
import com.crtmg.myapplicationmvvm.data.NutritionalPlan
import java.util.*

class NutritionalPlanViewModel(var nutritionalPlan : NutritionalPlan = NutritionalPlan()) : Observable() {

    fun addFood(name: String, calories: Int){
        val food = Food(name, calories)
        nutritionalPlan.addFood(food)
        setChanged()
        notifyObservers()
    }

    fun showAddFoodDialog(){
        setChanged()
        notifyObservers(SHOW_ADD_FOOD_DIALOG)
    }

    companion object {
        const val SHOW_ADD_FOOD_DIALOG = "show add food dialog"
    }
}