package com.example.soulforge.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.soulforge.R;
import com.example.soulforge.model.HomeModel;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder>{

    private List<HomeModel> list;
    Context context;

    public HomeAdapter(List<HomeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_items,  parent, false);
        return new HomeAdapter.HomeHolder(view);

    }
    @Override
    public int getItemViewType(int position)
    {
        return position;
    }




    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.nameTv.setText(list.get(position).getName());
        holder.timeTv.setText(""+list.get(position).getTimestamp());


        int count = list.get(position).getLikeCount();
        if(count ==0){
            holder.likeCountTv.setVisibility(View.VISIBLE);
        }else if (count == 1){
            holder.likeCountTv.setText(count + " like");
        }else{
            holder.likeCountTv.setText(count + " likes");
        }

        holder.descriptionTv.setText(list.get(position).getDescription());


        Random random = new Random();

        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        Glide.with(context.getApplicationContext())
                .load(list.get(position).getProfileImage())
                .placeholder(R.drawable.ic_person)
                .timeout(6500)
                .into(holder.profileImage);

        Glide.with(context.getApplicationContext())
                .load(list.get(position).getImageUrl())
                .placeholder(new ColorDrawable(color))
                .timeout(7000)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    static class HomeHolder extends RecyclerView.ViewHolder{
        private CircleImageView profileImage;
        public TextView nameTv, timeTv, likeCountTv, descriptionTv;
        private ImageView imageView;
        private ImageButton likeBtn, commentBtn, shareBtn;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImage);
            imageView = itemView.findViewById(R.id.imageView);
            nameTv = itemView.findViewById(R.id.nameTv);
            timeTv = itemView.findViewById(R.id.timeTv);
            likeCountTv = itemView.findViewById(R.id.likeCountTv);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            commentBtn = itemView.findViewById(R.id.commentBtn);
            shareBtn = itemView.findViewById(R.id.shareBtn);
            descriptionTv = itemView.findViewById(R.id.descTv);


        }
    }
}
