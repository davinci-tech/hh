package com.huawei.healthcloud.plugintrack.sportmusic;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;

/* loaded from: classes4.dex */
public class SportMusicInteratorService extends Service {
    private SportMusicController e = null;
    private SportMusicInteratorBroadcastReceiver c = null;

    /* renamed from: a, reason: collision with root package name */
    private Handler f3579a = new b();
    private LocalBroadcastManager d = null;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.a("Track_SportMusicInteratorService", "onStartCommand ", Integer.valueOf(i), " ", Integer.valueOf(i2));
        super.onStartCommand(intent, i, i2);
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager localBroadcastManager = this.d;
            if (localBroadcastManager != null) {
                localBroadcastManager.unregisterReceiver(this.c);
            } else {
                LogUtil.h("Track_SportMusicInteratorService", "onDestroy mLocalBroadcastManager is null");
            }
        } catch (IllegalArgumentException e) {
            LogUtil.h("Track_SportMusicInteratorService", "onDestroy ", e.getMessage());
        }
        LogUtil.a("Track_SportMusicInteratorService", "onDestroy");
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.e = SportMusicController.a();
        this.c = new SportMusicInteratorBroadcastReceiver(this.f3579a);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_stop_play_sport_music");
        intentFilter.addAction("action_send_steprate_to_hwmusic");
        intentFilter.addAction("action_next_song");
        intentFilter.addAction("action_pause_song");
        intentFilter.addAction("action_play_song");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        this.d = localBroadcastManager;
        if (localBroadcastManager != null) {
            localBroadcastManager.registerReceiver(this.c, intentFilter);
        } else {
            LogUtil.h("Track_SportMusicInteratorService", "onCreate mLocalBroadcastManager is null");
        }
        LogUtil.a("Track_SportMusicInteratorService", "onCreate");
    }

    public static class SportMusicInteratorBroadcastReceiver extends BroadcastReceiver {
        private Handler c;

        public SportMusicInteratorBroadcastReceiver(Handler handler) {
            this.c = handler;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("Track_SportMusicInteratorService", "intent or action is null");
                return;
            }
            if (this.c == null) {
                LogUtil.h("Track_SportMusicInteratorService", "mHandler is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("Track_SportMusicInteratorService", intent.getPackage(), ":", action);
            if ("action_stop_play_sport_music".equals(action)) {
                Message obtainMessage = this.c.obtainMessage(2002);
                Bundle bundleExtra = intent.getBundleExtra("extroInfo");
                obtainMessage.setData(bundleExtra);
                if (bundleExtra == null) {
                    obtainMessage.arg1 = -1;
                }
                this.c.sendMessage(obtainMessage);
                this.c.sendMessageDelayed(this.c.obtainMessage(2006), 100L);
                return;
            }
            if ("action_send_steprate_to_hwmusic".equals(action)) {
                Message obtainMessage2 = this.c.obtainMessage(2003);
                obtainMessage2.arg1 = intent.getIntExtra("stepRate", 0);
                this.c.sendMessage(obtainMessage2);
            } else if ("action_next_song".equals(action)) {
                this.c.sendMessage(this.c.obtainMessage(2001));
            } else if ("action_pause_song".equals(action)) {
                this.c.sendMessage(this.c.obtainMessage(2004));
            } else if ("action_play_song".equals(action)) {
                this.c.sendMessage(this.c.obtainMessage(2005));
            } else {
                LogUtil.b("Track_SportMusicInteratorService", "other action");
            }
        }
    }

    class b extends Handler {
        private b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            LogUtil.a("Track_SportMusicInteratorService", "handle msg : ", Integer.valueOf(message.what));
            if (message.what != 2006) {
                if (SportMusicInteratorService.this.e != null) {
                    switch (message.what) {
                        case 2001:
                            SportMusicInteratorService.this.e.a(3, (String) null);
                            break;
                        case 2002:
                            if (message.arg1 == -1) {
                                SportMusicInteratorService.this.e.aXK_(null);
                                break;
                            } else {
                                SportMusicInteratorService.this.e.aXK_(message.getData());
                                break;
                            }
                        case 2003:
                            SportMusicInteratorService.this.e.c(message.arg1);
                            break;
                        case 2004:
                            SportMusicInteratorService.this.e.a(1, (String) null);
                            break;
                        case 2005:
                            SportMusicInteratorService.this.e.a(2, (String) null);
                            break;
                    }
                    return;
                }
                LogUtil.h("Track_SportMusicInteratorService", "mController is null");
                return;
            }
            try {
                c();
            } catch (IllegalArgumentException e) {
                LogUtil.b("Track_SportMusicInteratorService", "handler ", e.getMessage());
            }
        }

        private void c() {
            if (SportMusicInteratorService.this.e != null) {
                SportMusicInteratorService.this.e.e();
            }
            Intent intent = new Intent(SportMusicInteratorService.this.getApplicationContext(), (Class<?>) SportMusicInteratorService.class);
            intent.setAction("action_stop_sport_music_interator_service");
            SportMusicInteratorService.this.getApplicationContext().stopService(intent);
        }
    }

    public static void e() {
        LogUtil.a("Track_SportMusicInteratorService", "startSportMusicService");
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportMusicInteratorService.class);
        intent.setAction("action_start_sport_music_interator_service");
        try {
            BaseApplication.getContext().startService(intent);
        } catch (IllegalArgumentException | IllegalStateException e) {
            LogUtil.b("Track_SportMusicInteratorService", "startSportMusicService ", LogAnonymous.b(e));
        }
    }

    public static void b() {
        LogUtil.a("Track_SportMusicInteratorService", "stopSportMusicService");
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) SportMusicInteratorService.class);
        intent.setAction("action_stop_sport_music_interator_service");
        try {
            BaseApplication.getContext().stopService(intent);
        } catch (IllegalArgumentException | IllegalStateException e) {
            LogUtil.b("Track_SportMusicInteratorService", "stopSportMusicService ", LogAnonymous.b(e));
        }
    }
}
