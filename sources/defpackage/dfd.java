package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.health.device.api.ConverResisToWeightDataUtilApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.api.WiFiWeightSaveHandlerApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dfd implements IHealthDeviceCallback {

    /* renamed from: a, reason: collision with root package name */
    private WeightInsertStatusCallback f11632a;
    private int b;
    private cfi c;
    private IBaseResponseCallback e;

    @Override // com.huawei.health.device.callback.IHealthDeviceCallback
    public void onFailed(HealthDevice healthDevice, int i) {
    }

    @Override // com.huawei.health.device.callback.IHealthDeviceCallback
    public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
    }

    @Override // com.huawei.health.device.callback.IHealthDeviceCallback
    public void onStatusChanged(HealthDevice healthDevice, int i) {
    }

    public dfd(int i) {
        this(null, i);
    }

    public dfd(cfi cfiVar, int i) {
        this.c = cfiVar;
        this.b = i;
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        this.e = iBaseResponseCallback;
    }

    public void b(cfi cfiVar) {
        this.c = cfiVar;
    }

    public void b(WeightInsertStatusCallback weightInsertStatusCallback) {
        this.f11632a = weightInsertStatusCallback;
    }

    private List<HiHealthData> d(HealthData healthData, HealthDevice healthDevice) {
        if (healthData == null) {
            return Collections.emptyList();
        }
        HiHealthData hiHealthData = new HiHealthData(this.b);
        long time = new Date().getTime();
        if (healthData.getStartTime() == 0 || healthData.getEndTime() == 0) {
            healthData.setStartTime(time);
            healthData.setEndTime(time);
        }
        hiHealthData.setStartTime(healthData.getStartTime());
        hiHealthData.setEndTime(healthData.getEndTime());
        if (healthDevice instanceof MeasurableDevice) {
            MeasurableDevice measurableDevice = (MeasurableDevice) healthDevice;
            String productId = measurableDevice.getProductId();
            String uniqueId = measurableDevice.getUniqueId();
            HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
            String deviceSerialNumberByUniqueId = honourDeviceConstantsApi.getDeviceSerialNumberByUniqueId(uniqueId);
            boolean z = (TextUtils.isEmpty(deviceSerialNumberByUniqueId) || "0".equals(deviceSerialNumberByUniqueId)) ? false : true;
            if (honourDeviceConstantsApi.isDualModeProduct(productId) && z) {
                hiHealthData.setDeviceUuid(deviceSerialNumberByUniqueId);
            } else {
                hiHealthData.setDeviceUuid(healthDevice.getUniqueId());
            }
        } else {
            hiHealthData.setDeviceUuid(healthDevice.getUniqueId());
        }
        ReleaseLogUtil.e("R_PluginDevice_IHealthDataHandler", "IHealthDataHandler endTime is ", Long.valueOf(hiHealthData.getEndTime()), ", deviceUuid is ", CommonUtil.l(hiHealthData.getDeviceUuid()));
        ArrayList arrayList = new ArrayList(16);
        d(hiHealthData, healthData, arrayList);
        return arrayList;
    }

    private void d(HiHealthData hiHealthData, HealthData healthData, List<HiHealthData> list) {
        int i = this.b;
        if (i != 2002) {
            if (i == 10006) {
                LogUtil.a("PluginDevice_IHealthDataHandler", "IHealthDataHandler HiHealthDataType.DATA_SET_WEIGHT_EX");
                if (healthData instanceof ckm) {
                    ((ConverResisToWeightDataUtilApi) Services.c("PluginDevice", ConverResisToWeightDataUtilApi.class)).convertWeightAndFatRateData(hiHealthData, (ckm) healthData, this.c);
                }
            } else if (i == 50001) {
                hiHealthData.putString("realtime_merge_mode", "instantaneousMode");
            } else if (i != 2103) {
                if (i != 2104) {
                    if (i != 10001) {
                        if (i == 10002 && (healthData instanceof deo)) {
                            e(hiHealthData, (deo) healthData);
                        }
                    } else if (healthData instanceof deb) {
                        c(hiHealthData, (deb) healthData);
                    }
                } else if (healthData instanceof ded) {
                    b(hiHealthData, (ded) healthData);
                }
            } else if (healthData instanceof dec) {
                a(hiHealthData, (dec) healthData);
            }
            list.add(hiHealthData);
        }
        if (healthData instanceof der) {
            LogUtil.a("PluginDevice_IHealthDataHandler", "IHealthDataHandler current device is heartRate device");
            hiHealthData.setValue(((der) healthData).getHeartRate());
            LogUtil.a("PluginDevice_IHealthDataHandler", "IHealthDataHandler heartRate is ***");
        }
        list.add(hiHealthData);
    }

    private void e(HiHealthData hiHealthData, deo deoVar) {
        hiHealthData.putShort("BLOOD_PRESSURE_DIASTOLIC", deoVar.getDiastolic());
        hiHealthData.putShort("BLOOD_PRESSURE_SYSTOLIC", deoVar.getSystolic());
        hiHealthData.putShort(BleConstants.BLOODPRESSURE_SPHYGMUS, deoVar.getHeartRate());
    }

    private void c(HiHealthData hiHealthData, deb debVar) {
        float bloodSugar = debVar.getBloodSugar();
        if ("dnurse".equals(debVar.getRecordId())) {
            LogUtil.a("PluginDevice_IHealthDataHandler", "HealthDHealthDataHandler endTime is ataHandler current device is dnurse bloodSugar type = ", Integer.valueOf(debVar.getType()));
            hiHealthData.setType(debVar.getType());
            hiHealthData.setValue(bloodSugar);
        } else {
            LogUtil.a("PluginDevice_IHealthDataHandler", "IHealthDataHandler current device is johnson");
            long startTime = debVar.getStartTime();
            LogUtil.a("PluginDevice_IHealthDataHandler", "getStartTime " + startTime);
            hiHealthData.setType(deb.e(startTime));
            hiHealthData.setValue(bloodSugar);
        }
        HiBloodSugarMetaData hiBloodSugarMetaData = new HiBloodSugarMetaData();
        hiBloodSugarMetaData.setConfirmed(true);
        hiHealthData.setMetaData(HiJsonUtil.e(hiBloodSugarMetaData));
    }

    private void b(HiHealthData hiHealthData, ded dedVar) {
        hiHealthData.setValue(dedVar.e());
    }

    private void a(HiHealthData hiHealthData, dec decVar) {
        hiHealthData.setValue(decVar.e());
    }

    private void d(final HealthDevice healthDevice, final HiDataInsertOption hiDataInsertOption, final WeightInsertStatusCallback weightInsertStatusCallback, final IBaseResponseCallback iBaseResponseCallback, final int i) {
        if (b(hiDataInsertOption, i)) {
            LogUtil.h("PluginDevice_IHealthDataHandler", "IHealthDataHandler insertWithRetry params is invalid");
            return;
        }
        LogUtil.a("PluginDevice_IHealthDataHandler", "IHealthDataHandler insertWithRetry, retryTimes ", Integer.valueOf(i));
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        HiDataInsertOption d = d(hiDataInsertOption, arrayList, arrayList2);
        b(d);
        HiHealthManager.d(cpp.a()).insertHiHealthData(d, new HiDataOperateListener() { // from class: dff
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                dfd.this.d(hiDataInsertOption, arrayList, arrayList2, iBaseResponseCallback, weightInsertStatusCallback, healthDevice, i, i2, obj);
            }
        });
    }

    /* synthetic */ void d(HiDataInsertOption hiDataInsertOption, List list, List list2, IBaseResponseCallback iBaseResponseCallback, WeightInsertStatusCallback weightInsertStatusCallback, HealthDevice healthDevice, int i, int i2, Object obj) {
        Object[] objArr = new Object[4];
        objArr[0] = "IHealthDataHandler insertWithRetry ";
        objArr[1] = i2 == 0 ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
        objArr[2] = " response Code:";
        objArr[3] = Integer.valueOf(i2);
        LogUtil.a("PluginDevice_IHealthDataHandler", objArr);
        if (i2 == 0) {
            a();
            a(hiDataInsertOption.getDatas());
            d((List<HiHealthData>) list, (List<HiHealthData>) list2, iBaseResponseCallback, weightInsertStatusCallback, i2);
            return;
        }
        if (i2 == 10 && (healthDevice instanceof MeasurableDevice)) {
            if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isDualModeProduct(((MeasurableDevice) healthDevice).getProductId())) {
                c(healthDevice, hiDataInsertOption);
            }
        }
        if (i > 1) {
            e(healthDevice, hiDataInsertOption, weightInsertStatusCallback, iBaseResponseCallback, i);
            return;
        }
        if (weightInsertStatusCallback != null) {
            weightInsertStatusCallback.isSuccess(false);
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i2, obj);
        }
    }

    private HiDataInsertOption d(HiDataInsertOption hiDataInsertOption, List<HiHealthData> list, List<HiHealthData> list2) {
        for (HiHealthData hiHealthData : hiDataInsertOption.getDatas()) {
            if (hiHealthData.getType() == 2018) {
                list2.add(hiHealthData);
            } else if (hiHealthData.getType() == DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()) {
                HashMap hashMap = new HashMap(3);
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()), Double.valueOf(hiHealthData.getShort("BLOOD_PRESSURE_SYSTOLIC")));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value()), Double.valueOf(hiHealthData.getShort("BLOOD_PRESSURE_DIASTOLIC")));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()), Double.valueOf(hiHealthData.getShort(BleConstants.BLOODPRESSURE_SPHYGMUS)));
                hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
                list.add(hiHealthData);
            } else {
                list.add(hiHealthData);
            }
        }
        HiDataInsertOption hiDataInsertOption2 = new HiDataInsertOption();
        hiDataInsertOption2.setDatas(list);
        return hiDataInsertOption2;
    }

    private void b(HiDataInsertOption hiDataInsertOption) {
        if (hiDataInsertOption == null) {
            return;
        }
        List<HiHealthData> datas = hiDataInsertOption.getDatas();
        if (koq.b(datas)) {
            LogUtil.h("PluginDevice_IHealthDataHandler", "logHfImpedance datas is null or empty");
            return;
        }
        HiHealthData hiHealthData = datas.get(0);
        LogUtil.a("PluginDevice_IHealthDataHandler", "save weight data param : ", CommonUtil.l(hiHealthData.getDeviceUuid()), " owner : ", Integer.valueOf(hiHealthData.getOwnerId()));
        for (HiHealthData hiHealthData2 : datas) {
            if (hiHealthData2 != null) {
                double d = hiHealthData2.getDouble("lhrhHfImpedance");
                double d2 = hiHealthData2.getDouble("lhlfHfImpedance");
                double d3 = hiHealthData2.getDouble("lhrfHfImpedance");
                double d4 = hiHealthData2.getDouble("rhlfHfImpedance");
                double d5 = hiHealthData2.getDouble("rhrfHfImpedance");
                double d6 = hiHealthData2.getDouble("lfrfHfImpedance");
                if (d6 > 0.0d) {
                    LogUtil.a("PluginDevice_IHealthDataHandler", "insertHiHealthData hiDataInsertOption : lhrhhfimpedance is " + d + " , lhlfhfimpedance is " + d2 + " , lhrfhfimpedance is " + d3 + " , rhlfhfimpedance is " + d4 + " , rhrfhfimpedance is " + d5 + " , lfrfhfimpedance is " + d6);
                }
            }
        }
    }

    private void e(HealthDevice healthDevice, HiDataInsertOption hiDataInsertOption, WeightInsertStatusCallback weightInsertStatusCallback, IBaseResponseCallback iBaseResponseCallback, int i) {
        d(healthDevice, hiDataInsertOption, weightInsertStatusCallback, iBaseResponseCallback, i - 1);
    }

    private boolean b(HiDataInsertOption hiDataInsertOption, int i) {
        if (hiDataInsertOption == null || koq.b(hiDataInsertOption.getDatas())) {
            LogUtil.h("PluginDevice_IHealthDataHandler", "IHealthDataHandler insert data is null");
            return true;
        }
        if (i > 0) {
            return false;
        }
        LogUtil.h("PluginDevice_IHealthDataHandler", "IHealthDataHandler insert illegal retryTimes");
        return true;
    }

    private void d(List<HiHealthData> list, List<HiHealthData> list2, IBaseResponseCallback iBaseResponseCallback, WeightInsertStatusCallback weightInsertStatusCallback, int i) {
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(list2);
        if (list.size() > 0 && list.get(0).getType() == 10002 && list2.size() > 0) {
            LogUtil.a("PluginDevice_IHealthDataHandler", "doSuccessResponse heartListJson:", new Gson().toJson(list2));
            a(hiDataInsertOption, iBaseResponseCallback, 3);
            return;
        }
        if (weightInsertStatusCallback != null) {
            weightInsertStatusCallback.isSuccess(true);
        }
        if (iBaseResponseCallback != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(true);
            iBaseResponseCallback.d(i, arrayList);
        }
    }

    private void a(final HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback, final int i) {
        HiHealthManager.d(cpp.a()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: dfh
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                dfd.this.c(iBaseResponseCallback, i, hiDataInsertOption, i2, obj);
            }
        });
    }

    /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, int i, HiDataInsertOption hiDataInsertOption, int i2, Object obj) {
        if (i2 == 0) {
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i2, obj);
                return;
            }
            return;
        }
        LogUtil.h("PluginDevice_IHealthDataHandler", "insertBloodPressHeartRate onResult else ", Integer.valueOf(i2));
        if (i > 1) {
            a(hiDataInsertOption, iBaseResponseCallback, i - 1);
        } else if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i2, obj);
        }
    }

    private void c(HealthDevice healthDevice, HiDataInsertOption hiDataInsertOption) {
        LogUtil.a("PluginDevice_IHealthDataHandler", "enter setErrorNoFindClient");
        List<HiHealthData> datas = hiDataInsertOption.getDatas();
        if (koq.b(datas)) {
            LogUtil.h("PluginDevice_IHealthDataHandler", "setErrorNoFindClient listDatas is null or empty");
            return;
        }
        for (HiHealthData hiHealthData : datas) {
            if (hiHealthData != null) {
                String deviceUuid = hiHealthData.getDeviceUuid();
                if (TextUtils.isEmpty(deviceUuid)) {
                    LogUtil.a("PluginDevice_IHealthDataHandler", "setErrorNoFindClient isEmpty uuid");
                    hiHealthData.setDeviceUuid(healthDevice.getUniqueId());
                } else if (!deviceUuid.contains(":")) {
                    LogUtil.a("PluginDevice_IHealthDataHandler", "setErrorNoFindClient uuid no contains");
                    hiHealthData.setDeviceUuid(healthDevice.getUniqueId());
                }
            }
        }
    }

    @Override // com.huawei.health.device.callback.IHealthDeviceCallback
    public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
        if (healthDevice == null || healthData == null) {
            LogUtil.a("PluginDevice_IHealthDataHandler", "IHealthDataHandler onDataChanged parameter is null");
            return;
        }
        List<HiHealthData> d = d(healthData, healthDevice);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        for (HiHealthData hiHealthData : d) {
            hiHealthData.setOwnerId(0);
            hiDataInsertOption.addData(hiHealthData);
        }
        d(healthDevice, hiDataInsertOption, this.f11632a, this.e, 3);
    }

    @Override // com.huawei.health.device.callback.IHealthDeviceCallback
    public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PluginDevice_IHealthDataHandler", "IHealthDataHandler onDataChanged empty dataList");
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        Iterator<HealthData> it = list.iterator();
        while (it.hasNext()) {
            for (HiHealthData hiHealthData : d(it.next(), healthDevice)) {
                hiHealthData.setOwnerId(0);
                hiDataInsertOption.addData(hiHealthData);
            }
        }
        d(healthDevice, hiDataInsertOption, this.f11632a, this.e, 3);
    }

    private void a(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PluginDevice_IHealthDataHandler", "IHealthDataHandler setMainUserHeartRate data is empty");
        } else if (!dfg.d().a() && !dfg.d().c()) {
            LogUtil.h("PluginDevice_IHealthDataHandler", "IHealthDataHandler setMainUserHeartRate is not haiget or ICommon product!");
        } else {
            ((WiFiWeightSaveHandlerApi) Services.c("PluginDevice", WiFiWeightSaveHandlerApi.class)).setMainUserHeartRate(list);
        }
    }

    private void a() {
        int i = this.b;
        if (i == 10001 || i == 10002 || i == 10006) {
            HiSyncOption hiSyncOption = new HiSyncOption();
            hiSyncOption.setSyncModel(2);
            hiSyncOption.setSyncAction(0);
            hiSyncOption.setSyncDataType(20000);
            hiSyncOption.setSyncMethod(2);
            hiSyncOption.setSyncScope(1);
            HiHealthNativeApi.a(cpp.a()).synCloud(hiSyncOption, null);
        }
    }
}
