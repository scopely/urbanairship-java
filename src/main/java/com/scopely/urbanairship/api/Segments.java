package com.scopely.urbanairship.api;

import com.scopely.urbanairship.model.Segment;
import com.scopely.urbanairship.model.SegmentListResponse;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface Segments {
    @GET("/segments")
    SegmentListResponse getAll();

    // used for pagination
    @GET("/segments?{params}")
    SegmentListResponse getMore(@Path("params") String queryParams);

    @GET("/segments/{id}")
    Segment get(@Path("id") String segmentId);

    @POST("/segments")
    Response create(@Body Segment segment);

    @PUT("/segments/{id}")
    Response update(@Path("id") String segmentId,
                  @Body Segment segment);

    @DELETE("/segments/{id}")
    Response delete(@Path("id") String segmentId);
}
