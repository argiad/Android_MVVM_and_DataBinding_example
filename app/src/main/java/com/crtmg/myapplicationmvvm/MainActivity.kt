package com.crtmg.myapplicationmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crtmg.myapplicationmvvm.data.Food
import com.crtmg.myapplicationmvvm.model.NutritionalPlanViewModel
import com.crtmg.myapplicationmvvm.ui.main.MainFragment

class MainActivity : AppCompatActivity(), MainActivityCallback {

    private val viewModel = NutritionalPlanViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun showAddFoodDialog() {
        viewModel.nutritionalPlan.apply {
            addFood(Food("#${foods.count() + 1} NEW FOOD", 100))
        }
    }

    override fun getViewModel(): NutritionalPlanViewModel {
        return viewModel
    }
}

interface MainActivityCallback {
    fun showAddFoodDialog()
    fun getViewModel(): NutritionalPlanViewModel
}