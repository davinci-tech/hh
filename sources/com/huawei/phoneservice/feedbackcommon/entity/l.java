package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes5.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("problemId")
    private String f8324a;

    @SerializedName("accessToken")
    private String b;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int c;

    @SerializedName("orderType")
    private int d;

    @SerializedName("problemSourceCode")
    private String e;

    public void c(int i) {
        this.c = i;
    }

    public void a(int i) {
        this.d = i;
    }

    public l(String str, String str2, String str3) {
        this.b = str;
        this.e = str2;
        this.f8324a = str3;
    }
}
