package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class qpk {
    private static volatile boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private int f16534a;
    private int b;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int r;
    private int s;
    private int t;

    private qpk() {
        w();
    }

    /* loaded from: classes7.dex */
    static class d {
        private static final qpk d = new qpk();
    }

    public static qpk d() {
        return d.d;
    }

    private void w() {
        this.g = DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value();
        this.o = DicDataTypeUtil.DataType.SKIN_TEMPERATURE_SET.value();
        this.b = DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM_SET.value();
        this.e = DicDataTypeUtil.DataType.LOW_BODY_TEMPERATURE_ALARM_SET.value();
        this.f16534a = DicDataTypeUtil.DataType.BODY_TEMPERATURE.value();
        this.f = DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value();
        this.h = DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM.value();
        this.i = DicDataTypeUtil.DataType.LOW_BODY_TEMPERATURE_ALARM.value();
        this.n = DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_ALARM.value();
        this.t = DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value();
        this.p = DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_SET.value();
        this.m = DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_ALARM_SET.value();
        this.d = DicDataTypeUtil.DataType.MAX_BODY_TEMPERATURE.value();
        this.j = DicDataTypeUtil.DataType.MIN_BODY_TEMPERATURE.value();
        this.s = DicDataTypeUtil.DataType.MAX_SUSPECTED_HIGH_TEMPERATURE.value();
        this.r = DicDataTypeUtil.DataType.MIN_SUSPECTED_HIGH_TEMPERATURE.value();
        this.l = DicDataTypeUtil.DataType.MAX_SKIN_TEMPERATURE.value();
        this.k = DicDataTypeUtil.DataType.MIN_SKIN_TEMPERATURE.value();
    }

    public int g() {
        return this.g;
    }

    public int m() {
        return this.o;
    }

    public int a() {
        return this.b;
    }

    public int e() {
        return this.e;
    }

    public int b() {
        return this.f16534a;
    }

    public int o() {
        return this.f;
    }

    public int j() {
        return this.i;
    }

    public int k() {
        return this.n;
    }

    public int s() {
        return this.t;
    }

    public int t() {
        return this.p;
    }

    public int p() {
        return this.m;
    }

    public int h() {
        return this.h;
    }

    public int i() {
        return this.d;
    }

    public int f() {
        return this.j;
    }

    public int n() {
        return this.l;
    }

    public int l() {
        return this.k;
    }

    public int r() {
        return this.s;
    }

    public int q() {
        return this.r;
    }

    public void a(int[] iArr, HiDataReadOption hiDataReadOption, IBaseResponseCallback iBaseResponseCallback) {
        a(iArr, hiDataReadOption, iBaseResponseCallback, false);
    }

    public void a(int[] iArr, HiDataReadOption hiDataReadOption, IBaseResponseCallback iBaseResponseCallback, boolean z) {
        boolean z2;
        if (hiDataReadOption == null) {
            LogUtil.b("TemperatureDataManager", "getPointData option is null");
            return;
        }
        String b = nsj.b(hiDataReadOption.getStartTime());
        SparseArray<Object> dGX_ = (z && c) ? qpj.dGX_(iArr, b) : new SparseArray<>();
        if (z && c && dGX_.size() > 0) {
            List<HiHealthData> dHc_ = dHc_(iArr, dGX_);
            LogUtil.a("TemperatureDataManager", "cacheData allList size ", Integer.valueOf(dHc_.size()));
            if (koq.b(dHc_)) {
                iBaseResponseCallback.d(-1, null);
            } else {
                iBaseResponseCallback.d(0, dHc_);
            }
            z2 = true;
        } else {
            z2 = false;
        }
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new c(dGX_, b, iArr, z2, z, iBaseResponseCallback, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int[] iArr, boolean z, boolean z2) {
        if (z && z2 && c) {
            qpj.e(iArr);
            ObserverManagerUtil.c("REFRESH_TEMPERATURE_CHART", new Object[0]);
            c = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HiHealthData> dHc_(int[] iArr, SparseArray<Object> sparseArray) {
        ArrayList arrayList = new ArrayList(16);
        for (int i : iArr) {
            Object obj = sparseArray.get(i);
            if (obj instanceof List) {
                List list = (List) obj;
                LogUtil.a("TemperatureDataManager", "getBodyAndSkinData type ", Integer.valueOf(i), " list size ", Integer.valueOf(list.size()));
                arrayList.addAll(list);
            }
        }
        return arrayList;
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("TemperatureDataManager", "getWarningData callback is null");
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setSortOrder(1);
        int[] iArr = {this.i, this.h, this.n};
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        a(iArr, hiDataReadOption, iBaseResponseCallback);
    }

    public void e(long j, long j2, float f, IBaseResponseCallback iBaseResponseCallback) {
        c(this.f16534a, j, j2, f, iBaseResponseCallback);
    }

    public void c(int i, long j, long j2, float f, IBaseResponseCallback iBaseResponseCallback) {
        HiHealthData hiHealthData = new HiHealthData(i);
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j2);
        hiHealthData.setValue(f);
        a(hiHealthData, iBaseResponseCallback);
    }

    private void a(final HiHealthData hiHealthData, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).fetchManualDataClient(new HiDataClientListener() { // from class: qpk.2
            @Override // com.huawei.hihealth.data.listener.HiDataClientListener
            public void onResult(List<HiHealthClient> list) {
                if (iBaseResponseCallback == null) {
                    return;
                }
                if (koq.b(list)) {
                    LogUtil.h("TemperatureDataManager", "clientList is empty");
                    iBaseResponseCallback.d(100001, false);
                    return;
                }
                HiHealthClient hiHealthClient = list.get(0);
                if (hiHealthClient == null) {
                    LogUtil.h("TemperatureDataManager", "client is null");
                    iBaseResponseCallback.d(100001, false);
                    return;
                }
                String deviceUuid = hiHealthClient.getDeviceUuid();
                LogUtil.a("TemperatureDataManager", "deviceUuid = ", deviceUuid);
                hiHealthData.setDeviceUuid(deviceUuid);
                HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
                hiDataInsertOption.addData(hiHealthData);
                qpk.this.e(hiDataInsertOption, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: qpo
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                qpk.d(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void d(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (obj != null && i == 0) {
            LogUtil.a("TemperatureDataManager", "insertData success");
            iBaseResponseCallback.d(0, obj);
        } else {
            LogUtil.h("TemperatureDataManager", "insertData fail errorCode = ", Integer.valueOf(i));
            iBaseResponseCallback.d(100001, false);
        }
    }

    public void c(Context context, List<HiTimeInterval> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("TemperatureDataManager", "deleteTemperatureData");
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(list);
        hiDataDeleteOption.setTypes(new int[]{this.f16534a});
        HiHealthManager.d(context).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: qpq
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                qpk.e(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void e(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("TemperatureDataManager", "deleteTemperatureData errorCode ", Integer.valueOf(i));
        if (iBaseResponseCallback == null) {
            LogUtil.h("TemperatureDataManager", "deleteTemperatureData callback is null");
        } else if (i == 0) {
            iBaseResponseCallback.d(0, Integer.valueOf(i));
        } else {
            iBaseResponseCallback.d(100001, Integer.valueOf(i));
        }
    }

    public void e(Context context, List<HiTimeInterval> list, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("TemperatureDataManager", "deleteTemperatureWarningData");
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(list);
        hiDataDeleteOption.setTypes(new int[]{this.h, this.i, this.n});
        HiHealthManager.d(context).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: qps
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                qpk.c(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("TemperatureDataManager", "deleteTemperatureData errorCode ", Integer.valueOf(i));
        if (iBaseResponseCallback == null) {
            LogUtil.h("TemperatureDataManager", "deleteTemperatureData callback is null");
        } else if (i == 0) {
            iBaseResponseCallback.d(0, Integer.valueOf(i));
        } else {
            iBaseResponseCallback.d(100001, Integer.valueOf(i));
        }
    }

    public static void d(boolean z) {
        c = z;
    }

    /* loaded from: classes7.dex */
    static final class c implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final boolean f16536a;
        private WeakReference<qpk> b;
        private final IBaseResponseCallback c;
        private final boolean d;
        private final SparseArray<Object> e;
        private final int[] f;
        private final String h;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        c(SparseArray<Object> sparseArray, String str, int[] iArr, boolean z, boolean z2, IBaseResponseCallback iBaseResponseCallback, qpk qpkVar) {
            this.b = new WeakReference<>(qpkVar);
            this.c = iBaseResponseCallback;
            this.f = iArr;
            this.f16536a = z2;
            this.d = z;
            this.h = str;
            this.e = sparseArray;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            WeakReference<qpk> weakReference;
            qpk qpkVar;
            if (this.c == null || (weakReference = this.b) == null || (qpkVar = weakReference.get()) == null) {
                return;
            }
            if (!(obj instanceof SparseArray)) {
                Object[] objArr = new Object[2];
                objArr[0] = "getTemperatureData convert fail, data = ";
                objArr[1] = Boolean.valueOf(obj == null);
                LogUtil.h("TemperatureDataManager", objArr);
                qpkVar.e(this.f, this.f16536a, this.d);
                this.c.d(-1, null);
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                LogUtil.h("TemperatureDataManager", "existOxygenRemindData map is null");
                qpkVar.e(this.f, this.f16536a, this.d);
                this.c.d(-1, null);
                return;
            }
            if (this.f16536a && qpk.c) {
                boolean unused = qpk.c = false;
                if (!qpj.dGZ_(this.f, this.e, sparseArray)) {
                    LogUtil.a("TemperatureDataManager", "getPointData same cache");
                    return;
                }
                qpj.dHa_(this.f, this.h, sparseArray);
                if (this.d) {
                    LogUtil.a("TemperatureDataManager", "has return cache before, need to refresh chart");
                    ObserverManagerUtil.c("REFRESH_TEMPERATURE_CHART", new Object[0]);
                }
            }
            List dHc_ = qpkVar.dHc_(this.f, sparseArray);
            LogUtil.a("TemperatureDataManager", "getBodyAndSkinData allList size ", Integer.valueOf(dHc_.size()));
            if (koq.b(dHc_)) {
                qpkVar.e(this.f, this.f16536a, this.d);
                this.c.d(-1, null);
            } else {
                this.c.d(0, dHc_);
            }
        }
    }
}
