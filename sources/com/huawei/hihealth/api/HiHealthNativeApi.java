package com.huawei.hihealth.api;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hihealth.HiAccountInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiAuthorizationOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataDeleteProOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDataUpdateOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiHealthUnit;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.HiSubscribeTrigger;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.IAuthorizationListener;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataClientListener;
import com.huawei.hihealth.IDataHiDeviceInfoListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IDeleteListenerEx;
import com.huawei.hihealth.IHiHealth;
import com.huawei.hihealth.IMultiDataClientListener;
import com.huawei.hihealth.IRegisterClientListener;
import com.huawei.hihealth.ISubscribeListener;
import com.huawei.hihealth.ISupportedTypesListener;
import com.huawei.hihealth.IUnSubscribeListener;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiAuthorizationListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataHiDeviceInfoListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiDeleteListenerEx;
import com.huawei.hihealth.data.listener.HiMultiDataClientListener;
import com.huawei.hihealth.data.listener.HiRegisterClientListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiSupportedTypesListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.HiScopeUtil;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.HiBroadcastAction;
import health.compact.a.HiBroadcastManager;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class HiHealthNativeApi implements HiHealthApi, IAuthApi, IDeviceApi, IStoreApi, UserInfoApi, ServiceConnection {
    private static final Object c = new Object();
    private static volatile HiHealthNativeApi d;
    private volatile IHiHealth e;
    private final Context f;
    private volatile CountDownLatch j;
    private final ThreadPoolExecutor k;
    private final ThreadPoolExecutor l;
    private volatile CountDownLatch m;
    private final ThreadPoolExecutor q;
    private final ThreadPoolExecutor t;
    private final Object b = new Object();
    private volatile boolean g = false;
    private volatile boolean i = false;
    private boolean h = false;
    private LinkedBlockingQueue<Runnable> n = new LinkedBlockingQueue<>();
    private long o = 0;

    /* renamed from: a, reason: collision with root package name */
    private MyBroadcastReceiver f4056a = new MyBroadcastReceiver();

    private HiHealthNativeApi(Context context) {
        LogUtil.d("HiH_HiHealthNatApi", "HiHealthNativeApi create");
        this.f = context.getApplicationContext();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.t = threadPoolExecutor;
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.q = threadPoolExecutor2;
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.l = threadPoolExecutor3;
        ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, this.n);
        this.k = threadPoolExecutor4;
        threadPoolExecutor.allowCoreThreadTimeOut(false);
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        threadPoolExecutor3.allowCoreThreadTimeOut(true);
        threadPoolExecutor4.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.1
            @Override // java.lang.Runnable
            public void run() {
                HiHealthNativeApi.this.d();
                HiHealthNativeApi.this.o = Thread.currentThread().getId();
            }
        });
    }

    public static void e(Context context, LogApi logApi, LogApi logApi2) {
        LogUtil.c(logApi);
        ReleaseLogUtil.e(logApi2);
        HuaweiHealth.c(context.getApplicationContext());
    }

    public static HiHealthNativeApi a(Context context) {
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    if (context == null) {
                        throw new IllegalArgumentException("context == null");
                    }
                    d = new HiHealthNativeApi(context);
                }
            }
        }
        return d;
    }

    private static Intent bwh_(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null) {
            LogUtil.c("HiH_HiHealthNatApi", "createExplicitIntent() resolveInfo == null");
            return null;
        }
        if (queryIntentServices.size() != 1) {
            LogUtil.c("HiH_HiHealthNatApi", "createExplicitIntent() resolveInfo.size = ", Integer.valueOf(queryIntentServices.size()));
            return null;
        }
        LogUtil.d("HiH_HiHealthNatApi", "createExplicitIntent() resolveInfo size more than one");
        return intent;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        long currentTimeMillis = System.currentTimeMillis();
        ReleaseLogUtil.b("HiH_HiHealthNatApi", "onServiceConnected() name = ", componentName, " time is ", Long.valueOf(currentTimeMillis));
        this.e = IHiHealth.Stub.asInterface(iBinder);
        if (this.e == null) {
            ReleaseLogUtil.d("HiH_HiHealthNatApi", "onServiceConnected error !");
        }
        synchronized (this.b) {
            this.b.notifyAll();
        }
        e();
        LogUtil.d("HiH_HiHealthNatApi", "onServiceConnected() end name = ", componentName, " time is ", Long.valueOf(currentTimeMillis));
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        ReleaseLogUtil.b("HiH_HiHealthNatApi", "onServiceDisconnected() name = ", componentName);
        if (this.g && this.j != null) {
            this.j.countDown();
            LogUtil.d("HiH_HiHealthNatApi", "onServiceDisconnected() insert latch countDown");
        }
        if (this.i && this.m != null) {
            this.m.countDown();
            LogUtil.d("HiH_HiHealthNatApi", "onServiceDisconnected() realtime latch countDown");
        }
        this.e = null;
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IHiHealth d() {
        if (this.e != null) {
            return this.e;
        }
        LogApi d2 = LogUtil.d();
        if (d2 != null && !d2.isSystemInfoAuthorized(this.f)) {
            ReleaseLogUtil.d("HiH_HiHealthNatApi", "bindService() androidId unauthorized, return null service");
            return null;
        }
        synchronized (c) {
            if (this.e == null) {
                long currentTimeMillis = System.currentTimeMillis();
                ReleaseLogUtil.b("HiH_HiHealthNatApi", "bindService() time is ", Long.valueOf(currentTimeMillis));
                String b = HuaweiHealth.b();
                Intent intent = new Intent();
                intent.setClassName(b, "com.huawei.hihealthservice.HiHealthService");
                intent.setPackage(b);
                Intent bwh_ = bwh_(this.f, intent);
                if (bwh_ == null) {
                    LogUtil.c("HiH_HiHealthNatApi", "bindService() explicitIntent = null");
                    return this.e;
                }
                this.f.bindService(bwh_, this, 1);
                synchronized (this.b) {
                    try {
                    } catch (InterruptedException unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "bindService() InterruptedException");
                    }
                    if (this.e != null) {
                        LogUtil.d("HiH_HiHealthNatApi", "bindService() bind mApiAidl is not null = ", this.e);
                        return this.e;
                    }
                    this.b.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
                    LogUtil.d("HiH_HiHealthNatApi", "bindService() bind over mApiAidl is ", this.e, " end time is ", Long.valueOf(currentTimeMillis));
                }
            }
            return this.e;
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void initHiHealth(final HiAppInfo hiAppInfo) {
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.2
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        d2.initHiHealth(hiAppInfo);
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "initHiHealth Exception");
                        return;
                    }
                }
                LogUtil.d("HiH_HiHealthNatApi", "initHiHealth cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void unBindHiHealth() {
        ReleaseLogUtil.b("HiH_HiHealthNatApi", "unBindHiHealth() unbindService");
        synchronized (c) {
            if (this.e != null) {
                this.e = null;
                this.f.unbindService(this);
                ReleaseLogUtil.b("HiH_HiHealthNatApi", "unbindService success.");
            }
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IAuthApi
    public void requestAuthorization(final HiAuthorizationOption hiAuthorizationOption, final HiAuthorizationListener hiAuthorizationListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.3
            @Override // java.lang.Runnable
            public void run() {
                HiAuthorizationOption hiAuthorizationOption2;
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiAuthorizationListener hiAuthorizationListener2 = hiAuthorizationListener;
                    if (hiAuthorizationListener2 != null) {
                        hiAuthorizationListener2.onResult(1, null);
                        return;
                    }
                    return;
                }
                try {
                    LogUtil.d("HiH_HiHealthNatApi", "requestAuthorization start");
                    if (HiScopeUtil.e(HiHealthNativeApi.this.f) != null && (hiAuthorizationOption2 = hiAuthorizationOption) != null) {
                        hiAuthorizationOption2.setAppId(HiScopeUtil.e(HiHealthNativeApi.this.f));
                    }
                    d2.requestAuthorization(hiAuthorizationOption, new IAuthorizationListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.3.1
                        @Override // com.huawei.hihealth.IAuthorizationListener
                        public void onResult(int i, List list) throws RemoteException {
                            if (hiAuthorizationListener != null) {
                                hiAuthorizationListener.onResult(i, list);
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "requestAuthorization cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    LogUtil.e("HiH_HiHealthNatApi", "requestAuthorization Exception");
                    HiAuthorizationListener hiAuthorizationListener3 = hiAuthorizationListener;
                    if (hiAuthorizationListener3 != null) {
                        hiAuthorizationListener3.onResult(1, null);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchSupportedTypes(final HiHealthDataType.TypeSelector typeSelector, final HiSupportedTypesListener hiSupportedTypesListener) {
        if (hiSupportedTypesListener == null) {
            LogUtil.e("HiH_HiHealthNatApi", "fetchSupportedTypes listener null");
        } else {
            this.k.execute(new ReadTask("fetchSupportedTypes", typeSelector) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.4
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiSupportedTypesListener.onResult(null);
                        return;
                    }
                    try {
                        d2.fetchSupportedTypes(AnonymousClass64.d[typeSelector.ordinal()] != 1 ? 0 : 1, new ISupportedTypesListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.4.1
                            @Override // com.huawei.hihealth.ISupportedTypesListener
                            public void onResult(List list) throws RemoteException {
                                hiSupportedTypesListener.onResult(list);
                                long currentTimeMillis2 = System.currentTimeMillis();
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchSupportedTypes cost, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait queue time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time = ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchSupportedTypes Exception");
                        hiSupportedTypesListener.onResult(null);
                    }
                }
            });
        }
    }

    /* renamed from: com.huawei.hihealth.api.HiHealthNativeApi$64, reason: invalid class name */
    static /* synthetic */ class AnonymousClass64 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HiHealthDataType.TypeSelector.values().length];
            d = iArr;
            try {
                iArr[HiHealthDataType.TypeSelector.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.n.isEmpty()) {
            return;
        }
        Object[] array = this.n.toArray();
        Object[] objArr = new Object[6];
        objArr[0] = "taskCt=";
        objArr[1] = Integer.valueOf(array.length);
        objArr[2] = ", head tskinfo:";
        objArr[3] = array.length > 0 ? array[0] : null;
        objArr[4] = ", last tskinfo: ";
        objArr[5] = array.length > 1 ? array[array.length - 1] : null;
        ReleaseLogUtil.b("HiH_HiHealthNatApi", objArr);
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IAuthApi
    public void subscribeHiHealthData(final List<Integer> list, final HiSubscribeListener hiSubscribeListener) {
        this.k.execute(new ReadTask("subscribeHiHealthData", list) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.5
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiSubscribeListener hiSubscribeListener2 = hiSubscribeListener;
                    if (hiSubscribeListener2 != null) {
                        hiSubscribeListener2.onResult(null, null);
                        return;
                    }
                    return;
                }
                try {
                    d2.subscribeHiHealthData(list, new ISubscribeListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.5.1
                        @Override // com.huawei.hihealth.ISubscribeListener
                        public void onResult(List list2, List list3) throws RemoteException {
                            if (hiSubscribeListener != null) {
                                hiSubscribeListener.onResult(list2, list3);
                            }
                            long currentTimeMillis2 = System.currentTimeMillis();
                            ReleaseLogUtil.b("HiH_HiHealthNatApi", "subscribeHiHealthData,totalTm=", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ",waitTm=", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ",execTm=", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                        }

                        @Override // com.huawei.hihealth.ISubscribeListener
                        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) throws RemoteException {
                            if (hiSubscribeListener != null) {
                                hiSubscribeListener.onChange(i, hiHealthClient, str, hiHealthData, j);
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "subscribeHiHealthData typeList Exception");
                    HiSubscribeListener hiSubscribeListener3 = hiSubscribeListener;
                    if (hiSubscribeListener3 != null) {
                        hiSubscribeListener3.onResult(null, null);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void subscribeHiHealthData(final HiHealthClient hiHealthClient, final HiSubscribeListener hiSubscribeListener) {
        this.k.execute(new ReadTask("subscribeHiHealthData", hiHealthClient) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.6
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiSubscribeListener hiSubscribeListener2 = hiSubscribeListener;
                    if (hiSubscribeListener2 != null) {
                        hiSubscribeListener2.onResult(null, null);
                        return;
                    }
                    return;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    HiHealthClient hiHealthClient2 = hiHealthClient;
                    if (hiHealthClient2 != null) {
                        arrayList.add(hiHealthClient2);
                    }
                    d2.subscribeHiHealthData(arrayList, new ISubscribeListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.6.1
                        @Override // com.huawei.hihealth.ISubscribeListener
                        public void onResult(List list, List list2) throws RemoteException {
                            if (hiSubscribeListener != null) {
                                hiSubscribeListener.onResult(list, list2);
                            }
                            long currentTimeMillis2 = System.currentTimeMillis();
                            ReleaseLogUtil.b("HiH_HiHealthNatApi", "subscribeHiHealthData, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time = ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                        }

                        @Override // com.huawei.hihealth.ISubscribeListener
                        public void onChange(int i, HiHealthClient hiHealthClient3, String str, HiHealthData hiHealthData, long j) throws RemoteException {
                            LogUtil.d("HiH_HiHealthNatApi", "onChange -> dataType:", Integer.valueOf(i), ",changeKey:", str);
                            if (hiSubscribeListener != null) {
                                LogUtil.d("HiH_HiHealthNatApi", "onChange and listener not null");
                                hiSubscribeListener.onChange(i, hiHealthClient3, str, hiHealthData, j);
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "subscribeHiHealthData Exception");
                    HiSubscribeListener hiSubscribeListener3 = hiSubscribeListener;
                    if (hiSubscribeListener3 != null) {
                        hiSubscribeListener3.onResult(null, null);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void subscribeHiHealthData(final int i, final HiSubscribeListener hiSubscribeListener) {
        this.k.execute(new ReadTask("subscribeHiHealthData", Integer.valueOf(i)) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.7
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiSubscribeListener hiSubscribeListener2 = hiSubscribeListener;
                    if (hiSubscribeListener2 != null) {
                        hiSubscribeListener2.onResult(null, null);
                        return;
                    }
                    return;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(i));
                    d2.subscribeHiHealthData(arrayList, new ISubscribeListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.7.1
                        @Override // com.huawei.hihealth.ISubscribeListener
                        public void onResult(List list, List list2) throws RemoteException {
                            if (hiSubscribeListener != null) {
                                hiSubscribeListener.onResult(list, list2);
                            }
                            long currentTimeMillis2 = System.currentTimeMillis();
                            ReleaseLogUtil.b("HiH_HiHealthNatApi", "subscribeHiHealthData, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time = ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                        }

                        @Override // com.huawei.hihealth.ISubscribeListener
                        public void onChange(int i2, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) throws RemoteException {
                            if (hiSubscribeListener != null) {
                                hiSubscribeListener.onChange(i2, hiHealthClient, str, hiHealthData, j);
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "subscribeHiHealthData type Exception");
                    HiSubscribeListener hiSubscribeListener3 = hiSubscribeListener;
                    if (hiSubscribeListener3 != null) {
                        hiSubscribeListener3.onResult(null, null);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public boolean addExternalSubscribeTrigger(final int i, HiHealthDataType.TriggerParam triggerParam, final HiSubscribeTrigger hiSubscribeTrigger) {
        if (triggerParam == null || hiSubscribeTrigger == null) {
            LogUtil.c("HiH_HiHealthNatApi", "addExternalSubscribeTrigger() parameter error");
            return false;
        }
        this.k.execute(new ReadTask("addExternalSubscribeTrigger", Integer.valueOf(i)) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.8
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        d2.addExternalSubscribeTrigger(i, 0, hiSubscribeTrigger);
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "addExternalSubscribeTrigger Exception");
                        return;
                    }
                }
                long currentTimeMillis2 = System.currentTimeMillis();
                LogUtil.d("HiH_HiHealthNatApi", "addExternalSubscribeTrigger, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time = ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
            }
        });
        return false;
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IAuthApi
    public void unSubscribeHiHealthData(final List<Integer> list, final HiUnSubscribeListener hiUnSubscribeListener) {
        this.k.execute(new ReadTask("unSubscribeHiHealthData", list) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.9
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiUnSubscribeListener hiUnSubscribeListener2 = hiUnSubscribeListener;
                    if (hiUnSubscribeListener2 != null) {
                        hiUnSubscribeListener2.onResult(false);
                        return;
                    }
                    return;
                }
                try {
                    d2.unSubscribeHiHealthData(list, new IUnSubscribeListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.9.1
                        @Override // com.huawei.hihealth.IUnSubscribeListener
                        public void onResult(boolean z) throws RemoteException {
                            if (hiUnSubscribeListener != null) {
                                hiUnSubscribeListener.onResult(z);
                            }
                            long currentTimeMillis2 = System.currentTimeMillis();
                            LogUtil.d("HiH_HiHealthNatApi", "unSubscribeHiHealthData result is ", Boolean.valueOf(z), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time = ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "unSubscribeHiHealthData Exception");
                    HiUnSubscribeListener hiUnSubscribeListener3 = hiUnSubscribeListener;
                    if (hiUnSubscribeListener3 != null) {
                        hiUnSubscribeListener3.onResult(false);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IStoreApi
    public void aggregateHiHealthData(final HiAggregateOption hiAggregateOption, final HiAggregateListener hiAggregateListener) {
        this.k.execute(new ReadTask("aggregateHiHealthData", hiAggregateOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.10
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiAggregateListener hiAggregateListener2 = hiAggregateListener;
                    if (hiAggregateListener2 != null) {
                        hiAggregateListener2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final ArrayList arrayList = new ArrayList();
                    d2.aggregateHiHealthData(hiAggregateOption, new IAggregateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.10.1
                        @Override // com.huawei.hihealth.IAggregateListener
                        public void onResult(List list, int i, int i2) throws RemoteException {
                            if (hiAggregateListener != null && HiDivideUtil.c(list, i2, arrayList)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiAggregateListener.onResult(arrayList.isEmpty() ? null : arrayList, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "aggregateHiHealthData errorCode=", Integer.valueOf(i), ",", hiAggregateOption, ",tlTm=", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ",waitTm=", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executedTm=", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ",onResultTm=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception unused) {
                    LogUtil.e("HiH_HiHealthNatApi", "aggregateHiHealthData Exception");
                    HiAggregateListener hiAggregateListener3 = hiAggregateListener;
                    if (hiAggregateListener3 != null) {
                        hiAggregateListener3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void insertFitnessData(final String str) {
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthNativeApi.this.c(str);
            }
        });
    }

    /* synthetic */ void c(String str) {
        IHiHealth d2 = d();
        if (d2 == null) {
            LogUtil.c("HiH_HiHealthNatApi", "insertFitnessData aidl is null");
            return;
        }
        try {
            d2.insertFitnessData(str);
        } catch (RemoteException unused) {
            LogUtil.e("HiH_HiHealthNatApi", "insertFitnessData Exception");
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void aggregateHiHealthDataEx(final List<HiAggregateOption> list, final HiAggregateListenerEx hiAggregateListenerEx) {
        this.k.execute(new ReadTask("aggregateHiHealthDataEx", list) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.11
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiAggregateListenerEx hiAggregateListenerEx2 = hiAggregateListenerEx;
                    if (hiAggregateListenerEx2 != null) {
                        hiAggregateListenerEx2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final SparseArray sparseArray = new SparseArray();
                    final ArrayList arrayList = new ArrayList();
                    d2.aggregateHiHealthDataEx(list, new IAggregateListenerEx.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.11.1
                        @Override // com.huawei.hihealth.IAggregateListenerEx
                        public void onResult(List list2, int i, int i2) throws RemoteException {
                            if (hiAggregateListenerEx != null && HiDivideUtil.bwN_(list2, i, i2, arrayList, sparseArray)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiAggregateListenerEx.onResult(sparseArray, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "aggregateHiHealthDataEx errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "aggregateHiHealthDataEx Exception");
                    HiAggregateListenerEx hiAggregateListenerEx3 = hiAggregateListenerEx;
                    if (hiAggregateListenerEx3 != null) {
                        hiAggregateListenerEx3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void aggregateHiHealthDataProEx(final List<HiDataAggregateProOption> list, final HiAggregateListenerEx hiAggregateListenerEx) {
        this.k.execute(new ReadTask("aggregateHiHealthDataProEx", list) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.12
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiAggregateListenerEx hiAggregateListenerEx2 = hiAggregateListenerEx;
                    if (hiAggregateListenerEx2 != null) {
                        hiAggregateListenerEx2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final SparseArray sparseArray = new SparseArray();
                    final ArrayList arrayList = new ArrayList();
                    d2.aggregateHiHealthDataProEx(list, new IAggregateListenerEx.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.12.1
                        @Override // com.huawei.hihealth.IAggregateListenerEx
                        public void onResult(List list2, int i, int i2) throws RemoteException {
                            if (hiAggregateListenerEx != null && HiDivideUtil.bwN_(list2, i, i2, arrayList, sparseArray)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiAggregateListenerEx.onResult(sparseArray, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "aggregateHiHealthDataProEx errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "aggregateHiHealthDataEx Exception");
                    HiAggregateListenerEx hiAggregateListenerEx3 = hiAggregateListenerEx;
                    if (hiAggregateListenerEx3 != null) {
                        hiAggregateListenerEx3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IStoreApi
    public void insertHiHealthData(HiDataInsertOption hiDataInsertOption, HiDataOperateListener hiDataOperateListener) {
        ReleaseLogUtil.b("HiH_HiHealthNatApi", "insertHiHealthData enter!");
        d(hiDataInsertOption, hiDataOperateListener, false);
    }

    private void d(final HiDataInsertOption hiDataInsertOption, final HiDataOperateListener hiDataOperateListener, final boolean z) {
        if (!z) {
            HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        }
        Runnable runnable = new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.13
            /* JADX WARN: Can't wrap try/catch for region: R(4:(3:16|17|18)|(4:19|20|(2:22|23)(2:25|26)|24)|13|14) */
            /* JADX WARN: Code restructure failed: missing block: B:57:0x00bf, code lost:
            
                r0 = th;
             */
            /* JADX WARN: Not initialized variable reg: 14, insn: 0x010b: MOVE (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r14 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:60:0x010b */
            /* JADX WARN: Removed duplicated region for block: B:40:0x012c  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 311
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealth.api.HiHealthNativeApi.AnonymousClass13.run():void");
            }
        };
        if (z) {
            this.q.execute(runnable);
        } else {
            this.t.execute(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiDataInsertOption hiDataInsertOption, IHiHealth iHiHealth, final int[] iArr, final Object[] objArr, final boolean z) throws RemoteException {
        if (z) {
            this.m = new CountDownLatch(1);
            this.i = true;
        } else {
            this.j = new CountDownLatch(1);
            this.g = true;
        }
        c(hiDataInsertOption, iHiHealth, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.14
            @Override // com.huawei.hihealth.IDataOperateListener
            public void onResult(int i, List list) throws RemoteException {
                LogUtil.d("HiH_HiHealthNatApi", "insertHiHealthDataRun onResult errorCode:", Integer.valueOf(i));
                if (i == 0) {
                    iArr[0] = 0;
                    objArr[0] = true;
                } else if (list != null && !list.isEmpty()) {
                    iArr[0] = i;
                    objArr[0] = list.get(0);
                } else {
                    LogUtil.d("HiH_HiHealthNatApi", "insertHiHealthDataRun onResult errorCode:", Integer.valueOf(i), " datas:", list);
                }
                if (z) {
                    if (HiHealthNativeApi.this.m != null) {
                        HiHealthNativeApi.this.m.countDown();
                    }
                } else if (HiHealthNativeApi.this.j != null) {
                    HiHealthNativeApi.this.j.countDown();
                }
            }
        }, z);
    }

    private void c(HiDataInsertOption hiDataInsertOption, IHiHealth iHiHealth, IDataOperateListener.Stub stub, boolean z) throws RemoteException {
        if (z) {
            iHiHealth.insertRealTimeHiHealthData(hiDataInsertOption, stub);
            try {
                this.m.await();
            } catch (InterruptedException unused) {
                ReleaseLogUtil.c("HiH_HiHealthNatApi", "insertHiHealthDataApiAidl InterruptedException");
            }
            this.i = false;
            this.m = null;
            return;
        }
        iHiHealth.insertHiHealthData(hiDataInsertOption, stub);
        try {
            this.j.await();
        } catch (InterruptedException unused2) {
            ReleaseLogUtil.c("HiH_HiHealthNatApi", "insertHiHealthDataApiAidl InterruptedException");
        }
        this.g = false;
        this.j = null;
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void insertRealTimeHiHealthData(HiDataInsertOption hiDataInsertOption, HiDataOperateListener hiDataOperateListener) {
        d(hiDataInsertOption, hiDataOperateListener, true);
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void deleteHiHealthData(final HiDataDeleteOption hiDataDeleteOption, final HiDataOperateListener hiDataOperateListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.15
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataOperateListener hiDataOperateListener2 = hiDataOperateListener;
                    if (hiDataOperateListener2 != null) {
                        hiDataOperateListener2.onResult(1, "deleteHiHealthData bindservice exeception");
                        return;
                    }
                    return;
                }
                try {
                    d2.deleteHiHealthData(hiDataDeleteOption, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.15.1
                        @Override // com.huawei.hihealth.IDataOperateListener
                        public void onResult(int i, List list) throws RemoteException {
                            if (list == null || list.isEmpty()) {
                                ReleaseLogUtil.d("HiH_HiHealthNatApi", "deleteHiHealthData onResult datas is null or empty");
                                if (hiDataOperateListener != null) {
                                    hiDataOperateListener.onResult(1, "result is null or empty delete data fail " + hiDataDeleteOption);
                                    return;
                                }
                                return;
                            }
                            Object obj = list.get(0);
                            if (hiDataOperateListener != null) {
                                hiDataOperateListener.onResult(i, obj);
                            }
                            ReleaseLogUtil.b("HiH_HiHealthNatApi", "deleteHiHealthData cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "deleteHiHealthData Exception");
                    HiDataOperateListener hiDataOperateListener3 = hiDataOperateListener;
                    if (hiDataOperateListener3 != null) {
                        hiDataOperateListener3.onResult(1, "delete data fail" + hiDataDeleteOption);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void deleteAllKitHealthData(final int i, final HiDataOperateListener hiDataOperateListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.16
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataOperateListener hiDataOperateListener2 = hiDataOperateListener;
                    if (hiDataOperateListener2 != null) {
                        hiDataOperateListener2.onResult(1, "deleteAllKitHealthData bindservice exeception");
                        return;
                    }
                    return;
                }
                try {
                    d2.deleteAllKitHealthData(i, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.16.1
                        @Override // com.huawei.hihealth.IDataOperateListener
                        public void onResult(int i2, List list) throws RemoteException {
                            if (list == null || list.isEmpty()) {
                                LogUtil.d("HiH_HiHealthNatApi", "deleteAllKitHealthData onResult datas is null or empty");
                                if (hiDataOperateListener != null) {
                                    hiDataOperateListener.onResult(1, "result is null or empty delete data fail " + i);
                                    return;
                                }
                                return;
                            }
                            Object obj = list.get(0);
                            if (hiDataOperateListener != null) {
                                hiDataOperateListener.onResult(i2, obj);
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "deleteAllKitHealthData cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception e) {
                    LogUtil.e("HiH_HiHealthNatApi", "deleteAllKitHealthData Exception :", e.getClass().getSimpleName());
                    HiDataOperateListener hiDataOperateListener3 = hiDataOperateListener;
                    if (hiDataOperateListener3 != null) {
                        hiDataOperateListener3.onResult(1, "delete data fail" + i);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IStoreApi
    public void readHiHealthData(final HiDataReadOption hiDataReadOption, final HiDataReadResultListener hiDataReadResultListener) {
        this.k.execute(new ReadTask("readHiHealthData", hiDataReadOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.17
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataReadResultListener hiDataReadResultListener2 = hiDataReadResultListener;
                    if (hiDataReadResultListener2 != null) {
                        hiDataReadResultListener2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final long currentTimeMillis = System.currentTimeMillis();
                    final SparseArray sparseArray = new SparseArray();
                    final ArrayList arrayList = new ArrayList();
                    d2.readHiHealthData(hiDataReadOption, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.17.1
                        @Override // com.huawei.hihealth.IDataReadResultListener
                        public void onResult(List list, int i, int i2) throws RemoteException {
                            if (hiDataReadResultListener == null) {
                                return;
                            }
                            if (HiDivideUtil.bwO_(HiHealthNativeApi.this.f, list, new int[]{i, i2}, arrayList, sparseArray)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiDataReadResultListener.onResult(sparseArray, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "readHiHealthData option = ", hiDataReadOption, "errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception e) {
                    LogApi d3 = LogUtil.d();
                    if (d3 != null) {
                        LogUtil.e("HiH_HiHealthNatApi", "readHiHealthData Exception", d3.logAnonymousException(e));
                    }
                    HiDataReadResultListener hiDataReadResultListener3 = hiDataReadResultListener;
                    if (hiDataReadResultListener3 != null) {
                        hiDataReadResultListener3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void readHiHealthData(final int i, final HiDataReadOption hiDataReadOption, final HiDataReadResultListener hiDataReadResultListener) {
        this.k.execute(new ReadTask("readHiHealthData", hiDataReadOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.18
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataReadResultListener hiDataReadResultListener2 = hiDataReadResultListener;
                    if (hiDataReadResultListener2 != null) {
                        hiDataReadResultListener2.onResultIntent(i, null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final SparseArray sparseArray = new SparseArray();
                    final ArrayList arrayList = new ArrayList();
                    d2.readHiHealthData(hiDataReadOption, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.18.1
                        @Override // com.huawei.hihealth.IDataReadResultListener
                        public void onResult(List list, int i2, int i3) throws RemoteException {
                            if (hiDataReadResultListener == null) {
                                return;
                            }
                            if (HiDivideUtil.bwO_(HiHealthNativeApi.this.f, list, new int[]{i2, i3}, arrayList, sparseArray)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiDataReadResultListener.onResultIntent(i, sparseArray, i2, i3);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "readHiHealthData errorCode is ", Integer.valueOf(i2), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "readHiHealthData readIntent Exception,readIntent = ", Integer.valueOf(i));
                    HiDataReadResultListener hiDataReadResultListener3 = hiDataReadResultListener;
                    if (hiDataReadResultListener3 != null) {
                        hiDataReadResultListener3.onResultIntent(i, null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void updateHiHealthData(final HiDataUpdateOption hiDataUpdateOption, final HiDataOperateListener hiDataOperateListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.19
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataOperateListener hiDataOperateListener2 = hiDataOperateListener;
                    if (hiDataOperateListener2 != null) {
                        hiDataOperateListener2.onResult(0, false);
                        return;
                    }
                    return;
                }
                try {
                    d2.updateHiHealthData(hiDataUpdateOption, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.19.1
                        @Override // com.huawei.hihealth.IDataOperateListener
                        public void onResult(int i, List list) throws RemoteException {
                            if (HiCommonUtil.d(list)) {
                                LogUtil.c("HiH_HiHealthNatApi", "updateHiHealthData onResult datas is null or empty");
                                if (hiDataOperateListener != null) {
                                    hiDataOperateListener.onResult(1, "result is null or empty update data fail " + hiDataUpdateOption);
                                    return;
                                }
                                return;
                            }
                            if (hiDataOperateListener != null) {
                                hiDataOperateListener.onResult(i, list.get(0));
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "updateHiHealthData cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    LogUtil.e("HiH_HiHealthNatApi", "updateHiHealthData Exception");
                    HiDataOperateListener hiDataOperateListener3 = hiDataOperateListener;
                    if (hiDataOperateListener3 != null) {
                        hiDataOperateListener3.onResult(0, false);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public int addHiHealthDataCustomType(final String str) {
        if (str == null) {
            return 0;
        }
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.20
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        d2.addHiHealthDataCustomType(str);
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "addHiHealthDataCustomType Exception");
                    }
                }
            }
        });
        return 0;
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public int fetchHiHealthDataCustomType(final String str) {
        if (str == null) {
            return 0;
        }
        this.k.execute(new ReadTask("fetchHiHealthDataCustomType", str) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.21
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        d2.fetchHiHealthDataCustomType(str);
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "fetchHiHealthDataCustomType Exception");
                    }
                }
            }
        });
        return 0;
    }

    private void d(HiDeviceInfo hiDeviceInfo) {
        if (hiDeviceInfo == null || TextUtils.isEmpty(hiDeviceInfo.getDeviceName()) || hiDeviceInfo.getDeviceType() != 1 || hiDeviceInfo.getDeviceName().indexOf("HUAWEI WATCH GT") == -1) {
            return;
        }
        if (HiCommonUtil.c(this.f)) {
            LogUtil.d("HiH_HiHealthNatApi", "checkWatchGtDeviceType() for a release problem");
        } else {
            LogUtil.e("HiH_HiHealthNatApi", "checkWatchGtDeviceType() for a other problem");
            throw new IllegalArgumentException("checkWatchGtDeviceType() for a other problem");
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IDeviceApi
    public void registerDataClient(final HiDeviceInfo hiDeviceInfo, final List<Integer> list, final HiRegisterClientListener hiRegisterClientListener) {
        if (LogUtil.a() && "-1".equals(hiDeviceInfo.getDeviceUniqueCode()) && hiDeviceInfo.getDeviceType() == 10001) {
            throw new IllegalArgumentException("deviceInfo error");
        }
        if (LogUtil.a() && !"-1".equals(hiDeviceInfo.getDeviceUniqueCode()) && hiDeviceInfo.getDeviceType() == 1) {
            throw new IllegalArgumentException("deviceInfo error");
        }
        d(hiDeviceInfo);
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.22
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiRegisterClientListener hiRegisterClientListener2 = hiRegisterClientListener;
                    if (hiRegisterClientListener2 != null) {
                        hiRegisterClientListener2.onResult(null);
                        return;
                    }
                    return;
                }
                try {
                    d2.registerDataClient(hiDeviceInfo, list, new IRegisterClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.22.1
                        @Override // com.huawei.hihealth.IRegisterClientListener
                        public void onResult(HiHealthClient hiHealthClient) throws RemoteException {
                            if (hiRegisterClientListener != null) {
                                hiRegisterClientListener.onResult(hiHealthClient);
                            }
                            ReleaseLogUtil.b("HiH_HiHealthNatApi", "registerDataClient cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "registerDataClient Exception");
                    HiRegisterClientListener hiRegisterClientListener3 = hiRegisterClientListener;
                    if (hiRegisterClientListener3 != null) {
                        hiRegisterClientListener3.onResult(null);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IDeviceApi
    public void registerDataClient(final HiDeviceInfo hiDeviceInfo, final HiUserInfo hiUserInfo, final List<Integer> list, final HiRegisterClientListener hiRegisterClientListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.23
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiRegisterClientListener hiRegisterClientListener2 = hiRegisterClientListener;
                    if (hiRegisterClientListener2 != null) {
                        hiRegisterClientListener2.onResult(null);
                        return;
                    }
                    return;
                }
                try {
                    d2.registerDataClientWithUserInfo(hiDeviceInfo, hiUserInfo, list, new IRegisterClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.23.1
                        @Override // com.huawei.hihealth.IRegisterClientListener
                        public void onResult(HiHealthClient hiHealthClient) throws RemoteException {
                            if (hiRegisterClientListener != null) {
                                hiRegisterClientListener.onResult(hiHealthClient);
                            }
                            ReleaseLogUtil.b("HiH_HiHealthNatApi", "registerDataClient cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "registerDataClientWithUserInfo Exception");
                    HiRegisterClientListener hiRegisterClientListener3 = hiRegisterClientListener;
                    if (hiRegisterClientListener3 != null) {
                        hiRegisterClientListener3.onResult(null);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchBuildInDataClient(final HiDataClientListener hiDataClientListener) {
        if (hiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchBuildInDataClient listener null");
        } else {
            this.k.execute(new ReadTask("fetchBuildInDataClient", hiDataClientListener) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.24
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataClientListener.onResult(null);
                        return;
                    }
                    try {
                        d2.fetchBuildInDataClient(new IDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.24.1
                            @Override // com.huawei.hihealth.IDataClientListener
                            public void onResult(List list) throws RemoteException {
                                hiDataClientListener.onResult(list);
                                long currentTimeMillis2 = System.currentTimeMillis();
                                LogUtil.d("HiH_HiHealthNatApi", "fetchBuildInDataClient, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                            }
                        });
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "fetchBuildInDataClient Exception");
                        hiDataClientListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchManualDataClient(final HiDataClientListener hiDataClientListener) {
        if (hiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchManualDataClient listener null");
        } else {
            this.k.execute(new ReadTask("fetchManualDataClient", hiDataClientListener) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.25
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataClientListener.onResult(null);
                        return;
                    }
                    try {
                        d2.fetchManualDataClient(new IDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.25.1
                            @Override // com.huawei.hihealth.IDataClientListener
                            public void onResult(List list) throws RemoteException {
                                hiDataClientListener.onResult(list);
                                long currentTimeMillis2 = System.currentTimeMillis();
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchManualDataClient, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchManualDataClient Exception");
                        hiDataClientListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchPhoneDataClient(final HiDataClientListener hiDataClientListener) {
        if (hiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchPhoneDataClient listener null");
        } else {
            HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
            this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.26
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataClientListener.onResult(null);
                        return;
                    }
                    try {
                        d2.fetchPhoneDataClient(new IDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.26.1
                            @Override // com.huawei.hihealth.IDataClientListener
                            public void onResult(List list) throws RemoteException {
                                hiDataClientListener.onResult(list);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchPhoneDataClient cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchPhoneDataClient Exception");
                        hiDataClientListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchRegisteredDataClient(final int i, final HiDataClientListener hiDataClientListener) {
        if (hiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchRegisteredDataClient listener null");
        } else {
            this.k.execute(new ReadTask("fetchRegisteredDataClient", Integer.valueOf(i)) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.27
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataClientListener.onResult(null);
                        return;
                    }
                    try {
                        d2.fetchRegisteredDataClient(i, new IDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.27.1
                            @Override // com.huawei.hihealth.IDataClientListener
                            public void onResult(List list) throws RemoteException {
                                hiDataClientListener.onResult(list);
                                long currentTimeMillis2 = System.currentTimeMillis();
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchRegisteredDataClient, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchRegisteredDataClient Exception");
                        hiDataClientListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IDeviceApi
    public void fetchDataClientByUniqueId(final int i, final String str, final HiDataClientListener hiDataClientListener) {
        if (hiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchDataClientByUniqueID listener null");
        } else {
            this.k.execute(new ReadTask("fetchDataClientByUniqueID", Integer.valueOf(i)) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.28
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataClientListener.onResult(null);
                        return;
                    }
                    try {
                        d2.fetchDataClientByUniqueID(i, str, new IDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.28.1
                            @Override // com.huawei.hihealth.IDataClientListener
                            public void onResult(List list) throws RemoteException {
                                hiDataClientListener.onResult(list);
                                long currentTimeMillis2 = System.currentTimeMillis();
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchDataClientByUniqueID, totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchDataClientByUniqueID Exception");
                        hiDataClientListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public HiHealthUnit fetchPreferUnit(final int i) {
        this.k.execute(new ReadTask("fetchPreferUnit", Integer.valueOf(i)) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.29
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        d2.fetchPreferUnit(i);
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "fetchPreferUnit Exception");
                    }
                }
            }
        });
        return null;
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void setPreferUnit(final int i, final HiHealthUnit hiHealthUnit) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.30
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        d2.setPreferUnit(i, hiHealthUnit);
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "setPreferUnit Exception");
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.IDeviceApi
    public void fetchDataSourceByType(final int i, final HiTimeInterval hiTimeInterval, final HiDataClientListener hiDataClientListener) {
        if (hiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchDataSourceByType listener null");
        } else {
            this.k.execute(new ReadTask("fetchDataSourceByType", Integer.valueOf(i)) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.31
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataClientListener.onResult(null);
                        return;
                    }
                    try {
                        d2.fetchDataSourceByType(i, hiTimeInterval, new IDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.31.1
                            @Override // com.huawei.hihealth.IDataClientListener
                            public void onResult(List list) throws RemoteException {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiDataClientListener.onResult(list);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchDataSourceByType type = ", Integer.valueOf(i), ", timeInterval = ", hiTimeInterval, ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchDataSourceByType Exception");
                        hiDataClientListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchDataSourceByTypes(final List<Integer> list, final HiTimeInterval hiTimeInterval, final boolean z, final HiMultiDataClientListener hiMultiDataClientListener) {
        if (hiMultiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchDataSourceByTypes listener null");
        } else {
            this.k.execute(new ReadTask("fetchDataSourceByTypes", list) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.32
                private void e() {
                    if (z) {
                        hiMultiDataClientListener.onMergeTypeResult(null);
                    } else {
                        hiMultiDataClientListener.onMultiTypeResult(null);
                    }
                }

                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        e();
                        return;
                    }
                    try {
                        d2.fetchDataSourceByTypes(list, hiTimeInterval, z, new IMultiDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.32.1
                            @Override // com.huawei.hihealth.IMultiDataClientListener
                            public void onMultiTypeResult(Map map) throws RemoteException {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiMultiDataClientListener.onMultiTypeResult(map);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchDataSourceByTypes type = ", HiJsonUtil.e(list), ", timeInterval = ", hiTimeInterval, ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()));
                            }

                            @Override // com.huawei.hihealth.IMultiDataClientListener
                            public void onMergeTypeResult(List list2) throws RemoteException {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiMultiDataClientListener.onMergeTypeResult(list2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchDataSourceByTypes type = ", HiJsonUtil.e(list), ", timeInterval = ", hiTimeInterval, ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchDataSourceByTypes Exception");
                        e();
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void setUserData(final HiUserInfo hiUserInfo, final HiCommonListener hiCommonListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.33
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiCommonListener hiCommonListener2 = hiCommonListener;
                    if (hiCommonListener2 != null) {
                        hiCommonListener2.onFailure(1, "setUserData bindService exception");
                        return;
                    }
                    return;
                }
                try {
                    d2.setUserData(hiUserInfo, new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.33.1
                        @Override // com.huawei.hihealth.ICommonListener
                        public void onSuccess(int i, List list) throws RemoteException {
                            if (HiCommonUtil.d(list)) {
                                LogUtil.c("HiH_HiHealthNatApi", "setUserData onSuccess datas is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "setUserData onSuccess datas empty");
                                    return;
                                }
                                return;
                            }
                            Object obj = list.get(0);
                            if (hiCommonListener != null) {
                                hiCommonListener.onSuccess(i, obj);
                            }
                        }

                        @Override // com.huawei.hihealth.ICommonListener
                        public void onFailure(int i, List list) throws RemoteException {
                            if (HiCommonUtil.d(list)) {
                                LogUtil.c("HiH_HiHealthNatApi", "setUserData onFailure errMsg is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "setUserData onFailure errMsg empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onFailure(i, list.get(0));
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "setUserData fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errMsg = ", list.get(0));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "setUserData Exception");
                    HiCommonListener hiCommonListener3 = hiCommonListener;
                    if (hiCommonListener3 != null) {
                        hiCommonListener3.onFailure(1, "setUserData Exception");
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi, com.huawei.hihealth.api.UserInfoApi
    public void fetchUserData(final HiCommonListener hiCommonListener) {
        if (hiCommonListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchUserData listener null");
        } else {
            this.k.execute(new ReadTask("fetchUserData", hiCommonListener) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.34
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiCommonListener.onFailure(1, "fetchUserData bindService exception");
                        return;
                    }
                    try {
                        d2.fetchUserData(new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.34.1
                            @Override // com.huawei.hihealth.ICommonListener
                            public void onSuccess(int i, List list) throws RemoteException {
                                hiCommonListener.onSuccess(i, list);
                            }

                            @Override // com.huawei.hihealth.ICommonListener
                            public void onFailure(int i, List list) throws RemoteException {
                                if (HiCommonUtil.d(list)) {
                                    ReleaseLogUtil.d("HiH_HiHealthNatApi", "fetchUserData onFailure errMsg is null or empty");
                                    if (hiCommonListener != null) {
                                        hiCommonListener.onFailure(1, "fetchUserData onFailure errMsg empty");
                                        return;
                                    }
                                    return;
                                }
                                hiCommonListener.onFailure(i, list.get(0));
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchUserData fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errMsg = ", list.get(0));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchUserData Exception");
                        hiCommonListener.onFailure(1, "fetchUserData Exception");
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void synCloud(final HiSyncOption hiSyncOption, final HiCommonListener hiCommonListener) {
        ReleaseLogUtil.b("HiH_HiHealthNatApi", "synCloud enter", hiSyncOption);
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        hiSyncOption.setStackTrace(c());
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.35
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiCommonListener hiCommonListener2 = hiCommonListener;
                    if (hiCommonListener2 != null) {
                        hiCommonListener2.onFailure(1, "synCloud bindService exception");
                        return;
                    }
                    return;
                }
                try {
                    d2.synCloud(hiSyncOption, null);
                    ReleaseLogUtil.b("HiH_HiHealthNatApi", "synCloud cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "synCloud Exception");
                    HiCommonListener hiCommonListener3 = hiCommonListener;
                    if (hiCommonListener3 != null) {
                        hiCommonListener3.onFailure(1, "synCloud Exception");
                    }
                }
            }
        });
    }

    private String c() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            sb.append(" at ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void checkDataStatus(final List<Integer> list, final HiCommonListener hiCommonListener) {
        this.k.execute(new ReadTask("checkDataStatus", list) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.36
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiCommonListener hiCommonListener2 = hiCommonListener;
                    if (hiCommonListener2 != null) {
                        hiCommonListener2.onFailure(1, "checkDataStatus bindService exception");
                        return;
                    }
                    return;
                }
                try {
                    d2.checkDataStatus(list, new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.36.1
                        @Override // com.huawei.hihealth.ICommonListener
                        public void onSuccess(int i, List list2) throws RemoteException {
                            if (HiCommonUtil.d(list2)) {
                                LogUtil.c("HiH_HiHealthNatApi", "checkDataStatus onSuccess data is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "checkDataStatus onSuccess data empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onSuccess(i, list2.get(0));
                            }
                        }

                        @Override // com.huawei.hihealth.ICommonListener
                        public void onFailure(int i, List list2) throws RemoteException {
                            if (HiCommonUtil.d(list2)) {
                                LogUtil.c("HiH_HiHealthNatApi", "checkDataStatus onFailure errMsg is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "checkDataStatus onFailure errMsg empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onFailure(i, list2.get(0));
                            }
                        }
                    });
                } catch (Exception unused) {
                    LogUtil.e("HiH_HiHealthNatApi", "checkDataStatus Exception");
                    HiCommonListener hiCommonListener3 = hiCommonListener;
                    if (hiCommonListener3 != null) {
                        hiCommonListener3.onFailure(1, "checkDataStatus exception");
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void synCloudCancel() {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.37
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        d2.synCloudCancel();
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "synCloudCancel Exception");
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void hiLogin(final HiAccountInfo hiAccountInfo, final HiCommonListener hiCommonListener) {
        this.l.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.38
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiCommonListener hiCommonListener2 = hiCommonListener;
                    if (hiCommonListener2 != null) {
                        hiCommonListener2.onFailure(1, "hiLogin bindService Exception");
                        return;
                    }
                    return;
                }
                try {
                    LogUtil.d("HiH_HiHealthNatApi", "hiLogin() start");
                    d2.hiLogin(hiAccountInfo, new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.38.1
                        @Override // com.huawei.hihealth.ICommonListener
                        public void onSuccess(int i, List list) throws RemoteException {
                            if (HiCommonUtil.d(list)) {
                                LogUtil.c("HiH_HiHealthNatApi", "hiLogin onSuccess datas is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "hiLogin onSuccess datas empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onSuccess(i, list.get(0));
                            }
                        }

                        @Override // com.huawei.hihealth.ICommonListener
                        public void onFailure(int i, List list) throws RemoteException {
                            if (HiCommonUtil.d(list)) {
                                LogUtil.c("HiH_HiHealthNatApi", "hiLogin onFailure errMsg is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "hiLogin onFailure errMsg empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onFailure(i, list.get(0));
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "hiLogin fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errMsg = ", list.get(0));
                        }
                    });
                } catch (Exception unused) {
                    LogUtil.e("HiH_HiHealthNatApi", "hiLogin Exception");
                    HiCommonListener hiCommonListener3 = hiCommonListener;
                    if (hiCommonListener3 != null) {
                        hiCommonListener3.onFailure(1, "hiLogin Exception");
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void hiLogout(final HiCommonListener hiCommonListener) {
        this.l.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.39
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiCommonListener hiCommonListener2 = hiCommonListener;
                    if (hiCommonListener2 != null) {
                        hiCommonListener2.onFailure(1, "hiLogout bindService Exception");
                        return;
                    }
                    return;
                }
                try {
                    LogUtil.d("HiH_HiHealthNatApi", "hiLogout() start");
                    d2.hiLogout(new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.39.1
                        @Override // com.huawei.hihealth.ICommonListener
                        public void onSuccess(int i, List list) throws RemoteException {
                            if (HiCommonUtil.d(list)) {
                                LogUtil.c("HiH_HiHealthNatApi", "hiLogout onSuccess data is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "hiLogout onSuccess data empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onSuccess(i, list.get(0));
                            }
                        }

                        @Override // com.huawei.hihealth.ICommonListener
                        public void onFailure(int i, List list) throws RemoteException {
                            if (HiCommonUtil.d(list)) {
                                LogUtil.c("HiH_HiHealthNatApi", "hiLogout onFailure errMsg is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "hiLogout onFailure errMsg empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onFailure(i, list.get(0));
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "hiLogout fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errMsg = ", list.get(0));
                        }
                    });
                } catch (Exception unused) {
                    LogUtil.e("HiH_HiHealthNatApi", "hiLogout Exception");
                    HiCommonListener hiCommonListener3 = hiCommonListener;
                    if (hiCommonListener3 != null) {
                        hiCommonListener3.onFailure(1, "hiLogout Exception");
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchAccountInfo(final HiCommonListener hiCommonListener) {
        if (hiCommonListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchAccountInfo listener null");
        } else {
            this.k.execute(new ReadTask("fetchAccountInfo", hiCommonListener) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.40
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiCommonListener.onFailure(1, "fetchAccountInfo bind service Exception");
                        return;
                    }
                    try {
                        d2.fetchAccountInfo(new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.40.1
                            @Override // com.huawei.hihealth.ICommonListener
                            public void onSuccess(int i, List list) throws RemoteException {
                                if (HiCommonUtil.d(list)) {
                                    LogUtil.c("HiH_HiHealthNatApi", "fetchAccountInfo onSuccess datas is null or empty");
                                    if (hiCommonListener != null) {
                                        hiCommonListener.onFailure(1, "fetchAccountInfo onSuccess data empty");
                                        return;
                                    }
                                    return;
                                }
                                hiCommonListener.onSuccess(i, list.get(0));
                                LogUtil.d("HiH_HiHealthNatApi", "fetchAccountInfo success, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                            }

                            @Override // com.huawei.hihealth.ICommonListener
                            public void onFailure(int i, List list) throws RemoteException {
                                if (HiCommonUtil.d(list)) {
                                    LogUtil.c("HiH_HiHealthNatApi", "fetchAccountInfo onFailure errMsg is null or empty");
                                    if (hiCommonListener != null) {
                                        hiCommonListener.onFailure(1, "fetchAccountInfo onFailure errMsg empty");
                                        return;
                                    }
                                    return;
                                }
                                hiCommonListener.onFailure(i, list.get(0));
                                LogUtil.d("HiH_HiHealthNatApi", "fetchAccountInfo fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errMsg = ", list.get(0));
                            }
                        });
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "fetchAccountInfo Exception");
                        hiCommonListener.onFailure(1, "fetchAccountInfo Exception");
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void setGoalInfo(final int i, final List<HiGoalInfo> list, final HiCommonListener hiCommonListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.41
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiCommonListener hiCommonListener2 = hiCommonListener;
                    if (hiCommonListener2 != null) {
                        hiCommonListener2.onFailure(1, "setGoalInfo bindService Exception");
                        return;
                    }
                    return;
                }
                try {
                    d2.setGoalInfo(i, list, new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.41.1
                        @Override // com.huawei.hihealth.ICommonListener
                        public void onSuccess(int i2, List list2) throws RemoteException {
                            if (HiCommonUtil.d(list2)) {
                                LogUtil.c("HiH_HiHealthNatApi", "setGoalInfo onSuccess datas is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "setGoalInfo onSuccess data empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onSuccess(i2, list2.get(0));
                            }
                        }

                        @Override // com.huawei.hihealth.ICommonListener
                        public void onFailure(int i2, List list2) throws RemoteException {
                            if (HiCommonUtil.d(list2)) {
                                LogUtil.c("HiH_HiHealthNatApi", "setGoalInfo onFailure errMsg is null or empty");
                                if (hiCommonListener != null) {
                                    hiCommonListener.onFailure(1, "setGoalInfo onFailure errMsg empty");
                                    return;
                                }
                                return;
                            }
                            if (hiCommonListener != null) {
                                hiCommonListener.onFailure(i2, list2.get(0));
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "setGoalInfo fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errMsg = ", list2.get(0));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "setGoalInfo Exception");
                    HiCommonListener hiCommonListener3 = hiCommonListener;
                    if (hiCommonListener3 != null) {
                        hiCommonListener3.onFailure(1, "setGoalInfo Exception");
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchGoalInfo(final int i, final int i2, final HiCommonListener hiCommonListener) {
        if (hiCommonListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchGoalInfo listener null");
        } else {
            this.k.execute(new ReadTask("fetchGoalInfo", Integer.valueOf(i2)) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.42
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiCommonListener.onFailure(1, "fetchGoalInfo bindservice Exception");
                        return;
                    }
                    try {
                        d2.fetchGoalInfo(i, i2, new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.42.1
                            @Override // com.huawei.hihealth.ICommonListener
                            public void onSuccess(int i3, List list) throws RemoteException {
                                hiCommonListener.onSuccess(i3, list);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchGoalInfo, totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                            }

                            @Override // com.huawei.hihealth.ICommonListener
                            public void onFailure(int i3, List list) throws RemoteException {
                                if (HiCommonUtil.d(list)) {
                                    LogUtil.c("HiH_HiHealthNatApi", "fetchGoalInfo onFailure errMsg is null or empty");
                                    if (hiCommonListener != null) {
                                        hiCommonListener.onFailure(1, "fetchGoalInfo onFailure errMsg empty");
                                        return;
                                    }
                                    return;
                                }
                                hiCommonListener.onFailure(i3, list.get(0));
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "fetchGoalInfo fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errMsg = ", list.get(0));
                            }
                        });
                    } catch (Exception unused) {
                        ReleaseLogUtil.c("HiH_HiHealthNatApi", "fetchGoalInfo Exception");
                        hiCommonListener.onFailure(1, "fetchGoalInfo Exception");
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public boolean setUserPreference(HiUserPreference hiUserPreference) {
        return setUserPreference(hiUserPreference, false);
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public boolean setUserPreference(final HiUserPreference hiUserPreference, final boolean z) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            LogUtil.e("HiH_HiHealthNatApi", "setUserPreference can't be called in main thread isNeedNotifyChange = ", Boolean.valueOf(z));
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = {false};
        LogUtil.d("HiH_HiHealthNatApi", "setUserPreference isNeedNotifyChange = ", Boolean.valueOf(z));
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthNativeApi.this.a(zArr, hiUserPreference, z, countDownLatch);
            }
        });
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LogUtil.e("HiH_HiHealthNatApi", "getUP InterruptedException keys = ", e.getMessage());
        }
        return zArr[0];
    }

    /* synthetic */ void a(boolean[] zArr, HiUserPreference hiUserPreference, boolean z, CountDownLatch countDownLatch) {
        long currentTimeMillis = System.currentTimeMillis();
        IHiHealth d2 = d();
        if (d2 != null) {
            try {
                zArr[0] = d2.setUserPreference(hiUserPreference, z);
            } catch (Exception e) {
                LogUtil.e("HiH_HiHealthNatApi", "setUserPreference Exception:", e.getMessage());
            }
        }
        LogUtil.d("HiH_HiHealthNatApi", "setUserPreference result = ", Boolean.valueOf(zArr[0]), ",totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " key ", hiUserPreference.getKey());
        countDownLatch.countDown();
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    @Deprecated
    public HiUserPreference getUserPreference(String str) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        List<HiUserPreference> userPreferenceList = getUserPreferenceList(arrayList);
        if (HiCommonUtil.d(userPreferenceList)) {
            return new HiUserPreference();
        }
        return userPreferenceList.get(0);
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public List<HiUserPreference> getUserPreferenceList(final List<String> list) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            LogUtil.e("HiH_HiHealthNatApi", "getUP can't be called in main thread keys = ", list);
        }
        long currentTimeMillis = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<HiUserPreference>[] listArr = {new ArrayList(1)};
        this.q.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.43
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.d("HiH_HiHealthNatApi", "getUP Do");
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 != null) {
                    try {
                        listArr[0] = d2.getUserPreferenceList(list);
                    } catch (RemoteException unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "getUP Exception keys = ", list);
                    }
                    LogUtil.d("HiH_HiHealthNatApi", "getUP cD keys=", list);
                } else {
                    LogUtil.c("HiH_HiHealthNatApi", "getUP hiHealthApiAidl=null");
                }
                countDownLatch.countDown();
                LogUtil.d("HiH_HiHealthNatApi", "getUP Done");
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.e("HiH_HiHealthNatApi", "getUP InterruptedException keys = ", list);
        }
        LogUtil.d("HiH_HiHealthNatApi", "getUP tlTm=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return listArr[0];
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public boolean checkHiHealthServiceExist() {
        Intent intent = new Intent("com.huawei.hihealthservice.HiHealthService");
        intent.setPackage(HuaweiHealth.b());
        List<ResolveInfo> queryIntentServices = this.f.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() != 1) {
            LogUtil.c("HiH_HiHealthNatApi", "checkHiHealthServiceExist() resolveInfo == null || resolveInfo.size () != 1 false ");
            return false;
        }
        LogUtil.d("HiH_HiHealthNatApi", "checkHiHealthServiceExist() true");
        return true;
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public boolean checkHiHealthLogin(String str) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            LogUtil.e("HiH_HiHealthNatApi", "checkHiHealthLogin can't be called in main thread");
        }
        boolean z = false;
        if (HiCommonUtil.b(str)) {
            LogUtil.c("HiH_HiHealthNatApi", "checkHiHealthLogin huid = null");
            return false;
        }
        LogUtil.b("HiH_HiHealthNatApi", "checkHiHealthLogin");
        long currentTimeMillis = System.currentTimeMillis();
        IHiHealth d2 = d();
        if (d2 != null) {
            try {
                z = d2.checkHiHealthLogin(str);
            } catch (Exception unused) {
                LogUtil.e("HiH_HiHealthNatApi", "checkHiHealthLogin Exception");
            }
        }
        LogUtil.d("HiH_HiHealthNatApi", "checkHiHealthLogin login = ", Boolean.valueOf(z), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return z;
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public int getHiHealthVersionCode() {
        int hiHealthVersionCode;
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            LogUtil.e("HiH_HiHealthNatApi", "getHiHealthVersionCode can't be called in main thread");
        }
        long currentTimeMillis = System.currentTimeMillis();
        IHiHealth d2 = d();
        if (d2 != null) {
            try {
                hiHealthVersionCode = d2.getHiHealthVersionCode();
            } catch (Exception unused) {
                LogUtil.e("HiH_HiHealthNatApi", "getHiHealthVersionCode Exception");
            }
            LogUtil.d("HiH_HiHealthNatApi", "getHiHealthVersion version = ", Integer.valueOf(hiHealthVersionCode), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return hiHealthVersionCode;
        }
        hiHealthVersionCode = -1;
        LogUtil.d("HiH_HiHealthNatApi", "getHiHealthVersion version = ", Integer.valueOf(hiHealthVersionCode), ", totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return hiHealthVersionCode;
    }

    private void e() {
        LogUtil.d("HiH_HiHealthNatApi", "registerMyBroadcast()");
        if (HuaweiHealth.b().equals(this.f.getPackageName())) {
            this.h = true;
            HiBroadcastManager.bwj_(this.f, this.f4056a, HiBroadcastAction.bwi_());
        }
    }

    private void a() {
        LogUtil.d("HiH_HiHealthNatApi", "unregisterMyBroadcast()");
        if (HuaweiHealth.b().equals(this.f.getPackageName()) && this.h) {
            this.h = false;
            HiBroadcastManager.bwm_(this.f, this.f4056a);
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void readDeviceInfo(final HiDataHiDeviceInfoListener hiDataHiDeviceInfoListener) {
        if (hiDataHiDeviceInfoListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "readDeviceInfo listener null");
        } else {
            this.k.execute(new ReadTask("readDeviceInfo", hiDataHiDeviceInfoListener) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.44
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataHiDeviceInfoListener.onResult(null);
                        return;
                    }
                    try {
                        d2.readDeviceInfo(new IDataHiDeviceInfoListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.44.1
                            @Override // com.huawei.hihealth.IDataHiDeviceInfoListener
                            public void onResult(List list) throws RemoteException {
                                hiDataHiDeviceInfoListener.onResult(list);
                            }
                        });
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "readHiHealthData Exception");
                        hiDataHiDeviceInfoListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void queryKitAppInfo(final HiDataOperateListener hiDataOperateListener) {
        if (hiDataOperateListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "queryKitAppInfo listener null");
        } else {
            this.k.execute(new ReadTask("queryKitAppInfo", hiDataOperateListener) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.45
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                        return;
                    }
                    try {
                        d2.queryKitAppInfo(new InnerDataReadResultListener(hiDataOperateListener));
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "queryKitAppInfo Exception");
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void queryHealthKitPermission(final int i, final HiDataOperateListener hiDataOperateListener) {
        if (hiDataOperateListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "queryHealthKitPermission listener null");
        } else {
            this.l.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.46
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                        return;
                    }
                    try {
                        d2.queryHealthKitPermission(i, new InnerDataReadResultListener(hiDataOperateListener));
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "queryHealthKitPermission Exception");
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void updateHealthKitPermission(final int i, final int i2, final int i3, final boolean z, final HiDataOperateListener hiDataOperateListener) {
        if (hiDataOperateListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "updateHealthKitPermission listener null");
        } else {
            this.l.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.47
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                        return;
                    }
                    try {
                        d2.updateHealthKitPermission(i, i2, i3, z, new InnerDataReadResultListener(hiDataOperateListener));
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "updateHealthKitPermission Exception");
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void queryWearKitAppInfo(final HiDataOperateListener hiDataOperateListener) {
        if (hiDataOperateListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "queryKitAppInfo listener null");
        } else {
            this.k.execute(new ReadTask("queryWearKitAppInfo", hiDataOperateListener) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.48
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                        return;
                    }
                    try {
                        d2.queryWearKitAppInfo(new InnerDataReadResultListener(hiDataOperateListener));
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "queryKitAppInfo Exception");
                        hiDataOperateListener.onResult(1, Collections.emptyList());
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void queryWearKitPermission(final int i, final HiDataOperateListener hiDataOperateListener) {
        if (hiDataOperateListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "queryWearKitPermission listener null");
        } else {
            this.l.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.49
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                        return;
                    }
                    try {
                        d2.queryWearKitPermission(i, new InnerDataReadResultListener(hiDataOperateListener));
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "queryWearKitPermission Exception");
                        hiDataOperateListener.onResult(1, Collections.emptyList());
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void updateWearKitPermission(final int i, final int i2, final boolean z, final HiDataOperateListener hiDataOperateListener) {
        if (hiDataOperateListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "updateWearKitPermission listener null");
        } else {
            this.l.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.50
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataOperateListener.onResult(1, new ArrayList(10));
                        return;
                    }
                    try {
                        d2.updateWearKitPermission(i, i2, z, new InnerDataReadResultListener(hiDataOperateListener));
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "updateWearKitPermission Exception");
                        hiDataOperateListener.onResult(1, Collections.emptyList());
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void setBinder(final String str, final IBinder iBinder, final HiCommonListener hiCommonListener) {
        if (hiCommonListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "setBinder listener null");
        } else if (TextUtils.isEmpty(str)) {
            hiCommonListener.onFailure(1, "HiHealthNativeApi.setBinder: tag can't be null or empty");
        } else {
            LogUtil.d("HiH_HiHealthNatApi", "setBinder, tag = ", str);
            this.l.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.51
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiCommonListener.onFailure(1, "HiHealthNativeApi.setBinder: hiHealthApiAidl == null");
                        return;
                    }
                    try {
                        d2.setBinder(str, iBinder, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.51.1
                            @Override // com.huawei.hihealth.ICommonCallback
                            public void onResult(int i, String str2) throws RemoteException {
                                hiCommonListener.onSuccess(i, str2);
                                LogUtil.d("HiH_HiHealthNatApi", "setBinder errorCode = ", Integer.valueOf(i));
                            }
                        });
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "setBinder Exception");
                        hiCommonListener.onFailure(1, "HiHealthNativeApi.setBinder: exception occurs when call hiHealthApiAidl.setBinder");
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchSportTypeList(final HiDataReadOption hiDataReadOption, final HiCommonListener hiCommonListener) {
        if (hiCommonListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fechSportTypeList listener is null");
        } else {
            this.k.execute(new ReadTask("fetchSportTypeList", hiDataReadOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.52
                @Override // java.lang.Runnable
                public void run() {
                    final long currentTimeMillis = System.currentTimeMillis();
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiCommonListener.onFailure(1, "fetchSportTypeList bindService exception");
                        return;
                    }
                    try {
                        d2.fetchSportTypeList(hiDataReadOption, new ICommonListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.52.1
                            @Override // com.huawei.hihealth.ICommonListener
                            public void onSuccess(int i, List list) throws RemoteException {
                                hiCommonListener.onSuccess(i, list);
                                LogUtil.d("HiH_HiHealthNatApi", "fetchSportTypeList, totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                            }

                            @Override // com.huawei.hihealth.ICommonListener
                            public void onFailure(int i, List list) throws RemoteException {
                                if (HiCommonUtil.d(list)) {
                                    LogUtil.c("HiH_HiHealthNatApi", "fetchSportTypeList onResult datas is null or empty");
                                    hiCommonListener.onFailure(1, "result is null or empty fetchSportTypeList data fail " + hiDataReadOption);
                                    return;
                                }
                                hiCommonListener.onFailure(i, list.get(0));
                                LogUtil.d("HiH_HiHealthNatApi", "fetchSportTypeList fail, cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), " errCode = ", Integer.valueOf(i));
                            }
                        });
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "fetchSportTypeList Exception");
                        hiCommonListener.onFailure(1, "fetchSportTypeList Exception");
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchSequenceDataBySportType(final HiDataReadOption hiDataReadOption, final HiDataReadResultListener hiDataReadResultListener) {
        if (hiDataReadResultListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchSequenceDataBySportType listener is null");
        } else {
            this.k.execute(new ReadTask("fetchSequenceDataBySportType", hiDataReadOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.53
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataReadResultListener.onResult(null, 1, 0);
                        return;
                    }
                    try {
                        final long currentTimeMillis = System.currentTimeMillis();
                        final SparseArray sparseArray = new SparseArray();
                        final ArrayList arrayList = new ArrayList();
                        d2.fetchSequenceDataBySportType(hiDataReadOption, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.53.1
                            @Override // com.huawei.hihealth.IDataReadResultListener
                            public void onResult(List list, int i, int i2) throws RemoteException {
                                if (HiDivideUtil.bwO_(HiHealthNativeApi.this.f, list, new int[]{i, i2}, arrayList, sparseArray)) {
                                    long currentTimeMillis2 = System.currentTimeMillis();
                                    hiDataReadResultListener.onResult(sparseArray, i, i2);
                                    LogUtil.d("HiH_HiHealthNatApi", "fetchSequenceDataBySportType option = ", hiDataReadOption, "errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                                }
                            }
                        });
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "fetchSequenceDataBySportType Exception");
                        hiDataReadResultListener.onResult(null, 1, 0);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void aggregateSportStatData(final HiSportStatDataAggregateOption hiSportStatDataAggregateOption, final HiAggregateListener hiAggregateListener) {
        this.k.execute(new ReadTask("aggregateSportStatData", hiSportStatDataAggregateOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.54
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiAggregateListener hiAggregateListener2 = hiAggregateListener;
                    if (hiAggregateListener2 != null) {
                        hiAggregateListener2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final ArrayList arrayList = new ArrayList();
                    d2.aggregateSportStatData(hiSportStatDataAggregateOption, new IAggregateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.54.1
                        @Override // com.huawei.hihealth.IAggregateListener
                        public void onResult(List list, int i, int i2) throws RemoteException {
                            if (hiAggregateListener != null && HiDivideUtil.c(list, i2, arrayList)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiAggregateListener.onResult(arrayList.isEmpty() ? null : arrayList, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "aggregateSportStatData errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "aggregateSportStatData Exception");
                    HiAggregateListener hiAggregateListener3 = hiAggregateListener;
                    if (hiAggregateListener3 != null) {
                        hiAggregateListener3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void fetchDataSource(final HiDataSourceFetchOption hiDataSourceFetchOption, final HiDataClientListener hiDataClientListener) {
        if (hiDataClientListener == null) {
            LogUtil.c("HiH_HiHealthNatApi", "fetchDataSource listener null");
        } else {
            this.k.execute(new ReadTask("fetchDataSource", hiDataSourceFetchOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.55
                @Override // java.lang.Runnable
                public void run() {
                    IHiHealth d2 = HiHealthNativeApi.this.d();
                    if (d2 == null) {
                        hiDataClientListener.onResult(null);
                        return;
                    }
                    try {
                        final long currentTimeMillis = System.currentTimeMillis();
                        d2.fetchDataSource(hiDataSourceFetchOption, new IDataClientListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.55.1
                            @Override // com.huawei.hihealth.IDataClientListener
                            public void onResult(List list) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiDataClientListener.onResult(list);
                                LogUtil.d("HiH_HiHealthNatApi", "fetchDataSource dataSourceFetchOption = ", hiDataSourceFetchOption, ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        });
                    } catch (Exception unused) {
                        LogUtil.e("HiH_HiHealthNatApi", "fetchDataSource Exception");
                        hiDataClientListener.onResult(null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void readHiHealthDataPro(final HiDataReadProOption hiDataReadProOption, final HiDataReadResultListener hiDataReadResultListener) {
        this.k.execute(new ReadTask("readHiHealthDataPro", hiDataReadProOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.56
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataReadResultListener hiDataReadResultListener2 = hiDataReadResultListener;
                    if (hiDataReadResultListener2 != null) {
                        hiDataReadResultListener2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final long currentTimeMillis = System.currentTimeMillis();
                    final SparseArray sparseArray = new SparseArray();
                    final ArrayList arrayList = new ArrayList();
                    d2.readHiHealthDataPro(hiDataReadProOption, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.56.1
                        @Override // com.huawei.hihealth.IDataReadResultListener
                        public void onResult(List list, int i, int i2) throws RemoteException {
                            if (hiDataReadResultListener == null) {
                                return;
                            }
                            if (HiDivideUtil.bwO_(HiHealthNativeApi.this.f, list, new int[]{i, i2}, arrayList, sparseArray)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiDataReadResultListener.onResult(sparseArray, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "readHiHealthDataPro option = ", hiDataReadProOption, "errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2), ",dataSz=", Integer.valueOf(sparseArray.size()));
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "readHiHealthDataPro Exception");
                    HiDataReadResultListener hiDataReadResultListener3 = hiDataReadResultListener;
                    if (hiDataReadResultListener3 != null) {
                        hiDataReadResultListener3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void readHiHealthDataEx(final List<HiDataReadProOption> list, final HiDataReadResultListener hiDataReadResultListener) {
        this.k.execute(new ReadTask("readHiHealthDataEx", list) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.57
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataReadResultListener hiDataReadResultListener2 = hiDataReadResultListener;
                    if (hiDataReadResultListener2 != null) {
                        hiDataReadResultListener2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final long currentTimeMillis = System.currentTimeMillis();
                    final SparseArray sparseArray = new SparseArray();
                    final ArrayList arrayList = new ArrayList();
                    d2.readHiHealthDataEx(list, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.57.1
                        @Override // com.huawei.hihealth.IDataReadResultListener
                        public void onResult(List list2, int i, int i2) throws RemoteException {
                            if (hiDataReadResultListener == null) {
                                return;
                            }
                            if (HiDivideUtil.bwO_(HiHealthNativeApi.this.f, list2, new int[]{i, i2}, arrayList, sparseArray)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiDataReadResultListener.onResult(sparseArray, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "readHiHealthDataEx options = ", list, "errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "readHiHealthDataEx Exception");
                    HiDataReadResultListener hiDataReadResultListener3 = hiDataReadResultListener;
                    if (hiDataReadResultListener3 != null) {
                        hiDataReadResultListener3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void aggregateHiHealthDataPro(final HiDataAggregateProOption hiDataAggregateProOption, final HiAggregateListener hiAggregateListener) {
        this.k.execute(new ReadTask("aggregateHiHealthDataPro", hiDataAggregateProOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.58
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiAggregateListener hiAggregateListener2 = hiAggregateListener;
                    if (hiAggregateListener2 != null) {
                        hiAggregateListener2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final ArrayList arrayList = new ArrayList();
                    d2.aggregateHiHealthDataPro(hiDataAggregateProOption, new IAggregateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.58.1
                        @Override // com.huawei.hihealth.IAggregateListener
                        public void onResult(List list, int i, int i2) throws RemoteException {
                            if (hiAggregateListener != null && HiDivideUtil.c(list, i2, arrayList)) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                hiAggregateListener.onResult(arrayList.isEmpty() ? null : arrayList, i, i2);
                                ReleaseLogUtil.b("HiH_HiHealthNatApi", "aggregateHiHealthDataPro errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                            }
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "aggregateHiHealthDataPro Exception");
                    HiAggregateListener hiAggregateListener3 = hiAggregateListener;
                    if (hiAggregateListener3 != null) {
                        hiAggregateListener3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void deleteHiHealthDataPro(final HiDataDeleteProOption hiDataDeleteProOption, final HiDataOperateListener hiDataOperateListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.59
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataOperateListener hiDataOperateListener2 = hiDataOperateListener;
                    if (hiDataOperateListener2 != null) {
                        hiDataOperateListener2.onResult(1, "deleteHiHealthDataPro bindservice exeception");
                        return;
                    }
                    return;
                }
                try {
                    d2.deleteHiHealthDataPro(hiDataDeleteProOption, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.59.1
                        @Override // com.huawei.hihealth.IDataOperateListener
                        public void onResult(int i, List list) throws RemoteException {
                            if (list == null || list.isEmpty()) {
                                LogUtil.c("HiH_HiHealthNatApi", "deleteHiHealthDataPro onResult datas is null or empty");
                                if (hiDataOperateListener != null) {
                                    hiDataOperateListener.onResult(1, "result is null or empty delete data fail " + hiDataDeleteProOption);
                                    return;
                                }
                                return;
                            }
                            Object obj = list.get(0);
                            if (hiDataOperateListener != null) {
                                hiDataOperateListener.onResult(i, obj);
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "deleteHiHealthDataPro cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "deleteHiHealthDataPro Exception");
                    HiDataOperateListener hiDataOperateListener3 = hiDataOperateListener;
                    if (hiDataOperateListener3 != null) {
                        hiDataOperateListener3.onResult(1, "delete data fail" + hiDataDeleteProOption);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void deleteHiHealthDataProEx(final List<HiDataDeleteProOption> list, final HiDeleteListenerEx hiDeleteListenerEx) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.60
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiHealthNativeApi.this.e(1, "deleteHiHealthDataProEx bindservice exeception", hiDeleteListenerEx);
                    return;
                }
                try {
                    d2.deleteHiHealthDataProEx(list, new IDeleteListenerEx.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.60.1
                        @Override // com.huawei.hihealth.IDeleteListenerEx
                        public void onResult(Map map) throws RemoteException {
                            if (hiDeleteListenerEx != null) {
                                hiDeleteListenerEx.onResult(map);
                            }
                            LogUtil.d("HiH_HiHealthNatApi", "deleteHiHealthDataProEx cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "deleteHiHealthDataProEx Exception");
                    HiHealthNativeApi.this.e(1, "delete datas fail" + list, hiDeleteListenerEx);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str, HiDeleteListenerEx hiDeleteListenerEx) {
        if (hiDeleteListenerEx != null) {
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(str);
            hashMap.put(Integer.valueOf(i), arrayList);
            hiDeleteListenerEx.onResult(hashMap);
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void setSampleConfig(final List<HiSampleConfig> list, final HiDataOperateListener hiDataOperateListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthNativeApi.this.e(hiDataOperateListener, list);
            }
        });
    }

    /* synthetic */ void e(final HiDataOperateListener hiDataOperateListener, List list) {
        final long currentTimeMillis = System.currentTimeMillis();
        IHiHealth d2 = d();
        if (d2 == null) {
            if (hiDataOperateListener != null) {
                hiDataOperateListener.onResult(1, "setSampleConfig bindService exception");
            }
        } else {
            try {
                d2.setSampleConfig(list, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.61
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list2) throws RemoteException {
                        if (HiCommonUtil.d(list2)) {
                            LogUtil.c("HiH_HiHealthNatApi", "setSampleConfig onResult datas is null or empty");
                            HiDataOperateListener hiDataOperateListener2 = hiDataOperateListener;
                            if (hiDataOperateListener2 != null) {
                                hiDataOperateListener2.onResult(1, "result is null or empty delete data fail");
                                return;
                            }
                            return;
                        }
                        Object obj = list2.get(0);
                        HiDataOperateListener hiDataOperateListener3 = hiDataOperateListener;
                        if (hiDataOperateListener3 != null) {
                            hiDataOperateListener3.onResult(i, obj);
                        }
                        LogUtil.d("HiH_HiHealthNatApi", "setSampleConfig cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    }
                });
            } catch (Exception unused) {
                ReleaseLogUtil.c("HiH_HiHealthNatApi", "setSampleConfig Exception");
                if (hiDataOperateListener != null) {
                    hiDataOperateListener.onResult(1, "setSampleConfig fail");
                }
            }
        }
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void getSampleConfig(final HiSampleConfigProcessOption hiSampleConfigProcessOption, final HiDataReadResultListener hiDataReadResultListener) {
        this.k.execute(new ReadTask("getSampleConfig", hiSampleConfigProcessOption) { // from class: com.huawei.hihealth.api.HiHealthNativeApi.62
            @Override // java.lang.Runnable
            public void run() {
                IHiHealth d2 = HiHealthNativeApi.this.d();
                if (d2 == null) {
                    HiDataReadResultListener hiDataReadResultListener2 = hiDataReadResultListener;
                    if (hiDataReadResultListener2 != null) {
                        hiDataReadResultListener2.onResult(null, 1, 0);
                        return;
                    }
                    return;
                }
                try {
                    final long currentTimeMillis = System.currentTimeMillis();
                    d2.getSampleConfig(hiSampleConfigProcessOption, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.62.1
                        @Override // com.huawei.hihealth.IDataReadResultListener
                        public void onResult(List list, int i, int i2) throws RemoteException {
                            if (hiDataReadResultListener == null) {
                                return;
                            }
                            long currentTimeMillis2 = System.currentTimeMillis();
                            hiDataReadResultListener.onResult(list, i, i2);
                            LogUtil.d("HiH_HiHealthNatApi", "getSampleConfig option = ", hiSampleConfigProcessOption, "errorCode is ", Integer.valueOf(i), ", totalTime = ", Long.valueOf(currentTimeMillis2 - getTaskCreateTime()), ", wait time = ", Long.valueOf(currentTimeMillis - getTaskCreateTime()), ", executed time is ", Long.valueOf(currentTimeMillis2 - currentTimeMillis), ", onResult time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                        }
                    });
                } catch (Exception unused) {
                    ReleaseLogUtil.c("HiH_HiHealthNatApi", "getSampleConfig Exception");
                    HiDataReadResultListener hiDataReadResultListener3 = hiDataReadResultListener;
                    if (hiDataReadResultListener3 != null) {
                        hiDataReadResultListener3.onResult(null, 1, 0);
                    }
                }
            }
        });
    }

    @Override // com.huawei.hihealth.api.HiHealthApi
    public void deleteSampleConfig(final HiSampleConfigProcessOption hiSampleConfigProcessOption, final HiDataOperateListener hiDataOperateListener) {
        HiCommonUtil.d("HiH_HiHealthNatApi", this.o);
        this.t.execute(new Runnable() { // from class: com.huawei.hihealth.api.HiHealthNativeApi$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthNativeApi.this.d(hiDataOperateListener, hiSampleConfigProcessOption);
            }
        });
    }

    /* synthetic */ void d(final HiDataOperateListener hiDataOperateListener, final HiSampleConfigProcessOption hiSampleConfigProcessOption) {
        final long currentTimeMillis = System.currentTimeMillis();
        IHiHealth d2 = d();
        if (d2 == null) {
            if (hiDataOperateListener != null) {
                hiDataOperateListener.onResult(1, "deleteSampleConfig bindService exception");
                return;
            }
            return;
        }
        try {
            d2.deleteSampleConfig(hiSampleConfigProcessOption, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.api.HiHealthNativeApi.63
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list) throws RemoteException {
                    if (HiCommonUtil.d(list)) {
                        LogUtil.c("HiH_HiHealthNatApi", "deleteSampleConfig onResult datas is null or empty");
                        if (hiDataOperateListener != null) {
                            hiDataOperateListener.onResult(1, "deleteSampleConfig fail " + hiSampleConfigProcessOption);
                            return;
                        }
                        return;
                    }
                    Object obj = list.get(0);
                    HiDataOperateListener hiDataOperateListener2 = hiDataOperateListener;
                    if (hiDataOperateListener2 != null) {
                        hiDataOperateListener2.onResult(i, obj);
                    }
                    LogUtil.d("HiH_HiHealthNatApi", "deleteSampleConfig cost time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            });
        } catch (Exception unused) {
            ReleaseLogUtil.c("HiH_HiHealthNatApi", "deleteSampleConfig Exception");
            if (hiDataOperateListener != null) {
                hiDataOperateListener.onResult(1, "deleteSampleConfig fail" + hiSampleConfigProcessOption);
            }
        }
    }

    static class MyBroadcastReceiver extends BroadcastReceiver {
        private MyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.c("HiH_HiHealthNatApi", "MyBroadcastReceiver onReceive intent = null");
            } else {
                HiBroadcastManager.bwl_(context, intent);
            }
        }
    }

    static class InnerDataReadResultListener extends IDataReadResultListener.Stub {
        private final HiDataOperateListener c;

        InnerDataReadResultListener(HiDataOperateListener hiDataOperateListener) {
            this.c = hiDataOperateListener;
        }

        @Override // com.huawei.hihealth.IDataReadResultListener
        public void onResult(List list, int i, int i2) throws RemoteException {
            HiDataOperateListener hiDataOperateListener = this.c;
            if (hiDataOperateListener != null) {
                hiDataOperateListener.onResult(i, list);
            }
        }
    }

    abstract class Task implements Runnable {
        private String mFocusActivity;
        private String mMethodInfo;
        private Object mParameterInfo;
        private long mTaskCreateTime = System.currentTimeMillis();

        Task(String str, Object obj) {
            this.mMethodInfo = str;
            this.mParameterInfo = obj;
            LogApi d = LogUtil.d();
            if (d != null) {
                this.mFocusActivity = d.getCurrentActivity();
            }
        }

        public long getTaskCreateTime() {
            return this.mTaskCreateTime;
        }

        public String getFocusActivity() {
            return this.mFocusActivity;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("TaskInfo{cTm:");
            sb.append(HiDateUtil.b(this.mTaskCreateTime, "yyyy-MM-dd HH:mm:ss:SSS"));
            if (this.mFocusActivity != null) {
                sb.append(",reqAct:");
                sb.append(this.mFocusActivity);
            }
            sb.append(",method:");
            sb.append(this.mMethodInfo);
            sb.append(",param:");
            sb.append(this.mParameterInfo);
            sb.append("}");
            return sb.toString();
        }
    }

    abstract class ReadTask extends Task {
        ReadTask(String str, Object obj) {
            super(str, obj);
            if (HiHealthNativeApi.this.n.isEmpty()) {
                ReleaseLogUtil.b("HiH_HiHealthNatApi", "taskCt=0 ", toString());
            }
            HiHealthNativeApi.this.b();
        }
    }
}
