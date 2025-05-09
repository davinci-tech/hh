package com.huawei.healthcloud.plugintrack.manager.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.healthcloud.plugintrack.manager.voice.PrimaryVoicePlayManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gut;
import defpackage.gxd;
import defpackage.jdt;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class VoiceEngService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private Handler f3522a;
    private a b;
    private Context c;
    private c e;
    private PrimaryVoicePlayManager f;
    private long h = 0;
    private long g = 0;
    private boolean d = false;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("Track_VoiceEngService", "onCreate");
        this.c = getApplicationContext();
        this.f3522a = new Handler();
        IntentFilter intentFilter = new IntentFilter("action_voice_soundpool_enge");
        intentFilter.addAction("action_play_voice");
        intentFilter.addAction("action_stop_voice");
        intentFilter.addAction("action_start_audio");
        intentFilter.addAction("action_pause_audio");
        intentFilter.addAction("action_resume_audio");
        intentFilter.addAction("action_mute_audio_volume");
        intentFilter.addAction("action_resume_audio_volume");
        intentFilter.addAction("action_stop_service");
        intentFilter.addAction("ACTION_RESET_MEDIA_IDLE");
        c cVar = new c();
        this.e = cVar;
        gut.aUm_(this.c, cVar, intentFilter);
        this.f = new PrimaryVoicePlayManager(this.c);
        if (this.b == null) {
            this.b = new a(this);
        }
        jdt.c(this.c, true, this.b);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("Track_VoiceEngService", "onDestroy");
        gut.aUp_(this.c, this.e);
        if (this.g < this.h && !CommonUtil.x(this.c)) {
            e();
        }
        PrimaryVoicePlayManager primaryVoicePlayManager = this.f;
        if (primaryVoicePlayManager != null) {
            primaryVoicePlayManager.e();
        }
        jdt.c(this.c, false, this.b);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.c("Track_VoiceEngService", "onStartCommand");
        super.onStartCommand(intent, i, i2);
        aUA_(intent);
        this.h = System.currentTimeMillis();
        this.f3522a.removeCallbacksAndMessages(null);
        b();
        return 2;
    }

    class c extends BroadcastReceiver {
        private c() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            char c;
            if (intent == null) {
                LogUtil.b("Track_VoiceEngService", "onReceive", "intent = null");
            }
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("player_name_key");
            ReleaseLogUtil.e("Track_VoiceEngService", "onReceive action = ", action, " player name:", stringExtra);
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "defaultPlayer";
            }
            action.hashCode();
            switch (action.hashCode()) {
                case -2088895936:
                    if (action.equals("action_mute_audio_volume")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1809804784:
                    if (action.equals("action_play_voice")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1465471696:
                    if (action.equals("action_start_audio")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1404989500:
                    if (action.equals("action_pause_audio")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -595262616:
                    if (action.equals("ACTION_RESET_MEDIA_IDLE")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -447169442:
                    if (action.equals("action_stop_voice")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -154180435:
                    if (action.equals("action_resume_audio")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 740184812:
                    if (action.equals("action_resume_audio_volume")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 1121945313:
                    if (action.equals("action_stop_service")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    VoiceEngService.this.f.b(stringExtra);
                    break;
                case 1:
                    if (!VoiceEngService.this.d) {
                        VoiceEngService.this.f.aVo_(stringExtra, intent);
                        break;
                    } else {
                        LogUtil.h("Track_VoiceEngService", "is Calling.do not insert voice.");
                        break;
                    }
                case 2:
                    VoiceEngService.this.f.b(stringExtra, intent.getStringExtra("param_long_audio_url"));
                    break;
                case 3:
                    VoiceEngService.this.f.a(stringExtra);
                    break;
                case 4:
                    VoiceEngService.this.f.c(stringExtra);
                    break;
                case 5:
                    VoiceEngService.this.f.j(stringExtra);
                    break;
                case 6:
                    VoiceEngService.this.f.d(stringExtra);
                    break;
                case 7:
                    VoiceEngService.this.f.e(stringExtra);
                    break;
                case '\b':
                    VoiceEngService.this.a();
                    break;
                default:
                    LogUtil.a("Track_VoiceEngService", "Invalid action");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("Track_VoiceEngService", "stopEngine() enter");
        this.f3522a.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.manager.service.VoiceEngService.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Track_VoiceEngService", "stopEngine() run");
                VoiceEngService.this.g = System.currentTimeMillis();
                gxd.a().b(false);
                VoiceEngService.this.stopSelf();
            }
        }, 25000L);
    }

    private void aUA_(Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("player_name_key");
        int intExtra = intent.getIntExtra("player_type_key", 0);
        ReleaseLogUtil.e("Track_VoiceEngService", "handleIntent", "intent action = ", action, " playerType:", Integer.valueOf(intExtra), " playerName:", stringExtra);
        if ("action_voice_soundpool_enge".equals(action)) {
            this.f.d(intExtra, stringExtra);
        }
    }

    private void e() {
        LogUtil.a("Track_VoiceEngService", "restartVoiceService() run");
        Intent intent = new Intent(this, (Class<?>) VoiceEngService.class);
        intent.setAction("action_voice_soundpool_enge");
        startService(intent);
    }

    private void b() {
        Intent intent = new Intent("checkserviceaction");
        Bundle bundle = new Bundle();
        bundle.putInt("SERVICETYPE", 1);
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(this.c).sendBroadcast(intent);
    }

    static class a extends jdt.c {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<VoiceEngService> f3523a;

        a(VoiceEngService voiceEngService) {
            this.f3523a = new WeakReference<>(voiceEngService);
        }

        @Override // jdt.c, android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            LogUtil.a("Track_VoiceEngService", "onCallStateChanged() enter , state is ", Integer.valueOf(i));
            VoiceEngService voiceEngService = this.f3523a.get();
            if (voiceEngService == null) {
                LogUtil.h("Track_VoiceEngService", "voiceEngService == null");
                return;
            }
            if (i != 0) {
                if (i == 1 || i == 2) {
                    b(voiceEngService);
                    return;
                }
                return;
            }
            voiceEngService.d = false;
            PrimaryVoicePlayManager primaryVoicePlayManager = voiceEngService.f;
            if (primaryVoicePlayManager != null) {
                primaryVoicePlayManager.a();
            }
        }

        private void b(VoiceEngService voiceEngService) {
            voiceEngService.d = true;
            PrimaryVoicePlayManager primaryVoicePlayManager = voiceEngService.f;
            if (primaryVoicePlayManager != null) {
                primaryVoicePlayManager.d();
            }
        }
    }
}
