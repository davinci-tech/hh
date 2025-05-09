package com.huawei.hihealth.dictionary.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.dictionary.utils.DictUtils;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class HiHealthDictStat {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mergePolicy")
    private String f4128a;

    @SerializedName("associateValue")
    private Double b;

    @SerializedName("statPolicy")
    private String c;

    @SerializedName("format")
    private String d;

    @SerializedName("statFieldName")
    private String e;

    @SerializedName("statType")
    private int g;

    public void a(int i) {
        if (this.g == 0) {
            this.g = DictUtils.b(i, this.e);
        }
    }

    public boolean g() {
        if (!DictUtils.a(this.e)) {
            LogUtil.e("HiH_HiHealthDictStat", "statFieldName is illegal: ", this.e);
            return false;
        }
        if (HealthDataStatPolicy.validate(this.c)) {
            return true;
        }
        LogUtil.e("HiH_HiHealthDictStat", "statPolicy is illegal: ", this.c);
        return false;
    }

    public int d() {
        return this.g;
    }

    public String c() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public String e() {
        return this.c;
    }

    public Double a() {
        return this.b;
    }
}
