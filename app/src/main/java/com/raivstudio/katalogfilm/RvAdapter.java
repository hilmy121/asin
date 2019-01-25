package com.raivstudio.katalogfilm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private ArrayList<DataMovies> data = new ArrayList<>();
    private LayoutInflater inflater;
    private static Context context;
    private AdapterView.OnClickListener listener;


    public RvAdapter(ArrayList<DataMovies> data,Context context/*, AdapterView.OnClickListener listener*/){
        this.context=context;
        this.data = data;
        notifyDataSetChanged();
    }
    public void setData(ArrayList<DataMovies> data){
        this.data = data;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data!=null) {
            return data.size();
        }else {
            return 0;
        }

    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,desc,date;
        private ImageView image;
        public ViewHolder (View itemView){
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_movie);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            desc = (TextView) itemView.findViewById(R.id.tv_overview);
            date = (TextView) itemView.findViewById(R.id.tv_release);

        }
        public void bind(final DataMovies data/*,AdapterView.OnClickListener listener*/){
            title.setText(data.getTitle());
            desc.setText(data.getOverview());
            Picasso.with(itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w154/"+data)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_panorama_black_24dp)
                    .into(image);
            date.setText(data.getRelease_date());
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    MainActivity mainActivity = new MainActivity();
                    Bundle mbundle = new Bundle();
                    Intent SwitchIntent = new Intent(context,
                            DetailActivity.class);
                    mbundle.putString(DetailActivity.EXTRA_BACKDROP_IMAGE,data.getBackdrop_image());
                    mbundle.putString(DetailActivity.EXTRA_MAIN_IMAGE,data.getMain_image());
                    mbundle.putString(DetailActivity.EXTRA_OVERVIEW,data.getOverview());
                    mbundle.putString(DetailActivity.EXTRA_TITLE,data.getTitle());
                    mbundle.putString(DetailActivity.EXTRA_RATING,data.getRating());
                    mbundle.putString(DetailActivity.EXTRA_RELEASE_DATE,data.getRelease_date());
                    SwitchIntent.putExtras(mbundle);

                    context.startActivity(SwitchIntent);






                }
            });

        }

    }
    public interface OnItemClickListener{
        void OnItemClick(DataMovies data);
    }
}
