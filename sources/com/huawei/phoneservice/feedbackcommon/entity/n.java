package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;

/* loaded from: classes9.dex */
public class n {

    @SerializedName("fileUniqueFlag")
    private String b;

    @SerializedName(FaqConstants.FAQ_SHASN)
    private String e;

    @SerializedName("countryCode")
    private String j;

    @SerializedName("appID")
    private String c = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("model")
    private String f8326a = com.huawei.phoneservice.faq.base.util.j.c().getSdk("model");

    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String h = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_ROMVERSION);

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String i = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION);

    @SerializedName("osVersion")
    private String g = com.huawei.phoneservice.faq.base.util.j.c().getSdk("osVersion");

    @SerializedName("patchVer")
    private String d = "0";

    public void e(String str) {
        this.b = str;
    }

    public n(String str, String str2) {
        this.e = str;
        this.j = str2;
    }
}
