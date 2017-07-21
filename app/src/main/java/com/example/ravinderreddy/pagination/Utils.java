package com.example.ravinderreddy.pagination;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.Toast;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Ravinder Reddy on 21-07-2017.
 */

public class Utils extends AppCompatActivity

{
      public static ApiServiceCall callApi(String middleUrl) {
        final String credentials = "phani141@gmail.com"+ ":" + "1pNql0g9saL9UoD19d6411T6G5X7GL6w";
        RestAdapter adapter = new  RestAdapter.Builder().
                setEndpoint(StringConstants.baseurl+middleUrl)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                        request.addHeader("Authorization", string);
                        request.addHeader("Accept", "application/json");
                    }
                })
                .build();
       return  adapter.create(ApiServiceCall.class);
    }


    public static void message( Context mContext,String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
