package uz.gita.paymedemo.presentation.view.splash.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.paymedemo.R
import uz.gita.paymedemo.data.local.intro.SharedPrefToken
import uz.gita.paymedemo.databinding.ScreenSplashBinding
import uz.gita.paymedemo.presentation.viewmodel.splash.SplashViewModel
import uz.gita.paymedemo.presentation.viewmodel.splash.impl.SplashViewModelImpl

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    lateinit var shared: SharedPrefToken


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared = SharedPrefToken(requireContext())
        viewModel.openLanguageScreen.observe(this@SplashScreen, openLanguageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
    }

    private val openLanguageObserver = Observer<Unit> {
        if (shared.token.isEmpty()) {
            findNavController().navigate(R.id.action_splashScreen_to_introScreen)
        } else {
            if (shared.id == 0) {
                findNavController().navigate(R.id.action_splashScreen_to_languageScreen)
            } else {
                findNavController().navigate(R.id.action_splashScreen_to_signUPScreen)
            }
        }
    }
}