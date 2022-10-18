package com.acclivousbyte.bassam.view.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.databinding.FragmentDetailBinding
import com.acclivousbyte.bassam.koinDI.persondetailModule
import com.acclivousbyte.bassam.models.genrelModels.Children
import com.acclivousbyte.bassam.models.genrelModels.DataX
import com.acclivousbyte.bassam.models.genrelModels.Sibling
import com.acclivousbyte.bassam.utils.BassamViewUtil
import com.acclivousbyte.bassam.utils.MaterialDialogHelper
import com.acclivousbyte.bassam.utils.setupProgressDialog
import com.acclivousbyte.bassam.view.adapter.ChildAdapter
import com.acclivousbyte.bassam.view.adapter.SiblingAdapter
import com.acclivousbyte.bassam.viewModel.PersonDetailVewModel
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import org.koin.core.module.Module

class PersonDetailFragment: MainMVVMNavigationFragment<PersonDetailVewModel>(PersonDetailVewModel::class) {

    private val args: PersonDetailFragmentArgs by navArgs()
    private lateinit var socailinfo: DataX
    var phone = ""
    var email = ""
    var socail = ""

    var pId = 0

    private var childerns = listOf<Children>()
    private var siblings = listOf<Sibling>()


    private val childadapter by lazy {
        ChildAdapter(requireContext(),childerns)
    }
    private val siblingadapter by lazy {
        SiblingAdapter(requireContext(),siblings)
    }

    override fun registerModule(): Module {
        return persondetailModule
    }
    private lateinit var binding: FragmentDetailBinding
    override fun getLayoutResId() = R.layout.fragment_detail

    override fun inOnCreateView(mRootView: ViewGroup, savedInstanceState: Bundle?) {
        binding = FragmentDetailBinding.bind(mRootView)
        val dialogHelper by inject<MaterialDialogHelper>()
        setupProgressDialog(viewModel.showHideProgressDialog, dialogHelper)

        pId = args.userObj.id

        if (isNetworkAvailable(requireContext())) {
            viewModel.personDetailData(pId.toString())
        } else {
            showAlertDialog(getString(R.string.no_internet))
        }
        attachViewModel()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backbtn?.setOnClickListener {
            findNavController().navigateUp()
         }
        binding.socail.setOnClickListener {
           val action = PersonDetailFragmentDirections.actionDetailFragmentToSocailProfileFragment(socailinfo)
            findNavController().navigate(action)
        }
        binding.mail.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:${email}")
            )
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject")
            intent.putExtra(android.content.Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(intent, "Email"))
        }
        binding.phone.setOnClickListener {
            dailerPad()

        }

        binding.childern.setOnClickListener {
            binding.siblingview.visibility = View.GONE
            binding.childernview.visibility = View.VISIBLE
            binding.rootimage.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.default_image
                )
            )
             binding.roottext.text = args.userObj.name
             binding.childRecycle.let {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.addItemDecoration(DividerItemDecoration(context, 0))
                it.adapter = childadapter
            }


        }
        binding.sibling.setOnClickListener {
            binding.childernview.visibility = View.GONE
            binding.siblingview.visibility = View.VISIBLE
             binding.rootsible.text = args.userObj.name
             binding.siblingRecycle.let {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.addItemDecoration(DividerItemDecoration(context, 0))
                it.adapter = siblingadapter
            }


        }
    }

    private fun attachViewModel() {
        observe(viewModel.snackbarMessage) {
            val msg = it?.consume()
            if (!msg.isNullOrEmpty()) {
                showAlertDialog(msg)
            }
        }

        observe(viewModel.personDetailData) {

            childerns = it.Data.children
            siblings = it.Data.siblings

            binding.nodeIdPass.text = it.Data.nodeID
            binding.passName.text = it.Data.name
            binding.passGrndname.text = it.Data.father_name +" "+ it.Data.grand_father_name +" "+ it.Data.g_grand_father_name
            binding.calender.text = it.Data.p_dob
            binding.desctext.text = it.Data.brief_description
            binding.location.text = it.Data.p_location
            binding.edu1.text = it.Data.p_education
            binding.city.text = it.Data.city
            binding.country.text = it.Data.country
            binding.job2.text = it.Data.p_workplace

            socailinfo = it.Data
            val profileImage = it.Data.profile_picture_square

            if (profileImage == "") {
                binding.passProfile.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.default_image
                    )
                )
            } else {
                Glide.with(this).load(profileImage).into(binding.passProfile)
            }
            phone = it.Data.contact
            email = it.Data.email
            socail = it.Data.p_socialnetworks

            if (email == "") {
                binding.mail.isClickable = false
                binding.mail.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.email_icon_gray
                    )
                )

            }
            if (phone=="") {
                binding.phone.isClickable = false
                binding.phone.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.phone_icon_gray
                    )
                )
            }
            if (socail == "") {
                binding.socail.isClickable = false
                binding.socail.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.socail_icon_gray
                    )
                )
            }

            if (it.Data.brief_description == "") {
                binding.description.visibility = View.GONE
            }

            if (it.Data.p_location == BassamViewUtil.HIDDEN) {
                binding.location.visibility = View.GONE
            }
            if (it.Data.p_dob == BassamViewUtil.HIDDEN) {
                binding.calender.visibility = View.GONE
                binding.calIcon.visibility = View.GONE

            }
            if (it.Data.p_education == BassamViewUtil.HIDDEN) {
                binding.education.visibility = View.GONE
            }

            if (it.Data.is_worthy == "No") {
                binding.worthyPass.visibility = View.GONE

            }


        }
    }
        fun dailerPad() {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            callIntent.data = Uri.parse("tel:" + phone)
            resultLauncher.launch(callIntent)
        }

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                }
                if (result.resultCode == Activity.RESULT_CANCELED) {

                }
            }
}