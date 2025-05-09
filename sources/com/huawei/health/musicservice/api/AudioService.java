package com.huawei.health.musicservice.api;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.musicservice.api.AudioService;
import com.huawei.health.musicservice.api.IAudioPlayer;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.utils.Constants;
import defpackage.enq;
import defpackage.eoa;
import defpackage.eoe;
import defpackage.eow;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class AudioService extends MediaBrowserService {

    /* renamed from: a, reason: collision with root package name */
    private IAudioPlayer f2920a;
    private long b = 439;
    private MediaSession c;
    private eoa d;
    private boolean e;

    @Override // android.service.media.MediaBrowserService
    public MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        LogUtil.c("R_AudioService", "onGetRoot packageName:", str);
        if (!str.equals(BaseApplication.d())) {
            LogUtil.e("R_AudioService", "wrong client, fail to bind to musicservice");
            return null;
        }
        return new MediaBrowserService.BrowserRoot("HuaweiHealth_AudioService", null);
    }

    @Override // android.service.media.MediaBrowserService
    public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
        LogUtil.d("R_AudioService", "onLoadChildren");
    }

    @Override // android.service.media.MediaBrowserService, android.app.Service
    public void onCreate() {
        LogUtil.c("R_AudioService", "onCreate");
        super.onCreate();
        MediaSession mediaSession = new MediaSession(this, "R_AudioService");
        this.c = mediaSession;
        mediaSession.setFlags(3);
        this.c.setCallback(new d(this, null));
        IAudioPlayer a2 = eoe.a(this);
        this.f2920a = a2;
        a2.setCallback(new b());
        setSessionToken(this.c.getSessionToken());
        this.d = new eoa(this, this.c.getSessionToken());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.c("R_AudioService", "onStartCommand");
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.service.media.MediaBrowserService, android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.c("R_AudioService", "onBind");
        return super.onBind(intent);
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        LogUtil.c("R_AudioService", "onRebind");
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.c("R_AudioService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.c("R_AudioService", "onDestroy");
        this.f2920a.release();
        this.c.release();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.c.setActive(eow.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.e = false;
        this.c.setActive(false);
    }

    public class b implements IAudioPlayer.AudioPlayerCallBack {
        private volatile enq c = new enq();

        /* renamed from: a, reason: collision with root package name */
        private long f2921a = 0;
        private boolean b = false;
        private final ExtendHandler e = HandlerCenter.e("AudioMsgDelay");

        b() {
        }

        @Override // com.huawei.health.musicservice.api.IAudioPlayer.AudioPlayerCallBack
        /* renamed from: onStatusChanged, reason: merged with bridge method [inline-methods] */
        public void b(final IAudioPlayer.PlayStatus playStatus, final enq enqVar) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = elapsedRealtime - this.f2921a;
            String str = Constants.NULL;
            if (j <= 50) {
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder("calling too fast, delay to 50ms later, onStatusChanged: ");
                sb.append(playStatus);
                sb.append(", audioItem: ");
                if (enqVar != null) {
                    str = enqVar.n();
                }
                sb.append(str);
                objArr[0] = sb.toString();
                LogUtil.c("R_AudioService", objArr);
                this.e.postTask(new Runnable() { // from class: enp
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioService.b.this.b(playStatus, enqVar);
                    }
                }, 50L);
            }
            this.f2921a = elapsedRealtime;
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder("onStatusChanged: ");
            sb2.append(playStatus);
            sb2.append(", audioItem: ");
            if (enqVar != null) {
                str = enqVar.n();
            }
            sb2.append(str);
            objArr2[0] = sb2.toString();
            LogUtil.c("R_AudioService", objArr2);
            AudioService.this.e();
            a(enqVar);
            switch (AnonymousClass5.b[playStatus.ordinal()]) {
                case 1:
                    AudioService.this.c.setPlaybackState(arv_(3, enqVar));
                    AudioService.this.d.a(enqVar, true);
                    break;
                case 2:
                    AudioService.this.c.setPlaybackState(arv_(2, enqVar));
                    AudioService.this.d.a(enqVar, false);
                    break;
                case 3:
                case 4:
                    AudioService.this.d.e();
                    AudioService.this.c.setPlaybackState(arv_(1, enqVar));
                    AudioService.this.stopSelf();
                    break;
                case 5:
                    AudioService.this.c.setPlaybackState(arv_(10, enqVar));
                    AudioService.this.d.e();
                    break;
                case 6:
                    AudioService.this.c.setPlaybackState(arv_(9, enqVar));
                    AudioService.this.d.e();
                    break;
                case 7:
                    AudioService.this.c.setPlaybackState(arv_(11, enqVar));
                    AudioService.this.d.e();
                    break;
                case 8:
                    AudioService.this.c.setPlaybackState(arv_(6, enqVar));
                    break;
                case 9:
                    AudioService.this.c();
                    break;
                case 10:
                    AudioService.this.c.setPlaybackState(arv_(7, enqVar));
                    break;
                default:
                    LogUtil.a("R_AudioService", "undefined status: " + playStatus);
                    AudioService.this.d.e();
                    break;
            }
        }

        @Override // com.huawei.health.musicservice.api.IAudioPlayer.AudioPlayerCallBack
        public void onProgressChanged(enq enqVar, int i) {
            a(enqVar);
            PlaybackState.Builder builder = new PlaybackState.Builder();
            builder.setState(3, i, 1.0f);
            builder.setActions(AudioService.this.b);
            if (enqVar != null) {
                Bundle bundle = new Bundle();
                bundle.putString("category", enqVar.c());
                builder.setExtras(bundle);
            }
            AudioService.this.c.setPlaybackState(builder.build());
        }

        private void a(enq enqVar) {
            boolean z;
            if (enqVar == null) {
                return;
            }
            if (this.c.equals(enqVar)) {
                z = false;
            } else {
                LogUtil.c("R_AudioService", "audio changed, ", this.c.n(), " -> ", enqVar.n());
                this.c = enqVar;
                z = true;
            }
            if (z) {
                b(enqVar);
                e(enqVar);
            }
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
                LogUtil.e("R_AudioService", "sleep interrupted...");
            }
            e(enqVar, z);
        }

        private void e(enq enqVar) {
            if (TextUtils.isEmpty(enqVar.b())) {
                LogUtil.a("R_AudioService", "audioItem don't have deeplink");
                return;
            }
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(enqVar.b()));
            intent.setPackage("com.huawei.health");
            intent.setFlags(603979776);
            AudioService.this.d.arA_(PendingIntent.getActivity(BaseApplication.e(), 0, intent, AppRouterExtras.COLDSTART));
        }

        private void b(enq enqVar) {
            LogUtil.c("R_AudioService", "notify metadata without bitmap");
            this.c = enqVar;
            AudioService.this.d.a(enqVar, true);
            AudioService.this.c.setMetadata(new MediaMetadata.Builder().putString(MediaMetadataCompat.METADATA_KEY_TITLE, enqVar.n()).putString(MediaMetadataCompat.METADATA_KEY_ARTIST, enqVar.f()).putLong(MediaMetadataCompat.METADATA_KEY_DURATION, enqVar.a() * 1000).putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, enqVar.h()).putString(MediaMetadataCompat.METADATA_KEY_GENRE, enqVar.c()).build());
        }

        private void e(final enq enqVar, boolean z) {
            if (z) {
                this.b = false;
            }
            if (this.b) {
                return;
            }
            LogUtil.c("R_AudioService", "notify metadata with bitmap");
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: ent
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.b.this.c(enqVar);
                }
            });
        }

        public /* synthetic */ void c(enq enqVar) {
            final MediaMetadata.Builder putString = new MediaMetadata.Builder().putString(MediaMetadataCompat.METADATA_KEY_TITLE, enqVar.n()).putString(MediaMetadataCompat.METADATA_KEY_ARTIST, enqVar.f()).putLong(MediaMetadataCompat.METADATA_KEY_DURATION, enqVar.a() * 1000).putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, enqVar.h()).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, TextUtils.isEmpty(enqVar.e()) ? "null_icon_uri" : enqVar.e()).putString(MediaMetadataCompat.METADATA_KEY_GENRE, enqVar.c());
            Bitmap arN_ = eow.arN_(enqVar.e());
            if (arN_ == null) {
                this.b = false;
                ReleaseLogUtil.b("R_AudioService", "query icon bitmap failed, will try when next status comes...");
            } else {
                this.b = true;
                putString.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, arN_);
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: enr
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.b.this.arw_(putString);
                }
            });
        }

        public /* synthetic */ void arw_(MediaMetadata.Builder builder) {
            AudioService.this.c.setMetadata(builder.build());
        }

        private PlaybackState arv_(int i, enq enqVar) {
            PlaybackState.Builder builder = new PlaybackState.Builder();
            long j = 0;
            if (enqVar != null) {
                if (i != 1 && i != 0) {
                    j = enqVar.j();
                }
                Bundle bundle = new Bundle();
                bundle.putString("category", enqVar.c());
                builder.setExtras(bundle);
            }
            LogUtil.d("R_AudioService", "createPlaybackState, state: " + i + ", position: " + j);
            builder.setState(i, j, 1.0f);
            builder.setActions(AudioService.this.b);
            return builder.build();
        }
    }

    /* renamed from: com.huawei.health.musicservice.api.AudioService$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[IAudioPlayer.PlayStatus.values().length];
            b = iArr;
            try {
                iArr[IAudioPlayer.PlayStatus.PLAYING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[IAudioPlayer.PlayStatus.PAUSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[IAudioPlayer.PlayStatus.STOPPED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[IAudioPlayer.PlayStatus.RELEASED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[IAudioPlayer.PlayStatus.SKIP_TO_NEXT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[IAudioPlayer.PlayStatus.SKIP_TO_PREV.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[IAudioPlayer.PlayStatus.SKIP_TO_INDEX.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[IAudioPlayer.PlayStatus.BUFFERING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[IAudioPlayer.PlayStatus.FOCUS_LOSS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[IAudioPlayer.PlayStatus.FAILED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public class d extends MediaSession.Callback {
        private d() {
        }

        /* synthetic */ d(AudioService audioService, AnonymousClass5 anonymousClass5) {
            this();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPlay() {
            LogUtil.c("R_AudioService", "receive command, play");
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: enw
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.d.this.b();
                }
            });
        }

        public /* synthetic */ void b() {
            AudioService.this.f2920a.play();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPause() {
            LogUtil.c("R_AudioService", "receive command, pause");
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: enx
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.d.this.c();
                }
            });
        }

        public /* synthetic */ void c() {
            AudioService.this.f2920a.pause();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onSkipToNext() {
            LogUtil.c("R_AudioService", "receive command, skipToNext");
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: ens
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.d.this.a();
                }
            });
        }

        public /* synthetic */ void a() {
            AudioService.this.f2920a.playNext();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onSkipToPrevious() {
            LogUtil.c("R_AudioService", "receive command, skipToPrevious");
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: eob
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.d.this.e();
                }
            });
        }

        public /* synthetic */ void e() {
            AudioService.this.f2920a.playPrevious();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onStop() {
            LogUtil.c("R_AudioService", "receive command, stop");
            AudioService.this.c();
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: env
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.d.this.d();
                }
            });
        }

        public /* synthetic */ void d() {
            AudioService.this.f2920a.stop();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onSeekTo(final long j) {
            LogUtil.c("R_AudioService", "receive command, seek to " + j);
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: enu
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.d.this.c(j);
                }
            });
        }

        public /* synthetic */ void c(long j) {
            AudioService.this.f2920a.seekTo((int) j);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onSkipToQueueItem(final long j) {
            LogUtil.c("R_AudioService", "receive command, skip to " + j);
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: eny
                @Override // java.lang.Runnable
                public final void run() {
                    AudioService.d.this.a(j);
                }
            });
        }

        public /* synthetic */ void a(long j) {
            AudioService.this.f2920a.play((int) j);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onCustomAction(String str, Bundle bundle) {
            LogUtil.c("R_AudioService", "receive command, custom action: " + str);
            arx_(str, bundle);
        }

        private void arx_(final String str, final Bundle bundle) {
            ThreadPoolManager.d().c("R_AudioService", new Runnable() { // from class: com.huawei.health.musicservice.api.AudioService.d.4
                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                @Override // java.lang.Runnable
                public void run() {
                    char c;
                    if (bundle == null) {
                        LogUtil.e("R_AudioService", "bundle extra is null");
                    }
                    String str2 = str;
                    str2.hashCode();
                    switch (str2.hashCode()) {
                        case -433667404:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_NOTIFICATION)) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 783098456:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_MEDIA_BUTTON_RECEIVER)) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 786809848:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_USAGE)) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 810819158:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_CANCEL_AUTO_STOP)) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 855333737:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_AUTO_STOP)) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1161184624:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_RESET_PLAY_LIST)) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1418697730:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_ADD_PLAY_LIST)) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1611303589:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_PLAY_MODE)) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1698805284:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_SUPPORT_ACTIONS)) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1754163774:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_DELETE_ITEM)) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1875042947:
                            if (str2.equals(IAudioPlayer.ParameterKeys.AUDIO_SET_PLAY_LIST)) {
                                c = '\n';
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
                            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(IAudioPlayer.ParameterKeys.AUDIO_NOTIFICATION);
                            if (pendingIntent != null) {
                                AudioService.this.d.arA_(pendingIntent);
                                break;
                            } else {
                                LogUtil.e("R_AudioService", "get PendingIntent from bundle extra is null");
                                AudioService.this.d.arA_(null);
                                break;
                            }
                        case 1:
                            Serializable serializable = bundle.getSerializable(IAudioPlayer.ParameterKeys.AUDIO_MEDIA_BUTTON_RECEIVER);
                            if (serializable instanceof Class) {
                                AudioService.this.c.setMediaButtonReceiver(PendingIntent.getBroadcast(AudioService.this, 0, new Intent(AudioService.this, (Class<?>) serializable), AppRouterExtras.COLDSTART));
                                break;
                            }
                            break;
                        case 2:
                            AudioService.this.f2920a.setUsage(bundle.getInt(IAudioPlayer.ParameterKeys.AUDIO_USAGE));
                            break;
                        case 3:
                            AudioService.this.f2920a.cancelAutoStop();
                            break;
                        case 4:
                            AudioService.this.f2920a.autoStop(bundle.getLong(IAudioPlayer.ParameterKeys.AUDIO_AUTO_STOP));
                            break;
                        case 5:
                            Serializable serializable2 = bundle.getSerializable(IAudioPlayer.ParameterKeys.AUDIO_RESET_PLAY_LIST);
                            if (a(serializable2)) {
                                AudioService.this.f2920a.resetPlayList((ArrayList) serializable2);
                                break;
                            } else {
                                LogUtil.e("R_AudioService", "set play list failed");
                                break;
                            }
                        case 6:
                            Serializable serializable3 = bundle.getSerializable(IAudioPlayer.ParameterKeys.AUDIO_ADD_PLAY_LIST);
                            if (a(serializable3)) {
                                AudioService.this.f2920a.addPlayList((ArrayList) serializable3);
                                break;
                            } else {
                                LogUtil.e("R_AudioService", "add play list failed");
                                break;
                            }
                        case 7:
                            AudioService.this.f2920a.setPlayMode(IAudioPlayer.PlayMode.valueOf(bundle.getString(IAudioPlayer.ParameterKeys.AUDIO_PLAY_MODE)));
                            break;
                        case '\b':
                            AudioService.this.b = bundle.getLong(IAudioPlayer.ParameterKeys.AUDIO_SUPPORT_ACTIONS);
                            break;
                        case '\t':
                            Serializable serializable4 = bundle.getSerializable(IAudioPlayer.ParameterKeys.AUDIO_DELETE_ITEM);
                            if (serializable4 instanceof enq) {
                                AudioService.this.f2920a.removeItem((enq) serializable4);
                                break;
                            } else {
                                LogUtil.e("R_AudioService", "remove item failed");
                                break;
                            }
                        case '\n':
                            Serializable serializable5 = bundle.getSerializable(IAudioPlayer.ParameterKeys.AUDIO_SET_PLAY_LIST);
                            if (a(serializable5)) {
                                AudioService.this.f2920a.setPlayList((ArrayList) serializable5);
                                break;
                            } else {
                                LogUtil.e("R_AudioService", "set play list failed");
                                break;
                            }
                        default:
                            LogUtil.a("R_AudioService", "undefined action: " + str);
                            break;
                    }
                }

                private boolean a(Object obj) {
                    if (!(obj instanceof ArrayList)) {
                        LogUtil.a("R_AudioService", "listCheck failed, input is not ArrayList");
                        return false;
                    }
                    ArrayList arrayList = (ArrayList) obj;
                    if (arrayList.isEmpty()) {
                        LogUtil.a("R_AudioService", "listCheck failed, list is empty");
                        return false;
                    }
                    if (arrayList.get(0) instanceof enq) {
                        return true;
                    }
                    LogUtil.a("R_AudioService", "listCheck failed, list is empty");
                    return false;
                }
            });
        }

        @Override // android.media.session.MediaSession.Callback
        public boolean onMediaButtonEvent(Intent intent) {
            if (!eow.b()) {
                LogUtil.c("R_AudioService", "receive media button event, but not in media center, do nothing...");
                return true;
            }
            return super.onMediaButtonEvent(intent);
        }
    }
}
