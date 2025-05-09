package com.huawei.hihealth.dictionary.model;

import android.util.ArrayMap;
import android.util.SparseArray;
import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.dictionary.expression.Expression;
import com.huawei.hihealth.dictionary.expression.Pattern;
import com.huawei.hihealth.dictionary.utils.DictUtils;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HiHealthDictType {

    @SerializedName("category")
    private int c;

    @SerializedName("commonStatTypes")
    private int[] e;

    @SerializedName("mergePolicy")
    private String f;

    @SerializedName("subType")
    private int g;

    @SerializedName("sensitivityLevel")
    private int h;

    @SerializedName("mergeMode")
    private HiHealthDictMergeMode i;

    @SerializedName("name")
    private String j;

    @SerializedName("typeId")
    private int l;

    @SerializedName("validatePolicies")
    private List<String> m;
    private transient List<Expression> n;

    @SerializedName("type")
    private int o;

    @SerializedName("fields")
    private List<HiHealthDictField> b = new ArrayList();
    private transient Map<String, HiHealthDictField> d = new ArrayMap();

    /* renamed from: a, reason: collision with root package name */
    private transient SparseArray<HiHealthDictField> f4129a = new SparseArray<>();

    public HiHealthDictField e(String str) {
        return this.d.get(str);
    }

    public void c() {
        LogUtil.b("HiH_HiHealthDictType", "start to generate field map.");
        for (HiHealthDictField hiHealthDictField : this.b) {
            this.d.put(hiHealthDictField.a(), hiHealthDictField);
            this.f4129a.put(hiHealthDictField.c(), hiHealthDictField);
        }
    }

    public void d() {
        List<String> list = this.m;
        if (list == null || list.isEmpty()) {
            LogUtil.c("HiH_HiHealthDictType", "mValidatePolicies is null or empty.");
            return;
        }
        this.n = new ArrayList();
        Iterator<String> it = this.m.iterator();
        while (it.hasNext()) {
            this.n.add(Pattern.d(it.next()));
        }
    }

    public boolean c(Map<String, Double> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.c("HiH_HiHealthDictType", "paramsMap is null or empty.");
            return false;
        }
        List<Expression> list = this.n;
        if (list == null || list.isEmpty()) {
            LogUtil.b("HiH_HiHealthDictType", "mValidateExpressions is null or empty.");
            return true;
        }
        for (Expression expression : this.n) {
            if (map.keySet().containsAll(expression.b()) && !expression.a(map)) {
                LogUtil.d("HiH_HiHealthDictType", "expression = ", expression.b());
                return false;
            }
        }
        return true;
    }

    public boolean o() {
        if (!DictUtils.a(this.j)) {
            LogUtil.e("HiH_HiHealthDictType", "name is illegal: ", this.j);
            return false;
        }
        if (!HealthDataCategory.validate(Integer.valueOf(this.c))) {
            LogUtil.e("HiH_HiHealthDictType", "category is illegal: ", Integer.valueOf(this.c));
            return false;
        }
        HiHealthDictMergeMode hiHealthDictMergeMode = this.i;
        if (hiHealthDictMergeMode != null && (!HealthDataMergePolicy.validate(hiHealthDictMergeMode.c()) || !HealthDataMergePolicy.validate(this.i.e()))) {
            LogUtil.e("HiH_HiHealthDictType", "mergePolicy is illegal: ", this.f);
            return false;
        }
        if (!SensitivityLevel.validate(Integer.valueOf(this.h))) {
            LogUtil.e("HiH_HiHealthDictType", "sensitivityLevel is illegal: ", Integer.valueOf(this.h));
            return false;
        }
        if (!HealthDataType.validate(Integer.valueOf(this.o))) {
            LogUtil.e("HiH_HiHealthDictType", "type is illegal: ", Integer.valueOf(this.o));
            return false;
        }
        for (HiHealthDictField hiHealthDictField : this.b) {
            if (!hiHealthDictField.f()) {
                LogUtil.e("HiH_HiHealthDictType", "field data is illegal: ", hiHealthDictField.a());
                return false;
            }
        }
        return true;
    }

    public int i() {
        return this.l;
    }

    public int g() {
        return this.g;
    }

    public String h() {
        return this.j;
    }

    public int e() {
        return this.c;
    }

    public int j() {
        return this.h;
    }

    public int[] a() {
        return (int[]) this.e.clone();
    }

    public HiHealthDictMergeMode f() {
        return this.i;
    }

    public List<HiHealthDictField> b() {
        return this.b;
    }
}
