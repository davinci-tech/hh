package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiConfigDataType;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.store.interfaces.IDataInsert;
import com.huawei.hihealthservice.store.stat.HiBloodOxygenSaturationStat;
import com.huawei.hihealthservice.store.stat.HiConfigDataStat;
import com.huawei.hihealthservice.store.stat.HiCoreSleepStat;
import com.huawei.hihealthservice.store.stat.HiDicHealthDataStat;
import com.huawei.hihealthservice.store.stat.HiExerciseIntensityStat;
import com.huawei.hihealthservice.store.stat.HiHeartRateAndRestHeartRateStat;
import com.huawei.hihealthservice.store.stat.HiHeartRateUpStat;
import com.huawei.hihealthservice.store.stat.HiNewStressStat;
import com.huawei.hihealthservice.store.stat.HiPpgStat;
import com.huawei.hihealthservice.store.stat.HiSleepStat;
import com.huawei.hihealthservice.store.stat.HiSportStat;
import com.huawei.hihealthservice.store.stat.HiStatCommon;
import com.huawei.hihealthservice.store.stat.HiStressAndRelaxtionStat;
import com.huawei.hihealthservice.store.stat.HiTrackStat;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/* loaded from: classes7.dex */
public class isf implements IDataInsert {
    private static Context b;

    /* renamed from: a, reason: collision with root package name */
    private isc f13571a;
    private ExecutorService c;
    private BlockingQueue<HiHealthData> d;
    private Queue<HiHealthData> e;
    private iks i;

    /* synthetic */ isf(AnonymousClass3 anonymousClass3) {
        this();
    }

    private isf() {
        this.e = new ConcurrentLinkedQueue();
        this.d = new PriorityBlockingQueue();
        this.i = iks.e();
        this.c = Executors.newSingleThreadExecutor();
        this.f13571a = isc.b(b);
    }

    static class a {
        private static final isf d = new isf(null);
    }

    public static isf a(Context context) {
        if (context != null) {
            b = context.getApplicationContext();
        }
        return a.d;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public int insertBatchDayStatTable(List<igo> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HiH_HiHlhDtIns", "transferHealthStatData statTables is null");
            return 7;
        }
        LogUtil.a("HiH_HiHlhDtIns", "insertBatchDayStatTable() statTables size = ", Integer.valueOf(list.size()));
        List<igo> a2 = ivu.a(b, list);
        List<igo> c = ivu.c(b, list);
        return ((!a2.isEmpty() ? ijb.b().e(a2, b) : true) && (!c.isEmpty() ? ijd.c(b).e(c, b) : true)) ? 0 : 1;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public int insertBatchHealthDetailData(List<HiHealthData> list, int i) {
        if (list == null || list.isEmpty() || i <= 0) {
            LogUtil.h("HiH_HiHlhDtIns", "insertBatchHealthDetailData datas is null or who <= 0");
            return 7;
        }
        LogUtil.c("HiH_HiHlhDtIns", "insertBatchHealthDetailData datas.size=" + list.size());
        return !iiy.e(b).a(list, b) ? 1 : 0;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public int transferHealthStatData(List<igo> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("HiH_HiHlhDtIns", "transferHealthStatData statTables is null");
            return 7;
        }
        LogUtil.c("HiH_HiHlhDtIns", "transferHealthStatData() statTables size = ", Integer.valueOf(list.size()));
        long currentTimeMillis = System.currentTimeMillis();
        List<igo> a2 = ivu.a(b, list);
        List<igo> c = ivu.c(b, list);
        int e = e(a2);
        int e2 = e(c);
        if (e2 != 0) {
            e = e2;
        }
        LogUtil.c("HiH_HiHlhDtIns", "transferHealthStatData() end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return e;
    }

    private int e(List<igo> list) {
        Exception e;
        boolean z;
        if (list != null) {
            boolean isEmpty = list.isEmpty();
            try {
                if (!isEmpty) {
                    try {
                        z = !ivu.i(b, list.get(0).f()) ? ivu.e(b, list.get(0).f()) : false;
                        try {
                            int i = 0;
                            for (igo igoVar : list) {
                                if (!ivu.a(b, igoVar.f()).a(igoVar)) {
                                    i = 4;
                                }
                            }
                            if (z) {
                                ivu.j(b, list.get(0).f());
                            }
                            if (!z) {
                                return i;
                            }
                            ivu.c(b, list.get(0).f());
                            return i;
                        } catch (Exception e2) {
                            e = e2;
                            ReleaseLogUtil.d("HiH_HiHlhDtIns", "transferHealthStatData Exception", LogAnonymous.b((Throwable) e));
                            if (z) {
                                ivu.c(b, list.get(0).f());
                            }
                            return 11;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        z = false;
                    } catch (Throwable th) {
                        th = th;
                        isEmpty = false;
                        if (isEmpty) {
                            ivu.c(b, list.get(0).f());
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return 0;
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public int transferHealthDetailData(List<HiHealthData> list, int i) {
        if (list == null || list.isEmpty() || i <= 0) {
            LogUtil.h("HiH_HiHlhDtIns", "transferHealthDetailData datas is null or who <= 0");
            return 7;
        }
        LogUtil.c("HiH_HiHlhDtIns", "transferHealthDetailData() data size = ", Integer.valueOf(list.size()));
        long currentTimeMillis = System.currentTimeMillis();
        List<Integer> a2 = this.i.a(i);
        if (a2 == null || a2.isEmpty()) {
            LogUtil.b("HiH_HiHlhDtIns", "transferHealthDetailData() clients == null or clients.isEmpty ()");
            return 10;
        }
        List<HiHealthData> e = ivu.e(b, list);
        List<HiHealthData> d = ivu.d(b, list);
        int e2 = e(e, i, a2);
        int e3 = e(d, i, a2);
        if (e3 != 0) {
            e2 = e3;
        }
        LogUtil.c("HiH_HiHlhDtIns", "transferHealthDetailData() end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return e2;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int e(java.util.List<com.huawei.hihealth.HiHealthData> r13, int r14, java.util.List<java.lang.Integer> r15) {
        /*
            r12 = this;
            r0 = 0
            if (r13 == 0) goto Lbe
            boolean r1 = r13.isEmpty()
            if (r1 == 0) goto Lb
            goto Lbe
        Lb:
            r1 = 1
            android.content.Context r2 = defpackage.isf.b     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.Object r3 = r13.get(r0)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            int r3 = r3.getType()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            boolean r2 = defpackage.ivu.i(r2, r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            if (r2 != 0) goto L2f
            android.content.Context r2 = defpackage.isf.b     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.Object r3 = r13.get(r0)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            int r3 = r3.getType()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            boolean r2 = defpackage.ivu.e(r2, r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            goto L30
        L2f:
            r2 = r0
        L30:
            r3 = r0
            r4 = r3
        L32:
            int r5 = r13.size()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            if (r3 >= r5) goto L5f
            java.lang.Object r5 = r13.get(r3)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            r7 = r5
            com.huawei.hihealth.HiHealthData r7 = (com.huawei.hihealth.HiHealthData) r7     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            int r8 = r7.getClientId()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            int r5 = r13.size()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            int r5 = r5 - r1
            if (r3 != r5) goto L4c
            r5 = r1
            goto L4d
        L4c:
            r5 = r0
        L4d:
            r7.setLastDataFlag(r5)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            isc r6 = r12.f13571a     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            r11 = 0
            r9 = r15
            r10 = r14
            boolean r5 = r6.b(r7, r8, r9, r10, r11)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            if (r5 != 0) goto L5c
            r4 = 4
        L5c:
            int r3 = r3 + 1
            goto L32
        L5f:
            if (r2 == 0) goto L70
            android.content.Context r14 = defpackage.isf.b     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            java.lang.Object r15 = r13.get(r0)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            com.huawei.hihealth.HiHealthData r15 = (com.huawei.hihealth.HiHealthData) r15     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            int r15 = r15.getType()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
            defpackage.ivu.j(r14, r15)     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> Lab
        L70:
            if (r2 == 0) goto Laa
            android.content.Context r14 = defpackage.isf.b
            java.lang.Object r13 = r13.get(r0)
            com.huawei.hihealth.HiHealthData r13 = (com.huawei.hihealth.HiHealthData) r13
            int r13 = r13.getType()
            defpackage.ivu.c(r14, r13)
            goto Laa
        L82:
            r14 = move-exception
            goto L89
        L84:
            r14 = move-exception
            r2 = r0
            goto Lac
        L87:
            r14 = move-exception
            r2 = r0
        L89:
            r15 = 2
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch: java.lang.Throwable -> Lab
            java.lang.String r3 = "transferHealthDetailData() e = "
            r15[r0] = r3     // Catch: java.lang.Throwable -> Lab
            r15[r1] = r14     // Catch: java.lang.Throwable -> Lab
            java.lang.String r14 = "HiH_HiHlhDtIns"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r14, r15)     // Catch: java.lang.Throwable -> Lab
            if (r2 == 0) goto La8
            android.content.Context r14 = defpackage.isf.b
            java.lang.Object r13 = r13.get(r0)
            com.huawei.hihealth.HiHealthData r13 = (com.huawei.hihealth.HiHealthData) r13
            int r13 = r13.getType()
            defpackage.ivu.c(r14, r13)
        La8:
            r4 = 11
        Laa:
            return r4
        Lab:
            r14 = move-exception
        Lac:
            if (r2 == 0) goto Lbd
            android.content.Context r15 = defpackage.isf.b
            java.lang.Object r13 = r13.get(r0)
            com.huawei.hihealth.HiHealthData r13 = (com.huawei.hihealth.HiHealthData) r13
            int r13 = r13.getType()
            defpackage.ivu.c(r15, r13)
        Lbd:
            throw r14
        Lbe:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isf.e(java.util.List, int, java.util.List):int");
    }

    public int c(List<HiHealthData> list, int i) {
        if (list == null) {
            return 7;
        }
        return this.f13571a.e(list, i);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public int saveSyncHealthDetailData(List<HiHealthData> list, int i) {
        if (list == null || list.isEmpty() || i <= 0) {
            LogUtil.h("HiH_HiHlhDtIns", "saveSyncHealthDetailData datas is null or who <= 0");
            return 7;
        }
        LogUtil.c("HiH_HiHlhDtIns", "saveSyncHealthDetailData() datas size is = ", Integer.valueOf(list.size()));
        long currentTimeMillis = System.currentTimeMillis();
        List<Integer> a2 = this.i.a(i);
        if (a2 == null || a2.isEmpty()) {
            LogUtil.b("HiH_HiHlhDtIns", "saveSyncHealthDetailData() null or clients ||clients.isEmpty ()");
            return 10;
        }
        List<HiHealthData> e = ivu.e(b, list);
        List<HiHealthData> d = ivu.d(b, list);
        int c = c(e, i, a2);
        int c2 = c(d, i, a2);
        if (c2 != 0) {
            c = c2;
        }
        LogUtil.c("HiH_HiHlhDtIns", "saveSyncHealthDetailData() end totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), ", errorCode = ", Integer.valueOf(c));
        return c;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int c(java.util.List<com.huawei.hihealth.HiHealthData> r13, int r14, java.util.List<java.lang.Integer> r15) {
        /*
            r12 = this;
            r0 = 0
            if (r13 == 0) goto Lcc
            boolean r1 = r13.isEmpty()
            if (r1 == 0) goto Lb
            goto Lcc
        Lb:
            r1 = 1
            android.content.Context r2 = defpackage.isf.b     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.Object r3 = r13.get(r0)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            int r3 = r3.getType()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            boolean r2 = defpackage.ivu.i(r2, r3)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            if (r2 != 0) goto L2f
            android.content.Context r2 = defpackage.isf.b     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            java.lang.Object r3 = r13.get(r0)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            com.huawei.hihealth.HiHealthData r3 = (com.huawei.hihealth.HiHealthData) r3     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            int r3 = r3.getType()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            boolean r2 = defpackage.ivu.e(r2, r3)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            goto L30
        L2f:
            r2 = r0
        L30:
            r3 = r0
            r4 = r3
        L32:
            int r5 = r13.size()     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            if (r3 >= r5) goto L69
            java.lang.Object r5 = r13.get(r3)     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            r7 = r5
            com.huawei.hihealth.HiHealthData r7 = (com.huawei.hihealth.HiHealthData) r7     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            int r8 = r7.getClientId()     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            r7.setSyncStatus(r1)     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            int r5 = r13.size()     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            int r5 = r5 - r1
            if (r3 != r5) goto L4f
            r5 = r1
            goto L50
        L4f:
            r5 = r0
        L50:
            r7.setLastDataFlag(r5)     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            isc r6 = r12.f13571a     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            r11 = 0
            r9 = r15
            r10 = r14
            boolean r5 = r6.b(r7, r8, r9, r10, r11)     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            if (r5 != 0) goto L5f
            r4 = 4
        L5f:
            boolean r5 = com.huawei.haf.application.BaseApplication.j()     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            defpackage.iuz.c(r3, r5)     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            int r3 = r3 + 1
            goto L32
        L69:
            if (r2 == 0) goto L7a
            android.content.Context r14 = defpackage.isf.b     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            java.lang.Object r15 = r13.get(r0)     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            com.huawei.hihealth.HiHealthData r15 = (com.huawei.hihealth.HiHealthData) r15     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            int r15 = r15.getType()     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
            defpackage.ivu.j(r14, r15)     // Catch: java.lang.Exception -> L8c java.lang.Throwable -> Lb9
        L7a:
            if (r2 == 0) goto Lb8
            android.content.Context r14 = defpackage.isf.b
            java.lang.Object r13 = r13.get(r0)
            com.huawei.hihealth.HiHealthData r13 = (com.huawei.hihealth.HiHealthData) r13
            int r13 = r13.getType()
            defpackage.ivu.c(r14, r13)
            goto Lb8
        L8c:
            r14 = move-exception
            goto L93
        L8e:
            r14 = move-exception
            r2 = r0
            goto Lba
        L91:
            r14 = move-exception
            r2 = r0
        L93:
            r15 = 2
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r3 = "saveSyncHealthDetailData Exception"
            r15[r0] = r3     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r14 = health.compact.a.LogAnonymous.b(r14)     // Catch: java.lang.Throwable -> Lb9
            r15[r1] = r14     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r14 = "HiH_HiHlhDtIns"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r14, r15)     // Catch: java.lang.Throwable -> Lb9
            if (r2 == 0) goto Lb6
            android.content.Context r14 = defpackage.isf.b
            java.lang.Object r13 = r13.get(r0)
            com.huawei.hihealth.HiHealthData r13 = (com.huawei.hihealth.HiHealthData) r13
            int r13 = r13.getType()
            defpackage.ivu.c(r14, r13)
        Lb6:
            r4 = 11
        Lb8:
            return r4
        Lb9:
            r14 = move-exception
        Lba:
            if (r2 == 0) goto Lcb
            android.content.Context r15 = defpackage.isf.b
            java.lang.Object r13 = r13.get(r0)
            com.huawei.hihealth.HiHealthData r13 = (com.huawei.hihealth.HiHealthData) r13
            int r13 = r13.getType()
            defpackage.ivu.c(r15, r13)
        Lcb:
            throw r14
        Lcc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isf.c(java.util.List, int, java.util.List):int");
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public boolean saveOneSyncHealthDetailData(HiHealthData hiHealthData, int i, List<Integer> list) {
        if (list == null || list.isEmpty() || i <= 0) {
            ReleaseLogUtil.d("HiH_HiHlhDtIns", "saveOneSyncHealthDetailData clients is null or who <= 0");
            return false;
        }
        int clientId = hiHealthData.getClientId();
        hiHealthData.setSyncStatus(1);
        return this.f13571a.b(hiHealthData, clientId, list, i, 0);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public int bulkSaveDetailHiHealthData(List<HiHealthData> list, ikv ikvVar, int i) {
        if (list == null || list.isEmpty() || ikvVar == null || ikvVar.e() < 0) {
            ReleaseLogUtil.d("HiH_HiHlhDtIns", "bulkSaveDetailHiHealthData hiHealthDatas is null or app < 0");
            return 10;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = b(ivu.d(b, list), ikvVar, i);
        List<HiHealthData> e = ivu.e(b, list);
        if (e != null && !e.isEmpty()) {
            if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
                ReleaseLogUtil.c("HiH_HiHlhDtIns", "Can not open db ", "hihealth_sensitive.db");
                return 26;
            }
            int b3 = b(e, ikvVar, i);
            if (b3 != 0) {
                b2 = b3;
            }
            LogUtil.c("HiH_HiHlhDtIns", "bulkSaveDetailHiHealthData() size = ", Integer.valueOf(list.size()), ",totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
        return b2;
    }

    private int b(List<HiHealthData> list, ikv ikvVar, int i) {
        boolean z;
        if (list != null) {
            boolean isEmpty = list.isEmpty();
            try {
                if (!isEmpty) {
                    try {
                        z = !ivu.i(b, list.get(0).getType()) ? ivu.e(b, list.get(0).getType()) : false;
                        try {
                            int c = this.f13571a.c(list, ikvVar, i);
                            if (z) {
                                ivu.j(b, list.get(0).getType());
                            }
                            if (!z) {
                                return c;
                            }
                            ivu.c(b, list.get(0).getType());
                            return c;
                        } catch (Exception e) {
                            e = e;
                            ReleaseLogUtil.d("HiH_HiHlhDtIns", "bulkSaveDetailHiHealthData() e = ", a(e));
                            if (z) {
                                ivu.c(b, list.get(0).getType());
                            }
                            return 11;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        z = false;
                    } catch (Throwable th) {
                        th = th;
                        isEmpty = false;
                        if (isEmpty) {
                            ivu.c(b, list.get(0).getType());
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return 0;
    }

    private String a(Exception exc) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : exc.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public void prepareAsyncHealthDataStat(List<HiHealthData> list) {
        if (list == null) {
            return;
        }
        List<HiHealthData> d = iwg.d(b, list);
        if (d == null || d.isEmpty()) {
            LogUtil.h("HiH_HiHlhDtIns", "prepareAsyncHealthDataStat() list is null ");
        } else {
            this.d.addAll(d);
            LogUtil.c("HiH_HiHlhDtIns", "prepareAsyncHealthDataStat() list size = ", Integer.valueOf(d.size()), ",asyncStatList size = ", Integer.valueOf(this.d.size()));
        }
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public void prepareRealTimeHealthDataStat(List<HiHealthData> list) {
        prepareRealTimeHealthDataStat(list, true);
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public void prepareRealTimeHealthDataStat(List<HiHealthData> list, boolean z) {
        if (z) {
            b(list);
        }
        List<HiHealthData> d = iwg.d(b, list);
        if (d == null || d.isEmpty()) {
            ReleaseLogUtil.d("HiH_HiHlhDtIns", "PrepareRelTmHlhDtStat List=null ");
        } else {
            this.e.addAll(d);
            LogUtil.c("HiH_HiHlhDtIns", "PrepareRelTmHlhDtStat ListSz=", Integer.valueOf(d.size()), ",relTmStatListSz=", Integer.valueOf(this.e.size()));
        }
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public void doAsyncHealthDataStat() {
        BlockingQueue<HiHealthData> blockingQueue = this.d;
        if (blockingQueue == null || blockingQueue.isEmpty()) {
            LogUtil.h("HiH_HiHlhDtIns", "doAsyncHealthDataStat() statList is null ");
            return;
        }
        LogUtil.c("HiH_HiHlhDtIns", "doAsyncHealthDataStat() start ");
        if (this.c.isShutdown()) {
            LogUtil.b("HiH_HiHlhDtIns", "doAsyncHealthDataStat singleThreadPool is closed! Restarting...");
            this.c = Executors.newSingleThreadExecutor();
        }
        this.c.execute(new b(this));
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public void doRealTimeHealthDataStat() {
        doRealTimeHealthDataStat(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0074  */
    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void doRealTimeHealthDataStat(boolean r7) {
        /*
            r6 = this;
            java.util.Queue<com.huawei.hihealth.HiHealthData> r0 = r6.e
            if (r0 == 0) goto L7a
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto Lc
            goto L7a
        Lc:
            java.util.Queue<com.huawei.hihealth.HiHealthData> r0 = r6.e
            java.lang.Object r0 = r0.element()
            com.huawei.hihealth.HiHealthData r0 = (com.huawei.hihealth.HiHealthData) r0
            int r0 = r0.getType()
            java.util.Queue<com.huawei.hihealth.HiHealthData> r1 = r6.e
            int r1 = r1.size()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = ",1stDtTp:"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            java.lang.String r4 = "dRelTmHlhDtStat Sz:"
            java.lang.Object[] r1 = new java.lang.Object[]{r4, r1, r2, r3}
            java.lang.String r2 = "HiH_HiHlhDtIns"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r1)
            r1 = 0
            android.content.Context r3 = defpackage.isf.b     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            boolean r3 = defpackage.ivu.i(r3, r0)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            if (r3 != 0) goto L43
            android.content.Context r3 = defpackage.isf.b     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            boolean r3 = defpackage.ivu.e(r3, r0)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            goto L44
        L43:
            r3 = r1
        L44:
            r6.e(r7)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L70
            if (r3 == 0) goto L4e
            android.content.Context r7 = defpackage.isf.b     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L70
            defpackage.ivu.j(r7, r0)     // Catch: java.lang.Exception -> L51 java.lang.Throwable -> L70
        L4e:
            if (r3 == 0) goto L6f
            goto L6a
        L51:
            r7 = move-exception
            goto L57
        L53:
            r7 = move-exception
            goto L72
        L55:
            r7 = move-exception
            r3 = r1
        L57:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = "dRelTmHlhDtStat Exp"
            r4[r1] = r5     // Catch: java.lang.Throwable -> L70
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L70
            r1 = 1
            r4[r1] = r7     // Catch: java.lang.Throwable -> L70
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r2, r4)     // Catch: java.lang.Throwable -> L70
            if (r3 == 0) goto L6f
        L6a:
            android.content.Context r7 = defpackage.isf.b
            defpackage.ivu.c(r7, r0)
        L6f:
            return
        L70:
            r7 = move-exception
            r1 = r3
        L72:
            if (r1 == 0) goto L79
            android.content.Context r1 = defpackage.isf.b
            defpackage.ivu.c(r1, r0)
        L79:
            throw r7
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.isf.doRealTimeHealthDataStat(boolean):void");
    }

    private void b(List<HiHealthData> list) {
        if (HiCommonUtil.d(list) || !iuz.i(BaseApplication.e(), list.get(0).getUserId())) {
            return;
        }
        List<HiHealthData> d = iwp.d(list);
        if (koq.b(d)) {
            ReleaseLogUtil.d("HiH_HiHlhDtIns", "activeHourAfterProcess triggerList is empty");
            return;
        }
        for (HiHealthData hiHealthData : d) {
            int type = hiHealthData.getType();
            if (type == 2 || type == 4 || type == 7) {
                if (iwp.d(hiHealthData, hiHealthData.getType())) {
                    iwp.e(hiHealthData);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        BlockingQueue<HiHealthData> blockingQueue = this.d;
        if (blockingQueue == null || blockingQueue.isEmpty()) {
            return;
        }
        int type = this.d.element().getType();
        ReleaseLogUtil.e("HiH_HiHlhDtIns", "saveAsynHealthDatasStat() asyncStatList size = ", Integer.valueOf(this.d.size()), ", firstDataType: ", Integer.valueOf(type));
        boolean z = false;
        while (!this.d.isEmpty()) {
            try {
                try {
                    if (!ivu.i(b, type)) {
                        z = ivu.e(b, type);
                    }
                    b(this.d.take());
                    if (z) {
                        ivu.j(b, type);
                    }
                } catch (Exception e) {
                    ReleaseLogUtil.c("HiH_HiHlhDtIns", "saveAsynHealthDatasStat() Exception", LogAnonymous.b((Throwable) e));
                    if (z) {
                    }
                }
                if (z) {
                    ivu.c(b, type);
                }
            } catch (Throwable th) {
                if (z) {
                    ivu.c(b, type);
                }
                throw th;
            }
        }
    }

    private void e(boolean z) {
        int size = this.e.size();
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<HiHealthData> it = this.e.iterator();
        int i = 0;
        while (it.hasNext()) {
            b(it.next(), z);
            it.remove();
            i++;
        }
        ReleaseLogUtil.e("HiH_HiHlhDtIns", "saveRlTmDtSt sz=", Integer.valueOf(size), ", rSz=", Integer.valueOf(i), ",tlTm=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void b(HiHealthData hiHealthData) {
        b(hiHealthData, true);
    }

    private void b(HiHealthData hiHealthData, boolean z) {
        HiStatCommon hiExerciseIntensityStat;
        int type = hiHealthData.getType();
        boolean z2 = true;
        if (type == 7) {
            hiExerciseIntensityStat = new HiExerciseIntensityStat(b);
        } else {
            if (type != 2002) {
                if (type == 2034) {
                    hiExerciseIntensityStat = new HiNewStressStat(b);
                } else if (type != 2105) {
                    if (type != 2200) {
                        if (type != 20001) {
                            if (type == 22000) {
                                hiExerciseIntensityStat = new HiSleepStat(b);
                            } else if (type == 22100) {
                                hiExerciseIntensityStat = new HiCoreSleepStat(b);
                            } else if (type == 30001) {
                                hiExerciseIntensityStat = new HiTrackStat(b);
                            } else if (type != 40054) {
                                switch (type) {
                                    case 2018:
                                        break;
                                    case 2019:
                                    case 2020:
                                    case 2021:
                                        hiExerciseIntensityStat = new HiStressAndRelaxtionStat(b);
                                        break;
                                    default:
                                        switch (type) {
                                            case 2101:
                                            case 2102:
                                                hiExerciseIntensityStat = new HiHeartRateUpStat(b);
                                                break;
                                            case 2103:
                                                hiExerciseIntensityStat = new HiBloodOxygenSaturationStat(b);
                                                break;
                                            default:
                                                hiExerciseIntensityStat = HiConfigDataType.g(type) ? new HiConfigDataStat(b) : null;
                                                a(hiHealthData);
                                                break;
                                        }
                                }
                            }
                        }
                        hiExerciseIntensityStat = new HiSportStat(b);
                    } else {
                        hiExerciseIntensityStat = new HiPpgStat(b);
                    }
                }
                z2 = false;
            }
            hiExerciseIntensityStat = new HiHeartRateAndRestHeartRateStat(b);
            z2 = false;
        }
        if (hiExerciseIntensityStat != null) {
            hiExerciseIntensityStat.stat(hiHealthData);
        }
        if (z && z2) {
            iwl.c().j(hiHealthData);
        }
    }

    /* renamed from: isf$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f13572a;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            f13572a = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13572a[HiHealthDataType.Category.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void a(HiHealthData hiHealthData) {
        int i = AnonymousClass3.f13572a[HiHealthDataType.e(hiHealthData.getType()).ordinal()];
        if (i == 1 || i == 2) {
            new HiDicHealthDataStat(b).c(hiHealthData);
        } else {
            LogUtil.h("HiH_HiHlhDtIns", "This type is not supported stat, type is ", Integer.valueOf(hiHealthData.getType()));
        }
    }

    static class b implements Runnable {
        private WeakReference<isf> e;

        b(isf isfVar) {
            this.e = new WeakReference<>(isfVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.c("HiH_HiHlhDtIns", "doAsyncHealthDataStat enter");
            isf isfVar = this.e.get();
            if (isfVar == null) {
                LogUtil.h("HiH_HiHlhDtIns", "StatDataRunnable() insertStore = null");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            isfVar.e();
            HiBroadcastUtil.c(isf.b, 0);
            ivg.c().a(200, "doAsyncHealthDataStat", new ikv(isf.b.getPackageName()));
            LogUtil.a("HiH_HiHlhDtIns", "doAsyncHealthDataStat totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
    }

    @Override // com.huawei.hihealthservice.store.interfaces.IDataInsert
    public void onDestroy() {
        LogUtil.c("HiH_HiHlhDtIns", "do onDestroy");
        ExecutorService executorService = this.c;
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
