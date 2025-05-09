package com.huawei.hihealth.dictionary.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.dictionary.utils.DictUtils;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class HiHealthDictField {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("optional")
    private boolean f4126a;

    @SerializedName("format")
    private String b;

    @SerializedName("mergeMode")
    private HiHealthDictMergeMode c;

    @SerializedName("mergePolicy")
    private String e;

    @SerializedName("unit")
    private String h;

    @SerializedName("name")
    private String i;

    @SerializedName(CommonUtil.PARAM_HEALTH_TYPE)
    private int j;

    @SerializedName("isOverSea")
    private boolean d = true;

    @SerializedName("statPolicies")
    private List<HiHealthDictStat> g = new ArrayList();

    public void b(int i) {
        a(i);
        e(i);
    }

    private void a(int i) {
        if (this.j == 0) {
            this.j = DictUtils.b(i, this.i);
        }
    }

    public void e(int i) {
        List<HiHealthDictStat> list = this.g;
        if (list == null) {
            LogUtil.b("HiH_HiHealthDictField", "statPolicies is null, no need to genarate type id.");
            return;
        }
        Iterator<HiHealthDictStat> it = list.iterator();
        while (it.hasNext()) {
            it.next().a(i);
        }
    }

    public boolean f() {
        if (!DictUtils.a(this.i)) {
            LogUtil.e("HiH_HiHealthDictField", "name is illegal: ", this.i);
            return false;
        }
        HiHealthDictMergeMode hiHealthDictMergeMode = this.c;
        if (hiHealthDictMergeMode != null && (!HealthDataMergePolicy.validate(hiHealthDictMergeMode.c()) || !HealthDataMergePolicy.validate(this.c.e()))) {
            LogUtil.e("HiH_HiHealthDictField", "mergePolicy is illegal: ", this.e);
            return false;
        }
        List<HiHealthDictStat> list = this.g;
        if (list == null) {
            return true;
        }
        for (HiHealthDictStat hiHealthDictStat : list) {
            if (!hiHealthDictStat.g()) {
                LogUtil.e("HiH_HiHealthDictField", "statPolicy is illegal: ", hiHealthDictStat.c());
                return false;
            }
        }
        return true;
    }

    public int c() {
        return this.j;
    }

    public String a() {
        return this.i;
    }

    public String e() {
        return this.b;
    }

    public HiHealthDictMergeMode b() {
        return this.c;
    }

    public boolean g() {
        return this.d;
    }

    public List<HiHealthDictStat> d() {
        return this.g;
    }
}
