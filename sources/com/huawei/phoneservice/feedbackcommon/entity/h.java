package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class h implements Serializable {
    private static final long serialVersionUID = -3641436311375093508L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("fileUniqueFlag")
    private String f8320a;

    @SerializedName("uploadTime")
    private String b;

    @SerializedName("appID")
    private String e = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);

    @SerializedName(FaqConstants.FAQ_SHASN)
    private String c = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN);

    @SerializedName("model")
    private String d = com.huawei.phoneservice.faq.base.util.j.c().getSdk("model");

    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String i = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_ROMVERSION);

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String g = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION);

    @SerializedName("osVersion")
    private String h = com.huawei.phoneservice.faq.base.util.j.c().getSdk("osVersion");

    @SerializedName("countryCode")
    private String f = com.huawei.phoneservice.faq.base.util.j.c().getSdk("country");

    public void d(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.f8320a = str;
    }
}
