package com.jhwasghb.newstestapi.viewModels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhwasghb.newstestapi.models.ArticlesItem
import com.jhwasghb.newstestapi.models.Command
import com.jhwasghb.newstestapi.ui.activities.CLOSE_FRAGMENT_COMMAND

class CurrentNewsViewModel : ViewModel() {

    val command = MutableLiveData<Command>()

    var item = MutableLiveData<ArticlesItem>()


    fun closeFragment(view: View){
        command.value = Command(CLOSE_FRAGMENT_COMMAND)
    }
}