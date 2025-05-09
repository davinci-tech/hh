package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class kbr {
    private cwl e;

    /* synthetic */ kbr(AnonymousClass3 anonymousClass3) {
        this();
    }

    private kbr() {
        this.e = new cwl();
    }

    public void c(LinkedList<kbm> linkedList, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.h("DataDictionarySingleSequenceManager", "startToSyncByHealthKit");
        if (linkedList != null) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "DataDictionarySingleSequenceManager");
            if (deviceList == null) {
                LogUtil.h("DataDictionarySingleSequenceManager", "startToSyncByHealthKit deviceInfoList is null");
                iBaseResponseCallback.d(1, "");
                return;
            }
            deviceList.removeAll(Collections.singleton(null));
            if (deviceList.size() != 0) {
                LogUtil.a("DataDictionarySingleSequenceManager", "connected device size = ", Integer.valueOf(deviceList.size()));
                Iterator<DeviceInfo> it = deviceList.iterator();
                while (it.hasNext()) {
                    kcb.a(it.next().getDeviceIdentify(), linkedList, iBaseResponseCallback);
                }
                return;
            }
            LogUtil.h("DataDictionarySingleSequenceManager", "startToSyncByHealthKit deviceInfoList is empty");
            iBaseResponseCallback.d(1, "");
            return;
        }
        LogUtil.h("DataDictionarySingleSequenceManager", "startToSyncByHealthKit singleInfosList is null");
        iBaseResponseCallback.d(1, "");
    }

    /* renamed from: kbr$3, reason: invalid class name */
    class AnonymousClass3 implements DataReceiveCallback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ kbr f14261a;

        @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
        public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
            if (cvrVar == null || deviceInfo == null) {
                LogUtil.h("DataDictionarySingleSequenceManager", "onDataReceived message or device is null,errorCode:", Integer.valueOf(i));
                return;
            }
            if (!(cvrVar instanceof cvp)) {
                LogUtil.h("DataDictionarySingleSequenceManager", "onDataReceived message not SampleEvent");
                return;
            }
            cvp cvpVar = (cvp) cvrVar;
            long e = cvpVar.e();
            if (e != 800100004) {
                LogUtil.h("DataDictionarySingleSequenceManager", "onDataReceived eventId not support:", Long.valueOf(e));
                return;
            }
            byte[] c = cvpVar.c();
            if (c == null) {
                LogUtil.h("DataDictionarySingleSequenceManager", "onDataReceived eventData is null");
                return;
            }
            String d = cvx.d(c);
            String deviceIdentify = deviceInfo.getDeviceIdentify();
            LogUtil.a("DataDictionarySingleSequenceManager", "onDataReceived data:", d);
            this.f14261a.a(deviceIdentify, d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        LinkedList<kbm> linkedList = new LinkedList<>();
        c(str2, linkedList);
        if (linkedList.isEmpty()) {
            LogUtil.h("DataDictionarySingleSequenceManager", "onDataReceived singleInfosList is Empty");
        }
    }

    private void c(String str, LinkedList<kbm> linkedList) {
        try {
            Iterator<cwe> it = this.e.a(str).a().iterator();
            while (it.hasNext()) {
                c(it.next(), linkedList);
            }
        } catch (cwg unused) {
            LogUtil.b("DataDictionarySingleSequenceManager", "onDataReceived processTlvData TlvException");
        }
    }

    private void c(cwe cweVar, LinkedList<kbm> linkedList) {
        if (cweVar == null) {
            return;
        }
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            d(it.next(), linkedList);
        }
    }

    private void d(cwe cweVar, LinkedList<kbm> linkedList) {
        if (cweVar == null) {
            return;
        }
        for (cwd cwdVar : cweVar.e()) {
            int w = CommonUtil.w(cwdVar.e());
            String c2 = cwdVar.c();
            LogUtil.a("DataDictionarySingleSequenceManager", "TLV-getTag:", Integer.valueOf(w), "TLV-getValue:", c2);
            if (w == 3) {
                kbm kbmVar = new kbm();
                kbmVar.d(CommonUtil.w(c2));
                linkedList.add(kbmVar);
            }
        }
    }

    public static kbr d() {
        return c.d;
    }

    static class c {
        private static final kbr d = new kbr(null);
    }
}
