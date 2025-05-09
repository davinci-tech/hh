package com.huawei.maps.offlinedata.service.cloud.dto;

import com.huawei.maps.offlinedata.network.d;

/* loaded from: classes5.dex */
public class AuthResponseDTO extends d {
    private Boolean enableAutoUpdates;
    private String p;
    private String politicalView;
    private String reviewNumberOfWatchMap;
    private String watchMapContourVersion;
    private String watchMapVersion;

    public String getP() {
        return this.p;
    }

    public void setP(String str) {
        this.p = str;
    }

    public String getPoliticalView() {
        return this.politicalView;
    }

    public void setPoliticalView(String str) {
        this.politicalView = str;
    }

    public String getReviewNumberOfWatchMap() {
        return this.reviewNumberOfWatchMap;
    }

    public void setReviewNumberOfWatchMap(String str) {
        this.reviewNumberOfWatchMap = str;
    }

    public String getWatchMapVersion() {
        return this.watchMapVersion;
    }

    public void setWatchMapVersion(String str) {
        this.watchMapVersion = str;
    }

    public String getWatchMapContourVersion() {
        return this.watchMapContourVersion;
    }

    public void setWatchMapContourVersion(String str) {
        this.watchMapContourVersion = str;
    }

    public Boolean getEnableAutoUpdates() {
        return this.enableAutoUpdates;
    }

    public void setEnableAutoUpdates(Boolean bool) {
        this.enableAutoUpdates = bool;
    }
}
