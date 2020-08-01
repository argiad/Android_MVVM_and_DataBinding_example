package com.crtmg.myapplicationmvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.crtmg.myapplicationmvvm.MainActivityCallback
import com.crtmg.myapplicationmvvm.R
import com.crtmg.myapplicationmvvm.adapter.MyAdapter
import com.crtmg.myapplicationmvvm.databinding.MainFragmentBinding
import com.crtmg.myapplicationmvvm.model.NutritionalPlanViewModel
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.util.*


class MainFragment : Fragment(), Observer {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val delegate: MainActivityCallback?
        get() {
            return this.context as? MainActivityCallback
        }

    private val viewModel: NutritionalPlanViewModel?
        get() {
            return delegate?.getViewModel()
        }

    private lateinit var mFragmentRootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<MainFragmentBinding>(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )
        binding.viewModel = viewModel
        mFragmentRootView = binding.root

        viewModel?.addObserver(this)

        val adapter = MyAdapter(viewModel!!)
        mFragmentRootView.rvFoods.layoutManager = LinearLayoutManager(context)
        mFragmentRootView.rvFoods.adapter = adapter

        return mFragmentRootView
    }


    override fun update(o: Observable?, arg: Any?) {
        if (arg.toString() == NutritionalPlanViewModel.SHOW_ADD_FOOD_DIALOG) {
            delegate?.showAddFoodDialog()
        }
    }


}