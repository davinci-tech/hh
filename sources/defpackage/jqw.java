package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.syncmgr.menstrual.MenstrualSyncApi;
import health.compact.a.EnvironmentUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@ApiDefine(uri = MenstrualSyncApi.class)
@Singleton
/* loaded from: classes5.dex */
public class jqw implements MenstrualSyncApi {
    @Override // com.huawei.syncmgr.menstrual.MenstrualSyncApi
    public boolean syncMenstrualToDevice(List<cvo> list) {
        DeviceInfo a2 = a();
        if (a2 == null) {
            LogUtil.h("MenstrualSyncApiImpl", "syncDataToDevice deviceInfo==null");
            return false;
        }
        if (a2.getDeviceConnectState() != 2) {
            LogUtil.h("MenstrualSyncApiImpl", "syncDataToDevice unConnected");
            return false;
        }
        if (koq.b(list)) {
            LogUtil.h("MenstrualSyncApiImpl", "pointInfoList isEmpty");
            return false;
        }
        cvi cviVar = new cvi();
        cviVar.setSrcPkgName("hw.unitedevice.physiological");
        cviVar.setWearPkgName("hw.watch.health.physiological");
        cviVar.b(400018L);
        cviVar.setCommandId(4);
        cviVar.b(list);
        boolean sendDictionaryPointInfoCommand = cuk.b().sendDictionaryPointInfoCommand(a2, cviVar, "MenstrualSyncApiImpl");
        LogUtil.a("MenstrualSyncApiImpl", "syncMenstrualToDevice() sent (", cviVar, ") dictionaryPointInfo isValid: ", Boolean.valueOf(sendDictionaryPointInfoCommand));
        return sendDictionaryPointInfoCommand;
    }

    @Override // com.huawei.syncmgr.menstrual.MenstrualSyncApi
    public List<HiHealthData> getMenstrualFlow(long j, long j2, long j3) {
        LogUtil.a("MenstrualSyncApiImpl", "startTime: ", Long.valueOf(j), " endTime: ", Long.valueOf(j2), " modifyTime: ", Long.valueOf(j3));
        ArrayList arrayList = new ArrayList();
        List<HiHealthData> e = e(j, j2);
        if (koq.c(e)) {
            d(j, j2, j3, e);
            for (HiHealthData hiHealthData : e) {
                if (hiHealthData.getModifiedTime() > j3) {
                    arrayList.add(hiHealthData);
                }
            }
        } else {
            d(j, j2, j3, arrayList);
        }
        LogUtil.a("MenstrualSyncApiImpl", "menstrualListSize:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private List<HiHealthData> e(long j, long j2) {
        int value = DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_QUANTITY.value();
        int value2 = DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_DYSMENORRHEA.value();
        final HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{value, value2});
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ArrayList arrayList = new ArrayList();
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: jqw.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("MenstrualSyncApiImpl", "readHiHealthData onResult errorCode = ", Integer.valueOf(i));
                if (obj == null) {
                    LogUtil.h("MenstrualSyncApiImpl", "readHiHealthData onResult data is null errorCode = ", Integer.valueOf(i));
                    countDownLatch.countDown();
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("MenstrualSyncApiImpl", "readHiHealthData onResult map isEmpty");
                    countDownLatch.countDown();
                    return;
                }
                for (int i3 : hiDataReadOption.getType()) {
                    List list = (List) sparseArray.get(i3);
                    if (koq.b(list)) {
                        LogUtil.h("MenstrualSyncApiImpl", "pressureMeasureValueList isEmpty type: ", Integer.valueOf(i3));
                    } else {
                        LogUtil.a("MenstrualSyncApiImpl", "readHiHealthData onResult() pressureMeasureValueList.size=", Integer.valueOf(list.size()), "type = ", Integer.valueOf(i3));
                        LogUtil.c("MenstrualSyncApiImpl", "readHiHealthData onResult() pressureMeasureValueList = ", HiJsonUtil.e(list));
                        arrayList.addAll(list);
                    }
                }
                LogUtil.a("MenstrualSyncApiImpl", "readHiHealthData onResult() result.size=", Integer.valueOf(arrayList.size()));
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("MenstrualSyncApiImpl", "readHiHealthData onResultIntent errorCode = ", Integer.valueOf(i2));
            }
        });
        try {
            if (!countDownLatch.await(5L, TimeUnit.SECONDS)) {
                ReleaseLogUtil.c("MenstrualSyncApiImpl", "readHiHealthData() timeout");
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("MenstrualSyncApiImpl", "readHiHealthData() interruptedException");
        }
        return arrayList;
    }

    private void d(long j, long j2, final long j3, final List<HiHealthData> list) {
        final HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setType(new int[]{47402, 47403});
        hiDataReadOption.setConstantsKey(new String[]{"menstrual_quantity", "menstrual_dysmenorrhea"});
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: jqw.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData onResult errorCode = ", Integer.valueOf(i));
                if (obj == null) {
                    LogUtil.h("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData onResult data is null errorCode = ", Integer.valueOf(i));
                    countDownLatch.countDown();
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData onResult map isEmpty");
                    countDownLatch.countDown();
                    return;
                }
                for (int i3 : hiDataReadOption.getType()) {
                    List<HiHealthData> list2 = (List) sparseArray.get(i3);
                    if (koq.b(list2)) {
                        LogUtil.h("MenstrualSyncApiImpl", "getMenstrualStat pressureMeasureValueList isEmpty type: ", Integer.valueOf(i3));
                    } else {
                        LogUtil.a("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData onResult() pressureMeasureValueList.size=", Integer.valueOf(list2.size()), "type = ", Integer.valueOf(i3));
                        LogUtil.c("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData onResult() pressureMeasureValueList = ", HiJsonUtil.e(list2));
                        int value = DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_QUANTITY.value();
                        int value2 = DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_DYSMENORRHEA.value();
                        for (HiHealthData hiHealthData : list2) {
                            if (hiHealthData.getModifiedTime() > j3) {
                                if (i3 == 47402) {
                                    hiHealthData.setType(value);
                                    jqw.this.e((List<HiHealthData>) list, hiHealthData);
                                } else if (i3 == 47403) {
                                    hiHealthData.setType(value2);
                                    jqw.this.e((List<HiHealthData>) list, hiHealthData);
                                }
                            }
                        }
                    }
                }
                LogUtil.a("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData onResult() result.size=", Integer.valueOf(list.size()));
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData onResultIntent errorCode = ", Integer.valueOf(i2));
            }
        });
        try {
            if (countDownLatch.await(5L, TimeUnit.SECONDS)) {
                return;
            }
            ReleaseLogUtil.c("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData() timeout");
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("MenstrualSyncApiImpl", "getMenstrualStat readHiHealthData() interruptedException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, HiHealthData hiHealthData) {
        if (list == null) {
            LogUtil.h("MenstrualSyncApiImpl", "menstrualList is null");
            return;
        }
        if (hiHealthData == null) {
            LogUtil.h("MenstrualSyncApiImpl", "hiHealthData is null");
            return;
        }
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 == null) {
                LogUtil.h("MenstrualSyncApiImpl", "temData is null");
            } else if (hiHealthData.getType() == hiHealthData2.getType() && hiHealthData.getStartTime() == hiHealthData2.getStartTime() && hiHealthData.getEndTime() == hiHealthData2.getEndTime()) {
                LogUtil.h("MenstrualSyncApiImpl", "list contain hiHealthData");
                return;
            }
        }
        list.add(hiHealthData);
    }

    private DeviceInfo a() {
        if (EnvironmentUtils.e()) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "MenstrualSyncApiImpl");
            if (!koq.b(deviceList)) {
                return deviceList.get(0);
            }
            LogUtil.a("MenstrualSyncApiImpl", "deviceInfo is empty");
            return null;
        }
        return cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "MenstrualSyncApiImpl");
    }
}
