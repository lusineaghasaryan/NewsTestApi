package com.jhwasghb.newstestapi.ui.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jhwasghb.newstestapi.R
import com.jhwasghb.newstestapi.databinding.FragmentCurrentBinding
import com.jhwasghb.newstestapi.models.ArticlesItem
import com.jhwasghb.newstestapi.ui.activities.CLOSE_FRAGMENT_COMMAND
import com.jhwasghb.newstestapi.viewModels.CurrentNewsViewModel
import com.jhwasghb.newstestapi.viewModels.NewsViewModel


class CurrentFragment(var item: ArticlesItem): Fragment() {

    private lateinit var binding:FragmentCurrentBinding
    private lateinit var currentViewModel:CurrentNewsViewModel
    private lateinit var newsViewModel:NewsViewModel


    companion object{
        fun newInstance(item: ArticlesItem): CurrentFragment{
            return CurrentFragment(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentViewModel = ViewModelProviders.of(this).get(CurrentNewsViewModel::class.java)
        newsViewModel = ViewModelProviders.of(activity!!).get(NewsViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_current, container, false)
        binding.viewModel = currentViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        currentViewModel.item.value = item
        currentViewModel.command.observe(this, Observer {
            when(it.command){
                CLOSE_FRAGMENT_COMMAND -> {
                    it.data = this
                    newsViewModel.commands.value = it
                }

            }
        })

    }
}