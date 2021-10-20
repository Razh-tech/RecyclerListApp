package com.dicoding.picodiploma.githubuserapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvGithubUser: RecyclerView
    private val list = ArrayList<DetailUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGithubUser = findViewById(R.id.rv_github_user)
        rvGithubUser.setHasFixedSize(true)

        list.addAll(listUser)
        showRecyclerList()
    }

    private val listUser: ArrayList<DetailUser>
        @SuppressLint("Recycle")
        get() {
            val username = resources.getStringArray(R.array.username)
            val fullName = resources.getStringArray(R.array.fullname)
            val avatar = resources.obtainTypedArray(R.array.avatar)
            val location = resources.getStringArray(R.array.location)
            val company = resources.getStringArray(R.array.company)
            val repository = resources.getStringArray(R.array.repository)
            val following = resources.getStringArray(R.array.following)
            val followers = resources.getStringArray(R.array.followers)
            val listUser = ArrayList<DetailUser>()
            for (i in username.indices) {
                val user = DetailUser(username[i], fullName[i], avatar.getResourceId(i, -1), location[i], company[i], repository[i], following[i], followers[i])
                listUser.add(user)
            }
            return listUser
        }

    // ShowRecyclerList accordingly to the device orientation
    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            rvGithubUser.layoutManager = GridLayoutManager(this, 2)
        else
            rvGithubUser.layoutManager = LinearLayoutManager(this)

        val listUserAdapter = ListUserAdapter(list)
        rvGithubUser.adapter = listUserAdapter

        // Call showSelectedUser function when item is clicked by user
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DetailUser) {
                showSelectedUser(data)
            }
        })
    }

    // Send data as mentioned within parameter putExtra to DetailUserActivity
    private fun showSelectedUser(user: DetailUser) {
        val manageDetailIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
            .apply {
                putExtra(DetailUserActivity.EXTRA_USERNAME, user.username)
                putExtra(DetailUserActivity.EXTRA_FULL_NAME, user.fullName)
                putExtra(DetailUserActivity.EXTRA_AVATAR, user.avatar)
                putExtra(DetailUserActivity.EXTRA_LOCATION, user.location)
                putExtra(DetailUserActivity.EXTRA_COMPANY, user.company)
                putExtra(DetailUserActivity.EXTRA_REPOSITORY, user.repository)
                putExtra(DetailUserActivity.EXTRA_FOLLOWERS, user.followers)
                putExtra(DetailUserActivity.EXTRA_FOLLOWING, user.following)
            }
        startActivity(manageDetailIntent)
    }
}