package defpackage;

import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: classes5.dex */
public class jul {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14095a = new Object();
    private static volatile jul b;
    private final Map<String, cvq> d = new HashMap();
    private final List<String> e = new ArrayList();

    private jul(DeviceInfo deviceInfo) {
        LogUtil.a("R_HwCommonManager", "HwCommonManager");
        e();
        a(deviceInfo);
    }

    public static jul e(DeviceInfo deviceInfo) {
        if (b == null) {
            synchronized (f14095a) {
                if (b == null) {
                    LogUtil.a("HwCommonManager", "getInstance()");
                    b = new jul(deviceInfo);
                }
            }
        }
        return b;
    }

    private void e() {
        this.d.put("900200035", b("com.huawei.health.wear.golf", "hw.unitedevice.golf", "900200035", 105, "9002"));
    }

    private cvq b(String str, String str2, String str3, int i, String str4) {
        cvq cvqVar = new cvq();
        cvqVar.setWearPkgName(str);
        cvqVar.setSrcPkgName(str2);
        cvn cvnVar = new cvn();
        try {
            cvnVar.e(Long.parseLong(str3));
            cvnVar.c(i);
            cvnVar.d(str4);
            ArrayList arrayList = new ArrayList();
            arrayList.add(cvnVar);
            cvqVar.setConfigInfoList(arrayList);
            return cvqVar;
        } catch (NumberFormatException e) {
            ReleaseLogUtil.e("R_HwCommonManager", "getSampleConfig exception ", LogAnonymous.b((Throwable) e));
            return cvqVar;
        }
    }

    public void a(DeviceInfo deviceInfo) {
        HashMap hashMap = new HashMap();
        synchronized (f14095a) {
            Iterator<Map.Entry<String, cvq>> it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                cvq value = it.next().getValue();
                if (value != null) {
                    String srcPkgName = value.getSrcPkgName();
                    if (hashMap.containsKey(srcPkgName)) {
                        List<cvn> configInfoList = ((cvq) hashMap.get(srcPkgName)).getConfigInfoList();
                        configInfoList.addAll(value.getConfigInfoList());
                        value.setConfigInfoList(configInfoList);
                    }
                    hashMap.put(srcPkgName, value);
                }
            }
        }
        for (final Map.Entry entry : hashMap.entrySet()) {
            cvq cvqVar = (cvq) entry.getValue();
            if (cvqVar != null && d(deviceInfo, cvqVar)) {
                this.e.add((String) entry.getKey());
                cuk.b().registerDeviceSampleListener((String) entry.getKey(), new DataReceiveCallback() { // from class: jur
                    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
                    public final void onDataReceived(int i, DeviceInfo deviceInfo2, cvr cvrVar) {
                        jul.this.c(entry, i, deviceInfo2, cvrVar);
                    }
                });
            }
        }
    }

    /* synthetic */ void c(Map.Entry entry, int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (i != 100000) {
            ReleaseLogUtil.e("R_HwCommonManager", "receive device event.", Integer.valueOf(i));
        } else if ((cvrVar instanceof cvq) && cvrVar.getWearPkgName().equals(entry.getKey())) {
            LogUtil.a("HwCommonManager", "message instanceof SampleConfig.", cvrVar.toString());
            e((cvq) cvrVar, ((cvq) entry.getValue()).getConfigInfoList());
        } else {
            ReleaseLogUtil.e("R_HwCommonManager", "diet receive unrecognized message = ", cvrVar);
        }
    }

    private boolean d(DeviceInfo deviceInfo, cvq cvqVar) {
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (koq.b(configInfoList)) {
            return true;
        }
        for (cvn cvnVar : configInfoList) {
            if (cvnVar.d() == 0 || bmm.a(deviceInfo, cvnVar.d())) {
                return true;
            }
        }
        return false;
    }

    private void e(cvq cvqVar, List<cvn> list) {
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (koq.b(configInfoList)) {
            ReleaseLogUtil.e("R_HwCommonManager", "infoList empty");
            return;
        }
        final HashMap hashMap = new HashMap();
        list.forEach(new Consumer() { // from class: jup
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                hashMap.put(Long.toString(r2.a()), ((cvn) obj).j());
            }
        });
        for (cvn cvnVar : configInfoList) {
            String l = Long.toString(cvnVar.a());
            if (!hashMap.containsKey(l)) {
                LogUtil.a("R_HwCommonManager", "processJsonConfigData configId = ", l, " is not register");
            } else {
                long longValue = cvnVar.c().longValue();
                final String d = HEXUtils.d(HEXUtils.a(cvnVar.b()));
                njj.a((String) hashMap.get(l), l, d, new HiDataOperateListener() { // from class: juq
                    @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                    public final void onResult(int i, Object obj) {
                        jul.d(d, i, obj);
                    }
                }, longValue);
            }
        }
    }

    static /* synthetic */ void d(String str, int i, Object obj) {
        String str2 = "onDataReceived setSampleConfig errorCode:" + i + ", object: " + obj + " configData = " + str;
        if (i == 0) {
            LogUtil.a("HwCommonManager", str2);
        } else {
            ReleaseLogUtil.e("HwCommonManager", str2);
        }
    }

    public static void d() {
        ReleaseLogUtil.e("R_HwCommonManager", "releaseMgr");
        synchronized (f14095a) {
            if (b == null) {
                ReleaseLogUtil.e("R_HwCommonManager", "sInstance is null");
                return;
            }
            if (b.e.isEmpty()) {
                ReleaseLogUtil.e("R_HwCommonManager", "mRegisterList.isEmpty()");
                return;
            }
            Iterator<String> it = b.e.iterator();
            while (it.hasNext()) {
                cuk.b().unRegisterDeviceSampleListener(it.next());
            }
            b.d.clear();
            b.e.clear();
            b = null;
        }
    }
}
