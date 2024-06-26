package com.shift.shiftfinal.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.shift.shiftfinal.MainActivity
import com.shift.shiftfinal.R
import com.shift.shiftfinal.databinding.FragmentMainBinding
import com.shift.shiftfinal.ui.screens.getHomeScreen
import com.shift.shiftfinal.ui.screens.getMenuScreen
import javax.inject.Inject
import javax.inject.Named

class MainFragment : Fragment() {

    @Inject
    @Named("MainFragmentRouter")
    lateinit var router: Router

    @Inject
    @Named("MainFragmentHolder")
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var navigator: AppNavigator

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        (activity as MainActivity).appComponent.inject(this)
        navigator = AppNavigator(requireActivity(), R.id.mainFragmentContainer)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            router.newRootScreen(getHomeScreen())
        }

        with(binding) {

            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main -> {
                        router.replaceScreen(getHomeScreen())
                        true
                    }

                    R.id.menu -> {
                        router.replaceScreen(getMenuScreen())
                        true
                    }

                    else -> false
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}