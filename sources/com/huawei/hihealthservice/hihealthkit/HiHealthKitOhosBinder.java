package com.huawei.hihealthservice.hihealthkit;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.hihealth.ClientToken;
import com.huawei.hihealth.DataReportModel;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiHealthDataQuery;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.HiHealthKitDataOhos;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.ICommonListenerOhos;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataOperateListenerOhos;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IDataReadResultListenerOhos;
import com.huawei.hihealth.IHiHealthKitOhos;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.util.HiFileUtil;
import com.huawei.hihealthservice.InsertExecutor;
import defpackage.cvx;
import defpackage.ioy;
import defpackage.iqy;
import defpackage.iqz;
import defpackage.irc;
import defpackage.ird;
import defpackage.iwi;
import defpackage.koq;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.WhiteBoxManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HiHealthKitOhosBinder extends IHiHealthKitOhos.Stub {

    /* renamed from: a, reason: collision with root package name */
    private HiHealthKitBinder f4175a;
    private Context d;

    public HiHealthKitOhosBinder(Context context, InsertExecutor insertExecutor) {
        this.d = context;
        this.f4175a = new HiHealthKitBinder(context, insertExecutor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> d(List list) {
        ArrayList arrayList = new ArrayList(16);
        if (!koq.b(list)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(it.next()));
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void getGenderOhos(int i, final ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter getGenderOhos");
        if (iCommonListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "getGender", " listener is null");
            c("getGender", 2);
        } else {
            this.f4175a.getGender(i, new ICommonListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.5
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onSuccess(i2, HiHealthKitOhosBinder.this.d(list));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onFailure(i2, HiHealthKitOhosBinder.this.d(list));
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void getBirthdayOhos(int i, final ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter getBirthdayOhos");
        if (iCommonListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "getBirthday", " listener is null");
            c("getBirthday", 2);
        } else {
            this.f4175a.getBirthday(i, new ICommonListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.1
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onSuccess(i2, HiHealthKitOhosBinder.this.d(list));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onFailure(i2, HiHealthKitOhosBinder.this.d(list));
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void getHeightOhos(int i, final ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter getHeight");
        if (iCommonListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "getHeight", " listener is null");
            c("getHeight", 2);
        } else {
            this.f4175a.getHeight(i, new ICommonListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.3
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onSuccess(i2, HiHealthKitOhosBinder.this.d(list));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onFailure(i2, HiHealthKitOhosBinder.this.d(list));
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void getWeightOhos(int i, final ICommonListenerOhos iCommonListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter getWeightOhos");
        if (iCommonListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", " listener is null");
            c("getWeight", 2);
        } else {
            this.f4175a.getWeight(i, new ICommonListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.6
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onSuccess(i2, HiHealthKitOhosBinder.this.d(list));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i2, List list) throws RemoteException {
                    iCommonListenerOhos.onFailure(i2, HiHealthKitOhosBinder.this.d(list));
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void execQueryOhos(int i, final IDataReadResultListenerOhos iDataReadResultListenerOhos, int i2, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter execQueryOhos");
        if (iDataReadResultListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "execQuery", " listener is null");
            c("execQuery", 2);
        } else {
            this.f4175a.execQuery(i, hiHealthDataQuery, 0, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.7
                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i3, int i4) throws RemoteException {
                    if (list == null) {
                        iDataReadResultListenerOhos.onResult(null, i3, i4);
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    if (list.isEmpty()) {
                        iDataReadResultListenerOhos.onResult(arrayList, i3, i4);
                        return;
                    }
                    for (Object obj : list) {
                        if (obj instanceof HiHealthKitData) {
                            HiHealthKitDataOhos hiHealthKitDataOhos = new HiHealthKitDataOhos();
                            HiHealthKitData hiHealthKitData = (HiHealthKitData) obj;
                            hiHealthKitDataOhos.setContentValues(hiHealthKitData.getContentValue());
                            hiHealthKitDataOhos.setMap(hiHealthKitData.getMap());
                            hiHealthKitDataOhos.setType(hiHealthKitData.getType());
                            arrayList.add(hiHealthKitDataOhos);
                        }
                    }
                    iDataReadResultListenerOhos.onResult(arrayList, i3, i4);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void getCountOhos(int i, final IDataReadResultListenerOhos iDataReadResultListenerOhos, final HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter getCountOhos");
        if (iDataReadResultListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "getCount", " listener is null");
            c("getCount", 2);
        } else {
            this.f4175a.getCount(i, hiHealthDataQuery, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.9
                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i2, int i3) throws RemoteException {
                    ArrayList arrayList = new ArrayList();
                    if (koq.b(list)) {
                        iDataReadResultListenerOhos.onResult(arrayList, i2, i3);
                        return;
                    }
                    for (Object obj : list) {
                        if (obj instanceof Integer) {
                            HiHealthKitDataOhos hiHealthKitDataOhos = new HiHealthKitDataOhos();
                            HashMap hashMap = new HashMap(1);
                            hashMap.put(Integer.valueOf(hiHealthDataQuery.getSampleType()), (Integer) obj);
                            hiHealthKitDataOhos.setMap(hashMap);
                            arrayList.add(hiHealthKitDataOhos);
                        }
                    }
                    iDataReadResultListenerOhos.onResult(arrayList, i2, i3);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void saveSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, final IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter saveSampleOhos");
        if (iDataOperateListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveSample", " listener is null");
            c("saveSample", 2);
        } else if (hiHealthKitDataOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveSample", " data is null");
            c("saveSample", 2);
        } else {
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            c(hiHealthKitData, hiHealthKitDataOhos);
            this.f4175a.saveSample(i, hiHealthKitData, new IDataOperateListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.8
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i2, List list) throws RemoteException {
                    HiHealthKitOhosBinder.this.a(i2, list, iDataOperateListenerOhos);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void saveSamplesOhos(int i, List<HiHealthKitDataOhos> list, final IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter saveSamplesOhos");
        if (iDataOperateListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveSamples", " listener is null");
            c("saveSamples", 2);
        } else if (koq.b(list)) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveSamples", " data is null");
            c("saveSamples", 2);
        } else {
            ArrayList arrayList = new ArrayList();
            e(arrayList, list);
            this.f4175a.saveSamples(i, arrayList, new IDataOperateListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.10
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i2, List list2) throws RemoteException {
                    HiHealthKitOhosBinder.this.a(i2, list2, iDataOperateListenerOhos);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void deleteSampleOhos(int i, HiHealthKitDataOhos hiHealthKitDataOhos, final IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter deleteSampleOhos");
        if (iDataOperateListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "deleteSample", " listener is null");
            c("deleteSample", 2);
        } else if (hiHealthKitDataOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "deleteSample", " data is null");
            c("deleteSample", 2);
        } else {
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            c(hiHealthKitData, hiHealthKitDataOhos);
            this.f4175a.deleteSample(i, hiHealthKitData, new IDataOperateListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.14
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i2, List list) throws RemoteException {
                    HiHealthKitOhosBinder.this.a(i2, list, iDataOperateListenerOhos);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void deleteSamplesOhos(int i, List<HiHealthKitDataOhos> list, final IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter deleteSamplesOhos");
        if (iDataOperateListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "deleteSamples", " listener is null");
            c("deleteSamples", 2);
        } else if (koq.b(list)) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "deleteSamples", " data is null");
            c("deleteSamples", 2);
        } else {
            ArrayList arrayList = new ArrayList();
            e(arrayList, list);
            this.f4175a.deleteSamples(i, arrayList, new IDataOperateListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.4
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i2, List list2) throws RemoteException {
                    HiHealthKitOhosBinder.this.a(i2, list2, iDataOperateListenerOhos);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void startReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter startReadingRRIOhos");
        this.f4175a.startReadingRRI(i, iRealTimeDataCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void stopReadingRRIOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter stopReadingRRIOhos");
        this.f4175a.stopReadingRRI(i, iRealTimeDataCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void registerToken(ClientToken clientToken) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter registerToken");
        this.f4175a.setBinder(clientToken.asBinder());
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void startSportOhos(int i, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter startSportOhos");
        this.f4175a.startSport(i, iCommonCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void startSportEnhanceOhos(StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter startSportEnhanceOhos");
        this.f4175a.startSportEnhance(startSportParam, iCommonCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void stopSportOhos(ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter stopSportOhos");
        this.f4175a.stopSport(iCommonCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void registerSportDataOhos(ISportDataCallback iSportDataCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter registerSportDataOhos");
        this.f4175a.registerRealTimeSportCallback(iSportDataCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void unregisterSportDataOhos(ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter unregisterSportDataOhos");
        this.f4175a.unregisterRealTimeSportCallback(iCommonCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public String getCategory(int i) {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter getCategory");
        return this.f4175a.getCategory(i);
    }

    private void c(HiHealthKitData hiHealthKitData, HiHealthKitDataOhos hiHealthKitDataOhos) {
        hiHealthKitData.setType(hiHealthKitDataOhos.getType());
        hiHealthKitData.setMap(hiHealthKitDataOhos.getMap());
        hiHealthKitData.setContentValues(hiHealthKitDataOhos.getContentValues());
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void startReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter startReadingHeartRateOhos");
        this.f4175a.startReadingHeartRate(i, iRealTimeDataCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void stopReadingHeartRateOhos(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter stopReadingHeartRateOhos");
        this.f4175a.stopReadingHeartRate(i, iRealTimeDataCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void setKitVersionOhos(String str) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter setKitVersionOhos");
        iqz.a(iwi.a(this.d), str);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void registerPackageName(String str) {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter registerPackageName, packageName: ", str);
        iwi.d(this.d, str);
        ird.d(this.d).d();
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public int getServiceApiLevel() throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter getServiceApiLevel");
        return this.f4175a.getServiceApiLevel();
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void registerDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter registerDataAutoReportOhos");
        this.f4175a.registerDataAutoReport(dataReportModel, iRegisterRealTimeCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void unregisterDataAutoReportOhos(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter unregisterDataAutoReportOhos");
        this.f4175a.unregisterDataAutoReport(dataReportModel, iRegisterRealTimeCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter bindDevice");
        this.f4175a.bindDevice(str, str2, str3, i, iCommonCallback);
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void queryDataOhos(HealthKitDictQuery healthKitDictQuery, final IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter queryDataOhos");
        if (iDataReadResultListenerOhos == null) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "queryData", " listener is null");
            c("queryData", 2);
        } else {
            this.f4175a.queryData(healthKitDictQuery, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitOhosBinder.2
                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i, int i2) throws RemoteException {
                    if (list == null || list.isEmpty()) {
                        iDataReadResultListenerOhos.onResult(null, i, i2);
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : list) {
                        if (obj instanceof HiHealthKitData) {
                            HiHealthKitDataOhos hiHealthKitDataOhos = new HiHealthKitDataOhos();
                            HiHealthKitData hiHealthKitData = (HiHealthKitData) obj;
                            hiHealthKitDataOhos.setContentValues(hiHealthKitData.getContentValue());
                            hiHealthKitDataOhos.setMap(hiHealthKitData.getMap());
                            hiHealthKitDataOhos.setType(hiHealthKitData.getType());
                            arrayList.add(hiHealthKitDataOhos);
                        }
                    }
                    iDataReadResultListenerOhos.onResult(arrayList, i, i2);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitOhos
    public void saveDeviceInfo(String str) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter saveDeviceInfo");
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveDeviceInfo: deviceInfo is null");
            return;
        }
        if (!ioy.b("saveDeviceInfo", null)) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveDeviceInfo: privacy not permitted");
            return;
        }
        if (!"com.huawei.ohos.health.device".equals(iwi.a(this.d))) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveDeviceInfo: Not FA Package");
            return;
        }
        LogUtil.c("HiHealthKitOhosBinder", "saveDeviceInfo: start");
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("uniqueId");
            String optString2 = jSONObject.optString("productId");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveDeviceInfo: uniqueId or productId is empty");
                return;
            }
            if (((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).getBondedDeviceByUniqueId(optString) != null) {
                if (jSONObject.optBoolean("bindFlag")) {
                    ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "saveDeviceInfo: device already bound");
                    return;
                } else {
                    ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "saveDeviceInfo: unbind device");
                    ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).unbindDeviceByUniqueId(optString);
                    return;
                }
            }
            b(jSONObject, optString);
        } catch (JSONException unused) {
            ReleaseLogUtil.c("HiH_HiHealthKitOhosBinder", "saveDeviceInfo: deviceInfo is not correct json format");
            throw new RemoteException("deviceInfo is not correct json format");
        }
    }

    private void b(JSONObject jSONObject, String str) {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "saveDeviceInner");
        String d = HiFileUtil.d(this.d, "cache_data_from_fa");
        JSONObject jSONObject2 = new JSONObject();
        if (TextUtils.isEmpty(d)) {
            d(jSONObject, str, jSONObject2);
            return;
        }
        try {
            jSONObject2 = new JSONObject(d);
        } catch (JSONException e) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "saveDeviceInner: JSONException = ", e.getMessage());
        }
        if (jSONObject2.isNull(String.valueOf(str.hashCode()))) {
            d(jSONObject, str, jSONObject2);
        } else {
            b(jSONObject, str, jSONObject2);
        }
    }

    private void d(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter handleDeviceNotInCache");
        if (!jSONObject.optBoolean("bindFlag")) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "handleDeviceNotInCache: no cache data and unbind, return");
        } else {
            c(jSONObject, str, jSONObject2);
        }
    }

    private void b(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "handleDeviceInCache");
        boolean optBoolean = jSONObject.optBoolean("bindFlag");
        String valueOf = String.valueOf(str.hashCode());
        if (optBoolean) {
            LogUtil.c("HiHealthKitOhosBinder", "handleDeviceInCache: this device info is already cached");
            JSONObject optJSONObject = jSONObject2.optJSONObject(valueOf);
            try {
                optJSONObject.putOpt("uniqueId", new String(WhiteBoxManager.d().a(cvx.a(optJSONObject.optString("uniqueId"))), StandardCharsets.UTF_8));
            } catch (JSONException e) {
                ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "handleDeviceInCache: JSONException = ", e.getMessage());
            }
            if (optJSONObject.toString().equals(jSONObject.toString())) {
                ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "handleDeviceInCache: this device info doesn't change");
                return;
            } else {
                c(jSONObject, str, jSONObject2);
                return;
            }
        }
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "handleDeviceInCache: delete cached info of this device");
        jSONObject2.remove(valueOf);
        if (jSONObject2.length() <= 0) {
            ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "handleDeviceInCache: no more device info, delete file");
            FileUtils.d(new File(this.d.getFilesDir(), "cache_data_from_fa"));
        } else {
            ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "handleDeviceInCache: cache remaining device info to file");
            HiFileUtil.c(this.d, jSONObject2.toString(), "cache_data_from_fa");
        }
    }

    private void c(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "enter writeDataToFile");
        String d = cvx.d(WhiteBoxManager.d().b(str));
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "writeDataToFile: encodedUniqueId is null, return");
            return;
        }
        try {
            jSONObject.putOpt("uniqueId", d);
            jSONObject2.putOpt(String.valueOf(str.hashCode()), jSONObject);
            HiFileUtil.c(this.d, jSONObject2.toString(), "cache_data_from_fa");
        } catch (JSONException e) {
            ReleaseLogUtil.d("HiH_HiHealthKitOhosBinder", "writeDataToFile: put object failed: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, List list, IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            iDataOperateListenerOhos.onResult(i, arrayList);
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next()));
        }
        iDataOperateListenerOhos.onResult(i, arrayList);
    }

    private void e(List<HiHealthKitData> list, List<HiHealthKitDataOhos> list2) {
        for (HiHealthKitDataOhos hiHealthKitDataOhos : list2) {
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            c(hiHealthKitData, hiHealthKitDataOhos);
            list.add(hiHealthKitData);
        }
    }

    private void c(String str, int i) {
        new irc().c(this.d, new iqy(str, i, iwi.a(this.d)));
    }

    public void onDestroy() {
        ReleaseLogUtil.e("HiH_HiHealthKitOhosBinder", "onDestroy");
    }
}
