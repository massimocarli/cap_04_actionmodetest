package uk.co.massimocarli.actionmodetest

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode

class MainActivity : AppCompatActivity() {

  /*
   * The Tag for the Log
   */
  private val LOG_TAG = "MainActivity"

  private var actionMode: ActionMode? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  /**
   * Called when we press the startActionMode button
   */
  fun startActionMode(button: View) {
    if (actionMode != null) {
      // We don't want it created more times
      return
    }
    actionMode = startSupportActionMode(object : ActionMode.Callback {

      override fun onCreateActionMode(
        mode: ActionMode,
        menu: Menu
      ): Boolean {
        Log.i(LOG_TAG, "onCreateActionMode")
        // We load and put the menu configuration file
        val inflater = mode.getMenuInflater()
        inflater.inflate(R.menu.action_mode_menu, menu)
        return true
      }

      override fun onPrepareActionMode(
        mode: ActionMode,
        menu: Menu
      ): Boolean {
        Log.i(LOG_TAG, "onPrepareActionMode")
        return false
      }

      override fun onDestroyActionMode(mode: ActionMode) {
        Log.i(LOG_TAG, "onDestroyActionMode")
        actionMode = null
      }

      override fun onActionItemClicked(
        mode: ActionMode,
        item: MenuItem
      ): Boolean {
        Log.i(LOG_TAG, "onActionItemClicked")
        var selectedTitle = item.title
        if (TextUtils.isEmpty(selectedTitle)) {
          selectedTitle = "Unknown"
        }
        Toast.makeText(
          applicationContext,
          selectedTitle.toString(),
          Toast.LENGTH_SHORT
        ).show()
        return false
      }
    })
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }
}
