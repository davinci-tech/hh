package com.huawei.health.suggestion.ui.fitness.module;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.ui.fitness.activity.BaseCoachActivity;
import com.huawei.health.suggestion.ui.fitness.activity.LongCoachActivity;
import com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper;
import com.huawei.health.suggestion.ui.fitness.helper.MediaHelper;
import com.huawei.health.suggestion.ui.fitness.helper.VolumeChangeObserver;
import com.huawei.health.suggestion.ui.fitness.module.LongCoachView;
import com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler;
import com.huawei.health.suggestion.ui.view.AniFrameLayout;
import com.huawei.health.suggestion.ui.view.BrightnessOrVolumeProgressPlus;
import com.huawei.health.suggestion.util.HeartRateGetterUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.ash;
import defpackage.eil;
import defpackage.ffy;
import defpackage.fno;
import defpackage.fnu;
import defpackage.fnz;
import defpackage.fop;
import defpackage.fox;
import defpackage.foy;
import defpackage.foz;
import defpackage.fpb;
import defpackage.fpt;
import defpackage.fpz;
import defpackage.fqz;
import defpackage.frn;
import defpackage.frv;
import defpackage.frx;
import defpackage.fsg;
import defpackage.ght;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdt;
import defpackage.jed;
import defpackage.koq;
import defpackage.mld;
import defpackage.moe;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class LongCoachView extends RelativeLayout implements SurfaceHolder.Callback, View.OnClickListener, MediaPlayer.OnCompletionListener, GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, CoachViewInterface {

    /* renamed from: a, reason: collision with root package name */
    private String f3181a;
    private Handler aa;
    private ImageView ab;
    private ImageView ac;
    private int ad;
    private ImageView ae;
    private boolean af;
    private boolean ag;
    private ImageView ah;
    private ImageView ai;
    private boolean aj;
    private boolean ak;
    private boolean al;
    private boolean am;
    private boolean an;
    private boolean ao;
    private boolean ap;
    private boolean aq;
    private boolean ar;
    private boolean as;
    private boolean at;
    private boolean au;
    private boolean av;
    private boolean aw;
    private boolean ax;
    private int ay;
    private int az;
    private LongMediaHelper b;
    private boolean ba;
    private LinearLayout bb;
    private RelativeLayout bc;
    private OnLongVideoChangeListener bd;
    private LongMediaHelper be;
    private HealthSeekBar bf;
    private ImageView bg;
    private int bh;
    private DisplayMetrics bi;
    private OnMotionChangeListener bj;
    private LinearLayout bk;
    private List<Motion> bl;
    private c bm;
    private HealthTextView bn;
    private HealthTextView bo;
    private HealthTextView bp;
    private HealthTextView bq;
    private HealthTextView br;
    private RelativeLayout bs;
    private Runnable bt;
    private HealthTextView bu;
    private fsg bv;
    private ImageView bw;
    private int bx;
    private frx by;
    private float bz;
    private BrightnessOrVolumeProgressPlus c;
    private int ca;
    private int cb;
    private HealthTextView cc;
    private SurfaceView cd;
    private Map<Integer, fqz> ce;
    private int cf;
    private HealthTextView cg;
    private HealthTextView ch;
    private long ci;
    private long cj;
    private HealthTextView ck;
    private HealthTextView cl;
    private String cm;
    private long cn;
    private List<VideoSegment> co;
    private HealthProgressBar cp;
    private int cq;
    private float cr;
    private LinearLayout cs;
    private Consumer<Boolean> ct;
    private Handler cu;
    private VolumeChangeObserver cv;
    private ToolsLayout d;
    private long e;
    private fnu.e f;
    private int g;
    private frn h;
    private CaloriesConsumeHandler i;
    private String j;
    private int k;
    private String l;
    private Context m;
    private fpz n;
    private CoachPauseRestView o;
    private int p;
    private float q;
    private float r;
    private frv s;
    private DistributedApi t;
    private int u;
    private AniFrameLayout v;
    private String w;
    private fop x;
    private GestureDetector y;
    private SurfaceHolder z;

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public LongCoachView(Context context) {
        this(context, null);
    }

    public LongCoachView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LongCoachView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = new fpz();
        this.w = "NORMAL_MODE";
        this.al = true;
        this.aq = true;
        this.aa = new fox(this);
        this.ci = 0L;
        this.cj = 0L;
        this.ay = 0;
        this.cu = new fpt(this);
        this.bh = 0;
        this.co = new ArrayList(16);
        this.ax = false;
        this.aw = false;
        this.bz = 1.0f;
        this.an = false;
        this.ca = 10001;
        this.af = false;
        this.ap = false;
        this.as = false;
        this.m = context;
        LongMediaHelper longMediaHelper = new LongMediaHelper(context);
        this.be = longMediaHelper;
        longMediaHelper.aCq_().setOnCompletionListener(this);
        this.be.aCt_(new MediaPlayer.OnVideoSizeChangedListener() { // from class: fqg
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                LongCoachView.this.aDt_(mediaPlayer, i2, i3);
            }
        });
        this.bi = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(this.bi);
        }
        this.ad = this.bi.heightPixels;
        this.bt = new Runnable() { // from class: fqk
            @Override // java.lang.Runnable
            public final void run() {
                LongCoachView.this.ak();
            }
        };
        if (this.bm == null) {
            c cVar = new c(this);
            this.bm = cVar;
            jdt.c(context, true, cVar);
        }
        if (CommonUtil.bh() && CommonUtil.ba()) {
            DistributedApi distributedApi = (DistributedApi) Services.a("DistributedService", DistributedApi.class);
            this.t = distributedApi;
            if (distributedApi != null) {
                distributedApi.init(this.m);
                this.t.setHideNavigationBar(true);
                this.t.detectLastWirelessDevice();
                return;
            }
            LogUtil.b("Suggestion_LongCoachView", "can not get DistributedApi.");
        }
    }

    public /* synthetic */ void aDt_(MediaPlayer mediaPlayer, int i, int i2) {
        bc();
        az();
    }

    public /* synthetic */ void ak() {
        this.c.setVisibility(4);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        LogUtil.a("Suggestion_LongCoachView", "Long video completion");
        this.n.c(253);
        finishTrain();
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (!this.aw) {
            return true;
        }
        if (this.ap) {
            bv();
        } else {
            bb();
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.aw && !this.ap) {
            fnu.a(this.f, 1);
            ba();
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        c(false);
        if (this.be.aCq_() == null) {
            LogUtil.h("Suggestion_LongCoachView", "onDown mMediaHelper.getPlayer() is null.");
            return false;
        }
        this.ci = System.currentTimeMillis();
        this.k = this.be.aCq_().getCurrentPosition();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || motionEvent2 == null || this.ap || !af()) {
            LogUtil.h("Suggestion_LongCoachView", "lastEvent == null or curEvent == null mIsLocked:", Boolean.valueOf(this.ap));
            return false;
        }
        if (Math.abs(f2) > Math.abs(f) * 1.2d) {
            return aDp_(motionEvent, f2);
        }
        if (Math.abs(f) > Math.abs(f2) * 1.2d) {
            return aDm_(motionEvent, motionEvent2, f);
        }
        LogUtil.c("Suggestion_LongCoachView", "onScroll didn't set visibility");
        return false;
    }

    private boolean aDm_(MotionEvent motionEvent, MotionEvent motionEvent2, float f) {
        if (this.al) {
            this.al = false;
            this.aq = true;
        }
        if (!this.aq) {
            LogUtil.h("Suggestion_LongCoachView", "onScroll current sliding is horizontal, invalid.");
            return false;
        }
        if (!bp()) {
            LogUtil.h("Suggestion_LongCoachView", "leftRightDragOperation isCourseEntrance is true.");
            return false;
        }
        this.ax = true;
        this.aa.removeCallbacksAndMessages(null);
        long currentTimeMillis = System.currentTimeMillis();
        this.cj = currentTimeMillis;
        if (currentTimeMillis - this.ci < 300) {
            this.ay = motionEvent2.getX() - motionEvent.getX() > 0.0f ? 1 : -1;
            if (LanguageUtil.bc(this.m)) {
                this.ay = -this.ay;
            }
            return true;
        }
        if (LanguageUtil.bc(this.m)) {
            f = -f;
        }
        return c(f);
    }

    private boolean aDp_(MotionEvent motionEvent, float f) {
        if (EnvironmentInfo.k()) {
            LogUtil.h("Suggestion_LongCoachView", "upDownDragOperation mobile app engine mute volume and brightness.");
            return false;
        }
        if (this.al) {
            this.al = false;
            this.aq = false;
        }
        if (this.aq) {
            LogUtil.h("Suggestion_LongCoachView", "onScroll current sliding is vertical, invalid.");
            return false;
        }
        if (motionEvent.getX() < (getWidth() * 2.0f) / 5.0f) {
            if (LanguageUtil.bc(this.m)) {
                setVolume(f);
            } else {
                setBrightness(f / (this.ad / 2.0f));
            }
        } else if (LanguageUtil.bc(this.m)) {
            setBrightness(f / (this.ad / 2.0f));
        } else {
            setVolume(f);
        }
        this.c.setVisibility(0);
        return true;
    }

    private boolean c(float f) {
        MediaPlayer aCq_ = this.be.aCq_();
        if (aCq_ == null) {
            LogUtil.h("Suggestion_LongCoachView", "onScroll player is null.");
            return false;
        }
        this.ay = 0;
        LogUtil.a("Suggestion_LongCoachView", "dragScreen() player getDuration()");
        int i = this.k + ((int) (-((((f / getWidth()) * aCq_.getDuration()) * 5.0f) / 4.0f)));
        this.k = i;
        if (i < 0) {
            this.k = 0;
        }
        if (this.k >= aCq_.getDuration()) {
            this.k = aCq_.getDuration() - 1;
        }
        g(this.k);
        return true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        LogUtil.a("Suggestion_LongCoachView", "surfaceCreated--- mIsHolderCreated = ", Boolean.valueOf(this.ar));
        this.be.aCs_(surfaceHolder);
        if (koq.b(this.bl)) {
            LogUtil.a("Suggestion_LongCoachView", "surfaceCreated mMotions is empty");
            return;
        }
        if (!this.ar) {
            if (NetworkUtil.m()) {
                bg();
            }
        } else {
            this.au = false;
            bj();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        LogUtil.a("Suggestion_LongCoachView", "surfaceChanged");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.au = true;
        LogUtil.a("Suggestion_LongCoachView", "surfaceDestroyed");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_LongCoachView", "view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (foy.d(id)) {
            fnu.a(this.f, 2);
            au();
        } else if (foy.c(this, id)) {
            LogUtil.a("Suggestion_LongCoachView", ":training completed");
            if (nsn.o()) {
                LogUtil.a("Suggestion_LongCoachView", "isFastClick stop");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.af = true;
            if (an()) {
                fnu.a(this.f, 3);
            }
            OnMotionChangeListener onMotionChangeListener = this.bj;
            if (onMotionChangeListener != null) {
                onMotionChangeListener.onCustomBeave();
            }
        } else if (id == R.id.sug_playback_params) {
            this.bk.setVisibility(0);
        } else if (id == R.id.sug_playback_params_l1) {
            b(0.25f, this.br);
        } else if (id == R.id.sug_playback_params_l2) {
            b(0.5f, this.bq);
        } else if (id == R.id.sug_playback_params_l3) {
            b(1.0f, this.bn);
        } else if (id == R.id.sug_playback_params_l4) {
            b(1.5f, this.bp);
        } else if (id == R.id.sug_playback_params_l5) {
            b(2.0f, this.bu);
        } else {
            LogUtil.c("Suggestion_LongCoachView", "Click action is not Continue or Finish");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(float f, HealthTextView healthTextView) {
        LogUtil.a("Suggestion_LongCoachView", "onclick level", Float.valueOf(f));
        if (f == 0.25f) {
            this.bo.setText(getResources().getString(R.string._2130845032_res_0x7f021d68, UnitUtil.e(f, 1, 2)));
        } else {
            this.bo.setText(getResources().getString(R.string._2130845032_res_0x7f021d68, UnitUtil.e(f, 1, 1)));
        }
        setPlayerSpeed(f);
        this.bk.setVisibility(8);
        cd();
        healthTextView.setTextColor(ContextCompat.getColor(this.m, R.color._2131296651_res_0x7f09018b));
    }

    private void cd() {
        this.br.setTextColor(ContextCompat.getColor(this.m, R.color._2131299238_res_0x7f090ba6));
        this.bq.setTextColor(ContextCompat.getColor(this.m, R.color._2131299238_res_0x7f090ba6));
        this.bn.setTextColor(ContextCompat.getColor(this.m, R.color._2131299238_res_0x7f090ba6));
        this.bp.setTextColor(ContextCompat.getColor(this.m, R.color._2131299238_res_0x7f090ba6));
        this.bu.setTextColor(ContextCompat.getColor(this.m, R.color._2131299238_res_0x7f090ba6));
    }

    private void setPlayerSpeed(final float f) {
        this.bz = f;
        ThreadPoolManager.d().execute(new Runnable() { // from class: fqh
            @Override // java.lang.Runnable
            public final void run() {
                LongCoachView.this.b(f);
            }
        });
    }

    public /* synthetic */ void b(float f) {
        try {
            this.be.setPlaybackParams(new PlaybackParams().setSpeed(f));
        } catch (IllegalArgumentException | IllegalStateException unused) {
            LogUtil.b("Suggestion_LongCoachView", "setPlaybackParamsClickEvent IllegalArgumentException or IllegalStateException");
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(this.m).inflate(R.layout.sug_fitness_coachview, this);
        bd();
        SurfaceHolder holder = this.cd.getHolder();
        this.z = holder;
        holder.setType(3);
        this.z.addCallback(this);
        LongMediaHelper longMediaHelper = this.be;
        if (longMediaHelper != null) {
            longMediaHelper.aCv_(this.cd);
            this.be.aCw_((ViewGroup) findViewById(R.id.sfv_parent));
            ce();
        }
        this.cr = fnz.e();
        GestureDetector gestureDetector = new GestureDetector(this.m.getApplicationContext(), this);
        this.y = gestureDetector;
        gestureDetector.setOnDoubleTapListener(this);
        setTag(false);
    }

    public void d(String str) {
        this.w = str;
        int dimension = (int) getResources().getDimension(R.dimen._2131361997_res_0x7f0a00cd);
        int j = (nsn.j() / 2) - mld.d(this.m, 24.0f);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(dimension, -1, GravityCompat.END);
        if ("HORIZONTAL_FOLDING_MODE".equals(this.w)) {
            layoutParams = new FrameLayout.LayoutParams(-1, j, 48);
            layoutParams2 = new FrameLayout.LayoutParams(-1, j, 80);
            layoutParams3 = new FrameLayout.LayoutParams(dimension, j, 8388693);
        }
        aDo_(findViewById(R.id.sfv_parent), layoutParams);
        aDo_(this.bs, layoutParams);
        aDo_(this.v, layoutParams);
        aDo_(this.d, layoutParams2);
        aDo_(this.bc, layoutParams2);
        aDo_((RelativeLayout) findViewById(R.id.sug_progress_layout), layoutParams2);
        aDo_(this.c, layoutParams2);
        aDo_(this.o, layoutParams2);
        aDo_(this.bk, layoutParams3);
        fsg fsgVar = this.bv;
        if (fsgVar != null) {
            fsgVar.d(this.w);
        }
    }

    private void aDo_(View view, FrameLayout.LayoutParams layoutParams) {
        if (view == null) {
            LogUtil.h("Suggestion_LongCoachView", "setLayoutParamsByNoNull view null");
        } else {
            view.setLayoutParams(layoutParams);
        }
    }

    private void ce() {
        this.be.a(new LongMediaHelper.OnPreparedListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.LongCoachView.1
            @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
            public void onPrepared() {
                ReleaseLogUtil.e("Suggestion_LongCoachView", "setPlayerListener() onPrepared");
                LongCoachView.this.setLoadingStatus(false);
                LongCoachView.this.bf.setMax(LongCoachView.this.be.aCq_().getDuration());
                LongCoachView.this.bv.c(LongCoachView.this.be.aCq_().getDuration());
                LongCoachView.this.cl.setText(new SimpleDateFormat("mm:ss", Locale.getDefault()).format(Integer.valueOf(LongCoachView.this.be.aCq_().getDuration())));
                LongCoachView.this.cc();
                LongCoachView.this.bb();
                if (!LongCoachView.this.ah() && LongCoachView.this.am) {
                    LogUtil.a("Suggestion_LongCoachView", "CalorieHeartView setViewsVisible");
                    LongCoachView.this.h.g(0);
                }
                LongCoachView.this.bh();
            }

            @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
            public void onBufferingStart() {
                LogUtil.a("Suggestion_LongCoachView", "setPlayerListener() onBufferingStart");
                LongCoachView.this.setLoadingStatus(true);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
            public void onBufferingEnd() {
                LogUtil.a("Suggestion_LongCoachView", "setPlayerListener() onBufferingEnd");
                LongCoachView.this.setLoadingStatus(false);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                LogUtil.a("Suggestion_LongCoachView", "onSeekComplete:", Integer.valueOf(mediaPlayer.getCurrentPosition()));
                LongCoachView.this.bk();
            }

            @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
            public void onVideoError() {
                LogUtil.b("Suggestion_LongCoachView", "video play error");
                LongCoachView longCoachView = LongCoachView.this;
                longCoachView.b(true, CommonUtil.aa(longCoachView.m));
            }
        });
    }

    private boolean bp() {
        return !this.an;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cc() {
        this.be.aCq_().setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.LongCoachView.3
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                LogUtil.a("Suggestion_LongCoachView", "OnInfoListener", ", what=", Integer.valueOf(i), ", extra=", Integer.valueOf(i2));
                if (i == 804 || i == 805) {
                    if (LongCoachView.this.au) {
                        LogUtil.h("Suggestion_LongCoachView", "player is not playing.");
                        return false;
                    }
                    LongCoachView longCoachView = LongCoachView.this;
                    longCoachView.b(true, CommonUtil.aa(longCoachView.m));
                }
                if (i == 701) {
                    LongCoachView.this.setLoadingStatus(true);
                }
                if (i == 702) {
                    LongCoachView.this.setLoadingStatus(false);
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bk() {
        if (this.av) {
            if (CommonUtil.aa(this.m)) {
                cg();
                if (bq()) {
                    al();
                    return;
                }
                this.be.videoContinue();
                if (this.aa.hasMessages(259)) {
                    return;
                }
                this.aa.sendEmptyMessage(259);
                return;
            }
            return;
        }
        this.cf = 251;
        this.n.c(251);
        bw();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bh() {
        int oldProgressTime = getOldProgressTime();
        if (this.av && oldProgressTime > 0) {
            if (oldProgressTime >= this.be.f() && this.be.f() > 0) {
                this.be.e(0);
                this.ck.setTag(0);
            } else {
                this.be.e(oldProgressTime);
            }
        } else {
            cg();
        }
        LogUtil.a("Suggestion_LongCoachView", "checkPreparedNextStep(), onPrepared, seekTo msec=", Integer.valueOf(oldProgressTime));
        bf();
    }

    private int getOldProgressTime() {
        if (this.ck.getTag() instanceof Integer) {
            return ((Integer) this.ck.getTag()).intValue();
        }
        return 0;
    }

    private void cg() {
        this.av = false;
        this.ck.setTag(0);
    }

    private void bf() {
        LogUtil.a("Suggestion_LongCoachView", "allowPlay()");
        if (bn()) {
            LogUtil.a("Suggestion_LongCoachView", "allowPlay isShowWifiTipsDialog");
            return;
        }
        this.be.start();
        this.aw = true;
        this.cf = 251;
        this.n.c(251);
        this.d.setVisibility(0);
        bw();
        s();
        if (koq.b(this.bl, this.n.b())) {
            LogUtil.h("Suggestion_LongCoachView", "onPrepared mMotions isOutOfBounds");
            return;
        }
        Motion motion = this.bl.get(this.n.b());
        if (motion == null) {
            LogUtil.h("Suggestion_LongCoachView", "onPrepared currentMotion is null");
            return;
        }
        fpb.a(this, motion);
        OnMotionChangeListener onMotionChangeListener = this.bj;
        if (onMotionChangeListener != null) {
            onMotionChangeListener.onMotionStart(motion, this.n.b());
        }
        ImageView imageView = this.ae;
        if (imageView != null) {
            imageView.setImageResource(R.drawable._2131431648_res_0x7f0b10e0);
            this.ae.setTag(false);
        }
    }

    private void bw() {
        this.aa.sendEmptyMessage(259);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.b("Suggestion_LongCoachView", "onTouchEvent event == null");
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.cu.removeMessages(1201);
        } else if (action == 1) {
            if (!jcf.c()) {
                this.cu.removeMessages(1201);
                this.cu.sendMessageDelayed(this.cu.obtainMessage(1201), 7000L);
            }
            removeCallbacks(this.bt);
            postDelayed(this.bt, 1200L);
            this.bv.b();
            if (bp() && !this.al && this.aq) {
                if (this.ay != 0) {
                    br();
                } else {
                    setDragScreenBiEvent(this.k);
                    this.be.e(this.k);
                    LogUtil.a("Suggestion_LongCoachView", "onTouchEvent(),seekTo msec=", Integer.valueOf(this.k));
                }
            }
            bt();
        } else {
            LogUtil.c("Suggestion_LongCoachView", "do not handle this action");
            this.bk.setVisibility(8);
        }
        return this.y.onTouchEvent(motionEvent);
    }

    private void br() {
        int max;
        this.ax = true;
        MediaPlayer aCq_ = this.be.aCq_();
        if (aCq_ == null) {
            LogUtil.h("Suggestion_LongCoachView", "quickDrag player is null.");
            return;
        }
        if (this.co.size() == 1) {
            int currentPosition = aCq_.getCurrentPosition();
            LogUtil.a("Suggestion_LongCoachView", "quickDrag() player getDuration()");
            if (this.ay == 1) {
                max = Math.min(currentPosition + (aCq_.getDuration() / 5), aCq_.getDuration());
            } else {
                max = Math.max(currentPosition - (aCq_.getDuration() / 5), 0);
            }
            c(max, CoachController.StatusSource.APP);
            this.be.e(max);
            LogUtil.a("Suggestion_LongCoachView", "quickDrag(),seekTo msec=", Integer.valueOf(max));
            v();
            return;
        }
        aDl_(this.ay == 1 ? this.ab : this.ah);
    }

    private void bt() {
        this.ay = 0;
        this.ci = 0L;
        this.cj = 0L;
        this.al = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.bt);
        this.cu.removeCallbacksAndMessages(null);
    }

    private void bc() {
        LongMediaHelper longMediaHelper = this.be;
        int h = longMediaHelper != null ? longMediaHelper.h() : 0;
        frn frnVar = this.h;
        if (frnVar != null) {
            frnVar.a(h);
        }
    }

    private void az() {
        if (this.aj) {
            HealthTextView n = n();
            if (n == null) {
                LogUtil.h("Suggestion_LongCoachView", "adjustCaptionsMargin textView == null");
                return;
            }
            int e = nrr.e(this.m, 38.0f);
            LongMediaHelper longMediaHelper = this.be;
            if (longMediaHelper != null) {
                e = longMediaHelper.h() + nrr.e(this.m, 48.0f);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) n.getLayoutParams();
            layoutParams.setMarginStart(e);
            layoutParams.setMarginEnd(e);
            n.setLayoutParams(layoutParams);
        }
    }

    public void setBrightness(float f) {
        foy.d(this, f, getContext());
    }

    public void setVolume(float f) {
        if (!(getContext().getSystemService(PresenterUtils.AUDIO) instanceof AudioManager)) {
            LogUtil.h("Suggestion_LongCoachView", "setVolume getContext().getSystemService(AUDIO_SERVICE) not instanceof AudioManager");
            return;
        }
        AudioManager audioManager = (AudioManager) getContext().getSystemService(PresenterUtils.AUDIO);
        if (audioManager == null) {
            LogUtil.h("Suggestion_LongCoachView", "setVolume audioManager is null");
            return;
        }
        this.p = audioManager.getStreamVolume(3);
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        float f2 = this.q + f;
        this.q = f2;
        float f3 = streamMaxVolume;
        if (Math.abs(f2) >= this.ad / (2.0f * f3)) {
            if (this.q > 0.0f) {
                int i = this.p + 1;
                this.p = i;
                audioManager.setStreamVolume(3, i, 0);
            } else {
                int i2 = this.p - 1;
                this.p = i2;
                audioManager.setStreamVolume(3, i2, 0);
            }
            this.q = 0.0f;
        }
        this.c.setProgressMax(f3);
        if (this.p <= 0) {
            this.c.a(R.drawable._2131430556_res_0x7f0b0c9c);
            aDn_(this.ai, R.drawable._2131431659_res_0x7f0b10eb, R.drawable._2131431659_res_0x7f0b10eb);
            jcf.bEz_(this.ai, nsf.j(R.string._2130850644_res_0x7f023354));
        } else {
            this.c.a(R.drawable._2131430557_res_0x7f0b0c9d);
            aDn_(this.ai, R.drawable._2131431660_res_0x7f0b10ec, R.drawable._2131431658_res_0x7f0b10ea);
            jcf.bEz_(this.ai, nsf.j(R.string._2130850643_res_0x7f023353));
        }
        int i3 = this.p;
        if (i3 >= 0) {
            this.c.setProgress(i3);
        }
        this.c.d(getResources().getString(R.string._2130845620_res_0x7f021fb4));
    }

    public void ao() {
        if (!this.ar) {
            bg();
            return;
        }
        LogUtil.c("Suggestion_LongCoachView", "onResume===", Boolean.valueOf(this.au));
        ImageView imageView = this.ae;
        if (imageView != null) {
            imageView.setImageResource(R.drawable._2131431648_res_0x7f0b10e0);
            this.ae.setTag(false);
        }
        au();
    }

    public void s() {
        if (this.am) {
            this.h.b(0.0f, 1.0f);
        } else {
            this.h.d();
        }
    }

    private void bd() {
        this.bs = (RelativeLayout) findViewById(R.id.sug_coach_right_data_layout);
        frn frnVar = new frn();
        this.h = frnVar;
        this.bs.addView(frnVar.aEY_());
        this.h.g(8);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.sug_progress_layout);
        fsg fsgVar = new fsg();
        this.bv = fsgVar;
        relativeLayout.addView(fsgVar.aFH_());
        this.bv.b();
        this.bv.c();
        this.cs = (LinearLayout) findViewById(R.id.marketing_wound_plast_view);
        this.o = (CoachPauseRestView) findViewById(R.id.sug_coach_set_rl_show);
        this.v = (AniFrameLayout) findViewById(R.id.fl_animation);
        bi();
        findViewById(R.id.sug_coach_mp_progress).setVisibility(8);
        this.c = (BrightnessOrVolumeProgressPlus) findViewById(R.id.brightorvolumeprogressplus_setting);
        this.ac = (ImageView) findViewById(R.id.sug_coach_iv_lock);
        findViewById(R.id.sug_rl_coach_guide).setVisibility(8);
        findViewById(R.id.sug_coach_caintro).setVisibility(8);
        setLoadingStatus(true);
        this.cd.setTag(false);
        this.bw = (ImageView) findViewById(R.id.sug_screen_cast);
        bm();
        if (CommonUtil.bh() && CommonUtil.ba() && !EnvironmentInfo.k()) {
            this.bw.setVisibility(0);
            this.bw.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.LongCoachView.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TextUtils.isEmpty(ash.b("screenCastTipShow"))) {
                        LongCoachView.this.ck();
                    } else {
                        LongCoachView.this.cj();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        bu();
        cb();
        eil.alR_(10001, this.cs);
        foy.aCn_(this.m, this.ai);
    }

    private void bm() {
        this.bo = (HealthTextView) findViewById(R.id.sug_playback_params);
        this.br = (HealthTextView) findViewById(R.id.sug_playback_params_l1);
        this.bq = (HealthTextView) findViewById(R.id.sug_playback_params_l2);
        this.bn = (HealthTextView) findViewById(R.id.sug_playback_params_l3);
        this.bp = (HealthTextView) findViewById(R.id.sug_playback_params_l4);
        this.bu = (HealthTextView) findViewById(R.id.sug_playback_params_l5);
        this.bk = (LinearLayout) findViewById(R.id.sug_playback_params_layout);
        this.bo.setVisibility(0);
        bo();
        this.bo.setOnClickListener(this);
        this.br.setOnClickListener(this);
        this.bq.setOnClickListener(this);
        this.bn.setOnClickListener(this);
        this.bp.setOnClickListener(this);
        this.bu.setOnClickListener(this);
    }

    private void bi() {
        this.cp = (HealthProgressBar) findViewById(R.id.sug_coach_loading_progress);
        HealthSeekBar healthSeekBar = (HealthSeekBar) findViewById(R.id.sug_long_media_progress);
        this.bf = healthSeekBar;
        healthSeekBar.setVisibility(0);
        ToolsLayout toolsLayout = (ToolsLayout) findViewById(R.id.sug_coach_rl_actiontools);
        this.d = toolsLayout;
        toolsLayout.setIsShowLockAnim(false);
        this.ab = (ImageView) findViewById(R.id.sug_coach_iv_next);
        this.ae = (ImageView) findViewById(R.id.sug_coach_iv_pause);
        this.cd = (SurfaceView) findViewById(R.id.sfv);
        this.ai = (ImageView) findViewById(R.id.sug_iv_voice_change);
        this.ah = (ImageView) findViewById(R.id.sug_coach_iv_pre);
        this.cc = (HealthTextView) findViewById(R.id.sug_tv_coach_course_name);
        this.ck = (HealthTextView) findViewById(R.id.sug_coach_tv_second);
        this.cl = (HealthTextView) findViewById(R.id.tv_total_second);
        this.cg = (HealthTextView) findViewById(R.id.sug_tv_video_srt);
        this.ch = (HealthTextView) findViewById(R.id.sug_tv_video_srt_top);
        this.bc = (RelativeLayout) findViewById(R.id.lock_layout);
        this.bg = (ImageView) findViewById(R.id.sug_coach_iv_locked);
    }

    private void bu() {
        if (LanguageUtil.bc(this.m)) {
            ((ImageView) findViewById(R.id.sug_coachiv_close)).setImageDrawable(nrz.cKn_(this.m, R.drawable._2131431618_res_0x7f0b10c2));
            this.ac.setImageResource(R.drawable._2131430503_res_0x7f0b0c67);
        }
    }

    private void bo() {
        this.br.setText(getResources().getString(R.string._2130845032_res_0x7f021d68, UnitUtil.e(0.25d, 1, 2)));
        this.bq.setText(getResources().getString(R.string._2130845032_res_0x7f021d68, UnitUtil.e(0.5d, 1, 1)));
        this.bn.setText(getResources().getString(R.string._2130845032_res_0x7f021d68, UnitUtil.e(1.0d, 1, 1)));
        this.bp.setText(getResources().getString(R.string._2130845032_res_0x7f021d68, UnitUtil.e(1.5d, 1, 1)));
        this.bu.setText(getResources().getString(R.string._2130845032_res_0x7f021d68, UnitUtil.e(2.0d, 1, 1)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ck() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.m).e(this.m.getString(R.string._2130848592_res_0x7f022b50)).czE_(this.m.getString(R.string._2130841554_res_0x7f020fd2), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.LongCoachView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ash.a("screenCastTipShow", Boolean.toString(true));
                LongCoachView.this.cj();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
        LogUtil.a("Suggestion_LongCoachView", "showScreenCastTipDialog.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cj() {
        DistributedApi distributedApi = this.t;
        if (distributedApi != null) {
            int startWirelessProjection = distributedApi.startWirelessProjection();
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", 1);
            hashMap.put("courseId", this.j);
            hashMap.put("action", Integer.valueOf(startWirelessProjection));
            ixx.d().d(this.m, AnalyticsValue.HEALTH_GYM_EQUIP_CLICK_BTN_2170008.value(), hashMap, 0);
        }
    }

    private void cb() {
        by();
        bz();
        this.ai.setOnClickListener(new View.OnClickListener() { // from class: fqe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDA_(view);
            }
        });
        this.bg.setOnClickListener(new View.OnClickListener() { // from class: fqp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDB_(view);
            }
        });
    }

    public /* synthetic */ void aDA_(View view) {
        ch();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aDB_(View view) {
        this.ap = false;
        this.bc.setVisibility(8);
        bb();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bz() {
        this.ab.setOnClickListener(new View.OnClickListener() { // from class: fqf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDy_(view);
            }
        });
        this.o.getCoachSetStop().setOnClickListener(this);
        this.o.getCoachSetOk().setOnClickListener(this);
        this.o.getCoachSetContinue().setOnClickListener(new View.OnClickListener() { // from class: fqo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDz_(view);
            }
        });
    }

    public /* synthetic */ void aDy_(View view) {
        aDl_(this.ab);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aDz_(View view) {
        fnu.a(this.f, 2);
        au();
        LogUtil.a("Suggestion_LongCoachView", "Click Continue Button ,all continue ");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void by() {
        this.ac.setOnClickListener(new View.OnClickListener() { // from class: fqs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDv_(view);
            }
        });
        this.ae.setOnClickListener(new View.OnClickListener() { // from class: fqu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDw_(view);
            }
        });
        findViewById(R.id.sug_coachiv_close).setOnClickListener(new View.OnClickListener() { // from class: fqi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDx_(view);
            }
        });
        this.ah.setOnClickListener(new View.OnClickListener() { // from class: fqj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LongCoachView.this.aDu_(view);
            }
        });
    }

    public /* synthetic */ void aDv_(View view) {
        this.ap = true;
        bv();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aDw_(View view) {
        fnu.a(this.f, 1);
        ba();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aDx_(View view) {
        ab();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aDu_(View view) {
        aDl_(this.ah);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bg() {
        if (TextUtils.isEmpty(this.cm)) {
            ReleaseLogUtil.d("Suggestion_LongCoachView", "afterHolderCreate mVideoUrl null");
            return;
        }
        this.ar = true;
        this.cc.setText(this.j);
        this.be.setMediaSources(Uri.parse(this.cm));
        this.be.k();
        cf();
        this.cf = 251;
        this.n.c(251);
        foy.e(this, this.bl.size());
        this.h.d();
        this.d.setVisibility(8);
        VolumeChangeObserver volumeChangeObserver = new VolumeChangeObserver(getContext());
        this.cv = volumeChangeObserver;
        volumeChangeObserver.a(new VolumeChangeObserver.VolumeChangeListener() { // from class: fqn
            @Override // com.huawei.health.suggestion.ui.fitness.helper.VolumeChangeObserver.VolumeChangeListener
            public final void onVolumeChanged(int i) {
                LongCoachView.this.a(i);
            }
        });
        this.cv.a();
        this.ao = true;
        HeartRateGetterUtil.a().c(0, (CoachController.d().i() || this.an) ? false : true);
        CoachController.d().a(CoachController.StatusSource.APP, 1);
    }

    public /* synthetic */ void a(int i) {
        this.p = i;
        if (i <= 0) {
            aDn_(this.ai, R.drawable._2131431659_res_0x7f0b10eb, R.drawable._2131431659_res_0x7f0b10eb);
            jcf.bEz_(this.ai, nsf.j(R.string._2130850644_res_0x7f023354));
        } else {
            aDn_(this.ai, R.drawable._2131431660_res_0x7f0b10ec, R.drawable._2131431658_res_0x7f0b10ea);
            jcf.bEz_(this.ai, nsf.j(R.string._2130850643_res_0x7f023353));
        }
    }

    private void aDn_(ImageView imageView, int i, int i2) {
        if (LanguageUtil.bc(this.m)) {
            imageView.setImageDrawable(nrz.cKn_(this.m, i2));
        } else {
            imageView.setImageResource(i);
        }
    }

    public void av() {
        LogUtil.a("Suggestion_LongCoachView", "allPause()--");
        ImageView imageView = this.ae;
        if (imageView != null) {
            imageView.setImageResource(R.drawable._2131431649_res_0x7f0b10e1);
            this.ae.setTag(true);
        }
        if (this.bk.getVisibility() == 0) {
            this.bk.setVisibility(8);
        }
        setLoadingStatus(false);
        this.be.pause();
        LongMediaHelper longMediaHelper = this.b;
        if (longMediaHelper != null) {
            longMediaHelper.pause();
        }
        HeartRateGetterUtil.a().c(1, (CoachController.d().i() || this.an) ? false : true);
        CoachController.d().a(CoachController.StatusSource.APP, 2);
        this.aa.removeCallbacksAndMessages(null);
        if (this.bj != null && this.bl != null) {
            int b = this.n.b();
            if (koq.b(this.bl, b)) {
                return;
            }
            Motion motion = this.bl.get(b);
            if (motion != null) {
                this.bj.onMotionPause(motion, b);
            }
        }
        this.ao = false;
    }

    public void as() {
        LogUtil.a("Suggestion_LongCoachView", "onPause()--mIsHolderCreated=", Boolean.valueOf(this.ar));
        if (this.ar) {
            av();
            this.ao = false;
        }
    }

    public void al() {
        ReleaseLogUtil.e("Suggestion_LongCoachView", "onAllContinue");
        ImageView imageView = this.ae;
        if (imageView != null) {
            imageView.setImageResource(R.drawable._2131431648_res_0x7f0b10e0);
            this.ae.setTag(false);
        }
        if (foy.e(this.ar, this.n.c(), this.au)) {
            ReleaseLogUtil.e("Suggestion_LongCoachView", "onAllContinue ??");
            foy.aCl_(this, this.aa);
            LongMediaHelper longMediaHelper = this.b;
            if (longMediaHelper != null) {
                longMediaHelper.videoContinue();
            }
            this.n.c(251);
            HeartRateGetterUtil.a().c(0, (CoachController.d().i() || this.an) ? false : true);
            CoachController.d().a(CoachController.StatusSource.APP, 1);
            this.aa.sendEmptyMessage(259);
            if (this.bj != null && this.bl != null) {
                int b = this.n.b();
                if (koq.b(this.bl, b)) {
                    return;
                }
                Motion motion = this.bl.get(b);
                if (motion != null) {
                    this.bj.onMotionContinue(motion, b);
                }
            }
            this.ao = true;
        }
    }

    private void bj() {
        ReleaseLogUtil.e("Suggestion_LongCoachView", "allContinue", ",mIsPlaying=", Boolean.valueOf(this.ao), ",mIsHolderCreated=", Boolean.valueOf(this.ar), ",mCoachState.acquireStation()=", Integer.valueOf(this.n.c()), ",mIsSurfaceDestroyed=", Boolean.valueOf(this.au));
        if (this.ao || !foy.e(this.ar, this.n.c(), this.au)) {
            return;
        }
        if (this.av && CommonUtil.aa(this.m)) {
            cl();
        } else {
            al();
        }
    }

    public boolean af() {
        return this.ao;
    }

    private void bv() {
        this.d.setVisibility(4);
        jcf.bEk_(this.d, nsf.j(R.string._2130850648_res_0x7f023358));
        this.cu.removeCallbacksAndMessages(null);
        if (this.aj) {
            this.cu.sendEmptyMessage(1202);
        }
        this.cu.sendMessage(this.cu.obtainMessage(1204));
        this.cu.sendMessageDelayed(this.cu.obtainMessage(1203), 7000L);
    }

    private void aDl_(ImageView imageView) {
        if (imageView.getAlpha() == 1.0f) {
            this.aa.removeCallbacksAndMessages(null);
            this.v.a();
            this.cu.removeMessages(1201);
            this.d.setIsShowBottomProgress(false);
            if (imageView == this.ah) {
                this.ax = true;
                be();
                LogUtil.a("Suggestion_LongCoachView", "change to previous action");
            } else {
                e(true);
                LogUtil.a("Suggestion_LongCoachView", "change to next action");
            }
        }
    }

    public void ab() {
        OnMotionChangeListener onMotionChangeListener = this.bj;
        if (onMotionChangeListener == null) {
            LogUtil.b("Suggestion_LongCoachView", "mMotionChangeListener null");
        } else {
            onMotionChangeListener.onCustomBeave();
        }
    }

    public void x() {
        LogUtil.a("Suggestion_LongCoachView", "changeMotionWhenPlaying==", Integer.valueOf(this.n.b()));
        if (this.n.b() != this.co.size() - 1) {
            e(false);
        }
    }

    public void ad() {
        LogUtil.c("Suggestion_LongCoachView", "finishAction action train time==", Long.valueOf(this.e));
        this.cn += this.e;
        Motion bs = bs();
        this.e = 0L;
        this.cn = (this.cn / 1000) * 1000;
        LogUtil.c("Suggestion_LongCoachView", "finishAction action completed");
        this.n.b(0);
        foy.c(this, bs);
        this.cf = 257;
        this.n.c(257);
        this.aa.sendEmptyMessage(257);
    }

    private Motion bs() {
        LogUtil.a("Suggestion_LongCoachView", "saveProgress, current action:{} current group:{} current beat:{}", Integer.valueOf(this.n.b()), Integer.valueOf(this.n.e()), Integer.valueOf(this.n.d()));
        if (koq.b(this.bl, this.n.b())) {
            return new Motion();
        }
        Motion motion = this.bl.get(this.n.b());
        if (motion == null) {
            return new Motion();
        }
        int round = Math.round(this.e / 1000.0f);
        if (this.ag && !"timer".equals(motion.acquireMotionType())) {
            round--;
        }
        if (koq.b(motion.getVideoSegments(), 0)) {
            LogUtil.h("Suggestion_LongCoachView", "saveProgress() getVideoSegments out of bounds");
            return motion;
        }
        int duration = motion.getVideoSegments().get(0).getDuration();
        if (duration == 0) {
            LogUtil.h("Suggestion_LongCoachView", "saveProgress() videoSegment duration is zero");
            return motion;
        }
        foy.a(this, (round * 1.0f) / duration);
        if (round > motion.acquireActionTrainTime()) {
            motion.saveActionTrainTime(round);
        }
        if (motion.acquireActionTrainTime() > duration) {
            motion.saveActionTrainTime(duration);
        }
        return motion;
    }

    public void v() {
        LogUtil.c("Suggestion_LongCoachView", "changeAction", Integer.valueOf(this.n.b()));
        foy.e(this, this.bl.size());
    }

    private void be() {
        if (this.o.getVisibility() == 0) {
            this.o.setVisibility(4);
        }
        ad();
        this.n.a();
        if (this.n.b() < 0) {
            this.n.d(0);
            return;
        }
        VideoSegment videoSegment = this.co.get(this.n.b());
        int endTime = (int) videoSegment.getEndTime();
        c(endTime, CoachController.StatusSource.APP);
        this.be.e(endTime);
        LogUtil.a("Suggestion_LongCoachView", "changeToPreAction(),seekTo msec=", Long.valueOf(videoSegment.getStartTime()));
        LogUtil.a("Suggestion_LongCoachView", "change to previous action, current action:{}", Integer.valueOf(this.n.b()));
        this.cf = 251;
        this.n.c(251);
        v();
    }

    private void e(boolean z) {
        if (this.o.getVisibility() == 0) {
            this.o.setVisibility(4);
        }
        ad();
        this.n.f();
        if (this.bl == null) {
            ReleaseLogUtil.d("Suggestion_LongCoachView", "changeToNextAction mMotions null");
            return;
        }
        if (this.n.b() >= this.bl.size()) {
            this.n.d(this.bl.size() - 1);
            return;
        }
        LogUtil.a("Suggestion_LongCoachView", "change to next action, current action:{}", Integer.valueOf(this.n.b()));
        if (z && this.co.get(this.n.b()) != null) {
            LogUtil.c("Suggestion_LongCoachView", "remove long coach training");
            this.aa.removeMessages(251);
            VideoSegment videoSegment = this.co.get(this.n.b());
            c((int) videoSegment.getStartTime(), CoachController.StatusSource.APP);
            this.be.e((int) videoSegment.getStartTime());
            this.ax = true;
        } else {
            bw();
        }
        this.cf = 251;
        this.n.c(251);
        v();
    }

    public void y() {
        fpz fpzVar = this.n;
        if (fpzVar == null || this.co.get(fpzVar.b()) == null || this.bf == null) {
            ReleaseLogUtil.e("Suggestion_LongCoachView", "chapterForward mCoachState = ", this.n, " mLongMediaSeekBar ", this.bf);
            return;
        }
        VideoSegment videoSegment = this.co.get(this.n.b());
        long progress = this.bf.getProgress();
        long endTime = videoSegment.getEndTime() - progress;
        h(endTime >= PreConnectManager.CONNECT_INTERNAL ? (int) (progress + PreConnectManager.CONNECT_INTERNAL) : (int) (PreConnectManager.CONNECT_INTERNAL - endTime));
    }

    public void w() {
        fpz fpzVar = this.n;
        if (fpzVar == null || this.co.get(fpzVar.b()) == null || this.bf == null) {
            ReleaseLogUtil.e("Suggestion_LongCoachView", "chapterForward mCoachState = ", this.n, " mLongMediaSeekBar ", this.bf);
            return;
        }
        VideoSegment videoSegment = this.co.get(this.n.b());
        long progress = this.bf.getProgress();
        long startTime = progress - videoSegment.getStartTime();
        h(startTime >= PreConnectManager.CONNECT_INTERNAL ? (int) (progress - PreConnectManager.CONNECT_INTERNAL) : (int) (PreConnectManager.CONNECT_INTERNAL - startTime));
    }

    private void h(int i) {
        c(i, CoachController.StatusSource.NEW_LINK_WEAR);
        this.bf.setProgress(i);
        this.be.e(i);
        this.ax = true;
        ReleaseLogUtil.e("Suggestion_LongCoachView", "chapterForward mCoachState = ", this.n, " mLongMediaSeekBar ", this.bf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HealthSeekBar healthSeekBar) {
        fsg fsgVar = this.bv;
        if (fsgVar != null) {
            fsgVar.b();
        }
        this.ax = true;
        if (healthSeekBar == null) {
            ReleaseLogUtil.d("Suggestion_LongCoachView", "seekTo healthSeekBar is null");
            return;
        }
        int progress = healthSeekBar.getProgress();
        if (this.be == null) {
            ReleaseLogUtil.d("Suggestion_LongCoachView", "seekTo mMediaHelper is null");
            return;
        }
        Handler handler = this.aa;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        setDragScreenBiEvent(progress);
        this.be.e(progress);
        LogUtil.a("Suggestion_LongCoachView", "seekTo progress ", Integer.valueOf(progress));
    }

    private void ch() {
        foy.aCo_(getContext(), this.ai);
    }

    public void ba() {
        if (this.aj) {
            this.cu.removeMessages(1202);
        }
        removeCallbacks(this.bt);
        post(this.bt);
        av();
        this.n.c(192);
        if (koq.b(this.bl, this.n.b())) {
            return;
        }
        Motion motion = this.bl.get(this.n.b());
        String string = getResources().getString(R.string._2130851561_res_0x7f0236e9);
        if (motion == null) {
            LogUtil.h("Suggestion_LongCoachView", "toPause motion is null");
            return;
        }
        String str = "";
        this.o.b(this.j, ffy.d(getContext().getApplicationContext(), R.string._2130837535_res_0x7f02001f, moe.d((((this.cr * motion.acquireCalorie()) * motion.acquireGroups()) * motion.acquireDuration()) / 1000.0f)), ffy.d(getContext().getApplicationContext(), R.string._2130848384_res_0x7f022a80, moe.f(motion.acquireDuration() * motion.acquireGroups())), this.ak ? "" : foz.e(motion, string), this.l);
        if (this.an) {
            int i = this.u;
            if (i == 1) {
                str = this.m.getResources().getString(R.string._2130848674_res_0x7f022ba2);
            } else if (i == 2) {
                str = this.m.getResources().getString(R.string._2130848675_res_0x7f022ba3, 60);
            } else {
                LogUtil.h("Suggestion_LongCoachView", "toPause other mEquipmentType: ", Integer.valueOf(i));
            }
            this.o.setEquipmentPauseTips(str);
            this.o.e();
        }
        jcf.bEE_(this.d, 4);
        jcf.bEE_(this.bs, 4);
        jcf.bEE_(this.v, 4);
    }

    public void au() {
        if (this.av && !CommonUtil.aa(this.m)) {
            nrh.d(this.m, this.m.getResources().getString(R$string.IDS_connect_error));
            return;
        }
        if (this.aj) {
            this.cu.sendEmptyMessage(1202);
        }
        this.n.c(this.cf);
        if (this.av) {
            LogUtil.a("Suggestion_LongCoachView", "mIsErrorByPlayVideo， after resume paly");
            cl();
        } else {
            LogUtil.a("Suggestion_LongCoachView", "toContinue, onAllContinue");
            al();
        }
        this.o.setVisibility(4);
        jcf.bEE_(this.d, 0);
        jcf.bEE_(this.bs, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, boolean z2) {
        if (this.av) {
            return;
        }
        if (z2) {
            if (z) {
                this.av = true;
                cl();
                return;
            }
            return;
        }
        LogUtil.a("Suggestion_LongCoachView", "setStuckErrorStateAndHints, is not network connect, isOnVideoError=", Boolean.valueOf(z));
        this.av = true;
        nrh.c(this.m, this.m.getResources().getString(R$string.IDS_connect_error));
        setLoadingStatus(true);
    }

    public void u() {
        LogUtil.a("Suggestion_LongCoachView", "checkResumePlayback, mIsStuckOrErrorByMediaPlayer=", Boolean.valueOf(this.av), ", mIsPlaying=", Boolean.valueOf(this.ao), ", isPause()=", Boolean.valueOf(bq()));
        if (!this.av || bq()) {
            return;
        }
        LogUtil.a("Suggestion_LongCoachView", "checkResumePlayback, resume paly");
        cl();
    }

    private boolean bq() {
        if (this.ae.getTag() instanceof Boolean) {
            return ((Boolean) this.ae.getTag()).booleanValue();
        }
        return false;
    }

    public void setEquipmentCourseAndType(boolean z, int i) {
        LogUtil.a("Suggestion_LongCoachView", "setEquipmentCourseAndType isEquipmentCourse = ", Boolean.valueOf(z), " equipmentType = ", Integer.valueOf(i));
        this.ak = z;
        this.u = i;
    }

    public void r() {
        List<Motion> list = this.bl;
        if (list == null) {
            ReleaseLogUtil.d("Suggestion_LongCoachView", "addEquipmentCourseTimeCounterView mMotions == null");
            return;
        }
        if (list.size() == 1) {
            ReleaseLogUtil.e("Suggestion_LongCoachView", "addEquipmentCourseTimeCounterView mMotions.size() == 1");
        } else {
            if (!this.ak || ah()) {
                return;
            }
            frv frvVar = new frv();
            this.s = frvVar;
            this.bs.addView(frvVar.aFl_());
        }
    }

    public void setTimeView(int i, int i2, boolean z) {
        frv frvVar;
        if (!this.ak || (frvVar = this.s) == null) {
            return;
        }
        frvVar.a(i, i2, z);
    }

    private void cl() {
        if (this.be == null || TextUtils.isEmpty(this.cm)) {
            ReleaseLogUtil.d("Suggestion_LongCoachView", "stuckResumePlayback() mMediaHelper or mVideoUrl is null ");
            return;
        }
        setLoadingStatus(true);
        MediaPlayer aCq_ = this.be.aCq_();
        if (aCq_ == null) {
            ReleaseLogUtil.d("Suggestion_LongCoachView", "stuckResumePlayback() player  is null ");
            return;
        }
        this.ck.setTag(Integer.valueOf(aCq_.getCurrentPosition()));
        this.be.setMediaSources(Uri.parse(this.cm));
        setPlayerSpeed(this.bz);
        LongMediaHelper longMediaHelper = this.b;
        if (longMediaHelper == null || longMediaHelper.o()) {
            return;
        }
        this.b.videoContinue();
    }

    public void setMotions(List<Motion> list) {
        this.bl = list;
        this.h.d((int) fno.d(list, this.cr, true));
        int i = 0;
        for (Motion motion : list) {
            if (motion == null) {
                LogUtil.h("Suggestion_LongCoachView", "setMotions() motion is null");
            } else if (koq.b(motion.getVideoSegments(), 0)) {
                LogUtil.h("Suggestion_LongCoachView", "setMotions() VideoSegments is empty");
            } else {
                VideoSegment videoSegment = motion.getVideoSegments().get(0);
                if (videoSegment != null) {
                    this.co.add(videoSegment);
                    i += videoSegment.getDuration();
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<VideoSegment> it = this.co.iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                if (i == 0) {
                    i = 1;
                }
                arrayList.add(Float.valueOf((r3.getStartTime() / 1000.0f) / i));
            }
        }
        this.bf.setNodeList(arrayList);
        ci();
    }

    public List<VideoSegment> getVideoSegments() {
        return this.co;
    }

    public void aw() {
        LogUtil.a("Suggestion_LongCoachView", "CoachView release---");
        jdt.c(this.m, false, this.bm);
        this.aa.removeCallbacksAndMessages(null);
        LongMediaHelper longMediaHelper = this.be;
        if (longMediaHelper != null) {
            longMediaHelper.m();
            this.be.release();
        }
        VolumeChangeObserver volumeChangeObserver = this.cv;
        if (volumeChangeObserver != null) {
            volumeChangeObserver.b();
        }
        DistributedApi distributedApi = this.t;
        if (distributedApi != null) {
            distributedApi.destroyWirelessProjection();
        }
        if (this.u == 2) {
            ((IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class)).setSuppressPause(false);
            LogUtil.a("Suggestion_LongCoachView", "closeImgAction, setSuppressPause false");
        }
        LongMediaHelper longMediaHelper2 = this.b;
        if (longMediaHelper2 != null) {
            longMediaHelper2.m();
            this.b.release();
        }
        fop.a();
        CoachController.d().a(CoachController.StatusSource.APP, 3);
    }

    public void e(OnMotionChangeListener onMotionChangeListener) {
        this.bj = onMotionChangeListener;
    }

    public void setLongVideoChangeListener(OnLongVideoChangeListener onLongVideoChangeListener) {
        this.bd = onLongVideoChangeListener;
    }

    public int getTrainStation() {
        return this.n.c();
    }

    public void bb() {
        this.d.setVisibility(this.d.getVisibility() == 0 ? 4 : 0);
        if (jcf.c()) {
            LogUtil.a("Suggestion_LongCoachView", "toggleTool isTouchExplorationEnabled");
            ToolsLayout toolsLayout = this.d;
            jcf.bEk_(toolsLayout, nsf.j(toolsLayout.getVisibility() == 0 ? R.string._2130850649_res_0x7f023359 : R.string._2130850648_res_0x7f023358));
        } else {
            this.cu.removeMessages(1201);
            this.cu.sendMessageDelayed(this.cu.obtainMessage(1201), 7000L);
        }
    }

    public void setDeviceConnected() {
        this.ag = true;
        this.at = true;
    }

    public void aa() {
        this.ag = false;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.CoachViewInterface
    public void finishTrain() {
        OnMotionChangeListener onMotionChangeListener;
        OnLongVideoChangeListener onLongVideoChangeListener;
        OnLongVideoChangeListener onLongVideoChangeListener2;
        LogUtil.a("Suggestion_LongCoachView", "finishTrain-----");
        Motion bs = bs();
        this.n.c(-100);
        if (bs == null || bs.getVideoSegments() == null) {
            LogUtil.h("Suggestion_LongCoachView", "finishMotion or VideoSegment is null");
            return;
        }
        List<VideoSegment> videoSegments = bs.getVideoSegments();
        if (koq.b(videoSegments, 0)) {
            LogUtil.h("Suggestion_LongCoachView", "videoSegments 0 is out bound");
            return;
        }
        VideoSegment videoSegment = videoSegments.get(0);
        if (videoSegment == null) {
            LogUtil.h("Suggestion_LongCoachView", "segment is null");
            return;
        }
        if (ah() && (onLongVideoChangeListener2 = this.bd) != null) {
            onLongVideoChangeListener2.onLongExplanationVideoComplete(getLongExplanationVideoWatchTime(), videoSegment.getDuration() * 1000);
        }
        if (ai() && (onLongVideoChangeListener = this.bd) != null) {
            onLongVideoChangeListener.onLongExerciseVideoComplete(p(), this.be.aCq_() != null ? this.be.aCq_().getDuration() : 0);
        }
        aw();
        if (!ai() || (onMotionChangeListener = this.bj) == null) {
            return;
        }
        onMotionChangeListener.onMotionOver(bs, this.n.b());
        this.bj.onTrainOver(this.ba);
    }

    public void at() {
        if (this.n.c() != 192) {
            this.cf = this.n.c();
            this.n.c(192);
        }
    }

    public void ax() {
        if (this.o.getVisibility() != 0) {
            this.n.c(this.cf);
            al();
        }
    }

    public fpz b() {
        return this.n;
    }

    public CoachPauseRestView d() {
        return this.o;
    }

    public int t() {
        return this.cf;
    }

    public void c(boolean z) {
        if (z) {
            View inflate = ((ViewStub) findViewById(R.id.viewstub)).inflate();
            if (inflate instanceof LinearLayout) {
                this.bb = (LinearLayout) inflate;
                bl();
                return;
            }
            return;
        }
        LinearLayout linearLayout = this.bb;
        if (linearLayout != null) {
            linearLayout.setVisibility(4);
        }
    }

    private void bl() {
        if (EnvironmentInfo.k()) {
            View findViewById = this.bb.findViewById(R.id.sug_guid_brightness);
            View findViewById2 = this.bb.findViewById(R.id.sug_guid_volume);
            findViewById.setVisibility(4);
            findViewById2.setVisibility(4);
        }
    }

    public void e(String str) {
        this.be.b(str);
    }

    public void e(fnu.e eVar) {
        this.f = eVar;
    }

    public SurfaceView aDs_() {
        return this.cd;
    }

    public ImageView aDq_() {
        return this.ab;
    }

    public ImageView aDr_() {
        return this.ah;
    }

    public HealthTextView q() {
        return this.ck;
    }

    public long e() {
        return this.e;
    }

    public void b(long j) {
        if (ag()) {
            return;
        }
        this.e += j;
    }

    public boolean c() {
        return this.ag;
    }

    public OnMotionChangeListener i() {
        return this.bj;
    }

    public ToolsLayout a() {
        return this.d;
    }

    public RelativeLayout getLockLayout() {
        return this.bc;
    }

    public void setSrtFile(File file) {
        if (file == null || !file.exists()) {
            LogUtil.h("Suggestion_LongCoachView", "setSrtFile() file is null or no exists");
            return;
        }
        Map<Integer, fqz> a2 = ght.a(file);
        if (!a2.isEmpty()) {
            this.aj = true;
            this.ce = a2;
            this.az = a2.get(Integer.valueOf(a2.size() - 1)).d();
            this.cu.sendEmptyMessage(1202);
            return;
        }
        LogUtil.h("Suggestion_LongCoachView", "setSrtFile() srtMap is empty");
    }

    public HealthTextView n() {
        if (this.ak) {
            return this.ch;
        }
        return this.cg;
    }

    public Map<Integer, fqz> l() {
        return this.ce;
    }

    public int k() {
        return this.az;
    }

    public void d(boolean z) {
        this.ba = z;
        Consumer<Boolean> consumer = this.ct;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
            this.ct = null;
        }
    }

    public void setValidListener(Consumer<Boolean> consumer) {
        this.ct = consumer;
    }

    public void j(int i) {
        this.cf = i;
    }

    public MediaHelper g() {
        return this.be;
    }

    public List<Motion> o() {
        return this.bl;
    }

    public boolean f() {
        return this.at;
    }

    public long p() {
        return this.cn;
    }

    public boolean an() {
        return this.ba;
    }

    public HealthSeekBar getLongMediaProgress() {
        return this.bf;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.CoachViewInterface
    public void updateHeartRate(int i) {
        this.cu.removeMessages(1200);
        this.cu.sendMessageDelayed(this.cu.obtainMessage(1200), 300000L);
        if (this.bx == 0) {
            return;
        }
        this.h.c(this.ca);
        this.h.h(i);
    }

    public void setSportType(int i) {
        LogUtil.a("Suggestion_LongCoachView", "setSportType = ", Integer.valueOf(i));
        this.ca = i;
    }

    public void b(int i, int i2) {
        if (ag()) {
            LogUtil.c("Suggestion_LongCoachView", "updateCaloriesByVideoProgress isLoading");
            return;
        }
        int size = this.bl.size() - 1;
        if (koq.d(this.co, size) && this.bl.size() > 1) {
            long j = i;
            if (j > this.co.get(0).getEndTime() || j > this.co.get(size).getStartTime()) {
                foy.e(this, this.bl.size());
            }
        }
        Motion motion = this.bl.get(this.n.b());
        if (this.i != null) {
            float d = fno.d(i, motion, this.cr);
            this.i.c(this.i.b() + d, this.i.d() + (d - fno.e(i, motion, this.cr)), CaloriesConsumeHandler.CaloriesSource.COURSE_PRESET);
        }
    }

    public void e(float f) {
        CoachController.d().b(f);
        this.h.e(f / 1000.0f);
        this.h.j((int) f);
    }

    private void setDragScreenBiEvent(int i) {
        c(i, CoachController.StatusSource.APP);
    }

    private void c(int i, CoachController.StatusSource statusSource) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("resourceType", Integer.valueOf(this.f.j()));
        CharSequence text = this.cc.getText();
        if (!TextUtils.isEmpty(text)) {
            hashMap.put("workout_name", text);
        }
        if (koq.d(this.bl, 0)) {
            Motion motion = this.bl.get(0);
            if (motion == null) {
                LogUtil.h("Suggestion_LongCoachView", "setDragScreenBiEvent motion is null.");
                return;
            } else {
                String acquireId = motion.acquireId();
                if (!TextUtils.isEmpty(acquireId)) {
                    hashMap.put("workout_id", acquireId);
                }
            }
        }
        hashMap.put("course_type", this.f.d());
        hashMap.put("body_position", this.f.e());
        hashMap.put("course_rate", this.f.c());
        if (this.be.aCq_() == null) {
            LogUtil.h("Suggestion_LongCoachView", "setDragScreenBiEvent mMediaHelper.getPlayer() is null.");
            return;
        }
        hashMap.put("eventType", Integer.valueOf(i <= this.be.aCq_().getCurrentPosition() ? 2 : 1));
        if (statusSource.equals(CoachController.StatusSource.NEW_LINK_WEAR)) {
            hashMap.put("ControlMode", PutDataRequest.WEAR_URI_SCHEME);
        } else {
            hashMap.put("ControlMode", "app");
        }
        ixx.d().d(this.m, AnalyticsValue.EVENT_FITNESS_COURSE_VIDEO_PROGRESS_DRAG.value(), hashMap, 0);
    }

    private void ci() {
        bx();
        this.bf.setOnSeekBarChangeListener(new HealthSeekBar.OnSeekBarChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.LongCoachView.2
            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
                if (z) {
                    LongCoachView.this.aa.removeCallbacksAndMessages(null);
                    LongCoachView.this.ax = true;
                    LongCoachView.this.g(i);
                    if (jcf.c()) {
                        LongCoachView.this.c(healthSeekBar);
                    }
                }
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
                LongCoachView.this.c(healthSeekBar);
            }
        });
        this.bf.setTouchable(bp());
    }

    private void bx() {
        if (!bp()) {
            this.ah.setVisibility(8);
            this.ae.setVisibility(8);
            this.ab.setVisibility(8);
        } else if (this.co.size() == 1) {
            this.ah.setVisibility(8);
            this.ab.setVisibility(8);
        } else {
            LogUtil.a("Suggestion_LongCoachView", "setImgBtnVisibility is not support drag and mVideoSegments.size() > 1.");
        }
        if (this.ae.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.ae.getLayoutParams();
            layoutParams.setMarginStart(getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532));
            this.ae.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) {
        if (this.bl.size() > 1) {
            int i2 = 0;
            while (i2 < this.co.size() && i >= this.co.get(i2).getEndTime()) {
                i2++;
            }
            foy.e(this, this.bl.size());
            if (i2 == this.co.size()) {
                i2--;
            }
            this.n.d(i2);
        }
        this.bf.setProgress(i);
        this.bv.e(i);
        this.ck.setText(new SimpleDateFormat("mm:ss", Locale.getDefault()).format(Integer.valueOf(i)));
    }

    public void setCoachPictureAndName(String str, String str2) {
        this.l = str;
        this.j = str2;
    }

    public void setVideoType(int i) {
        if (!FitWorkout.b.d(i)) {
            LogUtil.b("Suggestion_LongCoachView", "videoProperty is not support!");
        } else {
            this.cq = i;
            ci();
        }
    }

    public boolean ai() {
        return FitWorkout.b.b(this.cq);
    }

    public boolean ah() {
        return FitWorkout.b.e(this.cq);
    }

    public void c(int i) {
        this.cb = i;
    }

    public void e(int i) {
        this.bx = i;
    }

    public void setHeartRateAndCalorieVisibility(int i) {
        this.h.g(i);
    }

    public void setVideoUrl(String str) {
        ReleaseLogUtil.e("Suggestion_LongCoachView", "setVideoUrl() videoUrl:", str);
        this.cm = str;
    }

    private void cf() {
        LongMediaHelper longMediaHelper;
        if (TextUtils.isEmpty(this.f3181a) || (longMediaHelper = this.b) == null) {
            ReleaseLogUtil.e("Suggestion_LongCoachView", "setPlayBackGroundMusic() bgMusicUrl isEmpty");
        } else {
            longMediaHelper.setMediaSources(Uri.parse(this.f3181a));
            this.b.a(new LongMediaHelper.OnPreparedListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.LongCoachView.6
                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onPrepared() {
                    LogUtil.a("Suggestion_LongCoachView", "BackgroundMusic onPrepared");
                    LongCoachView.this.b.start();
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onBufferingStart() {
                    LogUtil.a("Suggestion_LongCoachView", "BackgroundMusic onBufferingStart");
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onBufferingEnd() {
                    LogUtil.a("Suggestion_LongCoachView", "BackgroundMusic onBufferingEnd");
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onSeekComplete(MediaPlayer mediaPlayer) {
                    LogUtil.a("Suggestion_LongCoachView", "BackgroundMusic onSeekComplete:", Integer.valueOf(mediaPlayer.getCurrentPosition()));
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onVideoError() {
                    boolean aa = CommonUtil.aa(LongCoachView.this.m);
                    LogUtil.b("Suggestion_LongCoachView", "background music play error.", Boolean.valueOf(aa));
                    if (aa) {
                        LongCoachView.this.b.setMediaSources(Uri.parse(LongCoachView.this.f3181a));
                    }
                }
            });
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.e("Suggestion_LongCoachView", "setBackgroundMusicListener() bgMusicUrl null");
            return;
        }
        ReleaseLogUtil.e("Suggestion_LongCoachView", "setBackgroundMusicUrl() bgMusicUrl:", str);
        this.f3181a = str;
        LongMediaHelper longMediaHelper = new LongMediaHelper(this.m);
        this.b = longMediaHelper;
        longMediaHelper.d(true);
    }

    public int getLongExplanationVideoWatchTime() {
        return this.bh;
    }

    public void f(int i) {
        if (ag()) {
            return;
        }
        this.bh += i;
    }

    public void setTextViewMotionName(String str) {
        if (this.ak) {
            this.s.b(str);
        }
    }

    public BrightnessOrVolumeProgressPlus getBrightOrVolumeProgressPlus() {
        return this.c;
    }

    public void setSeekVideoFinish() {
        this.ax = false;
    }

    public boolean am() {
        return this.ax;
    }

    public void aDC_(Bundle bundle) {
        double b;
        int i = bundle.getInt("fitnessHeart");
        LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData heartValue: ", Integer.valueOf(i));
        updateHeartRate(i);
        this.by.e(UnitUtil.e(bundle.getInt("fitnessTime") / 60, 1, 0));
        int i2 = bundle.getInt("fitnessDistance");
        if (i2 >= 100 && !this.ba) {
            LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData() mIsValid save record!");
            this.ba = true;
        }
        if (UnitUtil.h()) {
            b = jed.c(i2);
        } else {
            b = jed.b(i2);
        }
        LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData distance: ", Double.valueOf(b));
        this.by.e(b);
        float f = bundle.getFloat("fitnessSpeed");
        this.r = f;
        LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData speed: ", Float.valueOf(f));
        setSpeedToView(this.r);
        if (this.u == 1) {
            String string = bundle.getString("fitnessSlope");
            LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData slopeValue: ", string);
            this.by.c(string);
            String string2 = bundle.getString("fitnessSteps");
            LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData stepsValue: ", string2);
            this.by.g(string2);
            String string3 = bundle.getString("fitnessStepFrequency");
            LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData stepFrequencyValue: ", string3);
            this.by.b(string3);
            return;
        }
        String string4 = bundle.getString("fitnessResistance");
        LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData resistanceValue: ", string4);
        this.by.a(string4);
        int i3 = bundle.getInt("fitnessTreadFrequancy");
        LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData treadFrequencyValue: ", Integer.valueOf(i3));
        this.by.e(i3, 150, this.m.getResources().getString(R.string._2130843688_res_0x7f021828), "", 0);
        String string5 = bundle.getString("fitnessPower");
        LogUtil.a("Suggestion_LongCoachView", "notifyUpdateItemData powerValue: ", string5);
        this.by.d(string5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ca() {
        frx frxVar = this.by;
        if (frxVar == null) {
            LogUtil.h("Suggestion_LongCoachView", "setEquipmentDefaultData mSportDataViewHolder == null");
            return;
        }
        frxVar.e(0.0d);
        setSpeedToView(0.0f);
        if (this.u == 2) {
            this.by.a("");
            this.by.e(0.0f, 150, this.m.getResources().getString(R.string._2130843688_res_0x7f021828), "", 0);
            this.by.d("");
            return;
        }
        this.by.c("");
        this.by.g("");
        this.by.b("");
    }

    private void setSpeedToView(float f) {
        String string;
        if (this.u == 2) {
            this.by.j(UnitUtil.e(f, 1, 1));
            return;
        }
        if (UnitUtil.h()) {
            string = this.m.getResources().getString(R.string._2130839825_res_0x7f020911);
        } else {
            string = this.m.getResources().getString(R.string._2130839826_res_0x7f020912);
        }
        this.by.e(f, 100, string, "", 1);
    }

    public void b(int i) {
        String string;
        this.an = true;
        if (fop.e() != null && fop.e().length != 0) {
            this.x = new fop();
            HealthTextView healthTextView = this.bo;
            if (healthTextView != null) {
                healthTextView.setVisibility(8);
            }
        }
        frx frxVar = new frx(i);
        this.by = frxVar;
        this.bs.addView(frxVar.aFm_());
        LogUtil.a("Suggestion_LongCoachView", "initEquipmentViewState addView equipmentType:", Integer.valueOf(i));
        if (i == 2) {
            this.by.e(0.0f, 150, this.m.getResources().getString(R.string._2130843688_res_0x7f021828), "", 0);
            return;
        }
        if (UnitUtil.h()) {
            string = this.m.getResources().getString(R.string._2130839825_res_0x7f020911);
        } else {
            string = this.m.getResources().getString(R.string._2130839826_res_0x7f020912);
        }
        this.by.e(0.0f, 100, string, "", 1);
    }

    public void d(int i) {
        fop fopVar = this.x;
        if (fopVar == null) {
            return;
        }
        float c2 = fopVar.c(i / 1000, (int) (this.r * 1000.0f));
        float f = this.bz;
        if (f != c2) {
            setPlayerSpeed(c2);
        } else {
            LogUtil.a("Suggestion_LongCoachView", "mSpeedLevel is not change.", Float.valueOf(f));
        }
    }

    public void aq() {
        LogUtil.a("Suggestion_LongCoachView", "onStopSport");
        this.aa.sendEmptyMessage(10001);
    }

    public void ap() {
        HandlerExecutor.e(new Runnable() { // from class: fql
            @Override // java.lang.Runnable
            public final void run() {
                LongCoachView.this.au();
            }
        });
        LogUtil.a("Suggestion_LongCoachView", "onResumeSport ,all continue ");
    }

    public void ar() {
        HandlerExecutor.e(new Runnable() { // from class: fqr
            @Override // java.lang.Runnable
            public final void run() {
                LongCoachView.this.ba();
            }
        });
        LogUtil.a("Suggestion_LongCoachView", "onPauseSport");
    }

    public void setBlutoothConnected(boolean z) {
        LogUtil.a("Suggestion_LongCoachView", "setBlutoothConnected : ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        HandlerExecutor.e(new Runnable() { // from class: fqm
            @Override // java.lang.Runnable
            public final void run() {
                LongCoachView.this.ca();
            }
        });
    }

    public void setScreenCastVisibility(int i) {
        nsy.cMA_(this.bw, i);
    }

    public boolean z() {
        return this.af;
    }

    public void setClickStop(boolean z) {
        this.af = z;
    }

    public void setCaloriesConsumeHandler(CaloriesConsumeHandler caloriesConsumeHandler) {
        this.i = caloriesConsumeHandler;
    }

    public boolean ae() {
        return this.ap;
    }

    public int getCalorieStartTime() {
        return this.g;
    }

    public void setCalorieStartTime(int i) {
        this.g = i;
    }

    public boolean aj() {
        if (this.cb != 1 || ah()) {
            this.am = false;
            return false;
        }
        if (this.ak || this.i.a()) {
            this.am = true;
            return true;
        }
        if (this.be.aCq_() == null) {
            LogUtil.h("Suggestion_LongCoachView", "isStartCalCalorie mMediaHelper.getPlayer() == null");
            this.am = true;
            return true;
        }
        ReleaseLogUtil.e("Suggestion_LongCoachView", "mShowCalories:", Integer.valueOf(this.cb), "getCurrentPosition: ", Integer.valueOf(this.be.aCq_().getCurrentPosition()), "mCalorieStartTime:", Integer.valueOf(this.g));
        boolean z = this.be.aCq_().getCurrentPosition() >= this.g * 1000;
        this.am = z;
        return z;
    }

    public void ay() {
        if (!this.am && aj()) {
            setHeartRateAndCalorieVisibility(0);
            this.h.b(0.0f, 1.0f);
        }
    }

    public void setHasRemindMobileConnected(boolean z) {
        this.as = z;
    }

    public boolean ac() {
        return this.as;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLoadingStatus(boolean z) {
        HealthProgressBar healthProgressBar = this.cp;
        if (healthProgressBar == null) {
            return;
        }
        if (z && healthProgressBar.getVisibility() == 0) {
            LogUtil.a("Suggestion_LongCoachView", "setLoadingStatus VISIBLE same");
            return;
        }
        if (!z && this.cp.getVisibility() == 8) {
            LogUtil.a("Suggestion_LongCoachView", "setLoadingStatus GONE same");
            return;
        }
        if (z) {
            this.cp.setVisibility(0);
        } else {
            this.cp.setVisibility(8);
        }
        a(z);
    }

    private void a(boolean z) {
        if (!(this.m instanceof BaseCoachActivity)) {
            LogUtil.h("Suggestion_LongCoachView", "setLoadingStatus !(mContext instanceof BaseCoachActivity)");
            return;
        }
        LogUtil.a("Suggestion_LongCoachView", "calculateLoadingTime isVisible", Boolean.valueOf(z));
        BaseCoachActivity baseCoachActivity = (BaseCoachActivity) this.m;
        if (z) {
            baseCoachActivity.mLoadingStartTime = System.currentTimeMillis();
        }
        if (baseCoachActivity.mLoadingStartTime == 0) {
            LogUtil.a("Suggestion_LongCoachView", "calculateLoadingTime mLoadingStartTime == 0");
            return;
        }
        if (baseCoachActivity.mLoadingStartTime < baseCoachActivity.mStartTrainTime) {
            LogUtil.a("Suggestion_LongCoachView", "mLoadingStartTime < activity.mPlayStartTime", Boolean.valueOf(z), " mLoadingStartTime", Long.valueOf(baseCoachActivity.mLoadingStartTime), "mStartTrainTime:", Long.valueOf(baseCoachActivity.mStartTrainTime));
            return;
        }
        if (!z) {
            baseCoachActivity.mLoadingEndTime = System.currentTimeMillis();
            baseCoachActivity.mLoadingDuration = (baseCoachActivity.mLoadingDuration + baseCoachActivity.mLoadingEndTime) - baseCoachActivity.mLoadingStartTime;
        }
        ReleaseLogUtil.e("Suggestion_LongCoachView", "calculateLoadingTime mLoadingDuration:", Long.valueOf(baseCoachActivity.mLoadingDuration), " loadStarTime:", Long.valueOf(baseCoachActivity.mLoadingStartTime), " loadEndTime:", Long.valueOf(baseCoachActivity.mLoadingEndTime));
    }

    public boolean ag() {
        HealthProgressBar healthProgressBar = this.cp;
        return (healthProgressBar != null && healthProgressBar.getVisibility() == 0) || this.av;
    }

    private boolean bn() {
        Context context = this.m;
        if (!(context instanceof LongCoachActivity)) {
            LogUtil.h("Suggestion_LongCoachView", "isShowWifiTipsDialog !(mContext instanceof BaseCoachActivity)");
            return false;
        }
        return ((LongCoachActivity) context).d();
    }

    static class c extends jdt.c {
        private final WeakReference<LongCoachView> b;

        c(LongCoachView longCoachView) {
            this.b = new WeakReference<>(longCoachView);
        }

        @Override // jdt.c, android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            LongCoachView longCoachView = this.b.get();
            if (longCoachView != null) {
                boolean z = longCoachView.ao;
                LogUtil.a("Suggestion_LongCoachView", "onCallStateChanged state = ", Integer.valueOf(i), ", mIsPlaying = ", Boolean.valueOf(z));
                if (z && i == 1) {
                    longCoachView.ba();
                    return;
                }
                return;
            }
            LogUtil.h("Suggestion_LongCoachView", "MyPhoneStateListener onCallStateChanged longCoachView == null");
        }
    }
}
