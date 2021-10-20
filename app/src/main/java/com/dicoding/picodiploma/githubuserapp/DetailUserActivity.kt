package com.dicoding.picodiploma.githubuserapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.githubuserapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_FULL_NAME = "extra_fullName"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_COMPANY = "extra_company"
        const val EXTRA_REPOSITORY = "extra_repository"
        const val EXTRA_FOLLOWERS = "extra_followers"
        const val EXTRA_FOLLOWING = "extra_following"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showUserDetail()

        val backButton = findViewById<ImageView>(R.id.iv_back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /* Return value of data that has been sent from MainActivity
    * to be displayed on DetailUserActivity (second page/clicked item) */
    private fun showUserDetail() {
        binding.tvItemUsername.text = intent.getStringExtra(EXTRA_USERNAME)
        binding.tvItemName.text = intent.getStringExtra(EXTRA_FULL_NAME)

        // Glide Library (to manage image etc) https://github.com/bumptech/glide
        Glide.with(this)
            .load(intent.getIntExtra(EXTRA_AVATAR, 0))
            .circleCrop()
            .into(binding.imgItemPhoto)

        binding.tvItemLocation.text = intent.getStringExtra(EXTRA_LOCATION)
        binding.tvItemCompany.text = intent.getStringExtra(EXTRA_COMPANY)
        binding.tvItemRepository.text = intent.getStringExtra(EXTRA_REPOSITORY)
        binding.tvItemFollowers.text = intent.getStringExtra(EXTRA_FOLLOWERS)
        binding.tvItemFollowing.text = intent.getStringExtra(EXTRA_FOLLOWING)
    }
}
