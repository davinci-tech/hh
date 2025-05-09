package com.huawei.hihealth.dictionary;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.dictionary.config.ConfigureManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictStat;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.model.HiHealthDictionary;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class HiHealthDictManager {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f4120a = false;
    private static volatile HiHealthDictManager d;
    private static final Object e = new Object();
    private Context b;
    private AtomicInteger c = new AtomicInteger(-1);
    private Map<Integer, HiHealthDictionary> h = new ConcurrentHashMap();

    private HiHealthDictManager(Context context) {
        this.b = context;
    }

    public static HiHealthDictManager d(Context context) {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new HiHealthDictManager(context);
                }
            }
        }
        return d;
    }

    public HiHealthDictionary b() {
        return this.h.get(Integer.valueOf(this.c.get()));
    }

    public List<Integer> c() {
        HiHealthDictionary b = b();
        if (b == null) {
            return new ArrayList();
        }
        return b.a();
    }

    public List<Integer> e() {
        HiHealthDictionary b = b();
        if (b == null) {
            return new ArrayList();
        }
        return b.b();
    }

    public List<Integer> d() {
        List<Integer> c = c();
        ArrayList arrayList = new ArrayList(c.size() + 1);
        Iterator<Integer> it = c.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            HiHealthDictType d2 = d(intValue);
            if (d2 != null && d2.g() == 0 && d2.e() != 3) {
                arrayList.add(Integer.valueOf(intValue));
            }
        }
        arrayList.add(30001);
        return arrayList;
    }

    public boolean k(int i) {
        HiHealthDictField i2 = i(i);
        if (i2 == null) {
            return false;
        }
        return i2.g();
    }

    public List<String> m(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthDictField> it = j(i).iterator();
        while (it.hasNext()) {
            Iterator<HiHealthDictStat> it2 = it.next().d().iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next().c());
            }
        }
        return arrayList;
    }

    public int d(int i, String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        Iterator<HiHealthDictField> it = j(i).iterator();
        while (it.hasNext()) {
            for (HiHealthDictStat hiHealthDictStat : it.next().d()) {
                if (str.equals(hiHealthDictStat.c())) {
                    return hiHealthDictStat.d();
                }
            }
        }
        return i2;
    }

    public int c(int i, String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        for (HiHealthDictField hiHealthDictField : j(i)) {
            Iterator<HiHealthDictStat> it = hiHealthDictField.d().iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().c())) {
                    return hiHealthDictField.c();
                }
            }
        }
        return i2;
    }

    public int c(int i) {
        if (f(i) != null) {
            return 0;
        }
        return h(i) != null ? 2 : -1;
    }

    public HiHealthDictType d(int i) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return null;
        }
        return b.e(i);
    }

    public boolean l(int i) {
        return (d(i) == null && f(i) == null && h(i) == null) ? false : true;
    }

    public boolean o(int i) {
        HiHealthDictType d2 = d(i);
        if (d2 == null) {
            return false;
        }
        Iterator<HiHealthDictField> it = d2.b().iterator();
        while (it.hasNext()) {
            if (!it.next().d().isEmpty()) {
                return true;
            }
        }
        return d2.g() != 0;
    }

    public List<Integer> g(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthDictField> it = j(i).iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().c()));
        }
        return arrayList;
    }

    public List<String> e(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthDictField> it = j(i).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    public List<HiHealthDictField> j(int i) {
        ArrayList arrayList = new ArrayList();
        HiHealthDictType d2 = d(i);
        return d2 == null ? arrayList : d2.b();
    }

    public HiHealthDictField d(int i, String str) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return null;
        }
        HiHealthDictType e2 = b.e(i);
        if (e2 == null) {
            return null;
        }
        return e2.e(str);
    }

    public HiHealthDictType h(int i) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return null;
        }
        return b.b(i);
    }

    public HiHealthDictField i(int i) {
        HiHealthDictType h = h(i);
        if (h == null) {
            return null;
        }
        for (HiHealthDictField hiHealthDictField : h.b()) {
            Iterator<HiHealthDictStat> it = hiHealthDictField.d().iterator();
            while (it.hasNext()) {
                if (i == it.next().d()) {
                    return hiHealthDictField;
                }
            }
        }
        return null;
    }

    public int b(int i, String str, String str2) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return -1;
        }
        HiHealthDictType e2 = b.e(i);
        if (e2 == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictType is null, typeId is ", Integer.valueOf(i));
            return -1;
        }
        HiHealthDictField e3 = e2.e(str);
        if (e3 == null || e3.d() == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictType is null or no stat policy, typeId is ", Integer.valueOf(i), ", filed Name is ", str);
            return -1;
        }
        for (HiHealthDictStat hiHealthDictStat : e3.d()) {
            if (str2.equals(hiHealthDictStat.e())) {
                return hiHealthDictStat.d();
            }
        }
        LogUtil.c("HiH_HiHealthDictManager", "This field not contains the stat policy ", str2);
        return -1;
    }

    public int b(int i, String str, double d2) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return -1;
        }
        HiHealthDictType e2 = b.e(i);
        if (e2 == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictType is null, typeId is ", Integer.valueOf(i));
            return -1;
        }
        HiHealthDictField e3 = e2.e(str);
        if (e3 == null || e3.d() == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictType is null or no stat policy, typeId is ", Integer.valueOf(i), ", filed Name is ", str);
            return -1;
        }
        for (HiHealthDictStat hiHealthDictStat : e3.d()) {
            if (hiHealthDictStat.a() != null && Double.compare(d2, hiHealthDictStat.a().doubleValue()) == 0) {
                return hiHealthDictStat.d();
            }
        }
        LogUtil.c("HiH_HiHealthDictManager", "This field not contains the stat value ", Double.valueOf(d2));
        return -1;
    }

    public HiHealthDictType f(int i) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return null;
        }
        return b.a(i);
    }

    public HiHealthDictField b(int i) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return null;
        }
        return b.d(i);
    }

    public HiHealthDictStat a(int i) {
        HiHealthDictionary b = b();
        if (b == null) {
            LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
            return null;
        }
        return b.c(i);
    }

    public void e(boolean z) {
        synchronized (this) {
            if (!f4120a || z) {
                long currentTimeMillis = System.currentTimeMillis();
                List<HiHealthDictionary> a2 = ConfigureManager.c().a(this.b);
                if (a2.isEmpty()) {
                    LogUtil.e("HiH_HiHealthDictManager", "dictionary is not available.");
                    return;
                }
                Collections.sort(a2, new Comparator<HiHealthDictionary>() { // from class: com.huawei.hihealth.dictionary.HiHealthDictManager.1
                    @Override // java.util.Comparator
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public int compare(HiHealthDictionary hiHealthDictionary, HiHealthDictionary hiHealthDictionary2) {
                        return hiHealthDictionary2.f() - hiHealthDictionary.f();
                    }
                });
                HiHealthDictionary hiHealthDictionary = null;
                for (HiHealthDictionary hiHealthDictionary2 : a2) {
                    if (hiHealthDictionary == null) {
                        hiHealthDictionary = hiHealthDictionary2;
                    } else {
                        hiHealthDictionary.merge(hiHealthDictionary2);
                    }
                }
                int f = a2.get(0).f();
                hiHealthDictionary.f(f);
                this.h.put(Integer.valueOf(f), hiHealthDictionary);
                this.c.set(f);
                LogUtil.d("HiH_HiHealthDictManager", "finish reload dictionary time is", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                f4120a = true;
            }
        }
    }
}
