package defpackage;

import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthApi;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class cjz {
    private int b;
    private String c;

    public cjz(String str, int i) {
        this.b = i;
        this.c = str;
    }

    public HiHealthData d(HealthData healthData) {
        if (healthData == null) {
            return null;
        }
        HiHealthData hiHealthData = new HiHealthData(this.b);
        hiHealthData.setStartTime(healthData.getStartTime());
        LogUtil.a("PluginDevice_PluginDeviceDataStored", "convertData startTime is ", Long.valueOf(healthData.getStartTime()));
        int i = this.b;
        if (i != 2002) {
            if (i != 2018) {
                if (i != 10006) {
                    if (i != 50001) {
                        if (i != 10001) {
                            if (i == 10002 && (healthData instanceof deo)) {
                                a(hiHealthData, (deo) healthData, false);
                            }
                        } else if (healthData instanceof deb) {
                            d((deb) healthData, hiHealthData);
                        }
                    }
                } else if (healthData instanceof ckm) {
                    ckm ckmVar = (ckm) healthData;
                    hiHealthData.putDouble("bodyWeight", ckmVar.getWeight());
                    hiHealthData.putDouble(BleConstants.BODY_FAT_RATE, ckmVar.getBodyFatRat());
                }
            } else if (healthData instanceof deo) {
                a(hiHealthData, (deo) healthData, true);
            }
            hiHealthData.setEndTime(healthData.getEndTime());
            hiHealthData.setDeviceUuid(this.c);
            hiHealthData.setOwnerId(0);
            LogUtil.a("PluginDevice_PluginDeviceDataStored", "convertData EndTime is ", Long.valueOf(healthData.getEndTime()));
            return hiHealthData;
        }
        if (healthData instanceof der) {
            hiHealthData.setValue(((der) healthData).getHeartRate());
        }
        hiHealthData.setEndTime(healthData.getEndTime());
        hiHealthData.setDeviceUuid(this.c);
        hiHealthData.setOwnerId(0);
        LogUtil.a("PluginDevice_PluginDeviceDataStored", "convertData EndTime is ", Long.valueOf(healthData.getEndTime()));
        return hiHealthData;
    }

    private void d(deb debVar, HiHealthData hiHealthData) {
        float bloodSugar = debVar.getBloodSugar();
        hiHealthData.setType(deb.e(debVar.getStartTime()));
        hiHealthData.setValue(bloodSugar);
        HiBloodSugarMetaData hiBloodSugarMetaData = new HiBloodSugarMetaData();
        hiBloodSugarMetaData.setConfirmed(false);
        hiHealthData.setMetaData(HiJsonUtil.e(hiBloodSugarMetaData));
    }

    private void a(HiHealthData hiHealthData, deo deoVar, boolean z) {
        if (!z) {
            hiHealthData.putShort("BLOOD_PRESSURE_DIASTOLIC", deoVar.getDiastolic());
            hiHealthData.putShort("BLOOD_PRESSURE_SYSTOLIC", deoVar.getSystolic());
            hiHealthData.putShort(BleConstants.BLOODPRESSURE_SPHYGMUS, deoVar.getHeartRate());
            return;
        }
        hiHealthData.setValue((int) deoVar.getHeartRate());
    }

    public void a(List<HiHealthData> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("PluginDevice_PluginDeviceDataStored", "putDataIntoDataEngine enter list");
        if (koq.b(list)) {
            LogUtil.h("PluginDevice_PluginDeviceDataStored", "putDataIntoDataEngine dataList is null");
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.h("PluginDevice_PluginDeviceDataStored", "putDataIntoDataEngine callback is null");
            return;
        }
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getType() == 2018) {
                arrayList.add(hiHealthData);
            } else if (hiHealthData.getType() == 10002) {
                HashMap hashMap = new HashMap(3);
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()), Double.valueOf(hiHealthData.getShort("BLOOD_PRESSURE_SYSTOLIC")));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value()), Double.valueOf(hiHealthData.getShort("BLOOD_PRESSURE_DIASTOLIC")));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()), Double.valueOf(hiHealthData.getShort(BleConstants.BLOODPRESSURE_SPHYGMUS)));
                hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
                arrayList2.add(hiHealthData);
            } else {
                arrayList2.add(hiHealthData);
            }
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList2);
        final HiHealthApi d = HiHealthManager.d(cpp.a());
        d.insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: cka
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                cjz.c(arrayList, d, iBaseResponseCallback, i, obj);
            }
        });
    }

    static /* synthetic */ void c(List list, HiHealthApi hiHealthApi, final IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("PluginDevice_PluginDevice", "PluginDeviceDataStored insert normal data type is " + i);
        if (list.size() > 0 && i == 0) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            hiDataInsertOption.setDatas(list);
            hiHealthApi.insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: ckg
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i2, Object obj2) {
                    cjz.c(IBaseResponseCallback.this, i2, obj2);
                }
            });
            return;
        }
        iBaseResponseCallback.d(i, obj);
    }

    static /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("PluginDevice_PluginDevice", "PluginDeviceDataStored insert heartRate data type is " + i);
        iBaseResponseCallback.d(i, obj);
    }
}
