package com.raivstudio.katalogfilm;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_TITLE    = "TITLE";
    public static String EXTRA_RELEASE_DATE  = "RELEASE DATE";
    public static String EXTRA_RATING   = "RATING";
    public static String EXTRA_OVERVIEW = "OVERVIEW";
    public static String EXTRA_MAIN_IMAGE   = "MAIN IMAGE";
    public static String EXTRA_BACKDROP_IMAGE = "BACKDROP IMAGE";
    public static String EXTRA_ORIGIN_LANGUAGE = "ORIGIN LANGUAGE";

    TextView Tvtitle, TvReleaseDate, TvRating, TvOverview, TvLanguage;
    ImageView Ivposter, Ivbackdrop;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title, release_date, rating, overview, main_image, backdrop_image,origin_language;
        TvLanguage = findViewById(R.id.tv_origin_language);
        Tvtitle       = findViewById(R.id.tv_title_detail);
        TvReleaseDate     = findViewById(R.id.tv_release_detail);
        TvRating      = findViewById(R.id.tv_rating_detail);
        TvOverview    = findViewById(R.id.tv_overview_detail);
        Ivposter      = findViewById(R.id.iv_movie_detail);
        Ivbackdrop    = findViewById(R.id.iv_backdrop_detail);

        title    = getIntent().getExtras().getString(DetailActivity.EXTRA_TITLE);/*getIntent().getStringExtra(EXTRA_TITLE);*/
        release_date  = getIntent().getExtras().getString(DetailActivity.EXTRA_RELEASE_DATE);/*getIntent().getStringExtra(EXTRA_RELEASE_DATE);*/
        rating   = getIntent().getExtras().getString(DetailActivity.EXTRA_RATING);/*getIntent().getStringExtra(EXTRA_RATING);*/
        overview = getIntent().getExtras().getString(DetailActivity.EXTRA_OVERVIEW);/*getIntent().getStringExtra(EXTRA_OVERVIEW);*/
        main_image   = getIntent().getExtras().getString(DetailActivity.EXTRA_MAIN_IMAGE);/*getIntent().getStringExtra(EXTRA_MAIN_IMAGE);*/
        backdrop_image = getIntent().getExtras().getString(DetailActivity.EXTRA_BACKDROP_IMAGE);/* getIntent().getStringExtra(EXTRA_BACKDROP_IMAGE);*/
        origin_language = getIntent().getExtras().getString(DetailActivity.EXTRA_ORIGIN_LANGUAGE);/*getIntent().getStringExtra(EXTRA_ORIGIN_LANGUAGE);*/

        Tvtitle.setText(title);
        getActionBar().setTitle(title);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(release_date);
            SimpleDateFormat _format = new SimpleDateFormat("EEEE, dd MMM yyyy");
            String _tanggalrilis = _format.format(date);
            TvReleaseDate.setText(_tanggalrilis);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TvRating.setText("IMDB Rating = "+rating+"out of 10");

        TvLanguage.setText(origin_language);

        TvOverview.setText(overview);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w342/"+main_image)
                .into(Ivposter);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/original/"+backdrop_image)
                .into(Ivbackdrop);

        }
    }

