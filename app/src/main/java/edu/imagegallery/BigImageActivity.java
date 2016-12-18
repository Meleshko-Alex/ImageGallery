package edu.imagegallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class BigImageActivity extends AppCompatActivity{
    private static final String EXTRA_IMAGE_LIST = "edu.imagegallery";
    private static final String EXTRA_SELECTED_INDEX = "edu.imagegallery.image";
    private static int selectedIndex;
    private ViewPager mViewPager;
    private ArrayList<String> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pager_image);

        mViewPager = (ViewPager)findViewById(R.id.pager);
        imageList = (ArrayList<String>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);
        selectedIndex = getIntent().getIntExtra(EXTRA_SELECTED_INDEX, 1);

        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setPageTransformer(false, new PagerTransformer(PagerTransformer.TransformType.SLIDE_OVER));
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return BigImageFragment.newInstance(imageList.get(position));
            }

            @Override
            public int getCount() {
                return imageList.size();
            }
        });

        for (int i = 0; i < imageList.size(); i++) {
            if (i == selectedIndex) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
