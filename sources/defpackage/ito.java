package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hihealthservice.sync.dataswitch.SportDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByVersionReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestRsp;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByTimeReq;
import com.huawei.hwcloudmodel.model.unite.AddHealthDataRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.GetSportDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.unite.SportDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes7.dex */
public class ito implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private iiy f13604a;
    private iix b;
    private HealthDataSwitch c;
    private ijj d;
    private ijo e;
    private List<SyncKey> f;
    private isf g;
    private jbs i;
    private HiSyncOption j;
    private int k;
    private Context l;
    private ijr n;
    private SportDataSwitch o;
    private int q;
    private List<Integer> s;
    private int t;
    private int m = 0;
    private boolean h = false;

    private boolean e(int i) {
        return i == 3 || i == 7 || i == 9 || i == 11 || i == 12;
    }

    private boolean h(int i) {
        return i == 7;
    }

    public ito(Context context, HiSyncOption hiSyncOption, int i) throws iut {
        LogUtil.c("HiH_HiSyncMltHlth", "HiSyncMultiHealth create");
        this.l = context.getApplicationContext();
        this.j = hiSyncOption;
        this.k = i;
        l();
    }

    private void l() throws iut {
        this.h = iuz.i();
        this.i = jbs.a(this.l);
        this.n = ijr.d();
        this.o = new SportDataSwitch(this.l);
        this.d = ijj.a(this.l);
        this.g = isf.a(this.l);
        this.b = iix.a(this.l);
        this.c = new HealthDataSwitch(this.l);
        this.e = ijo.b(this.l);
        this.f13604a = iiy.e(this.l);
        this.s = m();
    }

    private List<Integer> m() {
        if (this.j.getSyncDataType() == 7) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(7);
            return arrayList;
        }
        if (this.j.getSyncDataType() == 11) {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(11);
            return arrayList2;
        }
        ArrayList arrayList3 = new ArrayList(4);
        arrayList3.add(7);
        arrayList3.add(9);
        arrayList3.add(11);
        arrayList3.add(12);
        return arrayList3;
    }

    private void b(int i, long j, long j2, boolean z) throws iut {
        int i2;
        int i3;
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "downloadEachTypeByTime type is", Integer.valueOf(i), " startTime is ", Long.valueOf(j), " endTime is ", Long.valueOf(j2), "isNeedRecordTime is ", Boolean.valueOf(z));
        if (i == 1 || i == 3) {
            i2 = 10015;
        } else {
            if (i == 7) {
                i3 = 10017;
            } else if (i == 9) {
                i2 = 10016;
            } else if (i == 11) {
                i3 = 10020;
            } else if (i != 12) {
                i2 = i;
            } else {
                i3 = 10022;
            }
            i2 = i3;
        }
        if (z) {
            this.n.a(this.k, i2, HiDateUtil.c(j2), 0L);
        }
        if (10015 == i2) {
            d(j, j2, i2, z);
            return;
        }
        if (!iuz.f() || 10016 != i2) {
            a(i, j, j2, i2, z);
            return;
        }
        long j3 = j2;
        int i4 = 0;
        while (i4 < 4) {
            long l = HiDateUtil.l(iuz.d(j3, 8));
            a(i, l, j3, i2, z);
            i4++;
            j3 = l;
            i2 = i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(long r9, long r11, int r13, boolean r14) throws defpackage.iut {
        /*
            r8 = this;
            com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByTimeReq r11 = r8.e(r9, r11)
            jbs r12 = r8.i
            com.huawei.hwcloudmodel.model.unite.GetSportDataByTimeRsp r11 = r12.b(r11)
            r12 = 0
            boolean r0 = defpackage.ius.a(r11, r12)
            java.lang.String r1 = "HiH_HiSyncMltHlth"
            if (r0 != 0) goto L1d
            java.lang.String r9 = "downloadSportDataByTime warning"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r9)
            return
        L1d:
            if (r14 == 0) goto L2d
            ijr r2 = r8.n
            int r3 = r8.k
            int r5 = health.compact.a.HiDateUtil.c(r9)
            r6 = 0
            r4 = r13
            r2.a(r3, r4, r5, r6)
        L2d:
            java.util.List r9 = r11.getDetailInfos()
            boolean r10 = health.compact.a.HiCommonUtil.d(r9)
            if (r10 == 0) goto L41
            java.lang.String r9 = "downloadSportDataByTime sleepDatas null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r9)
            return
        L41:
            android.content.Context r10 = r8.l     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L63
            boolean r10 = defpackage.ivu.i(r10, r12)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L63
            if (r10 != 0) goto L50
            android.content.Context r10 = r8.l     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L63
            boolean r10 = defpackage.ivu.e(r10, r12)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L63
            goto L51
        L50:
            r10 = r12
        L51:
            r8.c(r9)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7e
            if (r10 == 0) goto L5b
            android.content.Context r9 = r8.l     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7e
            defpackage.ivu.j(r9, r12)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L7e
        L5b:
            if (r10 == 0) goto L7d
            goto L78
        L5e:
            r9 = move-exception
            goto L65
        L60:
            r9 = move-exception
            r10 = r12
            goto L7f
        L63:
            r9 = move-exception
            r10 = r12
        L65:
            r11 = 2
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch: java.lang.Throwable -> L7e
            java.lang.String r13 = "downloadHealthDataByTime Exception"
            r11[r12] = r13     // Catch: java.lang.Throwable -> L7e
            java.lang.String r9 = health.compact.a.LogAnonymous.b(r9)     // Catch: java.lang.Throwable -> L7e
            r13 = 1
            r11[r13] = r9     // Catch: java.lang.Throwable -> L7e
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r1, r11)     // Catch: java.lang.Throwable -> L7e
            if (r10 == 0) goto L7d
        L78:
            android.content.Context r9 = r8.l
            defpackage.ivu.c(r9, r12)
        L7d:
            return
        L7e:
            r9 = move-exception
        L7f:
            if (r10 == 0) goto L86
            android.content.Context r10 = r8.l
            defpackage.ivu.c(r10, r12)
        L86:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ito.d(long, long, int, boolean):void");
    }

    private void c(List<SportDetail> list) throws iut {
        b(list, true);
        this.g.doRealTimeHealthDataStat();
    }

    private void a(int i, long j, long j2, int i2, boolean z) throws iut {
        boolean z2;
        GetHealthDataByTimeRsp e = this.i.e(d(i, j, j2));
        if (!ius.a(e, false)) {
            LogUtil.h("HiH_HiSyncMltHlth", "downloadHealthDataByTime error type is ", Integer.valueOf(i));
            return;
        }
        if (z) {
            this.n.a(this.k, i2, HiDateUtil.c(j), 0L);
        }
        List<HealthDetail> detailInfos = e.getDetailInfos();
        if (detailInfos != null) {
            try {
                if (!detailInfos.isEmpty()) {
                    try {
                        z2 = !ivu.i(this.l, 0) ? ivu.e(this.l, 0) : false;
                        try {
                            d(detailInfos, i, j, j2);
                            if (z2) {
                                ivu.j(this.l, 0);
                            }
                            if (!z2) {
                                return;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            ReleaseLogUtil.c("HiH_HiSyncMltHlth", "downloadHealthDataByTime Exception", LogAnonymous.b((Throwable) e));
                            if (!z2) {
                                return;
                            }
                            ivu.c(this.l, 0);
                            return;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        z2 = false;
                    } catch (Throwable th) {
                        th = th;
                        if (0 != 0) {
                            ivu.c(this.l, 0);
                        }
                        throw th;
                    }
                    ivu.c(this.l, 0);
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        LogUtil.h("HiH_HiSyncMltHlth", "downloadHealthDataByTime data error type is ", Integer.valueOf(i));
    }

    private void d(List<HealthDetail> list, int i, long j, long j2) throws iut {
        if (b(list, i, true)) {
            if (i == 7) {
                b(j, j2);
            }
            ivg.c().b(i, new ikv(this.l.getPackageName()));
        }
    }

    private void b(long j, long j2) {
        HashSet hashSet = new HashSet();
        String b = SharedPreferenceManager.b(this.l, "huawei_hihealth", "heartRate_" + this.k);
        if (!TextUtils.isEmpty(b)) {
            hashSet.addAll((Collection) HiJsonUtil.b(b, new TypeToken<Set<Integer>>() { // from class: ito.2
            }.getType()));
        }
        while (j < j2) {
            hashSet.add(Integer.valueOf(HiDateUtil.c(j)));
            j += 86400000;
        }
        SharedPreferenceManager.e(this.l, "huawei_hihealth", "heartRate_" + this.k, HiJsonUtil.e(hashSet), (StorageParams) null);
    }

    private void b(List<SportDetail> list, boolean z) throws iut {
        List<HiHealthData> c = this.o.c(list, this.k, 2);
        if (HiCommonUtil.d(c)) {
            return;
        }
        Iterator<HiHealthData> it = c.iterator();
        while (it.hasNext()) {
            it.next().setMergedStatus(999);
        }
        boolean z2 = this.g.saveSyncHealthDetailData(c, this.k) == 0;
        if (z) {
            this.g.prepareRealTimeHealthDataStat(c);
            this.g.doRealTimeHealthDataStat();
        } else {
            this.g.prepareAsyncHealthDataStat(c);
        }
        if (z2) {
            ivg.c().a(2, "sync download", new ikv(this.l.getPackageName()));
        }
    }

    private GetHealthDataByTimeReq d(int i, long j, long j2) {
        GetHealthDataByTimeReq getHealthDataByTimeReq = new GetHealthDataByTimeReq();
        getHealthDataByTimeReq.setQueryType(2);
        getHealthDataByTimeReq.setDataType(2);
        getHealthDataByTimeReq.setStartTime(Long.valueOf(j));
        getHealthDataByTimeReq.setEndTime(Long.valueOf(j2));
        getHealthDataByTimeReq.setType(Integer.valueOf(i));
        return getHealthDataByTimeReq;
    }

    private GetSportDataByTimeReq e(long j, long j2) {
        GetSportDataByTimeReq getSportDataByTimeReq = new GetSportDataByTimeReq();
        getSportDataByTimeReq.setQueryType(2);
        getSportDataByTimeReq.setDataType(2);
        getSportDataByTimeReq.setStartTime(Long.valueOf(j));
        getSportDataByTimeReq.setEndTime(Long.valueOf(j2));
        HashSet hashSet = new HashSet(3);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        getSportDataByTimeReq.setSportTypes(hashSet);
        return getSportDataByTimeReq;
    }

    private void h() throws iut {
        Iterator<SyncKey> it = this.f.iterator();
        while (it.hasNext()) {
            e(it.next());
            ivc.b(this.l, 1.0d, 1.0d, 1.0d);
        }
    }

    private void e(SyncKey syncKey) throws iut {
        if (syncKey == null) {
            return;
        }
        int intValue = syncKey.getType().intValue();
        long longValue = syncKey.getVersion().longValue();
        LogUtil.c("HiH_HiSyncMltHlth", "downloadOneTypeDataByVersion type is ", Integer.valueOf(intValue), " maxVersion is ", Long.valueOf(longValue));
        if (longValue <= 0) {
            ReleaseLogUtil.d("HiH_HiSyncMltHlth", "downloadOneTypeDataByVersion cloud has no such data ,type is ", Integer.valueOf(intValue));
            return;
        }
        GetHealthDataByVersionReq getHealthDataByVersionReq = new GetHealthDataByVersionReq();
        getHealthDataByVersionReq.setDataType(Integer.valueOf(this.j.getSyncMethod()));
        getHealthDataByVersionReq.setType(Integer.valueOf(intValue));
        if (this.j.getSyncDataArea() == 1) {
            long a2 = iwe.a(this.l, intValue, this.k, 0L);
            if (a2 < longValue) {
                getHealthDataByVersionReq.setVersion(a2);
                e(getHealthDataByVersionReq, longValue);
                return;
            } else {
                ReleaseLogUtil.e("HiH_HiSyncMltHlth", "do not need downloadOneTypeDataByVersion data,type is ", Integer.valueOf(intValue), " DBversion is ", Long.valueOf(a2), " maxVersion is ", Long.valueOf(longValue));
                return;
            }
        }
        igq c = this.n.c(this.k, 0L, intValue);
        if (c == null) {
            getHealthDataByVersionReq.setVersion(0L);
            e(getHealthDataByVersionReq, longValue);
        } else if (c.a() < longValue) {
            getHealthDataByVersionReq.setVersion(c.a());
            e(getHealthDataByVersionReq, longValue);
        } else {
            ReleaseLogUtil.e("HiH_HiSyncMltHlth", "do not need downloadOneTypeDataByVersion data,type is ", Integer.valueOf(intValue), " DBversion is ", Long.valueOf(c.a()), " maxVersion is ", Long.valueOf(longValue));
        }
    }

    private void e(GetHealthDataByVersionReq getHealthDataByVersionReq, long j) throws iut {
        long c;
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "downloadOneTypeDataWithMaxVersion rep = ", getHealthDataByVersionReq, " maxVersion = ", Long.valueOf(j));
        int i = 0;
        do {
            c = c(getHealthDataByVersionReq);
            LogUtil.c("HiH_HiSyncMltHlth", "downloadOneTypeDataWithMaxVersion downCurrentVersion = ", Long.valueOf(c), " maxVersion = ", Long.valueOf(j));
            i++;
            if (c <= -1) {
                return;
            }
            if (this.j.getSyncDataArea() == 1) {
                iwe.c(this.l, getHealthDataByVersionReq.getType().intValue(), c, this.k);
            } else if (!this.n.d(this.k, getHealthDataByVersionReq.getType().intValue(), c, 0L)) {
                LogUtil.h("HiH_HiSyncMltHlth", "downloadOneTypeDataWithMaxVersion saveVersionToDB failed ");
                return;
            }
            getHealthDataByVersionReq.setVersion(c);
            if (i >= 20) {
                LogUtil.h("HiH_HiSyncMltHlth", "downloadOneTypeDataWithMaxVersion pullDataByVersion too many times.");
                return;
            }
        } while (c < j);
    }

    private long c(GetHealthDataByVersionReq getHealthDataByVersionReq) throws iut {
        GetHealthDataByVersionRsp a2 = this.i.a(getHealthDataByVersionReq);
        if (!ius.a(a2, false)) {
            return -1L;
        }
        long currentVersion = a2.getCurrentVersion();
        if (currentVersion <= getHealthDataByVersionReq.getVersion()) {
            ReleaseLogUtil.d("HiH_HiSyncMltHlth", "downOneTypeDataOnce downloadVersion ", Long.valueOf(currentVersion), " smaller than currentVersion ", Long.valueOf(getHealthDataByVersionReq.getVersion()));
            return -1L;
        }
        List<HealthDetail> detailInfos = a2.getDetailInfos();
        if (detailInfos == null || detailInfos.isEmpty()) {
            ReleaseLogUtil.d("HiH_HiSyncMltHlth", "downOneTypeDataOnce detailInfos is null or empty");
            return currentVersion;
        }
        if (!b(detailInfos, getHealthDataByVersionReq.getType().intValue(), false)) {
            return -1L;
        }
        ivg.c().b(getHealthDataByVersionReq.getType().intValue(), new ikv(this.l.getPackageName()));
        return currentVersion;
    }

    private boolean b(List<HealthDetail> list, int i, boolean z) throws iut {
        List<HiHealthData> c;
        ArrayList arrayList = new ArrayList(10);
        Collections.sort(list, iuu.e());
        ArrayList arrayList2 = new ArrayList();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            HealthDetail healthDetail = list.get(i2);
            if (healthDetail != null && (c = this.c.c(healthDetail, this.k)) != null && !c.isEmpty()) {
                if (iuz.f() && !z && h(i) && i2 != size - 1) {
                    itc.d(this.l, c, 10001, this.k);
                } else {
                    if (z || ism.e()) {
                        Iterator<HiHealthData> it = c.iterator();
                        while (it.hasNext()) {
                            it.next().setMergedStatus(999);
                        }
                    }
                    arrayList2.addAll(c);
                    if (e(i)) {
                        arrayList.addAll(c);
                    }
                }
            }
        }
        this.g.saveSyncHealthDetailData(arrayList2, this.k);
        if (arrayList.isEmpty()) {
            return true;
        }
        this.g.prepareRealTimeHealthDataStat(arrayList);
        this.g.doRealTimeHealthDataStat();
        return true;
    }

    private void e(int i, int i2) throws iut {
        while (true) {
            if (this.t < 2) {
                if (this.h && i2 == 12 && this.m >= 4) {
                    LogUtil.h("HiH_HiSyncMltHlth", "oversea exercise intensity detail data upload 1000");
                    break;
                }
                List<HiHealthData> b = b(i, i2);
                if (HiCommonUtil.d(b) || !e(b, i, i2, false)) {
                    break;
                }
                if (this.h && i2 == 12) {
                    this.m++;
                    iuz.c(100);
                }
            } else {
                break;
            }
        }
        this.t = 0;
    }

    private List<HiHealthData> b(int i, int i2) {
        if (i2 == 7) {
            return c(i);
        }
        if (i2 == 9) {
            return d(i);
        }
        if (i2 == 11) {
            return a(i);
        }
        if (i2 == 12) {
            return b(i);
        }
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "type is invalid!");
        return new ArrayList();
    }

    private List<HiHealthData> d(int i) {
        return this.b.e(i, Arrays.asList(22101, 22102, 22103, 22104, 22105, 22106, 22107), 200);
    }

    private List<HiHealthData> c(int i) {
        return this.d.c(i, Arrays.asList(2002, 2018, 2105), 200);
    }

    private List<HiHealthData> a(int i) {
        return this.e.a(i, Arrays.asList(2034, 2037, 2036, 2035), 50);
    }

    private List<HiHealthData> b(int i) {
        return this.f13604a.e(i, Collections.singletonList(7), this.h ? 250 : 50);
    }

    private void c(List<HiHealthData> list, int i) {
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            e(hiHealthData, i);
            hiHealthData.setUserId(this.k);
        }
        this.g.prepareRealTimeHealthDataStat(list);
        this.g.doRealTimeHealthDataStat();
    }

    private void e(HiHealthData hiHealthData, int i) {
        long dataId = hiHealthData.getDataId();
        if (i == 7) {
            this.d.e(dataId, hiHealthData.getLong("modified_time"), 1);
            return;
        }
        if (i == 9) {
            this.b.d(dataId, hiHealthData.getLong("modified_time"), 1);
            return;
        }
        if (i == 11) {
            this.e.d(dataId, hiHealthData.getLong("modified_time"), 1);
        } else if (i == 12) {
            this.f13604a.a(dataId, hiHealthData.getLong("modified_time"), 1);
        } else {
            ReleaseLogUtil.e("HiH_HiSyncMltHlth", "type is invalid!");
        }
    }

    private void j(int i) throws iut {
        List<Integer> asList = Arrays.asList(22101, 22102, 22103, 22104, 22105, 22106, 22107);
        while (this.t < 2) {
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> e = this.b.e(i, asList, HiDateUtil.l(currentTimeMillis), HiDateUtil.o(currentTimeMillis));
            if (HiCommonUtil.d(e) || !e(e, i, 9, true)) {
                break;
            }
        }
        this.t = 0;
    }

    private void f(int i) throws iut {
        List<Integer> asList = Arrays.asList(2018, 2105, 2002);
        while (this.t < 2) {
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> e = this.d.e(i, asList, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            if (HiCommonUtil.d(e) || !e(e, i, 7, true)) {
                break;
            }
        }
        this.t = 0;
    }

    private void g(int i) throws iut {
        List<Integer> asList = Arrays.asList(2034, 2037, 2036, 2035);
        while (this.t < 2) {
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> e = this.e.e(i, asList, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            if (HiCommonUtil.d(e) || !e(e, i, 11, true)) {
                break;
            }
        }
        this.t = 0;
    }

    private void i(int i) throws iut {
        List<Integer> singletonList = Collections.singletonList(7);
        while (this.t < 2) {
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> c = this.f13604a.c(i, singletonList, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            if (HiCommonUtil.d(c) || !e(c, i, 12, true)) {
                break;
            }
        }
        this.t = 0;
    }

    private boolean e(List<HiHealthData> list, int i, int i2, boolean z) throws iut {
        if (z || !this.h) {
            int i3 = this.q + 1;
            this.q = i3;
            iuz.e(i3, this.j.getSyncManual());
        } else {
            int i4 = this.q + 1;
            this.q = i4;
            if (20 < i4) {
                this.t += 2;
                return false;
            }
        }
        List<HealthDetail> b = this.c.b(list, i, i2);
        if (HiCommonUtil.d(list)) {
            LogUtil.h("HiH_HiSyncMltHlth", "addHealthData healthDetails is null or empty cloudType is ", Integer.valueOf(i2), " clientId is ", Integer.valueOf(i));
            return false;
        }
        AddHealthDataReq addHealthDataReq = new AddHealthDataReq();
        addHealthDataReq.setDetailInfo(b);
        addHealthDataReq.setTimeZone(list.get(0).getTimeZone());
        igq c = this.n.c(this.k, 0L, i2);
        if (c == null) {
            addHealthDataReq.setLocalMaxVersion(0L);
        } else {
            addHealthDataReq.setLocalMaxVersion(Long.valueOf(c.a()));
        }
        while (this.t < 2) {
            AddHealthDataRsp d = this.i.d(addHealthDataReq);
            if (!ius.a(d, false)) {
                this.t++;
            } else {
                iuz.c(500);
                ReleaseLogUtil.e("HiH_HiSyncMltHlth", "Multi addDataToCloud OK ! uploadCount is ", Integer.valueOf(this.q), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
                c(list, i2);
                c(addHealthDataReq, d, i2);
                return true;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "Multi addDataToCloud failed ! uploadCount is ", Integer.valueOf(this.q), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
        return false;
    }

    private void c(AddHealthDataReq addHealthDataReq, AddHealthDataRsp addHealthDataRsp, int i) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "LocalMaxVersion()=", addHealthDataReq.getLocalMaxVersion(), ",rsp=", addHealthDataRsp.getTimestamp(), ",isHasMore()=", Boolean.valueOf(addHealthDataRsp.isHasMore()), ",cloudType=", Integer.valueOf(i));
        if (addHealthDataRsp.isHasMore()) {
            SyncKey syncKey = new SyncKey();
            syncKey.setType(Integer.valueOf(i));
            syncKey.setVersion(addHealthDataRsp.getTimestamp());
            e(syncKey);
            return;
        }
        if (this.n.d(this.k, i, addHealthDataRsp.getTimestamp().longValue(), 0L)) {
            return;
        }
        ReleaseLogUtil.d("HiH_HiSyncMltHlth", "downloadMore saveVersionToDB failed!");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pullDataByVersion() begin !");
        ivc.c(3.0d, "SYNC_DOWNLOAD_MULTIHEALTH_PERCENT_MAX");
        List<SyncKey> a2 = ity.a(this.l).a(this.s);
        this.f = a2;
        LogUtil.a("HiH_HiSyncMltHlth", "pullDataByVersion() syncKeys is ", a2);
        if (HiCommonUtil.d(this.f)) {
            LogUtil.h("HiH_HiSyncMltHlth", "pullDataByVersion() keys is null,stop pullDataByVersion");
        } else {
            h();
        }
        ivc.b(this.l);
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pullDataByVersion() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        ivc.c(3.0d, "SYNC_DOWNLOAD_MULTIHEALTH_PERCENT_MAX");
        List<SyncKey> a2 = ity.a(this.l).a(this.s);
        this.f = a2;
        LogUtil.a("HiH_HiSyncMltHlth", "pullDataByTime syncKeys is ", a2);
        if (HiCommonUtil.d(this.f)) {
            LogUtil.h("HiH_HiSyncMltHlth", "pullDataByTime() keys is null,stop pullDataByVersion");
            return;
        }
        for (SyncKey syncKey : this.f) {
            int intValue = syncKey.getType().intValue();
            long longValue = syncKey.getVersion().longValue();
            LogUtil.c("HiH_HiSyncMltHlth", "pullDataByTime type is ", Integer.valueOf(intValue), " maxVersion is ", Long.valueOf(longValue));
            if (longValue <= 0) {
                LogUtil.h("HiH_HiSyncMltHlth", "pullDataByTime cloud has no such data ,type is ", Integer.valueOf(intValue));
            } else if (iuz.f() && 7 == intValue && o()) {
                c(syncKey);
            } else {
                long t = HiDateUtil.t(j);
                if (intValue == 9) {
                    t = HiDateUtil.l(j);
                }
                b(intValue, t, j2, true);
                ivc.b(this.l, 1.0d, 1.0d, 1.0d);
            }
        }
        ivc.b(this.l);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() begin !");
        if (!ism.m()) {
            ReleaseLogUtil.d("HiH_HiSyncMltHlth", "pushData() healthDataPrivacy switch is closed, push end!");
            return;
        }
        List<Integer> i = iis.d().i(this.k);
        if (HiCommonUtil.d(i)) {
            ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end ! no client");
            return;
        }
        if (this.j.getSyncDataType() == 7) {
            List<Integer> i2 = i();
            if (!HiCommonUtil.d(i2)) {
                LogUtil.a("HiH_HiSyncMltHlth", "heartRateClients list before ", HiJsonUtil.e(i2));
                i2.retainAll(i);
                if (!HiCommonUtil.d(i2)) {
                    LogUtil.a("HiH_HiSyncMltHlth", "heartRateClients list after ", HiJsonUtil.e(i2));
                    if (!this.h) {
                        Iterator<Integer> it = i2.iterator();
                        while (it.hasNext()) {
                            e(it.next().intValue(), 7);
                        }
                    } else {
                        this.q = 0;
                        for (Integer num : i2) {
                            f(num.intValue());
                            e(num.intValue(), 7);
                        }
                    }
                    ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end !");
                    return;
                }
                ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end !");
                return;
            }
            ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end !");
            return;
        }
        if (this.j.getSyncDataType() == 11) {
            d(i);
            return;
        }
        if (!this.h) {
            b(i);
        } else {
            e(i);
        }
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end !");
    }

    private void b(List<Integer> list) throws iut {
        List<Integer> i = i();
        LogUtil.a("HiH_HiSyncMltHlth", "heartRateClientIds list is ", HiJsonUtil.e(i));
        if (!HiCommonUtil.d(i)) {
            i.retainAll(list);
            Iterator<Integer> it = i.iterator();
            while (it.hasNext()) {
                e(it.next().intValue(), 7);
            }
        }
        List<Integer> f = f();
        LogUtil.a("HiH_HiSyncMltHlth", "professionalSleepClients list is ", HiJsonUtil.e(f));
        if (!HiCommonUtil.d(f)) {
            f.retainAll(list);
            Iterator<Integer> it2 = f.iterator();
            while (it2.hasNext()) {
                e(it2.next().intValue(), 9);
            }
        }
        List<Integer> n = n();
        LogUtil.a("HiH_HiSyncMltHlth", "stressClientIds list is ", HiJsonUtil.e(n));
        if (!HiCommonUtil.d(n)) {
            n.retainAll(list);
            Iterator<Integer> it3 = n.iterator();
            while (it3.hasNext()) {
                e(it3.next().intValue(), 11);
            }
        }
        List<Integer> j = j();
        LogUtil.a("HiH_HiSyncMltHlth", "exerciseClientIds list is ", HiJsonUtil.e(j));
        if (HiCommonUtil.d(j)) {
            return;
        }
        j.retainAll(list);
        Iterator<Integer> it4 = j.iterator();
        while (it4.hasNext()) {
            e(it4.next().intValue(), 12);
        }
    }

    private void e(List<Integer> list) throws iut {
        List<Integer> i = i();
        LogUtil.a("HiH_HiSyncMltHlth", "heartRateClientIds list is ", HiJsonUtil.e(i));
        if (!HiCommonUtil.d(i)) {
            i.retainAll(list);
            for (Integer num : i) {
                f(num.intValue());
                e(num.intValue(), 7);
            }
        }
        this.q = 0;
        List<Integer> f = f();
        LogUtil.a("HiH_HiSyncMltHlth", "professionalSleepClients list is ", HiJsonUtil.e(f));
        if (!HiCommonUtil.d(f)) {
            f.retainAll(list);
            for (Integer num2 : f) {
                j(num2.intValue());
                e(num2.intValue(), 9);
            }
        }
        this.q = 0;
        List<Integer> n = n();
        LogUtil.a("HiH_HiSyncMltHlth", "stressClientIds list is ", HiJsonUtil.e(n));
        if (!HiCommonUtil.d(n)) {
            n.retainAll(list);
            for (Integer num3 : n) {
                g(num3.intValue());
                e(num3.intValue(), 11);
            }
        }
        this.q = 0;
        this.m = 0;
        List<Integer> j = j();
        LogUtil.a("HiH_HiSyncMltHlth", "exerciseClientIds list is ", HiJsonUtil.e(j));
        if (HiCommonUtil.d(j)) {
            return;
        }
        j.retainAll(list);
        for (Integer num4 : j) {
            i(num4.intValue());
            if (this.m < 4 && 20000 == this.j.getSyncDataType() && 5 == this.j.getSyncAction()) {
                LogUtil.h("HiH_HiSyncMltHlth", "oversea exercise intensity detail data begin to upload");
                e(num4.intValue(), 12);
            }
        }
    }

    private List<Integer> i() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(2002);
        arrayList.add(2018);
        arrayList.add(2105);
        return this.d.b(arrayList);
    }

    private List<Integer> f() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(22101);
        arrayList.add(22102);
        arrayList.add(22103);
        arrayList.add(22104);
        arrayList.add(22105);
        arrayList.add(22106);
        arrayList.add(22107);
        return this.b.c(arrayList);
    }

    private List<Integer> n() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(2034);
        arrayList.add(2037);
        arrayList.add(2036);
        arrayList.add(2035);
        return this.e.c(arrayList);
    }

    private List<Integer> j() {
        return this.f13604a.d(Collections.singletonList(7));
    }

    public void g() throws iut {
        List<SyncKey> b = iuz.b(this.l, this.j.getSyncMethod(), this.s);
        if (b == null || b.isEmpty()) {
            LogUtil.h("HiH_HiSyncMltHlth", "pullDataByVersion() keys is null,stop pullDataByVersion");
            return;
        }
        for (int i = 0; i < this.s.size(); i++) {
            this.n.d(this.k, b.get(i).getType().intValue(), b.get(i).getVersion().longValue(), 0L);
        }
    }

    public void a(int i, int i2) throws iut {
        b(1, HiDateUtil.a(i), HiDateUtil.a(i2), true);
    }

    public void c() throws iut {
        long t;
        igq e = this.n.e(this.k, 0L, 10016);
        if (e != null) {
            long t2 = HiDateUtil.t(HiDateUtil.a(e.d()));
            b(9, iuz.d(t2, 7), t2, true);
        }
        igq e2 = this.n.e(this.k, 0L, 10015);
        if (e2 == null) {
            t = HiDateUtil.t(this.j.getSyncDay());
        } else {
            t = HiDateUtil.t(HiDateUtil.a(e2.d()));
        }
        long j = t;
        b(3, iuz.d(j, 7), j, true);
    }

    public void e() throws iut {
        igq e = this.n.e(this.k, 0L, 10017);
        if (e != null) {
            long t = HiDateUtil.t(HiDateUtil.a(e.d()));
            b(7, iuz.d(t, 7), t, true);
        }
    }

    public void a() throws iut {
        igq e = this.n.e(this.k, 0L, 10022);
        if (e != null) {
            long t = HiDateUtil.t(HiDateUtil.a(e.d()));
            b(12, iuz.d(t, 7), t, true);
        }
    }

    public void b() throws iut {
        igq e = this.n.e(this.k, 0L, 10020);
        if (e != null) {
            long t = HiDateUtil.t(HiDateUtil.a(e.d()));
            b(11, iuz.d(t, 7), t, true);
        }
    }

    private boolean o() {
        return !ijd.c(this.l).e();
    }

    private void c(SyncKey syncKey) throws iut {
        this.n.d(this.k, syncKey.getType().intValue(), 0L, 0L);
        e(syncKey);
    }

    public boolean d() throws iut {
        igq e;
        List<Integer> b = itn.b(this.l).b(this.k);
        if (HiCommonUtil.d(b) || (e = this.n.e(this.k, 0L, 10015)) == null) {
            return true;
        }
        int d = e.d();
        LogUtil.c("HiH_HiSyncMltHlth", "downloadSevenSleepDetailData days=", b.toString(), ", already download day=", Integer.valueOf(d));
        for (Integer num : b) {
            if (num.intValue() < d) {
                long a2 = HiDateUtil.a(num.intValue());
                c(HiDateUtil.n(a2), HiDateUtil.f(a2));
            }
        }
        return true;
    }

    private void c(long j, long j2) throws iut {
        GetSportDataByTimeRsp b = this.i.b(e(j, j2));
        if (!ius.a(b, false)) {
            LogUtil.h("HiH_HiSyncMltHlth", "downloadSportDataByTimeNoUpdateTime warning");
            return;
        }
        List<SportDetail> detailInfos = b.getDetailInfos();
        if (HiCommonUtil.d(detailInfos)) {
            ReleaseLogUtil.e("HiH_HiSyncMltHlth", "sleepDatas null startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2));
        } else {
            b(detailInfos, true);
            this.g.doRealTimeHealthDataStat();
        }
    }

    public void c(int i, long j) throws iut {
        b(i, iuz.d(j, 1), j - 1, false);
    }

    public void c(long j, int i) throws iut {
        if (i == 22100) {
            b(9, iuz.d(j, 1) + 60000, j, false);
        } else {
            b(3, iuz.d(j, 1) + 60000, j, false);
        }
    }

    private void d(List<Integer> list) throws iut {
        List<Integer> n = n();
        if (HiCommonUtil.d(n)) {
            ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end !");
            return;
        }
        LogUtil.a("HiH_HiSyncMltHlth", "StressClients list before ", HiJsonUtil.e(n));
        n.retainAll(list);
        if (HiCommonUtil.d(n)) {
            ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end !");
            return;
        }
        LogUtil.a("HiH_HiSyncMltHlth", "stressClients list after ", HiJsonUtil.e(n));
        if (!this.h) {
            Iterator<Integer> it = n.iterator();
            while (it.hasNext()) {
                e(it.next().intValue(), 11);
            }
        } else {
            this.q = 0;
            for (Integer num : n) {
                g(num.intValue());
                e(num.intValue(), 11);
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "pushData() end !");
    }

    public void d(int i, int i2, int i3) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncMltHlth", "downloadLatestData enter");
        GetHealthDataLatestReq getHealthDataLatestReq = new GetHealthDataLatestReq();
        getHealthDataLatestReq.setType(i3);
        getHealthDataLatestReq.setDataType(2);
        if (i3 == 9) {
            getHealthDataLatestReq.setTimestamp(HiDateUtil.o(System.currentTimeMillis()) + 1);
        } else {
            getHealthDataLatestReq.setTimestamp(HiDateUtil.g(System.currentTimeMillis()));
        }
        if (i > 0) {
            getHealthDataLatestReq.setDays(i);
        }
        if (i2 > 0) {
            getHealthDataLatestReq.setCounts(i2);
        }
        GetHealthDataLatestRsp c = this.i.c(getHealthDataLatestReq);
        if (!ius.a(c, false)) {
            ReleaseLogUtil.d("HiH_HiSyncMltHlth", "downloadLatestData warning");
            return;
        }
        List<HealthDetail> detailInfos = c.getDetailInfos();
        if (koq.b(detailInfos)) {
            ReleaseLogUtil.d("HiH_HiSyncMltHlth", "downloadLatestData dataList is Empty");
        } else {
            b(detailInfos, i3, true);
            ivg.c().a(ivg.d(i3), "latestDataDownload", new ikv(this.l.getPackageName()));
        }
    }

    public String toString() {
        return "--HiSyncMultiHealth{}";
    }
}
