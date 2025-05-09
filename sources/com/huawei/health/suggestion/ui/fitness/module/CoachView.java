package com.huawei.health.suggestion.ui.fitness.module;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.model.CoachMotion;
import com.huawei.health.suggestion.ui.fitness.adapter.CoachActionRecyclerAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.BeatHelper;
import com.huawei.health.suggestion.ui.fitness.helper.GuideHelper;
import com.huawei.health.suggestion.ui.fitness.helper.MediaHelper;
import com.huawei.health.suggestion.ui.fitness.helper.VideoMediaHelper;
import com.huawei.health.suggestion.ui.fitness.module.CoachView;
import com.huawei.health.suggestion.ui.fitness.module.SwitchView;
import com.huawei.health.suggestion.ui.fitness.module.TimeProgressPlus;
import com.huawei.health.suggestion.ui.fitness.mvp.CaloriesConsumeHandler;
import com.huawei.health.suggestion.ui.view.AniFrameLayout;
import com.huawei.health.suggestion.ui.view.BrightnessOrVolumeProgressPlus;
import com.huawei.health.suggestion.util.HeartRateGetterUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Comment;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.ash;
import defpackage.eil;
import defpackage.ffy;
import defpackage.fno;
import defpackage.fnu;
import defpackage.fnw;
import defpackage.fnx;
import defpackage.fnz;
import defpackage.foa;
import defpackage.fpd;
import defpackage.fpf;
import defpackage.frn;
import defpackage.frs;
import defpackage.ggn;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdt;
import defpackage.koq;
import defpackage.mld;
import defpackage.moe;
import defpackage.mxb;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.squ;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class CoachView extends RelativeLayout implements SurfaceHolder.Callback, View.OnClickListener, HealthSeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener, GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener, SwitchView.OnSwitchStateChangeListener, CoachViewInterface {

    /* renamed from: a, reason: collision with root package name */
    private BeatHelper f3172a;
    private GuideHelper aa;
    private int ab;
    private boolean ac;
    private Handler ad;
    private ImageView ae;
    private ImageView af;
    private ImageView ag;
    private ImageView ah;
    private ImageView ai;
    private boolean aj;
    private List ak;
    private boolean al;
    private fnw am;
    private boolean an;
    private boolean ao;
    private boolean ap;
    private boolean aq;
    private boolean ar;
    private boolean as;
    private RelativeLayout at;
    private LinearLayout au;
    private MediaHelper av;
    private ImageView aw;
    private Runnable ax;
    private DisplayMetrics ay;
    private b az;
    private ToolsLayout b;
    private List<Motion> ba;
    private String[] bb;
    private OnMotionChangeListener bc;
    private long bd;
    private MediaProgress be;
    private Runnable bf;
    private ImageView bg;
    private RelativeLayout bh;
    private HealthTextView bi;
    private int bj;
    private SurfaceView bk;
    private long bl;
    private int bm;
    private HealthTextView bn;
    private String bo;
    private TranslateAnimation bp;
    private float bq;
    private long br;
    private float bs;
    private BeatHelper bt;
    private Handler bu;
    private LinearLayout bv;
    private int c;
    private int d;
    private MediaHelper e;
    private frn f;
    private TrainActionIntro g;
    private BrightnessOrVolumeProgressPlus h;
    private long i;
    private CaloriesConsumeHandler j;
    private HealthRecycleView k;
    private CoachPauseRestView l;
    private a m;
    private fnu.e n;
    private CoachActionRecyclerAdapter o;
    private frs p;
    private List<Integer> q;
    private int r;
    private float s;
    private Context t;
    private TranslateAnimation u;
    private GestureDetector v;
    private DistributedApi w;
    private AniFrameLayout x;
    private String y;
    private SurfaceHolder z;

    private int c(boolean z, int i) {
        return z ? i - 1 : i + 1;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
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

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
    }

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
    }

    public CoachView(Context context) {
        this(context, null);
    }

    public CoachView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CoachView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bd = 100L;
        this.m = new a();
        this.q = new ArrayList(10);
        this.aj = true;
        this.ad = new fnx(this);
        this.bb = new String[2];
        this.bu = new d(this);
        this.al = false;
        this.ap = false;
        this.y = "NORMAL_MODE";
        this.t = context;
        VideoMediaHelper videoMediaHelper = new VideoMediaHelper(context);
        this.av = videoMediaHelper;
        videoMediaHelper.aCt_(new MediaPlayer.OnVideoSizeChangedListener() { // from class: fqb
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                CoachView.this.aDd_(mediaPlayer, i2, i3);
            }
        });
        this.bt = new BeatHelper(this.t);
        this.ay = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(this.ay);
        }
        this.ab = this.ay.heightPixels;
        this.bf = new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.5
            @Override // java.lang.Runnable
            public void run() {
                CoachView.this.h.setVisibility(4);
            }
        };
        if (this.az == null) {
            b bVar = new b(this);
            this.az = bVar;
            jdt.c(context, true, bVar);
        }
        if (CommonUtil.bh() && CommonUtil.ba()) {
            DistributedApi distributedApi = (DistributedApi) Services.a("DistributedService", DistributedApi.class);
            this.w = distributedApi;
            if (distributedApi != null) {
                distributedApi.init(this.t);
                this.w.setHideNavigationBar(true);
                this.w.detectLastWirelessDevice();
                return;
            }
            LogUtil.b("Suggestion_CoachView", "can not get DistributedApi.");
        }
    }

    public /* synthetic */ void aDd_(MediaPlayer mediaPlayer, int i, int i2) {
        ay();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        LogUtil.c("Suggestion_CoachView", "Training voice completion");
        this.ad.sendEmptyMessage(102);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        d(false);
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || motionEvent2 == null || this.ap) {
            LogUtil.b("Suggestion_CoachView", "onFling lastEvent == null or curEvent == null, mIsLocked: ", Boolean.valueOf(this.ap));
            return false;
        }
        if (motionEvent.getX() - motionEvent2.getX() > 150.0f && Math.abs(f) * 0.5d > Math.abs(f2)) {
            aDc_(this.ah, true, CoachController.StatusSource.APP);
        } else if (motionEvent2.getX() - motionEvent.getX() > 150.0f && Math.abs(f) * 0.5d > Math.abs(f2)) {
            aDc_(this.ag, false, CoachController.StatusSource.APP);
        } else {
            LogUtil.c("Suggestion_CoachView", "onFling didn't change action");
        }
        at();
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || motionEvent2 == null || this.ap || !aj()) {
            LogUtil.b("Suggestion_CoachView", "lastEvent == null or curEvent == null mIsLocked:", Boolean.valueOf(this.ap));
            return false;
        }
        if (EnvironmentInfo.k()) {
            LogUtil.h("Suggestion_CoachView", "onScroll mobile app engine mute volume and brightness.");
            return false;
        }
        if (motionEvent2.getRawX() < (getWidth() * 2.0f) / 5.0f && Math.abs(f2) > Math.abs(f) * 1.2d) {
            setBrightness(f2 / (this.ab / 2.0f));
            this.h.setVisibility(0);
            return true;
        }
        if (motionEvent2.getRawX() > (getWidth() * 3.0f) / 5.0f && Math.abs(f2) > Math.abs(f) * 1.2d) {
            setVolume(f2);
            this.h.setVisibility(0);
            return true;
        }
        LogUtil.c("Suggestion_CoachView", "onScroll didn't set visibility");
        return true;
    }

    public void setBrightness(float f) {
        if (!(getContext() instanceof Activity)) {
            LogUtil.h("Suggestion_CoachView", "setBrightness getContext() not instanceof Activity");
            return;
        }
        WindowManager.LayoutParams attributes = ((Activity) getContext()).getWindow().getAttributes();
        if (attributes.screenBrightness == -1.0f) {
            attributes.screenBrightness = fpf.c(getContext()) / 255.0f;
        }
        attributes.screenBrightness += f;
        if (attributes.screenBrightness > 1.0d) {
            attributes.screenBrightness = 1.0f;
        } else if (attributes.screenBrightness < 0.01d) {
            attributes.screenBrightness = 0.01f;
        } else {
            LogUtil.c("Suggestion_CoachView", "screenBrightness is normal value");
        }
        this.h.setProgressMax(1.0f);
        this.h.setProgress(attributes.screenBrightness);
        this.h.a(R.drawable._2131430555_res_0x7f0b0c9b);
        this.h.d(getResources().getString(R.string._2130845621_res_0x7f021fb5));
        ((Activity) getContext()).getWindow().setAttributes(attributes);
    }

    public void setVolume(float f) {
        if (!(getContext().getSystemService(PresenterUtils.AUDIO) instanceof AudioManager)) {
            LogUtil.h("Suggestion_CoachView", "setVolume getContext().getSystemService(AUDIO_SERVICE) not instanceof AudioManager");
            return;
        }
        AudioManager audioManager = (AudioManager) getContext().getSystemService(PresenterUtils.AUDIO);
        this.r = audioManager.getStreamVolume(3);
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        float f2 = this.s + f;
        this.s = f2;
        float f3 = streamMaxVolume;
        if (Math.abs(f2) >= this.ab / (2.0f * f3)) {
            if (this.s > 0.0f) {
                int i = this.r + 1;
                this.r = i;
                audioManager.setStreamVolume(3, i, 0);
            } else {
                int i2 = this.r - 1;
                this.r = i2;
                audioManager.setStreamVolume(3, i2, 0);
            }
            this.s = 0.0f;
        }
        this.h.setProgressMax(f3);
        if (this.r <= 0) {
            this.h.a(R.drawable._2131430556_res_0x7f0b0c9c);
        } else {
            this.h.a(R.drawable._2131430557_res_0x7f0b0c9d);
        }
        int i3 = this.r;
        if (i3 >= 0) {
            this.h.setProgress(i3);
        }
        this.h.d(getResources().getString(R.string._2130845620_res_0x7f021fb4));
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.SwitchView.OnSwitchStateChangeListener
    public void onSwitchStateChange(View view, boolean z) {
        if (view == null) {
            LogUtil.b("Suggestion_CoachView", "onSwitchStateChange switchView == null");
            return;
        }
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue == 0) {
            foa.d(this.f3172a, z, this.l.getCoachSetCountVoice().getProgress() / 1000.0f);
            LogUtil.a("Suggestion_CoachView", "change mBeatHelper");
        } else if (intValue == 1) {
            foa.d(this.aa, z, this.l.getCoachSetGuideVoice().getProgress() / 1000.0f);
            LogUtil.a("Suggestion_CoachView", "change mGuiderhelper");
        } else {
            foa.d(this.e, z, this.l.getCoachSetBackgroundVoice().getProgress() / 1000.0f);
            LogUtil.a("Suggestion_CoachView", "change mBackgroundHelper");
        }
    }

    public void ar() {
        LogUtil.c("Suggestion_CoachView", "onResume===", Boolean.valueOf(this.as));
        if (ffy.c(this.am)) {
            this.am.notifyDataSetChanged();
        }
        ba();
    }

    public void b(int i, boolean z) {
        LogUtil.a("Suggestion_CoachView", "applyAnimation-", Integer.valueOf(i));
        if (!z) {
            this.x.b();
            return;
        }
        if (i >= 1 && i <= 3) {
            this.x.e(i);
        } else if (i == 0) {
            this.x.e(0);
            this.x.e();
        } else {
            LogUtil.c("Suggestion_CoachView", "Animation number is unexpected");
        }
    }

    public void ah() {
        this.p.c(0.0f, 1.0f);
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f3177a = 0;
        private int e = 0;
        private int b = 0;
        private int c = 190;

        public int e() {
            return this.f3177a;
        }

        public void b(int i) {
            this.f3177a = i;
        }

        public int a() {
            return this.e;
        }

        public void c(int i) {
            this.e = i;
        }

        public int b() {
            return this.b;
        }

        public void d(int i) {
            this.b = i;
        }

        public int c() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }

        public void d() {
            this.b--;
        }

        public void g() {
            this.b++;
        }
    }

    public void ax() {
        LogUtil.a("Suggestion_CoachView", "start rest.");
        this.p.b(3);
        this.av.pause();
        this.bm = 193;
        this.m.a(193);
        boolean z = this.m.b() != this.ba.size() - 1;
        Motion motion = this.ba.get(this.m.b());
        String e = fnz.e(motion, getResources().getString(R.string._2130851561_res_0x7f0236e9));
        int restTime = getRestTime();
        CoachController.d().a(this.m.b(), restTime, restTime);
        this.l.e(restTime, z, motion.acquireName(), ffy.d(getContext().getApplicationContext(), R.string._2130837535_res_0x7f02001f, moe.d((((this.bs * motion.acquireCalorie()) * motion.acquireGroups()) * motion.acquireDuration()) / 1000.0f)), ffy.d(getContext().getApplicationContext(), R.string._2130848384_res_0x7f022a80, moe.f(motion.acquireDuration() * motion.acquireGroups())), e, motion.acquirePicUrl());
    }

    public void al() {
        LogUtil.a("Suggestion_CoachView", "end rest.");
        if (foa.a(this)) {
            this.bm = 190;
            this.m.a(190);
            this.l.c();
            fnz.b(this, this.ba.size());
            if (this.aj) {
                this.aa.restEnd(this.m.e());
            } else if (koq.d(this.ba, this.m.b())) {
                fnz.a(this, this.m.b(), this.ba.get(this.m.b()), this.ba.size() - 1);
            }
            this.av.repeat();
            CoachController.d().e(this.m.b());
        }
    }

    private void bb() {
        this.bh = (RelativeLayout) findViewById(R.id.sug_coach_right_data_layout);
        frs frsVar = new frs();
        this.p = frsVar;
        this.bh.addView(frsVar.aFb_());
        frn frnVar = new frn();
        this.f = frnVar;
        this.bh.addView(frnVar.aEY_());
        this.f.g(8);
        this.l = (CoachPauseRestView) findViewById(R.id.sug_coach_set_rl_show);
        this.g = (TrainActionIntro) findViewById(R.id.sug_coach_caintro);
        this.x = (AniFrameLayout) findViewById(R.id.fl_animation);
        this.bi = (HealthTextView) findViewById(R.id.sug_tv_coach_course_name);
        this.at = (RelativeLayout) findViewById(R.id.lock_layout);
        this.aw = (ImageView) findViewById(R.id.sug_coach_iv_locked);
        ToolsLayout toolsLayout = (ToolsLayout) findViewById(R.id.sug_coach_rl_actiontools);
        this.b = toolsLayout;
        toolsLayout.setIsShowLockAnim(false);
        this.ag = (ImageView) findViewById(R.id.sug_coach_iv_next);
        this.ae = (ImageView) findViewById(R.id.sug_coach_iv_pause);
        this.bk = (SurfaceView) findViewById(R.id.sfv);
        this.af = (ImageView) findViewById(R.id.sug_iv_voice_change);
        this.ah = (ImageView) findViewById(R.id.sug_coach_iv_pre);
        bu();
        this.be = (MediaProgress) findViewById(R.id.sug_coach_mp_progress);
        this.h = (BrightnessOrVolumeProgressPlus) findViewById(R.id.brightorvolumeprogressplus_setting);
        this.ai = (ImageView) findViewById(R.id.sug_coach_iv_lock);
        this.bv = (LinearLayout) findViewById(R.id.marketing_wound_plast_view);
        this.bk.setTag(false);
        this.bg = (ImageView) findViewById(R.id.sug_screen_cast);
        if (CommonUtil.bh() && CommonUtil.ba() && !EnvironmentInfo.k()) {
            this.bg.setVisibility(0);
            this.bg.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TextUtils.isEmpty(ash.b("screenCastTipShow"))) {
                        CoachView.this.bv();
                    } else {
                        CoachView.this.bw();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        bg();
        bo();
    }

    public void e(fnu.e eVar) {
        this.n = eVar;
    }

    private void bu() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.tv_total_second);
        this.bn = healthTextView;
        if (healthTextView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bn.getLayoutParams();
            layoutParams.topMargin = this.t.getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
            layoutParams.rightMargin = this.t.getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
            this.bn.setLayoutParams(layoutParams);
        }
    }

    private void bg() {
        aCX_(this.ag, R.drawable._2131431612_res_0x7f0b10bc, R.drawable._2131431611_res_0x7f0b10bb);
        aCX_(this.ah, R.drawable._2131431610_res_0x7f0b10ba, R.drawable._2131431609_res_0x7f0b10b9);
        aCX_(this.af, R.drawable._2131431660_res_0x7f0b10ec, R.drawable._2131431658_res_0x7f0b10ea);
        aCX_((ImageView) findViewById(R.id.sug_coachiv_close), R.drawable._2131431620_res_0x7f0b10c4, R.drawable._2131431618_res_0x7f0b10c2);
        if (LanguageUtil.bc(this.t)) {
            this.be.setScaleX(-1.0f);
            this.ai.setImageResource(R.drawable._2131430503_res_0x7f0b0c67);
        }
    }

    private void aCX_(ImageView imageView, int i, int i2) {
        if (LanguageUtil.bc(this.t)) {
            imageView.setImageDrawable(nrz.cKn_(this.t, i2));
        } else {
            imageView.setImageResource(i);
        }
    }

    public void setTimeView(int i, int i2, boolean z) {
        frs frsVar = this.p;
        if (frsVar == null) {
            LogUtil.b("Suggestion_CoachView", "setTimeView mCountTimerViewHolder == null");
        } else {
            frsVar.a(i, i2, z);
        }
    }

    public void setTimeProgress(int i) {
        frs frsVar = this.p;
        if (frsVar == null) {
            LogUtil.b("Suggestion_CoachView", "setTimeProgress mCountTimerViewHolder == null");
        } else {
            frsVar.b(i);
        }
    }

    public int getTimeProgressMax() {
        frs frsVar = this.p;
        if (frsVar == null) {
            LogUtil.b("Suggestion_CoachView", "setTimeProgress mCountTimerViewHolder == null");
            return 0;
        }
        return frsVar.c();
    }

    public void setTimeProgressMax(int i) {
        frs frsVar = this.p;
        if (frsVar == null) {
            LogUtil.b("Suggestion_CoachView", "setTimeProgressMax mCountTimerViewHolder == null");
        } else {
            frsVar.e(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bw() {
        DistributedApi distributedApi = this.w;
        if (distributedApi != null) {
            int startWirelessProjection = distributedApi.startWirelessProjection();
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", 1);
            hashMap.put("courseId", this.n.a());
            hashMap.put("action", Integer.valueOf(startWirelessProjection));
            ixx.d().d(this.t, AnalyticsValue.HEALTH_GYM_EQUIP_CLICK_BTN_2170008.value(), hashMap, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bv() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.t).e(this.t.getString(R.string._2130848592_res_0x7f022b50)).czE_(this.t.getString(R.string._2130841554_res_0x7f020fd2), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ash.a("screenCastTipShow", Boolean.toString(true));
                CoachView.this.bw();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
        LogUtil.a("Suggestion_CoachView", "showScreenCastTipDialog.");
    }

    private void bo() {
        bp();
        bq();
        this.l.getCoachSetPausePage().setOnClickListener(new View.OnClickListener() { // from class: fpx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CoachView.this.aDe_(view);
            }
        });
        this.l.d().d(new TimeProgressPlus.OnTimeCountFinishListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.17
            @Override // com.huawei.health.suggestion.ui.fitness.module.TimeProgressPlus.OnTimeCountFinishListener
            public void onTimeCountFinished() {
                CoachView.this.al();
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.TimeProgressPlus.OnTimeCountFinishListener
            public void setCountDownResidueSec(int i) {
                if (CoachController.d().g()) {
                    CoachController.d().c(ggn.c(CoachView.this.m.b(), CoachView.this.getRestTime(), i));
                }
            }
        });
        this.af.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.aa();
                CoachView.this.bs();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.aw.setOnClickListener(new View.OnClickListener() { // from class: fpw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CoachView.this.aDf_(view);
            }
        });
        this.l.d(this);
    }

    public /* synthetic */ void aDe_(View view) {
        LogUtil.a("Suggestion_CoachView", "click endResting.", Integer.valueOf(this.m.c()));
        if (this.m.c() == 193) {
            al();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aDf_(View view) {
        this.ap = false;
        this.at.setVisibility(8);
        at();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRestTime() {
        if (koq.d(this.ba, this.m.b() - 1)) {
            return this.ba.get(this.m.b() - 1).acquireGap();
        }
        return 0;
    }

    private void bq() {
        this.ag.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView coachView = CoachView.this;
                coachView.aDc_(coachView.ag, false, CoachController.StatusSource.APP);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.l.getCoachSetStop().setOnClickListener(this);
        this.l.getCoachSetOk().setOnClickListener(this);
        this.l.getCoachSetContinue().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                fnu.a(CoachView.this.n, 2);
                CoachView.this.au();
                LogUtil.a("Suggestion_CoachView", "Click Continue Button ,all continue ");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.l.getCoachSetCountVoice().setOnSeekBarChangeListener(this);
        this.l.getCoachSetGuideVoice().setOnSeekBarChangeListener(this);
        this.l.getCoachSetBackgroundVoice().setOnSeekBarChangeListener(this);
        this.l.getCoachSetPreButton().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.e(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.l.getCoachSetNextButton().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.e(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void bp() {
        br();
        this.ai.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.ap = true;
                CoachView.this.bm();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ae.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.av();
                fnu.a(CoachView.this.n, 1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        findViewById(R.id.sug_coachiv_close).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.ae();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        findViewById(R.id.sug_rl_coach_guide).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_CoachView", "enter motion detail interface");
                CoachView.this.g.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.1.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        CoachView.this.ae();
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
                CoachView.this.bt();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ah.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView coachView = CoachView.this;
                coachView.aDc_(coachView.ah, true, CoachController.StatusSource.APP);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void br() {
        this.g.setOnSlidingListener(new OnSlidingListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.8
            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSliding(float f) {
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSlidingFinish(boolean z) {
                if (z) {
                    CoachView.this.g.setVisibility(4);
                    CoachView.this.au();
                }
            }
        });
        this.g.getPreAction().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.a(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.g.getNextAction().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CoachView.this.a(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
    public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
        if (healthSeekBar == null) {
            LogUtil.b("Suggestion_CoachView", "onProgressChanged seekBar == null");
        } else {
            if (this.f3172a == null || this.aa == null || this.e == null) {
                return;
            }
            foa.e(this, i, healthSeekBar.getId());
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(this.t).inflate(R.layout.sug_fitness_coachview, this);
        bb();
        SurfaceHolder holder = this.bk.getHolder();
        this.z = holder;
        holder.addCallback(this);
        MediaHelper mediaHelper = this.av;
        if (mediaHelper != null) {
            mediaHelper.aCv_(this.bk);
            this.av.aCw_((ViewGroup) findViewById(R.id.sfv_parent));
            bn();
        }
        this.e = new MediaHelper(this.t);
        boolean b2 = mxb.a().b(this.t, SingleDailyMomentContent.COURSE_TYPE);
        LogUtil.a("Suggestion_CoachView", "onFinishInflate() enableCurLang:", Boolean.valueOf(b2));
        if (b2) {
            this.aa = new fpd(this.t, this.ad);
        } else {
            this.aa = new GuideHelper(this.t, this.ad);
        }
        this.f3172a = new BeatHelper(this.t);
        this.bs = fnz.e();
        GestureDetector gestureDetector = new GestureDetector(this.t.getApplicationContext(), this);
        this.v = gestureDetector;
        gestureDetector.setOnDoubleTapListener(this);
        setTag(false);
        eil.alR_(10001, this.bv);
    }

    public void c(String str) {
        int i;
        this.y = str;
        int j = (nsn.j() / 2) - mld.d(this.t, 24.0f);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        if ("HORIZONTAL_FOLDING_MODE".equals(str)) {
            layoutParams = new FrameLayout.LayoutParams(-1, j, 48);
            layoutParams2 = new FrameLayout.LayoutParams(-1, j, 80);
            i = 0;
        } else {
            i = 8;
        }
        aCY_(findViewById(R.id.sfv_parent), layoutParams);
        aCY_(this.bh, layoutParams);
        aCY_(this.x, layoutParams);
        aCY_(this.b, layoutParams2);
        aCY_(findViewById(R.id.sug_progress_layout), layoutParams2);
        aCY_(this.h, layoutParams2);
        aCY_(findViewById(R.id.sug_coach_set_rl_show), layoutParams2);
        aCY_(this.g, layoutParams2);
        aCY_(this.at, layoutParams2);
        HealthRecycleView healthRecycleView = this.k;
        if (healthRecycleView != null) {
            healthRecycleView.setVisibility(i);
        }
    }

    private void aCY_(View view, FrameLayout.LayoutParams layoutParams) {
        if (view == null) {
            LogUtil.h("Suggestion_CoachView", "setLayoutParamsByNoNull view null");
        } else {
            view.setLayoutParams(layoutParams);
        }
    }

    private void bh() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.sug_rcv_motions);
        this.k = healthRecycleView;
        healthRecycleView.setNestedScrollingEnabled(false);
        CoachActionRecyclerAdapter coachActionRecyclerAdapter = new CoachActionRecyclerAdapter(this.k, this.g, this.t);
        this.o = coachActionRecyclerAdapter;
        this.k.setAdapter(coachActionRecyclerAdapter);
        this.k.setLayoutManager(new LinearLayoutManager(this.t, 0, false));
        this.k.addItemDecoration(getItemDecoration());
        final ArrayList arrayList = new ArrayList();
        List<Motion> list = this.ba;
        if (list != null && list.size() != 0) {
            for (int i = 0; i < this.ba.size(); i++) {
                Motion motion = this.ba.get(i);
                CoachMotion coachMotion = new CoachMotion();
                coachMotion.setMotionImageUrl(motion.acquirePicUrl());
                coachMotion.setTotalMotionNum(this.ba.size());
                coachMotion.setCurrMotionNum(i);
                coachMotion.setMotion(motion);
                arrayList.add(coachMotion);
            }
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.6
                @Override // java.lang.Runnable
                public void run() {
                    CoachView.this.o.d(arrayList);
                }
            });
        }
        this.k.setVisibility("HORIZONTAL_FOLDING_MODE".equals(this.y) ? 0 : 8);
    }

    private ColumnLayoutItemDecoration getItemDecoration() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
        return new ColumnLayoutItemDecoration(getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), 0, this.ba.size(), new int[]{0, 0, dimensionPixelSize, 0});
    }

    public void b(int i) {
        HealthRecycleView healthRecycleView = this.k;
        if (healthRecycleView == null || this.o == null) {
            return;
        }
        healthRecycleView.smoothScrollToPosition(i);
        this.o.a(i, true);
    }

    private void bn() {
        this.av.aCq_().setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: fqd
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return CoachView.this.aDg_(mediaPlayer, i, i2);
            }
        });
    }

    public /* synthetic */ boolean aDg_(MediaPlayer mediaPlayer, int i, int i2) {
        MediaHelper mediaHelper = this.av;
        LogUtil.h("Suggestion_CoachView", "setPlayerListener, mMediaHelper is error, what = ", Integer.valueOf(i), ", currentName = ", mediaHelper != null ? mediaHelper.g() : null);
        return false;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    private void ay() {
        MediaHelper mediaHelper = this.av;
        int i = 0;
        int h = (mediaHelper == null || mediaHelper.h() <= 0) ? 0 : this.av.h();
        MediaHelper mediaHelper2 = this.av;
        if (mediaHelper2 != null && mediaHelper2.j() > 0) {
            i = this.av.j();
        }
        frn frnVar = this.f;
        if (frnVar != null) {
            frnVar.a(h);
        }
        frs frsVar = this.p;
        if (frsVar != null) {
            frsVar.c(h, i);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            LogUtil.b("Suggestion_CoachView", "onTouchEvent event == null");
            return false;
        }
        if (motionEvent.getAction() == 1) {
            removeCallbacks(this.bf);
            postDelayed(this.bf, 1200L);
        }
        return this.v.onTouchEvent(motionEvent);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        LogUtil.a("Suggestion_CoachView", "surfaceCreated--- mIsHolderCreated = ", Boolean.valueOf(this.aq));
        bk();
        this.av.aCs_(surfaceHolder);
        if (koq.c(this.ba)) {
            if (!this.aq) {
                bc();
            } else {
                this.as = false;
                ba();
            }
        }
    }

    private void bc() {
        int i;
        this.aq = true;
        String[] strArr = this.bb;
        if (strArr != null && strArr.length > 0) {
            this.av.setSdSources(strArr);
            LogUtil.a("Suggestion_CoachView", "mMediaHelper.setSdSources(mMotionPath):", Integer.valueOf(this.bb.length));
            this.bi.setText(this.n.i());
            this.bm = 190;
            this.m.a(190);
            i(this.bj);
            fnz.b(this, this.ba.size());
            bd();
            LogUtil.a("Suggestion_CoachView", "afterHolderCreate -------");
            Runnable runnable = new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.12
                @Override // java.lang.Runnable
                public void run() {
                    if (CoachView.this.m.b() == 0) {
                        LogUtil.h("Suggestion_CoachView", "afterHolderCreate mMediaHelper not playing -- to replay: ", Integer.valueOf(CoachView.this.m.b()));
                        CoachView.this.av.start();
                    }
                }
            };
            this.ax = runnable;
            postDelayed(runnable, 185L);
            this.p.e();
            if (this.bj < this.ba.size() && (i = this.bj) >= 0) {
                this.f3172a.resetBeatNum(this.ba.get(i).acquireRepeat());
            }
            this.b.setVisibility(0);
            this.ar = true;
            return;
        }
        LogUtil.b("Suggestion_CoachView", "mMotionPath is null");
    }

    private void bd() {
        this.av.start();
        LogUtil.a("Suggestion_CoachView", "{}---initFirstMotion--{}=={}", Integer.valueOf(this.ba.size()), this.ba.get(0).acquireName(), Boolean.valueOf(this.av.o()));
        this.aa.firstMotion(this.ba.get(0));
    }

    private void bk() {
        String b2 = fnz.b("voicecoachviewcount");
        String b3 = fnz.b("voicecoachviewbg");
        String b4 = fnz.b("voicecoachviewguide");
        try {
            float parseInt = (Integer.parseInt(b2) * 1.0f) / 1000.0f;
            this.bq = parseInt;
            this.f3172a.e(parseInt);
            this.aa.e((Integer.parseInt(b4) * 1.0f) / 1000.0f);
            this.e.e((Integer.parseInt(b3) * 0.5f) / 1000.0f);
        } catch (NumberFormatException e) {
            LogUtil.a("Suggestion_CoachView", e.getMessage());
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        LogUtil.a("Suggestion_CoachView", "surfaceChanged");
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.as = true;
        LogUtil.a("Suggestion_CoachView", "surfaceDestroyed");
    }

    public void aa() {
        LogUtil.a("Suggestion_CoachView", "allPause()--");
        ImageView imageView = this.ae;
        if (imageView != null) {
            aCX_(imageView, R.drawable._2131431649_res_0x7f0b10e1, R.drawable._2131431649_res_0x7f0b10e1);
        }
        if (this.m.c() != -100) {
            this.av.pause();
            this.e.pause();
            this.f3172a.pause();
            HeartRateGetterUtil.a().c(1, !CoachController.d().i());
            CoachController.d().a(CoachController.StatusSource.APP, 2);
            if (this.m.c() == 193) {
                this.l.d().d();
            } else {
                bj();
                if (this.bc != null && koq.d(this.ba, this.m.b())) {
                    this.bc.onMotionPause(this.ba.get(this.m.b()), this.m.b());
                }
            }
        }
        this.ar = false;
    }

    public void ap() {
        LogUtil.a("Suggestion_CoachView", "onPause()--mIsHolderCreated=", Boolean.valueOf(this.aq));
        if (this.aq) {
            aa();
            this.ar = false;
        }
    }

    private void bj() {
        if (this.m.c() == 191) {
            this.f3172a.d();
            this.aa.voiceGuidePause();
            this.ad.removeMessages(153);
            this.i = this.d;
            return;
        }
        if (this.m.c() == 190) {
            this.aa.voiceGuidePause();
        } else {
            LogUtil.c("Suggestion_CoachView", "Action mStation is not BEATING or GUIDING");
        }
    }

    public void aq() {
        LogUtil.a("Suggestion_CoachView", "onAllContinue");
        ImageView imageView = this.ae;
        if (imageView != null) {
            imageView.setImageResource(R.drawable._2131431648_res_0x7f0b10e0);
        }
        if (fnz.a(this.aq, this.m.c(), this.as)) {
            LogUtil.a("Suggestion_CoachView", "onAllContinue ??");
            foa.aBE_(this, this.ad);
            HeartRateGetterUtil.a().c(0, !CoachController.d().i());
            CoachController.d().a(CoachController.StatusSource.APP, 1);
            if (this.bc != null && koq.d(this.ba, this.m.b())) {
                this.bc.onMotionContinue(this.ba.get(this.m.b()), this.m.b());
            }
            this.ar = true;
        }
    }

    private void ba() {
        LogUtil.a("Suggestion_CoachView", "allContinue", ",mIsPlaying=", Boolean.valueOf(this.ar), ",mIsHolderCreated=", Boolean.valueOf(this.aq), ",mCoachState.acquireStation()=", Integer.valueOf(this.m.c()), ",mIsSurfaceDestroyed=", Boolean.valueOf(this.as));
        if (this.ar || !fnz.a(this.aq, this.m.c(), this.as)) {
            return;
        }
        aq();
    }

    public boolean aj() {
        return this.ar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (fnz.d(id)) {
            au();
            fnu.a(this.n, 2);
        } else if (fnz.e(this, id)) {
            if (nsn.o()) {
                LogUtil.a("Suggestion_CoachView", "isFastClick stop");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                LogUtil.a("Suggestion_CoachView", ":training completed");
                this.al = true;
                if (ak()) {
                    fnu.a(this.n, 3);
                }
                this.bc.onCustomBeave();
            }
        } else {
            LogUtil.c("Suggestion_CoachView", "Click action is not Continue or Finish");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bm() {
        this.b.setVisibility(4);
        jcf.bEk_(this.b, nsf.j(R.string._2130850648_res_0x7f023358));
        this.bu.removeCallbacksAndMessages(null);
        this.bu.sendMessage(this.bu.obtainMessage(1203));
        this.bu.sendMessageDelayed(this.bu.obtainMessage(1202), 7000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        if (z) {
            this.e.pre();
        } else {
            this.e.next();
        }
        this.l.getCoachSetMotionText().setText(this.e.g());
        c(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bt() {
        aa();
        aDh_(this.g);
        this.m.a(194);
        this.g.a(UnitUtil.e(this.m.b() + 1, 1, 0), UnitUtil.e(this.ba.size(), 1, 0));
        h(this.m.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        int c = c(z, this.g.getCurrentIndex());
        if (g(c)) {
            this.g.setCurrentIndex(UnitUtil.e(c, 1, 0));
            h(c - 1);
        }
    }

    private boolean g(int i) {
        return i > 0 && i <= this.ba.size();
    }

    public void aDc_(ImageView imageView, boolean z, CoachController.StatusSource statusSource) {
        if (imageView == null) {
            LogUtil.h("Suggestion_CoachView", "changeAction img == null");
            return;
        }
        if (imageView.getAlpha() == 1.0f) {
            this.ad.removeCallbacksAndMessages(null);
            this.x.a();
            this.bu.removeCallbacksAndMessages(null);
            this.b.setIsShowBottomProgress(false);
            if (z) {
                af();
                LogUtil.a("Suggestion_CoachView", "change to previous action");
            } else {
                ai();
                LogUtil.a("Suggestion_CoachView", "change to next action");
            }
            b(this.m.b());
        }
        d(z, statusSource);
    }

    private void c(boolean z) {
        d(z, CoachController.StatusSource.APP);
    }

    public void d(boolean z, CoachController.StatusSource statusSource) {
        fnu.e eVar = this.n;
        if (eVar != null) {
            eVar.b(z ? 2 : 1);
        }
        fnu.d(this.n, statusSource);
    }

    public void ae() {
        LogUtil.a("Suggestion_CoachView", "click x button");
        if (bl()) {
            this.bc.onCustomBeave();
        } else if (this.m.c() == 193) {
            al();
        } else {
            bf();
        }
    }

    private void bf() {
        if (this.m.c() == 194) {
            aCW_(this.g);
        } else {
            au();
        }
    }

    private boolean bl() {
        return (this.bc != null && this.m.c() == 190) || this.m.c() == 191;
    }

    public void ab() {
        long j = this.br + this.d;
        this.br = j;
        this.d = 0;
        this.br = (j / 1000) * 1000;
        this.c = 0;
        LogUtil.c("Suggestion_CoachView", "action completed");
        foa.aBF_(this, this.ad);
        Motion bi = bi();
        this.m.c(0);
        this.m.b(0);
        this.p.e();
        this.aj = false;
        foa.c(this, bi);
    }

    private Motion bi() {
        Motion motion;
        LogUtil.a("Suggestion_CoachView", "save process for training, current action:{} current group:{} current beat:{}", Integer.valueOf(this.m.b()), Integer.valueOf(this.m.e()), Integer.valueOf(this.m.a()));
        if (koq.d(this.ba, this.m.b())) {
            motion = this.ba.get(this.m.b());
        } else {
            motion = new Motion();
        }
        int e = (this.m.e() * motion.acquireRepeat()) + this.m.a();
        if (this.an && !"timer".equals(motion.acquireMotionType())) {
            e--;
        }
        if (motion.acquireGroups() * motion.acquireRepeat() == 0) {
            LogUtil.h("Suggestion_CoachView", "Division by zero attempted!");
            return motion;
        }
        fnz.c(this, e / ((motion.acquireGroups() * motion.acquireRepeat()) * 1.0f));
        if (e > motion.acquireProgress()) {
            motion.saveProgress(e);
        }
        return motion;
    }

    public void c(Motion motion) {
        this.ad.removeMessages(153);
        long j = this.br;
        long j2 = this.d;
        this.d = 0;
        this.br = (long) (((j + j2) / 1000.0f) * 1000.0f);
        this.c = 0;
        LogUtil.c("Suggestion_CoachView", "a group of actions completed.");
        this.aa.saveCurrent(-1);
        this.m.c(0);
        this.p.e();
        fnz.e(this, motion);
        this.aj = true;
    }

    public void z() {
        this.be.setProgress(this.m.b(), this.m.e(), 0.0f);
        if (koq.b(this.ba, this.m.b())) {
            LogUtil.b("Suggestion_CoachView", "mMotions is out of bounds");
            return;
        }
        Motion motion = this.ba.get(this.m.b());
        fnz.b(this, this.ba.size());
        i(this.m.b());
        this.f3172a.resetBeatNum(motion.acquireRepeat());
    }

    public void af() {
        if (this.l.getVisibility() == 0) {
            this.l.setVisibility(4);
        }
        this.m.b(0);
        ab();
        this.m.d();
        if (this.m.b() < 0) {
            this.m.d(0);
            this.aa.firstMotion(this.ba.get(this.m.b()));
        } else if (this.m.b() == 0) {
            this.aa.firstMotion(this.ba.get(this.m.b()));
        } else {
            this.aa.preMotion(this.ba.get(this.m.b()));
        }
        LogUtil.a("Suggestion_CoachView", "change to previous action, current action:{}", Integer.valueOf(this.m.b()));
        this.av.pre();
        CoachController.d().c(CoachController.StatusSource.APP, this.m.b());
        this.bm = 190;
        this.m.a(190);
        z();
    }

    public void ai() {
        if (this.l.getVisibility() == 0) {
            this.l.setVisibility(4);
        }
        this.aa.m();
        this.m.b(0);
        ab();
        this.m.g();
        if (this.m.b() >= this.ba.size()) {
            this.m.d(this.ba.size() - 1);
            this.aa.lastMotion(this.ba.get(this.m.b()));
        } else if (this.m.b() == this.ba.size() - 1) {
            this.aa.lastMotion(this.ba.get(this.m.b()));
        } else {
            this.aa.nextMotion(this.ba.get(this.m.b()));
        }
        LogUtil.a("Suggestion_CoachView", "change to next action, current action:{}", Integer.valueOf(this.m.b()));
        this.av.next();
        CoachController.d().b(CoachController.StatusSource.APP, this.m.b());
        this.bm = 190;
        this.m.a(190);
        z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bs() {
        this.m.a(195);
        this.l.a();
        aa();
        this.e.videoContinue();
    }

    public void av() {
        removeCallbacks(this.bf);
        post(this.bf);
        aa();
        this.m.a(192);
        if (koq.b(this.ba, this.m.b())) {
            LogUtil.h("Suggestion_CoachView", "mMotions is outOfBounds");
            return;
        }
        Motion motion = this.ba.get(this.m.b());
        this.l.d(motion.acquireName(), ffy.d(getContext().getApplicationContext(), R.string._2130837535_res_0x7f02001f, moe.d((((this.bs * motion.acquireCalorie()) * motion.acquireGroups()) * motion.acquireDuration()) / 1000.0f)), ffy.d(getContext().getApplicationContext(), R.string._2130848384_res_0x7f022a80, moe.f(motion.acquireDuration() * motion.acquireGroups())), fnz.e(motion, getResources().getString(R.string._2130851561_res_0x7f0236e9)), motion.acquirePicUrl());
        jcf.bEE_(this.b, 4);
        jcf.bEE_(this.bh, 4);
        jcf.bEE_(this.x, 4);
    }

    public void au() {
        this.m.a(this.bm);
        if (CoachController.d().g() && this.m.c() == 193) {
            this.l.b();
            aq();
        } else {
            aq();
            this.l.setVisibility(4);
            jcf.bEE_(this.b, 0);
            jcf.bEE_(this.bh, 0);
        }
    }

    public void az() {
        this.av.repeat();
    }

    private void h(int i) {
        List<Motion> list = this.ba;
        if (list == null || koq.b(list, i)) {
            LogUtil.b("Suggestion_CoachView", "mMoions is null or currPosition is invalid: ", Integer.valueOf(i));
            return;
        }
        Motion motion = this.ba.get(i);
        this.ak.clear();
        this.ak.add(motion);
        if (ffy.c(motion.acquireCovers())) {
            this.ak.add(motion.acquireCovers());
        }
        fnz.a(this, i, this.ba.size() - 1);
        this.g.setAdapter(this.am);
        this.am.notifyDataSetChanged();
    }

    public void setMotions(List<Motion> list, int i) {
        this.bj = i;
        this.ba = list;
        this.be.setMotions(list);
        this.f.d((int) fno.d(this.ba, this.bs, false));
        this.bb = new String[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (TextUtils.isEmpty(list.get(i2).acquireMotionPath())) {
                this.bb[i2] = "";
            } else {
                this.bb[i2] = list.get(i2).acquireMotionPath();
            }
        }
        LogUtil.a("Suggestion_CoachView", "saveMotions----size-", Integer.valueOf(list.size()));
        this.ak = new ArrayList(10);
        fnw fnwVar = new fnw(this.ak, R.layout.sug_coach_vp_intro);
        this.am = fnwVar;
        fnwVar.e(this.bo);
        this.g.setAdapter(this.am);
        bh();
        b(this.m.b());
    }

    public void c(int i) {
        LogUtil.a("Suggestion_CoachView", "refresh guide voice");
        List<Motion> list = this.ba;
        if (list == null || koq.b(list, i)) {
            LogUtil.b("Suggestion_CoachView", "mMoions is null or startPosition is invalid: ", Integer.valueOf(i));
            return;
        }
        List<Comment> acquireCommentaryTraining = this.ba.get(i).acquireCommentaryTraining();
        if (koq.c(acquireCommentaryTraining)) {
            this.q.clear();
            ArrayList arrayList = new ArrayList(acquireCommentaryTraining.size() + 1);
            c(acquireCommentaryTraining, arrayList);
            this.aa.a(arrayList).saveCurrent(-1);
        }
    }

    private void c(List<Comment> list, List list2) {
        for (Comment comment : list) {
            if (comment != null) {
                int round = Math.round(comment.acquireTime() / 1000.0f) <= 0 ? 1 : Math.round(comment.acquireTime() / 1000.0f);
                this.q.add(Integer.valueOf(round));
                LogUtil.a("Suggestion_CoachView", "guide voice times: ", Integer.valueOf(round));
                list2.add(squ.c(comment.acquireName()));
            }
        }
    }

    public CoachView d(String str) {
        this.e.e(str);
        if (!this.e.o()) {
            this.e.start();
        }
        this.l.getCoachSetMotionText().setText(str);
        return this;
    }

    public void as() {
        LogUtil.a("Suggestion_CoachView", "CoachView release---");
        jdt.c(this.t, false, this.az);
        this.ad.removeCallbacksAndMessages(null);
        BeatHelper beatHelper = this.f3172a;
        if (beatHelper != null) {
            beatHelper.m();
            this.f3172a.release();
        }
        GuideHelper guideHelper = this.aa;
        if (guideHelper != null) {
            if (guideHelper.o() && this.aa.e() == 1) {
                LogUtil.a("Suggestion_CoachView", "mGuiderHelp isPlaying.not release.");
                this.aa.a(new OnFitnessStatusChangeCallback() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.15
                    @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
                    public void onUpdate() {
                        if (CoachView.this.aa != null) {
                            CoachView.this.aa.m();
                            CoachView.this.aa.release();
                        }
                    }
                });
            } else {
                this.aa.m();
                this.aa.release();
            }
        }
        MediaHelper mediaHelper = this.av;
        if (mediaHelper != null) {
            mediaHelper.m();
            this.av.release();
        }
        MediaHelper mediaHelper2 = this.e;
        if (mediaHelper2 != null) {
            mediaHelper2.m();
            this.e.release();
        }
        BeatHelper beatHelper2 = this.bt;
        if (beatHelper2 != null) {
            beatHelper2.m();
            this.bt.release();
        }
        DistributedApi distributedApi = this.w;
        if (distributedApi != null) {
            distributedApi.destroyWirelessProjection();
        }
        CoachController.d().a(CoachController.StatusSource.APP, 3);
    }

    public void b(OnMotionChangeListener onMotionChangeListener) {
        this.bc = onMotionChangeListener;
    }

    private void i(int i) {
        String format;
        String e = UnitUtil.e(this.ba.size(), 1, 0);
        String e2 = UnitUtil.e(i + 1, 1, 0);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, "%s %s%s%s  ", this.ba.get(i).acquireName(), e, "/", e2);
        } else {
            format = String.format(Locale.ENGLISH, "%s%s%s %s  ", e2, "/", e, this.ba.get(i).acquireName());
        }
        this.p.b(format);
        if ("timer".equals(this.ba.get(i).acquireMotionType())) {
            setTimeView(-1, (int) (this.ba.get(i).acquireDuration() / 1000.0f), true);
        } else {
            setTimeView(-1, this.ba.get(i).acquireRepeat(), false);
        }
    }

    public int getTrainStation() {
        return this.m.c();
    }

    public void at() {
        this.b.setVisibility(this.b.getVisibility() == 0 ? 4 : 0);
        if (jcf.c()) {
            LogUtil.a("Suggestion_CoachView", "toggleTool isTouchExplorationEnabled");
            ToolsLayout toolsLayout = this.b;
            jcf.bEk_(toolsLayout, nsf.j(toolsLayout.getVisibility() == 0 ? R.string._2130850649_res_0x7f023359 : R.string._2130850648_res_0x7f023358));
        } else {
            this.bu.removeCallbacksAndMessages(null);
            this.bu.sendMessageDelayed(this.bu.obtainMessage(1201), 7000L);
        }
    }

    public void setGender(int i) {
        this.aa.b(i);
        this.f3172a.b(i);
        this.bt.b(i);
    }

    public void ag() {
        this.an = false;
    }

    private void aCW_(final View view) {
        if (this.u == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            this.u = translateAnimation;
            translateAnimation.setDuration(400L);
            this.u.setInterpolator(new DecelerateInterpolator());
            this.u.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.11
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(4);
                    CoachView.this.au();
                }
            });
        }
        view.startAnimation(this.u);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.CoachViewInterface
    public void finishTrain() {
        LogUtil.a("Suggestion_CoachView", "finishTrain-----");
        Motion bi = bi();
        this.m.a(-100);
        as();
        OnMotionChangeListener onMotionChangeListener = this.bc;
        if (onMotionChangeListener != null) {
            onMotionChangeListener.onMotionOver(bi, this.m.b());
            this.bc.onTrainOver(this.ao);
        }
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        LogUtil.a("Suggestion_CoachView", "onSingleTapConfirmed mIsLocked", Boolean.valueOf(this.ap));
        if (this.ap) {
            bm();
            return true;
        }
        at();
        return true;
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.ap) {
            LogUtil.a("Suggestion_CoachView", "onDoubleTap mIsLocked true");
            return true;
        }
        if (this.m.c() != 193) {
            fnu.a(this.n, 1);
            av();
        }
        return true;
    }

    public void aDh_(final View view) {
        if (this.bp == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
            this.bp = translateAnimation;
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            this.bp.setDuration(400L);
            this.bp.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.health.suggestion.ui.fitness.module.CoachView.13
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(0);
                }
            });
        }
        view.startAnimation(this.bp);
    }

    public void aw() {
        if (this.m.c() != 192) {
            this.bm = this.m.c();
            this.m.a(192);
        }
    }

    public void ao() {
        if (this.l.getVisibility() != 0) {
            this.m.a(this.bm);
            aq();
        }
    }

    public a f() {
        return this.m;
    }

    public CoachPauseRestView g() {
        return this.l;
    }

    public int w() {
        return this.bm;
    }

    public void d(boolean z) {
        if (z) {
            View inflate = ((ViewStub) findViewById(R.id.viewstub)).inflate();
            if (inflate instanceof LinearLayout) {
                this.au = (LinearLayout) inflate;
                be();
                return;
            }
            return;
        }
        LinearLayout linearLayout = this.au;
        if (linearLayout != null) {
            linearLayout.setVisibility(4);
        }
    }

    private void be() {
        if (EnvironmentInfo.k()) {
            View findViewById = this.au.findViewById(R.id.sug_guid_brightness);
            View findViewById2 = this.au.findViewById(R.id.sug_guid_volume);
            findViewById.setVisibility(4);
            findViewById2.setVisibility(4);
        }
    }

    public void a(String str) {
        this.bo = str;
        this.av.b(str);
        this.e.b(str);
        this.f3172a.b(str);
        this.aa.b(str);
        this.bt.b(str);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.bf);
        removeCallbacks(this.ax);
        this.bu.removeCallbacksAndMessages(null);
    }

    public long q() {
        return this.bd;
    }

    public SurfaceView aDb_() {
        return this.bk;
    }

    public ImageView aCZ_() {
        return this.ag;
    }

    public ImageView aDa_() {
        return this.ah;
    }

    public HealthTextView v() {
        return this.bn;
    }

    public int d() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public int b() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public BeatHelper a() {
        return this.f3172a;
    }

    public MediaHelper c() {
        return this.e;
    }

    public long i() {
        return this.i;
    }

    public void b(long j) {
        this.i = j;
    }

    public TrainActionIntro h() {
        return this.g;
    }

    public boolean n() {
        return this.an;
    }

    public GuideHelper m() {
        return this.aa;
    }

    public OnMotionChangeListener r() {
        return this.bc;
    }

    public MediaProgress t() {
        return this.be;
    }

    public ToolsLayout e() {
        return this.b;
    }

    public void setCaloreHeartViewsVisibility(int i) {
        frn frnVar = this.f;
        if (frnVar == null) {
            LogUtil.h("Suggestion_CoachView", "mCalorieHeartViewHoldr == null");
        } else {
            frnVar.g(i);
        }
    }

    public void b(boolean z) {
        this.ao = z;
    }

    public void e(int i) {
        this.bm = i;
    }

    public List<Integer> j() {
        return this.q;
    }

    public MediaHelper s() {
        return this.av;
    }

    public List<Motion> p() {
        return this.ba;
    }

    public void e(long j) {
        this.bl = j;
    }

    public boolean o() {
        return this.ac;
    }

    public long x() {
        return this.bl;
    }

    public long u() {
        return this.br;
    }

    public BeatHelper ad() {
        return this.bt;
    }

    public boolean ak() {
        return this.ao;
    }

    public float ac() {
        return this.bq;
    }

    public void c(float f) {
        this.bq = f;
    }

    /* loaded from: classes8.dex */
    static class d extends Handler {
        private WeakReference<CoachView> b;

        d(CoachView coachView) {
            this.b = new WeakReference<>(coachView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("Suggestion_CoachView", "UpdateUiHandler msg is null!");
                return;
            }
            super.handleMessage(message);
            CoachView coachView = this.b.get();
            if (coachView == null) {
                LogUtil.b("Suggestion_CoachView", "UpdateUiHandler mWeakRefView.get() == null");
                return;
            }
            if (message.what == 1200) {
                LogUtil.h("Suggestion_CoachView", "last updateTime is 5 mins ago");
                return;
            }
            if (message.what == 1201 && coachView.b != null && coachView.b.getVisibility() == 0) {
                coachView.b.setVisibility(8);
                jcf.bEk_(coachView.b, nsf.j(R.string._2130850648_res_0x7f023358));
            } else if (message.what == 1203 && coachView.at != null) {
                coachView.at.setVisibility(fnz.aBG_(coachView.at));
            } else if (message.what == 1202 && coachView.at != null && coachView.at.getVisibility() == 0) {
                coachView.at.setVisibility(8);
            } else {
                LogUtil.c("Suggestion_CoachView", "Message mStation unexpected");
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.CoachViewInterface
    public void updateHeartRate(int i) {
        Message obtainMessage = this.bu.obtainMessage(1200);
        this.bu.removeMessages(1200);
        this.bu.sendMessageDelayed(obtainMessage, 300000L);
        this.f.h(i);
    }

    public void b(float f) {
        CoachController.d().b(f);
        this.f.e(f / 1000.0f);
        this.f.j((int) f);
    }

    public void d(int i, int i2) {
        this.f.e(i, i2);
    }

    public void setScreenCastVisibility(int i) {
        nsy.cMA_(this.bg, i);
    }

    public boolean am() {
        return this.al;
    }

    public void setClickStop(boolean z) {
        this.al = z;
    }

    public boolean an() {
        return this.ap;
    }

    public void setCaloriesConsumeHandler(CaloriesConsumeHandler caloriesConsumeHandler) {
        this.j = caloriesConsumeHandler;
    }

    public CaloriesConsumeHandler getCaloriesConsumeHandler() {
        return this.j;
    }

    public float getWeight() {
        return this.bs;
    }

    static class b extends jdt.c {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<CoachView> f3178a;

        b(CoachView coachView) {
            this.f3178a = new WeakReference<>(coachView);
        }

        @Override // jdt.c, android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            CoachView coachView = this.f3178a.get();
            if (coachView != null) {
                boolean z = coachView.ar;
                LogUtil.a("Suggestion_CoachView", "onCallStateChanged state = ", Integer.valueOf(i), ", mIsPlaying = ", Boolean.valueOf(z));
                if (z && i == 1) {
                    coachView.av();
                    return;
                }
                return;
            }
            LogUtil.h("Suggestion_CoachView", "MyPhoneStateListener onCallStateChanged coachView == null");
        }
    }
}
