package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class e implements Serializable {
    private static final long serialVersionUID = 7368877497797411927L;

    @SerializedName("fileUniqueFlag")
    private String e;

    @SerializedName("appID")
    private String c = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(FaqConstants.FAQ_SHASN)
    private String f8318a = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN);

    @SerializedName("model")
    private String b = com.huawei.phoneservice.faq.base.util.j.c().getSdk("model");

    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String i = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_ROMVERSION);

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String h = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION);

    @SerializedName("osVersion")
    private String g = com.huawei.phoneservice.faq.base.util.j.c().getSdk("osVersion");

    @SerializedName("countryCode")
    private String j = com.huawei.phoneservice.faq.base.util.j.c().getSdk("country");

    @SerializedName("patchVer")
    private String d = "0";

    public void b(String str) {
        this.e = str;
    }
}
