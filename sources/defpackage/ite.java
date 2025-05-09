package defpackage;

import android.content.Context;
import android.os.SystemClock;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealthservice.db.helper.HiHealthDBHelper;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes7.dex */
public class ite {

    /* renamed from: a, reason: collision with root package name */
    private int f13593a;
    private HealthDataSwitch b;
    private isf c;
    private Context d;
    private jbs e;
    private long f = 0;
    private ity i;

    public ite(Context context, int i) {
        this.d = context;
        this.i = ity.a(context);
        this.e = jbs.a(context);
        this.f13593a = i;
        this.b = new HealthDataSwitch(this.d);
        this.c = isf.a(this.d);
    }

    public void e() throws iut {
        int value = DicDataTypeUtil.DataType.ALTITUDE_TYPE_SET.value();
        List<SyncKey> d = this.i.d(value);
        if (HiCommonUtil.d(d)) {
            ReleaseLogUtil.d("HiH_HiSyncAltitude", "dictionary data not exist in cloud");
            return;
        }
        long longValue = d.get(0).getVersion().longValue();
        if (longValue == 0) {
            return;
        }
        long g = HiDateUtil.g(longValue);
        d(iuz.d(g, 7), g);
        ijr.d().d(this.f13593a, value, longValue, 0L);
    }

    private void d(long j, long j2) throws iut {
        GetHealthDataByTimeReq e = e(DicDataTypeUtil.DataType.ALTITUDE_TYPE_SET.value(), j, j2);
        ReleaseLogUtil.e("HiH_HiSyncAltitude", "pullDataByTime st,", Long.valueOf(j), ",et,", Long.valueOf(j2));
        GetHealthDataByTimeRsp e2 = this.e.e(e);
        if (!ius.a(e2, false)) {
            ReleaseLogUtil.d("HiH_HiSyncAltitude", "pullDataByTime error");
            return;
        }
        List<HealthDetail> detailInfos = e2.getDetailInfos();
        if (detailInfos == null || detailInfos.isEmpty()) {
            ReleaseLogUtil.d("HiH_HiSyncAltitude", "pullDataByTime data error");
        } else {
            if (b(detailInfos, 0L)) {
                return;
            }
            ReleaseLogUtil.d("HiH_HiSyncAltitude", "pullDataByTime save data error");
        }
    }

    public void b() {
        this.f = SystemClock.elapsedRealtime();
        c(new HiAggregateListener() { // from class: ite.3
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                ReleaseLogUtil.e("HiH_HiSyncAltitude", "getLakeLouiseScoreList errorCode:", Integer.valueOf(i));
                if (i == 0 && koq.c(list)) {
                    try {
                        ite.this.b(list);
                    } catch (iut e) {
                        ReleaseLogUtil.c("HiH_HiSyncAltitude", "pullAltitudeData SyncException:", e.getMessage());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list) throws iut {
        int i;
        long j;
        LogUtil.a("HiH_HiSyncAltitude", "divideAndPullData size:", Integer.valueOf(list.size()));
        if (list.get(0) == null) {
            return;
        }
        long startTime = list.get(0).getStartTime();
        long endTime = list.get(0).getEndTime();
        itk itkVar = new itk(this.d, new HiSyncOption(), this.f13593a);
        boolean z = HiHealthDBHelper.e("hihealth_sensitive.db").getWritableDatabase() != null;
        int i2 = 1;
        while (i2 < list.size()) {
            if (endTime - list.get(i2).getStartTime() < 604800000) {
                startTime = list.get(i2).getStartTime();
                if (i2 != list.size() - 1) {
                    j = endTime;
                    i = i2;
                    i2 = i + 1;
                    endTime = j;
                }
            }
            long t = jdl.t(startTime);
            long e = jdl.e(endTime);
            d(t, e);
            if (z) {
                i = i2;
                itkVar.e(16, t, e);
            } else {
                i = i2;
            }
            long endTime2 = list.get(i).getEndTime();
            long startTime2 = list.get(i).getStartTime();
            if (i == list.size() - 1) {
                long t2 = jdl.t(startTime2);
                long e2 = jdl.e(endTime2);
                d(t2, e2);
                if (z) {
                    itkVar.e(16, t2, e2);
                }
            }
            j = endTime2;
            startTime = startTime2;
            i2 = i + 1;
            endTime = j;
        }
        LogUtil.a("HiH_HiSyncAltitude", "divideAndPullData totalTime:", Long.valueOf(SystemClock.elapsedRealtime() - this.f));
    }

    private void c(HiAggregateListener hiAggregateListener) {
        ReleaseLogUtil.e("HiH_HiSyncAltitude", "getLakeLouiseScoreList start");
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(1388509200000L);
        hiAggregateOption.setEndTime(System.currentTimeMillis());
        hiAggregateOption.setConstantsKey(new String[]{DicDataTypeUtil.DataType.LAKELOUISE_SCORE.name()});
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.LAKELOUISE_SCORE.value()});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        HiHealthNativeApi.a(this.d).aggregateHiHealthDataPro(HiDataAggregateProOption.builder().c(hiAggregateOption).c(), hiAggregateListener);
    }

    private GetHealthDataByTimeReq e(int i, long j, long j2) {
        GetHealthDataByTimeReq getHealthDataByTimeReq = new GetHealthDataByTimeReq();
        getHealthDataByTimeReq.setQueryType(2);
        getHealthDataByTimeReq.setDataType(2);
        getHealthDataByTimeReq.setStartTime(Long.valueOf(j));
        getHealthDataByTimeReq.setEndTime(Long.valueOf(j2));
        getHealthDataByTimeReq.setType(Integer.valueOf(i));
        return getHealthDataByTimeReq;
    }

    private boolean b(List<HealthDetail> list, long j) throws iut {
        LogUtil.c("HiH_HiSyncAltitude", " saveData()");
        Collections.sort(list, iuu.e());
        List<HiHealthData> e = this.b.e(list, this.f13593a, j);
        if (!HiCommonUtil.d(e)) {
            ReleaseLogUtil.e("HiH_HiSyncAltitude", "local point data type is ", Integer.valueOf(e.get(0).getType()), ", size is ", Integer.valueOf(e.size()));
            this.c.saveSyncHealthDetailData(e, this.f13593a);
            return true;
        }
        ReleaseLogUtil.d("HiH_HiSyncAltitude", "local data empty");
        return true;
    }
}
