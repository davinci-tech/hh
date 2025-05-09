package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.ble.BleConstants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes5.dex */
public class aa implements Serializable {
    private static final long serialVersionUID = -2965823344242365762L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("patchSize")
    private long f8314a;

    @SerializedName(ContentResource.FILE_NAME)
    private String b;

    @SerializedName("encryptKey")
    private String c;

    @SerializedName("logType")
    private int d;

    @SerializedName("fileSize")
    private long e;

    @SerializedName("fileHashList")
    private List<ad> h;

    @SerializedName("patchNum")
    private int j;

    @SerializedName(FaqConstants.FAQ_SHASN)
    private String m;

    @SerializedName("countryCode")
    private String s;

    @SerializedName("appID")
    private String i = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_LOG_SERVER_APPID);

    @SerializedName("model")
    private String k = com.huawei.phoneservice.faq.base.util.j.c().getSdk("model");

    @SerializedName(FaqConstants.FAQ_ROMVERSION)
    private String o = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_ROMVERSION);

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String n = com.huawei.phoneservice.faq.base.util.j.c().getSdk(FaqConstants.FAQ_EMUIVERSION);

    @SerializedName("osVersion")
    private String l = com.huawei.phoneservice.faq.base.util.j.c().getSdk("osVersion");

    @SerializedName("patchVer")
    private String f = "0";

    @SerializedName("others")
    private String g = BleConstants.WEIGHT_KEY;

    public void d(String str) {
        this.b = str;
    }

    public void a(long j) {
        this.f8314a = j;
    }

    public void d(int i) {
        this.j = i;
    }

    public void b(List<ad> list) {
        this.h = list;
    }

    public void b(String str) {
        this.c = str;
    }

    public void b(long j) {
        this.e = j;
    }

    public void a(int i) {
        this.d = i;
    }

    public aa(String str, String str2) {
        this.m = str;
        this.s = str2;
    }
}
