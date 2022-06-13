package com.spacemooncake.iteractivetap.ui.video


import android.animation.ObjectAnimator
import android.graphics.Path
import android.graphics.RectF
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.addListener
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
    private var interactiveIsRunning: Boolean = false

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
            interactiveIsRunning = true
            video.setVideoURI(Uri.parse(sourceVideo[0].sourceVideo))
            video.start()
            butterflyFly()
            Thread(Runnable {
                while (interactiveIsRunning) {
                    val currentDuration = binding.video.duration
                    val currentPosition = binding.video.currentPosition
                    val progress = (currentPosition * 100) / currentDuration
                    binding.timerLeft.progress = progress
                    binding.timerRight.progress = progress
                }
            }).start()
            butterflyButton.setOnClickListener {
                interactiveIsRunning = false
                butterflyButton.visibility = View.GONE
                video.setVideoURI(Uri.parse(sourceVideo[3].sourceVideo))
                video.start()
            }

        }
    }


    private fun butterflyFly() {
        val path1 = Path()
        var rectf = RectF(400F, 100F, 700F, 2000F)
//      var rectf2 = RectF(400F, 200F, 700F, 1000F)
//      var rectf3 = RectF(400F, 200F, 1000F, 2000F)
        path1.addOval(rectf, Path.Direction.CCW)
//        path1.addOval(rectf2, Path.Direction.CW )
//        path1.addOval(rectf3, Path.Direction.CCW )
        var pathAnimator = ObjectAnimator
            .ofFloat(
                binding.butterflyButton,
                "x",
                "y", path1
            )
        pathAnimator.duration = 9000
        pathAnimator.start()
        pathAnimator.addListener(
            onEnd = {
                with(binding) {
                    if (interactiveIsRunning) {
                        timerLeft.visibility = View.GONE
                        timerRight.visibility = View.GONE
                        butterflyButton.visibility = View.GONE
                        textTask.visibility = View.GONE
                        failedTask.visibility = View.VISIBLE
                    }
                }
            }
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = VideoFragment()
    }
}