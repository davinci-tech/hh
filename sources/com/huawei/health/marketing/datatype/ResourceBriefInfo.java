package com.huawei.health.marketing.datatype;

import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ResourceBriefInfo {
    private final ABTestDetail abTestDetail;
    private final boolean abTestSwitch;
    private final List<String> bucketIds;
    private final String category;
    private final ResourceContentBase content;
    public int contentType;
    private final long effectiveTime;
    private final long expirationTime;
    private int functionID;
    private String logicControlCharacter;
    private final MarketingRule marketingRule;
    private final long modifyTime;
    private final boolean participateAbTest;
    private final int priority;
    private final List<RcmItem> rcmTabList;
    private final List<RcmItem> recommendList;
    private final String resourceId;
    private final String resourceName;
    private boolean showLogicSwitch;
    private boolean smartRecommend;
    private final String sortingAlgId;
    private final String sortingRules;
    private final List<String> strategyIds;
    private final String timeZone;

    public ResourceBriefInfo(Builder builder) {
        this.resourceId = builder.resourceId;
        this.resourceName = builder.resourceName;
        this.category = builder.category;
        this.contentType = builder.contentType;
        this.modifyTime = builder.modifyTime;
        this.effectiveTime = builder.effectiveTime;
        this.expirationTime = builder.expirationTime;
        this.timeZone = builder.timeZone;
        this.priority = builder.priority;
        this.marketingRule = builder.marketingRule;
        this.participateAbTest = builder.participateAbTest;
        this.abTestSwitch = builder.abTestSwitch;
        this.abTestDetail = builder.abTestDetail;
        this.sortingRules = builder.sortingRules;
        this.sortingAlgId = builder.sortingAlgId;
        this.content = builder.content;
        this.smartRecommend = builder.smartRecommend;
        this.functionID = builder.functionID;
        this.strategyIds = builder.strategyIds;
        this.bucketIds = builder.bucketIds;
        this.recommendList = builder.recommendList;
        this.rcmTabList = builder.rcmTabList;
    }

    public static class Builder {
        private ABTestDetail abTestDetail;
        private boolean abTestSwitch;
        private List<String> bucketIds;
        private String category;
        private ResourceContentBase content;
        private int contentType;
        private long effectiveTime;
        private long expirationTime;
        private int functionID;
        private MarketingRule marketingRule;
        private long modifyTime;
        private boolean participateAbTest;
        private int priority;
        private List<RcmItem> rcmTabList;
        private List<RcmItem> recommendList;
        private String resourceId;
        private String resourceName;
        private boolean smartRecommend;
        private String sortingAlgId;
        private String sortingRules;
        private List<String> strategyIds;
        private String timeZone;

        public void setSmartRecommend(boolean z) {
            this.smartRecommend = z;
        }

        public void setFunctionID(int i) {
            this.functionID = i;
        }

        public void setAbTestDetail(ABTestDetail aBTestDetail) {
            this.abTestDetail = aBTestDetail;
        }

        public void setStrategyIds(List<String> list) {
            this.strategyIds = list;
        }

        public void setBucketIds(List<String> list) {
            this.bucketIds = list;
        }

        public void setRecommendList(List<RcmItem> list) {
            this.recommendList = list;
        }

        public void setRcmTabList(List<RcmItem> list) {
            this.rcmTabList = list;
        }

        public void setAbTestSwitch(boolean z) {
            this.abTestSwitch = z;
        }

        public void setSortingAlgId(String str) {
            this.sortingAlgId = str;
        }

        public void setSortingRules(String str) {
            this.sortingRules = str;
        }

        public Builder setResourceId(String str) {
            this.resourceId = str;
            return this;
        }

        public Builder setResourceName(String str) {
            this.resourceName = str;
            return this;
        }

        public Builder setCategory(String str) {
            this.category = str;
            return this;
        }

        public void setParticipateAbTest(boolean z) {
            this.participateAbTest = z;
        }

        public Builder setContentType(int i) {
            this.contentType = i;
            return this;
        }

        public Builder setModifyTime(long j) {
            this.modifyTime = j;
            return this;
        }

        public Builder setEffectiveTime(long j) {
            this.effectiveTime = j;
            return this;
        }

        public Builder setExpirationTime(long j) {
            this.expirationTime = j;
            return this;
        }

        public Builder setTimeZone(String str) {
            this.timeZone = str;
            return this;
        }

        public Builder setPriority(int i) {
            this.priority = i;
            return this;
        }

        public Builder setMarketingRule(MarketingRule marketingRule) {
            this.marketingRule = marketingRule;
            return this;
        }

        public Builder setContent(ResourceContentBase resourceContentBase) {
            this.content = resourceContentBase;
            return this;
        }

        public ResourceBriefInfo build() {
            return new ResourceBriefInfo(this);
        }
    }

    public boolean getSmartRecommend() {
        return "intelligent_alpha".equals(this.sortingRules);
    }

    public int getFunctionID() {
        return this.functionID;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public String getCategory() {
        return this.category;
    }

    public ABTestDetail getAbTestDetail() {
        return this.abTestDetail;
    }

    public List<String> getStrategyIds() {
        return this.strategyIds;
    }

    public List<String> getBucketIds() {
        return this.bucketIds;
    }

    public List<RcmItem> getRecommendList() {
        return this.recommendList;
    }

    public List<RcmItem> getRcmTabList() {
        return this.rcmTabList;
    }

    public boolean getAbTestSwitch() {
        return this.abTestSwitch;
    }

    public String getSortingAlgId() {
        return this.sortingAlgId;
    }

    public String getSortingRules() {
        return this.sortingRules;
    }

    public boolean getParticipateAbTest() {
        return this.participateAbTest;
    }

    public void modifyContentType(int i) {
        this.contentType = i;
    }

    public int getContentType() {
        return this.contentType;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public long getEffectiveTime() {
        return this.effectiveTime;
    }

    public long getExpirationTime() {
        return this.expirationTime;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public int getPriority() {
        return this.priority;
    }

    public ResourceContentBase getContent() {
        return this.content;
    }

    public MarketingRule getMarketingRule() {
        return this.marketingRule;
    }

    public boolean isShowLogicSwitch() {
        return this.showLogicSwitch;
    }

    public String getLogicControlCharacter() {
        return this.logicControlCharacter;
    }

    public String toString() {
        return "ResourceBriefInfo{resourceId='" + this.resourceId + "', resourceName='" + this.resourceName + "', category='" + this.category + "', contentType=" + this.contentType + ", modifyTime=" + this.modifyTime + ", effectiveTime=" + this.effectiveTime + ", expirationTime=" + this.expirationTime + ", timeZone='" + this.timeZone + "', priority=" + this.priority + ", marketingRule=" + this.marketingRule + ", participateAbTest=" + this.participateAbTest + ", abTestSwitch=" + this.abTestSwitch + ", abTestDetail=" + this.abTestDetail + ", strategyIds=" + this.strategyIds + ", bucketIds=" + this.bucketIds + ", recommendList=" + this.recommendList + ", rcmTabList=" + this.rcmTabList + ", sortingRules='" + this.sortingRules + "', sortingAlgId='" + this.sortingAlgId + "', content=" + this.content + ", smartRecommend=" + this.smartRecommend + ", functionID=" + this.functionID + ", showLogicSwitch=" + this.showLogicSwitch + ", logicControlCharacter=" + this.logicControlCharacter + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceBriefInfo)) {
            return false;
        }
        ResourceBriefInfo resourceBriefInfo = (ResourceBriefInfo) obj;
        return getContentType() == resourceBriefInfo.getContentType() && getModifyTime() == resourceBriefInfo.getModifyTime() && getEffectiveTime() == resourceBriefInfo.getEffectiveTime() && getExpirationTime() == resourceBriefInfo.getExpirationTime() && getPriority() == resourceBriefInfo.getPriority() && getResourceId().equals(resourceBriefInfo.getResourceId()) && getResourceName().equals(resourceBriefInfo.getResourceName()) && getCategory().equals(resourceBriefInfo.getCategory());
    }

    public int hashCode() {
        return Objects.hash(getResourceId(), getResourceName(), getCategory(), Integer.valueOf(getContentType()), Long.valueOf(getModifyTime()), Long.valueOf(getEffectiveTime()), Long.valueOf(getExpirationTime()), Integer.valueOf(getPriority()));
    }
}
