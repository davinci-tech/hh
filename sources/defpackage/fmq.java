package defpackage;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.mvp.ScreenController;
import com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer;
import com.huawei.health.suggestion.ui.fitness.mvp.ViewVideoPlayListener;
import com.huawei.health.suggestion.ui.fitness.mvp.VipViewShowProvider;
import com.huawei.health.suggestion.ui.view.AutoFillColorView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class fmq implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public HealthTextView f12564a;
    private ImageView ab;
    private ImageView ad;
    private ImageView af;
    private LinearLayout ag;
    private LinearLayout ah;
    private ImageView ai;
    private View aj;
    private FrameLayout ak;
    private FrameLayout al;
    private LinearLayout an;
    private LinearLayout ao;
    private RelativeLayout ap;
    private fpe ar;
    private HealthSeekBar as;
    private HealthTextView at;
    private String au;
    private ScreenController av;
    private HealthTextView ax;
    private FrameLayout ay;
    private int az;
    private HealthTextView b;
    private ViewVideoPlayListener ba;
    private VipViewShowProvider bb;
    private VideoPlayer bc;
    private ImageView bg;
    private HealthTextView bh;
    private AutoFillColorView c;
    private AudioManager d;
    private Context e;
    private CustomTitleBar f;
    private RelativeLayout g;
    private FitWorkout h;
    private ImageView i;
    private ImageView k;
    private View l;
    private HealthSeekBar m;
    private ImageView o;
    private ImageView p;
    private HealthTextView q;
    private FrameLayout r;
    private ImageView s;
    private HealthTextView t;
    private LinearLayout x;
    private boolean aq = false;
    private boolean y = false;
    private boolean u = false;
    private boolean z = false;
    private int am = 0;
    private int j = 0;
    private int ae = 0;
    private int n = 0;
    private Runnable aw = new c(this);
    private Handler v = new d(Looper.getMainLooper(), this);
    private boolean aa = false;
    private boolean ac = true;
    private boolean w = false;

    static class c implements Runnable {
        private WeakReference<fmq> e;

        c(fmq fmqVar) {
            this.e = new WeakReference<>(fmqVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<fmq> weakReference = this.e;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.h("Suggestion_VipPreview", "VipPreview is destroyed.");
                return;
            }
            fmq fmqVar = this.e.get();
            if (fmqVar.y) {
                fmqVar.ag.setVisibility(8);
                fmqVar.y = false;
            }
            if (fmqVar.u) {
                fmqVar.g.setVisibility(8);
                fmqVar.s.setVisibility(8);
                fmqVar.b.setVisibility(8);
                fmqVar.u = false;
            }
        }
    }

    static class d extends BaseHandler<fmq> {
        d(Looper looper, fmq fmqVar) {
            super(looper, fmqVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aAB_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(fmq fmqVar, Message message) {
            int i = message.what;
            if (i == 0) {
                if (message.obj instanceof Integer) {
                    fmqVar.j = ((Integer) message.obj).intValue();
                    if (fmqVar.z) {
                        fmqVar.al();
                        return;
                    } else {
                        fmqVar.aj();
                        return;
                    }
                }
                return;
            }
            if (i == 1) {
                if (message.obj instanceof Integer) {
                    fmqVar.am = ((Integer) message.obj).intValue();
                    fmqVar.ad();
                    return;
                }
                return;
            }
            if (i == 2 && (message.obj instanceof Integer)) {
                int intValue = ((Integer) message.obj).intValue();
                LogUtil.a("Suggestion_VipPreview", "MSG_PREVIEW_PLAY_STATUS_CHANGE state:", Integer.valueOf(intValue));
                fmqVar.d(intValue);
            }
        }
    }

    public fmq(Context context, FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3) {
        LogUtil.a("Suggestion_VipPreview", "VipPreview init");
        if (frameLayout == null || frameLayout2 == null) {
            LogUtil.b("Suggestion_VipPreview", "trainDetailContentLayout is null");
            return;
        }
        this.ay = frameLayout3;
        this.e = context;
        this.bc = new VideoPlayer(context);
        if (context.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager) {
            this.d = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
        }
        am();
        this.bc.b(new VideoPlayer.VideoPlayStateListener() { // from class: fmy
            @Override // com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.VideoPlayStateListener
            public final void onPlayStateChange(int i) {
                fmq.this.a(i);
            }
        });
        this.ak = frameLayout;
        this.al = frameLayout2;
        p();
        this.ar = new fpe(this.e, this.ak, this.al, this);
    }

    /* synthetic */ void a(int i) {
        LogUtil.c("Suggestion_VipPreview", "onPlayStateChange mVideoPlayer");
        Message obtainMessage = this.v.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = Integer.valueOf(i);
        this.v.sendMessage(obtainMessage);
    }

    private void am() {
        this.bc.a(new VideoPlayer.VideoCurrentPositionCallBack() { // from class: fmq.4
            @Override // com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.VideoCurrentPositionCallBack
            public void onCurrentPostionChange(int i) {
                if ((fmq.this.as == null || fmq.this.ax == null) && (fmq.this.m == null || fmq.this.t == null)) {
                    return;
                }
                Message obtainMessage = fmq.this.v.obtainMessage();
                obtainMessage.what = 0;
                obtainMessage.obj = Integer.valueOf(i);
                fmq.this.v.sendMessage(obtainMessage);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.mvp.VideoPlayer.VideoCurrentPositionCallBack
            public void onVideoDurationChange(int i) {
                if (fmq.this.ax == null && fmq.this.t == null) {
                    return;
                }
                fmq.this.aa = true;
                LogUtil.a("Suggestion_VipPreview", "onVideoDurationChange mIsFistClickPreStart:", Boolean.valueOf(fmq.this.ac), "mIsClicked:", Boolean.valueOf(fmq.this.w));
                if (fmq.this.w && fmq.this.ac && fmq.this.ae != 1) {
                    fmq.this.ab();
                    fmq.this.bc.c(0);
                    fmq.this.ac = false;
                }
                Message obtainMessage = fmq.this.v.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = Integer.valueOf(i);
                fmq.this.v.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        if (this.z) {
            b(i);
        } else {
            e(i);
        }
    }

    private void e(int i) {
        if (i == 1) {
            LogUtil.a("Suggestion_VipPreview", "STATE_SMALL_PLAYING");
            this.ae = 1;
            w();
        } else if (i == 2) {
            LogUtil.a("Suggestion_VipPreview", "STATE_SMALL_PAUSE");
            this.ae = 2;
            w();
        } else if (i == 0) {
            LogUtil.a("Suggestion_VipPreview", "STATE_SMALL_STOP");
            this.ae = 0;
            w();
        } else if (i == 3) {
            LogUtil.a("Suggestion_VipPreview", "STATE_SMALL_RESET");
            this.ae = 3;
            this.bc.j();
            this.bc.g();
            l();
        } else {
            LogUtil.h("Suggestion_VipPreview", "handlePlayStatusMessage else");
        }
        LogUtil.a("Suggestion_VipPreview", "handlePlayStatusMessage mPlayState:", Integer.valueOf(this.ae));
        this.aj.setVisibility(8);
    }

    private void b(int i) {
        if (i == 1) {
            LogUtil.c("Suggestion_VipPreview", "STATE_FULL_PLAYING");
            this.n = 1;
        } else if (i == 2) {
            LogUtil.c("Suggestion_VipPreview", "STATE_FULL_PAUSE");
            this.n = 2;
            o();
        } else if (i == 0) {
            LogUtil.c("Suggestion_VipPreview", "STATE_FULL_STOP");
            this.n = 0;
            o();
        } else {
            LogUtil.h("Suggestion_VipPreview", "handleFullPlayStatusMessage else");
        }
        this.l.setVisibility(8);
        LogUtil.c("Suggestion_VipPreview", "handleFullPlayStatusMessage mFullPlayState :", Integer.valueOf(this.n));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        int i = this.am;
        if (i > 0) {
            int i2 = this.j;
            if (i - i2 >= 0) {
                int i3 = this.ae;
                if (i3 == 1) {
                    this.as.setProgress(i2);
                    this.ax.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am - this.j)));
                } else if (i3 == 2) {
                    LogUtil.c("Suggestion_VipPreview", "upFullDateProgressAndTime STATE_SMALL_PAUSE");
                } else if (i3 == 0) {
                    this.as.setProgress(0);
                    this.ax.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am)));
                } else {
                    LogUtil.h("Suggestion_VipPreview", "updateProgressAndTime STATE OTHER");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        int i = this.am;
        if (i > 0) {
            int i2 = this.j;
            if (i - i2 >= 0) {
                int i3 = this.n;
                if (i3 == 1) {
                    this.m.setProgress(i2);
                    this.t.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am - this.j)));
                } else if (i3 == 2) {
                    LogUtil.c("Suggestion_VipPreview", "upFullDateProgressAndTime STATE_FULL_PAUSE");
                } else if (i3 == 0) {
                    this.m.setProgress(0);
                    this.t.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am)));
                } else {
                    LogUtil.h("Suggestion_VipPreview", "updataFullDateProgressAndTime STATE OTHER");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        LogUtil.a("Suggestion_VipPreview", "setPreviewTime mPreviewDuration :", Integer.valueOf(this.am));
        if (this.am > 0) {
            this.ax.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am)));
            this.t.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am)));
            this.as.setMax(this.am);
            this.m.setMax(this.am);
            this.ar.aCy_(r(), this.bh, this.ao);
        }
    }

    private String r() {
        if (this.am <= 0) {
            return "";
        }
        String a2 = ffy.a(TimeUnit.MILLISECONDS.toSeconds(this.am));
        this.f12564a.setText(ffy.d(this.e, R.string._2130848770_res_0x7f022c02, a2));
        if (a().h()) {
            this.f12564a.setText(ffy.d(this.e, R.string._2130848439_res_0x7f022ab7, new Object[0]));
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        AudioManager audioManager = this.d;
        if (audioManager == null) {
            LogUtil.b("Suggestion_VipPreview", "mAudioManager is null.");
        } else {
            LogUtil.a("Suggestion_VipPreview", "requestAudioFocus:", Integer.valueOf(audioManager.requestAudioFocus(null, 3, 1)));
        }
    }

    private void k() {
        AudioManager audioManager = this.d;
        if (audioManager == null) {
            LogUtil.b("Suggestion_VipPreview", "mAudioManager is null.");
        } else {
            LogUtil.a("Suggestion_VipPreview", "abandonMusicFocus:", Integer.valueOf(audioManager.abandonAudioFocus(null)));
        }
    }

    private void p() {
        this.aj = this.ak.findViewById(R.id.sug_preview_coach_loading_progress);
        this.c = (AutoFillColorView) this.ak.findViewById(R.id.auto_fill_color_view_preview);
        this.ap = (RelativeLayout) this.ak.findViewById(R.id.preview_start);
        this.af = (ImageView) this.ak.findViewById(R.id.preview_button);
        this.p = (ImageView) this.ak.findViewById(R.id.full_screen_change);
        this.ao = (LinearLayout) this.ak.findViewById(R.id.preview_tips);
        this.ad = (ImageView) this.ak.findViewById(R.id.pause_image);
        this.ai = (ImageView) this.ak.findViewById(R.id.play_image);
        this.ag = (LinearLayout) this.ak.findViewById(R.id.sug_preview_coach_control_);
        this.as = (HealthSeekBar) this.ak.findViewById(R.id.sug_preview_seekBar_progress);
        this.ax = (HealthTextView) this.ak.findViewById(R.id.sug_preview_tv_second);
        this.an = (LinearLayout) this.ak.findViewById(R.id.member_preview_header);
        this.f12564a = (HealthTextView) this.ak.findViewById(R.id.pre_time_try_watch);
        this.bh = (HealthTextView) this.ak.findViewById(R.id.pre_tips_goto_vip);
        this.l = this.al.findViewById(R.id.sug_full_preview_loading_progress);
        this.s = (ImageView) this.al.findViewById(R.id.full_screen_back);
        this.b = (HealthTextView) this.al.findViewById(R.id.course_title);
        this.i = (ImageView) this.al.findViewById(R.id.small_screen_change);
        this.bg = (ImageView) this.al.findViewById(R.id.volume_adjustment);
        this.r = (FrameLayout) this.al.findViewById(R.id.sfv_parent_preview);
        this.m = (HealthSeekBar) this.al.findViewById(R.id.sug_full_preview_seekBar_progress);
        this.k = (ImageView) this.al.findViewById(R.id.full_pause_image);
        this.o = (ImageView) this.al.findViewById(R.id.full_play_image);
        this.g = (RelativeLayout) this.al.findViewById(R.id.sug_full_coach_rl_actiontools);
        this.t = (HealthTextView) this.al.findViewById(R.id.sug_full_preview_tv_second);
        this.x = (LinearLayout) this.al.findViewById(R.id.full_screen_play_end);
        this.ab = (ImageView) this.al.findViewById(R.id.play_end_full_screen_sfv);
        this.ah = (LinearLayout) this.ak.findViewById(R.id.member_preview_end);
        this.at = (HealthTextView) this.ak.findViewById(R.id.re_start_play);
        this.q = (HealthTextView) this.al.findViewById(R.id.full_re_start_play);
        this.f = (CustomTitleBar) this.ak.findViewById(R.id.titlebar_panel);
        q();
        t();
        x();
        z();
        l();
    }

    private void t() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.an.getLayoutParams();
        layoutParams.topMargin = this.az;
        this.an.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.ah.getLayoutParams();
        layoutParams2.topMargin = this.az;
        this.ah.setLayoutParams(layoutParams2);
    }

    private void q() {
        CustomTitleBar customTitleBar = this.f;
        if (customTitleBar == null || customTitleBar.getHeight() == 0) {
            int dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131362340_res_0x7f0a0224);
            this.az = dimensionPixelSize;
            LogUtil.a("Suggestion_VipPreview", "getMarginTop marginTop default:", Integer.valueOf(dimensionPixelSize));
        } else {
            int height = this.f.getHeight();
            this.az = height;
            LogUtil.a("Suggestion_VipPreview", "getMarginTop marginTop:", Integer.valueOf(height));
        }
    }

    private void x() {
        this.g.setVisibility(8);
        this.s.setVisibility(8);
        this.b.setVisibility(8);
        this.x.setVisibility(8);
        this.ab.setVisibility(8);
        this.ah.setVisibility(8);
    }

    private void z() {
        this.ay.setOnClickListener(this);
        this.af.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.ad.setOnClickListener(this);
        this.ai.setVisibility(8);
        this.ai.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.bg.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.m.setTouchable(true);
        this.at.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.m.setOnSeekBarChangeListener(new HealthSeekBar.OnSeekBarChangeListener() { // from class: fmq.1
            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
                if (z) {
                    fmq.this.bc.l();
                    fmq.this.i(i);
                } else {
                    LogUtil.h("Suggestion_VipPreview", "onProgressChanged isFromUser is false.");
                }
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
                fmq.this.i(healthSeekBar.getProgress());
                fmq.this.bc.e(healthSeekBar.getProgress());
                fmq.this.bc.n();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i) {
        this.v.removeCallbacksAndMessages(null);
        this.m.setProgress(i);
        this.j = i;
        this.t.setText(new SimpleDateFormat("mm:ss", Locale.getDefault()).format(Integer.valueOf(this.am - this.j)));
    }

    public void c(FitWorkout fitWorkout) {
        this.w = false;
        this.ac = true;
        this.aa = false;
        LogUtil.a("Suggestion_VipPreview", "vipPreView bind");
        this.h = fitWorkout;
        if (TextUtils.isEmpty(fitWorkout.getPreviewVideoUrl())) {
            return;
        }
        this.au = fitWorkout.getPreviewVideoUrl();
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_VipPreview", "setCourseTitle title is null");
            return;
        }
        this.b.setText(str);
        if (a().h()) {
            this.f12564a.setText(ffy.d(this.e, R.string._2130848439_res_0x7f022ab7, new Object[0]));
        }
    }

    private void u() {
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("Suggestion_VipPreview", "Network is not Connected!");
            nrh.e(BaseApplication.e(), R.string._2130841083_res_0x7f020dfb);
        } else if (CommonUtil.l(BaseApplication.e()) != 1) {
            nrh.b(BaseApplication.e(), R.string._2130841376_res_0x7f020f20);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ImageView imageView = this.af;
        if (view == imageView) {
            aAA_(imageView);
        } else if (view == this.p) {
            ah();
        } else {
            ImageView imageView2 = this.ad;
            if (view == imageView2) {
                aAy_(imageView2);
            } else {
                ImageView imageView3 = this.k;
                if (view == imageView3) {
                    aAy_(imageView3);
                } else {
                    ImageView imageView4 = this.ai;
                    if (view == imageView4) {
                        aAA_(imageView4);
                    } else {
                        ImageView imageView5 = this.o;
                        if (view == imageView5) {
                            aAA_(imageView5);
                        } else if (view == this.at || view == this.q) {
                            this.z = false;
                            this.bc.e(0);
                            l();
                            v();
                        } else if (view == this.s || view == this.i) {
                            d();
                        } else if (view == this.bg) {
                            ak();
                        } else {
                            FrameLayout frameLayout = this.ay;
                            if (view == frameLayout) {
                                aAz_(frameLayout);
                            } else {
                                LogUtil.h("Suggestion_VipPreview", "onClick else");
                            }
                        }
                    }
                }
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void d(boolean z) {
        if (TextUtils.isEmpty(this.au)) {
            return;
        }
        LogUtil.c("Suggestion_VipPreview", "playerResourceLoading");
        this.bc.e();
        this.bc.aEW_(this.ay);
        this.bc.d(this.au, z);
        this.bc.c(false);
        if (z) {
            ab();
        }
    }

    public fpy a() {
        VipViewShowProvider vipViewShowProvider = this.bb;
        if (vipViewShowProvider == null) {
            LogUtil.h("Suggestion_VipPreview", "getAuditionCourseData() mVipViewShowProvider null");
            return new fpy();
        }
        fpy auditionCourseData = vipViewShowProvider.getAuditionCourseData();
        if (auditionCourseData != null) {
            return auditionCourseData;
        }
        LogUtil.h("Suggestion_VipPreview", "getAuditionCourseData() auditionCourseData null");
        return new fpy();
    }

    private void n() {
        LogUtil.c("Suggestion_VipPreview", "hideFullScreen");
        this.r.setVisibility(8);
        this.ab.setVisibility(8);
        this.x.setVisibility(8);
        this.z = false;
        ScreenController screenController = this.av;
        if (screenController != null) {
            screenController.setScreenOrientation(1);
            LogUtil.c("Suggestion_VipPreview", "SCREEN_ORIENTATION_PORTRAIT OVER");
        }
    }

    private void s() {
        this.an.setVisibility(8);
        this.ah.setVisibility(8);
        ScreenController screenController = this.av;
        if (screenController != null) {
            screenController.setScreenOrientation(0);
        }
        a(true);
    }

    public void a(boolean z) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.ay.getLayoutParams();
        if (z) {
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.topMargin = 0;
        } else {
            layoutParams.width = -1;
            layoutParams.height = this.e.getResources().getDimensionPixelSize(R.dimen._2131364636_res_0x7f0a0b1c);
            layoutParams.topMargin = this.az;
        }
        this.ay.setLayoutParams(layoutParams);
        SurfaceView aEP_ = this.bc.aEP_();
        if (aEP_ == null) {
            LogUtil.h("Suggestion_VipPreview", "setScreenLayout surfaceView == null");
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) aEP_.getLayoutParams();
        if (z) {
            layoutParams2.width = -1;
            layoutParams2.height = -1;
        } else {
            layoutParams2.width = -1;
            layoutParams2.height = this.e.getResources().getDimensionPixelSize(R.dimen._2131364636_res_0x7f0a0b1c);
        }
        aEP_.setLayoutParams(layoutParams2);
        aEP_.post(new Runnable() { // from class: fmx
            @Override // java.lang.Runnable
            public final void run() {
                fmq.this.e();
            }
        });
    }

    /* synthetic */ void e() {
        this.bc.i();
    }

    private void aa() {
        if (TextUtils.isEmpty(this.au)) {
            return;
        }
        LogUtil.c("Suggestion_VipPreview", "previewLoadingDisplay");
        this.aq = true;
        this.an.setVisibility(0);
        this.aj.setVisibility(0);
        this.c.setVisibility(8);
        this.ap.setVisibility(8);
        this.ah.setVisibility(8);
        this.r.setVisibility(8);
        this.ab.setVisibility(8);
        this.x.setVisibility(8);
        this.ay.setVisibility(8);
        LogUtil.c("Suggestion_VipPreview", "previewLoadingDisplay end");
    }

    private void l() {
        LogUtil.c("Suggestion_VipPreview", "displayDefaultPreview");
        this.ae = 0;
        this.n = 0;
        this.ag.setVisibility(8);
        this.an.setVisibility(0);
        this.c.setVisibility(0);
        this.ap.setVisibility(0);
        this.ah.setVisibility(8);
        this.ao.setVisibility(8);
        n();
        this.bc.j();
        this.bc.g();
        this.ay.setVisibility(8);
        LogUtil.c("Suggestion_VipPreview", "displayDefaultPreview end");
        r();
    }

    private void m() {
        this.r.setVisibility(0);
        this.s.setVisibility(0);
        this.b.setVisibility(0);
        this.ab.setVisibility(8);
        this.x.setVisibility(8);
        this.l.setVisibility(8);
        this.ay.setVisibility(0);
        LogUtil.c("Suggestion_VipPreview", "fullScreenSetVisibility");
    }

    private void o() {
        int i = this.n;
        if (i == 1) {
            LogUtil.c("Suggestion_VipPreview", "fullScreenPreviewDisplay mFullPlayState STATE_PLAYING");
            this.o.setVisibility(8);
            this.k.setVisibility(0);
        } else if (i == 2) {
            LogUtil.c("Suggestion_VipPreview", "fullScreenPreviewDisplay mFullPlayState STATE_PAUSE");
            this.o.setVisibility(0);
            this.k.setVisibility(8);
        } else if (i == 0) {
            LogUtil.c("Suggestion_VipPreview", "fullScreenPreviewDisplay mFullPlayState STATE_STOP");
            if (a().i()) {
                l();
            } else {
                j();
                this.x.setVisibility(0);
                this.s.setVisibility(0);
                this.b.setVisibility(0);
                this.ab.setVisibility(0);
            }
            this.g.setVisibility(8);
            this.j = 0;
            this.m.setProgress(0);
            this.l.setVisibility(8);
        } else {
            LogUtil.h("Suggestion_VipPreview", "fullScreenPreviewDisplay mFullPlayState else");
        }
        this.l.setVisibility(8);
        LogUtil.c("Suggestion_VipPreview", "fullScreenPreviewDisplay ok");
    }

    private void w() {
        int i = this.ae;
        if (i == 1) {
            LogUtil.c("Suggestion_VipPreview", "previewDisplay mPlayState STATE_PLAYING");
            if (!a().i() && this.aq) {
                this.ao.setVisibility(0);
                this.aq = false;
                this.v.removeCallbacks(this.aw);
                this.v.postDelayed(this.aw, 5000L);
            }
            this.ai.setVisibility(8);
            this.ad.setVisibility(0);
            ae();
            return;
        }
        if (i == 2) {
            LogUtil.c("Suggestion_VipPreview", "previewDisplay mPlayState STATE_PAUSE");
            this.ai.setVisibility(0);
            this.ad.setVisibility(8);
            ae();
            return;
        }
        if (i == 0) {
            LogUtil.c("Suggestion_VipPreview", "previewDisplay mPlayState STATE_STOP");
            if (a().i()) {
                l();
            } else {
                j();
                this.ah.setVisibility(0);
                this.an.setVisibility(8);
                this.c.setVisibility(8);
                this.ap.setVisibility(8);
            }
            this.ao.setVisibility(8);
            this.aj.setVisibility(8);
            this.j = 0;
            this.as.setProgress(0);
            LogUtil.a("Suggestion_VipPreview", "previewDisplay small stop");
            return;
        }
        LogUtil.c("Suggestion_VipPreview", "previewDisplay else");
    }

    private void aAy_(View view) {
        if (view == this.ad) {
            this.bc.a(true);
        } else if (view == this.k) {
            this.bc.a(true);
        } else {
            LogUtil.b("Suggestion_VipPreview", "clickPauseDealWith error click");
        }
    }

    private void aAA_(View view) {
        ImageView imageView = this.ai;
        if (view == imageView) {
            if (this.ae == 2) {
                imageView.setVisibility(8);
                this.ad.setVisibility(0);
                this.bc.d(this.j);
                return;
            }
            return;
        }
        ImageView imageView2 = this.o;
        if (view == imageView2) {
            if (this.n == 2) {
                imageView2.setVisibility(8);
                this.k.setVisibility(0);
                this.bc.d(this.j);
                return;
            }
            return;
        }
        if (view == this.af) {
            y();
        } else {
            LogUtil.b("Suggestion_VipPreview", "clickPlayDealWith error click");
        }
    }

    private void y() {
        if (a().h()) {
            LogUtil.a("Suggestion_VipPreview", "clickPlayDealWith isNormalStarPlay");
            ag();
        } else {
            v();
        }
    }

    private void ag() {
        ViewVideoPlayListener viewVideoPlayListener = this.ba;
        if (viewVideoPlayListener != null) {
            viewVideoPlayListener.startCoachPlay();
        }
    }

    private void v() {
        u();
        this.j = 0;
        if (TextUtils.isEmpty(this.au)) {
            return;
        }
        LogUtil.a("Suggestion_VipPreview", "clickPlayDealWith mPreviewButton");
        ViewVideoPlayListener viewVideoPlayListener = this.ba;
        if (viewVideoPlayListener != null) {
            viewVideoPlayListener.onVideoStartPlay();
        }
        this.w = true;
        if (this.ac) {
            if (this.aa) {
                ab();
                this.bc.c(0);
                this.ac = false;
            }
        } else {
            d(true);
        }
        aa();
        ggr.c(this.h, 0, this.am / 1000, 1);
    }

    private void aAz_(View view) {
        LogUtil.c("Suggestion_VipPreview", "clickPlayArea start");
        if (!this.z) {
            LogUtil.c("Suggestion_VipPreview", "clickPlayArea mSmallViewFrameLayout");
            int i = this.ae;
            if (i == 1 || i == 2) {
                LogUtil.a("Suggestion_VipPreview", "clickPlayArea mSmallViewFrameLayout mPlayState:", Integer.valueOf(i));
                this.ag.setVisibility(0);
                this.y = true;
                this.v.removeCallbacks(this.aw);
                this.v.postDelayed(this.aw, 7000L);
                return;
            }
            LogUtil.c("Suggestion_VipPreview", "onclick mSmallViewFrameLayout else");
            return;
        }
        LogUtil.c("Suggestion_VipPreview", "clickPlayArea mFullViewFrameLayout");
        int i2 = this.n;
        if (i2 == 1 || i2 == 2) {
            LogUtil.a("Suggestion_VipPreview", "clickPlayArea mFullViewFrameLayout mFullPlayState:", Integer.valueOf(i2));
            this.g.setVisibility(0);
            this.s.setVisibility(0);
            this.b.setVisibility(0);
            this.u = true;
            this.v.removeCallbacks(this.aw);
            this.v.postDelayed(this.aw, 7000L);
            return;
        }
        if (i2 == 0) {
            this.s.setVisibility(0);
            this.b.setVisibility(0);
            this.u = true;
            this.v.removeCallbacks(this.aw);
            this.v.postDelayed(this.aw, 7000L);
            return;
        }
        LogUtil.c("Suggestion_VipPreview", "onclick mFullViewFrameLayout else");
    }

    public void i() {
        if (TextUtils.isEmpty(this.au)) {
            return;
        }
        LogUtil.c("Suggestion_VipPreview", "switchBackToSmallScreenPlayer");
        ai();
        int i = this.n;
        if (i == 1) {
            LogUtil.c("Suggestion_VipPreview", "switchBackToSmallScreenPlayer mFullPlayState STATE_PLAYING");
            this.bc.d(this.j);
            this.ae = 1;
            this.ai.setVisibility(8);
            this.ad.setVisibility(0);
        } else if (i == 2) {
            LogUtil.c("Suggestion_VipPreview", "switchBackToSmallScreenPlayer mFullPlayState STATE_PAUSE");
            this.bc.a(false);
            this.ae = 2;
            this.ai.setVisibility(0);
            this.ad.setVisibility(8);
        } else {
            this.ae = 0;
            LogUtil.c("Suggestion_VipPreview", "switchBackToSmallScreenPlayer else");
        }
        this.bc.e(this.j);
    }

    private void ae() {
        this.an.setVisibility(0);
        this.ah.setVisibility(8);
        this.c.setVisibility(8);
        this.ap.setVisibility(8);
        this.aj.setVisibility(8);
        this.ay.setVisibility(0);
        a(false);
        LogUtil.c("Suggestion_VipPreview", "smallScreenSetVisibility");
    }

    private void ai() {
        LogUtil.c("Suggestion_VipPreview", "switchSmallScreenDisplay");
        this.z = false;
        LogUtil.a("Suggestion_VipPreview", "switchSmallScreenDisplay mPlaybackPosition:", Integer.valueOf(this.j));
        this.as.setProgress(this.j);
        this.ax.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am - this.j)));
        ae();
        n();
    }

    private void af() {
        LogUtil.c("Suggestion_VipPreview", "switchFullScreenDisplay");
        this.z = true;
        this.m.setProgress(this.j);
        this.t.setText(new SimpleDateFormat("mm:ss").format(Integer.valueOf(this.am - this.j)));
        m();
        s();
    }

    private void ah() {
        if (TextUtils.isEmpty(this.au)) {
            return;
        }
        af();
        int i = this.ae;
        if (i == 1) {
            LogUtil.c("Suggestion_VipPreview", "switchFullScreenPlayer mPlayState STATE_PLAYING");
            this.bc.d(this.j);
            this.n = 1;
            this.o.setVisibility(8);
            this.k.setVisibility(0);
        } else if (i == 2) {
            LogUtil.c("Suggestion_VipPreview", "switchFullScreenPlayer STATE_PAUSE");
            this.bc.a(false);
            this.n = 2;
            this.ab.setVisibility(8);
            this.x.setVisibility(8);
            this.o.setVisibility(0);
            this.k.setVisibility(8);
        } else {
            this.n = 0;
            LogUtil.c("Suggestion_VipPreview", "switchFullScreenPlayer else");
        }
        this.bc.e(this.j);
    }

    public boolean b() {
        return this.z;
    }

    private void ak() {
        foy.aCo_(this.e, this.bg);
    }

    public void b(ScreenController screenController) {
        this.av = screenController;
    }

    public void c(VipViewShowProvider vipViewShowProvider) {
        this.bb = vipViewShowProvider;
    }

    public void g() {
        LogUtil.c("Suggestion_VipPreview", "onPause");
        l();
    }

    public void d() {
        int i = this.n;
        if (i == 1 || i == 2) {
            i();
        } else {
            l();
        }
    }

    public void d(ViewVideoPlayListener viewVideoPlayListener) {
        this.ba = viewVideoPlayListener;
    }

    public void f() {
        LogUtil.a("Suggestion_VipPreview", "stopVideoPlay");
        l();
    }

    public void c() {
        LogUtil.a("Suggestion_VipPreview", "destroy.");
        VideoPlayer videoPlayer = this.bc;
        if (videoPlayer != null) {
            videoPlayer.a();
        }
        Handler handler = this.v;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        k();
        ggr.c(this.h, this.j / 1000, this.am / 1000, 0);
    }

    public void h() {
        ac();
        l();
        this.f12564a.setText(ffy.d(this.e, R.string._2130848439_res_0x7f022ab7, new Object[0]));
    }

    private void ac() {
        this.j = 0;
        this.z = false;
        this.bc.e(0);
    }

    public void j() {
        fpy a2 = a();
        LogUtil.a("Suggestion_VipPreview", "updateVipOrPayView vip:", Boolean.valueOf(a2.j()), "isBuy:", Boolean.valueOf(a2.a()), "commodityFlag:", Integer.valueOf(a2.c()));
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: fmu
                @Override // java.lang.Runnable
                public final void run() {
                    fmq.this.j();
                }
            });
            return;
        }
        if (this.ar == null) {
            LogUtil.h("Suggestion_VipPreview", "updateVipOrPayView mPreviewStatusHelper == null");
            return;
        }
        if (a2.h()) {
            this.f12564a.setText(ffy.d(this.e, R.string._2130848439_res_0x7f022ab7, new Object[0]));
            return;
        }
        if (a2.l()) {
            this.ar.openVipView();
            return;
        }
        if (a2.n()) {
            this.ar.payCourseHasVipPriceNotOpenVip();
        } else if (a2.k()) {
            this.ar.payCourseHasVipPriceByOpenVip();
        } else if (a2.o()) {
            this.ar.payCourseNotVipPriceView();
        }
    }

    public void c(int i) {
        ggr.c(this.h, this.j, this.am / 1000, i);
    }
}
