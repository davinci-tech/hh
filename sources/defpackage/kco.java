package defpackage;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
public class kco implements DataReceiveCallback {
    private static kco b;
    private static final Object e = new Object();

    public static kco e() {
        kco kcoVar;
        synchronized (e) {
            if (b == null) {
                b = new kco();
            }
            kcoVar = b;
        }
        return kcoVar;
    }

    private kco() {
    }

    public void a() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.physiological", this);
    }

    private void b() {
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.physiological");
    }

    private List<cvo> c(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(5);
        for (HiHealthData hiHealthData : list) {
            cvo cvoVar = new cvo();
            cvoVar.a(hiHealthData.getStartTime());
            cvoVar.c(hiHealthData.getEndTime());
            cvoVar.b(hiHealthData.getModifiedTime());
            cvv cvvVar = new cvv();
            cvvVar.d(hiHealthData.getType());
            cvvVar.b(cvx.g(hiHealthData.getIntValue()));
            ArrayList arrayList2 = new ArrayList(5);
            arrayList2.add(cvvVar);
            cvoVar.a(arrayList2);
            arrayList.add(cvoVar);
        }
        LogUtil.c("HwMenstrualDictionaryManager", "sampleConfigInfos is:", arrayList);
        return arrayList;
    }

    private boolean d(List<cvo> list) {
        DeviceInfo c = kcl.a().c("HwMenstrualDictionaryManager");
        if (!bmm.a(c, 174)) {
            ReleaseLogUtil.d("HwMenstrualDictionaryManager", "sendMenstrualFlowCommand capability is not support.");
            return false;
        }
        boolean sendDictionaryPointInfoCommand = cuk.b().sendDictionaryPointInfoCommand(c, b(list), "HwMenstrualDictionaryManager");
        if (!sendDictionaryPointInfoCommand) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualDictionaryManager", "sendFindDeviceCommand fail");
        }
        return sendDictionaryPointInfoCommand;
    }

    private cvi b(List<cvo> list) {
        cvi cviVar = new cvi();
        cviVar.setSrcPkgName("hw.unitedevice.physiological");
        cviVar.setWearPkgName("hw.watch.health.physiological");
        cviVar.setCommandId(4);
        cviVar.b(400018L);
        cviVar.d(1);
        cviVar.b(list);
        return cviVar;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (deviceInfo == null || d(cvrVar)) {
            ReleaseLogUtil.d("DEVMGR_HwMenstrualDictionaryManager", "device == null or isCheckSampleError");
            return;
        }
        if (cvrVar instanceof cvi) {
            cvi cviVar = (cvi) cvrVar;
            LogUtil.c("HwMenstrualDictionaryManager", "sampleConfig is:", cviVar);
            if (cviVar.d() != 400018) {
                ReleaseLogUtil.d("DEVMGR_HwMenstrualDictionaryManager", "onDataReceived, message dictTypeId", Long.valueOf(cviVar.d()));
                return;
            }
            final cvo cvoVar = cviVar.b().get(0);
            if (cvoVar == null) {
                LogUtil.h("HwMenstrualDictionaryManager", "getDataInfoList onResult pointInfo is null");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: kcn
                    @Override // java.lang.Runnable
                    public final void run() {
                        kco.this.d(cvoVar);
                    }
                });
            }
        }
    }

    /* synthetic */ void d(cvo cvoVar) {
        List<HiHealthData> menstrualFlow = nhu.e().getMenstrualFlow(cvoVar.c(), cvoVar.d(), cvoVar.e());
        a(menstrualFlow);
        ArrayList arrayList = new ArrayList();
        if (menstrualFlow.size() < 10) {
            arrayList.addAll(menstrualFlow);
        } else {
            arrayList.addAll(menstrualFlow.subList(0, 10));
        }
        d(c(arrayList));
        ReleaseLogUtil.e("DEVMGR_HwMenstrualDictionaryManager", "sendMenstrualFlowCommand is success.");
    }

    private void a(List<HiHealthData> list) {
        Collections.sort(list, new Comparator<HiHealthData>() { // from class: kco.3
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                return Long.compare(hiHealthData.getStartTime(), hiHealthData2.getStartTime());
            }
        });
    }

    private boolean d(cvr cvrVar) {
        if (cvrVar == null) {
            ReleaseLogUtil.e("DEVMGR_HwMenstrualDictionaryManager", "sampleBase == null");
            return true;
        }
        if (cvrVar.getCommandId() == 4 && "hw.watch.health.physiological".equals(cvrVar.getSrcPkgName()) && "hw.unitedevice.physiological".equals(cvrVar.getWearPkgName())) {
            return false;
        }
        LogUtil.h("HwMenstrualDictionaryManager", "message: ", cvrVar.toString());
        return true;
    }

    public void c(long j, long j2, long j3) {
        List<HiHealthData> menstrualFlow = nhu.e().getMenstrualFlow(j, j2, j3);
        a(menstrualFlow);
        d(c(menstrualFlow));
        ReleaseLogUtil.e("DEVMGR_HwMenstrualDictionaryManager", "sendMenstrualFlowCommand is success.");
    }

    public void c() {
        b();
        LogUtil.a("HwMenstrualDictionaryManager", "onDestroy()");
    }
}
