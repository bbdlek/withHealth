package com.example.my_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter_main extends RecyclerView.Adapter<CustomAdapter_main.CustomViewHolder> implements Filterable {

    private ArrayList<GroupAll> arrayList;
    private Context context;
    private ArrayList<GroupAll> mDataListAll;

    public CustomAdapter_main(ArrayList<GroupAll> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        mDataListAll = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_all, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        String imageUrl = arrayList.get(position).getProfile();
        Glide.with(holder.itemView.getContext()).load("https://firebasestorage.googleapis.com/v0/b/withhealth-ada67.appspot.com/o/images%2Fgroup1.jpg?alt=media&token=d50b27c1-c874-43ba-8473-0b214c4f9e18").into(holder.iv_profile);
        holder.tv_groupname.setText(arrayList.get(position).getGroupName());
        holder.tv_groupinfo.setText(String.valueOf(arrayList.get(position).getGroupinfo()));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            //        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams)holder.imageView.getLayoutParams();
            @Override
            public void onClick(View v) {
//                layoutParams.width = 125;
                Intent intent = new Intent(v.getContext(), group_info.class);
//                intent.putExtra("number", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<GroupAll> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDataListAll);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (GroupAll item : mDataListAll) {
                    //TODO filter 대상 setting
                    if (item.getGroupName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

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
