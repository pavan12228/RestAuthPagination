package com.example.ravinderreddy.pagination;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    public ListView recyclerView;
    ApiServiceCall apiServiceCall;
    CustomAdapter1 customAdapter;
    List<Model> modelList = new LinkedList<>();
    private int currentPage = 0;
    private String TAG = "LokeshLVPA";
    private boolean loading = true;
    private int previousTotal = 0;
    private int visibleThreshold = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (ListView) findViewById(R.id.recyclerview);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
        callApi();

    }

    public void callApi() {
        apiServiceCall = Utils.callApi("");
//         ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
//        progressDialog.setMessage("loading....");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
        apiServiceCall.pagination(10, currentPage, "", "", "A", new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
//                progressDialog.dismiss();
                //Utils.message(getApplicationContext(), "response:" + jsonObject.toString());
                Log.d("response",jsonObject.toString());
                JsonObject jsonObject1 = jsonObject.getAsJsonObject();
                JsonArray jsonArray = jsonObject1.get("products").getAsJsonArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();
                    String pId = jsonObject2.get("product_id").getAsString();
                    String pName = jsonObject2.get("product").getAsString();
                    String price = jsonObject2.get("list_price").getAsString();
                    Model model = new Model();
                    model.setProduct_id(pId);
                    model.setProductName(pName);
                    model.setProductPrice(price);
                    modelList.add(model);
                }
                 customAdapter = new CustomAdapter1(getApplicationContext(), modelList);
                recyclerView.setAdapter(customAdapter);
                customAdapter.notifyDataSetChanged();
                recyclerView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView absListView, int i) {
                    }
                    @Override
                    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        if (loading) {
                            if (totalItemCount > previousTotal) {
                                loading = false;
                                previousTotal = totalItemCount;
                                currentPage++;
                            }
                        }
                        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                            callApi();
                            loading = true;
                        }
                    }
                });


            }


            @Override
            public void failure(RetrofitError error) {
             //   progressDialog.dismiss();
                Utils.message(getApplicationContext(), "Retrofit errror:" + error.getMessage());

            }
        });
    }

}
