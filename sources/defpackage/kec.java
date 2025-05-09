package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.algorithm.callback.IBluetoothResponseCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwcoresleepmgr.SyncBaseFunction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kec implements SyncBaseFunction {
    private String b;
    private IBluetoothResponseCallback c;
    private int[] e;

    static class a {
        static final kec c = new kec();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(int i) {
        if (i == 144001) {
            return 10000;
        }
        return i;
    }

    private kec() {
        this.e = null;
        this.b = null;
    }

    public static kec c() {
        return a.c;
    }

    @Override // com.huawei.hwcoresleepmgr.SyncBaseFunction
    public void addOtaCoreSleepLog(int i, long j) {
        String d = jsd.d(j);
        jsd.b(BaseApplication.getContext(), "031301", "EXCE_SLEEP_RECORD_ABNORMAL_ERROR", i + "", d);
    }

    @Override // com.huawei.hwcoresleepmgr.SyncBaseFunction
    public void registerHiHealthDataClient(DeviceInfo deviceInfo) {
        jpp.i(deviceInfo);
    }

    @Override // com.huawei.hwcoresleepmgr.SyncBaseFunction
    public void getSleepDataFromDevice(boolean z, boolean z2, int i, int i2, IBluetoothResponseCallback iBluetoothResponseCallback) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "enter getSleepDetailFromDevice, isNormal: ", Boolean.valueOf(z2), ", startTime: ", Integer.valueOf(i), ", endTime: ", Integer.valueOf(i2), ", isPriority: ", Boolean.valueOf(z));
        DeviceCapability deviceCapability = getDeviceCapability(getDeviceInfo());
        if (deviceCapability == null || (!deviceCapability.isSupportCoreSleep() && !deviceCapability.isSupportQueryDeviceCoreSleepSwitch())) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_BluetoothTransImpl", "deviceCapability is null or not support core sleep and switch");
        } else if (deviceCapability.isSupportCoreSleepNewFile()) {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "support new file.");
            d(i, i2, iBluetoothResponseCallback);
        } else {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "support old file.");
            c(z2, i, i2, iBluetoothResponseCallback);
        }
    }

    private void c(boolean z, int i, int i2, IBluetoothResponseCallback iBluetoothResponseCallback) {
        TransferFileInfo transferFileInfo = new TransferFileInfo();
        transferFileInfo.setType(2);
        if (!z) {
            transferFileInfo.setPriority(3);
        } else {
            transferFileInfo.setPriority(2);
        }
        transferFileInfo.setSuspend(0);
        transferFileInfo.setTaskType(0);
        transferFileInfo.setRecordId(new ArrayList(16));
        transferFileInfo.setStartTime(i);
        transferFileInfo.setEndTime(i2);
        this.c = iBluetoothResponseCallback;
        jvl.b().b(transferFileInfo, new ITransferSleepAndDFXFileCallback.Stub() { // from class: kec.2
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i3, String str, String str2) {
                kec.this.b(i3, str, str2, false);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i3, String str) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "getSleepDataFromDevice getFile onFailure errCode = ", Integer.valueOf(i3));
                LogUtil.a("BluetoothTransImpl", "getSleepDataFromDevice getFile onFailure errMsg = ", str);
                if (kec.this.c != null) {
                    kec.this.c.onFailure(i3, str);
                } else {
                    LogUtil.a("BluetoothTransImpl", "getSleepDataFromDevice getFile onFailure mCoreSleepCallback is null.");
                }
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i3, String str) {
                LogUtil.a("BluetoothTransImpl", "getSleepDataFromDevice getFile onProgress progress = " + i3);
                if (kec.this.c != null) {
                    kec.this.c.onProgress(i3, null);
                } else {
                    LogUtil.a("BluetoothTransImpl", "getSleepDataFromDevice getFile onProgress mCoreSleepCallback is null.");
                }
            }
        });
    }

    @Override // com.huawei.hwcoresleepmgr.SyncBaseFunction
    public DeviceInfo getDeviceInfo() {
        LogUtil.a("BluetoothTransImpl", "phonservice getDeviceInfo ");
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, "BluetoothTransImpl");
        if (deviceList == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_BluetoothTransImpl", "getDeviceInfo deviceInfoList is null");
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (!cvt.c(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("BluetoothTransImpl", "getActiveDeviceInfo deviceInfo ", deviceInfo);
        return deviceInfo;
    }

    @Override // com.huawei.hwcoresleepmgr.SyncBaseFunction
    public DeviceCapability getDeviceCapability(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_BluetoothTransImpl", "getDeviceCapability deviceInfo is null");
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "BluetoothTransImpl");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }

    @Override // com.huawei.hwcoresleepmgr.SyncBaseFunction
    public void sendCommandToDevice(int i, Map<Integer, String> map, DeviceInfo deviceInfo) {
        if (map == null || map.size() > 1) {
            LogUtil.h("BluetoothTransImpl", "sendSleepDeviceData parameter is error.");
            return;
        }
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        String str = "";
        while (it.hasNext()) {
            str = it.next().getValue();
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BluetoothTransImpl", "sendSleepDeviceData parameter value is null");
            return;
        }
        if (i == 24) {
            c(Integer.parseInt(str));
        } else if (i == 25) {
            b(Integer.parseInt(str), deviceInfo);
        } else {
            LogUtil.h("BluetoothTransImpl", "sendSleepDeviceData enter other, commandId is :", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.hwcoresleepmgr.SyncBaseFunction
    public void dicSyncCoreSleep(String str, final IBaseResponseCallback iBaseResponseCallback) {
        LinkedList<kbm> linkedList = new LinkedList<>();
        kbm kbmVar = new kbm();
        kbmVar.d(DicDataTypeUtil.DataType.SLEEP_DETAILS.value());
        if (kbq.d(kbmVar.d()) == -1) {
            ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "dicSyncCoreSleep DictionarySequenceCapability error");
            return;
        }
        linkedList.add(kbmVar);
        keu keuVar = new keu();
        keuVar.d(str);
        keuVar.c(2);
        keuVar.b(linkedList);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", " start dicSyncCoreSleep");
        ket.a().e("DIC_SEQUENCE_SYNC_TASK", keuVar, new IBaseResponseCallback() { // from class: kec.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "dicSyncCoreSleep errorCode:", Integer.valueOf(i));
                if (i != 0) {
                    i = -1;
                }
                iBaseResponseCallback.d(i, obj);
            }
        });
    }

    private void c(int i) {
        LogUtil.a("BluetoothTransImpl", "sendSleepStateCmd enabled:", Integer.valueOf(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(24);
        byte[] c = jhn.c(i);
        deviceCommand.setDataLen(c.length);
        deviceCommand.setDataContent(c);
        LogUtil.a("BluetoothTransImpl", "sendSleepStateCmd dataInfos :", Arrays.toString(c));
        d(deviceCommand);
    }

    private void b(int i, DeviceInfo deviceInfo) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(7);
        deviceCommand.setCommandID(25);
        ByteBuffer allocate = ByteBuffer.allocate(3);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put(cvx.a(cvx.e(i)));
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        if (deviceInfo != null) {
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        }
        d(deviceCommand);
    }

    private void d(DeviceCommand deviceCommand) {
        List<DeviceInfo> deviceList;
        if (TextUtils.isEmpty(deviceCommand.getmIdentify()) && ((deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "BluetoothTransImpl")) == null || deviceList.isEmpty())) {
            return;
        }
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    private void d(int i, int i2, IBluetoothResponseCallback iBluetoothResponseCallback) {
        this.c = iBluetoothResponseCallback;
        int[] iArr = {i, i2};
        this.e = iArr;
        jqj jqjVar = new jqj();
        jqjVar.a("sleep_state.bin");
        jqjVar.d(14);
        jqjVar.a(false);
        jqjVar.e(iArr);
        jqjVar.c((String) null);
        jyp.c().b(jqjVar, new ITransferSleepAndDFXFileCallback.Stub() { // from class: kec.3
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i3, String str, String str2) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "mStateCallback : errorCode", Integer.valueOf(i3));
                LogUtil.a("BluetoothTransImpl", "mStateCallback : transferDataContent : ", str);
                kec.this.b = str;
                kec.this.b();
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i3, String str) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "mStateCallback, onFailure errorCode is : ", Integer.valueOf(i3));
                int e = kec.this.e(i3);
                if (kec.this.c != null) {
                    kec.this.c.onFailure(e, str);
                } else {
                    LogUtil.h("BluetoothTransImpl", "mStateCallback, onFailure mCoreSleepCallback is null.");
                }
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i3, String str) {
                LogUtil.a("BluetoothTransImpl", "mStateCallback, onProgress progress is ", Integer.valueOf(i3));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        int[] iArr = this.e;
        if (iArr == null || iArr.length != 2) {
            LogUtil.h("BluetoothTransImpl", "getDataFile times is wrong.");
            return;
        }
        LogUtil.a("BluetoothTransImpl", "lastTime : ", Integer.valueOf(iArr[0]), "nowTime : ", Integer.valueOf(iArr[1]));
        jqj jqjVar = new jqj();
        jqjVar.a("sleep_data.bin");
        jqjVar.d(15);
        jqjVar.a(false);
        jqjVar.e(iArr);
        jqjVar.c((String) null);
        jyp.c().b(jqjVar, new ITransferSleepAndDFXFileCallback.Stub() { // from class: kec.5
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "mDataCallback : errorCode", Integer.valueOf(i));
                LogUtil.a("BluetoothTransImpl", "mDataCallback : transferDataContent : ", str);
                kec kecVar = kec.this;
                kecVar.b(i, str, kecVar.b, true);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "mDataCallback, onFailure errorCode is : ", Integer.valueOf(i));
                int e = kec.this.e(i);
                if (e != 10000) {
                    if (kec.this.c != null) {
                        kec.this.c.onFailure(e, str);
                        return;
                    } else {
                        LogUtil.h("BluetoothTransImpl", "mStateCallback, onFailure mCoreSleepCallback is null.");
                        return;
                    }
                }
                LogUtil.h("BluetoothTransImpl", "mDataCallback file is empty.");
                kec kecVar = kec.this;
                kecVar.b(i, null, kecVar.b, true);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) {
                LogUtil.a("BluetoothTransImpl", "mDataCallback, onProgress progress is ", Integer.valueOf(i));
                if (kec.this.c != null) {
                    kec.this.c.onProgress(i, null);
                } else {
                    LogUtil.h("BluetoothTransImpl", "mDataCallback, onProgress mCoreSleepCallback is null.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str, String str2, boolean z) {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_BluetoothTransImpl", "enter successProgress, sucCode ", Integer.valueOf(i), ", isNewFile ", Boolean.valueOf(z));
        LogUtil.a("BluetoothTransImpl", "successProgress mTransferStateContentPath = ", str2, ", mTransferDataContentPath = ", str);
        if (str2 == null) {
            ReleaseLogUtil.d("BTSYNC_CoreSleep_BluetoothTransImpl", "successProgress transferStateContent is null.");
            IBluetoothResponseCallback iBluetoothResponseCallback = this.c;
            if (iBluetoothResponseCallback != null) {
                iBluetoothResponseCallback.onFailure(10001, "state path is null.");
                return;
            } else {
                ReleaseLogUtil.d("BluetoothTransImpl", "successProgress mCoreSleepCallback is null.");
                return;
            }
        }
        a(str, str2, i, z);
    }

    private void a(String str, String str2, int i, boolean z) {
        if (z) {
            LogUtil.a("BluetoothTransImpl", "parseSleepFile enter.");
            jry d = jry.d();
            d.b(str);
            d.b(str2);
            d.b();
            a(z, str, str2, i);
            return;
        }
        a(z, str, str2, i);
    }

    private void a(boolean z, String str, String str2, int i) {
        IBluetoothResponseCallback iBluetoothResponseCallback = this.c;
        if (iBluetoothResponseCallback != null) {
            iBluetoothResponseCallback.onSuccess(i, a(z, str, str2));
        }
    }

    private String a(boolean z, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("isNewFile", String.valueOf(z));
        hashMap.put("coreSleepDataFilePath", str);
        hashMap.put("coreSleepStatusFilePath", str2);
        return new Gson().toJson(hashMap);
    }
}
