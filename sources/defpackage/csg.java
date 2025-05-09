package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class csg implements Runnable {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private String f11433a;
    private WeightInsertStatusCallback b;
    private ArrayList<csh> d;
    private cfi e;
    private int f;
    private double[] i = new double[6];
    private double[] j = new double[6];
    private int h = 1;
    private c g = new c();

    public static int[] e() {
        return new int[]{2004, 2001, 2031, Constants.START_TO_MAIN_ACTIVITY, 2022, 2023, 2024, 2025, 2033, 2026, 2027, 2028, 2029, 2030, 2051, 2052, 2053, 2054, GLMapStaticValue.MAP_PARAMETERNAME_POLYGON_FILL_CONTROL, GLMapStaticValue.AM_PARAMETERNAME_TEXT_GL_UNIT, 2057, 2058, 2059, 2060, 2061, 2062, 2063, 2064, 2065, 2066, 2067, 2068, 2069, 2070, 2071, 2072, 2073, 2074, 2075, 2076, 2077, 2078, 2079, 2080, 2081};
    }

    static /* synthetic */ int f(csg csgVar) {
        int i = csgVar.f;
        csgVar.f = i + 1;
        return i;
    }

    class c extends Handler {
        private c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("SaveClaimWeightDataTask", "SaveHandler msg is null");
                return;
            }
            int i = message.what;
            if (i == 5) {
                sendEmptyMessage(7);
                return;
            }
            if (i == 7) {
                csg.this.d();
            } else if (i == 8) {
                csg.this.c();
            } else {
                LogUtil.h("SaveClaimWeightDataTask", "SaveHandler what is error");
            }
        }
    }

    public csg(ArrayList<csh> arrayList, cfi cfiVar, String str, WeightInsertStatusCallback weightInsertStatusCallback) {
        this.d = arrayList;
        this.e = cfiVar;
        this.f11433a = str;
        this.b = weightInsertStatusCallback;
    }

    private HiHealthData b() {
        HiHealthData c2 = this.d.get(0).c();
        Iterator<csh> it = this.d.iterator();
        while (it.hasNext()) {
            csh next = it.next();
            if (next.c().getStartTime() > c2.getStartTime()) {
                c2 = next.c();
            }
        }
        LogUtil.c("SaveClaimWeightDataTask", "getWeightData data", Double.valueOf(c2.getDouble("bodyWeight")));
        return c2;
    }

    private String b(cfi cfiVar) {
        if (cfiVar == null || cfiVar.i() == null || "-1".equals(cfiVar.i()) || "0".equals(cfiVar.i())) {
            return "0";
        }
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        return (i == null || !i.equals(cfiVar.i())) ? cfiVar.i() : "0";
    }

    private float c(int i, HiHealthData hiHealthData, float f, String str) {
        boolean a2 = cpa.a(hiHealthData);
        if (i == 2) {
            d(hiHealthData);
            return d(hiHealthData, f, a2, str).h();
        }
        if (i == 1) {
            return c(hiHealthData, f, a2).n();
        }
        double d = hiHealthData.getDouble(BleConstants.IMPEDANCE);
        if (d > 0.0d) {
            oi oiVar = new oi();
            oiVar.b(this.e.d(), f, cff.c(this.e.c()), this.e.a(), (float) d);
            try {
                return oiVar.e();
            } catch (og unused) {
                LogUtil.b("SaveClaimWeightDataTask", "getBodyFatRate NoAuthException");
            }
        }
        return 0.0f;
    }

    private cps c(HiHealthData hiHealthData, float f, boolean z) {
        if (z) {
            d(hiHealthData);
            a(hiHealthData);
            this.h = 1;
            return new cps(this.e.d(), f, cff.c(this.e.c()), this.e.a(), this.e.g(), 4, this.i, this.h, this.j);
        }
        this.i[0] = hiHealthData.getDouble(BleConstants.IMPEDANCE);
        return new cps(this.e.d(), f, cff.c(this.e.c()), this.e.a(), this.e.g(), this.i[0]);
    }

    private cps d(HiHealthData hiHealthData, float f, boolean z, String str) {
        float f2 = c(str, 43) ? (float) hiHealthData.getDouble(BleConstants.BODY_FAT_RATE) : 0.0f;
        if (z) {
            a(hiHealthData);
            this.h = 2;
            return new cps(this.e.d(), f, cff.c(this.e.c()), this.e.a(), this.e.g(), 8, this.i, f2, this.h, this.j);
        }
        return new cps(this.e.d(), f, cff.c(this.e.c()), this.e.a(), this.e.g(), this.i, f2);
    }

    private void d(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return;
        }
        String[] strArr = {"lfrfImpedance", "lhrhImpedance", "lhlfImpedance", "lhrfImpedance", "rhlfImpedance", "rhrfImpedance"};
        for (int i = 0; i < 6; i++) {
            this.i[i] = hiHealthData.getDouble(strArr[i]);
        }
    }

    private void a(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return;
        }
        String[] strArr = {"lfrfHfImpedance", "lhrhHfImpedance", "lhlfHfImpedance", "lhrfHfImpedance", "rhlfHfImpedance", "rhrfHfImpedance"};
        for (int i = 0; i < 6; i++) {
            this.j[i] = hiHealthData.getDouble(strArr[i]);
        }
    }

    private Map<String, String> e(String str, HiHealthData hiHealthData, String str2, String str3) {
        HashMap hashMap = new HashMap(16);
        b(str, str3, hashMap);
        double d = hiHealthData.getDouble("bodyWeight");
        hashMap.put("isDelete", String.valueOf(0));
        int i = hiHealthData.getInt("pole");
        LogUtil.a("SaveClaimWeightDataTask", "getSendCloudData pole:", Integer.valueOf(i));
        float f = (float) hiHealthData.getDouble(BleConstants.BODY_FAT_RATE);
        if (f <= 0.0f) {
            f = c(i, hiHealthData, (float) d, str3);
        }
        if (f > 0.0f) {
            hashMap.put("currentFat", String.format(Locale.ENGLISH, "%.1f", Float.valueOf(f * 10.0f)));
        }
        if (cpa.ae(str2)) {
            hashMap.put("currentWeight", String.valueOf(((float) d) * 100.0f));
            if (i == 2) {
                hashMap.put("bodyRes_Z12", String.valueOf(hiHealthData.getDouble("lhrhImpedance") * 10.0d));
                hashMap.put("bodyRes_Z13", String.valueOf(hiHealthData.getDouble("lhlfImpedance") * 10.0d));
                hashMap.put("bodyRes_Z14", String.valueOf(hiHealthData.getDouble("lhrfImpedance") * 10.0d));
                hashMap.put("bodyRes_Z23", String.valueOf(hiHealthData.getDouble("rhlfImpedance") * 10.0d));
                hashMap.put("bodyRes_Z24", String.valueOf(hiHealthData.getDouble("rhrfImpedance") * 10.0d));
                hashMap.put("bodyRes_Z34", String.valueOf(hiHealthData.getDouble("lfrfImpedance") * 10.0d));
            } else {
                hashMap.put("bodyRes", String.valueOf(hiHealthData.getDouble(BleConstants.IMPEDANCE) * 10.0d));
            }
        } else {
            hashMap.put("currentWeight", String.valueOf(((float) d) * 10.0f));
            hashMap.put("bodyRes", String.valueOf(hiHealthData.getDouble(BleConstants.IMPEDANCE)));
        }
        b(hashMap, hiHealthData, str3);
        return hashMap;
    }

    private void b(String str, String str2, Map<String, String> map) {
        map.put("id", this.f11433a);
        map.put("uid", str);
        map.put(CommonConstant.KEY_GENDER, String.valueOf((int) cff.c(this.e.c())));
        map.put("age", String.valueOf(cgs.e(this.e.g(), this.e.a())));
        map.put("height", String.valueOf(this.e.d()));
        if (cgs.d(str2)) {
            LogUtil.a("SaveClaimWeightDataTask", "enter setUserBaseInfo supportChildren");
            map.put("month", String.valueOf(cgs.a(this.e.g())));
        }
    }

    private void b(Map<String, String> map, HiHealthData hiHealthData, String str) {
        if (!c(str, 43)) {
            LogUtil.h("SaveClaimWeightDataTask", "no support smooth impedance");
            return;
        }
        String[] strArr = {"lhrhHfImpedance", "lhlfHfImpedance", "lhrfHfImpedance", "rhlfHfImpedance", "rhrfHfImpedance", "lfrfHfImpedance"};
        String[] strArr2 = {"res_LHRH_HF", "res_LHLF_HF", "res_LHRF_HF", "res_RHLF_HF", "res_RHRF_HF", "res_LFRF_HF"};
        for (int i = 0; i < 6; i++) {
            map.put(strArr2[i], String.valueOf(hiHealthData.getDouble(strArr[i]) * 10.0d));
        }
        d(map, hiHealthData);
    }

    private void d(Map<String, String> map, HiHealthData hiHealthData) {
        String string = hiHealthData.getString("metadata");
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("SaveClaimWeightDataTask", "setUserInfoTimestamp metaDataJsonArray is null or empty");
            return;
        }
        try {
            List list = (List) new Gson().fromJson(string, new TypeToken<List<csi>>() { // from class: csg.4
            }.getType());
            if (koq.b(list)) {
                LogUtil.h("SaveClaimWeightDataTask", "setUserInfoTimestamp conflictingUserList is empty");
                return;
            }
            int a2 = ((csi) list.get(0)).a();
            if ((2 != a2 || list.size() >= 3) && 5 != a2) {
                return;
            }
            LogUtil.a("SaveClaimWeightDataTask", "setUserInfoTimestamp userInfoMap put timestamp");
            map.put("dateTime", String.valueOf(hiHealthData.getLong("start_time")));
        } catch (JsonSyntaxException | IllegalStateException unused) {
            LogUtil.b("SaveClaimWeightDataTask", "setUserInfoTimestamp catch JsonSyntaxException or IllegalStateException");
        }
    }

    private boolean d(DeviceServiceInfo deviceServiceInfo, String str) {
        ctk ctkVar = (ctk) cjx.e().e(str);
        if (ctkVar != null) {
            String sid = deviceServiceInfo.getSid();
            if (sid.indexOf("_") >= 0) {
                if (ctkVar.b().k() == 2) {
                    return true;
                }
                deviceServiceInfo.setSid(sid.substring(0, sid.indexOf("_")));
                return true;
            }
            if (ctkVar.b().k() != 2) {
                return true;
            }
            deviceServiceInfo.setSid(sid + "_" + this.f11433a);
            return true;
        }
        LogUtil.a("SaveClaimWeightDataTask", "device is null");
        this.g.obtainMessage(5).sendToTarget();
        return false;
    }

    private boolean c(String str, int i) {
        if (csf.a(str, i) == 1) {
            return true;
        }
        return cji.a(str, i);
    }

    private void e(HiHealthData hiHealthData, String str, String str2, final String str3) {
        LogUtil.a("SaveClaimWeightDataTask", "enter saveSingleCloudData");
        cfi cfiVar = this.e;
        if (cfiVar == null || hiHealthData == null) {
            LogUtil.h("SaveClaimWeightDataTask", "saveSingleCloudData params is null");
            return;
        }
        String b = b(cfiVar);
        Map<String, String> e = e(b, hiHealthData, str2, str);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        deviceServiceInfo.setData(e);
        if (b.equals("0")) {
            deviceServiceInfo.setSid(cts.b);
        } else {
            deviceServiceInfo.setSid(this.e.j());
        }
        if (d(deviceServiceInfo, str3)) {
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(deviceServiceInfo);
            WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
            wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
            wifiDeviceControlDataModelReq.setDevId(str3);
            jbs.a(BaseApplication.getContext()).d(wifiDeviceControlDataModelReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: csg.2
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void operationResult(CloudCommonReponse cloudCommonReponse, String str4, boolean z) {
                    LogUtil.a("SaveClaimWeightDataTask", "saveSingleCloudData isSuccess,", Boolean.valueOf(z));
                    if (z) {
                        csg.this.g.obtainMessage(5).sendToTarget();
                        return;
                    }
                    if (cloudCommonReponse != null) {
                        int intValue = cloudCommonReponse.getResultCode().intValue();
                        LogUtil.a("SaveClaimWeightDataTask", "saveSingleCloudData syncWifiDeviceControl errorCode,", Integer.valueOf(intValue), ",errorDesc,", cloudCommonReponse.getResultDesc());
                        if (intValue == 112000000) {
                            csf.m(str3);
                            csg.this.g.obtainMessage(5).sendToTarget();
                            return;
                        }
                    }
                    if (csg.this.b != null) {
                        csg.this.b.isSuccess(false);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ArrayList<ctk> a2 = cjx.e().a();
        if (koq.b(a2)) {
            LogUtil.h("SaveClaimWeightDataTask", "saveCloudData devices is null or empty");
            this.g.obtainMessage(5).sendToTarget();
            return;
        }
        HiHealthData b = b();
        MeasurableDevice c2 = ceo.d().c(b.getString("device_uniquecode"), false);
        if (c2 == null) {
            LogUtil.h("SaveClaimWeightDataTask", "saveCloudData device is null");
            this.g.obtainMessage(5).sendToTarget();
            return;
        }
        Iterator<ctk> it = a2.iterator();
        while (it.hasNext()) {
            ctk next = it.next();
            if (next.getAddress().equals(c2.getAddress())) {
                e(b, c2.getUniqueId(), c2.getProductId(), next.d());
                return;
            }
        }
        LogUtil.a("SaveClaimWeightDataTask", "saveCloudData device address no wifi device");
        this.g.obtainMessage(5).sendToTarget();
    }

    @Override // java.lang.Runnable
    public void run() {
        c cVar = this.g;
        if (cVar == null) {
            LogUtil.h("SaveClaimWeightDataTask", "run mHandler is null");
        } else {
            cVar.obtainMessage(8).sendToTarget();
        }
    }

    public static ArrayList<HiTimeInterval> b(ArrayList<csh> arrayList) {
        ArrayList<HiTimeInterval> arrayList2 = new ArrayList<>(16);
        if (arrayList == null) {
            LogUtil.h("SaveClaimWeightDataTask", "getDeleteData dataBeans is null");
            return arrayList2;
        }
        LogUtil.a("SaveClaimWeightDataTask", "getDeleteData in Data size:", Integer.valueOf(arrayList.size()));
        Iterator<csh> it = arrayList.iterator();
        while (it.hasNext()) {
            csh next = it.next();
            HiTimeInterval hiTimeInterval = new HiTimeInterval();
            hiTimeInterval.setStartTime(next.c().getStartTime());
            hiTimeInterval.setEndTime(next.c().getEndTime());
            arrayList2.add(hiTimeInterval);
        }
        return arrayList2;
    }

    public static void d(List<HiTimeInterval> list, HiDataOperateListener hiDataOperateListener) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(list);
        hiDataDeleteOption.setTypes(e());
        HiHealthManager.d(BaseApplication.getContext()).deleteHiHealthData(hiDataDeleteOption, hiDataOperateListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (koq.b(this.d)) {
            LogUtil.h("SaveClaimWeightDataTask", "saveData mBeans is null or empty");
            return;
        }
        HashSet<String> hashSet = new HashSet(16);
        Iterator<csh> it = this.d.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().c().getDeviceUuid());
        }
        if (koq.b(hashSet)) {
            LogUtil.h("SaveClaimWeightDataTask", "saveData deviceUuids is null or empty");
            return;
        }
        for (String str : hashSet) {
            ArrayList arrayList = new ArrayList(16);
            Iterator<csh> it2 = this.d.iterator();
            while (it2.hasNext()) {
                csh next = it2.next();
                if (str.equals(next.c().getDeviceUuid())) {
                    arrayList.add(next.c());
                }
            }
            e(arrayList, hashSet.size());
        }
    }

    private void e(List<HiHealthData> list, final int i) {
        if (koq.b(list)) {
            LogUtil.h("SaveClaimWeightDataTask", "saveClaimWeightData hiHealthData is null or empty");
        } else {
            new cso(this.e, new WeightInsertStatusCallback() { // from class: csg.1
                @Override // com.huawei.health.device.callback.WeightInsertStatusCallback
                public void isSuccess(boolean z) {
                    LogUtil.a("SaveClaimWeightDataTask", "saveClaimWeightData WiFiWeightSaveHandler isSuccess,", Boolean.valueOf(z));
                    if (csg.this.b != null) {
                        synchronized (csg.c) {
                            csg.f(csg.this);
                            if (csg.this.f == i) {
                                csg.this.b.isSuccess(z);
                                csg.this.f = 0;
                            }
                        }
                        return;
                    }
                    LogUtil.h("SaveClaimWeightDataTask", "saveClaimWeightData mCallback is null");
                }
            }).d(list);
        }
    }
}
