package com.huawei.hihealthservice.hihealthkit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.ITrackDataForDeveloper;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.DataReportModel;
import com.huawei.hihealth.HealthKitDictQuery;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiHealthDataQuery;
import com.huawei.hihealth.HiHealthDataQueryOption;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.HiUserPreferenceData;
import com.huawei.hihealth.IBaseCallback;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IHiHealthKit;
import com.huawei.hihealth.IReadCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealth.ISubScribeCallback;
import com.huawei.hihealth.IWriteCallback;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.TrendQuery;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Goal;
import com.huawei.hihealth.model.GoalInfo;
import com.huawei.hihealth.model.Notification;
import com.huawei.hihealth.model.SubscribeModel;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hihealth.option.HiHealthCapabilityQuery;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hihealthservice.InsertExecutor;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder;
import com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder;
import com.huawei.hihealthservice.hihealthkit.util.AppStatusHelper;
import com.huawei.hihealthservice.hihealthkit.util.HiHealthKitDataChecker;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import defpackage.Cint;
import defpackage.ash;
import defpackage.ify;
import defpackage.iip;
import defpackage.ijm;
import defpackage.ikv;
import defpackage.ikx;
import defpackage.ilb;
import defpackage.ima;
import defpackage.ini;
import defpackage.ino;
import defpackage.inp;
import defpackage.inq;
import defpackage.ins;
import defpackage.inv;
import defpackage.iov;
import defpackage.iox;
import defpackage.ioy;
import defpackage.ipd;
import defpackage.ipl;
import defpackage.iqr;
import defpackage.iqs;
import defpackage.iqt;
import defpackage.iqw;
import defpackage.iqx;
import defpackage.iqy;
import defpackage.iqz;
import defpackage.ira;
import defpackage.irc;
import defpackage.ird;
import defpackage.irg;
import defpackage.irh;
import defpackage.irn;
import defpackage.iro;
import defpackage.irp;
import defpackage.ivg;
import defpackage.ivv;
import defpackage.ivw;
import defpackage.ivz;
import defpackage.iwd;
import defpackage.iwi;
import defpackage.iwu;
import defpackage.koq;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HiHealthKitBinder extends IHiHealthKit.Stub {
    private Handler f;
    private IBaseCallback g;
    private Context i;
    private AppStatusHelper j;
    private ima k;
    private InsertExecutor n;
    private HealthOpenSDK o;
    private Set<String> p;
    private static final String d = BaseApplication.getAppPackage();
    private static final List b = new ArrayList(0);

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f4151a = new byte[0];
    private static final Object e = new Object();
    private boolean h = false;
    private volatile IBinder l = null;
    private Bundle s = new Bundle();
    private Map<String, Long> m = new ConcurrentHashMap();
    private int t = -1;
    private ConcurrentHashMap<Integer, ITrackDataForDeveloper> q = new ConcurrentHashMap<>();
    private IBinder.DeathRecipient c = new IBinder.DeathRecipient() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "binderDied");
            ino.b(HiHealthKitBinder.this.i).a(new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.2.5
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str) {
                    ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "auto stop sport result code = ", Integer.valueOf(i));
                    if (i == 0) {
                        HiHealthKitBinder.this.l = null;
                    }
                }
            });
        }
    };

    /* loaded from: classes7.dex */
    public interface Action {
        void operate() throws RemoteException;
    }

    public HiHealthKitBinder(Context context, InsertExecutor insertExecutor) {
        if (context == null || insertExecutor == null) {
            throw new iwu("HiHealthKitBinder() param is null");
        }
        this.i = context;
        this.n = insertExecutor;
        this.k = ima.a();
        this.p = iov.d();
        this.f = new Handler(this.i.getMainLooper()) { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.12
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 101) {
                    try {
                        HiHealthKitBinder.this.bBF_(message);
                        return;
                    } catch (RemoteException e2) {
                        ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "handleMessage RemoteException = ", e2.getMessage());
                        return;
                    }
                }
                if (i == 102) {
                    HiHealthKitBinder.this.e();
                    ash.a("isAppInstalled", "true");
                } else {
                    ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "invalid msg: ", Integer.valueOf(message.what));
                }
            }
        };
        ify.e().a(context, insertExecutor);
        this.n.execute(new Runnable() { // from class: imc
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitBinder.this.c();
            }
        });
        if ("true".equals(ash.b("isAppInstalled"))) {
            LogUtil.a("HiHlthKitBnd", "direct init");
            e();
        } else {
            this.f.sendEmptyMessageDelayed(102, PreConnectManager.CONNECT_INTERNAL);
        }
        irn.b(this.i).c();
    }

    public /* synthetic */ void c() {
        try {
            this.k.e();
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "HiHealthKitBinder() getCurrentAppId e = ", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("HiHlthKitBnd", "start init sdk, init result before ", Boolean.valueOf(this.h));
        if (this.h) {
            return;
        }
        try {
            HealthOpenSDK healthOpenSDK = new HealthOpenSDK();
            this.o = healthOpenSDK;
            healthOpenSDK.initSDK(this.i, new b(), "Health Kit");
        } catch (Exception unused) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "init openSDK error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bBF_(Message message) throws RemoteException {
        boolean a2 = AuthorizationUtils.a(BaseApplication.getContext());
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "handleAuthMessage requestAuthorization isAuthPermitted = ", Boolean.valueOf(a2));
        HashMap hashMap = new HashMap(16);
        IBaseCallback iBaseCallback = this.g;
        if (iBaseCallback == null) {
            return;
        }
        if (!a2) {
            iBaseCallback.onResult(1003, hashMap);
            return;
        }
        hashMap.put("flag", String.valueOf(message.arg1));
        Intent intent = new Intent();
        String str = d;
        intent.setClassName(str, "com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAuthActivity");
        intent.setPackage(str);
        intent.setFlags(268435456);
        intent.addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        intent.addFlags(524288);
        intent.addFlags(8388608);
        irc ircVar = new irc();
        String str2 = (String) message.obj;
        try {
            try {
                Bundle data = message.getData();
                int[] intArray = data.getIntArray("writeTypes");
                int[] intArray2 = data.getIntArray("readTypes");
                intent.putExtra("third_party_package_name", str2);
                intent.putExtra("writeTypes", intArray);
                intent.putExtra("readTypes", intArray2);
                intent.putExtra("uidTypes", message.arg1);
                intent.putExtra(MapKeyNames.APP_INFO, c(str2));
                this.i.startActivity(intent);
                this.g.onResult(0, hashMap);
                e("requestAuthorization", 0, ircVar, str2);
            } catch (Exception unused) {
                ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "handleAuthMessage Exception");
                this.g.onResult(4, hashMap);
                e("requestAuthorization", 4, ircVar, str2);
            }
        } finally {
            this.g = null;
        }
    }

    private HiAppInfo c(String str) {
        HiAppInfo b2 = iip.b().b(str);
        if (b2 != null) {
            return b2;
        }
        HiAppInfo c2 = ivw.c(this.i, str);
        if (c2 == null) {
            return null;
        }
        iip.b().e(c2, 0);
        return iip.b().b(str);
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void setBinder(IBinder iBinder) {
        synchronized (e) {
            if (iBinder != null) {
                if (this.l == null) {
                    this.t = Binder.getCallingUid();
                    this.l = iBinder;
                    try {
                        this.l.linkToDeath(this.c, 0);
                    } catch (RemoteException e2) {
                        ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "setBinder RemoteException: ", e2.getMessage());
                    }
                }
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getGender(int i, ICommonListener iCommonListener) throws RemoteException {
        c(iCommonListener, "getGender", 101001);
    }

    private void c(final ICommonListener iCommonListener, String str, int i) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", str);
        irc ircVar = new irc();
        if (b(iCommonListener, str, ircVar) || !ioy.b(str, iCommonListener) || c(str, i, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilq
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                ICommonListener.this.onSuccess(i2, null);
            }
        })) {
            return;
        }
        LogUtil.a("HiHlthKitBnd", str, " appId = ", Integer.valueOf(this.k.i()));
        Context context = this.i;
        new HiHealthKitQueryHelper(context, iwi.a(context)).c(ircVar, iCommonListener, str);
    }

    private boolean c(String str, int i, String str2, irc ircVar, HiHealthKitExtendBinder.Action action) throws RemoteException {
        int d2 = ipl.b(this.i).d(i, iwi.a(this.i), !this.p.contains(str));
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "cpHmsAuthResult:", Integer.valueOf(d2));
        if (d2 == 8) {
            if (this.j == null) {
                this.j = new AppStatusHelper(this.i);
            }
            d2 = this.j.c(HiScopeUtil.c(BaseApplication.getContext(), iwi.a(this.i)), this.p.contains(str) ? 2 : 1, i);
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "beta scope auth result: ", Integer.valueOf(d2));
        }
        if (d2 == 0 && iqt.d(str, i)) {
            return false;
        }
        ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", str, " permission deny, appId = ", Integer.valueOf(this.k.e()), ", type =", Integer.valueOf(i));
        action.operate(d2 != 0 ? d2 : 1001);
        c(str, d2, str2, ircVar);
        return true;
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getBirthday(int i, ICommonListener iCommonListener) throws RemoteException {
        c(iCommonListener, "getBirthday", 101001);
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getHeight(int i, ICommonListener iCommonListener) throws RemoteException {
        c(iCommonListener, "getHeight", 101002);
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getWeight(int i, ICommonListener iCommonListener) throws RemoteException {
        c(iCommonListener, "getWeight", 101002);
    }

    private void c(HiHealthDataQuery hiHealthDataQuery, final IDataReadResultListener iDataReadResultListener) {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter querySleepWakeTime, type = ", 22104);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(hiHealthDataQuery.getStartTime());
        hiDataReadOption.setEndTime(hiHealthDataQuery.getEndTime());
        hiDataReadOption.setType(new int[]{22104});
        HiHealthManager.d(this.i).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.13
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "querySleepWakeTime resultCode: ", Integer.valueOf(i));
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray != null) {
                    try {
                        if (sparseArray.size() > 0) {
                            iDataReadResultListener.onResult(HiHealthKitBinder.this.d((List<HiHealthData>) sparseArray.get(22104)), 0, 2);
                            return;
                        }
                    } catch (RemoteException e2) {
                        ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "querySleepWakeTime RemoteException: ", e2.getMessage());
                        return;
                    }
                }
                iDataReadResultListener.onResult(null, iox.b(i), i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthKitData> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        int i = 0;
        long startTime = list.get(0).getStartTime();
        long endTime = list.get(0).getEndTime();
        while (i < list.size()) {
            HiHealthData hiHealthData = list.get(i);
            long startTime2 = hiHealthData.getStartTime();
            long endTime2 = hiHealthData.getEndTime();
            if (startTime - endTime2 > 60000) {
                HiHealthKitData hiHealthKitData = new HiHealthKitData();
                hiHealthKitData.setStartTime(startTime);
                hiHealthKitData.setEndTime(endTime);
                arrayList.add(hiHealthKitData);
                endTime = endTime2;
            }
            if (i == list.size() - 1) {
                HiHealthKitData hiHealthKitData2 = new HiHealthKitData();
                hiHealthKitData2.setStartTime(startTime2);
                hiHealthKitData2.setEndTime(endTime);
                arrayList.add(hiHealthKitData2);
            }
            i++;
            startTime = startTime2;
        }
        return arrayList;
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void querySleepWakeTime(int i, HiHealthDataQuery hiHealthDataQuery, int i2, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "querySleepWakeTime");
        irc ircVar = new irc();
        if (b(iDataReadResultListener, "querySleepWakeTime", ircVar) || e(hiHealthDataQuery, iDataReadResultListener, "querySleepWakeTime", ircVar) || !ioy.b("querySleepWakeTime", iDataReadResultListener) || c("querySleepWakeTime", hiHealthDataQuery.getSampleType(), (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilr
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i3) {
                HiHealthKitBinder.this.c(iDataReadResultListener, i3);
            }
        })) {
            return;
        }
        c(hiHealthDataQuery, d(iDataReadResultListener, "querySleepWakeTime", ircVar));
    }

    public /* synthetic */ void c(IDataReadResultListener iDataReadResultListener, int i) throws RemoteException {
        if (!iqw.a(this.i)) {
            i = 0;
        }
        iDataReadResultListener.onResult(null, i, 2);
    }

    private boolean e(HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener, String str, irc ircVar) throws RemoteException {
        if (hiHealthDataQuery != null) {
            return false;
        }
        ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "query is null");
        iDataReadResultListener.onResult(null, 2, 2);
        d(str, 2, ircVar);
        return true;
    }

    private IDataReadResultListener d(final IDataReadResultListener iDataReadResultListener, final String str, final irc ircVar) {
        final String a2 = iwi.a(this.i);
        return new IDataReadResultListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.15
            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i, int i2) throws RemoteException {
                if (i2 == 2) {
                    HiHealthKitBinder.this.e(str, i, ircVar, a2);
                }
                iDataReadResultListener.onResult(list, i, i2);
            }
        };
    }

    private boolean c(HiHealthDataQueryOption hiHealthDataQueryOption) {
        boolean z = true;
        if (hiHealthDataQueryOption == null) {
            return true;
        }
        if (hiHealthDataQueryOption.getLimit() < 0 || hiHealthDataQueryOption.getOffset() < 0 || (hiHealthDataQueryOption.getOrder() != 0 && hiHealthDataQueryOption.getOrder() != 1)) {
            z = false;
        }
        if (!z) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "checkHiHealthDataQuery invalid");
        }
        return z;
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void queryData(HealthKitDictQuery healthKitDictQuery, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        irc ircVar = new irc();
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "queryData");
        if (b(iDataReadResultListener, "queryData", ircVar) || e(healthKitDictQuery, iDataReadResultListener, "queryData", ircVar)) {
            return;
        }
        int sampleType = healthKitDictQuery.getSampleType();
        if (!iqz.c(sampleType)) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "queryData type is not support.");
            iDataReadResultListener.onResult(null, 30, 2);
            d("queryData", 30, ircVar);
            return;
        }
        if (ioy.b("queryData", iDataReadResultListener)) {
            if (HiHealthKitDataChecker.a(healthKitDictQuery.getStartTime(), healthKitDictQuery.getEndTime()) || !HiHealthKitDataChecker.e(healthKitDictQuery)) {
                ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", ipd.b(2));
                iDataReadResultListener.onResult(null, 2, 2);
                d("queryData", 2, ircVar);
                return;
            }
            if (c("queryData", iqt.e(sampleType), String.valueOf(sampleType), ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilh
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitBinder.this.d(iDataReadResultListener, i);
                }
            })) {
                return;
            }
            Context context = this.i;
            inp inpVar = new inp(context, iwi.a(context), ircVar);
            boolean booleanValue = healthKitDictQuery.getBoolean("dataSourceQuery").booleanValue();
            boolean booleanValue2 = healthKitDictQuery.getBoolean("aggregateQuery").booleanValue();
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "queryData isDatasourceQuery: ", Boolean.valueOf(booleanValue), ", isAggregateQuery: ", Boolean.valueOf(booleanValue2));
            if (booleanValue) {
                inpVar.e(healthKitDictQuery, iDataReadResultListener, d("queryData", sampleType));
            } else if (booleanValue2) {
                inpVar.d(healthKitDictQuery, iDataReadResultListener, d("queryData", sampleType));
            } else {
                inpVar.b(healthKitDictQuery, iDataReadResultListener, d("queryData", sampleType));
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void execQuery(int i, HiHealthDataQuery hiHealthDataQuery, int i2, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        irc ircVar = new irc();
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "execQuery");
        if (b(iDataReadResultListener, "execQuery", ircVar) || e(hiHealthDataQuery, iDataReadResultListener, "execQuery", ircVar)) {
            return;
        }
        int sampleType = hiHealthDataQuery.getSampleType();
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "execQuery dataType: ", Integer.valueOf(sampleType));
        if (iqt.b() && sampleType == 40002) {
            iro.a(hiHealthDataQuery, iDataReadResultListener, this.o);
            return;
        }
        if (ioy.b("execQuery", iDataReadResultListener)) {
            if (!c(hiHealthDataQuery.getHiHealthDataQueryOption()) || d(hiHealthDataQuery, sampleType) || HiHealthKitDataChecker.a(hiHealthDataQuery.getStartTime(), hiHealthDataQuery.getEndTime())) {
                ircVar.c(this.i, new iqy("execQuery", 0, iwi.a(this.i), null, String.valueOf(sampleType)).b(2));
                iDataReadResultListener.onResult(null, 2, 2);
            } else {
                if (c("execQuery", iqt.e(sampleType), String.valueOf(sampleType), ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilj
                    @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                    public final void operate(int i3) {
                        HiHealthKitBinder.this.b(iDataReadResultListener, i3);
                    }
                })) {
                    return;
                }
                d(hiHealthDataQuery, iDataReadResultListener, d("execQuery", sampleType));
            }
        }
    }

    private boolean d(HiHealthDataQuery hiHealthDataQuery, int i) {
        if (iqz.c(iwi.a(this.i)) > 2) {
            return false;
        }
        if (i != 10001 && i != 10006 && i != 10002) {
            return false;
        }
        boolean z = hiHealthDataQuery.getEndTime() - hiHealthDataQuery.getStartTime() > 2592000000L;
        if (z) {
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "Kit SDK Version <= 2, dataType is one of [10001,10006,10002], The interval cannot exceed 30 days.");
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void d(IDataReadResultListener iDataReadResultListener, int i) throws RemoteException {
        if (iqw.a(this.i)) {
            iDataReadResultListener.onResult(null, i, 2);
        } else if (iqz.d(iwi.a(this.i), 2)) {
            iDataReadResultListener.onResult(null, i, 2);
        } else {
            iDataReadResultListener.onResult(null, 0, 2);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getCount(int i, HiHealthDataQuery hiHealthDataQuery, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getCount");
        irc ircVar = new irc();
        if (b(iDataReadResultListener, "getCount", ircVar) || e(hiHealthDataQuery, iDataReadResultListener, "getCount", ircVar) || !ioy.b("getCount", iDataReadResultListener)) {
            return;
        }
        int sampleType = hiHealthDataQuery.getSampleType();
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getCount dataType = ", Integer.valueOf(sampleType));
        if (c("getCount", iqt.e(sampleType), String.valueOf(sampleType), ircVar, new HiHealthKitExtendBinder.Action() { // from class: ils
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                HiHealthKitBinder.this.e(iDataReadResultListener, i2);
            }
        })) {
            return;
        }
        d(hiHealthDataQuery, iDataReadResultListener, d("getCount", sampleType));
    }

    public /* synthetic */ void e(IDataReadResultListener iDataReadResultListener, int i) throws RemoteException {
        if (iqz.d(iwi.a(this.i), 2)) {
            iDataReadResultListener.onResult(null, i, 2);
        } else {
            iDataReadResultListener.onResult(null, 1, 2);
        }
    }

    private void d(HiHealthDataQuery hiHealthDataQuery, IDataReadResultListener iDataReadResultListener, Cint cint) throws RemoteException {
        new HiHealthKitQueryHelper(this.i, cint.c()).c(hiHealthDataQuery, iDataReadResultListener, cint);
    }

    private Cint d(String str, int i) {
        int d2 = ipl.b(this.i).d(101201, iwi.a(this.i), true);
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getQueryParameterChannel auth result: ", Integer.valueOf(d2));
        if (d2 == 8) {
            if (this.j == null) {
                this.j = new AppStatusHelper(this.i);
            }
            d2 = this.j.c(HiScopeUtil.c(BaseApplication.getContext(), iwi.a(this.i)), this.p.contains(str) ? 2 : 1, 101201);
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getQueryParameterChannel beta scope auth result: ", Integer.valueOf(d2));
        }
        return new Cint(d2 == 0, str, iwi.a(this.i), Integer.toString(i));
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void deleteSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
        e(Arrays.asList(hiHealthKitData), iDataOperateListener, "deleteSample");
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void deleteSamples(int i, List list, IDataOperateListener iDataOperateListener) throws RemoteException {
        e(list, iDataOperateListener, "deleteSamples");
    }

    private void e(List list, final IDataOperateListener iDataOperateListener, String str) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", str);
        irc ircVar = new irc();
        if (b(iDataOperateListener, str, ircVar)) {
            return;
        }
        Iterator<Integer> it = ima.e((List<HiHealthKitData>) list).iterator();
        while (it.hasNext()) {
            if (!iqz.e(str, it.next().intValue(), b(str, 30), iDataOperateListener)) {
                return;
            }
        }
        if (ioy.b(str, iDataOperateListener)) {
            LogUtil.a("HiHlthKitBnd", str, " appId:", Integer.valueOf(iip.b().a(iwi.a(this.i))), " userId:", Integer.valueOf(this.k.j()));
            if (!HiHealthKitDataChecker.a(list, HiHealthKitDataChecker.MethodType.DELETE_SAMPLES, true)) {
                iDataOperateListener.onResult(2, ipd.c(2));
                d(str, 2, ircVar);
                LogUtil.h("R_HiH_HiHlthKitBnd", str, ipd.b(2));
                return;
            }
            int a2 = ima.a(list);
            if (c(str, a2, String.valueOf(a2), ircVar, new HiHealthKitExtendBinder.Action() { // from class: ily
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    IDataOperateListener.this.onResult(i, HiHealthKitBinder.b);
                }
            })) {
                return;
            }
            HiDataDeleteOption e2 = e((List<HiHealthKitData>) list);
            ify e3 = ify.e();
            int[] iArr = new int[1];
            if (a2 == 30029) {
                a2 = 30001;
            }
            iArr[0] = a2;
            e3.b(e2, b(str, iArr, iDataOperateListener, new ArrayList(), ircVar), true, false);
        }
    }

    private <T> boolean b(T t, String str, irc ircVar) {
        if (t != null) {
            return false;
        }
        d(str, 2, ircVar);
        ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", str, " listener is null");
        return true;
    }

    private irc b(String str, int i) {
        return new irc(this.i, new iqy(str, i, iwi.a(this.i)));
    }

    private HiDataDeleteOption e(List<HiHealthKitData> list) {
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        int type = list.get(0).getType();
        LogUtil.a("HiHlthKitBnd", "deleteSamplesImpl type = ", Integer.valueOf(type));
        hiDataDeleteOption.setTypes(ira.c(type));
        for (HiHealthKitData hiHealthKitData : list) {
            hiDataDeleteOption.addTimeInterval(new HiTimeInterval(hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime()));
        }
        return hiDataDeleteOption;
    }

    private IDataOperateListener b(final String str, final int[] iArr, final IDataOperateListener iDataOperateListener, final List<String> list, final irc ircVar) {
        return new IDataOperateListener() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.11
            @Override // android.os.IInterface
            public IBinder asBinder() {
                return null;
            }

            @Override // com.huawei.hihealth.IDataOperateListener
            public void onResult(int i, List list2) {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "HiHealthApiManager deleteHiHealthData errorCode = ", Integer.valueOf(i));
                int b2 = iox.b(i);
                iqy iqyVar = new iqy(str, b2, iwi.a(HiHealthKitBinder.this.i), null, String.valueOf(iArr[0]));
                try {
                    ikv f = HiHealthKitBinder.this.k.f();
                    if (!CommonUtil.bu()) {
                        ivg.c().e(iwd.b(iArr), ArkUIXConstants.DELETE, f);
                    }
                    list.add(ipd.b(b2));
                    iDataOperateListener.onResult(b2, list);
                    ircVar.c(HiHealthKitBinder.this.i, iqyVar);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "deleteHiHealthData onResult RemoteException = ", e2.getMessage());
                    try {
                        list.add(ipd.b(4));
                        iDataOperateListener.onResult(4, list);
                    } catch (RemoteException e3) {
                        ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "deleteHiHealthData onResult RemoteException2 = ", e3.getMessage());
                    }
                    ircVar.c(HiHealthKitBinder.this.i, iqyVar.b(4));
                }
            }
        };
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void saveSample(int i, HiHealthKitData hiHealthKitData, IDataOperateListener iDataOperateListener) throws RemoteException {
        a(Arrays.asList(hiHealthKitData), iDataOperateListener, "saveSample");
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void saveSamples(int i, List<HiHealthKitData> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        a(list, iDataOperateListener, "saveSamples");
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public String getCategory(int i) {
        return HiHealthDataType.e(i).name();
    }

    private void a(List<HiHealthKitData> list, final IDataOperateListener iDataOperateListener, String str) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", str);
        final irc ircVar = new irc();
        if (b(iDataOperateListener, str, ircVar)) {
            return;
        }
        List<Integer> e2 = ima.e(list);
        Iterator<Integer> it = e2.iterator();
        while (it.hasNext()) {
            if (!iqz.e(str, it.next().intValue(), b(str, 30), iDataOperateListener)) {
                return;
            }
        }
        if (ioy.b(str, iDataOperateListener)) {
            if (!HiHealthKitDataChecker.a(list, HiHealthKitDataChecker.MethodType.SAVE_SAMPLES, false)) {
                d(str, 2, ircVar);
                iDataOperateListener.onResult(2, ipd.c(2));
                LogUtil.h("R_HiH_HiHlthKitBnd", str, ipd.b(2));
                return;
            }
            String a2 = iwi.a(this.i);
            final ArrayList arrayList = new ArrayList(e2.size());
            Iterator<Integer> it2 = e2.iterator();
            while (it2.hasNext()) {
                int intValue = it2.next().intValue();
                if (c(str, intValue, String.valueOf(intValue), ircVar, new HiHealthKitExtendBinder.Action() { // from class: ikz
                    @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                    public final void operate(int i) {
                        HiHealthKitBinder.this.c(iDataOperateListener, i);
                    }
                })) {
                    return;
                } else {
                    arrayList.add(new iqy(str, 0, a2, null, String.valueOf(intValue)));
                }
            }
            ini c2 = ini.c();
            if (c2 == null) {
                ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "HiHealthKitInsertHelper is null");
                iDataOperateListener.onResult(4, null);
                d(ircVar, arrayList, 4);
            } else {
                c2.e(list, new IDataOperateListener.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.14
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list2) throws RemoteException {
                        int b2 = iox.b(i);
                        iDataOperateListener.onResult(b2, list2);
                        HiHealthKitBinder.this.d(ircVar, (List<iqy>) arrayList, b2);
                    }
                }, iwi.c(a2, false));
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "addPackageNameToSharedPreferences result: ", Boolean.valueOf(iqr.c(list, a2)));
            }
        }
    }

    public /* synthetic */ void c(IDataOperateListener iDataOperateListener, int i) throws RemoteException {
        iDataOperateListener.onResult(d(i), null);
    }

    private int d(int i) {
        if (iqz.d(iwi.a(this.i), 2)) {
            return i;
        }
        return 0;
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void requestAuthorization(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "requestAuthorization");
        irc ircVar = new irc();
        if (b(iBaseCallback, "requestAuthorization", ircVar)) {
            return;
        }
        if (!ima.d(iArr2, iArr)) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "requestAuthorization", " isKitValidTypes false");
            iBaseCallback.onResult(7, null);
            d("requestAuthorization", 7, ircVar);
            return;
        }
        if (c(iArr, iArr2, iBaseCallback, "requestAuthorization", ircVar) || c(iBaseCallback, "requestAuthorization", ircVar)) {
            return;
        }
        try {
            int callingUid = Binder.getCallingUid();
            LogUtil.a("HiHlthKitBnd", "startActivity before: ", Long.valueOf(System.currentTimeMillis()));
            d(i, callingUid);
            this.g = iBaseCallback;
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.arg1 = callingUid;
            obtain.obj = iwi.a(this.i);
            ird d2 = ird.d(this.i);
            Bundle bundle = new Bundle();
            bundle.putIntArray("writeTypes", d2.b(iArr, false));
            bundle.putIntArray("readTypes", d2.b(iArr2, true));
            obtain.setData(bundle);
            this.f.sendMessage(obtain);
        } catch (Exception e2) {
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "requestAuthorization Exception: ", e2.getMessage());
            iBaseCallback.onResult(4, null);
            d("requestAuthorization", 4, ircVar);
        }
    }

    private boolean c(int[] iArr, int[] iArr2, IBaseCallback iBaseCallback, String str, irc ircVar) throws RemoteException {
        ird d2 = ird.d(this.i);
        int[] b2 = d2.b(iArr, false);
        int[] b3 = d2.b(iArr2, true);
        if (b2.length != 0 || b3.length != 0) {
            return false;
        }
        ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", str, " scope deny ");
        iBaseCallback.onResult(1002, null);
        d(str, 1002, ircVar);
        return true;
    }

    private void d(int i, int i2) {
        String str;
        LogUtil.a("HiHlthKitBnd", "checkFlagPermission ", Integer.valueOf(i), Constants.LINK, Integer.valueOf(i2), Constants.LINK, iwi.a(this.i));
        iip b2 = iip.b();
        int a2 = b2.a(iwi.a(this.i));
        if (i2 != i) {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "deleteHealthUserPermissionData appId = ", Integer.valueOf(a2), " result = ", Integer.valueOf(ijm.e(this.i).a(a2)));
        }
        HiAppInfo c2 = b2.c(a2);
        if (c2 == null) {
            return;
        }
        int b3 = ivv.b(c2.getSignature());
        PackageManager packageManager = this.i.getPackageManager();
        try {
            str = packageManager.getApplicationLabel(packageManager.getApplicationInfo(iwi.a(this.i), 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "checkFlagPermission NameNotFoundException");
            str = "";
        }
        Context context = this.i;
        String e2 = HsfSignValidator.e(context, iwi.a(context));
        if (b3 == i2 && str.equals(c2.getAppName())) {
            return;
        }
        c2.setSignature(ivw.a(e2, i2));
        Context context2 = this.i;
        HiAppInfo b4 = ivw.b(context2, iwi.a(context2));
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getAppInfo packageName = ", iwi.a(this.i));
        if (b4 != null) {
            c2.setAppName(b4.getAppName());
            c2.setVersion(b4.getVersion());
            c2.setPackageName(b4.getPackageName());
            c2.setCloudCode(b4.getCloudCode());
        }
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "updateAppInfo result = ", Integer.valueOf(b2.c(c2)));
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getDataAuthStatus(int i, int i2, IDataOperateListener iDataOperateListener) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getDataAuthStatus");
        irc ircVar = new irc();
        if (!b(iDataOperateListener, "getDataAuthStatus", ircVar) && ioy.b("getDataAuthStatus", iDataOperateListener)) {
            d(i, Binder.getCallingUid());
            int a2 = iip.b().a(iwi.a(this.i));
            LogUtil.a("HiHlthKitBnd", "getDataAuthStatus", " appId:", Integer.valueOf(a2));
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(ijm.e(this.i).e(a2, i2).getAllowWrite()));
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getDataAuthStatus: ", HiJsonUtil.e(arrayList));
            iDataOperateListener.onResult(0, arrayList);
            d("getDataAuthStatus", 0, ircVar);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getDataAuthStatusEx(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getDataAuthStatusEx");
        irc ircVar = new irc();
        if (!b(iBaseCallback, "getDataAuthStatusEx", ircVar) && ioy.b("getDataAuthStatusEx", iBaseCallback)) {
            d(i, Binder.getCallingUid());
            Context context = this.i;
            new HiHealthKitQueryHelper(context, iwi.a(context)).c(iArr, iArr2, iBaseCallback);
            d("getDataAuthStatusEx", 0, ircVar);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void startReadingHeartRate(int i, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        final String str = "startReadingHeartRate";
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "startReadingHeartRate");
        irc ircVar = new irc();
        if (b(iRealTimeDataCallback, "startReadingHeartRate", ircVar) || !ioy.b("startReadingHeartRate", iRealTimeDataCallback) || c("startReadingHeartRate", 50001, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilv
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                HiHealthKitBinder.this.b(iRealTimeDataCallback, i2);
            }
        })) {
            return;
        }
        final String a2 = iwi.a(this.i);
        LogUtil.c("HiHlthKitBnd", "startReadingHeartRate", " packageName:", a2);
        ins.a(this.i).j(a2, e(iRealTimeDataCallback, "startReadingHeartRate", new Action() { // from class: ilx
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.Action
            public final void operate() {
                HiHealthKitBinder.this.a(a2, str);
            }
        }, ircVar));
        irh.e(a2, 50001, "ReadingHeartRate", iRealTimeDataCallback, (ITrackDataForDeveloper) null);
    }

    public /* synthetic */ void b(IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        iRealTimeDataCallback.onResult(d(i));
    }

    public /* synthetic */ void a(String str, String str2) throws RemoteException {
        ins.a(this.i).m(str, new c(str2));
    }

    private IRealTimeDataCallback e(final IRealTimeDataCallback iRealTimeDataCallback, final String str, final Action action, final irc ircVar) {
        final String a2 = iwi.a(this.i);
        return new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.19
            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) throws RemoteException {
                IBinder asBinder = iRealTimeDataCallback.asBinder();
                if (asBinder != null && !asBinder.pingBinder() && action != null) {
                    ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getProxyIRealTimeDataListener onResult pingBinder ", Boolean.valueOf(asBinder.pingBinder()));
                    action.operate();
                    return;
                }
                int f = iox.f(i);
                if (iqz.d(a2, 2)) {
                    iRealTimeDataCallback.onResult(f);
                } else {
                    iRealTimeDataCallback.onResult(i);
                }
                HiHealthKitBinder.this.e(str, f, ircVar, a2);
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str2) throws RemoteException {
                IBinder asBinder = iRealTimeDataCallback.asBinder();
                if (asBinder != null && !asBinder.pingBinder()) {
                    ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getProxyIRealTimeDataListener onChange pingBinder ", Boolean.valueOf(asBinder.pingBinder()));
                    action.operate();
                } else if (iqz.d(a2, 2) && !"startReadingAtrial".equals(str)) {
                    iRealTimeDataCallback.onChange(iox.f(i), str2);
                } else {
                    iRealTimeDataCallback.onChange(i, str2);
                }
            }
        };
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void stopReadingHeartRate(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "stopReadingHeartRate");
        irc ircVar = new irc();
        if (!b(iRealTimeDataCallback, "stopReadingHeartRate", ircVar) && ioy.b("stopReadingHeartRate", iRealTimeDataCallback)) {
            LogUtil.c("HiHlthKitBnd", "stopReadingHeartRate", " packageName:", iwi.a(this.i));
            ins.a(this.i).m(iwi.a(this.i), e(iRealTimeDataCallback, "stopReadingHeartRate", (Action) null, ircVar));
            irh.e(iwi.a(this.i), "ReadingHeartRate");
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void startReadingRRI(int i, final IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        final String str = "startReadingRRI";
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "startReadingRRI");
        irc ircVar = new irc();
        if (b(iRealTimeDataCallback, "startReadingRRI", ircVar) || !ioy.b("startReadingRRI", iRealTimeDataCallback) || c("startReadingRRI", 50001, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ili
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                HiHealthKitBinder.this.e(iRealTimeDataCallback, i2);
            }
        })) {
            return;
        }
        final String a2 = iwi.a(this.i);
        LogUtil.c("HiHlthKitBnd", "startReadingRRI", " packageName:", a2);
        ins.a(this.i).i(a2, e(iRealTimeDataCallback, "startReadingRRI", new Action() { // from class: ilg
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.Action
            public final void operate() {
                HiHealthKitBinder.this.e(a2, str);
            }
        }, ircVar));
        irh.e(a2, 50001, "ReadingRri", iRealTimeDataCallback, (ITrackDataForDeveloper) null);
    }

    public /* synthetic */ void e(IRealTimeDataCallback iRealTimeDataCallback, int i) throws RemoteException {
        iRealTimeDataCallback.onResult(d(i));
    }

    public /* synthetic */ void e(String str, String str2) throws RemoteException {
        ins.a(this.i).o(str, new c(str2));
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void stopReadingRRI(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "stopReadingRRI");
        irc ircVar = new irc();
        if (!b(iRealTimeDataCallback, "stopReadingRRI", ircVar) && ioy.b("stopReadingRRI", iRealTimeDataCallback)) {
            LogUtil.c("HiHlthKitBnd", "stopReadingRRI", " packageName:", iwi.a(this.i));
            ins.a(this.i).o(iwi.a(this.i), e(iRealTimeDataCallback, "stopReadingRRI", (Action) null, ircVar));
            irh.e(iwi.a(this.i), "ReadingRri");
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getDeviceList(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getDeviceList");
        irc ircVar = new irc();
        if (!b(iRealTimeDataCallback, "getDeviceList", ircVar) && ioy.b("getDeviceList", iRealTimeDataCallback)) {
            if (!iqw.b(this.i, 22)) {
                Objects.requireNonNull(iRealTimeDataCallback);
                if (c("getDeviceList", 101201, (String) null, ircVar, new ilb(iRealTimeDataCallback))) {
                    return;
                }
            }
            ins a2 = ins.a(this.i);
            Context context = this.i;
            a2.b(iqx.e(iRealTimeDataCallback, ircVar, context, new iqy("getDeviceList", 0, iwi.a(context))));
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void sendDeviceCommand(int i, String str, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "sendDeviceCommand");
        irc ircVar = new irc();
        if (!b(iRealTimeDataCallback, "sendDeviceCommand", ircVar) && ioy.b("sendDeviceCommand", iRealTimeDataCallback)) {
            if (!iqw.b(this.i, 23)) {
                Objects.requireNonNull(iRealTimeDataCallback);
                if (c("sendDeviceCommand", 101204, (String) null, ircVar, new ilb(iRealTimeDataCallback))) {
                    return;
                }
            }
            ins a2 = ins.a(this.i);
            Context context = this.i;
            a2.h(str, iqx.e(iRealTimeDataCallback, ircVar, context, new iqy("sendDeviceCommand", 0, iwi.a(context))));
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getSwitch(int i, String str, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getSwitch");
        irc ircVar = new irc();
        if (!b(iCommonCallback, "getSwitch", ircVar) && ioy.b("getSwitch", iCommonCallback)) {
            if (iqw.b(this.i, 15) || !c("getSwitch", 101204, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilu
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i2) {
                    HiHealthKitBinder.this.e(iCommonCallback, i2);
                }
            })) {
                ins a2 = ins.a(this.i);
                Context context = this.i;
                a2.e(str, iqx.a(iCommonCallback, ircVar, context, new iqy("getSwitch", 0, iwi.a(context)), "com.huawei.hihealthservice.hihealthkit.constant.BusinessErrorCode.wearApiFilterCode"));
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void startReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        final String str = "startReadingAtrial";
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "startReadingAtrial");
        irc ircVar = new irc();
        if (!b(iRealTimeDataCallback, "startReadingAtrial", ircVar) && ioy.b("startReadingAtrial", iRealTimeDataCallback)) {
            if (!iqw.b(this.i, 16)) {
                Objects.requireNonNull(iRealTimeDataCallback);
                if (c("startReadingAtrial", 101202, (String) null, ircVar, new ilb(iRealTimeDataCallback))) {
                    return;
                }
            }
            final String a2 = iwi.a(this.i);
            LogUtil.c("HiHlthKitBnd", "startReadingAtrial", " packageName:", a2);
            ins.a(this.i).g(a2, e(iRealTimeDataCallback, "startReadingAtrial", new Action() { // from class: ilm
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.Action
                public final void operate() {
                    HiHealthKitBinder.this.c(a2, str);
                }
            }, ircVar));
            d("startReadingAtrial", 0, ircVar);
            irh.e(iwi.a(this.i), 101202, "ReadingAtrial", iRealTimeDataCallback, (ITrackDataForDeveloper) null);
        }
    }

    public /* synthetic */ void c(String str, String str2) throws RemoteException {
        ins.a(this.i).k(str, new c(str2));
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void stopReadingAtrial(int i, IRealTimeDataCallback iRealTimeDataCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "stopReadingAtrial");
        irc ircVar = new irc();
        if (!b(iRealTimeDataCallback, "stopReadingAtrial", ircVar) && ioy.b("stopReadingAtrial", iRealTimeDataCallback)) {
            if (!iqw.b(this.i, 17)) {
                Objects.requireNonNull(iRealTimeDataCallback);
                if (c("stopReadingAtrial", 101202, (String) null, ircVar, new ilb(iRealTimeDataCallback))) {
                    return;
                }
            }
            ins.a(this.i).k(iwi.a(this.i), e(iRealTimeDataCallback, "stopReadingAtrial", (Action) null, ircVar));
            irh.e(iwi.a(this.i), "ReadingAtrial");
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void pushMsgToWearable(String str, String str2, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "pushMsgToWearable");
        irc ircVar = new irc();
        if (!b(iCommonCallback, "pushMsgToWearable", ircVar) && ioy.b("pushMsgToWearable", iCommonCallback)) {
            if (iqw.b(this.i, 9) || !c("pushMsgToWearable", 101203, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ild
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitBinder.this.b(iCommonCallback, i);
                }
            })) {
                ins a2 = ins.a(this.i);
                com.huawei.health.HiAppInfo b2 = ivw.b(this.i);
                Context context = this.i;
                a2.d(b2, str, str2, iqx.a(iCommonCallback, ircVar, context, new iqy("pushMsgToWearable", 0, iwi.a(context)), "com.huawei.hihealthservice.hihealthkit.constant.BusinessErrorCode.pushMessageFilterCode"));
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void writeToWearable(String str, String str2, byte[] bArr, String str3, final IWriteCallback iWriteCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "writeToWearable");
        irc ircVar = new irc();
        if (!b(iWriteCallback, "writeToWearable", ircVar) && ioy.b("writeToWearable", iWriteCallback)) {
            if (iqw.b(this.i, 8) || !c("writeToWearable", 101202, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilw
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    IWriteCallback.this.onResult(i, null);
                }
            })) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("isWriteFile", bArr != null);
                    jSONObject.put("sizeAndFinish", str3);
                    ins.a(this.i).e(str, str2, bArr, str3, iqx.a(iWriteCallback, ircVar, this.i, new iqy("writeToWearable", 0, iwi.a(this.i)), jSONObject));
                } catch (JSONException e2) {
                    ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "writeToWearable JSONException: ", e2.getMessage());
                }
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void readFromWearable(String str, String str2, final IReadCallback iReadCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "readFromWearable");
        irc ircVar = new irc();
        if (!b(iReadCallback, "readFromWearable", ircVar) && ioy.b("readFromWearable", iReadCallback)) {
            if (iqw.b(this.i, 7) || !c("readFromWearable", 101202, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ile
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    IReadCallback.this.onResult(i, null, HiHealthKitBinder.f4151a);
                }
            })) {
                ins a2 = ins.a(this.i);
                Context context = this.i;
                a2.c(str, str2, iqx.b(iReadCallback, ircVar, context, new iqy("readFromWearable", 0, iwi.a(context))));
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void registerRealTimeSportCallback(final ISportDataCallback iSportDataCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "registerSportData");
        irc ircVar = new irc();
        if (!b(iSportDataCallback, "registerSportData", ircVar) && ioy.b("registerSportData", iSportDataCallback)) {
            Objects.requireNonNull(iSportDataCallback);
            if (c("registerSportData", 101003, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilp
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    ISportDataCallback.this.onResult(i);
                }
            })) {
                return;
            }
            ITrackDataForDeveloper iTrackDataForDeveloper = this.q.get(Integer.valueOf(Binder.getCallingUid()));
            if (iTrackDataForDeveloper != null) {
                ino.b(this.i).d(iTrackDataForDeveloper, new ICommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.17
                    @Override // com.huawei.hihealth.ICommonCallback
                    public void onResult(int i, String str) {
                        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "unregister prior callback result ", Integer.valueOf(i));
                    }
                });
            }
            ITrackDataForDeveloper c2 = c(iSportDataCallback);
            this.q.put(Integer.valueOf(Binder.getCallingUid()), c2);
            ino.b(this.i).c(c2);
            iSportDataCallback.onResult(0);
            d("registerSportData", 0, ircVar);
            irh.e(iwi.a(this.i), 101003, "RealingTimeSport", (IRealTimeDataCallback) null, c2);
        }
    }

    private ITrackDataForDeveloper c(final ISportDataCallback iSportDataCallback) {
        return new ITrackDataForDeveloper.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.20
            @Override // com.huawei.health.ITrackDataForDeveloper
            public void onStateChanged(int i) {
                try {
                    ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "onStateChanged state: ", Integer.valueOf(i));
                    if (HiHealthKitBinder.this.s != null) {
                        HiHealthKitBinder.this.s.putInt("sportState", i);
                    }
                    iSportDataCallback.onDataChanged(i, HiHealthKitBinder.this.s);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "registerRealTimeSportCallback onDataChange RemoteException: ", e2.getMessage());
                }
            }

            @Override // com.huawei.health.ITrackDataForDeveloper
            public void onDataChange(Bundle bundle) {
                if (bundle == null) {
                    LogUtil.h("HiHlthKitBnd", "TrackData onDataChanged, get null");
                    return;
                }
                try {
                    LogUtil.a("HiHlthKitBnd", "onDataChange : duration = ", bundle.get("duration"), " calorie = ", bundle.get("calorie"), " distance = ", bundle.get("distance"), " speed = ", bundle.get("speed"));
                    iSportDataCallback.onDataChanged(bundle.getInt("sportState"), bundle);
                    HiHealthKitBinder.this.s = (Bundle) bundle.clone();
                } catch (RemoteException e2) {
                    LogUtil.b("HiHlthKitBnd", "registerRealTimeSportCallback onDataChange RemoteException: ", e2.getMessage());
                }
            }
        };
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void unregisterRealTimeSportCallback(final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "unRegisterSportData");
        final irc ircVar = new irc();
        if (!b(iCommonCallback, "unRegisterSportData", ircVar) && ioy.b("unRegisterSportData", iCommonCallback)) {
            final String a2 = iwi.a(this.i);
            final int callingUid = Binder.getCallingUid();
            final String str = "unRegisterSportData";
            ino.b(this.i).d(this.q.get(Integer.valueOf(callingUid)), new ICommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.3
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str2) throws RemoteException {
                    iCommonCallback.onResult(i, str2);
                    HiHealthKitBinder.this.e(str, i, ircVar, a2);
                    if (i == 0) {
                        HiHealthKitBinder.this.q.remove(Integer.valueOf(callingUid));
                    }
                }
            });
            irh.e(a2, "RealingTimeSport");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void j(ICommonCallback iCommonCallback, int i) throws RemoteException {
        iCommonCallback.onResult(i, ipd.b(1001));
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void startSport(final int i, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "startSport");
        final irc ircVar = new irc();
        if (!c("startSport", ircVar, iCommonCallback, String.valueOf(i))) {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "startSport", " API is not ready");
            return;
        }
        final String a2 = iwi.a(this.i);
        final String str = "startSport";
        ino.b(this.i).d(i, new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.1
            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i2, String str2) throws RemoteException {
                int h;
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", str, " result code ", Integer.valueOf(i2));
                if (!iqz.d(a2, 5)) {
                    h = iox.j(i2);
                } else {
                    h = !iqz.d(a2, 9) ? iox.h(i2) : i2;
                }
                iCommonCallback.onResult(h, str2);
                ircVar.c(HiHealthKitBinder.this.i, new iqy(str, i2, a2, null, String.valueOf(i)));
            }
        });
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void startSportEnhance(StartSportParam startSportParam, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter enhance ", "startSportEnhance", ", param: ", HiJsonUtil.e(startSportParam));
        final irc ircVar = new irc();
        if (!HiHealthKitDataChecker.e(startSportParam)) {
            iCommonCallback.onResult(2, ipd.b(2));
            return;
        }
        final int i = startSportParam.getInt(BleConstants.SPORT_TYPE);
        if (!c("startSportEnhance", ircVar, iCommonCallback, String.valueOf(i))) {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "startSportEnhance", " API is not ready");
            return;
        }
        final String a2 = iwi.a(this.i);
        final String str = "startSportEnhance";
        ino.b(this.i).c(startSportParam, new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.4
            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i2, String str2) throws RemoteException {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "startSportEnhance result code ", Integer.valueOf(i2));
                if (iqz.d(a2, 5)) {
                    iCommonCallback.onResult(i2, str2);
                } else {
                    iCommonCallback.onResult(iox.j(i2), str2);
                }
                ircVar.c(HiHealthKitBinder.this.i, new iqy(str, i2, a2, null, String.valueOf(i)));
            }
        });
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void stopSport(final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "stopSport");
        final irc ircVar = new irc();
        if (!c("stopSport", ircVar, iCommonCallback, (String) null)) {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "stopSport", " API is not ready");
            return;
        }
        final String a2 = iwi.a(this.i);
        final String str = "stopSport";
        ino.b(this.i).a(new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.5
            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i, String str2) throws RemoteException {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", str, " result code ", Integer.valueOf(i));
                iCommonCallback.onResult(i, str2);
                HiHealthKitBinder.this.e(str, i, ircVar, a2);
                if (i == 0) {
                    HiHealthKitBinder.this.l = null;
                    HiHealthKitBinder.this.t = -1;
                }
            }
        });
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void sendDeviceControlinstruction(String str, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "sendDeviceControlinstruction");
        final irc ircVar = new irc();
        if (b(iCommonCallback, "sendDeviceControlinstruction", ircVar)) {
            return;
        }
        if (!HiHealthKitDataChecker.a(str)) {
            iCommonCallback.onResult(2, ipd.b(2));
        } else if (ioy.b("sendDeviceControlinstruction", iCommonCallback) && !c("sendDeviceControlinstruction", 101204, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: imb
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitBinder.this.g(iCommonCallback, i);
            }
        })) {
            final String a2 = iwi.a(this.i);
            final String str2 = "sendDeviceControlinstruction";
            ino.b(this.i).a(str, new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.6
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i, String str3) throws RemoteException {
                    ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "sendDeviceControlinstruction result code ", Integer.valueOf(i));
                    if (!iqz.d(a2, 9)) {
                        i = iox.c(i);
                    }
                    iCommonCallback.onResult(i, str3);
                    ircVar.c(HiHealthKitBinder.this.i, new iqy(str2, i, a2));
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void pauseSport(final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "pauseSport");
        final irc ircVar = new irc();
        if (!c("pauseSport", ircVar, iCommonCallback, (String) null)) {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "pauseSport", " API is not ready");
            return;
        }
        final String a2 = iwi.a(this.i);
        final String str = "pauseSport";
        ino.b(this.i).d(new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.8
            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i, String str2) throws RemoteException {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", str, " result code ", Integer.valueOf(i));
                iCommonCallback.onResult(i, str2);
                HiHealthKitBinder.this.e(str, i, ircVar, a2);
            }
        });
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void resumeSport(final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "resumeSport");
        final irc ircVar = new irc();
        if (!c("resumeSport", ircVar, iCommonCallback, (String) null)) {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "resumeSport", " API is not ready");
            return;
        }
        final String a2 = iwi.a(this.i);
        final String str = "resumeSport";
        ino.b(this.i).c(new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.9
            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i, String str2) throws RemoteException {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", str, " result code ", Integer.valueOf(i));
                iCommonCallback.onResult(i, str2);
                HiHealthKitBinder.this.e(str, i, ircVar, a2);
            }
        });
    }

    private boolean c(String str, irc ircVar, final ICommonCallback iCommonCallback, String str2) throws RemoteException {
        LogUtil.a("HiHlthKitBnd", "enter ", str);
        if (b(iCommonCallback, str, ircVar)) {
            return false;
        }
        if (!TextUtils.isEmpty(str2) && !iqz.e(str, Integer.valueOf(str2).intValue(), b(str, 30), iCommonCallback)) {
            return false;
        }
        if (str.equals("startSport") || this.l == null || Binder.getCallingUid() == this.t) {
            return ioy.b(str, iCommonCallback) && !c(str, 101003, str2, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ila
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    HiHealthKitBinder.this.d(iCommonCallback, i);
                }
            });
        }
        ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "startSport's uid is not equals ", str, "'s uid");
        d(str, 4, ircVar);
        iCommonCallback.onResult(4, "unable to " + str + " started by other apps");
        return false;
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void setUserPreference(HiUserPreferenceData hiUserPreferenceData, boolean z, IDataOperateListener iDataOperateListener) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "setUserPreference");
        irc ircVar = new irc();
        if (b(iDataOperateListener, "setUserPreference", ircVar)) {
            return;
        }
        if (hiUserPreferenceData == null) {
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "userPreference is null");
            d("setUserPreference", 2, ircVar);
            iDataOperateListener.onResult(2, null);
        } else if (ioy.b("setUserPreference", iDataOperateListener)) {
            if (!iqw.b(this.i, 18)) {
                d("setUserPreference", 1001, ircVar);
                iDataOperateListener.onResult(1001, null);
                return;
            }
            boolean userPreference = HiHealthNativeApi.a(this.i).setUserPreference(new HiUserPreference(hiUserPreferenceData.getKey(), hiUserPreferenceData.getValue()), z);
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "setUserPreference isSuccess = ", Boolean.valueOf(userPreference));
            int i = !userPreference ? 1 : 0;
            iDataOperateListener.onResult(i, null);
            d("setUserPreference", i, ircVar);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getUserPreference(List<String> list, IDataOperateListener iDataOperateListener) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getUserPreference");
        irc ircVar = new irc();
        if (HiCommonUtil.d(list)) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "getUserPreferenceList keys = null");
            d("getUserPreference", 2, ircVar);
            iDataOperateListener.onResult(2, null);
            return;
        }
        if (ioy.b("getUserPreference", iDataOperateListener)) {
            if (!iqw.b(this.i, 19)) {
                d("getUserPreference", 1001, ircVar);
                iDataOperateListener.onResult(1001, null);
                return;
            }
            List<HiUserPreference> userPreferenceList = HiHealthNativeApi.a(this.i).getUserPreferenceList(list);
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "hiUserPreferenceList size = ", Integer.valueOf(userPreferenceList.size()));
            ArrayList arrayList = new ArrayList(10);
            for (HiUserPreference hiUserPreference : userPreferenceList) {
                if (hiUserPreference == null) {
                    ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "hiUserPreference == null");
                } else {
                    HiUserPreferenceData hiUserPreferenceData = new HiUserPreferenceData();
                    hiUserPreferenceData.setKey(hiUserPreference.getKey());
                    hiUserPreferenceData.setValue(hiUserPreference.getValue());
                    arrayList.add(hiUserPreferenceData);
                }
            }
            iDataOperateListener.onResult(0, arrayList);
            d("getUserPreference", 0, ircVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, int i, irc ircVar) {
        e(str, i, ircVar, iwi.a(this.i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i, irc ircVar, String str2) {
        ircVar.c(this.i, new iqy(str, i, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(irc ircVar, List<iqy> list, int i) {
        Iterator<iqy> it = list.iterator();
        while (it.hasNext()) {
            ircVar.c(this.i, it.next().b(i));
        }
    }

    private void c(String str, int i, String str2, irc ircVar) {
        iqy iqyVar = new iqy(str, 0, iwi.a(this.i), null, str2);
        Context context = this.i;
        if (i == 0) {
            i = 25;
        }
        ircVar.c(context, iqyVar.b(i));
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void setKitVersion(String str) {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter setKitVersion ", str);
        iqz.a(iwi.a(this.i), str);
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void registerPackageName(String str) {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "registerPackageName enter, packageName: ", str);
        iwi.d(this.i, str);
        ird.d(this.i).d();
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public int getServiceApiLevel() {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getServiceApiLevel");
        irc ircVar = new irc();
        int c2 = iqz.c(this.i);
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "serviceApiLevel: ", Integer.valueOf(c2));
        d("getServiceApiLevel", 0, ircVar);
        return c2;
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getHealthyLivingData(int i, HiHealthCapabilityQuery hiHealthCapabilityQuery, final ICommonCallback iCommonCallback) throws RemoteException {
        final String str = "getHealthyLivingData";
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "getHealthyLivingData");
        final irc ircVar = new irc();
        if (b(iCommonCallback, "getHealthyLivingData", ircVar)) {
            return;
        }
        if (hiHealthCapabilityQuery == null) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "query is null");
            iCommonCallback.onResult(2, ipd.b(2));
            d("getHealthyLivingData", 2, ircVar);
        } else if (ioy.b("getHealthyLivingData", iCommonCallback) && !c("getHealthyLivingData", iqt.e(hiHealthCapabilityQuery.getType()), (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: iln
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i2) {
                HiHealthKitBinder.this.c(iCommonCallback, i2);
            }
        })) {
            ikx.a(this.i).d(hiHealthCapabilityQuery.getStartTime(), hiHealthCapabilityQuery.getEndTime(), new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.7
                @Override // com.huawei.health.IBaseCommonCallback
                public void onResponse(int i2, String str2) throws RemoteException {
                    ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "getHealthyLivingData resultCode: ", Integer.valueOf(i2));
                    int a2 = iox.a(i2);
                    iCommonCallback.onResult(a2, str2);
                    HiHealthKitBinder.this.d(str, a2, ircVar);
                }
            });
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public String preRequestAuth(int i, int[] iArr, int[] iArr2, IBaseCallback iBaseCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "preRequestAuth");
        irc ircVar = new irc();
        if (b(iBaseCallback, "preRequestAuth", ircVar) || c(iBaseCallback, "preRequestAuth", ircVar)) {
            return "";
        }
        if (!ima.d(iArr2, iArr)) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "preRequestAuth", " isKitValidTypes false");
            iBaseCallback.onResult(7, Collections.EMPTY_MAP);
            d("preRequestAuth", 7, ircVar);
            return "";
        }
        if (c(iArr, iArr2, iBaseCallback, "preRequestAuth", ircVar) || !ioy.b("preRequestAuth", iBaseCallback)) {
            return "";
        }
        d(i, Binder.getCallingUid());
        try {
            ird d2 = ird.d(this.i);
            JSONObject a2 = iqs.a(iwi.a(this.i), Binder.getCallingUid(), d2.b(iArr, false), d2.b(iArr2, true));
            HashMap hashMap = new HashMap(16);
            hashMap.put("flag", String.valueOf(Binder.getCallingUid()));
            iBaseCallback.onResult(0, hashMap);
            d("preRequestAuth", 0, ircVar);
            return a2.toString();
        } catch (JSONException e2) {
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "preRequestAuth", " JSONException: ", e2.getMessage());
            iBaseCallback.onResult(4, Collections.EMPTY_MAP);
            d("preRequestAuth", 4, ircVar);
            return "";
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void registerDataAutoReport(DataReportModel dataReportModel, final IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "registerDataAutoReport");
        irc ircVar = new irc();
        if (!b(iRegisterRealTimeCallback, "registerDataAutoReport", ircVar) && ioy.b("registerDataAutoReport", iRegisterRealTimeCallback)) {
            if (!HiHealthKitDataChecker.e(dataReportModel)) {
                iRegisterRealTimeCallback.onResult(2, ipd.b(2));
            } else if (CharacteristicConstant.ReportType.containsAuthIgnore(dataReportModel.getReportType()) || !c("registerDataAutoReport", dataReportModel.getDataType(), String.valueOf(dataReportModel.getDataType()), ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilt
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i) {
                    IRegisterRealTimeCallback.this.onResult(i, ipd.b(1001));
                }
            })) {
                irn.b(this.i).c(dataReportModel, iRegisterRealTimeCallback, ircVar);
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void unregisterDataAutoReport(DataReportModel dataReportModel, IRegisterRealTimeCallback iRegisterRealTimeCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "unregisterDataAutoReport");
        irc ircVar = new irc();
        if (!b(iRegisterRealTimeCallback, "unregisterDataAutoReport", ircVar) && ioy.b("unregisterDataAutoReport", iRegisterRealTimeCallback)) {
            if (!HiHealthKitDataChecker.e(dataReportModel)) {
                iRegisterRealTimeCallback.onResult(2, ipd.b(2));
            } else {
                irn.b(this.i).d(dataReportModel, iRegisterRealTimeCallback, ircVar);
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void bindDevice(String str, String str2, String str3, int i, ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "bindDevice");
        irc ircVar = new irc();
        if (b(iCommonCallback, "bindDevice", ircVar)) {
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "productId or uniqueId is null");
            iCommonCallback.onResult(2, ipd.b(2));
            d("bindDevice", 2, ircVar);
        } else if (ioy.b("bindDevice", iCommonCallback)) {
            if (!iqt.a()) {
                ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "bindDevice permission deny");
                iCommonCallback.onResult(1001, ipd.b(1001));
                d("bindDevice", 1001, ircVar);
            } else {
                ins a2 = ins.a(this.i);
                Context context = this.i;
                a2.c(str, str2, str3, i, iqx.a(iCommonCallback, ircVar, context, new iqy("bindDevice", 0, iwi.a(context)), "com.huawei.hihealthservice.hihealthkit.constant.BusinessErrorCode.wearApiFilterCode"));
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void getUserInfo(List<String> list, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", com.huawei.up.utils.Constants.METHOD_GET_USER_INFO);
        irc ircVar = new irc();
        if (b(iCommonCallback, com.huawei.up.utils.Constants.METHOD_GET_USER_INFO, ircVar)) {
            return;
        }
        if (koq.b(list)) {
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "getUserInfo dataTypeList is empty");
            iCommonCallback.onResult(2, ipd.b(2));
            d(com.huawei.up.utils.Constants.METHOD_GET_USER_INFO, 2, ircVar);
        } else if (ioy.b(com.huawei.up.utils.Constants.METHOD_GET_USER_INFO, iCommonCallback) && !c(com.huawei.up.utils.Constants.METHOD_GET_USER_INFO, 101001, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilk
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitBinder.this.a(iCommonCallback, i);
            }
        })) {
            irg.d(list, iCommonCallback, ircVar, new iqy(com.huawei.up.utils.Constants.METHOD_GET_USER_INFO, 0, iwi.a(this.i)), this.i);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void subscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "subscribeData");
        irc ircVar = new irc(this.i, new iqy("subscribeData", 0, iwi.a(this.i)));
        if (!b(iSubScribeCallback, "subscribeData", ircVar) && ioy.b("subscribeData", iSubScribeCallback)) {
            Notification notification = new Notification(0, 4, ipd.b(4));
            if (!HiHealthKitDataChecker.c(subscribeModel)) {
                ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "subscribeData wrong subscribe model");
                notification.setErrorInfo(2, ipd.b(2));
                iSubScribeCallback.onResult(new ArrayList(), Arrays.asList(notification));
                ircVar.c().b(2);
                for (int i : subscribeModel.getDataTypes()) {
                    ircVar.c().e(String.valueOf(i));
                    ircVar.d();
                }
                return;
            }
            irp.d(this.i).a(subscribeModel, iSubScribeCallback, ircVar);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void unSubscribeData(SubscribeModel subscribeModel, ISubScribeCallback iSubScribeCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "unSubscribeData");
        irc ircVar = new irc(this.i, new iqy("unSubscribeData", 0, iwi.a(this.i)));
        if (ioy.b("unSubscribeData", iSubScribeCallback)) {
            Notification notification = new Notification(0, 4, ipd.b(4));
            if (!HiHealthKitDataChecker.c(subscribeModel)) {
                ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "unSubscribeData wrong subscribe model");
                notification.setErrorInfo(2, ipd.b(2));
                iSubScribeCallback.onResult(new ArrayList(), Arrays.asList(notification));
                ircVar.c().b(2);
                for (int i : subscribeModel.getDataTypes()) {
                    ircVar.c().e(String.valueOf(i));
                    ircVar.d();
                }
                return;
            }
            irp.d(this.i).c(subscribeModel, iSubScribeCallback, ircVar);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void subscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "subscribeDataEx");
        irc ircVar = new irc(this.i, new iqy("subscribeDataEx", 0, iwi.a(this.i)));
        if (b(iCommonCallback, "subscribeDataEx", ircVar)) {
            return;
        }
        if (!iqz.d(eventTypeInfo)) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "subscribeDataEx dataType is not support.");
            iCommonCallback.onResult(30, ipd.b(30));
            d("subscribeDataEx", 30, ircVar);
            return;
        }
        if (ioy.b("subscribeDataEx", iCommonCallback)) {
            int d2 = HiHealthKitDataChecker.d(subscriber);
            if (d2 != 0) {
                ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "subscribeDataEx subscriber is invalid. ");
                iCommonCallback.onResult(d2, ipd.b(d2));
                d("subscribeDataEx", d2, ircVar);
            } else {
                if (!HiHealthKitDataChecker.e(eventTypeInfo, true)) {
                    iCommonCallback.onResult(2, ipd.b(2));
                    d("subscribeDataEx", 2, ircVar);
                    return;
                }
                if (eventTypeInfo instanceof GoalInfo) {
                    for (Goal goal : ((GoalInfo) eventTypeInfo).getGoals()) {
                        if (!iqw.b(this.i, 20) && c("subscribeDataEx", goal.getDataType(), (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilf
                            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                            public final void operate(int i) {
                                HiHealthKitBinder.this.h(iCommonCallback, i);
                            }
                        })) {
                            return;
                        }
                    }
                }
                eventTypeInfo.setSubscriptionMode(2);
                inv.c(this.i).b(subscriber, eventTypeInfo, iCommonCallback, ircVar);
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void unSubscribeDataEx(Subscriber subscriber, EventTypeInfo eventTypeInfo, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "unSubscribeDataEx");
        irc ircVar = new irc(this.i, new iqy("unSubscribeDataEx", 0, iwi.a(this.i)));
        if (b(iCommonCallback, "unSubscribeDataEx", ircVar)) {
            return;
        }
        if (!iqz.d(eventTypeInfo)) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "unSubscribeDataEx dataType is not support.");
            iCommonCallback.onResult(30, ipd.b(30));
            d("unSubscribeDataEx", 30, ircVar);
            return;
        }
        if (ioy.b("unSubscribeDataEx", iCommonCallback)) {
            int d2 = HiHealthKitDataChecker.d(subscriber);
            if (d2 != 0) {
                ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "unSubscribeDataEx subscriber is invalid. ");
                iCommonCallback.onResult(d2, ipd.b(d2));
                d("unSubscribeDataEx", d2, ircVar);
            } else {
                if (!HiHealthKitDataChecker.e(eventTypeInfo, false)) {
                    iCommonCallback.onResult(2, ipd.b(2));
                    d("unSubscribeDataEx", 2, ircVar);
                    return;
                }
                if (eventTypeInfo instanceof GoalInfo) {
                    for (Goal goal : ((GoalInfo) eventTypeInfo).getGoals()) {
                        if (!iqw.b(this.i, 21) && c("unSubscribeDataEx", goal.getDataType(), (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ill
                            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                            public final void operate(int i) {
                                HiHealthKitBinder.this.j(iCommonCallback, i);
                            }
                        })) {
                            return;
                        }
                    }
                }
                eventTypeInfo.setSubscriptionMode(2);
                inv.c(this.i).d(eventTypeInfo, subscriber, iCommonCallback, ircVar);
            }
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void syncData(int[] iArr, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter syncData");
        irc ircVar = new irc();
        if (b(iCommonCallback, "syncData", ircVar)) {
            return;
        }
        if (!HiHealthKitDataChecker.e(iArr)) {
            iCommonCallback.onResult(2, ipd.b(2));
            d("syncData", 2, ircVar);
        } else if (ioy.b("syncData", iCommonCallback) && !c("syncData", 0, (String) null, ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilc
            @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
            public final void operate(int i) {
                HiHealthKitBinder.this.f(iCommonCallback, i);
            }
        })) {
            inq.b().e(iArr, iCommonCallback);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void connectSportDevice(final int i, final ICommonCallback iCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "connectSportDevice");
        final irc ircVar = new irc();
        if (!c("connectSportDevice", ircVar, iCommonCallback, String.valueOf(i))) {
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "connectSportDevice", " API is not ready");
            return;
        }
        final String a2 = iwi.a(this.i);
        final String str = "connectSportDevice";
        ino.b(this.i).c(i, new IBaseCommonCallback.Stub() { // from class: com.huawei.hihealthservice.hihealthkit.HiHealthKitBinder.10
            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i2, String str2) throws RemoteException {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", str, " result code ", Integer.valueOf(i2));
                iCommonCallback.onResult(i2, str2);
                ircVar.c(HiHealthKitBinder.this.i, new iqy(str, i2, a2, null, String.valueOf(i)));
            }
        });
    }

    @Override // com.huawei.hihealth.IHiHealthKit
    public void queryTrends(TrendQuery trendQuery, int i, final IDataReadResultListener iDataReadResultListener) throws RemoteException {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "enter ", "queryTrends");
        String a2 = iwi.a(this.i);
        irc ircVar = new irc(this.i, new iqy("queryTrends", 0, a2));
        if (b(iDataReadResultListener, "queryTrends", ircVar)) {
            iDataReadResultListener.onResult(Collections.EMPTY_LIST, 2, 2);
            return;
        }
        if (!HiHealthKitDataChecker.e(trendQuery, i)) {
            iDataReadResultListener.onResult(Collections.EMPTY_LIST, 2, 2);
            d("queryTrends", 2, ircVar);
            return;
        }
        if (!iqz.b(trendQuery)) {
            iDataReadResultListener.onResult(Collections.EMPTY_LIST, 30, 2);
            d("queryTrends", 30, ircVar);
        } else if (ioy.b("queryTrends", iDataReadResultListener)) {
            if (c("queryTrends", iqt.e(0), String.valueOf(0), ircVar, new HiHealthKitExtendBinder.Action() { // from class: ilo
                @Override // com.huawei.hihealthservice.hihealthkit.HiHealthKitExtendBinder.Action
                public final void operate(int i2) {
                    HiHealthKitBinder.this.a(iDataReadResultListener, i2);
                }
            })) {
                return;
            }
            new HiHealthKitQueryHelper(this.i, a2).e(trendQuery, i, iDataReadResultListener);
        }
    }

    @Override // com.huawei.hihealth.IHiHealthKit.Stub, android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        try {
            return super.onTransact(i, parcel, parcel2, i2);
        } catch (Exception e2) {
            String b2 = LogAnonymous.b((Throwable) e2);
            ReleaseLogUtil.c("R_HiH_HiHlthKitBnd", "HiHealthKitBinder onTransact Exception: ", b2);
            String arrays = Arrays.toString(e2.getStackTrace());
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(6);
            linkedHashMap.put("keyWords", "KitBinderTransactException");
            linkedHashMap.put("messages", b2 + arrays);
            ivz.d(this.i).e(OperationKey.HEALTH_APP_PROBABILITY_PROBLEM_85070032.value(), linkedHashMap, true);
            return false;
        }
    }

    private boolean c(IBaseCallback iBaseCallback, String str, irc ircVar) throws RemoteException {
        String a2 = iwi.a(this.i);
        Long l = this.m.get(a2);
        if (l != null) {
            long currentTimeMillis = System.currentTimeMillis() - l.longValue();
            if (currentTimeMillis < 2000) {
                ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "checkRequestTime diffTime less than defaultTime: ", Long.valueOf(currentTimeMillis));
                iBaseCallback.onResult(4, Collections.EMPTY_MAP);
                d(str, 4, ircVar);
                return true;
            }
        }
        this.m.put(a2, Long.valueOf(System.currentTimeMillis()));
        return false;
    }

    public void onDestroy() {
        ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "onDestroy");
        HealthOpenSDK healthOpenSDK = this.o;
        if (healthOpenSDK != null) {
            healthOpenSDK.destorySDK();
        }
    }

    /* loaded from: classes7.dex */
    static class c extends IRealTimeDataCallback.Stub {
        private String d;

        @Override // com.huawei.hihealth.IRealTimeDataCallback
        public void onChange(int i, String str) {
        }

        c(String str) {
            this.d = str;
        }

        @Override // com.huawei.hihealth.IRealTimeDataCallback
        public void onResult(int i) throws RemoteException {
            LogUtil.a("HiHlthKitBnd", "auto stop ", this.d, " result code = ", Integer.valueOf(i));
        }
    }

    /* loaded from: classes7.dex */
    class b implements IExecuteResult {
        private b() {
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            HiHealthKitBinder.this.h = true;
            ReleaseLogUtil.e("R_HiH_HiHlthKitBnd", "healthOpenSDKCallback initSDK success");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "healthOpenSDKCallback : initSDK Failed");
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            ReleaseLogUtil.d("R_HiH_HiHlthKitBnd", "healthOpenSDKCallback : onServiceException");
        }
    }
}
