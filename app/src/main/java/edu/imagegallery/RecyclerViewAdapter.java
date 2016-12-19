package edu.imagegallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageHolder>{
    private static  ArrayList<String> imageList;
    private static  Context mContext;
    private static final String EXTRA_IMAGE_LIST = "edu.imagegallery";
    private static final String EXTRA_SELECTED_INDEX = "edu.imagegallery.image";

    public RecyclerViewAdapter(Context context) {
        imageList = ImageFilesList.getFilePaths();
        mContext = context;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        ImageHolder imageHolder = new ImageHolder(v);
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        String url = "file://" + imageList.get(position);
        SingleFragmentActivity.imageLoader.displayImage(url, holder.image);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder{
        private ImageView image;

        public ImageHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.item_image_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BigImageActivity.class);
                    intent.putExtra(EXTRA_IMAGE_LIST, imageList);
                    intent.putExtra(EXTRA_SELECTED_INDEX, getAdapterPosition());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
