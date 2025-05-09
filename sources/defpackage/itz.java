package defpackage;

import android.content.Context;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealthservice.store.merge.HiDataSequenceMerge;
import com.huawei.hihealthservice.sync.dataswitch.MotionPathDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.model.BaseMetaData;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetMotionPathByVersionReq;
import com.huawei.hwcloudmodel.model.unite.GetMotionPathByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.MotionPathDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class itz implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private long f13611a;
    private isf b;
    private HiSyncOption c;
    private int d;
    private jbs e;
    private long f;
    private MotionPathDataSwitch g;
    private Context h;
    private int i;
    private List<SyncKey> j;
    private ijr o;

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    public itz(Context context, HiSyncOption hiSyncOption, int i) {
        LogUtil.a("HiH_HiSyncTrack", "HiSyncTrack create");
        this.h = context.getApplicationContext();
        this.c = hiSyncOption;
        this.i = i;
        this.d = hiSyncOption.getSyncModel();
        c();
    }

    private void c() {
        this.e = jbs.a(this.h);
        this.g = new MotionPathDataSwitch(this.h);
        this.o = ijr.d();
        this.b = isf.a(this.h);
    }

    private GetMotionPathByVersionRsp a(GetMotionPathByVersionReq getMotionPathByVersionReq) {
        return this.e.a(getMotionPathByVersionReq);
    }

    private void b() throws iut {
        a(this.j.get(0));
    }

    protected void a(SyncKey syncKey) throws iut {
        LogUtil.c("HiH_HiSyncTrack", "downloadOneByVersion key = ", syncKey);
        if (syncKey == null || syncKey.getType().intValue() != 2) {
            LogUtil.h("HiH_HiSyncTrack", "downloadOneByVersion the key is not right");
            return;
        }
        long longValue = syncKey.getDeviceCode().longValue();
        long longValue2 = syncKey.getVersion().longValue();
        if (longValue2 <= 0) {
            ReleaseLogUtil.d("HiH_HiSyncTrack", "downloadOneByVersion the maxVersion is not right , maxVersion is ", Long.valueOf(longValue2));
            return;
        }
        igq c = this.o.c(this.i, longValue, this.c.getSyncDataType());
        GetMotionPathByVersionReq getMotionPathByVersionReq = new GetMotionPathByVersionReq();
        getMotionPathByVersionReq.setDataType(Integer.valueOf(this.c.getSyncMethod()));
        getMotionPathByVersionReq.setDeviceCode(Long.valueOf(longValue));
        getMotionPathByVersionReq.setCondition("all");
        if (c == null) {
            getMotionPathByVersionReq.setVersion(0);
            e(getMotionPathByVersionReq, longValue2);
        } else if (c.a() < longValue2) {
            getMotionPathByVersionReq.setVersion(Long.valueOf(c.a()));
            e(getMotionPathByVersionReq, longValue2);
        } else {
            LogUtil.a("HiH_HiSyncTrack", "do not need pullDataByVersion data, DBversion is ", Long.valueOf(c.a()));
        }
    }

    private void e(GetMotionPathByVersionReq getMotionPathByVersionReq, long j) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncTrack", "performDownloadByVersion req = ", getMotionPathByVersionReq, " maxVersion = ", Long.valueOf(j));
        long longValue = getMotionPathByVersionReq.getVersion().longValue();
        this.f = longValue;
        if (longValue <= 0) {
            this.f = 0L;
        }
        this.f13611a = this.f;
        int i = 0;
        while (true) {
            long e = e(getMotionPathByVersionReq);
            ReleaseLogUtil.e("HiH_HiSyncTrack", "performDownloadByVersion downCurrentVersion = ", Long.valueOf(e), " maxVersion = ", Long.valueOf(j));
            i++;
            if (e <= 0) {
                return;
            }
            if (!this.o.d(this.i, this.c.getSyncDataType(), e, getMotionPathByVersionReq.getDeviceCode().longValue())) {
                ReleaseLogUtil.d("HiH_HiSyncTrack", "performDownloadByVersion saveVersionToDB failed ");
            }
            getMotionPathByVersionReq.setVersion(Long.valueOf(e));
            if (i >= 20) {
                LogUtil.h("HiH_HiSyncTrack", "performDownloadByVersion pullDataByVersion too many times.");
            }
            ivc.b(this.h, 1.0d, 1.0d, 1.0d);
            if (this.d != 3 && e >= j) {
                return;
            }
        }
    }

    private long e(GetMotionPathByVersionReq getMotionPathByVersionReq) throws iut {
        long longValue;
        ReleaseLogUtil.e("HiH_HiSyncTrack", "performDownloadByVersion req = ", getMotionPathByVersionReq);
        GetMotionPathByVersionRsp a2 = a(getMotionPathByVersionReq);
        if (!ius.a(a2, false)) {
            return -1L;
        }
        List<BaseMetaData> deleteInfos = a2.getDeleteInfos();
        if (!HiCommonUtil.d(deleteInfos) && !iuz.f()) {
            d(deleteInfos);
        }
        List<MotionPathDetail> detailInfos = a2.getDetailInfos();
        if (detailInfos == null || detailInfos.isEmpty()) {
            LogUtil.h("HiH_HiSyncTrack", "downloadTrack cloudTracks is null or empty");
            return a2.getCurrentVersion().longValue();
        }
        if (a2.getCurrentVersion() == null) {
            longValue = Long.MIN_VALUE;
            long j = LocationRequestCompat.PASSIVE_INTERVAL;
            for (MotionPathDetail motionPathDetail : detailInfos) {
                if (motionPathDetail != null) {
                    long longValue2 = motionPathDetail.getVersion().longValue();
                    if (longValue2 >= longValue) {
                        longValue = longValue2;
                    }
                    if (longValue2 < j) {
                        j = longValue2;
                    }
                }
            }
            LogUtil.a("HiH_HiSyncTrack", "downloadTrack maxTempVersion is ", Long.valueOf(longValue), " minTempVersion is ", Long.valueOf(j));
        } else {
            longValue = a2.getCurrentVersion().longValue();
        }
        if (longValue <= this.f13611a) {
            ReleaseLogUtil.d("HiH_HiSyncTrack", "downloadTrack downloadVersion is ", Long.valueOf(longValue), " smaller than currentVersion ", Long.valueOf(this.f13611a));
            return -1L;
        }
        this.f13611a = longValue;
        List<HiHealthData> d = this.g.d(detailInfos, this.i, this.d);
        ReleaseLogUtil.e("HiH_HiSyncTrack", "saveData cloudTracks is ", Integer.valueOf(detailInfos.size()), ", saveTracks is ", Integer.valueOf(d.size()));
        c(d, true);
        return longValue;
    }

    private void d(List<BaseMetaData> list) {
        boolean z;
        ArrayList arrayList = new ArrayList(10);
        iiz a2 = iiz.a(this.h);
        for (BaseMetaData baseMetaData : list) {
            ReleaseLogUtil.d("HiH_HiSyncTrack", "deleteSequences ", baseMetaData.toString());
            List<Integer> b = iis.d().b(baseMetaData.getDeviceCode());
            if (HiCommonUtil.d(b)) {
                ReleaseLogUtil.d("HiH_HiSyncTrack", "deleteSequence clientId error");
            } else {
                List<HiHealthData> a3 = a2.a(baseMetaData.getStartTime(), baseMetaData.getEndTime(), new int[]{30001}, b, 0);
                if (a3 == null || a3.isEmpty()) {
                    ReleaseLogUtil.d("HiH_HiSyncTrack", "deleteSequence not found");
                } else {
                    boolean z2 = false;
                    HiHealthData hiHealthData = a3.get(0);
                    try {
                        z = !ivu.i(this.h, 30001) ? ivu.e(this.h, 30001) : false;
                        try {
                            try {
                                int e = a2.e(hiHealthData);
                                if (e <= 0) {
                                    ReleaseLogUtil.d("HiH_HiSyncTrack", "deleteSequences() fail");
                                } else {
                                    hiHealthData.setUserId(this.i);
                                    hiHealthData.setSyncStatus(2);
                                    arrayList.add(hiHealthData);
                                }
                                if (z && e > 0) {
                                    ivu.j(this.h, 30001);
                                }
                            } catch (Throwable th) {
                                th = th;
                                z2 = z;
                                if (z2) {
                                    ivu.c(this.h, 30001);
                                }
                                throw th;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            ReleaseLogUtil.c("HiH_HiSyncTrack", "deleteSequences Exception ", LogAnonymous.b((Throwable) e));
                            if (z) {
                                ivu.c(this.h, 30001);
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        z = false;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    if (z) {
                        ivu.c(this.h, 30001);
                    }
                }
            }
        }
        c(arrayList, iis.d().e(this.i));
        b(arrayList);
    }

    private void c(List<HiHealthData> list, List<Integer> list2) {
        HiDataSequenceMerge hiDataSequenceMerge = new HiDataSequenceMerge(this.h);
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            hiDataSequenceMerge.merge(hiHealthData, hiHealthData.getClientId(), list2);
        }
    }

    private void b(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            return;
        }
        isf a2 = isf.a(this.h);
        a2.prepareRealTimeHealthDataStat(list);
        a2.doRealTimeHealthDataStat();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(java.util.List<com.huawei.hihealth.HiHealthData> r18, boolean r19) throws defpackage.iut {
        /*
            Method dump skipped, instructions count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.itz.c(java.util.List, boolean):void");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncTrack", "downLoad() begin !");
        ivc.c(30.0d, "SYNC_TRACK_DOWNLOAD_PERCENT_MAX_ALL");
        int i = this.d;
        if (i == 3) {
            LogUtil.a("HiH_HiSyncTrack", "downLoad 3.0 model");
            this.j = iuz.a(this.h, this.c.getSyncMethod(), this.c.getSyncDataType());
        } else if (i == 2) {
            LogUtil.a("HiH_HiSyncTrack", "downLoad 2.0 model");
            this.j = ity.a(this.h).d(this.c.getSyncDataType());
        } else {
            LogUtil.a("HiH_HiSyncTrack", "downLoad else");
        }
        ReleaseLogUtil.e("HiH_HiSyncTrack", "pullDataByVersion() end ! syncKeys is ", this.j);
        if (HiCommonUtil.d(this.j)) {
            LogUtil.h("HiH_HiSyncTrack", "pullDataByVersion() end ! versions is null,stop pullDataByVersion");
        } else {
            b();
        }
        ivc.b(this.h);
        HiBroadcastUtil.c(this.h, 2);
        ivg.c().a(4, "HiSyncTrack", new ikv(this.h.getPackageName()));
        ReleaseLogUtil.e("HiH_HiSyncTrack", "pullDataByVersion() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        itw.b(this.h).d(this.i, this.c, this.d, this.g, this);
    }

    public String toString() {
        return "--HiSyncTrack{}";
    }
}
