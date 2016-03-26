package com.epicodus.loglegal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class YelpService {
    private Context mContext;

    public YelpService(Context context) {
        this.mContext = context;
    }

    public void findLegalOffices(String zipcode, Callback callback) {
        String CONSUMER_KEY = mContext.getString(R.string.consumer_key);
        String CONSUMER_SECRET = mContext.getString(R.string.consumer_secret);
        String TOKEN = mContext.getString(R.string.token);
        String TOKEN_SECRET = mContext.getString(R.string.token_secret);

        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(TOKEN, TOKEN_SECRET);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.yelp.com/v2/search/?term=legal%20services&limit=10&radius_filter=8000").newBuilder();
        urlBuilder.addQueryParameter("location", zipcode);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Legal> processResults(Response response) {
        ArrayList<Legal> legalOffices = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject legalJSON = businessesJSON.getJSONObject(i);
                    String name = legalJSON.getString("name");
                    String phone = legalJSON.getString("display_phone");
                    String website = legalJSON.getString("url");
                    double rating = legalJSON.getDouble("rating");
                    String imageUrl = legalJSON.getString("image_url");
                    double latitude = legalJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("latitude");
                    double longitude = legalJSON.getJSONObject("location")
                            .getJSONObject("coordinate").getDouble("longitude");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = legalJSON.getJSONObject("location")
                            .getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }

                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesJSON = legalJSON.getJSONArray("categories");

                    // Go back and try to add more categories
                    for (int y = 0; y < categoriesJSON.length(); y++) {
                        categories.add(categoriesJSON.getJSONArray(y).get(0).toString());
                    }
                    Legal legal = new Legal(name, phone, website, rating, imageUrl, address, latitude, longitude, categories);
                    legalOffices.add(legal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return legalOffices;
    }

}
