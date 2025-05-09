package com.huawei.ui.homehealth.interactors;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.healthcloud.plugintrack.golf.Utils;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseData;
import com.huawei.healthcloud.plugintrack.golf.cloud.CloudManager;
import com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadTaskUtils;
import com.huawei.healthcloud.plugintrack.golf.cloud.beans.CourseDetail;
import com.huawei.healthcloud.plugintrack.golf.cloud.beans.CourseMapData;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GetGolfCourseDetailReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GetGolfCourseMapDataReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GetGolfCourseDetailRsp;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GetGolfCourseMapDataRsp;
import com.huawei.healthcloud.plugintrack.golf.device.CloudHelper;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDataCallback;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceEngineManager;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.Social;
import com.huawei.ui.commonui.dialog.CustomImageDialog;
import com.huawei.ui.homehealth.interactors.GolfUpdateMapInteractor;
import defpackage.bzs;
import defpackage.cun;
import defpackage.cwi;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nrv;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class GolfUpdateMapInteractor {

    /* renamed from: a, reason: collision with root package name */
    private Handler f9466a;
    private GolfUpdateMapReceiver d;

    public GolfUpdateMapInteractor() {
        c();
    }

    public static class GolfUpdateMapReceiver extends BroadcastReceiver {
        private final WeakReference<GolfUpdateMapInteractor> e;

        public GolfUpdateMapReceiver(GolfUpdateMapInteractor golfUpdateMapInteractor) {
            this.e = new WeakReference<>(golfUpdateMapInteractor);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            GolfUpdateMapInteractor golfUpdateMapInteractor = this.e.get();
            if (golfUpdateMapInteractor != null) {
                if (intent == null) {
                    LogUtil.h("Track_GolfUpdateMapUtils", "onReceive intent is null");
                    return;
                }
                LogUtil.a("Track_GolfUpdateMapUtils", "GolfUpdateMapReceiver onReceive to enter, action = ", intent.getAction());
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    golfUpdateMapInteractor.c();
                }
            }
        }
    }

    public void dcG_(Handler handler) {
        try {
            if (this.d == null) {
                IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
                this.d = new GolfUpdateMapReceiver(this);
                this.f9466a = handler;
                BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.d, intentFilter, LocalBroadcast.c, null);
            }
        } catch (Exception unused) {
            LogUtil.b("Track_GolfUpdateMapUtils", "registerDeviceStatusBroadcast Exception");
        }
    }

    public void a() {
        try {
            LogUtil.a("Track_GolfUpdateMapUtils", "Enter unregisterNonLocalBroadcast!");
            if (this.d != null) {
                BaseApplication.getContext().unregisterReceiver(this.d);
            }
        } catch (IllegalArgumentException e) {
            LogUtil.b("Track_GolfUpdateMapUtils", LogAnonymous.b((Throwable) e));
        }
    }

    public void c() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            LogUtil.a("Track_GolfUpdateMapUtils", "checkGolfUpdateMap mainThread");
            ThreadPoolManager.d().execute(new Runnable() { // from class: ona
                @Override // java.lang.Runnable
                public final void run() {
                    GolfUpdateMapInteractor.this.c();
                }
            });
        } else {
            if (!e()) {
                LogUtil.a("Track_GolfUpdateMapUtils", "checkGolfUpdateMap isIntervalTime not Finish");
                return;
            }
            final DeviceInfo d = d();
            if (d == null) {
                return;
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "golf_update_map_last_query_time", Long.toString(System.currentTimeMillis()), (StorageParams) null);
            GolfDeviceProxy.getInstance().getDeviceDownloadedCoursesForNative(new GolfDataCallback() { // from class: onb
                @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataCallback
                public final void onReceived(List list) {
                    GolfUpdateMapInteractor.this.e(d, list);
                }
            });
        }
    }

    public /* synthetic */ void e(DeviceInfo deviceInfo, List list) {
        LogUtil.a("Track_GolfUpdateMapUtils", "getDeviceDownloadedCoursesForNative list.size() = ", Integer.valueOf(list.size()));
        List<Long> b = b(list, a(list));
        if (koq.b(b)) {
            ReleaseLogUtil.c("Track_GolfUpdateMapUtils", "checkGolfUpdateMap courseIds isEmpty");
        } else {
            a(deviceInfo, b);
        }
    }

    private void a(DeviceInfo deviceInfo, List<Long> list) {
        String e = SharedPreferenceManager.e(String.valueOf(10000), "sp_golf_not_update_map_ids", "");
        LogUtil.a("Track_GolfUpdateMapUtils", "sendUpdateMapMessage spIds = ", e);
        if (!TextUtils.isEmpty(e)) {
            List list2 = (List) nrv.c(e, new TypeToken<List<Long>>() { // from class: com.huawei.ui.homehealth.interactors.GolfUpdateMapInteractor.5
            }.getType());
            LogUtil.a("Track_GolfUpdateMapUtils", "sendUpdateMapMessage longList = ", Integer.valueOf(list2.size()));
            if (koq.c(list2) && list2.containsAll(list)) {
                ReleaseLogUtil.c("Track_GolfUpdateMapUtils", "sendUpdateMapMessage spIds json = ", e);
                return;
            }
        }
        int d = d(deviceInfo, list);
        Bundle bundle = new Bundle();
        bundle.putString("bundle_golf_update_map_device_name", deviceInfo.getDeviceName());
        Message message = new Message();
        message.setData(bundle);
        message.arg1 = d;
        message.obj = list;
        message.what = 20241105;
        Handler handler = this.f9466a;
        if (handler != null) {
            handler.sendMessageDelayed(message, PreConnectManager.CONNECT_INTERNAL);
        }
    }

    private int d(DeviceInfo deviceInfo, List<Long> list) {
        if (CommonUtil.l(BaseApplication.getContext()) == 1) {
            return -1;
        }
        boolean isVectorMap = GolfDeviceEngineManager.getInstance().isVectorMap(deviceInfo);
        GetGolfCourseMapDataReq.Builder builder = new GetGolfCourseMapDataReq.Builder();
        builder.addCourseIds(list).language(Utils.getLanguage()).type(GolfDownloadTaskUtils.DeviceType.getType(isVectorMap ? 1 : 0)).mapLevel(Utils.getGolfMapDeviceLevel("Track_GolfUpdateMapUtils"));
        GetGolfCourseMapDataRsp golfCourseMapData = CloudManager.getInstance().getGolfCourseMapData(builder.build());
        if (golfCourseMapData == null) {
            LogUtil.b("Track_GolfUpdateMapUtils", "sendUpdateMapMessage response == null");
            return -1;
        }
        List<CourseMapData> courseMapDataList = golfCourseMapData.getCourseMapDataList();
        if (koq.b(courseMapDataList)) {
            LogUtil.b("Track_GolfUpdateMapUtils", "golf map is not update");
            return -1;
        }
        Iterator<CourseMapData> it = courseMapDataList.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            d += it.next().getSize();
        }
        return (int) Math.round((d / 1024.0d) / 1024.0d);
    }

    public static void dcE_(Activity activity, Message message) {
        String format;
        if (activity == null || activity.isFinishing()) {
            LogUtil.a("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog activity == null or isFinishing");
            return;
        }
        if (!koq.e(message.obj, Long.class)) {
            LogUtil.a("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog msg.obj is not list");
            return;
        }
        final List list = (List) message.obj;
        if (koq.b(list)) {
            LogUtil.a("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog courseIds is empty");
            return;
        }
        final Context context = BaseApplication.getContext();
        SharedPreferenceManager.c(String.valueOf(10000), "sp_golf_not_update_map_ids", "");
        int size = list.size();
        String a2 = nsf.a(R.plurals._2130903424_res_0x7f030180, size, Integer.valueOf(size));
        String string = context.getString(R.string.IDS_device_upgrade_file_size_mb, String.valueOf(message.arg1));
        String string2 = message.getData().getString("bundle_golf_update_map_device_name");
        if (message.arg1 == -1) {
            format = String.format(Locale.ENGLISH, context.getString(R.string._2130847449_res_0x7f0226d9), string2, a2);
        } else {
            format = String.format(Locale.ENGLISH, context.getString(R.string._2130847448_res_0x7f0226d8), string2, a2, string);
        }
        dcF_(activity, format, new View.OnClickListener() { // from class: omt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GolfUpdateMapInteractor.dcA_(list, context, view);
            }
        }, new View.OnClickListener() { // from class: omu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GolfUpdateMapInteractor.dcB_(view);
            }
        }, new View.OnClickListener() { // from class: omw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GolfUpdateMapInteractor.dcC_(list, view);
            }
        });
    }

    public static /* synthetic */ void dcA_(List list, Context context, View view) {
        if (nsn.a(500)) {
            LogUtil.a("Track_GolfUpdateMapUtils", "onClick isFastClick");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            b(list);
            nrh.e(context, R.string._2130847450_res_0x7f0226da);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static /* synthetic */ void dcB_(View view) {
        if (nsn.a(500)) {
            LogUtil.a("Track_GolfUpdateMapUtils", "onClick isFastClick");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            ReleaseLogUtil.e("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog and see maps");
            e(BaseApplication.getContext(), "0");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static /* synthetic */ void dcC_(List list, View view) {
        ReleaseLogUtil.e("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog not update again");
        SharedPreferenceManager.c(String.valueOf(10000), "sp_golf_not_update_map_ids", nrv.a(list));
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void dcF_(Context context, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnClickListener onClickListener3) {
        LogUtil.a("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog enter", " content = ", str);
        if (context == null) {
            context = BaseApplication.getActivity();
        }
        if (context == null) {
            ReleaseLogUtil.d("Track_GolfUpdateMapUtils", "topActivity and context are null");
            return;
        }
        CustomImageDialog.Builder builder = new CustomImageDialog.Builder(context);
        builder.d(R.mipmap._2131820681_res_0x7f110089).d(context.getString(R.string._2130847447_res_0x7f0226d7)).a(str).b(context.getString(R.string._2130847434_res_0x7f0226ca)).e(context.getString(R.string._2130843312_res_0x7f0216b0)).c(context.getString(R.string._2130845852_res_0x7f02209c)).cyC_(onClickListener).cyD_(onClickListener2).cyE_(onClickListener3).cyF_(new View.OnClickListener() { // from class: omx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GolfUpdateMapInteractor.dcD_(view);
            }
        });
        CustomImageDialog d = builder.d();
        d.setCancelable(false);
        d.show();
    }

    public static /* synthetic */ void dcD_(View view) {
        LogUtil.a("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void b(final List<Long> list) {
        ReleaseLogUtil.e("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog and update all courseIds = ", list);
        GolfDeviceProxy.getInstance().isDevicesPinged(new CommonSingleCallback() { // from class: omy
            @Override // com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback
            public final void callback(Object obj) {
                GolfUpdateMapInteractor.e(list, (Boolean) obj);
            }
        });
    }

    public static /* synthetic */ void e(List list, Boolean bool) {
        if (!bool.booleanValue()) {
            ReleaseLogUtil.d("Track_GolfUpdateMapUtils", "App ping devices failed in downloadMap");
            nrh.e(BaseApplication.getActivity(), R.string._2130843052_res_0x7f0215ac);
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Long l = (Long) it.next();
            if (CloudHelper.getVersion(l.longValue(), false) <= 0) {
                LogUtil.h("Track_GolfUpdateMapUtils", "invalid course version");
            } else {
                GolfDownloadTaskUtils.getInstance().bgUpdateDownloadHandler(l.longValue(), GolfDeviceProxy.getInstance().getWatchType(), -1);
            }
        }
    }

    private static DeviceInfo d() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_GolfUpdateMapUtils");
        if (deviceInfo == null) {
            LogUtil.a("Track_GolfUpdateMapUtils", "checkGolfUpdateMap deviceInfo == null");
            return null;
        }
        if (deviceInfo.getDeviceConnectState() == 2 && cwi.c(deviceInfo, 105)) {
            return deviceInfo;
        }
        LogUtil.a("Track_GolfUpdateMapUtils", "showGolfUpdateMapDialog(): device is not  not connect or not support golf.");
        return null;
    }

    private static boolean e() {
        long n = CommonUtil.n(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "golf_update_map_last_query_time"));
        if (n == 0 || System.currentTimeMillis() - n > 86400000) {
            return true;
        }
        LogUtil.a("Track_GolfUpdateMapUtils", "lastQueryTime:", Long.valueOf(n));
        return false;
    }

    public static List<Long> b(List<GolfCourseData> list, HashMap<Long, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (GolfCourseData golfCourseData : list) {
            int courseId = golfCourseData.getCourseId();
            int version = golfCourseData.getVersion();
            LogUtil.a("Track_GolfUpdateMapUtils", "current course, courseId = ", Integer.valueOf(courseId), ", version = ", Integer.valueOf(version));
            String valueOf = String.valueOf(version);
            String str = hashMap.get(Long.valueOf(golfCourseData.getCourseId()));
            LogUtil.a("Track_GolfUpdateMapUtils", "current version info, curMapVersionStr = ", valueOf, ", cloudMapVersionStr = ", str);
            if (TextUtils.isEmpty(valueOf)) {
                LogUtil.b("Track_GolfUpdateMapUtils", "curMapVersionStr is empty");
            } else if (TextUtils.isEmpty(str)) {
                LogUtil.b("Track_GolfUpdateMapUtils", "cloudMapVersionStr is empty");
            } else if (!valueOf.equals(str)) {
                arrayList.add(Long.valueOf(courseId));
            }
        }
        return arrayList;
    }

    public static HashMap<Long, String> a(List<GolfCourseData> list) {
        HashMap<Long, String> hashMap = new HashMap<>();
        if (koq.b(list)) {
            LogUtil.a("Track_GolfUpdateMapUtils", "updateVersionMap(), courses isEmpty");
            return hashMap;
        }
        GetGolfCourseDetailReq.Builder builder = new GetGolfCourseDetailReq.Builder();
        builder.language(Utils.getLanguage());
        Iterator<GolfCourseData> it = list.iterator();
        while (it.hasNext()) {
            builder.addCourseId(Long.valueOf(it.next().getCourseId()));
        }
        GetGolfCourseDetailRsp golfCourseDetail = CloudManager.getInstance().getGolfCourseDetail(builder.build());
        LogUtil.a("Track_GolfUpdateMapUtils", "updateVersionMap(), cloud response = ", golfCourseDetail);
        if (golfCourseDetail == null) {
            return hashMap;
        }
        for (CourseDetail courseDetail : golfCourseDetail.getCourseDetails()) {
            hashMap.put(courseDetail.getCourseId(), courseDetail.getVersion());
        }
        return hashMap;
    }

    public static void e(Context context, String str) {
        if (context == null) {
            LogUtil.h("Track_GolfUpdateMapUtils", "jumpToGolfTrainingCourse(): context is null");
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        builder.setImmerse();
        builder.showStatusBar();
        builder.addPath("#/map?updateClicked=" + str);
        builder.addCustomizeJsModule(NotificationCompat.CATEGORY_SOCIAL, Social.class);
        builder.setStatusBarTextBlack(true);
        builder.setForceDarkMode(1);
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.golf", builder);
    }
}
