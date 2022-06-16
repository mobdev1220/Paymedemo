package uz.gita.paymedemo.presentation.view.intro.pager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.paymedemo.R
import uz.gita.paymedemo.databinding.PagerIntro4Binding

class IntroPager4 : Fragment(R.layout.pager_intro4) {
    private val binding by viewBinding(PagerIntro4Binding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        Glide
            .with(intro4Image)
            .load(R.drawable.intro_4)
            .into(intro4Image)
        return@with
    }
}