package com.huawei.health.featuremarketing.rules.activityinfo;

import java.util.List;

/* loaded from: classes3.dex */
public class ActivityInfo {
    private final String resultCode;
    private final String resultDesc;
    private final UserActivityInfo userActivityInfo;
    private final List<UserActivityInfo> userActivityInfoList;

    public ActivityInfo(e eVar) {
        this.resultCode = eVar.d;
        this.resultDesc = eVar.f2352a;
        this.userActivityInfo = eVar.e;
        this.userActivityInfoList = eVar.b;
    }

    /* loaded from: classes8.dex */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private String f2352a;
        private List<UserActivityInfo> b;
        private String d;
        private UserActivityInfo e;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public UserActivityInfo getUserActivityInfo() {
        return this.userActivityInfo;
    }

    public List<UserActivityInfo> getUserActivityInfoList() {
        return this.userActivityInfoList;
    }
}
