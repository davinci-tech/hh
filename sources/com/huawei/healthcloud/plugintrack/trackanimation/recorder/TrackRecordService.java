package com.huawei.healthcloud.plugintrack.trackanimation.recorder;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ham;
import defpackage.hbl;
import defpackage.hbn;
import defpackage.jdh;
import defpackage.nsn;
import defpackage.sqd;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;

/* loaded from: classes4.dex */
public class TrackRecordService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private Handler f3588a;
    private boolean b;
    private BroadcastReceiver c;
    private boolean d;
    private String e;
    private Intent f;
    private long g;
    private TrackRecorder h;
    private String i;
    private int j;
    private Uri k;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f3588a = new Handler(Looper.getMainLooper()) { // from class: com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecordService.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 15790320) {
                    LogUtil.a("Suggestion_TrackRecordService", "handleMessage: ", Integer.valueOf(message.what));
                    if (TrackRecordService.this.h == null) {
                        TrackRecordService.this.c(true, 0L);
                    } else {
                        TrackRecordService trackRecordService = TrackRecordService.this;
                        trackRecordService.c(true ^ trackRecordService.h.isRunSuccess(), TrackRecordService.this.h.getVideoDuration());
                    }
                    TrackRecordService.this.c();
                    TrackRecordService.this.stopSelf();
                }
            }
        };
        c();
        b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ats_track_record_stop");
        intentFilter.addAction("ats_track_record_stop_when_pause");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.c, intentFilter);
    }

    private void b() {
        this.c = new BroadcastReceiver() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.recorder.TrackRecordService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    ReleaseLogUtil.d("Suggestion_TrackRecordService", "onReceive: intent is null");
                    return;
                }
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    ReleaseLogUtil.d("Suggestion_TrackRecordService", "onReceive: action is empty");
                    return;
                }
                ReleaseLogUtil.e("Suggestion_TrackRecordService", "onReceive: action : ", action);
                action.hashCode();
                if (!action.equals("ats_track_record_stop")) {
                    if (action.equals("ats_track_record_stop_when_pause") && TrackRecordService.this.h != null) {
                        TrackRecordService.this.h.stopScreenOnActive();
                        return;
                    }
                    return;
                }
                if (TrackRecordService.this.h != null) {
                    TrackRecordService.this.h.stopScreen();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z, long j) {
        ReleaseLogUtil.e("Suggestion_TrackRecordService", "notifyRecordFinish: isRecordError = ", Boolean.valueOf(z), " videoDuration = ", Long.valueOf(j));
        Intent intent = new Intent("sta_track_record_finish");
        intent.putExtra("data_key_video_duration", j);
        intent.putExtra("data_key_video_record_error", z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.c != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.c);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 2;
        }
        aYz_(intent);
        if (this.f == null) {
            LogUtil.h("Suggestion_TrackRecordService", "onStartCommand: mResultData is null");
            return 2;
        }
        e();
        Object systemService = getSystemService("media_projection");
        MediaProjectionManager mediaProjectionManager = systemService instanceof MediaProjectionManager ? (MediaProjectionManager) systemService : null;
        if (mediaProjectionManager != null) {
            aYA_(mediaProjectionManager.getMediaProjection(this.j, this.f));
        }
        return 2;
    }

    private void aYz_(Intent intent) {
        this.j = intent.getIntExtra("resultCode", -1);
        this.f = (Intent) intent.getParcelableExtra("data");
        this.g = intent.getLongExtra("duration", 0L);
        this.b = intent.getBooleanExtra("isShort", false);
        String stringExtra = intent.getStringExtra("mMusicResourcePath");
        String e = ham.e();
        if (!TextUtils.isEmpty(stringExtra) && sqd.d(new File(stringExtra), e)) {
            this.e = stringExtra;
        }
        String stringExtra2 = intent.getStringExtra("mVideoPath");
        String d = new hbl().d();
        if (!TextUtils.isEmpty(stringExtra2) && !TextUtils.isEmpty(d) && sqd.d(new File(stringExtra2), d)) {
            this.i = stringExtra2;
        }
        LogUtil.a("Suggestion_TrackRecordService", "getDataFromIntent dynamicCloudConfigPathPrefix ", e, " musicResourcePath ", stringExtra, " savedDirectory ", d, " videoPath ", stringExtra2);
        this.d = intent.getBooleanExtra("isHdVideoType", false);
        this.k = null;
        String stringExtra3 = intent.getStringExtra("mVideoUri");
        if (!TextUtils.isEmpty(stringExtra3)) {
            try {
                this.k = Uri.parse(stringExtra3);
                return;
            } catch (IllegalArgumentException e2) {
                ReleaseLogUtil.d("Suggestion_TrackRecordService", "getDataFromIntent: uri parse error ", LogAnonymous.b((Throwable) e2));
                return;
            }
        }
        ReleaseLogUtil.e("Suggestion_TrackRecordService", "getDataFromIntent: uri is empty=true, videoPath is = ", Boolean.valueOf(TextUtils.isEmpty(this.i)));
    }

    private void aYA_(MediaProjection mediaProjection) {
        if (mediaProjection == null) {
            LogUtil.h("Suggestion_TrackRecordService", "initRecorder: mediaProjection is null");
            return;
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("sta_track_record_start"));
        boolean ag = nsn.ag(this);
        try {
            if (this.k != null && PermissionUtil.a()) {
                aYy_(mediaProjection, ag);
            } else if (TextUtils.isEmpty(this.i)) {
                ReleaseLogUtil.d("Suggestion_TrackRecordService", "initRecorder: os is ", Integer.valueOf(Build.VERSION.SDK_INT), " videoPath is empty ,uri is null");
            } else {
                this.h = new hbn().a(this.b).c(this.g).aYw_(mediaProjection, this.i, ag, this.d).setAudioFilePath(this.e).setHandler(this.f3588a);
            }
            TrackRecorder trackRecorder = this.h;
            if (trackRecorder == null) {
                ReleaseLogUtil.d("Suggestion_TrackRecordService", "initRecorder: mRecorder is null");
                c(true, 0L);
            } else {
                trackRecorder.start();
            }
        } catch (IllegalArgumentException e) {
            ReleaseLogUtil.c("Suggestion_TrackRecordService", "initRecorder error ", LogAnonymous.b((Throwable) e));
            c(true, 0L);
        }
    }

    private void aYy_(MediaProjection mediaProjection, boolean z) {
        if (this.k == null) {
            LogUtil.h("Suggestion_TrackRecordService", "buildRecorderInR: fileDescriptor is null");
        } else {
            this.h = new hbn().a(this.b).c(this.g).aYv_(mediaProjection, this.k, z, this.d).setAudioFilePath(this.e).setHandler(this.f3588a);
        }
    }

    private void e() {
        Notification build = jdh.c().xf_().setContentIntent(PendingIntent.getService(this, 0, new Intent().setPackage(BaseApplication.getAppPackage()), 201326592)).setSmallIcon(R.drawable.healthlogo_ic_notification).setContentTitle(getResources().getString(R.string._2130839870_res_0x7f02093e)).setContentText(getResources().getString(R.string._2130840041_res_0x7f0209e9)).setWhen(System.currentTimeMillis()).build();
        build.flags = 32;
        jdh.c().xh_(20211229, build);
        if (PermissionUtil.c()) {
            startForeground(20211229, build, 32);
        } else {
            startForeground(20211229, build);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        TrackRecorder trackRecorder = this.h;
        if (trackRecorder != null) {
            trackRecorder.stopScreen();
            this.h = null;
        }
        c();
        stopForeground(true);
    }
}
