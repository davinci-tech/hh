package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class spj {

    /* renamed from: a, reason: collision with root package name */
    private Context f17201a;
    private snq d;

    private spj() {
        LogUtil.c("SampleCommandManager", "create SampleCommandManager");
        this.f17201a = BaseApplication.e();
        snq c2 = snq.c();
        this.d = c2;
        c2.a(this.f17201a);
    }

    public static spj c() {
        return c.f17202a;
    }

    public boolean d(DeviceInfo deviceInfo, cvq cvqVar, String str) {
        LogUtil.c("SampleCommandManager", "sendSampleConfigCommand enter. tag: ", str);
        if (a(deviceInfo)) {
            return false;
        }
        if (!bmm.a(deviceInfo, 83)) {
            LogUtil.a("SampleCommandManager", "sendSampleConfigCommand capability is not support.");
            return false;
        }
        UniteDevice e = spu.e(deviceInfo.getDeviceIdentify());
        bir b = spy.b(cvqVar);
        if (b != null) {
            blt.d("SampleCommandManager", b.e(), "sendSampleConfigCommand commandMessage: ");
            this.d.sendCommand(e, b);
            return true;
        }
        LogUtil.a("SampleCommandManager", "sendSampleConfigCommand commandMessage is invalid.");
        return false;
    }

    public boolean e(DeviceInfo deviceInfo, cvp cvpVar, String str) {
        LogUtil.c("SampleCommandManager", "sendSampleEventCommand enter. tag: ", str);
        if (a(deviceInfo)) {
            return false;
        }
        if (!bmm.a(deviceInfo, 84)) {
            LogUtil.a("SampleCommandManager", "sendSampleEventCommand capability is not support.");
            return false;
        }
        UniteDevice e = spu.e(deviceInfo.getDeviceIdentify());
        bir e2 = spy.e(cvpVar);
        if (e2 == null) {
            LogUtil.a("SampleCommandManager", "sendEventConfigCommand commandMessage is invalid.");
            return false;
        }
        this.d.sendCommand(e, e2);
        return true;
    }

    public boolean e(DeviceInfo deviceInfo, cvu cvuVar, String str) {
        LogUtil.c("SampleCommandManager", "sendSamplePointCommand enter. tag: ", str);
        if (a(deviceInfo)) {
            return false;
        }
        if (!bmm.a(deviceInfo, 85)) {
            LogUtil.a("SampleCommandManager", "sendSamplePointCommand capability is not support.");
            return false;
        }
        UniteDevice e = spu.e(deviceInfo.getDeviceIdentify());
        bir c2 = spy.c(cvuVar);
        if (c2 == null) {
            LogUtil.a("SampleCommandManager", "sendSamplePointCommand commandMessage is invalid.");
            return false;
        }
        this.d.sendCommand(e, c2);
        return true;
    }

    public boolean c(DeviceInfo deviceInfo, cvi cviVar, String str) {
        LogUtil.c("SampleCommandManager", "sendDictionaryPointInfoCommand enter. tag: ", str);
        if (a(deviceInfo)) {
            return false;
        }
        if (!bmm.a(deviceInfo, 173)) {
            LogUtil.a("SampleCommandManager", "sendDictionaryPointInfoCommand capability is not support.");
            return false;
        }
        UniteDevice e = spu.e(deviceInfo.getDeviceIdentify());
        bir b = spy.b(cviVar);
        if (b != null) {
            blt.d("SampleCommandManager", b.e(), "sendDictionaryPointInfoCommand commandMessage: ");
            this.d.sendCommand(e, b);
            return true;
        }
        LogUtil.a("SampleCommandManager", "sendSamplePointCommand commandMessage is invalid.");
        return false;
    }

    public void a(String str, DataReceiveCallback dataReceiveCallback) {
        LogUtil.c("SampleCommandManager", "registerDeviceMessageListener srcPkgName: ", str);
        if (dataReceiveCallback == null || TextUtils.isEmpty(str)) {
            LogUtil.a("SampleCommandManager", "callback or callback is invalid.");
        } else {
            this.d.registerListener(str, "sampleCommandCallback", dataReceiveCallback);
        }
    }

    public void c(String str) {
        LogUtil.c("SampleCommandManager", "unRegisterDeviceMessageListener srcPkgName: ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SampleCommandManager", "srcPkgName is invalid.");
        } else {
            this.d.unregisterListener(str, "sampleCommandCallback");
        }
    }

    public void c(UniteDevice uniteDevice, byte[] bArr, DataReceiveCallback dataReceiveCallback) {
        boolean e = e(bArr);
        LogUtil.c("SampleCommandManager", "isSampleCommand Result: ", Boolean.valueOf(e));
        if (!e || dataReceiveCallback == null) {
            return;
        }
        DeviceInfo c2 = blc.c(uniteDevice);
        blt.d("SampleCommandManager", bArr, "getResult() message: ");
        byte b = bArr[1];
        if (b == 1) {
            a(bArr, c2, dataReceiveCallback);
            return;
        }
        if (b == 2) {
            c(bArr, c2, dataReceiveCallback);
            return;
        }
        if (b == 3) {
            b(bArr, c2, dataReceiveCallback);
        } else if (b == 4) {
            e(bArr, c2, dataReceiveCallback);
        } else {
            LogUtil.a("SampleCommandManager", "getResult default branch.");
        }
    }

    private boolean e(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            LogUtil.a("SampleCommandManager", "isSampleCommandResult input param is invalid.");
            return false;
        }
        if (bArr[0] != 55) {
            return false;
        }
        LogUtil.c("SampleCommandManager", "isSampleCommandResult data received");
        return true;
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo, DataReceiveCallback dataReceiveCallback) {
        LogUtil.c("SampleCommandManager", "processSampleConfig enter.");
        cvq cvqVar = new cvq();
        cvqVar.setCommandId(1);
        try {
            bmq c2 = new bmt().c(bArr, 2);
            List<bmu> d = c2.d();
            List<bmq> a2 = c2.a();
            int c3 = c(d, cvqVar);
            if (c3 != 100000) {
                LogUtil.a("SampleCommandManager", "processSampleConfig errorCode is not default value.");
                dataReceiveCallback.onDataReceived(c3, deviceInfo, cvqVar);
                return;
            }
            if (a2 != null && a2.size() > 0) {
                LogUtil.c("SampleCommandManager", "5.55.1 tlvFatherList.size() :", Integer.valueOf(a2.size()));
                Iterator<bmq> it = a2.iterator();
                while (it.hasNext()) {
                    List<bmq> a3 = it.next().a();
                    if (a3 != null && a3.size() > 0) {
                        LogUtil.c("SampleCommandManager", "5.55.1 childTLVFatherList.size() :", Integer.valueOf(a3.size()));
                        b(c3, deviceInfo, a3, cvqVar, dataReceiveCallback);
                    }
                    LogUtil.a("SampleCommandManager", "childTLVFatherList is null or size is zero");
                    return;
                }
                return;
            }
            LogUtil.a("SampleCommandManager", "tlvFatherList is null or size is zero");
        } catch (bmk e) {
            LogUtil.e("SampleCommandManager", "processSampleConfig Exception: ", bll.a(e));
        }
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo, DataReceiveCallback dataReceiveCallback) {
        LogUtil.c("SampleCommandManager", "processSampleEvent enter.");
        cvp cvpVar = new cvp();
        cvpVar.setCommandId(2);
        try {
            List<bmu> d = new bmt().c(bArr, 2).d();
            int c2 = c(d, cvpVar);
            if (c2 != 100000) {
                LogUtil.a("SampleCommandManager", "processSampleEvent errorCode is not default value.");
                dataReceiveCallback.onDataReceived(c2, deviceInfo, cvpVar);
                return;
            }
            if (bkz.e(d)) {
                LogUtil.a("SampleCommandManager", "getResult tlvList isEmpty.");
                return;
            }
            for (bmu bmuVar : d) {
                byte a2 = bmuVar.a();
                if (a2 == 3) {
                    cvpVar.a(blq.e(bmuVar.c(), -1L));
                } else if (a2 == 4) {
                    cvpVar.b(blq.d(bmuVar.c(), -1));
                } else if (a2 != 5) {
                    LogUtil.a("SampleCommandManager", "processSampleEvent default type: ", Integer.valueOf(a2));
                } else {
                    cvpVar.e(bmuVar.c());
                }
            }
            dataReceiveCallback.onDataReceived(c2, deviceInfo, cvpVar);
        } catch (bmk e) {
            LogUtil.e("SampleCommandManager", "processSampleEvent Exception: ", bll.a(e));
        }
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo, DataReceiveCallback dataReceiveCallback) {
        LogUtil.c("SampleCommandManager", "processSamplePoint enter.");
        cvu cvuVar = new cvu();
        cvuVar.setCommandId(3);
        try {
            bmq c2 = new bmt().c(bArr, 2);
            List<bmu> d = c2.d();
            List<bmq> a2 = c2.a();
            int d2 = d(d, cvuVar);
            if (d2 != 100000) {
                LogUtil.a("SampleCommandManager", "processSamplePoint errorCode is not default value.");
                dataReceiveCallback.onDataReceived(d2, deviceInfo, cvuVar);
                return;
            }
            if (a2 != null && a2.size() > 0) {
                LogUtil.c("SampleCommandManager", "5.55.3 tlvFatherList.size() :", Integer.valueOf(a2.size()));
                Iterator<bmq> it = a2.iterator();
                while (it.hasNext()) {
                    List<bmq> a3 = it.next().a();
                    if (a3 != null && a3.size() > 0) {
                        LogUtil.c("SampleCommandManager", "5.55.3 childTLVFatherList.size(): ", Integer.valueOf(a3.size()));
                        c(d2, deviceInfo, a3, cvuVar, dataReceiveCallback);
                    }
                    LogUtil.a("SampleCommandManager", "childTLVFatherList is null or size is zero");
                    return;
                }
                return;
            }
            LogUtil.a("SampleCommandManager", "tlvFatherList is null or size is zero");
        } catch (bmk e) {
            LogUtil.e("SampleCommandManager", "processSamplePoint Exception: ", bll.a(e));
        }
    }

    private void e(byte[] bArr, DeviceInfo deviceInfo, DataReceiveCallback dataReceiveCallback) {
        int d;
        LogUtil.c("SampleCommandManager", "processDictionaryPoint enter.");
        cvi cviVar = new cvi();
        cviVar.setCommandId(4);
        try {
            bmq c2 = new bmt().c(bArr, 2);
            List<bmu> d2 = c2.d();
            List<bmq> a2 = c2.a();
            if (a2 != null && a2.size() > 0) {
                LogUtil.a("SampleCommandManager", "processDictionaryPoint tlvFatherList is size ：", Integer.valueOf(a2.size()));
                cviVar.d(1);
                d = e(d2, cviVar);
                LogUtil.c("SampleCommandManager", "processDictionaryPoint dictionaryPointInfo header to string: ", cviVar.toString());
                if (d != 100000) {
                    LogUtil.a("SampleCommandManager", "processDictionaryPoint errorCode is not default value.");
                    dataReceiveCallback.onDataReceived(d, deviceInfo, cviVar);
                    return;
                } else {
                    e(cviVar, a2);
                    LogUtil.c("SampleCommandManager", "processDictionaryPoint dictionaryPointInfo data to string: ", cviVar.toString());
                    dataReceiveCallback.onDataReceived(d, deviceInfo, cviVar);
                    LogUtil.c("SampleCommandManager", "processDictionaryPoint callback successful");
                }
            }
            LogUtil.a("SampleCommandManager", "processDictionaryPoint tlvFatherList is null or size is zero");
            cviVar.d(0);
            d = d(d2, cviVar);
            if (d != 100000) {
                LogUtil.a("SampleCommandManager", "processDictionaryPoint errorCode is not default value.");
                dataReceiveCallback.onDataReceived(d, deviceInfo, cviVar);
                return;
            }
            LogUtil.c("SampleCommandManager", "processDictionaryPoint dictionaryPointInfo data to string: ", cviVar.toString());
            dataReceiveCallback.onDataReceived(d, deviceInfo, cviVar);
            LogUtil.c("SampleCommandManager", "processDictionaryPoint callback successful");
        } catch (bmk e) {
            LogUtil.e("SampleCommandManager", "processDictionaryPoint Exception: ", bll.a(e));
        }
    }

    private void e(cvi cviVar, List<bmq> list) {
        List<bmq> a2 = list.get(0).a();
        ArrayList arrayList = new ArrayList(5);
        for (bmq bmqVar : a2) {
            List<bmu> d = bmqVar.d();
            if (d == null || d.size() <= 0) {
                LogUtil.a("SampleCommandManager", "processDictionaryPoint fillTlvPointList childTlvList is null or size is zero");
            } else {
                cvo cvoVar = new cvo();
                for (bmu bmuVar : d) {
                    byte a3 = bmuVar.a();
                    if (a3 == 6) {
                        cvoVar.a(blq.e(bmuVar.c(), -1L));
                    } else if (a3 == 7) {
                        cvoVar.c(blq.e(bmuVar.c(), -1L));
                    } else if (a3 == 8) {
                        cvoVar.b(blq.e(bmuVar.c(), -1L));
                    } else if (a3 == 13) {
                        cvoVar.a(bmuVar.c());
                    } else {
                        LogUtil.a("SampleCommandManager", "processDictionaryPoint fillTlvPointList fillTlvInfo default: ", Integer.valueOf(a3));
                    }
                }
                LogUtil.c("SampleCommandManager", "processDictionaryPoint fillTlvPointList dictionaryPointInfo time to string: ", cviVar.toString());
                List<bmq> a4 = bmqVar.a();
                if (a4 == null || a4.size() <= 0) {
                    LogUtil.a("SampleCommandManager", "processDictionaryPoint fillTlvPointList childDataList is null or size is zero");
                    arrayList.add(cvoVar);
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    e(a4, arrayList2);
                    cvoVar.a(arrayList2);
                    arrayList.add(cvoVar);
                    LogUtil.c("SampleCommandManager", "processDictionaryPoint fillTlvPointList dictionaryPointInfo key-value to string: ", cviVar.toString());
                }
            }
        }
        cviVar.b(arrayList);
    }

    private void e(List<bmq> list, List<cvv> list2) {
        Iterator<bmq> it = list.get(0).a().iterator();
        while (it.hasNext()) {
            List<bmu> d = it.next().d();
            cvv cvvVar = new cvv();
            for (bmu bmuVar : d) {
                byte a2 = bmuVar.a();
                if (a2 == 11) {
                    cvvVar.d(blq.e(bmuVar.c(), -1L));
                } else if (a2 == 12) {
                    cvvVar.b(bmuVar.c());
                } else {
                    LogUtil.a("SampleCommandManager", "processDictionaryPoint fillTlvPointListKeyAndValue default branches.");
                }
            }
            LogUtil.c("SampleCommandManager", "processDictionaryPoint pointInfo fillTlvPointListKeyAndValue to string: ", list2.toString());
            list2.add(cvvVar);
        }
    }

    private int c(List<bmu> list, cvr cvrVar) {
        if (bkz.e(list)) {
            LogUtil.a("SampleCommandManager", "fillHeadInfo tlvList is null or size is zero");
            return -1;
        }
        LogUtil.c("SampleCommandManager", "fillHeadInfo enter.");
        int i = 100000;
        for (bmu bmuVar : list) {
            byte a2 = bmuVar.a();
            if (a2 == 1) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.a("SampleCommandManager", "fillHeadInfo Constant.SRC_PKG_TYPE is null.");
                    return -1;
                }
                cvrVar.setSrcPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == 2) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.a("SampleCommandManager", "fillHeadInfo Constant.DEST_PKG_TYPE is null.");
                    return -1;
                }
                cvrVar.setWearPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == Byte.MAX_VALUE) {
                LogUtil.c("SampleCommandManager", "fillHeadInfo CommonUtil.ERROR_TYPE");
                i = blq.d(bmuVar.c(), -1);
            } else {
                LogUtil.a("SampleCommandManager", "fillHeadInfo default: ", Integer.valueOf(a2));
            }
        }
        return i;
    }

    private int d(List<bmu> list, cvu cvuVar) {
        if (bkz.e(list)) {
            LogUtil.a("SampleCommandManager", "fillHeadInfo tlvList is null or size is zero");
            return -1;
        }
        LogUtil.c("SampleCommandManager", "fillHeadInfo");
        int i = 100000;
        for (bmu bmuVar : list) {
            byte a2 = bmuVar.a();
            if (a2 == 1) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillHeadInfo Constant.SRC_PKG_TYPE is null.");
                    return -1;
                }
                cvuVar.setSrcPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == 2) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillHeadInfo Constant.DEST_PKG_TYPE is null.");
                    return -1;
                }
                cvuVar.setWearPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == 3) {
                cvuVar.c(blq.e(bmuVar.c(), -1L));
            } else if (a2 == 4) {
                cvuVar.d(blq.e(bmuVar.c(), -1L));
            } else if (a2 == 5) {
                cvuVar.b(blq.e(bmuVar.c(), -1L));
            } else if (a2 == 10) {
                cvuVar.d(bmuVar.c());
            } else if (a2 == Byte.MAX_VALUE) {
                LogUtil.c("SampleCommandManager", "fillHeadInfo CommonUtil.ERROR_TYPE");
                i = blq.d(bmuVar.c(), -1);
            } else {
                LogUtil.a("SampleCommandManager", "fillTlvInfo default: ", Integer.valueOf(a2));
            }
        }
        return i;
    }

    private int e(List<bmu> list, cvi cviVar) {
        if (bkz.e(list)) {
            LogUtil.a("SampleCommandManager", "fillTlvPointInfo fillHeadInfo tlvList is null or size is zero");
            return -1;
        }
        LogUtil.c("SampleCommandManager", "fillTlvPointInfo fillHeadInfo");
        int i = 100000;
        for (bmu bmuVar : list) {
            byte a2 = bmuVar.a();
            if (a2 == 1) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillTlvPointInfo fillHeadInfo Constant.SRC_PKG_TYPE is null.");
                    return -1;
                }
                cviVar.setSrcPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == 2) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillTlvPointInfo fillHeadInfo Constant.DEST_PKG_TYPE is null.");
                    return -1;
                }
                cviVar.setWearPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == 3) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillTlvPointInfo fillHeadInfo DICT_ID_TYPE is null.");
                    return -1;
                }
                cviVar.b(blq.e(bmuVar.c(), -1L));
            } else if (a2 == Byte.MAX_VALUE) {
                LogUtil.c("SampleCommandManager", "fillTlvPointInfo fillHeadInfo CommonUtil.ERROR_TYPE");
                i = blq.d(bmuVar.c(), -1);
            } else {
                LogUtil.a("SampleCommandManager", "fillTlvPointInfo fillTlvInfo default: ", Integer.valueOf(a2));
            }
        }
        LogUtil.c("SampleCommandManager", "fillTlvPointInfo dictionaryPointInfo：", cviVar);
        return i;
    }

    private int d(List<bmu> list, cvi cviVar) {
        if (bkz.e(list)) {
            LogUtil.a("SampleCommandManager", "fillTlvRequestPointInfo fillHeadInfo tlvList is null or size is zero");
            return -1;
        }
        LogUtil.c("SampleCommandManager", "fillTlvRequestPointInfo fillHeadInfo");
        ArrayList arrayList = new ArrayList();
        cvo cvoVar = new cvo();
        int i = 100000;
        for (bmu bmuVar : list) {
            byte a2 = bmuVar.a();
            if (a2 == 1) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillTlvRequestPointInfo fillHeadInfo Constant.SRC_PKG_TYPE is null.");
                    return -1;
                }
                cviVar.setSrcPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == 2) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillTlvRequestPointInfo fillHeadInfo Constant.DEST_PKG_TYPE is null.");
                    return -1;
                }
                cviVar.setWearPkgName(new String(bmuVar.c(), StandardCharsets.UTF_8));
            } else if (a2 == 3) {
                if (bmuVar.c() == null || bmuVar.c().length == 0) {
                    LogUtil.c("SampleCommandManager", "fillTlvRequestPointInfo fillHeadInfo DICT_ID_TYPE is null.");
                    return -1;
                }
                cviVar.b(blq.e(bmuVar.c(), 16L));
            } else if (a2 == 6) {
                cvoVar.a(blq.e(bmuVar.c(), 16L));
            } else if (a2 == 7) {
                cvoVar.c(blq.e(bmuVar.c(), 16L));
            } else if (a2 == 8) {
                cvoVar.b(blq.e(bmuVar.c(), 16L));
            } else {
                if (a2 == 14) {
                    cviVar.a(blq.d(bmuVar.c(), Integer.MAX_VALUE));
                    LogUtil.c("SampleCommandManager", "current sample limit is " + cviVar.c());
                } else if (a2 == Byte.MAX_VALUE) {
                    LogUtil.c("SampleCommandManager", "fillTlvRequestPointInfo fillHeadInfo CommonUtil.ERROR_TYPE");
                    i = blq.d(bmuVar.c(), 16);
                }
                LogUtil.a("SampleCommandManager", "fillTlvRequestPointInfo fillTlvInfo default: ", Integer.valueOf(a2));
            }
        }
        arrayList.add(cvoVar);
        cviVar.b(arrayList);
        LogUtil.c("SampleCommandManager", "fillTlvRequestPointInfo dictionaryPointInfo：", cviVar);
        return i;
    }

    private void b(int i, DeviceInfo deviceInfo, List<bmq> list, cvq cvqVar, DataReceiveCallback dataReceiveCallback) {
        ArrayList arrayList = new ArrayList(5);
        cvqVar.setConfigInfoList(arrayList);
        Iterator<bmq> it = list.iterator();
        while (it.hasNext()) {
            List<bmu> d = it.next().d();
            if (d == null || d.size() <= 0) {
                LogUtil.a("SampleCommandManager", "childTlv is null or size is zero");
                return;
            }
            cvn cvnVar = new cvn();
            for (bmu bmuVar : d) {
                byte a2 = bmuVar.a();
                if (a2 == 5) {
                    cvnVar.e(blq.e(bmuVar.c(), -1L));
                } else if (a2 == 6) {
                    cvnVar.d(blq.d(bmuVar.c(), -1));
                } else if (a2 == 7) {
                    cvnVar.c(bmuVar.c());
                } else if (a2 == 8) {
                    LogUtil.c("SampleCommandManager", "received loseWeight capability parse tlv start.");
                    cvnVar.a(Long.valueOf(blq.e(bmuVar.c(), -1L)));
                } else {
                    LogUtil.a("SampleCommandManager", "default branches.");
                }
            }
            LogUtil.c("SampleCommandManager", "configInfo to string:", cvnVar.toString());
            arrayList.add(cvnVar);
        }
        dataReceiveCallback.onDataReceived(i, deviceInfo, cvqVar);
        LogUtil.a("SampleCommandManager", "onDataReceived successful.");
    }

    private void c(int i, DeviceInfo deviceInfo, List<bmq> list, cvu cvuVar, DataReceiveCallback dataReceiveCallback) {
        ArrayList arrayList = new ArrayList(5);
        cvuVar.e(arrayList);
        Iterator<bmq> it = list.iterator();
        while (it.hasNext()) {
            List<bmu> d = it.next().d();
            if (d == null || d.size() <= 0) {
                LogUtil.a("SampleCommandManager", "childTlv is null or size is zero");
                return;
            }
            cvv cvvVar = new cvv();
            for (bmu bmuVar : d) {
                byte a2 = bmuVar.a();
                if (a2 == 8) {
                    cvvVar.d(blq.e(bmuVar.c(), -1L));
                } else if (a2 == 9) {
                    cvvVar.b(bmuVar.c());
                } else {
                    LogUtil.a("SampleCommandManager", "default branches.");
                }
            }
            LogUtil.c("SampleCommandManager", "pointInfo to string: ", cvvVar.toString());
            arrayList.add(cvvVar);
        }
        dataReceiveCallback.onDataReceived(i, deviceInfo, cvuVar);
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("SampleCommandManager", "checkError deviceInfo is invalid.");
            return true;
        }
        if (deviceInfo.getDeviceConnectState() == 2) {
            return false;
        }
        LogUtil.a("SampleCommandManager", "checkError deviceInfo connectState is invalid.");
        return true;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static spj f17202a = new spj();
    }
}
