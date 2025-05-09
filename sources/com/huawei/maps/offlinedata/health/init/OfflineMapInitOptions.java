package com.huawei.maps.offlinedata.health.init;

import android.app.Activity;
import android.app.Application;

/* loaded from: classes5.dex */
public class OfflineMapInitOptions {
    private Activity activity;
    private Application context;
    private String deviceName;
    private String h5proUrl;
    private String[] recommendCityIds;
    private String uniqueDevice;

    public Application getContext() {
        return this.context;
    }

    public void setContext(Application application) {
        this.context = application;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getH5proUrl() {
        return this.h5proUrl;
    }

    public void setH5proUrl(String str) {
        this.h5proUrl = str;
    }

    public String getUniqueDevice() {
        return this.uniqueDevice;
    }

    public void setUniqueDevice(String str) {
        this.uniqueDevice = str;
    }

    public String[] getRecommendCityIds() {
        return this.recommendCityIds;
    }

    public void setRecommendCityIds(String[] strArr) {
        this.recommendCityIds = strArr;
    }
}
