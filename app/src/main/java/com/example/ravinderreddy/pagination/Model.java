package com.example.ravinderreddy.pagination;

import java.io.Serializable;

/**
 * Created by Ravinder Reddy on 21-07-2017.
 */

public class Model implements Serializable
{
    String product_id,
        productName,
        productPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }


}
