package com.dicoding.picodiploma.githubuserapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.githubuserapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showUserDetail()

        val backButton: ImageView = binding.ivBackButton
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /* Return value of data that has been sent from MainActivity
    * to be displayed on DetailUserActivity (second page/clicked item) */
    private fun showUserDetail() {
        val detailUser = intent.getParcelableExtra<DetailUser>(EXTRA_DETAIL_USER) as DetailUser

        // Glide Library (to manage image etc) https://github.com/bumptech/glide
        Glide.with(this)
            .load(detailUser.avatar)
            .circleCrop()
            .into(binding.imgItemPhoto)

        binding.apply {
            tvItemUsername.text = detailUser.username
            tvItemName.text = detailUser.fullName
            tvItemLocation.text = detailUser.location
            tvItemCompany.text = detailUser.company
            tvItemRepository.text = detailUser.repository
            tvItemFollowers.text = detailUser.followers
            tvItemFollowing.text = detailUser.following
        }
    }

    companion object {
        const val EXTRA_DETAIL_USER = "extra_detail_user"
    }
}
