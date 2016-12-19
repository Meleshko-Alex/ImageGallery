package edu.imagegallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ImageGalleryFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private ArrayList<String> filePaths;
    private RecyclerViewAdapter adapter;

    public static ImageGalleryFragment newInstance(){
        return  new ImageGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        filePaths = ImageFilesList.getFilePaths();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_gallery, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.fragment_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        if(adapter == null){
            adapter = new RecyclerViewAdapter(getActivity());
            mRecyclerView.setAdapter(adapter);
        }else{
            filePaths = ImageFilesList.getFilePaths();
            adapter.notifyDataSetChanged();
        }

        return v;
    }
}
