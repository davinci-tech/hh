package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import android.util.LruCache;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.iic;
import defpackage.iis;
import defpackage.ijf;
import defpackage.iuz;
import defpackage.ivu;
import defpackage.ivx;
import defpackage.iwc;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiHealthDataPointMerge implements HiMergeCommon {

    /* renamed from: a, reason: collision with root package name */
    private static LruCache<Integer, String> f4206a;
    private static Context b;

    private boolean b(int i) {
        return i == 2107;
    }

    public HiHealthDataPointMerge(Context context) {
        b = context.getApplicationContext();
        f4206a = new LruCache<>(16);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        long j;
        boolean z;
        int type = hiHealthData.getType();
        List<HiHealthData> a2 = ivu.d(b, type).a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), type, list);
        boolean z2 = hiHealthData.getModifiedTime() == 0;
        if (z2) {
            j = iwc.b(a2, hiHealthData);
            hiHealthData.setModifiedTime(j);
        } else {
            j = 0;
        }
        if (HiCommonUtil.d(a2)) {
            return hiHealthData.getSyncStatus() == 2 || ivu.d(b, type).e(hiHealthData, i, 0) > 0;
        }
        Iterator<HiHealthData> it = a2.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            HiHealthData next = it.next();
            if (next.getClientId() == i) {
                if (c(hiHealthData, next)) {
                    ReleaseLogUtil.e("Debug_HiHealthDataPointMerge", "merge time is same and value is same, type=", Integer.valueOf(type));
                    return true;
                }
                if (!z2 && next.getModifiedTime() >= hiHealthData.getModifiedTime()) {
                    ReleaseLogUtil.e("Debug_HiHealthDataPointMerge", "getModifiedTime is small, type = ", Integer.valueOf(type), " time = ", Long.valueOf(hiHealthData.getModifiedTime()));
                    return true;
                }
                if (b(type)) {
                    next.setMetaData(hiHealthData.getMetaData());
                }
                if (a(type)) {
                    try {
                        HiBloodSugarMetaData hiBloodSugarMetaData = (HiBloodSugarMetaData) new Gson().fromJson(hiHealthData.getMetaData(), HiBloodSugarMetaData.class);
                        if (hiBloodSugarMetaData != null && hiBloodSugarMetaData.getConfirmed()) {
                            next.setMetaData(hiHealthData.getMetaData());
                        }
                    } catch (JsonSyntaxException e) {
                        ReleaseLogUtil.d("HiH_HiHealthDataPointMerge", "JsonSyntaxException ", e.getMessage());
                        return false;
                    }
                }
                next.setValue(hiHealthData.getValue());
                if (hiHealthData.getSyncStatus() != 1 || next.getInt("merged") != 2) {
                    next.putInt("merged", 0);
                }
                if (z2) {
                    next.setModifiedTime(j);
                } else {
                    next.setModifiedTime(hiHealthData.getModifiedTime());
                }
                next.setSyncStatus(hiHealthData.getSyncStatus());
                z = false;
            }
        }
        if (z && hiHealthData.getSyncStatus() != 2) {
            hiHealthData.setClientId(i);
            hiHealthData.putInt("merged", 0);
            a2.add(hiHealthData);
        }
        if (type == 2002 || type == 2018 || type == 2105) {
            for (HiHealthData hiHealthData2 : a2) {
                String str = f4206a.get(Integer.valueOf(hiHealthData2.getClientId()));
                if (str == null) {
                    HiDeviceInfo a3 = ijf.d(b).a(iis.d().a(hiHealthData2.getClientId()));
                    if (a3 == null) {
                        ReleaseLogUtil.d("Debug_HiHealthDataPointMerge", "deviceInfo is null");
                        return false;
                    }
                    str = a3.getDeviceUniqueCode();
                    f4206a.put(Integer.valueOf(hiHealthData2.getClientId()), str);
                }
                hiHealthData2.setDeviceUuid(str);
            }
        }
        e(a2);
        boolean d = d(a2.get(0), hiHealthData, z, 0);
        for (int i2 = 1; i2 < a2.size(); i2++) {
            HiHealthData hiHealthData3 = a2.get(i2);
            if (hiHealthData3.getInt("merged") == 0) {
                boolean d2 = d(hiHealthData3, hiHealthData, z, 1);
                LogUtil.c("Debug_HiHealthDataPointMerge", "pointDataMerge() insertOrUpdatePointData unmerge change = ", Boolean.valueOf(d2));
                if (!d2) {
                    d = false;
                }
            }
        }
        return d;
    }

    private boolean a(int i) {
        for (int i2 : HiHealthDataType.d(10001)) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean c(java.util.List<com.huawei.hihealth.HiHealthData> r3, long r4, long r6, java.util.List<java.lang.Integer> r8) {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r1 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            boolean r1 = defpackage.ivu.i(r1, r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            if (r1 != 0) goto L10
            android.content.Context r1 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            boolean r1 = defpackage.ivu.e(r1, r0)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L29
            goto L11
        L10:
            r1 = r0
        L11:
            boolean r3 = r2.e(r3, r4, r6, r8)     // Catch: java.lang.Exception -> L24 java.lang.Throwable -> L46
            if (r1 == 0) goto L1c
            android.content.Context r4 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b     // Catch: java.lang.Exception -> L24 java.lang.Throwable -> L46
            defpackage.ivu.j(r4, r0)     // Catch: java.lang.Exception -> L24 java.lang.Throwable -> L46
        L1c:
            if (r1 == 0) goto L23
            android.content.Context r4 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b
            defpackage.ivu.c(r4, r0)
        L23:
            return r3
        L24:
            r3 = move-exception
            goto L2b
        L26:
            r3 = move-exception
            r1 = r0
            goto L47
        L29:
            r3 = move-exception
            r1 = r0
        L2b:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L46
            java.lang.String r5 = "merges: "
            r4[r0] = r5     // Catch: java.lang.Throwable -> L46
            java.lang.String r3 = health.compact.a.LogAnonymous.b(r3)     // Catch: java.lang.Throwable -> L46
            r5 = 1
            r4[r5] = r3     // Catch: java.lang.Throwable -> L46
            java.lang.String r3 = "HiH_HiHealthDataPointMerge"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r3, r4)     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L45
            android.content.Context r3 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b
            defpackage.ivu.c(r3, r0)
        L45:
            return r0
        L46:
            r3 = move-exception
        L47:
            if (r1 == 0) goto L4e
            android.content.Context r4 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b
            defpackage.ivu.c(r4, r0)
        L4e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.c(java.util.List, long, long, java.util.List):boolean");
    }

    private boolean e(List<HiHealthData> list, long j, long j2, List<Integer> list2) {
        List<HiHealthData> c = ivu.d(b, list.get(0).getType()).c(j, j2, list.get(0).getType(), list2);
        if (CollectionUtils.d(c)) {
            return c(list);
        }
        ArrayList arrayList = new ArrayList(10);
        boolean z = true;
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            iuz.c(i, false);
            arrayList.clear();
            int clientId = hiHealthData.getClientId();
            boolean a2 = a(c, hiHealthData, clientId, arrayList);
            if (a2) {
                hiHealthData.setClientId(clientId);
                hiHealthData.putInt("merged", 0);
                arrayList.add(hiHealthData);
                c.add(hiHealthData);
            }
            e(arrayList);
            HiHealthData hiHealthData2 = arrayList.get(0);
            z = b(!d(hiHealthData2, hiHealthData, a2, 0) ? false : z, arrayList, hiHealthData, a2, hiHealthData2);
        }
        return z;
    }

    private static boolean a(List<HiHealthData> list, HiHealthData hiHealthData, int i, List<HiHealthData> list2) {
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2.getStartTime() == hiHealthData.getStartTime() && hiHealthData2.getEndTime() == hiHealthData.getEndTime() && hiHealthData.getType() == hiHealthData2.getType()) {
                if (hiHealthData2.getClientId() != i) {
                    list2.add(hiHealthData2);
                } else {
                    hiHealthData2.setValue(hiHealthData.getValue());
                    hiHealthData2.putInt("merged", 0);
                    hiHealthData2.setSyncStatus(hiHealthData.getSyncStatus());
                    list2.add(hiHealthData2);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean c(List<HiHealthData> list) {
        boolean z = true;
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (ivu.d(b, hiHealthData.getType()).e(hiHealthData, hiHealthData.getClientId(), 0) <= 0) {
                z = false;
            }
            iuz.c(i, false);
        }
        return z;
    }

    private boolean b(boolean z, List<HiHealthData> list, HiHealthData hiHealthData, boolean z2, HiHealthData hiHealthData2) {
        hiHealthData2.putInt("merged", 0);
        for (int i = 1; i < list.size(); i++) {
            HiHealthData hiHealthData3 = list.get(i);
            if (hiHealthData3.getInt("merged") == 0) {
                boolean d = d(hiHealthData3, hiHealthData, z2, 1);
                hiHealthData3.putInt("merged", 1);
                LogUtil.c("Debug_HiHealthDataPointMerge", "pointDataMerge() insertOrUpdatePointData unmerge change = ", Boolean.valueOf(d));
                if (!d) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0043 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0045 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(com.huawei.hihealth.HiHealthData r2, com.huawei.hihealth.HiHealthData r3, boolean r4, int r5) {
        /*
            r1 = this;
            int r0 = r2.getClientId()
            int r3 = r3.getClientId()
            if (r0 != r3) goto L2e
            if (r4 == 0) goto L1f
            android.content.Context r3 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b
            int r4 = r2.getType()
            ijj r3 = defpackage.ivu.d(r3, r4)
            int r4 = r2.getClientId()
            long r2 = r3.e(r2, r4, r5)
            goto L3d
        L1f:
            android.content.Context r3 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b
            int r4 = r2.getType()
            ijj r3 = defpackage.ivu.d(r3, r4)
            int r2 = r3.b(r2, r5)
            goto L3c
        L2e:
            android.content.Context r3 = com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.b
            int r4 = r2.getType()
            ijj r3 = defpackage.ivu.d(r3, r4)
            int r2 = r3.b(r2, r5)
        L3c:
            long r2 = (long) r2
        L3d:
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L45
            r2 = 1
            goto L46
        L45:
            r2 = 0
        L46:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge.d(com.huawei.hihealth.HiHealthData, com.huawei.hihealth.HiHealthData, boolean, int):boolean");
    }

    private void e(List<HiHealthData> list) {
        Comparator bVar;
        int type = list.get(0).getType();
        if (type == 2101 || type == 2102 || type == 2103) {
            Collections.reverse(list);
            return;
        }
        if (type == 2002 || type == 2018 || type == 2105) {
            bVar = new ivx.b(iic.e(), "MIN");
        } else {
            bVar = new ivx.i();
        }
        Collections.sort(list, bVar);
    }

    private boolean c(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        int type = hiHealthData.getType();
        if (type != 2002 && type != 2018) {
            switch (type) {
                case 2101:
                case 2102:
                    return hiHealthData.getMetaData().equals(hiHealthData2.getMetaData());
                case 2103:
                    break;
                default:
                    return false;
            }
        }
        return hiHealthData.getValue() == hiHealthData2.getValue();
    }
}
