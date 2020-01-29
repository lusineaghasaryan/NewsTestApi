package com.jhwasghb.newstestapi.viewModels

import android.app.Application
import android.util.Log
import android.util.Log.d
import android.widget.LinearLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhwasghb.newstestapi.adapters.NewsRecyclerViewAdapter
import com.jhwasghb.newstestapi.models.ArticlesItem
import com.jhwasghb.newstestapi.models.Command
import com.jhwasghb.newstestapi.models.Response
import com.jhwasghb.newstestapi.repositories.NewsRepository
import com.jhwasghb.newstestapi.ui.activities.SHOW_NEWS_COMMAND
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class NewsViewModel(application: Application): AndroidViewModel(application) {

    var newsRepository:NewsRepository
    private val compositeDisposable =  CompositeDisposable()
    val commands = MutableLiveData<Command>()

    var adapter: NewsRecyclerViewAdapter = NewsRecyclerViewAdapter(application, emptyList())
        .apply {
            itemClick = {
                i, item -> d("RRRR", "Position = $i")
                commands.value = Command(SHOW_NEWS_COMMAND, item)
            }
        }
    var layoutManager: LinearLayoutManager = LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false)

    init {
        newsRepository = NewsRepository()

    }


    fun getAllNews(country:String) {
         newsRepository.getAllNews(country)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<Response>{
                override fun onSuccess(t: Response) {


                    d("TTTTTTT", "${t.totalResults}")
                    adapter.setNews((t.articles as List<ArticlesItem>?)!!)

                }

                override fun onSubscribe(d: Disposable) {
                    d("TTTTTTT", "Disposable")
                    compositeDisposable.add(d)

                }

                override fun onError(e: Throwable) {
                    d("TTTTTTT", "${e.message}")

                }

            })
}
}