package com.acclivousbyte.bassam.view.fragments

import android.content.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.acclivousbyte.bassam.R
import com.acclivousbyte.bassam.databinding.FragmentSocailProfileBinding


class SocailProfileFragment : Fragment(R.layout.fragment_socail_profile) {
    private var twitter: String? = null
    private var instagram: String? = null
    private var snapchat: String? = null
    private lateinit var clipboard: ClipboardManager
    private lateinit var cliptext: ClipData
    private lateinit var binding: FragmentSocailProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSocailProfileBinding.bind(view)

        clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager


        val bundle = arguments

        twitter = bundle?.getString("twitter")
        instagram = bundle?.getString("insta")
        snapchat = bundle?.getString("snap")


        binding.tvTwitterFatah.setOnClickListener {
            if (twitter == "") {
                Toast.makeText(context, "Not have Profile", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=$twitter")))
                } catch (e: java.lang.Exception) {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/$twitter")))
                }

            }
        }

        binding.tvTwitterNasah.setOnClickListener {
            if (twitter == "") {
                Toast.makeText(context, "Not have Profile", Toast.LENGTH_SHORT).show()

            } else {
                cliptext = ClipData.newPlainText("label", "$twitter")
                clipboard.setPrimaryClip(cliptext)
                Toast.makeText(context, "Text Copied", Toast.LENGTH_SHORT).show()

            }
        }



        binding.tvInstaFatah.setOnClickListener {
            if (instagram == "") {
                Toast.makeText(context, "Not have Profile", Toast.LENGTH_SHORT).show()
            } else {
                val uri = instagram?.toUri()
                val likeIng = Intent(Intent.ACTION_VIEW, uri)
                likeIng.setPackage("com.instagram.android")

                try {
                    startActivity(likeIng)
                } catch (e: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/xxx")))
                }

            }

        }

        binding.tvInstaNasah.setOnClickListener {
            if (instagram == "") {
                Toast.makeText(context, "Not have Profile", Toast.LENGTH_SHORT).show()
            } else {
                cliptext = ClipData.newPlainText("label", "$instagram")
                clipboard.setPrimaryClip(cliptext)
                Toast.makeText(context, "Text Copied", Toast.LENGTH_SHORT).show()
            }

        }



        binding.tvSnapFatah.setOnClickListener {
            if (snapchat == "") {
                Toast.makeText(context, "Not have Profile", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://snapchat.com/add/$snapchat"))
                    intent.setPackage("com.snapchat.android")
                    startActivity(intent)
                } catch (e: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://snapchat.com/add/$snapchat")))
                }

            }

        }

        binding.tvSnapNasah.setOnClickListener {
            if (snapchat == "") {
                Toast.makeText(context, "Not have Profile", Toast.LENGTH_SHORT).show()
            } else {
                cliptext = ClipData.newPlainText("label", "$snapchat")
                clipboard.setPrimaryClip(cliptext)
                Toast.makeText(context, "Text Copied", Toast.LENGTH_SHORT).show()
            }

        }

    }


}
