package com.example.karizp.topmovies;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Document document;
    String[] titles = new String[20];
    String[] src = new String[20];
    String[] stars = new String[20];
    String[] metaScores = new String[20];
    GridView gridView;
    ArrayList<Movie> movies=new ArrayList<>();
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridViewMovies);
        DownloadTask downloadTask = new DownloadTask();

        try {
            downloadTask.execute("http://www.imdb.com/list/ls064079588/").get();

            MovieAdapter movieAdapter=new MovieAdapter(this,R.layout.grid_view_items,movies);
            gridView.setAdapter(movieAdapter);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMovies()
    {
        for(int i = 0; i < 20; i++)
        {

            try {
                ImageDownloadTask imageDownloadTask = new ImageDownloadTask();
                Log.i("loadMovies","loading "+src[i]);

                bitmap = imageDownloadTask.execute(src[i]).get();
                Movie movie = new Movie(titles[i],stars[i],metaScores[i],bitmap);
                movies.add(movie);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    class DownloadTask extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... url)
        {
            try {
                document = Jsoup.connect(url[0]).get();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            return "";
        }

        protected void onPostExecute(String s)
        {
            Elements moviesTitles = document.select(".lister-item-header a");
            Elements moviesImage = document.select(".lister-item-image .loadlate");
            Elements movieStars = document.select(".lister-item-content .ratings-bar .inline-block.ratings-imdb-rating strong");
            Elements movieMetaScore =document.select(".lister-item-content .ratings-bar .inline-block.ratings-metascore span");

            for(int i = 0; i<20; i++)
            {
                titles[i]=(moviesTitles.get(i).text());
                src[i] = moviesImage.get(i).attr("loadlate");
                stars[i] = movieStars.get(i).text();
                metaScores[i] = movieMetaScore.get(i).text();
            }

            loadMovies();
        }
    }
    class ImageDownloadTask extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected  Bitmap doInBackground(String...urls)
        {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection =(HttpURLConnection)url.openConnection();

                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
