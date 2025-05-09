package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.gson.JsonObject;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.weight.WeightApi;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kpu;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class kpu {
    private static volatile BroadcastReceiver c;
    private static final byte[] e = new byte[0];
    private static final float[][] d = {new float[]{0.0051f, 0.01f}, new float[]{0.0101f, 0.015f}, new float[]{0.0151f, 0.02f}};

    /* renamed from: a, reason: collision with root package name */
    private static final int[][] f14537a = {new int[]{24, 1}, new int[]{12, 1}, new int[]{6, 1}};

    /* loaded from: classes5.dex */
    static class e extends BroadcastReceiver {
        private String d;
        private ResponseCallback<Object> e;

        e(String str, ResponseCallback<Object> responseCallback) {
            this.d = str;
            this.e = responseCallback;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("WeightManagerUtil", "intent is null");
                return;
            }
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("sync_data_result_type");
            long longExtra = intent.getLongExtra("sync_data_result_id", 0L);
            boolean booleanExtra = intent.getBooleanExtra("sync_data_result_success", true);
            ReleaseLogUtil.e("WeightManagerUtil", "action=", action, " type=", stringExtra, " syncId=", Long.valueOf(longExtra), " result=", Boolean.valueOf(booleanExtra));
            if (String.valueOf(900000000).equals(stringExtra) || String.valueOf(20000).equals(stringExtra)) {
                kpu.c();
                kpu.b(longExtra, booleanExtra, this.d, this.e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, ResponseCallback<Object> responseCallback) {
        LogUtil.a("WeightManagerUtil", "registerSyncBroadcast configId ", str);
        synchronized (e) {
            if (c != null) {
                responseCallback.onResponse(-1, null);
                return;
            }
            IntentFilter intentFilter = new IntentFilter("com.huawei.hihealth.action_sync_data_result");
            c = new e(str, responseCallback);
            BroadcastManagerUtil.bFA_(BaseApplication.e(), c, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c() {
        synchronized (e) {
            if (c != null) {
                ReleaseLogUtil.e("R_WeightManagerUtil", "unregisterSyncBroadcast");
                try {
                    BaseApplication.e().unregisterReceiver(c);
                } catch (RuntimeException e2) {
                    ReleaseLogUtil.d("R_WeightManagerUtil", "unregisterSyncBroadcast exception ", ExceptionUtils.d(e2));
                }
                c = null;
            }
        }
    }

    public static void b(final String str, final boolean z, final ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.d("R_WeightManagerUtil", "getWeightSampleConfig callback is null");
        } else {
            LogUtil.a("WeightManagerUtil", "getWeightSampleConfig configId ", str, " isFirstRequest ", Boolean.valueOf(z));
            sqp.c(str, new HiDataReadResultListener() { // from class: kpu.1
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    boolean i3 = Utils.i();
                    long currentTimeMillis = System.currentTimeMillis();
                    LogUtil.a("WeightManagerUtil", "getWeightSampleConfig onResult configId ", str, " isFirstRequest ", Boolean.valueOf(z), " ifAllowLogin ", Boolean.valueOf(i3), " errorCode ", Integer.valueOf(i), " data ", obj);
                    if (!koq.e(obj, HiSampleConfig.class)) {
                        if (!i3) {
                            responseCallback.onResponse(i, null);
                            return;
                        }
                        boolean n = gsd.n();
                        ReleaseLogUtil.d("WeightManagerUtil", "getWeightSampleConfig onResult data is not HiSampleConfigList isSync ", Boolean.valueOf(n), " isFirstRequest ", Boolean.valueOf(z));
                        if (!z || n) {
                            kpu.a(str, responseCallback);
                            return;
                        } else {
                            kpu.b(str, (ResponseCallback<Object>) responseCallback);
                            sqp.d(currentTimeMillis);
                            return;
                        }
                    }
                    List list = (List) obj;
                    if (koq.b(list)) {
                        if (!i3) {
                            responseCallback.onResponse(i, null);
                            return;
                        }
                        boolean n2 = gsd.n();
                        ReleaseLogUtil.d("WeightManagerUtil", "getWeightSampleConfig onResult list isEmpty isSync ", Boolean.valueOf(n2), " isFirstRequest ", Boolean.valueOf(z));
                        if (!z || n2) {
                            kpu.a(str, responseCallback);
                            return;
                        } else {
                            kpu.b(str, (ResponseCallback<Object>) responseCallback);
                            sqp.d(currentTimeMillis);
                            return;
                        }
                    }
                    kpu.c();
                    String configData = ((HiSampleConfig) list.get(0)).getConfigData();
                    if ("900300023".equals(str)) {
                        responseCallback.onResponse(0, (gsi) HiJsonUtil.e(configData, gsi.class));
                    } else if ("900300022".equals(str)) {
                        try {
                            responseCallback.onResponse(i, Float.valueOf((float) new JSONObject(configData).getDouble("weightGoal")));
                        } catch (JSONException e2) {
                            ReleaseLogUtil.c("R_WeightManagerUtil", "getWeightSampleConfig onResult exception ", ExceptionUtils.d(e2));
                            responseCallback.onResponse(i, null);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final String str, final ResponseCallback<Object> responseCallback) {
        if (NetworkUtil.j()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kpy
                @Override // java.lang.Runnable
                public final void run() {
                    grz.e((ResponseCallback<Object>) ResponseCallback.this, str);
                }
            });
        }
        c();
    }

    public static void b(long j, boolean z, String str, ResponseCallback<Object> responseCallback) {
        LogUtil.a("WeightManagerUtil", "processSyncResultBroadcast syncId ", Long.valueOf(j), " result ", Boolean.valueOf(z));
        if (z) {
            gsd.b(System.currentTimeMillis());
            b(str, false, responseCallback);
        } else {
            responseCallback.onResponse(-1, null);
        }
    }

    public static gsi d(gse gseVar, float f, int i, int i2) {
        gsg b;
        gsi gsiVar = new gsi();
        gsiVar.c(f);
        gsiVar.e(i);
        gsb gsbVar = new gsb();
        gsf gsfVar = new gsf();
        if (i == 1) {
            if (gseVar != null && (b = gseVar.b()) != null) {
                gsiVar.b(b.a());
                gsiVar.c(b.d());
            }
            gsbVar.b(i2);
            float[][] fArr = d;
            int i3 = i2 - 1;
            gsbVar.e(fArr[i3][0]);
            gsbVar.a(fArr[i3][1]);
            gsbVar.a(f14537a[i3][0]);
            gsfVar.c(30);
            gsfVar.a(40);
            gsfVar.b(25);
            gsfVar.e(5);
        } else {
            gsiVar.b(0);
            gsiVar.c(0);
            gsbVar.b(1);
            gsbVar.e(0.0f);
            gsbVar.a(0.0f);
            gsbVar.a(0);
            gsfVar.c(0);
            gsfVar.a(0);
            gsfVar.b(0);
            gsfVar.e(0);
        }
        gsiVar.b(gsbVar);
        gsiVar.b(gsfVar);
        return gsiVar;
    }

    public static void e(double d2, long j, ResponseCallback<Double> responseCallback) {
        d(d2, j, responseCallback, false);
    }

    public static void d(final double d2, final long j, final ResponseCallback<Double> responseCallback, final boolean z) {
        if (!Utils.i()) {
            ReleaseLogUtil.e("WeightManagerUtil", "saveWeightGoal ifAllowLogin false");
            e(d2, responseCallback);
            return;
        }
        if (d2 > 1000.0d) {
            LogUtil.a("WeightManagerUtil", "targetWeight is too big, targetWeight is: ", Double.valueOf(d2));
            if (responseCallback != null) {
                responseCallback.onResponse(-1, Double.valueOf(d2));
                return;
            }
            return;
        }
        boolean a2 = jfa.a();
        ReleaseLogUtil.e("WeightManagerUtil", "saveWeightGoal isPullAllStatus ", Boolean.valueOf(a2));
        if (a2) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("weightGoal", Double.valueOf(d2));
            sqp.b("900300022", jsonObject.toString(), j, new HiDataOperateListener() { // from class: kpx
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i, Object obj) {
                    kpu.c(ResponseCallback.this, d2, j, z, i, obj);
                }
            });
        } else if (responseCallback != null) {
            responseCallback.onResponse(-1, Double.valueOf(d2));
        }
    }

    static /* synthetic */ void c(final ResponseCallback responseCallback, final double d2, final long j, final boolean z, int i, Object obj) {
        LogUtil.a("WeightManagerUtil", "saveWeightGoal errorCode ", Integer.valueOf(i), " object ", obj);
        ThreadPoolManager.d().execute(new Runnable() { // from class: kqb
            @Override // java.lang.Runnable
            public final void run() {
                sqp.c("900300022", new HiDataReadResultListener() { // from class: kpu.4
                    @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                    public void onResultIntent(int i2, Object obj2, int i3, int i4) {
                    }

                    @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                    public void onResult(Object obj2, int i2, int i3) {
                        if (ResponseCallback.this == null) {
                            return;
                        }
                        if (i2 != 0 || !koq.e(obj2, HiSampleConfig.class)) {
                            ResponseCallback.this.onResponse(-1, Double.valueOf(r2));
                            return;
                        }
                        List list = (List) obj2;
                        if (koq.b(list) || list.get(0) == null) {
                            ResponseCallback.this.onResponse(-1, Double.valueOf(r2));
                            LogUtil.h("WeightManagerUtil", "empty list");
                        } else {
                            if (((HiSampleConfig) list.get(0)).getModifiedTime() != r4) {
                                if (r6) {
                                    ResponseCallback.this.onResponse(-1, Double.valueOf(r2));
                                } else {
                                    ResponseCallback.this.onResponse(0, Double.valueOf(r2));
                                }
                                LogUtil.h("WeightManagerUtil", "update failed!");
                                return;
                            }
                            ResponseCallback.this.onResponse(i2, Double.valueOf(r2));
                            sqp.d(System.currentTimeMillis());
                            kpu.e(r2, (ResponseCallback<Double>) null);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final double d2, final ResponseCallback<Double> responseCallback) {
        LogUtil.a("WeightManagerUtil", "setWeightGoalInfo targetWeight ", Double.valueOf(d2));
        if (d2 <= 0.0d) {
            if (responseCallback != null) {
                responseCallback.onResponse(-1, Double.valueOf(d2));
            }
        } else {
            HiGoalInfo hiGoalInfo = new HiGoalInfo();
            hiGoalInfo.setGoalType(5);
            hiGoalInfo.setGoalValue(d2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(hiGoalInfo);
            HiHealthManager.d(BaseApplication.e()).setGoalInfo(0, arrayList, new HiCommonListener() { // from class: kpu.2
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    LogUtil.a("WeightManagerUtil", "setWeightGoalInfo onSuccess resultCode ", Integer.valueOf(i), " message ", obj);
                    ResponseCallback responseCallback2 = ResponseCallback.this;
                    if (responseCallback2 != null) {
                        responseCallback2.onResponse(i, Double.valueOf(d2));
                    }
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    ReleaseLogUtil.d("WeightManagerUtil", "setWeightGoalInfo onFailure resultCode ", Integer.valueOf(i), " message ", obj);
                    ResponseCallback responseCallback2 = ResponseCallback.this;
                    if (responseCallback2 != null) {
                        responseCallback2.onResponse(i, Double.valueOf(d2));
                    }
                }
            });
        }
    }

    public static void c(gsi gsiVar, long j, boolean z, ResponseCallback<gsi> responseCallback) {
        e(gsiVar, j, z, responseCallback, false, null);
    }

    public static void e(final gsi gsiVar, final long j, final boolean z, final ResponseCallback<gsi> responseCallback, final boolean z2, gsh gshVar) {
        if (!Utils.i()) {
            ReleaseLogUtil.e("WeightManagerUtil", "saveWeightManager ifAllowLogin false");
            if (gsiVar != null) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: kpz
                    @Override // java.lang.Runnable
                    public final void run() {
                        kpu.b(gsi.this, responseCallback);
                    }
                });
                return;
            } else {
                if (responseCallback != null) {
                    responseCallback.onResponse(-1, null);
                    return;
                }
                return;
            }
        }
        boolean a2 = jfa.a();
        ReleaseLogUtil.e("WeightManagerUtil", "saveWeightManager isPullAllStatus ", Boolean.valueOf(a2));
        if (!a2) {
            if (responseCallback != null) {
                responseCallback.onResponse(-1, gsiVar);
            }
        } else {
            if (gshVar == null) {
                gshVar = ((WeightApi) Services.c("Main", WeightApi.class)).getOnePointFull(gsiVar.g());
            }
            if (gshVar != null) {
                gsiVar.e(gshVar);
            }
            sqp.b("900300023", HiJsonUtil.e(gsiVar), j, new HiDataOperateListener() { // from class: kqa
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i, Object obj) {
                    kpu.b(gsi.this, j, responseCallback, z2, z, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void b(gsi gsiVar, ResponseCallback responseCallback) {
        boolean a2 = a(gsiVar.d());
        ReleaseLogUtil.e("WeightManagerUtil", "saveWeightManager isSuccess ", Boolean.valueOf(a2));
        if (responseCallback == null) {
            return;
        }
        responseCallback.onResponse(a2 ? 0 : -1, gsiVar);
    }

    static /* synthetic */ void b(gsi gsiVar, long j, ResponseCallback responseCallback, boolean z, boolean z2, int i, Object obj) {
        LogUtil.a("WeightManagerUtil", "saveWeightManager errorCode ", Integer.valueOf(i), " object ", obj);
        c(gsiVar, j, responseCallback, z, z2);
    }

    private static void c(final gsi gsiVar, final long j, final ResponseCallback<gsi> responseCallback, final boolean z, final boolean z2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: kqd
            @Override // java.lang.Runnable
            public final void run() {
                sqp.c("900300023", new kpu.AnonymousClass3(ResponseCallback.this, gsiVar, j, z, z2));
            }
        });
    }

    /* renamed from: kpu$3, reason: invalid class name */
    class AnonymousClass3 implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f14540a;
        final /* synthetic */ ResponseCallback b;
        final /* synthetic */ boolean c;
        final /* synthetic */ long d;
        final /* synthetic */ gsi e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass3(ResponseCallback responseCallback, gsi gsiVar, long j, boolean z, boolean z2) {
            this.b = responseCallback;
            this.e = gsiVar;
            this.d = j;
            this.f14540a = z;
            this.c = z2;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (i != 0 || !koq.e(obj, HiSampleConfig.class)) {
                ResponseCallback responseCallback = this.b;
                if (responseCallback != null) {
                    responseCallback.onResponse(-1, this.e);
                    return;
                }
                return;
            }
            List list = (List) obj;
            if (koq.b(list) || list.get(0) == null) {
                ResponseCallback responseCallback2 = this.b;
                if (responseCallback2 != null) {
                    responseCallback2.onResponse(-1, this.e);
                }
                LogUtil.h("WeightManagerUtil", "empty list");
                return;
            }
            if (((HiSampleConfig) list.get(0)).getModifiedTime() != this.d) {
                ResponseCallback responseCallback3 = this.b;
                if (responseCallback3 != null) {
                    responseCallback3.onResponse(this.f14540a ? -1 : 0, this.e);
                }
                LogUtil.h("WeightManagerUtil", "update failed!");
                return;
            }
            gsd.a(this.e);
            ResponseCallback responseCallback4 = this.b;
            if (responseCallback4 != null) {
                responseCallback4.onResponse(i, this.e);
            }
            ThreadPoolManager d = ThreadPoolManager.d();
            final boolean z = this.c;
            final gsi gsiVar = this.e;
            d.execute(new Runnable() { // from class: kqf
                @Override // java.lang.Runnable
                public final void run() {
                    kpu.AnonymousClass3.b(z, gsiVar);
                }
            });
        }

        static /* synthetic */ void b(boolean z, gsi gsiVar) {
            sqp.d(System.currentTimeMillis());
            if (z && gsiVar != null) {
                kpu.a(gsiVar.d());
            } else {
                LogUtil.h("WeightManagerUtil", "saveWeightManager isRefreshInitWeight ", Boolean.valueOf(z), " weightManager ", gsiVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean a(float f) {
        LogUtil.a("WeightManagerUtil", "setStartWeight startWeight ", Float.valueOf(f));
        if (f <= 0.0f) {
            return false;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.start_weight_base");
        hiUserPreference.setValue(String.valueOf(f));
        boolean userPreference = HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
        c((ResponseCallback<Object>) null);
        return userPreference;
    }

    public static void c(final ResponseCallback<Object> responseCallback) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10026);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, new HiCommonListener() { // from class: kpu.5
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                ReleaseLogUtil.e("WeightManagerUtil", "syncUserPreference onSuccess errorCode ", Integer.valueOf(i), " object ", obj);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 != null) {
                    responseCallback2.onResponse(i, obj);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                ReleaseLogUtil.d("WeightManagerUtil", "syncUserPreference onFailure errorCode ", Integer.valueOf(i), " errorMessage ", obj);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 != null) {
                    responseCallback2.onResponse(i, obj);
                }
            }
        });
    }
}
