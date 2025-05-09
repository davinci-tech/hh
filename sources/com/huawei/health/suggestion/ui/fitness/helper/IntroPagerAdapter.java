package com.huawei.health.suggestion.ui.fitness.helper;

import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.Coordinate;
import com.huawei.pluginfitnessadvice.Cover;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.VideoSegment;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import defpackage.ffy;
import defpackage.gfm;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes8.dex */
public class IntroPagerAdapter extends HealthPagerAdapter implements TextureView.SurfaceTextureListener, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3161a;
    private Motion aa;
    private HealthTextView ab;
    private MediaHelper ac;
    private LinearLayout ad;
    private SparseArray<View> ae;
    private Surface af;
    private int ag;
    private String ah;
    private HealthTextView ai;
    private HealthTextView b;
    private HealthTextView c;
    private ImageView d;
    private HealthTextView e;
    private HealthTextView f;
    private TextureView g;
    private HealthTextView h;
    private HealthTextView i;
    private ImageView j;
    private LinearLayout k;
    private List l;
    private LinearLayout m;
    private ImageView n;
    private ConstraintLayout o;
    private int p;
    private IvDownLoadClick q;
    private HealthTextView s;
    private LinearLayout t;
    private RelativeLayout v;
    private int w;
    private boolean y = true;
    private Handler r = new Handler(Looper.getMainLooper());
    private boolean x = false;
    private String z = "";
    private boolean u = false;

    public interface HeadStatus {
        public static final int LOADING = 0;
        public static final int LOADING_SUCCESS = 3;
        public static final int LONG_VIDEO_LOADING = 5;
        public static final int LONG_VIDEO_READY = 6;
        public static final int NO_NET = 2;
        public static final int NO_WIFI = 1;
        public static final int RESET_STATUS = 4;

        void headStatus();
    }

    public interface IvDownLoadClick {
        void onIvDownLoadClick();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public IntroPagerAdapter(List list, int i) {
        this.l = list;
        this.w = i;
        this.ae = new SparseArray<>(list.size());
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (this.l.size() > 1) {
            return 1;
        }
        return this.l.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = this.ae.get(i);
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), this.w, null);
            aCg_(view);
        }
        if (this.x) {
            if (!(this.l.get(i) instanceof Motion)) {
                return view;
            }
            this.aa = (Motion) this.l.get(i);
            d();
            aCh_(viewGroup);
        } else if (i == 0) {
            if (koq.b(this.l, i) || !(this.l.get(i) instanceof Motion)) {
                return view;
            }
            this.aa = (Motion) this.l.get(i);
            a();
            aCh_(viewGroup);
        } else {
            b(i);
        }
        this.ae.put(i, view);
        ImageView imageView = this.n;
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        viewGroup.addView(view);
        return view;
    }

    private void a() {
        if (this.y) {
            e();
            return;
        }
        this.g.setVisibility(8);
        this.d.setVisibility(0);
        nrf.cJB_(this.aa.acquirePicUrl(), this.d);
        LogUtil.a("Suggestion_IntroPagerAdapter", "instantiateItem2");
    }

    private void e() {
        this.g.setVisibility(0);
        this.g.setSurfaceTextureListener(this);
        if (CommonUtil.bu()) {
            this.ac = new MediaHelper(BaseApplication.getContext());
        } else {
            this.ac = new MediaHelper();
        }
        this.ac.b(this.ah);
        this.ac.setSdSources(this.aa.acquireMotionPath());
    }

    private void b(int i) {
        Object obj;
        this.g.setVisibility(8);
        int i2 = 0;
        this.d.setVisibility(0);
        if (koq.d(this.l, i) && (obj = this.l.get(i)) != null && (obj instanceof Cover)) {
            Cover cover = (Cover) obj;
            nrf.cIu_(cover.getUrl(), this.d);
            this.m.setVisibility(8);
            List<Coordinate> coordinates = cover.getCoordinates();
            if (coordinates == null) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (i2 < coordinates.size()) {
                int i3 = i2 + 1;
                stringBuffer.append(i3).append(":").append(coordinates.get(i2).getTip()).append("/n");
                i2 = i3;
            }
            this.e.setText(stringBuffer);
        }
    }

    public void d() {
        this.g.setOutlineProvider(new gfm(nrr.e(BaseApplication.getContext(), 8.0f)));
        this.g.setClipToOutline(true);
        this.g.setSurfaceTextureListener(this);
        LongMediaHelper longMediaHelper = new LongMediaHelper(this.g.getContext().getApplicationContext());
        this.ac = longMediaHelper;
        longMediaHelper.b(this.ah);
        if (TextUtils.isEmpty(this.z)) {
            return;
        }
        this.ac.setMediaSources(Uri.parse(this.z));
        e(this.aa);
    }

    private void e(final Motion motion) {
        MediaHelper mediaHelper = this.ac;
        if ((mediaHelper instanceof LongMediaHelper) || motion == null) {
            final LongMediaHelper longMediaHelper = (LongMediaHelper) mediaHelper;
            longMediaHelper.a(new LongMediaHelper.OnPreparedListener() { // from class: com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter.5
                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onPrepared() {
                    VideoSegment videoSegment;
                    LogUtil.a("Suggestion_IntroPagerAdapter", "setMediaLongVideoListener onPrepared");
                    if (!IntroPagerAdapter.this.u) {
                        LogUtil.h("Suggestion_IntroPagerAdapter", "setMediaLongVideoListener--isActivityForGround:", false);
                        return;
                    }
                    if (koq.d(motion.getVideoSegments(), 0) && (videoSegment = motion.getVideoSegments().get(0)) != null) {
                        IntroPagerAdapter.this.ag = (int) videoSegment.getStartTime();
                        IntroPagerAdapter.this.p = (int) videoSegment.getEndTime();
                    }
                    IntroPagerAdapter introPagerAdapter = IntroPagerAdapter.this;
                    introPagerAdapter.c(longMediaHelper, introPagerAdapter.ag, IntroPagerAdapter.this.p);
                    LogUtil.a("Suggestion_IntroPagerAdapter", "setMediaLongVideoListener--isActivityForGround:", true);
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onBufferingStart() {
                    LogUtil.a("Suggestion_IntroPagerAdapter", "long Video buffering start", Long.valueOf(System.currentTimeMillis()));
                    if (!NetworkUtil.m()) {
                        IntroPagerAdapter.this.c(1);
                    } else {
                        IntroPagerAdapter.this.c(5);
                    }
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onBufferingEnd() {
                    LogUtil.a("Suggestion_IntroPagerAdapter", "long video buffering end", Long.valueOf(System.currentTimeMillis()));
                    IntroPagerAdapter.this.c(6);
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onSeekComplete(MediaPlayer mediaPlayer) {
                    LogUtil.a("Suggestion_IntroPagerAdapter", "long video seek complete", Long.valueOf(System.currentTimeMillis()));
                    IntroPagerAdapter.this.c(6);
                }

                @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
                public void onVideoError() {
                    LogUtil.b("Suggestion_IntroPagerAdapter", "onVideoError");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(LongMediaHelper longMediaHelper, int i, int i2) {
        longMediaHelper.start();
        longMediaHelper.e(i);
        a(longMediaHelper, i, i2);
    }

    private void a(final LongMediaHelper longMediaHelper, final int i, final int i2) {
        Runnable runnable = new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                int i3 = i2;
                if (i3 <= 0) {
                    return;
                }
                if (IntroPagerAdapter.this.b(longMediaHelper, i3)) {
                    longMediaHelper.e(i);
                }
                IntroPagerAdapter.this.r.postDelayed(this, 1000L);
            }
        };
        Handler handler = this.r;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(LongMediaHelper longMediaHelper, int i) {
        return longMediaHelper != null && longMediaHelper.o() && (longMediaHelper.e.getCurrentPosition() > i || longMediaHelper.e.getCurrentPosition() == i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = this.ae.get(i);
        if (view != null) {
            viewGroup.removeView(view);
        }
    }

    private void aCg_(View view) {
        this.d = (ImageView) view.findViewById(R.id.sug_coachi_iv_pic);
        this.j = (ImageView) view.findViewById(R.id.sug_iv_coach_intro_orign);
        this.o = (ConstraintLayout) view.findViewById(R.id.sug_action_detail_banner);
        this.k = (LinearLayout) view.findViewById(R.id.sug_traindetail_content);
        this.h = (HealthTextView) view.findViewById(R.id.sug_coach_intro_orign_new_textview);
        this.m = (LinearLayout) view.findViewById(R.id.sug_coach_ll_first);
        this.g = (TextureView) view.findViewById(R.id.sug_coachi_sv_pic);
        this.b = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_actiontitle);
        this.i = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_traindif);
        this.ai = (HealthTextView) view.findViewById(R.id.sug_tv_trainpoint);
        this.f = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_trainpoint);
        this.f3161a = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_equipment);
        this.e = (HealthTextView) view.findViewById(R.id.sug_coachi_tv_des);
        this.t = (LinearLayout) view.findViewById(R.id.sug_downloading);
        this.ad = (LinearLayout) view.findViewById(R.id.ll_nowifi);
        this.n = (ImageView) view.findViewById(R.id.iv_download);
        this.v = (RelativeLayout) view.findViewById(R.id.sug_headView);
        this.s = (HealthTextView) view.findViewById(R.id.tv_downloading_progress);
        this.c = (HealthTextView) view.findViewById(R.id.tv_audio_size);
        this.ab = (HealthTextView) view.findViewById(R.id.tv_no_wifi_msg);
        c();
    }

    public void c() {
        ConstraintLayout constraintLayout = this.o;
        if (constraintLayout == null) {
            LogUtil.h("Suggestion_IntroPagerAdapter", "initLayout, mConstraintLayout is null");
            return;
        }
        ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
        if (nsn.ag(BaseApplication.getContext())) {
            float d = new HealthColumnSystem(BaseApplication.getContext(), 0).d(6);
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) layoutParams).width = (int) d;
            } else {
                new ViewGroup.MarginLayoutParams(layoutParams).width = (int) d;
            }
            this.o.setLayoutParams(layoutParams);
            this.k.setLayoutParams(layoutParams);
            return;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).width = BaseApplication.getContext().getResources().getDisplayMetrics().widthPixels;
        } else {
            new ViewGroup.MarginLayoutParams(layoutParams).width = BaseApplication.getContext().getResources().getDisplayMetrics().widthPixels;
        }
        this.o.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        int dimension = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b);
        layoutParams2.setMarginStart(dimension);
        layoutParams2.setMarginEnd(dimension);
        this.k.setLayoutParams(layoutParams2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        LogUtil.a("Suggestion_IntroPagerAdapter", "onSurfaceTextureAvailable");
        Surface surface = new Surface(surfaceTexture);
        this.af = surface;
        this.ac.aCu_(surface);
        if (this.x) {
            return;
        }
        this.ac.start();
        LogUtil.a("Suggestion_IntroPagerAdapter", "setMediaLongVideoListener onSurfaceTextureAvailable IsActivityForGround start");
        this.r.postDelayed(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter.3
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Suggestion_IntroPagerAdapter", "mCoachImg.setVisibility(View.GONE);", Long.valueOf(System.currentTimeMillis()));
                IntroPagerAdapter.this.d.setVisibility(8);
                if (IntroPagerAdapter.this.v != null) {
                    IntroPagerAdapter.this.b();
                }
            }
        }, 500L);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        LogUtil.a("Suggestion_IntroPagerAdapter", "onSurfaceTextureDestroyed");
        this.ac.release();
        return false;
    }

    public void c(boolean z) {
        this.y = z;
    }

    public void c(int i) {
        if (i == 0) {
            RelativeLayout relativeLayout = this.v;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(0);
                this.t.setVisibility(0);
                this.ad.setVisibility(8);
                this.d.setVisibility(0);
                return;
            }
            return;
        }
        if (i == 1) {
            RelativeLayout relativeLayout2 = this.v;
            if (relativeLayout2 != null) {
                relativeLayout2.setVisibility(0);
                this.t.setVisibility(8);
                this.ad.setVisibility(0);
                this.ab.setVisibility(0);
                this.d.setVisibility(0);
                return;
            }
            return;
        }
        if (i == 3) {
            LinearLayout linearLayout = this.t;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                this.ad.setVisibility(8);
                return;
            }
            return;
        }
        e(i);
    }

    private void e(int i) {
        if (i == 2) {
            RelativeLayout relativeLayout = this.v;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(0);
                this.t.setVisibility(8);
                this.ad.setVisibility(0);
                this.ab.setVisibility(8);
                this.d.setVisibility(0);
                return;
            }
            return;
        }
        if (i == 4) {
            RelativeLayout relativeLayout2 = this.v;
            if (relativeLayout2 != null) {
                relativeLayout2.setVisibility(8);
                this.d.setVisibility(0);
                this.d.setImageDrawable(new ColorDrawable(ContextCompat.getColor(BaseApplication.getContext(), R$color.color_normal_titlebar_title)));
                return;
            }
            return;
        }
        if (i == 5) {
            if (g()) {
                this.v.setVisibility(0);
                this.t.setVisibility(0);
                this.ad.setVisibility(8);
                this.d.setVisibility(8);
                return;
            }
            return;
        }
        if (i == 6) {
            if (g()) {
                this.g.setVisibility(0);
                this.v.setVisibility(8);
                this.t.setVisibility(8);
                this.ad.setVisibility(8);
                this.d.setVisibility(8);
                return;
            }
            return;
        }
        LogUtil.h("Suggestion_IntroPagerAdapter", "refreshHeadView headStatus is nothing");
    }

    private boolean g() {
        return (this.g == null || this.v == null || this.t == null || this.ad == null || this.d == null) ? false : true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IvDownLoadClick ivDownLoadClick;
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        ImageView imageView = this.n;
        if (imageView != null && id == imageView.getId() && (ivDownLoadClick = this.q) != null) {
            ivDownLoadClick.onIvDownLoadClick();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c(IvDownLoadClick ivDownLoadClick) {
        this.q = ivDownLoadClick;
    }

    private void aCh_(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        if (TextUtils.isEmpty(FitWorkout.acquireComeFrom(this.aa.acquireWorkoutId()))) {
            this.h.setText(viewGroup.getContext().getApplicationContext().getString(R.string._2130848455_res_0x7f022ac7));
        } else {
            this.h.setText(viewGroup.getContext().getApplicationContext().getString(R.string._2130848471_res_0x7f022ad7));
        }
        j();
        this.b.setText(this.aa.acquireName());
        this.i.setText(ffy.a(viewGroup.getContext().getApplicationContext(), this.aa.acquireDifficulty()));
        this.f.setText(ffy.e(this.aa.getTrainingPoints()));
        this.ai.setText(viewGroup.getContext().getResources().getString(R.string._2130848728_res_0x7f022bd8, ffy.e(this.aa.getTrainingPoints())));
        List<Attribute> equipments = this.aa.getEquipments();
        if (equipments.size() == 0) {
            this.f3161a.setText(viewGroup.getContext().getResources().getString(R.string._2130848730_res_0x7f022bda));
        } else {
            this.f3161a.setText(ffy.d(equipments));
        }
        if (this.aa.getDescription() != null) {
            this.e.setText(ffy.awS_(viewGroup.getContext().getApplicationContext(), this.aa.getDescription(), Constants.LINK, R.drawable._2131431626_res_0x7f0b10ca));
        }
    }

    private void j() {
        if (this.j != null && !TextUtils.isEmpty(this.aa.getOriginLogo())) {
            nrf.cJB_(this.aa.getOriginLogo(), this.j);
            return;
        }
        HealthTextView healthTextView = this.h;
        if (healthTextView != null) {
            healthTextView.setVisibility(8);
        }
        ImageView imageView = this.j;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
    }

    public void c(String str) {
        HealthTextView healthTextView = this.s;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public void d(String str) {
        HealthTextView healthTextView = this.c;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                LogUtil.a("Suggestion_IntroPagerAdapter", "mHeadView onAnimationEnd");
                IntroPagerAdapter.this.v.setVisibility(8);
            }
        });
        alphaAnimation.setDuration(150L);
        this.v.startAnimation(alphaAnimation);
    }
}
