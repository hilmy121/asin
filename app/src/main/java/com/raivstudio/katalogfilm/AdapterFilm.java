package com.raivstudio.katalogfilm;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.squareup.picasso.Picasso;
import java.text.*;
import java.util.*;

class AdapterFilm extends BaseAdapter{
    private ArrayList<DataMovies> listFilm = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public AdapterFilm(Context context) {
        this.context    = context;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<DataMovies> items){
        listFilm = items;
        notifyDataSetChanged();
    }

    public void addItem(final DataMovies item) {
        listFilm.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        listFilm.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (listFilm == null)
            return 0;

        return listFilm.size();
    }

    @Override
    public DataMovies getItem(int position) {
        return listFilm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ComponentHolder holder = null;
        if (convertView == null) {
            holder                  = new ComponentHolder();
            convertView             = inflater.inflate(R.layout.item_movies, null);
            holder.tvTitle         = convertView.findViewById(R.id.tv_title);
            holder.tvOverview         = convertView.findViewById(R.id.tv_overview);
            holder.tvReleaseDate         = convertView.findViewById(R.id.tv_release);
            holder.ivMovie          = convertView.findViewById(R.id.iv_movie);
            convertView.setTag(holder);
        }else {
            holder = (ComponentHolder) convertView.getTag();
        }

        holder.tvTitle.setText(listFilm.get(position).getTitle());

        holder.tvOverview.setText(listFilm.get(position).getOverview());

        String tanggal = listFilm.get(position).getRelease_date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = format.parse(tanggal);

            SimpleDateFormat _formatbaru = new SimpleDateFormat("EEEE, dd - mm - yyyy");
            String _tanggalRilis = _formatbaru.format(date);
            holder.tvReleaseDate.setText(_tanggalRilis);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/"+ listFilm
                .get(position)
                .getMain_image())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.ivMovie);

        return convertView;
    }

    private static class ComponentHolder {
        TextView tvTitle,tvReleaseDate,tvOverview;
        ImageView ivMovie;
    }
}
