package defpackage;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.MusicInfo;
import com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface;
import com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.HealthMediaCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class khd extends khb {
    private static khd b;
    private AudioManager g;
    private MediaDescription m;
    private HealthMediaCallback o;
    private MediaSessionManager q;
    private ControlInterface.MusicChangeCallback u;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14364a = new Object();
    private static final Object e = new Object();
    private static List<MediaController> c = new ArrayList(16);
    private List<String> k = Arrays.asList("com.huawei.camera", "tv.danmaku.bili", "com.android.server.telecom", "com.iflytek.readassistant", "com.huawei.hicar", "com.autonavi.minimap", "com.duowan.kiwi", "com.youku.phone", "com.ss.android.article.video");
    private List<MediaController.Callback> i = new ArrayList(16);
    private HashMap<String, MediaSession.Token> j = new HashMap<>();
    private List<MediaController> h = new ArrayList(16);
    private String x = "";
    private MediaController r = null;
    private int s = 0;
    private int p = 0;
    private HashMap<String, String> d = new HashMap<>();
    private boolean l = false;
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: khd.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("MusicController", "mBroadcastReceiver, intent is null");
                return;
            }
            LogUtil.a("MusicController", "mBroadcastReceiver intent : " + intent.getAction());
            if (Constants.VOLUME_CHANGED_ACTION.equals(intent.getAction())) {
                try {
                    if (khd.this.g == null) {
                        return;
                    }
                    int streamVolume = khd.this.g.getStreamVolume(3);
                    LogUtil.a("MusicController", "volume：", Integer.valueOf(streamVolume));
                    if (streamVolume != khd.this.p) {
                        if (khd.this.u != null) {
                            khd.this.u.onMusicChanged();
                        }
                        khd.this.p = streamVolume;
                    }
                } catch (NullPointerException unused) {
                    LogUtil.b("MusicController", "NullPointerException IAudioService");
                }
            }
        }
    };
    private MediaSessionManager.OnActiveSessionsChangedListener t = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: khd.2
        @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
        public void onActiveSessionsChanged(List<MediaController> list) {
            LogUtil.a("MusicController", "onActiveSessionsChanged start.");
            khd.this.h(list);
            khd.this.g(list);
            khd.this.f(list);
            if (list != null) {
                synchronized (khd.f14364a) {
                    List unused = khd.c = list;
                }
                LogUtil.a("MusicController", "onActiveSessionsChanged activeSessionsChangedHandle");
                khd.this.c(list);
                return;
            }
            LogUtil.a("MusicController", "onActiveSessionsChanged controllerList is null");
            khd.this.i();
            khd.this.e(true);
        }
    };
    private Handler n = new Handler(Looper.getMainLooper()) { // from class: khd.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 20) {
                LogUtil.a("MusicController", "handleMessage no music time out !");
                if (khd.this.q != null) {
                    try {
                        khd.this.q.removeOnActiveSessionsChangedListener(khd.this.t);
                    } catch (SecurityException unused) {
                        LogUtil.b("MusicController", "SecurityException Missing permission to control media");
                    }
                }
                if (khd.this.u != null) {
                    khd.this.u.onMusicDiedTimeOut();
                }
                khd.this.unRegisterMusicCallback();
                return;
            }
            if (i == 21) {
                LogUtil.a("MusicController", "MSG_ACTION_REGISTER_MUSIC_CONTROL");
                if (khd.this.q != null) {
                    try {
                        khd.this.q.addOnActiveSessionsChangedListener(khd.this.t, new ComponentName(BaseApplication.getContext().getPackageName(), "com.huawei.bone.ui.setting.NotificationPushListener"));
                        return;
                    } catch (SecurityException unused2) {
                        LogUtil.b("MusicController", "SecurityException Missing permission to control media");
                        return;
                    }
                }
                return;
            }
            if (i == 30) {
                if (khd.this.u != null) {
                    LogUtil.a("MusicController", "MSG_ACTION_MUSIC_UPDATE");
                    khd.this.u.onMusicChanged();
                    return;
                }
                return;
            }
            LogUtil.h("MusicController", "mHandler default");
        }
    };

    public boolean b() {
        return this.l;
    }

    public void e(boolean z) {
        this.l = z;
    }

    private HealthMediaCallback bNJ_(MediaController mediaController) {
        return new HealthMediaCallback(mediaController.getPackageName()) { // from class: khd.4
            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                super.onSessionDestroyed();
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(String str, Bundle bundle) {
                super.onSessionEvent(str, bundle);
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                super.onPlaybackStateChanged(playbackState);
                khd.this.bNI_(getPackageName(), playbackState);
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                super.onMetadataChanged(mediaMetadata);
                khd.this.bNH_(getPackageName(), mediaMetadata);
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(List<MediaSession.QueueItem> list) {
                super.onQueueChanged(list);
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(CharSequence charSequence) {
                super.onQueueTitleChanged(charSequence);
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(Bundle bundle) {
                super.onExtrasChanged(bundle);
            }

            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(MediaController.PlaybackInfo playbackInfo) {
                super.onAudioInfoChanged(playbackInfo);
            }
        };
    }

    private khd() {
        c();
        h();
    }

    private void a(CharSequence charSequence) {
        if (!b() || charSequence == null || TextUtils.equals("", charSequence)) {
            return;
        }
        LogUtil.a("MusicController", "mediaDescriptionCompat.getTitle() is not blank string");
        e(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bNI_(String str, PlaybackState playbackState) {
        LogUtil.h("MusicController", "changeState packageName:", str);
        if (this.k.contains(str)) {
            LogUtil.h("MusicController", "changeState Filtered Packages.");
            return;
        }
        if (playbackState == null) {
            LogUtil.h("MusicController", "onPlaybackStateChanged state is null.");
            return;
        }
        ReleaseLogUtil.e("R_MusicController", "onPlaybackStateChanged state :", playbackState.toString(), ",last state :", Integer.valueOf(this.s));
        int state = playbackState.getState();
        int intValue = this.d.containsKey(str) ? Integer.valueOf(this.d.get(str)).intValue() : -1;
        ReleaseLogUtil.e("R_MusicController", "lastState :", Integer.valueOf(intValue), ",stateCode :", Integer.valueOf(state));
        if (intValue != state) {
            this.d.put(str, String.valueOf(state));
            if (state == 3 && !str.equals(this.r.getPackageName())) {
                LogUtil.a("MusicController", "changeState playing changeMediaSequence");
                c(str);
            }
            ControlInterface.MusicChangeCallback musicChangeCallback = this.u;
            if (musicChangeCallback != null) {
                musicChangeCallback.onMusicChanged();
                return;
            } else {
                LogUtil.a("MusicController", "mMusicChangeCallback == null");
                return;
            }
        }
        LogUtil.a("MusicController", "lastState equals stateCode");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bNH_(String str, MediaMetadata mediaMetadata) {
        LogUtil.h("MusicController", "onMetadataChanged packageName:", str);
        if (this.k.contains(str)) {
            LogUtil.h("MusicController", "onMetadataChanged Filtered Packages.");
            return;
        }
        try {
            if (mediaMetadata == null) {
                ReleaseLogUtil.e("R_MusicController", "onMetadataChanged metadata == null");
                return;
            }
            MediaDescription description = mediaMetadata.getDescription();
            if (description != null) {
                bNM_(description);
                int intValue = this.d.containsKey(str) ? Integer.valueOf(this.d.get(str)).intValue() : -1;
                LogUtil.a("MusicController", "changeMetadata lastState:", Integer.valueOf(intValue));
                if (!description.equals(this.m) && !str.equals(this.r.getPackageName()) && intValue == 3) {
                    LogUtil.a("MusicController", "changeMetadata changeMediaSequence");
                    c(str);
                    this.m = description;
                }
                a(description.getTitle());
                ControlInterface.MusicChangeCallback musicChangeCallback = this.u;
                if (musicChangeCallback != null) {
                    musicChangeCallback.onMusicChanged();
                }
            }
        } catch (BadParcelableException e2) {
            ReleaseLogUtil.e("R_MusicController", "changeMetadata print metadata");
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("MusicController", "BadParcelableException :" + ExceptionUtils.d(e2));
            LogUtil.a("MusicController", "changeMetadata e: ", ExceptionUtils.d(e2));
        } catch (RuntimeException e3) {
            OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("MusicController", "changeMetadata RuntimeException :" + ExceptionUtils.d(e3));
            LogUtil.a("MusicController", "changeMetadata RuntimeException e: ", ExceptionUtils.d(e3));
        }
    }

    public static khd d() {
        khd khdVar;
        synchronized (e) {
            if (b == null) {
                LogUtil.a("MusicController", "getInstance() instance is null ");
                b = new khd();
            }
            khdVar = b;
        }
        return khdVar;
    }

    private void c() {
        LogUtil.a("MusicController", "enter initMediaSession");
        Object systemService = BaseApplication.getContext().getSystemService("media_session");
        List<MediaController> list = null;
        if (systemService instanceof MediaSessionManager) {
            MediaSessionManager mediaSessionManager = (MediaSessionManager) systemService;
            this.q = mediaSessionManager;
            try {
                list = mediaSessionManager.getActiveSessions(new ComponentName(BaseApplication.getContext().getPackageName(), "com.huawei.bone.ui.setting.NotificationPushListener"));
                h(list);
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    this.q.addOnActiveSessionsChangedListener(this.t, new ComponentName(BaseApplication.getContext().getPackageName(), "com.huawei.bone.ui.setting.NotificationPushListener"));
                } else {
                    this.n.sendEmptyMessage(21);
                }
            } catch (SecurityException unused) {
                LogUtil.b("MusicController", "SecurityException Missing permission to control media");
            }
        }
        if (list != null) {
            LogUtil.a("MusicController", "controllers.size:", Integer.valueOf(list.size()));
            if (list.size() > 0) {
                g();
                MediaController mediaController = list.get(0);
                this.r = mediaController;
                this.x = mediaController.getPackageName();
                HealthMediaCallback bNJ_ = bNJ_(this.r);
                this.o = bNJ_;
                this.r.registerCallback(bNJ_, new Handler(Looper.getMainLooper()));
                if (this.r.getPlaybackInfo() != null) {
                    LogUtil.a("MusicController", "Maxvolume:", Integer.valueOf(this.r.getPlaybackInfo().getMaxVolume()), "currentvolume : ", Integer.valueOf(this.r.getPlaybackInfo().getCurrentVolume()));
                }
                if (this.r.getPlaybackState() != null) {
                    this.o.onPlaybackStateChanged(this.r.getPlaybackState());
                    LogUtil.a("MusicController", "playbackState:", this.r.getPlaybackState());
                }
                MediaMetadata metadata = this.r.getMetadata();
                if (metadata != null) {
                    this.o.onMetadataChanged(metadata);
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.a("MusicController", "controllers is null");
    }

    private void bNM_(MediaDescription mediaDescription) {
        try {
            if (mediaDescription != null) {
                CharSequence subtitle = mediaDescription.getSubtitle();
                if (subtitle != null) {
                    LogUtil.a("MusicController", "subTitle:", subtitle);
                }
                CharSequence title = mediaDescription.getTitle();
                if (title != null) {
                    LogUtil.a("MusicController", "title:", title);
                    return;
                }
                return;
            }
            LogUtil.a("MusicController", "mediaDescription is null");
        } catch (BadParcelableException e2) {
            OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "BadParcelableException");
            LogUtil.a("MusicController", "printMediaDescription e: ", ExceptionUtils.d(e2));
        } catch (RuntimeException e3) {
            OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "printMediaDescription RuntimeException:" + ExceptionUtils.d(e3));
            LogUtil.a("MusicController", "printMediaDescription RuntimeException e: ", ExceptionUtils.d(e3));
        }
    }

    private void h() {
        LogUtil.a("MusicController", "initVolume");
        Object systemService = BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO);
        if (systemService instanceof AudioManager) {
            this.g = (AudioManager) systemService;
        }
        LogUtil.a("MusicController", "registerVolumeChangeReceiver");
        BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.f, new IntentFilter(Constants.VOLUME_CHANGED_ACTION), LocalBroadcast.c, null);
    }

    private void f() {
        synchronized (f14364a) {
            if (c != null && this.r != null) {
                ArrayList arrayList = new ArrayList(16);
                for (MediaController mediaController : c) {
                    HealthMediaCallback bNJ_ = bNJ_(mediaController);
                    String packageName = mediaController.getPackageName();
                    arrayList.add(packageName);
                    if (mediaController != null && !bNK_(mediaController)) {
                        this.j.put(packageName, mediaController.getSessionToken());
                        mediaController.registerCallback(bNJ_, new Handler(Looper.getMainLooper()));
                        this.i.add(bNJ_);
                        this.h.add(mediaController);
                    }
                }
                a(arrayList);
            }
        }
    }

    private boolean bNK_(MediaController mediaController) {
        String packageName = mediaController.getPackageName();
        return this.j.containsKey(packageName) && this.j.get(packageName).equals(mediaController.getSessionToken());
    }

    private void a(List<String> list) {
        Iterator<Map.Entry<String, MediaSession.Token>> it = this.j.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (!list.contains(key)) {
                LogUtil.h("MusicController", "deleteUselessPacakege, packageName：", key);
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        List<MediaController> list = this.h;
        if (list == null || this.i == null || list.size() != this.i.size()) {
            return;
        }
        LogUtil.a("MusicController", "unRegisterCallbackList enter mControllerList.size :", Integer.valueOf(this.h.size()));
        for (int i = 0; i < this.h.size(); i++) {
            if (this.h.get(i) != null && this.i.get(i) != null) {
                this.h.get(i).unregisterCallback(this.i.get(i));
            }
        }
        this.h.clear();
        this.i.clear();
        this.j.clear();
    }

    private void c(String str) {
        boolean z;
        LogUtil.a("MusicController", "enter changeMediaSequence packageName", str);
        int i = 0;
        while (true) {
            if (i >= c.size()) {
                z = false;
                break;
            }
            if (c.get(i) != null) {
                LogUtil.a("MusicController", "PackageName: ", c.get(i).getPackageName());
                if (str.equals(c.get(i).getPackageName())) {
                    z = true;
                    break;
                }
            }
            i++;
        }
        if (i != 0 && z) {
            MediaController mediaController = c.get(i);
            if (this.r.getSessionToken().equals(mediaController.getSessionToken())) {
                return;
            }
            c.remove(mediaController);
            c.add(0, mediaController);
            LogUtil.a("MusicController", "replace");
        }
        LogUtil.a("MusicController", "changeMediaSequence activeSessionsChangedHandle");
        c(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(List<MediaController> list) {
        PlaybackState playbackState;
        if (list == null || c == null || list.size() <= 1 || c.size() == 0 || list.size() > c.size()) {
            return;
        }
        MediaController mediaController = list.get(0);
        MediaController mediaController2 = c.get(0);
        if (mediaController == null || (playbackState = mediaController.getPlaybackState()) == null || playbackState.getState() != 3) {
            String packageName = mediaController2 != null ? mediaController2.getPackageName() : null;
            if (packageName == null) {
                return;
            }
            int i = -1;
            for (int i2 = 0; i2 < list.size(); i2++) {
                MediaController mediaController3 = list.get(i2);
                if (mediaController3 != null && mediaController3.getPackageName() != null && packageName.equals(mediaController3.getPackageName())) {
                    i = i2;
                }
            }
            if (i == 0 || i == -1) {
                return;
            }
            MediaController mediaController4 = list.get(i);
            LogUtil.a("MusicController", "keepPlayOrder replace :", Integer.valueOf(i));
            list.remove(mediaController4);
            list.add(0, mediaController4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(List<MediaController> list) {
        PlaybackState playbackState;
        if (list == null || list.size() == 0) {
            ReleaseLogUtil.d("R_MusicController", "mediaList is empty");
            return;
        }
        MediaController mediaController = list.get(0);
        boolean z = (mediaController == null || (playbackState = mediaController.getPlaybackState()) == null || playbackState.getState() != 3) ? false : true;
        LogUtil.a("MusicController", "OnActiveSessionsChangedListener isMusicActive", Boolean.valueOf(z));
        List<MediaController> list2 = c;
        int size = list2 != null ? list2.size() : 0;
        int size2 = list.size();
        if (z || size2 <= size) {
            return;
        }
        MediaController mediaController2 = list.get(size2 - 1);
        LogUtil.a("MusicController", "OnActiveSessionsChangedListener replace");
        list.remove(mediaController2);
        list.add(0, mediaController2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<MediaController> list) {
        if (list == null) {
            return;
        }
        f();
        for (MediaController mediaController : list) {
            if (mediaController != null) {
                LogUtil.a("MusicController", "package name: ", mediaController.getPackageName());
            }
        }
        LogUtil.a("MusicController", "onActiveSessionsChanged size:", Integer.valueOf(list.size()));
        if (this.r != null) {
            if (this.n == null) {
                return;
            }
            LogUtil.a("MusicController", "onActiveSessionsChanged mMediaController is not null");
            if (list.size() > 0) {
                b(list);
            } else {
                this.x = "";
                this.n.removeMessages(20);
                this.n.sendEmptyMessageDelayed(20, 120000L);
                e(true);
                ControlInterface.MusicChangeCallback musicChangeCallback = this.u;
                if (musicChangeCallback != null) {
                    musicChangeCallback.onMusicChanged();
                }
            }
        } else if (list.size() > 0) {
            ReleaseLogUtil.e("R_MusicController", "onActiveSessionsChanged mMediaController is null");
            g();
            MediaController mediaController2 = list.get(0);
            this.r = mediaController2;
            HealthMediaCallback bNJ_ = bNJ_(mediaController2);
            this.o = bNJ_;
            this.r.registerCallback(bNJ_, new Handler(Looper.getMainLooper()));
            this.x = this.r.getPackageName();
            LogUtil.a("MusicController", "onActiveSessionsChanged packagename:", list.get(0).getPackageName());
        } else {
            LogUtil.h("MusicController", "onActiveSessionsChanged mMediaController is null");
        }
        LogUtil.a("MusicController", "mIsMusicAppDied:", Boolean.valueOf(b()));
    }

    private void b(List<MediaController> list) {
        if (list == null) {
            return;
        }
        this.n.removeMessages(20);
        e(false);
        if (i(list)) {
            this.r.unregisterCallback(this.o);
            MediaController mediaController = list.get(0);
            this.r = mediaController;
            HealthMediaCallback bNJ_ = bNJ_(mediaController);
            this.o = bNJ_;
            this.r.registerCallback(bNJ_, new Handler(Looper.getMainLooper()));
            this.x = this.r.getPackageName();
            ReleaseLogUtil.e("R_MusicController", "changeMediaController.");
            ControlInterface.MusicChangeCallback musicChangeCallback = this.u;
            if (musicChangeCallback != null) {
                musicChangeCallback.onMusicChanged();
            }
        }
    }

    private boolean i(List<MediaController> list) {
        MediaController mediaController = list.get(0);
        if (mediaController == null) {
            return false;
        }
        if (!TextUtils.equals(this.x, mediaController.getPackageName())) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            LogUtil.a("MusicController", "mMediaController.getTag(): ", this.r.getTag(), "; firstController.getTag(): ", mediaController.getTag());
            return !TextUtils.equals(this.r.getTag(), mediaController.getTag());
        }
        LogUtil.a("MusicController", "mMediaSessionPackageName = ", this.x, "; firstController.getPackageName() = ", mediaController.getPackageName());
        if (d(list) > 1) {
            return (TextUtils.equals(this.x, "com.huawei.music") || TextUtils.equals(this.x, "com.android.mediacenter")) && TextUtils.equals(this.x, mediaController.getPackageName());
        }
        return false;
    }

    private int d(List<MediaController> list) {
        int i = 0;
        for (MediaController mediaController : list) {
            if (TextUtils.equals(mediaController.getPackageName(), "com.huawei.music") || TextUtils.equals(mediaController.getPackageName(), "com.android.mediacenter")) {
                i++;
            }
        }
        LogUtil.a("MusicController", "countSameController = ", Integer.valueOf(i));
        return i;
    }

    @Override // defpackage.khb, com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public MusicInfo getMusicInfo() {
        MusicInfo musicInfo = new MusicInfo();
        if (b()) {
            this.s = 0;
            LogUtil.a("MusicController", "getMusicInfo mIsMusicAppDied is STATE_STOPPED");
            musicInfo.setPlayState(1);
        } else {
            MediaController mediaController = this.r;
            if (mediaController != null) {
                try {
                    MediaMetadata metadata = mediaController.getMetadata();
                    MediaController.PlaybackInfo playbackInfo = this.r.getPlaybackInfo();
                    LogUtil.a("MusicController", "controller package: ", this.r.getPackageName());
                    bNN_(musicInfo, metadata);
                    if (playbackInfo != null) {
                        musicInfo.setMaxVolume(playbackInfo.getMaxVolume());
                        musicInfo.setCurrentVolume(playbackInfo.getCurrentVolume());
                    } else {
                        musicInfo.setMaxVolume(0);
                        musicInfo.setCurrentVolume(0);
                    }
                    PlaybackState playbackState = this.r.getPlaybackState();
                    if (playbackState != null) {
                        musicInfo.setPlayState(playbackState.getState());
                        ReleaseLogUtil.e("R_MusicController", "getMusicInfo getPlaybackState().getState: ", Integer.valueOf(playbackState.getState()));
                    } else {
                        musicInfo.setPlayState(0);
                        LogUtil.a("MusicController", "musicInfo.setPlayState(0): ", Integer.valueOf(musicInfo.getPlayState()));
                    }
                } catch (IllegalArgumentException e2) {
                    OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "getMusicInfo IllegalArgumentException:" + ExceptionUtils.d(e2));
                    LogUtil.a("MusicController", "getMusicInfo IllegalArgumentException : ", ExceptionUtils.d(e2));
                } catch (RuntimeException e3) {
                    OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "getMusicInfo RuntimeException:" + ExceptionUtils.d(e3));
                    LogUtil.a("MusicController", "getMusicInfo RuntimeException : ", ExceptionUtils.d(e3));
                }
            } else {
                ReleaseLogUtil.d("R_MusicController", "getMusicInfo mMediaController is null");
            }
        }
        c(musicInfo);
        return musicInfo;
    }

    private void bNN_(MusicInfo musicInfo, MediaMetadata mediaMetadata) {
        LogUtil.a("MusicController", "enter setMusicInfo: ", Boolean.valueOf(Objects.isNull(mediaMetadata)));
        try {
            if (mediaMetadata != null) {
                MediaDescription description = mediaMetadata.getDescription();
                bNM_(description);
                if (description != null) {
                    CharSequence subtitle = description.getSubtitle();
                    if (subtitle != null) {
                        musicInfo.setSingerName(subtitle.toString());
                    }
                    CharSequence title = description.getTitle();
                    if (title != null) {
                        musicInfo.setSongName(title.toString());
                        return;
                    }
                    return;
                }
                return;
            }
            musicInfo.setSingerName("");
            musicInfo.setSongName("");
        } catch (RuntimeException e2) {
            OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "setMusicInfo RuntimeException:" + ExceptionUtils.d(e2));
            LogUtil.a("MusicController", "setMusicInfo RuntimeException e: ", ExceptionUtils.d(e2));
        }
    }

    private void c(MusicInfo musicInfo) {
        if (musicInfo == null || this.r == null) {
            return;
        }
        LogUtil.a("MusicController", "correctStatus getPlayState()", Integer.valueOf(musicInfo.getPlayState()), "mAudioManager.isMusicActive()", Boolean.valueOf(this.g.isMusicActive()));
        this.s = musicInfo.getPlayState();
        if (b() && !this.g.isMusicActive()) {
            musicInfo.setPlayState(0);
            this.s = musicInfo.getPlayState();
            ReleaseLogUtil.e("R_MusicController", "isIsMusicAppDied,real setPlayState is STATE_NONE");
            return;
        }
        if (bNL_(musicInfo, this.r) || musicInfo.getPlayState() == 1 || musicInfo.getPlayState() == 2 || musicInfo.getPlayState() == 3) {
            return;
        }
        if (this.g.isMusicActive()) {
            if (musicInfo.getPlayState() == 0) {
                LogUtil.a("MusicController", "real setPlayState is STATE_NONE, set STATE_PAUSED");
                musicInfo.setPlayState(2);
                this.s = musicInfo.getPlayState();
                return;
            } else {
                LogUtil.a("MusicController", "correctStatus setPlayState is STATE_PLAYING");
                musicInfo.setPlayState(3);
                this.s = 3;
                return;
            }
        }
        LogUtil.a("MusicController", "correctStatus setPlayState is STATE_STOPPED");
        musicInfo.setPlayState(1);
        this.s = 1;
    }

    private boolean bNL_(MusicInfo musicInfo, MediaController mediaController) {
        return "cn.kuwo.player".equals(mediaController.getPackageName()) && musicInfo.getPlayState() == 0;
    }

    @Override // defpackage.khb, com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public int getPlayState() {
        AudioManager audioManager = this.g;
        return (audioManager == null || !audioManager.isMusicActive()) ? -1 : 3;
    }

    @Override // defpackage.khb, com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void removeMusicUpdate() {
        this.n.removeMessages(30);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a() {
        char c2;
        String packageName = this.r.getPackageName();
        if (packageName.isEmpty()) {
            LogUtil.a("MusicController", "MediaController getPackageName is null.");
        }
        packageName.hashCode();
        switch (packageName.hashCode()) {
            case -2004763571:
                if (packageName.equals("com.android.mediacenter")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1635328017:
                if (packageName.equals("com.tencent.qqmusic")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -641838655:
                if (packageName.equals("app.podcast.cosmos")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -472574293:
                if (packageName.equals("com.huawei.music")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -277627811:
                if (packageName.equals("com.shinyv.cnr")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 460049591:
                if (packageName.equals("com.kugou.android")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 706813998:
                if (packageName.equals("com.ximalaya.ting.android")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1429484426:
                if (packageName.equals("cn.kuwo.player")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1979515232:
                if (packageName.equals("com.netease.cloudmusic")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 1994036591:
                if (packageName.equals("tv.danmaku.bili")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
            case 3:
                PowerKitManager.e().e(packageName, 100, "user-musicController");
                PowerKitManager.e().a(packageName, 100, "user-musicController");
                break;
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
                PowerKitManager.e().e(packageName, 65535, "user-musicController");
                PowerKitManager.e().a(packageName, 65535, "user-musicController");
                break;
            default:
                LogUtil.a("MusicController", "apply");
                break;
        }
    }

    public void e(int i) {
        ReleaseLogUtil.e("R_MusicController", "controllerMusicChange type: ", Integer.valueOf(i));
        if (this.r == null) {
            LogUtil.a("MusicController", "play mMediaController is null.");
            return;
        }
        a();
        try {
            if (i == 1) {
                this.r.getTransportControls().play();
            } else if (i == 2) {
                this.r.getTransportControls().pause();
            } else if (i == 3) {
                this.r.getTransportControls().skipToPrevious();
            } else if (i == 4) {
                this.r.getTransportControls().skipToNext();
            } else {
                LogUtil.a("MusicController", "controllerMusicChange.");
            }
        } catch (RuntimeException e2) {
            OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "controllerMusicChange RuntimeException:" + ExceptionUtils.d(e2));
            ReleaseLogUtil.e("R_MusicController", "controllerMusicChange RuntimeException: ", ExceptionUtils.d(e2));
        }
    }

    @Override // defpackage.khb, com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void controlVolume(boolean z) {
        ReleaseLogUtil.e("R_MusicController", "controlVolume isUp: ", Boolean.valueOf(z));
        try {
            MediaController mediaController = this.r;
            if (mediaController != null) {
                if (z) {
                    mediaController.adjustVolume(1, 1);
                    return;
                } else {
                    mediaController.adjustVolume(-1, 1);
                    return;
                }
            }
            LogUtil.a("MusicController", "ControlVolume mMediaController is null");
            AudioManager audioManager = this.g;
            if (audioManager != null) {
                if (z) {
                    audioManager.adjustStreamVolume(3, 1, 1);
                } else {
                    audioManager.adjustStreamVolume(3, -1, 1);
                }
                LogUtil.a("MusicController", "end raiseVoice");
            }
        } catch (RuntimeException e2) {
            OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "controlVolume RuntimeException:" + ExceptionUtils.d(e2));
            ReleaseLogUtil.e("R_MusicController", "controlVolume RuntimeException: ", ExceptionUtils.d(e2));
        }
    }

    @Override // defpackage.khb, com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void setVolume(int i) {
        ReleaseLogUtil.e("R_MusicController", "setVolume volume: ", Integer.valueOf(i));
        try {
            MediaController mediaController = this.r;
            if (mediaController != null) {
                mediaController.setVolumeTo(i, 1);
                ReleaseLogUtil.e("R_MusicController", "mMediaController setVolumeTo = ", Integer.valueOf(i));
            } else {
                AudioManager audioManager = this.g;
                if (audioManager != null) {
                    audioManager.setStreamVolume(3, i, 1);
                    ReleaseLogUtil.e("R_MusicController", "mAudioManager. setStreamVolume = ", Integer.valueOf(i));
                }
            }
        } catch (RuntimeException e2) {
            OpAnalyticsUtil.getInstance().setRiskWarningEvent("MusicController", "setVolume RuntimeException:" + ExceptionUtils.d(e2));
            ReleaseLogUtil.e("R_MusicController", "setVolume RuntimeException: ", ExceptionUtils.d(e2));
        }
    }

    @Override // defpackage.khb, com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void registerMusicCallback(ControlInterface.MusicChangeCallback musicChangeCallback) {
        List<MediaController> list;
        LogUtil.a("MusicController", "enter registerMusicCallback");
        if (this.u == null) {
            MediaSessionManager mediaSessionManager = this.q;
            if (mediaSessionManager != null) {
                try {
                    list = mediaSessionManager.getActiveSessions(new ComponentName(BaseApplication.getContext().getPackageName(), "com.huawei.bone.ui.setting.NotificationPushListener"));
                } catch (SecurityException unused) {
                    LogUtil.b("MusicController", "SecurityException Missing permission to control media");
                    list = null;
                }
                h(list);
                if (list != null && list.size() > 0) {
                    g();
                    MediaController mediaController = list.get(0);
                    this.r = mediaController;
                    HealthMediaCallback bNJ_ = bNJ_(mediaController);
                    this.o = bNJ_;
                    this.r.registerCallback(bNJ_, new Handler(Looper.getMainLooper()));
                    this.x = this.r.getPackageName();
                }
                try {
                    this.q.removeOnActiveSessionsChangedListener(this.t);
                    this.q.addOnActiveSessionsChangedListener(this.t, new ComponentName(BaseApplication.getContext().getPackageName(), "com.huawei.bone.ui.setting.NotificationPushListener"), new Handler(Looper.getMainLooper()));
                } catch (SecurityException unused2) {
                    LogUtil.b("MusicController", "SecurityException Missing permission to control media");
                }
                if (this.t != null) {
                    LogUtil.a("MusicController", "init Sessions onActiveSessionsChanged");
                    this.t.onActiveSessionsChanged(list);
                }
            }
            BaseApplication.getContext().registerReceiver(this.f, new IntentFilter(Constants.VOLUME_CHANGED_ACTION), LocalBroadcast.c, null);
            LogUtil.a("MusicController", "mMusicChangeCallback is null ,do registCallback");
        }
        this.u = musicChangeCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(List<MediaController> list) {
        if (list == null || list.size() < 1) {
            return;
        }
        j(list);
    }

    private void j(List<MediaController> list) {
        ArrayList arrayList = new ArrayList();
        for (MediaController mediaController : list) {
            if (mediaController != null) {
                String packageName = mediaController.getPackageName();
                if (this.k.contains(packageName)) {
                    ReleaseLogUtil.e("R_MusicController", "removeController packageName:" + packageName);
                    arrayList.add(mediaController);
                }
            }
        }
        if (koq.c(arrayList)) {
            ReleaseLogUtil.e("R_MusicController", "removeController size:" + arrayList.size());
            list.removeAll(arrayList);
        }
    }

    private void g() {
        MediaController mediaController = this.r;
        if (mediaController != null) {
            mediaController.unregisterCallback(this.o);
        }
    }

    @Override // defpackage.khb, com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void unRegisterMusicCallback() {
        MediaSessionManager.OnActiveSessionsChangedListener onActiveSessionsChangedListener;
        LogUtil.a("MusicController", "enter unRegisterMusicCallback");
        this.u = null;
        MediaController mediaController = this.r;
        if (mediaController != null) {
            mediaController.unregisterCallback(this.o);
        }
        i();
        MediaSessionManager mediaSessionManager = this.q;
        if (mediaSessionManager != null && (onActiveSessionsChangedListener = this.t) != null) {
            try {
                mediaSessionManager.removeOnActiveSessionsChangedListener(onActiveSessionsChangedListener);
            } catch (SecurityException unused) {
                LogUtil.b("MusicController", "SecurityException Missing permission to control media");
            }
        }
        if (this.d != null) {
            LogUtil.a("MusicController", "lastStateMap clear");
            this.d.clear();
        }
        try {
            BaseApplication.getContext().unregisterReceiver(this.f);
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("MusicController", "unregister broadcast occur IllegalArgumentException.");
        }
        this.n.removeCallbacksAndMessages(null);
        LogUtil.a("MusicController", "end unRegisterMusicCallback");
    }
}
