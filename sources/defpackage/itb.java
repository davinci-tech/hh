package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByVersionReq;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class itb implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private int f13590a;
    isf b;
    private HealthDataSwitch c;
    private long d;
    private double e;
    private HiSyncOption f;
    private int g;
    private Context h;
    private jbs i;
    private double j;
    private long l;
    private ijr m;
    private List<SyncKey> n;

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    public itb(Context context, HiSyncOption hiSyncOption, int i) {
        LogUtil.c("Debug_HiSyncHealthData", "HiSyncHealthData create");
        this.h = context.getApplicationContext();
        this.f = hiSyncOption;
        this.g = i;
        this.f13590a = hiSyncOption.getSyncModel();
        e();
    }

    private void e() {
        this.i = jbs.a(this.h);
        this.c = new HealthDataSwitch(this.h);
        this.m = ijr.d();
        this.b = isf.a(this.h);
    }

    private GetHealthDataByVersionRsp d(GetHealthDataByVersionReq getHealthDataByVersionReq) {
        return this.i.a(getHealthDataByVersionReq);
    }

    private void b() throws iut {
        LogUtil.c("Debug_HiSyncHealthData", "downloadAllData");
        c(this.n.get(0));
    }

    private void c() throws iut {
        this.e = 3.0d;
        GetHealthDataByTimeReq getHealthDataByTimeReq = new GetHealthDataByTimeReq();
        getHealthDataByTimeReq.setQueryType(1);
        getHealthDataByTimeReq.setDataType(Integer.valueOf(this.f.getSyncMethod()));
        long currentTimeMillis = System.currentTimeMillis();
        int b = iuz.b(currentTimeMillis, 7);
        int c = HiDateUtil.c(currentTimeMillis);
        LogUtil.c("Debug_HiSyncHealthData", "downloadDataByTime startDay is ", Integer.valueOf(b), " endDay is ", Integer.valueOf(c));
        getHealthDataByTimeReq.setStartTime(Long.valueOf(b));
        getHealthDataByTimeReq.setEndTime(Long.valueOf(c));
        if (b >= c) {
            return;
        }
        this.j = 1.0d;
        GetHealthDataByTimeRsp e = this.i.e(getHealthDataByTimeReq);
        if (e == null) {
            LogUtil.c("Debug_HiSyncHealthData", "downloadDataByTime pullDataByVersion nothing by time");
            return;
        }
        Map<String, List<HealthDetail>> data = e.getData();
        if (data == null || data.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthData", "downloadDataByTime data is null or empty");
            return;
        }
        ArrayList arrayList = new ArrayList(data.values().size());
        int i = 0;
        for (List<HealthDetail> list : data.values()) {
            if (list != null && !list.isEmpty()) {
                Collections.sort(list, iuu.e());
                arrayList.add(list);
                i += list.size();
            }
        }
        ArrayList arrayList2 = new ArrayList(i);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.addAll((List) it.next());
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        c((List<HealthDetail>) arrayList2, false);
    }

    private void c(SyncKey syncKey) throws iut {
        if (syncKey == null || syncKey.getType().intValue() != 10001) {
            LogUtil.h("Debug_HiSyncHealthData", "downloadOneData the key is not right");
            return;
        }
        LogUtil.c("Debug_HiSyncHealthData", "syncOneDeviceByVersion key = ", syncKey);
        long longValue = syncKey.getDeviceCode().longValue();
        long longValue2 = syncKey.getVersion().longValue();
        if (longValue2 <= 0) {
            LogUtil.h("Debug_HiSyncHealthData", "downloadOneData the maxVersion is not right , maxVersion is ", Long.valueOf(longValue2));
            return;
        }
        igq c = this.m.c(this.g, longValue, this.f.getSyncDataType());
        GetHealthDataByVersionReq getHealthDataByVersionReq = new GetHealthDataByVersionReq();
        getHealthDataByVersionReq.setDataType(Integer.valueOf(this.f.getSyncMethod()));
        getHealthDataByVersionReq.setDeviceCode(Long.valueOf(longValue));
        if (c == null) {
            getHealthDataByVersionReq.setVersion(0L);
            c();
            c(getHealthDataByVersionReq, longValue2);
        } else if (c.a() < longValue2) {
            getHealthDataByVersionReq.setVersion(c.a());
            c(getHealthDataByVersionReq, longValue2);
        } else {
            LogUtil.c("Debug_HiSyncHealthData", "do not need pullDataByVersion data,DBversion is ", Long.valueOf(c.a()));
        }
    }

    private void c(GetHealthDataByVersionReq getHealthDataByVersionReq, long j) throws iut {
        LogUtil.c("Debug_HiSyncHealthData", "downOneDataByVersionAll GetSportDataByVersionReq = ", getHealthDataByVersionReq, " maxVersion = ", Long.valueOf(j));
        long version = getHealthDataByVersionReq.getVersion();
        this.l = version;
        if (version <= 0) {
            this.l = 0L;
        }
        this.d = this.l;
        int i = 0;
        while (true) {
            long b = b(getHealthDataByVersionReq, j);
            LogUtil.c("Debug_HiSyncHealthData", "downOneDataByVersionAll downCurrentVersion = ", Long.valueOf(b), " maxVersion = ", Long.valueOf(j));
            i++;
            if (b <= -1) {
                return;
            }
            if (!this.m.d(this.g, this.f.getSyncDataType(), b, getHealthDataByVersionReq.getDeviceCode().longValue())) {
                LogUtil.h("Debug_HiSyncHealthData", "downOneDataByVersionAll saveVersionToDB failed ");
            }
            getHealthDataByVersionReq.setVersion(b);
            if (i >= 20) {
                LogUtil.h("Debug_HiSyncHealthData", "downOneDataByVersionAll pullDataByVersion too many times.");
                return;
            } else if (this.f13590a != 3 && b >= j) {
                return;
            }
        }
    }

    private long b(GetHealthDataByVersionReq getHealthDataByVersionReq, long j) throws iut {
        GetHealthDataByVersionRsp d = d(getHealthDataByVersionReq);
        if (d == null) {
            LogUtil.h("Debug_HiSyncHealthData", "downOneDataByVersionOnce getSportDataByVersionRsp is null");
            return -1L;
        }
        if (d.getResultCode().intValue() != 0) {
            LogUtil.h("Debug_HiSyncHealthData", "downOneDataByVersionOnce getHealthDataByVersionRsp result code is not 0, result is ", d.getResultCode());
            return -1L;
        }
        List<HealthDetail> detailInfos = d.getDetailInfos();
        if (detailInfos == null || detailInfos.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthData", "downOneDataByVersionOnce healthDetails is null or empty");
            return -1L;
        }
        long version = getHealthDataByVersionReq.getVersion();
        if (version <= this.d) {
            ReleaseLogUtil.d("Debug_HiSyncHealthData", "downOneDataByVersionOnce downloadVersion ", Long.valueOf(version), " smaller than currentVersion ", Long.valueOf(this.d));
            return -1L;
        }
        this.j = (version - r7) / (j - this.l);
        this.d = version;
        c(detailInfos, true);
        return d.getCurrentVersion();
    }

    private void c(List<HealthDetail> list, boolean z) throws iut {
        List<HiHealthData> c;
        int size = list.size();
        if (z) {
            Collections.sort(list, iuu.e());
        }
        boolean z2 = false;
        for (HealthDetail healthDetail : list) {
            ivc.b(this.h, 1.0d / size, this.j, this.e);
            if (healthDetail != null && (c = this.c.c(healthDetail, this.g)) != null && !c.isEmpty() && this.b.saveSyncHealthDetailData(c, this.g) == 0) {
                z2 = true;
            }
        }
        if (z2) {
            ivg.c().a(1, "sync download", new ikv(this.h.getPackageName()));
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        LogUtil.a("Debug_HiSyncHealthData", "pullDataByVersion() begin !");
        ivc.c(18.0d, "SYNC_HEALTH_DATA_DOWNLOAD_PERCENT_MAX_ALL");
        List<SyncKey> a2 = iuz.a(this.h, this.f.getSyncMethod(), this.f.getSyncDataType());
        this.n = a2;
        LogUtil.c("Debug_HiSyncHealthData", "pullDataByVersion() type versions is ", a2);
        List<SyncKey> list = this.n;
        if (list == null || list.isEmpty()) {
            LogUtil.h("Debug_HiSyncHealthData", "pullDataByVersion() end ! type versions is null,stop pullDataByVersion");
            return;
        }
        b();
        ivc.b(this.h);
        LogUtil.a("Debug_HiSyncHealthData", "pullDataByVersion() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() {
        ith.b(this.h).d(this.g, this.c);
    }

    public String toString() {
        return "--HiSyncHealthData{}";
    }
}
