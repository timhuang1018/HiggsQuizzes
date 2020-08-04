package com.timhuang.higgsquizzes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timhuang.higgsquizzes.R
import com.timhuang.higgsquizzes.adapter.AdapterClick
import com.timhuang.higgsquizzes.adapter.AdapterListener
import com.timhuang.higgsquizzes.adapter.UsersAdapter
import com.timhuang.higgsquizzes.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.users_page.*

/**
 * Created by timhuang on 2020/8/4.
 **/

class UsersPage :Fragment(),AdapterListener {
    private lateinit var viewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.users_page,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun getData() {
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.getUsers()
    }

    private fun initUI() {
        usersAdapter = UsersAdapter(this@UsersPage)

        vertical_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }

        viewModel.apply {
            users.observe(viewLifecycleOwner, Observer { list->
                Log.d("UserPage","list:$list")
                usersAdapter.submitList(list)
            })
            isLoading.observe(viewLifecycleOwner, Observer { isLoading->
                if (isLoading){
                    list_progressbar.visibility = View.VISIBLE
                }else{
                    list_progressbar.visibility = View.GONE
                }
            })
            error.observe(viewLifecycleOwner, Observer {
                it.contentGetHandled()?.let {errorMessage->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }

        vertical_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //give positive number to check scrolling down, if not ,fetch data
                if (!recyclerView.canScrollVertically(1) && !viewModel.isLoading() &&viewModel.moreData){
                    viewModel.getUsers()
                }
            }
        })

    }

    //simply respond click and navigate at here
    override fun listenClick(item: AdapterClick) {

    }
}