package com.example.karizp.topmovies;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by karizp on 23/03/2018.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    ArrayList<Movie> movies = new ArrayList<>();

    public  MovieAdapter(Context context, int textViewId, ArrayList<Movie> movies)
    {
        super(context,textViewId,movies);
        this.movies=movies;
    }

    public View getView(int pos, View convertView, ViewGroup parent)
    {
        View v = convertView;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = li.inflate(R.layout.grid_view_items,null);

        TextView title = v.findViewById(R.id.textViewTitle);
        TextView stars = v.findViewById(R.id.textViewStars);
        TextView metascore = v.findViewById(R.id.textViewMetaScore);
        ImageView poster = v.findViewById(R.id.imageViewPoster);

        title.setText(movies.get(pos).getTitle());
        stars.setText(movies.get(pos).getStars());
        metascore.setText(movies.get(pos).getMetaScore());

        if(movies.get(pos).getMoviePoster() !=  null)
            poster.setImageBitmap(movies.get(pos).getMoviePoster());
        else
            poster.setImageResource(movies.get(pos).getImageResourse());

        return v;
    }
}
