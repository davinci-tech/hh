package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.weight.WeightApi;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kfu {

    /* renamed from: a, reason: collision with root package name */
    private static kfu f14354a;
    private static final Object c = new Object();
    private List<Integer> b;

    public static kfu e() {
        kfu kfuVar;
        synchronized (c) {
            if (f14354a == null) {
                LogUtil.a("HwSyncWeightManager", "getInstance()");
                f14354a = new kfu();
            }
            kfuVar = f14354a;
        }
        return kfuVar;
    }

    private kfu() {
        LogUtil.a("R_Weight_HwSyncWeightManager", "HwSyncWeightManager");
        d();
        c();
        a();
        e("900300022");
        e("900300023");
    }

    public void a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(103);
        arrayList.add(Integer.valueOf(HiSubscribeType.c));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.DIET_RECORD_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value()));
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(arrayList, new HiSubscribeListener() { // from class: kfu.3
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                kfu.this.b = list;
                LogUtil.a("HwSyncWeightManager", "onResult:", list);
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                LogUtil.a("HwSyncWeightManager", "onChange changeKey.", str, " type ", Integer.valueOf(i));
                if (i == 103) {
                    if ("900300022".equals(str) || "900300023".equals(str)) {
                        kfu.this.e(str);
                        ((WeightApi) Services.c("Main", WeightApi.class)).refreshDietOverview(DateFormatUtil.b(System.currentTimeMillis()));
                        return;
                    }
                    return;
                }
                if (i == HiSubscribeType.c) {
                    kfu.this.e(System.currentTimeMillis() - 604800000);
                    return;
                }
                if (i == DicDataTypeUtil.DataType.DIET_RECORD_SET.value() && "sync download".equals(str)) {
                    dpe.e(0L, 0L, keb.b("HwSyncWeightManager"));
                } else if (i == DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value() && ArkUIXConstants.INSERT.equals(str)) {
                    grz.h();
                    gsd.t();
                    dpe.e(System.currentTimeMillis(), System.currentTimeMillis(), keb.b("HwSyncWeightManager"));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j) {
        LogUtil.a("HwSyncWeightManager", "updateLatestWeightData");
        kot.a().c(j, new IBaseResponseCallback() { // from class: kfu.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 100001 || !(obj instanceof HiHealthData)) {
                    LogUtil.b("HwSyncWeightManager", "objData is null");
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) obj;
                if (i != 0) {
                    if (i == 100002) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(kfu.this.d(DicDataTypeUtil.DataType.BODY_WEIGHT.value(), hiHealthData.getDouble("bodyWeight")));
                        kfu.this.c(arrayList, hiHealthData);
                        return;
                    }
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(kfu.this.d(DicDataTypeUtil.DataType.BODY_WEIGHT.value(), hiHealthData.getDouble("bodyWeight")));
                arrayList2.add(kfu.this.d(DicDataTypeUtil.DataType.BODY_FAT_RATE.value(), hiHealthData.getDouble(BleConstants.BODY_FAT_RATE)));
                arrayList2.add(kfu.this.d(DicDataTypeUtil.DataType.BASAL_METABOLISM.value(), hiHealthData.getDouble(BleConstants.BASAL_METABOLISM)));
                arrayList2.add(kfu.this.d(DicDataTypeUtil.DataType.SKELETAL_MUSCLEL_MASS.value(), hiHealthData.getDouble("skeletalMusclelMass")));
                kfu.this.c(arrayList2, hiHealthData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cvv d(int i, double d) {
        cvv cvvVar = new cvv();
        cvvVar.d(i);
        cvvVar.b(cvx.d(d));
        return cvvVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cvv c(int i, int i2) {
        cvv cvvVar = new cvv();
        cvvVar.d(i);
        cvvVar.b(cvx.g(i2));
        return cvvVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final String str) {
        LogUtil.a("HwSyncWeightManager", "updateWeightDataToDevice ", str);
        kot.a().b("9003", str, new HiDataReadResultListener() { // from class: kfu.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (!koq.e(obj, HiSampleConfig.class)) {
                    ReleaseLogUtil.d("R_Weight_HwSyncWeightManager", "onResult objData is not instanceof HiSampleConfig");
                    return;
                }
                if (i == 2) {
                    ReleaseLogUtil.d("R_Weight_HwSyncWeightManager", "just in transferring");
                    return;
                }
                List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h("HwSyncWeightManager", "onResult list is empty");
                    return;
                }
                HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                String configData = hiSampleConfig.getConfigData();
                if ("900300023".equals(str)) {
                    kfu.this.a(keb.b("HwSyncWeightManager"), (gsi) HiJsonUtil.e(configData, gsi.class), hiSampleConfig.getModifiedTime());
                    dpe.e(System.currentTimeMillis(), System.currentTimeMillis(), keb.b("HwSyncWeightManager"));
                } else if ("900300022".equals(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(configData);
                        jSONObject.getInt("weightGoal");
                        kfu.this.a(keb.b("HwSyncWeightManager"), jSONObject.getDouble("weightGoal"), hiSampleConfig.getModifiedTime());
                    } catch (JSONException unused) {
                        LogUtil.b("HwSyncWeightManager", "parse exception when get weight goal");
                    }
                }
            }
        });
    }

    public void d() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.weightMgr", new DataReceiveCallback() { // from class: kfu.5
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "receive device event.", Integer.valueOf(i));
                if (cvrVar instanceof cvp) {
                    cvp cvpVar = (cvp) cvrVar;
                    LogUtil.a("HwSyncWeightManager", "message instanceof SampleEvent.", cvpVar.toString());
                    kfu.this.b(keb.b("HwSyncWeightManager"), cvpVar);
                } else if (cvrVar instanceof cvq) {
                    cvq cvqVar = (cvq) cvrVar;
                    LogUtil.a("HwSyncWeightManager", "message instanceof SampleConfig.", cvqVar.toString());
                    kfu.this.d(cvqVar);
                } else {
                    if (cvrVar instanceof cvi) {
                        cvi cviVar = (cvi) cvrVar;
                        LogUtil.a("HwSyncWeightManager", "message instanceof DictionaryPointInfo.", cviVar.toString());
                        kfu.this.e(cviVar);
                        return;
                    }
                    ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "diet receive unrecognized message.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(cvi cviVar) {
        LogUtil.c("HwSyncWeightManager", "sampleConfig is:", cviVar);
        if (cviVar.d() != DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value()) {
            ReleaseLogUtil.d("R_Weight_HwSyncWeightManager", "onDataReceived, message dictTypeId", Long.valueOf(cviVar.d()));
            return;
        }
        cvo cvoVar = cviVar.b().get(0);
        if (cvoVar == null) {
            LogUtil.h("HwSyncWeightManager", "getDataInfoList onResult pointInfo is null");
            return;
        }
        kot.a().b(cvoVar.c(), cvoVar.d(), new IBaseResponseCallback() { // from class: kfu.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (!koq.e(obj, HiHealthData.class)) {
                    LogUtil.h("HwSyncWeightManager", "objData is not instance Of List<HiHealthData>");
                    return;
                }
                LogUtil.a("HwSyncWeightManager", "HistoryRestMetaData is ", obj);
                ArrayList arrayList = new ArrayList();
                for (HiHealthData hiHealthData : (List) obj) {
                    ArrayList arrayList2 = new ArrayList();
                    cvo cvoVar2 = new cvo();
                    cvoVar2.a(hiHealthData.getStartTime());
                    cvoVar2.c(hiHealthData.getEndTime());
                    cvoVar2.b(hiHealthData.getModifiedTime());
                    if (hiHealthData.getInt("basalMetabolismAfterExercise") != 0) {
                        arrayList2.add(kfu.this.c(DicDataTypeUtil.DataType.RESTING_METABOLISM.value(), hiHealthData.getInt("basalMetabolismAfterExercise")));
                    } else {
                        arrayList2.add(kfu.this.c(DicDataTypeUtil.DataType.RESTING_METABOLISM.value(), hiHealthData.getInt(BleConstants.BASAL_METABOLISM)));
                    }
                    cvoVar2.a(arrayList2);
                    arrayList.add(cvoVar2);
                }
                if (koq.c(arrayList)) {
                    kfu.this.c(arrayList);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(cvq cvqVar) {
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        if (koq.b(configInfoList) || configInfoList.get(0) == null) {
            ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "infoList empty");
            return;
        }
        long longValue = configInfoList.get(0).c().longValue();
        long a2 = configInfoList.get(0).a();
        kot.a().c("9003", String.valueOf(a2), HEXUtils.d(HEXUtils.a(configInfoList.get(0).b())), longValue, true);
    }

    public void c() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.common", new DataReceiveCallback() { // from class: kfu.7
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "receive device event.", Integer.valueOf(i));
                if (cvrVar instanceof cvp) {
                    cvp cvpVar = (cvp) cvrVar;
                    LogUtil.a("HwSyncWeightManager", "message instanceof SampleEvent.", cvpVar.toString());
                    kfu.this.b(keb.b("HwSyncWeightManager"), cvpVar);
                    return;
                }
                ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "vip flag receive unrecognized message.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, cvp cvpVar) {
        long e = cvpVar.e();
        if (e != 80030003) {
            if (e == 80030004) {
                LogUtil.h("HwSyncWeightManager", "onDataReceived eventId not support:", Long.valueOf(e));
                dpe.e(deviceInfo, cvpVar);
                return;
            }
            return;
        }
        LogUtil.h("HwSyncWeightManager", "onDataReceived eventId not support:", Long.valueOf(e));
        byte[] c2 = cvpVar.c();
        if (c2 == null) {
            LogUtil.h("HwSyncWeightManager", "onDataReceived eventData is null");
            return;
        }
        String d = HEXUtils.d(HEXUtils.a(c2));
        LogUtil.a("HwSyncWeightManager", "onDataReceived data:", d);
        try {
            JSONObject jSONObject = new JSONObject(d);
            long j = jSONObject.getLong("startTime");
            long j2 = jSONObject.getLong("endTime");
            int i = jSONObject.getInt("event");
            LogUtil.a("HwSyncWeightManager", "requestType:", Integer.valueOf(i), " startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
            if (i == 1) {
                long b = gib.b(j, 0);
                if (j > b) {
                    j = b;
                }
                dpe.e(j, j2, deviceInfo);
            } else if (i == 4) {
                a(deviceInfo, 1);
            } else if (i == 5) {
                e(j);
            } else if (i == 6) {
                d(deviceInfo, d);
            }
        } catch (JSONException unused) {
            ReleaseLogUtil.c("R_Weight_HwSyncWeightManager", "parse json exception");
        }
    }

    private void d(final DeviceInfo deviceInfo, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("meals");
            long j = jSONObject.getLong("startTime");
            ArrayList arrayList = (ArrayList) HiJsonUtil.b(string, new TypeToken<ArrayList<qul>>() { // from class: kfu.6
            }.getType());
            final long d = d(arrayList);
            if (koq.b(arrayList)) {
                LogUtil.h("HwSyncWeightManager", "deviceFastRecordMeal get meals is null");
                d(deviceInfo, j, 0);
            } else {
                grz.d(a(arrayList), new ResponseCallback<List<quh>>() { // from class: kfu.10
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onResponse(int i, List<quh> list) {
                        LogUtil.a("HwSyncWeightManager", "recordMeals errorCode is : ", Integer.valueOf(i), "time is : ", Long.valueOf(d));
                        kfu.this.d(deviceInfo, d, i);
                    }
                });
            }
        } catch (JsonSyntaxException | JSONException e) {
            LogUtil.b("HwSyncWeightManager", "JsonSyntaxException in deviceFastRecordMeal: ", e.getMessage());
        }
    }

    private List<qul> a(List<qul> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            qul qulVar = list.get(i);
            if (qulVar.h() == 0) {
                LogUtil.a("HwSyncWeightManager", "whichMeal is 0, error");
            } else {
                qulVar.c(qulVar.i() * 1000);
                if (TextUtils.isEmpty(qulVar.j())) {
                    LogUtil.a("HwSyncWeightManager", "deviceMeal time zone is empty");
                } else {
                    qulVar.c(jdl.q(System.currentTimeMillis()));
                }
                arrayList.add(qulVar);
            }
        }
        return arrayList;
    }

    public void a(DeviceInfo deviceInfo, gsi gsiVar, long j) {
        ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "enter sendWeightManagerCommand");
        if (gsiVar == null) {
            return;
        }
        LogUtil.a("HwSyncWeightManager", "weight manager is ", gsiVar.toString());
        cuk.b().sendSampleConfigCommand(deviceInfo, d(HiJsonUtil.d(gsiVar, gsi.class), CommonUtil.n(BaseApplication.getContext(), "900300023"), j), "HwSyncWeightManager");
    }

    public void a(DeviceInfo deviceInfo, int i) {
        ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "enter sendWeekReportCommand");
        LogUtil.a("HwSyncWeightManager", "stageType is ", Integer.valueOf(i));
        cuk.b().sendSampleEventCommand(deviceInfo, c(i, deviceInfo), "HwSyncWeightManager");
    }

    public void d(DeviceInfo deviceInfo, long j, int i) {
        ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "sendRecordMealsCommand resultCode is: ", Integer.valueOf(i), "time is: ", Long.valueOf(j));
        if (i != 0) {
            i = 102;
        }
        boolean c2 = cwi.c(deviceInfo, a.N);
        if (!c2) {
            LogUtil.h("HwSyncWeightManager", "isSupportCapability is: ", Boolean.valueOf(c2));
        } else {
            cuk.b().sendSampleEventCommand(deviceInfo, a(i, j), "HwSyncWeightManager");
        }
    }

    private long d(List<qul> list) {
        for (int i = 0; i < list.size(); i++) {
            qul qulVar = list.get(i);
            if (qulVar != null && qulVar.h() != 0) {
                long d = qulVar.d();
                LogUtil.a("HwSyncWeightManager", "getDeviceMealsTime is time", Long.valueOf(d));
                return d;
            }
        }
        return 0L;
    }

    private cvp a(int i, long j) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.weightMgr");
        cvpVar.setWearPkgName("hw.sport.dietcal");
        cvpVar.setCommandId(2);
        cvpVar.b(0);
        cvpVar.a(80030003L);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("punchResult", Integer.valueOf(i));
        if (i != 0) {
            jsonObject.addProperty("punchFailTime", Long.valueOf(j));
        }
        jsonObject.addProperty("event", (Number) 6);
        cvpVar.e(HEXUtils.c(HEXUtils.b(jsonObject.toString())));
        LogUtil.a("HwSyncWeightManager", "createRecordMealsSampleEvent ", cvpVar.toString(), "object is： ", jsonObject.toString());
        return cvpVar;
    }

    public void a(DeviceInfo deviceInfo, double d, long j) {
        ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "enter sendWeightGoalCommand");
        LogUtil.a("HwSyncWeightManager", "goal is ", Double.valueOf(d));
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("weightGoal", Double.valueOf(d));
        cuk.b().sendSampleConfigCommand(deviceInfo, d(jsonObject.toString(), CommonUtil.n(BaseApplication.getContext(), "900300022"), j), "HwSyncWeightManager");
    }

    public void c(List<cvo> list) {
        cvi cviVar = new cvi();
        cviVar.setSrcPkgName("hw.unitedevice.weightMgr");
        cviVar.setWearPkgName("hw.sport.dietcal");
        cviVar.b(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value());
        cviVar.setCommandId(4);
        cviVar.b(list);
        LogUtil.a("HwSyncWeightManager", "sendRestMetabolismDataCommand samplePoint: ", cviVar.toString());
        cuk.b().sendDictionaryPointInfoCommand(keb.b("HwSyncWeightManager"), cviVar, "HwSyncWeightManager");
    }

    public void c(List<cvv> list, HiHealthData hiHealthData) {
        ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "sendWeightDataCommand");
        ArrayList arrayList = new ArrayList();
        cvo cvoVar = new cvo();
        cvoVar.a(list);
        cvoVar.a(hiHealthData.getStartTime());
        cvoVar.c(hiHealthData.getEndTime());
        cvoVar.b(hiHealthData.getModifiedTime());
        arrayList.add(cvoVar);
        cvi cviVar = new cvi();
        cviVar.setSrcPkgName("hw.unitedevice.weightMgr");
        cviVar.setWearPkgName("hw.sport.dietcal");
        cviVar.b(DicDataTypeUtil.DataType.WEIGHT_BODYFAT_BROAD.value());
        cviVar.setCommandId(4);
        cviVar.b(arrayList);
        LogUtil.a("HwSyncWeightManager", "sendWeightDataCommand samplePoint: ", cviVar.toString());
        cuk.b().sendDictionaryPointInfoCommand(keb.b("HwSyncWeightManager"), cviVar, "HwSyncWeightManager");
    }

    private cvp c(int i, DeviceInfo deviceInfo) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.weightMgr");
        cvpVar.setWearPkgName("hw.sport.dietcal");
        cvpVar.setCommandId(2);
        cvpVar.b(0);
        cvpVar.a(80030003L);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stageType", Integer.valueOf(i));
        boolean c2 = cwi.c(deviceInfo, a.N);
        if (c2) {
            LogUtil.a("HwSyncWeightManager", "isSupportCapability is ", Boolean.valueOf(c2));
            jsonObject.addProperty("event", (Number) 4);
        }
        cvpVar.e(HEXUtils.c(HEXUtils.b(jsonObject.toString())));
        LogUtil.a("HwSyncWeightManager", "createWeekReportSampleEvent ", cvpVar.toString());
        return cvpVar;
    }

    private cvq d(String str, long j, long j2) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.weightMgr");
        cvqVar.setWearPkgName("hw.sport.dietcal");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(j);
        cvnVar.d(1);
        cvnVar.a(Long.valueOf(j2));
        cvnVar.c(HEXUtils.c(HEXUtils.b(str)));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        LogUtil.a("HwSyncWeightManager", "weight sample config ", cvqVar.toString());
        return cvqVar;
    }

    public static void b() {
        ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "releaseMgr");
        synchronized (c) {
            if (f14354a == null) {
                ReleaseLogUtil.e("R_Weight_HwSyncWeightManager", "sInstance is null");
                return;
            }
            cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.weightMgr");
            cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.common");
            if (koq.c(f14354a.b)) {
                HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(f14354a.b, new HiUnSubscribeListener() { // from class: kfu.9
                    @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                    public void onResult(boolean z) {
                        LogUtil.a("HwSyncWeightManager", "unSubscribeHiHealthData ", Boolean.valueOf(z));
                    }
                });
            }
            f14354a = null;
        }
    }
}
