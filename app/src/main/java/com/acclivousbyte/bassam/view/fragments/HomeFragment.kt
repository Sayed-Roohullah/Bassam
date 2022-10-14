package com.acclivousbyte.bassam.view.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.databinding.FragmentHomeBinding
import com.acclivousbyte.bassam.koinDI.homeModule
import com.acclivousbyte.bassam.models.genrelModels.Data
import com.acclivousbyte.bassam.utils.MaterialDialogHelper
import com.acclivousbyte.bassam.utils.OnClickView
import com.acclivousbyte.bassam.utils.setupProgressDialog
import com.acclivousbyte.bassam.view.adapter.ParentAdapter
import com.acclivousbyte.bassam.viewModel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.core.module.Module


class HomeFragment : MainMVVMNavigationFragment<HomeViewModel>(HomeViewModel::class), OnClickView,
    View.OnClickListener {

    private val parentadapter by lazy {
        ParentAdapter(requireContext(), this)
    }
    private  var filterArray = ArrayList<Data>()
    private var isMale = false
    private var isFemale = false
    private var isWorthy = false

    private lateinit var binding: FragmentHomeBinding
    override fun getLayoutResId() = R.layout.fragment_home
    override fun registerModule(): Module {
        return homeModule
    }

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(mRootView)

         val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)

        if (isNetworkAvailable(requireContext())) {
            viewModel.DetailData()
         } else {
            showAlertDialog(getString(R.string.no_internet))
        }

        attachViewModel()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                 filter(newText)
                 return false
            }
        })

        binding.malenew.setOnClickListener(this)
        binding.femalenew.setOnClickListener(this)
        binding.worthy.setOnClickListener(this)

    }
    private fun setupAdapter(){
        binding.parentrecycle.let { it1->
            it1.layoutManager = LinearLayoutManager(requireContext())
            it1.adapter = parentadapter
        }
    }


    private fun attachViewModel() {
        observe(viewModel.snackbarMessage) {
            val msg = it?.consume()
            if (!msg.isNullOrEmpty()) {
                showAlertDialog(msg)
            }
        }

        observe(viewModel.maindetailData) {
            filterArray = arrayListOf()
            filterArray.addAll(it.Data)
            parentadapter.updatedList(filterArray)

            setupAdapter()

         }

    }

    override fun onclicklistener(data: Data) {
        val bundle = Bundle()
        bundle.putString("name", data.name)
        bundle.putString("fname", data.father_name)
        bundle.putString("gfname", data.grand_father_name)
        bundle.putString("ggfname", data.g_grand_father_name)
        bundle.putString("nodeid", data.nodeID)
        bundle.putString("parentid", data.parent_id)
        bundle.putInt("id", data.id)
        bundle.putString("image", data.profile_picture_square)
        bundle.putString("worth", data.is_worthy)

        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.malenew -> {
                binding.malenew.isActivated = !binding.malenew.isActivated
                isMale = binding.malenew.isActivated
                listMerge()
            }
            R.id.femalenew -> {
                binding.femalenew.isActivated = !binding.femalenew.isActivated
                isFemale = binding.femalenew.isActivated
                listMerge()
            }
            R.id.worthy -> {

                binding.worthy.isActivated = !binding.worthy.isActivated
                isWorthy = binding.worthy.isActivated
                listMerge()
            }

        }
    }


   private fun listMerge() {
        val templist = ArrayList<Data>()
        if (isMale) {
            val filterList = filterArray.filter {
                it.gender.equals("0", ignoreCase = true)

            }
            templist.addAll(filterList)
        }
        if (isFemale) {
            val filterList = filterArray.filter {
                it.gender.equals("1", ignoreCase = true)

            }
            templist.addAll(filterList)
        }
        if (isWorthy) {
            val filterList = filterArray.filter {
                it.is_worthy.equals("Yes", ignoreCase = true)

            }
            templist.addAll(filterList)
        }
        if (!isMale && !isFemale && !isWorthy) {
            parentadapter.updatedList(filterArray)
        }else

        parentadapter.updatedList(templist)

    }
    private fun filter(text: String) {
        val filteredlist: ArrayList<Data> = ArrayList()

         for (item in filterArray) {
            if (item.name.contains(text)) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(context, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {

            parentadapter.updatedList(filteredlist)

        }
    }

}