package com.example.android.instantappsample.character_list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import android.support.v7.widget.RecyclerView
import com.example.android.instantappsample.MyObserver
import com.example.android.instantappsample.RestClient
import com.example.android.instantappsample.data.CharacterPage
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class CharacterListViewModel : ViewModel() {

    private val apiInterface = RestClient.apiInterface
    private var nextPage: Long? = 0

    val pageData = MutableLiveData<CharacterPage>()

    fun fetchPage(page: Long) {
        apiInterface.getCharacterPage(page)
                .subscribeOn(Schedulers.io())
                .subscribe(object : MyObserver<CharacterPage>() {
                    override fun onNext(t: CharacterPage) {
                        pageData.postValue(t)
                        val uri = Uri.parse(t.info.next)
                        nextPage = uri.getQueryParameter("page")?.toLong()
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)
                    }
                })
    }

    fun setupRecyclerViewPager(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    nextPage?.let(::fetchPage)
                }
            }
        })
    }
}