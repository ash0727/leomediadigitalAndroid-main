package com.leomediadigital.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leomediadigital.R;
import com.leomediadigital.activity.ViewMediaActivity;
import com.leomediadigital.activity.ViewPhotoActivity;
import com.leomediadigital.model.MediaPartner;
import com.leomediadigital.util.Constant;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class OurMediaPartenerAdapter extends RecyclerView.Adapter<OurMediaPartenerAdapter.ViewHolder> {

    ArrayList<MediaPartner> mediaPartnerArrayList;
    Context context;
    public OurMediaPartenerAdapter(Context context,ArrayList<MediaPartner>mediaPartnerArrayList)
    {
        this.context=context;
        this.mediaPartnerArrayList=mediaPartnerArrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ourmedia_poartener_adapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        final MediaPartner mediaPartner =mediaPartnerArrayList.get(position);
        String url= Constant.IMGPATHMEDIA +mediaPartner.getPhotos();
//        Picasso.with(context).load(url).into(viewHolder.imgmedia);
       Picasso.with(context).load(url).placeholder( R.drawable.progress_animation ).into(viewHolder.imgmedia);

        viewHolder.imgmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ViewMediaActivity.class);
                Bundle args = new Bundle();
                String pos = String.valueOf(position);
                args.putSerializable("image",(Serializable) mediaPartnerArrayList);
                intent.putExtra("Bundle",args);
                intent.putExtra("pos",pos);
                context.startActivity(intent);

//                Intent intent = new Intent(context, ViewMediaActivity.class);
//                intent.putExtra("image",Constant.IMGPATHMEDIA+mediaPartner.getMedia());
//                context.startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() {
        return mediaPartnerArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgmedia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgmedia=itemView.findViewById(R.id.imgmedia);
        }
    }
}
