package com.example.mana.cinematvclub.model.models;

import com.google.gson.annotations.SerializedName;

public class ExternalIds {
  @SerializedName("imdb_id") private String imdbId;
  @SerializedName("facebook_id") private String facebookId;
  @SerializedName("instagram_id") private String instagramId;
  @SerializedName("twitter_id") private String twitterId;

  public String getImdbId() {
    return imdbId;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public String getInstagramId() {
    return instagramId;
  }

  public String getTwitterId() {
    return twitterId;
  }
}
