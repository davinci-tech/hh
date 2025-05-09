package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.constant.TransferFileReqType;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.healthcloudconfig.listener.featureconfig.ConfigFileCallback;
import com.huawei.health.healthcloudconfig.voiceconfig.VoiceConfigCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwconfigmgr.FileDataContent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.spn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jzd {
    private static volatile jzd j;
    private Bundle f;
    private int h;
    private DataReceiveCallback i = new DataReceiveCallback() { // from class: jzd.4
        @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
        public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
            LogUtil.a("HwCloudFileConfigManager", "callback onDataReceived");
            if (deviceInfo == null || cvrVar == null) {
                LogUtil.a("HwCloudFileConfigManager", "device or message is null.");
            } else if (cvrVar instanceof cvq) {
                jzd.this.b((cvq) cvrVar, deviceInfo.getDeviceIdentify());
            }
        }
    };
    private int o;
    private static final Object c = new Object();
    private static final Object b = new Object();
    private static Map<String, DeviceInfo> e = new HashMap(16);
    private static List<jyz> g = new ArrayList(16);
    private static boolean d = false;

    /* renamed from: a, reason: collision with root package name */
    private static ConfigFileCallback f14215a = new ConfigFileCallback() { // from class: jzd.2
        @Override // com.huawei.health.healthcloudconfig.listener.featureconfig.ConfigFileCallback
        public void onFileResponse(List<String> list, String str, String str2) {
            LogUtil.a("HwCloudFileConfigManager", "registe ConfigFileCallback response");
            jzd.c(list, str, str2, "");
        }
    };

    public static jzd b() {
        synchronized (b) {
            if (j == null) {
                j = new jzd();
            }
        }
        return j;
    }

    public void d(DeviceInfo deviceInfo) {
        LogUtil.a("HwCloudFileConfigManager", "onDeviceConnected,deviceName:", deviceInfo.getDeviceName());
        jzb.e();
        e.put(deviceInfo.getDeviceIdentify(), deviceInfo);
        e();
        drb.b().e(deviceInfo.getDeviceIdentify(), f14215a);
    }

    public void e(DeviceInfo deviceInfo) {
        LogUtil.a("HwCloudFileConfigManager", "onDeviceDisconnected,deviceName:", deviceInfo.getDeviceName());
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwCloudFileConfigManager");
        if (deviceList == null || deviceList.size() == 0) {
            e.remove(deviceInfo.getDeviceIdentify());
            i();
            drb.b().d(deviceInfo.getDeviceIdentify());
            return;
        }
        for (int i = 0; i < deviceList.size(); i++) {
            if (cwi.c(deviceList.get(i), 104)) {
                return;
            }
        }
        e.remove(deviceInfo.getDeviceIdentify());
        i();
        drb.b().d(deviceInfo.getDeviceIdentify());
    }

    private void e() {
        LogUtil.a("HwCloudFileConfigManager", "registerDeviceSampleListener enter");
        cuk.b().registerDeviceSampleListener("hw.unitedevice.configManager", this.i);
    }

    private void i() {
        LogUtil.a("HwCloudFileConfigManager", "unRegisterDeviceSampleListener");
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.configManager");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(cvq cvqVar, String str) {
        ReleaseLogUtil.e("R_HwCloudFileConfigManager", "enter getResult");
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (configInfoList == null || configInfoList.size() == 0) {
            LogUtil.h("HwCloudFileConfigManager", "onDataReceived sampleConfigInfos empty");
            return;
        }
        cvn cvnVar = configInfoList.get(0);
        LogUtil.a("HwCloudFileConfigManager", "onDataReceived,action:", Integer.valueOf(cvnVar.e()));
        int e2 = cvnVar.e();
        if (e2 == 2) {
            c(cvqVar, str);
            return;
        }
        if (e2 == 5) {
            a(cvqVar, str);
        } else if (e2 == 6) {
            e(cvqVar, str);
        } else {
            LogUtil.h("HwCloudFileConfigManager", "other action");
        }
    }

    private void e(cvq cvqVar, String str) {
        if (!jyw.d(cvx.e(jyw.b(cvqVar)))) {
            d(cvqVar, str);
            return;
        }
        synchronized (c) {
            LogUtil.a("HwCloudFileConfigManager", "addDownloadTask");
            boolean e2 = bkz.e(g);
            g.add(new jyz(cvqVar, str));
            if (e2) {
                c();
            } else {
                LogUtil.h("HwCloudFileConfigManager", "isRunTask is false");
            }
        }
    }

    private void c() {
        LogUtil.a("HwCloudFileConfigManager", "enter runTask");
        synchronized (c) {
            if (g.isEmpty()) {
                LogUtil.h("HwCloudFileConfigManager", "task is completed");
                return;
            }
            cvq d2 = g.get(0).d();
            String b2 = g.get(0).b();
            if (d2 == null && TextUtils.isEmpty(b2)) {
                LogUtil.h("HwCloudFileConfigManager", "sampleConfig is null");
                g.remove(0);
                c();
            }
            if (d(b2)) {
                LogUtil.a("HwCloudFileConfigManager", "task is removed, run next task");
                c();
            } else {
                d(d2, b2);
            }
        }
    }

    private void c(final cvq cvqVar, String str) {
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (configInfoList == null || configInfoList.size() == 0) {
            LogUtil.h("HwCloudFileConfigManager", "onDataReceived sampleConfigInfos empty");
            return;
        }
        cvn cvnVar = configInfoList.get(0);
        LogUtil.a("HwCloudFileConfigManager", "onDataReceived,action:", Integer.valueOf(cvnVar.e()));
        if (cvnVar.e() == 2) {
            String d2 = cvx.d(cvnVar.b());
            LogUtil.a("HwCloudFileConfigManager", "hexConfigData:", d2);
            if (drb.b().e(d2)) {
                ReleaseLogUtil.e("R_HwCloudFileConfigManager", "file has update");
                b(2, mbg.e(1), str, cvqVar.getSrcPkgName());
                drb.b().d(d2, str, new ConfigFileCallback() { // from class: jzd.3
                    @Override // com.huawei.health.healthcloudconfig.listener.featureconfig.ConfigFileCallback
                    public void onFileResponse(List<String> list, String str2, String str3) {
                        LogUtil.a("HwCloudFileConfigManager", "has update file response");
                        jzd.c(list, str2, str3, cvqVar.getSrcPkgName());
                    }
                });
                return;
            }
            b(2, mbg.e(0), str, cvqVar.getSrcPkgName());
        }
    }

    private void a(final cvq cvqVar, String str) {
        String b2 = jyw.b(cvqVar);
        final String e2 = cvx.e(b2);
        drt.a(str, b2, new VoiceConfigCallback() { // from class: jzd.1
            @Override // com.huawei.health.healthcloudconfig.voiceconfig.VoiceConfigCallback
            public void onSuccess(String str2, List<drm> list) {
                if (!bkz.e(list)) {
                    List<FileDataContent.FileData> c2 = jzd.this.c(list);
                    FileDataContent b3 = jzd.this.b(e2);
                    if (b3 == null) {
                        LogUtil.h("HwCloudFileConfigManager", "checkFileUpdate, fileDataContent is null");
                        return;
                    }
                    b3.setFileData(c2);
                    String e3 = HiJsonUtil.e(b3);
                    LogUtil.a("HwCloudFileConfigManager", "checkFileUpdate, data:", e3);
                    jzd.b(5, e3, str2, cvqVar.getSrcPkgName());
                    return;
                }
                ReleaseLogUtil.d("R_HwCloudFileConfigManager", "checkFileUpdate is not update");
            }

            @Override // com.huawei.health.healthcloudconfig.voiceconfig.VoiceConfigCallback
            public void onFailure(String str2, String str3) {
                LogUtil.h("HwCloudFileConfigManager", "checkFileUpdate is fail: ", str3);
            }
        });
    }

    private void d(cvq cvqVar, String str) {
        ReleaseLogUtil.e("R_HwCloudFileConfigManager", "enter downloadFile");
        String b2 = jyw.b(cvqVar);
        boolean d2 = jyw.d(cvx.e(b2));
        LogUtil.a("HwCloudFileConfigManager", "downloadFile, isNotice: ", Boolean.valueOf(d2));
        Bundle bLH_ = bLH_(cvx.e(b2), str, cvqVar.getSrcPkgName());
        if (d2) {
            jzb.e().bLA_(bLH_);
        } else {
            bLS_(bLH_, false);
        }
    }

    private Bundle bLH_(String str, String str2, String str3) {
        LogUtil.a("HwCloudFileConfigManager", "getFileBundle, data: ", str, " wearName: ", str3);
        Bundle bundle = new Bundle();
        bundle.putString("hex_data", str);
        bundle.putString("device_id", str2);
        bundle.putString("wear_name", str3);
        return bundle;
    }

    public void bLS_(final Bundle bundle, final boolean z) {
        ReleaseLogUtil.e("R_HwCloudFileConfigManager", "startDownload");
        if (bundle == null) {
            LogUtil.h("HwCloudFileConfigManager", "startDownload,bundle is null");
            return;
        }
        if (z) {
            jzb.e().bLz_(0, false, bundle);
        }
        final String string = bundle.getString("hex_data");
        String c2 = cvx.c(string);
        final String string2 = bundle.getString("device_id");
        drt.e(string2, c2, new VoiceConfigCallback() { // from class: jzd.5
            @Override // com.huawei.health.healthcloudconfig.voiceconfig.VoiceConfigCallback
            public void onSuccess(String str, List<drm> list) {
                if (z) {
                    jzd.this.h = 0;
                }
                FileDataContent b2 = jzd.this.b(string);
                if (b2 == null) {
                    LogUtil.h("HwCloudFileConfigManager", "startDownload, fileDataContent is null");
                    return;
                }
                b2.setFileData(jzd.this.c(list));
                bundle.putString("hex_data", HiJsonUtil.e(b2));
                bundle.putStringArrayList("file_list_info", jzd.this.b(list));
                jzd.this.bLT_(bundle, z);
            }

            @Override // com.huawei.health.healthcloudconfig.voiceconfig.VoiceConfigCallback
            public void onFailure(String str, String str2) {
                LogUtil.h("HwCloudFileConfigManager", "startDownload is fail: ", str2);
                if (z) {
                    jzd.this.h = 0;
                    jzb.e().bLz_(0, true, bundle);
                }
                jzd.b(4, string, string2, bundle.getString("wear_name"));
            }

            @Override // com.huawei.health.healthcloudconfig.voiceconfig.VoiceConfigCallback
            public void onProgress(long j2, long j3, boolean z2, String str) {
                jzd.this.bLI_(j2, j3, z, bundle);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bLI_(long j2, long j3, boolean z, Bundle bundle) {
        if (z) {
            if (j3 == 0) {
                LogUtil.h("HwCloudFileConfigManager", "showDownloadProgress, totalBytes is not correct");
                return;
            }
            int i = (int) ((j2 * 100) / j3);
            if (i - this.h <= 5) {
                LogUtil.c("HwCloudFileConfigManager", "startDownload, not update progress ");
                return;
            }
            this.h = i;
            LogUtil.c("HwCloudFileConfigManager", "startDownload, progress is: ", Integer.valueOf(i));
            jzb.e().bLz_(i, false, bundle);
        }
    }

    public void bLT_(Bundle bundle, boolean z) {
        LogUtil.a("HwCloudFileConfigManager", "startTransFor");
        if (bundle == null) {
            LogUtil.a("HwCloudFileConfigManager", "startTransFor,bundle is null");
            return;
        }
        if (z) {
            jzb.e().bLB_(0, bundle);
        }
        if (TextUtils.isEmpty(jyw.c(bundle.getString("hex_data")))) {
            LogUtil.a("HwCloudFileConfigManager", "wearName is null");
            bLM_(bundle, z);
        } else {
            bLK_(bundle, z);
        }
    }

    private void bLM_(Bundle bundle, boolean z) {
        LogUtil.a("HwCloudFileConfigManager", "startTransferFile");
        String string = bundle.getString("wear_name");
        String string2 = bundle.getString("device_id");
        if (z && e(string2)) {
            LogUtil.h("HwCloudFileConfigManager", "startTransferFile, device is not connected");
            jzb.e().bLC_(true, bundle);
            return;
        }
        String string3 = bundle.getString("hex_data");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("file_list_info");
        if (bkz.e(stringArrayList)) {
            LogUtil.h("HwCloudFileConfigManager", "startTransferFile is empty");
            b(4, string3, string2, string);
            if (z) {
                jzb.e().bLC_(true, bundle);
                return;
            }
            return;
        }
        bLN_(0, stringArrayList, bundle, z);
    }

    private void bLN_(final int i, final List<String> list, final Bundle bundle, final boolean z) {
        LogUtil.a("HwCloudFileConfigManager", "startTransferFileIndex: ", Integer.valueOf(i), Integer.valueOf(list.size()), Boolean.valueOf(z));
        String str = list.get(i);
        final int size = list.size();
        final jqj d2 = d(str, bundle.getString("device_id"));
        final int i2 = i * 100;
        jyx.a().c(d2, new IAppTransferFileResultAIDLCallback.Stub() { // from class: jzd.10
            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileTransferState(int i3, String str2) throws RemoteException {
                jzd.this.bLJ_(i3, z, i2, size, bundle);
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onUpgradeFailed(int i3, String str2) throws RemoteException {
                LogUtil.a("HwCloudFileConfigManager", "onUpgradeFailed:", Integer.valueOf(i3), ", fileName:", d2.j());
                jzd.this.bLO_(i3, bundle, list, i);
            }

            @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
            public void onFileRespond(int i3, String str2) throws RemoteException {
                LogUtil.a("HwCloudFileConfigManager", "onFileRespond:", Integer.valueOf(i3), ",fileName:", d2.j());
                jzd.this.bLO_(i3, bundle, list, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bLO_(int i, Bundle bundle, List<String> list, int i2) {
        String string = bundle.getString("wear_name");
        String string2 = bundle.getString("device_id");
        String string3 = bundle.getString("hex_data");
        int size = list.size();
        boolean d2 = jyw.d(string3);
        if (i != 1) {
            bLQ_(d2, true, bundle);
            b(4, string3, string2, string);
        } else {
            if (i2 >= size - 1) {
                bLQ_(d2, false, bundle);
                b(1, string3, string2, string);
                e(list, string3);
                return;
            }
            bLN_(i2 + 1, list, bundle, d2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bLJ_(int i, boolean z, int i2, int i3, Bundle bundle) {
        LogUtil.c("HwCloudFileConfigManager", "showTransProgress", Integer.valueOf(i));
        if (z) {
            int i4 = (i + i2) / i3;
            if (i4 - this.o <= 5) {
                LogUtil.c("HwCloudFileConfigManager", "showTransProgress, progress is not update");
            } else {
                this.o = i4;
                jzb.e().bLB_(i4, bundle);
            }
        }
    }

    private void bLQ_(boolean z, boolean z2, Bundle bundle) {
        if (z) {
            this.o = 0;
            jzb.e().bLC_(z2, bundle);
        }
    }

    private boolean e(String str) {
        LogUtil.a("HwCloudFileConfigManager", "isDisconnected: ", blt.a(str));
        if (e.get(str) == null) {
            LogUtil.h("HwCloudFileConfigManager", "isDisconnected, sDeviceInfoMap is empty");
            return true;
        }
        HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
        hwGetDevicesParameter.setDeviceIdentify(str);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwCloudFileConfigManager");
        if (bkz.e(deviceList)) {
            LogUtil.h("HwCloudFileConfigManager", "isDisconnected, deviceInfoList is empty");
            return true;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo.getDeviceConnectState() == 2) {
            return false;
        }
        LogUtil.a("HwCloudFileConfigManager", "isDisconnected, device name: ", deviceInfo.getDeviceName());
        return true;
    }

    private void b(SendCallback sendCallback, File file, String str, String str2) {
        spn.b bVar = new spn.b();
        bVar.a(file);
        spn e2 = bVar.e();
        snt sntVar = new snt();
        sntVar.e(WearEngineModule.FEATURE_SOURCE_MODULE);
        sntVar.i(str);
        sntVar.f(OfflineMapWearEngineManager.WEAR_FINGERPRINT);
        sntVar.e(e2);
        b(sph.e(e.get(str2)), sntVar, sendCallback);
    }

    private void b(UniteDevice uniteDevice, snt sntVar, SendCallback sendCallback) {
        snq.c().p2pSendForWearEngine(BaseApplication.getContext(), uniteDevice, sntVar, sendCallback);
    }

    private void bLK_(Bundle bundle, boolean z) {
        ReleaseLogUtil.e("R_HwCloudFileConfigManager", "startTransFileP2p");
        String string = bundle.getString("wear_name");
        String string2 = bundle.getString("device_id");
        if (z && e(string2)) {
            LogUtil.h("HwCloudFileConfigManager", "startTransFileP2p, device is not connected");
            jzb.e().bLC_(true, bundle);
            return;
        }
        String string3 = bundle.getString("hex_data");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("file_list_info");
        if (bkz.e(stringArrayList)) {
            LogUtil.h("HwCloudFileConfigManager", "startTransferFile is empty");
            b(4, string3, string2, string);
            if (z) {
                jzb.e().bLC_(true, bundle);
                return;
            }
            return;
        }
        bLL_(0, stringArrayList, bundle, z);
    }

    private void bLL_(final int i, final List<String> list, final Bundle bundle, final boolean z) {
        LogUtil.a("HwCloudFileConfigManager", "startTransFileP2pIndex: ", Integer.valueOf(i), Integer.valueOf(list.size()), Boolean.valueOf(z));
        String string = bundle.getString("hex_data");
        String string2 = bundle.getString("device_id");
        String str = list.get(i);
        final int size = list.size();
        LogUtil.h("HwCloudFileConfigManager", "filePath, filepath: ", str);
        final int i2 = i * 100;
        b(new SendCallback() { // from class: jzd.7
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i3) {
                LogUtil.h("HwCloudFileConfigManager", "onSendResult, errCode: ", Integer.valueOf(i3));
                jzd.this.bLP_(i3, bundle, list, i);
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j2) {
                jzd.this.bLJ_((int) j2, z, i2, size, bundle);
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
                LogUtil.c("HwCloudFileConfigManager", "onFileTransferReport, transferWay: ", str2);
            }
        }, new File(str.trim()), jyw.c(string), string2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bLP_(int i, Bundle bundle, List<String> list, int i2) {
        String string = bundle.getString("wear_name");
        String string2 = bundle.getString("device_id");
        String string3 = bundle.getString("hex_data");
        int size = list.size();
        boolean d2 = jyw.d(string3);
        if (i != 207) {
            bLQ_(d2, true, bundle);
            b(4, string3, string2, string);
        } else {
            if (i2 >= size - 1) {
                bLQ_(d2, false, bundle);
                b(1, string3, string2, string);
                e(list, string3);
                return;
            }
            bLL_(i2 + 1, list, bundle, d2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FileDataContent b(String str) {
        LogUtil.a("HwCloudFileConfigManager", "getFileDataContent,hexContent: ", str);
        FileDataContent fileDataContent = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwCloudFileConfigManager", "getFileDataContent, hexContent is null");
            return null;
        }
        try {
            FileDataContent fileDataContent2 = (FileDataContent) HiJsonUtil.e(str, FileDataContent.class);
            if (fileDataContent2 != null) {
                return fileDataContent2;
            }
            try {
                LogUtil.h("HwCloudFileConfigManager", "getFileDataContent, fileDataContent is null");
                return fileDataContent2;
            } catch (JsonSyntaxException unused) {
                fileDataContent = fileDataContent2;
                LogUtil.h("HwCloudFileConfigManager", "getFileDataContent is JsonSyntaxException");
                return fileDataContent;
            }
        } catch (JsonSyntaxException unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FileDataContent.FileData> c(List<drm> list) {
        ArrayList arrayList = new ArrayList(16);
        for (drm drmVar : list) {
            FileDataContent.FileData fileData = new FileDataContent.FileData();
            fileData.setFileName(drmVar.b());
            fileData.setVersion(drmVar.d());
            fileData.setFileSize(drmVar.c());
            arrayList.add(fileData);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<String> b(List<drm> list) {
        ArrayList<String> arrayList = new ArrayList<>(16);
        Iterator<drm> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    private void e(List<String> list, String str) {
        if (jyw.e(str)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                kam.c(it.next());
            }
        }
    }

    public static void b(int i, String str, String str2, String str3) {
        LogUtil.a("HwCloudFileConfigManager", "action:", Integer.valueOf(i), ",data:", str);
        DeviceInfo deviceInfo = e.get(str2);
        cvq d2 = d(i, str, str3);
        boolean sendSampleConfigCommand = cuk.b().sendSampleConfigCommand(deviceInfo, d2, "HwCloudFileConfigManager");
        LogUtil.a("HwCloudFileConfigManager", "constructSampleConfig: ", d2);
        if (sendSampleConfigCommand) {
            return;
        }
        LogUtil.h("HwCloudFileConfigManager", "sendSettingDeviceCommand fail");
    }

    private static cvq d(int i, String str, String str2) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.configManager");
        if (TextUtils.isEmpty(str2)) {
            cvqVar.setWearPkgName("featureManager");
        } else {
            cvqVar.setWearPkgName(str2);
        }
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900100007L);
        cvnVar.d(i);
        cvnVar.c(mbg.a(mbg.e(str)));
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(List<String> list, final String str, final String str2, final String str3) {
        if (list == null || list.size() == 0) {
            LogUtil.h("HwCloudFileConfigManager", "commonTransferFile configFileInfos is empty");
            b(4, str, str2, str3);
            return;
        }
        int i = 0;
        while (i < list.size()) {
            final jqj d2 = d(list.get(i), str2);
            LogUtil.a("HwCloudFileConfigManager", "onFileResponse,fileName:", d2.j(), ",filePath:", d2.h());
            final boolean z = i == list.size() - 1;
            jyx.a().c(d2, new IAppTransferFileResultAIDLCallback.Stub() { // from class: jzd.9
                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onFileTransferState(int i2, String str4) throws RemoteException {
                }

                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onUpgradeFailed(int i2, String str4) throws RemoteException {
                    LogUtil.a("HwCloudFileConfigManager", "onUpgradeFailed:", Integer.valueOf(i2), ", fileName:", jqj.this.j());
                    boolean unused = jzd.d = true;
                    if (z) {
                        LogUtil.a("HwCloudFileConfigManager", "commonTransferFile last one");
                        if (jzd.d) {
                            boolean unused2 = jzd.d = false;
                            jzd.b(4, str, str2, str3);
                        } else {
                            jzd.b(1, str, str2, str3);
                        }
                    }
                }

                @Override // com.huawei.hwservicesmgr.IAppTransferFileResultAIDLCallback
                public void onFileRespond(int i2, String str4) throws RemoteException {
                    LogUtil.a("HwCloudFileConfigManager", "onFileRespond:", Integer.valueOf(i2), ",fileName:", jqj.this.j());
                    if (i2 != 1) {
                        boolean unused = jzd.d = true;
                    }
                    if (z) {
                        LogUtil.a("HwCloudFileConfigManager", "commonTransferFile last one");
                        if (jzd.d) {
                            boolean unused2 = jzd.d = false;
                            jzd.b(4, str, str2, str3);
                        } else {
                            ReleaseLogUtil.e("R_HwCloudFileConfigManager", "send feature file success");
                            jzd.b(1, str, str2, str3);
                        }
                    }
                }
            });
            i++;
        }
    }

    private static jqj d(String str, String str2) {
        String name = new File(str.trim()).getName();
        LogUtil.a("HwCloudFileConfigManager", "ConstructTransFileInfo,filePath:", str, ",fileName:", name);
        jqj jqjVar = new jqj();
        jqjVar.a(name);
        jqjVar.f(str);
        jqjVar.d(15);
        jqjVar.c(TransferFileReqType.APP_DELIVERY);
        jqjVar.c(str2);
        return jqjVar;
    }

    public void a() {
        LogUtil.a("HwCloudFileConfigManager", "checkTaskStatus");
        synchronized (c) {
            if (bkz.e(g)) {
                LogUtil.h("HwCloudFileConfigManager", "checkTaskStatus, all task is run");
            } else {
                g.remove(0);
                c();
            }
        }
    }

    private boolean d(String str) {
        if (!e(str)) {
            return false;
        }
        Iterator<jyz> it = g.iterator();
        while (it.hasNext()) {
            if (it.next().b().equals(str)) {
                it.remove();
            }
        }
        return true;
    }

    public void bLR_(Bundle bundle) {
        this.f = bundle;
    }

    public void c(String str) {
        char c2;
        LogUtil.a("HwCloudFileConfigManager", "startRunTaskFromUI, message: ", str);
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -986909602) {
            if (str.equals("start_transfor")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -839973947) {
            if (hashCode == -590900557 && str.equals("task_complete")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("start_download")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            jzb.e().d();
            bLT_(this.f, true);
        } else if (c2 == 1) {
            jzb.e().d();
            bLS_(this.f, true);
        } else if (c2 == 2) {
            jzb.e().d();
            a();
        } else {
            LogUtil.h("HwCloudFileConfigManager", "startRunTaskFromUI, other message: ", str);
        }
    }
}
