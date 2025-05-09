package com.huawei.health.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.huawei.health.R;
import com.huawei.health.baseapi.hiaiengine.HiAiSmartClipApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.profile.ProfileExtendConstants;
import defpackage.bzo;
import defpackage.jdh;
import defpackage.jli;
import defpackage.jnu;
import defpackage.jpt;
import health.compact.a.EnvironmentInfo;
import health.compact.a.NotificationUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class HandoffService extends IntentService {
    private static final int CLIPING_PHOTO_MESSAGE_ID = 10005;
    private static final int CLIP_MESSAGE_ID = 10002;
    private static final int FOREGROUND_SERVICE_ID = 100;
    private static final int NOTIFICATION_CANCEL_DELAYED_TIME = 5000;
    private static final int NOTIFICATION_CANCEL_MESSAGE_ID = 10004;
    private static final int NOTIFICATION_TRANSFER_MESSAGE_TIME = 1500;
    private static final int PROGRESS_MAX_VALUE = 100;
    private static final int PROGRESS_OFFSET = 1;
    private static final int QUANTITY_UNIT_LEVEL = 1;
    private static final String TAG = "HandoffService";
    private static final String TAG_RELEASE = "R_HandoffService";
    private static final int TRANFERING_MESSAGE_ID = 10001;
    private static final int TRANSFER_NOTIFICATION_ID = 100000;
    private static final int TRANSFRED_MESSAGE_ID = 10003;
    private static final String WATCH_FACE_HAND_OFF_FILE_PATH = "watchFaceHandoffFilePath";
    private static boolean sIsCancelNotification;
    private static long sTransferMillTime;
    private SmartClipManager.SmartClipCallback mClipCallback;
    private Handler mHandler;
    private Notification mNotification;
    private RemoteViews mRemoteViews;
    private SmartClipManager.ClipTransferCallback mTransferCallback;
    private int mTransferFailedCount;
    private int mTransferProgressPaddingValue;
    private int mTransferProgressValue;
    private int mTransferTotalCount;

    public static int getTransferNotificationId() {
        return 100000;
    }

    public HandoffService() {
        super(TAG);
        this.mHandler = new Handler() { // from class: com.huawei.health.service.HandoffService.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.h(HandoffService.TAG, "mHandler handleMessage: message is null");
                }
                super.handleMessage(message);
                LogUtil.a(HandoffService.TAG_RELEASE, "message.what:", Integer.valueOf(message.what));
                switch (message.what) {
                    case 10001:
                        HandoffService.this.transferingNotificationView();
                        break;
                    case 10002:
                        if (!jli.d(BaseApplication.getContext()).e()) {
                            ReleaseLogUtil.d(HandoffService.TAG_RELEASE, "HwHandoffManager getBluetoothOpenState false");
                            break;
                        } else {
                            ArrayList<String> arrayList = message.obj instanceof ArrayList ? (ArrayList) message.obj : null;
                            LogUtil.a(HandoffService.TAG, "CLIP_MESSAGE_ID pathsList: ", arrayList);
                            SmartClipManager.e(BaseApplication.getContext()).e(arrayList, HandoffService.this.mTransferCallback);
                            break;
                        }
                    case 10003:
                        HandoffService.this.displayTransferedNotificationView();
                        break;
                    case 10004:
                        if (HandoffService.sIsCancelNotification) {
                            boolean unused = HandoffService.sIsCancelNotification = false;
                            try {
                                jdh.d().a(100000);
                                break;
                            } catch (IllegalStateException unused2) {
                                LogUtil.b(HandoffService.TAG, "cancel transfer notification exception");
                                return;
                            }
                        }
                        break;
                    case 10005:
                        HandoffService.this.clipingNotificationView();
                        break;
                    default:
                        LogUtil.h(HandoffService.TAG, "Handler default");
                        break;
                }
            }
        };
        this.mClipCallback = new SmartClipManager.SmartClipCallback() { // from class: com.huawei.health.service.HandoffService.4
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.SmartClipCallback
            public void onClipResult(int i, List<String> list) {
                LogUtil.a(HandoffService.TAG, "onClipResult clipPaths: ", list);
                ReleaseLogUtil.e(HandoffService.TAG_RELEASE, "onClipResult resultCode = ", Integer.valueOf(i));
                if (i == 0) {
                    if (list != null) {
                        Message obtainMessage = HandoffService.this.mHandler.obtainMessage();
                        obtainMessage.what = 10002;
                        obtainMessage.obj = list;
                        HandoffService.this.mHandler.sendMessage(obtainMessage);
                        return;
                    }
                    return;
                }
                if (i == -2) {
                    LogUtil.a(HandoffService.TAG, "Clip result disconnect");
                    Message obtainMessage2 = HandoffService.this.mHandler.obtainMessage();
                    obtainMessage2.what = 10003;
                    HandoffService.this.mHandler.sendMessage(obtainMessage2);
                    return;
                }
                if (i == -3) {
                    LogUtil.a(HandoffService.TAG, "Clip result timeout");
                    try {
                        jdh.d().a(100000);
                        return;
                    } catch (IllegalStateException unused) {
                        LogUtil.b(HandoffService.TAG, "delete transfer notification exception");
                        return;
                    }
                }
                LogUtil.h(HandoffService.TAG, "Clip Not Success");
            }
        };
        this.mTransferCallback = new SmartClipManager.ClipTransferCallback() { // from class: com.huawei.health.service.HandoffService.5
            @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.ClipTransferCallback
            public void onTransferProgress(int i, int i2, int i3) {
                LogUtil.a(HandoffService.TAG, "transfer progress: ", Integer.valueOf(i3));
                HandoffService.this.mTransferProgressValue = i3;
                HandoffService.this.mTransferTotalCount = i;
                if (HandoffService.this.mTransferProgressValue == 99) {
                    Message obtainMessage = HandoffService.this.mHandler.obtainMessage();
                    obtainMessage.what = 10001;
                    HandoffService.this.mHandler.sendMessage(obtainMessage);
                } else if (HandoffService.this.mTransferProgressValue < 99) {
                    SmartClipManager.d(true);
                    HandoffService.this.processTransferingMessage();
                } else {
                    SmartClipManager.d(false);
                    boolean unused = HandoffService.sIsCancelNotification = true;
                    Message obtainMessage2 = HandoffService.this.mHandler.obtainMessage();
                    obtainMessage2.what = 10004;
                    HandoffService.this.mHandler.sendMessageDelayed(obtainMessage2, 5000L);
                    LogUtil.h(HandoffService.TAG, "transfer complete");
                }
                LogUtil.a(HandoffService.TAG, "transfer mTransferTotalCount: ", Integer.valueOf(HandoffService.this.mTransferTotalCount));
            }

            @Override // com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.SmartClipManager.ClipTransferCallback
            public void onTransferResult(int i, int i2) {
                SmartClipManager.d(false);
                HandoffService.this.mTransferFailedCount = i2;
                LogUtil.a(HandoffService.TAG_RELEASE, "onTransferResultCode: ", Integer.valueOf(i));
                Message obtainMessage = HandoffService.this.mHandler.obtainMessage();
                obtainMessage.what = 10003;
                HandoffService.this.mHandler.sendMessage(obtainMessage);
                HiAiSmartClipApi hiAiSmartClip = bzo.c().getHiAiSmartClip();
                if (hiAiSmartClip != null) {
                    hiAiSmartClip.unbindHiAiService();
                }
                if (i == 0 && HandoffService.this.mTransferFailedCount == 0) {
                    boolean unused = HandoffService.sIsCancelNotification = true;
                    Message obtainMessage2 = HandoffService.this.mHandler.obtainMessage();
                    obtainMessage2.what = 10004;
                    HandoffService.this.mHandler.sendMessageDelayed(obtainMessage2, 5000L);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processTransferingMessage() {
        if (this.mTransferProgressValue - this.mTransferProgressPaddingValue > 1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - sTransferMillTime > ProfileExtendConstants.TIME_OUT) {
                sTransferMillTime = currentTimeMillis;
                LogUtil.a(TAG, "sTransferMillTime: ", Long.valueOf(currentTimeMillis));
                this.mTransferProgressPaddingValue = this.mTransferProgressValue;
                Message obtainMessage = this.mHandler.obtainMessage();
                obtainMessage.what = 10001;
                this.mHandler.sendMessage(obtainMessage);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clipingNotificationView() {
        if (EnvironmentInfo.j()) {
            String string = BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_enter_title);
            String string2 = BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_cliping);
            Notification.Builder xf_ = jdh.d().xf_();
            xf_.setProgress(100, this.mTransferProgressValue, false);
            xf_.setContentTitle(string);
            xf_.setContentText(string2);
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            xf_.setOngoing(true);
            this.mNotification = xf_.build();
            jdh.d().xh_(100000, this.mNotification);
            return;
        }
        this.mRemoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_transfering_view);
        if (Build.VERSION.SDK_INT >= 31) {
            this.mRemoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_transfering_view_large_android13);
        }
        this.mRemoteViews.setTextViewText(R.id.touch_transfer_percent, UnitUtil.e(this.mTransferProgressValue, 2, 0));
        this.mRemoteViews.setTextViewText(R.id.touch_transfer_title, BaseApplication.getContext().getResources().getString(R$string.IDS_device_touch_transfer_cliping));
        this.mRemoteViews.setProgressBar(R.id.touch_transfer_progress, 0, this.mTransferProgressValue, false);
        this.mNotification.contentView = this.mRemoteViews;
        this.mNotification.bigContentView = this.mRemoteViews;
        jdh.d().xh_(100000, this.mNotification);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transferingNotificationView() {
        String i = jnu.i();
        if (i == null) {
            i = "";
        }
        if (EnvironmentInfo.j()) {
            String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903260_res_0x7f0300dc, 1, Integer.valueOf(this.mTransferTotalCount), i);
            Notification.Builder xf_ = jdh.d().xf_();
            xf_.setProgress(100, this.mTransferProgressValue, false);
            xf_.setContentTitle(quantityString);
            xf_.setOngoing(true);
            xf_.setContentText(getString(R$string.IDS_settings_firmware_upgrade_band_transfer_finish) + UnitUtil.e(this.mTransferProgressValue, 2, 0));
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            this.mNotification = xf_.build();
            jdh.d().xh_(100000, this.mNotification);
            return;
        }
        this.mRemoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_transfering_view);
        if (Build.VERSION.SDK_INT >= 31) {
            this.mRemoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_transfering_view_large_android13);
        }
        this.mRemoteViews.setTextViewText(R.id.touch_transfer_percent, UnitUtil.e(this.mTransferProgressValue, 2, 0));
        this.mRemoteViews.setTextViewText(R.id.touch_transfer_title, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903260_res_0x7f0300dc, 1, Integer.valueOf(this.mTransferTotalCount), i));
        LogUtil.a(TAG, "transferingNotificationView mTransferProgressValue: ", Integer.valueOf(this.mTransferProgressValue));
        this.mRemoteViews.setProgressBar(R.id.touch_transfer_progress, 100, this.mTransferProgressValue, false);
        this.mNotification.contentView = this.mRemoteViews;
        this.mNotification.bigContentView = this.mRemoteViews;
        jdh.d().xh_(100000, this.mNotification);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayTransferedNotificationView() {
        String string;
        String i = jnu.i();
        if (i == null) {
            i = "";
        }
        Context context = BaseApplication.getContext();
        Resources resources = context.getResources();
        DeviceInfo a2 = jpt.a(TAG);
        if (!jli.d(context).a() || a2 == null || a2.getDeviceConnectState() != 2) {
            string = resources.getString(R$string.IDS_hw_health_wear_connect_device_disconnect);
        } else {
            string = resources.getString(R$string.IDS_device_touch_transfer_have_create_album_dial, i);
        }
        int d = SmartClipManager.e(BaseApplication.getContext()).d();
        if (d < 0) {
            d = 0;
        }
        int c = SmartClipManager.e(BaseApplication.getContext()).c();
        String quantityString = resources.getQuantityString(R.plurals._2130903261_res_0x7f0300dd, 1, Integer.valueOf(d));
        String quantityString2 = resources.getQuantityString(R.plurals._2130903261_res_0x7f0300dd, 1, Integer.valueOf(c));
        String format = String.format(Locale.ENGLISH, resources.getString(R$string.IDS_device_touch_transfer_transferred_photos_result_content), quantityString, quantityString2);
        if (EnvironmentInfo.j()) {
            Notification.Builder xf_ = jdh.d().xf_();
            xf_.setContentTitle(string);
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            this.mNotification = xf_.build();
            xf_.setContentText(format);
            xf_.setOngoing(true);
            jdh.d().xh_(100000, this.mNotification);
            return;
        }
        this.mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_transfer_end_view);
        if (Build.VERSION.SDK_INT >= 31) {
            this.mRemoteViews = new RemoteViews(BaseApplication.getContext().getPackageName(), R.layout.notification_transfer_end_view_large_android13);
        }
        this.mRemoteViews.setTextViewText(R.id.touch_transfer_end_title, string);
        this.mRemoteViews.setTextViewText(R.id.touch_transfer_content, format);
        this.mNotification.contentView = this.mRemoteViews;
        this.mNotification.bigContentView = this.mRemoteViews;
        LogUtil.a(TAG, "displayTransferedNotificationView mNotification: ", this.mNotification);
        jdh.d().xh_(100000, this.mNotification);
    }

    public static void startWatchFaceTransferService(Context context, String str) {
        if (context == null) {
            LogUtil.h(TAG, "startWatchFaceTransferService context is null");
            return;
        }
        LogUtil.a(TAG, "startWatchFaceTransferService onStartCommand: ", str);
        Intent intent = new Intent(context, (Class<?>) HandoffService.class);
        intent.putExtra(WATCH_FACE_HAND_OFF_FILE_PATH, str);
        context.startForegroundService(intent);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a(TAG, "onCreate");
        buildTransferNotification();
        try {
            startForeground(100, this.mNotification);
        } catch (Exception unused) {
            LogUtil.b(TAG, "startForeground Exception");
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Context context;
        ReleaseLogUtil.e(TAG_RELEASE, "onStartCommand");
        bindHiaiService();
        if (!jli.d(BaseApplication.getContext()).c() && (context = (Context) new WeakReference(this).get()) != null) {
            jli.d(context).b();
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a(TAG, "onBind");
        return null;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        jdh.d().a(100000);
        super.onTaskRemoved(intent);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        JSONArray jSONArray;
        if (intent == null) {
            LogUtil.h(TAG, "intent is null");
            return;
        }
        bindHiaiService();
        if (!jli.d(BaseApplication.getContext()).c()) {
            Context context = (Context) new WeakReference(this).get();
            if (context != null) {
                jli.d(context).b();
            } else {
                LogUtil.h(TAG, "context is null.");
            }
        }
        try {
            String stringExtra = intent.getStringExtra(WATCH_FACE_HAND_OFF_FILE_PATH);
            if (!TextUtils.isEmpty(stringExtra)) {
                try {
                    jSONArray = new JSONArray(stringExtra);
                } catch (JSONException unused) {
                    ReleaseLogUtil.c(TAG_RELEASE, "onHandleIntent JSONException");
                    jSONArray = null;
                }
                if (jSONArray == null || jSONArray.length() <= 0) {
                    return;
                }
                transferWatchFaceToWatch(jSONArray);
                return;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "onHandleIntent pathString is null");
        } catch (Exception unused2) {
            LogUtil.b(TAG, "onHandleIntent Exception");
        }
    }

    private void bindHiaiService() {
        HiAiSmartClipApi hiAiSmartClip;
        boolean f = SmartClipManager.e(BaseApplication.getContext()).f();
        ReleaseLogUtil.e(TAG_RELEASE, "isSupportSmartClip:", Boolean.valueOf(f));
        if (!f || (hiAiSmartClip = bzo.c().getHiAiSmartClip()) == null) {
            return;
        }
        hiAiSmartClip.init(BaseApplication.getContext());
    }

    private void transferWatchFaceToWatch(JSONArray jSONArray) {
        LogUtil.a(TAG, "transferWatchFaceToWatch");
        this.mTransferProgressValue = 0;
        this.mTransferTotalCount = 0;
        this.mTransferFailedCount = 0;
        this.mTransferProgressPaddingValue = 0;
        sTransferMillTime = 0L;
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String string = jSONArray.getString(i);
                LogUtil.a(TAG, "fileName: ", string);
                arrayList.add(string);
            } catch (JSONException unused) {
                LogUtil.b(TAG, "transferWatchFaceToWatch JSONException");
            }
        }
        if (arrayList.size() > 0) {
            LogUtil.a(TAG, "pathsList: ", arrayList.toString());
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 10005;
            this.mHandler.sendMessage(obtainMessage);
            SmartClipManager.e(BaseApplication.getContext()).b(arrayList, false, true, this.mClipCallback);
            sIsCancelNotification = true;
            Message obtainMessage2 = this.mHandler.obtainMessage();
            obtainMessage2.what = 10004;
            this.mHandler.sendMessageDelayed(obtainMessage2, 5000L);
            return;
        }
        LogUtil.h(TAG, "pathsList.size is 0");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        stopForeground(false);
        super.onDestroy();
    }

    private void buildTransferNotification() {
        Notification.Builder xf_ = jdh.d().xf_();
        xf_.setAutoCancel(true);
        xf_.setPriority(0);
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setContentTitle(BaseApplication.getContext().getString(R$string.IDS_device_touch_transfer_enter_title));
        xf_.setGroup(TAG);
        xf_.setShowWhen(true);
        xf_.setContentIntent(NotificationUtil.aOw_(BaseApplication.getContext()));
        xf_.setOngoing(true);
        xf_.setWhen(System.currentTimeMillis());
        this.mNotification = xf_.build();
    }
}
