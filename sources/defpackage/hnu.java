package defpackage;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.basesport.sportui.OnEndCountdownListener;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.healthcloud.plugintrack.bolt.BoltCustomDialog;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportDataFragmentListener;
import com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.CameraUtil;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import com.huawei.healthcloud.plugintrack.ui.adapter.TrackGridViewAdapter;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton;
import com.huawei.healthcloud.plugintrack.ui.view.MusicControlLayout;
import com.huawei.healthcloud.plugintrack.ui.view.ShowDataPanelLayout;
import com.huawei.healthcloud.plugintrack.ui.view.UnlockSliderView;
import com.huawei.healthcloud.plugintrack.ui.viewholder.CountdownDialog;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.illustration.IllustrationView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.zhangyue.iReader.sdk.scheme.ISchemeListener;
import defpackage.gsy;
import defpackage.hnu;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hnu implements ViewHolderInterface, View.OnClickListener, UnlockSliderView.SliderListener, ISportDataFragmentListener {

    /* renamed from: a, reason: collision with root package name */
    private hnj f13277a;
    private HealthColumnLinearLayout aa;
    private LinearLayout ab;
    private CustomTextAlertDialog ad;
    private boolean ae;
    private boolean af;
    private IllustrationView ag;
    private boolean ah;
    private boolean al;
    private boolean ap;
    private boolean au;
    private RelativeLayout av;
    private ImageButton ay;
    private ImageView az;
    private e bb;
    private MusicControlLayout bd;
    private RelativeLayout be;
    private RelativeLayout bg;
    private HealthTextView bh;
    private View bj;
    private Resources bk;
    private ImageView bl;
    private PopupWindow bm;
    private GradientDrawable bp;
    private int bq;
    private Map<TrackMainFragmentShowType, hoj> br;
    private gtx bt;
    private int bx;
    private int by;
    private int bz;
    private int cb;
    private CustomTextAlertDialog cc;
    private hnq ce;
    private Animator cf;
    private TrackMainMapActivity cg;
    private gww ch;
    private Context d;
    private RelativeLayout f;
    private Bundle g;
    private Context h;
    private ImageView i;
    private RelativeLayout k;
    private View l;
    private int m;
    private GridView o;
    private int p;
    private int q;
    private int s;
    private HealthButton u;
    private CustomTextAlertDialog x;
    private HealthTextView z;
    private hog cj = null;
    private SparseArray<hoj> ax = new SparseArray<>();
    private boolean aq = false;
    private boolean v = true;
    private CountdownDialog n = null;
    private SparseArray<TrackMainFragmentShowType> bn = new SparseArray<>(16);
    private SparseArray<TrackMainFragmentShowType> ac = new SparseArray<>(16);
    private boolean an = false;
    private int bu = -1;
    private boolean ak = false;
    private boolean ao = true;
    private Map<Integer, String> bw = new HashMap(16);
    private String bi = null;
    private int bs = 0;
    private int bv = 0;
    private int ca = gwh.l;
    private int r = gwh.n;
    private float e = 17.0f;
    private ImageButton bf = null;
    private ImageButton j = null;
    private ImageButton b = null;
    private LinearLayout w = null;
    private ImageView t = null;
    private boolean aj = false;
    private boolean ar = false;
    private int y = 1;
    private boolean ai = false;
    private gxw cl = new gxw() { // from class: hnu.1
        @Override // defpackage.gxw
        public void e() {
            super.e();
            if (hnu.this.y == 4) {
                hnu.this.j(this.c);
            }
        }
    };
    private Animator.AnimatorListener cd = new Animator.AnimatorListener() { // from class: hnu.15
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            hnu.this.cj.aj().setVisibility(4);
        }
    };
    private int bo = 0;
    private long at = 0;
    private boolean am = false;
    private long aw = 0;
    private boolean as = false;
    private List<gsy.b> c = new ArrayList();
    private Handler bc = new c(this);
    private int ba = -1;

    static /* synthetic */ boolean bmC_(View view, MotionEvent motionEvent) {
        return true;
    }

    public hnu(View view, Context context, TrackBaseActivity trackBaseActivity, TrackMainMapActivity trackMainMapActivity, Bundle bundle) {
        hog hogVar;
        this.bj = null;
        this.h = null;
        this.d = null;
        this.cg = null;
        this.g = null;
        this.bk = null;
        this.bb = null;
        this.bt = null;
        this.ap = false;
        if (view == null || context == null || trackMainMapActivity == null) {
            throw new RuntimeException("invalid context or view");
        }
        this.bj = view;
        this.h = BaseApplication.getContext();
        this.d = context;
        this.cg = trackMainMapActivity;
        this.af = trackMainMapActivity.e();
        this.ah = trackMainMapActivity.a();
        this.g = bundle;
        this.bk = this.h.getResources();
        this.bb = new e(this);
        bn();
        if (!bu()) {
            gtx c2 = gtx.c(this.h.getApplicationContext());
            this.bt = c2;
            c2.as();
            this.bt.a(this.cl);
        }
        this.ap = av();
        br();
        bl();
        bo();
        cy();
        if (!bu()) {
            co();
        }
        if (UnitUtil.h() && this.cj != null) {
            d((HealthTextView) this.bj.findViewById(R.id.text_targetUnit));
        }
        if (this.bs == 264 && this.bu == 0 && (hogVar = this.cj) != null && hogVar.ae() != null) {
            this.cj.ae().setTextSize(0, this.bk.getDimension(R.dimen._2131363841_res_0x7f0a0801));
        }
        if (!bu()) {
            gtx.e(this);
        }
        this.au = jcf.c();
        jcf.bEj_(new b(this));
    }

    private boolean bu() {
        return this.af;
    }

    static class b implements AccessibilityManager.TouchExplorationStateChangeListener {
        private final WeakReference<hnu> c;

        b(hnu hnuVar) {
            this.c = new WeakReference<>(hnuVar);
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            hnu hnuVar = this.c.get();
            if (hnuVar == null) {
                ReleaseLogUtil.d("Track_TrackMainViewHolder", "TouchExplorationStateChangeListener onTouchExplorationStateChanged holder is null");
            } else {
                LogUtil.a("Track_TrackMainViewHolder", "TouchExplorationStateChangeListener onTouchExplorationStateChanged enabled ", Boolean.valueOf(z), " mIsTouchExplorationEnabled ", Boolean.valueOf(hnuVar.au));
                hnuVar.au = z;
            }
        }
    }

    static class c extends BaseHandler<hnu> {
        c(hnu hnuVar) {
            super(hnuVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bnd_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(hnu hnuVar, Message message) {
            if (message == null) {
                LogUtil.b("Track_TrackMainViewHolder", "MsgHandler message == null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                hnuVar.b(true);
                hnuVar.f(1);
                return;
            }
            if (i == 1) {
                hnuVar.b(false);
                hnuVar.f(0);
                return;
            }
            if (i == 2) {
                if (hnuVar.n != null) {
                    hnuVar.n.startCountdown();
                }
            } else {
                if (i == 3) {
                    hnuVar.f13277a.d();
                    return;
                }
                if (i != 4) {
                    return;
                }
                hnuVar.ck();
                hnuVar.b = (ImageButton) hnuVar.bj.findViewById(R.id.track_main_page_bolt_icon);
                LogUtil.a("Track_TrackMainViewHolder", "refreshBoltButton() mBoltDeviceInfos.size: ", Integer.valueOf(hnuVar.c.size()));
                hnuVar.g(hnuVar.c.size());
                if (hnuVar.b != null) {
                    hnuVar.b.setOnClickListener(hnuVar);
                }
            }
        }
    }

    public CustomTextAlertDialog h() {
        return this.x;
    }

    public CustomTextAlertDialog i() {
        return this.ad;
    }

    private void co() {
        if (ktj.e(this.h) || this.bs == 264) {
            if (!ktj.b(this.h) && this.bs != 264) {
                ktj.c(this.h);
            }
            if (this.n != null && b(this.d)) {
                this.bc.sendEmptyMessageDelayed(2, 500L);
            }
            this.cg.b(true);
            return;
        }
        if (nsn.ae(this.h)) {
            LogUtil.a("Track_TrackMainViewHolder", "Pad not support showDialog");
        } else if (!ktj.e(this.h)) {
            cm();
        } else {
            cq();
        }
    }

    private void cm() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.d).b(R.string.IDS_device_replace_dialog_title_notification).d(R.string._2130841420_res_0x7f020f4c).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: hnu.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_TrackMainViewHolder", "showGPSSettingDialog()");
                if (ktj.a(hnu.this.h)) {
                    hnu.this.cg.b(true);
                    if (hnu.this.n != null && hnu.b(hnu.this.d)) {
                        hnu.this.bc.sendEmptyMessageDelayed(2, 500L);
                    } else {
                        LogUtil.h("Track_TrackMainViewHolder", "Unexpected showDialog status");
                    }
                } else {
                    hnu.this.cg.bdc_(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1000);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: hnu.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (hnu.this.n != null && hnu.b(hnu.this.d)) {
                    gso.e().c(0);
                    hnu.this.cg.b(false);
                    hnu.this.cg.k();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.x = a2;
        a2.setCancelable(false);
        Context context = this.d;
        if (!(context instanceof Activity) || ((Activity) context).isDestroyed() || ((Activity) this.d).isFinishing()) {
            return;
        }
        this.x.show();
    }

    private void cz() {
        CustomTextAlertDialog customTextAlertDialog = this.cc;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.show();
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.d).b(R.string._2130847216_res_0x7f0225f0).d(R.string._2130847215_res_0x7f0225ef).cyU_(R.string._2130841233_res_0x7f020e91, new View.OnClickListener() { // from class: hnw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                hnu.this.bmY_(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: hoa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                hnu.bmD_(view);
            }
        }).a();
        this.cc = a2;
        a2.setCancelable(false);
        Context context = this.d;
        if (!(context instanceof Activity) || ((Activity) context).isDestroyed() || ((Activity) this.d).isFinishing()) {
            return;
        }
        this.cc.show();
    }

    /* synthetic */ void bmY_(View view) {
        LogUtil.a("Track_TrackMainViewHolder", "showStopReverseDialog() click positive");
        this.cg.finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void bmD_(View view) {
        LogUtil.a("Track_TrackMainViewHolder", "showStopReverseDialog() click nagtive");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void cq() {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.d).b(R.string.IDS_device_replace_dialog_title_notification).d(R.string._2130843391_res_0x7f0216ff).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: hnu.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_TrackMainViewHolder", "showHmsDialog() click positive");
                hnu.this.cg.b(true);
                if (hnu.this.n != null && hnu.b(hnu.this.d)) {
                    hnu.this.bc.sendEmptyMessageDelayed(2, 500L);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: hnu.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (hnu.this.n != null && hnu.b(hnu.this.d)) {
                    gso.e().c(0);
                    hnu.this.cg.b(false);
                    hnu.this.cg.k();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        this.ad = a2;
        a2.setCancelable(false);
        Context context = this.d;
        if (!(context instanceof Activity) || ((Activity) context).isDestroyed() || ((Activity) this.d).isFinishing()) {
            return;
        }
        this.ad.show();
    }

    public void e(int i) {
        if (i == 1000) {
            if (ktj.e(this.h)) {
                if (this.n != null && b(this.d)) {
                    this.bc.sendEmptyMessageDelayed(2, 500L);
                }
                this.cg.b(true);
                return;
            }
            this.cg.b(false);
            gso.e().c(0);
            this.cg.k();
        }
    }

    private void bl() {
        SparseArray<TrackMainFragmentShowType> configFromLocal = hnb.a().getConfigFromLocal(this.bs, this.bu, this.bv);
        this.bn = configFromLocal;
        if (configFromLocal == null || configFromLocal.size() == 0) {
            this.bn = hnb.a().getDefaultConfig(this.bs, this.bu, this.bv);
        }
        this.ac.put(0, TrackMainFragmentShowType.TOTAL_TIME);
        this.ac.put(1, TrackMainFragmentShowType.TOTAL_DISTANCES);
        this.ao = this.cg.c();
    }

    private void bn() {
        Bundle bundle = this.g;
        if (bundle == null) {
            return;
        }
        this.bs = bundle.getInt("map_tracking_sport_type_sportting", 0);
        float f = this.g.getFloat("sport_target_value_sportting", -1.0f);
        this.bu = this.g.getInt("sport_target_type_sportting", -1);
        this.bv = this.g.getInt("sport_data_source_sportting", 0);
        ao();
        int i = this.bu;
        if (i != -1) {
            a(this.bs, i, f);
        }
        this.aj = this.g.getBoolean("indoor_Running_Info", false);
        if (this.g.getInt("pathClass", -1) == 1) {
            this.ba = nsf.c(R.color._2131296651_res_0x7f09018b);
        }
    }

    private void ao() {
        if (this.bu == 4) {
            FitWorkout fitWorkout = (FitWorkout) this.g.getParcelable("runCourseDetail");
            if (fitWorkout == null) {
                LogUtil.h("Track_TrackMainViewHolder", "fitWorkout == null");
                return;
            }
            int acquireMeasurementType = fitWorkout.acquireMeasurementType();
            this.bu = ghp.e(acquireMeasurementType);
            LogUtil.a("Track_TrackMainViewHolder", "coverCourseType courseType:", Integer.valueOf(acquireMeasurementType), "mSportTarget:", Integer.valueOf(this.bu));
        }
    }

    private void a(int i, int i2, float f) {
        String str;
        if (i2 != -1) {
            if (i2 == 0) {
                str = UnitUtil.e(f / 60.0f, 1, 0) + " " + this.h.getResources().getString(R.string._2130839756_res_0x7f0208cc);
            } else if (i2 != 1) {
                if (i2 != 2) {
                    str = "";
                } else {
                    str = UnitUtil.e(f / 1000.0f, 1, 0) + " " + this.h.getResources().getString(R.string._2130839711_res_0x7f02089f);
                }
            } else if (UnitUtil.h()) {
                str = UnitUtil.e(UnitUtil.e(f, 3), 1, 2) + " " + this.h.getResources().getString(R.string._2130844081_res_0x7f0219b1);
            } else {
                str = e(f);
            }
            this.bi = str + " " + aj().get(Integer.valueOf(i));
        }
    }

    private String e(float f) {
        double d = f;
        int i = 4;
        if (Math.abs(d - 21.0975d) >= 1.0E-6d) {
            if (Math.abs(d - 42.195d) >= 1.0E-6d) {
                if (d(f) < 4) {
                    if (d(f) != 3) {
                        LogUtil.c("Track_TrackMainViewHolder", "Distance is normal");
                        i = 2;
                    }
                }
            }
            i = 3;
        }
        return UnitUtil.e(d, 1, i) + " " + this.h.getResources().getString(R.string._2130844082_res_0x7f0219b2);
    }

    private int d(float f) {
        int length = (Float.toString(Math.abs(f)).length() - Integer.toString(Math.abs(Math.round(f))).length()) - 1;
        LogUtil.a("Track_TrackMainViewHolder", "getNumberOfDigits is ", Integer.valueOf(length));
        return length;
    }

    private Map<Integer, String> aj() {
        this.bw.put(259, this.h.getResources().getString(R.string._2130842145_res_0x7f021221));
        this.bw.put(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE), this.h.getResources().getString(R.string.IDS_hwh_motiontrack_indoor_cycling));
        this.bw.put(258, this.h.getResources().getString(R.string._2130839778_res_0x7f0208e2));
        this.bw.put(264, this.h.getResources().getString(R.string._2130839778_res_0x7f0208e2));
        this.bw.put(257, this.h.getResources().getString(R.string._2130839779_res_0x7f0208e3));
        this.bw.put(282, this.h.getResources().getString(R.string.IDS_hwh_sport_type_hiking));
        this.bw.put(262, this.h.getResources().getString(R.string._2130839781_res_0x7f0208e5));
        this.bw.put(263, this.h.getResources().getString(R.string.IDS_motiontrack_golf_tip));
        this.bw.put(260, this.h.getResources().getString(R.string.IDS_motiontrack_climb_hill_tip));
        this.bw.put(261, this.h.getResources().getString(R.string.IDS_motiontrack_climb_stairs_tip));
        this.bw.put(0, this.h.getResources().getString(R.string._2130839778_res_0x7f0208e2));
        return this.bw;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface
    public void setEventListener() {
        if (this.bj == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "setEventListener mRootView is null");
            return;
        }
        hog hogVar = this.cj;
        if (hogVar == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "setEventListener mTrackMainViewHolderBean is null");
            return;
        }
        hogVar.bnz_(this, this.v);
        this.cj.al().a(new CircleProgressButton.CircleProcessListener() { // from class: hnu.32
            @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
            public void onFinished() {
                if (hnu.this.ce != null) {
                    hnu.this.ce.b(hnu.this.bk.getColor(R.color._2131298760_res_0x7f0909c8));
                }
                hnu.this.db();
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
            public void onStarted() {
                gtx.c(hnu.this.h).b(4);
            }

            @Override // com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton.CircleProcessListener
            public void onCancel() {
                hnu.this.da();
                hnu.this.bb.sendEmptyMessageDelayed(101, 2000L);
                gtx.c(hnu.this.h).b(5);
            }
        });
        ci();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface
    public hoh<hnu> getViewCell() {
        return new hoh<>(this);
    }

    private void bo() {
        if (this.bj == null) {
            return;
        }
        if (nsn.ae(this.h)) {
            bf();
        } else {
            bh();
        }
        if (!bt()) {
            this.az = (ImageView) this.bj.findViewById(R.id.track_sport_map_anim);
            RelativeLayout relativeLayout = (RelativeLayout) this.bj.findViewById(R.id.fragment_sportdata);
            this.k = relativeLayout;
            BaseActivity.cancelLayoutById(relativeLayout);
            BaseActivity.setViewSafeRegion(false, this.k);
            hnq hnqVar = new hnq(this.bj, this.d, this.bs, this.k);
            this.ce = hnqVar;
            hnqVar.d(this.ba);
            if (this.ce.d()) {
                ReleaseLogUtil.c("Track_TrackMainViewHolder", "initMapView failed!");
                this.cg.finish();
                return;
            }
        }
        this.f = (RelativeLayout) this.bj.findViewById(R.id.track_map_anim_button_relative);
        this.i = (ImageView) this.bj.findViewById(R.id.track_map_anim_button_image);
        hog hogVar = new hog(this.bj, this.bs, this.aj, this.ap, this.h);
        this.cj = hogVar;
        this.f13277a = new hnj(this, hogVar, this.bc);
        this.l = this.bj.findViewById(R.id.track_disconnect_tip);
        az();
        ay();
        cl();
        bi();
        bm();
        if (bt()) {
            ba();
        }
        bd();
        ax();
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.31
            @Override // java.lang.Runnable
            public void run() {
                String e2 = UnitUtil.e(CommonUtil.a(hnu.this.h.getResources().getString(R.string._2130850273_res_0x7f0231e1)), 1, 2);
                hnu.this.cj.ae().setText(e2);
                if (hnu.this.bt()) {
                    return;
                }
                hnu.this.cj.t().setText(e2);
            }
        });
        be();
        ac();
        u();
        bq();
        if (bs() && !bu()) {
            this.cj.bnt_().setVisibility(4);
        }
        if (bu()) {
            bc();
        }
    }

    public void b(int i) {
        View view = this.l;
        if (view == null) {
            return;
        }
        if (i < 0) {
            view.setVisibility(8);
            return;
        }
        view.setVisibility(0);
        ((HealthTextView) this.l.findViewById(R.id.track_disconnect_time_tx)).setText(UnitUtil.d(Math.round(i)));
        ((HealthButton) this.l.findViewById(R.id.track_disconnect_button)).setOnClickListener(new View.OnClickListener() { // from class: hnx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                hnu.this.bmZ_(view2);
            }
        });
    }

    /* synthetic */ void bmZ_(View view) {
        this.cg.finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bt() {
        return this.ah;
    }

    private void az() {
        if (bt()) {
            return;
        }
        ImageButton imageButton = (ImageButton) this.bj.findViewById(R.id.track_main_page_sport_camera);
        this.j = imageButton;
        imageButton.setOnClickListener(this);
        this.j.setVisibility(0);
    }

    public void u() {
        LogUtil.a("Track_TrackMainViewHolder", "refreshBoltButton() mSportType: ", Integer.valueOf(this.bs));
        jdx.b(new Runnable() { // from class: hof
            @Override // java.lang.Runnable
            public final void run() {
                hnu.this.v();
            }
        });
    }

    /* synthetic */ void v() {
        if (SportSupportUtil.f(this.bs) && bv()) {
            this.bc.sendEmptyMessage(4);
        }
    }

    private boolean bv() {
        List<gsy.b> e2 = gsy.b().e(this.bs);
        this.c = e2;
        LogUtil.a("Track_TrackMainViewHolder", "isConnectedBolt() mBoltDeviceInfos.size: ", Integer.valueOf(e2.size()));
        return koq.c(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ck() {
        gsy.b().c("Track_TrackMainViewHolder", new IBaseResponseCallback() { // from class: hnu.34
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                List<gsy.b> e2 = gsy.b().e(hnu.this.bs);
                LogUtil.a("Track_TrackMainViewHolder", "registerBoltConnectionStatusListener tempBoltDevices.size: ", Integer.valueOf(e2.size()));
                hnu.this.g(e2.size());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) {
        ImageButton imageButton = this.b;
        if (imageButton == null) {
            return;
        }
        if (i == 0) {
            imageButton.setVisibility(8);
            BoltCustomDialog.a().b();
        } else if (i == 1) {
            j(e(R.drawable._2131430534_res_0x7f0b0c86, R.drawable._2131430535_res_0x7f0b0c87));
        } else {
            if (i != 2) {
                return;
            }
            j(e(R.drawable._2131430536_res_0x7f0b0c88, R.drawable._2131430537_res_0x7f0b0c89));
        }
    }

    private int e(int i, int i2) {
        return (bt() || nrt.a(this.h)) ? i2 : i;
    }

    private void j(int i) {
        this.b.setBackgroundResource(i);
        int i2 = this.bs;
        if (i2 == 258 || i2 == 259) {
            ag();
        }
        this.b.setVisibility(0);
    }

    private void ag() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
        ImageButton imageButton = this.bf;
        if (imageButton == null || imageButton.getVisibility() == 8) {
            ImageButton imageButton2 = this.j;
            if (imageButton2 == null || imageButton2.getVisibility() == 8) {
                layoutParams.topMargin = 0;
                layoutParams.removeRule(3);
                layoutParams.addRule(6, this.w.getId());
                this.b.setLayoutParams(layoutParams);
            }
        }
    }

    private void ax() {
        this.cj.bny_().setClickable(true);
        if (!bt()) {
            this.cj.bni_().setClickable(true);
        }
        this.cj.bnj_().setClickable(true);
        this.cj.bnr_().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hnu.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (hnu.this.cj.bnr_().getWidth() == 0) {
                    return;
                }
                hnu.this.cj.bnr_().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) hnu.this.i.getLayoutParams();
                layoutParams.width = hnu.this.cj.bnr_().getWidth();
                layoutParams.height = hnu.this.cj.bnr_().getHeight();
                int[] iArr = new int[2];
                hnu.this.cj.bnr_().getLocationInWindow(iArr);
                if (LanguageUtil.bc(hnu.this.h)) {
                    layoutParams.setMargins(0, iArr[1], iArr[0], 0);
                } else {
                    layoutParams.setMargins(iArr[0], iArr[1], 0, 0);
                }
                double d = layoutParams.width / 2.0d;
                double d2 = iArr[0] + d;
                double d3 = iArr[1] + d;
                float sqrt = ((int) (Math.sqrt((d2 * d2) + (d3 * d3)) / d)) + 1;
                if (sqrt > hnu.this.e) {
                    hnu.this.e = sqrt;
                }
                hnu.this.i.setLayoutParams(layoutParams);
            }
        });
    }

    private void bk() {
        this.ch = new gww(this.h, new StorageParams(1), Integer.toString(20002));
    }

    private void be() {
        bk();
        this.bf = (ImageButton) this.bj.findViewById(R.id.track_main_page_sport_music);
        this.w = (LinearLayout) this.bj.findViewById(R.id.track_main_page_gps_ll);
        this.bd = (MusicControlLayout) this.bj.findViewById(R.id.music_content_layout);
        this.aa = (HealthColumnLinearLayout) this.bj.findViewById(R.id.music_column_layout);
        this.ab = (LinearLayout) this.bj.findViewById(R.id.music_control_layout);
        if (!bt()) {
            this.aa.setPadding(0, nsn.r(this.h) + nsn.c(this.h, 8.0f), 0, 0);
        } else {
            this.ab.setPadding(0, nsn.r(this.h) + nsn.c(this.h, 8.0f), 0, 0);
        }
        ch();
        this.ay = (ImageButton) this.bj.findViewById(R.id.track_main_page_btn_lock);
        Context context = BaseApplication.getContext();
        this.bd.setSportTypeDrawable(bt(), this.bs);
        ab();
        if (bz()) {
            d(context);
            this.bf.setOnClickListener(this);
        }
        if (bt() || nrt.a(this.h)) {
            h(R.drawable._2131431845_res_0x7f0b11a5);
        }
    }

    private void h(int i) {
        if (this.ay == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "setLockBackground mLockButton is null");
        } else if (LanguageUtil.bc(this.h)) {
            this.ay.setBackground(nrz.cKn_(this.h, i));
        } else {
            this.ay.setBackgroundResource(i);
        }
    }

    private boolean bz() {
        if (!CommonUtil.bd() || gtx.c(this.h).aq() || gtx.c(this.h).at()) {
            return false;
        }
        return gwg.c();
    }

    private void ch() {
        int r = nsn.r(this.h) + this.h.getResources().getDimensionPixelSize(R.dimen._2131362184_res_0x7f0a0188);
        int dimensionPixelSize = this.h.getResources().getDimensionPixelSize(R.dimen._2131364634_res_0x7f0a0b1a);
        int dimensionPixelSize2 = this.h.getResources().getDimensionPixelSize(R.dimen._2131364635_res_0x7f0a0b1b);
        int dimensionPixelSize3 = this.h.getResources().getDimensionPixelSize(R.dimen._2131363692_res_0x7f0a076c);
        bmQ_(this.bf, 0, r, dimensionPixelSize, 0);
        int i = this.bs;
        if (i != 282) {
            switch (i) {
                case 257:
                case 258:
                case 259:
                case 260:
                    break;
                default:
                    LogUtil.h("Track_TrackMainViewHolder", "don't need setmarginTop");
                    break;
            }
        }
        bmQ_(this.w, dimensionPixelSize2, r, dimensionPixelSize3, 0);
    }

    private void bmQ_(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        }
        marginLayoutParams.setMargins(i, i2, i3, i4);
        view.setLayoutParams(marginLayoutParams);
    }

    private void d(Context context) {
        if (gww.a(context) == 0) {
            this.bo = 0;
            if (bt()) {
                this.bf.setBackgroundResource(R.drawable._2131431865_res_0x7f0b11b9);
                return;
            } else {
                this.bf.setBackgroundResource(R.drawable._2131431879_res_0x7f0b11c7);
                return;
            }
        }
        am();
    }

    private void bq() {
        LogUtil.a("Track_TrackMainViewHolder", "istahiti ", Boolean.valueOf(nsn.ag(this.h)));
        Bundle bundle = new Bundle();
        bundle.putString("duration", UnitUtil.d(Math.round(0.0f)));
        bundle.putString("distance", this.h.getResources().getString(R.string._2130850273_res_0x7f0231e1));
        bundle.putString("speed", this.h.getResources().getString(R.string._2130850262_res_0x7f0231d6));
        bundle.putString("pace", this.h.getResources().getString(R.string._2130850262_res_0x7f0231d6));
        bundle.putString("calorie", String.valueOf(0));
        updateSportViewFragment(bundle);
    }

    private void bd() {
        if (LanguageUtil.bc(this.h)) {
            this.cj.bns_().setImageResource(R.drawable._2131431869_res_0x7f0b11bd);
        }
        if (gtx.c(this.h).au() || by()) {
            ah();
        }
    }

    private void ah() {
        if (this.cj.bnl_() != null) {
            this.cj.bnl_().setVisibility(0);
        }
        ShowDataPanelLayout p = this.cj.p();
        if (p == null) {
            return;
        }
        int dimension = (int) this.h.getResources().getDimension(R.dimen._2131361864_res_0x7f0a0048);
        if (!CommonUtil.bh()) {
            dimension = (int) (dimension + nsf.a(R.dimen._2131362906_res_0x7f0a045a));
        }
        ViewGroup.LayoutParams layoutParams = p.getLayoutParams();
        layoutParams.height = dimension;
        p.setLayoutParams(layoutParams);
    }

    private void ba() {
        if (bu()) {
            this.bj.findViewById(R.id.indoor_run_tips).setVisibility(4);
        } else {
            this.z = (HealthTextView) this.bj.findViewById(R.id.indoor_run_tips);
            if (nsn.s()) {
                nsn.b(this.z);
            }
            if (gvv.d(this.h)) {
                ImageView imageView = (ImageView) this.bj.findViewById(R.id.track_main_page_free_indoor_running);
                this.t = imageView;
                imageView.setVisibility(0);
                this.z.setText(this.h.getString(R.string._2130843107_res_0x7f0215e3));
            }
        }
        this.bl = (ImageView) this.bj.findViewById(R.id.runway);
        RelativeLayout relativeLayout = (RelativeLayout) this.bj.findViewById(R.id.layout_StopOrResume);
        this.av = relativeLayout;
        BaseActivity.cancelLayoutById(relativeLayout);
    }

    public void b(boolean z) {
        if (z) {
            this.bl.setBackgroundResource(R.drawable._2131431338_res_0x7f0b0faa);
        } else {
            this.bl.setBackgroundResource(R.drawable._2131431339_res_0x7f0b0fab);
        }
    }

    public void af() {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.c();
        }
    }

    private void bf() {
        this.be = (RelativeLayout) this.bj.findViewById(R.id.track_main_pad_guide_bg);
        this.bh = (HealthTextView) this.bj.findViewById(R.id.track_main_pad_guide_txt);
        this.u = (HealthButton) this.bj.findViewById(R.id.track_main_pad_guide_btn);
        IllustrationView illustrationView = (IllustrationView) this.bj.findViewById(R.id.track_main_pad_guide_img);
        this.ag = illustrationView;
        illustrationView.setIllustration(R.drawable._2131431913_res_0x7f0b11e9);
        this.ag.setVisibility(0);
        this.ag.setSubHeaderVisibility(8);
        this.be.setVisibility(0);
        this.bh.setVisibility(0);
        this.u.setVisibility(0);
        this.u.setOnClickListener(new View.OnClickListener() { // from class: hnv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                hnu.this.bmU_(view);
            }
        });
    }

    /* synthetic */ void bmU_(View view) {
        this.cg.finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bh() {
        RelativeLayout relativeLayout = (RelativeLayout) this.bj.findViewById(R.id.track_main_page_perm_bg);
        this.bg = relativeLayout;
        if (relativeLayout == null) {
            LogUtil.h("Track_TrackMainViewHolder", "in initPermissionBackground method mPermissionBackground is null");
            return;
        }
        int i = this.bs;
        if (i == 258 || i == 264) {
            bj();
        }
        this.bg.setOnClickListener(new View.OnClickListener() { // from class: hnu.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.h("Track_TrackMainViewHolder", " the countdown view can't click");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void bj() {
        String str;
        int i;
        boolean z;
        if (this.bs == 258) {
            str = "SportAdSportingCountdownBgOutdoor";
            i = 16001;
            z = true;
        } else {
            str = "SportAdSportingCountdownBgIndoor";
            i = 16002;
            z = false;
        }
        gxu a2 = gtv.a(str);
        if (a2 != null) {
            String a3 = a2.a();
            String e2 = a2.e();
            if (!TextUtils.isEmpty(e2) && nsn.ag(this.h)) {
                LogUtil.a("Track_TrackMainViewHolder", "SportAd background is big, isOutdoorRun: ", Boolean.valueOf(z));
                a3 = e2;
            }
            e(z, i, a3);
            return;
        }
        LogUtil.h("Track_TrackMainViewHolder", "SportAd background not valid data, isOutdoorRun: ", Boolean.valueOf(z));
    }

    private void e(final boolean z, final int i, String str) {
        nrf.b(str, new CustomTarget<Bitmap>() { // from class: hnu.2
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
            }

            @Override // com.bumptech.glide.request.target.Target
            /* renamed from: bnc_, reason: merged with bridge method [inline-methods] */
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                Drawable cHq_ = nrf.cHq_(bitmap);
                if (cHq_ != null) {
                    LogUtil.a("Track_TrackMainViewHolder", "SportAd load background success, isOutdoorRun: ", Boolean.valueOf(z));
                    hnu.this.bg.setBackground(cHq_);
                    int i2 = i;
                    MarketingBiUtils.d(i2, gtv.b(i2));
                }
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                LogUtil.a("Track_TrackMainViewHolder", "SportAd load background onLoadFailed(), isOutdoorRun: ", Boolean.valueOf(z));
            }
        });
    }

    public void ai() {
        RelativeLayout relativeLayout = this.k;
        if (relativeLayout != null) {
            relativeLayout.post(new Runnable() { // from class: hnu.5
                @Override // java.lang.Runnable
                public void run() {
                    if (hnu.this.k != null) {
                        int c2 = nsn.c(hnu.this.h, 16.0f);
                        if (hnu.this.ce != null) {
                            hnu.this.ce.c(c2, 0, 0, hnu.this.k.getHeight());
                            return;
                        }
                        return;
                    }
                    LogUtil.h("Track_TrackMainViewHolder", "setLogoPadding mControlAnimLinearLayout is null");
                }
            });
        }
    }

    private void am() {
        tye.e(BaseApplication.getContext(), "huaweisport", "q3@!DF5*&$9MrhCS", "tingshu", new ISchemeListener() { // from class: hnu.9
            @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
            public void onSuccess(Object obj) {
                if (obj != null) {
                    LogUtil.a("Track_TrackMainViewHolder", "checkSupportListenBook ok");
                } else {
                    LogUtil.h("Track_TrackMainViewHolder", "checkSupportListenBook  obj  is null");
                }
                hnu.this.cg.runOnUiThread(new Runnable() { // from class: hnu.9.4
                    @Override // java.lang.Runnable
                    public void run() {
                        hnu.this.bo = 1;
                        if (hnu.this.bt()) {
                            hnu.this.bf.setBackgroundResource(R.drawable._2131431862_res_0x7f0b11b6);
                        } else {
                            hnu.this.bf.setBackgroundResource(R.drawable._2131431878_res_0x7f0b11c6);
                        }
                    }
                });
            }

            @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
            public void onError(int i, String str) {
                LogUtil.h("Track_TrackMainViewHolder", "errorMsg is", str, "errorCode is", Integer.valueOf(i));
                hnu.this.cg.runOnUiThread(new Runnable() { // from class: hnu.9.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!gwg.i(BaseApplication.getContext()) || !CommonUtil.bd()) {
                            hnu.this.bf.setVisibility(8);
                            return;
                        }
                        hnu.this.bo = 0;
                        if (hnu.this.bt()) {
                            hnu.this.bf.setBackgroundResource(R.drawable._2131431865_res_0x7f0b11b9);
                        } else {
                            hnu.this.bf.setBackgroundResource(R.drawable._2131431879_res_0x7f0b11c7);
                        }
                    }
                });
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        boolean z = this.ae;
        if (z || view == null) {
            LogUtil.h("Track_TrackMainViewHolder", "mIsButtonPlayingAnimation=", Boolean.valueOf(z));
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.a(700)) {
            LogUtil.h("Track_TrackMainViewHolder", "view click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.track_main_page_btn_pause) {
            LogUtil.a("Track_TrackMainViewHolder", "click btn_pause button");
            if (bu()) {
                gso.e().c(0);
                this.cg.g();
            } else {
                cc();
            }
        } else if (id == R.id.track_main_page_btn_play) {
            LogUtil.a("Track_TrackMainViewHolder", "click btn_play button");
            if (bu()) {
                gso.e().c(0);
                this.cg.o();
            } else if (p()) {
                aa();
            }
        } else if (id == R.id.track_main_page_btn_lock) {
            LogUtil.a("Track_TrackMainViewHolder", "track_main_page_btn_lock");
            cu();
        } else if (id == R.id.track_main_page_btn_setting) {
            if (bu()) {
                cz();
            } else {
                ca();
            }
        } else if (id == R.id.track_main_page_bolt_icon) {
            BoltCustomDialog.a().b(this.d, this.bs, (BoltCustomDialog.OnConfirmCallBack) null, 1);
        } else if (id == R.id.track_main_page_sport_music) {
            LogUtil.a("Track_TrackMainViewHolder", "click music or listen book button");
            cd();
        } else if (id == R.id.data_value_show_container || id == R.id.switch_data_hint_text) {
            if (this.o.getVisibility() == 0 && this.f13277a.a() && this.f13277a.e()) {
                this.bc.sendMessage(this.bc.obtainMessage(3));
            }
        } else if (id == R.id.track_main_page_sport_camera) {
            dd();
        } else if (id == R.id.next_action_image) {
            cf();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void cf() {
        if (this.bt != null) {
            if (p()) {
                aa();
            }
            this.bt.t();
        }
    }

    private void cd() {
        HashMap hashMap = new HashMap(16);
        if (this.bo == 0) {
            d(hashMap);
        } else {
            nsn.cLN_("com.huawei.hwireader", com.huawei.haf.application.BaseApplication.wa_(), nsf.h(R.string._2130850371_res_0x7f023243), new View.OnClickListener() { // from class: hnu.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    tye.d(new ISchemeListener() { // from class: hnu.10.3
                        @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                        public void onSuccess(Object obj) {
                            LogUtil.a("Track_TrackMainViewHolder", "openTingChannel is onSuccess");
                        }

                        @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                        public void onError(int i, String str) {
                            LogUtil.a("Track_TrackMainViewHolder", "openTingChannel is onError");
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            hashMap.put("sportMusicType", 1);
        }
        hashMap.put("click", 1);
        hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 1);
        hashMap.put("musicType", Integer.valueOf(gwg.i(this.h) ? 1 : 0));
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.bs));
        ixx.d().d(this.h, AnalyticsValue.MOTION_TRACK_1040014.value(), hashMap, 0);
    }

    private void cc() {
        if (t()) {
            ce();
            if (this.cj.l() != null) {
                MarketingBiUtils.b(this.cj.o(), this.cj.l(), System.currentTimeMillis());
            }
        }
    }

    private void dd() {
        PermissionUtil.b(this.d, PermissionUtil.PermissionType.CAMERA_IMAGE, new CustomPermissionAction(this.d) { // from class: hnu.6
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                hnu.this.cg.c(new CameraUtil(hnu.this.d).e());
            }
        });
    }

    public void ab() {
        if (this.bd == null || gtx.c(this.h).aq()) {
            return;
        }
        if (!SportMusicController.a().d()) {
            this.bd.e(this.bs);
        }
        this.bd.c();
        if (bz()) {
            ct();
        } else {
            bb();
        }
    }

    private void bb() {
        this.aa.setVisibility(8);
        this.bf.setVisibility(8);
    }

    private void ct() {
        if (this.ch.f(this.bs) == 1 && gwg.a(this.h) && gvv.b(this.h) == 0) {
            this.aa.setVisibility(0);
            this.bf.setVisibility(8);
        } else {
            this.aa.setVisibility(8);
            this.bf.setVisibility(0);
        }
    }

    public void ac() {
        ImageButton imageButton = this.j;
        if (imageButton == null || !(imageButton.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        if (this.bf.getVisibility() == 8) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
            layoutParams.topMargin = 0;
            layoutParams.removeRule(3);
            layoutParams.addRule(6, this.w.getId());
            this.j.setLayoutParams(layoutParams);
            return;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
        layoutParams2.topMargin = nsn.c(this.h, 2.0f);
        layoutParams2.removeRule(6);
        layoutParams2.addRule(3, this.bf.getId());
        this.j.setLayoutParams(layoutParams2);
    }

    private void ca() {
        Intent intent = new Intent();
        intent.setClassName(this.h, "com.huawei.ui.homehealth.runcard.SportMetronomeActivity");
        intent.putExtra("jump_source", 2);
        intent.putExtra("is_support_metronome", bw());
        this.cg.bdc_(intent, 2000);
        this.cg.j();
    }

    private void d(Map<String, Object> map) {
        try {
            SportMusicController.a().d(this.ch.d(this.bs) == null ? 1 : 2, this.bs, true);
        } catch (ActivityNotFoundException e2) {
            LogUtil.a("Track_TrackMainViewHolder", "music running list activity  not found, check ", e2.getMessage());
        } catch (SecurityException e3) {
            LogUtil.a("Track_TrackMainViewHolder", "SE ", e3.getMessage());
        }
        map.put("sportMusicType", 0);
    }

    public void d(boolean z) {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.e(z);
        }
    }

    public void c() {
        if (!this.as || p()) {
            n();
            k();
        }
        hog hogVar = this.cj;
        if (hogVar == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "addOnGlobalLayoutListener mTrackMainViewHolderBean is null");
            return;
        }
        if (hogVar.bnk_() == null || this.cj.bnk_().getViewTreeObserver() == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "addOnGlobalLayoutListener  mTrackMainViewHolderBean.getLayoutOperation() ", "mTrackMainViewHolderBean.getLayoutOperation().getViewTreeObserver() is null");
        } else if (this.ai) {
            LogUtil.a("Track_TrackMainViewHolder", "onGlobalLayoutListener Already added");
        } else {
            this.ai = true;
            this.cj.bnk_().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hnu.8
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (hnu.this.cj != null) {
                        if (hnu.this.cj.bnk_() != null && hnu.this.cj.bnk_().getViewTreeObserver() != null) {
                            if (!eie.alE_(hnu.this.cj.bnk_())) {
                                LogUtil.a("Track_TrackMainViewHolder", "LayoutOperation Invisible");
                                return;
                            }
                            hnu.this.cj.bnk_().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            hnu.this.ai = false;
                            if (hnu.this.p()) {
                                hnu.this.cn();
                                return;
                            } else if (hnu.this.as) {
                                hnu.this.at();
                                return;
                            } else {
                                LogUtil.a("Track_TrackMainViewHolder", "isn't pause and sportingCommand");
                                return;
                            }
                        }
                        LogUtil.b("Track_TrackMainViewHolder", "onGlobalLayout  mTrackMainViewHolderBean.getLayoutOperation() ", "mTrackMainViewHolderBean.getLayoutOperation().getViewTreeObserver() is null");
                        return;
                    }
                    LogUtil.b("Track_TrackMainViewHolder", "onGlobalLayout mTrackMainViewHolderBean is null");
                }
            });
        }
    }

    public void k() {
        hog hogVar = this.cj;
        if (hogVar == null || hogVar.bns_() == null || this.cj.al() == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "initPlayAndStopMargin mTrackMainViewHolderBean is null", "requestTrackMainPageBtnStop is null", "getTrackMainPageButtonPlay is null");
            return;
        }
        if (this.cj.bns_().getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.cj.bns_().getLayoutParams();
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(0);
            layoutParams.addRule(14);
        }
        if (this.cj.al().getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.cj.al().getLayoutParams();
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(0);
            layoutParams2.addRule(14);
        }
    }

    public void n() {
        hog hogVar = this.cj;
        if (hogVar == null || hogVar.bnq_() == null || this.cj.bnt_() == null) {
            LogUtil.b("Track_TrackMainViewHolder", "initLockAndMusicMargin mTrackMainViewHolderBean is null", "getTrackMainPageButtonLock is null", "getTrackMainPageButtonSetting is null");
            return;
        }
        int c2 = nsn.c(this.h, 20.0f);
        if (this.cj.bnq_().getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.cj.bnq_().getLayoutParams();
            layoutParams.setMarginStart(c2);
            layoutParams.setMarginEnd(0);
            layoutParams.addRule(20);
        }
        if (this.cj.bnt_().getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.cj.bnt_().getLayoutParams();
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(c2);
            layoutParams2.addRule(21);
        }
    }

    public void ad() {
        hog hogVar = this.cj;
        if (hogVar == null || hogVar.bnq_() == null || this.cj.bnt_() == null) {
            LogUtil.b("Track_TrackMainViewHolder", "resumeLockAndMusicMargin mTrackMainViewHolderBean is null", "getTrackMainPageButtonLock is null", "getTrackMainPageButtonSetting is null");
            return;
        }
        if (this.cj.bnq_().getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.cj.bnq_().getLayoutParams();
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(0);
            layoutParams.addRule(20);
        }
        if (this.cj.bnt_().getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.cj.bnt_().getLayoutParams();
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(0);
            layoutParams2.addRule(21);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cn() {
        int left;
        int width;
        Context context = this.h;
        if (context == null) {
            LogUtil.h("Track_TrackMainViewHolder", "mContext is null");
            return;
        }
        if (this.ae) {
            LogUtil.h("Track_TrackMainViewHolder", "showBtnStopAndPlay, but IsButtonPlayingAnimation");
            return;
        }
        int c2 = nsn.c(context, 20.0f);
        if (LanguageUtil.bc(this.h)) {
            left = this.cj.bnq_().getLeft() - this.cj.bnt_().getRight();
            width = this.cj.bnr_().getWidth();
        } else {
            left = this.cj.bnt_().getLeft() - this.cj.bnq_().getRight();
            width = this.cj.bnr_().getWidth();
        }
        int width2 = (this.cj.bnr_().getWidth() / 2) + (((left - (width * 2)) + c2) / 6);
        this.cj.bnr_().setVisibility(4);
        this.cj.bns_().setVisibility(0);
        this.cj.al().setVisibility(0);
        if (LanguageUtil.bc(this.h)) {
            bnb_(0, 0 - width2, 300, this.cj.al(), false);
            bnb_(0, width2, 300, this.cj.bns_(), false);
        } else {
            bnb_(0, width2, 300, this.cj.al(), false);
            bnb_(0, 0 - width2, 300, this.cj.bns_(), false);
        }
        cv();
    }

    private void cv() {
        int c2 = nsn.c(this.h, 20.0f);
        if (LanguageUtil.bc(this.h)) {
            bnb_(0, c2, 300, this.cj.bnq_(), false);
            if (this.cj.bnt_().getVisibility() == 0) {
                bnb_(0, -c2, 300, this.cj.bnt_(), false);
                return;
            }
            return;
        }
        bnb_(0, -c2, 300, this.cj.bnq_(), false);
        if (this.cj.bnt_().getVisibility() == 0) {
            bnb_(0, c2, 300, this.cj.bnt_(), false);
        }
    }

    private void aw() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.7
            @Override // java.lang.Runnable
            public void run() {
                if (!hnu.this.ae) {
                    hnu.this.df();
                    hnu.this.cj.aj().setVisibility(4);
                    hnu.this.cj.al().setProgressZero();
                    hnu.this.cj.al().a(true);
                    int left = ((hnu.this.cj.bnr_().getLeft() + hnu.this.cj.bnr_().getRight()) / 2) - ((hnu.this.cj.al().getLeft() + hnu.this.cj.al().getRight()) / 2);
                    hnu hnuVar = hnu.this;
                    hnuVar.bnb_(0, 0 - left, 300, hnuVar.cj.bns_(), true);
                    hnu hnuVar2 = hnu.this;
                    hnuVar2.bnb_(0, left, 300, hnuVar2.cj.al(), true);
                    hnu.this.au();
                    return;
                }
                LogUtil.h("Track_TrackMainViewHolder", "foldBtnStopAndPlay, but IsButtonPlayingAnimation");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void au() {
        int c2 = nsn.c(this.h, 20.0f);
        if (LanguageUtil.bc(this.h)) {
            bnb_(0, -c2, 300, this.cj.bnq_(), true);
            if (this.cj.bnt_().getVisibility() == 0) {
                bnb_(0, c2, 300, this.cj.bnt_(), true);
                return;
            }
            return;
        }
        bnb_(0, c2, 300, this.cj.bnq_(), false);
        if (this.cj.bnt_().getVisibility() == 0) {
            bnb_(0, -c2, 300, this.cj.bnt_(), false);
        }
    }

    public void bnb_(final int i, final int i2, int i3, final View view, final boolean z) {
        if (view == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "slideView view is null");
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(i, i2, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new OvershootInterpolator());
        translateAnimation.setDuration(i3);
        translateAnimation.setStartOffset(0L);
        translateAnimation.setInterpolator(new OvershootInterpolator(0.0f));
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: hnu.14
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                hnu.this.ae = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                hnu.this.bmS_(z, view, i2, i);
            }
        });
        view.startAnimation(translateAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void bmS_(boolean r5, android.view.View r6, int r7, int r8) {
        /*
            r4 = this;
            int r0 = r6.getId()
            r1 = 2131571925(0x7f0d34d5, float:1.8769547E38)
            r2 = 0
            if (r0 == r1) goto L16
            int r0 = r6.getId()
            r1 = 2131571928(0x7f0d34d8, float:1.8769553E38)
            if (r0 != r1) goto L14
            goto L16
        L14:
            r0 = r2
            goto L17
        L16:
            r0 = 1
        L17:
            if (r0 == 0) goto L4e
            android.content.Context r1 = r4.h
            boolean r1 = health.compact.a.LanguageUtil.bc(r1)
            if (r1 == 0) goto L4e
            boolean r1 = r4.bt()
            if (r1 == 0) goto L37
            android.widget.RelativeLayout r1 = r4.av
            int r1 = r1.getWidth()
            int r3 = r6.getLeft()
            int r1 = r1 - r3
            int r3 = r6.getWidth()
            goto L4a
        L37:
            hog r1 = r4.cj
            android.widget.RelativeLayout r1 = r1.bnk_()
            int r1 = r1.getWidth()
            int r3 = r6.getLeft()
            int r1 = r1 - r3
            int r3 = r6.getWidth()
        L4a:
            int r3 = r3 * 2
            int r1 = r1 - r3
            goto L5c
        L4e:
            android.content.Context r1 = r4.h
            boolean r1 = health.compact.a.LanguageUtil.bc(r1)
            if (r1 == 0) goto L60
            if (r5 != 0) goto L60
            int r1 = r6.getLeft()
        L5c:
            int r8 = r7 - r8
            int r1 = r1 - r8
            goto L67
        L60:
            int r1 = r6.getLeft()
            int r8 = r7 - r8
            int r1 = r1 + r8
        L67:
            int r8 = r6.getTop()
            r6.clearAnimation()
            bmM_(r6, r1, r8)
            r8 = 2131571929(0x7f0d34d9, float:1.8769555E38)
            if (r5 == 0) goto L97
            if (r0 != 0) goto L97
            int r5 = r6.getVisibility()
            if (r5 == 0) goto L82
            r6.setVisibility(r2)
            goto Lb0
        L82:
            r5 = 8
            r6.setVisibility(r5)
            int r5 = r6.getId()
            if (r5 != r8) goto Lb0
            hog r5 = r4.cj
            android.widget.ImageView r5 = r5.bnr_()
            r5.setVisibility(r2)
            goto Lb0
        L97:
            int r5 = r6.getId()
            if (r5 != r8) goto Lb0
            hog r5 = r4.cj
            com.huawei.ui.commonui.healthtextview.HealthTextView r5 = r5.aj()
            float r7 = (float) r7
            r5.setTranslationX(r7)
            hog r5 = r4.cj
            com.huawei.healthcloud.plugintrack.ui.view.CircleProgressButton r5 = r5.al()
            r5.a(r2)
        Lb0:
            r6.postInvalidate()
            hnu$e r5 = r4.bb
            hnu$12 r6 = new hnu$12
            r6.<init>()
            r7 = 50
            r5.postDelayed(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hnu.bmS_(boolean, android.view.View, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cg() {
        hog hogVar = this.cj;
        if (hogVar == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "mTrackMainViewHolderBean is null in refreshLastState");
            return;
        }
        ImageView bnr_ = hogVar.bnr_();
        if (bnr_ == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "pauseButton is null in refreshLastState");
            return;
        }
        boolean p = p();
        boolean z = bnr_.getVisibility() == 0;
        if (p && z) {
            ReleaseLogUtil.e("Track_TrackMainViewHolder", "change to Pause");
            cn();
        } else {
            if (p || z) {
                return;
            }
            ReleaseLogUtil.e("Track_TrackMainViewHolder", "change to sporting");
            aw();
        }
    }

    public boolean q() {
        return this.am;
    }

    public void c(boolean z) {
        this.am = z;
    }

    private void cu() {
        hog hogVar = this.cj;
        if (hogVar == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "showLockFragment mTrackMainViewHolderBean is null");
            return;
        }
        LinearLayout bnj_ = hogVar.bnj_();
        if (bnj_ == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "showLockFragment layoutLockOperation is null");
            return;
        }
        int i = 1;
        if (this.au) {
            bmG_(bnj_);
            if (!this.am) {
                i = 0;
            }
        } else {
            this.am = true;
            bnj_.setAnimation(bmF_());
            bnj_.setVisibility(0);
            if (bt()) {
                this.av.setVisibility(4);
            } else {
                this.cj.bnk_().setVisibility(4);
            }
            CircleProgressButton al = this.cj.al();
            if (al != null) {
                al.setOnTouchListener(null);
            }
            jcf.bEE_(al, 1);
            bmK_(this.cj.bnt_());
            bmK_(this.cj.bnr_());
            bmK_(this.cj.bns_());
        }
        this.f13277a.a(false);
        df();
        if (this.cj.bnw_() != null) {
            this.cj.bnw_().setClickable(false);
        }
        this.cj.aj().setVisibility(4);
        i(i);
    }

    private void bmK_(View view) {
        if (view == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "setImportantForAccessibility view is null");
        } else {
            view.setEnabled(true);
            jcf.bEE_(view, 1);
        }
    }

    private void bmG_(View view) {
        if (view == null || this.ay == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "setAccessibility layoutLockOperation ", view, " mLockButton ", this.ay);
            return;
        }
        this.am = !this.am;
        view.setVisibility(8);
        this.ay.setVisibility(0);
        bmI_(this.cj.al(), this.am);
        bmH_(this.cj.bnt_(), this.am);
        bmH_(this.cj.bnr_(), this.am);
        bmH_(this.cj.bns_(), this.am);
        if (bt() || nrt.a(this.h)) {
            h(this.am ? R.drawable._2131431859_res_0x7f0b11b3 : R.drawable._2131431845_res_0x7f0b11a5);
        } else {
            h(this.am ? R.drawable._2131431858_res_0x7f0b11b2 : R.drawable._2131431866_res_0x7f0b11ba);
        }
        jcf.bEk_(this.ay, nsf.h(this.am ? R.string._2130850633_res_0x7f023349 : R.string._2130850673_res_0x7f023371));
        jcf.bEz_(this.ay, nsf.h(this.am ? R.string._2130850672_res_0x7f023370 : R.string._2130850632_res_0x7f023348));
    }

    private void bmI_(View view, boolean z) {
        if (view == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "setAccessibilityForStopButton view is null");
        } else if (z) {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: hnu.11
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return true;
                }
            });
            jcf.bEE_(view, 2);
        } else {
            view.setOnTouchListener(null);
            jcf.bEE_(view, 1);
        }
    }

    private void bmH_(View view, boolean z) {
        if (view == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "setAccessibilityForButton view is null");
            return;
        }
        view.setEnabled(!z);
        if (z) {
            jcf.bEE_(view, 2);
        } else {
            jcf.bEE_(view, 1);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.view.UnlockSliderView.SliderListener
    public void siderRight() {
        this.cj.bnj_().setAnimation(bmE_());
        this.cj.bnj_().setVisibility(8);
        this.cj.bnk_().setAnimation(bmF_());
        this.cj.bnk_().setVisibility(0);
        i(0);
    }

    private void i(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.h, AnalyticsValue.MOTION_TRACK_1040028.value(), hashMap, 0);
    }

    private TranslateAnimation bmF_() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setDuration(250L);
        return translateAnimation;
    }

    private TranslateAnimation bmE_() {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setInterpolator(new DecelerateInterpolator());
        translateAnimation.setDuration(250L);
        return translateAnimation;
    }

    private void cy() {
        if (!b(this.d) || this.bg == null) {
            return;
        }
        if (bu()) {
            this.bj.post(new Runnable() { // from class: hnt
                @Override // java.lang.Runnable
                public final void run() {
                    hnu.this.w();
                }
            });
        } else {
            if (bp()) {
                return;
            }
            this.bt.bw();
            as();
        }
    }

    /* synthetic */ void w() {
        this.bg.setVisibility(8);
        if (!bt()) {
            this.az.setVisibility(8);
        }
        de();
        this.bj.invalidate();
    }

    private void as() {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.e(8);
        }
        ImageView imageView = this.bl;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        CountdownDialog countdownDialog = new CountdownDialog(this.d, this.bj);
        this.n = countdownDialog;
        countdownDialog.setTimeStart(3);
        this.n.addEndCountdown(new OnEndCountdownListener() { // from class: hnu.13
            @Override // com.huawei.health.basesport.sportui.OnEndCountdownListener
            public void endCountdown() {
                hnu.this.dc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dc() {
        ImageView imageView;
        hnq hnqVar;
        if (b(this.d)) {
            if (!bt() && (hnqVar = this.ce) != null) {
                hnqVar.e(0);
            }
            if (bt() && (imageView = this.bl) != null) {
                imageView.setVisibility(0);
            }
            View view = this.bj;
            if (view != null) {
                view.setBackgroundResource(R.color._2131296657_res_0x7f090191);
            }
            this.bg.setVisibility(8);
            bmT_(new Animator.AnimatorListener() { // from class: hnu.20
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    hnu.this.f.setVisibility(8);
                    if (!hnu.this.bt()) {
                        hnu.this.az.setVisibility(8);
                    }
                    hnu.this.bb.postDelayed(new Runnable() { // from class: hnu.20.5
                        @Override // java.lang.Runnable
                        public void run() {
                            hnu.this.de();
                        }
                    }, 50L);
                }
            });
        }
    }

    private boolean bp() {
        if ((!this.bt.ba() || !ktj.e(this.h)) && !this.bt.aq()) {
            return false;
        }
        this.bg.setVisibility(8);
        if (!bt()) {
            this.az.setVisibility(8);
        }
        this.bj.post(new Runnable() { // from class: hnu.16
            @Override // java.lang.Runnable
            public void run() {
                hnu.this.de();
                hnu.this.bj.invalidate();
            }
        });
        return true;
    }

    private void bmT_(Animator.AnimatorListener animatorListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.i, "scaleX", this.e, 1.0f);
        ofFloat.setDuration(500L);
        ofFloat.setInterpolator(new DecelerateInterpolator(2.0f));
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.i, "scaleY", this.e, 1.0f);
        ofFloat2.setDuration(500L);
        ofFloat2.setInterpolator(new DecelerateInterpolator(2.0f));
        this.bp = (GradientDrawable) this.i.getBackground();
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: hnu.18
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator == null || !(valueAnimator.getAnimatedValue() instanceof Float)) {
                    LogUtil.b("Track_TrackMainViewHolder", "valueAnimator is invalid");
                } else {
                    hnu.this.bp.setColor(hnu.this.c(((Float) valueAnimator.getAnimatedValue()).floatValue()));
                }
            }
        });
        ofFloat3.setDuration(500L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
        if (!bt()) {
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.az, "alpha", 1.0f, 0.0f);
            ofFloat4.setStartDelay(100L);
            ofFloat4.setDuration(400L);
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.k, "translationY", r3.getHeight(), 0.0f);
            ofFloat5.setStartDelay(100L);
            ofFloat5.setDuration(400L);
            animatorSet.playTogether(ofFloat4, ofFloat5);
        }
        animatorSet.addListener(animatorListener);
        this.f.setVisibility(0);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Context context) {
        if (!(context instanceof Activity)) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "context is not instanceof Activity");
            return false;
        }
        Activity activity = (Activity) context;
        if (!activity.isDestroyed() && !activity.isFinishing()) {
            return true;
        }
        ReleaseLogUtil.d("Track_TrackMainViewHolder", "Activity is invalid.isDestoryed", Boolean.valueOf(activity.isDestroyed()), "isFinishing", Boolean.valueOf(activity.isFinishing()));
        return false;
    }

    public void d(int i) {
        this.y = i;
        if (i == 0) {
            cj();
            return;
        }
        if (i == 1) {
            cr();
            return;
        }
        if (i == 2) {
            cw();
            return;
        }
        if (i == 3) {
            cs();
        } else if (i == 4) {
            cx();
        } else {
            LogUtil.b("Track_TrackMainViewHolder", "Wrong GPS signal");
        }
    }

    private void cx() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.17
            @Override // java.lang.Runnable
            public void run() {
                if (hnu.this.cj != null) {
                    hnu.this.cj.af().setText(R.string._2130839793_res_0x7f0208f1);
                    hnu.this.cj.ah().setVisibility(8);
                    hnu.this.cj.ai().setVisibility(8);
                    hnu.this.cj.bnv_().setVisibility(0);
                    hnu.this.cj.bnv_().setBackground(hnu.this.h.getResources().getDrawable(R.drawable._2131431840_res_0x7f0b11a0));
                    return;
                }
                ReleaseLogUtil.c("Track_TrackMainViewHolder", "showStrongSignal mTrackMainViewHolderBean is null");
            }
        });
    }

    private void cs() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.19
            @Override // java.lang.Runnable
            public void run() {
                if (hnu.this.cj != null) {
                    hnu.this.cj.af().setText(R.string._2130839793_res_0x7f0208f1);
                    hnu.this.cj.ah().setVisibility(8);
                    hnu.this.cj.ai().setVisibility(8);
                    hnu.this.cj.bnv_().setVisibility(0);
                    hnu.this.cj.bnv_().setBackground(hnu.this.h.getResources().getDrawable(R.drawable._2131431839_res_0x7f0b119f));
                    return;
                }
                ReleaseLogUtil.c("Track_TrackMainViewHolder", "showNormalSignal mTrackMainViewHolderBean is null");
            }
        });
    }

    private void cw() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.24
            @Override // java.lang.Runnable
            public void run() {
                if (hnu.this.cj != null) {
                    hnu.this.cj.af().setText(R.string._2130839793_res_0x7f0208f1);
                    hnu.this.cj.ai().setVisibility(8);
                    hnu.this.cj.bnv_().setVisibility(0);
                    hnu.this.cj.bnv_().setBackground(hnu.this.h.getResources().getDrawable(R.drawable._2131431838_res_0x7f0b119e));
                    hnu.this.cj.ah().setVisibility(0);
                    if (hnu.this.ak && hnu.this.ao) {
                        hnu.this.cj.ah().setText(R.string._2130839720_res_0x7f0208a8);
                        return;
                    } else {
                        hnu.this.cj.ah().setText(R.string._2130839718_res_0x7f0208a6);
                        return;
                    }
                }
                ReleaseLogUtil.c("Track_TrackMainViewHolder", "showWeakSignal mTrackMainViewHolderBean is null");
            }
        });
    }

    private void cr() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.23
            @Override // java.lang.Runnable
            public void run() {
                if (hnu.this.cj != null) {
                    hnu.this.cj.bnv_().setVisibility(8);
                    hnu.this.cj.ai().setVisibility(8);
                    hnu.this.cj.af().setText(R.string._2130839830_res_0x7f020916);
                    hnu.this.cj.ai().setText(R.string._2130839722_res_0x7f0208aa);
                    hnu.this.cj.ah().setVisibility(0);
                    if (hnu.this.ak && hnu.this.ao) {
                        hnu.this.cj.ah().setText(R.string._2130839719_res_0x7f0208a7);
                        return;
                    } else {
                        hnu.this.cj.ah().setText(R.string._2130839717_res_0x7f0208a5);
                        return;
                    }
                }
                ReleaseLogUtil.c("Track_TrackMainViewHolder", "showSearching mTrackMainViewHolderBean is null");
            }
        });
    }

    private void cj() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.22
            @Override // java.lang.Runnable
            public void run() {
                if (hnu.this.cj != null) {
                    hnu.this.cj.ah().setVisibility(8);
                    hnu.this.cj.bnv_().setVisibility(8);
                    hnu.this.cj.ai().setVisibility(0);
                    hnu.this.cj.ai().setText(R.string._2130839721_res_0x7f0208a9);
                    hnu.this.cj.af().setText(R.string._2130839793_res_0x7f0208f1);
                    return;
                }
                ReleaseLogUtil.c("Track_TrackMainViewHolder", "remindUserOpenGps mTrackMainViewHolderBean is null");
            }
        });
    }

    private void bc() {
        HandlerExecutor.e(new Runnable() { // from class: hoe
            @Override // java.lang.Runnable
            public final void run() {
                hnu.this.s();
            }
        });
    }

    /* synthetic */ void s() {
        hog hogVar = this.cj;
        if (hogVar == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "remindUserOpenGps mTrackMainViewHolderBean is null");
            return;
        }
        hogVar.bnu_().setVisibility(4);
        this.cj.ah().setVisibility(8);
        this.cj.bnv_().setVisibility(8);
        this.cj.ai().setVisibility(8);
        this.cj.af().setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(final boolean z) {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.25
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    hnu.this.cj.ah().setVisibility(0);
                    hnu.this.cj.ah().setText(R.string._2130842489_res_0x7f021379);
                } else {
                    hnu.this.cj.ah().setVisibility(8);
                }
            }
        });
    }

    private void ci() {
        boolean a2 = nrt.a(this.h);
        ImageButton bnt_ = this.cj.bnt_();
        if (bu()) {
            if (bt() || a2) {
                bnt_.setBackground(nrz.cKl_(this.h, R.drawable._2131430540_res_0x7f0b0c8c));
                return;
            } else {
                bnt_.setBackground(nrz.cKl_(this.h, R.drawable._2131430539_res_0x7f0b0c8b));
                return;
            }
        }
        if (bt() || a2) {
            bnt_.setBackground(nrz.cKl_(this.h, R.drawable._2131431854_res_0x7f0b11ae));
        } else {
            bnt_.setBackground(nrz.cKl_(this.h, R.drawable._2131431853_res_0x7f0b11ad));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
    
        if (r0 > 13.0f) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(int r5) {
        /*
            r4 = this;
            android.os.Handler r0 = r4.bc
            r1 = 1
            if (r0 == 0) goto L31
            gtx r0 = r4.bt
            if (r0 == 0) goto L31
            int r0 = r0.m()
            if (r0 != r1) goto L31
            gtx r0 = r4.bt
            float r0 = r0.y()
            r1 = 1077936128(0x40400000, float:3.0)
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 >= 0) goto L1d
        L1b:
            r0 = r1
            goto L24
        L1d:
            r1 = 1095761920(0x41500000, float:13.0)
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 <= 0) goto L24
            goto L1b
        L24:
            r1 = -1038090240(0xffffffffc2200000, float:-40.0)
            float r0 = r0 * r1
            int r0 = (int) r0
            android.os.Handler r1 = r4.bc
            int r0 = r0 + 720
            long r2 = (long) r0
            r1.sendEmptyMessageDelayed(r5, r2)
            goto L40
        L31:
            android.os.Handler r5 = r4.bc
            if (r5 == 0) goto L40
            r0 = 0
            r5.removeMessages(r0)
            android.os.Handler r5 = r4.bc
            r5.removeMessages(r1)
            r4.an = r0
        L40:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hnu.f(int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void de() {
        this.cg.startSport();
        this.ar = true;
    }

    private void ce() {
        if (!this.ar) {
            LogUtil.h("Track_TrackMainViewHolder", "the sport have not started,pls wait...");
            return;
        }
        gso.e().c(0);
        e();
        this.cg.g();
    }

    public void b() {
        TrackMainMapActivity trackMainMapActivity = this.cg;
        if (CommonUtil.h(trackMainMapActivity, trackMainMapActivity.getClass().getName())) {
            e();
        } else {
            cp();
        }
    }

    public void d() {
        TrackMainMapActivity trackMainMapActivity = this.cg;
        if (CommonUtil.h(trackMainMapActivity, trackMainMapActivity.getClass().getName())) {
            aa();
        } else {
            this.as = true;
            at();
        }
    }

    public void e() {
        cn();
        if (!bt() || this.h == null) {
            return;
        }
        this.cg.runOnUiThread(new Runnable() { // from class: hnz
            @Override // java.lang.Runnable
            public final void run() {
                hnu.this.r();
            }
        });
    }

    /* synthetic */ void r() {
        HealthTextView healthTextView = this.z;
        if (healthTextView != null) {
            healthTextView.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void db() {
        this.cg.m();
    }

    public void aa() {
        gso.e().c(0);
        aw();
        if (!bu()) {
            this.cg.o();
        }
        if (!bt() || this.h == null) {
            return;
        }
        this.cg.runOnUiThread(new Runnable() { // from class: hod
            @Override // java.lang.Runnable
            public final void run() {
                hnu.this.y();
            }
        });
    }

    /* synthetic */ void y() {
        HealthTextView healthTextView = this.z;
        if (healthTextView != null) {
            healthTextView.setVisibility(0);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportDataFragmentListener
    public void updateSportViewFragment(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (!this.an && this.bc != null && bt()) {
            this.an = true;
            this.bc.sendEmptyMessage(0);
        }
        String string = bundle.getString("extroInfo");
        final String str = (string == null || string.isEmpty()) ? "" : string;
        final String string2 = bundle.getString("planProgress", "");
        this.br = hnf.blN_(bundle);
        bmR_(bundle);
        final SparseArray<hoj> bmz_ = bmz_(bundle, this.bn);
        hog hogVar = this.cj;
        if (hogVar == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "updateSportViewFragment mTrackMainViewHolderBean is null");
        } else {
            final SparseArray<hok> bno_ = hogVar.bno_();
            this.cg.runOnUiThread(new Runnable() { // from class: hns
                @Override // java.lang.Runnable
                public final void run() {
                    hnu.this.bna_(bmz_, bno_, bundle, str, string2);
                }
            });
        }
    }

    /* synthetic */ void bna_(SparseArray sparseArray, SparseArray sparseArray2, Bundle bundle, String str, String str2) {
        this.cj.bnA_(bmA_(sparseArray), sparseArray2);
        this.ax = sparseArray;
        int i = 0;
        int i2 = bundle.getInt("progreeRate", 0);
        if (i2 >= 0) {
            i = 100;
            if (i2 <= 100) {
                i = i2;
            }
        }
        bmJ_(i, bundle, str, str2);
    }

    private SparseArray<hoj> bmA_(SparseArray<hoj> sparseArray) {
        SparseArray<hoj> sparseArray2 = new SparseArray<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            hoj hojVar = sparseArray.get(keyAt);
            if (hojVar != null) {
                if (i >= this.ax.size()) {
                    sparseArray2.put(keyAt, hojVar);
                } else if (this.ax.keyAt(i) != keyAt || !hojVar.equals(this.ax.get(keyAt))) {
                    sparseArray2.put(keyAt, hojVar);
                }
            }
        }
        return sparseArray2;
    }

    private void bmR_(Bundle bundle) {
        int i = bundle.getInt("sport_target_type_sportting", -1);
        if (i == -1) {
            return;
        }
        LogUtil.a("Track_TrackMainViewHolder", "showTargetType mSportTarget:", Integer.valueOf(i));
        if (i == 0) {
            this.bn.put(0, TrackMainFragmentShowType.TOTAL_TIME);
            return;
        }
        if (i == 1) {
            this.bn.put(0, TrackMainFragmentShowType.TOTAL_DISTANCES);
            return;
        }
        if (i == 2) {
            this.bn.put(0, TrackMainFragmentShowType.CALORIE);
            return;
        }
        if (i == 4 || i == 5) {
            this.bn.put(0, TrackMainFragmentShowType.HEART_RATE);
        } else if (i == 255) {
            this.bn.put(0, TrackMainFragmentShowType.TOTAL_TIME);
        } else {
            LogUtil.b("Track_TrackMainViewHolder", "showTargetType other sportTarget");
        }
    }

    private void bmJ_(int i, Bundle bundle, String str, String str2) {
        if (bundle == null) {
            ReleaseLogUtil.c("Track_TrackMainViewHolder", "sport data is null");
            return;
        }
        String bmB_ = bmB_(bundle);
        if (!bt()) {
            this.cj.t().setText(bmB_);
            this.cj.q().setText(bundle.getString("duration", "--"));
        }
        if (this.aj && this.cj.b() != null) {
            bmP_(bundle);
        }
        if (this.ap && this.cj.c() != null) {
            this.cj.c().setText("3D:" + bundle.getString("climb_3d_dis", "--") + ", 2D:" + bundle.getString("climb_2d_dis", "--") + ", 海拔:" + bundle.getString("climb_3d_pressure", "--"));
        }
        bmL_(bundle);
        c(i, str, str2);
        bmO_(i, bundle, str);
        if (!bu() && this.cj.bnw_() != null && this.bt.ar()) {
            if (bundle.getBoolean("planIsLastTarget")) {
                this.cj.bnw_().setVisibility(8);
            } else {
                this.cj.bnw_().setVisibility(0);
            }
        }
        if (this.t != null) {
            bmN_(bundle);
        }
    }

    private void bmO_(int i, Bundle bundle, String str) {
        int i2 = bundle.getInt("extroLocationState", -1);
        if (i2 != -1) {
            if (this.cj.bnl_() != null && this.cj.bnl_().getVisibility() != 0) {
                this.cj.bnl_().setVisibility(0);
                ah();
                ai();
            }
            int i3 = i2 == 1 ? R.color._2131296651_res_0x7f09018b : R.color._2131299241_res_0x7f090ba9;
            this.cj.ag().setText(str);
            this.cj.ag().setTextColor(nsf.c(i3));
            this.cj.ab().setProgress(i);
        }
    }

    private void bmN_(Bundle bundle) {
        int i = bundle.getInt("ihealth_free_indoor_running_style", 0);
        if (i == 1 && this.t.getVisibility() == 4) {
            this.t.setVisibility(0);
        } else if (i == 2 && this.t.getVisibility() == 0) {
            this.t.setVisibility(4);
        } else {
            LogUtil.c("Track_TrackMainViewHolder", "not a special style");
        }
    }

    private void c(int i, String str, String str2) {
        if (gtx.c(this.h).au()) {
            this.cj.ag().setText(str);
            this.cj.ab().setProgress(i);
        } else if (by()) {
            this.cj.ag().setText(this.bi);
            this.cj.ab().setProgress(i);
        }
        if (str2 != null && !str2.isEmpty()) {
            this.cj.an().setVisibility(0);
            this.cj.an().setText(str2);
        } else {
            this.cj.an().setVisibility(8);
        }
    }

    private void bmP_(Bundle bundle) {
        this.cj.b().setText(bundle.getString("indoor_run_step", "--"));
        this.cj.a().setText(bundle.getString("indoor_run_distance", "--"));
        this.cj.d().setText(bundle.getString("indoor_run_normal_distance", "--"));
        this.cj.e().setText(bundle.getString("indoor_running_number_data", "--"));
    }

    private void bmL_(Bundle bundle) {
        String b2;
        if (this.cj.aa() == null || this.cj.bnx_() == null) {
            return;
        }
        int i = bundle.getInt("IntensityType");
        if (i == -1) {
            this.cj.aa().setVisibility(8);
            this.cj.bnx_().setVisibility(8);
            return;
        }
        if (i == 15) {
            b2 = fhq.b(this.h, i, bundle.getDouble("IntensityLow"), bundle.getDouble("IntensityHeight"));
        } else {
            b2 = fhq.b(this.h, i, (int) bundle.getDouble("IntensityLow"), (int) bundle.getDouble("IntensityHeight"));
        }
        if (TextUtils.isEmpty(b2)) {
            this.cj.aa().setVisibility(8);
            this.cj.bnx_().setVisibility(8);
        } else {
            this.cj.aa().setText(b2);
            this.cj.aa().setVisibility(0);
            this.cj.bnx_().setVisibility(0);
        }
    }

    private boolean by() {
        String str = this.bi;
        return (str == null || str.equals("")) ? false : true;
    }

    private SparseArray<hoj> bmz_(Bundle bundle, SparseArray<TrackMainFragmentShowType> sparseArray) {
        SparseArray<hoj> sparseArray2 = new SparseArray<>(16);
        if (bundle == null) {
            LogUtil.b("Track_TrackMainViewHolder", "sportData is null");
            return sparseArray2;
        }
        if (sparseArray == null) {
            return sparseArray2;
        }
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            sparseArray2.put(i, hnf.blM_(sparseArray.get(i), bundle));
        }
        return sparseArray2;
    }

    private String bmB_(Bundle bundle) {
        if (bundle == null || bundle.getString("distance") == null) {
            return UnitUtil.e(0.0d, 1, 0);
        }
        try {
            return UnitUtil.e(Double.parseDouble(bundle.getString("distance")), 1, 2);
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_TrackMainViewHolder", LogAnonymous.b((Throwable) e2));
            return "";
        }
    }

    private hoj e(String str) {
        return new hoj(this.bk.getString(R.string._2130841430_res_0x7f020f56), str, this.bk.getString(R.string.IDS_main_watch_heart_rate_unit_string));
    }

    private hoj c(String str) {
        return new hoj(this.bk.getString(R.string._2130850263_res_0x7f0231d7), str, this.bk.getString(R.string._2130839711_res_0x7f02089f));
    }

    public void e(boolean z) {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.b(z);
        }
    }

    public void c(MotionData motionData) {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.d(motionData);
        }
    }

    public InterfaceHiMap f() {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            return hnqVar.b();
        }
        return null;
    }

    public boolean p() {
        if (bu()) {
            return ((SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class)).getStatus() == 2;
        }
        gtx gtxVar = this.bt;
        return gtxVar != null && gtxVar.m() == 2;
    }

    public boolean t() {
        if (bu()) {
            return ((SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class)).getStatus() == 1;
        }
        gtx gtxVar = this.bt;
        return gtxVar != null && gtxVar.m() == 1;
    }

    static class e extends Handler {
        private WeakReference<hnu> e;

        e(hnu hnuVar) {
            this.e = null;
            this.e = new WeakReference<>(hnuVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.e == null || message == null) {
                return;
            }
            super.handleMessage(message);
            hnu hnuVar = this.e.get();
            if (hnuVar == null) {
                return;
            }
            if (message.what != 101) {
                hnuVar.c(message.what, message.arg1);
                return;
            }
            hnuVar.cf = AnimatorInflater.loadAnimator(hnuVar.h, R.animator._2131034183_res_0x7f050047);
            hnuVar.cf.setTarget(hnuVar.cj.aj());
            hnuVar.cf.addListener(hnuVar.cd);
            hnuVar.cf.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, int i2) {
        hok hokVar;
        int i3 = i2 - 4;
        if (this.aq) {
            this.bn.put(i2, TrackMainFragmentShowType.values()[i]);
        } else {
            this.ac.put(i3, TrackMainFragmentShowType.values()[i]);
        }
        if (this.aq) {
            hokVar = this.cj.bno_().get(i2);
        } else {
            hokVar = this.cj.bnp_().get(i3);
        }
        hoj e2 = e(TrackMainFragmentShowType.values()[i]);
        if (e2 == null) {
            return;
        }
        e(hokVar, e2);
    }

    private hoj e(TrackMainFragmentShowType trackMainFragmentShowType) {
        hoj hojVar;
        if (trackMainFragmentShowType == TrackMainFragmentShowType.TOTAL_DISTANCES) {
            return ar();
        }
        if (trackMainFragmentShowType == TrackMainFragmentShowType.TOTAL_TIME) {
            hojVar = new hoj(this.bk.getString(R.string._2130843686_res_0x7f021826), "", "");
        } else if (trackMainFragmentShowType == TrackMainFragmentShowType.SPEED) {
            hojVar = new hoj(UnitUtil.h() ? this.bk.getString(R.string._2130839825_res_0x7f020911) : this.bk.getString(R.string._2130839826_res_0x7f020912), "", "");
        } else {
            if (trackMainFragmentShowType == TrackMainFragmentShowType.CALORIE) {
                return al();
            }
            if (trackMainFragmentShowType == TrackMainFragmentShowType.HEART_RATE) {
                return e("");
            }
            if (trackMainFragmentShowType == TrackMainFragmentShowType.STEP_RATE) {
                return aq();
            }
            if (trackMainFragmentShowType == TrackMainFragmentShowType.PACE) {
                hojVar = new hoj(this.bk.getString(R.string._2130844083_res_0x7f0219b3), "", "");
            } else {
                if (trackMainFragmentShowType == TrackMainFragmentShowType.COUNTDOWN_DISTANCES) {
                    return ak();
                }
                if (trackMainFragmentShowType == TrackMainFragmentShowType.COUNTDOWN_TIME) {
                    hojVar = new hoj(this.bk.getString(R.string._2130850265_res_0x7f0231d9), "", "");
                } else {
                    if (trackMainFragmentShowType == TrackMainFragmentShowType.COUNTDOWN_CALORIE) {
                        return an();
                    }
                    LogUtil.c("Track_TrackMainViewHolder", "TrackShowItemValue is not in enumeration");
                    return null;
                }
            }
        }
        return hojVar;
    }

    private hoj al() {
        return new hoj(this.bk.getString(R.string._2130847439_res_0x7f0226cf), "", this.bk.getString(R.string._2130839711_res_0x7f02089f));
    }

    private hoj aq() {
        return new hoj(this.bk.getString(R.string._2130844075_res_0x7f0219ab), "", this.bk.getString(R.string._2130844082_res_0x7f0219b2));
    }

    private hoj an() {
        return c("");
    }

    private hoj ak() {
        return new hoj(this.bk.getString(R.string._2130850264_res_0x7f0231d8), "", this.bk.getString(R.string._2130844082_res_0x7f0219b2));
    }

    private hoj ar() {
        return new hoj(this.bk.getString(R.string._2130839729_res_0x7f0208b1), "", this.bk.getString(R.string._2130844082_res_0x7f0219b2));
    }

    private void e(final hok hokVar, final hoj hojVar) {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.27
            @Override // java.lang.Runnable
            public void run() {
                hokVar.d(hojVar);
            }
        });
    }

    public void c(int i) {
        LogUtil.a("Track_TrackMainViewHolder", "performUpdateStatus, to pause/resume sport ", Integer.valueOf(i));
        if (i == 1) {
            this.as = true;
            at();
            this.cg.o();
        } else if (i == 2) {
            cp();
            this.cg.g();
        } else if (i == 3) {
            this.cg.k();
        } else {
            LogUtil.h("Track_TrackMainViewHolder", "Wrong command");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bmM_(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        marginLayoutParams.setMargins(i, i2, marginLayoutParams.width + i, i2);
        view.setLayoutParams(new RelativeLayout.LayoutParams(marginLayoutParams));
    }

    private void cp() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.30
            @Override // java.lang.Runnable
            public void run() {
                int left;
                int i;
                if (hnu.this.cj != null) {
                    int c2 = nsn.c(hnu.this.h, 20.0f);
                    ImageView bns_ = hnu.this.cj.bns_();
                    ImageView bnr_ = hnu.this.cj.bnr_();
                    if (LanguageUtil.bc(hnu.this.h)) {
                        left = ((hnu.this.cj.bnq_().getLeft() - hnu.this.cj.bnt_().getRight()) - (hnu.this.cj.bnr_().getWidth() * 2)) + c2;
                        i = 1;
                    } else {
                        left = ((hnu.this.cj.bnt_().getLeft() - hnu.this.cj.bnq_().getRight()) - (hnu.this.cj.bnr_().getWidth() * 2)) + c2;
                        i = -1;
                    }
                    bnr_.setVisibility(8);
                    bns_.setVisibility(0);
                    CircleProgressButton al = hnu.this.cj.al();
                    al.setVisibility(0);
                    int width = (left / 6) + (bnr_.getWidth() / 2);
                    hnu.this.cj.aj().setTranslationX((-i) * width);
                    int top = bnr_.getTop();
                    int left2 = (hnu.this.cj.bnr_().getLeft() + hnu.this.cj.bnr_().getRight()) / 2;
                    hnu.bmM_(al, (left2 - (bnr_.getWidth() / 2)) + width, top);
                    hnu.bmM_(bns_, (left2 - (bnr_.getWidth() / 2)) - width, top);
                    al.a(false);
                    bns_.invalidate();
                    al.invalidate();
                    hnu.this.b(left2, 2);
                    return;
                }
                LogUtil.h("Track_TrackMainViewHolder", "showBtnPlayAndStopWhenLock mTrackMainViewHolderBean is null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        int width;
        int top = this.cj.bnq_().getTop();
        int visibility = this.cj.bnt_().getVisibility();
        if (bt()) {
            width = this.av.getWidth();
        } else {
            width = this.cj.bnk_().getWidth();
        }
        d(i, width, i2 == 1 ? nsn.c(this.h, 20.0f) : 0, visibility, top);
        this.cj.bnq_().invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void at() {
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.29
            @Override // java.lang.Runnable
            public void run() {
                ImageView bns_ = hnu.this.cj.bns_();
                CircleProgressButton al = hnu.this.cj.al();
                ImageView bnr_ = hnu.this.cj.bnr_();
                if (bns_ != null && al != null && bnr_ != null) {
                    int left = (hnu.this.cj.bnr_().getLeft() + hnu.this.cj.bnr_().getRight()) / 2;
                    int top = bnr_.getTop();
                    hnu.bmM_(bns_, left - (bnr_.getWidth() / 2), top);
                    hnu.bmM_(al, left - (bnr_.getWidth() / 2), top);
                    hnu.bmM_(bnr_, left - (bnr_.getWidth() / 2), top);
                    bnr_.setVisibility(0);
                    bns_.setVisibility(8);
                    al.setVisibility(8);
                    al.a(true);
                    hnu.this.df();
                    hnu.this.cj.aj().setVisibility(4);
                    bns_.invalidate();
                    al.invalidate();
                    bnr_.invalidate();
                    hnu.this.b(left, 1);
                    return;
                }
                ReleaseLogUtil.c("Track_TrackMainViewHolder", "button is invalid");
            }
        });
    }

    private void d(int i, int i2, int i3, int i4, int i5) {
        if (LanguageUtil.bc(this.h)) {
            bmM_(this.cj.bnq_(), ((i - (i2 / 2)) - this.cj.bnq_().getWidth()) + i3, i5);
            if (i4 == 0) {
                bmM_(this.cj.bnt_(), (i2 - (this.cj.bnt_().getWidth() * 2)) - i3, i5);
                this.cj.bnt_().invalidate();
                return;
            }
            return;
        }
        int i6 = i2 / 2;
        bmM_(this.cj.bnq_(), (i - i6) + i3, i5);
        if (i4 == 0) {
            bmM_(this.cj.bnt_(), ((i + i6) - this.cj.bnt_().getWidth()) - i3, i5);
            this.cj.bnt_().invalidate();
        }
    }

    private void d(final HealthTextView healthTextView) {
        int i = this.bu;
        if (i == 0 || i == 2) {
            return;
        }
        this.cg.runOnUiThread(new Runnable() { // from class: hnu.26
            @Override // java.lang.Runnable
            public void run() {
                healthTextView.setText(R.string._2130844081_res_0x7f0219b1);
            }
        });
    }

    public void a(boolean z) {
        this.ak = z;
    }

    private void br() {
        int i = this.ca;
        this.cb = (i >> 24) & 255;
        this.by = (i >> 16) & 255;
        this.bz = (i >> 8) & 255;
        this.bx = i & 255;
        int i2 = this.r;
        this.m = (i2 >> 24) & 255;
        this.s = (i2 >> 16) & 255;
        this.q = (i2 >> 8) & 255;
        this.p = i2 & 255;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(float f) {
        return a(f) | ((this.cb + ((int) ((this.m - r0) * f))) << 24);
    }

    private int a(float f) {
        return ((this.by + ((int) ((this.s - r0) * f))) << 16) | ((this.bz + ((int) ((this.q - r2) * f))) << 8) | (this.bx + ((int) (f * (this.p - r4))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void df() {
        this.bb.removeMessages(101);
        Animator animator = this.cf;
        if (animator != null) {
            if (animator.isRunning()) {
                this.cf.cancel();
            }
            this.cf = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void da() {
        df();
        hog hogVar = this.cj;
        if (hogVar == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "showStopContent mTrackMainViewHolderBean is null");
            return;
        }
        HealthTextView aj = hogVar.aj();
        if (aj == null) {
            ReleaseLogUtil.d("Track_TrackMainViewHolder", "showStopContent textView is null");
            return;
        }
        aj.setAlpha(1.0f);
        aj.setVisibility(0);
        jcf.bEk_(aj, nsf.j(R.string._2130842133_res_0x7f021215));
    }

    public void g() {
        gtx gtxVar = this.bt;
        if (gtxVar != null) {
            gtxVar.cc();
        }
        if (SportSupportUtil.f(this.bs) && koq.c(this.c)) {
            gsy.b().e("Track_TrackMainViewHolder");
        }
        ap();
    }

    private boolean av() {
        if (this.bs == 264) {
            return false;
        }
        return "true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "dev_3ddis_track"));
    }

    public void c(hjd hjdVar, hjd hjdVar2) {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.drawPauseLine(hjdVar, hjdVar2);
        }
    }

    public void z() {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.releaseMap();
        }
    }

    public void x() {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.pauseSportClear();
        }
    }

    public void j() {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.forceDrawLineToMap();
        }
    }

    public void b(List<hiy> list) {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.drawLineToMap(list);
        }
    }

    public void a() {
        if (nsn.ag(this.h)) {
            this.o.setNumColumns(5);
        } else {
            this.o.setNumColumns(3);
        }
        if (this.o.getVisibility() == 0) {
            this.f13277a.b();
        }
    }

    public boolean m() {
        if (this.o.getVisibility() != 0 || !this.f13277a.a()) {
            return false;
        }
        this.bc.sendMessage(this.bc.obtainMessage(3));
        return true;
    }

    private void ay() {
        if (!bt()) {
            this.cj.p().setAlpha(0.95f);
        }
        this.o = this.cj.bng_();
        List<TrackGridViewAdapter.b> valueHolderList = hnb.a().getValueHolderList(this.bs, this.bv);
        TrackGridViewAdapter trackGridViewAdapter = new TrackGridViewAdapter(this.h, valueHolderList, bt());
        this.o.setAdapter((ListAdapter) trackGridViewAdapter);
        if (nsn.ag(this.h)) {
            this.o.setNumColumns(5);
        } else {
            this.o.setNumColumns(3);
        }
        b(valueHolderList, trackGridViewAdapter);
        bg();
    }

    private void bg() {
        ImageView imageView = (ImageView) this.bj.findViewById(R.id.img_arrows);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            nsy.cMm_(imageView, nrz.cKn_(BaseApplication.getContext(), bt() ? R.drawable._2131427555_res_0x7f0b00e3 : R.drawable._2131427843_res_0x7f0b0203));
        }
    }

    private void b(final List<TrackGridViewAdapter.b> list, final TrackGridViewAdapter trackGridViewAdapter) {
        this.o.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainViewHolder$$ExternalSyntheticLambda4
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                hnu.this.bmW_(list, trackGridViewAdapter, adapterView, view, i, j);
            }
        });
    }

    public /* synthetic */ void bmW_(List list, TrackGridViewAdapter trackGridViewAdapter, AdapterView adapterView, View view, int i, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.at < ProfileExtendConstants.TIME_OUT || !this.f13277a.e()) {
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        this.f13277a.e(false);
        this.f13277a.b(false);
        this.at = currentTimeMillis;
        if (koq.b(list, i)) {
            LogUtil.h("Track_TrackMainViewHolder", "setDataPanelGridViewListener position is out of valueHolderList.");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        TrackGridViewAdapter.b bVar = (TrackGridViewAdapter.b) list.get(i);
        trackGridViewAdapter.a();
        bVar.d(true);
        trackGridViewAdapter.notifyDataSetChanged();
        SparseArray<hok> bno_ = this.cj.bno_();
        TrackMainFragmentShowType d = bVar.d();
        this.f13277a.c(this.br.get(d), bno_.get(7));
        this.bn.put(7, d);
        bno_.get(this.bq).d(this.br.get(d));
        this.bn.put(this.bq, d);
        hnb.a().saveConfigToLocal(this.bn, this.bs, this.bu, this.bv);
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    private void a(TrackMainFragmentShowType trackMainFragmentShowType) {
        TrackGridViewAdapter trackGridViewAdapter = (TrackGridViewAdapter) this.o.getAdapter();
        TrackGridViewAdapter.b c2 = trackGridViewAdapter.c(trackMainFragmentShowType);
        trackGridViewAdapter.a();
        if (c2 != null) {
            c2.d(true);
            trackGridViewAdapter.notifyDataSetChanged();
            this.cj.bno_().get(7).d(this.br.get(trackMainFragmentShowType));
            this.bn.put(7, trackMainFragmentShowType);
        }
    }

    private void cl() {
        SparseArray<hok> bno_ = this.cj.bno_();
        for (final int i = 0; i < bno_.size(); i++) {
            final hok hokVar = bno_.get(i);
            if ((this.bu == -1 || i != 0) && i != 7) {
                hokVar.bnC_().setOnClickListener(new View.OnClickListener() { // from class: hoc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        hnu.this.bmV_(i, hokVar, view);
                    }
                });
            }
        }
    }

    /* synthetic */ void bmV_(int i, hok hokVar, View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.aw < ProfileExtendConstants.TIME_OUT || !this.f13277a.c()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.f13277a.b(false);
        this.aw = currentTimeMillis;
        this.bq = i;
        c(i, hokVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(int i, hok hokVar) {
        this.f13277a.e(false);
        SparseArray<TrackMainFragmentShowType> sparseArray = this.bn;
        if (sparseArray != null) {
            a(sparseArray.get(i));
        }
        this.f13277a.a(false);
        this.f13277a.b(4);
        this.f13277a.e(hokVar);
        a(4);
        if (this.cj.bny_() != null) {
            this.cj.bny_().setOnTouchListener(new View.OnTouchListener() { // from class: hob
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return hnu.bmC_(view, motionEvent);
                }
            });
        }
    }

    private void bi() {
        this.f13277a.i();
    }

    private void bm() {
        ShowDataPanelLayout p = this.cj.p();
        if (p != null) {
            p.setMainViewHolder(this);
        }
    }

    private boolean bs() {
        if (bu() || this.bt.aq()) {
            return true;
        }
        return UnitUtil.h() && !bw();
    }

    public void a(int i) {
        View findViewById = this.bj.findViewById(bt() ? R.id.music_and_indoor_running_icon : R.id.gps_content_and_music_control);
        if (findViewById != null) {
            findViewById.setVisibility(i);
        }
    }

    public void ae() {
        boolean a2 = SharedPreferenceManager.a(Integer.toString(20002), "first_time_enter_run_beat", true);
        this.al = a2;
        if (a2 && bw()) {
            View inflate = View.inflate(this.h, R.layout.layout_beat_bubble, null);
            if (inflate == null || this.cg.isFinishing()) {
                LogUtil.a("Track_TrackMainViewHolder", "beatBubbleLayout is null or Activity is finish", Boolean.valueOf(this.cg.isFinishing()));
                return;
            }
            PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, false);
            this.bm = popupWindow;
            popupWindow.setOutsideTouchable(true);
            inflate.measure(0, 0);
            this.bm.showAsDropDown(this.cj.bnt_(), LanguageUtil.bc(this.h) ? -nsn.c(this.h, 20.0f) : (-inflate.getMeasuredWidth()) + nsn.c(this.h, 70.0f), -nsn.c(this.h, 108.0f));
            this.bm.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainViewHolder$$ExternalSyntheticLambda13
                @Override // android.widget.PopupWindow.OnDismissListener
                public final void onDismiss() {
                    hnu.this.o();
                }
            });
            inflate.setOnClickListener(new View.OnClickListener() { // from class: hny
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    hnu.this.bmX_(view);
                }
            });
        }
    }

    /* synthetic */ void bmX_(View view) {
        o();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean bw() {
        return !bu() && gvv.a(this.bs, this.bt.at()) && hpg.c();
    }

    public void o() {
        if (this.al && bw()) {
            ap();
            SharedPreferenceManager.e(Integer.toString(20002), "first_time_enter_run_beat", false);
            this.al = false;
        }
    }

    private void ap() {
        PopupWindow popupWindow = this.bm;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.bm.dismiss();
    }

    public hnq l() {
        return this.ce;
    }

    public void b(enc encVar) {
        hnq hnqVar = this.ce;
        if (hnqVar != null) {
            hnqVar.e(encVar);
        }
    }
}
