package com.example.randomduck.data.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.randomduck.data.adapter.DuckAdapter
import com.example.randomduck.data.model.Duck
import com.example.randomduck.databinding.FragmentDuckListBinding

class DuckListFragment : Fragment() {
    private lateinit var binding: FragmentDuckListBinding
    private val viewModel:DuckListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDuckListBinding.inflate(inflater,
            container,
            false,
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = DuckAdapter(::onShowDetail)
        binding.duckList.adapter = adapter

        val observer = Observer<List<Duck>> {
            adapter.submitList(it)
        }
        viewModel.duck.observe(viewLifecycleOwner, observer)
    }

    private fun onShowDetail(duck: Duck, view: View) {
        val action = DuckListFragmentDirections.actionDuckListFragmentToDuckDetailFragment(duck)
        view.findNavController().navigate(action)
    }
}