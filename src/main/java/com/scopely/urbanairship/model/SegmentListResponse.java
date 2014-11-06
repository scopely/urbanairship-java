package com.scopely.urbanairship.model;

import com.scopely.urbanairship.api.Segments;
import com.sun.istack.internal.Nullable;

import java.net.URI;
import java.util.List;

public class SegmentListResponse {
    @Nullable private URI nextPage;
    private List<Segment> segments;

    @Nullable public URI getNextPageUrl() {
        return nextPage;
    }

    @Nullable public SegmentListResponse fetchNextPageUrl(Segments api) {
        if (nextPage == null) {
            return null;
        }
        return api.getMore(nextPage.getQuery());
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
