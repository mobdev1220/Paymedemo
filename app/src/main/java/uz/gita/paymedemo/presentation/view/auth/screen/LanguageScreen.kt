package uz.gita.paymedemo.presentation.view.auth.screen

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.paymedemo.BuildConfig
import uz.gita.paymedemo.R
import uz.gita.paymedemo.data.local.SharedPrefToken
import uz.gita.paymedemo.databinding.ScreenLanguageBinding
import uz.gita.paymedemo.presentation.viewmodel.auth.LanguageVIewModel
import uz.gita.paymedemo.presentation.viewmodel.auth.impl.LanguageVIewModelImpl
import uz.gita.paymedemo.utils.LocaleHelper
import java.util.*

@AndroidEntryPoint
class LanguageScreen : Fragment(R.layout.screen_language) {
    private val binding by viewBinding(ScreenLanguageBinding::bind)
    private val viewModel: LanguageVIewModel by viewModels<LanguageVIewModelImpl>()
    private var shared: SharedPrefToken? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared = SharedPrefToken(requireContext())
        viewModel.openSignUpScreen.observe(this@LanguageScreen, openSignUpObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        paymeVersion.text =
            resources.getString(R.string.text_splash_payme, BuildConfig.VERSION_NAME)
        rusButton.setOnClickListener {
            shared?.language = 1
            LocaleHelper.setLocale(requireContext(), "ru")
            viewModel.openSingUp()
        }
        uzButton.setOnClickListener {
            shared?.language = 2
            LocaleHelper.setLocale(requireContext(), "uz")
            viewModel.openSingUp()
        }
        engButton.setOnClickListener {
            LocaleHelper.setLocale(requireContext(), "en")
            shared?.language = 3
            viewModel.openSingUp()
        }
    }

    private fun onOptionsItemSelected(id: Int) {
        var languageToLoad = "en"
        languageToLoad = when (id) {
            1 -> {
                "ru"
            }
            2 -> {
                "uz"
            }
            else -> {
                "en"
            }
        }
        val locale = Locale(languageToLoad);
        Locale.setDefault(locale);
        val config = Configuration();
        config.locale = locale;
        requireContext().resources.updateConfiguration(
            config,
            requireContext().resources.displayMetrics
        )
    }

    private val openSignUpObserver = Observer<Unit> {
        findNavController().navigate(R.id.action_languageScreen_to_policeScreen)
    }
}