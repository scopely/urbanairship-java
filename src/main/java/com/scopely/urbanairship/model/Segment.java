package com.scopely.urbanairship.model;

public class Segment {
    private Long creationDate, modificationDate; // Long for nullable
    private String displayName;
    private String id;
    private TagCriteria criteria; // TODO

    public Segment(String displayName, TagCriteria criteria) {
        this.displayName = displayName;
        this.criteria = criteria;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public Long getModificationDate() {
        return modificationDate;
    }

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public static class TagCriteria {
        private String tag;

        public TagCriteria(String tagName) {
            tag = tagName;
        }

        public String getTagName() {
            return tag;
        }
    }
}
