package com.spacemooncake.iteractivetap.ui.video

import android.animation.ObjectAnimator
import android.graphics.Path
import android.graphics.RectF
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.spacemooncake.iteractivetap.databinding.VideoFragmentBinding
import com.spacemooncake.iteractivetap.domain.entities.Video
import com.spacemooncake.iteractivetap.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class VideoFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private var _binding: VideoFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var sourceVideo: List<Video>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceVideo = viewModel.getVideoFromLocalStorage()
        bindView()
    }


    private fun bindView() {
        with(binding) {
            video.setVideoURI(Uri.parse(sourceVideo[0].sourceVideo))
            video.start()
            butterflyFly()
        }
    }

    private fun butterflyFly() {
        val path1 = Path()
        var rectf = RectF(400F, 100F, 700F, 2000F)
//      var rectf2 = RectF(400F, 200F, 700F, 1000F)
//      var rectf3 = RectF(400F, 200F, 1000F, 2000F)
        path1.addOval(rectf, Path.Direction.CCW )
//        path1.addOval(rectf2, Path.Direction.CW )
//        path1.addOval(rectf3, Path.Direction.CCW )
        var pathAnimator = ObjectAnimator
            .ofFloat(binding.butterflyButton,
            "x",
            "y", path1)

        pathAnimator.duration = 5000L
        pathAnimator.start()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = VideoFragment()
    }
}