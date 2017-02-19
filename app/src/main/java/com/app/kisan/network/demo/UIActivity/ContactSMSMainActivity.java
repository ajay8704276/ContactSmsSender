package com.app.kisan.network.demo.UIActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.kisan.network.demo.Adapter.PagerAdapter;
import com.app.kisan.network.demo.R;
import com.app.kisan.network.demo.UIFragments.ContactListTabFragment;
import com.app.kisan.network.demo.UIFragments.SmsSentTabFragment;

public class ContactSMSMainActivity extends AppCompatActivity {

    private ViewPager mHomeViewPager;
    private TabLayout mHomeTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setUpViewPager();

    }


    private void setUpViewPager() {

        mHomeViewPager = (ViewPager) findViewById(R.id.viewpager_home);
        PagerAdapter mHomePagePagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mHomePagePagerAdapter.addFragment(new ContactListTabFragment(),"Contacts");
        mHomePagePagerAdapter.addFragment(new SmsSentTabFragment(),"Messages");
        mHomeViewPager.setAdapter(mHomePagePagerAdapter);

        setUpTabLayOut(mHomeViewPager);
    }

    private void setUpTabLayOut(ViewPager mHomeViewPager) {
        mHomeTabLayout = (TabLayout) findViewById(R.id.home_tab);
        mHomeTabLayout.setupWithViewPager(mHomeViewPager);
        setUpTabIcon();
    }

    private void setUpTabIcon() {
        mHomeTabLayout.getTabAt(0).setIcon(android.R.drawable.ic_dialog_email);
        mHomeTabLayout.getTabAt(1).setIcon(android.R.drawable.ic_dialog_dialer);
    }


}
