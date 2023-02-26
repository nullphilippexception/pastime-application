package at.nullphilippexception.pastimeapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.nullphilippexception.pastimeapplication.EHobbyTypes.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    val uiData: MutableLiveData<Hobby> by lazy {
        MutableLiveData<Hobby>()
    }
    val viewModelEvent: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        fetchHobbyData(ALL)
    }

    fun fetchHobbyData(type: EHobbyTypes) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                uiData.postValue(
                    if(type == ALL) {
                        HobbyApi().getRandomHobby()
                    } else {
                        HobbyApi().getTypedHobby(type)
                    }
                )
            }
            catch(e: Exception) {
                viewModelEvent.postValue(e.message)
            }
        }
    }
}

enum class EHobbyTypes {
    ALL,
    EDUCATION,
    RECREATIONAL,
    SOCIAL,
    DIY,
    CHARITY,
    COOKING,
    RELAXATION,
    MUSIC,
    BUSYWORK
}