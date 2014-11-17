package com.scopely.urbanairship.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TagUpdateRequest extends HashMap<ChannelType, TagUpdateRequest.ChannelUpdateObject> {
    public ChannelUpdateObject getChannelObject(ChannelType type) {
        if (this.containsKey(type)) {
            return this.get(type);
        } else {
            ChannelUpdateObject updates = new ChannelUpdateObject();
            this.put(type, updates);
            return updates;
        }
    }

    public void addMember(ChannelType type, String member) {
        getChannelObject(type).addMember(member);
    }

    public void removeMember(ChannelType type, String member) {
        getChannelObject(type).removeMember(member);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    protected class ChannelUpdateObject {
        private final Set<String> add;
        private final Set<String> remove;

        public ChannelUpdateObject() {
            add = new HashSet<>();
            remove = new HashSet<>();
        }

        public void addMember(String channelId) {
            add.add(channelId);
        }

        public void removeMember(String channelId) {
            remove.add(channelId);
        }
    }
}
