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
    private var isInteractiveVin: Boolean = false

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
        startInteractive()
    }


    private fun startInteractive() {
        interactiveIsRunning = true
        startVideo()
        startTimer()
        flyButterfly()
        setListenerToCatchButterfly()
    }

    private fun setListenerToCatchButterfly() {
        binding.butterflyButton.setOnClickListener {
            interactiveIsRunning = false
            isInteractiveVin = true
            andInteractive(isInteractiveVin)
        }
    }

    private fun andInteractive(isInteractiveVin: Boolean) {
        if (isInteractiveVin) {
            with(binding) {
                interactiveGroup.visibility = View.GONE
                video.setVideoURI(Uri.parse(sourceVideo[1].sourceVideo))
                video.start()
            }
        } else {
            with(binding) {
                interactiveGroup.visibility = View.GONE
                failedTask.visibility = View.VISIBLE
            }
        }
    }

    private fun startTimer() {
        with(binding) {
            Thread{
                while (interactiveIsRunning) {
                    val currentDuration = video.duration
                    val currentPosition = video.currentPosition
                    val progress = (currentPosition * 100) / currentDuration
                    timerLeft.progress = progress
                    timerRight.progress = progress
                }
            }.start()
        }
    }

    private fun startVideo() {
        with(binding) {
            video.setVideoURI(Uri.parse(sourceVideo[0].sourceVideo))
            video.start()
        }
    }


    private fun flyButterfly() {
        val pathAnimator: ObjectAnimator = ObjectAnimator
            .ofFloat(binding.butterflyButton, "x", "y", getPathButterfly())
        pathAnimator.duration = 9500
        pathAnimator.start()
        pathAnimator.addListener(
            onEnd = {
                if (interactiveIsRunning) {
                    andInteractive(isInteractiveVin)
                }
            }
        )

    }

    private fun getPathButterfly(): Path {
        val path = Path()
        val rect = RectF(400F, 100F, 700F, 2000F)
        path.addOval(rect, Path.Direction.CCW)
        return path
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = VideoFragment()
    }
}