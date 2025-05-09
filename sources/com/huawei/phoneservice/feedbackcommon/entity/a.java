package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class a implements Serializable {
    private static final long serialVersionUID = 735508157925975893L;

    @SerializedName(FaqConstants.FAQ_SHASN)
    private String e;

    @SerializedName("countryCode")
    private String j;

    @SerializedName("appID")
    private String b = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);

    @SerializedName("model")
    private String c = com.huawei.phoneservice.faq.base.util.j.c().getSdk("model");

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String f8313a = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_ROMVERSION);

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String d = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION);

    @SerializedName("osVersion")
    private String i = com.huawei.phoneservice.faq.base.util.j.c().getSdk("osVersion");

    public a(String str, String str2) {
        this.e = str;
        this.j = str2;
    }
}
