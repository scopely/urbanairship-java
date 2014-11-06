package com.scopely.urbanairship.model;

import java.util.ArrayList;
import java.util.List;

public class TagBatchRequest extends ArrayList<TagBatchRequest.TagBatchDevice> {
    public void addDevice(ChannelType type, String identifier, List<String> tags) {
        TagBatchDevice device = new TagBatchDevice(tags);
        switch (type) {
            case IOS_CHANNELS:     device.iosChannel     = identifier; break;
            case ANDROID_CHANNELS: device.androidChannel = identifier; break;
            case AMAZON_CHANNELS:  device.amazonChannel  = identifier; break;
            case DEVICE_PINS:      device.device_pin     = identifier; break;
            case DEVICE_TOKENS:    device.device_token   = identifier; break;
            case APIDS:            device.apid           = identifier; break;
        }
        this.add(device);
    }

    protected class TagBatchDevice {
        protected final List<String> tags;
        protected String iosChannel;
        protected String androidChannel;
        protected String amazonChannel;
        protected String device_pin;
        protected String device_token;
        protected String apid;

        public TagBatchDevice() {
            tags = new ArrayList<>();
        }
        public TagBatchDevice(List<String> tags) {
            this.tags = tags;
        }
    }
}
