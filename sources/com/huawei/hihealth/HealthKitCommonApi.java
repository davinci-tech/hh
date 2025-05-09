package com.huawei.hihealth;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.huawei.hihealth.IBinderInterceptor;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IHiHealthKit;
import com.huawei.hihealth.IHiHealthKitEx;
import com.huawei.hihealth.IRegisterRealTimeCallback;
import com.huawei.hihealth.ISubScribeCallback;
import com.huawei.hihealth.error.HiHealthError;
import com.huawei.hihealth.listener.HiSubscribeCallback;
import com.huawei.hihealth.listener.IuniversalCallback;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Notification;
import com.huawei.hihealth.model.SubscribeModel;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealthkit.context.OutOfBandContext;
import com.huawei.hihealthkit.data.store.HiRealTimeCallback;
import com.huawei.up.utils.Constants;
import defpackage.ife;
import defpackage.iff;
import defpackage.ifi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class HealthKitCommonApi {
    private static Context d;
    private IHiHealthKit b;
    private final Object f;
    private IHiHealthKitEx g;
    private ServiceConnection h;
    private ExecutorService i;
    private IBinder j;
    private List<SubscribeModel> m;
    private Map<HiSubscribeCallback, ISubScribeCallback> o;
    private static Map<HiRealTimeCallback, IRegisterRealTimeCallback> e = new ConcurrentHashMap(10);
    private static List<DataReportModel> c = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private static DataReportModel f3970a = null;

    private HealthKitCommonApi() {
        this.f = new Object();
        this.m = new ArrayList(10);
        this.o = new HashMap(10);
        this.j = new Binder();
        this.h = new ServiceConnection() { // from class: com.huawei.hihealth.HealthKitCommonApi.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                try {
                    if (HealthKitCommonApi.d instanceof OutOfBandContext) {
                        IBinder serviceBinder = IBinderInterceptor.Stub.asInterface(iBinder).getServiceBinder("KIT_EXTEND");
                        HealthKitCommonApi.this.g = IHiHealthKitEx.Stub.asInterface(serviceBinder);
                    } else {
                        IBinder serviceBinder2 = IBinderInterceptor.Stub.asInterface(iBinder).getServiceBinder(null);
                        HealthKitCommonApi.this.b = IHiHealthKit.Stub.asInterface(serviceBinder2);
                    }
                } catch (RemoteException unused) {
                    Log.w("HealthKitCommonApi", "onServiceConnected Exception");
                }
                try {
                    if (HealthKitCommonApi.this.b != null || HealthKitCommonApi.this.g != null) {
                        if (HealthKitCommonApi.this.b != null) {
                            HealthKitCommonApi.this.b.registerPackageName(HealthKitCommonApi.d.getPackageName());
                            HealthKitCommonApi.this.b.setKitVersion(String.valueOf(iff.a(HealthKitCommonApi.d)));
                            iff.e(HealthKitCommonApi.this.b.getServiceApiLevel());
                        } else {
                            HealthKitCommonApi.this.g.registerPackageName(HealthKitCommonApi.d.getPackageName());
                            HealthKitCommonApi.this.g.setKitVersion(String.valueOf(iff.a(HealthKitCommonApi.d)));
                            iff.e(HealthKitCommonApi.this.g.getServiceApiLevel(((OutOfBandContext) HealthKitCommonApi.d).getOutOfBandData()));
                        }
                    } else {
                        Log.w("HealthKitCommonApi", "bind service fail");
                    }
                } catch (RemoteException unused2) {
                    Log.w("HealthKitCommonApi", "init kit version error");
                }
                synchronized (HealthKitCommonApi.this.f) {
                    HealthKitCommonApi.this.f.notifyAll();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.w("HealthKitCommonApi", "onServiceDisconnected");
                HealthKitCommonApi.this.b = null;
                HealthKitCommonApi.this.g = null;
                Iterator it = HealthKitCommonApi.e.keySet().iterator();
                while (it.hasNext()) {
                    ((HiRealTimeCallback) it.next()).onResultHandler(31, HiHealthError.e(31));
                }
                Iterator it2 = HealthKitCommonApi.this.o.keySet().iterator();
                while (it2.hasNext()) {
                    ((HiSubscribeCallback) it2.next()).onResultHandler(new ArrayList(), Arrays.asList(new Notification(0, 31, HiHealthError.e(31))));
                }
                HealthKitCommonApi.e.clear();
                HealthKitCommonApi.this.o.clear();
                HealthKitCommonApi.this.m.clear();
            }
        };
        Log.i("HealthKitCommonApi", "HiHealthKitCommonApi construct");
        this.i = Executors.newSingleThreadExecutor();
    }

    public static HealthKitCommonApi d(Context context) {
        synchronized (HealthKitCommonApi.class) {
            if (context instanceof OutOfBandContext) {
                d = context;
            } else if (d == null) {
                d = context.getApplicationContext();
            } else {
                return Instance.d;
            }
            return Instance.d;
        }
    }

    private void d() {
        if (this.b == null && this.g == null) {
            Intent intent = new Intent("com.huawei.health.action.KIT_SERVICE");
            intent.setClassName(ife.e(), "com.huawei.hihealthservice.HiHealthService");
            intent.setPackage(ife.e());
            try {
                d.bindService(intent, this.h, 1);
            } catch (SecurityException unused) {
                Log.e("HealthKitCommonApi", "bindService exception");
            }
            synchronized (this.f) {
                try {
                    this.f.wait(5000L);
                } catch (InterruptedException unused2) {
                    Log.e("HealthKitCommonApi", "bindService InterruptedException");
                }
            }
        }
    }

    /* synthetic */ void b(HiRealTimeCallback hiRealTimeCallback, DataReportModel dataReportModel) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "registerDataAutoReport mApiAidl is null");
            hiRealTimeCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("register_data_auto_report")) {
            hiRealTimeCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            b(dataReportModel, hiRealTimeCallback);
        }
    }

    private boolean a(DataReportModel dataReportModel, HiRealTimeCallback hiRealTimeCallback) {
        if (c.contains(dataReportModel)) {
            Log.i("HealthKitCommonApi", "checkModelValid repeat error");
            hiRealTimeCallback.onResultHandler(101, HiHealthError.e(101));
            return false;
        }
        if (c.size() >= 10) {
            Log.i("HealthKitCommonApi", "checkModelValid limit error");
            hiRealTimeCallback.onResultHandler(102, HiHealthError.e(102));
            return false;
        }
        c.add(dataReportModel);
        Log.i("HealthKitCommonApi", "checkModelValid checkModelValid ok");
        return true;
    }

    private void e(DataReportModel dataReportModel) {
        c.remove(dataReportModel);
    }

    private void b(DataReportModel dataReportModel, final HiRealTimeCallback hiRealTimeCallback) {
        IRegisterRealTimeCallback iRegisterRealTimeCallback;
        if (!a(dataReportModel, hiRealTimeCallback)) {
            Log.i("HealthKitCommonApi", "checkModelValid fail");
            return;
        }
        if (e.containsKey(hiRealTimeCallback)) {
            iRegisterRealTimeCallback = e.get(hiRealTimeCallback);
        } else {
            iRegisterRealTimeCallback = new IRegisterRealTimeCallback.Stub() { // from class: com.huawei.hihealth.HealthKitCommonApi.2
                @Override // com.huawei.hihealth.IRegisterRealTimeCallback
                public void onResult(int i, String str) {
                    Log.i("HealthKitCommonApi", "DataAutoReportImpl onResult errCode = " + i);
                    hiRealTimeCallback.onResultHandler(HiHealthError.c(i), str);
                }

                @Override // com.huawei.hihealth.IRegisterRealTimeCallback
                public void onDataChanged(Bundle bundle) {
                    hiRealTimeCallback.onDataChangedHandler(bundle);
                }
            };
            e.put(hiRealTimeCallback, iRegisterRealTimeCallback);
        }
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.registerDataAutoReport(dataReportModel, iRegisterRealTimeCallback);
            } else {
                this.g.registerDataAutoReport(((OutOfBandContext) d).getOutOfBandData(), dataReportModel, iRegisterRealTimeCallback);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "registerRealTimeCallbackImpl exception");
            hiRealTimeCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void a(HiRealTimeCallback hiRealTimeCallback, DataReportModel dataReportModel) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "registerDataAutoReport mApiAidl is null");
            hiRealTimeCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("register_data_auto_report")) {
            hiRealTimeCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            d(dataReportModel, hiRealTimeCallback);
        }
    }

    private void d(DataReportModel dataReportModel, HiRealTimeCallback hiRealTimeCallback) {
        e(dataReportModel);
        IRegisterRealTimeCallback iRegisterRealTimeCallback = e.get(hiRealTimeCallback);
        if (iRegisterRealTimeCallback == null) {
            Log.e("HealthKitCommonApi", "unregisterDataAutoReportImpl innerCallback = null");
            hiRealTimeCallback.onResultHandler(4, HiHealthError.e(4));
            return;
        }
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.unregisterDataAutoReport(dataReportModel, iRegisterRealTimeCallback);
            } else {
                this.g.unregisterDataAutoReport(((OutOfBandContext) d).getOutOfBandData(), dataReportModel, iRegisterRealTimeCallback);
            }
        } catch (Exception unused) {
            Log.e("HealthKitCommonApi", "unregisterDataAutoReportImpl Exception");
            hiRealTimeCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    public void c(final StartSportParam startSportParam, final ResultCallback resultCallback) {
        Log.i("HealthKitCommonApi", "startSportEnhance");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HealthKitCommonApi$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                HealthKitCommonApi.this.c(resultCallback, startSportParam);
            }
        });
    }

    /* synthetic */ void c(ResultCallback resultCallback, StartSportParam startSportParam) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "startSportEnhance mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("register_data_auto_report")) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            a(startSportParam, resultCallback);
        }
    }

    private void a(StartSportParam startSportParam, final ResultCallback resultCallback) {
        ICommonCallback.Stub stub = new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HealthKitCommonApi.3
            @Override // com.huawei.hihealth.ICommonCallback
            public void onResult(int i, String str) throws RemoteException {
                Log.i("HealthKitCommonApi", "resultCode = " + i);
                resultCallback.onResultHandler(HiHealthError.c(i), str);
                if (i != 0 || HealthKitCommonApi.this.b == null) {
                    return;
                }
                HealthKitCommonApi.this.b.setBinder(HealthKitCommonApi.this.j);
            }
        };
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.startSportEnhance(startSportParam, stub);
            } else {
                this.g.startSportEnhance(((OutOfBandContext) d).getOutOfBandData(), startSportParam, stub);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "startSportEnhanceImpl RemoteException");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "startSportEnhanceImpl Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    public void d(final ResultCallback resultCallback) {
        Log.i("HealthKitCommonApi", "stopSport");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HealthKitCommonApi$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                HealthKitCommonApi.this.c(resultCallback);
            }
        });
    }

    /* synthetic */ void c(ResultCallback resultCallback) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "stopSport mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else {
            f(resultCallback);
        }
    }

    private void f(ResultCallback resultCallback) {
        ICommonCallback.Stub a2 = a(resultCallback);
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.stopSport(a2);
            } else {
                this.g.stopSport(((OutOfBandContext) d).getOutOfBandData(), a2);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "stopSport RemoteException");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "stopSport Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void e(ResultCallback resultCallback) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "stopSport mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("pauseSport")) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            h(resultCallback);
        }
    }

    private void h(ResultCallback resultCallback) {
        ICommonCallback.Stub a2 = a(resultCallback);
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.pauseSport(a2);
            } else {
                this.g.pauseSport(((OutOfBandContext) d).getOutOfBandData(), a2);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "stopSport RemoteException");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "stopSport Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void b(ResultCallback resultCallback) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "stopSport mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("resumeSport")) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            g(resultCallback);
        }
    }

    private void g(ResultCallback resultCallback) {
        ICommonCallback.Stub a2 = a(resultCallback);
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.resumeSport(a2);
            } else {
                this.g.resumeSport(((OutOfBandContext) d).getOutOfBandData(), a2);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "stopSport RemoteException");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "stopSport Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    private ICommonCallback.Stub a(final ResultCallback resultCallback) {
        return new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HealthKitCommonApi.4
            @Override // com.huawei.hihealth.ICommonCallback
            public void onResult(int i, String str) {
                Log.i("HealthKitCommonApi", "resultCode = " + i);
                resultCallback.onResultHandler(HiHealthError.c(i), str);
            }
        };
    }

    /* synthetic */ void b(ResultCallback resultCallback, String str) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "sendDeviceControlinstruction mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("send_device_controlinstruction")) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            b(str, resultCallback);
        }
    }

    private void b(String str, ResultCallback resultCallback) {
        ICommonCallback.Stub a2 = a(resultCallback);
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.sendDeviceControlinstruction(str, a2);
            } else {
                this.g.sendDeviceControlinstruction(((OutOfBandContext) d).getOutOfBandData(), str, a2);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "sendDeviceControlinstructionImpl RemoteException");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "sendDeviceControlinstructionImpl Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void e(ResultCallback resultCallback, List list) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "getUserInfo mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d(Constants.METHOD_GET_USER_INFO)) {
            Log.w("HealthKitCommonApi", "getUserInfo not support");
            resultCallback.onResultHandler(30, HiHealthError.e(30));
        } else {
            d((List<String>) list, resultCallback);
        }
    }

    private void d(List<String> list, ResultCallback resultCallback) {
        ICommonCallback.Stub a2 = a(resultCallback);
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.getUserInfo(list, a2);
            } else {
                this.g.getUserInfo(((OutOfBandContext) d).getOutOfBandData(), list, a2);
            }
        } catch (Exception unused) {
            Log.e("HealthKitCommonApi", "getUserInfo Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void c(final ResultCallback resultCallback, HiUserPreferenceData hiUserPreferenceData, boolean z) {
        d();
        if (this.b == null) {
            resultCallback.onResultHandler(1, "setUserPreference mApiAidl is null");
            Log.w("HealthKitCommonApi", "setUserPreference mApiAidl is null");
            return;
        }
        if (!iff.d("setUserPreference")) {
            Log.w("HealthKitCommonApi", "setUserPreference not support");
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            return;
        }
        try {
            IDataOperateListener.Stub stub = new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HealthKitCommonApi.5
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list) throws RemoteException {
                    Log.i("HealthKitCommonApi", "setUserPreference onResult:" + i);
                    resultCallback.onResultHandler(i, i == 0 ? null : HiHealthError.e(i));
                }
            };
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.setUserPreference(hiUserPreferenceData, z, stub);
            } else {
                this.g.setUserPreference(((OutOfBandContext) d).getOutOfBandData(), hiUserPreferenceData, z, stub);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "setUserPreference, RemoteException");
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "setUserPreference, Exception");
        }
    }

    /* synthetic */ void c(final ResultCallback resultCallback, List list) {
        d();
        if (this.b == null) {
            resultCallback.onResultHandler(1, "setUserPreference mApiAidl is null");
            Log.w("HealthKitCommonApi", "getUserPreference mApiAidl is null");
            return;
        }
        if (!iff.d("getUserPreference")) {
            Log.w("HealthKitCommonApi", "getUserPreference not support");
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            return;
        }
        try {
            IDataOperateListener.Stub stub = new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HealthKitCommonApi.6
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list2) throws RemoteException {
                    Log.i("HealthKitCommonApi", "getUserPreference onResult:" + i);
                    resultCallback.onResultHandler(i, list2);
                }
            };
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.getUserPreference(list, stub);
            } else {
                this.g.getUserPreference(((OutOfBandContext) d).getOutOfBandData(), list, stub);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "getUserPreference, RemoteException");
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "getUserPreference, Exception");
        }
    }

    /* synthetic */ void d(HiSubscribeCallback hiSubscribeCallback, Notification notification, SubscribeModel subscribeModel) {
        Log.i("HealthKitCommonApi", "subscribeDataApi");
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "registerDataAutoReport mApiAidl is null");
            hiSubscribeCallback.onResultHandler(new ArrayList(), Arrays.asList(notification));
        } else {
            if (this.m.contains(subscribeModel)) {
                Log.w("HealthKitCommonApi", HiHealthError.e(101));
                notification.setErrorInfo(101, HiHealthError.e(101));
                hiSubscribeCallback.onResultHandler(new ArrayList(), Arrays.asList(notification));
                return;
            }
            a(subscribeModel, hiSubscribeCallback, notification);
        }
    }

    private void a(final SubscribeModel subscribeModel, final HiSubscribeCallback hiSubscribeCallback, Notification notification) {
        ISubScribeCallback iSubScribeCallback;
        if (this.o.containsKey(hiSubscribeCallback)) {
            iSubScribeCallback = this.o.get(hiSubscribeCallback);
        } else {
            iSubScribeCallback = new ISubScribeCallback.Stub() { // from class: com.huawei.hihealth.HealthKitCommonApi.7
                @Override // com.huawei.hihealth.ISubScribeCallback
                public void onResult(List<Notification> list, List<Notification> list2) {
                    for (Notification notification2 : list) {
                        Log.i("HealthKitCommonApi", "success data result, data type is " + notification2.getDataType() + " error code is " + notification2.getErrorCode());
                    }
                    for (Notification notification3 : list2) {
                        Log.w("HealthKitCommonApi", "fail data result, data type is " + notification3.getDataType() + " error code is " + notification3.getErrorCode());
                    }
                    hiSubscribeCallback.onResultHandler(list, list2);
                    if (list2.isEmpty() && !list.isEmpty() && list.get(0).getSubscribeTag()) {
                        Log.i("HealthKitCommonApi", "subscribe success");
                        HealthKitCommonApi.this.m.add(subscribeModel);
                        HealthKitCommonApi.this.o.put(hiSubscribeCallback, this);
                    }
                    if (!list2.isEmpty() || list.isEmpty() || list.get(0).getSubscribeTag()) {
                        return;
                    }
                    Log.i("HealthKitCommonApi", "unsubscribe success");
                    HealthKitCommonApi.this.m.remove(subscribeModel);
                    HealthKitCommonApi.this.o.remove(hiSubscribeCallback);
                }

                @Override // com.huawei.hihealth.ISubScribeCallback
                public void onDataChanged(Notification notification2) {
                    Log.i("HealthKitCommonApi", "subscribe data changed, error code is " + notification2.getErrorCode());
                    hiSubscribeCallback.onDataChangedHandler(notification2);
                }
            };
        }
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.subscribeData(subscribeModel, iSubScribeCallback);
            } else {
                this.g.subscribeData(((OutOfBandContext) d).getOutOfBandData(), subscribeModel, iSubScribeCallback);
            }
        } catch (Exception unused) {
            Log.e("HealthKitCommonApi", "registerRealTimeCallbackImpl exception");
            notification.setErrorInfo(4, HiHealthError.e(4));
            hiSubscribeCallback.onResultHandler(new ArrayList(), Arrays.asList(notification));
        }
    }

    /* synthetic */ void b(Notification notification, HiSubscribeCallback hiSubscribeCallback, SubscribeModel subscribeModel) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "mApiAidl is null");
            notification.setErrorInfo(1, HiHealthError.e(1));
            hiSubscribeCallback.onResultHandler(new ArrayList(), Arrays.asList(notification));
        } else {
            if (!this.m.contains(subscribeModel)) {
                Log.w("HealthKitCommonApi", "not register before");
                notification.setErrorInfo(101, HiHealthError.e(101));
                hiSubscribeCallback.onResultHandler(new ArrayList(), Arrays.asList(notification));
                return;
            }
            e(subscribeModel, hiSubscribeCallback, notification);
        }
    }

    private void e(SubscribeModel subscribeModel, HiSubscribeCallback hiSubscribeCallback, Notification notification) {
        ISubScribeCallback iSubScribeCallback = this.o.get(hiSubscribeCallback);
        if (iSubScribeCallback == null) {
            Log.e("HealthKitCommonApi", "unSubscribeDataImpl innerCallback = null");
            notification.setErrorInfo(101, HiHealthError.e(101));
            hiSubscribeCallback.onResultHandler(new ArrayList(), Arrays.asList(notification));
            return;
        }
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.unSubscribeData(subscribeModel, iSubScribeCallback);
            } else {
                this.g.unSubscribeData(((OutOfBandContext) d).getOutOfBandData(), subscribeModel, iSubScribeCallback);
            }
        } catch (Exception unused) {
            Log.e("HealthKitCommonApi", "unregisterDataAutoReportImpl exception");
            notification.setErrorInfo(4, HiHealthError.e(4));
            hiSubscribeCallback.onResultHandler(new ArrayList(), Arrays.asList(notification));
        }
    }

    /* synthetic */ void b(ResultCallback resultCallback, Subscriber subscriber, EventTypeInfo eventTypeInfo) {
        Log.i("HealthKitCommonApi", "enter subscribeDataEx");
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "subscribeDataEx aidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("subscribe_goal_achieve")) {
            Log.w("HealthKitCommonApi", "does not support subscribeDataEx");
            resultCallback.onResultHandler(30, HiHealthError.e(30));
        } else {
            try {
                this.b.subscribeDataEx(subscriber, eventTypeInfo, a(resultCallback));
            } catch (Exception unused) {
                Log.w("HealthKitCommonApi", "aidl exception");
            }
        }
    }

    /* synthetic */ void c(ResultCallback resultCallback, Subscriber subscriber, EventTypeInfo eventTypeInfo) {
        Log.i("HealthKitCommonApi", "enter unSubscribeDataEx");
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "unSubscribeDataEx aidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("subscribe_goal_achieve")) {
            Log.w("HealthKitCommonApi", "does not support subscribeDataEx");
            resultCallback.onResultHandler(30, HiHealthError.e(30));
        } else {
            try {
                this.b.unSubscribeDataEx(subscriber, eventTypeInfo, a(resultCallback));
            } catch (Exception unused) {
                Log.w("HealthKitCommonApi", "aidl exception");
            }
        }
    }

    /* synthetic */ void e() {
        Log.i("HealthKitCommonApi", "enter unBindHealthService");
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "no need to unbind");
            return;
        }
        try {
            d.unbindService(this.h);
            this.b = null;
            this.g = null;
        } catch (Exception unused) {
            Log.w("HealthKitCommonApi", "unbind service exception");
        }
    }

    public void c(final int[] iArr, final ResultCallback resultCallback) {
        Log.i("HealthKitCommonApi", "enter syncData");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HealthKitCommonApi$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HealthKitCommonApi.this.a(resultCallback, iArr);
            }
        });
    }

    /* synthetic */ void a(ResultCallback resultCallback, int[] iArr) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "syncData mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("syncData")) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else if (ifi.e(Arrays.asList(iArr))) {
            resultCallback.onResultHandler(1021, HiHealthError.e(1021));
            Log.w("HealthKitCommonApi", "syncDataTypes is too large");
        } else {
            b(iArr, resultCallback);
        }
    }

    private void b(int[] iArr, ResultCallback resultCallback) {
        ICommonCallback.Stub a2 = a(resultCallback);
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.syncData(iArr, a2);
            } else {
                this.g.syncData(((OutOfBandContext) d).getOutOfBandData(), iArr, a2);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "syncDataImpl RemoteException");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "syncDataImpl Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void e(ResultCallback resultCallback, int i) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "connectSportDevice mApiAidl is null");
            resultCallback.onResultHandler(1, HiHealthError.e(1));
        } else if (!iff.d("connectSportDevice")) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            a(i, resultCallback);
        }
    }

    private void a(int i, ResultCallback resultCallback) {
        ICommonCallback.Stub a2 = a(resultCallback);
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.connectSportDevice(i, a2);
            } else {
                this.g.connectSportDevice(((OutOfBandContext) d).getOutOfBandData(), i, a2);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "syncDataImpl RemoteException");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "syncDataImpl Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    public void d(final TrendQuery trendQuery, final int i, final IuniversalCallback iuniversalCallback) {
        Log.i("HealthKitCommonApi", "queryTrends");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HealthKitCommonApi$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                HealthKitCommonApi.this.b(iuniversalCallback, trendQuery, i);
            }
        });
    }

    /* synthetic */ void b(IuniversalCallback iuniversalCallback, TrendQuery trendQuery, int i) {
        d();
        if (this.b == null && this.g == null) {
            Log.w("HealthKitCommonApi", "queryTrends mApiAidl is null");
            iuniversalCallback.onResultHandler(1, null, HiHealthError.e(1));
        } else if (!iff.d("queryTrends")) {
            iuniversalCallback.onResultHandler(30, null, HiHealthError.e(30));
            Log.w("HealthKitCommonApi", "Health Application need to be updated to support this API");
        } else {
            c(trendQuery, i, iuniversalCallback);
        }
    }

    private void c(TrendQuery trendQuery, int i, final IuniversalCallback iuniversalCallback) {
        IDataReadResultListener.Stub stub = new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HealthKitCommonApi.8
            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i2, int i3) {
                Log.i("HealthKitCommonApi", "queryTrends callback errorCode: " + i2 + ", resultType: " + i3);
                int c2 = HiHealthError.c(i2);
                if (list != null) {
                    iuniversalCallback.onResultHandler(c2, list, HiHealthError.e(c2));
                } else {
                    iuniversalCallback.onResultHandler(c2, new ArrayList(), HiHealthError.e(c2));
                }
            }
        };
        try {
            IHiHealthKit iHiHealthKit = this.b;
            if (iHiHealthKit != null) {
                iHiHealthKit.queryTrends(trendQuery, i, stub);
            } else {
                this.g.queryTrends(((OutOfBandContext) d).getOutOfBandData(), trendQuery, i, stub);
            }
        } catch (RemoteException unused) {
            Log.e("HealthKitCommonApi", "queryTrendsImpl RemoteException");
            iuniversalCallback.onResultHandler(4, new ArrayList(), HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HealthKitCommonApi", "queryTrendsImpl Exception");
            iuniversalCallback.onResultHandler(4, new ArrayList(), HiHealthError.e(4));
        }
    }

    static class Instance {
        private static final HealthKitCommonApi d = new HealthKitCommonApi();

        private Instance() {
        }
    }
}
