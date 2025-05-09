package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.emcom.IOneHopAppCallback;
import android.emcom.IOneHopAuthReqCallback;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.service.HandoffService;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.HarmonyBuild;
import health.compact.a.LocalBroadcast;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jli {

    /* renamed from: a, reason: collision with root package name */
    private static jlu f13931a;
    private static jli e;
    private ExtendHandler f;
    private Context g;
    private static final Object c = new Object();
    private static final List<String> b = Collections.unmodifiableList(new ArrayList<String>() { // from class: jli.1
        {
            add("png");
            add("jpg");
            add("jpeg");
            add("bmp");
            add(MetaCreativeType.GIF);
            add("psd");
            add("svg");
            add("tif");
            add("tiff");
            add("webp");
            add("pcx");
        }
    });
    private boolean i = false;
    private int o = -1;
    private Handler j = new Handler(Looper.getMainLooper()) { // from class: jli.15
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.a("R_HwHandoffManager", "msg.what:", Integer.valueOf(message.what));
            switch (message.what) {
                case 1000:
                    if (message.obj instanceof List) {
                        jli.this.e((List<Integer>) message.obj);
                        break;
                    }
                    break;
                case 1001:
                    jli.this.h();
                    break;
                case 1002:
                    jli.this.f();
                    break;
                case 1003:
                    jli.this.i();
                    break;
                case 1004:
                    jli.this.s();
                    break;
                case 1005:
                    jli.this.q();
                    break;
                case 1006:
                    if (message.obj instanceof String) {
                        HandoffService.startWatchFaceTransferService(jli.this.g, (String) message.obj);
                        break;
                    }
                    break;
                case 1007:
                    jli.this.r();
                    break;
                case 1008:
                    jli.this.g();
                    break;
                case 1009:
                    jli.this.j();
                    break;
                case 1010:
                    jli.this.o();
                    break;
                default:
                    LogUtil.a("HwHandoffManager", "Handler default");
                    break;
            }
        }
    };
    private SmartClipManager.SmartLoadPluginCallback h = new SmartClipManager.SmartLoadPluginCallback() { // from class: jli.14
        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.SmartLoadPluginCallback
        public void onLoadPluginResult(int i) {
            if (i != 0) {
                if (!CommonUtil.aa(BaseApplication.getContext())) {
                    LogUtil.h("HwHandoffManager", "network not connected");
                } else if (CommonUtil.l(BaseApplication.getContext()) != 1) {
                    LogUtil.a("HwHandoffManager", "send Hiai Notification");
                    jli.this.e(0);
                } else {
                    LogUtil.a("HwHandoffManager", "network is wifi");
                }
            }
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.SmartLoadPluginCallback
        public void onLoadPluginProgress(int i) {
            LogUtil.a("HwHandoffManager", "load plugin progress =", Integer.valueOf(i));
        }
    };
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: jli.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            LogUtil.a("HwHandoffManager", "HwHandoffManager onReceive enter");
            if (context == null) {
                LogUtil.h("HwHandoffManager", "context is null");
                return;
            }
            if (intent == null) {
                LogUtil.h("HwHandoffManager", "intent is null");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                LogUtil.h("HwHandoffManager", "action is null.");
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                try {
                    deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                } catch (ClassCastException unused) {
                    LogUtil.b("HwHandoffManager", "ClassCastException exception");
                    deviceInfo = null;
                }
                if (deviceInfo == null) {
                    LogUtil.h("HwHandoffManager", "deviceInfo is null");
                    return;
                }
                if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                    LogUtil.a("HwHandoffManager", "This device does not have the correspond capability.");
                    return;
                }
                if (deviceInfo.getDeviceConnectState() == 2) {
                    LogUtil.a("HwHandoffManager", "mConnectStateChangedReceiver mHandoffTransferBean:", jli.f13931a);
                    jlu jluVar = jli.f13931a;
                    jli.this.n();
                    if (jluVar != null) {
                        jli.this.a(jluVar);
                        return;
                    }
                    return;
                }
                LogUtil.h("HwHandoffManager", "onConnectStateChange state is not connect");
            }
        }
    };

    class c implements Handler.Callback {
        private c() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1002) {
                LogUtil.a("HwHandoffManager", "DeviceConnectTimeoutHandler");
                if (jli.f13931a != null) {
                    jli.this.t();
                    jlu unused = jli.f13931a = null;
                }
                jli.this.f();
                return true;
            }
            LogUtil.h("HwHandoffManager", "DeviceConnectTimeoutHandler default");
            return false;
        }
    }

    private jli(Context context) {
        this.g = context;
    }

    public static jli d(Context context) {
        jli jliVar;
        synchronized (c) {
            if (e == null) {
                e = new jli(context);
            }
            a(context);
            jliVar = e;
        }
        return jliVar;
    }

    private static void a(Context context) {
        if (jlo.d(context).a() == 0) {
            jlo.d(context).c(0, new IBaseResponseCallback() { // from class: jli.12
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HwHandoffManager", "facePhotoInfo getMaxBackgroundImages:", obj, "errorCode:", Integer.valueOf(i));
                }
            });
        }
    }

    public void b() {
        if (!HwWatchFaceUtil.c()) {
            LogUtil.h("HwHandoffManager", "handoff is not support");
            return;
        }
        try {
            Class.forName("lsk");
            if (CommonUtil.bh() && CommonUtil.ch()) {
                int b2 = lsk.c().b("com.huawei.health", new IOneHopAuthReqCallback.Stub() { // from class: jli.13
                    @Override // android.emcom.IOneHopAuthReqCallback
                    public void onAuthResult(boolean z) {
                        LogUtil.a("HwHandoffManager", "onehopAuthReq, result:", Boolean.valueOf(z));
                        if (jli.this.o != 0 && z) {
                            jli.this.l();
                        } else {
                            LogUtil.a("HwHandoffManager", "onehopAuthReq, mOnehopAuthSuccessresult or result is false");
                        }
                    }
                });
                this.o = b2;
                LogUtil.a("HwHandoffManager", "onehopAuthReq, onehopAuthSuccess:", Integer.valueOf(b2));
                if (this.o == 0) {
                    l();
                    return;
                }
                return;
            }
            l();
        } catch (ClassNotFoundException unused) {
            LogUtil.b("HwHandoffManager", "Cannot find HwHandoffSdk");
        }
    }

    public boolean c() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        ReleaseLogUtil.e("R_HwHandoffManager", "registerHandoffService");
        int d = lsk.c().d("com.huawei.health", 5, new IOneHopAppCallback.Stub() { // from class: jli.20
            @Override // android.emcom.IOneHopAppCallback
            public void onOneHopReceived(final String str) {
                LogUtil.a("HwHandoffManager", "onOneHopReceived, para:", str);
                HwWatchFaceUtil.e(new HwWatchFaceUtil.TouchSupportCallback() { // from class: jli.20.1
                    @Override // com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil.TouchSupportCallback
                    public void touchSupportResult(int i) {
                        if (i == 1) {
                            if (!TextUtils.isEmpty(str)) {
                                jlu b2 = jli.this.b(str);
                                if (b2 == null) {
                                    LogUtil.h("HwHandoffManager", "handoffDataEvent watchFaceHandoffBean is null.");
                                    return;
                                } else if (b2.b() == 5) {
                                    jli.this.e(b2);
                                    return;
                                } else {
                                    LogUtil.h("HwHandoffManager", "The handoff data type is:", Integer.valueOf(b2.b()));
                                    return;
                                }
                            }
                            ReleaseLogUtil.d("R_HwHandoffManager", "handoffDataEvent The para is null or empty.");
                            return;
                        }
                        ReleaseLogUtil.d("R_HwHandoffManager", "is not support touch transfer");
                    }
                });
            }
        });
        LogUtil.a("HwHandoffManager", "registerHandoffService isRegisterSuccess =", Integer.valueOf(d));
        if (d == 0) {
            this.i = true;
            m();
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.d, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
            return;
        }
        this.i = false;
    }

    private void m() {
        String c2 = GRSManager.a(BaseApplication.getContext()).c();
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainContentcenterDbankcdnNew", c2);
        jqi.a().setSwitchSettingToDb("onehop_grs_countryCode", c2);
        jqi.a().setSwitchSettingToDb("onehop_grs_watchFaceGrsUrl", noCheckUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Integer> list) {
        final int intValue = list.get(0).intValue();
        final int intValue2 = list.get(1).intValue();
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.17
            @Override // java.lang.Runnable
            public void run() {
                String format;
                ReleaseLogUtil.e("R_HwHandoffManager", "max transfer number message. maxNum = ", Integer.valueOf(intValue));
                if (intValue == 0) {
                    format = BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_over_max_limit);
                } else if (intValue2 == 0) {
                    format = String.format(Locale.ENGLISH, BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_max_photos_title), Integer.valueOf(intValue));
                } else {
                    Locale locale = Locale.ENGLISH;
                    int i = intValue;
                    format = String.format(locale, nsf.a(R.plurals._2130903421_res_0x7f03017d, i, Integer.valueOf(i)), new Object[0]);
                }
                Toast.makeText(BaseApplication.getContext(), format, 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.16
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwHandoffManager", "bluetooth disconnect message");
                Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_wear_connect_device_disconnect), 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwHandoffManager", "cloud image format message");
                Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_cloud_image_error), 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwHandoffManager", "hiai uninstall message");
                SmartClipManager.e(BaseApplication.getContext()).a(jli.this.h);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.3
            @Override // java.lang.Runnable
            public void run() {
                ReleaseLogUtil.e("R_HwHandoffManager", "device not connected message");
                Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_device_disconnect_error), 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.2
            @Override // java.lang.Runnable
            public void run() {
                jli.this.e(1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.10
            @Override // java.lang.Runnable
            public void run() {
                ReleaseLogUtil.e("R_HwHandoffManager", "video format message");
                Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_video_format_error), 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.8
            @Override // java.lang.Runnable
            public void run() {
                ReleaseLogUtil.e("R_HwHandoffManager", "illegal picture message");
                Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_watchface_file_is_not_support), 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.9
            @Override // java.lang.Runnable
            public void run() {
                ReleaseLogUtil.e("R_HwHandoffManager", "transferring image message");
                Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_have_photo_transfering), 1).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        new Handler(BaseApplication.getContext().getMainLooper()).post(new Runnable() { // from class: jli.7
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("HwHandoffManager", "device disconnected message");
                Toast.makeText(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_watchface_wearable_need_connect_to_use), 1).show();
            }
        });
    }

    private void k() {
        ExtendHandler yt_ = HandlerCenter.yt_(new c(), "HwHandoffManager");
        this.f = yt_;
        yt_.sendEmptyMessage(1002, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ExtendHandler extendHandler = this.f;
        if (extendHandler != null) {
            extendHandler.quit(false);
            this.f = null;
        }
        LogUtil.a("HwHandoffManager", "Remove DeviceConnectTimeoutHandler");
        if (f13931a != null) {
            t();
            f13931a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.d);
        } catch (IllegalStateException unused) {
            LogUtil.b("HwHandoffManager", "unRegisterReceiver IllegalStateException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(jlu jluVar) {
        if (jpt.a("HwHandoffManager") == null) {
            LogUtil.h("HwHandoffManager", "deviceInfo is null");
            f13931a = jluVar;
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.d, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
            k();
            return;
        }
        a(jluVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(jlu jluVar) {
        if (this.g == null) {
            this.g = BaseApplication.getContext();
        }
        if (!a()) {
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = 1003;
            this.j.sendMessage(obtainMessage);
            return;
        }
        DeviceInfo a2 = jpt.a("HwHandoffManager");
        if (a2 == null) {
            LogUtil.h("HwHandoffManager", "judgeDeviceUnusualStateTransferInfoToWatchFace deviceInfo is null");
            return;
        }
        String replace = a2.getDeviceIdentify().replace(":", "");
        String upperCase = replace.length() > 3 ? replace.substring(replace.length() - 3, replace.length()).toUpperCase(Locale.ENGLISH) : "";
        if (a2.getDeviceConnectState() != 2 || a2.getDeviceName() == null || !upperCase.equals(jluVar.d())) {
            ReleaseLogUtil.d("R_HwHandoffManager", "device is not connected.");
            Message obtainMessage2 = this.j.obtainMessage();
            obtainMessage2.what = 1001;
            this.j.sendMessage(obtainMessage2);
            return;
        }
        if (!jdi.c(this.g, PermissionUtil.c(PermissionUtil.PermissionType.MANAGE_EXTERNAL_STORAGE))) {
            if (!jdi.c(this.g, PermissionUtil.c(PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES))) {
                ReleaseLogUtil.d("R_HwHandoffManager", "has no permissions.");
                Message obtainMessage3 = this.j.obtainMessage();
                obtainMessage3.what = 1005;
                this.j.sendMessage(obtainMessage3);
                return;
            }
        }
        c(jluVar);
    }

    private void c(jlu jluVar) {
        if (SmartClipManager.e()) {
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = 1004;
            this.j.sendMessage(obtainMessage);
            return;
        }
        if (d(jluVar.a())) {
            LogUtil.a("HwHandoffManager", "is have cloud image format: true");
            Message obtainMessage2 = this.j.obtainMessage();
            obtainMessage2.what = 1009;
            this.j.sendMessage(obtainMessage2);
            return;
        }
        if (e(jluVar.a())) {
            LogUtil.a("HwHandoffManager", "is have video format: true");
            Message obtainMessage3 = this.j.obtainMessage();
            obtainMessage3.what = 1007;
            this.j.sendMessage(obtainMessage3);
            return;
        }
        if (!c(jluVar.a())) {
            ReleaseLogUtil.e("R_HwHandoffManager", "is have illegal picture format: true");
            Message obtainMessage4 = this.j.obtainMessage();
            obtainMessage4.what = 1010;
            this.j.sendMessage(obtainMessage4);
            return;
        }
        boolean h = SmartClipManager.e(this.g).h();
        boolean f = SmartClipManager.e(this.g).f();
        ReleaseLogUtil.e("R_HwHandoffManager", "isPluginPrepared:", Boolean.valueOf(h), "isSupportSmartClip:", Boolean.valueOf(f));
        if (!Utils.o() && !CommonUtil.bf()) {
            SmartClipManager.e(this.g).j();
            if (f && !h) {
                Message obtainMessage5 = this.j.obtainMessage();
                obtainMessage5.what = 1008;
                this.j.sendMessage(obtainMessage5);
            }
        } else {
            try {
                jdh.c().a(100001);
            } catch (IllegalStateException unused) {
                LogUtil.b("HwHandoffManager", "delete health notification exception");
            }
        }
        b(jluVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        Notification build;
        if (i == 1) {
            Notification.Builder xf_ = jdh.d().xf_();
            xf_.setAutoCancel(true);
            xf_.setPriority(0);
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            xf_.setContentTitle(BaseApplication.getContext().getString(R$string.IDS_device_touch_transfer_enter_title));
            xf_.setGroup("HwHandoffManager");
            xf_.setShowWhen(true);
            xf_.setContentIntent(jdh.bFr_(BaseApplication.getContext()));
            xf_.setOngoing(true);
            xf_.setWhen(System.currentTimeMillis());
            build = xf_.build();
        } else {
            Notification.Builder xf_2 = jdh.c().xf_();
            xf_2.setSmallIcon(R.drawable.healthlogo_ic_notification);
            xf_2.setShowWhen(true);
            xf_2.setContentIntent(jdh.bFr_(BaseApplication.getContext()));
            xf_2.setAutoCancel(true);
            xf_2.setContentTitle(BaseApplication.getContext().getString(R$string.IDS_device_touch_transfer_enter_title));
            xf_2.setGroup("HwHandoffManager");
            xf_2.setWhen(System.currentTimeMillis());
            build = xf_2.build();
        }
        build.contentIntent = jdh.bFr_(BaseApplication.getContext());
        build.priority = 0;
        bHV_(build, i);
    }

    private void bHV_(Notification notification, int i) {
        if (EnvironmentInfo.j()) {
            String string = BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_enter_title);
            Notification.Builder xf_ = jdh.d().xf_();
            xf_.setContentTitle(string);
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            xf_.setStyle(new Notification.InboxStyle());
            Intent intent = new Intent();
            if (i == 1) {
                xf_.setContentText(BaseApplication.getContext().getResources().getString(R$string.IDS_harmony_permission_torage));
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + BaseApplication.getContext().getPackageName()));
            } else {
                intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.touchtransfer.TouchTransferHiaiActivity");
                xf_.setContentText(BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_hiai_content_title));
            }
            Notification build = xf_.build();
            xf_.setAutoCancel(true);
            build.contentIntent = PendingIntent.getActivity(BaseApplication.getContext(), 0, intent, 201326592);
            jdh.d().xh_(100001, build);
            return;
        }
        RemoteViews remoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_hiai_view);
        if (Build.VERSION.SDK_INT >= 31) {
            remoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_hiai_view_large_android13);
        }
        notification.contentView = remoteViews;
        Intent intent2 = new Intent();
        if (i == 1) {
            intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent2.setData(Uri.parse("package:" + BaseApplication.getContext().getPackageName()));
            String string2 = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_feedback_permission_guide_torage);
            if (Build.VERSION.SDK_INT >= 33) {
                string2 = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_permission_guide_image);
            }
            if ((CommonUtil.az() && HarmonyBuild.c > 6) || EnvironmentInfo.k()) {
                string2 = BaseApplication.getContext().getResources().getString(R$string.IDS_harmony_permission_torage);
            }
            remoteViews.setTextViewText(R.id.touch_hiai_title, string2);
        } else {
            intent2.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.touchtransfer.TouchTransferHiaiActivity");
            remoteViews.setTextViewText(R.id.touch_hiai_title, BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_hiai_content_title));
        }
        notification.contentIntent = PendingIntent.getActivity(BaseApplication.getContext(), 0, intent2, 201326592);
        if (i == 1) {
            jdh.d().xh_(100001, notification);
        } else {
            jdh.c().xh_(100001, notification);
        }
    }

    private void b(final jlu jluVar) {
        if (jluVar == null) {
            LogUtil.h("HwHandoffManager", "watchFaceHandoffBean is null");
        } else {
            jlo.d(this.g).c(0, new IBaseResponseCallback() { // from class: jli.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    int i2;
                    ReleaseLogUtil.e("R_HwHandoffManager", "facePhotoInfo getMaxBackgroudImages:", obj + "errorCode:" + i);
                    if (i == 101 && (obj instanceof jlq)) {
                        jlq jlqVar = (jlq) obj;
                        r1 = jlqVar.b() > 0 ? jlqVar.b() : 5;
                        i2 = jlqVar.a().size();
                        LogUtil.a("HwHandoffManager", "getAlbumWatchFaceNumber albumWatchFaceNumber is,", Integer.valueOf(i2));
                    } else {
                        i2 = 0;
                    }
                    jli.this.b(jluVar, i2, r1);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(jlu jluVar, int i, int i2) {
        int c2 = jluVar.c();
        LogUtil.a("HwHandoffManager", "processedTransferMessage albumWatchFaceNumber is,", Integer.valueOf(i));
        Message obtainMessage = this.j.obtainMessage();
        if (c2 > 0 && c2 + i <= i2) {
            obtainMessage.what = 1006;
            obtainMessage.obj = jluVar.a();
        } else {
            obtainMessage.what = 1000;
            obtainMessage.obj = Arrays.asList(Integer.valueOf(i2 - i), Integer.valueOf(i));
        }
        this.j.sendMessage(obtainMessage);
    }

    private boolean d(String str) {
        JSONArray jSONArray;
        String string;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException unused) {
            LogUtil.b("HwHandoffManager", "isCloudImageFormat build array find JSONException");
            jSONArray = null;
        }
        if (jSONArray == null) {
            LogUtil.h("HwHandoffManager", "resultArray is null");
            return false;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                string = jSONArray.getString(i);
                LogUtil.a("HwHandoffManager", ContentResource.FILE_NAME, string);
            } catch (JSONException unused2) {
                LogUtil.b("HwHandoffManager", "isCloudImageFormat find JSONException");
            }
            if (TextUtils.isEmpty(string)) {
                break;
            }
            if (string.contains(".photoShare")) {
                return true;
            }
        }
        return false;
    }

    private boolean e(String str) {
        JSONArray jSONArray;
        String string;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException unused) {
            LogUtil.b("HwHandoffManager", "isHaveVideoFormat build array find JSONException");
            jSONArray = null;
        }
        if (jSONArray == null) {
            LogUtil.h("HwHandoffManager", "resultArray is null");
            return false;
        }
        List asList = Arrays.asList("MP4", "MPEG", "MPG", "DAT", "AVI", "MOV", "ASF", "WMV", "NAVI", "3GP", "FLV", "RMVB");
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                string = jSONArray.getString(i);
                LogUtil.a("HwHandoffManager", "isHaveVideoFormat fileName:", string);
            } catch (JSONException unused2) {
                LogUtil.b("HwHandoffManager", "isHaveVideoFormat find JSONException");
            }
            if (TextUtils.isEmpty(string)) {
                break;
            }
            String[] split = string.toUpperCase(Locale.ENGLISH).split("\\.");
            if (split.length > 0 && asList.contains(split[split.length - 1])) {
                return true;
            }
        }
        return false;
    }

    private String a(String str) {
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException unused) {
            LogUtil.b("HwHandoffManager", "build array find JSONException");
            jSONArray = null;
        }
        String str2 = "";
        if (jSONArray == null) {
            LogUtil.h("HwHandoffManager", "resultArray is null");
            return "";
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                str2 = jSONArray.getString(i);
                LogUtil.a("HwHandoffManager", ContentResource.FILE_NAME, str2);
            } catch (JSONException unused2) {
                LogUtil.b("HwHandoffManager", "find JSONException");
            }
        }
        return str2;
    }

    private boolean c(String str) {
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            ReleaseLogUtil.e("R_HwHandoffManager", "isSupportPictureFormat fileName is null");
            return false;
        }
        String[] split = a2.split("\\.");
        if (split.length <= 0) {
            return false;
        }
        String str2 = split[split.length - 1];
        ReleaseLogUtil.e("R_HwHandoffManager", "fileType is:", str2);
        return b.contains(str2.toLowerCase(Locale.ENGLISH));
    }

    public boolean a() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            if (defaultAdapter.isEnabled()) {
                LogUtil.a("HwHandoffManager", "bluetooth is open");
                return true;
            }
            LogUtil.a("HwHandoffManager", "bluetooth is close");
            return false;
        }
        LogUtil.a("HwHandoffManager", "bluetooth is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public jlu b(String str) {
        try {
            jlu jluVar = new jlu();
            JSONObject jSONObject = new JSONObject(str);
            int d = jnn.d(jSONObject, "handoff_data_type");
            if (d == -1) {
                d = jnn.d(jSONObject, "onehop_data_type");
            }
            jluVar.e(d);
            jluVar.e(jnn.e(jSONObject, "btmac").toUpperCase(Locale.ENGLISH));
            JSONObject jSONObject2 = new JSONObject(jnn.e(jSONObject, "url"));
            int d2 = jnn.d(jSONObject2, "handoff_media_file_num");
            jluVar.a(d2);
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < d2) {
                StringBuilder sb = new StringBuilder();
                sb.append("handoff_media_file_path");
                i++;
                sb.append(String.valueOf(i));
                String e2 = jnn.e(jSONObject2, sb.toString());
                if (CommonUtil.ag(BaseApplication.getContext())) {
                    File file = new File(e2);
                    int lastIndexOf = e2.lastIndexOf("/");
                    if (file.isFile() && lastIndexOf != -1) {
                        LogUtil.a("R_HwHandoffManager", "parse fileName:", e2.substring(lastIndexOf));
                    }
                }
                jSONArray.put(e2);
            }
            jluVar.c(jSONArray.toString());
            return jluVar;
        } catch (JSONException unused) {
            LogUtil.b("HwHandoffManager", "parseHandoffJsonString find JSONException");
            return null;
        }
    }

    public boolean e() {
        if (!a()) {
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = 1003;
            this.j.sendMessage(obtainMessage);
            return false;
        }
        DeviceInfo a2 = jpt.a("HwHandoffManager");
        if (a2 != null && a2.getDeviceConnectState() == 2) {
            return true;
        }
        Message obtainMessage2 = this.j.obtainMessage();
        obtainMessage2.what = 1002;
        this.j.sendMessage(obtainMessage2);
        return false;
    }
}
