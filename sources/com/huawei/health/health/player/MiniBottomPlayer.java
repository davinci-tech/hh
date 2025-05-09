package com.huawei.health.health.player;

import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.health.player.MiniBottomPlayer;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.musicservice.mediacenter.MediaCenterCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressindicator.HealthProgressIndicator;
import com.huawei.ui.commonui.utils.TagResourcePolicy;
import defpackage.dow;
import defpackage.enq;
import defpackage.eoi;
import defpackage.evl;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nkr;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsf;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class MiniBottomPlayer extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f2480a;
    private ImageView b;
    private ImageView c;
    private long d;
    private Context e;
    private ImageView f;
    private volatile String g;
    private eoi h;
    private PlaybackState i;
    private ImageView j;
    private int k;
    private eoi.e l;
    private HealthTextView m;
    private HealthProgressIndicator n;
    private HealthTextView o;
    private View s;

    private void g() {
    }

    public MiniBottomPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = -1;
        this.h = eoi.c();
        this.g = "";
        this.d = 0L;
        this.l = new eoi.e() { // from class: com.huawei.health.health.player.MiniBottomPlayer.1
            @Override // eoi.e
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                if (playbackState == null) {
                    LogUtil.h("MiniBottomPlayer", "onPlaybackStateChanged, state is null ,this should NOT happen...");
                    return;
                }
                Bundle extras = playbackState.getExtras();
                if (extras != null && "sleepMusic".equals(extras.getString("category"))) {
                    if (MiniBottomPlayer.this.i == null || MiniBottomPlayer.this.i.getState() != playbackState.getState()) {
                        ReleaseLogUtil.e("R_MiniBottomPlayer", "onPlaybackStateChanged, state.getState:", Integer.valueOf(playbackState.getState()));
                        MiniBottomPlayer miniBottomPlayer = MiniBottomPlayer.this;
                        miniBottomPlayer.d(miniBottomPlayer.h.g());
                    }
                    if (playbackState.getState() == 1) {
                        MiniBottomPlayer.this.c(0.0f);
                    } else if (playbackState.getState() == 3 && (MiniBottomPlayer.this.i == null || MiniBottomPlayer.this.i.getState() == 3)) {
                        long currentItemDuration = MiniBottomPlayer.this.getCurrentItemDuration();
                        if (currentItemDuration > 0) {
                            MiniBottomPlayer.this.c(playbackState.getPosition() / currentItemDuration);
                        }
                    }
                    MiniBottomPlayer.this.i = playbackState;
                    return;
                }
                LogUtil.h("MiniBottomPlayer", "onPlaybackStateChanged not sleep music media");
                MiniBottomPlayer.this.e();
            }

            @Override // eoi.e
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                if (mediaMetadata == null) {
                    return;
                }
                if (!"sleepMusic".equals(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_GENRE))) {
                    LogUtil.h("MiniBottomPlayer", "onMetadataChanged not sleep music media");
                    MiniBottomPlayer.this.e();
                } else {
                    if (!TextUtils.isEmpty(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI))) {
                        LogUtil.a("MiniBottomPlayer", "It's the second time that onMetadataChanged callback, do not consider");
                        return;
                    }
                    MiniBottomPlayer.this.d = mediaMetadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                    ReleaseLogUtil.e("R_MiniBottomPlayer", "onMediaChanged mCurrentItemDuration:", Long.valueOf(MiniBottomPlayer.this.d));
                    MiniBottomPlayer miniBottomPlayer = MiniBottomPlayer.this;
                    miniBottomPlayer.d(miniBottomPlayer.getCurrentAudioItem());
                }
            }
        };
    }

    public MiniBottomPlayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = -1;
        this.h = eoi.c();
        this.g = "";
        this.d = 0L;
        this.l = new eoi.e() { // from class: com.huawei.health.health.player.MiniBottomPlayer.1
            @Override // eoi.e
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                if (playbackState == null) {
                    LogUtil.h("MiniBottomPlayer", "onPlaybackStateChanged, state is null ,this should NOT happen...");
                    return;
                }
                Bundle extras = playbackState.getExtras();
                if (extras != null && "sleepMusic".equals(extras.getString("category"))) {
                    if (MiniBottomPlayer.this.i == null || MiniBottomPlayer.this.i.getState() != playbackState.getState()) {
                        ReleaseLogUtil.e("R_MiniBottomPlayer", "onPlaybackStateChanged, state.getState:", Integer.valueOf(playbackState.getState()));
                        MiniBottomPlayer miniBottomPlayer = MiniBottomPlayer.this;
                        miniBottomPlayer.d(miniBottomPlayer.h.g());
                    }
                    if (playbackState.getState() == 1) {
                        MiniBottomPlayer.this.c(0.0f);
                    } else if (playbackState.getState() == 3 && (MiniBottomPlayer.this.i == null || MiniBottomPlayer.this.i.getState() == 3)) {
                        long currentItemDuration = MiniBottomPlayer.this.getCurrentItemDuration();
                        if (currentItemDuration > 0) {
                            MiniBottomPlayer.this.c(playbackState.getPosition() / currentItemDuration);
                        }
                    }
                    MiniBottomPlayer.this.i = playbackState;
                    return;
                }
                LogUtil.h("MiniBottomPlayer", "onPlaybackStateChanged not sleep music media");
                MiniBottomPlayer.this.e();
            }

            @Override // eoi.e
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                if (mediaMetadata == null) {
                    return;
                }
                if (!"sleepMusic".equals(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_GENRE))) {
                    LogUtil.h("MiniBottomPlayer", "onMetadataChanged not sleep music media");
                    MiniBottomPlayer.this.e();
                } else {
                    if (!TextUtils.isEmpty(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI))) {
                        LogUtil.a("MiniBottomPlayer", "It's the second time that onMetadataChanged callback, do not consider");
                        return;
                    }
                    MiniBottomPlayer.this.d = mediaMetadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                    ReleaseLogUtil.e("R_MiniBottomPlayer", "onMediaChanged mCurrentItemDuration:", Long.valueOf(MiniBottomPlayer.this.d));
                    MiniBottomPlayer miniBottomPlayer = MiniBottomPlayer.this;
                    miniBottomPlayer.d(miniBottomPlayer.getCurrentAudioItem());
                }
            }
        };
    }

    public MiniBottomPlayer(Context context) {
        super(context);
        this.k = -1;
        this.h = eoi.c();
        this.g = "";
        this.d = 0L;
        this.l = new eoi.e() { // from class: com.huawei.health.health.player.MiniBottomPlayer.1
            @Override // eoi.e
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                if (playbackState == null) {
                    LogUtil.h("MiniBottomPlayer", "onPlaybackStateChanged, state is null ,this should NOT happen...");
                    return;
                }
                Bundle extras = playbackState.getExtras();
                if (extras != null && "sleepMusic".equals(extras.getString("category"))) {
                    if (MiniBottomPlayer.this.i == null || MiniBottomPlayer.this.i.getState() != playbackState.getState()) {
                        ReleaseLogUtil.e("R_MiniBottomPlayer", "onPlaybackStateChanged, state.getState:", Integer.valueOf(playbackState.getState()));
                        MiniBottomPlayer miniBottomPlayer = MiniBottomPlayer.this;
                        miniBottomPlayer.d(miniBottomPlayer.h.g());
                    }
                    if (playbackState.getState() == 1) {
                        MiniBottomPlayer.this.c(0.0f);
                    } else if (playbackState.getState() == 3 && (MiniBottomPlayer.this.i == null || MiniBottomPlayer.this.i.getState() == 3)) {
                        long currentItemDuration = MiniBottomPlayer.this.getCurrentItemDuration();
                        if (currentItemDuration > 0) {
                            MiniBottomPlayer.this.c(playbackState.getPosition() / currentItemDuration);
                        }
                    }
                    MiniBottomPlayer.this.i = playbackState;
                    return;
                }
                LogUtil.h("MiniBottomPlayer", "onPlaybackStateChanged not sleep music media");
                MiniBottomPlayer.this.e();
            }

            @Override // eoi.e
            public void onMetadataChanged(MediaMetadata mediaMetadata) {
                if (mediaMetadata == null) {
                    return;
                }
                if (!"sleepMusic".equals(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_GENRE))) {
                    LogUtil.h("MiniBottomPlayer", "onMetadataChanged not sleep music media");
                    MiniBottomPlayer.this.e();
                } else {
                    if (!TextUtils.isEmpty(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI))) {
                        LogUtil.a("MiniBottomPlayer", "It's the second time that onMetadataChanged callback, do not consider");
                        return;
                    }
                    MiniBottomPlayer.this.d = mediaMetadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                    ReleaseLogUtil.e("R_MiniBottomPlayer", "onMediaChanged mCurrentItemDuration:", Long.valueOf(MiniBottomPlayer.this.d));
                    MiniBottomPlayer miniBottomPlayer = MiniBottomPlayer.this;
                    miniBottomPlayer.d(miniBottomPlayer.getCurrentAudioItem());
                }
            }
        };
        this.e = context;
        ReleaseLogUtil.e("R_MiniBottomPlayer", "MiniBottomPlayer create");
        View inflate = inflate(getContext(), R.layout.commonui_bottom_player_layout, this);
        this.s = inflate;
        this.o = (HealthTextView) inflate.findViewById(R.id.float_music_name);
        this.m = (HealthTextView) this.s.findViewById(R.id.float_music_artist);
        this.j = (ImageView) this.s.findViewById(R.id.float_music_image);
        this.c = (ImageView) this.s.findViewById(R.id.float_play_button);
        this.f2480a = (RelativeLayout) this.s.findViewById(R.id.control_group);
        ImageView imageView = (ImageView) this.s.findViewById(R.id.float_list_button);
        this.f = imageView;
        imageView.setVisibility(8);
        this.b = (ImageView) this.s.findViewById(R.id.control_close);
        HealthProgressIndicator healthProgressIndicator = (HealthProgressIndicator) this.s.findViewById(R.id.bottom_player_media_indicator);
        this.n = healthProgressIndicator;
        healthProgressIndicator.setWaitingAnimationEnabled(false);
        this.s.setClickable(true);
        c();
        o();
        d();
    }

    private void d() {
        d(getCurrentAudioItem(), this.h.g());
    }

    private void c() {
        LogUtil.a("MiniBottomPlayer", "miniPlayer setClickListener, this = ", this);
        i();
        f();
        j();
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public enq getCurrentAudioItem() {
        if (koq.b(this.h.j(), this.h.f())) {
            LogUtil.h("MiniBottomPlayer", "getCurrentAudioItem getPlayList error, index:", Integer.valueOf(this.h.f()));
            return null;
        }
        return this.h.j().get(this.h.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getCurrentItemDuration() {
        if (this.d <= 0) {
            enq currentAudioItem = getCurrentAudioItem();
            if (currentAudioItem == null) {
                return 0L;
            }
            this.d = currentAudioItem.a() * 1000;
        }
        return this.d;
    }

    private void o() {
        eoi eoiVar = this.h;
        if (eoiVar != null) {
            eoiVar.d(this.l);
        }
    }

    public void b() {
        eoi eoiVar = this.h;
        if (eoiVar != null) {
            eoiVar.a(this.l);
        }
    }

    private void d(enq enqVar, int i) {
        d(enqVar);
        d(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.health.player.MiniBottomPlayer.5
            @Override // java.lang.Runnable
            public void run() {
                int i2;
                int i3;
                int i4 = i;
                if (i4 != 6) {
                    if (i4 == 3) {
                        i2 = R$drawable.icon_bottom_player_play;
                        i3 = R$string.accessibility_pause;
                    } else {
                        i2 = R$drawable.icon_bottom_player_pause;
                        i3 = R$string.IDS_bolt_button_playback;
                    }
                    MiniBottomPlayer.this.c.setImageDrawable(nsf.cKq_(i2));
                    jcf.bEE_(MiniBottomPlayer.this.c, 1);
                    jcf.bEz_(MiniBottomPlayer.this.c, nsf.h(i3));
                    return;
                }
                LogUtil.a("MiniBottomPlayer", "playState is loading, don't change the icon");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(enq enqVar) {
        if (enqVar == null) {
            LogUtil.h("MiniBottomPlayer", "refreshMediaInfo audioItem is null");
            return;
        }
        LogUtil.a("MiniBottomPlayer", "refreshMediaInfo mMediaId = ", this.g, ", audioItem = ", enqVar, ", this = ", this);
        this.g = enqVar.h();
        HandlerExecutor.e(new AnonymousClass2(enqVar, new WeakReference(this.j)));
    }

    /* renamed from: com.huawei.health.health.player.MiniBottomPlayer$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ enq c;
        final /* synthetic */ WeakReference e;

        AnonymousClass2(enq enqVar, WeakReference weakReference) {
            this.c = enqVar;
            this.e = weakReference;
        }

        @Override // java.lang.Runnable
        public void run() {
            MiniBottomPlayer.this.o.setText(this.c.n());
            MiniBottomPlayer.this.m.setText(this.c.f());
            nrf.cJf_((ImageView) this.e.get(), this.c.e(), nrf.e, this.c.h(), new TagResourcePolicy() { // from class: dov
                @Override // com.huawei.ui.commonui.utils.TagResourcePolicy
                public final boolean filterTagResource(String str) {
                    return MiniBottomPlayer.AnonymousClass2.this.e(str);
                }
            });
        }

        public /* synthetic */ boolean e(String str) {
            return MiniBottomPlayer.this.g.equals(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final float f) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.health.player.MiniBottomPlayer.4
            @Override // java.lang.Runnable
            public void run() {
                MiniBottomPlayer.this.setPlayerProgress((int) (f * 100.0f));
            }
        });
    }

    private void i() {
        this.f2480a.setOnClickListener(new View.OnClickListener() { // from class: dos
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiniBottomPlayer.this.Yg_(view);
            }
        });
    }

    public /* synthetic */ void Yg_(View view) {
        if (this.h.g() != 3) {
            if (!CommonUtil.aa(getContext())) {
                nrh.c(getContext(), getContext().getResources().getString(R$string.IDS_network_connect_error));
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.h.b(new MediaCenterCallback() { // from class: doq
                @Override // com.huawei.health.musicservice.mediacenter.MediaCenterCallback
                public final void onResponse(int i, String str) {
                    MiniBottomPlayer.this.b(i, str);
                }
            }, "sleepMusic");
        } else {
            this.h.m();
            a(4);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void b(int i, String str) {
        LogUtil.a("MiniBottomPlayer", "checkWifiDialogBeforePlay when play = ", Integer.valueOf(i));
        if (i == 0) {
            this.h.k();
            a(3);
        } else {
            LogUtil.a("MiniBottomPlayer", "check mobile data dialog click cancel");
        }
    }

    private void f() {
        this.s.setOnClickListener(new View.OnClickListener() { // from class: dom
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiniBottomPlayer.this.Yh_(view);
            }
        });
    }

    public /* synthetic */ void Yh_(View view) {
        ReleaseLogUtil.e("R_MiniBottomPlayer", "jumpToPlayPage");
        m();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void m() {
        String str;
        enq currentAudioItem = getCurrentAudioItem();
        if (currentAudioItem != null) {
            str = TextUtils.isEmpty(currentAudioItem.i()) ? currentAudioItem.b() : currentAudioItem.i();
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MiniBottomPlayer", "jumpToPlayPage url invalid");
            return;
        }
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&playing=" : "?playing=");
        sb.append(this.h.g() == 3);
        String sb2 = sb.toString();
        LogUtil.a("MiniBottomPlayer", "jumpToPlayPage url :", sb2);
        ((MarketRouterApi) Services.c("FeatureMarketing", MarketRouterApi.class)).router(sb2);
    }

    private void j() {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: dop
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiniBottomPlayer.this.Yf_(view);
            }
        });
    }

    public /* synthetic */ void Yf_(View view) {
        LogUtil.a("MiniBottomPlayer", "jumpToListPage");
        g();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        this.b.setOnClickListener(new View.OnClickListener() { // from class: dor
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiniBottomPlayer.this.Ye_(view);
            }
        });
    }

    public /* synthetic */ void Ye_(View view) {
        LogUtil.a("MiniBottomPlayer", "closeMiniPlayer this = ", this);
        eoi.c().l();
        a(6);
        setVisibility(8);
        nkr.d().b(false);
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerProgress(int i) {
        if (this.k == i) {
            return;
        }
        LogUtil.c("MiniBottomPlayer", "setPlayerProgress progress = ", Integer.valueOf(i), " mProgress ", Integer.valueOf(this.k), ", this = ", this);
        this.k = i;
        this.n.setProgress(i);
    }

    private void a(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("controlType", 1);
        hashMap.put("event", Integer.valueOf(i));
        evl a2 = dow.c().a(this.h.f());
        if (a2 != null) {
            hashMap.put("serviceType", Integer.valueOf(a2.b() == 1 ? 3 : 2));
        }
        LogUtil.a("MiniBottomPlayer", "HEALTH_HEADLINES_CONTROL_USE_1100060 biMap = ", hashMap, ", this = ", this);
        ixx.d().d(this.e, AnalyticsValue.HEALTH_HEADLINES_CONTROL_USE_1100060.value(), hashMap, 0);
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void e() {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: doo
                @Override // java.lang.Runnable
                public final void run() {
                    MiniBottomPlayer.this.e();
                }
            });
            return;
        }
        setVisibility(8);
        b();
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }
        nkr.d().cwX_(null);
        nkr.d().b(false);
    }
}
