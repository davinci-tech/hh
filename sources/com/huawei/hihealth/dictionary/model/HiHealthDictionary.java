package com.huawei.hihealth.dictionary.model;

import com.google.gson.annotations.SerializedName;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HiHealthDictionary implements Mergable<HiHealthDictionary> {

    @SerializedName("dictTypes")
    private List<HiHealthDictType> c;

    @SerializedName("version")
    private int h = -1;
    private transient Map<Integer, HiHealthDictType> e = new HashMap();
    private transient Map<Integer, HiHealthDictStat> d = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private transient Map<Integer, HiHealthDictField> f4130a = new HashMap();
    private transient Map<Integer, HiHealthDictType> f = new HashMap();
    private transient Map<Integer, HiHealthDictType> b = new HashMap();

    @Override // com.huawei.hihealth.dictionary.model.Mergable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void merge(HiHealthDictionary hiHealthDictionary) {
        if (this.h > hiHealthDictionary.f()) {
            LogUtil.e("HiH_HiHealthDictionary", "merge failed. src version:", Integer.valueOf(this.h), " dst version:", Integer.valueOf(hiHealthDictionary.f()));
        } else {
            this.e.putAll(hiHealthDictionary.g());
        }
    }

    public List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.e.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public List<Integer> b() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Integer, HiHealthDictType>> it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            int g = it.next().getValue().g();
            if (g != 0) {
                arrayList.add(Integer.valueOf(g));
            }
        }
        return arrayList;
    }

    public HiHealthDictType e(int i) {
        return this.e.get(Integer.valueOf(i));
    }

    public HiHealthDictStat c(int i) {
        return this.d.get(Integer.valueOf(i));
    }

    public HiHealthDictField d(int i) {
        return this.f4130a.get(Integer.valueOf(i));
    }

    public HiHealthDictType b(int i) {
        return this.f.get(Integer.valueOf(i));
    }

    public HiHealthDictType a(int i) {
        return this.b.get(Integer.valueOf(i));
    }

    public void e() {
        LogUtil.b("HiH_HiHealthDictionary", "start to generate validateExpressions.");
        List<HiHealthDictType> list = this.c;
        if (list == null || list.isEmpty()) {
            LogUtil.e("HiH_HiHealthDictionary", "There is no dictTypes.");
            return;
        }
        Iterator<HiHealthDictType> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
    }

    public void d() {
        LogUtil.b("HiH_HiHealthDictionary", "start to generate dict ids.");
        List<HiHealthDictType> list = this.c;
        if (list == null || list.isEmpty()) {
            LogUtil.e("HiH_HiHealthDictionary", "There is no dictTypes.");
            return;
        }
        for (HiHealthDictType hiHealthDictType : this.c) {
            Iterator<HiHealthDictField> it = hiHealthDictType.b().iterator();
            while (it.hasNext()) {
                it.next().b(hiHealthDictType.i());
            }
        }
    }

    public void c() {
        LogUtil.b("HiH_HiHealthDictionary", "start to generate cache maps.");
        for (HiHealthDictType hiHealthDictType : this.c) {
            if (this.e.containsKey(Integer.valueOf(hiHealthDictType.i()))) {
                LogUtil.e("HiH_HiHealthDictionary", "Duplicated typeId for dictType:", hiHealthDictType.h());
                throw new IllegalArgumentException("Duplicated typeId for dictType:" + hiHealthDictType.h());
            }
            this.e.put(Integer.valueOf(hiHealthDictType.i()), hiHealthDictType);
            for (HiHealthDictField hiHealthDictField : hiHealthDictType.b()) {
                if (g(hiHealthDictField.c())) {
                    LogUtil.e("HiH_HiHealthDictionary", "Duplicated typeId for field:", hiHealthDictField.a());
                    throw new IllegalArgumentException("Duplicated typeId for field:" + hiHealthDictField.a());
                }
                this.f4130a.put(Integer.valueOf(hiHealthDictField.c()), hiHealthDictField);
                this.b.put(Integer.valueOf(hiHealthDictField.c()), hiHealthDictType);
                if (hiHealthDictField.d() != null) {
                    for (HiHealthDictStat hiHealthDictStat : hiHealthDictField.d()) {
                        if (g(hiHealthDictStat.d())) {
                            LogUtil.e("HiH_HiHealthDictionary", "Duplicated typeId for stat:", hiHealthDictStat.c());
                            throw new IllegalArgumentException("Duplicated typeId for stat:" + hiHealthDictStat.c());
                        }
                        this.d.put(Integer.valueOf(hiHealthDictStat.d()), hiHealthDictStat);
                        this.f.put(Integer.valueOf(hiHealthDictStat.d()), hiHealthDictType);
                    }
                }
            }
            hiHealthDictType.c();
        }
    }

    public boolean h() {
        int i = this.h;
        if (i < 0) {
            LogUtil.e("HiH_HiHealthDictionary", "dict version error:", Integer.valueOf(i));
            return false;
        }
        List<HiHealthDictType> list = this.c;
        if (list == null || list.isEmpty()) {
            LogUtil.e("HiH_HiHealthDictionary", "dictTypes is null or empty.");
            return false;
        }
        Iterator<HiHealthDictType> it = this.c.iterator();
        while (it.hasNext()) {
            if (!it.next().o()) {
                LogUtil.e("HiH_HiHealthDictionary", "dictType data is illegal.");
                return false;
            }
        }
        return true;
    }

    public int f() {
        return this.h;
    }

    public void f(int i) {
        this.h = i;
    }

    private Map<Integer, HiHealthDictType> g() {
        return this.e;
    }

    private boolean g(int i) {
        return this.f4130a.containsKey(Integer.valueOf(i)) || this.d.containsKey(Integer.valueOf(i));
    }
}
