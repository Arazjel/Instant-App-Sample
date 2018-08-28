package com.example.android.instantappsample.character_list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.android.instantappsample.base.getInstantAppCookie
import com.google.android.gms.auth.api.credentials.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import kotlinx.android.synthetic.main.activity_character_list.*
import timber.log.Timber
import java.nio.charset.Charset


public class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by lazy { ViewModelProviders.of(this@CharacterListActivity)[CharacterListViewModel::class.java] }
    private lateinit var characterListAdapter: CharacterItemAdapter
    private val credentialsClient: CredentialsClient by lazy { Credentials.getClient(this) }

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
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("""https://potatoinc.com/character/${it.id}"""))
            startActivity(intent)
        }

        viewModel.pageData.observe(this@CharacterListActivity, Observer {
            it?.results?.let(characterListAdapter.characterList::addAll)
            characterListAdapter.notifyDataSetChanged()
            characterListPb.visibility = View.GONE
        })
        characterListRv.adapter = characterListAdapter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Timber.e(String(packageManager.instantAppCookie, Charset.defaultCharset()))
        }

        val request = CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .setAccountTypes(IdentityProviders.GOOGLE, IdentityProviders.TWITTER)
                .build()

        credentialsClient.request(request)
                .addOnCompleteListener {
                    Timber.e("complete: ${it.isSuccessful}")
                    if (it.isSuccessful) {
                        return@addOnCompleteListener
                    }

                    when (it.exception) {
                        is ResolvableApiException -> {
                            val hintRequest = HintRequest.Builder()
                                    .setHintPickerConfig(CredentialPickerConfig.Builder()
                                            .setShowCancelButton(true)
                                            .build())
                                    .setEmailAddressIdentifierSupported(true)
                                    .setAccountTypes(IdentityProviders.GOOGLE)
                                    .build()

                            val intent = credentialsClient.getHintPickerIntent(hintRequest)

                            try {
                                startIntentSenderForResult(intent.intentSender, RC_HINT, null, 0, 0, 0)
                            } catch (e: IntentSender.SendIntentException) {
                                Timber.e(e)
                            }
                        }
                        is ApiException -> {

                        }
                    }

                }
    }

    override fun onResume() {
        super.onResume()
        Timber.e(String(getInstantAppCookie(), Charset.defaultCharset()))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_HINT) {
            if (resultCode == RESULT_OK) {
                val credential = data?.getParcelableExtra<Credential>(Credential.EXTRA_KEY);
                Timber.e(credential?.id)
            } else {
                Timber.e("Hint Read: NOT OK")
            }
        }
    }

    companion object {
        private const val RC_HINT = 123
    }

}
