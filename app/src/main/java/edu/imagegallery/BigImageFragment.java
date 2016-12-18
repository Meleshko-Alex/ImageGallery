package edu.imagegallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class BigImageFragment extends Fragment{
    private static File file;
    static final String ARGUMENT_IMAGE_NUMBER = "arg_page_number";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_big_image, container, false);
        ImageView image = (ImageView)v.findViewById(R.id.iv_big_image);

        String filePath = getArguments().getString(ARGUMENT_IMAGE_NUMBER);
        file = new File(filePath);

        String url = "file://" + filePath;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_delete, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_delete:
                if (file.delete()) getActivity().finish();
                Toast.makeText(getActivity(), "deleted" + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                return  true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
