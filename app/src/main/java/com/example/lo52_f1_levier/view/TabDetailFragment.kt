package com.example.lo52_f1_levier.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_tab_detail.*

/**
 * A fragment representing a single tab detail screen.
 * This fragment is either contained in a [TabListActivity]
 * in two-pane mode (on tablets) or a [TabDetailActivity]
 * on handsets.
 */
class TabDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: DummyContent.MainTab? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                activity?.toolbar_layout?.title = item?.content
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*when (item?.content) {
            "Participants" -> return inflater.inflate(R.layout.fragment_participant, container, false)
            "Equipes" -> return inflater.inflate(R.layout.fragment_participant, container, false)
        }*/
        val rootView = inflater.inflate(R.layout.tab_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            //rootView.tab_detail.text = it.details
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
