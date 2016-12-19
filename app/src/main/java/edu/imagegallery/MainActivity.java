package edu.imagegallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Create imageList
         */
        ImageFilesList ifl = new ImageFilesList(this);
    }

    @Override
    protected Fragment createFragment() {
        return new ImageGalleryFragment();
    }
}
