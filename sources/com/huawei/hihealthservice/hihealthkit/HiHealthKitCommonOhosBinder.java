package com.huawei.hihealthservice.hihealthkit;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiHealthDataQuery;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.HiHealthKitDataOhos;
import com.huawei.hihealth.HiUserPreferenceData;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataOperateListenerOhos;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IDataReadResultListenerOhos;
import com.huawei.hihealth.IHiHealthKitCommonOhos;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealthkit.context.H5ProAppInfo;
import com.huawei.hihealthkit.context.OutOfBandData;
import com.huawei.hihealthservice.InsertExecutor;
import com.huawei.up.utils.Constants;
import defpackage.ioy;
import defpackage.ipd;
import defpackage.iqy;
import defpackage.irc;
import defpackage.iwi;
import defpackage.koq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class HiHealthKitCommonOhosBinder extends IHiHealthKitCommonOhos.Stub {
    private HiHealthKitExtendBinder b;
    private HiHealthKitBinder d;
    private Context e;

    public HiHealthKitCommonOhosBinder(Context context, InsertExecutor insertExecutor) {
        this.e = context;
        this.d = new HiHealthKitBinder(context, insertExecutor);
        this.b = new HiHealthKitExtendBinder(context, insertExecutor);
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void execQueryOhos(OutOfBandData outOfBandData, IDataReadResultListenerOhos iDataReadResultListenerOhos, int i, HiHealthDataQuery hiHealthDataQuery) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "execQuery");
        if (a(outOfBandData, iDataReadResultListenerOhos, "execQuery")) {
            return;
        }
        IDataReadResultListener e = e(iDataReadResultListenerOhos);
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.execQuery(outOfBandData, hiHealthDataQuery, i, e);
        } else {
            this.d.execQuery(0, hiHealthDataQuery, i, e);
        }
    }

    private void b(OutOfBandData outOfBandData, String str, int i) {
        String a2;
        String str2;
        irc ircVar = new irc();
        if (outOfBandData instanceof H5ProAppInfo) {
            H5ProAppInfo h5ProAppInfo = (H5ProAppInfo) outOfBandData;
            a2 = h5ProAppInfo.getPkgName();
            str2 = h5ProAppInfo.getAppId();
        } else {
            a2 = iwi.a(this.e);
            str2 = null;
        }
        ircVar.c(this.e, new iqy(str, i, a2, str2));
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void setKitVersion(String str) {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter setKitVersion");
        this.d.setKitVersion(str);
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void registerPackageName(String str) {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter registerPackageName");
        this.b.registerPackageName(str);
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public int getServiceApiLevel(OutOfBandData outOfBandData) {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter getServiceApiLevel");
        if (outOfBandData instanceof H5ProAppInfo) {
            return this.b.getServiceApiLevel(outOfBandData);
        }
        return this.d.getServiceApiLevel();
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public String getCategory(int i) {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter getCategory");
        return this.d.getCategory(i);
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void saveSamplesOhos(OutOfBandData outOfBandData, List<HiHealthKitDataOhos> list, final IDataOperateListenerOhos iDataOperateListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "saveSamples");
        if (a(outOfBandData, iDataOperateListenerOhos, "saveSamples")) {
            return;
        }
        if (koq.b(list)) {
            ReleaseLogUtil.d("HiH_HiHealthKitCommonOhosBinder", "saveSamples", " data is null");
            b(outOfBandData, "saveSamples", 2);
            iDataOperateListenerOhos.onResult(2, ipd.c(2));
            return;
        }
        ArrayList arrayList = new ArrayList();
        b(arrayList, list);
        IDataOperateListener.Stub stub = new IDataOperateListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitCommonOhosBinder.1
            @Override // com.huawei.hihealth.IDataOperateListener
            public void onResult(int i, List list2) throws RemoteException {
                HiHealthKitCommonOhosBinder.this.a(i, list2, iDataOperateListenerOhos);
            }
        };
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.saveSamples(outOfBandData, arrayList, stub);
        } else {
            this.d.saveSamples(0, arrayList, stub);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void queryDataOhos(OutOfBandData outOfBandData, HealthKitDictQuery healthKitDictQuery, IDataReadResultListenerOhos iDataReadResultListenerOhos) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "queryData");
        if (a(outOfBandData, iDataReadResultListenerOhos, "queryData")) {
            return;
        }
        IDataReadResultListener e = e(iDataReadResultListenerOhos);
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.queryData(outOfBandData, healthKitDictQuery, e);
        } else {
            this.d.queryData(healthKitDictQuery, e);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void getUserInfo(OutOfBandData outOfBandData, List<String> list, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", Constants.METHOD_GET_USER_INFO);
        if (a(outOfBandData, iCommonCallback, Constants.METHOD_GET_USER_INFO)) {
            return;
        }
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.getUserInfo(outOfBandData, list, iCommonCallback);
        } else {
            this.d.getUserInfo(list, iCommonCallback);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void setUserPreference(OutOfBandData outOfBandData, HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "setUserPreference");
        if (a(outOfBandData, iDataOperateListener, "setUserPreference")) {
            return;
        }
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.setUserPreference(outOfBandData, hiUserPreferenceData, z, iDataOperateListener);
        } else {
            this.d.setUserPreference(hiUserPreferenceData, z, iDataOperateListener);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void getUserPreference(OutOfBandData outOfBandData, List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "getUserPreference");
        if (a(outOfBandData, iDataOperateListener, "getUserPreference")) {
            return;
        }
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.getUserPreference(outOfBandData, list, iDataOperateListener);
        } else {
            this.d.getUserPreference(list, iDataOperateListener);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void startSportEnhance(OutOfBandData outOfBandData, StartSportParam startSportParam, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "startSportEnhance");
        if (a(outOfBandData, iCommonCallback, "startSportEnhance")) {
            return;
        }
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.startSportEnhance(outOfBandData, startSportParam, iCommonCallback);
        } else {
            this.d.startSportEnhance(startSportParam, iCommonCallback);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void stopSport(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "stopSport");
        if (a(outOfBandData, iCommonCallback, "stopSport")) {
            return;
        }
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.stopSport(outOfBandData, iCommonCallback);
        } else {
            this.d.stopSport(iCommonCallback);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void registerSportData(OutOfBandData outOfBandData, ISportDataCallback iSportDataCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "registerSportData");
        if (a(outOfBandData, iSportDataCallback, "registerSportData")) {
            return;
        }
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.registerRealTimeSportCallback(outOfBandData, iSportDataCallback);
        } else {
            this.d.registerRealTimeSportCallback(iSportDataCallback);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void unregisterSportData(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "unregisterSportData");
        if (a(outOfBandData, iCommonCallback, "unregisterSportData")) {
            return;
        }
        if (outOfBandData instanceof H5ProAppInfo) {
            this.b.unregisterRealTimeSportCallback(outOfBandData, iCommonCallback);
        } else {
            this.d.unregisterRealTimeSportCallback(iCommonCallback);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKitCommonOhos
    public void isAvailable(OutOfBandData outOfBandData, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "enter ", "isAvailable");
        if (!a(outOfBandData, iCommonCallback, "isAvailable") && ioy.b("isAvailable", iCommonCallback)) {
            iCommonCallback.onResult(0, ipd.b(0));
            b(outOfBandData, "isAvailable", 0);
        }
    }

    private boolean a(OutOfBandData outOfBandData, Object obj, String str) {
        if (obj != null) {
            return false;
        }
        ReleaseLogUtil.d("HiH_HiHealthKitCommonOhosBinder", str, " listener is null");
        b(outOfBandData, str, 2);
        return true;
    }

    private IDataReadResultListener e(final IDataReadResultListenerOhos iDataReadResultListenerOhos) {
        return new IDataReadResultListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitCommonOhosBinder.4
            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i, int i2) throws RemoteException {
                if (list == null) {
                    iDataReadResultListenerOhos.onResult(null, i, i2);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                HiHealthKitCommonOhosBinder.this.e(list, arrayList);
                iDataReadResultListenerOhos.onResult(arrayList, i, i2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List list, List<HiHealthKitDataOhos> list2) {
        for (Object obj : list) {
            if (obj instanceof HiHealthKitData) {
                HiHealthKitDataOhos hiHealthKitDataOhos = new HiHealthKitDataOhos();
                HiHealthKitData hiHealthKitData = (HiHealthKitData) obj;
                hiHealthKitDataOhos.setContentValues(hiHealthKitData.getContentValue());
                hiHealthKitDataOhos.setMap(hiHealthKitData.getMap());
                hiHealthKitDataOhos.setType(hiHealthKitData.getType());
                list2.add(hiHealthKitDataOhos);
            }
        }
    }

    private void d(HiHealthKitData hiHealthKitData, HiHealthKitDataOhos hiHealthKitDataOhos) {
        hiHealthKitData.setType(hiHealthKitDataOhos.getType());
        hiHealthKitData.setMap(hiHealthKitDataOhos.getMap());
        hiHealthKitData.setContentValues(hiHealthKitDataOhos.getContentValues());
    }

    private void b(List<HiHealthKitData> list, List<HiHealthKitDataOhos> list2) {
        for (HiHealthKitDataOhos hiHealthKitDataOhos : list2) {
            HiHealthKitData hiHealthKitData = new HiHealthKitData();
            d(hiHealthKitData, hiHealthKitDataOhos);
            list.add(hiHealthKitData);
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

    public void onDestroy() {
        ReleaseLogUtil.e("HiH_HiHealthKitCommonOhosBinder", "onDestroy");
    }
}
