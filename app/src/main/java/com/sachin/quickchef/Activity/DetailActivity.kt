package com.sachin.quickchef.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sachin.quickchef.Domain.DoctorsModel
import com.sachin.quickchef.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: DoctorsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!
      binding.apply {
          titleTxt.text = item.Name
          specialTxt.text = item.Special
          patiensTxt.text = item.Patieng
          bioTxt.text = item.Biography
          addressTxt.text = item.Address
          experienTxt.text = item.Expriense.toString() + " Years"
          ratingTxt.text = item.Rating.toString()

          messageBtn.setOnClickListener {
              val uri = Uri.parse("smsto:${item.Mobile}")
              val intent = Intent(Intent.ACTION_SENDTO, uri).apply {
                  putExtra("sms_body", "the SMS text")
              }
              startActivity(intent)
          }

          callBtn.setOnClickListener {
              val uri = "tel:${item.Mobile.trim()}"
              val intent = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
              startActivity(intent)
          }

          directionBtn.setOnClickListener {
              val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.Location))
              startActivity(intent)
          }

          shareBtn.setOnClickListener {
              val intent = Intent(Intent.ACTION_SEND).apply {
                  type = "text/plain"
                  putExtra(Intent.EXTRA_SUBJECT, item.Name)
                  putExtra(Intent.EXTRA_TEXT, "${item.Name} ${item.Address} ${item.Mobile}")
              }
              startActivity(Intent.createChooser(intent, "Share via"))
          }

          backBtn.setOnClickListener {
              finish()
          }

          websiteBtn.setOnClickListener {
              val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.Site))
              startActivity(intent)
          }

          Glide.with(this@DetailActivity)
              .load(item.Picture)
              .into(binding.img)
      }
    }
}
