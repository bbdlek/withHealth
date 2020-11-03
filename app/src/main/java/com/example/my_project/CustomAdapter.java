package com.example.my_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<GroupAll> arrayList;
    private Context context;


    public CustomAdapter(ArrayList<GroupAll> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_all, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String imageUrl = arrayList.get(position).getProfile();
        Glide.with(holder.itemView.getContext()).load("https://firebasestorage.googleapis.com/v0/b/withhealth-ada67.appspot.com/o/images%2Fgroup1.jpg?alt=media&token=d50b27c1-c874-43ba-8473-0b214c4f9e18").into(holder.iv_profile);
        holder.tv_groupname.setText(arrayList.get(position).getGroupName());
        holder.tv_groupinfo.setText(String.valueOf(arrayList.get(position).getGroupinfo()));
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_groupname;
        TextView tv_groupinfo;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.group_profile);
            this.tv_groupname = itemView.findViewById(R.id.nameofgroup);
            this.tv_groupinfo = itemView.findViewById(R.id.group_info);
        }
    }
}
