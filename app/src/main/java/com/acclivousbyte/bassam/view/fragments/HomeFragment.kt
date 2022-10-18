package com.acclivousbyte.bassam.view.fragments

 import android.os.Bundle
import android.view.View
import android.view.ViewGroup
 import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
 import androidx.recyclerview.widget.LinearLayoutManager
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.databinding.FragmentHomeBinding
import com.acclivousbyte.bassam.koinDI.homeModule
import com.acclivousbyte.bassam.models.genrelModels.Data
import com.acclivousbyte.bassam.utils.*
import com.acclivousbyte.bassam.view.adapter.ParentAdapter
import com.acclivousbyte.bassam.viewModel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.core.module.Module


class HomeFragment : MainMVVMNavigationFragment<HomeViewModel>(HomeViewModel::class), onClickView,
    View.OnClickListener {

       val parentadapter by lazy {
            ParentAdapter(requireContext(), this)
        }

       var filterArray = ArrayList<Data>()

        var isMale = false
        var isFemale = false
        var isWorthy = false

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
            viewModel.detailData()
         } else {
            showAlertDialog(getString(R.string.no_internet))
        }

        attachViewModel()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
               viewModel.filter(query)
                  return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
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
            setupAdapter()
            filterArray = arrayListOf()
            filterArray.addAll(it.Data)
            parentadapter.updatedList(filterArray)


         }
        observe(viewModel.mergefilter){ result->
            result.apply {
                parentadapter.updatedList(ArrayList(result))
            }
        }
        observe(viewModel.searchfilter){ result ->
            result.apply {
                parentadapter.updatedList(ArrayList(result))

            }
        }



    }

    override fun onClickListener(data: Data) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
        findNavController().navigate(action)

    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.malenew -> {
                binding.malenew.isActivated = !binding.malenew.isActivated
                isMale = binding.malenew.isActivated
                viewModel.listMerge(isMale,isFemale,isWorthy)

            }
            R.id.femalenew -> {
                binding.femalenew.isActivated = !binding.femalenew.isActivated
                isFemale = binding.femalenew.isActivated
                viewModel.listMerge(isMale,isFemale,isWorthy)
              }
            R.id.worthy -> {
                binding.worthy.isActivated = !binding.worthy.isActivated
                isWorthy = binding.worthy.isActivated
                viewModel.listMerge(isMale,isFemale,isWorthy)

            }

        }
    }


}