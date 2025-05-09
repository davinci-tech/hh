package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.store.interfaces.IDataDelete;
import com.huawei.hihealthservice.store.merge.HiDicHealthDataMerge;
import com.huawei.hihealthservice.store.merge.HiHealthDataPointMerge;
import com.huawei.hihealthservice.store.merge.HiHealthDataPointStressMerge;
import com.huawei.hwcloudmodel.model.unite.DataTimeDelCondition;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class isa implements IDataDelete {

    /* renamed from: a, reason: collision with root package name */
    private static Context f13566a;
    private irz b;
    private List<DataTimeDelCondition> c;
    private ise d;

    /* synthetic */ isa(AnonymousClass4 anonymousClass4) {
        this();
    }

    private isa() {
        this.d = ise.a(f13566a);
        this.b = irz.d();
    }

    static class e {
        private static final isa e = new isa(null);
    }

    public static isa d(Context context) {
        if (context != null) {
            f13566a = context.getApplicationContext();
        }
        return e.e;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0074 A[Catch: Exception -> 0x009a, all -> 0x00c6, TryCatch #1 {all -> 0x00c6, blocks: (B:19:0x0055, B:25:0x0074, B:27:0x007b, B:35:0x0064, B:37:0x009a), top: B:9:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007b A[Catch: Exception -> 0x009a, all -> 0x00c6, TRY_LEAVE, TryCatch #1 {all -> 0x00c6, blocks: (B:19:0x0055, B:25:0x0074, B:27:0x007b, B:35:0x0064, B:37:0x009a), top: B:9:0x0035 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(int r12) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isa.a(int):int");
    }

    private int b(int i, int i2, int i3) {
        ikv e2 = ikr.b(f13566a).e(i, i2, i3, "-1");
        if (e2 == null) {
            return 10;
        }
        return ikr.e(ikr.b(f13566a), 0, i, i3, e2.d());
    }

    private boolean e(List<Integer> list) {
        boolean z;
        ijj a2 = ijj.a(f13566a);
        iji b = iji.b();
        if (a2.c(list) <= 0) {
            LogUtil.h("HiH_HiHealthDataDeleteStore", "deleteKitHealthPointDatas() change <= 0");
            z = false;
        } else {
            z = true;
        }
        if (b.c(list) > 0) {
            return z;
        }
        LogUtil.h("HiH_HiHealthDataDeleteStore", "deleteKitHealthPointDatas() delete health ensitive data failed, change <= 0");
        return false;
    }

    private boolean b(int i, int i2) {
        HiAppInfo c = iip.b().c(i2);
        if (c != null && "com.huawei.health.mc".equals(c.getPackageName())) {
            ijd c2 = ijd.c(f13566a);
            ijb b = ijb.b();
            int c3 = c2.c(47401, 47405, i);
            if (c3 < 0) {
                LogUtil.h("HiH_HiHealthDataDeleteStore", "deleteKitHealthStatData fails");
            }
            int c4 = b.c(47401, 47405, i);
            if (c4 < 0) {
                LogUtil.h("HiH_HiHealthDataDeleteStore", "deleteKitHealthStatData delete health ensitive stat data failed");
                c3 = c4;
            }
            if (c3 < 0) {
                return false;
            }
        }
        return true;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataDelete
    public List<DataTimeDelCondition> deleteHealthData(HiDataDeleteOption hiDataDeleteOption, ikv ikvVar, int i) {
        int g = ikvVar.g();
        List<Integer> c = ikvVar.c();
        int[] types = hiDataDeleteOption.getTypes();
        long currentTimeMillis = System.currentTimeMillis();
        int[] e2 = ivu.e(f13566a, types);
        int[] c2 = ivu.c(f13566a, types);
        List<DataTimeDelCondition> a2 = a(hiDataDeleteOption, g, c, e2, i);
        List<DataTimeDelCondition> a3 = a(hiDataDeleteOption, g, c, c2, i);
        ArrayList arrayList = new ArrayList(16);
        if (!HiCommonUtil.d(a2)) {
            arrayList.addAll(a2);
        }
        if (!HiCommonUtil.d(a3)) {
            arrayList.addAll(a3);
        }
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteHealthData() end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return arrayList;
    }

    private List<DataTimeDelCondition> a(HiDataDeleteOption hiDataDeleteOption, int i, List<Integer> list, int[] iArr, int i2) {
        ArrayList arrayList = new ArrayList(16);
        if (iArr != null && iArr.length != 0) {
            for (HiTimeInterval hiTimeInterval : hiDataDeleteOption.getTimes()) {
                if (!a(hiTimeInterval, iArr, list, i, i2)) {
                    ReleaseLogUtil.d("HiH_HiHealthDataDeleteStore", "deleteHealthData() delete fail types = ", HiJsonUtil.e(iArr), ",timeInterval = ", hiTimeInterval, ",clients = ", list);
                    DataTimeDelCondition dataTimeDelCondition = new DataTimeDelCondition();
                    dataTimeDelCondition.setStartTime(Long.valueOf(hiTimeInterval.getStartTime()));
                    dataTimeDelCondition.setEndTime(Long.valueOf(hiTimeInterval.getEndTime()));
                    arrayList.add(dataTimeDelCondition);
                }
            }
            List<DataTimeDelCondition> e2 = this.d.e();
            if (!HiCommonUtil.d(e2)) {
                arrayList.addAll(e2);
            }
            List<DataTimeDelCondition> e3 = e();
            if (!HiCommonUtil.d(e3)) {
                arrayList.addAll(e3);
            }
        }
        return arrayList;
    }

    private boolean a(HiTimeInterval hiTimeInterval, int[] iArr, List<Integer> list, int i, int i2) {
        if (iArr == null) {
            return false;
        }
        long startTime = hiTimeInterval.getStartTime();
        long endTime = hiTimeInterval.getEndTime();
        int i3 = iArr[0];
        HiHealthDataType.Category e2 = HiHealthDataType.e(i3);
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteHiHealthData() category = ", e2);
        int i4 = AnonymousClass4.f13567a[e2.ordinal()];
        if (i4 == 1) {
            if (i3 < 2000) {
                return c(startTime, endTime, iArr, list, i);
            }
            if (HiHealthDataType.j(i3)) {
                return a(startTime, endTime, iArr, list, i);
            }
            return b(startTime, endTime, iArr, list, i);
        }
        if (i4 == 2) {
            if (i3 <= 21000) {
                return this.d.a(startTime, endTime, iArr, list, i);
            }
            if (i3 <= 22099) {
                return this.d.b(startTime, endTime, iArr, list, i);
            }
            return this.d.d(startTime, endTime, iArr, list, i);
        }
        if (i4 == 3) {
            return c(hiTimeInterval, iArr, list, i, i2);
        }
        if (i4 == 4) {
            return d(startTime, endTime, iArr, i);
        }
        if (i4 != 5) {
            return false;
        }
        return this.b.d(startTime, endTime, iArr, list);
    }

    /* renamed from: isa$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f13567a;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            f13567a = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13567a[HiHealthDataType.Category.SESSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13567a[HiHealthDataType.Category.SEQUENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13567a[HiHealthDataType.Category.STAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13567a[HiHealthDataType.Category.CONFIG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c(long r18, long r20, int[] r22, java.util.List<java.lang.Integer> r23, int r24) {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isa.c(long, long, int[], java.util.List, int):boolean");
    }

    private boolean a(long j, long j2, int[] iArr, List<Integer> list, int i) {
        int[] e2 = ivu.e(f13566a, iArr);
        int[] c = ivu.c(f13566a, iArr);
        boolean e3 = e(j, j2, e2, list, i);
        if (e(j, j2, c, list, i)) {
            return e3;
        }
        return false;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(3:(5:36|37|(2:39|40)(3:67|68|69)|(3:45|46|47)(2:42|43)|44)|33|34) */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0196, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01ec, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(long r24, long r26, int[] r28, java.util.List<java.lang.Integer> r29, int r30) {
        /*
            Method dump skipped, instructions count: 525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isa.e(long, long, int[], java.util.List, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(long r20, long r22, int[] r24, java.util.List<java.lang.Integer> r25, int r26) {
        /*
            Method dump skipped, instructions count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isa.b(long, long, int[], java.util.List, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c(com.huawei.hihealth.HiTimeInterval r11, int[] r12, java.util.List<java.lang.Integer> r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isa.c(com.huawei.hihealth.HiTimeInterval, int[], java.util.List, int, int):boolean");
    }

    static /* synthetic */ boolean d(int i) {
        return i == DicDataTypeUtil.DataType.SLEEP_DETAILS.value();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(long r12, long r14, int[] r16, int r17) {
        /*
            r11 = this;
            android.content.Context r0 = defpackage.isa.f13566a
            ijd r0 = defpackage.ijd.c(r0)
            android.content.Context r1 = defpackage.isa.f13566a
            ikr r1 = defpackage.ikr.b(r1)
            r8 = 0
            r2 = r17
            int r1 = r1.e(r8, r2, r8)
            java.lang.String r9 = "HiH_HiHealthDataDeleteStore"
            if (r1 > 0) goto L21
            java.lang.String r0 = "deleteStatDatas stat() statClient <= 0"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r9, r0)
            return r8
        L21:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.util.List r7 = java.util.Collections.singletonList(r1)
            r1 = r0
            r2 = r12
            r4 = r14
            r6 = r16
            java.util.List r1 = r1.b(r2, r4, r6, r7)
            if (r1 == 0) goto Lc6
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L3c
            goto Lc6
        L3c:
            r2 = 2
            r3 = 1
            android.content.Context r4 = defpackage.isa.f13566a     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            r5 = r16[r8]     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            boolean r4 = defpackage.ivu.i(r4, r5)     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            if (r4 != 0) goto L51
            android.content.Context r4 = defpackage.isa.f13566a     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            r5 = r16[r8]     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            boolean r4 = defpackage.ivu.e(r4, r5)     // Catch: java.lang.Throwable -> L9d java.lang.Exception -> La0
            goto L52
        L51:
            r4 = r8
        L52:
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            r5 = r3
        L57:
            boolean r6 = r1.hasNext()     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            if (r6 == 0) goto L87
            java.lang.Object r6 = r1.next()     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            igo r6 = (defpackage.igo) r6     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            int r6 = r6.a()     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            int r6 = r0.e(r6, r2)     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            if (r6 > 0) goto L77
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            java.lang.String r7 = "deleteStatDatas() change <= 0"
            r5[r8] = r7     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r9, r5)     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            r5 = r8
        L77:
            java.lang.Object[] r7 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            java.lang.String r10 = "deleteStatDatas result = "
            r7[r8] = r10     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            r7[r3] = r6     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r9, r7)     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            goto L57
        L87:
            if (r4 == 0) goto L90
            android.content.Context r0 = defpackage.isa.f13566a     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            r1 = r16[r8]     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
            defpackage.ivu.j(r0, r1)     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Lbb
        L90:
            if (r4 == 0) goto L99
            android.content.Context r0 = defpackage.isa.f13566a
            r1 = r16[r8]
            defpackage.ivu.c(r0, r1)
        L99:
            r8 = r5
            goto Lba
        L9b:
            r0 = move-exception
            goto La2
        L9d:
            r0 = move-exception
            r4 = r8
            goto Lbc
        La0:
            r0 = move-exception
            r4 = r8
        La2:
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r2 = "deleteStatDatas Exception "
            r1[r8] = r2     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> Lbb
            r1[r3] = r0     // Catch: java.lang.Throwable -> Lbb
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r9, r1)     // Catch: java.lang.Throwable -> Lbb
            if (r4 == 0) goto Lba
            android.content.Context r0 = defpackage.isa.f13566a
            r1 = r16[r8]
            defpackage.ivu.c(r0, r1)
        Lba:
            return r8
        Lbb:
            r0 = move-exception
        Lbc:
            if (r4 == 0) goto Lc5
            android.content.Context r1 = defpackage.isa.f13566a
            r2 = r16[r8]
            defpackage.ivu.c(r1, r2)
        Lc5:
            throw r0
        Lc6:
            java.lang.String r0 = "deleteStatDatas() statDatas is null"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r9, r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isa.d(long, long, int[], int):boolean");
    }

    private void d(List<HiHealthData> list) {
        isf a2 = isf.a(f13566a);
        a2.prepareRealTimeHealthDataStat(list);
        a2.doRealTimeHealthDataStat();
    }

    public List<DataTimeDelCondition> e() {
        List<DataTimeDelCondition> subList;
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudHealth mDeleteTimeList=" + HiJsonUtil.e(this.c));
        ArrayList arrayList = new ArrayList(16);
        if (HiCommonUtil.d(this.c)) {
            return arrayList;
        }
        int size = this.c.size();
        int i = size / 10;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 == i) {
                subList = this.c.subList(i3 * 10, size);
            } else {
                subList = this.c.subList(i3 * 10, (i3 + 1) * 10);
            }
            try {
                if (!a(subList)) {
                    arrayList.addAll(subList);
                }
            } catch (iut unused) {
                ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudHealth SyncException=" + HiJsonUtil.e(subList));
                arrayList.addAll(subList);
            }
        }
        this.c.clear();
        return arrayList;
    }

    private boolean a(List<DataTimeDelCondition> list) throws iut {
        ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudHealth deleteDataByTime=" + HiJsonUtil.e(list));
        int i = 0;
        while (true) {
            if (i >= 2) {
                break;
            }
            if (iuz.d(f13566a, list)) {
                ReleaseLogUtil.e("HiH_HiHealthDataDeleteStore", "deleteCloudHealth OK !  dataTimeDelConditions = ", HiJsonUtil.e(list));
                break;
            }
            ReleaseLogUtil.d("HiH_HiHealthDataDeleteStore", "deleteCloudHealth failed dataTimeDelConditions = ", HiJsonUtil.e(list), " upLoadFailCount= ", Integer.valueOf(i));
            i++;
        }
        return i < 2;
    }

    private DataTimeDelCondition b(long j, long j2, int i) {
        DataTimeDelCondition dataTimeDelCondition = new DataTimeDelCondition();
        dataTimeDelCondition.setStartTime(Long.valueOf(j));
        dataTimeDelCondition.setEndTime(Long.valueOf(j2));
        dataTimeDelCondition.setType(Integer.valueOf(i));
        dataTimeDelCondition.setDeviceCode(0L);
        return dataTimeDelCondition;
    }

    private void a(List<HiHealthData> list, List<Integer> list2) {
        HiHealthDataPointStressMerge hiHealthDataPointStressMerge = new HiHealthDataPointStressMerge(f13566a);
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            hiHealthDataPointStressMerge.merge(hiHealthData, hiHealthData.getClientId(), list2);
        }
    }

    private void c(List<HiHealthData> list, List<Integer> list2) {
        HiHealthDataPointMerge hiHealthDataPointMerge = new HiHealthDataPointMerge(f13566a);
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            hiHealthDataPointMerge.merge(hiHealthData, hiHealthData.getClientId(), list2);
        }
    }

    private void d(List<HiHealthData> list, List<Integer> list2) {
        HiDicHealthDataMerge hiDicHealthDataMerge = new HiDicHealthDataMerge(f13566a);
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (!hiDicHealthDataMerge.a(hiHealthData)) {
                ReleaseLogUtil.d("HiH_HiHealthDataDeleteStore", "hiDicHealthDataMerge init failed!");
                return;
            }
            hiDicHealthDataMerge.a(hiHealthData, hiHealthData.getClientId(), list2);
        }
    }
}
