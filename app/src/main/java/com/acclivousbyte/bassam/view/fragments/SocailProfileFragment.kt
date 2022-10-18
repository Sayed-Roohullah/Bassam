package com.acclivousbyte.bassam.view.fragments

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.databinding.FragmentSocailProfileBinding
import com.acclivousbyte.bassam.utils.BassamViewUtil


class SocailProfileFragment : Fragment(R.layout.fragment_socail_profile) {
    private var twitter: String? = null
    private var instagram: String? = null
    private var snapchat: String? = null
    private lateinit var clipboard: ClipboardManager
    private lateinit var cliptext: ClipData
    private val args: SocailProfileFragmentArgs by navArgs()

    private lateinit var binding: FragmentSocailProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSocailProfileBinding.bind(view)

        clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager



        twitter = args.userSocailObj.twitter
        instagram = args.userSocailObj.instagram
        snapchat = args.userSocailObj.snapchat


        binding.tvTwitterFatah.setOnClickListener {
            if (twitter == "") {
                Toast.makeText(context, BassamViewUtil.NOT_HAVE_PROFILE_MESSAGE, Toast.LENGTH_SHORT).show()
            } else {
                try {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(BassamViewUtil.TWITTER_URI+"$twitter")))
                } catch (e: java.lang.Exception) {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(BassamViewUtil.TWITTER_EXCEPTION_URI+"$twitter")))
                }

            }
        }

        binding.tvTwitterNasah.setOnClickListener {
            if (twitter == "") {
                Toast.makeText(context, BassamViewUtil.NOT_HAVE_PROFILE_MESSAGE, Toast.LENGTH_SHORT).show()

            } else {
                cliptext = ClipData.newPlainText(BassamViewUtil.LABEL, "$twitter")
                clipboard.setPrimaryClip(cliptext)
                Toast.makeText(context, BassamViewUtil.TEXT_COPIED, Toast.LENGTH_SHORT).show()

            }
        }



        binding.tvInstaFatah.setOnClickListener {
            if (instagram == "") {
                Toast.makeText(context, BassamViewUtil.NOT_HAVE_PROFILE_MESSAGE, Toast.LENGTH_SHORT).show()
            } else {
                val uri = instagram?.toUri()
                val likeIng = Intent(Intent.ACTION_VIEW, uri)
                likeIng.setPackage(BassamViewUtil.INSTA_PACKAGE)

                try {
                    startActivity(likeIng)
                } catch (e: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BassamViewUtil.INSTAGRAM_URI)))
                }

            }

        }

        binding.tvInstaNasah.setOnClickListener {
            if (instagram == "") {
                Toast.makeText(context, BassamViewUtil.NOT_HAVE_PROFILE_MESSAGE, Toast.LENGTH_SHORT).show()
            } else {
                cliptext = ClipData.newPlainText(BassamViewUtil.LABEL, "$instagram")
                clipboard.setPrimaryClip(cliptext)
                Toast.makeText(context, BassamViewUtil.TEXT_COPIED, Toast.LENGTH_SHORT).show()
            }

        }



        binding.tvSnapFatah.setOnClickListener {
            if (snapchat == "") {
                Toast.makeText(context, BassamViewUtil.NOT_HAVE_PROFILE_MESSAGE, Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse(BassamViewUtil.SNAPCHAT_URI+"$snapchat"))
                    intent.setPackage(BassamViewUtil.SNAP_PACKAGE)
                    startActivity(intent)
                } catch (e: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW,
                        Uri.parse(BassamViewUtil.SNAPCHAT_URI+"$snapchat")))
                }

            }

        }

        binding.tvSnapNasah.setOnClickListener {
            if (snapchat == "") {
                Toast.makeText(context, BassamViewUtil.NOT_HAVE_PROFILE_MESSAGE, Toast.LENGTH_SHORT).show()
            } else {
                cliptext = ClipData.newPlainText(BassamViewUtil.LABEL, "$snapchat")
                clipboard.setPrimaryClip(cliptext)
                Toast.makeText(context, BassamViewUtil.TEXT_COPIED, Toast.LENGTH_SHORT).show()
            }

        }

    }


}
