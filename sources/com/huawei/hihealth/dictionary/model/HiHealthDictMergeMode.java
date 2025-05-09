package com.huawei.hihealth.dictionary.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class HiHealthDictMergeMode {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dataSourcePriority")
    private int[] f4127a;

    @SerializedName("compareKey")
    private String b;

    @SerializedName("categoryPriority")
    private String[] c;

    @SerializedName("sourceInternalMergePolicy")
    private String d;

    @SerializedName("mergePolicy")
    private String e;

    public String c() {
        return this.e;
    }

    public int[] d() {
        return (int[]) this.f4127a.clone();
    }

    public String[] a() {
        return (String[]) this.c.clone();
    }

    public String b() {
        return this.b;
    }

    public String e() {
        return this.d;
    }
}
