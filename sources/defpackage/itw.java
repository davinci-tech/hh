package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealthservice.sync.dataswitch.MotionPathDataSwitch;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddMotionPathReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteMotionPathReq;
import com.huawei.hwcloudmodel.model.unite.AddMotionPathRsp;
import com.huawei.hwcloudmodel.model.unite.DataDeleteCondition;
import com.huawei.hwcloudmodel.model.unite.MotionPathDetail;
import com.huawei.hwcloudmodel.model.unite.ParamValidDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class itw {
    private static Context e;

    /* renamed from: a, reason: collision with root package name */
    private jbs f13607a;
    private iis b;
    private boolean c;
    private iiz d;
    private itz f;
    private int g;
    private int h;
    private ijr i;
    private int j;

    private itw() {
        this.g = 0;
        this.h = 0;
        this.c = false;
        this.d = iiz.a(e);
        this.b = iis.d();
        this.f13607a = jbs.a(e);
        this.i = ijr.d();
    }

    static class a {
        private static final itw b = new itw();
    }

    public static itw b(Context context) {
        e = context.getApplicationContext();
        return a.b;
    }

    public void d(int i, HiSyncOption hiSyncOption, int i2, MotionPathDataSwitch motionPathDataSwitch, itz itzVar) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncTrack", "pushData() begin !");
        this.j = i;
        this.f = itzVar;
        this.g = 0;
        this.h = 0;
        this.c = iuz.i();
        if (!ism.l()) {
            ReleaseLogUtil.d("HiH_HiSyncTrack", "pushData() dataPrivacy switch is closed, push end !");
            return;
        }
        ivc.c(2.0d, "SYNC_TRACK_UPLOAD_PERCENT_MAX");
        List<Integer> e2 = this.b.e(i);
        if (HiCommonUtil.d(e2)) {
            ivc.b(e);
            ReleaseLogUtil.e("HiH_HiSyncTrack", "pushData() end !, dataClientId is Empty");
            return;
        }
        List<Integer> d = iiz.a(e).d();
        if (HiCommonUtil.d(d)) {
            ivc.b(e);
            ReleaseLogUtil.e("HiH_HiSyncTrack", "pushData() end !, sequence no data to cloud");
            return;
        }
        e2.retainAll(d);
        if (HiCommonUtil.d(e2)) {
            ivc.b(e);
            ReleaseLogUtil.e("HiH_HiSyncTrack", "pushData() end !, clientIds is Empty");
            return;
        }
        LogUtil.a("HiH_HiSyncTrack", "clientIds list is ", e2);
        e(i, hiSyncOption, e2);
        if (this.c) {
            a(e2, hiSyncOption, i2, motionPathDataSwitch);
            this.h = 0;
        }
        for (Integer num : e2) {
            ivc.b(e, 1.0d, 1.0d / e2.size(), 2.0d);
            b(num.intValue(), hiSyncOption, i2, motionPathDataSwitch);
        }
        ivc.b(e);
        ReleaseLogUtil.e("HiH_HiSyncTrack", "pushData() end !");
    }

    private void a(List<Integer> list, HiSyncOption hiSyncOption, int i, MotionPathDataSwitch motionPathDataSwitch) throws iut {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            c(it.next().intValue(), hiSyncOption, i, motionPathDataSwitch);
        }
    }

    private void b(int i, HiSyncOption hiSyncOption, int i2, MotionPathDataSwitch motionPathDataSwitch) throws iut {
        igq c = this.i.c(this.j, 0L, 2);
        long a2 = c != null ? c.a() : 0L;
        while (true) {
            if (this.g >= 2) {
                break;
            }
            List<HiHealthData> d = d(i);
            ArrayList arrayList = new ArrayList(d.size());
            arrayList.addAll(d);
            if (arrayList.isEmpty()) {
                break;
            }
            if (!a(arrayList, hiSyncOption, i2, motionPathDataSwitch, false, a2)) {
                LogUtil.h("HiH_HiSyncTrack", "uploadTrack failed , clientId is ", Integer.valueOf(i));
                break;
            } else if (arrayList.size() < 10) {
                LogUtil.a("HiH_HiSyncTrack", "uploadTrack the size is smaller than HiSyncUtil.UPLOAD_TRACK_DATA_MAX, size is", Integer.valueOf(arrayList.size()), ", clientId is", Integer.valueOf(i));
                break;
            }
        }
        this.g = 0;
    }

    private void c(int i, HiSyncOption hiSyncOption, int i2, MotionPathDataSwitch motionPathDataSwitch) throws iut {
        igq c = this.i.c(this.j, 0L, 2);
        long a2 = c != null ? c.a() : 0L;
        while (true) {
            if (this.g >= 2) {
                break;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long t = HiDateUtil.t(currentTimeMillis);
            long f = HiDateUtil.f(currentTimeMillis);
            List<Integer> c2 = c();
            c2.addAll(HiHealthDictManager.d(e).e());
            List<HiHealthData> d = this.d.d(i, 30001, CommonUtil.b(c2), 0, t, f);
            if (!HiCommonUtil.d(d)) {
                List<HiHealthData> arrayList = new ArrayList<>(d.size());
                arrayList.addAll(d);
                if (arrayList.isEmpty()) {
                    break;
                }
                if (!a(arrayList, hiSyncOption, i2, motionPathDataSwitch, true, a2)) {
                    LogUtil.h("HiH_HiSyncTrack", "uploadTodayTrackData failed , clientId is ", Integer.valueOf(i));
                    break;
                } else if (arrayList.size() < 10) {
                    LogUtil.a("HiH_HiSyncTrack", "uploadTodayTrackData the size is smaller than HiSyncUtil.UPLOAD_TRACK_DATA_MAX, size is", Integer.valueOf(arrayList.size()));
                    break;
                }
            } else {
                break;
            }
        }
        this.g = 0;
    }

    private List<HiHealthData> d(int i) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(i));
        List<Integer> c = c();
        c.addAll(HiHealthDictManager.d(e).e());
        return this.d.d(arrayList, 30001, CommonUtil.b(c), 0, 10);
    }

    private boolean a(List<HiHealthData> list, HiSyncOption hiSyncOption, int i, MotionPathDataSwitch motionPathDataSwitch, boolean z, long j) throws iut {
        if (z || !this.c) {
            int i2 = this.h + 1;
            this.h = i2;
            iuz.e(i2, hiSyncOption.getSyncManual());
        } else {
            int i3 = this.h + 1;
            this.h = i3;
            if (10 < i3) {
                this.g += 2;
                return false;
            }
        }
        List<MotionPathDetail> c = motionPathDataSwitch.c(list, i);
        if (HiCommonUtil.d(c)) {
            ReleaseLogUtil.d("HiH_HiSyncTrack", "uploadTrackDataOnce cloudTrack is null or empty ");
            return false;
        }
        AddMotionPathReq addMotionPathReq = new AddMotionPathReq();
        addMotionPathReq.setDetailInfo(c);
        addMotionPathReq.setTimeZone(list.get(0).getTimeZone());
        addMotionPathReq.setLocalMaxVersion(Long.valueOf(j));
        while (this.g < 2) {
            try {
                AddMotionPathRsp a2 = this.f13607a.a(addMotionPathReq);
                if (a2 != null && a2.getResultCode().intValue() == 1001) {
                    ParamValidDetail paramValidDetail = a2.getParamValidDetail();
                    iuz.b(a2, paramValidDetail);
                    if (paramValidDetail == null) {
                        this.g++;
                    } else {
                        iuz.b(list, paramValidDetail);
                        c(list);
                        ReleaseLogUtil.e("HiH_HiSyncTrack", "uploadTrackDone failed ! resultCode is ", a2.getResultCode(), ", resultDesc is ", a2.getResultDesc());
                        c(addMotionPathReq, a2, 2);
                        this.g++;
                    }
                } else if (!ius.a(a2, false)) {
                    this.g++;
                } else {
                    c(list);
                    c(addMotionPathReq, a2, 2);
                    ReleaseLogUtil.e("HiH_HiSyncTrack", "uploadTrackDataOnce OK ! uploadCount is ", Integer.valueOf(this.h), ", date size = ", Integer.valueOf(list.size()));
                    return true;
                }
            } catch (OutOfMemoryError e2) {
                ReleaseLogUtil.e("HiH_HiSyncTrack", "addMotionPathSync OutOfMemoryError", LogAnonymous.b((Throwable) e2));
                this.g++;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncTrack", "uploadTrackDataOnce failed ! uploadCount is ", Integer.valueOf(this.h), ", date size = ", Integer.valueOf(list.size()));
        return false;
    }

    private void c(AddMotionPathReq addMotionPathReq, AddMotionPathRsp addMotionPathRsp, int i) throws iut {
        ReleaseLogUtil.e("HiH_HiSyncTrack", "track LocalMaxVersion=", addMotionPathReq.getLocalMaxVersion(), ",rsp=", addMotionPathRsp.getTimestamp(), ",isHasMore=", Boolean.valueOf(addMotionPathRsp.isHasMore()), ",cloudTp=", Integer.valueOf(i));
        if (addMotionPathRsp.isHasMore()) {
            SyncKey syncKey = new SyncKey();
            syncKey.setType(Integer.valueOf(i));
            syncKey.setVersion(addMotionPathRsp.getTimestamp());
            syncKey.setDeviceCode(0L);
            this.f.a(syncKey);
            return;
        }
        if (this.i.d(this.j, i, addMotionPathRsp.getTimestamp().longValue(), 0L)) {
            return;
        }
        ReleaseLogUtil.d("HiH_HiSyncTrack", "sequence downloadMore saveVersionToDB failed!");
    }

    private void c(List<HiHealthData> list) {
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
            ReleaseLogUtil.e("HiH_HiSyncTrack", "uploadTrackDone sequenceID is ", Long.valueOf(hiHealthData.getDataId()), ", startTime is ", Long.valueOf(hiHealthData.getStartTime()), ", endTime is ", Long.valueOf(hiHealthData.getEndTime()), ", type is ", Integer.valueOf(hiHealthData.getType()), ", sportType is ", Integer.valueOf(hiHealthData.getSubType()), " update ans is ", Integer.valueOf(i));
        }
    }

    private void e(int i, HiSyncOption hiSyncOption, List<Integer> list) throws iut {
        LogUtil.a("HiH_HiSyncTrack", "delTrack userid is ", Integer.valueOf(i));
        while (true) {
            if (this.g >= 2) {
                break;
            }
            List<HiHealthData> d = this.d.d(list, 30001, CommonUtil.b(HiHealthDictManager.d(e).e()), 2, 10);
            if (HiCommonUtil.d(d)) {
                LogUtil.h("HiH_HiSyncTrack", "no track date should be delete!");
                break;
            } else {
                if (!e(d, hiSyncOption)) {
                    break;
                }
                b(d);
                if (d.size() < 10) {
                    LogUtil.a("HiH_HiSyncTrack", "delTrack the size is smaller than HiSyncUtil.UPLOAD_TRACK_DATA_MAX, size is", Integer.valueOf(d.size()));
                    break;
                }
            }
        }
        this.g = 0;
    }

    private boolean e(List<HiHealthData> list, HiSyncOption hiSyncOption) throws iut {
        int i = this.h + 1;
        this.h = i;
        iuz.e(i, hiSyncOption.getSyncManual());
        List<DataDeleteCondition> b = iuz.b(e.getApplicationContext(), list);
        if (HiCommonUtil.d(b)) {
            LogUtil.h("HiH_HiSyncTrack", "delTrackDataOnce conditons is null or empty ");
            return false;
        }
        DeleteMotionPathReq deleteMotionPathReq = new DeleteMotionPathReq();
        deleteMotionPathReq.setDeleteMotionConditons(b);
        while (this.g < 2) {
            if (!ius.a(this.f13607a.d(deleteMotionPathReq), false)) {
                this.g++;
            } else {
                ReleaseLogUtil.e("HiH_HiSyncTrack", "delTrackDataOnce OK ! deleteCount is ", Integer.valueOf(this.h));
                return true;
            }
        }
        ReleaseLogUtil.e("HiH_HiSyncTrack", "delTrackDataOnce failed ! deleteCount is ", Integer.valueOf(this.h));
        return false;
    }

    private void b(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            LogUtil.a("HiH_HiSyncTrack", "delTrackDone sequenceID is ", Long.valueOf(hiHealthData.getDataId()), " delete is ", Integer.valueOf(this.d.e(hiHealthData)));
        }
    }

    private List<Integer> c() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        arrayList.add(2);
        arrayList.add(263);
        arrayList.add(10001);
        arrayList.add(500001);
        return arrayList;
    }
}
