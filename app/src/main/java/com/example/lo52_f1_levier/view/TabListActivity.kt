package com.example.lo52_f1_levier.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lo52_f1_levier.R

import com.example.lo52_f1_levier.item.MenuContent
import kotlinx.android.synthetic.main.activity_tab_list.*
import kotlinx.android.synthetic.main.tab_list_content.view.*
import kotlinx.android.synthetic.main.tab_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [TabDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class TabListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (tab_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(tab_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, MenuContent.ITEMS, twoPane)
    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: TabListActivity,
        private val values: List<MenuContent.MainTab>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                // MenuContent initialise les "onglets" de base
                val item = v.tag as MenuContent.MainTab
                if (twoPane) {

                    // fragment par défault
                    var fragment : Fragment = TabDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(TabDetailFragment.ARG_ITEM_ID, item.id)
                        }
                    }
                    // En fontion du nom de "l'onglet" selectionné, on affiche le bon fragment
                    when(item.content) {
                        "Participants" -> fragment = ParticipantFragment().apply {
                            arguments = Bundle().apply {
                                putString(ParticipantFragment.ARG_ITEM_ID, item.id)
                            }
                        }
                        "Equipes" -> fragment = EquipeFragment().apply {
                            arguments = Bundle().apply {
                                putString(EquipeFragment.ARG_ITEM_ID, item.id)
                            }
                        }
                        "Courses" -> fragment = CourseFragment()
                        //TODO : ajouter les autres "onglets"
                    }
                    /*val fragment = TabDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(TabDetailFragment.ARG_ITEM_ID, item.id)
                        }
                    }*/
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.tab_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, TabDetailActivity::class.java).apply {
                        putExtra(TabDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tab_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.contentView.text = item.content

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val contentView: TextView = view.content
        }
    }
}
