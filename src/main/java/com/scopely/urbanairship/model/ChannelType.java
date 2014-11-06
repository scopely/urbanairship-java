package com.scopely.urbanairship.model;

public enum ChannelType {
    IOS_CHANNELS,
    ANDROID_CHANNELS,
    AMAZON_CHANNELS,
    DEVICE_TOKENS,
    APIDS,
    DEVICE_PINS;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
