package com.huawei.ui.main.stories.guidepage.data;

import java.util.List;

/* loaded from: classes6.dex */
public class GuidePageInfo {
    private String appVersion;
    private long effectiveTime;
    private long expireTime;
    private int guideId;
    private String guideTitle;
    private List<GuideResource> resourceList;

    public void setGuideId(int i) {
        this.guideId = i;
    }

    public int getGuideId() {
        return this.guideId;
    }

    public void setGuideTitle(String str) {
        this.guideTitle = str;
    }

    public String getGuideTitle() {
        return this.guideTitle;
    }

    public void setEffectiveTime(Long l) {
        this.effectiveTime = l.longValue();
    }

    public long getEffectiveTime() {
        return this.effectiveTime;
    }

    public void setExpireTime(Long l) {
        this.expireTime = l.longValue();
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public List<GuideResource> getResourceList() {
        return this.resourceList;
    }

    public void setResourceList(List<GuideResource> list) {
        this.resourceList = list;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }
}
