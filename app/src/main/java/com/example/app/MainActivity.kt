package com.example.app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.app.databinding.ActivityMainBinding
import com.example.app.util.FragmentPageAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: FragmentPageAdapter
    private lateinit var linkedin: FloatingActionButton
    private lateinit var instagram: FloatingActionButton
    private lateinit var rdamasco: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_App)
        setContentView(binding.root)

        linkedin = binding.linkedin
        instagram = binding.instagram
        rdamasco = binding.rdamasco

        linkedin.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/company/r&damasco-soluções-industriais/")
            )
            startActivity(intent)
        }

        instagram.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.instagram.com/r_damasco/")
            )
            startActivity(intent)
        }

        rdamasco.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.rdamasco.com.br")
            )
            startActivity(intent)
        }

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        tabLayout.addTab(tabLayout.newTab().setText("Entrar"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        adapter = FragmentPageAdapter(supportFragmentManager, lifecycle)

        viewPager.adapter = adapter
    }

}