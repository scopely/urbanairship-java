package com.scopely.urbanairship;

import com.scopely.urbanairship.api.Tags;
import com.scopely.urbanairship.model.ChannelType;
import com.scopely.urbanairship.model.TagBatchRequest;
import com.scopely.urbanairship.model.TagUpdateRequest;
import org.junit.Before;
import org.junit.Test;
import retrofit.RestAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;

public class TagTest {
    RestAdapter client;
    Tags tags;

    @Before
    public void setup() {
        client = new ClientBuilder()
                .setCredentials(System.getenv("UA_APP_KEY"), System.getenv("UA_APP_SECRET"))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        tags = client.create(Tags.class);
    }

    @Test
    public void tagLifecycle() {
        List<String> allTags = tags.getAll().getTags();
        assertThat(allTags.size()).isGreaterThan(0);

        if (allTags.contains("my_tag")) {
            tags.delete("my_tag");
        }
        if (allTags.contains("my_other_tag")) {
            tags.delete("my_other_tag");
        }

        tags.create("my_tag");
        // my_other_tag doesn't get created explicitly

        TagUpdateRequest request = new TagUpdateRequest();
        request.addMember(ChannelType.DEVICE_TOKENS, System.getenv("UA_DEVICE_TOKEN"));
        request.addMember(ChannelType.ANDROID_CHANNELS, System.getenv("UA_ANDROID_CHANNEL"));
        tags.updateMembership("my_tag", request);

        TagBatchRequest batch = new TagBatchRequest();
        batch.addDevice(ChannelType.DEVICE_TOKENS, System.getenv("UA_DEVICE_TOKEN"), Arrays.asList("my_tag", "level7"));
        batch.addDevice(ChannelType.ANDROID_CHANNELS, System.getenv("UA_ANDROID_CHANNEL"), Arrays.asList("my_tag"));
        batch.addDevice(ChannelType.IOS_CHANNELS, UUID.randomUUID().toString(), Arrays.asList("my_tag", "my_other_tag")); // doesn't exist
        tags.batchUpdate(batch);
    }
}
