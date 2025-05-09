package com.huawei.ui.homehealth.healthheadlinecard.bottomplayer;

import android.content.Context;
import android.media.session.PlaybackState;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.vengine.H5ProViewControls;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressindicator.HealthProgressIndicator;
import com.huawei.ui.commonui.utils.TagResourcePolicy;
import com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber;
import com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter;
import com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView;
import com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.enq;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.okx;
import defpackage.oli;
import defpackage.omn;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class BottomPlayerView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f9463a;
    private ImageView b;
    private okx c;
    private ImageView d;
    private Context e;
    private MediaStatusSubscriber f;
    private ImageView g;
    private HealthTextView h;
    private oli i;
    private volatile String j;
    private View k;
    private HealthProgressIndicator m;
    private HealthTextView n;
    private int o;

    private int c(int i) {
        return i == 3 ? 1 : 0;
    }

    public BottomPlayerView(Context context, okx okxVar) {
        super(context);
        this.o = -1;
        this.i = oli.a();
        this.j = "";
        this.f = new MediaStatusSubscriberAdapter() { // from class: com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView.5
            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                BottomPlayerView bottomPlayerView = BottomPlayerView.this;
                bottomPlayerView.a(bottomPlayerView.i.n());
                if (playbackState.getState() == 1) {
                    BottomPlayerView.this.e(0.0f);
                }
            }

            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onMediaChanged(enq enqVar, int i) {
                LogUtil.a("BottomPlayerView", "onMediaChanged audioItem = ", enqVar, ", itemIndex = ", Integer.valueOf(i));
                BottomPlayerView.this.d(enqVar);
            }

            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onProgressChanged(int i, int i2, float f, int i3) {
                BottomPlayerView.this.e(f);
            }
        };
        this.e = context;
        this.c = okxVar;
        View inflate = inflate(getContext(), R.layout.commonui_bottom_player_layout, this);
        this.k = inflate;
        this.n = (HealthTextView) inflate.findViewById(R.id.float_music_name);
        this.h = (HealthTextView) this.k.findViewById(R.id.float_music_artist);
        this.f9463a = (ImageView) this.k.findViewById(R.id.float_music_image);
        this.d = (ImageView) this.k.findViewById(R.id.float_play_button);
        this.g = (ImageView) this.k.findViewById(R.id.float_list_button);
        this.b = (ImageView) this.k.findViewById(R.id.control_close);
        HealthProgressIndicator healthProgressIndicator = (HealthProgressIndicator) this.k.findViewById(R.id.bottom_player_media_indicator);
        this.m = healthProgressIndicator;
        healthProgressIndicator.setWaitingAnimationEnabled(false);
        this.k.setClickable(true);
        d();
        f();
    }

    public BottomPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = -1;
        this.i = oli.a();
        this.j = "";
        this.f = new MediaStatusSubscriberAdapter() { // from class: com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView.5
            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                BottomPlayerView bottomPlayerView = BottomPlayerView.this;
                bottomPlayerView.a(bottomPlayerView.i.n());
                if (playbackState.getState() == 1) {
                    BottomPlayerView.this.e(0.0f);
                }
            }

            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onMediaChanged(enq enqVar, int i) {
                LogUtil.a("BottomPlayerView", "onMediaChanged audioItem = ", enqVar, ", itemIndex = ", Integer.valueOf(i));
                BottomPlayerView.this.d(enqVar);
            }

            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onProgressChanged(int i, int i2, float f, int i3) {
                BottomPlayerView.this.e(f);
            }
        };
    }

    public BottomPlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = -1;
        this.i = oli.a();
        this.j = "";
        this.f = new MediaStatusSubscriberAdapter() { // from class: com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView.5
            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onPlaybackStateChanged(PlaybackState playbackState) {
                BottomPlayerView bottomPlayerView = BottomPlayerView.this;
                bottomPlayerView.a(bottomPlayerView.i.n());
                if (playbackState.getState() == 1) {
                    BottomPlayerView.this.e(0.0f);
                }
            }

            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onMediaChanged(enq enqVar, int i2) {
                LogUtil.a("BottomPlayerView", "onMediaChanged audioItem = ", enqVar, ", itemIndex = ", Integer.valueOf(i2));
                BottomPlayerView.this.d(enqVar);
            }

            @Override // com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriberAdapter, com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber
            public void onProgressChanged(int i2, int i22, float f, int i3) {
                BottomPlayerView.this.e(f);
            }
        };
    }

    private void d() {
        LogUtil.a("BottomPlayerView", "miniPlayer setClickListener, this = ", this);
        e();
        h();
        a();
        b();
    }

    private void f() {
        this.i.a(this.f);
    }

    public void c() {
        this.i.b(this.f);
    }

    public void e(enq enqVar, int i, float f) {
        d(enqVar);
        a(i);
        e(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView.2
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
                    BottomPlayerView.this.d.setImageDrawable(nsf.cKq_(i2));
                    jcf.bEE_(BottomPlayerView.this.d, 1);
                    jcf.bEz_(BottomPlayerView.this.d, nsf.h(i3));
                    return;
                }
                LogUtil.a("BottomPlayerView", "playState is loading, don't change the icon");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(enq enqVar) {
        LogUtil.a("BottomPlayerView", "refreshMediaInfo mMediaId = ", this.j, ", audioItem = ", enqVar, ", this = ", this);
        this.j = enqVar.h();
        HandlerExecutor.e(new AnonymousClass4(enqVar, new WeakReference(this.f9463a)));
    }

    /* renamed from: com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView$4, reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ enq c;
        final /* synthetic */ WeakReference e;

        AnonymousClass4(enq enqVar, WeakReference weakReference) {
            this.c = enqVar;
            this.e = weakReference;
        }

        @Override // java.lang.Runnable
        public void run() {
            BottomPlayerView.this.n.setText(this.c.n());
            BottomPlayerView.this.h.setText(this.c.f());
            nrf.cJf_((ImageView) this.e.get(), this.c.e(), nrf.e, this.c.h(), new TagResourcePolicy() { // from class: omo
                @Override // com.huawei.ui.commonui.utils.TagResourcePolicy
                public final boolean filterTagResource(String str) {
                    return BottomPlayerView.AnonymousClass4.this.e(str);
                }
            });
        }

        public /* synthetic */ boolean e(String str) {
            return BottomPlayerView.this.j.equals(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final float f) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView.3
            @Override // java.lang.Runnable
            public void run() {
                BottomPlayerView.this.setPlayerProgress((int) (f * 100.0f));
            }
        });
    }

    private void e() {
        this.d.setOnClickListener(new View.OnClickListener() { // from class: oml
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BottomPlayerView.this.dcw_(view);
            }
        });
    }

    public /* synthetic */ void dcw_(View view) {
        if (this.i.n() != 3) {
            if (!CommonUtil.aa(getContext())) {
                nrh.c(getContext(), getContext().getResources().getString(com.huawei.ui.main.R$string.IDS_network_connect_error));
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            omn.d(new MediaCenterCallback() { // from class: com.huawei.ui.homehealth.healthheadlinecard.bottomplayer.BottomPlayerView.1
                @Override // com.huawei.ui.homehealth.healthheadlinecard.utils.MediaCenterCallback
                public void onResponse(int i, String str) {
                    LogUtil.a("BottomPlayerView", "checkWifiDialogBeforePlay when play = ", Integer.valueOf(i));
                    if (i == 0) {
                        BottomPlayerView.this.i.ab();
                        BottomPlayerView.this.d(3);
                    } else {
                        LogUtil.a("BottomPlayerView", "check mobile data dialog click cancel");
                    }
                }
            });
        } else {
            this.i.ad();
            d(4);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        this.k.setOnClickListener(new View.OnClickListener() { // from class: omp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BottomPlayerView.this.dcx_(view);
            }
        });
    }

    public /* synthetic */ void dcx_(View view) {
        LogUtil.a("BottomPlayerView", "jumpToPlayPage");
        c(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        this.g.setOnClickListener(new View.OnClickListener() { // from class: omq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BottomPlayerView.this.dcv_(view);
            }
        });
    }

    public /* synthetic */ void dcv_(View view) {
        LogUtil.a("BottomPlayerView", "jumpToListPage");
        c(true);
        d(5);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(boolean z) {
        LogUtil.a("BottomPlayerView", "passParams isPlayList: ", Boolean.valueOf(z));
        int i = this.i.i();
        boolean z2 = true;
        if (i == 7) {
            a(i, z, false, true);
            return;
        }
        if (i != 8 && i != 9) {
            z2 = false;
        }
        a(i, z, z2, false);
    }

    private void b() {
        this.b.setOnClickListener(new View.OnClickListener() { // from class: omr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BottomPlayerView.this.dcu_(view);
            }
        });
    }

    public /* synthetic */ void dcu_(View view) {
        LogUtil.a("BottomPlayerView", "closeMiniPlayer this = ", this);
        Context context = this.e;
        if (!(context instanceof H5ProViewControls)) {
            LogUtil.b("BottomPlayerView", "closeMiniPlayer failed, cause mContext is not H5ProViewControls, mContext = ", context);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.c.e();
            oli.a().i(-5);
            d(6);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerProgress(int i) {
        LogUtil.a("BottomPlayerView", "setPlayerProgress progress = ", Integer.valueOf(i), " mProgress ", Integer.valueOf(this.o), ", this = ", this);
        if (this.o == i) {
            return;
        }
        this.o = i;
        this.m.setProgress(i);
    }

    private void a(int i, boolean z, boolean z2, boolean z3) {
        String h = this.i.f().h();
        String s = this.i.s();
        String j = this.i.j();
        int c = c(this.i.n());
        LogUtil.a("BottomPlayerView", "passIdToPlayPage workoutId: ", h, " type: ", Integer.valueOf(i), " topicId: ", s, " lecturerId:", j, " isPlayList: ", Boolean.valueOf(z), " isTopic: ", Boolean.valueOf(z2), " isLecturer: ", Boolean.valueOf(z3), " playStatus: ", Integer.valueOf(c));
        bzs e = bzs.e();
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        int i2 = i;
        if (i2 == 1) {
            i2 = 2;
        }
        builder.addPath("#/audio-detail").addCustomizeJsModule("healthHeadlines", okx.class).addCustomizeJsModule("innerapi", e.getCommonJsModule("innerapi")).addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement")).addCustomizeArg(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, h).addCustomizeArg("type", String.valueOf(i2)).addCustomizeArg("miniPlayerPlayStatus", String.valueOf(c)).setImmerse().setStatusBarTextBlack(true).enableSlowWholeDocumentDraw().showStatusBar();
        builder.addCustomizeArg("clickPosition", z ? "list" : "normal");
        if (z2) {
            builder.addCustomizeArg("topicId", s);
        }
        if (z3) {
            builder.addCustomizeArg("lecturerId", j);
        }
        H5ProClient.startH5MiniProgram(this.e, "com.huawei.health.h5.health-headlines", builder.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("controlType", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("serviceType", 1);
        LogUtil.a("BottomPlayerView", "HEALTH_HEADLINES_CONTROL_USE_1100060 healthHeadLinesMap = ", hashMap, ", this = ", this);
        ixx.d().d(this.e, AnalyticsValue.HEALTH_HEADLINES_CONTROL_USE_1100060.value(), hashMap, 0);
    }
}
