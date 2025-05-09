package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByVersionReq;
import com.huawei.hwcloudmodel.model.unite.AddHealthDataRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class itj implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private HealthDataSwitch f13599a;
    private isf b;
    private ijj c;
    private iiz d;
    private HiSyncOption e;
    private List<SyncKey> f;
    private int g;
    private Context h;
    private boolean i = false;
    private jbs j;
    private double k;
    private List<Integer> l;
    private int m;
    private ijr n;
    private int o;

    private boolean a(int i) {
        return i == 7;
    }

    public itj(Context context, HiSyncOption hiSyncOption, int i) throws iut {
        LogUtil.c("Debug_HiSyncHealthDataByUnite", "HiSyncHealthDataByUnite create");
        this.h = context.getApplicationContext();
        this.e = hiSyncOption;
        this.g = i;
        e();
    }

    private void e() throws iut {
        if (this.h == null) {
            LogUtil.b("Debug_HiSyncHealthDataByUnite", "HiSyncHealthDataByUnite init");
            return;
        }
        this.i = iuz.i();
        this.j = jbs.a(this.h);
        this.n = ijr.d();
        this.c = ijj.a(this.h);
        this.d = iiz.a(this.h);
        this.b = isf.a(this.h);
        this.f13599a = new HealthDataSwitch(this.h);
        this.l = d();
        List<SyncKey> a2 = ity.a(this.h).a(this.l);
        this.f = a2;
        LogUtil.a("Debug_HiSyncHealthDataByUnite", "init syncKeys is ", a2);
    }

    private List<Integer> d() {
        return Arrays.asList(13, 14, 15, 21);
    }

    private void a(int i, long j, long j2) throws iut {
        LogUtil.c("Debug_HiSyncHealthDataByUnite", "downloadEachTypeByTime type is", Integer.valueOf(i), " startTime is ", Long.valueOf(j), " endTime is ", Long.valueOf(j2));
        GetHealthDataByTimeRsp e = this.j.e(c(i, j, j2));
        if (!ius.a(e, false)) {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", "downloadEachTypeByTime error type is ", Integer.valueOf(i));
            return;
        }
        List<HealthDetail> detailInfos = e.getDetailInfos();
        if (detailInfos == null || detailInfos.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", "downloadEachTypeByTime data error type is ", Integer.valueOf(i));
        } else {
            d(detailInfos, i, true);
        }
    }

    private GetHealthDataByTimeReq c(int i, long j, long j2) {
        GetHealthDataByTimeReq getHealthDataByTimeReq = new GetHealthDataByTimeReq();
        getHealthDataByTimeReq.setQueryType(2);
        getHealthDataByTimeReq.setDataType(2);
        getHealthDataByTimeReq.setStartTime(Long.valueOf(j));
        getHealthDataByTimeReq.setEndTime(Long.valueOf(j2));
        getHealthDataByTimeReq.setType(Integer.valueOf(i));
        return getHealthDataByTimeReq;
    }

    private void a() throws iut {
        this.k = 1.0d / this.f.size();
        Iterator<SyncKey> it = this.f.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    private void e(SyncKey syncKey) throws iut {
        if (syncKey == null) {
            return;
        }
        int intValue = syncKey.getType().intValue();
        long longValue = syncKey.getVersion().longValue();
        LogUtil.c("Debug_HiSyncHealthDataByUnite", "downloadOneTypeDataByVersion type is ", Integer.valueOf(intValue), ", maxVersion is ", Long.valueOf(longValue));
        if (longValue <= 0) {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", "downloadOneTypeDataByVersion cloud has no such data, type is ", Integer.valueOf(intValue));
            return;
        }
        GetHealthDataByVersionReq getHealthDataByVersionReq = new GetHealthDataByVersionReq();
        getHealthDataByVersionReq.setDataType(Integer.valueOf(this.e.getSyncMethod()));
        getHealthDataByVersionReq.setType(Integer.valueOf(intValue));
        igq c = this.n.c(this.g, 0L, intValue);
        if (c == null) {
            LogUtil.c("Debug_HiSyncHealthDataByUnite", " syncAnchorTable is null");
            getHealthDataByVersionReq.setVersion(0L);
            a(getHealthDataByVersionReq, longValue);
        } else if (c.a() < longValue) {
            getHealthDataByVersionReq.setVersion(c.a());
            a(getHealthDataByVersionReq, longValue);
        } else {
            LogUtil.a("Debug_HiSyncHealthDataByUnite", " do not need downloadOneTypeDataByVersion data, type is ", Integer.valueOf(intValue), ", DBversion is ", Long.valueOf(c.a()), ", maxVersion is ", Long.valueOf(longValue));
        }
    }

    private void a(GetHealthDataByVersionReq getHealthDataByVersionReq, long j) throws iut {
        long a2;
        ReleaseLogUtil.e("HiH_HiSyncHlthDtByUn", " downloadOneTypeDataWithMaxVersion rep = ", getHealthDataByVersionReq, ", maxVersion = ", Long.valueOf(j));
        int i = 0;
        do {
            a2 = a(getHealthDataByVersionReq);
            LogUtil.c("Debug_HiSyncHealthDataByUnite", " downloadOneTypeDataWithMaxVersion downCurrentVersion is ", Long.valueOf(a2), " maxVersion is ", Long.valueOf(j));
            i++;
            if (a2 <= -1) {
                return;
            }
            if (!this.n.d(this.g, getHealthDataByVersionReq.getType().intValue(), a2, 0L)) {
                LogUtil.h("Debug_HiSyncHealthDataByUnite", "downloadOneTypeDataWithMaxVersion saveVersionToDB failed!");
                return;
            }
            getHealthDataByVersionReq.setVersion(a2);
            if (ism.h() && !iuz.f()) {
                LogUtil.h("HiH_HiSyncHlthDtByUn", " downloadOneTypeDataWithMaxVersion() backgroud is running");
                return;
            } else if (i >= 20) {
                LogUtil.h("Debug_HiSyncHealthDataByUnite", " downloadOneTypeDataWithMaxVersion() pullDataByVersion too many times");
                return;
            }
        } while (a2 < j);
    }

    private long a(GetHealthDataByVersionReq getHealthDataByVersionReq) throws iut {
        GetHealthDataByVersionRsp a2 = this.j.a(getHealthDataByVersionReq);
        if (!ius.a(a2, false)) {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", "downOneTypeDataOnce() SyncError checkCloudRsp");
            return -1L;
        }
        long currentVersion = a2.getCurrentVersion();
        if (currentVersion <= getHealthDataByVersionReq.getVersion()) {
            ReleaseLogUtil.d("Debug_HiSyncHealthDataByUnite", "downOneTypeDataOnce downloadVersion ", Long.valueOf(currentVersion), " smaller than currentVersion ", Long.valueOf(getHealthDataByVersionReq.getVersion()));
            return -1L;
        }
        List<HealthDetail> detailInfos = a2.getDetailInfos();
        if (HiCommonUtil.d(detailInfos)) {
            ReleaseLogUtil.d("HiH_HiSyncHlthDtByUn", " downOneTypeDataOnce() detailInfos is null or empty");
            return currentVersion;
        }
        if (!d(detailInfos, getHealthDataByVersionReq.getType().intValue(), false)) {
            return -1L;
        }
        ivg.c().b(getHealthDataByVersionReq.getType().intValue(), new ikv(this.h.getPackageName()));
        return currentVersion;
    }

    private boolean d(List<HealthDetail> list, int i, boolean z) throws iut {
        List<HiHealthData> c;
        LogUtil.c("Debug_HiSyncHealthDataByUnite", " saveData()");
        double size = list.size();
        ReleaseLogUtil.e("Debug_HiSyncHealthDataByUnite", " saveData() type:", Integer.valueOf(i), ", size:", Double.valueOf(size));
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList();
        Collections.sort(list, iuu.e());
        for (HealthDetail healthDetail : list) {
            if (healthDetail != null && (c = this.f13599a.c(healthDetail, this.g)) != null && !c.isEmpty()) {
                ivc.b(this.h, 1.0d / size, this.k, 15.0d);
                if (iuz.f() && !z && a(i)) {
                    itc.d(this.h, c, 10001, this.g);
                } else {
                    arrayList2.addAll(c);
                    if (i == 7 || i == 3 || i == 9 || i == 13) {
                        arrayList.addAll(c);
                    }
                }
            }
        }
        this.b.saveSyncHealthDetailData(arrayList2, this.g);
        if (arrayList.isEmpty()) {
            return true;
        }
        this.b.prepareRealTimeHealthDataStat(arrayList);
        this.b.doRealTimeHealthDataStat();
        return true;
    }

    private void b(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            LogUtil.c("Debug_HiSyncHealthDataByUnite", " uploadPointsOK() hiHealthDatas is null or empty");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            this.c.e(hiHealthData.getDataId(), hiHealthData.getLong("modified_time"), 1);
            hiHealthData.setUserId(this.g);
        }
        this.b.prepareRealTimeHealthDataStat(list);
        this.b.doRealTimeHealthDataStat();
    }

    private boolean d(List<HiHealthData> list, int i, int i2, boolean z, boolean z2) throws iut {
        if (z || !this.i) {
            int i3 = this.o + 1;
            this.o = i3;
            iuz.e(i3, this.e.getSyncManual());
        } else {
            int i4 = this.o + 1;
            this.o = i4;
            if (5 < i4) {
                this.m += 2;
                return false;
            }
        }
        List<HealthDetail> b = this.f13599a.b(list, i, i2);
        if (HiCommonUtil.d(b)) {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", " addHealthData() healthDetails is null or empty cloudType is ", Integer.valueOf(i2), " clientId is ", Integer.valueOf(i));
            return false;
        }
        AddHealthDataReq addHealthDataReq = new AddHealthDataReq();
        addHealthDataReq.setTimeZone(list.get(0).getTimeZone());
        addHealthDataReq.setDetailInfo(b);
        igq c = this.n.c(this.g, 0L, i2);
        if (c == null) {
            addHealthDataReq.setLocalMaxVersion(0L);
        } else {
            addHealthDataReq.setLocalMaxVersion(Long.valueOf(c.a()));
        }
        while (this.m < 2) {
            AddHealthDataRsp d = this.j.d(addHealthDataReq);
            if (!ius.a(d, false)) {
                this.m++;
            } else {
                ReleaseLogUtil.e("HiH_HiSyncHlthDtByUn", "addDataToCloud OK ! uploadCount is ", Integer.valueOf(this.o), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
                if (z2) {
                    a(list);
                } else {
                    b(list);
                }
                e(addHealthDataReq, d, i2);
                return true;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncHlthDtByUn", "addDataToCloud failed ! uploadCount is ", Integer.valueOf(this.o), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
        return false;
    }

    private void e(AddHealthDataReq addHealthDataReq, AddHealthDataRsp addHealthDataRsp, int i) throws iut {
        ReleaseLogUtil.e("Debug_HiSyncHealthDataByUnite", "LocalMaxVersion()=", addHealthDataReq.getLocalMaxVersion(), ",rsp=", addHealthDataRsp.getTimestamp(), ",isHasMore()=", Boolean.valueOf(addHealthDataRsp.isHasMore()), ",cloudType=", Integer.valueOf(i));
        if (addHealthDataRsp.isHasMore()) {
            SyncKey syncKey = new SyncKey();
            syncKey.setType(Integer.valueOf(i));
            syncKey.setVersion(addHealthDataRsp.getTimestamp());
            e(syncKey);
            return;
        }
        if (this.n.d(this.g, i, addHealthDataRsp.getTimestamp().longValue(), 0L)) {
            return;
        }
        ReleaseLogUtil.d("HiH_HiSyncHlthDtByUn", "downloadMore saveVersionToDB failed!");
    }

    private void b(int i) throws iut {
        List<HiHealthData> e;
        while (this.m < 2 && (e = e(i)) != null && !e.isEmpty() && d(e, i, 15, false, true)) {
        }
        this.m = 0;
    }

    private void a(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = 0;
        for (HiHealthData hiHealthData : list) {
            long dataId = hiHealthData.getDataId();
            long j = hiHealthData.getLong("modified_time");
            if (this.d.d(dataId, j)) {
                i = this.d.a(dataId, j);
            }
            LogUtil.a("Debug_HiSyncHealthDataByUnite", "uploadEcgDone sequenceID is ", Long.valueOf(dataId), " Time is ", HiDateUtil.m(hiHealthData.getCreateTime()), " modifiedTime is ", HiDateUtil.m(j), " update ans is ", Integer.valueOf(i));
        }
    }

    private List<HiHealthData> e(int i) {
        return this.d.d(i, 31001, 0, 10);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncHlthDtByUn", "pullDataByVersion() begin !");
        ivc.c(15.0d, "SYNC_HEALTH_DATA_DOWNLOAD_PERCENT_MAX_ALL");
        LogUtil.c("Debug_HiSyncHealthDataByUnite", "pullDataByVersion() keys is ", this.f);
        if (HiCommonUtil.d(this.f)) {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", "pullDataByVersion() keys is null,stop pullDataByVersion");
        } else {
            a();
        }
        ivc.b(this.h);
        ReleaseLogUtil.e("HiH_HiSyncHlthDtByUn", "pullDataByVersion() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
        List<SyncKey> list = this.f;
        if (list == null || list.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", "pullDataByTime() keys is null,stop pullDataByVersion");
            return;
        }
        for (SyncKey syncKey : this.f) {
            int intValue = syncKey.getType().intValue();
            long longValue = syncKey.getVersion().longValue();
            LogUtil.c("Debug_HiSyncHealthDataByUnite", "pullDataByTime type is ", Integer.valueOf(intValue), " maxVersion is ", Long.valueOf(longValue));
            if (longValue <= 0) {
                LogUtil.h("Debug_HiSyncHealthDataByUnite", "pullDataByTime cloud has no such data ,type is ", Integer.valueOf(intValue));
            } else if (intValue == 7) {
                this.k = 1.0d;
                a(intValue, j, j2);
            }
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        int i;
        ReleaseLogUtil.e("HiH_HiSyncHlthDtByUn", "pushData() begin !");
        if (!ism.m()) {
            ReleaseLogUtil.d("HiH_HiSyncHlthDtByUn", "pushData() dataPrivacy switch is closed, push end !");
            return;
        }
        ivc.c(2.0d, "SYNC_HEALTH_DATA_UPLOAD_PERCENT_MAX");
        List<Integer> i2 = iis.d().i(this.g);
        if (i2 != null && !i2.isEmpty()) {
            LogUtil.a("HiH_HiSyncHlthDtByUn", "clientid list size =", Integer.valueOf(i2.size()));
            i = i2.size();
            d(i2);
        } else {
            LogUtil.h("Debug_HiSyncHealthDataByUnite", "pushData() end ! no client get, maybe no data need to pushData");
            i = 0;
        }
        if (i != 0) {
            ivc.b(this.h, 1.0d, 1.0d / i, 2.0d);
        } else {
            LogUtil.b("HiH_HiSyncHlthDtByUn", "pushData() divide by zero !");
        }
        ivc.b(this.h);
        ReleaseLogUtil.e("HiH_HiSyncHlthDtByUn", "pushData() end !");
    }

    private void d(List<Integer> list) throws iut {
        if (!this.i) {
            c(list);
            return;
        }
        f(list);
        this.o = 0;
        e(list);
    }

    private void f(List<Integer> list) throws iut {
        List<Integer> b = this.c.b(Collections.singletonList(2109));
        if (HiCommonUtil.d(b)) {
            return;
        }
        b.retainAll(list);
        Iterator<Integer> it = b.iterator();
        while (it.hasNext()) {
            c(2109, 21, it.next().intValue());
        }
    }

    private void c(int i, int i2, int i3) throws iut {
        while (this.m < 2) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(i));
            long currentTimeMillis = System.currentTimeMillis();
            List<HiHealthData> e = this.c.e(i3, arrayList, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
            if (e == null || e.isEmpty() || !d(e, i3, i2, true, false)) {
                break;
            }
        }
        this.m = 0;
    }

    private void a(int i, int i2, int i3) throws iut {
        while (this.m < 2) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(i));
            List<HiHealthData> c = this.c.c(i3, arrayList, 50);
            if (c == null || c.isEmpty() || !d(c, i3, i2, false, false)) {
                break;
            }
        }
        this.m = 0;
    }

    private void c(List<Integer> list) throws iut {
        List<Integer> e = this.d.e(Collections.singletonList(31001));
        if (!HiCommonUtil.d(e)) {
            e.retainAll(list);
            Iterator<Integer> it = e.iterator();
            while (it.hasNext()) {
                b(it.next().intValue());
            }
        }
        e(list);
    }

    private void e(List<Integer> list) throws iut {
        List<Integer> b = this.c.b("client_id", 0);
        if (HiCommonUtil.d(b)) {
            return;
        }
        list.retainAll(b);
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (Integer num : list) {
            a(2101, 13, num.intValue());
            a(2102, 14, num.intValue());
            a(2109, 21, num.intValue());
        }
    }

    public String toString() {
        return "--HiSyncHealthDataByUnite{}";
    }
}
