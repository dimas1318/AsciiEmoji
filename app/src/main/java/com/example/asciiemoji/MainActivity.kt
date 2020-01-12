package com.example.asciiemoji

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.asciiemoji.components.EmojiAdapter
import com.example.asciiemoji.components.OnEmojiClickListener
import com.example.asciiemoji.model.Emoji
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnEmojiClickListener {

    private lateinit var mViewModel: EmojiViewModel

    private lateinit var mAdapter: EmojiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        mViewModel = ViewModelProviders.of(this).get(EmojiViewModel::class.java)

        setupObservers()

        mViewModel.loadEmojies(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dark_mode, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_enable_dark_mode) {
            val currentMode: Int = AppCompatDelegate.getDefaultNightMode()
            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            delegate.applyDayNight()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onEmojiClick(emoji: Emoji) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.primaryClip = ClipData.newPlainText(null, emoji.emoji)

        Toast.makeText(this, R.string.copy_notification, Toast.LENGTH_LONG).show()
    }

    private fun initViews() {
        emoji_recycler_view.addItemDecoration(GridItemDecoration(this, R.dimen.margin_8dp))

        mAdapter = EmojiAdapter(this)
        emoji_recycler_view.adapter = mAdapter
    }

    private fun setupObservers() {
        mViewModel.emojies.observe(this, Observer {
            mAdapter.mEmojies = it
        })

        mViewModel.error.observe(this, Observer { processError(it) })
    }

    private fun processError(error: String?) {
        val text = error ?: getString(R.string.unknown_error)
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}