package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HealthDataMergePolicy;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictMergeMode;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.iis;
import defpackage.ijf;
import defpackage.ijj;
import defpackage.ivu;
import defpackage.ivx;
import defpackage.iwc;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiDicHealthDataMerge {
    private static Context c;

    /* renamed from: a, reason: collision with root package name */
    private ijj f4204a;
    private String b;
    private String f;
    private int j;
    private static HashMap<Integer, Integer> e = new HashMap<>();
    private static HashMap<String, Integer> d = new HashMap<>();

    public HiDicHealthDataMerge(Context context) {
        b(context.getApplicationContext());
    }

    private static void b(Context context) {
        c = context;
    }

    public boolean a(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("Debug_HiDicHealthDataMerge", "healthData is null");
            return false;
        }
        HiHealthDictField b = HiHealthDictManager.d(c).b(hiHealthData.getType());
        if (b == null) {
            ReleaseLogUtil.d("Debug_HiDicHealthDataMerge", "HiHealthDictField is null, type is ", Integer.valueOf(hiHealthData.getType()));
            return false;
        }
        this.j = hiHealthData.getType();
        HiHealthDictMergeMode b2 = b.b();
        if (b2 == null) {
            ReleaseLogUtil.d("Debug_HiDicHealthDataMerge", "mergeMode is null, type is ", Integer.valueOf(this.j));
            return false;
        }
        String c2 = b2.c();
        this.b = c2;
        if (HealthDataMergePolicy.SOURCE_PRIORITY.equals(c2)) {
            int[] d2 = b2.d();
            for (int i = 0; i < d2.length; i++) {
                e.put(Integer.valueOf(d2[i]), Integer.valueOf(d2.length - i));
            }
            this.f = b2.e();
        }
        if (HealthDataMergePolicy.CATEGORY_PRIORITY.equals(this.b)) {
            String[] a2 = b2.a();
            for (int i2 = 0; i2 < a2.length; i2++) {
                d.put(a2[i2], Integer.valueOf(a2.length - i2));
            }
            this.f = b2.e();
        }
        this.f4204a = ivu.d(c, hiHealthData.getType());
        return true;
    }

    public boolean a(final HiHealthData hiHealthData, int i, List<Integer> list) {
        long j;
        List<HiHealthData> list2;
        List<HiHealthData> list3;
        if (hiHealthData == null || HiCommonUtil.d(list)) {
            ReleaseLogUtil.d("Debug_HiDicHealthDataMerge", "healthData is null or clients is null empty");
            return false;
        }
        List<HiHealthData> a2 = this.f4204a.a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), this.j, list);
        boolean z = hiHealthData.getModifiedTime() == 0;
        if (z) {
            j = iwc.b(a2, hiHealthData);
            hiHealthData.setModifiedTime(j);
        } else {
            j = 0;
        }
        if (HiCommonUtil.d(a2)) {
            if (hiHealthData.getSyncStatus() == 2) {
                return true;
            }
            long e2 = this.f4204a.e(hiHealthData, i, 0);
            if (hiHealthData.getSyncStatus() != 1) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: isk
                    @Override // java.lang.Runnable
                    public final void run() {
                        irs.c().process(HiHealthData.this);
                    }
                });
            }
            return e2 > 0;
        }
        Iterator<HiHealthData> it = a2.iterator();
        while (true) {
            if (it.hasNext()) {
                HiHealthData next = it.next();
                if (next.getClientId() == i) {
                    if (c(hiHealthData)) {
                        list3 = a2;
                    } else {
                        list3 = a2;
                        if (Double.compare(hiHealthData.getValue(), next.getValue()) == 0 && b(next, hiHealthData) && next.getInt("merged") == 0) {
                            ReleaseLogUtil.e("Debug_HiDicHealthDataMerge", "merge time is same and value is same, type = ", Integer.valueOf(this.j));
                            return true;
                        }
                    }
                    if (!z && next.getModifiedTime() >= hiHealthData.getModifiedTime()) {
                        ReleaseLogUtil.e("Debug_HiDicHealthDataMerge", "getModifiedTime is small, type = ", Integer.valueOf(this.j), " time = ", Long.valueOf(hiHealthData.getModifiedTime()));
                        return true;
                    }
                    if (!b(next, hiHealthData)) {
                        next.setMetaData(hiHealthData.getMetaData());
                    }
                    next.setValue(hiHealthData.getValue());
                    if (hiHealthData.getSyncStatus() != 1 || next.getInt("merged") != 2) {
                        next.putInt("merged", 0);
                    }
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                    if (z) {
                        next.setModifiedTime(j);
                    } else {
                        next.setModifiedTime(hiHealthData.getModifiedTime());
                    }
                    list2 = list3;
                }
            } else {
                hiHealthData.setClientId(i);
                hiHealthData.putInt("merged", 0);
                list2 = a2;
                list2.add(hiHealthData);
                break;
            }
        }
        if (HealthDataMergePolicy.SOURCE_PRIORITY.equals(this.b) || HealthDataMergePolicy.CATEGORY_PRIORITY.equals(this.b)) {
            for (HiHealthData hiHealthData2 : list2) {
                HiDeviceInfo a3 = ijf.d(c).a(iis.d().a(hiHealthData2.getClientId()));
                if (a3 == null) {
                    ReleaseLogUtil.d("Debug_HiDicHealthDataMerge", "deviceInfo is null");
                    return false;
                }
                hiHealthData2.setDeviceUuid(a3.getDeviceUniqueCode());
            }
        }
        return d(list2);
    }

    private boolean b(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        if (hiHealthData.getMetaData() == null && hiHealthData2.getMetaData() == null) {
            return true;
        }
        return (hiHealthData.getMetaData() == null || hiHealthData2.getMetaData() == null || !hiHealthData.getMetaData().equals(hiHealthData2.getMetaData())) ? false : true;
    }

    private boolean c(HiHealthData hiHealthData) {
        return HiHealthDictManager.d(c).g(DicDataTypeUtil.DataType.FASTING_LITE_PHASE_SET.value()).contains(Integer.valueOf(hiHealthData.getType())) || HiHealthDictManager.d(c).g(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()).contains(Integer.valueOf(hiHealthData.getType())) || HiHealthDictManager.d(c).g(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE.value()).contains(Integer.valueOf(hiHealthData.getType()));
    }

    private boolean d(List<HiHealthData> list) {
        c(list);
        HiHealthData hiHealthData = list.get(0);
        boolean a2 = this.f4204a.a(hiHealthData, hiHealthData.getClientId(), 0);
        boolean z = true;
        for (int i = 1; i < list.size(); i++) {
            HiHealthData hiHealthData2 = list.get(i);
            if (hiHealthData2.getInt("merged") == 0) {
                boolean a3 = this.f4204a.a(hiHealthData2, hiHealthData2.getClientId(), 1);
                LogUtil.c("Debug_HiDicHealthDataMerge", "insertOrUpdatePointData unmerge change = ", Boolean.valueOf(a3));
                if (!a3) {
                    z = false;
                }
            }
        }
        return z && a2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c(List<HiHealthData> list) {
        char c2;
        Comparator bVar;
        String str = this.b;
        str.hashCode();
        switch (str.hashCode()) {
            case -758469691:
                if (str.equals(HealthDataMergePolicy.CATEGORY_PRIORITY)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 76100:
                if (str.equals("MAX")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 76338:
                if (str.equals("MIN")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 77184:
                if (str.equals(HealthDataMergePolicy.NEW)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 78343:
                if (str.equals(HealthDataMergePolicy.OLD)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1010216264:
                if (str.equals(HealthDataMergePolicy.SOURCE_PRIORITY)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            bVar = new ivx.b(d, this.f);
        } else if (c2 == 1) {
            bVar = new ivx.i();
        } else if (c2 == 2) {
            bVar = new ivx.j();
        } else if (c2 == 3) {
            bVar = new ivx.g();
        } else {
            if (c2 == 4) {
                return;
            }
            if (c2 == 5) {
                bVar = new ivx.c(e, this.f);
            } else {
                LogUtil.h("Debug_HiDicHealthDataMerge", "This mergePolicy is not supported! mergePolicy is ", this.b);
                return;
            }
        }
        Collections.sort(list, bVar);
    }
}
