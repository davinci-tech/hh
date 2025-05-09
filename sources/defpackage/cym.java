package defpackage;

import com.google.gson.GsonBuilder;
import com.huawei.health.ecologydevice.callback.RopeHistoryCallback;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cym {

    /* renamed from: a, reason: collision with root package name */
    private String f11542a;
    private RopeHistoryCallback b;
    private List<cys> d = new ArrayList();
    private List<cys> c = new ArrayList();

    public cym(String str, RopeHistoryCallback ropeHistoryCallback) {
        this.f11542a = str;
        this.b = ropeHistoryCallback;
    }

    public void a(RopeHistoryCallback ropeHistoryCallback) {
        this.b = ropeHistoryCallback;
    }

    public void d(cys cysVar) {
        LogUtil.c("PDROPE_RopeHistoryProcessor", "old size before:", Integer.valueOf(this.d.size()));
        if (cysVar == null) {
            return;
        }
        if (koq.c(this.d)) {
            Iterator<cys> it = this.d.iterator();
            while (it.hasNext()) {
                if (it.next().f() == cysVar.f()) {
                    return;
                }
            }
        }
        RopeHistoryCallback ropeHistoryCallback = this.b;
        if (ropeHistoryCallback != null) {
            ropeHistoryCallback.mergeHistorySuccess(cysVar.d());
        }
        this.d.add(cysVar);
        LogUtil.c("PDROPE_RopeHistoryProcessor", "old size after:", Integer.valueOf(this.d.size()));
    }

    public void c() {
        LogUtil.a("PDROPE_RopeHistoryProcessor", "uploadHistoryData");
        if (koq.b(this.d)) {
            LogUtil.a("PDROPE_RopeHistoryProcessor", "No data to upload");
            return;
        }
        final HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        if (this.c.size() > 0) {
            LogUtil.a("PDROPE_RopeHistoryProcessor", "uploading");
            return;
        }
        this.c.addAll(this.d);
        this.d.clear();
        cya cyaVar = new cya();
        for (cys cysVar : this.c) {
            if (cyaVar.c(cysVar) == null) {
                LogUtil.a("PDROPE_RopeHistoryProcessor", "clearData delete");
            } else {
                hiDataInsertOption.addData(a(cysVar));
            }
        }
        if (koq.b(hiDataInsertOption.getDatas())) {
            LogUtil.a("PDROPE_RopeHistoryProcessor", "Cleared all data, no data to upload");
            e(this.c.size());
            this.c.clear();
            return;
        }
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: cyl
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                cym.this.e(hiDataInsertOption, i, obj);
            }
        });
    }

    /* synthetic */ void e(HiDataInsertOption hiDataInsertOption, int i, Object obj) {
        LogUtil.a("PDROPE_RopeHistoryProcessor", "uploadHistoryData onResult errorCode:", Integer.valueOf(i));
        if (obj != null) {
            LogUtil.c("PDROPE_RopeHistoryProcessor", "uploadHistoryData onResult object:", obj);
        }
        if (i == 0) {
            e(this.c.size());
            new cyo().a(hiDataInsertOption.getDatas());
            RopeHistoryCallback ropeHistoryCallback = this.b;
            if (ropeHistoryCallback != null) {
                ropeHistoryCallback.uploadHistorySuccess();
            }
            LogUtil.a("PDROPE_RopeHistoryProcessor", "syncCloud, size() = ", Integer.valueOf(hiDataInsertOption.getDatas().size()));
            e();
        } else {
            LogUtil.h("PDROPE_RopeHistoryProcessor", "uploadHistoryData ERROR:", Integer.valueOf(i));
        }
        this.c.clear();
    }

    private void e() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
        LogUtil.a("PDROPE_RopeHistoryProcessor", "syncCloud, end");
    }

    private HiHealthData a(cys cysVar) {
        HiHealthData hiHealthData = new HiHealthData();
        long f = cysVar.f() * 1000;
        long d = (cysVar.d() * 1000) + f;
        LogUtil.c("PDROPE_RopeHistoryProcessor", "startTime:", Long.valueOf(f), ", date:", nsj.a(f), " endTime:", Long.valueOf(d), ", date:", nsj.a(d));
        hiHealthData.setTimeInterval(f, d);
        hiHealthData.setType(30001);
        hiHealthData.setValue(cysVar.h());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeSpecialFloatingPointValues();
        hiHealthData.setMetaData(gsonBuilder.create().toJson(b(cysVar), HiTrackMetaData.class));
        if (koq.c(cysVar.a())) {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator<ckk> it = cysVar.a().iterator();
            while (it.hasNext()) {
                ckk next = it.next();
                kwt kwtVar = new kwt();
                kwtVar.f(cysVar.j());
                kwtVar.d(next.e());
                kwtVar.d(next.g());
                kwtVar.b(next.j());
                kwtVar.a(next.d());
                kwtVar.e(next.b() * 1000);
                kwtVar.c(next.a());
                kwtVar.g(next.c());
                kwtVar.toTrackString(stringBuffer);
            }
            hiHealthData.setSequenceData(stringBuffer.toString());
        } else {
            hiHealthData.setSequenceData("0");
        }
        hiHealthData.setDeviceUuid(this.f11542a);
        return hiHealthData;
    }

    private HiTrackMetaData b(cys cysVar) {
        HiTrackMetaData hiTrackMetaData = new HiTrackMetaData();
        hiTrackMetaData.setSportType(283);
        hiTrackMetaData.setTotalCalories(cysVar.i() * 1000);
        hiTrackMetaData.setChiefSportDataType(7);
        hiTrackMetaData.setSportDataSource(5);
        hiTrackMetaData.setHasTrackPoint(false);
        long d = cysVar.d() * 1000;
        hiTrackMetaData.setTotalTime(d);
        HashMap hashMap = new HashMap();
        int b = cysVar.b();
        if (b == 65535) {
            b = -1;
        }
        hashMap.put("interruptTimes", String.valueOf(b));
        int c = cysVar.c();
        hashMap.put("maxSkippingTimes", String.valueOf(c != 65535 ? c : -1));
        int b2 = CommonUtil.b(cysVar.h(), d);
        hashMap.put("skipSpeed", String.valueOf(b2));
        hiTrackMetaData.setAbnormalTrack(koj.d(b2, null));
        hashMap.put("skipNum", String.valueOf(cysVar.h()));
        if (koq.c(cysVar.a())) {
            hashMap.put("total_action_group", String.valueOf(cysVar.e()));
        }
        if (cysVar.j() != 0) {
            hashMap.put("skippingMode", String.valueOf(cysVar.j()));
        }
        if (cysVar.j() == 3 && cysVar.g() != null) {
            hashMap.put("singleShakeNum", String.valueOf(cysVar.g().d()));
            hashMap.put("doubleShakeNum", String.valueOf(cysVar.g().b()));
            hashMap.put("tripleShakeNum", String.valueOf(cysVar.g().e()));
            hashMap.put("reversedSkipNum", String.valueOf(cysVar.g().c()));
        }
        hiTrackMetaData.setTotalSteps(cysVar.h());
        hiTrackMetaData.setExtendTrackDataMap(hashMap);
        return hiTrackMetaData;
    }

    private void e(int i) {
        LogUtil.a("PDROPE_RopeHistoryProcessor", "deleteDeviceHistoryData count:", Integer.valueOf(i));
        dds.c().c(3, 8, new int[]{i});
    }
}
