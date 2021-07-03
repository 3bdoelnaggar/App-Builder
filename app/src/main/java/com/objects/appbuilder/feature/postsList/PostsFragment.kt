package com.objects.appbuilder.feature.postsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.objects.appbuilder.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostsFragment : Fragment() {


    private val navArg: PostsFragmentArgs by navArgs()
    val viewModel: PostsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.post_list_fragment, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)

            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchData(navArg.baseUrl, navArg.api)
        viewModel.stateLiveData.observe(viewLifecycleOwner){
            it?.let{
                render(it)
            }
        }
    }

    private fun render(state: PostsState) {
        when(state){
            PostsState.Error -> {
                Toast.makeText(requireContext(),"ERROR",Toast.LENGTH_LONG).show()

            }
            is PostsState.Success -> {
                Toast.makeText(requireContext(),state.data,Toast.LENGTH_LONG).show()
            }
        }
    }

}