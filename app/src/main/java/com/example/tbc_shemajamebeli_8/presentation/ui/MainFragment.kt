package com.example.tbc_shemajamebeli_8.presentation.ui

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbc_shemajamebeli_8.R
import com.example.tbc_shemajamebeli_8.databinding.FragmentMainBinding
import com.example.tbc_shemajamebeli_8.domain.RecyclerViewInterface
import com.example.tbc_shemajamebeli_8.domain.utils.ResponseState
import com.example.tbc_shemajamebeli_8.presentation.MainFragmentViewModel
import com.example.tbc_shemajamebeli_8.presentation.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : RecyclerViewInterface, BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {
    private val viewModel: MainFragmentViewModel by viewModels()
    @Inject
    lateinit var appContext: Application

    override fun setup() {

        var adapter: ItemAdapter = ItemAdapter(this)
        val recyclerView = binding.mainRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.getInfo()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.myState.collect{
                    when(it){
                        is ResponseState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is ResponseState.Error -> {
                            binding.finalText.text = it.message
                            binding.progressBar.visibility = View.GONE
                        }
                        is ResponseState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.submitList(it.items.content)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    // we are not going to implement it
    override fun onFavoriteButtonClick(Position: Int) {
        TODO("Not yet implemented")
    }

    override fun onBuyButtonClick(Position: Int) {
        val myToast: Toast = Toast.makeText(requireContext(), "Item bought!", Toast.LENGTH_LONG)
        myToast.show()
    }


}