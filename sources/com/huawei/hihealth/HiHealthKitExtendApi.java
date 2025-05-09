package com.huawei.hihealth;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
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
import com.huawei.hihealthkit.context.OutOfBandContext;
import com.huawei.hihealthkit.context.OutOfBandData;
import com.huawei.hihealthkit.data.store.HiRealTimeListener;
import com.huawei.hihealthkit.data.store.HiSportDataCallback;
import com.huawei.hihealthkit.data.type.HiHealthDataType;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.nfc.PluginPayAdapter;
import defpackage.idw;
import defpackage.idx;
import defpackage.idy;
import defpackage.idz;
import defpackage.ifa;
import defpackage.ife;
import defpackage.iff;
import defpackage.ifi;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HiHealthKitExtendApi implements ServiceConnection {
    private static volatile OutOfBandContext b;
    private IHiHealthKitEx c;
    private volatile boolean e;
    private CountDownLatch h;
    private ExecutorService i;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4014a = new Object();

    private HiHealthKitExtendApi() {
        this.e = false;
        this.i = Executors.newSingleThreadExecutor();
    }

    public static HiHealthKitExtendApi d(OutOfBandContext outOfBandContext) {
        HiHealthKitExtendApi hiHealthKitExtendApi;
        synchronized (HiHealthKitExtendApi.class) {
            if (outOfBandContext != null) {
                b = outOfBandContext;
            }
            hiHealthKitExtendApi = Instance.e;
        }
        return hiHealthKitExtendApi;
    }

    public void b(final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.g(resultCallback);
            }
        });
    }

    /* synthetic */ void g(final ResultCallback resultCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "getGender mApiAidl is null");
            Log.w("HiHealthKitExtend", "getGender mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getGender(b.getOutOfBandData(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.1
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKitExtend", "enter KitExAPI getGender onSuccess");
                    if (list == null || list.size() <= 0) {
                        HiHealthKitExtendApi.this.d(resultCallback, 1, HiHealthError.e(1));
                    } else {
                        HiHealthKitExtendApi.this.d(resultCallback, 0, Integer.valueOf(((Integer) list.get(0)).intValue()));
                    }
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    int c = HiHealthError.c(i);
                    HiHealthKitExtendApi.this.d(resultCallback, c, HiHealthError.e(c));
                    Log.i("HiHealthKitExtend", "get gender onfailure");
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "get gender RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getGender Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void a(final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.i(resultCallback);
            }
        });
    }

    /* synthetic */ void i(final ResultCallback resultCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "getBirthday mApiAidl is null");
            Log.w("HiHealthKitExtend", "getBirthday mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getBirthday(b.getOutOfBandData(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.2
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKitExtend", "enter KitAPI getBirthday onSuccess");
                    if (list == null || list.size() <= 0) {
                        HiHealthKitExtendApi.this.d(resultCallback, 1, HiHealthError.e(1));
                    } else {
                        HiHealthKitExtendApi.this.d(resultCallback, 0, Integer.valueOf(((Integer) list.get(0)).intValue()));
                    }
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    Log.i("HiHealthKitExtend", "get birthday onfailure");
                    int c = HiHealthError.c(i);
                    HiHealthKitExtendApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "get birthday RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "get birthday Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void c(final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.h(resultCallback);
            }
        });
    }

    /* synthetic */ void h(final ResultCallback resultCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "getHeight mApiAidl is null");
            Log.w("HiHealthKitExtend", "getHeight mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getHeight(b.getOutOfBandData(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.3
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKitExtend", "getHeight:onSuccess");
                    if (list == null || list.size() <= 0) {
                        HiHealthKitExtendApi.this.d(resultCallback, 1, HiHealthError.e(1));
                        return;
                    }
                    int intValue = ((Integer) list.get(0)).intValue();
                    Log.d("HiHealthKitExtend", "getHeight height: " + intValue);
                    HiHealthKitExtendApi.this.d(resultCallback, 0, Integer.valueOf(intValue));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    Log.i("HiHealthKitExtend", "getHeight onfailure");
                    int c = HiHealthError.c(i);
                    HiHealthKitExtendApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "getHeight RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getHeight Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void e(final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.o(resultCallback);
            }
        });
    }

    /* synthetic */ void o(final ResultCallback resultCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "getWeight mApiAidl is null");
            Log.w("HiHealthKitExtend", "getWeight mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getWeight(b.getOutOfBandData(), new ICommonListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.4
                @Override // com.huawei.hihealth.ICommonListener
                public void onSuccess(int i, List list) {
                    Log.i("HiHealthKitExtend", "enter KitAPI getWeight onSuccess");
                    if (list == null || list.size() <= 0) {
                        HiHealthKitExtendApi.this.d(resultCallback, 1, HiHealthError.e(1));
                        return;
                    }
                    float floatValue = ((Float) list.get(0)).floatValue();
                    Log.i("HiHealthKitExtend", "getWeight onSuccess weight: " + floatValue);
                    HiHealthKitExtendApi.this.d(resultCallback, 0, Float.valueOf(floatValue));
                }

                @Override // com.huawei.hihealth.ICommonListener
                public void onFailure(int i, List list) {
                    Log.i("HiHealthKitExtend", "get weight onfailure");
                    int c = HiHealthError.c(i);
                    HiHealthKitExtendApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "get weight RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "get weight Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void c(final HiHealthDataQuery hiHealthDataQuery, final int i, final ResultCallback resultCallback) {
        Log.i("HiHealthKitExtend", "enter execQuery");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.b(resultCallback, hiHealthDataQuery, i);
            }
        });
    }

    /* synthetic */ void b(final ResultCallback resultCallback, final HiHealthDataQuery hiHealthDataQuery, int i) {
        c();
        if (this.c == null) {
            d(resultCallback, 1, "execQuery mApiAidl is null");
            Log.w("HiHealthKitExtend", "execQuery mApiAidl is null");
        } else {
            if (!iff.b(hiHealthDataQuery.getSampleType())) {
                resultCallback.onResultHandler(30, HiHealthError.e(30));
                return;
            }
            try {
                this.c.execQuery(b.getOutOfBandData(), hiHealthDataQuery, i, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.5

                    /* renamed from: a, reason: collision with root package name */
                    final List f4043a = new ArrayList(10);

                    @Override // com.huawei.hihealth.IDataReadResultListener
                    public void onResult(List list, int i2, int i3) {
                        Log.i("HiHealthKitExtend", "enter KitAPI execQuery onSuccess, errorCode = " + i2 + "resultType = " + i3);
                        HiHealthKitExtendApi.this.a(i2, hiHealthDataQuery.getSampleType(), list, this.f4043a);
                        a(i2, i3);
                    }

                    private void a(int i2, int i3) {
                        if (i3 == 2) {
                            int c = HiHealthError.c(i2);
                            if (this.f4043a.size() == 0) {
                                HiHealthKitExtendApi.this.d(resultCallback, c, c == 0 ? new ArrayList(10) : HiHealthError.e(c));
                            } else {
                                HiHealthKitExtendApi.this.d(resultCallback, c, this.f4043a);
                            }
                            this.f4043a.clear();
                        }
                    }
                });
            } catch (RemoteException unused) {
                Log.e("HiHealthKitExtend", "exec query RemoteException");
            } catch (Exception unused2) {
                Log.e("HiHealthKitExtend", "exec query Exception");
            }
        }
    }

    /* renamed from: com.huawei.hihealth.HiHealthKitExtendApi$6, reason: invalid class name */
    class AnonymousClass6 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ResultCallback f4044a;
        final /* synthetic */ int b;
        final /* synthetic */ OutputStream c;
        final /* synthetic */ HiHealthDataQuery d;
        final /* synthetic */ HiHealthKitExtendApi e;

        @Override // java.lang.Runnable
        public void run() {
            this.e.c();
            if (this.e.c == null) {
                this.e.d(this.f4044a, 1, "execQuery mApiAidl is null");
                Log.w("HiHealthKitExtend", "execQuery mApiAidl is null");
                return;
            }
            try {
                this.e.c.execQuery(HiHealthKitExtendApi.b.getOutOfBandData(), this.d, this.b, this.e.new SequenceDataProxy(this.c, this.f4044a));
            } catch (RemoteException unused) {
                Log.e("HiHealthKitExtend", "exec query ecgData RemoteException");
            } catch (Exception unused2) {
                Log.e("HiHealthKitExtend", "exec query ecgData Exception");
            }
        }
    }

    public void c(final HiHealthAggregateQuery hiHealthAggregateQuery, final int i, final ResultCallback resultCallback) {
        Log.i("HiHealthKitExtend", "enter execQuery");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.b(resultCallback, hiHealthAggregateQuery, i);
            }
        });
    }

    /* synthetic */ void b(final ResultCallback resultCallback, final HiHealthAggregateQuery hiHealthAggregateQuery, int i) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "exec query mApiAidl is null");
            Log.w("HiHealthKitExtend", "execQuery mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.execAggregateQuery(b.getOutOfBandData(), hiHealthAggregateQuery, i, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.7
                boolean c;

                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i2, int i3) {
                    Log.i("HiHealthKitExtend", "execQuery execAggregateQuery onSuccess");
                    if (list != null) {
                        Log.i("HiHealthKitExtend", "execQuery, datas size = " + list.size() + ", errorCode = " + i2);
                        ArrayList arrayList = new ArrayList(10);
                        HiHealthKitExtendApi.this.a(i2, hiHealthAggregateQuery.getSampleType(), list, arrayList);
                        this.c = true;
                        resultCallback.onResultHandler(0, arrayList);
                        return;
                    }
                    if (this.c) {
                        return;
                    }
                    Log.i("HiHealthKitExtend", "execQuery, datas == null");
                    int c = i2 != hiHealthAggregateQuery.getSampleType() ? HiHealthError.c(i2) : 0;
                    HiHealthKitExtendApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "execQuery, RemoteException");
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "execQuery, Exception");
        }
    }

    class SequenceDataProxy extends IDataReadResultListener.Stub {

        /* renamed from: a, reason: collision with root package name */
        final List<HiHealthKitData> f4047a = new ArrayList(10);
        private ResultCallback b;
        private OutputStream c;

        SequenceDataProxy(OutputStream outputStream, ResultCallback resultCallback) {
            this.c = outputStream;
            this.b = resultCallback;
        }

        @Override // com.huawei.hihealth.IDataReadResultListener
        public void onResult(List list, int i, int i2) {
            Log.i("HiHealthKitExtend", "enter KitAPI execQuery ecgData onSuccess");
            if (i != 0) {
                Log.i("HiHealthKitExtend", "allData is Empty");
                int c = HiHealthError.c(i);
                HiHealthKitExtendApi.this.d(this.b, c, HiHealthError.e(c));
                return;
            }
            if (c(list, i2)) {
                if (!this.f4047a.isEmpty()) {
                    Log.i("HiHealthKitExtend", "data size = " + this.f4047a.size() + ", error code = " + i);
                    if (!c()) {
                        Log.e("HiHealthKitExtend", "sequenceDetail unmatched size");
                        this.b.onResultHandler(4, null);
                        return;
                    } else {
                        this.b.onResultHandler(0, b());
                        return;
                    }
                }
                Log.i("HiHealthKitExtend", "allData is Empty");
                int c2 = HiHealthError.c(i);
                HiHealthKitExtendApi.this.d(this.b, c2, HiHealthError.e(c2));
            }
        }

        private List b() {
            ArrayList arrayList = new ArrayList(1);
            HiHealthKitData hiHealthKitData = this.f4047a.get(0);
            Map map = hiHealthKitData.getMap();
            map.remove("detail_data");
            idx idxVar = new idx(hiHealthKitData.getType(), map, hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime());
            HiHealthKitExtendApi.this.b(hiHealthKitData, idxVar);
            arrayList.add(idxVar);
            return arrayList;
        }

        private boolean c() {
            StringBuilder sb = new StringBuilder();
            Iterator<HiHealthKitData> it = this.f4047a.iterator();
            while (it.hasNext()) {
                Map map = it.next().getMap();
                if (map.containsKey("detail_data")) {
                    sb.append((String) map.get("detail_data"));
                }
            }
            try {
                if (sb.length() != this.f4047a.get(0).getLong("size")) {
                    return false;
                }
                try {
                    HiZipUtil.d(sb.toString(), this.c);
                } catch (IOException unused) {
                    Log.i("HiHealthKitExtend", "enter query ecgData IOException");
                }
                e(this.c);
                return true;
            } catch (Throwable th) {
                e(this.c);
                throw th;
            }
        }

        private void e(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    Log.e("HiHealthKitExtend", e.getMessage());
                }
            }
        }

        private boolean c(List<HiHealthKitData> list, int i) {
            if (list != null && !list.isEmpty()) {
                this.f4047a.addAll(list);
            }
            return i == 1;
        }
    }

    /* renamed from: com.huawei.hihealth.HiHealthKitExtendApi$26, reason: invalid class name */
    static /* synthetic */ class AnonymousClass26 {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, List list, List list2) {
        switch (AnonymousClass26.c[b(i2).ordinal()]) {
            case 1:
            case 2:
            case 3:
                d(list, list2, i2);
                break;
            case 4:
                d(list, list2);
                break;
            case 5:
            case 6:
                c(list, list2);
                break;
        }
        if (i == i2) {
            d((List<idy>) list2, i);
        }
    }

    public HiHealthDataType.Category b(int i) {
        HiHealthDataType.Category a2;
        c();
        if (this.c != null && iff.d("get_category")) {
            try {
                a2 = HiHealthDataType.Category.valueOf(this.c.getCategory(i));
            } catch (RemoteException unused) {
                Log.e("HiHealthKitExtend", "getCategory RemoteException");
                a2 = HiHealthDataType.a(i);
            }
        } else {
            a2 = HiHealthDataType.a(i);
        }
        return i == 44000 ? HiHealthDataType.Category.SET : a2;
    }

    private void d(List<idy> list, int i) {
        Iterator<idy> it = list.iterator();
        while (it.hasNext()) {
            it.next().setType(i);
        }
    }

    public void b(final HiHealthDataQuery hiHealthDataQuery, final int i, final ResultCallback resultCallback) {
        Log.i("HiHealthKitExtend", "enter querySleepWakeTime");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda22
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.e(resultCallback, hiHealthDataQuery, i);
            }
        });
    }

    /* synthetic */ void e(final ResultCallback resultCallback, HiHealthDataQuery hiHealthDataQuery, int i) {
        c();
        if (this.c == null) {
            d(resultCallback, 1, "querySleepWakeTime mApiAidl is null");
            Log.w("HiHealthKitExtend", "querySleepWakeTime mApiAidl is null");
            return;
        }
        if (hiHealthDataQuery.getEndTime() - hiHealthDataQuery.getStartTime() > 345600000) {
            d(resultCallback, 1, "querySleepWakeTime, the period of time should be less than 96 hours.");
            Log.w("HiHealthKitExtend", "The period of time should be less than 96 hours.");
            return;
        }
        try {
            this.c.querySleepWakeTime(b.getOutOfBandData(), hiHealthDataQuery, i, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.8
                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i2, int i3) {
                    Log.i("HiHealthKitExtend", "enter KitAPI querySleepWakeTime onSuccess");
                    if (list != null) {
                        Log.i("HiHealthKitExtend", "datas size = " + list.size() + ", error code = " + i2);
                        ArrayList arrayList = new ArrayList(10);
                        HiHealthKitExtendApi.this.c(list, arrayList);
                        resultCallback.onResultHandler(i2, arrayList);
                        return;
                    }
                    Log.i("HiHealthKitExtend", "datas == null");
                    int c = HiHealthError.c(i2);
                    HiHealthKitExtendApi.this.d(resultCallback, c, HiHealthError.e(c));
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "querySleepWakeTime RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getSwitch Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void b(final HiHealthDataQuery hiHealthDataQuery, final ResultCallback resultCallback) {
        Log.i("HiHealthKitExtend", "enter getCount");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.a(resultCallback, hiHealthDataQuery);
            }
        });
    }

    /* synthetic */ void a(final ResultCallback resultCallback, HiHealthDataQuery hiHealthDataQuery) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "getCount mApiAidl is null");
            Log.w("HiHealthKitExtend", "getCount mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getCount(b.getOutOfBandData(), hiHealthDataQuery, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.9
                private int c;

                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i, int i2) {
                    Log.i("HiHealthKitExtend", "enter KitAPI getCount onSuccess, errorCode = " + i + " resultType = " + i2);
                    if (list != null && !list.isEmpty() && (list.get(0) instanceof Integer)) {
                        this.c += ((Integer) list.get(0)).intValue();
                    }
                    if (i2 == 2) {
                        resultCallback.onResultHandler(HiHealthError.c(i), Integer.valueOf(this.c));
                        this.c = 0;
                    }
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "getCount RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getCount Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiHealthKitData hiHealthKitData, idy idyVar) {
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

    private void d(List list, List list2, int i) {
        idz idzVar;
        if (list != null) {
            Log.i("HiHealthKitExtend", "handlePointData size = " + list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthKitData hiHealthKitData = (HiHealthKitData) it.next();
                if (ifa.e(i)) {
                    idzVar = new idz(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime(), hiHealthKitData.getDoubleValue(), 0);
                } else if (ifa.d(i)) {
                    idzVar = new idz(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime(), hiHealthKitData.getString("metadata"), 0);
                } else {
                    idzVar = new idz(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime(), hiHealthKitData.getIntValue(), 0);
                }
                b(hiHealthKitData, idzVar);
                idzVar.setUpdateTime(hiHealthKitData.getLong("update_time"));
                list2.add(idzVar);
            }
            return;
        }
        Log.i("HiHealthKitExtend", "point data null");
    }

    private void d(List list, List list2) {
        if (list != null) {
            Log.i("HiHealthKitExtend", "handleSessionData size = " + list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthKitData hiHealthKitData = (HiHealthKitData) it.next();
                idw idwVar = new idw(hiHealthKitData.getType(), hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime());
                b(hiHealthKitData, idwVar);
                idwVar.setUpdateTime(hiHealthKitData.getLong("update_time"));
                list2.add(idwVar);
            }
            return;
        }
        Log.w("HiHealthKitExtend", "session data null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List list, List list2) {
        if (list != null) {
            Log.i("HiHealthKitExtend", "handleSetData size = " + list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HiHealthKitData hiHealthKitData = (HiHealthKitData) it.next();
                Map map = hiHealthKitData.getMap();
                try {
                    if (map.containsKey("detail_data")) {
                        Object obj = map.get("detail_data");
                        if (obj instanceof String) {
                            map.remove("detail_data");
                            map.put("detail_data", HiZipUtil.a((String) obj));
                        }
                    }
                } catch (IOException unused) {
                    Log.i("HiHealthKitExtend", "enter query ecgData IOException");
                }
                idx idxVar = new idx(hiHealthKitData.getType(), map, hiHealthKitData.getStartTime(), hiHealthKitData.getEndTime());
                b(hiHealthKitData, idxVar);
                idxVar.setMetaData(hiHealthKitData.getString("metadata"));
                idxVar.setUpdateTime(hiHealthKitData.getLong("update_time"));
                list2.add(idxVar);
            }
        }
    }

    public void c(final idy idyVar, final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.c(resultCallback, idyVar);
            }
        });
    }

    /* synthetic */ void c(final ResultCallback resultCallback, idy idyVar) {
        c();
        if (this.c == null) {
            Log.w("HiHealthKitExtend", "saveSample mApiAidl is null");
            d(resultCallback, 1, "saveSample mApiAidl is null");
            return;
        }
        try {
            HiHealthKitData d2 = ifi.d(idyVar, b(idyVar.getType()));
            if (!iff.d(d2.getType())) {
                resultCallback.onResultHandler(30, HiHealthError.e(30));
            } else {
                this.c.saveSample(b.getOutOfBandData(), d2, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.10
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list) {
                        Log.i("HiHealthKitExtend", "enter saveSample result code " + i);
                        resultCallback.onResultHandler(HiHealthError.c(i), list);
                    }
                });
            }
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "save sample RemoteException");
            resultCallback.onResultHandler(4, null);
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "save sample Exception");
            resultCallback.onResultHandler(4, null);
        }
    }

    public void b(final List<idy> list, final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.c(resultCallback, list);
            }
        });
    }

    /* synthetic */ void c(ResultCallback resultCallback, List list) {
        Log.i("HiHealthKitExtend", "enter saveSamples");
        c();
        if (this.c == null) {
            d(resultCallback, 1, "saveSamples mApiAidl is null");
            Log.w("HiHealthKitExtend", "saveSamples mApiAidl is null");
            return;
        }
        if (list == null || (list.size() > 20 && !iff.d("divide_save_samples"))) {
            resultCallback.onResultHandler(2, "too much datas!");
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!iff.d(((idy) it.next()).getType())) {
                resultCallback.onResultHandler(30, HiHealthError.e(30));
                return;
            }
        }
        int[] iArr = {4};
        Object[] objArr = {HiHealthError.e(4)};
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            idy idyVar = (idy) it2.next();
            arrayList.add(ifi.d(idyVar, b(idyVar.getType())));
        }
        int d2 = d(b.getOutOfBandData());
        if (!iff.d("divide_save_samples")) {
            if (d2 > 0) {
                d(arrayList, resultCallback, iArr, objArr);
                return;
            } else {
                b(arrayList, resultCallback, iArr, objArr);
                return;
            }
        }
        if (ifi.e(arrayList)) {
            b(iArr, objArr, arrayList);
            d(resultCallback, iArr[0], objArr[0]);
        } else {
            a(iArr, objArr, arrayList);
            d(resultCallback, iArr[0], objArr[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.String] */
    private void a(final int[] iArr, final Object[] objArr, List<HiHealthKitData> list) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            try {
                this.c.saveSamples(b.getOutOfBandData(), list, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.11
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list2) {
                        Log.i("HiHealthKitExtend", "enter saveSamplesImpl result errorCode:" + i);
                        iArr[0] = HiHealthError.c(i);
                        objArr[0] = list2;
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await(30L, TimeUnit.SECONDS);
                    iArr = iArr;
                } catch (InterruptedException unused) {
                    Log.e("HiHealthKitExtend", "saveSamplesImpl interrupted exception");
                    iArr[0] = 4;
                    ?? e = HiHealthError.e(4);
                    objArr[0] = e;
                    iArr = e;
                }
            } catch (RemoteException unused2) {
                Log.e("HiHealthKitExtend", "saveSamplesImpl RemoteException");
                b(countDownLatch);
                iArr[0] = 4;
                objArr[0] = HiHealthError.e(4);
            }
        } finally {
            try {
                countDownLatch.await(30L, TimeUnit.SECONDS);
            } catch (InterruptedException unused3) {
                Log.e("HiHealthKitExtend", "saveSamplesImpl interrupted exception");
                iArr[0] = 4;
                objArr[0] = HiHealthError.e(4);
            }
        }
    }

    private void b(int[] iArr, Object[] objArr, List<HiHealthKitData> list) {
        int b2 = ifi.b(list);
        int size = list.size();
        int i = (int) ((size * 524288.0f) / b2);
        if (i == 0) {
            i = 1;
        }
        Log.i("HiHealthKitExtend", "divideSaveSamplesImpl dataList size = " + size + ", parcelSize = " + b2 + ", divideLength = " + i);
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + i;
            if (i3 >= size) {
                a(iArr, objArr, list.subList(i2, size));
            } else {
                a(iArr, objArr, list.subList(i2, i3));
            }
            i2 = i3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    private void d(List<HiHealthKitData> list, ResultCallback resultCallback, int[] iArr, final Object[] objArr) {
        ?? r7;
        int c;
        ?? r72;
        ?? r73;
        final int[] iArr2 = iArr;
        String str = "save samples interrupted exception";
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        try {
            try {
                this.c.saveSamples(b.getOutOfBandData(), list, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.12
                    @Override // com.huawei.hihealth.IDataOperateListener
                    public void onResult(int i, List list2) {
                        Log.i("HiHealthKitExtend", "saveSamples result errorCode " + i);
                        iArr2[0] = i;
                        objArr[0] = list2;
                        if (i != 0) {
                            HiHealthKitExtendApi.this.b(countDownLatch);
                        }
                        if (countDownLatch.getCount() > 0) {
                            countDownLatch.countDown();
                        }
                    }
                });
                try {
                    countDownLatch.await(3L, TimeUnit.SECONDS);
                    r73 = str;
                } catch (InterruptedException unused) {
                    Log.e("HiHealthKitExtend", "save samples interrupted exception");
                    r73 = 4;
                    iArr2[0] = 4;
                    objArr[0] = "InterruptedException";
                }
                c = HiHealthError.c(iArr2[0]);
                iArr2 = objArr[0];
                str = r73;
            } catch (Throwable th) {
                try {
                    countDownLatch.await(3L, TimeUnit.SECONDS);
                } catch (InterruptedException unused2) {
                    Log.e("HiHealthKitExtend", str);
                    iArr2[0] = 4;
                    objArr[0] = "InterruptedException";
                }
                resultCallback.onResultHandler(HiHealthError.c(iArr2[0]), objArr[0]);
                Log.i("HiHealthKitExtend", "saveSamples end");
                throw th;
            }
        } catch (RemoteException unused3) {
            Log.e("HiHealthKitExtend", "save samples RemoteException");
            iArr2[0] = 4;
            objArr[0] = "RemoteException";
            try {
                countDownLatch.await(3L, TimeUnit.SECONDS);
                r72 = str;
            } catch (InterruptedException unused4) {
                Log.e("HiHealthKitExtend", "save samples interrupted exception");
                r72 = 4;
                iArr2[0] = 4;
                objArr[0] = "InterruptedException";
            }
            c = HiHealthError.c(iArr2[0]);
            iArr2 = objArr[0];
            str = r72;
        } catch (Exception unused5) {
            Log.e("HiHealthKitExtend", "save samples Exception");
            iArr2[0] = 4;
            objArr[0] = HiHealthError.e(4);
            try {
                countDownLatch.await(3L, TimeUnit.SECONDS);
                r7 = str;
            } catch (InterruptedException unused6) {
                Log.e("HiHealthKitExtend", "save samples interrupted exception");
                r7 = 4;
                iArr2[0] = 4;
                objArr[0] = "InterruptedException";
            }
            c = HiHealthError.c(iArr2[0]);
            iArr2 = objArr[0];
            str = r7;
        }
        resultCallback.onResultHandler(c, iArr2);
        Log.i("HiHealthKitExtend", "saveSamples end");
    }

    private void b(List<HiHealthKitData> list, ResultCallback resultCallback, final int[] iArr, final Object[] objArr) {
        int c;
        Object obj;
        try {
            try {
                for (HiHealthKitData hiHealthKitData : list) {
                    this.h = new CountDownLatch(1);
                    this.e = true;
                    Log.i("HiHealthKitExtend", String.valueOf(hiHealthKitData.getStartTime()));
                    this.c.saveSample(b.getOutOfBandData(), hiHealthKitData, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.13
                        @Override // com.huawei.hihealth.IDataOperateListener
                        public void onResult(int i, List list2) {
                            Log.i("HiHealthKitExtend", "enter saveSample errorCode = " + i);
                            iArr[0] = i;
                            objArr[0] = list2;
                            if (HiHealthKitExtendApi.this.h != null) {
                                HiHealthKitExtendApi.this.h.countDown();
                            }
                        }
                    });
                    try {
                        this.h.await();
                    } catch (InterruptedException unused) {
                        Log.e("HiHealthKitExtend", "saveSample InterruptedException");
                    }
                    this.e = false;
                    this.h = null;
                    if (iArr[0] != 0) {
                        break;
                    }
                }
            } catch (RemoteException unused2) {
                Log.e("HiHealthKitExtend", "save sample RemoteException");
                iArr[0] = 4;
                objArr[0] = "RemoteException";
                if (resultCallback != null) {
                    c = HiHealthError.c(4);
                    obj = objArr[0];
                }
            } catch (Exception unused3) {
                Log.e("HiHealthKitExtend", "save sample Exception");
                iArr[0] = 4;
                objArr[0] = "Exception";
                if (resultCallback != null) {
                    c = HiHealthError.c(4);
                    obj = objArr[0];
                }
            }
            if (resultCallback != null) {
                c = HiHealthError.c(iArr[0]);
                obj = objArr[0];
                resultCallback.onResultHandler(c, obj);
            }
            Log.i("HiHealthKitExtend", "saveSamples end");
        } catch (Throwable th) {
            if (resultCallback != null) {
                resultCallback.onResultHandler(HiHealthError.c(iArr[0]), objArr[0]);
            }
            Log.i("HiHealthKitExtend", "saveSamples end");
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CountDownLatch countDownLatch) {
        while (countDownLatch.getCount() > 0) {
            countDownLatch.countDown();
        }
    }

    public void b(final idy idyVar, final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.d(resultCallback, idyVar);
            }
        });
    }

    /* synthetic */ void d(ResultCallback resultCallback, idy idyVar) {
        c();
        if (this.c == null) {
            Log.w("HiHealthKitExtend", "deleteSample mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
        } else if (idyVar == null) {
            Log.w("HiHealthKitExtend", "deleteSample hiHealthData is null");
            d(resultCallback, 2, HiHealthError.e(2));
        } else {
            if (iff.d("delete_sample")) {
                e(idyVar, resultCallback);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(idyVar);
            d(arrayList, resultCallback);
        }
    }

    private void e(idy idyVar, final ResultCallback resultCallback) {
        final int[] iArr = {4};
        final Object[] objArr = new Object[1];
        if (!iff.a(idyVar.getType())) {
            resultCallback.onResultHandler(30, HiHealthError.e(30));
            return;
        }
        try {
            this.c.deleteSample(b.getOutOfBandData(), ifi.d(idyVar, b(idyVar.getType())), new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.14
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list) {
                    Log.i("HiHealthKitExtend", "deleteSampleImpl result:" + i);
                    iArr[0] = i;
                    objArr[0] = list;
                    HiHealthKitExtendApi.this.d(resultCallback, HiHealthError.c(i), objArr[0]);
                }
            });
        } catch (RemoteException unused) {
            Log.w("HiHealthKitExtend", "deleteSampleImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.w("HiHealthKitExtend", "deleteSampleImpl Exception");
            d(resultCallback, 4, "Exception");
        }
    }

    public void e(final List<idy> list, final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.d(resultCallback, list);
            }
        });
    }

    /* synthetic */ void d(ResultCallback resultCallback, List list) {
        c();
        if (this.c == null) {
            d(resultCallback, 1, "deleteSamples mApiAidl is null");
            Log.w("HiHealthKitExtend", "deleteSamples mApiAidl is null");
        } else if (list == null || list.size() > 20) {
            resultCallback.onResultHandler(2, "too much datas!");
        } else {
            d((List<idy>) list, resultCallback);
        }
    }

    private void d(List<idy> list, final ResultCallback resultCallback) {
        final int[] iArr = {4};
        final Object[] objArr = new Object[1];
        try {
            Iterator<idy> it = list.iterator();
            while (it.hasNext()) {
                if (!iff.a(it.next().getType())) {
                    resultCallback.onResultHandler(30, HiHealthError.e(30));
                    return;
                }
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (idy idyVar : list) {
                arrayList.add(ifi.d(idyVar, b(idyVar.getType())));
            }
            this.c.deleteSamples(b.getOutOfBandData(), arrayList, new IDataOperateListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.15
                @Override // com.huawei.hihealth.IDataOperateListener
                public void onResult(int i, List list2) {
                    Log.i("HiHealthKitExtend", "deleteSamplesImpl result:" + i);
                    iArr[0] = i;
                    objArr[0] = list2;
                    HiHealthKitExtendApi.this.d(resultCallback, HiHealthError.c(i), objArr[0]);
                }
            });
        } catch (RemoteException unused) {
            Log.w("HiHealthKitExtend", "deleteSamplesImpl RemoteException");
            d(resultCallback, 4, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.w("HiHealthKitExtend", "deleteSamplesImpl Exception");
            d(resultCallback, 4, "Exception");
        }
    }

    public void b(final HiRealTimeListener hiRealTimeListener) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.c(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void c(HiRealTimeListener hiRealTimeListener) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKitExtend", "startReadingHeartRate mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.startReadingHeartRate(b.getOutOfBandData(), d("startReadingHeartRate", hiRealTimeListener));
            Log.i("HiHealthKitExtend", "startReadingHeartRate end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "startReadingHeartRate RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "startReadingHeartRate Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void j(final HiRealTimeListener hiRealTimeListener) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.a(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void a(HiRealTimeListener hiRealTimeListener) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKitExtend", "stopReadingHeartRate mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.stopReadingHeartRate(b.getOutOfBandData(), d("stopReadingHeartRate", hiRealTimeListener));
            Log.i("HiHealthKitExtend", "stopReadingHeartRate end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "stopReadingHeartRate RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "stopReadingHeartRate Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void i(final HiRealTimeListener hiRealTimeListener) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.e(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void e(HiRealTimeListener hiRealTimeListener) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKitExtend", "startReadingRri mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.startReadingRRI(b.getOutOfBandData(), d("startReadingRRI", hiRealTimeListener));
            Log.i("HiHealthKitExtend", "startReadingRRI end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "startReadingRRI RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "startReadingRRI Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void g(final HiRealTimeListener hiRealTimeListener) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda21
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.d(hiRealTimeListener);
            }
        });
    }

    /* synthetic */ void d(HiRealTimeListener hiRealTimeListener) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            hiRealTimeListener.onResultHandler(1);
            Log.w("HiHealthKitExtend", "stopReadingRri mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.stopReadingRRI(b.getOutOfBandData(), d("stopReadingRRI", hiRealTimeListener));
            Log.i("HiHealthKitExtend", "stopReadingRRI end");
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "stopReadingRRI RemoteException");
            hiRealTimeListener.onResultHandler(4);
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "stopReadingRRI Exception");
            hiRealTimeListener.onResultHandler(4);
        }
    }

    public void d(final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda29
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.f(resultCallback);
            }
        });
    }

    /* synthetic */ void f(ResultCallback resultCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "getDeviceList mApiAidl is null");
            Log.w("HiHealthKitExtend", "getDeviceList mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getDeviceList(b.getOutOfBandData(), c("getDeviceList", resultCallback));
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "getDeviceList RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getDeviceList Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void e(final String str, final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.a(resultCallback, str);
            }
        });
    }

    /* synthetic */ void a(ResultCallback resultCallback, String str) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "sendDeviceCommand mApiAidl is null");
            Log.w("HiHealthKitExtend", "sendDeviceCommand mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.sendDeviceCommand(b.getOutOfBandData(), str, c("sendDeviceCommand", resultCallback));
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "sendDeviceCommand RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "sendDeviceCommand Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void n(final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.m(resultCallback);
            }
        });
    }

    /* synthetic */ void m(ResultCallback resultCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "startReadingAtrial mApiAidl is null");
            Log.w("HiHealthKitExtend", "startReadingAtrial mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.startReadingAtrial(b.getOutOfBandData(), c("startReadingAtrial", resultCallback));
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "startReadingAtrial RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "startReadingAtrial Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void l(final ResultCallback resultCallback) {
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.k(resultCallback);
            }
        });
    }

    /* synthetic */ void k(ResultCallback resultCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "stopReadingAtrial mApiAidl is null");
            Log.w("HiHealthKitExtend", "stopReadingAtrial mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.stopReadingAtrial(b.getOutOfBandData(), c("stopReadingAtrial", resultCallback));
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "stopReadingAtrial RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "stopReadingAtrial Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    /* synthetic */ void a(final ResultCallback resultCallback, String str, String str2) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            Log.e("HiHealthKitExtend", "pushMsgToWearable:mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
            return;
        }
        try {
            iHiHealthKitEx.pushMsgToWearable(b.getOutOfBandData(), str, str2, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.16
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str3) throws RemoteException {
                    resultCallback.onResultHandler(HiHealthError.c(i), str3);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "pushMsgToWearable RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "pushMsgToWearable Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    /* synthetic */ void e(final ResultCallback resultCallback, String str, String str2, final OutputStream outputStream) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            Log.i("HiHealthKitExtend", "readFromWearable:mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
            return;
        }
        try {
            iHiHealthKitEx.readFromWearable(b.getOutOfBandData(), str, str2, new IReadCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.17
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
                            Log.i("HiHealthKitExtend", "readFromWearable IOException");
                            HiHealthKitExtendApi.this.d(resultCallback, 1, HiHealthError.e(1));
                            return;
                        }
                    }
                    resultCallback.onResultHandler(HiHealthError.c(i), str3);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "readFromWearable RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "readFromWearable Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    /* synthetic */ void c(ResultCallback resultCallback, InputStream inputStream, String str, String str2) {
        c();
        if (this.c == null) {
            Log.i("HiHealthKitExtend", "writeToWearable:mApiAidl is null");
            d(resultCallback, 1, HiHealthError.e(1));
        } else if (inputStream != null) {
            Log.i("HiHealthKitExtend", "writeToWearable is a big file.");
            b(inputStream, str, str2, resultCallback);
        } else {
            Log.i("HiHealthKitExtend", "writeToWearable is not a big file.");
            a(str, str2, null, null, resultCallback);
        }
    }

    private void a(String str, String str2, byte[] bArr, String str3, final ResultCallback resultCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(RemoteMessageConst.INPUT_TYPE, str);
            jSONObject.put("inputDescription", str2);
            jSONObject.put("sizeAndFinish", str3);
            this.c.writeToWearable(b.getOutOfBandData(), jSONObject.toString(), bArr, new IWriteCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.18
                @Override // com.huawei.hihealth.IWriteCallback
                public void onResult(int i, String str4) throws RemoteException {
                    resultCallback.onResultHandler(HiHealthError.c(i), str4);
                }
            });
        } catch (TransactionTooLargeException unused) {
            Log.e("HiHealthKitExtend", "writeToWearable TransactionTooLargeException");
            d(resultCallback, 1021, HiHealthError.e(1021));
        } catch (RemoteException unused2) {
            Log.i("HiHealthKitExtend", "writeToWearable RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (JSONException unused3) {
            Log.i("HiHealthKitExtend", "writeToWearable JSONException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused4) {
            Log.i("HiHealthKitExtend", "writeToWearable Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    private void b(InputStream inputStream, String str, String str2, ResultCallback resultCallback) {
        InputStream inputStream2;
        byte[] bArr;
        boolean z;
        try {
            try {
                int available = inputStream.available();
                byte[] bArr2 = new byte[51200];
                boolean z2 = false;
                int i = available;
                while (i > 0) {
                    if (i >= 51200) {
                        inputStream2 = inputStream;
                        z = z2;
                        bArr = bArr2;
                    } else {
                        inputStream2 = inputStream;
                        bArr = new byte[i];
                        z = true;
                    }
                    int read = inputStream2.read(bArr);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("size", available);
                    jSONObject.put("is_finished", z);
                    a(str, str2, bArr, jSONObject.toString(), resultCallback);
                    i -= read;
                    z2 = z;
                }
            } catch (IOException unused) {
                Log.i("HiHealthKitExtend", "writeToWearable IOException");
                d(resultCallback, 1, HiHealthError.e(1));
                a(str, str2, null, null, resultCallback);
            } catch (JSONException unused2) {
                Log.i("HiHealthKitExtend", "writeToWearable JSONException");
                d(resultCallback, 1, HiHealthError.e(1));
                a(str, str2, null, null, resultCallback);
            }
            try {
                inputStream.close();
            } catch (IOException unused3) {
                Log.i("HiHealthKitExtend", "writeToWearable:close inputStream IOException");
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException unused4) {
                Log.i("HiHealthKitExtend", "writeToWearable:close inputStream IOException");
            }
            throw th;
        }
    }

    public void a(final HiSportDataCallback hiSportDataCallback) {
        Log.i("HiHealthKitExtend", "startRealTimeSportData");
        if (hiSportDataCallback == null) {
            Log.w("HiHealthKitExtend", "startRealTimeSportData callback is null");
        } else {
            this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda26
                @Override // java.lang.Runnable
                public final void run() {
                    HiHealthKitExtendApi.this.c(hiSportDataCallback);
                }
            });
        }
    }

    /* synthetic */ void c(final HiSportDataCallback hiSportDataCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            Log.w("HiHealthKitExtend", "fetchRealTimeSportData mApiAidl is null");
            hiSportDataCallback.onResultHandler(1);
            return;
        }
        try {
            iHiHealthKitEx.registerRealTimeSportCallback(b.getOutOfBandData(), new ISportDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.19
                @Override // com.huawei.hihealth.ISportDataCallback
                public void onResult(int i) throws RemoteException {
                    Log.i("HiHealthKitExtend", "startRealTimeSportData onResult errCode = " + i);
                    hiSportDataCallback.onResultHandler(i);
                }

                @Override // com.huawei.hihealth.ISportDataCallback
                public void onDataChanged(int i, Bundle bundle) {
                    Log.i("HiHealthKitExtend", "startRealTimeSportData onDataChanged sportState = " + i);
                    Log.i("HiHealthKitExtend", "startRealTimeSportData onDataChanged bundle = " + bundle);
                    hiSportDataCallback.onDataChangedHandler(i, bundle);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "startRealTimeSportData RemoteException");
            hiSportDataCallback.onResultHandler(1);
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "startRealTimeSportData Exception");
            hiSportDataCallback.onResultHandler(1);
        }
    }

    public void d(final HiSportDataCallback hiSportDataCallback) {
        Log.i("HiHealthKitExtend", "stopRealTimeSportData");
        if (hiSportDataCallback == null) {
            Log.w("HiHealthKitExtend", "stopRealTimeSportData callback is null");
        } else {
            this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HiHealthKitExtendApi.this.b(hiSportDataCallback);
                }
            });
        }
    }

    /* synthetic */ void b(final HiSportDataCallback hiSportDataCallback) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            Log.w("HiHealthKitExtend", "stopRealTimeSportData mApiAidl is null");
            hiSportDataCallback.onResultHandler(1);
            return;
        }
        try {
            iHiHealthKitEx.unregisterRealTimeSportCallback(b.getOutOfBandData(), new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.20
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str) {
                    Log.i("HiHealthKitExtend", "stopRealTimeSportData resultCode = " + i);
                    hiSportDataCallback.onResultHandler(i);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "stopRealTimeSportData RemoteException");
            hiSportDataCallback.onResultHandler(1);
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "stopRealTimeSportData Exception");
            hiSportDataCallback.onResultHandler(1);
        }
    }

    public void c(final HiHealthCapabilityQuery hiHealthCapabilityQuery, final CapabilityResultCallback capabilityResultCallback) {
        Log.i("HiHealthKitExtend", "getHealthyLivingData");
        if (capabilityResultCallback == null) {
            Log.w("HiHealthKitExtend", "getHealthyLivingData callback is null");
        } else {
            this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    HiHealthKitExtendApi.this.c(capabilityResultCallback, hiHealthCapabilityQuery);
                }
            });
        }
    }

    /* synthetic */ void c(final CapabilityResultCallback capabilityResultCallback, HiHealthCapabilityQuery hiHealthCapabilityQuery) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            Log.w("HiHealthKitExtend", "getHealthyLivingData mApiAidl is null");
            capabilityResultCallback.onResultHandler(1, "getHealthyLivingData mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getHealthyLivingData(b.getOutOfBandData(), hiHealthCapabilityQuery, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.21
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str) {
                    Log.i("HiHealthKitExtend", "getHealthyLivingData result:" + i);
                    capabilityResultCallback.onResultHandler(HiHealthError.c(i), str);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "getHealthyLivingData RemoteException");
            capabilityResultCallback.onResultHandler(1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getHealthyLivingData Exception");
            capabilityResultCallback.onResultHandler(1, HiHealthError.e(1));
        }
    }

    /* synthetic */ void e(final ResultCallback resultCallback, String str) {
        c();
        IHiHealthKitEx iHiHealthKitEx = this.c;
        if (iHiHealthKitEx == null) {
            d(resultCallback, 1, "getSwitch mApiAidl is null");
            Log.w("HiHealthKitExtend", "getSwitch mApiAidl is null");
            return;
        }
        try {
            iHiHealthKitEx.getSwitch(b.getOutOfBandData(), str, new ICommonCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.22
                @Override // com.huawei.hihealth.ICommonCallback
                public void onResult(int i, String str2) {
                    Log.i("HiHealthKitExtend", "getSwitch onResult errCode = " + i);
                    HiHealthKitExtendApi.this.d(resultCallback, HiHealthError.c(i), str2);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "getSwitch RemoteException");
            d(resultCallback, 1, HiHealthError.e(1));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getSwitch Exception");
            d(resultCallback, 1, HiHealthError.e(1));
        }
    }

    public void e(final HealthKitDictQuery healthKitDictQuery, final IuniversalCallback iuniversalCallback) {
        Log.i("HiHealthKitExtend", "enter queryData");
        this.i.execute(new Runnable() { // from class: com.huawei.hihealth.HiHealthKitExtendApi$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                HiHealthKitExtendApi.this.e(iuniversalCallback, healthKitDictQuery);
            }
        });
    }

    /* synthetic */ void e(IuniversalCallback iuniversalCallback, HealthKitDictQuery healthKitDictQuery) {
        c();
        if (this.c == null) {
            iuniversalCallback.onResultHandler(1, null, HiHealthError.e(1));
            Log.w("HiHealthKitExtend", "queryData mApiAidl is null");
        } else if (!iff.c(healthKitDictQuery.getSampleType())) {
            iuniversalCallback.onResultHandler(30, null, HiHealthError.e(30));
        } else {
            b(healthKitDictQuery, iuniversalCallback);
        }
    }

    private void b(final HealthKitDictQuery healthKitDictQuery, final IuniversalCallback iuniversalCallback) {
        try {
            final ArrayList arrayList = new ArrayList(10);
            this.c.queryData(b.getOutOfBandData(), healthKitDictQuery, new IDataReadResultListener.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.23
                @Override // com.huawei.hihealth.IDataReadResultListener
                public void onResult(List list, int i, int i2) {
                    Log.i("HiHealthKitExtend", "enter KitAPI queryDataImp onSuccess");
                    int c = HiHealthError.c(i);
                    if (list != null) {
                        DictDataTransformUtil.e(HiHealthKitExtendApi.this.b(healthKitDictQuery.getSampleType()), list, arrayList);
                    }
                    DictDataTransformUtil.a(c, i2, arrayList, iuniversalCallback);
                }
            });
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "queryDataImp RemoteException");
            iuniversalCallback.onResultHandler(4, null, HiHealthError.e(4));
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "queryDataImp Exception");
            iuniversalCallback.onResultHandler(4, null, HiHealthError.e(4));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ResultCallback resultCallback, int i, Object obj) {
        if (resultCallback != null) {
            resultCallback.onResultHandler(i, obj);
        }
    }

    static class Instance {
        public static final HiHealthKitExtendApi e = new HiHealthKitExtendApi();

        private Instance() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        synchronized (d) {
            if (this.c == null) {
                Intent intent = new Intent("com.huawei.health.action.KIT_SERVICE");
                intent.setClassName(ife.e(), "com.huawei.hihealthservice.HiHealthService");
                intent.setPackage(ife.e());
                try {
                } catch (SecurityException e) {
                    Log.e("HiHealthKitExtend", "bindService exception" + e.getMessage());
                }
                if (b == null) {
                    return;
                }
                b.bindService(intent, this, 1);
                Object obj = f4014a;
                synchronized (obj) {
                    try {
                    } catch (InterruptedException e2) {
                        Log.e("HiHealthKitExtend", "bindService() InterruptedException = " + e2.getMessage());
                    }
                    if (this.c != null) {
                        Log.i("HiHealthKitExtend", "bindService bind mApiAidl is not null");
                        return;
                    }
                    obj.wait(5000L);
                    Log.i("HiHealthKitExtend", "bindService bind over mApiAidl is " + this.c);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
            r4 = this;
            java.lang.String r5 = "HiHealthKitExtend"
            java.lang.String r0 = "onServiceConnected"
            android.util.Log.i(r5, r0)
            r5 = 0
            int r0 = android.os.Binder.getCallingUid()     // Catch: java.lang.Exception -> L5d
            com.huawei.hihealthkit.context.OutOfBandContext r1 = com.huawei.hihealth.HiHealthKitExtendApi.b     // Catch: java.lang.Exception -> L5d
            if (r1 != 0) goto L12
            return
        L12:
            com.huawei.hihealthkit.context.OutOfBandContext r1 = com.huawei.hihealth.HiHealthKitExtendApi.b     // Catch: java.lang.Exception -> L5d
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch: java.lang.Exception -> L5d
            if (r1 == 0) goto L3a
            java.lang.String r1 = r1.getNameForUid(r0)     // Catch: java.lang.Exception -> L5d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5e
            java.lang.String r3 = "getCallingUid uid:"
            r2.<init>(r3)     // Catch: java.lang.Exception -> L5e
            java.lang.String r3 = "HiHealthKitExtend"
            r2.append(r0)     // Catch: java.lang.Exception -> L5e
            java.lang.String r0 = " packageName:"
            r2.append(r0)     // Catch: java.lang.Exception -> L5e
            r2.append(r1)     // Catch: java.lang.Exception -> L5e
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Exception -> L5e
            android.util.Log.d(r3, r0)     // Catch: java.lang.Exception -> L5e
            goto L3b
        L3a:
            r1 = r5
        L3b:
            com.huawei.hihealth.IBinderInterceptor r6 = com.huawei.hihealth.IBinderInterceptor.Stub.asInterface(r6)     // Catch: java.lang.Exception -> L5e
            java.lang.String r0 = "KIT_EXTEND"
            android.os.IBinder r6 = r6.getServiceBinder(r0)     // Catch: java.lang.Exception -> L5e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5e
            java.lang.String r2 = "binder: "
            r0.<init>(r2)     // Catch: java.lang.Exception -> L5e
            java.lang.String r2 = "HiHealthKitExtend"
            r0.append(r6)     // Catch: java.lang.Exception -> L5e
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L5e
            android.util.Log.i(r2, r0)     // Catch: java.lang.Exception -> L5e
            com.huawei.hihealth.IHiHealthKitEx r5 = com.huawei.hihealth.IHiHealthKitEx.Stub.asInterface(r6)     // Catch: java.lang.Exception -> L5e
            goto L66
        L5d:
            r1 = r5
        L5e:
            java.lang.String r6 = "HiHealthKitExtend"
            java.lang.String r0 = "onServiceConnected Exception"
            android.util.Log.w(r6, r0)
        L66:
            java.lang.Object r6 = com.huawei.hihealth.HiHealthKitExtendApi.f4014a
            monitor-enter(r6)
            if (r5 != 0) goto L74
            java.lang.String r5 = "HiHealthKitExtend"
            java.lang.String r0 = "onServiceConnected error !"
            android.util.Log.w(r5, r0)     // Catch: java.lang.Throwable -> Lab
            goto La9
        L74:
            r4.c = r5     // Catch: java.lang.Throwable -> Lab
            if (r1 != 0) goto L7a
            java.lang.String r1 = ""
        L7a:
            r5.registerPackageName(r1)     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            com.huawei.hihealth.IHiHealthKitEx r5 = r4.c     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            com.huawei.hihealthkit.context.OutOfBandContext r0 = com.huawei.hihealth.HiHealthKitExtendApi.b     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            int r0 = defpackage.iff.a(r0)     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            r5.setKitVersion(r0)     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            com.huawei.hihealth.IHiHealthKitEx r5 = r4.c     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            com.huawei.hihealthkit.context.OutOfBandContext r0 = com.huawei.hihealth.HiHealthKitExtendApi.b     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            com.huawei.hihealthkit.context.OutOfBandData r0 = r0.getOutOfBandData()     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            int r5 = r5.getServiceApiLevel(r0)     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            defpackage.iff.e(r5)     // Catch: android.os.RemoteException -> L9c java.lang.Throwable -> Lab
            goto La4
        L9c:
            java.lang.String r5 = "HiHealthKitExtend"
            java.lang.String r0 = "setKitVersion RemoteException"
            android.util.Log.e(r5, r0)     // Catch: java.lang.Throwable -> Lab
        La4:
            java.lang.Object r5 = com.huawei.hihealth.HiHealthKitExtendApi.f4014a     // Catch: java.lang.Throwable -> Lab
            r5.notifyAll()     // Catch: java.lang.Throwable -> Lab
        La9:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lab
            return
        Lab:
            r5 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lab
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealth.HiHealthKitExtendApi.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i("HiHealthKitExtend", "onServiceDisconnected");
        if (this.h != null && this.e) {
            Log.i("HiHealthKitExtend", "onServiceDisconnected() latch countDown");
            this.h.countDown();
        }
        this.c = null;
    }

    /* synthetic */ void j(ResultCallback resultCallback) {
        c();
        if (this.c == null) {
            resultCallback.onResultHandler(1, HiHealthError.e(1));
            Log.w("HiHealthKitExtend", "getServiceApiLevel mApiAidl is null");
        } else {
            resultCallback.onResultHandler(0, Integer.valueOf(iff.c()));
        }
    }

    private int d(OutOfBandData outOfBandData) {
        int i = 0;
        try {
            i = this.c.getAbilityVersion(outOfBandData);
            Log.i("HiHealthKitExtend", "getAbilityVersion version = " + i);
            return i;
        } catch (RemoteException unused) {
            Log.e("HiHealthKitExtend", "getAbilityVersion RemoteException");
            return i;
        } catch (Exception unused2) {
            Log.e("HiHealthKitExtend", "getAbilityVersion Exception");
            return i;
        }
    }

    private IRealTimeDataCallback.Stub d(final String str, final HiRealTimeListener hiRealTimeListener) {
        return new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.24
            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) {
                Log.i("HiHealthKitExtend", str + " onResult: " + i);
                hiRealTimeListener.onResultHandler(HiHealthError.c(i));
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str2) {
                hiRealTimeListener.onChangeHandler(HiHealthError.c(i), str2);
            }
        };
    }

    private IRealTimeDataCallback.Stub c(final String str, final ResultCallback resultCallback) {
        return new IRealTimeDataCallback.Stub() { // from class: com.huawei.hihealth.HiHealthKitExtendApi.25
            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) {
                Log.i("HiHealthKitExtend", str + " onResult: " + i);
                HiHealthKitExtendApi.this.d(resultCallback, i, HiHealthError.e(i));
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str2) {
                HiHealthKitExtendApi.this.d(resultCallback, i, str2);
            }
        };
    }

    /* synthetic */ void e() {
        Log.i("HiHealthKitExtend", "enter unBindHealthService");
        if (this.c == null) {
            Log.w("HiHealthKitExtend", "no need to unbind");
            return;
        }
        try {
            b.unbindService(Instance.e);
            this.c = null;
        } catch (Exception unused) {
            Log.w("HiHealthKitExtend", "unbind service exception");
        }
    }
}
