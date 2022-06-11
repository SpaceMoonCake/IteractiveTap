package com.spacemooncake.iteractivetap.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spacemooncake.iteractivetap.databinding.MainFragmentBinding
import com.spacemooncake.iteractivetap.domain.entities.Video
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.HttpCookie.parse
import java.net.URI
import java.util.logging.Level.parse

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var sourceVideo : List<Video>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceVideo = viewModel.getVideoFromLocalStorage()
        bindView()
    }

    private fun bindView() {
        with(binding){
            video.setVideoURI(Uri.parse(sourceVideo[0].sourceVideo))
            video.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}