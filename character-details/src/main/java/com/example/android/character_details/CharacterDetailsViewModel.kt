package com.example.android.character_details

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent
import com.example.android.instantappsample.base.MyObserver
import com.example.android.instantappsample.base.RestClient
import com.example.android.instantappsample.base.data.Character
import io.reactivex.schedulers.Schedulers

class CharacterDetailsViewModel : ViewModel() {

    private val apiInterface = RestClient.apiInterface

    val characterDetailsData = MutableLiveData<Character>()

    fun fetchCharacterDetails(intent: Intent) {
        intent.data?.pathSegments?.last()?.toLong()?.let { id ->
            apiInterface.fetchCharacterDetails(id)
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : MyObserver<Character>() {
                        override fun onNext(character: Character) {
                            characterDetailsData.postValue(character)
                        }
                    })
        }
    }
}