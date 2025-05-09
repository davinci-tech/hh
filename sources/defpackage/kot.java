package defpackage;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.up.utils.ErrorCode;
import defpackage.kot;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class kot {

    /* renamed from: a, reason: collision with root package name */
    private static volatile kot f14469a;
    private Context b = BaseApplication.e();

    private kot() {
    }

    public static kot a() {
        if (f14469a == null) {
            synchronized (kot.class) {
                if (f14469a == null) {
                    f14469a = new kot();
                }
            }
        }
        return f14469a;
    }

    public void b(Context context, long j, long j2, int i, final IBaseResponseCallback iBaseResponseCallback) {
        if (j > j2) {
            LogUtil.h("Common_HwHealthWeightDataManager", "startTime > endTime");
            return;
        }
        String[] strArr = {BleConstants.WEIGHT_KEY};
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(HiDateUtil.t(j), HiDateUtil.f(j2));
        hiAggregateOption.setTimeRange(j, j2);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setConstantsKey(strArr);
        if (i != 0) {
            if (i == 99999) {
                hiAggregateOption.setCount(1);
                hiAggregateOption.setFilter("");
            } else {
                hiAggregateOption.setCount(i);
                hiAggregateOption.setFilter("NULL");
            }
        }
        HiHealthManager.d(context).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kot.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (list == null) {
                    LogUtil.h("Common_HwHealthWeightDataManager", "getWeightData, datas is null");
                    iBaseResponseCallback.d(1, Collections.EMPTY_LIST);
                } else {
                    iBaseResponseCallback.d(0, list);
                }
            }
        });
    }

    public void b(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        int[] iArr = {DicDataTypeUtil.DataType.BODY_SHAPE.value()};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: kot.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                kot.this.a(obj, iBaseResponseCallback, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj, IBaseResponseCallback iBaseResponseCallback, int i) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastBodyShapeDataResult callback is null");
            return;
        }
        if (!(obj instanceof SparseArray)) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastBodyShapeDataResult onResult data not instanceof SparseArray");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastBodyShapeDataResult data is empty errorCode：", Integer.valueOf(i));
            iBaseResponseCallback.d(i, 0L);
            return;
        }
        Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.BODY_SHAPE.value());
        if (!koq.e(obj2, HiHealthData.class)) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastBodyShapeDataResult onResult data not instanceof List");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        List list = (List) obj2;
        if (list.isEmpty()) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastBodyShapeDataResult onResult list is empty");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData != null) {
            long startTime = hiHealthData.getStartTime();
            LogUtil.a("Common_HwHealthWeightDataManager", "acquireLastBodyShapeDataResult getStartTime:", Long.valueOf(startTime));
            iBaseResponseCallback.d(0, Long.valueOf(startTime));
        } else {
            iBaseResponseCallback.d(100001, null);
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastBodyShapeDataResult onResult hiHealthData is null");
        }
    }

    public void c(String str, String str2, String str3, long j, boolean z) {
        LogUtil.a("Common_HwHealthWeightDataManager", "setSampleConfig type " + str + " configId " + str2 + " configData " + str3, " timeStamp ", Long.valueOf(j));
        sqp.c(str2, new AnonymousClass4(str2, str3, j, z));
    }

    /* renamed from: kot$4, reason: invalid class name */
    class AnonymousClass4 implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f14473a;
        final /* synthetic */ long c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass4(String str, String str2, long j, boolean z) {
            this.d = str;
            this.e = str2;
            this.c = j;
            this.f14473a = z;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (!koq.e(obj, HiSampleConfig.class)) {
                ThreadPoolManager d = ThreadPoolManager.d();
                final String str = this.d;
                final String str2 = this.e;
                final long j = this.c;
                final boolean z = this.f14473a;
                d.execute(new Runnable() { // from class: kpd
                    @Override // java.lang.Runnable
                    public final void run() {
                        kot.AnonymousClass4.this.d(str, str2, j, z);
                    }
                });
                return;
            }
            List list = (List) obj;
            if (koq.b(list) || list.get(0) == null) {
                LogUtil.h("Common_HwHealthWeightDataManager", "setSampleConfig empty list");
                ThreadPoolManager d2 = ThreadPoolManager.d();
                final String str3 = this.d;
                final String str4 = this.e;
                final long j2 = this.c;
                final boolean z2 = this.f14473a;
                d2.execute(new Runnable() { // from class: kpe
                    @Override // java.lang.Runnable
                    public final void run() {
                        kot.AnonymousClass4.this.e(str3, str4, j2, z2);
                    }
                });
                return;
            }
            long modifiedTime = ((HiSampleConfig) list.get(0)).getModifiedTime();
            LogUtil.a("Common_HwHealthWeightDataManager", "setSampleConfig modifiedTime: ", Long.valueOf(modifiedTime));
            if (this.c > modifiedTime) {
                ThreadPoolManager d3 = ThreadPoolManager.d();
                final String str5 = this.d;
                final String str6 = this.e;
                final long j3 = this.c;
                final boolean z3 = this.f14473a;
                d3.execute(new Runnable() { // from class: kpf
                    @Override // java.lang.Runnable
                    public final void run() {
                        kot.AnonymousClass4.this.b(str5, str6, j3, z3);
                    }
                });
            }
        }

        /* synthetic */ void d(String str, String str2, long j, boolean z) {
            kot.this.b(str, str2, j, z);
        }

        /* synthetic */ void e(String str, String str2, long j, boolean z) {
            kot.this.b(str, str2, j, z);
        }

        /* synthetic */ void b(String str, String str2, long j, boolean z) {
            kot.this.b(str, str2, j, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, final String str2, final long j, final boolean z) {
        if ("900300022".equals(str)) {
            try {
                kpu.d(new JSONObject(str2).optDouble("weightGoal"), j, new AnonymousClass3(z, j), z);
                return;
            } catch (JSONException e) {
                ReleaseLogUtil.c("Common_HwHealthWeightDataManager", "setSampleConfig exception ", ExceptionUtils.d(e));
                return;
            }
        }
        if ("900300023".equals(str)) {
            kpu.e((gsi) HiJsonUtil.e(str2, gsi.class), j, true, new ResponseCallback() { // from class: kov
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    kot.this.d(z, str2, j, i, (gsi) obj);
                }
            }, z, null);
        }
    }

    /* renamed from: kot$3, reason: invalid class name */
    /* loaded from: classes9.dex */
    class AnonymousClass3 implements ResponseCallback<Double> {
        final /* synthetic */ boolean c;
        final /* synthetic */ long d;

        AnonymousClass3(boolean z, long j) {
            this.c = z;
            this.d = j;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, Double d) {
            if (i == -1 || !this.c) {
                return;
            }
            ThreadPoolManager d2 = ThreadPoolManager.d();
            final long j = this.d;
            d2.execute(new Runnable() { // from class: kph
                @Override // java.lang.Runnable
                public final void run() {
                    kot.AnonymousClass3.this.b(j);
                }
            });
        }

        /* synthetic */ void b(final long j) {
            sqp.c("900300023", new HiDataReadResultListener() { // from class: kot.3.3
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    if (koq.e(obj, HiSampleConfig.class)) {
                        List list = (List) obj;
                        if (koq.b(list) || list.get(0) == null) {
                            LogUtil.h("Common_HwHealthWeightDataManager", "empty list");
                            return;
                        }
                        LogUtil.a("Common_HwHealthWeightDataManager", "weight goal:saveWeightTargetDifferences");
                        kot.this.a(((HiSampleConfig) list.get(0)).getConfigData(), j);
                    }
                }
            });
        }
    }

    /* synthetic */ void d(boolean z, String str, long j, int i, gsi gsiVar) {
        if (i == 0 && z) {
            LogUtil.a("Common_HwHealthWeightDataManager", "weight manager:saveWeightTargetDifferences");
            a(str, j);
        }
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(final String str, final long j) {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kpc
                @Override // java.lang.Runnable
                public final void run() {
                    kot.this.a(str, j);
                }
            });
            return;
        }
        gsi gsiVar = (gsi) HiJsonUtil.e(str, gsi.class);
        if (gsiVar == null) {
            ReleaseLogUtil.d("Common_HwHealthWeightDataManager", "saveWeightTargetDifferences weightManager is null");
        } else {
            grz.a(gsiVar, j);
        }
    }

    public void b(String str, String str2, HiDataReadResultListener hiDataReadResultListener) {
        sqp.c(str2, hiDataReadResultListener);
    }

    public void b(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        if (j >= j2) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        int[] iArr = {DicDataTypeUtil.DataType.RESTING_METABOLISM.value(), DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE.value()};
        String[] strArr = {BleConstants.BASAL_METABOLISM, "basalMetabolismAfterExercise"};
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(HiDateUtil.t(j), HiDateUtil.f(j2 - 1000));
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setConstantsKey(strArr);
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kot.5
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.h("Common_HwHealthWeightDataManager", "queryHistoryRestMetaData data ", list, " error code ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    return;
                }
                iBaseResponseCallback2.d(i, list);
            }
        });
    }

    public void c(long j, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Common_HwHealthWeightDataManager", "getLatestWeight startTime ", Long.valueOf(j));
        int[] iArr = {10006};
        String[] strArr = {BleConstants.WEIGHT_KEY};
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        if (j == 0) {
            hiAggregateOption.setTimeInterval(0L, System.currentTimeMillis());
        } else {
            hiAggregateOption.setTimeInterval(j, System.currentTimeMillis());
        }
        hiAggregateOption.setTimeInterval(0L, System.currentTimeMillis());
        hiAggregateOption.setType(iArr);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setConstantsKey(strArr);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setFilter("NULL");
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: kot.10
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.h("Common_HwHealthWeightDataManager", "getLatestWeight data ", list, " error code ", Integer.valueOf(i), " anchor ", Integer.valueOf(i2));
                kot.this.c(list, iBaseResponseCallback, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj, IBaseResponseCallback iBaseResponseCallback, int i) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastWeightDataResult callback is null");
            return;
        }
        if (!koq.e(obj, HiHealthData.class)) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastWeightDataResult onResult data not instanceof List");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        List list = (List) obj;
        if (list.isEmpty()) {
            LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastWeightDataResult onResult list is empty");
            iBaseResponseCallback.d(100001, null);
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData != null) {
            LogUtil.a("Common_HwHealthWeightDataManager", "acquireLastWeightDataResult getStartTime:", Long.valueOf(hiHealthData.getStartTime()));
            if (b(hiHealthData)) {
                iBaseResponseCallback.d(0, hiHealthData);
                return;
            } else {
                iBaseResponseCallback.d(ErrorCode.HWID_IS_STOPED, hiHealthData);
                return;
            }
        }
        iBaseResponseCallback.d(100001, null);
        LogUtil.h("Common_HwHealthWeightDataManager", "acquireLastWeightDataResult onResult hiHealthData is null");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x009a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(com.huawei.hihealth.HiHealthData r22) {
        /*
            r21 = this;
            r0 = r22
            java.lang.String r1 = "bodyWeight"
            boolean r2 = r0.containsKey(r1)
            if (r2 == 0) goto L9b
            java.lang.String r2 = "gender"
            boolean r3 = r0.containsKey(r2)
            if (r3 == 0) goto L9b
            java.lang.String r3 = "height"
            boolean r4 = r0.containsKey(r3)
            if (r4 == 0) goto L9b
            java.lang.String r4 = "age"
            boolean r5 = r0.containsKey(r4)
            if (r5 != 0) goto L24
            goto L9b
        L24:
            java.lang.String r5 = "basalMetabolism"
            double r5 = r0.getDouble(r5)
            int r2 = r0.getInt(r2)
            int r1 = r0.getInt(r1)
            int r3 = r0.getInt(r3)
            int r0 = r0.getInt(r4)
            double r7 = (double) r1
            r9 = 4621813488089437307(0x4023fae147ae147b, double:9.99)
            double r7 = r7 * r9
            double r9 = (double) r3
            r11 = 4618722892845154304(0x4019000000000000, double:6.25)
            double r9 = r9 * r11
            double r7 = r7 + r9
            int r4 = r0 * 5
            double r9 = (double) r4
            double r7 = r7 - r9
            r4 = 1
            if (r2 != 0) goto L53
            r9 = 4639868700470542336(0x4064200000000000, double:161.0)
            goto L5b
        L53:
            r9 = 2
            if (r2 != r9) goto L5d
            r9 = 4635189178982727680(0x4053800000000000, double:78.0)
        L5b:
            double r7 = r7 - r9
            goto L62
        L5d:
            if (r2 != r4) goto L62
            r9 = 4617315517961601024(0x4014000000000000, double:5.0)
            double r7 = r7 + r9
        L62:
            java.lang.String r9 = "satisfySendToDevice bmr="
            java.lang.Double r10 = java.lang.Double.valueOf(r5)
            java.lang.String r11 = " gender="
            java.lang.Integer r12 = java.lang.Integer.valueOf(r2)
            java.lang.String r13 = " weight="
            java.lang.Integer r14 = java.lang.Integer.valueOf(r1)
            java.lang.String r15 = " height="
            java.lang.Integer r16 = java.lang.Integer.valueOf(r3)
            java.lang.String r17 = " age="
            java.lang.Integer r18 = java.lang.Integer.valueOf(r0)
            java.lang.String r19 = " msje="
            java.lang.Double r20 = java.lang.Double.valueOf(r7)
            java.lang.Object[] r0 = new java.lang.Object[]{r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20}
            java.lang.String r1 = "Common_HwHealthWeightDataManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 4608983858650965606(0x3ff6666666666666, double:1.4)
            double r7 = r7 * r0
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 >= 0) goto L9b
            return r4
        L9b:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kot.b(com.huawei.hihealth.HiHealthData):boolean");
    }

    public void b(final ResponseCallback<gsi> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("Common_HwHealthWeightDataManager", "getSampleConfig listener is null");
        } else if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kpa
                @Override // java.lang.Runnable
                public final void run() {
                    kot.this.e(responseCallback);
                }
            });
        } else {
            kpu.b("900300023", true, new ResponseCallback() { // from class: koz
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    kot.e(ResponseCallback.this, i, obj);
                }
            });
        }
    }

    /* synthetic */ void e(ResponseCallback responseCallback) {
        b((ResponseCallback<gsi>) responseCallback);
    }

    static /* synthetic */ void e(ResponseCallback responseCallback, int i, Object obj) {
        if (obj instanceof gsi) {
            gsi gsiVar = (gsi) obj;
            gsd.a(gsiVar);
            responseCallback.onResponse(i, gsiVar);
            return;
        }
        responseCallback.onResponse(-1, null);
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(final ResponseCallback<Float> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("Common_HwHealthWeightDataManager", "getSampleConfig listener is null");
        } else if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kpb
                @Override // java.lang.Runnable
                public final void run() {
                    kot.this.c(responseCallback);
                }
            });
        } else {
            kpu.b("900300022", true, new ResponseCallback() { // from class: koy
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    kot.this.b(responseCallback, i, obj);
                }
            });
        }
    }

    /* synthetic */ void b(ResponseCallback responseCallback, int i, Object obj) {
        ReleaseLogUtil.e("Common_HwHealthWeightDataManager", "getWeightGoal data ", obj);
        if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            c(floatValue);
            gsd.e(floatValue);
            responseCallback.onResponse(i, Float.valueOf(floatValue));
            return;
        }
        d(responseCallback);
    }

    private void d(final ResponseCallback<Float> responseCallback) {
        HiHealthNativeApi.a(BaseApplication.e()).fetchGoalInfo(0, 5, new HiCommonListener() { // from class: kot.8
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj == null) {
                    LogUtil.b("Common_HwHealthWeightDataManager", "getWeightFromHiGoal data is null");
                    responseCallback.onResponse(-1, null);
                    return;
                }
                if (!koq.e(obj, HiGoalInfo.class)) {
                    LogUtil.b("Common_HwHealthWeightDataManager", "getWeightFromHiGoal is not list<HiGoalInfo> type");
                    responseCallback.onResponse(-1, null);
                    return;
                }
                List list = (List) obj;
                if (koq.b(list) || list.get(0) == null) {
                    LogUtil.b("Common_HwHealthWeightDataManager", "getWeightFromHiGoal goalInformationList is null");
                    responseCallback.onResponse(-1, null);
                } else {
                    float goalValue = (float) ((HiGoalInfo) list.get(0)).getGoalValue();
                    LogUtil.c("Common_HwHealthWeightDataManager", "getWeightFromHiGoal targetWeight is ", Float.valueOf(goalValue));
                    kot.this.c(goalValue);
                    responseCallback.onResponse(0, Float.valueOf(goalValue));
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("Common_HwHealthWeightDataManager", "getWeightFromHiGoal failed, errCode is", Integer.valueOf(i));
                responseCallback.onResponse(-1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f) {
        if (f <= 0.0f) {
            ReleaseLogUtil.d("Common_HwHealthWeightDataManager", "setFatReductionShapingState targetWeight ", Float.valueOf(f));
            return;
        }
        boolean a2 = gsd.a();
        ReleaseLogUtil.e("Common_HwHealthWeightDataManager", "setFatReductionShapingState isOpenFatReductionShaping ", Boolean.valueOf(a2));
        if (a2) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: kpg
            @Override // java.lang.Runnable
            public final void run() {
                grz.b(false, (ResponseCallback<Boolean>) new ResponseCallback() { // from class: kou
                    @Override // com.huawei.hwbasemgr.ResponseCallback
                    public final void onResponse(int i, Object obj) {
                        kot.b(i, (Boolean) obj);
                    }
                });
            }
        });
    }

    static /* synthetic */ void b(int i, Boolean bool) {
        if (bool != null && bool.booleanValue()) {
            gsd.c(System.currentTimeMillis());
        }
    }
}
