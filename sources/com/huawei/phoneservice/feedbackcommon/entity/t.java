package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;

/* loaded from: classes9.dex */
public class t {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("fileUniqueFlag")
    private String f8329a;

    @SerializedName(FaqConstants.FAQ_SHASN)
    private String c;

    @SerializedName("uploadTime")
    private String e;

    @SerializedName("countryCode")
    private String h;

    @SerializedName("appID")
    private String b = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);

    @SerializedName("model")
    private String d = com.huawei.phoneservice.faq.base.util.j.c().getSdk("model");

    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String i = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_ROMVERSION);

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String f = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION);

    @SerializedName("osVersion")
    private String g = com.huawei.phoneservice.faq.base.util.j.c().getSdk("osVersion");

    public void c(String str) {
        this.e = str;
    }

    public void a(String str) {
        this.f8329a = str;
    }

    public t(String str, String str2) {
        this.c = str;
        this.h = str2;
    }
}
