package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import defpackage.kuk;
import health.compact.a.CommonUtil;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class kuf {
    private static final Object b = new Object();
    private static volatile kuf d;

    /* renamed from: a, reason: collision with root package name */
    private kuk f14599a;

    public int c(int i) {
        if (i == 2) {
            return 1;
        }
        return i;
    }

    public static kuf a() {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new kuf();
                }
            }
        }
        return d;
    }

    private kuf() {
        if (this.f14599a == null) {
            try {
                this.f14599a = (kuk) ixu.d(BaseApplication.e().getAssets().open("dict_config.json"), kuk.class);
            } catch (IOException e) {
                LogUtil.e("DictConfigManager", e.toString());
            }
        }
    }

    public kuk.b e(int i) {
        kuk kukVar = this.f14599a;
        if (kukVar == null) {
            return null;
        }
        for (kuk.b bVar : kukVar.c()) {
            if (bVar.h() == i) {
                return bVar;
            }
        }
        return null;
    }

    public int[] c(int i, String str) {
        ArrayList arrayList = new ArrayList();
        if (this.f14599a == null) {
            return CommonUtil.b(arrayList);
        }
        kuk.b e = e(i);
        if (e == null) {
            int d2 = HiHealthDictManager.d(BaseApplication.e()).d(i, str, 0);
            HiHealthDictField d3 = HiHealthDictManager.d(BaseApplication.e()).d(i, str);
            int c = d3 != null ? d3.c() : 0;
            if (c > 0) {
                arrayList.add(Integer.valueOf(c));
            } else if (d2 > 0) {
                arrayList.add(Integer.valueOf(d2));
            } else {
                arrayList.add(Integer.valueOf(i));
            }
        } else if (e.b(str) <= 0) {
            Iterator<kuk.b.d> it = e.a().iterator();
            while (it.hasNext()) {
                List<kuk.b.d.e> f = it.next().f();
                if (!CollectionUtils.d(f)) {
                    for (kuk.b.d.e eVar : f) {
                        if (str.equals(eVar.c())) {
                            arrayList.add(Integer.valueOf(eVar.a()));
                        }
                    }
                }
            }
        } else {
            arrayList.add(Integer.valueOf(e.b(str)));
        }
        return CommonUtil.b(arrayList);
    }

    public int c(kum kumVar, String str, int i) {
        int a2 = kumVar.e().a();
        int d2 = kumVar.a().d();
        kuk.b e = e(a2);
        if (e != null && d2 >= 3 && kumVar.d() == null) {
            for (kuk.b.d dVar : e.a()) {
                if (str.equals(dVar.d())) {
                    List<kuk.b.d.e> f = dVar.f();
                    if (CollectionUtils.d(f)) {
                        continue;
                    } else {
                        for (kuk.b.d.e eVar : f) {
                            if (!TextUtils.isEmpty(eVar.e()) && kuh.c(eVar.e()) == i) {
                                return eVar.a();
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public String d(int i) {
        if (this.f14599a == null) {
            return "";
        }
        kuk.b e = e(i);
        if (e != null) {
            return e.b();
        }
        HiHealthDictType d2 = HiHealthDictManager.d((Context) null).d(i);
        return d2 == null ? "" : d2.h();
    }

    public int[] d(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        if (this.f14599a == null) {
            return CommonUtil.b(arrayList);
        }
        for (int i : iArr) {
            kuk.b e = e(i);
            if (e == null) {
                arrayList.add(Integer.valueOf(i));
            } else {
                arrayList.addAll(e.e());
            }
        }
        return CommonUtil.b(arrayList);
    }

    public int[] c(int i, Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        if (this.f14599a == null) {
            return CommonUtil.b(arrayList);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Set hashSet = new HashSet();
            if (entry.getValue() instanceof Map) {
                hashSet = ((Map) entry.getValue()).keySet();
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                arrayList.addAll(e(i, (String) it.next()));
            }
        }
        return CommonUtil.b(arrayList);
    }

    private List<Integer> e(int i, String str) {
        ArrayList arrayList = new ArrayList();
        if (this.f14599a == null) {
            return arrayList;
        }
        kuk.b e = e(i);
        if (e == null) {
            int d2 = HiHealthDictManager.d(BaseApplication.e()).d(i, str, 0);
            if (d2 <= 0) {
                arrayList.add(Integer.valueOf(i));
            } else {
                arrayList.add(Integer.valueOf(d2));
            }
        } else if (e.b(str) <= 0) {
            Iterator<kuk.b.d> it = e.a().iterator();
            while (it.hasNext()) {
                List<kuk.b.d.e> f = it.next().f();
                if (!CollectionUtils.d(f)) {
                    for (kuk.b.d.e eVar : f) {
                        if (str.equals(eVar.c())) {
                            arrayList.add(Integer.valueOf(eVar.a()));
                        }
                    }
                }
            }
        } else {
            arrayList.add(Integer.valueOf(e.b(str)));
        }
        return arrayList;
    }

    public List<JsonObject> e(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        if (this.f14599a == null) {
            return arrayList;
        }
        kuk.b e = e(i);
        if (e == null) {
            HiHealthDictField b2 = HiHealthDictManager.d(BaseApplication.e()).b(i2);
            if (b2 != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(kuh.f14600a, b2.a());
                jsonObject.addProperty(kuh.d, Integer.valueOf(i));
                arrayList.add(jsonObject);
            } else {
                List<HiHealthDictField> j = HiHealthDictManager.d(BaseApplication.e()).j(i2);
                if (CollectionUtils.d(j)) {
                    JsonObject jsonObject2 = new JsonObject();
                    jsonObject2.addProperty(kuh.f14600a, "");
                    jsonObject2.addProperty(kuh.d, Integer.valueOf(i));
                    arrayList.add(jsonObject2);
                } else {
                    for (HiHealthDictField hiHealthDictField : j) {
                        JsonObject jsonObject3 = new JsonObject();
                        jsonObject3.addProperty(kuh.f14600a, hiHealthDictField.a());
                        jsonObject3.addProperty(kuh.d, Integer.valueOf(i));
                        arrayList.add(jsonObject3);
                    }
                }
            }
            return arrayList;
        }
        for (kuk.b.d dVar : e.a()) {
            if (dVar.e() == i2) {
                JsonObject jsonObject4 = new JsonObject();
                jsonObject4.addProperty(kuh.f14600a, dVar.d());
                jsonObject4.addProperty(kuh.b, Boolean.valueOf(dVar.h()));
                jsonObject4.addProperty(kuh.d, Integer.valueOf(i));
                arrayList.add(jsonObject4);
            } else if (dVar.a() == i2) {
                JsonObject jsonObject5 = new JsonObject();
                jsonObject5.addProperty(kuh.f14600a, dVar.d());
                jsonObject5.addProperty(kuh.b, Boolean.valueOf(dVar.h()));
                jsonObject5.addProperty(kuh.d, Integer.valueOf(i));
                if (!TextUtils.isEmpty(dVar.b())) {
                    jsonObject5.addProperty(kuh.e, dVar.b());
                }
                arrayList.add(jsonObject5);
            }
        }
        return arrayList;
    }

    public int b(int i, String str) {
        if (this.f14599a == null) {
            return -1;
        }
        kuk.b e = e(i);
        if (e == null) {
            return HiHealthDictManager.d(BaseApplication.e()).d(i, str, 0);
        }
        Iterator<kuk.b.d> it = e.a().iterator();
        while (it.hasNext()) {
            List<kuk.b.d.e> f = it.next().f();
            if (!CollectionUtils.d(f)) {
                for (kuk.b.d.e eVar : f) {
                    if (str.equals(eVar.c())) {
                        return eVar.a();
                    }
                }
            }
        }
        return -1;
    }

    public Map<Integer, Integer> c(int[] iArr) {
        HashMap hashMap = new HashMap();
        if (this.f14599a == null) {
            return hashMap;
        }
        for (int i : iArr) {
            kuk.b e = e(i);
            if (e == null) {
                if (!HiHealthDictManager.d((Context) null).l(i)) {
                    hashMap.put(Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    Iterator<Integer> it = e(new int[]{i}).iterator();
                    while (it.hasNext()) {
                        hashMap.put(it.next(), Integer.valueOf(i));
                    }
                }
            } else {
                Iterator<Integer> it2 = e.e().iterator();
                while (it2.hasNext()) {
                    hashMap.put(it2.next(), Integer.valueOf(i));
                }
            }
        }
        return hashMap;
    }

    public Map<Integer, Integer> b(int[] iArr) {
        HashMap hashMap = new HashMap();
        if (this.f14599a == null) {
            return hashMap;
        }
        for (int i : iArr) {
            kuk.b e = e(i);
            if (e == null) {
                if (!HiHealthDictManager.d((Context) null).l(i)) {
                    hashMap.put(Integer.valueOf(i), Integer.valueOf(i));
                } else {
                    Iterator<Integer> it = e(new int[]{i}).iterator();
                    while (it.hasNext()) {
                        hashMap.put(it.next(), Integer.valueOf(i));
                    }
                }
            } else {
                Iterator<Integer> it2 = e.d().iterator();
                while (it2.hasNext()) {
                    hashMap.put(it2.next(), Integer.valueOf(i));
                }
            }
        }
        return hashMap;
    }

    public String a(int[] iArr) {
        kuk kukVar = this.f14599a;
        if (kukVar == null || iArr == null || iArr.length == 0) {
            return null;
        }
        for (kuk.d dVar : kukVar.e()) {
            for (int i : iArr) {
                if (dVar.b() == i) {
                    return dVar.c();
                }
            }
        }
        return null;
    }

    public List<Integer> e(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        if (this.f14599a == null) {
            return arrayList;
        }
        for (int i : iArr) {
            if (a(i)) {
                arrayList.add(Integer.valueOf(i));
            } else {
                kuk.b e = e(i);
                if (e == null) {
                    arrayList.addAll(HiHealthDictManager.d((Context) null).g(i));
                } else {
                    arrayList.addAll(e.d());
                }
            }
        }
        return arrayList;
    }

    public boolean a(int i) {
        return HiHealthDataType.Category.SEQUENCE == HiHealthDataType.e(i);
    }

    public List<Integer> c(List<kun> list) {
        ArrayList arrayList = new ArrayList();
        for (kun kunVar : list) {
            kuk.b e = e(kunVar.a());
            if (e == null) {
                arrayList.add(Integer.valueOf(kunVar.a()));
            } else if (e.c() == 0) {
                arrayList.add(Integer.valueOf(kunVar.a()));
            } else {
                arrayList.add(Integer.valueOf(e.c()));
            }
        }
        return arrayList;
    }
}
