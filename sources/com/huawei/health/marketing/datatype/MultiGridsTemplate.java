package com.huawei.health.marketing.datatype;

import java.util.List;

/* loaded from: classes3.dex */
public class MultiGridsTemplate {
    private String extend;
    private List<SingleGridContent> gridContents;
    private boolean intelligentFlag;
    private int itemLimit;
    private String linkValue;
    private String name;
    private Boolean nameVisibility;
    private String resourceSceneId;
    private String resourceSceneName;
    private Integer sleepAudioType;
    private String sortingRules;

    public MultiGridsTemplate(Builder builder) {
        this.name = builder.name;
        this.nameVisibility = builder.nameVisibility;
        this.linkValue = builder.linkValue;
        this.sleepAudioType = builder.sleepAudioType;
        this.extend = builder.extend;
        this.gridContents = builder.gridContents;
        this.intelligentFlag = builder.intelligentFlag;
        this.sortingRules = builder.sortingRules;
        this.resourceSceneId = builder.resourceSceneId;
        this.resourceSceneName = builder.resourceSceneName;
        this.itemLimit = builder.itemLimit;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Boolean getNameVisibility() {
        return this.nameVisibility;
    }

    public void setNameVisibility(Boolean bool) {
        this.nameVisibility = bool;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public Integer getSleepAudioType() {
        return this.sleepAudioType;
    }

    public void setSleepAudioType(Integer num) {
        this.sleepAudioType = num;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setExtend(String str) {
        this.extend = str;
    }

    public List<SingleGridContent> getGridContents() {
        return this.gridContents;
    }

    public void setGridContents(List<SingleGridContent> list) {
        this.gridContents = list;
    }

    public boolean isIntelligentFlag() {
        return this.intelligentFlag;
    }

    public void setIntelligentFlag(boolean z) {
        this.intelligentFlag = z;
    }

    public String getSortingRules() {
        return this.sortingRules;
    }

    public void setSortingRules(String str) {
        this.sortingRules = str;
    }

    public String getResourceSceneId() {
        return this.resourceSceneId;
    }

    public void setResourceSceneId(String str) {
        this.resourceSceneId = str;
    }

    public String getResourceSceneName() {
        return this.resourceSceneName;
    }

    public void setResourceSceneName(String str) {
        this.resourceSceneName = str;
    }

    public int getItemLimit() {
        return this.itemLimit;
    }

    public void setItemLimit(int i) {
        this.itemLimit = i;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        private String extend;
        private List<SingleGridContent> gridContents;
        private boolean intelligentFlag;
        private int itemLimit;
        private String linkValue;
        private String name;
        private Boolean nameVisibility;
        private String resourceSceneId;
        private String resourceSceneName;
        private Integer sleepAudioType;
        private String sortingRules;

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setNameVisibility(Boolean bool) {
            this.nameVisibility = bool;
            return this;
        }

        public Builder setLinkValue(String str) {
            this.linkValue = str;
            return this;
        }

        public Builder setSleepAudioType(Integer num) {
            this.sleepAudioType = num;
            return this;
        }

        public Builder setExtend(String str) {
            this.extend = str;
            return this;
        }

        public Builder setGridContents(List<SingleGridContent> list) {
            this.gridContents = list;
            return this;
        }

        public Builder setIntelligentFlag(boolean z) {
            this.intelligentFlag = z;
            return this;
        }

        public Builder setSortingRules(String str) {
            this.sortingRules = str;
            return this;
        }

        public Builder setResourceSceneId(String str) {
            this.resourceSceneId = str;
            return this;
        }

        public Builder setResourceSceneName(String str) {
            this.resourceSceneName = str;
            return this;
        }

        public Builder setItemLimit(int i) {
            this.itemLimit = i;
            return this;
        }

        public MultiGridsTemplate build() {
            return new MultiGridsTemplate(this);
        }
    }
}
