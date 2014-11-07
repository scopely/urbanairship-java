package com.scopely.urbanairship.model;

import com.scopely.urbanairship.api.Segments;

import java.net.URI;
import java.util.List;

public class SegmentListResponse {
    private URI nextPage;
    private List<Segment> segments;

    public URI getNextPageUrl() {
        return nextPage;
    }

    public SegmentListResponse fetchNextPageUrl(Segments api) {
        if (nextPage == null) {
            return null;
        }
        return api.getMore(nextPage.getQuery());
    }

    public List<Segment> getSegments() {
        return segments;
    }
}
