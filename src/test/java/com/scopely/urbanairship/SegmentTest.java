package com.scopely.urbanairship;

import com.scopely.urbanairship.api.Segments;
import com.scopely.urbanairship.model.Segment;
import com.scopely.urbanairship.model.SegmentListResponse;
import org.junit.Before;
import org.junit.Test;
import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.Response;

import java.util.Optional;

import static org.fest.assertions.api.Assertions.assertThat;

public class SegmentTest {
    RestAdapter client;
    Segments segments;

    @Before
    public void setup() {
        client = new ClientBuilder()
                .setCredentials(System.getenv("UA_APP_KEY"), System.getenv("UA_APP_SECRET"))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        segments = client.create(Segments.class);
    }

    @Test
    public void segmentLifecycle() {
        segments.create(new Segment("My tag", new Segment.TagCriteria("my_tag")));

        SegmentListResponse listResponse = segments.getAll();
        int originalSize = listResponse.getSegments().size();
        assertThat(originalSize).isGreaterThan(0);

        Segment segment = listResponse.getSegments().get(0);
        Segment sameSegment = segments.get(segment.getId());
        assertThat(segment).isNotNull();
        assertThat(sameSegment).isNotNull();

        Segment updatedSegment = new Segment("My updated tag", new Segment.TagCriteria("my_updated_tag"));
        segments.update(segment.getId(), updatedSegment);

        Segment mostRecentSegment = segments.get(segment.getId());
        assertThat(mostRecentSegment.getDisplayName()).isEqualTo(updatedSegment.getDisplayName());

        segments.delete(segment.getId());

        listResponse = segments.getAll();
        assertThat(listResponse.getSegments().size()).isEqualTo(originalSize - 1);
    }

    @Test
    public void creation() {
        Segment request = new Segment("Creation test", new Segment.TagCriteria("my_tag"));

        // create and scrape id
        Response resp = segments.create(request);
        Optional<Header> header = resp.getHeaders().stream().skip(1).filter(h -> h.getName().equals("Location")).findFirst();
        assertThat(header.isPresent()).isTrue();
        String location = header.get().getValue();
        String id = location.substring(location.lastIndexOf('/') + 1);
        assertThat(id).contains("-");
        assertThat(id.length()).isGreaterThan(20);

        // check that it exists
        Segment seg = segments.get(id);
        assertThat(seg.getDisplayName()).isEqualTo(request.getDisplayName());

        // cleanup
        segments.delete(id);
    }
}
