package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.ble.BleConstants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class j implements Serializable {
    private static final long serialVersionUID = -8115898618013300188L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("patchSize")
    private long f8322a;

    @SerializedName("patchNum")
    private int b;

    @SerializedName(ContentResource.FILE_NAME)
    private String c;

    @SerializedName("fileSize")
    private long d;

    @SerializedName("fileHashList")
    private List<ad> j;

    @SerializedName("appID")
    private String g = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);

    @SerializedName(FaqConstants.FAQ_SHASN)
    private String f = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_SHASN);

    @SerializedName("model")
    private String i = com.huawei.phoneservice.faq.base.util.j.c().getSdk("model");

    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String k = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_ROMVERSION);

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String l = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION);

    @SerializedName("osVersion")
    private String m = com.huawei.phoneservice.faq.base.util.j.c().getSdk("osVersion");

    @SerializedName("countryCode")
    private String o = com.huawei.phoneservice.faq.base.util.j.c().getSdk("country");

    @SerializedName("patchVer")
    private String h = "0";

    @SerializedName("logType")
    private int e = 0;

    @SerializedName("others")
    private String n = BleConstants.WEIGHT_KEY;

    public void c(long j) {
        this.f8322a = j;
    }

    public void a(List<ad> list) {
        this.j = list;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(long j) {
        this.d = j;
    }

    public void d(int i) {
        this.b = i;
    }
}
