package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.musicservice.api.AudioService;
import com.huawei.health.musicservice.api.IAudioPlayer;
import com.huawei.health.musicservice.mediacenter.MediaCenterCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class eoi {

    /* renamed from: a, reason: collision with root package name */
    private Context f12121a;
    private int b;
    private IBaseResponseCallback c;
    private long d;
    private long e;
    private volatile MediaBrowser f;
    private boolean g;
    private String h;
    private Map<String, Integer> i;
    private MediaController j;
    private List<e> k;
    private IAudioPlayer.PlayMode l;
    private int m;
    private Queue<Runnable> n;
    private List<enq> o;

    public static class e {
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
        }

        public void onPlaybackStateChanged(PlaybackState playbackState) {
        }
    }

    private eoi() {
        this.k = new CopyOnWriteArrayList();
        this.i = new ConcurrentHashMap();
        this.n = new ConcurrentLinkedDeque();
        this.m = 0;
        this.b = 0;
        this.f12121a = BaseApplication.e();
        this.o = new CopyOnWriteArrayList();
        this.c = new IBaseResponseCallback() { // from class: eon
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.a("MusicService_MediaCenter", "inner init errorCode:", Integer.valueOf(i));
            }
        };
    }

    public static eoi c() {
        return d.d;
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("R_MusicService_MediaCenter", "callback is null");
            return;
        }
        if (n()) {
            ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaBrowser already connected before");
            iBaseResponseCallback.d(0, "mediaBrowser already connected before");
        } else {
            ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaBrowser start to connect");
            this.f = new MediaBrowser(this.f12121a, new ComponentName(BaseApplication.e(), (Class<?>) AudioService.class), new b(iBaseResponseCallback), null);
            this.f.connect();
        }
    }

    public void k() {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter play");
        if (!n()) {
            this.n.add(new Runnable() { // from class: eop
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.k();
                }
            });
            b(this.c);
        } else {
            this.j.getTransportControls().play();
        }
    }

    public void m() {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter pause");
        if (!n() && this.m == 3) {
            this.n.add(new Runnable() { // from class: eok
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.m();
                }
            });
            b(this.c);
        } else {
            this.j.getTransportControls().pause();
        }
    }

    public void l() {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter stop");
        int i = this.m;
        if (i == 1 || i == 0) {
            ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter currentStatus is STOPPED OR NONE");
        } else if (!n()) {
            this.n.add(new Runnable() { // from class: eor
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.l();
                }
            });
            b(this.c);
        } else {
            this.j.getTransportControls().stop();
        }
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void a(final long j) {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter seekTo");
        if (!n()) {
            this.n.add(new Runnable() { // from class: eos
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.a(j);
                }
            });
            b(this.c);
        } else {
            this.j.getTransportControls().seekTo(j);
        }
    }

    public void a(final List<enq> list) {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter resetPlayList");
        if (!n()) {
            this.n.add(new Runnable() { // from class: eom
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.c(list);
                }
            });
            b(this.c);
            return;
        }
        this.o.clear();
        if (koq.c(list)) {
            this.o.addAll(list);
        }
        this.b = 0;
        Bundle bundle = new Bundle();
        bundle.putSerializable(IAudioPlayer.ParameterKeys.AUDIO_RESET_PLAY_LIST, (Serializable) list);
        LogUtil.a("MusicService_MediaCenter", "resetPlayList playList size = ", Integer.valueOf(list.size()));
        this.j.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_RESET_PLAY_LIST, bundle);
    }

    /* synthetic */ void c(List list) {
        a((List<enq>) list);
    }

    public List<enq> j() {
        return this.o;
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void b(final long j) {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter autoStop time:", Long.valueOf(j));
        if (!n()) {
            this.n.add(new Runnable() { // from class: eoq
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.b(j);
                }
            });
            b(this.c);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong(IAudioPlayer.ParameterKeys.AUDIO_AUTO_STOP, j);
        this.j.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_AUTO_STOP, bundle);
        this.g = true;
        this.d = System.currentTimeMillis() + j;
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void e(final IAudioPlayer.PlayMode playMode) {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter setPlayMode playMode:", playMode.toString());
        if (!n()) {
            this.n.add(new Runnable() { // from class: eov
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.e(playMode);
                }
            });
            b(this.c);
        } else {
            this.l = playMode;
            Bundle bundle = new Bundle();
            bundle.putString(IAudioPlayer.ParameterKeys.AUDIO_PLAY_MODE, playMode.toString());
            this.j.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_PLAY_MODE, bundle);
        }
    }

    public boolean e(String str) {
        boolean a2 = SharedPreferenceManager.a("MusicService", str + "-isNonWifiTipsShow", true);
        boolean m = NetworkUtil.m();
        boolean i = NetworkUtil.i();
        LogUtil.a("MusicService_MediaCenter", "isNetworkConnected = ", Boolean.valueOf(i), "; isWifiConnected = ", Boolean.valueOf(m), "; isNeedShowWifiTips = ", Boolean.valueOf(a2));
        return i && !m && a2;
    }

    public void b(MediaCenterCallback mediaCenterCallback, String str) {
        if (e(str)) {
            d(mediaCenterCallback, str);
        } else {
            mediaCenterCallback.onResponse(0, "noNeedToShowDialog");
        }
    }

    private void d(final MediaCenterCallback mediaCenterCallback, final String str) {
        LogUtil.a("MusicService_MediaCenter", "showWifiTipsDialog");
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("MusicService_MediaCenter", "context is null when show mobile data dialog.");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(wa_);
        builder.e(R$string.IDS_use_rate_of_flow).czC_(R$string.IDS_continue, new View.OnClickListener() { // from class: eoo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                eoi.arL_(str, mediaCenterCallback, view);
            }
        }).czz_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: eox
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                eoi.arM_(MediaCenterCallback.this, view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    static /* synthetic */ void arL_(String str, MediaCenterCallback mediaCenterCallback, View view) {
        SharedPreferenceManager.e("MusicService", str + "-isNonWifiTipsShow", false);
        if (mediaCenterCallback != null) {
            mediaCenterCallback.onResponse(0, "success");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void arM_(MediaCenterCallback mediaCenterCallback, View view) {
        if (mediaCenterCallback != null) {
            mediaCenterCallback.onResponse(-1, ParamConstants.CallbackMethod.ON_FAIL);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public IAudioPlayer.PlayMode h() {
        return this.l;
    }

    public int g() {
        return this.m;
    }

    public int f() {
        return this.b;
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(final int i) {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter skipTo index:", Integer.valueOf(i));
        if (!n()) {
            this.n.add(new Runnable() { // from class: eoj
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.b(i);
                }
            });
            b(this.c);
        } else {
            this.j.getTransportControls().skipToQueueItem(i);
        }
    }

    public long a() {
        return this.e;
    }

    public boolean o() {
        return this.g;
    }

    public long b() {
        return this.d;
    }

    public void e() {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter disconnect");
        if (n()) {
            this.f.disconnect();
        }
        this.f = null;
        this.j = null;
        s();
    }

    private void s() {
        this.i.clear();
        this.m = 0;
        this.b = 0;
        this.o.clear();
        this.l = IAudioPlayer.PlayMode.SEQUENCE_PLAY;
        this.e = 0L;
        this.d = 0L;
        this.g = false;
        this.h = "";
    }

    public void d(e eVar) {
        this.k.add(eVar);
    }

    public void a(e eVar) {
        this.k.remove(eVar);
    }

    public void d() {
        ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter cancelAutoStop");
        if (!this.g) {
            ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaCenter doesn't set autoStop");
            return;
        }
        if (!n()) {
            this.n.add(new Runnable() { // from class: eol
                @Override // java.lang.Runnable
                public final void run() {
                    eoi.this.d();
                }
            });
            b(this.c);
        } else {
            this.g = false;
            this.d = 0L;
            this.j.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_CANCEL_AUTO_STOP, new Bundle());
        }
    }

    public String i() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        return this.f != null && this.f.isConnected();
    }

    static class d {
        private static final eoi d = new eoi();
    }

    class b extends MediaBrowser.ConnectionCallback {

        /* renamed from: a, reason: collision with root package name */
        private IBaseResponseCallback f12122a;

        public b(IBaseResponseCallback iBaseResponseCallback) {
            this.f12122a = iBaseResponseCallback;
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnected() {
            ReleaseLogUtil.e("R_MusicService_MediaCenter", "onConnected");
            if (!eoi.this.n()) {
                ReleaseLogUtil.e("R_MusicService_MediaCenter", "mediaBrowser is null or not connected");
                this.f12122a.d(-1, "mediaBrowser is null or not connected");
                return;
            }
            eoi.this.j = new MediaController(BaseApplication.e(), eoi.this.f.getSessionToken());
            eoi.this.j.registerCallback(new a());
            while (!eoi.this.n.isEmpty()) {
                Runnable runnable = (Runnable) eoi.this.n.peek();
                if (runnable != null) {
                    runnable.run();
                    eoi.this.n.poll();
                }
            }
            this.f12122a.d(0, "onConnected");
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionFailed() {
            super.onConnectionFailed();
            ReleaseLogUtil.e("R_MusicService_MediaCenter", "onConnectionFailed");
            this.f12122a.d(-1, "onConnectionFailed");
        }
    }

    class a extends MediaController.Callback {
        private a() {
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            if (playbackState == null) {
                ReleaseLogUtil.d("R_MusicService_MediaCenter", "onPlaybackStateChanged state is null");
                return;
            }
            eoi.this.m = playbackState.getState();
            eoi.this.e = playbackState.getPosition();
            Iterator it = eoi.this.k.iterator();
            while (it.hasNext()) {
                ((e) it.next()).onPlaybackStateChanged(playbackState);
            }
        }

        @Override // android.media.session.MediaController.Callback
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            if (mediaMetadata == null) {
                ReleaseLogUtil.d("R_MusicService_MediaCenter", "onMetadataChanged metadata is null");
                return;
            }
            String string = mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
            eoi.this.h = string;
            if (string != null && eoi.this.i.containsKey(string)) {
                eoi eoiVar = eoi.this;
                eoiVar.b = ((Integer) eoiVar.i.get(string)).intValue();
            }
            Iterator it = eoi.this.k.iterator();
            while (it.hasNext()) {
                ((e) it.next()).onMetadataChanged(mediaMetadata);
            }
        }
    }
}
