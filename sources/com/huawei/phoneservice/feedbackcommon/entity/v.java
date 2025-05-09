package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class v implements Serializable {
    private static final long serialVersionUID = -4589818087876763047L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("countryCode")
    private String f8331a;

    @SerializedName(FaqConstants.FAQ_LANGUAGE)
    private String b;

    @SerializedName("problemSourceCode")
    private String c;

    @SerializedName("emuiLanguageCode")
    private String d;

    public v(String str, String str2, String str3, String str4) {
        this.c = str;
        this.f8331a = str2;
        this.b = str3;
        this.d = str4;
    }
}
