package defpackage;

import android.content.Context;
import android.util.SparseArray;
import androidx.core.location.LocationRequestCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealthservice.sync.dataswitch.SportDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddSportDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSleepSportsDataLatestReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSleepSportsDataLatestRsp;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByVersionReq;
import com.huawei.hwcloudmodel.model.unite.GetSportDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.GetSportDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.SportDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ivx;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class itl implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private ijc f13601a;
    private double b;
    private iis c;
    private iiy d;
    private long e;
    private ijn f;
    private SparseArray<Integer> g;
    private HiSyncOption h;
    private int i;
    private isf j;
    private ijt k;
    private Context l;
    private int n;
    private jbs o;
    private double p;
    private long q;
    private HiDataReadOption r;
    private SportDataSwitch s;
    private double t;
    private ijr w;
    private List<SyncKey> x;
    private int v = 0;
    private int u = 0;
    private boolean m = false;

    public itl(Context context, HiSyncOption hiSyncOption, int i) {
        LogUtil.a("HiH_HiSyncSport", "HiSyncSport create");
        this.l = context.getApplicationContext();
        this.h = hiSyncOption;
        this.n = i;
        this.i = hiSyncOption.getSyncModel();
        c();
    }

    private void c() {
        this.m = iuz.i();
        this.o = jbs.a(this.l);
        this.s = new SportDataSwitch(this.l);
        this.k = ijt.b();
        this.c = iis.d();
        this.w = ijr.d();
        this.f13601a = ijc.d(this.l);
        this.d = iiy.e(this.l);
        this.f = ijn.a(this.l);
        this.j = isf.a(this.l);
        this.r = f();
    }

    private GetSportDataByVersionRsp d(GetSportDataByVersionReq getSportDataByVersionReq) {
        return this.o.c(getSportDataByVersionReq);
    }

    private void b() throws iut {
        LogUtil.a("HiH_HiSyncSport", "downloadByVersion");
        Iterator<SyncKey> it = this.x.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    private void e(SyncKey syncKey) throws iut {
        if (syncKey == null || syncKey.getType().intValue() != 1) {
            LogUtil.h("HiH_HiSyncSport", "downloadByKey the key is not right");
            return;
        }
        long longValue = syncKey.getDeviceCode().longValue();
        long longValue2 = syncKey.getVersion().longValue();
        if (longValue2 <= 0) {
            LogUtil.h("HiH_HiSyncSport", "downloadByKey the maxVersion is not right , wrong key is ", syncKey);
            return;
        }
        GetSportDataByVersionReq getSportDataByVersionReq = new GetSportDataByVersionReq();
        getSportDataByVersionReq.setDataType(Integer.valueOf(this.h.getSyncMethod()));
        getSportDataByVersionReq.setDeviceCode(Long.valueOf(longValue));
        if (this.h.getSyncDataArea() == 1) {
            long a2 = iwe.a(this.l, 1, this.n, 0L);
            if (a2 < longValue2) {
                getSportDataByVersionReq.setVersion(Long.valueOf(a2));
                e(getSportDataByVersionReq, longValue2);
                return;
            } else {
                LogUtil.a("HiH_HiSyncSport", "do not need download data,DB version is ", Long.valueOf(a2), ", max version is ", Long.valueOf(longValue2));
                return;
            }
        }
        igq c = this.w.c(this.n, longValue, 1);
        if (c == null) {
            getSportDataByVersionReq.setVersion(0);
            e(getSportDataByVersionReq, longValue2);
        } else if (c.a() < longValue2) {
            getSportDataByVersionReq.setVersion(Long.valueOf(c.a()));
            e(getSportDataByVersionReq, longValue2);
        } else {
            LogUtil.a("HiH_HiSyncSport", "do not need download data,DB version is ", Long.valueOf(c.a()), ", max version is ", Long.valueOf(longValue2));
        }
    }

    private void a(long j, long j2, boolean z) throws iut {
        LogUtil.a("HiH_HiSyncSport", "performDownloadByTime startTime is ", Long.valueOf(j), " endTime is ", Long.valueOf(j2));
        SparseArray<Integer> bCF_ = iuz.bCF_(j, j2, 9);
        this.g = bCF_;
        if (bCF_ == null) {
            LogUtil.h("HiH_HiSyncSport", "performDownloadByTime downloadDaysMap is null");
            return;
        }
        int size = bCF_.size();
        if (size <= 0) {
            LogUtil.h("HiH_HiSyncSport", "performDownloadByTime downloadDaysMap size is wrong, size is ", Integer.valueOf(size));
            return;
        }
        LogUtil.a("HiH_HiSyncSport", "performDownloadByTime downloadDaysMap is ", this.g, ", and size = ", Integer.valueOf(size));
        this.p = 1.0d / size;
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            int keyAt = this.g.keyAt(size);
            e(c(keyAt, this.g.get(keyAt).intValue(), z));
        }
        if (iuz.f()) {
            for (int i = 0; i < 3; i++) {
                int c = HiDateUtil.c(j);
                j = iuz.d(j, 8);
                e(a(HiDateUtil.c(j), c, z));
            }
        }
    }

    private Map<String, List<SportDetail>> a(int i, int i2, boolean z) throws iut {
        LogUtil.a("HiH_HiSyncSport", "downloadSleepByTime startDay is ", Integer.valueOf(i), " endDay is ", Integer.valueOf(i2));
        if (i > i2) {
            return null;
        }
        if (z) {
            this.w.a(this.n, 10015, i2, 0L);
        }
        GetSportDataByTimeReq getSportDataByTimeReq = new GetSportDataByTimeReq();
        getSportDataByTimeReq.setQueryType(1);
        getSportDataByTimeReq.setDataType(Integer.valueOf(this.h.getSyncMethod()));
        getSportDataByTimeReq.setStartTime(Long.valueOf(i));
        getSportDataByTimeReq.setEndTime(Long.valueOf(i2));
        HashSet hashSet = new HashSet();
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        getSportDataByTimeReq.setSportTypes(hashSet);
        GetSportDataByTimeRsp b = this.o.b(getSportDataByTimeReq);
        if (!ius.a(b, false)) {
            LogUtil.h("HiH_HiSyncSport", "downloadSleepByTime warning");
            return null;
        }
        if (z) {
            this.w.a(this.n, 10015, i, 0L);
        }
        return b.getData();
    }

    private Map<String, List<SportDetail>> c(int i, int i2, boolean z) throws iut {
        LogUtil.a("HiH_HiSyncSport", "downloadOneByTime startDay is ", Integer.valueOf(i), " endDay is ", Integer.valueOf(i2));
        if (i > i2) {
            return null;
        }
        if (z) {
            this.w.a(this.n, PrebakedEffectId.RT_CALENDAR_DATE, i2, 0L);
            this.w.a(this.n, 10015, i2, 0L);
        }
        GetSportDataByTimeRsp c = c(i, i2, 1, this.h.getSyncMethod());
        if (!ius.a(c, false)) {
            LogUtil.h("HiH_HiSyncSport", "downloadOneByTime warning");
            return null;
        }
        if (z) {
            this.w.a(this.n, PrebakedEffectId.RT_CALENDAR_DATE, i, 0L);
            this.w.a(this.n, 10015, i, 0L);
        }
        return c.getData();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(java.util.Map<java.lang.String, java.util.List<com.huawei.hwcloudmodel.model.unite.SportDetail>> r7) throws defpackage.iut {
        /*
            r6 = this;
            java.lang.String r0 = "HiH_HiSyncSport"
            if (r7 == 0) goto L6e
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto Lb
            goto L6e
        Lb:
            r1 = 1
            r2 = 0
            android.content.Context r3 = r6.l     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            boolean r3 = defpackage.ivu.i(r3, r2)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            if (r3 != 0) goto L1c
            android.content.Context r3 = r6.l     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            boolean r3 = defpackage.ivu.e(r3, r2)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4b
            goto L1d
        L1c:
            r3 = r2
        L1d:
            boolean r7 = r6.e(r7, r2)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            if (r7 == 0) goto L37
            ivg r7 = defpackage.ivg.c()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            ikv r4 = new ikv     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            android.content.Context r5 = r6.l     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            java.lang.String r5 = r5.getPackageName()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            r4.<init>(r5)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            java.lang.String r5 = "sync download"
            r7.a(r1, r5, r4)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
        L37:
            isf r7 = r6.j     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            r7.doAsyncHealthDataStat()     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            if (r3 == 0) goto L43
            android.content.Context r7 = r6.l     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
            defpackage.ivu.j(r7, r2)     // Catch: java.lang.Exception -> L46 java.lang.Throwable -> L65
        L43:
            if (r3 == 0) goto L64
            goto L5f
        L46:
            r7 = move-exception
            goto L4d
        L48:
            r7 = move-exception
            r3 = r2
            goto L66
        L4b:
            r7 = move-exception
            r3 = r2
        L4d:
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L65
            java.lang.String r5 = "saveDataByTime Exception"
            r4[r2] = r5     // Catch: java.lang.Throwable -> L65
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)     // Catch: java.lang.Throwable -> L65
            r4[r1] = r7     // Catch: java.lang.Throwable -> L65
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r4)     // Catch: java.lang.Throwable -> L65
            if (r3 == 0) goto L64
        L5f:
            android.content.Context r7 = r6.l
            defpackage.ivu.c(r7, r2)
        L64:
            return
        L65:
            r7 = move-exception
        L66:
            if (r3 == 0) goto L6d
            android.content.Context r0 = r6.l
            defpackage.ivu.c(r0, r2)
        L6d:
            throw r7
        L6e:
            java.lang.String r7 = "downloadOneByTime stringListMap is null or empty"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.itl.e(java.util.Map):void");
    }

    private GetSportDataByTimeRsp c(int i, int i2, int i3, int i4) {
        GetSportDataByTimeReq getSportDataByTimeReq = new GetSportDataByTimeReq();
        getSportDataByTimeReq.setQueryType(Integer.valueOf(i3));
        getSportDataByTimeReq.setDataType(Integer.valueOf(i4));
        getSportDataByTimeReq.setStartTime(Long.valueOf(i));
        getSportDataByTimeReq.setEndTime(Long.valueOf(i2));
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(10);
        getSportDataByTimeReq.setSportTypes(hashSet);
        return this.o.b(getSportDataByTimeReq);
    }

    private void e(GetSportDataByVersionReq getSportDataByVersionReq, long j) throws iut {
        LogUtil.a("HiH_HiSyncSport", "performDownloadByVersion GetSportDataByVersionReq = ", getSportDataByVersionReq, " maxVersion = ", Long.valueOf(j));
        long longValue = getSportDataByVersionReq.getVersion().longValue();
        this.q = longValue;
        if (longValue <= 0) {
            this.q = 0L;
        }
        this.e = this.q;
        int i = 0;
        while (true) {
            long a2 = a(getSportDataByVersionReq, j);
            LogUtil.a("HiH_HiSyncSport", "performDownloadByVersion downCurrentVersion = ", Long.valueOf(a2), " maxVersion = ", Long.valueOf(j));
            i++;
            if (a2 <= -1) {
                return;
            }
            if (this.h.getSyncDataArea() == 1) {
                iwe.c(this.l, 1, a2, this.n);
            } else if (!this.w.d(this.n, 1, a2, getSportDataByVersionReq.getDeviceCode().longValue())) {
                LogUtil.h("HiH_HiSyncSport", "performDownloadByVersion saveVersionToDB failed ");
            }
            getSportDataByVersionReq.setVersion(Long.valueOf(a2));
            if (ism.h() && !iuz.f()) {
                LogUtil.h("HiH_HiSyncSport", "performDownloadByVersion backgroud is running");
                return;
            } else if (i >= 20) {
                LogUtil.h("HiH_HiSyncSport", "performDownloadByVersion pullDataByVersion too many times.");
                return;
            } else if (this.i != 3 && a2 >= j) {
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0094, code lost:
    
        if (r12 != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b4, code lost:
    
        defpackage.ivu.c(com.huawei.haf.application.BaseApplication.e(), 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b2, code lost:
    
        if (r12 == false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long a(com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByVersionReq r12, long r13) throws defpackage.iut {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.itl.a(com.huawei.hwcloudmodel.healthdatacloud.model.GetSportDataByVersionReq, long):long");
    }

    private Map<String, List<SportDetail>> d(GetSportDataByVersionRsp getSportDataByVersionRsp) {
        if (this.i == 3) {
            HashMap hashMap = new HashMap(1);
            List<SportDetail> detailInfos = getSportDataByVersionRsp.getDetailInfos();
            if (detailInfos == null || detailInfos.isEmpty()) {
                return null;
            }
            hashMap.put("one", detailInfos);
            return hashMap;
        }
        return getSportDataByVersionRsp.getData();
    }

    private long c(Map<String, List<SportDetail>> map) {
        long j = Long.MIN_VALUE;
        long j2 = LocationRequestCompat.PASSIVE_INTERVAL;
        for (List<SportDetail> list : map.values()) {
            if (list != null && !list.isEmpty()) {
                Iterator<SportDetail> it = list.iterator();
                while (it.hasNext()) {
                    long longValue = it.next().getVersion().longValue();
                    if (longValue >= j) {
                        j = longValue;
                    }
                    if (longValue < j2) {
                        j2 = longValue;
                    }
                }
            }
        }
        return j;
    }

    private boolean e(Map<String, List<SportDetail>> map, final boolean z) throws iut {
        List<HiHealthData> c;
        double size = map.size();
        for (List<SportDetail> list : map.values()) {
            if (ism.h() && !iuz.f()) {
                ivc.b(this.l, 1.0d / size, 1.0d, this.b);
            } else {
                ivc.b(this.l, 1.0d / size, this.p, this.b);
            }
            if (list != null && !list.isEmpty() && (c = this.s.c(list, this.n, this.i)) != null && !c.isEmpty()) {
                if (iuz.f() && !z) {
                    itc.d(this.l, c, 1, this.n);
                } else {
                    ArrayList arrayList = new ArrayList(c.size());
                    final ArrayList arrayList2 = new ArrayList(c.size());
                    e(c, arrayList, arrayList2);
                    Collections.sort(arrayList, new ivx.f());
                    d(z, arrayList);
                    if (!arrayList2.isEmpty()) {
                        ThreadPoolManager.d().execute(new Runnable() { // from class: itp
                            @Override // java.lang.Runnable
                            public final void run() {
                                itl.this.d(z, arrayList2);
                            }
                        });
                    }
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void d(boolean z, List<HiHealthData> list) {
        this.j.saveSyncHealthDetailData(list, this.n);
        if (!z) {
            this.j.prepareAsyncHealthDataStat(list);
        } else {
            this.j.prepareRealTimeHealthDataStat(list);
            this.j.doRealTimeHealthDataStat();
        }
    }

    private void e(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3) {
        if (this.h.getSyncType() == 0) {
            list2.addAll(list);
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (this.h.getSyncType() == hiHealthData.getType()) {
                hiHealthData.setMergedStatus(999);
                list2.add(hiHealthData);
            } else if (HiHealthDataType.e(hiHealthData.getType()) == HiHealthDataType.Category.SESSION) {
                list2.add(hiHealthData);
            } else {
                list3.add(hiHealthData);
            }
        }
        LogUtil.a("HiH_HiSyncSport", "sync size = ", Integer.valueOf(list2.size()), ", async size = ", Integer.valueOf(list3.size()));
    }

    private void e(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (this.f13601a.d(hiHealthData.getDataId(), hiHealthData.getLong("modified_time"), 1) > 0) {
                long startTime = hiHealthData.getStartTime();
                long endTime = hiHealthData.getEndTime();
                int clientId = hiHealthData.getClientId();
                this.d.e(clientId, startTime, endTime, 2, 1);
                this.d.e(clientId, startTime, endTime, 4, 1);
                this.d.e(clientId, startTime, endTime, 3, 1);
                this.d.e(clientId, startTime, endTime, 5, 1);
            }
        }
    }

    private void a(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            this.f.d(hiHealthData.getDataId(), hiHealthData.getLong("modified_time"), 1);
        }
    }

    private boolean c(List<HiHealthData> list, int i, boolean z) throws iut {
        if (z || !this.m) {
            int i2 = this.u + 1;
            this.u = i2;
            iuz.c(i2, this.h.getSyncManual());
        } else {
            int i3 = this.u + 1;
            this.u = i3;
            if (5 < i3) {
                return false;
            }
        }
        List<SportDetail> a2 = this.s.a(list, i, this.i);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("HiH_HiSyncSport", "addDataToCloud sportDetails is null or empty dataType is ", Integer.valueOf(i));
            return false;
        }
        AddSportDataReq addSportDataReq = new AddSportDataReq();
        addSportDataReq.setDetailInfo(a2);
        addSportDataReq.setTimeZone(list.get(0).getTimeZone());
        while (this.v < 2) {
            if (!ius.a(this.o.d(addSportDataReq), false)) {
                this.v++;
            } else {
                ReleaseLogUtil.e("HiH_HiSyncSport", "addDataToCloud OK ! uploadCount is ", Integer.valueOf(this.u), " dataType is ", Integer.valueOf(i));
                LogUtil.a("HiH_", "addDataToCloud OK ! uploadCount is ", Integer.valueOf(this.u), " dataType is ", Integer.valueOf(i));
                return true;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncSport", "addDataToCloud failed ! uploadCount is ", Integer.valueOf(this.u), " dataType is ", Integer.valueOf(i));
        LogUtil.a("HiH_", "addDataToCloud failed ! uploadCount is ", Integer.valueOf(this.u), " dataType is ", Integer.valueOf(i));
        return false;
    }

    private void d(int i) throws iut {
        while (true) {
            if (this.v >= 2) {
                break;
            }
            List<HiHealthData> a2 = a(i);
            if (!HiCommonUtil.d(a2)) {
                LogUtil.a("HiH_HiSyncSport", "getUploadSportData size =", Integer.valueOf(a2.size()));
                if (c(a2, 1, false)) {
                    e(a2);
                    if (a2.size() < 1000) {
                        LogUtil.a("HiH_HiSyncSport", "uploadSportData the size is smaller than HiSyncUtil.UPLOAD_SPORT_DATA_MAX, size is", Integer.valueOf(a2.size()));
                        break;
                    }
                } else {
                    LogUtil.h("HiH_HiSyncSport", "uploadSportData upload failed，clientId is ", Integer.valueOf(i));
                    break;
                }
            } else {
                break;
            }
        }
        this.v = 0;
    }

    private void j(int i) throws iut {
        while (true) {
            if (this.v >= 2) {
                break;
            }
            HiDataReadOption hiDataReadOption = new HiDataReadOption();
            long currentTimeMillis = System.currentTimeMillis();
            hiDataReadOption.setTimeInterval(HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            hiDataReadOption.setConstantsKey(new String[]{"step", "calorie", "distance", "altitude_offset"});
            hiDataReadOption.setType(new int[]{2, 4, 3, 5});
            hiDataReadOption.setAlignType(20001);
            List<HiHealthData> a2 = this.k.a(i, hiDataReadOption);
            if (a2 != null && !a2.isEmpty()) {
                LogUtil.a("HiH_HiSyncSport", "uploadTodaySportData size =", Integer.valueOf(a2.size()));
                if (c(a2, 1, true)) {
                    e(a2);
                    if (a2.size() < 1000) {
                        LogUtil.a("HiH_HiSyncSport", "uploadTodaySportData the size is smaller than HiSyncUtil.UPLOAD_SPORT_DATA_MAX, size is", Integer.valueOf(a2.size()));
                        break;
                    }
                } else {
                    LogUtil.h("HiH_HiSyncSport", "uploadTodaySportData upload failed，clientId is ", Integer.valueOf(i));
                    break;
                }
            } else {
                break;
            }
        }
        this.v = 0;
    }

    private void e(int i) throws iut {
        List<Integer> asList = Arrays.asList(22001, 22002, 22003);
        while (true) {
            if (this.v >= 2) {
                break;
            }
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> a2 = this.f.a(i, asList, HiDateUtil.l(currentTimeMillis), HiDateUtil.o(currentTimeMillis));
            if (a2 != null && !a2.isEmpty()) {
                LogUtil.a("HiH_HiSyncSport", "uploadTodaySleepData size =", Integer.valueOf(a2.size()));
                if (c(a2, 3, true)) {
                    a(a2);
                    if (a2.size() < 1000) {
                        LogUtil.a("HiH_HiSyncSport", "uploadTodaySleepData the size is smaller than HiSyncUtil.UPLOAD_SPORT_DATA_MAX, size is", Integer.valueOf(a2.size()));
                        break;
                    }
                } else {
                    LogUtil.h("HiH_HiSyncSport", "uploadTodaySleepData upload failed，clientId is ", Integer.valueOf(i));
                    break;
                }
            } else {
                break;
            }
        }
        this.v = 0;
    }

    private List<HiHealthData> a(int i) {
        return this.k.b(i, this.r, 1000);
    }

    private void b(int i) throws iut {
        while (true) {
            if (this.v >= 2) {
                break;
            }
            List<HiHealthData> c = c(i);
            if (!HiCommonUtil.d(c)) {
                LogUtil.a("HiH_HiSyncSport", "getUploadSleepData size =", Integer.valueOf(c.size()));
                if (c(c, 3, false)) {
                    a(c);
                    if (c.size() < 1000) {
                        LogUtil.a("HiH_HiSyncSport", "uploadSleepData the size is smaller than HiSyncUtil.UPLOAD_SPORT_DATA_MAX, size is", Integer.valueOf(c.size()));
                        break;
                    }
                } else {
                    LogUtil.h("HiH_HiSyncSport", "uploadSleepData upload failed，clientId is ", Integer.valueOf(i));
                    break;
                }
            } else {
                break;
            }
        }
        this.v = 0;
    }

    private List<HiHealthData> c(int i) {
        return this.f.b(i, Arrays.asList(22001, 22002, 22003), 1000);
    }

    private HiDataReadOption f() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setConstantsKey(new String[]{"step", "calorie", "distance", "altitude_offset"});
        hiDataReadOption.setType(new int[]{2, 4, 3, 5});
        hiDataReadOption.setAlignType(20001);
        return hiDataReadOption;
    }

    private void a() throws iut {
        int i = this.i;
        if (i == 3) {
            LogUtil.a("HiH_HiSyncSport", "pullDataByVersion 3.0 model");
            this.x = iuz.a(this.l, this.h.getSyncMethod(), this.h.getSyncDataType());
        } else if (i == 2) {
            LogUtil.a("HiH_HiSyncSport", "pullDataByVersion 2.0 model");
            this.x = ity.a(this.l).d(this.h.getSyncDataType());
        } else {
            LogUtil.a("HiH_HiSyncSport", "pullDataByVersion else");
        }
        LogUtil.a("HiH_HiSyncSport", "pullDataByVersion() syncKeys is ", this.x);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncSport", "pullDataByVersion() begin !");
        this.b = 2.0d;
        ivc.c(2.0d, "SYNC_SPORT_DOWNLOAD_PERCENT_MAX_ALL");
        a();
        List<SyncKey> list = this.x;
        if (list == null || list.isEmpty()) {
            LogUtil.h("HiH_HiSyncSport", "pullDataByVersion() syncKeys is null,stop pullDataByVersion");
            return;
        }
        b();
        ivc.b(this.l);
        ReleaseLogUtil.e("HiH_HiSyncSport", "pullDataByVersion() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncSport", "pushData() begin !");
        boolean l = ism.l();
        boolean m = ism.m();
        if (!l && !m) {
            ReleaseLogUtil.d("HiH_HiSyncSport", "pushData() sportDataPrivacy switch is closed and healthDataPrivacy switch is closed , push end !");
            return;
        }
        ivc.c(5.0d, "SYNC_SPORT_UPLOAD_PERCENT_MAX");
        List<Integer> i = this.c.i(this.n);
        if (i != null && !i.isEmpty()) {
            LogUtil.a("HiH_HiSyncSport", "clientid list size =", Integer.valueOf(i.size()));
            this.t = 1.0d / i.size();
            if (!this.m) {
                for (Integer num : i) {
                    if (l) {
                        d(num.intValue());
                    }
                    if (m) {
                        b(num.intValue());
                    }
                }
            } else {
                d(i, l, m);
                this.u = 0;
                for (Integer num2 : i) {
                    if (l) {
                        d(num2.intValue());
                    }
                }
                this.u = 0;
                for (Integer num3 : i) {
                    if (m) {
                        b(num3.intValue());
                    }
                    ivc.b(this.l, 1.0d, this.t, 5.0d);
                }
            }
        } else {
            LogUtil.h("HiH_HiSyncSport", "pushData() no client get, maybe no data need to pushData");
        }
        pullDataByVersion();
        ivc.b(this.l);
        ReleaseLogUtil.e("HiH_HiSyncSport", "pushData() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        this.b = 3.0d;
        ivc.c(3.0d, "SYNC_SPORT_DOWNLOAD_PERCENT_BY_TIME");
        a(j, j2, true);
        ivc.b(this.l);
    }

    private void d(List<Integer> list, boolean z, boolean z2) throws iut {
        for (Integer num : list) {
            if (z) {
                j(num.intValue());
            }
            if (z2) {
                e(num.intValue());
            }
        }
    }

    public void d() throws iut {
        a();
        List<SyncKey> list = this.x;
        if (list == null || list.isEmpty()) {
            LogUtil.h("HiH_HiSyncSport", "pullDataByVersion() syncKeys is null,stop pullDataByVersion");
            return;
        }
        this.w.d(this.n, 1, this.x.get(0).getVersion().longValue(), this.x.get(0).getDeviceCode().longValue());
    }

    public void e() throws iut {
        igq e = this.w.e(this.n, 0L, PrebakedEffectId.RT_CALENDAR_DATE);
        if (e != null) {
            long a2 = HiDateUtil.a(e.d());
            a(iuz.d(a2, 7), a2, true);
        }
    }

    public void a(long j) throws iut {
        if (Utils.o()) {
            a(iuz.d(j, 1), j, false);
        } else {
            a(iuz.d(j, 1), iuz.d(j, 1), false);
        }
    }

    public void c(double d) {
        ivc.c(d, "SYNC_SPORT_DOWNLOAD_PERCENT_MAX_ALL");
        ivc.b(this.l);
    }

    public void c(int i, int i2) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncSport", "downloadLatestSleepData enter");
        GetSleepSportsDataLatestReq getSleepSportsDataLatestReq = new GetSleepSportsDataLatestReq();
        getSleepSportsDataLatestReq.setTimestamp(HiDateUtil.o(System.currentTimeMillis()) + 1);
        getSleepSportsDataLatestReq.setDataType(2);
        if (i > 0) {
            getSleepSportsDataLatestReq.setDays(i);
        }
        if (i2 > 0) {
            getSleepSportsDataLatestReq.setCounts(i2);
        }
        GetSleepSportsDataLatestRsp a2 = this.o.a(getSleepSportsDataLatestReq);
        if (!ius.a(a2, false)) {
            ReleaseLogUtil.d("HiH_HiSyncSport", "downloadLatestSleepData warning");
            return;
        }
        List<HiHealthData> c = this.s.c(a2.getDetailInfos(), this.n, this.i);
        if (koq.b(c)) {
            ReleaseLogUtil.d("HiH_HiSyncSport", "downloadLatestSleepData dataList is Empty");
        } else {
            d(true, c);
            ivg.c().a(2, "latestDataDownload", new ikv(this.l.getPackageName()));
        }
    }

    public String toString() {
        return "--HiSyncSport{}";
    }
}
