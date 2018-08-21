package com.example.android.instantappsample.character_list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by lazy { ViewModelProviders.of(this)[CharacterListViewModel::class.java] }
    private lateinit var characterListAdapter: CharacterItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Timber.treeCount() < 1) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.fetchPage(1)
        viewModel.setupRecyclerViewPager(characterListRv)

        characterListAdapter = CharacterItemAdapter {

        }

        viewModel.pageData.observe(this, Observer {
            it?.results?.let(characterListAdapter.characterList::addAll)
            characterListAdapter.notifyDataSetChanged()
        })
        characterListRv.adapter = characterListAdapter
    }
}
