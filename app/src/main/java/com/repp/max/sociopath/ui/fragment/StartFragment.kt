package com.repp.max.sociopath.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.repp.max.sociopath.R
import com.repp.max.sociopath.presentation.presenter.StartPresenter
import com.repp.max.sociopath.presentation.view.StartView
import kotlinx.android.synthetic.main.activity_main.*

class StartFragment : MvpAppCompatFragment(), StartView {
    companion object {
        const val TAG = "StartFragment"

        fun newInstance(): StartFragment {
            val fragment: StartFragment = StartFragment()
            val args: Bundle = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    lateinit var mStartPresenter: StartPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        becomeSociopathButton.setOnClickListener(mStartPresenter::onBecomeSociopathClicked)
    }

}
