package com.jhwasghb.newstestapi.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jhwasghb.newstestapi.R
import com.jhwasghb.newstestapi.databinding.ActivityMainBinding
import com.jhwasghb.newstestapi.models.ArticlesItem
import com.jhwasghb.newstestapi.ui.fragments.CurrentFragment
import com.jhwasghb.newstestapi.viewModels.NewsViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


val SHOW_NEWS_COMMAND = 1001
val CLOSE_FRAGMENT_COMMAND = 1002

class MainActivity : AppCompatActivity() {
    private lateinit var newsViewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingUtil = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        newsViewModel.getAllNews("us")

        bindingUtil.newsViewModel = newsViewModel


        newsViewModel.commands.observe(this, Observer {
            when(it.command){
                SHOW_NEWS_COMMAND -> openFragment(it.data as ArticlesItem)
                CLOSE_FRAGMENT_COMMAND -> closeFragment(it.data as Fragment)
            }
        })
    }


    fun openFragment(item: ArticlesItem){
        container.visibility = View.VISIBLE
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, CurrentFragment.newInstance(item),"Current")
            .commit()

    }

    fun closeFragment(instance :Fragment){
        supportFragmentManager
            .beginTransaction()
            .remove(instance)
            .commit()

        container.visibility = View.INVISIBLE
    }

}
