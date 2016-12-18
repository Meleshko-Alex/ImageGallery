package edu.imagegallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BigImageFragment extends Fragment{
    private static String url;
    static final String ARGUMENT_IMAGE_NUMBER = "arg_page_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_big_image, container, false);
        ImageView image = (ImageView)v.findViewById(R.id.iv_big_image);

        url = "file://" + getArguments().getString(ARGUMENT_IMAGE_NUMBER);
        SingleFragmentActivity.imageLoader.displayImage(url, image);
        return v;
    }

    public static BigImageFragment newInstance(String index){
        BigImageFragment pageFragment = new BigImageFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_IMAGE_NUMBER, index);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }
}
