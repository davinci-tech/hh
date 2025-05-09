package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.CloudDeviceInfo;
import com.huawei.hwdevice.phoneprocess.mgr.intelligentlifemgr.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class kdi implements DataReceiveCallback {
    private static kdi d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Boolean> f14303a = new HashMap(16);
    private final BroadcastReceiver c = new BroadcastReceiver() { // from class: kdi.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("HwSyncDeviceInfoMgr", "mDeviceStatusReceiver intent is null.");
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                LogUtil.h("HwSyncDeviceInfoMgr", "mDeviceStatusReceiver action is null");
                return;
            }
            LogUtil.a("HwSyncDeviceInfoMgr", "mDeviceStatusReceiver action = ", action);
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                if (deviceInfo == null) {
                    return;
                }
                LogUtil.a("HwSyncDeviceInfoMgr", "mDeviceStatusReceiver deviceInfo = ", deviceInfo.toString(), ",state = ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                kdi.this.a(deviceInfo);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo) {
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 1) {
            HwGetDevicesParameter hwGetDevicesParameter = new HwGetDevicesParameter();
            hwGetDevicesParameter.setDeviceIdentify(deviceInfo.getDeviceIdentify());
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.IDENTIFY_DEVICES, hwGetDevicesParameter, "HwSyncDeviceInfoMgr");
            if (deviceList == null || deviceList.size() == 0) {
                this.f14303a.put(deviceInfo.getDeviceIdentify(), true);
                return;
            }
            return;
        }
        if (deviceConnectState != 2) {
            return;
        }
        this.f14303a.put(deviceInfo.getDeviceIdentify(), false);
        if ("main_relationship".equals(deviceInfo.getRelationship()) && cwi.c(deviceInfo, 191)) {
            LogUtil.a("HwSyncDeviceInfoMgr", "dealDeviceConnectState main device connected");
        } else if (cwi.c(deviceInfo, 92) && "main_relationship".equals(deviceInfo.getRelationship())) {
            d();
            d(deviceInfo);
        }
    }

    private kdi() {
        a();
        kdj.e().a("HwSyncDeviceInfoMgr");
        LogUtil.a("HwSyncDeviceInfoMgr", "create HwSyncDeviceInfoMgr.");
    }

    public static kdi c() {
        kdi kdiVar;
        synchronized (e) {
            if (d == null) {
                d = new kdi();
            }
            kdiVar = d;
        }
        return kdiVar;
    }

    private void a() {
        LogUtil.a("HwSyncDeviceInfoMgr", "registerBroadcastReceiver");
        d();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.c, intentFilter, LocalBroadcast.c, null);
    }

    public void d(List<String> list, boolean z) {
        if (z) {
            d(list);
        }
        jsz.b(BaseApplication.getContext()).unPair(list, z);
    }

    public void b(DeviceInfo deviceInfo) {
        e(deviceInfo);
    }

    private void d(DeviceInfo deviceInfo) {
        List<DeviceInfo> b = b();
        if (b.size() > 0) {
            LogUtil.a("HwSyncDeviceInfoMgr", "sendSyncAllDeviceCommand send command");
            c(deviceInfo, b, 1);
        }
    }

    private void e(DeviceInfo deviceInfo) {
        List<DeviceInfo> deviceList;
        DeviceInfo deviceInfo2;
        if ((!this.f14303a.containsKey(deviceInfo.getDeviceIdentify()) || this.f14303a.get(deviceInfo.getDeviceIdentify()).booleanValue()) && (deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwSyncDeviceInfoMgr")) != null && deviceList.size() > 0 && (deviceInfo2 = deviceList.get(0)) != null && cwi.c(deviceInfo2, 92)) {
            LogUtil.a("HwSyncDeviceInfoMgr", "sendSyncAddDeviceCommand send command");
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(deviceInfo);
            c(deviceInfo2, arrayList, 3);
        }
    }

    private void d(List<String> list) {
        e(list);
        c(list);
    }

    private void e(List<String> list) {
        if (list == null) {
            LogUtil.h("HwSyncDeviceInfoMgr", "unPairEarphone identifyList is null");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwSyncDeviceInfoMgr");
        if (deviceList == null || deviceList.size() <= 0) {
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("HwSyncDeviceInfoMgr", "unPairEarphone mainDeviceInfo is null");
            return;
        }
        if (!list.contains(deviceInfo.getDeviceIdentify())) {
            LogUtil.h("HwSyncDeviceInfoMgr", "unPairEarphone mainDeviceInfo is not match. ");
        } else if (!cwi.c(deviceInfo, 112)) {
            LogUtil.h("HwSyncDeviceInfoMgr", "unPairEarphone capability is not match");
        } else {
            kcf.b().e(deviceInfo);
        }
    }

    private void c(List<String> list) {
        if (list == null) {
            LogUtil.h("HwSyncDeviceInfoMgr", "sendSyncDeleteDeviceCommand identifyList is null");
            return;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwSyncDeviceInfoMgr");
        if (deviceList == null || deviceList.size() <= 0) {
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        if (deviceInfo == null) {
            LogUtil.h("HwSyncDeviceInfoMgr", "sendSyncDeleteDeviceCommand mainDeviceInfo is null");
            return;
        }
        if (!cwi.c(deviceInfo, 92)) {
            LogUtil.h("HwSyncDeviceInfoMgr", "sendSyncDeleteDeviceCommand capability is not match");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        List<DeviceInfo> e2 = e();
        for (String str : list) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("HwSyncDeviceInfoMgr", "sendSyncDeleteDeviceCommand identify is empty");
            } else if (str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.h("HwSyncDeviceInfoMgr", "sendSyncDeleteDeviceCommand delete device is main device");
            } else {
                DeviceInfo a2 = a(str, e2);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        if (arrayList.size() > 0) {
            LogUtil.a("HwSyncDeviceInfoMgr", "sendSyncDeleteDeviceCommand send command");
            c(deviceInfo, arrayList, 4);
        }
    }

    private DeviceInfo a(String str, List<DeviceInfo> list) {
        if (list != null && list.size() > 0) {
            for (DeviceInfo deviceInfo : list) {
                if (str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    return deviceInfo;
                }
            }
        }
        return null;
    }

    private List<DeviceInfo> b() {
        ArrayList arrayList = new ArrayList(16);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwSyncDeviceInfoMgr");
        if (deviceList.size() > 0) {
            for (int i = 0; i < deviceList.size(); i++) {
                DeviceInfo deviceInfo = deviceList.get(i);
                if (a(deviceInfo, deviceInfo.getDeviceModel())) {
                    arrayList.add(deviceInfo);
                }
            }
        }
        List<CloudDeviceInfo> c = jyr.a().c();
        if (c.size() > 0) {
            for (int i2 = 0; i2 < c.size(); i2++) {
                CloudDeviceInfo cloudDeviceInfo = c.get(i2);
                if (a(cloudDeviceInfo, cloudDeviceInfo.getDeviceModel())) {
                    arrayList.add(cloudDeviceInfo);
                }
            }
        }
        return arrayList;
    }

    private boolean a(DeviceInfo deviceInfo, String str) {
        return deviceInfo.getProductType() == 75 && (str.contains("Bolt") || str.contains("bolt"));
    }

    private List<DeviceInfo> e() {
        ArrayList arrayList = new ArrayList(16);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwSyncDeviceInfoMgr");
        if (deviceList.size() > 0) {
            arrayList.addAll(deviceList);
        }
        List<CloudDeviceInfo> c = jyr.a().c();
        if (c.size() > 0) {
            arrayList.addAll(c);
        }
        return arrayList;
    }

    public void d() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.syncdevice", this);
    }

    private void c(DeviceInfo deviceInfo, List<DeviceInfo> list, int i) {
        if (deviceInfo == null) {
            LogUtil.h("HwSyncDeviceInfoMgr", "sendSyncDeviceCommand currentDeviceInfo is null");
            return;
        }
        if (list == null || list.size() <= 0) {
            LogUtil.h("HwSyncDeviceInfoMgr", "sendSyncDeviceCommand deviceList is null or empty");
            return;
        }
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceIdentify() != null && next.getDeviceIdentify().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                list.remove(next);
                break;
            }
        }
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.syncdevice");
        cvqVar.setWearPkgName("syncdevice");
        ArrayList arrayList = new ArrayList(10);
        if (list.size() == 0) {
            LogUtil.a("HwSyncDeviceInfoMgr", "no pairDevice need to sync.");
            a(deviceInfo, i, cvqVar, arrayList);
            return;
        }
        int i2 = 0;
        for (DeviceInfo deviceInfo2 : list) {
            i2++;
            if (i2 == list.size()) {
                LogUtil.a("HwSyncDeviceInfoMgr", "sendSyncDeviceCommand is last device");
                arrayList.add(c(deviceInfo2, i, true));
                cvqVar.setConfigInfoList(arrayList);
                cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "HwSyncDeviceInfoMgr");
            } else {
                arrayList.add(c(deviceInfo2, i, false));
                if (arrayList.size() == 10) {
                    LogUtil.a("HwSyncDeviceInfoMgr", "sendSyncDeviceCommand sampleConfigInfos size is SEND_DEVICE_SIZE");
                    cvqVar.setConfigInfoList(arrayList);
                    cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "HwSyncDeviceInfoMgr");
                    arrayList.clear();
                }
            }
        }
    }

    private void a(DeviceInfo deviceInfo, int i, cvq cvqVar, List<cvn> list) {
        list.add(c((DeviceInfo) null, i, true));
        cvqVar.setConfigInfoList(list);
        cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "HwSyncDeviceInfoMgr");
    }

    private cvn c(DeviceInfo deviceInfo, int i, boolean z) {
        String e2;
        cvn cvnVar = new cvn();
        cvnVar.e(900100003L);
        cvnVar.d(i);
        StringBuilder sb = new StringBuilder(10);
        if (z) {
            e2 = cvx.e(1);
        } else {
            e2 = cvx.e(0);
        }
        sb.append(cvx.e(8) + cvx.e(1) + e2);
        if (deviceInfo == null) {
            LogUtil.a("HwSyncDeviceInfoMgr", "structStringBuilder: ", sb.toString());
            cvnVar.c(cvx.a(sb.toString()));
            return cvnVar;
        }
        e(deviceInfo, sb);
        LogUtil.a("HwSyncDeviceInfoMgr", "constructSampleConfigInfo structStringBuilder: ", sb.toString());
        cvnVar.c(cvx.a(sb.toString()));
        return cvnVar;
    }

    private void e(DeviceInfo deviceInfo, StringBuilder sb) {
        if (!TextUtils.isEmpty(deviceInfo.getDeviceModel())) {
            String c = cvx.c(deviceInfo.getDeviceModel());
            sb.append(cvx.e(9) + cvx.e(c.length() / 2) + c);
        }
        sb.append(cvx.e(10) + cvx.e(2) + cvx.a(deviceInfo.getProductType()));
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        if (!TextUtils.isEmpty(hiLinkDeviceId)) {
            String c2 = cvx.c(hiLinkDeviceId);
            sb.append(cvx.e(11) + cvx.e(c2.length() / 2) + c2);
        }
        if (!TextUtils.isEmpty(deviceInfo.getDeviceName())) {
            String c3 = cvx.c(deviceInfo.getDeviceName());
            sb.append(cvx.e(12) + cvx.e(c3.length() / 2) + c3);
        }
        if (!TextUtils.isEmpty(deviceInfo.getSecurityUuid())) {
            String c4 = cvx.c(deviceInfo.getSecurityUuid());
            sb.append(cvx.e(13) + cvx.e(c4.length() / 2) + c4);
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            return;
        }
        d(deviceInfo, sb);
    }

    private void d(DeviceInfo deviceInfo, StringBuilder sb) {
        String c = cvx.c(deviceInfo.getDeviceIdentify());
        sb.append(cvx.e(14) + cvx.e(c.length() / 2) + c);
        String switchSettingFromDb = jqi.a().getSwitchSettingFromDb(jrh.b(deviceInfo.getDeviceIdentify()));
        if (TextUtils.isEmpty(switchSettingFromDb)) {
            return;
        }
        jns a2 = jrh.a(switchSettingFromDb);
        sb.append(cvx.e(15) + cvx.e(8) + String.format(Locale.ENGLISH, "%016d", Long.valueOf(a2.b())));
        sb.append(cvx.e(16) + cvx.e(2) + cvx.a(a2.a()));
        sb.append(cvx.e(17) + cvx.e(1) + cvx.e(a2.e()));
        sb.append(cvx.e(18) + cvx.e(1) + cvx.e(a2.d()));
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (deviceInfo == null) {
            LogUtil.h("HwSyncDeviceInfoMgr", "onDataReceived deviceInfo is null");
            return;
        }
        if (cvrVar instanceof cvq) {
            LogUtil.a("HwSyncDeviceInfoMgr", "onDataReceived sampleBase instanceof SampleConfig");
            List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
            if (configInfoList == null || configInfoList.size() <= 0) {
                LogUtil.h("HwSyncDeviceInfoMgr", "onDataReceived sampleConfigInfos is null or empty");
            } else {
                e(configInfoList, deviceInfo);
            }
        }
    }

    private void e(List<cvn> list, DeviceInfo deviceInfo) {
        List<DeviceInfo> arrayList = new ArrayList<>(10);
        for (cvn cvnVar : list) {
            String d2 = cvx.d(cvnVar.b());
            LogUtil.a("HwSyncDeviceInfoMgr", "configDeviceDbInfo configData = ", d2);
            CloudDeviceInfo e2 = e(d2);
            if (e2 != null) {
                if (cvnVar.e() == 1 || cvnVar.e() == 3) {
                    if (!a(e2) && !d(e2)) {
                        e2.setIsCloudDevice(true);
                        arrayList.add(e2);
                        if (a(e2.getProductType())) {
                            jyr.a().e(e2);
                        }
                    }
                } else if (cvnVar.e() == 4) {
                    if (a(e2)) {
                        ArrayList arrayList2 = new ArrayList(10);
                        arrayList2.add(e2.getDeviceIdentify());
                        jtc.c().unPair(arrayList2, true);
                        a(e2.getSecurityUuid() + "#ANDROID21");
                        return;
                    }
                    if (d(e2)) {
                        jyr.a().c(e2);
                        a(e2.getSecurityUuid() + "#ANDROID21");
                        h();
                    }
                } else if (cvnVar.e() == 5) {
                    c(e2);
                } else {
                    LogUtil.h("HwSyncDeviceInfoMgr", "configDeviceDbInfo configAction is not match");
                }
            }
        }
        b(arrayList);
    }

    private void c(CloudDeviceInfo cloudDeviceInfo) {
        String deviceReportInfo = cloudDeviceInfo.getDeviceReportInfo();
        if (deviceReportInfo.length() % 4 == 0) {
            Map<Integer, Integer> hashMap = new HashMap<>();
            int i = 0;
            while (i < deviceReportInfo.length()) {
                int i2 = i + 4;
                String substring = deviceReportInfo.substring(i, i2);
                hashMap.put(Integer.valueOf(CommonUtil.w(substring.substring(0, 2))), Integer.valueOf(CommonUtil.w(substring.substring(2))));
                i = i2;
            }
            List<DeviceInfo> arrayList = new ArrayList<>(16);
            HashMap<Integer, Integer> hashMap2 = new HashMap<>();
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwSyncDeviceInfoMgr");
            Collections.sort(deviceList, new Comparator<DeviceInfo>() { // from class: kdi.1
                @Override // java.util.Comparator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public int compare(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
                    return (int) (deviceInfo2.getLastConnectedTime() - deviceInfo.getLastConnectedTime());
                }
            });
            if (deviceList.size() > 0) {
                d(hashMap, arrayList, hashMap2, deviceList);
            }
            List<CloudDeviceInfo> c = jyr.a().c();
            if (c.size() > 0) {
                List<DeviceInfo> arrayList2 = new ArrayList<>();
                arrayList2.addAll(c);
                d(hashMap, arrayList, hashMap2, arrayList2);
            }
            List<DeviceInfo> deviceList2 = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwSyncDeviceInfoMgr");
            if (deviceList2 == null || deviceList2.size() <= 0 || arrayList.size() <= 0) {
                return;
            }
            c(deviceList2.get(0), arrayList, 1);
        }
    }

    private void d(Map<Integer, Integer> map, List<DeviceInfo> list, HashMap<Integer, Integer> hashMap, List<DeviceInfo> list2) {
        for (int i = 0; i < list2.size(); i++) {
            DeviceInfo deviceInfo = list2.get(i);
            int productType = deviceInfo.getProductType();
            Integer num = hashMap.get(Integer.valueOf(productType));
            Integer num2 = map.get(Integer.valueOf(productType));
            if (!map.keySet().contains(Integer.valueOf(productType))) {
                LogUtil.a("HwSyncDeviceInfoMgr", "getMatchDeviceList current productType not contains productTypeMap");
            } else if (num == null || num != num2) {
                list.add(deviceInfo);
                if (!hashMap.containsKey(Integer.valueOf(productType))) {
                    hashMap.put(Integer.valueOf(productType), 1);
                } else {
                    hashMap.put(Integer.valueOf(productType), Integer.valueOf(hashMap.get(Integer.valueOf(productType)).intValue() + 1));
                }
            }
        }
    }

    private void h() {
        for (int i = 0; i < bfe.b.size(); i++) {
            KeyValDbManager.b(BaseApplication.getContext()).e("profile_devices_last_query_time_01" + bfe.b.get(i), String.valueOf(0));
        }
    }

    private CloudDeviceInfo e(String str) {
        CloudDeviceInfo cloudDeviceInfo = null;
        try {
            List<cwd> e2 = new cwl().a(str).e();
            if (e2 != null && e2.size() > 0) {
                CloudDeviceInfo cloudDeviceInfo2 = new CloudDeviceInfo();
                try {
                    jns jnsVar = new jns();
                    for (cwd cwdVar : e2) {
                        int w = CommonUtil.w(cwdVar.e());
                        if (w != 19) {
                            switch (w) {
                                case 8:
                                    LogUtil.a("HwSyncDeviceInfoMgr", "parseConfigData flag type is ", Integer.valueOf(CommonUtil.w(cwdVar.c())));
                                    break;
                                case 9:
                                    cloudDeviceInfo2.setDeviceModel(cvx.e(cwdVar.c()));
                                    break;
                                case 10:
                                    cloudDeviceInfo2.setProductType(CommonUtil.w(cwdVar.c()));
                                    break;
                                case 11:
                                    cloudDeviceInfo2.setHiLinkDeviceId(cvx.e(cwdVar.c()));
                                    break;
                                case 12:
                                    cloudDeviceInfo2.setDeviceName(cvx.e(cwdVar.c()));
                                    break;
                                case 13:
                                    cloudDeviceInfo2.setUuid(cwdVar.c());
                                    break;
                                case 14:
                                    cloudDeviceInfo2.setDeviceIdentify(cvx.e(cwdVar.c()));
                                    break;
                                default:
                                    e(jnsVar, cwdVar);
                                    break;
                            }
                        } else {
                            LogUtil.a("HwSyncDeviceInfoMgr", "parseConfigData value=", cwdVar.c());
                            cloudDeviceInfo2.setDeviceReportInfo(cwdVar.c());
                        }
                    }
                    c(cloudDeviceInfo2.getDeviceIdentify(), jnsVar);
                    return cloudDeviceInfo2;
                } catch (cwg e3) {
                    e = e3;
                    cloudDeviceInfo = cloudDeviceInfo2;
                    LogUtil.b("HwSyncDeviceInfoMgr", "parseConfigData e1 = ", ExceptionUtils.d(e));
                    return cloudDeviceInfo;
                }
            }
            LogUtil.h("HwSyncDeviceInfoMgr", "parseConfigData tlvList is null or empty");
            return null;
        } catch (cwg e4) {
            e = e4;
        }
    }

    private void e(jns jnsVar, cwd cwdVar) {
        LogUtil.c("HwSyncDeviceInfoMgr", "parseWheelSizeTlv - getTag:", cwdVar.e(), ",getValue:", cwdVar.c());
        switch (CommonUtil.w(cwdVar.e())) {
            case 15:
                jnsVar.b(CommonUtil.y(cwdVar.c()));
                break;
            case 16:
                jnsVar.b(CommonUtil.w(cwdVar.c()));
                break;
            case 17:
                jnsVar.e(CommonUtil.w(cwdVar.c()));
                break;
            case 18:
                jnsVar.a(CommonUtil.w(cwdVar.c()));
                break;
            default:
                LogUtil.h("HwSyncDeviceInfoMgr", "parseConfigData default branches.");
                break;
        }
    }

    private void c(String str, jns jnsVar) {
        if (!TextUtils.isEmpty(str) && jnsVar != null) {
            try {
                long y = CommonUtil.y(String.format(Locale.ENGLISH, "%016d", Long.valueOf(jnsVar.b())));
                LogUtil.a("HwSyncDeviceInfoMgr", "timeFromDevice: ", Long.valueOf(y));
                if (y == -1) {
                    LogUtil.h("HwSyncDeviceInfoMgr", "timeFromDevice is invalid. ");
                    return;
                }
                String switchSettingFromDb = jqi.a().getSwitchSettingFromDb(jrh.b(str));
                jns a2 = jrh.a(switchSettingFromDb);
                if (TextUtils.isEmpty(switchSettingFromDb) || a2 == null) {
                    return;
                }
                long y2 = CommonUtil.y(String.format(Locale.ENGLISH, "%016d", Long.valueOf(a2.b())));
                LogUtil.a("HwSyncDeviceInfoMgr", "timeFromDb: ", Long.valueOf(y2));
                if (y2 < y) {
                    jqi.a().setSwitchSettingToDb(jrh.b(str), jnsVar.toString());
                    return;
                }
                return;
            } catch (NumberFormatException unused) {
                LogUtil.b("HwSyncDeviceInfoMgr", "saveRimSize NumberFormatException.");
                return;
            }
        }
        LogUtil.h("HwSyncDeviceInfoMgr", "saveRimSize parameter is invalid.");
    }

    private boolean a(CloudDeviceInfo cloudDeviceInfo) {
        if (cloudDeviceInfo == null) {
            LogUtil.h("HwSyncDeviceInfoMgr", "isMathLocalDeviceList syncDeviceInfo is null");
            return false;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HwSyncDeviceInfoMgr");
        if (deviceList == null || deviceList.size() <= 0) {
            LogUtil.h("HwSyncDeviceInfoMgr", "isMathLocalDeviceList deviceInfoList is null or empty");
            return false;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo == null || deviceInfo.getDeviceIdentify() == null) {
                LogUtil.h("HwSyncDeviceInfoMgr", "isMathLocalDeviceList deviceInfo or deviceIdentify is null");
            } else if (deviceInfo.getDeviceIdentify().contains(cloudDeviceInfo.getDeviceIdentify()) && deviceInfo.getDeviceName().equalsIgnoreCase(cloudDeviceInfo.getDeviceName())) {
                LogUtil.a("HwSyncDeviceInfoMgr", "isMathLocalDeviceList is match");
                cloudDeviceInfo.setDeviceIdentify(deviceInfo.getDeviceIdentify());
                return true;
            }
        }
        return false;
    }

    private boolean d(CloudDeviceInfo cloudDeviceInfo) {
        if (cloudDeviceInfo == null || TextUtils.isEmpty(cloudDeviceInfo.getDeviceIdentify())) {
            LogUtil.h("HwSyncDeviceInfoMgr", "isMathCloudDeviceList syncDeviceInfo or identifyis null");
            return false;
        }
        List<CloudDeviceInfo> c = jyr.a().c();
        if (c.size() <= 0) {
            LogUtil.h("HwSyncDeviceInfoMgr", "isMathCloudDeviceList cloudDeviceInfoList is null or empty");
            return false;
        }
        for (CloudDeviceInfo cloudDeviceInfo2 : c) {
            if (cloudDeviceInfo2 == null || cloudDeviceInfo2.getDeviceIdentify() == null) {
                LogUtil.h("HwSyncDeviceInfoMgr", "isMathCloudDeviceList cloudDeviceInfo or deviceIdentify is null");
            } else if (cloudDeviceInfo.getDeviceIdentify().contains(cloudDeviceInfo2.getDeviceIdentify()) && cloudDeviceInfo2.getDeviceName().equalsIgnoreCase(cloudDeviceInfo.getDeviceName())) {
                LogUtil.a("HwSyncDeviceInfoMgr", "isMathCloudDeviceList is match");
                return true;
            }
        }
        return false;
    }

    private boolean a(int i) {
        String d2 = juu.d(i);
        LogUtil.a("HwSyncDeviceInfoMgr", "isDownloadResource deviceUuid is null: ", Boolean.valueOf(TextUtils.isEmpty(d2)));
        return ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(d2);
    }

    private void b(final List<DeviceInfo> list) {
        LogUtil.a("HwSyncDeviceInfoMgr", "syncDeviceInfoToCloud enter, size is ", Integer.valueOf(list.size()));
        if (list.size() == 0) {
            LogUtil.h("HwSyncDeviceInfoMgr", "deviceInfos size is 0.");
        } else {
            if (Utils.o()) {
                return;
            }
            ProfileAgent.PROFILE_AGENT.connectProfile(new ServiceConnectCallback() { // from class: kdi.4
                @Override // com.huawei.profile.client.connect.ServiceConnectCallback
                public void onConnect() {
                    LogUtil.a("HwSyncDeviceInfoMgr", "profile connected");
                    ProfileAgent.PROFILE_AGENT.setConnected(true);
                    jdx.b(new Runnable() { // from class: kdi.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                kfz.a().e((DeviceInfo) it.next());
                            }
                            ProfileAgent.PROFILE_AGENT.disconnectProfile();
                        }
                    });
                }

                @Override // com.huawei.profile.client.connect.ServiceConnectCallback
                public void onDisconnect() {
                    ProfileAgent.PROFILE_AGENT.setConnected(false);
                    LogUtil.a("HwSyncDeviceInfoMgr", "profile disconnected");
                }
            });
            LogUtil.a("HwSyncDeviceInfoMgr", "syncDeviceInfoToCloud end");
        }
    }

    private void a(final String str) {
        LogUtil.a("HwSyncDeviceInfoMgr", "removeDeviceFromCloud enter");
        if (Utils.o() || TextUtils.isEmpty(str)) {
            LogUtil.h("HwSyncDeviceInfoMgr", "removeDeviceFromCloud overSea or deviceUuid is null.");
        } else {
            ProfileAgent.PROFILE_AGENT.connectProfile(new ServiceConnectCallback() { // from class: kdi.2
                @Override // com.huawei.profile.client.connect.ServiceConnectCallback
                public void onConnect() {
                    LogUtil.a("HwSyncDeviceInfoMgr", "profile connected");
                    jdx.b(new Runnable() { // from class: kdi.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            kfz.a().a(str);
                            ProfileAgent.PROFILE_AGENT.disconnectProfile();
                        }
                    });
                    ProfileAgent.PROFILE_AGENT.setConnected(true);
                }

                @Override // com.huawei.profile.client.connect.ServiceConnectCallback
                public void onDisconnect() {
                    ProfileAgent.PROFILE_AGENT.setConnected(false);
                    LogUtil.a("HwSyncDeviceInfoMgr", "profile disconnected");
                }
            });
        }
    }
}
