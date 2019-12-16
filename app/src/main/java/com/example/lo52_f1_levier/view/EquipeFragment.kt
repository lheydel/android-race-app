package com.example.lo52_f1_levier.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.dummy.DummyContent
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_detail.*
import kotlinx.android.synthetic.main.fragment_participant.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EquipeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EquipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EquipeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var item: DummyContent.MainTab? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(TabDetailFragment.ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = DummyContent.ITEM_MAP[it.getString(TabDetailFragment.ARG_ITEM_ID)]
                activity?.toolbar_layout?.title = item?.content
            }
        }
    }

    // Pour agir sur les éléments du layout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // sale mais fonctionne
        var fragment : Fragment
        fragment = EquipeTabAjouterFragment().apply {}
        childFragmentManager
            .beginTransaction()
            .replace(R.id.toReplaceWithFragment, fragment)
            .commit()

        // listener sur le TabLayout
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                // Ajouter
                if(tab?.position == 0){
                    fragment = EquipeTabAjouterFragment().apply {}
                    childFragmentManager
                        .beginTransaction()
                        .replace(R.id.toReplaceWithFragment, fragment)
                        .commit()
                }
                // Consult...
                if(tab?.position == 1){
                    fragment = EquipeTabConsultFragment().apply {}
                    childFragmentManager
                        .beginTransaction()
                        .replace(R.id.toReplaceWithFragment, fragment)
                        .commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipe, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }*/

    /*override fun onDetach() {
        super.onDetach()
        listener = null
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    /*companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EquipeFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EquipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}
