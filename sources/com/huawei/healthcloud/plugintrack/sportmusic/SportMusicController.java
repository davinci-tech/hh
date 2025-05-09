package com.huawei.healthcloud.plugintrack.sportmusic;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import com.android.mediacenter.localmusic.IMediaPlaybackService;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.ui.commonui.R$string;
import defpackage.gwg;
import defpackage.gwh;
import defpackage.gww;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SportMusicController {
    private static volatile SportMusicController e;

    /* renamed from: a, reason: collision with root package name */
    private MediaControllerCompat f3577a;
    private IBaseResponseCallback c;
    private IBaseResponseCallback m;
    private IMediaPlaybackService f = null;
    private List<MediaControllerCompat.Callback> b = new ArrayList();
    private boolean g = false;
    private boolean i = false;
    private boolean j = false;
    private MediaControllerCompat.Callback d = new MediaControllerCompat.Callback() { // from class: com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.3
        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) {
            LogUtil.a("Track_SportMusicController", "onMetadataChanged");
            SportMusicController.this.i();
            SportMusicController.this.b(new MusicControlCommand() { // from class: com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.3.1
                @Override // com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.MusicControlCommand
                public void dealMusicControlCallback(MediaControllerCompat.Callback callback) {
                    callback.onMetadataChanged(mediaMetadataCompat);
                }
            });
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
            LogUtil.a("Track_SportMusicController", "onPlaybackStateChanged");
            SportMusicController.this.b(new MusicControlCommand() { // from class: com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.3.2
                @Override // com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.MusicControlCommand
                public void dealMusicControlCallback(MediaControllerCompat.Callback callback) {
                    callback.onPlaybackStateChanged(playbackStateCompat);
                }
            });
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            LogUtil.a("Track_SportMusicController", "onSessionDestroyed");
            super.onSessionDestroyed();
            SportMusicController.this.g();
            SportMusicController.this.f();
            SportMusicController.this.f3577a = null;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionEvent(final String str, final Bundle bundle) {
            SportMusicController.this.b(new MusicControlCommand() { // from class: com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.3.3
                @Override // com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.MusicControlCommand
                public void dealMusicControlCallback(MediaControllerCompat.Callback callback) {
                    callback.onSessionEvent(str, bundle);
                }
            });
        }
    };
    private ServiceConnection h = new ServiceConnection() { // from class: com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MediaSessionCompat.Token mediaSessionToken;
            LogUtil.c("Track_SportMusicController", "onServiceConnected name:", componentName);
            SportMusicController.this.j = true;
            SportMusicController.this.f = IMediaPlaybackService.Stub.asInterface(iBinder);
            try {
                mediaSessionToken = SportMusicController.this.f.getMediaSessionToken();
            } catch (RemoteException unused) {
                LogUtil.b("Track_SportMusicController", "Make mediaControllerCompat remoteException");
            }
            if (mediaSessionToken != null) {
                SportMusicController.this.f3577a = new MediaControllerCompat(BaseApplication.getContext(), mediaSessionToken);
                if (SportMusicController.this.f3577a != null) {
                    SportMusicController.this.f3577a.registerCallback(SportMusicController.this.d);
                    SportMusicController.this.i = true;
                    if (SportMusicController.this.c != null) {
                        SportMusicController.this.c.d(0, null);
                        SportMusicController.this.c = null;
                    }
                    SportMusicController.this.i();
                    SportMusicController.this.b(new MusicControlCommand() { // from class: com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.2.4
                        @Override // com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.MusicControlCommand
                        public void dealMusicControlCallback(MediaControllerCompat.Callback callback) {
                            if (SportMusicController.this.f3577a != null) {
                                MediaMetadataCompat metadata = SportMusicController.this.f3577a.getMetadata();
                                if (metadata != null) {
                                    callback.onMetadataChanged(metadata);
                                }
                                PlaybackStateCompat playbackState = SportMusicController.this.f3577a.getPlaybackState();
                                if (playbackState != null) {
                                    callback.onPlaybackStateChanged(playbackState);
                                }
                            }
                        }
                    });
                    return;
                }
                return;
            }
            ReleaseLogUtil.d("Track_SportMusicController", "Empty token!");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (SportMusicController.this.h != null && SportMusicController.this.j) {
                BaseApplication.getContext().unbindService(SportMusicController.this.h);
                SportMusicController.this.i = false;
                SportMusicController.this.j = false;
                ReleaseLogUtil.e("Track_SportMusicController", "unbindService");
            }
            if (SportMusicController.this.m != null) {
                SportMusicController.this.m.d(0, null);
            }
            LogUtil.a("Track_SportMusicController", "onServiceDisconnected name:", componentName);
        }
    };

    interface MusicControlCommand {
        void dealMusicControlCallback(MediaControllerCompat.Callback callback);
    }

    private SportMusicController() {
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        if (!CommonUtil.v(gwh.s)) {
            ReleaseLogUtil.c("Track_SportMusicController", "isMusicAppSignature is not Authority.");
            return;
        }
        Intent intent = new Intent();
        this.c = iBaseResponseCallback;
        intent.setComponent(new ComponentName(gwh.s, "com.android.mediacenter.localmusic.MediaPlaybackService"));
        ReleaseLogUtil.e("Track_SportMusicController", "bind ret = ", Boolean.valueOf(BaseApplication.getContext().bindService(intent, this.h, 1)));
    }

    public static SportMusicController a() {
        if (e == null) {
            synchronized (SportMusicController.class) {
                if (e == null) {
                    e = new SportMusicController();
                }
            }
        }
        return e;
    }

    public void b(int i) {
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        if (gwg.i(BaseApplication.getContext()) && CommonUtil.bd() && gwg.a(BaseApplication.getContext())) {
            if (!CommonUtil.v(gwh.s)) {
                ReleaseLogUtil.c("Track_SportMusicController", "isMusicAppSignature is not Authority.");
                return;
            }
            if (gwwVar.f(i) != 1) {
                return;
            }
            if (j() != null && j().getState() == 3) {
                a(1, (String) null);
            }
            String b = gwwVar.b(i);
            String a2 = gwwVar.a(i);
            if (TextUtils.isEmpty(a2)) {
                a2 = "4";
            }
            LogUtil.a("Track_SportMusicController", "playListType ", a2);
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwmediacenter://com.android.mediacenter/playMusicEx?contentType=" + a2 + "&contentInfo=" + b + "&showPlayer=0&timeOut=5000&showProgressDialog=0&from=" + BaseApplication.getAppPackage()));
            intent.setPackage(gwh.s);
            intent.putExtra("tag", BaseApplication.getContext().getPackageName());
            try {
                if (BaseApplication.getActivity() != null) {
                    BaseApplication.getActivity().startActivity(intent);
                }
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("Track_SportMusicController", "music play activity not found, check");
            }
        }
    }

    public void c(MediaControllerCompat.Callback callback) {
        if (callback != null && !this.b.contains(callback)) {
            this.b.add(callback);
        }
        MediaControllerCompat mediaControllerCompat = this.f3577a;
        if (mediaControllerCompat != null) {
            MediaMetadataCompat metadata = mediaControllerCompat.getMetadata();
            if (metadata != null && callback != null) {
                callback.onMetadataChanged(metadata);
            }
            PlaybackStateCompat playbackState = this.f3577a.getPlaybackState();
            if (playbackState == null || callback == null) {
                return;
            }
            callback.onPlaybackStateChanged(playbackState);
        }
    }

    public void d(MediaControllerCompat.Callback callback) {
        for (int i = 0; i < this.b.size(); i++) {
            if (this.b.get(i) == callback) {
                this.b.remove(i);
                return;
            }
        }
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        b(iBaseResponseCallback);
    }

    public void h() {
        b((IBaseResponseCallback) null);
    }

    private void b(IBaseResponseCallback iBaseResponseCallback) {
        this.m = iBaseResponseCallback;
    }

    public void i() {
        LogUtil.a("Track_SportMusicController", "getSongCollectState mController ", this.f3577a);
        MediaControllerCompat mediaControllerCompat = this.f3577a;
        if (mediaControllerCompat == null || mediaControllerCompat.getTransportControls() == null) {
            return;
        }
        this.f3577a.getTransportControls().sendCustomAction("com.huawei.music.action.LIKE_STATUS", (Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MusicControlCommand musicControlCommand) {
        for (MediaControllerCompat.Callback callback : this.b) {
            if (callback != null) {
                musicControlCommand.dealMusicControlCallback(callback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        b(new MusicControlCommand() { // from class: com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.4
            @Override // com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController.MusicControlCommand
            public void dealMusicControlCallback(MediaControllerCompat.Callback callback) {
                callback.onPlaybackStateChanged(null);
            }
        });
    }

    public MediaMetadataCompat c() {
        MediaControllerCompat mediaControllerCompat = this.f3577a;
        if (mediaControllerCompat != null) {
            return mediaControllerCompat.getMetadata();
        }
        return null;
    }

    public PlaybackStateCompat j() {
        MediaControllerCompat mediaControllerCompat = this.f3577a;
        if (mediaControllerCompat != null) {
            return mediaControllerCompat.getPlaybackState();
        }
        return null;
    }

    public void e() {
        LogUtil.a("Track_SportMusicController", "destroyController");
        g();
        if (this.h != null && this.j) {
            BaseApplication.getContext().unbindService(this.h);
            this.j = false;
            this.i = false;
            LogUtil.a("Track_SportMusicController", "unbindService");
        }
        this.f = null;
    }

    public boolean d() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        MediaControllerCompat mediaControllerCompat = this.f3577a;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(this.d);
        }
    }

    public void a(int i, String str) {
        if (this.f == null) {
            ReleaseLogUtil.d("Track_SportMusicController", "processMusicControlMsg mMediaPlayerCallbackService is null");
        } else {
            d(i, str);
        }
    }

    private void d(int i, String str) {
        if (i == 0) {
            try {
                this.f.stop();
                this.g = false;
                return;
            } catch (RemoteException e2) {
                LogUtil.b("Track_SportMusicController", "processMusicControlMsg STOP_MUSIC ", LogAnonymous.b((Throwable) e2));
                return;
            }
        }
        if (i == 1) {
            try {
                ReleaseLogUtil.e("Track_SportMusicController", "processMusicControlMsg pause music");
                this.f.pause();
                return;
            } catch (RemoteException e3) {
                LogUtil.b("Track_SportMusicController", "processMusicControlMsg STOP_MUSIC ", LogAnonymous.b((Throwable) e3));
                return;
            }
        }
        if (i == 2) {
            try {
                i();
                ReleaseLogUtil.e("Track_SportMusicController", "processMusicControlMsg play music");
                this.f.play();
                return;
            } catch (RemoteException e4) {
                LogUtil.b("Track_SportMusicController", "processMusicControlMsg PLAY_MUSIC ", LogAnonymous.b((Throwable) e4));
                return;
            }
        }
        if (i == 3) {
            try {
                this.f.next();
                return;
            } catch (RemoteException e5) {
                LogUtil.b("Track_SportMusicController", "processMusicControlMsg NEXT_SONG ", LogAnonymous.b((Throwable) e5));
                return;
            }
        }
        b(i, str);
    }

    private void b(int i, String str) {
        MediaControllerCompat mediaControllerCompat;
        if (i == 4) {
            try {
                this.f.prev();
                return;
            } catch (RemoteException e2) {
                LogUtil.b("Track_SportMusicController", "processMusicControlMsg FORWARD_SONG ", LogAnonymous.b((Throwable) e2));
                return;
            }
        }
        if (i != 5 || (mediaControllerCompat = this.f3577a) == null || mediaControllerCompat.getTransportControls() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("fromPage", "HealthPlayer");
        this.f3577a.getTransportControls().sendCustomAction(str, bundle);
    }

    public void c(int i) {
        LogUtil.a("Track_SportMusicController", "pushStepRate :", Integer.valueOf(i));
        IMediaPlaybackService iMediaPlaybackService = this.f;
        if (iMediaPlaybackService != null) {
            try {
                iMediaPlaybackService.stepFrequency(i);
                return;
            } catch (RemoteException e2) {
                LogUtil.b("Track_SportMusicController", "pushStepRate remoteException ", e2.getMessage());
                return;
            }
        }
        ReleaseLogUtil.d("Track_SportMusicController", "pushStepRate mMediaPlayerCallbackService is NULL");
    }

    public void aXK_(Bundle bundle) {
        if (bundle != null) {
            LogUtil.c("Track_SportMusicController", "duration = ", bundle.get("duration"), " steprate = ", bundle.get("stepRate"), " totalSteps = ", bundle.get(MedalConstants.EVENT_STEPS));
        }
        IMediaPlaybackService iMediaPlaybackService = this.f;
        if (iMediaPlaybackService != null) {
            try {
                iMediaPlaybackService.stopRunning(bundle);
                ReleaseLogUtil.e("Track_SportMusicController", "send stop command to music");
                return;
            } catch (RemoteException e2) {
                LogUtil.b("Track_SportMusicController", "notifyMusicToStop remoteException ", e2.getMessage());
                return;
            }
        }
        ReleaseLogUtil.d("Track_SportMusicController", "notifyMusicToStop mMediaPlayerCallbackService is NULL");
    }

    public void d(int i, int i2, boolean z) {
        Uri parse = Uri.parse(e(i, i2, z));
        if (!CommonUtil.v(gwh.s)) {
            ReleaseLogUtil.c("Track_SportMusicController", "isMusicAppSignature is not Authority.");
            return;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, parse);
        intent.setFlags(268599296);
        intent.setPackage(gwh.s);
        intent.putExtra("tag", BaseApplication.getContext().getPackageName());
        Context activity = BaseApplication.getActivity();
        try {
            String str = gwh.s;
            if (activity == null) {
                activity = BaseApplication.getContext();
            }
            nsn.cLM_(intent, str, activity, nsf.h(R$string.IDS_hwh_motiontrack_music));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_SportMusicController", "music running list activity not found, check");
        } catch (AndroidRuntimeException e2) {
            LogUtil.b("Track_SportMusicController", "gotoMusicList ", LogAnonymous.b((Throwable) e2));
        } catch (SecurityException e3) {
            LogUtil.b("Track_SportMusicController", "gotoMusicList securityException", LogAnonymous.b((Throwable) e3));
        }
    }

    private static boolean e(Context context) {
        IntentFilter intentFilter;
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwmediacenter://com.android.mediacenter/showrunplaylistnew?pver=80002300&portal=qq&from=com.huawei.health"));
        intent.setFlags(268599296);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.h("Track_SportMusicController", "packageManager is null");
            return false;
        }
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 64)) {
            if (resolveInfo != null && (intentFilter = resolveInfo.filter) != null && intentFilter.hasDataPath("/showrunplaylistnew")) {
                return true;
            }
        }
        return false;
    }

    public String e(int i, int i2, boolean z) {
        boolean e2 = e(BaseApplication.getContext());
        LogUtil.a("Track_SportMusicController", "music isSupportNewPath ", Boolean.valueOf(e2));
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        String a2 = gwwVar.a(i2);
        if (TextUtils.isEmpty(a2)) {
            a2 = "4";
        }
        String b = gwwVar.b(i2);
        LogUtil.a("Track_SportMusicController", "getSportMusicUrl playListType  ", a2, " isNeedPlay ", Boolean.valueOf(z));
        return (e2 ? "hwmediacenter://com.android.mediacenter/showrunplaylistnew?pver=80002300&portal=qq&from=com.huawei.health" : "hwmediacenter://com.android.mediacenter/showrunplaylist?pver=80002300&portal=qq&from=com.huawei.health") + a(i, i2) + "&nopage=1&needback=1&playlistId=" + b + d(i2, a2, z);
    }

    private String a(int i, int i2) {
        if (i2 == 264) {
            return i == 2 ? "&channelid=1113" : i == 1 ? "&channelid=1109" : "&channelid=1104";
        }
        if (i2 != 265) {
            if (i2 == 283) {
                return i == 2 ? "&channelid=1116" : i == 1 ? "&channelid=1111" : "&channelid=1107";
            }
            switch (i2) {
                case 257:
                    return i == 2 ? "&channelid=1114" : i == 1 ? "&channelid=1117" : "&channelid=1105";
                case 258:
                    return i == 2 ? "&channelid=1112" : i == 1 ? "&channelid=1108" : "&channelid=1103";
                case 259:
                    break;
                default:
                    LogUtil.h("Track_SportMusicController", "getNotSelectMusicUrl sportType is ", Integer.valueOf(i2));
            }
        }
        return i == 2 ? "&channelid=1115" : i == 1 ? "&channelid=1110" : "&channelid=1106";
    }

    private String d(int i, String str, boolean z) {
        String str2 = "&playlistType=" + str + "&sceneName=";
        if (i == 137) {
            str2 = str2 + "yoga&needPlay=";
        } else if (i == 283) {
            str2 = str2 + "skipRope&needPlay=";
        } else if (i == 10001) {
            str2 = str2 + "fitness&needPlay=";
        } else if (i == 264) {
            str2 = str2 + "indoorRun&needPlay=";
        } else {
            if (i != 265) {
                switch (i) {
                    case 257:
                        str2 = str2 + "walk&needPlay=";
                        break;
                    case 258:
                        str2 = str2 + "outdoorRun&needPlay=";
                        break;
                    case 259:
                        break;
                    default:
                        LogUtil.h("Track_SportMusicController", "getNotSelectMusicUrl sportType is ", Integer.valueOf(i));
                        break;
                }
            }
            str2 = str2 + "ride&needPlay=";
        }
        return str2 + z;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean b() {
        return this.g;
    }
}
