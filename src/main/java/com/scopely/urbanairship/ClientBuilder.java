package com.scopely.urbanairship;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Credentials;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ClientBuilder {
    public final static String DEFAULT_ENDPOINT = "https://go.urbanairship.com/api";
    public final static RestAdapter.LogLevel DEFAULT_LOG_LEVEL = RestAdapter.LogLevel.BASIC;

    private String endpoint;
    private String credential;
    private RestAdapter.LogLevel logLevel;

    public ClientBuilder() {
        endpoint = DEFAULT_ENDPOINT;
        logLevel = RestAdapter.LogLevel.BASIC;
    }

    public ClientBuilder setCredentials(String appKey, String appSecret) {
        credential = Credentials.basic(appKey, appSecret);
        return this;
    }

    public ClientBuilder setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ClientBuilder setLogLevel(RestAdapter.LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public RestAdapter build() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(request -> request.addHeader("Authorization", credential))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
