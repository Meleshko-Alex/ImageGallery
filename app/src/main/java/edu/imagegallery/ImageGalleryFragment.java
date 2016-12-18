package edu.imagegallery;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class ImageGalleryFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private ArrayList<String> filePaths;

    public static ImageGalleryFragment newInstance(){
        return  new ImageGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        filePaths = getFilePaths();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_image_gallery, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.fragment_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(filePaths, getActivity());
        mRecyclerView.setAdapter(adapter);

        return v;
    }

    private ArrayList<String> getFilePaths() {

            Uri u = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.Images.ImageColumns.DATA};
            Cursor c = null;
            SortedSet<String> dirList = new TreeSet<>();
            ArrayList<String> resultIAV = new ArrayList<>();

            String[] directories = null;
            if (u != null)
            {
                c =  getActivity().getContentResolver()
                        .query(u, projection, null, null, null);
            }

            if ((c != null) && (c.moveToFirst()))
            {
                do
                {
                    String tempDir = c.getString(0);
                    tempDir = tempDir.substring(0, tempDir.lastIndexOf("/"));
                    try{
                        dirList.add(tempDir);
                    }
                    catch(Exception e)
                    {

                    }
                }
                while (c.moveToNext());
                directories = new String[dirList.size()];
                dirList.toArray(directories);
            }

            for(int i=0;i<dirList.size();i++)
            {
                File imageDir = new File(directories[i]);
                File[] imageList = imageDir.listFiles();
                if(imageList == null)
                    continue;
                for (File imagePath : imageList) {
                    try {

                        if(imagePath.isDirectory())
                        {
                            imageList = imagePath.listFiles();
                        }
                        if ( imagePath.getName().contains(".jpg")|| imagePath.getName().contains(".JPG")
                                || imagePath.getName().contains(".jpeg")|| imagePath.getName().contains(".JPEG")

                            // Will get only jpg files
                            /*|| imagePath.getName().contains(".png") || imagePath.getName().contains(".PNG")
                            || imagePath.getName().contains(".gif") || imagePath.getName().contains(".GIF")
                            || imagePath.getName().contains(".bmp") || imagePath.getName().contains(".BMP")*/
                                )
                        {
                            String path= imagePath.getAbsolutePath();
                            resultIAV.add(path);
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return resultIAV;
        }
}
