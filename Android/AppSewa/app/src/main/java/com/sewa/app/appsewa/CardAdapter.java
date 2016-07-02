package com.sewa.app.appsewa;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private Context context;
    //List to store all sites
    private List<Post> posts=new ArrayList<Post>();
    //Constructor of this class
    public CardAdapter(List<Post> posts, Context context){
        super();
        //setting all sites
        this.posts = posts;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Getting the particular item from the list
        Post newPost =  posts.get(position);

        //Showing data on the views
        holder.tvOrgName.setText(newPost.getOrg_name());
        holder.tvCause.setText(newPost.getCause());
        holder.tvNumOfVols.setText(String.valueOf(newPost.getNum_of_vols()));
        holder.tvEmergencyLvl.setText(newPost.getEmergency_lvl());
        holder.tvLocation.setText(newPost.getDisaster_location());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public TextView tvOrgName,tvCause, tvNumOfVols,tvEmergencyLvl, tvLocation ;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            tvOrgName = (TextView) itemView.findViewById(R.id.tvOrgName);
            tvCause = (TextView) itemView.findViewById(R.id.tvCause);
            tvNumOfVols = (TextView) itemView.findViewById(R.id.tvNumOfVols);
            tvEmergencyLvl = (TextView) itemView.findViewById(R.id.tvEmergencyLvl);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}

