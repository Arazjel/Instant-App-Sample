package com.example.android.instantappsample.character_list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import android.support.v7.widget.RecyclerView
import com.example.android.instantappsample.base.MyObserver
import com.example.android.instantappsample.base.RestClient
import com.example.android.instantappsample.base.data.CharacterPage
import io.reactivex.schedulers.Schedulers

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
                })
    }

    fun setupRecyclerViewPager(
            recyclerView: RecyclerView,
            onNextPageFetch: () -> Unit
    ) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    nextPage?.let(::fetchPage)
                    onNextPageFetch.invoke()
                }
            }
        })
    }
}