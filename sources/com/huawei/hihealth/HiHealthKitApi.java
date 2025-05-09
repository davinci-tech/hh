package com.huawei.hihealth;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.common.Constant;
import com.huawei.hihealth.IBinderInterceptor;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IHiHealthKit;
import com.huawei.hihealth.IReadCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.ISportDataCallback;
import com.huawei.hihealth.IWriteCallback;
import com.huawei.hihealth.device.HiHealthDeviceInfo;
import com.huawei.hihealth.error.HiHealthError;
import com.huawei.hihealth.listener.CapabilityResultCallback;
import com.huawei.hihealth.listener.IuniversalCallback;
import com.huawei.hihealth.listener.ResultCallback;
import com.huawei.hihealth.option.HiHealthCapabilityQuery;
import com.huawei.hihealth.util.DictDataTransformUtil;
import com.huawei.hihealthkit.data.store.HiRealTimeCallback;
import com.huawei.hihealthkit.data.store.HiRealTimeListener;
import com.huawei.hihealthkit.data.store.HiSportDataCallback;
import com.huawei.hihealthkit.data.type.HiHealthDataType;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.idw;
import defpackage.idx;
import defpackage.idy;
import defpackage.idz;
import defpackage.ifa;
import defpackage.ifc;
import defpackage.ife;
import defpackage.iff;
import defpackage.ifi;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HiHealthKitApi implements ServiceConnection {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f3983a = new Object();
    private static volatile Context d;
    private final Object b;
    private IHiHealthKit c;
    private IBinder e;
    private ExecutorService f;
    private Set<Object> j;

    private HiHealthKitApi() {
        this.b = new Object();
        this.j = new CopyOnWriteArraySet();
        this.e = new Binder();
        Log.i("HiHealthKit", "HiHealthKitApi construct");
        this.f = Executors.newSingleThreadExecutor();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i("HiHealthKit", Constant.SERVICE_CONNECT_MESSAGE);
        try {
            int callingUid = Binder.getCallingUid();
            String packageName = d.getPackageName();
            Log.d("HiHealthKit", "getCallingUid uid:" + callingUid + " packageName1:" + packageName);
            IBinder serviceBinder = IBinderInterceptor.Stub.asInterface(iBinder).getServiceBinder(null);
            StringBuilder sb = new StringBuilder("binder: ");
            sb.append(serviceBinder);
            Log.i("HiHealthKit", sb.toString());
            this.c = IHiHealthKit.Stub.asInterface(serviceBinder);
            Log.i("HiHealthKit", "mApiAidl: " + this.c);
            IHiHealthKit iHiHealthKit = this.c;
            if (iHiHealthKit == null) {
                Log.w("HiHealthKit", "onServiceConnected mApiAidl is null");
            } else {
                if (packageName == null) {
                    packageName = "";
                }
                try {
                    iHiHealthKit.registerPackageName(packageName);
                    this.c.setKitVersion(String.valueOf(iff.a(d)));
                    iff.e(this.c.getServiceApiLevel());
                } catch (RemoteException unused) {
                    Log.e("HiHealthKit", "setKitVersion RemoteException");
                }
            }
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "onServiceConnected Exception");
        }
        synchronized (this.b) {
            this.b.notifyAll();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i("HiHealthKit", "onServiceDisconnected");
        this.c = null;
        e();
    }

    private void e() {
        Log.i("HiHealthKit", "need notify callback number " + this.j.size());
        for (Object obj : this.j) {
            if (obj instanceof HiRealTimeCallback) {
                ((HiRealTimeCallback) obj).onResultHandler(31, HiHealthError.e(31));
            } else if (obj instanceof HiSportDataCallback) {
                ((HiSportDataCallback) obj).onResultHandler(31);
            } else {
                Log.w("HiHealthKit", "no callback of this type");
            }
        }
        this.j.clear();
    }

    static class Instance {
        private static final HiHealthKitApi c = new HiHealthKitApi();

        private Instance() {
        }
    }

    public static HiHealthKitApi c(Context context) {
        Log.i("HiHealthKit", "HiHealthKitApi getInstance");
        if (d == null) {
            d = context.getApplicationContext();
        }
        return Instance.c;
    }

    private void d() {
        synchronized (f3983a) {
            if (this.c != null) {
                return;
            }
            Intent intent = new Intent("com.huawei.health.action.KIT_SERVICE");
            intent.setClassName(ife.e(), "com.huawei.hihealthservice.HiHealthService");
            intent.setPackage(ife.e());
            try {
                d.bindService(intent, this, 1);
            } catch (SecurityException e) {
                Log.e("HiHealthKit", "bindService exception" + e.getMessage());
            }
            synchronized (this.b) {
                try {
                } catch (InterruptedException e2) {
                    Log.e("HiHealthKit", "bindService InterruptedException = " + e2.getMessage());
                }
                if (this.c != null) {
                    Log.i("HiHealthKit", "bindService bind mApiAidl is not null = " + this.c);
                } else {
                    this.b.wait(OpAnalyticsConstants.H5_LOADING_DELAY);
                    Log.i("HiHealthKit", "bindService bind over mApiAidl is " + this.c);
                }
            }
        }
    }

    private int c() {
        SharedPreferences sharedPreferences;
        if (d == null || (sharedPreferences = d.getSharedPreferences("hihealth_kit", 0)) == null) {
            return 0;
        }
        return sharedPreferences.getInt("hihealth_kit", 0);
    }

    public void d(final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.h(resultCallback);
            }
        });
    }

    /* synthetic */ void h(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "getGender mApiAidl is null");
            Log.w("HiHealthKit", "getGender mApiAidl is null");
        } else {
            t(resultCallback);
        }
    }

    private void t(final ResultCallback resultCallback) {
        try {
            this.c.getGender(c(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.1
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKit", "enter KitAPI getGenderImpl onSuccess errorCode:" + i);
                    if (list != null && list.size() > 0) {
                        HiHealthKitApi.this.d(resultCallback, 0, Integer.valueOf(((Integer) list.get(0)).intValue()));
                    } else {
                        int c = HiHealthError.c(i);
                        HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                    }
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    Log.i("HiHealthKit", "getGenderImpl onfailure");
                    int c = HiHealthError.c(i);
                    HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getGenderImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getGenderImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    public void e(final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.f(resultCallback);
            }
        });
    }

    /* synthetic */ void f(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "getBirthday mApiAidl is null");
            Log.w("HiHealthKit", "getBirthday mApiAidl is null");
        } else {
            p(resultCallback);
        }
    }

    private void p(final ResultCallback resultCallback) {
        try {
            this.c.getBirthday(c(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.2
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKit", "enter KitAPI getBirthdayImpl onSuccess errorCode:" + i);
                    if (list != null && list.size() > 0) {
                        HiHealthKitApi.this.d(resultCallback, 0, Integer.valueOf(((Integer) list.get(0)).intValue()));
                    } else {
                        int c = HiHealthError.c(i);
                        HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                    }
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    Log.i("HiHealthKit", "getBirthdayImpl onfailure");
                    int c = HiHealthError.c(i);
                    HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getBirthdayImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getBirthdayImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    public void b(final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.j(resultCallback);
            }
        });
    }

    /* synthetic */ void j(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "getHeight mApiAidl is null");
            Log.w("HiHealthKit", "getHeight mApiAidl is null");
        } else {
            q(resultCallback);
        }
    }

    private void q(final ResultCallback resultCallback) {
        try {
            this.c.getHeight(c(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.3
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKit", "getHeightImpl:onSuccess errorCode:" + i);
                    if (list != null && list.size() > 0) {
                        int intValue = ((Integer) list.get(0)).intValue();
                        Log.i("HiHealthKit", "getHeightImpl height: " + intValue);
                        HiHealthKitApi.this.d(resultCallback, 0, Integer.valueOf(intValue));
                        return;
                    }
                    int c = HiHealthError.c(i);
                    HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    Log.i("HiHealthKit", "getHeightImpl onfailure");
                    int c = HiHealthError.c(i);
                    HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getHeightImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getHeightImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    public void a(final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.n(resultCallback);
            }
        });
    }

    /* synthetic */ void n(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "getWeight mApiAidl is null");
            Log.w("HiHealthKit", "getWeight mApiAidl is null");
        } else {
            r(resultCallback);
        }
    }

    private void r(final ResultCallback resultCallback) {
        try {
            this.c.getWeight(c(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.4
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKit", "enter KitAPI getWeightImp onSuccess errorCode:" + i);
                    if (list != null && list.size() > 0) {
                        float floatValue = ((Float) list.get(0)).floatValue();
                        Log.i("HiHealthKit", "getWeightImpl onSuccess weight: " + floatValue);
                        HiHealthKitApi.this.d(resultCallback, 0, Float.valueOf(floatValue));
                        return;
                    }
                    int c = HiHealthError.c(i);
                    HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    Log.i("HiHealthKit", "getWeightImpl onfailure");
                    int c = HiHealthError.c(i);
                    HiHealthKitApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getWeightImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getWeightImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    public void d(final HiHealthDataQuery hiHealthDataQuery, final int i, final ResultCallback resultCallback) {
        Log.i("HiHealthKit", "enter execQuery");
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.b(resultCallback, hiHealthDataQuery, i);
            }
        });
    }

    /* synthetic */ void b(ResultCallback resultCallback, HiHealthDataQuery hiHealthDataQuery, int i) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "execQuery mApiAidl is null");
            Log.w("HiHealthKit", "execQuery mApiAidl is null");
        } else if (!iff.b(hiHealthDataQuery.getSampleType())) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
        } else {
            a(hiHealthDataQuery, i, resultCallback);
        }
    }

    class QueryDataResultListener extends IDataReadResultListener.Stub {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4013a;
        private ResultCallback b;
        private HiHealthDataQuery c;
        private List d = new ArrayList(10);

        QueryDataResultListener(boolean z, HiHealthDataQuery hiHealthDataQuery, ResultCallback resultCallback) {
            this.f4013a = z;
            this.c = hiHealthDataQuery;
            this.b = resultCallback;
        }

        @Override // com.huawei.hihealth.IDataReadResultListener
        public void onResult(List list, int i, int i2) {
            Log.i("HiHealthKit", "enter KitAPI execQueryImpl onSuccess errorCode:" + i + ", resultType:" + i2);
            if (this.f4013a) {
                a(list, this.d, i);
                a(i, i2);
            } else {
                ArrayList arrayList = new ArrayList(10);
                a(list, arrayList, i);
                d(list, i, arrayList);
            }
        }

        private void d(List list, int i, List list2) {
            if (list != null) {
                this.b.onResultHandler(0, list2);
                return;
            }
            Log.w("HiHealthKit", "dataList is null");
            int c = HiHealthError.c(i);
            HiHealthKitApi.this.d(this.b, c, c == 0 ? new ArrayList(10) : HiHealthError.e(c));
        }

        private void a(int i, int i2) {
            if (i2 == 2) {
                int c = HiHealthError.c(i);
                if (this.d.size() == 0) {
                    HiHealthKitApi.this.d(this.b, c, c == 0 ? new ArrayList(10) : HiHealthError.e(c));
                } else {
                    HiHealthKitApi.this.d(this.b, c, this.d);
                }
                this.d.clear();
            }
        }

        private void a(List list, List list2, int i) {
            if (list != null) {
                Log.i("HiHealthKit", "datas size = " + list.size() + ", error code = " + i);
                switch (AnonymousClass30.c[HiHealthKitApi.this.d(this.c.getSampleType()).ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        HiHealthKitApi.this.c(list, list2, this.c);
                        break;
                    case 4:
                        HiHealthKitApi.this.d(list, list2);
                        break;
                    case 5:
                    case 6:
                        HiHealthKitApi.this.a(list, list2);
                        break;
                }
                if (i == this.c.getSampleType()) {
                    HiHealthKitApi.this.d((List<idy>) list2, i);
                }
            }
        }
    }

    /* renamed from: com.huawei.hihealth.HiHealthKitApi$30, reason: invalid class name */
    static /* synthetic */ class AnonymousClass30 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[HiHealthDataType.Category.values().length];
            c = iArr;
            try {
                iArr[HiHealthDataType.Category.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[HiHealthDataType.Category.STAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[HiHealthDataType.Category.BUSINESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[HiHealthDataType.Category.SESSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[HiHealthDataType.Category.SET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[HiHealthDataType.Category.SEQUENCE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public HiHealthDataType.Category d(int i) {
        HiHealthDataType.Category a2;
        d();
        if (this.c != null && iff.d("get_category")) {
            try {
                a2 = HiHealthDataType.Category.valueOf(this.c.getCategory(i));
            } catch (RemoteException unused) {
                Log.e("HiHealthKit", "getCategory RemoteException");
                a2 = HiHealthDataType.a(i);
            }
        } else {
            a2 = HiHealthDataType.a(i);
        }
        return i == 44000 ? HiHealthDataType.Category.SET : a2;
    }

    private void a(HiHealthDataQuery hiHealthDataQuery, int i, ResultCallback resultCallback) {
        Log.i("HiHealthKit", "enter execQueryImpl");
        try {
            this.c.execQuery(c(), hiHealthDataQuery, i, new QueryDataResultListener(ife.d(d, ife.e()) >= 1010053501, hiHealthDataQuery, resultCallback));
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "execQueryImpl RemoteException");
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "execQueryImpl Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<idy> list, int i) {
        Iterator<idy> it = list.iterator();
        while (it.hasNext()) {
            it.next().setType(i);
        }
    }

    public void b(final HiHealthDataQuery hiHealthDataQuery, final int i, final ResultCallback resultCallback) {
        Log.i("HiHealthKit", "enter querySleepWakeTime");
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.a(resultCallback, hiHealthDataQuery, i);
            }
        });
    }

    /* synthetic */ void a(ResultCallback resultCallback, HiHealthDataQuery hiHealthDataQuery, int i) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "querySleepWakeTime mApiAidl is null");
            Log.w("HiHealthKit", "execQuery mApiAidl is null");
        } else {
            a(hiHealthDataQuery, resultCallback, i);
        }
    }

    private void a(HiHealthDataQuery hiHealthDataQuery, final ResultCallback resultCallback, int i) {
        if (hiHealthDataQuery.getEndTime() - hiHealthDataQuery.getStartTime() > 345600000) {
            d(resultCallback, 1, "querySleepWakeTime, the period of time should be less than 96 hours.");
            Log.w("HiHealthKit", "The period of time should be less than 96 hours.");
        } else {
            try {
                this.c.querySleepWakeTime(c(), hiHealthDataQuery, i, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.5
                    @Override // com.huawei.hihealth.IDataReadResultListener
                    public void onResult(List list, int i2, int i3) {
                        Log.i("HiHealthKit", "enter KitAPI querySleepWakeTimeImpl onSuccess");
                        try {
                            if (list != null) {
                                Log.i("HiHealthKit", "datas size =" + list.size() + ", error code = " + i2);
                                ArrayList arrayList = new ArrayList(10);
                                HiHealthKitApi.this.a(list, arrayList);
                                resultCallback.onResultHandler(i2, arrayList);
                            } else {
                                Log.w("HiHealthKit", "dataList is null");
                                HiHealthKitApi.this.d(resultCallback, i2, (Object) null);
                            }
                        } catch (Exception unused) {
                            Log.w("HiHealthKit", "query sleep wake time exception");
                        }
                    }
                });
            } catch (RemoteException unused) {
                Log.e("HiHealthKit", "querySleepWakeTimeImpl RemoteException");
                d(resultCallback, 4, HiHealthError.e(4));
            }
        }
    }

    public void a(final HiHealthDataQuery hiHealthDataQuery, final ResultCallback resultCallback) {
        Log.i("HiHealthKit", "enter getCount");
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.d(resultCallback, hiHealthDataQuery);
            }
        });
    }

    /* synthetic */ void d(ResultCallback resultCallback, HiHealthDataQuery hiHealthDataQuery) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "getCount mApiAidl is null");
            Log.w("HiHealthKit", "getCount mApiAidl is null");
        } else {
            b(hiHealthDataQuery, resultCallback);
        }
    }

    private void b(HiHealthDataQuery hiHealthDataQuery, final ResultCallback resultCallback) {
        try {
            final boolean z = ife.d(d, ife.e()) >= 1010053501;
            this.c.getCount(c(), hiHealthDataQuery, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.6
                private int e;

                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i, int i2) {
                    Integer num;
                    Log.i("HiHealthKit", "enter KitAPI getCountImpl onSuccess errorCode:" + i + ",resultType:" + i2);
                    if (list == null || !(list.get(0) instanceof Integer)) {
                        num = null;
                    } else {
                        num = (Integer) list.get(0);
                        this.e += num.intValue();
                    }
                    if (z) {
                        a(i, i2);
                    } else {
                        d(list, i, num);
                    }
                }

                private void d(List list, int i, Integer num) {
                    if (list != null) {
                        ResultCallback resultCallback2 = resultCallback;
                        if (num == null) {
                            num = 0;
                        }
                        resultCallback2.onResultHandler(0, num);
                        return;
                    }
                    resultCallback.onResultHandler(HiHealthError.c(i), 0);
                }

                private void a(int i, int i2) {
                    if (i2 == 2) {
                        resultCallback.onResultHandler(HiHealthError.c(i), Integer.valueOf(this.e));
                        this.e = 0;
                    }
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getCountImpl RemoteException");
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getCountImpl Exception");
        }
    }

    private void a(HiHealthKitData hiHealthKitData, idy idyVar) {
        String string = hiHealthKitData.getString("device_uniquecode");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String string2 = hiHealthKitData.getString(PluginPayAdapter.KEY_DEVICE_INFO_NAME);
        String string3 = hiHealthKitData.getString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL);
        String string4 = hiHealthKitData.getString("deviceType");
        HiHealthDeviceInfo hiHealthDeviceInfo = new HiHealthDeviceInfo(string, string2, string3);
        if (!TextUtils.isEmpty(string4)) {
            hiHealthDeviceInfo.setDeviceType(string4);
        }
        idyVar.setSourceDevice(hiHealthDeviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List list, List list2, HiHealthDataQuery hiHealthDataQuery) {
        idz idzVar;
        if (list != null) {
            Log.i("HiHealthKit", "handlePointData size = " + list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthKitData hiHealthKitData = (HiHealthKitData) it.next();
                if (ifa.e(hiHealthDataQuery.getSampleType())) {
                    idzVar = new idz(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime(), hiHealthKitData.getDoubleValue(), 0);
                } else if (ifa.d(hiHealthDataQuery.getSampleType())) {
                    idzVar = new idz(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime(), hiHealthKitData.getString("metadata"), 0);
                } else {
                    idzVar = new idz(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime(), hiHealthKitData.getIntValue(), 0);
                }
                a(hiHealthKitData, idzVar);
                idzVar.setUpdateTime(hiHealthKitData.getLong("update_time"));
                list2.add(idzVar);
            }
            return;
        }
        Log.w("HiHealthKit", "point data null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List list, List list2) {
        if (list != null) {
            Log.i("HiHealthKit", "handleSessionData size = " + list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthKitData hiHealthKitData = (HiHealthKitData) it.next();
                idw idwVar = new idw(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime());
                a(hiHealthKitData, idwVar);
                idwVar.setUpdateTime(hiHealthKitData.getLong("update_time"));
                list2.add(idwVar);
            }
            return;
        }
        Log.w("HiHealthKit", "session data null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List list, List list2) {
        if (list != null) {
            Log.i("HiHealthKit", "handleSetData size = " + list.size());
            for (Object obj : list) {
                if (obj instanceof HiHealthKitData) {
                    HiHealthKitData hiHealthKitData = (HiHealthKitData) obj;
                    Map map = hiHealthKitData.getMap();
                    if (map != null) {
                        try {
                            if (map.containsKey("detail_data")) {
                                Object obj2 = map.get("detail_data");
                                if (obj2 instanceof String) {
                                    map.remove("detail_data");
                                    map.put("detail_data", HiZipUtil.a((String) obj2));
                                }
                            }
                        } catch (IOException unused) {
                            Log.e("HiHealthKit", "enter query ecgData IOException");
                        }
                    }
                    idx idxVar = new idx(hiHealthKitData.getType(), hiHealthKitData.getMap(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime());
                    a(hiHealthKitData, idxVar);
                    idxVar.setMetaData(hiHealthKitData.getString("metadata"));
                    idxVar.setUpdateTime(hiHealthKitData.getLong("update_time"));
                    list2.add(idxVar);
                }
            }
        }
    }

    public void b(final idy idyVar, final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.e(resultCallback, idyVar);
            }
        });
    }

    /* synthetic */ void e(ResultCallback resultCallback, idy idyVar) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "saveSample mApiAidl is null");
            d(resultCallback, 1, "saveSample mApiAidl is null");
        } else if (ifc.c(idyVar, resultCallback)) {
            e(idyVar, resultCallback);
        }
    }

    private void e(idy idyVar, final ResultCallback resultCallback) {
        try {
            Log.i("HiHealthKit", "saveSampleImpl set");
            int type = idyVar.getType();
            if (!iff.d(type)) {
                resultCallback.onResultHandler(30, HiHealthError.e(30));
            } else {
                this.c.saveSample(c(), ifi.d(idyVar, d(type)), new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.7
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list) {
                        Log.i("HiHealthKit", "enter saveSampleImpl result errorCode:" + i);
                        resultCallback.onResultHandler(HiHealthError.c(i), list);
                    }
                });
            }
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "saveSampleImpl RemoteException");
            resultCallback.onResultHandler(4, null);
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "saveSampleImpl Exception");
            resultCallback.onResultHandler(4, null);
        }
    }

    public void c(final List<idy> list, final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.a(resultCallback, list);
            }
        });
    }

    /* synthetic */ void a(ResultCallback resultCallback, List list) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "saveSamples mApiAidl is null");
            Log.w("HiHealthKit", "saveSamples mApiAidl is null");
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            idy idyVar = (idy) it.next();
            if (!ifc.c(idyVar, resultCallback)) {
                return;
            }
            if (!iff.d(idyVar.getType())) {
                resultCallback.onResult(30, HiHealthError.e(30));
                return;
            }
        }
        e((List<idy>) list, resultCallback);
    }

    private void e(List<idy> list, final ResultCallback resultCallback) {
        int[] iArr = {4};
        Object[] objArr = new Object[1];
        try {
            ArrayList arrayList = new ArrayList(list.size());
            for (idy idyVar : list) {
                arrayList.add(ifi.d(idyVar, d(idyVar.getType())));
            }
            if (iff.d("save_samples")) {
                if (ifi.e(arrayList)) {
                    c(iArr, objArr, arrayList);
                    d(resultCallback, iArr[0], objArr[0]);
                    return;
                } else {
                    this.c.saveSamples(c(), arrayList, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.8
                        @Override // com.huawei.hihealth.IDataOperateListener
                        public void onResult(int i, List list2) {
                            Log.i("HiHealthKit", "enter saveSamplesImpl result errorCode:" + i);
                            HiHealthKitApi.this.d(resultCallback, HiHealthError.c(i), list2);
                        }
                    });
                    return;
                }
            }
            if (list.size() > 20) {
                d(resultCallback, 2, "too much data!");
                return;
            }
            Iterator<HiHealthKitData> it = arrayList.iterator();
            while (it.hasNext()) {
                c(iArr, objArr, it.next());
            }
            d(resultCallback, iArr[0], objArr[0]);
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "saveSamplesImpl RemoteException");
            d(resultCallback, 4, "RemoteException");
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "save sample Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    private void c(final int[] iArr, final Object[] objArr, HiHealthKitData hiHealthKitData) throws RemoteException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            this.c.saveSample(c(), hiHealthKitData, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.9
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list) {
                    Log.i("HiHealthKit", "enter saveSampleImpl result errorCode:" + i);
                    iArr[0] = HiHealthError.c(i);
                    objArr[0] = list;
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await(10L, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                Log.e("HiHealthKit", "saveSampleImpl InterruptedException");
            }
        } catch (RemoteException e) {
            countDownLatch.countDown();
            throw e;
        }
    }

    private void b(final int[] iArr, final Object[] objArr, List<HiHealthKitData> list) throws RemoteException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            this.c.saveSamples(c(), list, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.10
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list2) {
                    Log.i("HiHealthKit", "enter saveSamplesImpl result errorCode:" + i);
                    iArr[0] = HiHealthError.c(i);
                    objArr[0] = list2;
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await(30L, TimeUnit.SECONDS);
        } catch (RemoteException e) {
            countDownLatch.countDown();
            throw e;
        }
    }

    private void c(int[] iArr, Object[] objArr, List<HiHealthKitData> list) throws RemoteException, InterruptedException {
        int b = ifi.b(list);
        int size = list.size();
        int i = (int) ((size * 524288.0f) / b);
        if (i == 0) {
            i = 1;
        }
        Log.i("HiHealthKit", "divideSaveSamplesImpl dataList size = " + size + ", parcelSize = " + b + ", divideLength = " + i);
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + i;
            if (i3 >= size) {
                b(iArr, objArr, list.subList(i2, size));
            } else {
                b(iArr, objArr, list.subList(i2, i3));
            }
            i2 = i3;
        }
    }

    public void a(final idy idyVar, final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.b(resultCallback, idyVar);
            }
        });
    }

    /* synthetic */ void b(ResultCallback resultCallback, idy idyVar) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "deleteSample mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
        } else if (idyVar == null) {
            Log.w("HiHealthKit", "deleteSample hiHealthData is null");
            d(resultCallback, 2, HiHealthError.e(2));
        } else {
            if (iff.d("delete_sample")) {
                d(idyVar, resultCallback);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(idyVar);
            d(arrayList, resultCallback);
        }
    }

    private void d(idy idyVar, ResultCallback resultCallback) {
        Log.i("HiHealthKit", "deleteSampleImpl " + idyVar.getType());
        final int[] iArr = {4};
        final Object[] objArr = new Object[1];
        if (!iff.a(idyVar.getType())) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            return;
        }
        try {
            try {
                this.c.deleteSample(c(), ifi.d(idyVar, d(idyVar.getType())), new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.11
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list) {
                        Log.i("HiHealthKit", "enter deleteSample result:" + i);
                        iArr[0] = HiHealthError.c(i);
                        objArr[0] = list;
                    }
                });
            } catch (RemoteException unused) {
                Log.e("HiHealthKit", "deleteSampleImpl RemoteException");
                iArr[0] = 4;
                objArr[0] = "RemoteException";
                if (resultCallback != null) {
                    resultCallback.onResultHandler(4, "RemoteException");
                }
            } catch (Exception unused2) {
                Log.e("HiHealthKit", "deleteSampleImpl Exception");
                iArr[0] = 4;
                String e = HiHealthError.e(4);
                objArr[0] = e;
                if (resultCallback != null) {
                    resultCallback.onResultHandler(iArr[0], e);
                }
            }
        } finally {
            if (resultCallback != null) {
                resultCallback.onResultHandler(iArr[0], objArr[0]);
            }
            Log.i("HiHealthKit", "deleteSampleImpl end");
        }
    }

    public void b(final List<idy> list, final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.e(resultCallback, list);
            }
        });
    }

    /* synthetic */ void e(ResultCallback resultCallback, List list) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "deleteSamples mApiAidl is null");
            Log.w("HiHealthKit", "deleteSamples mApiAidl is null");
        } else if (list == null || list.size() > 20) {
            d(resultCallback, 2, "too much data!");
        } else {
            d((List<idy>) list, resultCallback);
        }
    }

    private void d(List<idy> list, ResultCallback resultCallback) {
        Log.i("HiHealthKit", "deleteSamplesImpl set");
        final int[] iArr = {4};
        final Object[] objArr = new Object[1];
        for (idy idyVar : list) {
            if (!iff.a(idyVar.getType())) {
                Log.w("HiHealthKit", "delete type is not allowed : " + idyVar.getType());
                resultCallback.onResult(30, HiHealthError.e(30));
                return;
            }
        }
        try {
            try {
                ArrayList arrayList = new ArrayList(list.size());
                for (idy idyVar2 : list) {
                    arrayList.add(ifi.d(idyVar2, d(idyVar2.getType())));
                }
                this.c.deleteSamples(c(), arrayList, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.12
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list2) {
                        Log.i("HiHealthKit", "enter deleteSamplesImpl result:" + i);
                        iArr[0] = HiHealthError.c(i);
                        objArr[0] = list2;
                    }
                });
            } catch (RemoteException unused) {
                Log.e("HiHealthKit", "deleteSamplesImpl RemoteException");
                iArr[0] = 4;
                objArr[0] = "RemoteException";
                if (resultCallback != null) {
                    resultCallback.onResultHandler(4, "RemoteException");
                }
            } catch (Exception unused2) {
                Log.e("HiHealthKit", "deleteSamplesImpl Exception");
                iArr[0] = 4;
                String e = HiHealthError.e(4);
                objArr[0] = e;
                if (resultCallback != null) {
                    resultCallback.onResultHandler(iArr[0], e);
                }
            }
        } finally {
            if (resultCallback != null) {
                resultCallback.onResultHandler(iArr[0], objArr[0]);
            }
            Log.i("HiHealthKit", "deleteSamplesImpl end");
        }
    }

    public void e(final HiRealTimeListener hiRealTimeListener) {
        if (hiRealTimeListener == null) {
            return;
        }
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.d(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void d(HiRealTimeListener hiRealTimeListener) {
        d();
        if (this.c == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKit", "startReadingHeartRate mApiAidl is null");
        } else {
            j(hiRealTimeListener);
        }
    }

    private void j(final HiRealTimeListener hiRealTimeListener) {
        try {
            this.c.startReadingHeartRate(c(), new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.13
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "startReadingHeartRateImpl onResultHandler:" + i);
                    hiRealTimeListener.onResultHandler(HiHealthError.c(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str) {
                    hiRealTimeListener.onChangeHandler(HiHealthError.c(i), str);
                }
            });
            Log.i("HiHealthKit", "startReadingHeartRateImpl end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "startReadingHeartRateImpl RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "startReadingHeartRateImpl Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void h(final HiRealTimeListener hiRealTimeListener) {
        if (hiRealTimeListener == null) {
            return;
        }
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.b(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void b(HiRealTimeListener hiRealTimeListener) {
        d();
        if (this.c == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKit", "stopReadingHeartRate mApiAidl is null");
        } else {
            k(hiRealTimeListener);
        }
    }

    private void k(final HiRealTimeListener hiRealTimeListener) {
        try {
            this.c.stopReadingHeartRate(c(), new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.14
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "stopReadingHeartRateImpl onResultHandler:" + i);
                    hiRealTimeListener.onResultHandler(HiHealthError.c(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str) {
                    hiRealTimeListener.onChangeHandler(HiHealthError.c(i), str);
                }
            });
            Log.i("HiHealthKit", "stopReadingHeartRateImpl end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "stopReadingHeartRateImpl RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "stopReadingHeartRateImpl Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void i(final HiRealTimeListener hiRealTimeListener) {
        if (hiRealTimeListener == null) {
            return;
        }
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.a(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void a(HiRealTimeListener hiRealTimeListener) {
        d();
        if (this.c == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKit", "startReadingRri mApiAidl is null");
        } else {
            f(hiRealTimeListener);
        }
    }

    private void f(final HiRealTimeListener hiRealTimeListener) {
        try {
            this.c.startReadingRRI(c(), new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.15
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "startReadingRriImpl onResultHandler:" + i);
                    hiRealTimeListener.onResultHandler(HiHealthError.c(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str) {
                    hiRealTimeListener.onChangeHandler(HiHealthError.c(i), str);
                }
            });
            Log.i("HiHealthKit", "startReadingRriImpl end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "startReadingRriImpl RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "startReadingRriImpl Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void g(final HiRealTimeListener hiRealTimeListener) {
        if (hiRealTimeListener == null) {
            return;
        }
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.c(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void c(HiRealTimeListener hiRealTimeListener) {
        d();
        if (this.c == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKit", "stopReadingRri mApiAidl is null");
        } else {
            l(hiRealTimeListener);
        }
    }

    private void l(final HiRealTimeListener hiRealTimeListener) {
        try {
            this.c.stopReadingRRI(c(), new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.16
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "stopReadingRriImpl onResultHandler:" + i);
                    hiRealTimeListener.onResultHandler(HiHealthError.c(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str) {
                    hiRealTimeListener.onChangeHandler(HiHealthError.c(i), str);
                }
            });
            Log.i("HiHealthKit", "stopReadingRriImpl end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "stopReadingRriImpl RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "stopReadingRriImpl Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void c(final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.g(resultCallback);
            }
        });
    }

    /* synthetic */ void g(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "getDeviceList mApiAidl is null");
            Log.w("HiHealthKit", "getDeviceList mApiAidl is null");
        } else {
            s(resultCallback);
        }
    }

    private void s(final ResultCallback resultCallback) {
        try {
            this.c.getDeviceList(c(), new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.17
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "getDeviceListImpl onResultHandler");
                    HiHealthKitApi.this.d(resultCallback, i, HiHealthError.e(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str) {
                    HiHealthKitApi.this.d(resultCallback, i, str);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getDeviceListImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getDeviceListImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    public void e(final String str, final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.e(resultCallback, str);
            }
        });
    }

    /* synthetic */ void e(ResultCallback resultCallback, String str) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "sendDeviceCommand mApiAidl is null");
            Log.w("HiHealthKit", "sendDeviceCommand mApiAidl is null");
        } else {
            c(str, resultCallback);
        }
    }

    private void c(String str, final ResultCallback resultCallback) {
        try {
            this.c.sendDeviceCommand(c(), str, new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.18
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "sendDeviceCommandImpl onResultHandler errCode = " + i);
                    HiHealthKitApi.this.d(resultCallback, i, HiHealthError.e(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str2) {
                    HiHealthKitApi.this.d(resultCallback, i, str2);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "sendDeviceCommandImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "sendDeviceCommandImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    public void o(final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.m(resultCallback);
            }
        });
    }

    /* synthetic */ void m(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "startReadingAtrial mApiAidl is null");
            Log.w("HiHealthKit", "startReadingAtrial mApiAidl is null");
        } else {
            u(resultCallback);
        }
    }

    private void u(final ResultCallback resultCallback) {
        try {
            this.c.startReadingAtrial(c(), new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.19
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "startReadingAtrialImpl onResultHandler errCode = " + i);
                    HiHealthKitApi.this.d(resultCallback, i, HiHealthError.e(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str) {
                    HiHealthKitApi.this.d(resultCallback, i, str);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "startReadingAtrialImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "startReadingAtrialImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    public void k(final ResultCallback resultCallback) {
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda21
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.l(resultCallback);
            }
        });
    }

    /* synthetic */ void l(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "stopReadingAtrial mApiAidl is null");
            Log.w("HiHealthKit", "stopReadingAtrial mApiAidl is null");
        } else {
            v(resultCallback);
        }
    }

    private void v(final ResultCallback resultCallback) {
        try {
            this.c.stopReadingAtrial(c(), new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.20
                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onResult(int i) {
                    Log.i("HiHealthKit", "stopReadingAtrialImpl onResultHandler errCode = " + i);
                    HiHealthKitApi.this.d(resultCallback, i, HiHealthError.e(i));
                }

                @Override // com.huawei.hihealth.IRealTimeDataCallback
                public void onChange(int i, String str) {
                    HiHealthKitApi.this.d(resultCallback, i, str);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "stopReadingAtrialImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "stopReadingAtrialImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void c(ResultCallback resultCallback, String str, String str2) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "pushMsgToWearable:mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
        } else {
            d(str, str2, resultCallback);
        }
    }

    private void d(String str, String str2, final ResultCallback resultCallback) {
        try {
            this.c.pushMsgToWearable(str, str2, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.21
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str3) {
                    resultCallback.onResultHandler(i, str3);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "pushMsgToWearableImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "pushMsgToWearableImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void a(ResultCallback resultCallback, String str, String str2, OutputStream outputStream) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "readFromWearable:mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
        } else {
            e(str, str2, outputStream, resultCallback);
        }
    }

    private void e(String str, String str2, OutputStream outputStream, ResultCallback resultCallback) {
        try {
            b(str, str2, outputStream, resultCallback);
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "readFromWearableImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "readFromWearableImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    private void b(String str, String str2, final OutputStream outputStream, final ResultCallback resultCallback) throws RemoteException {
        this.c.readFromWearable(str, str2, new IReadCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.22
            @Override // com.huawei.hihealth.IReadCallback
            public void onResult(int i, String str3, byte[] bArr) {
                if (i == 0) {
                    try {
                        OutputStream outputStream2 = outputStream;
                        if (outputStream2 != null && bArr != null) {
                            outputStream2.write(bArr);
                        }
                        resultCallback.onResultHandler(0, str3);
                        return;
                    } catch (IOException unused) {
                        Log.e("HiHealthKit", "readFromWearableAidl IOException");
                        HiHealthKitApi.this.d(resultCallback, 4, HiHealthError.e(4));
                        return;
                    }
                }
                resultCallback.onResultHandler(i, str3);
            }
        });
    }

    /* synthetic */ void e(ResultCallback resultCallback, InputStream inputStream, String str, String str2) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "writeToWearable:mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
        } else {
            a(inputStream, str, str2, resultCallback);
        }
    }

    private void d(String str, String str2, byte[] bArr, String str3, final ResultCallback resultCallback) {
        try {
            this.c.writeToWearable(str, str2, bArr, str3, new IWriteCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.23
                @Override // com.huawei.hihealth.IWriteCallback
                public void onResult(int i, String str4) {
                    resultCallback.onResultHandler(HiHealthError.c(i), str4);
                }
            });
        } catch (TransactionTooLargeException unused) {
            Log.e("HiHealthKit", "writeToWearable TransactionTooLargeException");
            d(resultCallback, 1021, HiHealthError.e(1021));
        } catch (RemoteException unused2) {
            Log.e("HiHealthKit", "writeToWearable RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused3) {
            Log.e("HiHealthKit", "writeToWearable Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    private void a(InputStream inputStream, String str, String str2, ResultCallback resultCallback) {
        byte[] bArr;
        boolean z;
        if (inputStream != null) {
            Log.i("HiHealthKit", "writeToWearableImpl is a big file.");
            try {
                try {
                    try {
                        int available = inputStream.available();
                        byte[] bArr2 = new byte[51200];
                        boolean z2 = false;
                        int i = available;
                        while (i > 0) {
                            if (i >= 51200) {
                                z = z2;
                                bArr = bArr2;
                            } else {
                                bArr = new byte[i];
                                z = true;
                            }
                            int read = inputStream.read(bArr);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("size", available);
                            jSONObject.put("is_finished", z);
                            boolean z3 = z;
                            d(str, str2, bArr, jSONObject.toString(), resultCallback);
                            i -= read;
                            z2 = z3;
                        }
                    } catch (JSONException unused) {
                        Log.e("HiHealthKit", "writeToWearableImpl JSONException");
                        d(resultCallback, 4, HiHealthError.e(4));
                        d(str, str2, null, null, resultCallback);
                    }
                } catch (IOException unused2) {
                    Log.e("HiHealthKit", "writeToWearableImpl IOException");
                    d(resultCallback, 4, HiHealthError.e(4));
                    d(str, str2, null, null, resultCallback);
                }
                try {
                    inputStream.close();
                    return;
                } catch (IOException unused3) {
                    Log.e("HiHealthKit", "writeToWearableImpl:close inputStream IOException");
                    return;
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                    Log.e("HiHealthKit", "writeToWearableImpl:close inputStream IOException");
                }
                throw th;
            }
        }
        Log.i("HiHealthKit", "writeToWearableImpl is not a big file.");
        d(str, str2, null, null, resultCallback);
    }

    public void a(final HiSportDataCallback hiSportDataCallback) {
        Log.i("HiHealthKit", "startRealTimeSportData");
        if (hiSportDataCallback == null) {
            Log.w("HiHealthKit", "startRealTimeSportData callback is null");
        } else {
            this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda18
                @Override // java.lang.Runnable
                public final void run() {
                    HiHealthKitApi.this.c(hiSportDataCallback);
                }
            });
        }
    }

    /* synthetic */ void c(HiSportDataCallback hiSportDataCallback) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "fetchRealTimeSportData mApiAidl is null");
            hiSportDataCallback.onResultHandler(1);
        } else {
            b(hiSportDataCallback);
        }
    }

    private void b(final HiSportDataCallback hiSportDataCallback) {
        try {
            this.c.registerRealTimeSportCallback(new ISportDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.24
                @Override // com.huawei.hihealth.ISportDataCallback
                public void onResult(int i) {
                    if (i == 0) {
                        HiHealthKitApi.this.j.add(hiSportDataCallback);
                    }
                    Log.i("HiHealthKit", "startRealTimeSportDataImpl onResultHandler errCode = " + i);
                    hiSportDataCallback.onResultHandler(HiHealthError.c(i));
                }

                @Override // com.huawei.hihealth.ISportDataCallback
                public void onDataChanged(int i, Bundle bundle) {
                    Log.i("HiHealthKit", "startRealTimeSportDataImpl onDataChanged sportState = " + i);
                    Log.i("HiHealthKit", "startRealTimeSportDataImpl onDataChanged bundle = " + bundle);
                    hiSportDataCallback.onDataChangedHandler(i, bundle);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "startRealTimeSportDataImpl RemoteException");
            hiSportDataCallback.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "startRealTimeSportDataImpl Exception");
            hiSportDataCallback.onResultHandler(4);
        }
    }

    public void e(final HiSportDataCallback hiSportDataCallback) {
        Log.i("HiHealthKit", "stopRealTimeSportData");
        if (hiSportDataCallback == null) {
            Log.w("HiHealthKit", "stopRealTimeSportData callback is null");
        } else {
            this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda12
                @Override // java.lang.Runnable
                public final void run() {
                    HiHealthKitApi.this.d(hiSportDataCallback);
                }
            });
        }
    }

    /* synthetic */ void d(HiSportDataCallback hiSportDataCallback) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "stopRealTimeSportData mApiAidl is null");
            hiSportDataCallback.onResultHandler(1);
        } else {
            i(hiSportDataCallback);
        }
    }

    private void i(final HiSportDataCallback hiSportDataCallback) {
        try {
            this.c.unregisterRealTimeSportCallback(new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.25
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str) {
                    if (i == 0) {
                        HiHealthKitApi.this.j.remove(hiSportDataCallback);
                    }
                    Log.i("HiHealthKit", "stopRealTimeSportDataImpl errorCode = " + i);
                    hiSportDataCallback.onResultHandler(HiHealthError.c(i));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "stopRealTimeSportDataImpl RemoteException");
            hiSportDataCallback.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "stopRealTimeSportDataImpl Exception");
            hiSportDataCallback.onResultHandler(4);
        }
    }

    /* synthetic */ void a(ResultCallback resultCallback, String str) {
        d();
        if (this.c == null) {
            d(resultCallback, 1, "getSwitch mApiAidl is null");
            Log.w("HiHealthKit", "getSwitch mApiAidl is null");
        } else {
            a(str, resultCallback);
        }
    }

    private void a(String str, final ResultCallback resultCallback) {
        try {
            this.c.getSwitch(c(), str, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.26
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str2) {
                    Log.i("HiHealthKit", "getSwitchImpl onResultHandler errCode = " + i);
                    HiHealthKitApi.this.d(resultCallback, i, str2);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getSwitchImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getSwitchImpl Exception");
            d(resultCallback, 4, HiHealthError.e(4));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ResultCallback resultCallback, int i, Object obj) {
        if (resultCallback != null) {
            resultCallback.onResultHandler(i, obj);
        }
    }

    /* synthetic */ void b(int i, final ResultCallback resultCallback) {
        d();
        try {
            this.c.startSport(i, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.27
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i2, String str) throws RemoteException {
                    Log.i("HiHealthKit", "start sport errorCode = " + i2);
                    resultCallback.onResultHandler(i2, str);
                    if (i2 == 0) {
                        HiHealthKitApi.this.c.setBinder(HiHealthKitApi.this.e);
                    }
                }
            });
        } catch (RemoteException unused) {
            Log.w("HiHealthKit", "remote Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.w("HiHealthKit", "unknown Exception");
            resultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    /* synthetic */ void i(ResultCallback resultCallback) {
        d();
        if (this.c == null) {
            resultCallback.onResultHandler(1, HiHealthError.e(1));
            Log.w("HiHealthKit", "getServiceApiLevel mApiAidl is null");
        } else {
            resultCallback.onResultHandler(0, Integer.valueOf(iff.c()));
        }
    }

    public void c(final HiHealthCapabilityQuery hiHealthCapabilityQuery, final CapabilityResultCallback capabilityResultCallback) {
        Log.i("HiHealthKit", "enter getHealthyLivingData");
        if (capabilityResultCallback == null) {
            Log.w("HiHealthKit", "callback is null");
        } else {
            this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda20
                @Override // java.lang.Runnable
                public final void run() {
                    HiHealthKitApi.this.a(capabilityResultCallback, hiHealthCapabilityQuery);
                }
            });
        }
    }

    /* synthetic */ void a(final CapabilityResultCallback capabilityResultCallback, HiHealthCapabilityQuery hiHealthCapabilityQuery) {
        d();
        IHiHealthKit iHiHealthKit = this.c;
        if (iHiHealthKit == null) {
            capabilityResultCallback.onResultHandler(1, "getHealthyLivingData mApiAidl is null");
            Log.w("HiHealthKit", "execQuery mApiAidl is null");
            return;
        }
        try {
            iHiHealthKit.getHealthyLivingData(c(), hiHealthCapabilityQuery, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.28
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str) {
                    Log.i("HiHealthKit", "getHealthyLivingData result : " + i);
                    capabilityResultCallback.onResultHandler(HiHealthError.c(i), str);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "getHealthyLivingData RemoteException");
            capabilityResultCallback.onResultHandler(4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "getHealthyLivingData Exception");
            capabilityResultCallback.onResultHandler(4, HiHealthError.e(4));
        }
    }

    public void e(final HealthKitDictQuery healthKitDictQuery, final IuniversalCallback iuniversalCallback) {
        Log.i("HiHealthKit", "enter queryData");
        this.f.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitApi$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitApi.this.b(iuniversalCallback, healthKitDictQuery);
            }
        });
    }

    /* synthetic */ void b(IuniversalCallback iuniversalCallback, HealthKitDictQuery healthKitDictQuery) {
        d();
        if (this.c == null) {
            Log.w("HiHealthKit", "queryData mApiAidl is null");
            iuniversalCallback.onResultHandler(1, null, HiHealthError.e(1));
        } else if (!iff.c(healthKitDictQuery.getSampleType())) {
            iuniversalCallback.onResultHandler(30, null, HiHealthError.e(30));
        } else {
            b(healthKitDictQuery, iuniversalCallback);
        }
    }

    private void b(final HealthKitDictQuery healthKitDictQuery, final IuniversalCallback iuniversalCallback) {
        try {
            final ArrayList arrayList = new ArrayList(10);
            this.c.queryData(healthKitDictQuery, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitApi.29
                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i, int i2) {
                    Log.i("HiHealthKit", "enter KitAPI queryDataImp onSuccess " + i);
                    int c = HiHealthError.c(i);
                    if (list != null) {
                        DictDataTransformUtil.e(HiHealthKitApi.this.d(healthKitDictQuery.getSampleType()), list, arrayList);
                    }
                    DictDataTransformUtil.a(c, i2, arrayList, iuniversalCallback);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKit", "queryDataImp RemoteException");
            iuniversalCallback.onResultHandler(4, null, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKit", "queryDataImp Exception");
            iuniversalCallback.onResultHandler(4, null, HiHealthError.e(4));
        }
    }

    /* synthetic */ void b() {
        Log.i("HiHealthKit", "enter unBindHealthService");
        if (this.c != null) {
            try {
                d.unbindService(Instance.c);
                this.c = null;
                return;
            } catch (Exception unused) {
                Log.w("HiHealthKit", "unbind service exception");
                return;
            }
        }
        Log.w("HiHealthKit", "no need to unbind");
    }
}
