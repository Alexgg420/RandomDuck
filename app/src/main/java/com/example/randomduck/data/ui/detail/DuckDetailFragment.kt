package com.example.randomduck.data.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.randomduck.data.ui.list.DuckListFragmentArgs
import com.example.randomduck.databinding.FragmentDuckDetailBinding

class DuckDetailFragment : Fragment() {
    private lateinit var binding: FragmentDuckDetailBinding
    private val args: DuckListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDuckDetailBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.duckImage.load(args.duck.image)
    }
}