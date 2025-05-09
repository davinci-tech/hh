package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByVersionReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestRsp;
import com.huawei.hwcloudmodel.model.unite.AddHealthDataRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class itk implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private HiSyncOption f13600a;
    private HealthDataSwitch b;
    private isf c;
    private Context d;
    private jbs e;
    private List<SyncKey> f;
    private int g;
    private List<Integer> h;
    private ijr i;
    private boolean j = false;
    private int m;
    private int o;

    private boolean e(int i) {
        return i == 16;
    }

    public itk(Context context, HiSyncOption hiSyncOption, int i) {
        LogUtil.c("Debug_HiSyncHealthSensitiveData", "HiSyncHealthSensitiveData create");
        this.d = context.getApplicationContext();
        this.f13600a = hiSyncOption;
        this.g = i;
        a();
    }

    private void a() {
        if (this.d == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveData", "HiSyncHealthSensitiveData init failed, mContext is null");
            return;
        }
        this.j = iuz.i();
        this.e = jbs.a(this.d);
        this.i = ijr.d();
        this.c = isf.a(this.d);
        this.b = new HealthDataSwitch(this.d);
        this.h = e();
        List<SyncKey> a2 = ity.a(this.d).a(this.h);
        this.f = a2;
        LogUtil.a("Debug_HiSyncHealthSensitiveData", "init syncKeys is ", a2);
    }

    private List<Integer> e() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(4);
        arrayList.add(18);
        arrayList.add(16);
        arrayList.add(19);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(int r7, long r8, long r10) throws defpackage.iut {
        /*
            r6 = this;
            java.lang.String r0 = "downloadEachTypeByTime type is "
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            java.lang.String r2 = " startTime is "
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            java.lang.String r4 = " endTime is "
            java.lang.Long r5 = java.lang.Long.valueOf(r10)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1, r2, r3, r4, r5}
            java.lang.String r1 = "Debug_HiSyncHealthSensitiveData"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
            com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq r8 = r6.b(r7, r8, r10)
            jbs r9 = r6.e
            com.huawei.hwcloudmodel.model.unite.GetHealthDataByTimeRsp r8 = r9.e(r8)
            r9 = 0
            boolean r10 = defpackage.ius.a(r8, r9)
            if (r10 != 0) goto L3a
            java.lang.String r8 = "downloadEachTypeByTime error type is "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r8, r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return
        L3a:
            java.util.List r8 = r8.getDetailInfos()
            if (r8 == 0) goto L93
            boolean r10 = r8.isEmpty()
            if (r10 == 0) goto L47
            goto L93
        L47:
            r10 = 10001(0x2711, float:1.4014E-41)
            android.content.Context r11 = r6.d     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            boolean r11 = defpackage.ivu.i(r11, r10)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            if (r11 != 0) goto L58
            android.content.Context r11 = r6.d     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            boolean r11 = defpackage.ivu.e(r11, r10)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            goto L59
        L58:
            r11 = r9
        L59:
            r0 = 0
            r6.b(r8, r7, r0)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L89
            if (r11 == 0) goto L65
            android.content.Context r7 = r6.d     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L89
            defpackage.ivu.j(r7, r10)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L89
        L65:
            if (r11 == 0) goto L88
            goto L83
        L68:
            r7 = move-exception
            goto L6e
        L6a:
            r7 = move-exception
            goto L8b
        L6c:
            r7 = move-exception
            r11 = r9
        L6e:
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L89
            java.lang.String r0 = "downloadEachTypeByTime Exception"
            r8[r9] = r0     // Catch: java.lang.Throwable -> L89
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L89
            r9 = 1
            r8[r9] = r7     // Catch: java.lang.Throwable -> L89
            java.lang.String r7 = "HiH_HiSyncHlthSensDt"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r7, r8)     // Catch: java.lang.Throwable -> L89
            if (r11 == 0) goto L88
        L83:
            android.content.Context r7 = r6.d
            defpackage.ivu.c(r7, r10)
        L88:
            return
        L89:
            r7 = move-exception
            r9 = r11
        L8b:
            if (r9 == 0) goto L92
            android.content.Context r8 = r6.d
            defpackage.ivu.c(r8, r10)
        L92:
            throw r7
        L93:
            java.lang.String r8 = "downloadEachTypeByTime data error type is "
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r8, r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.itk.e(int, long, long):void");
    }

    private GetHealthDataByTimeReq b(int i, long j, long j2) {
        GetHealthDataByTimeReq getHealthDataByTimeReq = new GetHealthDataByTimeReq();
        getHealthDataByTimeReq.setQueryType(2);
        getHealthDataByTimeReq.setDataType(2);
        getHealthDataByTimeReq.setStartTime(Long.valueOf(j));
        getHealthDataByTimeReq.setEndTime(Long.valueOf(j2));
        getHealthDataByTimeReq.setType(Integer.valueOf(i));
        return getHealthDataByTimeReq;
    }

    private void d() throws iut {
        Iterator<SyncKey> it = this.f.iterator();
        while (it.hasNext()) {
            b(it.next());
            ivc.b(this.d, 1.0d, 1.0d, 1.0d);
        }
    }

    private void b(SyncKey syncKey) throws iut {
        if (syncKey == null) {
            LogUtil.h("Debug_HiSyncHealthSensitiveData", "downloadOneTypeDataByVersion syncKey is null!");
            return;
        }
        int intValue = syncKey.getType().intValue();
        long longValue = syncKey.getVersion().longValue();
        LogUtil.a("Debug_HiSyncHealthSensitiveData", "downloadOneTypeDataByVersion type is ", Integer.valueOf(intValue), ", cloudDataVersion is ", Long.valueOf(longValue));
        if (longValue <= 0) {
            LogUtil.h("Debug_HiSyncHealthSensitiveData", "downloadOneTypeDataByVersion cloud has no such data, type is ", Integer.valueOf(intValue));
            return;
        }
        GetHealthDataByVersionReq getHealthDataByVersionReq = new GetHealthDataByVersionReq();
        getHealthDataByVersionReq.setDataType(Integer.valueOf(this.f13600a.getSyncMethod()));
        getHealthDataByVersionReq.setType(Integer.valueOf(intValue));
        if (this.f13600a.getSyncDataArea() == 1 && intValue == 16) {
            long a2 = iwe.a(this.d, intValue, this.g, 0L);
            ReleaseLogUtil.e("Debug_HiSyncHealthSensitiveData", "downloadAllSpO2Data,type is ", Integer.valueOf(intValue), " DBversion is ", Long.valueOf(a2), " maxVersion is ", Long.valueOf(longValue));
            if (a2 < longValue) {
                getHealthDataByVersionReq.setVersion(a2);
                c(getHealthDataByVersionReq, longValue);
            }
        }
        igq c = this.i.c(this.g, 0L, intValue);
        if (c == null) {
            LogUtil.a("Debug_HiSyncHealthSensitiveData", " syncAnchorTable is null");
            getHealthDataByVersionReq.setVersion(0L);
            c(getHealthDataByVersionReq, longValue);
        } else {
            if (c.a() < longValue) {
                LogUtil.a("Debug_HiSyncHealthSensitiveData", "Local version is ", Long.valueOf(c.a()));
                getHealthDataByVersionReq.setVersion(c.a());
                c(getHealthDataByVersionReq, longValue);
                return;
            }
            LogUtil.a("Debug_HiSyncHealthSensitiveData", " do not need downloadOneTypeDataByVersion data, type is ", Integer.valueOf(intValue), ", DBDataversion is ", Long.valueOf(c.a()), ", cloudDataVersion is ", Long.valueOf(longValue));
        }
    }

    private void c(GetHealthDataByVersionReq getHealthDataByVersionReq, long j) throws iut {
        long b;
        ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", " downloadOneTypeDataWithMaxVersion rep = ", getHealthDataByVersionReq, ", maxVersion = ", Long.valueOf(j));
        int i = 0;
        do {
            b = b(getHealthDataByVersionReq);
            LogUtil.c("Debug_HiSyncHealthSensitiveData", " downloadOneTypeDataWithMaxVersion downCurrentVersion is ", Long.valueOf(b), " maxVersion is ", Long.valueOf(j));
            i++;
            if (b <= -1) {
                return;
            }
            if (this.f13600a.getSyncDataArea() == 1 && getHealthDataByVersionReq.getType().intValue() == 16) {
                iwe.c(this.d, getHealthDataByVersionReq.getType().intValue(), b, this.g);
            } else if (!this.i.d(this.g, getHealthDataByVersionReq.getType().intValue(), b, 0L)) {
                LogUtil.h("Debug_HiSyncHealthSensitiveData", "downloadOneTypeDataWithMaxVersion saveVersionToDB failed!");
                return;
            }
            getHealthDataByVersionReq.setVersion(b);
            if (ism.h() && !iuz.f()) {
                LogUtil.h("HiH_HiSyncHlthSensDt", " downloadOneTypeDataWithMaxVersion() backgroud is running");
                return;
            } else if (i >= 20) {
                LogUtil.h("Debug_HiSyncHealthSensitiveData", " downloadOneTypeDataWithMaxVersion() pullDataByVersion too many times");
                return;
            }
        } while (b < j);
    }

    public void d(int i, long j) throws iut {
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveData", "Can not open db ", "hihealth_sensitive.db");
        } else {
            e(i, iuz.d(j, 1), j - 1);
        }
    }

    private long b(GetHealthDataByVersionReq getHealthDataByVersionReq) throws iut {
        GetHealthDataByVersionRsp a2 = this.e.a(getHealthDataByVersionReq);
        if (!ius.a(a2, false)) {
            LogUtil.h("Debug_HiSyncHealthSensitiveData", "downOneTypeDataOnce() SyncError checkCloudRsp");
            return -1L;
        }
        long currentVersion = a2.getCurrentVersion();
        if (currentVersion <= getHealthDataByVersionReq.getVersion()) {
            ReleaseLogUtil.d("HiH_HiSyncHlthSensDt", "downOneTypeDataOnce downloadVersion ", Long.valueOf(currentVersion), " smaller than currentVersion ", Long.valueOf(getHealthDataByVersionReq.getVersion()));
            return -1L;
        }
        List<HealthDetail> detailInfos = a2.getDetailInfos();
        if (HiCommonUtil.d(detailInfos)) {
            ReleaseLogUtil.d("HiH_HiSyncHlthSensDt", "downOneTypeDataOnce() detailInfos is null or empty");
            return currentVersion;
        }
        if (!b(detailInfos, getHealthDataByVersionReq.getType().intValue(), getHealthDataByVersionReq.getVersion())) {
            return -1L;
        }
        ivg.c().b(getHealthDataByVersionReq.getType().intValue(), new ikv(this.d.getPackageName()));
        return currentVersion;
    }

    private boolean b(List<HealthDetail> list, int i, long j) throws iut {
        List<HiHealthData> c;
        LogUtil.c("Debug_HiSyncHealthSensitiveData", " saveData()");
        ArrayList arrayList = new ArrayList(10);
        Collections.sort(list, iuu.e());
        for (HealthDetail healthDetail : list) {
            if (healthDetail != null && healthDetail.getVersion() >= j && (c = this.b.c(healthDetail, this.g)) != null && !c.isEmpty()) {
                this.c.saveSyncHealthDetailData(c, this.g);
                if (e(i)) {
                    arrayList.addAll(c);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        this.c.prepareRealTimeHealthDataStat(arrayList);
        this.c.doRealTimeHealthDataStat();
        return true;
    }

    private void b(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            LogUtil.c("Debug_HiSyncHealthSensitiveData", " uploadPointsOK() hiHealthDatas is null or empty");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            ivu.d(this.d, hiHealthData.getType()).e(hiHealthData.getDataId(), hiHealthData.getLong("modified_time"), 1);
            hiHealthData.setUserId(this.g);
        }
        this.c.prepareRealTimeHealthDataStat(list);
        this.c.doRealTimeHealthDataStat();
    }

    private boolean b(List<HiHealthData> list, int i, int i2, boolean z) throws iut {
        if (z || !this.j) {
            int i3 = this.o + 1;
            this.o = i3;
            iuz.e(i3, this.f13600a.getSyncManual());
        } else {
            int i4 = this.o + 1;
            this.o = i4;
            if (i4 > 5) {
                this.m += 2;
                return false;
            }
        }
        List<HealthDetail> b = this.b.b(list, i, i2);
        if (b == null || b.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthSensitiveData", " addHealthData() healthDetails is null or empty cloudType is ", Integer.valueOf(i2), " clientId is ", Integer.valueOf(i));
            return false;
        }
        AddHealthDataReq addHealthDataReq = new AddHealthDataReq();
        addHealthDataReq.setTimeZone(list.get(0).getTimeZone());
        addHealthDataReq.setDetailInfo(b);
        igq c = this.i.c(this.g, 0L, i2);
        if (c == null) {
            addHealthDataReq.setLocalMaxVersion(0L);
        } else {
            addHealthDataReq.setLocalMaxVersion(Long.valueOf(c.a()));
        }
        while (this.m < 2) {
            AddHealthDataRsp d = this.e.d(addHealthDataReq);
            if (!ius.a(d, false)) {
                this.m++;
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "addDataToCloud OK ! uploadCount is ", Integer.valueOf(this.o), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
                b(list);
                d(addHealthDataReq, d, i2);
                return true;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "addDataToCloud failed ! uploadCount is ", Integer.valueOf(this.o), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
        return false;
    }

    private void d(AddHealthDataReq addHealthDataReq, AddHealthDataRsp addHealthDataRsp, int i) throws iut {
        ReleaseLogUtil.e("Debug_HiSyncHealthSensitiveData", "LocalMaxVersion()=", addHealthDataReq.getLocalMaxVersion(), ",rsp=", addHealthDataRsp.getTimestamp(), ",isHasMore()=", Boolean.valueOf(addHealthDataRsp.isHasMore()), ",cloudType=", Integer.valueOf(i));
        if (addHealthDataRsp.isHasMore()) {
            SyncKey syncKey = new SyncKey();
            syncKey.setType(Integer.valueOf(i));
            syncKey.setVersion(addHealthDataRsp.getTimestamp());
            b(syncKey);
            return;
        }
        if (this.i.d(this.g, i, addHealthDataRsp.getTimestamp().longValue(), 0L)) {
            return;
        }
        ReleaseLogUtil.d("HiH_HiSyncHlthSensDt", "downloadMore saveVersionToDB failed!");
    }

    private void d(int i, int i2) throws iut {
        while (this.m < 2) {
            List<HiHealthData> c = c(i, i2);
            if (HiCommonUtil.d(c) || !b(c, i, i2, false)) {
                break;
            }
        }
        this.m = 0;
    }

    private List<HiHealthData> c(int i, int i2) {
        if (i2 == 4) {
            return a(i);
        }
        if (i2 == 16) {
            return c(i);
        }
        if (i2 == 18) {
            return d(i);
        }
        if (i2 == 19) {
            return b(i);
        }
        ReleaseLogUtil.e("Debug_HiSyncHealthSensitiveData", "type is invalid!");
        return new ArrayList();
    }

    private List<HiHealthData> a(int i) {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(2015);
        arrayList.add(2008);
        arrayList.add(2009);
        arrayList.add(2010);
        arrayList.add(2011);
        arrayList.add(2012);
        arrayList.add(2013);
        arrayList.add(2014);
        arrayList.add(2106);
        return ivu.d(this.d, 10001).c(i, arrayList, 50);
    }

    private List<HiHealthData> d(int i) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(2104);
        return ivu.d(this.d, 2104).c(i, arrayList, 50);
    }

    private List<HiHealthData> c(int i) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(2103);
        return ivu.d(this.d, 2103).c(i, arrayList, 200);
    }

    private List<HiHealthData> b(int i) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(2107);
        return ivu.d(this.d, 2107).c(i, arrayList, 50);
    }

    private void f(int i) throws iut {
        while (this.m < 2) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(2103);
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> e = ivu.d(this.d, 2103).e(i, arrayList, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            if (e == null || e.isEmpty() || !b(e, i, 16, true)) {
                break;
            }
        }
        this.m = 0;
    }

    private void j(int i) throws iut {
        while (this.m < 2) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(2107);
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> e = ivu.d(this.d, 2107).e(i, arrayList, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            if (e == null || e.isEmpty() || !b(e, i, 19, true)) {
                break;
            }
        }
        this.m = 0;
    }

    public void c() throws iut {
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveData", "Can not open db ", "hihealth_sensitive.db");
            return;
        }
        igq e = this.i.e(this.g, 0L, 10024);
        if (e != null) {
            long t = HiDateUtil.t(HiDateUtil.a(e.d()));
            this.i.a(this.g, 10024, HiDateUtil.c(t), 0L);
            e(16, iuz.d(t, 7), t);
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        ivc.c(5.0d, "SYNC_SENSITIVE_DOWNLOAD_PERCENT_MAX_ALL");
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveData", "Can not open db ", "hihealth_sensitive.db");
            ivc.b(this.d);
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "pullDataByVersion() begin !");
        LogUtil.c("Debug_HiSyncHealthSensitiveData", "pullDataByVersion() keys is ", this.f);
        if (HiCommonUtil.d(this.f)) {
            LogUtil.h("Debug_HiSyncHealthSensitiveData", "pullDataByVersion() keys is null, stop pullDataByVersion");
        } else {
            d();
        }
        ivc.b(this.d);
        ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "pullDataByVersion() end !");
    }

    public void b() {
        List<SyncKey> d = ity.a(this.d).d(16);
        if (HiCommonUtil.d(d)) {
            ReleaseLogUtil.d("Debug_HiSyncHealthSensitiveData", "BLOOD_OXYGEN not exist in cloud");
        } else {
            this.i.d(this.g, 16, d.get(0).getVersion().longValue(), 0L);
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveData", "Can not open db ", "hihealth_sensitive.db");
            return;
        }
        List<SyncKey> list = this.f;
        if (list == null || list.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthSensitiveData", "pullDataByTime() keys is null,stop pullDataByVersion");
            return;
        }
        for (SyncKey syncKey : this.f) {
            int intValue = syncKey.getType().intValue();
            long longValue = syncKey.getVersion().longValue();
            LogUtil.a("Debug_HiSyncHealthSensitiveData", "pullDataByTime type is ", Integer.valueOf(intValue), " cloudDataVersion is ", Long.valueOf(longValue));
            if (longValue <= 0) {
                LogUtil.h("Debug_HiSyncHealthSensitiveData", "pullDataByTime cloud has no such data, type is ", Integer.valueOf(intValue));
            } else if (intValue != 16) {
                continue;
            } else {
                HiDataReadOption hiDataReadOption = new HiDataReadOption();
                hiDataReadOption.setStartTime(0L);
                hiDataReadOption.setEndTime(System.currentTimeMillis());
                hiDataReadOption.setType(new int[]{47204});
                hiDataReadOption.setCount(1);
                hiDataReadOption.setSortOrder(1);
                List<HiHealthData> b = ijb.b().b(hiDataReadOption, hiDataReadOption.getType(), ikr.b(this.d).e(0, this.g, 0));
                if (HiCommonUtil.d(b)) {
                    return;
                }
                long startTime = 86400000 + b.get(0).getStartTime();
                long d = iuz.d(startTime, 7);
                this.i.a(this.g, 10024, HiDateUtil.c(startTime), 0L);
                e(intValue, d, startTime);
            }
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            LogUtil.b("Debug_HiSyncHealthSensitiveData", "Can not open db ", "hihealth_sensitive.db");
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "pushData() begin !");
        if (!ism.m()) {
            ReleaseLogUtil.d("HiH_HiSyncHlthSensDt", "pushData() dataPrivacy switch is closed, push end!");
            return;
        }
        iuy.e(this.d, this.g);
        List<Integer> i = iis.d().i(this.g);
        if (HiCommonUtil.d(i)) {
            ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "pushData() end!");
            return;
        }
        List<Integer> b = iji.b().b("client_id", 0);
        if (HiCommonUtil.d(b)) {
            ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "pushData() end!");
            return;
        }
        i.retainAll(b);
        if (!HiCommonUtil.d(i)) {
            LogUtil.a("HiH_HiSyncHlthSensDt", "clientid list size = ", Integer.valueOf(i.size()));
            if (!this.j) {
                c(i);
            } else {
                d(i);
                this.o = 0;
                d(4, i);
                d(16, i);
                d(19, i);
            }
            ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "pushData() end!");
            return;
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthSensDt", "pushData() end!");
    }

    private void d(List<Integer> list) throws iut {
        for (Integer num : list) {
            h(num.intValue());
            f(num.intValue());
            j(num.intValue());
        }
    }

    private void h(int i) throws iut {
        while (this.m < 2) {
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(2015);
            arrayList.add(2008);
            arrayList.add(2009);
            arrayList.add(2010);
            arrayList.add(2011);
            arrayList.add(2012);
            arrayList.add(2013);
            arrayList.add(2014);
            arrayList.add(2106);
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> e = ivu.d(this.d, 10001).e(i, arrayList, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            if (e == null || e.isEmpty() || !b(e, i, 0, true)) {
                break;
            }
        }
        this.m = 0;
    }

    private void c(List<Integer> list) throws iut {
        for (Integer num : list) {
            d(num.intValue(), 4);
            d(num.intValue(), 18);
            d(num.intValue(), 16);
            d(num.intValue(), 19);
        }
    }

    private void d(int i, List<Integer> list) throws iut {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            d(it.next().intValue(), i);
        }
        this.o = 0;
    }

    public void c(int i, int i2, int i3) throws iut {
        ReleaseLogUtil.e("Debug_HiSyncHealthSensitiveData", "enter downloadLatestData type = ", Integer.valueOf(i3));
        if (HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() == null) {
            ReleaseLogUtil.c("Debug_HiSyncHealthSensitiveData", "Can not open db ", "hihealth_sensitive.db");
            return;
        }
        GetHealthDataLatestReq getHealthDataLatestReq = new GetHealthDataLatestReq();
        getHealthDataLatestReq.setType(i3);
        getHealthDataLatestReq.setDataType(2);
        getHealthDataLatestReq.setTimestamp(HiDateUtil.g(System.currentTimeMillis()));
        if (i > 0) {
            getHealthDataLatestReq.setDays(i);
        }
        if (i2 > 0) {
            getHealthDataLatestReq.setCounts(i2);
        }
        GetHealthDataLatestRsp c = this.e.c(getHealthDataLatestReq);
        if (!ius.a(c, false)) {
            ReleaseLogUtil.d("Debug_HiSyncHealthSensitiveData", "downloadLatestData warning");
            return;
        }
        List<HealthDetail> detailInfos = c.getDetailInfos();
        if (koq.b(detailInfos)) {
            ReleaseLogUtil.d("Debug_HiSyncHealthSensitiveData", "downloadLatestData dataList is Empty");
        } else {
            b(detailInfos, i3, 0L);
            ivg.c().a(ivg.d(i3), "latestDataDownload", new ikv(this.d.getPackageName()));
        }
    }

    public String toString() {
        return "--HiSyncHealthSensitiveData {}";
    }
}
