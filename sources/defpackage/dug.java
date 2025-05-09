package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dug {

    static class c {
        private static final dug d = new dug();
    }

    private dug() {
    }

    public static final dug c() {
        return c.d;
    }

    public void d(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthLibInteractor", "insertHeartRateIntoHihealthLib jsonObject is null");
            return;
        }
        duc ducVar = (duc) new Gson().fromJson(CommonUtil.z(jSONObject.toString()), duc.class);
        long a2 = ducVar.a();
        int b = ducVar.b();
        if (a2 == 0 || b == 0) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthLibInteractor", "insertHeartRateIntoHihealthLib HeartRateData error.");
            return;
        }
        DeviceInfo b2 = b();
        if (b2 == null) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthLibInteractor", "insertHeartRateIntoHihealthLib connectedDeviceInfo is null.");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(i);
        c(b, hiHealthData, b2.getSecurityUuid() + "#ANDROID21");
        hiHealthData.setStartTime(a2);
        hiHealthData.setValue(b);
        hiHealthData.setEndTime(a2);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        hiHealthData.putString("realtime_merge_mode", "instantaneousMode");
        hiHealthData.setOwnerId(0);
        HiHealthManager.d(cpp.a()).insertRealTimeHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: dug.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i2, Object obj) {
                LogUtil.a("HealthLibInteractor", "IHealthDataHandler type is " + i2);
            }
        });
    }

    private void c(int i, HiHealthData hiHealthData, String str) {
        if (i == 255) {
            hiHealthData.setDataSource(String.valueOf(-1));
            hiHealthData.setDeviceUuid(String.valueOf(-1));
        } else {
            hiHealthData.setDeviceUuid(str);
        }
    }

    public void d(Object obj) {
        if (!(obj instanceof String)) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthLibInteractor", "insertRealTimeHeartRateForMerge srcObject is not String");
            return;
        }
        kon konVar = (kon) new Gson().fromJson(CommonUtil.z(obj.toString()), kon.class);
        int j = konVar.j();
        String q = konVar.q();
        if (j == 0) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthLibInteractor", "insertRealTimeHeartRateForMerge HeartRateData error.");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(50001);
        c(j, hiHealthData, q);
        hiHealthData.setStartTime(new Date().getTime());
        hiHealthData.setValue(j);
        hiHealthData.setEndTime(new Date().getTime());
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        LogUtil.a("HealthLibInteractor", "insertRealTimeHeartRateForMerge hr ", Double.valueOf(hiHealthData.getValue()), " startTime ", Long.valueOf(hiHealthData.getStartTime()), " endTime ", Long.valueOf(hiHealthData.getEndTime()));
        hiHealthData.setOwnerId(0);
        hiHealthData.putString("realtime_merge_mode", "instantaneousMode");
        HiHealthManager.d(cpp.a()).insertRealTimeHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: dug.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj2) {
                LogUtil.a("HealthLibInteractor", "insertRealTimeHeartRateForMerge type is ", Integer.valueOf(i), ".obj = ", obj2);
            }
        });
    }

    public void b(kon konVar) {
        int j = konVar.j();
        String q = konVar.q();
        if (j == 0) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthLibInteractor", "insertRealTimeHeartRateForMerge HeartRateData error.");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(50001);
        c(j, hiHealthData, q);
        hiHealthData.setStartTime(new Date().getTime());
        hiHealthData.setValue(j);
        hiHealthData.setEndTime(new Date().getTime());
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        LogUtil.a("HealthLibInteractor", "insertRealTimeHeartRateForMerge hr ", Double.valueOf(hiHealthData.getValue()), " startTime ", Long.valueOf(hiHealthData.getStartTime()), " endTime ", Long.valueOf(hiHealthData.getEndTime()));
        hiHealthData.setOwnerId(0);
        hiHealthData.putString("realtime_merge_mode", "instantaneousMode");
        HiHealthManager.d(cpp.a()).insertRealTimeHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: duh
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                LogUtil.a("HealthLibInteractor", "insertRealTimeHeartRateByReal type is ", Integer.valueOf(i), ".obj = ", obj);
            }
        });
    }

    public void e(Object obj) {
        LogUtil.a("HealthLibInteractor", "insertAllSportData enter");
        if (!(obj instanceof String)) {
            ReleaseLogUtil.d("HWhealthLinkage_HealthLibInteractor", "insertAllSportData srcObject is not JSONObject");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(HiHealthStatusCodes.APP_MISMATCH_ERROR);
        kon konVar = (kon) new Gson().fromJson(CommonUtil.z(obj.toString()), kon.class);
        c(konVar.j(), hiHealthData, konVar.q());
        hiHealthData.setMetaData(obj.toString());
        LogUtil.a("HealthLibInteractor", "insertAllSportData MetaData = ", obj.toString());
        hiHealthData.setStartTime(new Date().getTime());
        hiHealthData.setEndTime(new Date().getTime());
        hiHealthData.setOwnerId(0);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(cpp.a()).insertRealTimeHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: dug.1
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj2) {
                LogUtil.a("HealthLibInteractor", "insertAllSportData type is ", Integer.valueOf(i), ".obj = ", obj2);
            }
        });
    }

    public void a(kon konVar) {
        LogUtil.a("HealthLibInteractor", "insertAllSportDataByReal enter");
        HiHealthData hiHealthData = new HiHealthData(HiHealthStatusCodes.APP_MISMATCH_ERROR);
        c(konVar.j(), hiHealthData, konVar.q());
        String json = new Gson().toJson(konVar);
        hiHealthData.setMetaData(json);
        LogUtil.a("HealthLibInteractor", "insertAllSportDataByReal MetaData = ", json);
        hiHealthData.setStartTime(new Date().getTime());
        hiHealthData.setEndTime(new Date().getTime());
        hiHealthData.setOwnerId(0);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(cpp.a()).insertRealTimeHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: duf
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                LogUtil.a("HealthLibInteractor", "insertAllSportDataByReal type is ", Integer.valueOf(i), ".obj = ", obj);
            }
        });
    }

    public void e(DeviceInfo deviceInfo, String str, String str2) {
        if (deviceInfo == null || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HWhealthLinkage_Posture_", "insertRunPostureData failed, object or deviceInfo is null.");
            return;
        }
        LogUtil.a("Posture_", "insertRunPostureDataDic, fieldsValueJson: ", str);
        HiHealthData hiHealthData = new HiHealthData(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA.value());
        hiHealthData.setStartTime(new Date().getTime());
        hiHealthData.setEndTime(new Date().getTime());
        hiHealthData.setDeviceUuid(deviceInfo.getSecurityUuid() + "#ANDROID21");
        hiHealthData.setOwnerId(0);
        hiHealthData.setFieldsValue(str);
        hiHealthData.putString("realtime_back_data_interval", str2);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(BaseApplication.e()).insertRealTimeHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: dug.3
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("Posture_", "insertRunPostureDataDic type is ", Integer.valueOf(i), ".obj = ", obj);
            }
        });
    }

    public void c(List<DeviceInfo> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLibInteractor", "addSupportDevices failed, deviceInfos is empty.");
            return;
        }
        for (DeviceInfo deviceInfo : list) {
            try {
                HiDeviceInfo hiDeviceInfo = new HiDeviceInfo(jpp.d(deviceInfo.getProductType()));
                hiDeviceInfo.setDeviceUniqueCode(deviceInfo.getDeviceIdentify());
                hiDeviceInfo.setDeviceName(deviceInfo.getDeviceName());
                ArrayList arrayList = new ArrayList(10);
                arrayList.add(0);
                HiHealthManager.d(cpp.a()).registerDataClient(hiDeviceInfo, arrayList, null);
            } catch (IllegalArgumentException unused) {
                LogUtil.h("HealthLibInteractor", "get HiDeviceInfo failed, ProductType = ", Integer.valueOf(deviceInfo.getProductType()));
            }
        }
    }

    public DeviceInfo d() {
        DeviceInfo e = jpu.e("HealthLibInteractor");
        if (e == null || e.getAutoDetectSwitchStatus() != 1) {
            return null;
        }
        return e;
    }

    private DeviceInfo b() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthLibInteractor");
        if (deviceInfo != null) {
            return deviceInfo;
        }
        LogUtil.h("HealthLibInteractor", "getHeartRateDevice connectedDeviceInfo is null");
        return null;
    }
}
