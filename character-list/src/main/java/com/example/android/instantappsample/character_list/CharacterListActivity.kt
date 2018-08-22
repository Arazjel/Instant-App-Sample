package com.example.android.instantappsample.character_list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_character_list.*
import timber.log.Timber

public class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by lazy { ViewModelProviders.of(this)[CharacterListViewModel::class.java] }
    private lateinit var characterListAdapter: CharacterItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)
        if (Timber.treeCount() < 1) {
            Timber.plant(Timber.DebugTree())
        }
        viewModel.fetchPage(1)
        viewModel.setupRecyclerViewPager(characterListRv) {
            characterListPb.visibility = View.VISIBLE
        }

        characterListAdapter = CharacterItemAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("""https://android.example.com/character/${it.id}"""))
            startActivity(intent)
        }

        viewModel.pageData.observe(this, Observer {
            it?.results?.let(characterListAdapter.characterList::addAll)
            characterListAdapter.notifyDataSetChanged()
            characterListPb.visibility = View.GONE
        })
        characterListRv.adapter = characterListAdapter


    }
}
