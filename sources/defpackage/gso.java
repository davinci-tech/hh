package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import com.amap.api.maps.MapsInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.healthlinkage.api.HWhealthLinkageApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.physicalfitness.h5.PhysicalFitnessH5Callback;
import com.huawei.healthcloud.plugintrack.ui.activity.SportBaseActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.IBaseStatusCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public class gso extends mml {
    private Context h;
    private int l;
    private IBaseStatusCallback m;
    private PhysicalFitnessH5Callback<gyp> n;
    public static final String b = SecurityConstant.d;
    private static final String d = Integer.toString(20002);
    private static final Object c = new Object();
    private static volatile gso e = null;
    private int q = 0;
    private PluginSportTrackAdapter r = null;
    private ISportDataCallback p = null;
    private long s = 0;
    private ISportDataCallback t = null;
    private ArrayList i = new ArrayList();
    private boolean o = false;
    private boolean j = false;
    private boolean k = false;
    private long g = 0;
    private Handler f = new Handler(Looper.getMainLooper()) { // from class: gso.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            Context d2 = gxf.d();
            int i = message.what;
            if (i == 100) {
                TrackMainMapActivity.e(d2);
                gtx.c(d2).by();
            } else {
                if (i != 101) {
                    return;
                }
                TrackMainMapActivity.b(d2);
                gtx.c(d2).bv();
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private guz f12911a = new guz();

    private boolean c(int i, float f) {
        if (i == 259 && f > 30.0f) {
            return true;
        }
        if ((i == 258 || i == 264) && f > 12.0f) {
            return true;
        }
        return i == 257 && f > 7.0f;
    }

    private boolean f(int i) {
        return i == 282 || i == 260;
    }

    private boolean g(int i) {
        return i == 3 || i == 4;
    }

    @Override // defpackage.mml
    public void finish() {
    }

    private gso() {
    }

    public static gso e() {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new gso();
                }
            }
        }
        if (e.c() == null) {
            LogUtil.h("Track_PluginSportTrack", "PluginSportTrackAdapter == null ");
            ag();
        }
        return e;
    }

    private static void ag() {
        try {
            Object invoke = Class.forName("com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl").getMethod("getInstance", Context.class).invoke(null, BaseApplication.getContext());
            if (!(invoke instanceof PluginSportTrackAdapter)) {
                LogUtil.b("Track_PluginSportTrack", "initAdapter fail: get Instance fail");
            } else {
                LogUtil.a("Track_PluginSportTrack", "initAdapter success:", invoke);
                e.setAdapter((PluginSportTrackAdapter) invoke);
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e2) {
            LogUtil.b("Track_PluginSportTrack", "initAdapter failed", LogAnonymous.b(e2));
        }
    }

    public void e(IBaseStatusCallback iBaseStatusCallback) {
        LogUtil.a("Track_PluginSportTrack", "registerSportStatusListen enter");
        this.m = iBaseStatusCallback;
    }

    public void e(PhysicalFitnessH5Callback<gyp> physicalFitnessH5Callback) {
        LogUtil.a("Track_PluginSportTrack", "registerPhysicalFitnessCallBack enter");
        this.n = physicalFitnessH5Callback;
    }

    public void b(int i, long j) {
        ReleaseLogUtil.e("Track_PluginSportTrack", "sendPhysicalFitnessResult sportType = ", Integer.valueOf(i), ", result = ", Long.valueOf(j));
        PhysicalFitnessH5Callback<gyp> physicalFitnessH5Callback = this.n;
        if (physicalFitnessH5Callback == null) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "sendPhysicalFitnessResult failed, mPhysicalFitnessCallBack is null");
            return;
        }
        if (j == -1) {
            physicalFitnessH5Callback.onFailure(-1, "data is error");
            return;
        }
        gyp gypVar = new gyp();
        gypVar.c(i);
        gypVar.d(j);
        this.n.onSuccess(gypVar);
    }

    public void t() {
        LogUtil.c("Track_PluginSportTrack", "mark MotionTrack status onOccupied");
        IBaseStatusCallback iBaseStatusCallback = this.m;
        if (iBaseStatusCallback != null) {
            iBaseStatusCallback.onOccupied();
        }
    }

    public void x() {
        LogUtil.c("Track_PluginSportTrack", "mark MotionTrack status onAvailable");
        IBaseStatusCallback iBaseStatusCallback = this.m;
        if (iBaseStatusCallback != null) {
            iBaseStatusCallback.onAvailable();
        }
    }

    @Override // defpackage.mml
    public void init(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        Context applicationContext = context.getApplicationContext();
        this.h = applicationContext;
        gxf.c(applicationContext);
        if (getAdapter() instanceof PluginSportTrackAdapter) {
            this.r = (PluginSportTrackAdapter) getAdapter();
        }
    }

    @Override // defpackage.mml
    public void setAdapter(PluginBaseAdapter pluginBaseAdapter) {
        if (pluginBaseAdapter == null) {
            return;
        }
        super.setAdapter(pluginBaseAdapter);
        if (pluginBaseAdapter instanceof PluginSportTrackAdapter) {
            this.r = (PluginSportTrackAdapter) pluginBaseAdapter;
        }
    }

    public PluginSportTrackAdapter c() {
        if (getAdapter() instanceof PluginSportTrackAdapter) {
            PluginSportTrackAdapter pluginSportTrackAdapter = (PluginSportTrackAdapter) getAdapter();
            this.r = pluginSportTrackAdapter;
            return pluginSportTrackAdapter;
        }
        return this.r;
    }

    public boolean m() {
        return this.o;
    }

    private void af() {
        try {
            MapsInitializer.sdcardDir = new File(CommonUtil.j(this.h), "amap").getCanonicalPath();
        } catch (IOException e2) {
            LogUtil.b("Track_PluginSportTrack", "not ", LogAnonymous.b((Throwable) e2));
        }
    }

    private void z() {
        gtx c2 = gtx.c(gxf.d());
        if (c2 == null || c2.r() != 1) {
            return;
        }
        c2.o(false);
        gvv.c(this.h, "motion_path2.txt");
        gvv.c(this.h, "simplemotion.txt");
    }

    public int c(int i, int i2, int i3, float f, ISportDataCallback iSportDataCallback, Context context) {
        return aTs_(i, aTm_(i2, i3, f, -1), iSportDataCallback, context, null);
    }

    public int aTs_(int i, Bundle bundle, ISportDataCallback iSportDataCallback, Context context, String str) {
        LogUtil.a("Track_PluginSportTrack", "is startTrack");
        int i2 = bundle.getInt("map_tracking_sport_type_sportting", 0);
        int i3 = bundle.getInt("sport_target_type_sportting", -1);
        float f = bundle.getFloat("sport_target_value_sportting", -1.0f);
        int i4 = bundle.getInt("trackFrom", -1);
        SharedPreferenceManager.e(this.h, String.valueOf(20002), "map_tracking_sport_type", String.valueOf(i2), new StorageParams());
        cav.d(context);
        ak();
        if (gwg.e(i2)) {
            return a(aTc_(i, i2, i3, f, i4, bundle), context);
        }
        Context g = g(context);
        int intValue = a(g, i2).intValue();
        if (intValue != 0) {
            LogUtil.h("Track_PluginSportTrack", "startTrack failed with error code:", Integer.valueOf(intValue));
            return intValue;
        }
        int i5 = i(g);
        if (i5 != 0) {
            return i5;
        }
        LogUtil.a("Track_PluginSportTrack", "is setTrackType");
        j(i);
        gtx c2 = gtx.c(gxf.d());
        c2.ao();
        c2.a(i2);
        c2.as();
        c2.aUe_(bundle);
        Bundle aTd_ = aTd_(bundle, i2, i3, f, str);
        ReleaseLogUtil.e("Track_PluginSportTrack", "startTrack: track = ", Integer.valueOf(i), ", sportType = ", Integer.valueOf(i2), ", target = ", Integer.valueOf(i3), ", targetValue = ", Float.valueOf(f), ", trackFrom = ", Integer.valueOf(i4));
        if (aTd_ == null) {
            return 3;
        }
        hab.c(i2, i3, false);
        a(i3, iSportDataCallback, i2);
        if (BaseApplication.getContext() == null) {
            LogUtil.h("Track_PluginSportTrack", "startTrack but isStartTrackSport is true");
            return 1;
        }
        if (i2 == 264) {
            guw.d();
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) TrackMainMapActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("sportdataparams", aTd_);
        gnm.aPB_(g, intent);
        c(0);
        e(i2);
        return 0;
    }

    public Bundle aTm_(int i, int i2, float f, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("map_tracking_sport_type_sportting", i);
        bundle.putInt("sport_target_type_sportting", i2);
        bundle.putInt("origintarget", i2);
        bundle.putFloat("sport_target_value_sportting", f);
        bundle.putInt("trackFrom", i3);
        return bundle;
    }

    public Bundle aTn_(int i, int i2, float f, int i3, int i4) {
        Bundle aTm_ = aTm_(i, i2, f, i3);
        aTm_.putInt("biTrackFrom", i4);
        return aTm_;
    }

    private void ak() {
        gtx c2 = gtx.c(gxf.d());
        if (c2.r() == 1) {
            if (c2.getDistance() >= this.f12911a.c()) {
                a(true);
            } else {
                z();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int a(final SportLaunchParams sportLaunchParams, final Context context) {
        if (!HandlerExecutor.b()) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "startNewTrack not in ui thread.");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: gsr
                @Override // java.lang.Runnable
                public final void run() {
                    gso.this.a(sportLaunchParams, context);
                }
            });
            return 0;
        }
        Context g = g(context);
        int b2 = b(g, sportLaunchParams.getSportType());
        if (b2 != 0) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "startNewTrack failed with error code:", Integer.valueOf(b2));
            return b2;
        }
        int i = i(g);
        if (i != 0) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "startNewTrack failed with error code:", Integer.valueOf(i));
            return i;
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportBaseActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        if (sportLaunchParams.getSportType() == 283) {
            boolean equals = "true".equals(ash.b("ROPE_DISPLAY_PROCESS"));
            int intValue = ((Integer) sportLaunchParams.getExtra("trackFrom", Integer.class, -1)).intValue();
            if (!equals && o() && intValue != 1) {
                e().c(283, sportLaunchParams.getSportTarget(), sportLaunchParams.getTargetValue());
            } else {
                fgb.awY_(g, intent);
                SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
                if (sportDataOutputApi == null) {
                    LogUtil.h("Track_PluginSportTrack", "sportDataOutputApi == null");
                    return 1;
                }
                sportDataOutputApi.registerDeviceDataCallback(this.p);
            }
        } else if (!fgb.awV_(sportLaunchParams.getSportType(), g, intent)) {
            gnm.aPB_(g, intent);
        }
        e(sportLaunchParams.getSportType());
        return 0;
    }

    private SportLaunchParams aTc_(int i, int i2, int i3, float f, int i4, Bundle bundle) {
        SportLaunchParams sportLaunchParams = (SportLaunchParams) bundle.getParcelable("bundle_key_sport_launch_paras");
        if (sportLaunchParams == null) {
            sportLaunchParams = new SportLaunchParams();
        }
        sportLaunchParams.setSportType(i2).setSportTarget(i3).setTargetValue(f).setDataSource(aa()).addExtra("trackFrom", Integer.valueOf(i4));
        ReleaseLogUtil.e("Track_PluginSportTrack", "startTrack: track = ", Integer.valueOf(i), ", sportType = ", Integer.valueOf(i2), ", target = ", Integer.valueOf(i3), ", targetValue = ", Float.valueOf(f), ", trackFrom = ", Integer.valueOf(i4));
        return sportLaunchParams;
    }

    private int aa() {
        LogUtil.a("Track_PluginSportTrack", "isRopeDeviceConnected() ", Boolean.valueOf(o()));
        return o() ? 5 : 7;
    }

    private int b(Context context, int i) {
        int c2 = c(context, i);
        if (c2 != 0) {
            return c2;
        }
        int d2 = d(context, i);
        if (d2 != 0) {
            return d2;
        }
        return 0;
    }

    private int c(Context context, int i) {
        if (i != 283 || SportSupportUtil.h() || o()) {
            return 0;
        }
        nrh.d(context, context.getResources().getString(R.string._2130844951_res_0x7f021d17));
        return 11;
    }

    private Integer a(Context context, int i) {
        int d2 = d(context, i);
        if (d2 != 0) {
            return Integer.valueOf(d2);
        }
        int e2 = e(context, i);
        if (e2 != 0) {
            return Integer.valueOf(e2);
        }
        return 0;
    }

    private void a(int i, ISportDataCallback iSportDataCallback, int i2) {
        if (g(i) && iSportDataCallback != null) {
            this.t = iSportDataCallback;
            ReleaseLogUtil.e("Track_PluginSportTrack", "register ISportDataCallback for suggestion ret = ", Integer.valueOf(gtx.c(gxf.d()).a(this.t, 1L)));
        }
        if (this.p == null || f(i2)) {
            return;
        }
        if (cap.a()) {
            cap.b(new IBaseResponseCallback() { // from class: gst
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj) {
                    gso.this.a(i3, obj);
                }
            });
        } else {
            ac();
        }
    }

    /* synthetic */ void a(int i, Object obj) {
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() == 0 || a(num.intValue())) {
                ac();
                return;
            }
        }
        ReleaseLogUtil.d("Track_PluginSportTrack", "addSportDataCallback getDeviceSportStatus errorCode:", Integer.valueOf(i), " objData:", obj);
    }

    private boolean a(int i) {
        boolean am = gtx.c(gxf.d()).am();
        ReleaseLogUtil.e("Track_PluginSportTrack", "isCrashRecovery isCrash = ", Boolean.valueOf(am), " deviceStatus = ", Integer.valueOf(i));
        return i == 1 && am;
    }

    private void ac() {
        int m = gtx.c(gxf.d()).m();
        ReleaseLogUtil.e("Track_PluginSportTrack", "addLinkSportData(): sport status:", Integer.valueOf(m), " mWearLinkSportDataCallback:", this.p, " mWearLinkSportDataReportInterval:", Long.valueOf(this.s));
        if (m == 0 || m == 6) {
            ReleaseLogUtil.e("Track_PluginSportTrack", "addLinkSportData(): sport status:", Integer.valueOf(m), " mWearLinkSportDataCallback:", this.p, " mWearLinkSportDataReportInterval:", Long.valueOf(this.s), " ret:", Integer.valueOf(gtx.c(gxf.d()).a(this.p, this.s)));
        }
    }

    private Bundle aTd_(Bundle bundle, int i, int i2, float f, String str) {
        gtx c2 = gtx.c(gxf.d());
        bundle.putBoolean("indoor_Running_Info", c2.e());
        e(i, i2, f);
        String num = Integer.toString(20002);
        if (g(i2) && c2.am()) {
            try {
                ArrayList arrayList = (ArrayList) gvv.a(SharedPreferenceManager.b(this.h, num, "sport_plan_target_new"), new TypeToken<ArrayList<ffd>>() { // from class: gso.2
                });
                if (arrayList != null && arrayList.size() > 0) {
                    ReleaseLogUtil.e("Track_PluginSportTrack", "recover TARGET_PLAN on error sp");
                    c2.a(arrayList);
                } else {
                    i2 = -1;
                    bundle.putInt("origintarget", -1);
                    bundle.putFloat("sport_target_value_sportting", -1.0f);
                    bundle.putInt("sport_target_type_sportting", -1);
                }
            } catch (NumberFormatException unused) {
                SharedPreferenceManager.c(this.h, num, "sport_plan_target_new");
                LogUtil.b("Track_PluginSportTrack", "NumberFormatException : get SegmentedTarget data error , delete sp!");
                return null;
            }
        }
        if (g(i2) && c2.au() && c2.l() != null && c2.l().size() > 0) {
            ffd ffdVar = c2.l().get(0);
            bundle.putInt("sport_target_type_sportting", ffdVar.f());
            bundle.putFloat("sport_target_value_sportting", ffdVar.i());
        }
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("PATH_ID", str);
        }
        return bundle;
    }

    private void e(int i, int i2, float f) {
        StorageParams storageParams = new StorageParams();
        Context context = this.h;
        String str = d;
        SharedPreferenceManager.e(context, str, "sporting_sport_type_for_restart", Integer.toString(i), storageParams);
        SharedPreferenceManager.e(this.h, str, "sporting_target_type_for_restart", Integer.toString(i2), storageParams);
        SharedPreferenceManager.e(this.h, str, "sporting_target_value_for_restart", Float.toString(f), storageParams);
    }

    public void a() {
        Context context = this.h;
        String str = d;
        SharedPreferenceManager.c(context, str, "sporting_sport_type_for_restart");
        SharedPreferenceManager.c(this.h, str, "sporting_target_type_for_restart");
        SharedPreferenceManager.c(this.h, str, "sporting_target_value_for_restart");
        SharedPreferenceManager.c(this.h, str, "sport_plan_target_new");
    }

    private int i(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("IndoorEquipServiceRunning" + CommonUtil.e(this.h), 0);
        if (sharedPreferences != null) {
            boolean z = sharedPreferences.getBoolean("IsIndoorEquipServiceRunning" + CommonUtil.e(this.h), false);
            long j = sharedPreferences.getLong("elapsedRealtime", 0L);
            long elapsedRealtime = SystemClock.elapsedRealtime() - j;
            LogUtil.a("Track_PluginSportTrack", "isIndoorEquipSerRunning ", Boolean.valueOf(z), "flagTime ", Long.valueOf(j), "timeDiff ", Long.valueOf(elapsedRealtime));
            if (z && elapsedRealtime < 2000 && elapsedRealtime > 0) {
                Intent intent = new Intent("com.huawei.health.START_INDOOR_EQUIP_MODULE");
                intent.setPackage(context.getPackageName());
                context.sendBroadcast(intent, b);
                return 9;
            }
        }
        gtx c2 = gtx.c(gxf.d());
        if (c2.bc() && c2.r() != 1) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "startTrack but isStartTrackSport is true");
            return 1;
        }
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (sportDataOutputApi == null) {
            LogUtil.h("Track_PluginSportTrack", "sportDataOutputApi == null");
            return 1;
        }
        if (sportDataOutputApi.getStatus() == 0 || sportDataOutputApi.getStatus() == 3) {
            return 0;
        }
        ReleaseLogUtil.d("Track_PluginSportTrack", "startTrack but sport status is not idle");
        return 1;
    }

    private Context g(Context context) {
        if (context instanceof Activity) {
            return context;
        }
        ReleaseLogUtil.d("Track_PluginSportTrack", "startTrack only take Activity as context");
        return BaseApplication.getActivity() != null ? BaseApplication.getActivity() : context;
    }

    private int d(Context context, int i) {
        PermissionUtil.PermissionType d2 = d(i);
        PermissionUtil.PermissionResult e2 = PermissionUtil.e(context, d2);
        if (e2 == PermissionUtil.PermissionResult.FOREVER_DENIED) {
            return caq.b(context, d2, e2, false);
        }
        return caq.b(context, d2, e2);
    }

    private int e(Context context, int i) {
        if (context == null) {
            LogUtil.h("Track_PluginSportTrack", "showDialogContext is null");
            return 5;
        }
        gwg.b(context);
        if (!gwg.l() || gwg.c(context) || i == 264) {
            return 0;
        }
        ReleaseLogUtil.e("Track_PluginSportTrack", "not have GooglePlayServices!");
        if (!(context instanceof Activity)) {
            LogUtil.h("Track_PluginSportTrack", "showDialogContext is not activity context");
            return 6;
        }
        try {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
            builder.e(context.getString(R.string._2130837688_res_0x7f0200b8)).czC_(R.string._2130850249_res_0x7f0231c9, new View.OnClickListener() { // from class: gso.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            builder.e().show();
            LogUtil.h("Track_PluginSportTrack", "showDialogContext has no google service");
            return 2;
        } catch (WindowManager.BadTokenException unused) {
            LogUtil.h("Track_PluginSportTrack", "showDialogContext cant be used to show dialog");
            return 6;
        }
    }

    private void e(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
        ixx.d().d(this.h, AnalyticsValue.MOTION_TRACK_1040027.value(), hashMap, 0);
        int e2 = gvv.e(i);
        if (e2 != 0) {
            OpAnalyticsUtil.getInstance().setEventWithActionType(e2, OperationKey.HEALTH_APP_RUN_START_2050005.value());
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("actiontype", String.valueOf(e2));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_PVUV_85050001.value(), linkedHashMap);
        }
    }

    public int v() {
        gtx c2 = gtx.c(gxf.d());
        if (ai()) {
            return 9;
        }
        int m = c2.m();
        if (m == 1) {
            ReleaseLogUtil.e("Track_PluginSportTrack", "Pause Track by outter operation");
            c2.c(false, 0);
            return 0;
        }
        ReleaseLogUtil.d("Track_PluginSportTrack", "You can pauseTrack just when sporting, currentStatus = ", Integer.valueOf(m));
        return 1;
    }

    public int y() {
        gtx c2 = gtx.c(gxf.d());
        if (ai()) {
            return 9;
        }
        int m = c2.m();
        if (m == 2) {
            ReleaseLogUtil.e("Track_PluginSportTrack", "Resume Track by outer operation");
            c2.bu();
            return 0;
        }
        ReleaseLogUtil.d("Track_PluginSportTrack", "You can resumeTrack just when pause, currentStatus = ", Integer.valueOf(m));
        return 1;
    }

    public void w() {
        this.o = true;
        af();
    }

    private boolean ai() {
        Context context = this.h;
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("IndoorEquipServiceRunning" + CommonUtil.e(this.h), 0);
        if (sharedPreferences != null) {
            boolean z = sharedPreferences.getBoolean("IsIndoorEquipServiceRunning" + CommonUtil.e(this.h), false);
            long j = sharedPreferences.getLong("elapsedRealtime", 0L);
            long elapsedRealtime = SystemClock.elapsedRealtime() - j;
            LogUtil.a("Track_PluginSportTrack", "isIndoorEquipSerRunning", Boolean.valueOf(z), "flagTime ", Long.valueOf(j), "timeDiff ", Long.valueOf(elapsedRealtime));
            if (z && elapsedRealtime < 2000 && elapsedRealtime > 0) {
                Intent intent = new Intent("com.huawei.health.START_INDOOR_EQUIP_MODULE");
                intent.setPackage(this.h.getPackageName());
                this.h.sendBroadcast(intent, b);
                return true;
            }
        }
        return false;
    }

    public void u() {
        gtx c2 = gtx.c(gxf.d());
        c2.a(false);
        c2.f(false);
        if (c2.j() >= 100.0f) {
            c2.bo();
        } else {
            c2.bm();
        }
    }

    public int c(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        PluginSportTrackAdapter pluginSportTrackAdapter = this.r;
        if (pluginSportTrackAdapter != null) {
            pluginSportTrackAdapter.saveTrackData(motionPathSimplify, motionPath);
            return 0;
        }
        LogUtil.h("Track_PluginSportTrack", "mTrackAdapter == NULL");
        return 5;
    }

    public void d(List<ffd> list) {
        gtx.c(gxf.d()).a(list);
    }

    public void aTr_(int i, boolean z, boolean z2, Bundle bundle) {
        LogUtil.a("Track_PluginSportTrack", "PluginSportTrack startTrack ", Boolean.valueOf(z2));
        if (this.h == null) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "startBackgroundTrack mContext is null");
            return;
        }
        if (PermissionUtil.e(this.h, PermissionUtil.c(PermissionUtil.PermissionType.LOCATION))) {
            aTj_(i, z, z2, bundle);
        }
    }

    private void aTj_(int i, boolean z, boolean z2, Bundle bundle) {
        if (z2 && this.f12911a == null) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "auto track config not avaliable");
            return;
        }
        if (gtx.c(gxf.d()).bc() || this.r == null) {
            ReleaseLogUtil.e("Track_PluginSportTrack", "Tracking Running, exit");
        } else if (!z2) {
            aTk_(i, z, z2, bundle);
        } else {
            this.g = System.currentTimeMillis();
            aTb_(i, z, z2, bundle, 3);
        }
    }

    private void aTb_(final int i, final boolean z, final boolean z2, final Bundle bundle, final int i2) {
        boolean a2 = cap.a();
        LogUtil.a("Track_PluginSportTrack", "startAutoTrackForGranted state:", Integer.valueOf(i), " isClassOne:", Boolean.valueOf(z), " isAutoTrack:", Boolean.valueOf(z2), " repeatNum:", Integer.valueOf(i2));
        if (c(a2) && i2 > 0) {
            HandlerExecutor.d(new Runnable() { // from class: gsp
                @Override // java.lang.Runnable
                public final void run() {
                    gso.this.aTp_(i, z, z2, bundle, i2);
                }
            }, 2000L);
            return;
        }
        if (System.currentTimeMillis() - this.g > PreConnectManager.CONNECT_INTERNAL) {
            ReleaseLogUtil.e("Track_PluginSportTrack", " startAutoTrack has delay! return ");
        } else if (cap.a()) {
            cap.b(new c(this, i, z, true, bundle));
        } else {
            aTk_(i, z, z2, bundle);
        }
    }

    /* synthetic */ void aTp_(int i, boolean z, boolean z2, Bundle bundle, int i2) {
        aTb_(i, z, z2, bundle, i2 - 1);
    }

    private boolean c(boolean z) {
        boolean isBindPhoneService = cun.c().isBindPhoneService();
        HWhealthLinkageApi hWhealthLinkageApi = (HWhealthLinkageApi) Services.a("HWhealthLinkage", HWhealthLinkageApi.class);
        boolean z2 = hWhealthLinkageApi != null && hWhealthLinkageApi.isMediatorInit();
        LogUtil.a("Track_PluginSportTrack", "isNeedWaitForInitWork: isNeedCheckSportStatus:", Boolean.valueOf(z), " isBindPhoneService:", Boolean.valueOf(isBindPhoneService), " isLinkageInit:", Boolean.valueOf(z2));
        return !(z || isBindPhoneService) || (z && !z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aTk_(int i, boolean z, boolean z2, Bundle bundle) {
        gwo.e(this.h, "auto_detect_motion_path.txt");
        gtx c2 = gtx.c(gxf.d());
        if (z2) {
            this.f12911a.d(this.h);
            c2.ao();
            c2.b(i, this.f12911a, z);
            ae();
            c2.aUf_(bundle);
        } else {
            c2.j(bundle.getInt("keySportRecordIsToSave", 1));
            c2.e(i);
        }
        if (this.p != null && !f(c2.n())) {
            gtx.c(gxf.d()).a(this.p, this.s);
        }
        c2.as();
        c2.bt();
        if (this.f != null) {
            if (c2.u()) {
                this.f.sendEmptyMessage(100);
            } else {
                this.f.sendEmptyMessageDelayed(100, 3000L);
            }
        }
    }

    /* loaded from: classes4.dex */
    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        boolean f12912a;
        int b;
        WeakReference<gso> c;
        Bundle d;
        boolean e;

        c(gso gsoVar, int i, boolean z, boolean z2, Bundle bundle) {
            this.c = new WeakReference<>(gsoVar);
            this.b = i;
            this.f12912a = z;
            this.e = z2;
            this.d = bundle;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Track_PluginSportTrack", "checkDeviceSportStatus: onResponse ", obj);
            gso gsoVar = this.c.get();
            if (gsoVar != null) {
                if ((obj instanceof Integer) && ((Integer) obj).intValue() == 0) {
                    ReleaseLogUtil.e("Track_PluginSportTrack", "not running");
                    gsoVar.aTk_(this.b, this.f12912a, this.e, this.d);
                    return;
                } else {
                    ReleaseLogUtil.e("Track_PluginSportTrack", "device is running, no need to start auto track.");
                    return;
                }
            }
            ReleaseLogUtil.d("Track_PluginSportTrack", "activity or handler is null");
        }
    }

    public static void a(boolean z) {
        gtx c2;
        if (gxf.d() == null) {
            if (BaseApplication.getContext() != null) {
                c2 = gtx.c(BaseApplication.getContext());
            } else {
                ReleaseLogUtil.d("Track_PluginSportTrack", "stopBackgroundTrack HealthApplication.getContext() is null");
                return;
            }
        } else {
            c2 = gtx.c(gxf.d());
        }
        if (c2 != null) {
            ReleaseLogUtil.e("Track_PluginSportTrack", "isStartTrackSport = ", Boolean.valueOf(c2.bc()), ", acquireTrackType =", Integer.valueOf(c2.r()));
            if (c2.bc()) {
                if (c2.r() == 1 || !z) {
                    LogUtil.a("Track_PluginSportTrack", "stopBackgroundTrack close track");
                    c2.bs();
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.h("Track_PluginSportTrack", "stopBackgroundTrack sportManager is null");
    }

    public void c(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null || motionPath == null) {
            ReleaseLogUtil.c("Track_PluginSportTrack", "showTrackMap simplifyData or motionData is null!");
            return;
        }
        LogUtil.c("Track_PluginSportTrack", "show track map = ", motionPathSimplify);
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplifyDatakey", motionPathSimplify);
        bundle.putSerializable("motionPath", motionPath);
        bundle.putString("contentpath", null);
        Intent intent = new Intent(gxf.d(), (Class<?>) TrackDetailActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(268435456);
        gnm.aPB_(gxf.d(), intent);
    }

    public void b(String str, MotionPathSimplify motionPathSimplify, boolean z) {
        aTq_(aTo_(str, motionPathSimplify, Collections.EMPTY_LIST, z, Boolean.FALSE.booleanValue()), motionPathSimplify);
    }

    public void c(String str, MotionPathSimplify motionPathSimplify, boolean z, boolean z2) {
        aTq_(aTo_(str, motionPathSimplify, Collections.EMPTY_LIST, z, z2), motionPathSimplify);
    }

    public void d(String str, MotionPathSimplify motionPathSimplify, List<gxy> list) {
        aTq_(aTo_(str, motionPathSimplify, list, Boolean.FALSE.booleanValue(), Boolean.FALSE.booleanValue()), motionPathSimplify);
    }

    public void e(String str, MotionPathSimplify motionPathSimplify) {
        b(str, motionPathSimplify, false);
    }

    public void aTq_(Bundle bundle, MotionPathSimplify motionPathSimplify) {
        if (bundle == null) {
            LogUtil.h("Track_PluginSportTrack", "showTrackMap get bundle null");
        }
        int requestSportType = motionPathSimplify.requestSportType();
        switch (requestSportType) {
            case 286:
                e(motionPathSimplify.requestStartTime(), motionPathSimplify.requestEndTime());
                break;
            case 287:
            case 291:
                aTl_(bundle, requestSportType);
                break;
            case 288:
                bundle.putString(BleConstants.KEY_PATH, "#/breathholdingtraining");
                aTl_(bundle, requestSportType);
                break;
            case ComponentInfo.PluginPay_A_N /* 289 */:
                bundle.putString(BleConstants.KEY_PATH, "#/closedbreathtest");
                aTl_(bundle, requestSportType);
                break;
            case 290:
            default:
                if (requestSportType != 264 && motionPathSimplify.hasTrackPoint() && gwg.g()) {
                    ReleaseLogUtil.d("Track_PluginSportTrack", "Show Gms install dialog");
                    ah();
                    break;
                } else {
                    aTt_(bundle);
                    break;
                }
        }
    }

    public Bundle aTo_(String str, MotionPathSimplify motionPathSimplify, List<gxy> list, boolean z, boolean z2) {
        if (str == null) {
            ReleaseLogUtil.c("Track_PluginSportTrack", "showTrackMap contentpath is null!");
            return null;
        }
        if (str.length() == 0) {
            ReleaseLogUtil.c("Track_PluginSportTrack", "showTrackMap contentpath is invalid!");
            return null;
        }
        if (motionPathSimplify == null) {
            ReleaseLogUtil.c("Track_PluginSportTrack", "showTrackMap simplifyData is null!");
            return null;
        }
        if (motionPathSimplify.requestSportType() == 512 && koq.b(list)) {
            ReleaseLogUtil.c("Track_PluginSportTrack", "showTrackMap subTrackDetail is null or empty for triathlon!");
            return null;
        }
        LogUtil.c("Track_PluginSportTrack", "show track content = ", str, "simplifyData = ", motionPathSimplify, "subTrackDetail = ", list);
        if (hab.g()) {
            gzw.b().a(motionPathSimplify);
            hab.b();
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplifyDatakey", motionPathSimplify);
        bundle.putString("contentpath", str);
        if (motionPathSimplify.requestSportType() == 512) {
            bundle.putSerializable("subTrackDetail", (Serializable) list);
        }
        bundle.putBoolean("isAfterSport", z);
        bundle.putBoolean("isNotNeedDeleteFile", z2);
        return bundle;
    }

    public void aTt_(Bundle bundle) {
        if (gxf.d() != null) {
            Intent intent = new Intent(gxf.d(), (Class<?>) TrackDetailActivity.class);
            intent.putExtras(bundle);
            intent.addFlags(268435456);
            gnm.aPB_(gxf.d(), intent);
        }
    }

    public void ad() {
        Context context = this.h;
        if (context != null) {
            b("motion_path2.txt", gwk.d(context), true);
        }
    }

    public guz d(Context context) {
        guz guzVar = this.f12911a;
        if (guzVar != null) {
            guzVar.d(context);
        }
        return this.f12911a;
    }

    private void aTl_(Bundle bundle, int i) {
        Gson gson = new Gson();
        String string = bundle.getString("contentpath");
        MotionPathSimplify motionPathSimplify = (MotionPathSimplify) bundle.getSerializable("simplifyDatakey");
        int requestDeviceType = motionPathSimplify.requestDeviceType();
        Context context = this.h;
        String a2 = cwa.a(requestDeviceType, context, context.getPackageName(), motionPathSimplify.requestProductId());
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addCustomizeArg("simplycontent", gson.toJson(bundle.getSerializable("simplifyDatakey")));
        builder.addCustomizeArg("contentPath", string);
        builder.addCustomizeArg("deviceName", a2);
        builder.addCustomizeArg("isShowImperialUnit", String.valueOf(UnitUtil.h()));
        builder.addCustomizeArg("isFahrenheit", String.valueOf(!UnitUtil.d()));
        LogUtil.a("Track_PluginSportTrack", "deviceName:", a2);
        builder.showStatusBar();
        builder.setImmerse();
        builder.addCustomizeJsModule("DivingJsApi", gsz.class);
        if (!TextUtils.isEmpty(bundle.getString(BleConstants.KEY_PATH))) {
            builder.addPath(bundle.getString(BleConstants.KEY_PATH));
        }
        bzs.e().loadH5ProApp(this.h, "com.huawei.health.h5.dive", builder);
    }

    public void e(long j, long j2) {
        String str = "?startTime=" + j + "&endTime=" + j2;
        LogUtil.a("Track_PluginSportTrack", "jumpToGolfCourseTrackDetail(): startAndEndTime is ", str);
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        builder.setImmerse();
        builder.showStatusBar();
        builder.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, bzs.e().getCommonJsModule(NotificationCompat.CATEGORY_SOCIAL));
        builder.setStatusBarTextBlack(true);
        builder.addPath("#/exedetails" + str);
        builder.setForceDarkMode(1);
        bzs.e().loadH5ProApp(this.h, "com.huawei.health.h5.golf", builder);
    }

    public boolean e(Context context) {
        int parseInt;
        if (context == null) {
            LogUtil.h("Track_PluginSportTrack", "context is null");
            return false;
        }
        boolean c2 = gww.c();
        LogUtil.a("Track_PluginSportTrack", "isCrash == ", Boolean.valueOf(c2));
        if (!c2) {
            return false;
        }
        File file = new File(context.getFilesDir(), "motion_path2.txt");
        boolean c3 = jdi.c(this.h, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        String b2 = SharedPreferenceManager.b(this.h, Integer.toString(20002), "sporting_sport_type_for_restart");
        if (TextUtils.isEmpty(b2)) {
            parseInt = 258;
        } else {
            try {
                parseInt = Integer.parseInt(b2);
            } catch (NumberFormatException e2) {
                LogUtil.b("Track_PluginSportTrack", "NumberFormatException exception = ", e2.getMessage());
                return false;
            }
        }
        if (file.exists() && System.currentTimeMillis() - file.lastModified() >= 1800000) {
            u();
            return false;
        }
        if (parseInt == 264) {
            return true;
        }
        return c3;
    }

    public void a(Context context) {
        gtx c2 = gtx.c(context);
        if (c2 == null || c2.r() != 1) {
            return;
        }
        c2.bp();
    }

    public int d(ISportDataCallback iSportDataCallback, long j) {
        LogUtil.a("Track_PluginSportTrack", "registerSportDataCallback, callback = ", iSportDataCallback, " interval = ", Long.valueOf(j));
        this.p = iSportDataCallback;
        this.s = j;
        return 0;
    }

    public ISportDataCallback f() {
        return this.p;
    }

    public boolean n() {
        if (((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).isDeviceConnected("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c")) {
            return true;
        }
        return hab.f() && hab.m();
    }

    public boolean q() {
        return gxf.d() != null && gtx.c(gxf.d()).r() == 1;
    }

    private void ae() {
        this.i.add(Long.valueOf(System.currentTimeMillis()));
    }

    private void j(int i) {
        gtx c2 = gtx.c(gxf.d());
        ReleaseLogUtil.e("Track_PluginSportTrack", "setTrackType", Integer.valueOf(c2.r()));
        c2.c(i);
    }

    public int d(ISportDataCallback iSportDataCallback) {
        gtx c2 = gtx.c(gxf.d());
        if (ai()) {
            return 9;
        }
        if (c2.m() == 0 || c2.m() == 3) {
            ReleaseLogUtil.d("Track_PluginSportTrack", "Stop Track by outer operation and sport is not running, failed");
            return 1;
        }
        if (c2.m() == 1) {
            ReleaseLogUtil.e("Track_PluginSportTrack", "Stop Track by outer operation and SPORTS_STATUS_SPORTING");
            c2.c(true, this.q);
            this.f.sendEmptyMessageDelayed(101, 50L);
            return 0;
        }
        if (c2.m() != 2) {
            return 0;
        }
        ReleaseLogUtil.e("Track_PluginSportTrack", "Stop Track by outer operation and SPORTS_STATUS_PAUSE");
        TrackMainMapActivity.b(gxf.d());
        c2.bv();
        return 0;
    }

    public void d(boolean z, boolean z2) {
        LogUtil.a("Track_PluginSportTrack", "notifyDeviceStatus isBinded = ", Boolean.valueOf(z), ". isConnected = ", Boolean.valueOf(z2));
        this.j = z;
        this.k = z2;
    }

    public boolean j() {
        return this.j;
    }

    public int s() {
        gtx c2 = gtx.c(gxf.d());
        if (!c2.bc()) {
            LogUtil.h("Track_PluginSportTrack", "launchTrackSportStateBroadcast: sport is not started, failed");
            return 1;
        }
        float h = c2.h();
        long sportDuration = c2.getSportDuration();
        int b2 = c2.b();
        if (!c2.al() && e().b() != 2) {
            return 0;
        }
        LogUtil.a("Track_PluginSportTrack", "launchTrackSportStateBroadcast distance = ", Float.valueOf(h), " duration = ", Long.valueOf(sportDuration), " heartRate = ", "***");
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", "sportStateWithoutCalorie");
        bundle.putInt(BleConstants.SPORT_TYPE, c2.n());
        bundle.putFloat("distance", h);
        bundle.putLong("duration", sportDuration);
        bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, b2);
        gxd.a().d(new gxi(11, bundle));
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int c(String str) {
        char c2;
        LogUtil.a("Track_PluginSportTrack", "launchTrackSportStateBroadcast voiceType enter");
        gtx c3 = gtx.c(gxf.d());
        if (!c3.bc()) {
            LogUtil.h("Track_PluginSportTrack", "launchTrackSportStateBroadcast: sport is not started, failed");
            return 1;
        }
        int n = c3.n();
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", str);
        bundle.putInt(BleConstants.SPORT_TYPE, n);
        str.hashCode();
        switch (str.hashCode()) {
            case -1992012396:
                if (str.equals("duration")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -19843715:
                if (str.equals("sportState")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 3432979:
                if (str.equals("pace")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 109641799:
                if (str.equals("speed")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 200416838:
                if (str.equals(IndoorEquipManagerApi.KEY_HEART_RATE)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 288459765:
                if (str.equals("distance")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            aTh_(str, c3, bundle);
        } else if (c2 == 1) {
            aTi_(c3, bundle);
        } else if (c2 != 2) {
            if (c2 == 3) {
                float y = c3.y();
                bundle.putFloat(str, y);
                bundle.putBoolean("paceTip", c(n, y));
            } else if (c2 == 4) {
                bundle.putInt(str, c3.b());
            } else if (c2 == 5) {
                aTg_(str, c3, bundle);
            } else {
                LogUtil.h("Track_PluginSportTrack", "launchTrackSportStateBroadcast(String),voiceType is wrong");
                return 1;
            }
        } else if (aTe_(str, c3, n, bundle)) {
            return 1;
        }
        gxi gxiVar = new gxi(11, bundle);
        if (c3.al() || e().b() == 2) {
            gxd.a().d(gxiVar);
        }
        b(0);
        return 0;
    }

    public void a(int i, int i2) {
        String str;
        LogUtil.a("Track_PluginSportTrack", "launchIndoorEquipSportStateBroadcast");
        if (i == 17) {
            str = "stepRateTips";
        } else if (i == 24) {
            str = "commonNormal";
        } else if (i != 29) {
            switch (i) {
                case 8:
                    str = "connectSuccess";
                    break;
                case 9:
                    str = "startRun";
                    break;
                case 10:
                    str = "commonAbnormal";
                    break;
                case 11:
                    str = "impactTips";
                    break;
                default:
                    LogUtil.h("Track_PluginSportTrack", "launchIndoorEquipSportStateBroadcast(type, event),type is wrong");
                    str = "invalid";
                    break;
            }
        } else {
            str = "startRun_others";
        }
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", str);
        bundle.putInt("treadmillEvent", i2);
        gxd.a().d(new gxi(11, bundle));
    }

    public void e(int i, int i2, int i3, int i4) {
        LogUtil.a("Track_PluginSportTrack", "launchIndoorEquipSportStateBroadcast, warm up");
        Bundle bundle = new Bundle();
        bundle.putString("voiceType", "warmUpTips");
        bundle.putInt("warmUpHeartRateLow", i3);
        bundle.putInt("warmUpHeartRateHeight", i4);
        bundle.putInt("treadmillEvent", i2);
        gxd.a().d(new gxi(11, bundle));
    }

    private void aTg_(String str, gtx gtxVar, Bundle bundle) {
        bundle.putFloat(str, gtxVar.h());
        int k = gtxVar.k();
        if (k == 1) {
            bundle.putInt("sport_target_type", k);
            bundle.putFloat("lastValue", gtxVar.a());
            bundle.putBoolean("targetTip", true);
            bundle.putInt("targetProgress", gtxVar.g());
        }
    }

    private void aTh_(String str, gtx gtxVar, Bundle bundle) {
        bundle.putLong(str, gtxVar.getSportDuration());
        int k = gtxVar.k();
        if (k == 0) {
            bundle.putInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, k);
            bundle.putFloat("lastValue", gtxVar.a());
            bundle.putBoolean("targetTip", true);
            bundle.putInt("targetProgress", gtxVar.g());
        }
    }

    private boolean aTe_(String str, gtx gtxVar, int i, Bundle bundle) {
        gvp ai = gtxVar.ai();
        if (ai != null) {
            long b2 = ai.b((int) gtxVar.j());
            bundle.putLong(str, b2);
            bundle.putBoolean("paceTip", guq.d(i, b2));
            return false;
        }
        ReleaseLogUtil.e("Track_PluginSportTrack", "launchTrackSportStateBroadcast BUNDLE_KEY_PACE, mPaceManager == null");
        return true;
    }

    private void aTi_(gtx gtxVar, Bundle bundle) {
        float h = gtxVar.h();
        long sportDuration = gtxVar.getSportDuration();
        float y = gtxVar.y();
        int x = (int) gtxVar.x();
        int b2 = gtxVar.b();
        bundle.putFloat("distance", h);
        bundle.putLong("duration", sportDuration);
        bundle.putFloat("speed", y);
        bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, b2);
        bundle.putInt("calorie", x);
    }

    public boolean o() {
        return cei.b().isRopeDeviceBtConnected();
    }

    public void c(int i, int i2, float f) {
        if (ai()) {
            LogUtil.b("Track_PluginSportTrack", "check if multi start");
            return;
        }
        int i3 = i2 == -1 ? 6 : i2;
        int round = Math.round(f) < 0 ? 0 : Math.round(f);
        cei.b().setSkippingTargetMode(new SkippingTargetMode(i3, round, null));
        Intent intent = new Intent();
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        intent.setFlags(268435456);
        sportLaunchParams.setSportType(i);
        sportLaunchParams.setSportTarget(i3);
        sportLaunchParams.setTargetValue(round);
        sportLaunchParams.addExtra("productId", cei.b().getProductId());
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.indoorequip.activity.IndoorEquipDisplayActivity");
        fgb.awY_(this.h, intent);
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (sportDataOutputApi != null) {
            sportDataOutputApi.registerDeviceDataCallback(this.p);
        }
        ReleaseLogUtil.e("Track_PluginSportTrack", " startIndoorEquipDisplay sourceSportType = ", Integer.valueOf(i), " sourceTargetType = ", Integer.valueOf(i2), " sourceTargetValue = ", Float.valueOf(f), " sportType = ", Integer.valueOf(i), " targetType = ", Integer.valueOf(i3), " targetValue = ", Integer.valueOf(round));
        d(i3, round);
    }

    public void d(int i, int i2) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("currentSkipperTargetType", Integer.valueOf(i));
        hashMap.put("currentSkipperTarget", Integer.valueOf(i2));
        hashMap.put("prodId", ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).getProdId(cei.b().getProductId()));
        hashMap.put("macAddress", knl.b(cei.b().getCurrentMacAddress()));
        ixx.d().d(this.h, AnalyticsValue.HEALTH_SKIPPING_2170016.value(), hashMap, 0);
    }

    public void c(Context context) {
        Context context2 = this.h;
        String str = d;
        String b2 = SharedPreferenceManager.b(context2, str, "sporting_sport_type_for_restart");
        String b3 = SharedPreferenceManager.b(this.h, str, "sporting_target_type_for_restart");
        String b4 = SharedPreferenceManager.b(this.h, str, "sporting_target_value_for_restart");
        ReleaseLogUtil.e("Track_PluginSportTrack", "gotoSport typeStr = ", b2, " targetTypeStr = ", b3, " targetValueStr = ", b4);
        a(context, CommonUtils.e(b2, 258), CommonUtils.e(b3, -1), CommonUtils.c(b4, -1.0f));
    }

    public void a(Context context, int i, int i2, float f) {
        if (!SportSupportUtil.i(i)) {
            i = 258;
        }
        int i3 = i;
        a(i3, i2, f);
        ReleaseLogUtil.e("Track_PluginSportTrack", " gotoSport sportType = ", Integer.valueOf(i3), " sportTarget = ", Integer.valueOf(i2), " targetValue = ", Float.valueOf(f));
        c(0, i3, i2, f, null, context);
    }

    public void a(int i, int i2, float f) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
        hashMap.put("goalType", Integer.valueOf(i2));
        if (!Utils.o()) {
            hashMap.put("goalValue", Float.valueOf(f));
        }
        ixx.d().d(this.h, AnalyticsValue.BI_TRACK_SPORT_START_ACTION_KEY.value(), hashMap, 0);
    }

    public boolean l() {
        return this.k;
    }

    public void b(Context context) {
        gsx.a().d(context);
    }

    public int i() {
        return gtx.c(gxf.d()).m();
    }

    public boolean k() {
        return gtx.c(gxf.d()).bc();
    }

    public void b(int i) {
        this.q = i;
    }

    public void d() {
        if (gsx.a().e()) {
            gsx.a().d(false);
            this.o = false;
            gsx.b(this.h);
        }
    }

    public int b() {
        return this.q;
    }

    public boolean p() {
        if (getAdapter() == null) {
            return false;
        }
        int i = i();
        LogUtil.a("Track_PluginSportTrack", "sportState", Integer.valueOf(i));
        return i == 1 || i == 2 || i == 6;
    }

    public static PermissionUtil.PermissionType d(int i) {
        if (i == 264) {
            if (gvv.d(BaseApplication.getContext())) {
                return PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES;
            }
            return PermissionUtil.PermissionType.NONE;
        }
        if (i == 283 || SportSupportUtil.e(i) || i == 700001) {
            if (Build.VERSION.SDK_INT < 33) {
                return PermissionUtil.PermissionType.CAMERA_IMAGE;
            }
            return PermissionUtil.PermissionType.CAMERA;
        }
        return caq.d();
    }

    public boolean h() {
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (sportDataOutputApi == null || sportDataOutputApi.getStatus() == 0 || sportDataOutputApi.getStatus() == 3) {
            return false;
        }
        LogUtil.a("Track_PluginSportTrack", "getIsSporting");
        return true;
    }

    public void ab() {
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) TrackMainMapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("map_tracking_sport_type_sportting", gtx.c(this.h).n());
        intent.putExtra("sportdataparams", bundle);
        intent.addFlags(268435456);
        gnm.aPB_(this.h, intent);
    }

    public int g() {
        return this.l;
    }

    public void c(int i) {
        this.l = i;
    }

    private void ah() {
        HandlerExecutor.e(new Runnable() { // from class: gsq
            @Override // java.lang.Runnable
            public final void run() {
                gso.this.r();
            }
        });
    }

    /* synthetic */ void r() {
        if (CommonUtil.bx()) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(BaseApplication.getActivity());
            builder.e(this.h.getString(R.string._2130837688_res_0x7f0200b8)).czC_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: gss
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            builder.e().show();
        }
    }
}
