package com.scopely.urbanairship.api;

import com.scopely.urbanairship.model.TagBatchRequest;
import com.scopely.urbanairship.model.TagBatchResponse;
import com.scopely.urbanairship.model.TagListResponse;
import com.scopely.urbanairship.model.TagUpdateRequest;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface Tags {
    @GET("/tags")
    TagListResponse getAll();

    @PUT("/tags/{name}")
    String create(@Path("name") String tagName);

    @POST("/tags/{name}")
    String updateMembership(@Path("name") String tagName, @Body TagUpdateRequest request);

    @DELETE("/tags/{name}")
    String delete(@Path("name") String tagName);

    @POST("/tags/batch/")
    TagBatchResponse batchUpdate(@Body TagBatchRequest request);
}
