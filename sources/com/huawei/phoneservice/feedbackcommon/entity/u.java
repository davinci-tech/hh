package com.huawei.phoneservice.feedbackcommon.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.util.List;

/* loaded from: classes5.dex */
public class u {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(FaqConstants.FAQ_LANGUAGE)
    private String f8330a;

    @SerializedName("problemSourceCode")
    private String b;

    @SerializedName("problemCatalogCode")
    private String c;

    @SerializedName("emuiLanguageCode")
    private String d;

    @SerializedName("countryCode")
    private String e;

    @SerializedName("attachs")
    private List<String> f;

    @SerializedName("logPath")
    private String g;

    @SerializedName("contact")
    private String h;

    @SerializedName("problemDesc")
    private String i;

    @SerializedName("sn")
    private String j;

    @SerializedName("accessToken")
    private String k;

    @SerializedName("appVersion")
    private String l;

    @SerializedName(FaqConstants.FAQ_SOFT_VERSION)
    private String m;

    @SerializedName("model")
    private String n;

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String o;

    @SerializedName("parentProblemId")
    private String p;

    @SerializedName("uploadUrls")
    private List<FeedbackZipBean> q;

    @SerializedName("id")
    private Long r;

    @SerializedName("uniqueCode")
    private String s;

    @SerializedName("srno")
    private String t;

    public void s(String str) {
        this.s = str;
    }

    public void r(String str) {
        this.t = str;
    }

    public void m(String str) {
        this.m = str;
    }

    public void k(String str) {
        this.j = str;
    }

    public void l(String str) {
        this.b = str;
    }

    public void o(String str) {
        this.i = str;
    }

    public void n(String str) {
        this.c = str;
    }

    public void i(String str) {
        this.p = str;
    }

    public void j(String str) {
        this.n = str;
    }

    public void g(String str) {
        this.g = str;
    }

    public void f(String str) {
        this.f8330a = str;
    }

    public void h(String str) {
        this.o = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public void a(String str) {
        this.e = str;
    }

    public void d(String str) {
        this.h = str;
    }

    public void b(List<FeedbackZipBean> list) {
        this.q = list;
    }

    public void c(String str) {
        this.l = str;
    }

    public void d(List<String> list) {
        this.f = list;
    }

    public void b(String str) {
        this.k = str;
    }

    public void b(Long l) {
        this.r = l;
    }
}
