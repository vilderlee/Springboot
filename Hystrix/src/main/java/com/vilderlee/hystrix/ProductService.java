package com.vilderlee.hystrix;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSONObject;
import com.vilderlee.hystrix.model.ProductInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/15      Create this file
 * </pre>
 */
public class ProductService {

    public ProductInfo getStock(Long productId) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder("http://127.0.0.1:8080/getProductInfo");
        uriBuilder.addParameter("productId", String.valueOf(productId));
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity entity = response.getEntity();
        ProductInfo productInfo = JSONObject.parseObject(EntityUtils.toString(entity), ProductInfo.class);
        return productInfo;
    }
}
