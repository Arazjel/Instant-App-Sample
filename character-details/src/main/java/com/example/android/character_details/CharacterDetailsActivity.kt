package com.example.android.character_details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_character_details.*
import timber.log.Timber

class CharacterDetailsActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this)[CharacterDetailsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        if (Timber.treeCount() < 1) {
            Timber.plant(Timber.DebugTree())
        }

        viewModel.fetchCharacterDetails(intent)
        viewModel.characterDetailsData.observe(this, Observer { data ->
            data?.let {
                Glide.with(this@CharacterDetailsActivity)
                        .load(it.image)
                        .into(characterImageIv)

                characterNameTv.text = it.name
                characterStatusTv.text = it.status
                characterSpeciesTv.text = it.species
            }
        })

    }

    fun onCreate() {

    }
}