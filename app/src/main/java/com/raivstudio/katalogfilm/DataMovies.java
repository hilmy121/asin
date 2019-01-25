package com.raivstudio.katalogfilm;

import org.json.JSONObject;

class DataMovies {

    private String title, release_date, rating, overview, main_image, backdrop_image, origin_language;

    public DataMovies(JSONObject object) {
        try{
            title       = object.getString("title");
            release_date= object.getString("release_date");
            rating      = object.getString("vote_average");
            overview    = object.getString("overview");
            main_image  = object.getString("poster_path");
            backdrop_image = object.getString("backdrop_path");
            origin_language = object.getString("original_language");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public String getMain_image() {
        return main_image;
    }

    public String getBackdrop_image() {
        return backdrop_image;
    }

    public String getOrigin_language() { return origin_language;
    }
}
