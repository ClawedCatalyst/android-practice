package com.example.testing.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testing.R
import com.example.testing.api.ProfileAPI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    @Inject
    lateinit var profileAPI: ProfileAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("ProfileData", "Yaha agaye hum")
            val response = profileAPI.getProfileData()
            Log.d("ProfileData", response.body().toString())
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}