package edu.imagegallery;

import android.support.v4.app.Fragment;

public class ImageGalleryActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return ImageGalleryFragment.newInstance();
    }
}
