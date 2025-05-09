package com.huawei.health.suggestion.ui.fitness.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.SportServiceApi;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.sportservice.SportBleStatus;
import com.huawei.health.suggestion.h5pro.AiSportVoiceHelper;
import com.huawei.health.suggestion.h5pro.CourseControlManager;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.health.suggestion.ui.fitness.activity.TrainDetail;
import com.huawei.health.suggestion.ui.fitness.callback.JumpFinishCallback;
import com.huawei.health.suggestion.ui.fitness.helper.IntPlanPreSportRemind;
import com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.module.TrainActionIntro;
import com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider;
import com.huawei.health.suggestion.ui.fitness.mvp.NewViewDetailInfo;
import com.huawei.health.suggestion.ui.fitness.mvp.ScreenController;
import com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract;
import com.huawei.health.suggestion.ui.fitness.mvp.ViewVideoPlayListener;
import com.huawei.health.suggestion.ui.fitness.mvp.VipViewShowProvider;
import com.huawei.health.suggestion.ui.fitness.viewholder.CourseDetailViewHolder;
import com.huawei.health.suggestion.ui.run.activity.fragment.CustomCourseDetailFragment;
import com.huawei.health.suggestion.ui.view.AutoFillColorView;
import com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.BelongInfo;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LayoutTemplateInfo;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.pluginfitnessadvice.RecommendationBar;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.trade.datatype.TradeViewInfo;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.downloadwidget.HealthDownLoadWidget;
import com.huawei.ui.commonui.errortip.NetworkErrorTipBar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;
import defpackage.arx;
import defpackage.asc;
import defpackage.ase;
import defpackage.ash;
import defpackage.fey;
import defpackage.ffl;
import defpackage.ffy;
import defpackage.fib;
import defpackage.fis;
import defpackage.fmq;
import defpackage.fni;
import defpackage.fnm;
import defpackage.fnq;
import defpackage.fny;
import defpackage.fot;
import defpackage.fpl;
import defpackage.fpq;
import defpackage.fpy;
import defpackage.fqy;
import defpackage.frb;
import defpackage.frp;
import defpackage.frq;
import defpackage.frt;
import defpackage.ftd;
import defpackage.fyc;
import defpackage.gge;
import defpackage.ggg;
import defpackage.ggr;
import defpackage.ggs;
import defpackage.ggx;
import defpackage.ghd;
import defpackage.gij;
import defpackage.gim;
import defpackage.gnm;
import defpackage.jdl;
import defpackage.jdx;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mmz;
import defpackage.mod;
import defpackage.mon;
import defpackage.mwt;
import defpackage.njn;
import defpackage.nqt;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.smy;
import defpackage.squ;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class TrainDetail extends BaseStateActivity implements View.OnClickListener, TrainDetailContract.Iview, JumpConnectHelper.JumpActivityHandleInterface, CourseEquipmentConnectionTipsUtil.DevicesConnectDialogCallback, ScreenController, VipViewShowProvider, JumpFinishCallback, CourseDetailProvider, SportBleStatus {

    /* renamed from: a, reason: collision with root package name */
    private AutoFillColorView f3100a;
    private FitWorkout aa;
    private nqt ac;
    private int ad;
    private IntPlanPreSportRemind ae;
    private HealthButton af;
    private RelativeLayout ai;
    private boolean ap;
    private boolean ar;
    private boolean at;
    private RelativeLayout au;
    private ImageView aw;
    private HealthTextView ax;
    private int ay;
    private NoTitleCustomAlertDialog az;
    private HealthButton b;
    private String ba;
    private String bc;
    private String bd;
    private ImageView bf;
    private NewViewDetailInfo bg;
    private frt bh;
    private CustomAlertDialog bi;
    private TrainDetailContract.Ipresenter bj;
    private AutoFillColorView bk;
    private PopupWindow bl;
    private FrameLayout bm;
    private frt bn;
    private HealthViewPager bo;
    private ArrayList<WorkoutRecord> bp;
    private WorkoutRecord br;
    private fpl bt;
    private long bu;
    private FrameLayout bv;
    private CustomViewDialog bw;
    private String[] bx;
    private HealthButton by;
    private int bz;
    private LinearLayout c;
    private HealthSubTabWidget ca;
    private RelativeLayout cc;
    private LinearLayout cd;
    private float ce;
    private fqy cf;
    private View cg;
    private fmq ch;
    private NoTitleCustomAlertDialog cj;
    private String ck;
    private WifiReceiver cl;
    private CoachData d;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthButton i;
    private Context j;
    private frq l;
    private frp m;
    private HealthCardView n;
    private CourseApi o;
    private CustomTitleBar p;
    private CourseDetailViewHolder q;
    private int r;
    private CustomViewDialog s;
    private gim t;
    private e u;
    private HealthColumnRelativeLayout v;
    private float w;
    private HealthDownLoadWidget x;
    private int y;
    private NetworkErrorTipBar z;
    private int ab = 1;
    private NetStateChangeReceiver bb = new NetStateChangeReceiver();
    private int cb = 0;
    private boolean av = false;
    private boolean aj = false;
    private boolean ag = false;
    private int be = 0;
    private int bq = 0;
    private boolean am = false;
    private boolean al = true;
    private boolean an = false;
    private Handler ah = new h(Looper.getMainLooper(), this);
    private boolean ao = true;
    private fny k = new fny(this.ah, this);
    private boolean aq = false;
    private boolean as = false;
    private boolean ak = false;
    private fpy e = new fpy();
    private View.OnClickListener bs = new AnonymousClass1();

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public int setLoadingBackgroundColor() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Suggestion_TrainDetail", "onCreate enter");
        nsn.cLf_(this, bundle);
        getWindow().setFlags(16777216, 16777216);
        this.j = this;
        this.k.b();
        if (getIntent() != null) {
            fqy fqyVar = new fqy(getIntent());
            this.cf = fqyVar;
            this.bp = fqyVar.m();
            ReleaseLogUtil.e("Suggestion_TrainDetail", "mTrainDetailIntentData:", this.cf.toString());
        }
        super.onCreate(bundle);
        clearBackgroundDrawable();
        if (z()) {
            return;
        }
        this.t = gim.c(IntPlan.PlanType.NA_PLAN.getType());
        this.bj = new frb(this, this.cf);
        this.bu = System.currentTimeMillis();
        this.bz = getWindow().getDecorView().getSystemUiVisibility();
    }

    private boolean z() {
        if (LoginInit.getInstance(BaseApplication.getContext()).getUsetId() != null || LoginInit.getInstance(this.j).isBrowseMode()) {
            return false;
        }
        ReleaseLogUtil.d("Suggestion_TrainDetail", "onCreate enter getAccountInfo() == null");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName(getPackageName(), "com.huawei.health.MainActivity");
        intent.addFlags(268435456);
        intent.putExtra("isFromTrainDetail", true);
        gnm.aPB_(arx.b(), intent);
        finish();
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        frq frqVar = this.l;
        if (frqVar != null) {
            frqVar.a();
        }
        NewViewDetailInfo newViewDetailInfo = this.bg;
        if (newViewDetailInfo != null) {
            newViewDetailInfo.d();
        }
        if (!bg() || this.ch == null) {
            return;
        }
        LogUtil.a("Suggestion_TrainDetail", "onPause mVipPreview backToPreview");
        this.ch.g();
    }

    public Handler azX_() {
        return this.ah;
    }

    public WorkoutRecord c() {
        return this.br;
    }

    public boolean b() {
        return this.k.d();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void addTradeView(View view) {
        LogUtil.a("Suggestion_TrainDetail", "addTradeView.");
        frp frpVar = this.m;
        if (frpVar != null) {
            frpVar.b(aj());
        }
        if (this.cc == null || view == null) {
            return;
        }
        this.x.setClickable(false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.cc.addView(view, layoutParams);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void recycleTradeView(View view) {
        LogUtil.a("Suggestion_TrainDetail", "recycleTradeView.");
        frp frpVar = this.m;
        if (frpVar != null) {
            frpVar.b();
        }
        RelativeLayout relativeLayout = this.cc;
        if (relativeLayout != null && view != null) {
            relativeLayout.removeView(view);
        }
        showDownloadView();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void hideDownloadView() {
        LogUtil.a("Suggestion_TrainDetail", "hideDownloadView.");
        this.x.setClickable(false);
        this.x.setVisibility(8);
        this.v.setVisibility(8);
        this.c.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void showDownloadView() {
        LogUtil.a("Suggestion_TrainDetail", "showDownloadView.");
        frp frpVar = this.m;
        if (frpVar != null) {
            frpVar.b();
        }
        this.x.setClickable(true);
        e(this.y);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void bindRecommendationData(List<FitWorkout> list, ArrayMap<String, String> arrayMap) {
        NewViewDetailInfo newViewDetailInfo = this.bg;
        if (newViewDetailInfo != null) {
            newViewDetailInfo.b(list, arrayMap);
        }
    }

    private String aj() {
        FitWorkout fitWorkout;
        if (this.bj == null || (fitWorkout = this.aa) == null) {
            return "";
        }
        int b2 = fpq.b(fitWorkout);
        if (this.bj.isPayCourses(b2)) {
            return getString(R.string._2130848747_res_0x7f022beb);
        }
        return this.bj.isVipCourses(b2) ? getString(R.string._2130848746_res_0x7f022bea) : "";
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public WeakReference<Activity> acquireActivity() {
        return new WeakReference<>(this);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void setNoticeContent(SpannableString spannableString) {
        CourseDetailViewHolder courseDetailViewHolder = this.q;
        if (courseDetailViewHolder != null) {
            courseDetailViewHolder.aFj_(spannableString);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public String acquireWorkoutName() {
        FitWorkout fitWorkout = this.aa;
        return fitWorkout == null ? "" : fitWorkout.acquireName();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.ScreenController
    public void setScreenOrientation(int i2) {
        setRequestedOrientation(i2);
        if (i2 == 0) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(this.bz);
            initSystemBar();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.callback.JumpFinishCallback
    public void onJumpFinish() {
        if (isFinishing()) {
            return;
        }
        finish();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.callback.JumpFinishCallback
    public void onJumpFail() {
        o();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider
    public boolean isRunModelCourse() {
        return j();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider
    public boolean isLongVideoCourse() {
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout != null) {
            return fitWorkout.isLongVideoCourse();
        }
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider
    public void getLongVideo(int i2, UiCallback<LongVideoInfo> uiCallback) {
        b(false, this.aa.getWorkoutActionProperty(), uiCallback);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider
    public int getCourseActionMediaSize(int i2) {
        if (this.o == null || this.br == null || TextUtils.isEmpty(this.ck)) {
            return 0;
        }
        return this.o.getCourseMediaFileSize(this.ck, this.br.acquireVersion(), i2);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider
    public boolean isCurActionResourceDownload(int i2) {
        CourseApi courseApi = this.o;
        if (courseApi == null) {
            LogUtil.h("Suggestion_TrainDetail", "isDownloaded: mCourseApi is null.");
            return false;
        }
        if (this.av) {
            return true;
        }
        return courseApi.isCourseMediaFileDownloaded(this.ck, this.br.acquireVersion(), i2);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider
    public void downloadCurActionResource(int i2, UiCallback uiCallback) {
        if (this.o == null || this.br == null || TextUtils.isEmpty(this.ck)) {
            return;
        }
        this.o.downloadCourseMediaFileByPosition(this.ck, this.br.acquireVersion(), i2, uiCallback);
    }

    /* loaded from: classes4.dex */
    static class h extends BaseHandler<TrainDetail> {
        public h(Looper looper, TrainDetail trainDetail) {
            super(looper, trainDetail);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aAq_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TrainDetail trainDetail, Message message) {
            if (message == null) {
                ReleaseLogUtil.d("Suggestion_TrainDetail", "handleMessage message is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                if (message.arg1 == 0) {
                    trainDetail.p.setRightTextButtonVisibility(8);
                    trainDetail.p.setPaddingRelative(0, 0, (int) trainDetail.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d), 0);
                    return;
                } else if (trainDetail.isRunModelCourse() && trainDetail.b()) {
                    trainDetail.p.setRightTextButtonVisibility(0);
                    trainDetail.p.setPaddingRelative(0, 0, 0, 0);
                    trainDetail.ac();
                    return;
                } else {
                    trainDetail.p.setPaddingRelative(0, 0, (int) trainDetail.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d), 0);
                    trainDetail.p.setRightTextButtonVisibility(8);
                    return;
                }
            }
            if (i == 1) {
                trainDetail.c(R.string._2130848490_res_0x7f022aea, 0);
                return;
            }
            if (i == 4) {
                trainDetail.c(R.string._2130848584_res_0x7f022b48, 0);
                return;
            }
            if (i == 102) {
                trainDetail.an();
            } else if (i == 103) {
                trainDetail.bx();
            } else {
                aAo_(trainDetail, message);
            }
        }

        protected void aAo_(TrainDetail trainDetail, Message message) {
            int i = message.what;
            if (i == 2) {
                trainDetail.m();
            }
            if (i == 110) {
                trainDetail.cp();
                return;
            }
            if (i == 111) {
                if (trainDetail == null) {
                    LogUtil.h("Suggestion_TrainDetail", "MSG_START_AIMOTIONGUIDANCE activity == null");
                    return;
                } else {
                    CourseControlManager.startAiCourse(trainDetail, trainDetail.aa, CourseControlManager.buildCommonParameter(trainDetail.cf, trainDetail.br.acquirePlanId(), trainDetail.k.c()), trainDetail.aj);
                    return;
                }
            }
            switch (i) {
                case 104:
                    trainDetail.g();
                    break;
                case 105:
                    if (!trainDetail.as) {
                        trainDetail.ck();
                        break;
                    } else {
                        CourseEquipmentConnectionTipsUtil.b(CourseEquipmentConnectionTipsUtil.d(trainDetail.aa, trainDetail.cf.i()), "Suggestion_TrainDetail", trainDetail);
                        break;
                    }
                case 106:
                    if (!trainDetail.as) {
                        trainDetail.co();
                        break;
                    } else {
                        CourseEquipmentConnectionTipsUtil.b(CourseEquipmentConnectionTipsUtil.d(trainDetail.aa, trainDetail.cf.i()), "Suggestion_TrainDetail", trainDetail);
                        break;
                    }
                default:
                    aAr_(trainDetail, message);
                    break;
            }
        }

        protected void aAr_(TrainDetail trainDetail, Message message) {
            int i = message.what;
            if (i == 5) {
                LogUtil.a("Suggestion_TrainDetail", "update Ui");
                if (message.obj instanceof ArrayList) {
                    trainDetail.e((ArrayList) message.obj);
                    return;
                }
                return;
            }
            if (i != 6) {
                switch (i) {
                    case 107:
                        trainDetail.cg();
                        break;
                    case 108:
                        trainDetail.ax();
                        break;
                    case 109:
                        LogUtil.a("Suggestion_TrainDetail", "get the show fa button message");
                        trainDetail.p.setRightThirdKeyVisibility(0);
                        trainDetail.by();
                        break;
                    default:
                        aAs_(trainDetail, message);
                        break;
                }
                return;
            }
            trainDetail.br();
        }

        protected void aAs_(TrainDetail trainDetail, Message message) {
            switch (message.what) {
                case 7:
                    LogUtil.a("Suggestion_TrainDetail", "showNoCourseListLayout");
                    trainDetail.cf();
                    break;
                case 8:
                    LogUtil.a("Suggestion_TrainDetail", "show detail info");
                    trainDetail.cb();
                    break;
                case 9:
                    trainDetail.cq();
                    break;
                case 10:
                    trainDetail.f(0);
                    break;
                case 11:
                    LogUtil.a("Suggestion_TrainDetail", "show member preview");
                    trainDetail.cl();
                    break;
                default:
                    aAp_(trainDetail, message);
                    break;
            }
        }

        protected void aAp_(TrainDetail trainDetail, Message message) {
            int i = message.what;
            if (i == 12) {
                LogUtil.c("Suggestion_TrainDetail", "show not support preview");
                trainDetail.cc();
            } else if (i == 13) {
                if (message.obj instanceof Integer) {
                    trainDetail.b(((Integer) message.obj).intValue());
                }
            } else if (i == 113) {
                LogUtil.c("Suggestion_TrainDetail", "show toast NetState");
                trainDetail.af();
            } else {
                LogUtil.h("Suggestion_TrainDetail", "handleMessageWhenReferenceNotNull is default");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2) {
        if (i2 == 0) {
            getWindow().clearFlags(8192);
        } else {
            getWindow().addFlags(8192);
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ArrayList arrayList = new ArrayList(Arrays.asList(TrainDetail.this.getString(R.string._2130848602_res_0x7f022b5a), TrainDetail.this.getString(R.string._2130848603_res_0x7f022b5b), TrainDetail.this.getString(R.string._2130848604_res_0x7f022b5c)));
            TrainDetail trainDetail = TrainDetail.this;
            new PopViewList(trainDetail, trainDetail.p, arrayList).e(new PopViewList.PopViewClickListener() { // from class: fmk
                @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
                public final void setOnClick(int i) {
                    TrainDetail.AnonymousClass1.this.e(i);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void e(int i) {
            if (i == 0) {
                LogUtil.a("Suggestion_TrainDetail", "mSettingsPointClick, EDIT");
                JumpUtil.b(TrainDetail.this.j, TrainDetail.this.aa != null ? TrainDetail.this.aa.acquireId() : "", 1);
            } else if (i == 1) {
                JumpUtil.b(TrainDetail.this.j, TrainDetail.this.aa != null ? TrainDetail.this.aa.acquireId() : "", 2);
            } else if (i == 2) {
                LogUtil.a("Suggestion_TrainDetail", ProfileRequestConstants.DELETE_TYPE);
                TrainDetail.this.ca();
            } else {
                LogUtil.h("Suggestion_TrainDetail", "mSettingsPointClick, position = ", Integer.valueOf(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ca() {
        View inflate = View.inflate(this.j, R.layout.delete_course_dialog, null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.czg_(inflate).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: fmb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAm_(view);
            }
        }).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: fmf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAn_(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.s = e2;
        e2.show();
    }

    public /* synthetic */ void aAm_(View view) {
        c(this.s);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aAn_(View view) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_TrainDetail", "deleteUserDefinedWorkout copy courseApi == null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        final HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("event", 3);
        courseApi.deleteUserDefinedWorkout(this.aa.acquireId(), 1, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.11
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                LogUtil.b("Suggestion_TrainDetail", "deleteUserDefinedWorkout copy failed.");
                hashMap.put("result", 1);
                gge.e("1130057", hashMap);
                nrh.d(BaseApplication.getContext(), str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_TrainDetail", "detail deleteUserDefinedWorkout data:", str);
                hashMap.put("result", 0);
                gge.e("1130057", hashMap);
                TrainDetail.this.finish();
            }
        });
        c(this.s);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(CustomViewDialog customViewDialog) {
        if (customViewDialog != null) {
            customViewDialog.dismiss();
            LogUtil.a("Suggestion_TrainDetail", "dismissDialog");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i2) {
        azW_(findViewById(R.id.sug_fitness_rl_2), i2);
        azW_(this.ca, i2);
        azW_(this.bo, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cq() {
        azW_(findViewById(R.id.non_trial_header), 8);
        f(8);
        azW_(this.mLoadingView, 8);
        CustomTitleBar customTitleBar = this.p;
        if (customTitleBar != null) {
            customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this.j, R.color._2131296690_res_0x7f0901b2));
        }
        azW_(this.cc, 0);
        azW_(this.v, 0);
        bq();
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout != null && fitWorkout.isCustomCourse()) {
            bs();
        }
        if (this.cf.af()) {
            bo();
        }
    }

    private void bq() {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.sug_traind_outer);
        if (frameLayout != null) {
            frameLayout.setBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        }
        RelativeLayout relativeLayout = this.au;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        }
        LinearLayout linearLayout = this.cd;
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.cd.getLayoutParams();
            layoutParams.height = -1;
            this.cd.setLayoutParams(layoutParams);
        }
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.sug_custom_course_fragment);
        if (frameLayout2 != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.sug_custom_course_fragment, CustomCourseDetailFragment.b(this.aa)).commitAllowingStateLoss();
            frameLayout2.setVisibility(0);
        }
    }

    private void bp() {
        azW_(findViewById(R.id.empty_vip_preview), 0);
        azW_(findViewById(R.id.member_preview_header), 0);
        azW_(findViewById(R.id.non_trial_header), 8);
        frt frtVar = this.bn;
        if (frtVar != null) {
            frtVar.b(0);
        }
        frt frtVar2 = this.bh;
        if (frtVar2 != null) {
            frtVar2.b(8);
        }
        azW_(this.bk, 0);
        azW_(this.h, 8);
        azW_(this.f3100a, 8);
        azW_(this.f, 8);
    }

    private void bs() {
        CustomTitleBar customTitleBar = this.p;
        if (customTitleBar != null) {
            customTitleBar.setRightButtonDrawable(getDrawable(R.drawable._2131429707_res_0x7f0b094b), nsf.h(R.string._2130850635_res_0x7f02334b));
            this.p.setRightButtonOnClickListener(this.bs);
            this.p.setRightButtonVisibility(0);
            if (EnvironmentInfo.k()) {
                this.p.setRightSoftkeyVisibility(8);
                return;
            }
            this.p.setRightSoftkeyBackground(ContextCompat.getDrawable(this.j, R.drawable._2131431688_res_0x7f0b1108), nsf.h(R.string.IDS_device_title_use));
            this.p.setRightSoftkeyVisibility(0);
            this.p.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: fmi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TrainDetail.this.aAj_(view);
                }
            });
        }
    }

    public /* synthetic */ void aAj_(View view) {
        if (nsn.a(1000)) {
            LogUtil.h("Suggestion_TrainDetail", "watch click is fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LogUtil.a("Suggestion_TrainDetail", "onClick : watch");
            bo();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void bo() {
        if (!ggx.a()) {
            fpq.a(this.j);
            return;
        }
        if (!fyc.d()) {
            fpq.d(this.j, getResources().getString(R.string.IDS_fitness_plan_not_connect_device), getResources().getString(R.string.IDS_sug_query_custom_course_devices));
        } else if (!gij.a()) {
            fpq.c(this.j, getResources().getString(R.string._2130848683_res_0x7f022bab), getResources().getString(R.string.IDS_sug_query_custom_course_devices));
        } else {
            if (this.t != null && gim.e(gij.b())) {
                LogUtil.a("Suggestion_TrainDetail", "watch device connected");
                this.t.d(this.aa, true, (IBaseResponseCallback) new b(this));
                return;
            }
            LogUtil.h("Suggestion_TrainDetail", "mDeviceManager is null ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void azW_(View view, int i2) {
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    /* loaded from: classes4.dex */
    public static class b implements IBaseResponseCallback {
        private WeakReference<TrainDetail> c;

        public b(TrainDetail trainDetail) {
            this.c = new WeakReference<>(trainDetail);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Integer valueOf = Integer.valueOf(i);
            StringBuilder sb = new StringBuilder(" ");
            Integer num = (Integer) obj;
            sb.append(num.intValue());
            LogUtil.a("Suggestion_TrainDetail", "mDeviceResponseCallback errorCode = ", valueOf, sb.toString());
            Context context = BaseApplication.getContext();
            if (i == 0) {
                if (num.intValue() == 4) {
                    d(context, context.getString(R.string.IDS_sug_devices_upper_limit));
                } else {
                    d(context, context.getString(R.string.IDS_sug_devices_success));
                }
            } else {
                d(context, context.getString(R.string.IDS_sug_devices_fail));
            }
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            hashMap.put("type", 1);
            hashMap.put("workout_name", fpq.e(context));
            hashMap.put("resultCode", obj);
            gge.e("1130024", hashMap);
        }

        private void d(final Context context, final String str) {
            TrainDetail trainDetail = this.c.get();
            if (trainDetail == null) {
                LogUtil.h("Suggestion_TrainDetail", "DeviceResponseCallback showDeviceLinkTips trainDetail is null");
            } else {
                trainDetail.runOnUiThread(new Runnable() { // from class: fmt
                    @Override // java.lang.Runnable
                    public final void run() {
                        nrh.c(context, str);
                    }
                });
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.a("Suggestion_TrainDetail", "onSaveInstanceState enter");
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        cancelAdaptRingRegion();
        this.cl = new WifiReceiver();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(this.cl, intentFilter);
        a(this.j);
        setContentView(R.layout.sug_fitness_activity_train_detail);
        aw();
        at();
        ay();
        if (CommonUtil.bu()) {
            this.p.setRightSoftkeyVisibility(8);
        }
        ba();
        this.ac = new nqt(this.bo, this.ca);
        bb();
        au();
        az();
        bu();
        cv();
        f(4);
        cn();
    }

    private void ap() {
        long b2 = jdl.b(System.currentTimeMillis(), 2, 0);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_TrainDetail", "TrainDetail PlanApi is null.");
            d(b2, R.string._2130848694_res_0x7f022bb6, false);
        } else {
            planApi.b(new AnonymousClass22(b2));
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail$22, reason: invalid class name */
    public class AnonymousClass22 extends UiPagingCallback<IntPlan> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f3105a;

        AnonymousClass22(long j) {
            this.f3105a = j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccessFirst(final IntPlan intPlan) {
            final long j = this.f3105a;
            HandlerExecutor.e(new Runnable() { // from class: fmp
                @Override // java.lang.Runnable
                public final void run() {
                    TrainDetail.AnonymousClass22.this.d(intPlan, j);
                }
            });
        }

        public /* synthetic */ void d(IntPlan intPlan, long j) {
            int i = R.string._2130848694_res_0x7f022bb6;
            boolean z = false;
            if (intPlan == null) {
                TrainDetail.this.d(j, R.string._2130848694_res_0x7f022bb6, false);
                return;
            }
            LogUtil.a("Suggestion_TrainDetail", "week end time:", Long.valueOf(j), "mPlanStartTime:", Long.valueOf(TrainDetail.this.cf.j()), " planType:", Integer.valueOf(intPlan.getPlanType().getType()));
            if (intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
                TrainDetail.this.y = 0;
                TrainDetail.this.e(0);
                TrainDetail trainDetail = TrainDetail.this;
                trainDetail.azW_(trainDetail.n, 8);
                return;
            }
            boolean equals = IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType());
            if (equals) {
                TrainDetail.this.cf.c("weightControlPlan");
            }
            if (equals && ase.f()) {
                z = true;
            }
            if (z) {
                j = jdl.e(System.currentTimeMillis());
                i = R.string._2130848848_res_0x7f022c50;
            }
            TrainDetail.this.d(j, i, z);
        }

        public /* synthetic */ void a(long j) {
            TrainDetail.this.d(j, R.string._2130848694_res_0x7f022bb6, false);
        }

        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
        public void onFailureFirst(int i, String str) {
            final long j = this.f3105a;
            HandlerExecutor.e(new Runnable() { // from class: fms
                @Override // java.lang.Runnable
                public final void run() {
                    TrainDetail.AnonymousClass22.this.a(j);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j2, int i2, boolean z) {
        HealthTextView healthTextView;
        if (isFinishing() || isDestroyed()) {
            LogUtil.h("Suggestion_TrainDetail", "showUnLockView is Destroyed or isFinishing");
            return;
        }
        if (nsn.p() && (healthTextView = this.ax) != null) {
            healthTextView.setTextSize(1, 20.0f);
        }
        if (this.cf.j() > j2) {
            azW_(this.v, 8);
            azW_(this.x, 8);
            this.y = 8;
            this.ax.setText(i2);
            azW_(this.n, 0);
            return;
        }
        if (this.cf.j() == -1 || this.cf.j() == -2 || this.cf.j() == -3) {
            azW_(this.v, 8);
            azW_(this.x, 8);
            this.y = 8;
            azW_(this.n, 0);
            azW_(this.aw, 8);
            b(z);
            return;
        }
        if (this.n.getVisibility() == 0) {
            this.y = 0;
            e(0);
            azW_(this.n, 8);
        }
    }

    private void cn() {
        LogUtil.a("Suggestion_TrainDetail", " mPlanTempId:", this.cf.g());
        if (this.cf.j() != 0) {
            ap();
            return;
        }
        if (!TextUtils.isEmpty(this.cf.g())) {
            this.y = 8;
            azW_(this.v, 8);
            azW_(this.x, this.y);
            azW_(this.n, 8);
            return;
        }
        this.y = 0;
        e(0);
        azW_(this.n, 8);
    }

    /* renamed from: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail$23, reason: invalid class name */
    public class AnonymousClass23 implements View.OnClickListener {
        AnonymousClass23() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TrainDetail.this.cf.aa()) {
                TrainDetail.this.bv();
            } else if (TrainDetail.this.cc == null) {
                TrainDetail.this.bf();
            } else {
                njn.cvi_(TrainDetail.this.cc.getChildAt(TrainDetail.this.cc.getChildCount() - 1), new IBaseResponseCallback() { // from class: fmr
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        TrainDetail.AnonymousClass23.this.a(i, obj);
                    }
                });
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        public /* synthetic */ void a(int i, Object obj) {
            if (i == 0) {
                TrainDetail.this.bf();
            }
        }
    }

    private void ba() {
        this.p.setLeftButtonOnClickListener(new AnonymousClass23());
        this.mLoadingView = findViewById(R.id.train_detail_loading);
        this.mLoadingView.setTag(false);
        HealthSubTabWidget healthSubTabWidget = (HealthSubTabWidget) findViewById(R.id.sug_detail_tab);
        this.ca = healthSubTabWidget;
        healthSubTabWidget.setVisibility(8);
        setViewSafeRegion(false, this.mLoadingView, this.ca);
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.sug_detail_vp);
        this.bo = healthViewPager;
        healthViewPager.setDynamicSpringAnimaitionEnabled(false);
        this.bx = new String[]{getString(R.string._2130848468_res_0x7f022ad4), getString(R.string._2130848469_res_0x7f022ad5)};
        this.bo.setOffscreenPageLimit(2);
        this.ca.setOnSubTabChangeListener(new HwSubTabWidget.OnSubTabChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.21
            @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
            public void onSubTabReselected(smy smyVar) {
            }

            @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
            public void onSubTabUnselected(smy smyVar) {
            }

            @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
            public void onSubTabSelected(smy smyVar) {
                if (TrainDetail.this.bg == null || smyVar == null || TextUtils.equals(TrainDetail.this.getString(R.string._2130848469_res_0x7f022ad5), smyVar.i())) {
                    return;
                }
                TrainDetail.this.bg.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bf() {
        if (getIntent() != null && getIntent().getBooleanExtra("moveTaskToBack", false)) {
            moveTaskToBack(true);
        }
        finish();
    }

    private void bu() {
        ReleaseLogUtil.e("Suggestion_TrainDetail", "setTrainViewClick enter");
        this.z.setOnClickListener(this);
        if (this.m != null) {
            this.q.c();
        }
    }

    private void ay() {
        this.p.setRightThirdKeyBackground(getResources().getDrawable(R.drawable._2131427796_res_0x7f0b01d4), nsf.h(R.string._2130842298_res_0x7f0212ba));
        this.p.setRightThirdKeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("onclick the startAbilityButton", new Object[0]);
                if (!nsn.a(500)) {
                    TrainDetail.this.ah.sendMessage(TrainDetail.this.ah.obtainMessage(108));
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.h("Suggestion_TrainDetail", "FA Button is fast click");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.p.setRightThirdKeyVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ax() {
        LogUtil.a("Suggestion_TrainDetail", "start to initFA");
        CourseControlManager.initFa(this.j, this.aa, this.br.acquirePlanId(), this.cf.j(), new UiCallback<Integer>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.30
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                ReleaseLogUtil.e("Suggestion_TrainDetail", "initFa errorCode:", Integer.valueOf(i2), " errorInfo:", str);
                TrainDetail.this.ah.sendMessage(TrainDetail.this.ah.obtainMessage(4));
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Integer num) {
                ReleaseLogUtil.e("Suggestion_TrainDetail", "initFa data:", num);
                if (TrainDetail.this.isDestroyed()) {
                    LogUtil.a("Suggestion_TrainDetail", "initFa isDestroyed");
                } else if (num.intValue() == 0) {
                    TrainDetail.this.finish();
                }
            }
        });
    }

    private void az() {
        HealthDownLoadWidget healthDownLoadWidget = (HealthDownLoadWidget) findViewById(R.id.health_download_widget);
        this.x = healthDownLoadWidget;
        healthDownLoadWidget.setTag(true);
        this.ax = (HealthTextView) findViewById(R.id.locked_course_button_text);
        bt();
        this.c = (LinearLayout) findViewById(R.id.sug_bottom_train_linear);
        HealthButton healthButton = (HealthButton) findViewById(R.id.general_training_btn);
        this.af = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: fls
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAb_(view);
            }
        });
        this.ai = (RelativeLayout) findViewById(R.id.interactive_training_relayout);
        ghd.a((HealthTextView) findViewById(R.id.interactive_training_text));
        this.ai.setOnClickListener(new View.OnClickListener() { // from class: fly
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAc_(view);
            }
        });
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.connect_device_btn);
        this.i = healthButton2;
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: flv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAd_(view);
            }
        });
        HealthButton healthButton3 = (HealthButton) findViewById(R.id.start_training_btn);
        this.by = healthButton3;
        healthButton3.setOnClickListener(new View.OnClickListener() { // from class: flw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAe_(view);
            }
        });
        this.h = (HealthTextView) findViewById(R.id.sug_coach_tv_train_desc);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sug_coach_train_tv_complete_num);
        this.f = healthTextView;
        setViewSafeRegion(false, this.h, healthTextView);
        this.bh = new frt(findViewById(R.id.normal_header_item));
        this.bn = new frt(findViewById(R.id.preview_header_item));
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout_connect_binding);
        this.au = relativeLayout;
        setViewSafeRegion(false, relativeLayout);
        this.g = (HealthTextView) findViewById(R.id.tv_connect_binding);
        this.b = (HealthButton) findViewById(R.id.but_connect_binding);
        this.z = (NetworkErrorTipBar) findViewById(R.id.ll_fitness_getdata_error);
        this.bm = (FrameLayout) findViewById(R.id.sfv_parent_preview);
        if (nsn.r()) {
            int c2 = nsn.c(this.j, 8.0f);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h.getLayoutParams();
            layoutParams.topMargin = c2;
            this.h.setLayoutParams(layoutParams);
            this.h.setLineSpacing(-c2, 1.0f);
            this.bh.a(c2);
        }
        if (nsn.s()) {
            ao();
        }
    }

    public /* synthetic */ void aAb_(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("Suggestion_TrainDetail", "GeneralTraining click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.ag = false;
            c(0);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void aAc_(View view) {
        LogUtil.a("Suggestion_TrainDetail", "InteractiveTraining click");
        if (nsn.cLk_(view)) {
            LogUtil.a("Suggestion_TrainDetail", "InteractiveTraining click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            AiSportVoiceHelper.getInstance().setHelperType(FitWorkout.acquireComeFrom(this.d.acquireWorkId()));
            ggr.b(1, this.d.acquireWorkId(), this.aa.acquireName());
            mon.d().launchActivity(BaseApplication.getContext(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.26
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context, Intent intent) {
                    LogUtil.a("Suggestion_TrainDetail", "InteractiveTraining launchActivity");
                    if (TrainDetail.this.av) {
                        TrainDetail.this.aj = false;
                    } else {
                        TrainDetail.this.aj = true;
                    }
                    TrainDetail.this.ag = true;
                    TrainDetail.this.c(1);
                    return false;
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void aAd_(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("Suggestion_TrainDetail", "GeneralTraining click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.as = true;
        this.ag = false;
        c(0);
        ggr.d(this.aa);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aAe_(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("Suggestion_TrainDetail", "GeneralTraining click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.as = false;
            this.ag = false;
            c(0);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        LogUtil.a("Suggestion_TrainDetail", "mIsWorkoutDownloaded: ", Boolean.valueOf(this.av), "select: ", Integer.valueOf(i2));
        if (bd()) {
            c(R.string._2130848740_res_0x7f022be4, 0);
            ReleaseLogUtil.e("Suggestion_TrainDetail", "The current language setting of the mobile phone does not support this course.");
            return;
        }
        if (this.av) {
            if (j() && i2 != 1) {
                t();
                return;
            } else if (i2 == 0) {
                q();
                return;
            } else {
                v();
                LogUtil.a("Suggestion_TrainDetail", "checkDeviceStatusAndStartTraining");
                return;
            }
        }
        ad();
    }

    private boolean bd() {
        return (SportSupportUtil.a() || this.aa.isRunModelCourse() || this.aa.isLongVideoCourse()) ? false : true;
    }

    private void ao() {
        this.h.setVisibility(8);
        this.g.setTextSize(1, 28.0f);
        if (this.g.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.g.getLayoutParams();
            layoutParams.removeRule(16);
            layoutParams.rightMargin = nsn.c(this.j, 8.0f);
            this.g.setLayoutParams(layoutParams);
        }
        if (this.b.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams2.removeRule(21);
            layoutParams2.addRule(14);
            layoutParams2.addRule(3, R.id.tv_connect_binding);
            this.b.setLayoutParams(layoutParams2);
        }
    }

    private void au() {
        this.bf = (ImageView) findViewById(R.id.new_imageView);
        this.v = (HealthColumnRelativeLayout) findViewById(R.id.sug_download_view);
        this.n = (HealthCardView) findViewById(R.id.corresponding_week_unlock_course);
        this.cc = (RelativeLayout) findViewById(R.id.sug_download_rl);
        this.aw = (ImageView) findViewById(R.id.locked_course_img);
    }

    private void bb() {
        this.m = new frp(this, this);
        this.q = new CourseDetailViewHolder(this);
        TrainActionIntro trainActionIntro = (TrainActionIntro) findViewById(R.id.sug_train_action_intro);
        this.cd = (LinearLayout) findViewById(R.id.ll_train_detail_content);
        frq frqVar = new frq(this, trainActionIntro, this.cd, this);
        this.l = frqVar;
        this.m.b(frqVar);
        this.bv = (FrameLayout) findViewById(R.id.sug_traind_outer);
    }

    private void cv() {
        this.ac.b();
        if (isFreeCourse()) {
            c(e(false));
        } else {
            e(c(false));
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider
    public boolean isFreeCourse() {
        FitWorkout fitWorkout;
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter == null || (fitWorkout = this.aa) == null) {
            return true;
        }
        return ipresenter.isFreeCourses(fpq.b(fitWorkout));
    }

    private boolean c(boolean z) {
        CourseDetailViewHolder courseDetailViewHolder;
        smy c2 = this.ca.c(this.bx[1]);
        if (this.cb == 0 && (courseDetailViewHolder = this.q) != null) {
            boolean z2 = !z;
            this.ac.cGt_(c2, courseDetailViewHolder.aFi_(), z2);
            return z2;
        }
        if (this.bg == null) {
            NewViewDetailInfo newViewDetailInfo = new NewViewDetailInfo(this.j);
            this.bg = newViewDetailInfo;
            newViewDetailInfo.a(new ViewVideoPlayListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.28
                @Override // com.huawei.health.suggestion.ui.fitness.mvp.ViewVideoPlayListener
                public void startCoachPlay() {
                }

                @Override // com.huawei.health.suggestion.ui.fitness.mvp.ViewVideoPlayListener
                public void onVideoStartPlay() {
                    if (!TrainDetail.this.bg() || TrainDetail.this.ch == null) {
                        return;
                    }
                    TrainDetail.this.ch.f();
                }
            });
        }
        boolean z3 = !z;
        this.ac.cGt_(c2, this.bg.aEI_(), z3);
        return z3;
    }

    private boolean e(boolean z) {
        frp frpVar;
        smy c2 = this.ca.c(this.bx[0]);
        if (!this.ao || (frpVar = this.m) == null) {
            return false;
        }
        boolean z2 = !z;
        this.ac.cGt_(c2, frpVar.aFg_(), z2);
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bg() {
        FitWorkout fitWorkout = this.aa;
        return (fitWorkout == null || TextUtils.isEmpty(fitWorkout.getPreviewVideoUrl()) || this.aa.acquireCommodityFlag() == 0) ? false : true;
    }

    public void a() {
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout == null) {
            LogUtil.b("Suggestion_TrainDetail", "bindNewDetailInfo mFitWorkout is null.");
            return;
        }
        this.cb = fitWorkout.getTemplateType();
        LayoutTemplateInfo layoutTemplateInfo = this.aa.getLayoutTemplateInfo();
        int i2 = this.cb;
        if (i2 == 1 && layoutTemplateInfo != null) {
            cv();
            this.bg.c(layoutTemplateInfo);
            this.ac.notifyDataSetChanged();
            RecommendationBar recommendationBar = layoutTemplateInfo.getRecommendationBar();
            TrainDetailContract.Ipresenter ipresenter = this.bj;
            if (ipresenter == null || recommendationBar == null) {
                return;
            }
            ipresenter.getRecommendationList(recommendationBar.getRecommendationWorkouts());
            return;
        }
        LogUtil.a("Suggestion_TrainDetail", "bindNewDetailInfo templateType:", Integer.valueOf(i2));
    }

    private void aw() {
        ReleaseLogUtil.e("Suggestion_TrainDetail", "initCollectionBehaveView message enter");
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_panel);
        this.p = customTitleBar;
        customTitleBar.setTitleText("");
        this.o = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        av();
        this.p.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131429872_res_0x7f0b09f0), nsf.h(R.string._2130848479_res_0x7f022adf));
        this.p.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: fmg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.azY_(view);
            }
        });
    }

    public /* synthetic */ void azY_(View view) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(this.j).browsingToLogin(new fnm(this, 0), "");
        } else {
            e();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void av() {
        if (EnvironmentInfo.k()) {
            return;
        }
        this.p.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131431688_res_0x7f0b1108), nsf.h(R.string.IDS_device_title_use));
        this.p.setRightTextButtonOnClickListener(new View.OnClickListener() { // from class: fme
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.azZ_(view);
            }
        });
    }

    public /* synthetic */ void azZ_(View view) {
        this.k.j();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void at() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.a("Suggestion_TrainDetail", "isBrowseMode.can not share.");
            return;
        }
        if (LanguageUtil.bc(this)) {
            this.p.setRightFourKeyBackground(nrz.cKn_(this, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.p.setRightFourKeyBackground(getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.p.setRightFourKeyOnClickListener(new View.OnClickListener() { // from class: fmd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAa_(view);
            }
        });
    }

    public /* synthetic */ void aAa_(View view) {
        if (this.aa == null) {
            LogUtil.h("Suggestion_TrainDetail", "on shared click, mFitWorkout is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (this.bt == null) {
                this.bt = new fpl();
            }
            this.bt.aCJ_(this, !TextUtils.isEmpty(this.aa.acquireName()) ? this.aa.acquireName() : StringUtils.c((Object) this.br.acquireWorkoutName()), this.ab, this.aa);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void bt() {
        this.x.setOnClickListener(new View.OnClickListener() { // from class: flu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAh_(view);
            }
        });
    }

    public /* synthetic */ void aAh_(View view) {
        ad();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        this.aq = true;
        if (bd()) {
            c(R.string._2130848740_res_0x7f022be4, 0);
            LogUtil.h("Suggestion_TrainDetail", "setListener() mDownloadWidget onClick is not open fitness course");
        } else if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(this.j).browsingToLogin(new fnm(this, 1), "");
        } else if (nsn.a(500)) {
            LogUtil.h("Suggestion_TrainDetail", "TrainDetail is fast click");
        } else {
            x();
        }
    }

    private void x() {
        CourseControlManager.checkMultilingualAudio(this.j, this.aa, new UiCallback<Integer>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Integer num) {
                TrainDetail.this.ab();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ab() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_TrainDetail", "checkUserInfo : userProfileMgrApi is null.");
        } else {
            userProfileMgrApi.checkUserInfo(this, new IBaseResponseCallback() { // from class: fmm
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    TrainDetail.this.b(i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void b(int i2, Object obj) {
        if (i2 != 0) {
            WorkoutRecord workoutRecord = this.br;
            if (workoutRecord == null) {
                LogUtil.h("Suggestion_TrainDetail", "checkUserInfo : mRecord is null.");
            } else {
                if (TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
                    if (this.ae == null) {
                        this.ae = new IntPlanPreSportRemind(this);
                    }
                    this.ae.c(this.ck, new IntPlanPreSportRemind.Action() { // from class: fma
                        @Override // com.huawei.health.suggestion.ui.fitness.helper.IntPlanPreSportRemind.Action
                        public final void execute() {
                            TrainDetail.this.g();
                        }
                    });
                    return;
                }
                g();
            }
        }
    }

    public boolean j() {
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout != null) {
            return fitWorkout.isRunModelCourse();
        }
        return false;
    }

    public boolean f() {
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_TrainDetail", " mFitWorkout is null");
            return false;
        }
        return ggs.d(fitWorkout);
    }

    private boolean bc() {
        return (!f() || this.aa.isCustomCourse() || EnvironmentInfo.k()) ? false : true;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        LogUtil.a("Suggestion_TrainDetail", "initViewController()");
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        LogUtil.a("Suggestion_TrainDetail", "initData1:", Long.valueOf(System.currentTimeMillis()));
        as();
    }

    private void as() {
        Intent intent = getIntent();
        if (intent == null) {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "havePermissionToGetData from others");
            am();
        } else {
            azV_(intent);
        }
    }

    private void azV_(Intent intent) {
        if (this.cf.ab()) {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "havePermissionToGetData from scheme");
            try {
                azU_(intent);
                return;
            } catch (BadParcelableException e2) {
                LogUtil.b("Suggestion_TrainDetail", "havePermissionToGetData BadParcelableException", LogAnonymous.b((Throwable) e2));
                return;
            }
        }
        azT_(intent);
    }

    private void b(boolean z) {
        if (this.cf.j() == -1) {
            this.ax.setText(R.string._2130848719_res_0x7f022bcf);
            azW_(this.aw, 0);
        } else {
            if (this.cf.j() == -2) {
                this.ax.setText(R.string._2130846125_res_0x7f0221ad);
                if (z) {
                    this.n.setAlpha(1.0f);
                    this.n.setOnClickListener(new View.OnClickListener() { // from class: flz
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            TrainDetail.this.aAi_(view);
                        }
                    });
                    return;
                }
                return;
            }
            this.ax.setText(R.string._2130848720_res_0x7f022bd0);
        }
    }

    public /* synthetic */ void aAi_(View view) {
        WorkoutRecord workoutRecord = this.br;
        if (workoutRecord != null) {
            workoutRecord.savePlanId("");
        }
        ad();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void azU_(Intent intent) {
        String stringExtra = intent.getStringExtra("version");
        String stringExtra2 = intent.getStringExtra("workoutid");
        if (TextUtils.isEmpty(stringExtra)) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "havePermissionToGetData from scheme and workid is null or empty");
            return;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "";
        }
        CourseApi courseApi = this.o;
        if (courseApi == null) {
            LogUtil.h("Suggestion_TrainDetail", "havePermissionToGetData: mCourseApi is null.");
        } else {
            courseApi.getCourseById(new ffl.d(stringExtra2).d(stringExtra).b(), new i(this));
        }
    }

    private void azT_(Intent intent) {
        Uri data = intent.getData();
        LogUtil.a("Suggestion_TrainDetail", "uri", data);
        if (data == null) {
            am();
            return;
        }
        try {
            String queryParameter = data.getQueryParameter("id");
            CourseApi courseApi = this.o;
            if (courseApi == null) {
                LogUtil.h("Suggestion_TrainDetail", "havePermissionToGetData: mCourseApi is null.");
            } else {
                courseApi.getCourseById(new ffl.d(queryParameter).b(), new i(this));
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.b("Suggestion_TrainDetail", "goFitnessPage IllegalArgumentException");
        } catch (UnsupportedOperationException unused2) {
            LogUtil.b("Suggestion_TrainDetail", "goFitnessPage UnsupportedOperationException");
        }
    }

    /* loaded from: classes4.dex */
    static class i extends UiCallback<Workout> {
        private WeakReference<TrainDetail> c;

        i(TrainDetail trainDetail) {
            this.c = new WeakReference<>(trainDetail);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "GetWorkoutCallback onFailure ", Integer.valueOf(i));
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            if (workout == null) {
                ReleaseLogUtil.d("Suggestion_TrainDetail", "GetWorkoutCallback onSuccess data is null ");
                return;
            }
            FitWorkout a2 = mod.a(workout);
            WeakReference<TrainDetail> weakReference = this.c;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            if (!fib.e().c()) {
                ReleaseLogUtil.d("Suggestion_TrainDetail", "PluginSuggestionHelper not isInitComplete");
                return;
            }
            gge.d((String) null);
            WorkoutRecord workoutRecord = new WorkoutRecord();
            workoutRecord.saveVersion(a2.accquireVersion());
            workoutRecord.saveExerciseTime(new Date().getTime());
            workoutRecord.saveWorkoutId(a2.acquireId());
            workoutRecord.saveWorkoutName(a2.acquireName());
            workoutRecord.savePlanId("");
            workoutRecord.saveVersion(a2.accquireVersion());
            workoutRecord.saveCalorie(a2.acquireCalorie());
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(workoutRecord);
            TrainDetail trainDetail = this.c.get();
            if (trainDetail != null) {
                trainDetail.bp = arrayList;
                trainDetail.am();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, int i2, UiCallback<LongVideoInfo> uiCallback) {
        if (uiCallback == null) {
            uiCallback = new a(this, z, i2);
        }
        CourseApi courseApi = this.o;
        if (courseApi != null) {
            courseApi.getCourseLongVideoInfo(new ffl.d(this.ck).a(1).b(), uiCallback);
        }
    }

    /* loaded from: classes4.dex */
    static class a extends UiCallback<LongVideoInfo> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<TrainDetail> f3112a;
        private boolean b;
        private int e;

        a(TrainDetail trainDetail, boolean z, int i) {
            this.f3112a = new WeakReference<>(trainDetail);
            this.b = z;
            this.e = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "getCourseLongVideoInfo:", Integer.valueOf(i), str);
            TrainDetail trainDetail = this.f3112a.get();
            if (trainDetail != null) {
                trainDetail.d(String.valueOf(i));
            } else {
                LogUtil.h("Suggestion_TrainDetail", "GetLongVideoUiCallback onFailure trainDetail is null");
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(LongVideoInfo longVideoInfo) {
            if (longVideoInfo == null) {
                ReleaseLogUtil.d("Suggestion_TrainDetail", "getLongVideo startLongCoachActivity data.getUrl:url is null");
                return;
            }
            boolean z = this.b;
            if (!z) {
                LogUtil.h("Suggestion_TrainDetail", "GetLongVideoUiCallback onSuccess mIsStartLongCoachActivity = ", Boolean.valueOf(z));
                return;
            }
            TrainDetail trainDetail = this.f3112a.get();
            if (trainDetail != null) {
                trainDetail.a(0);
                trainDetail.ba = longVideoInfo.getUrl();
                trainDetail.ay = this.e;
                if (TextUtils.isEmpty(longVideoInfo.getSubtitlesUrl())) {
                    trainDetail.s();
                } else {
                    trainDetail.bc = longVideoInfo.getSubtitlesUrl();
                    trainDetail.ak();
                }
                ReleaseLogUtil.e("Suggestion_TrainDetail", "getLongVideo startLongCoachActivity URL has been obtained");
                return;
            }
            LogUtil.h("Suggestion_TrainDetail", "GetLongVideoUiCallback onSuccess trainDetail is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        int i2;
        ArrayList<WorkoutRecord> arrayList = this.bp;
        if (arrayList == null || this.r >= arrayList.size() || (i2 = this.r) < 0) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "intent.getParcelableArrayListExtra(Constants.WORKOUT_RECORD_OLD) is null");
            finish();
            return;
        }
        WorkoutRecord workoutRecord = this.bp.get(i2);
        this.br = workoutRecord;
        this.k.c(workoutRecord);
        if (w()) {
            return;
        }
        ReleaseLogUtil.e("Suggestion_TrainDetail", "id:", this.br.acquireWorkoutId(), "-workoutname:", this.br.acquireWorkoutName());
        CoachData coachData = new CoachData();
        this.d = coachData;
        coachData.saveWorkId(this.ck);
        this.d.savePlanId(this.br.acquirePlanId());
        bh();
        jdx.b(new Runnable() { // from class: fmh
            @Override // java.lang.Runnable
            public final void run() {
                TrainDetail.this.i();
            }
        });
    }

    public /* synthetic */ void i() {
        CourseApi courseApi = this.o;
        if (courseApi != null) {
            courseApi.isCourseCollected(this.ck, new d(this));
        }
    }

    /* loaded from: classes4.dex */
    static class d extends UiCallback<Boolean> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<TrainDetail> f3114a;

        d(TrainDetail trainDetail) {
            this.f3114a = null;
            this.f3114a = new WeakReference<>(trainDetail);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            TrainDetail trainDetail = this.f3114a.get();
            if (trainDetail != null) {
                trainDetail.an = false;
                if (trainDetail.o != null) {
                    trainDetail.o.getCourseById(new ffl.d(trainDetail.ck).d(trainDetail.br.acquireVersion()).d(trainDetail.cf.v()).b(), new f(trainDetail));
                    return;
                }
                return;
            }
            LogUtil.h("Suggestion_TrainDetail", "CollectUiCallback onFailure mTrainDetail is null ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            TrainDetail trainDetail = this.f3114a.get();
            if (trainDetail != null) {
                trainDetail.an = bool.booleanValue();
                if (trainDetail.o != null) {
                    trainDetail.o.getCourseById(new ffl.d(trainDetail.ck).d(trainDetail.br.acquireVersion()).d(trainDetail.cf.v()).b(), new f(trainDetail));
                    return;
                }
                return;
            }
            LogUtil.h("Suggestion_TrainDetail", "CollectUiCallback onSuccess mTrainDetail is null ");
        }
    }

    /* loaded from: classes4.dex */
    static class f extends UiCallback<Workout> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<TrainDetail> f3115a;

        f(TrainDetail trainDetail) {
            this.f3115a = null;
            this.f3115a = new WeakReference<>(trainDetail);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_TrainDetail", str);
            TrainDetail trainDetail = this.f3115a.get();
            if (trainDetail != null) {
                Handler handler = trainDetail.ah;
                if (handler != null) {
                    if (trainDetail.aa != null) {
                        trainDetail.br.saveVersion(trainDetail.aa.accquireVersion());
                        if (trainDetail.aa.isNewRunCourse()) {
                            LogUtil.a("Suggestion_TrainDetail", "isNewRunCourse.");
                            a(trainDetail.aa, trainDetail);
                            handler.sendEmptyMessage(9);
                        } else {
                            handler.sendEmptyMessage(10);
                            a(trainDetail.aa, trainDetail);
                            if (trainDetail.f() && !trainDetail.aa.acquireId().equals("R011R")) {
                                Message obtainMessage = handler.obtainMessage(0);
                                obtainMessage.arg1 = 1;
                                handler.sendMessage(obtainMessage);
                            } else {
                                Message obtainMessage2 = handler.obtainMessage(0);
                                obtainMessage2.arg1 = 0;
                                handler.sendMessage(obtainMessage2);
                            }
                        }
                        LogUtil.a("Suggestion_TrainDetail", "have local data");
                        trainDetail.ai();
                        return;
                    }
                    LogUtil.a("Suggestion_TrainDetail", "WorkoutUiCallback > onFailure> mFitWorkout is null");
                    handler.sendEmptyMessage(6);
                    Message obtainMessage3 = handler.obtainMessage(0);
                    obtainMessage3.arg1 = 0;
                    handler.sendMessage(obtainMessage3);
                    return;
                }
                LogUtil.h("Suggestion_TrainDetail", "WorkoutUiCallback onFailure handler is null ");
                return;
            }
            ReleaseLogUtil.d("Suggestion_TrainDetail", "WorkoutUiCallback onFailure mTrainDetail is null", str);
        }

        private void a(FitWorkout fitWorkout, TrainDetail trainDetail) {
            if (fitWorkout == null || trainDetail == null) {
                LogUtil.h("Suggestion_TrainDetail", "buildLongVideoAndShowCourseList fitWorkout or trainDetail is null");
                return;
            }
            if (fitWorkout.isLongVideoCourse()) {
                trainDetail.ao = fitWorkout.getWorkoutActionProperty() != 0;
            }
            if (trainDetail.isLongVideoCourse()) {
                trainDetail.g(fitWorkout);
            } else {
                trainDetail.j(fitWorkout);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            TrainDetail trainDetail = this.f3115a.get();
            if (trainDetail != null) {
                Handler handler = trainDetail.ah;
                if (handler == null) {
                    LogUtil.h("Suggestion_TrainDetail", "WorkoutUiCallback onSuccess handler is null ");
                    return;
                }
                FitWorkout a2 = mod.a(workout);
                if (ffy.c(a2)) {
                    trainDetail.aa = a2;
                    trainDetail.k.c(a2);
                    trainDetail.br.saveVersion(trainDetail.aa.accquireVersion());
                    if (!trainDetail.aa.isNewRunCourse()) {
                        trainDetail.d.setPrimaryClassify(a2.getPrimaryClassify());
                        handler.sendEmptyMessage(10);
                        a(a2, trainDetail);
                        aAt_(handler, trainDetail, a2);
                        return;
                    }
                    LogUtil.a("Suggestion_TrainDetail", "isNewRunCourse.");
                    a(a2, trainDetail);
                    handler.sendEmptyMessage(9);
                    if (trainDetail.bj != null) {
                        trainDetail.bj.initTradeView(a2);
                        return;
                    }
                    return;
                }
                LogUtil.b("Suggestion_TrainDetail", "---onSuccess FitWorkout==null");
                handler.sendEmptyMessage(6);
                Message obtainMessage = handler.obtainMessage(0);
                obtainMessage.arg1 = 0;
                handler.sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("Suggestion_TrainDetail", "WorkoutUiCallback onSuccess mTrainDetail is null ");
        }

        private void aAt_(Handler handler, TrainDetail trainDetail, FitWorkout fitWorkout) {
            if (trainDetail.f() && !trainDetail.aa.acquireId().equals("R011R")) {
                Message obtainMessage = handler.obtainMessage(0);
                obtainMessage.arg1 = 1;
                handler.sendMessage(obtainMessage);
            } else {
                Message obtainMessage2 = handler.obtainMessage(0);
                obtainMessage2.arg1 = 0;
                handler.sendMessage(obtainMessage2);
            }
            if (trainDetail.bj != null) {
                trainDetail.bj.initTradeView(fitWorkout);
            }
            if (fitWorkout.getLayoutTemplateInfo() != null) {
                handler.sendMessage(handler.obtainMessage(8));
                LogUtil.a("Suggestion_TrainDetail", "getLayoutTemplateInfo() is not null");
            }
            Message obtainMessage3 = handler.obtainMessage(13);
            obtainMessage3.obj = Integer.valueOf(fitWorkout.getAntiScreenRecording());
            handler.sendMessage(obtainMessage3);
            if (trainDetail.bg()) {
                handler.sendMessage(handler.obtainMessage(11));
            } else {
                handler.sendMessage(handler.obtainMessage(12));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2) {
        fqy fqyVar;
        LogUtil.a("Suggestion_TrainDetail", " showStart() downLoadViewVisibility:", Integer.valueOf(i2));
        if (this.aa == null || ((fqyVar = this.cf) != null && !TextUtils.isEmpty(fqyVar.g()))) {
            LogUtil.h("Suggestion_TrainDetail", " showStart() mFitWorkout is null", this.cf.g());
            return;
        }
        this.v.setVisibility(i2);
        c(i2, fpq.b(this.aa.getIsAi()), CourseEquipmentConnectionTipsUtil.d(this.aa, this.cf.i()));
        e(getResources().getString(R.string._2130848439_res_0x7f022ab7));
    }

    private void c(int i2, boolean z, int i3) {
        if (z && this.n.getVisibility() == 8) {
            this.c.setVisibility(0);
            this.x.setVisibility(8);
        } else if (i3 != 0 && this.n.getVisibility() == 8) {
            bn();
        } else {
            this.c.setVisibility(8);
            this.x.setVisibility(i2);
        }
    }

    private void bn() {
        this.c.setVisibility(0);
        this.x.setVisibility(8);
        this.by.setVisibility(0);
        this.i.setVisibility(0);
        this.af.setVisibility(8);
        this.ai.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void br() {
        if (this.bp.size() > 1) {
            if (ffy.c(this.f3100a, this.m, this.q, this.bk)) {
                AutoFillColorView autoFillColorView = this.f3100a;
                if (autoFillColorView != null) {
                    autoFillColorView.aMh_(new ColorDrawable(-1));
                }
                AutoFillColorView autoFillColorView2 = this.bk;
                if (autoFillColorView2 != null) {
                    autoFillColorView2.aMh_(new ColorDrawable(-1));
                }
                this.h.setText("");
                frt frtVar = this.bh;
                if (frtVar != null) {
                    frtVar.e();
                }
                frt frtVar2 = this.bn;
                if (frtVar2 != null) {
                    frtVar2.e();
                }
                this.m.c(4);
                this.q.d(4);
                this.v.setVisibility(4);
            }
            this.av = false;
        }
        ReleaseLogUtil.e("Suggestion_TrainDetail", "setDataError workoutName:", this.br.acquireWorkoutName());
        this.p.setTitleText(this.br.acquireWorkoutName());
        this.mLoadingView.setVisibility(8);
        this.z.setVisibility(0);
        azW_(findViewById(R.id.non_trial_header), 0);
        azW_(findViewById(R.id.sug_fitness_rl_2), 0);
    }

    private void h(FitWorkout fitWorkout) {
        l(fitWorkout);
        k(fitWorkout);
        bw();
        e(fitWorkout);
        l();
        r();
        cs();
        n();
        be();
        ci();
    }

    private void e(FitWorkout fitWorkout) {
        frt frtVar = this.bh;
        if (frtVar != null) {
            frtVar.d(fitWorkout);
        }
        frt frtVar2 = this.bn;
        if (frtVar2 != null) {
            frtVar2.d(fitWorkout);
        }
    }

    private void k(FitWorkout fitWorkout) {
        d(fitWorkout);
        c(fitWorkout);
    }

    private void cs() {
        SportServiceApi sportServiceApi;
        if (!j() || (sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class)) == null) {
            return;
        }
        sportServiceApi.setLastUseOrWatchCourse("last_watch_run_course", System.currentTimeMillis(), this.aa);
    }

    private void r() {
        if (!TextUtils.isEmpty(this.aa.acquireName())) {
            this.p.setTitleText(this.aa.acquireName());
        } else {
            this.p.setTitleText(StringUtils.c((Object) this.br.acquireWorkoutName()));
        }
    }

    private void n() {
        if (!TextUtils.isEmpty(this.aa.acquireDescription())) {
            this.h.setText(this.aa.acquireDescription().replaceAll(System.lineSeparator(), ""));
        } else {
            this.h.setText("");
        }
    }

    private void l() {
        this.f3100a = (AutoFillColorView) findViewById(R.id.auto_fill_color_view);
        this.bk = (AutoFillColorView) findViewById(R.id.auto_fill_color_view_preview);
        fpq.d(this.aa.acquireMidPicture(), this.aa.acquirePicture(), this.f3100a);
        fpq.d(this.aa.acquireMidPicture(), this.aa.acquirePicture(), this.bk);
    }

    private void ci() {
        if (isFinishing() || isDestroyed()) {
            LogUtil.h("Suggestion_TrainDetail", "showSeriesCourseTip is Destroyed");
            return;
        }
        if (CommonUtil.h(this.cf.i()) == 1) {
            LogUtil.h("Suggestion_TrainDetail", "showSeriesCourseTip() series course H5");
            return;
        }
        final BelongInfo courseBelongInfoByType = this.aa.getCourseBelongInfoByType(0);
        if (courseBelongInfoByType == null) {
            LogUtil.h("Suggestion_TrainDetail", "showSeriesCourseTip() course is not series");
            return;
        }
        int b2 = fpq.b(this.aa);
        LogUtil.a("Suggestion_TrainDetail", "showSeriesCourseTip() commodityFlag:", Integer.valueOf(b2));
        if (b2 == 0 || b2 == 2) {
            LogUtil.h("Suggestion_TrainDetail", "showSeriesCourseTip() FREE_COURSE or MEMBER_COURSE no showSeriesCourseTip");
            return;
        }
        ViewStub viewStub = (ViewStub) findViewById(R.id.viewStub_tip);
        if (viewStub == null) {
            LogUtil.h("Suggestion_TrainDetail", "showSeriesCourseTip() mViewStubTip is null");
            return;
        }
        if (this.cg == null && viewStub.getParent() != null) {
            LogUtil.a("Suggestion_TrainDetail", "showSeriesCourseTip() mTipsView is null");
            this.cg = viewStub.inflate();
        }
        View view = this.cg;
        if (view == null) {
            LogUtil.h("Suggestion_TrainDetail", "showSeriesCourseTip() mTipsView == null");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sug_series_course_name);
        if (healthTextView != null) {
            healthTextView.setText(getResources().getString(R.string._2130845340_res_0x7f021e9c, courseBelongInfoByType.getName()));
        }
        LinearLayout linearLayout = (LinearLayout) this.cg.findViewById(R.id.layout_tip);
        if (linearLayout == null) {
            LogUtil.h("Suggestion_TrainDetail", "showSeriesCourseTip() tipsLayout == null");
        } else {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    JumpUtil.c(TrainDetail.this, courseBelongInfoByType.getId());
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
    }

    private void l(FitWorkout fitWorkout) {
        aa();
        a(fitWorkout);
    }

    private void a(FitWorkout fitWorkout) {
        ImageView imageView = this.bf;
        if (imageView == null) {
            LogUtil.h("Suggestion_TrainDetail", "bindNewPicView mNewPic == null");
            return;
        }
        imageView.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = this.bf.getLayoutParams();
        if (LanguageUtil.j(this.j) || LanguageUtil.p(this.j)) {
            layoutParams.height = -2;
            if (fitWorkout.acquireStage() == 0 && fitWorkout.acquireIsSupportDevice() == 0) {
                this.bf.setImageResource(R.drawable.pic_corner_new_watchwear);
                this.bf.setVisibility(0);
            } else if (fitWorkout.acquireStage() == 0) {
                this.bf.setImageResource(R.drawable.pic_corner_new);
                this.bf.setVisibility(0);
            } else if (fitWorkout.acquireIsSupportDevice() == 0) {
                this.bf.setImageResource(R.drawable.pic_corner_watchwear);
                this.bf.setVisibility(0);
            } else {
                this.bf.setVisibility(8);
            }
        }
        List<PriceTagBean> acquirePriceTagBeanList = fitWorkout.acquirePriceTagBeanList();
        if (fitWorkout.getCornerImgDisplay() == 1 && acquirePriceTagBeanList != null) {
            String b2 = mod.b(acquirePriceTagBeanList);
            if (!TextUtils.isEmpty(b2)) {
                this.bf.setVisibility(0);
                layoutParams.height = (int) this.bf.getResources().getDimension(R.dimen._2131362959_res_0x7f0a048f);
                nrf.cIK_(b2, this.bf, 0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        this.bf.setLayoutParams(layoutParams);
    }

    private void d(FitWorkout fitWorkout) {
        if ((j() && !Utils.j()) || CommonUtil.bu()) {
            this.p.setRightSoftkeyVisibility(8);
            return;
        }
        if (fitWorkout != null && !fitWorkout.isCourseLibraryShow()) {
            this.p.setRightSoftkeyVisibility(8);
            return;
        }
        if (this.an) {
            this.p.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131429873_res_0x7f0b09f1), nsf.h(R.string._2130847793_res_0x7f022831));
        } else {
            this.p.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131429872_res_0x7f0b09f0), nsf.h(R.string._2130848479_res_0x7f022adf));
        }
        this.p.setRightSoftkeyVisibility(0);
    }

    private void c(FitWorkout fitWorkout) {
        if (SportSupportUtil.f() || fitWorkout.isRunModelCourse()) {
            this.p.setRightFourKeyVisibility(8);
        } else if (fitWorkout.isCourseLibraryShow()) {
            this.p.setRightFourKeyVisibility(0);
        }
    }

    private void aa() {
        if (CourseControlManager.isSupportFaController(this.aa.getSmartScreeFlag())) {
            this.ah.sendMessage(this.ah.obtainMessage(109));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void by() {
        LogUtil.a("Suggestion_TrainDetail", "enter showCirculatingBubble");
        if (isFinishing()) {
            LogUtil.a("Suggestion_TrainDetail", "showCirculatingBubble is Finishing");
        } else if (!fni.a()) {
            LogUtil.a("Suggestion_TrainDetail", "no need to show FaRemind");
        } else {
            ((DistributedApi) Services.c("DistributedService", DistributedApi.class)).getAvailableFaDevice(new fni(this));
        }
    }

    private void bw() {
        if (bc()) {
            this.k.e(new IBaseResponseCallback() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    ReleaseLogUtil.e("Suggestion_TrainDetail", "showBondDeviceLayout() status = ", obj);
                    if (obj instanceof Integer) {
                        TrainDetail.this.d(((Integer) obj).intValue());
                    }
                }
            });
        } else {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "showBondDeviceLayout()", "isRunWorkoutOfHeartRate() = false");
            this.au.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2) {
        this.au.setVisibility(0);
        if (i2 == 1) {
            this.g.setText(R.string._2130848503_res_0x7f022af7);
            this.b.setText(R.string.IDS_plugin_fitnessadvice_connect_device);
            this.b.setOnClickListener(new View.OnClickListener() { // from class: fml
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TrainDetail.this.aAf_(view);
                }
            });
        } else if (i2 == 2) {
            this.g.setText(R.string.IDS_plugin_fitnessadvice_need_device);
            this.b.setText(R.string.IDS_plugin_fitnessadvice_undestance_device);
            this.b.setOnClickListener(new View.OnClickListener() { // from class: flx
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TrainDetail.this.aAg_(view);
                }
            });
        } else if (i2 == 3) {
            this.k.h();
            this.au.setVisibility(8);
        } else {
            this.au.setVisibility(8);
        }
    }

    public /* synthetic */ void aAf_(View view) {
        fny fnyVar = this.k;
        if (fnyVar != null) {
            fnyVar.f();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aAg_(View view) {
        fny fnyVar = this.k;
        if (fnyVar != null) {
            fnyVar.b(this.j);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        fny fnyVar = this.k;
        if (fnyVar != null) {
            fnyVar.aBT_(i2, i3, intent);
        }
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter != null) {
            ipresenter.onActivityResult(i2, i3, intent);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        bw();
    }

    private String aq() {
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout == null) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "getUsers_mFitWorkout_null");
            return "";
        }
        return ffy.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(this.aa.acquireUsers(), 1, 0));
    }

    private String al() {
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout == null) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "getExerciseTimes_mFitWorkout = null");
            return "";
        }
        int acquireExerciseTimes = fitWorkout.acquireExerciseTimes();
        if (acquireExerciseTimes == 0) {
            return aq();
        }
        return ffy.b(R.plurals._2130903489_res_0x7f0301c1, acquireExerciseTimes, Integer.valueOf(acquireExerciseTimes));
    }

    private void be() {
        Map<String, mmz> a2 = ggs.a(this.aa);
        if (!a2.isEmpty()) {
            this.q.d(a2);
        }
        this.q.c(this.aa.getSupplierLogoUrl());
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter != null) {
            ipresenter.initNoticeContent(this.aa.getBuyNotesTitle(), this.aa.getBuyNotesUrl());
        }
        this.q.b(ffy.d(this.aa.acquireEquipments()));
        asc.e().b(new g(this, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ar() {
        List<ffl> d2 = mod.d(mod.b(this.aa));
        if (d2.size() == 0) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "no relative courses");
        } else if (this.o == null) {
            LogUtil.h("Suggestion_TrainDetail", "getRelativeCourses: mCourseApi is null.");
        } else {
            this.o.getCourseByIds(d2, false, new j(this));
        }
    }

    /* loaded from: classes4.dex */
    static class j extends UiCallback<List<FitWorkout>> {
        private WeakReference<TrainDetail> d;

        j(TrainDetail trainDetail) {
            this.d = new WeakReference<>(trainDetail);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "GetRelativeCoursesUiCallback onFailure ", Integer.valueOf(i));
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<FitWorkout> list) {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "getWorkout onSuccess");
            TrainDetail trainDetail = this.d.get();
            if (trainDetail != null) {
                trainDetail.q.c(list);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(FitWorkout fitWorkout) {
        LogUtil.a("Suggestion_TrainDetail", "newLongWorkout:", Long.valueOf(System.currentTimeMillis()));
        ai();
        ggr.a(fitWorkout, i(fitWorkout));
        h(fitWorkout);
        this.z.setVisibility(8);
        m(fitWorkout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(FitWorkout fitWorkout) {
        LogUtil.a("Suggestion_TrainDetail", "-----refreshHeadUi:", Long.valueOf(System.currentTimeMillis()));
        ai();
        ggr.a(fitWorkout, i(fitWorkout));
        if (!fitWorkout.isNewRunCourse()) {
            h(fitWorkout);
        }
        this.z.setVisibility(8);
        int a2 = ggg.a();
        this.ab = a2;
        LogUtil.a("Suggestion_TrainDetail", "newWorkout : mGender: ", Integer.valueOf(a2));
        if (fitWorkout.getCourseAttr() != 2) {
            this.ab = fitWorkout.getCourseAttr();
        }
        asc.e().b(new c(this, fitWorkout, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FitWorkout fitWorkout) {
        float c2 = mod.c(this.ck);
        this.w = c2;
        if (c2 == 1.0f) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "have download progress: mDownloaded == 1 ");
            this.w = 0.0f;
        }
        float f2 = this.w;
        this.ce = 1.0f - f2;
        ReleaseLogUtil.e("Suggestion_TrainDetail", "have download progress: ", Float.valueOf(f2), " --left: ", Float.valueOf(this.ce));
        CourseApi courseApi = this.o;
        if (courseApi == null) {
            LogUtil.h("Suggestion_TrainDetail", "downloadWorkout : mCourseApi is null.");
            return;
        }
        try {
            boolean z = courseApi.getCourseMediaFilesLength(this.ck, this.br.acquireVersion()) == 0;
            this.av = z;
            ReleaseLogUtil.e("Suggestion_TrainDetail", "-----refreshProgressButton----", Boolean.valueOf(z), "The true is down");
        } catch (Exception e2) {
            ReleaseLogUtil.c("Suggestion_TrainDetail", "newWorkout refreshProgressButton", LogAnonymous.b((Throwable) e2));
        }
        m(fitWorkout);
        Intent intent = getIntent();
        if (intent != null) {
            try {
                if (intent.getBooleanExtra("IS_START_DOWNLOAD_WORKOUT", false)) {
                    this.ah.sendMessage(this.ah.obtainMessage(104));
                }
            } catch (BadParcelableException e3) {
                LogUtil.b("Suggestion_TrainDetail", "downloadWorkout BadParcelableException", LogAnonymous.b((Throwable) e3));
            }
        }
    }

    private Map<String, Object> i(FitWorkout fitWorkout) {
        HashMap hashMap = new HashMap(this.cf.d());
        hashMap.put("resourceType", Integer.valueOf(fpq.b(fitWorkout)));
        hashMap.put("entrance", this.cf.a());
        hashMap.put("supportDevice", Integer.valueOf(CourseEquipmentConnectionTipsUtil.c(CourseEquipmentConnectionTipsUtil.d(this.aa, this.cf.i()))));
        return hashMap;
    }

    private void m(FitWorkout fitWorkout) {
        ArrayList<Motion> f2 = f(fitWorkout);
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter != null && !ipresenter.isFreeCourses(fitWorkout.acquireCommodityFlag()) && !CommonUtil.aa(this.j)) {
            LogUtil.a("Suggestion_TrainDetail", "setMotionsRun > is not Connected");
            this.ah.sendEmptyMessage(6);
            return;
        }
        if (isLongVideoCourse() && !this.ao) {
            this.ah.sendEmptyMessage(7);
        }
        if (koq.c(f2)) {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "Child thread conversion data:", Long.valueOf(System.currentTimeMillis()));
            this.ah.sendMessage(Message.obtain(this.ah, 5, f2));
            if (this.cf.ag()) {
                if (this.av) {
                    ReleaseLogUtil.e("Suggestion_TrainDetail", "-----EXCUTE COARSE IMMEDIATE:", Long.valueOf(System.currentTimeMillis()));
                    if (isLongVideoCourse()) {
                        b(true, this.aa.getWorkoutActionProperty(), null);
                        return;
                    } else {
                        an();
                        return;
                    }
                }
                LogUtil.h("Suggestion_TrainDetail", "-----course have not download，pls download first:", Long.valueOf(System.currentTimeMillis()));
                ftd.e().a(this.cf.f());
                return;
            }
            return;
        }
        ReleaseLogUtil.d("Suggestion_TrainDetail", "fitness data error");
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.2
            @Override // java.lang.Runnable
            public void run() {
                TrainDetail.this.c(R.string._2130839508_res_0x7f0207d4, 0);
                TrainDetail.this.showErrorLayout();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cb() {
        try {
            a();
        } catch (SecurityException unused) {
            LogUtil.b("Suggestion_TrainDetail", "SecurityException exception showDetailInfo");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cl() {
        if (this.aa == null) {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "showVipPreview mFitWorkout = null");
            return;
        }
        try {
            if (bg()) {
                if (this.ch == null) {
                    this.ch = new fmq(this, this.bv, this.bm, (FrameLayout) findViewById(R.id.screen_sfv));
                }
                this.ch.b(this);
                this.ch.c(this);
                this.ch.c(this.aa);
                this.ch.c(this.aa.acquireName());
                this.ch.d(new ViewVideoPlayListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.8
                    @Override // com.huawei.health.suggestion.ui.fitness.mvp.ViewVideoPlayListener
                    public void onVideoStartPlay() {
                        if (TrainDetail.this.bg != null) {
                            TrainDetail.this.bg.c();
                        }
                    }

                    @Override // com.huawei.health.suggestion.ui.fitness.mvp.ViewVideoPlayListener
                    public void startCoachPlay() {
                        TrainDetail.this.ad();
                    }
                });
                this.ch.d(false);
                this.bj.judgeVipState();
            }
        } catch (SecurityException unused) {
            LogUtil.b("Suggestion_TrainDetail", "SecurityException exception showDetailInfo");
        }
        bp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cc() {
        azW_(findViewById(R.id.empty_vip_preview), 8);
        azW_(findViewById(R.id.member_preview_header), 8);
        azW_(findViewById(R.id.non_trial_header), 0);
        azW_(this.bk, 8);
        azW_(this.f3100a, 0);
        azW_(this.h, 0);
        FitWorkout fitWorkout = this.aa;
        if (fitWorkout != null && fitWorkout.isLongExplainVideoCourse()) {
            this.f.setVisibility(8);
        } else if (!nsn.p()) {
            azW_(this.f, 0);
        }
        frt frtVar = this.bn;
        if (frtVar != null) {
            frtVar.b(8);
        }
        frt frtVar2 = this.bh;
        if (frtVar2 != null) {
            frtVar2.b(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cf() {
        cv();
        if (this.ac.getCount() > 1) {
            this.ca.setVisibility(0);
        } else {
            this.ca.setVisibility(8);
        }
        this.ac.notifyDataSetChanged();
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter == null || ipresenter.isFreeCourses(this.aa.acquireCommodityFlag())) {
            cn();
        }
        finishLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Motion> list) {
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter == null || ipresenter.isFreeCourses(this.aa.acquireCommodityFlag())) {
            cn();
        }
        finishLoading();
        updateViewController();
        if (this.aa.isNewRunCourse()) {
            LogUtil.a("Suggestion_TrainDetail", "mFitWorkout.isNewRunCourse");
            return;
        }
        if (!isLongVideoCourse()) {
            LogUtil.a("Suggestion_TrainDetail", "updateUi mTabStrip set VISIBLE");
            this.ca.setVisibility(0);
        }
        frp frpVar = this.m;
        if (frpVar != null) {
            frpVar.b(this.aa);
            this.m.c(list);
        }
    }

    private void bh() {
        if (TextUtils.isEmpty(this.br.acquirePlanId())) {
            LogUtil.h("Suggestion_TrainDetail", "acquirePlan action");
        } else {
            LogUtil.a("Suggestion_TrainDetail", "acquirePlan workout actionList");
        }
    }

    private boolean w() {
        WorkoutRecord workoutRecord = this.br;
        if (workoutRecord != null) {
            String acquireWorkoutId = workoutRecord.acquireWorkoutId();
            this.ck = acquireWorkoutId;
            frq frqVar = this.l;
            if (frqVar != null) {
                frqVar.e(acquireWorkoutId);
            }
            if (!TextUtils.isEmpty(this.ck)) {
                return false;
            }
            LogUtil.h("Suggestion_TrainDetail", "mWorkId==null");
            finish();
            return true;
        }
        LogUtil.h("Suggestion_TrainDetail", "mRecord==null");
        return true;
    }

    private ArrayList<Motion> f(FitWorkout fitWorkout) {
        this.aa = fitWorkout;
        o(fitWorkout);
        fpq.a();
        ArrayList<Motion> arrayList = new ArrayList<>(fpq.d(fitWorkout, this.ab));
        this.d.saveMotions(arrayList);
        this.d.saveDuration(this.aa.acquireDuration());
        this.d.setBackgroundMusicUrl(this.aa.getBackgroundMusic());
        this.d.setCalorieStartTime(this.aa.getCalorieStartTime());
        LogUtil.a("Suggestion_TrainDetail", "setBackgroundMusicUrl:", this.aa.getBackgroundMusic());
        return arrayList;
    }

    private void o(FitWorkout fitWorkout) {
        this.br.saveVersion(fitWorkout.accquireVersion());
        if (TextUtils.isEmpty(this.br.acquireWorkoutName())) {
            this.br.saveWorkoutName(StringUtils.c((Object) fitWorkout.acquireName()));
        }
        this.br.saveCalorie(fitWorkout.acquireCalorie());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fmj
            @Override // java.lang.Runnable
            public final void run() {
                TrainDetail.this.h();
            }
        });
    }

    public /* synthetic */ void h() {
        HashMap hashMap = new HashMap();
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_TrainDetail", "doViewTrainDetailBi, getCurrentPlan : planApi is null.");
            return;
        }
        IntPlan currentIntPlan = planApi.getCurrentIntPlan();
        if (fpq.b(currentIntPlan, this.br.acquirePlanId())) {
            hashMap.put("type", Integer.valueOf(ggr.e(currentIntPlan)));
        }
        gge.e("1130006", hashMap);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        if (intent == null) {
            LogUtil.h("Suggestion_TrainDetail", "the intent is null, pls check");
            finish();
            return;
        }
        super.onNewIntent(intent);
        this.cb = 0;
        this.ao = true;
        this.k.b();
        cv();
        this.ca.setVisibility(8);
        fqy fqyVar = new fqy(intent);
        this.cf = fqyVar;
        if (fqyVar.z()) {
            finish();
            return;
        }
        cn();
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter != null) {
            ipresenter.onDestroy();
        }
        this.bj = new frb(this, this.cf);
        this.bp = this.cf.m();
        e eVar = this.u;
        if (eVar != null) {
            eVar.d = true;
        }
        CourseApi courseApi = this.o;
        if (courseApi != null) {
            courseApi.cancelDownloadCourseMediaFiles(new fey.d().e(this.ck).d(this.cf.v()).a());
        }
        fot.a().c();
        bm();
        showLoading();
        bi();
        azW_(findViewById(R.id.non_trial_header), 8);
        azW_(findViewById(R.id.member_preview_header), 8);
        azW_(findViewById(R.id.empty_vip_preview), 8);
        this.q.b();
        RelativeLayout relativeLayout = this.au;
        if (relativeLayout != null && relativeLayout.getVisibility() == 0) {
            this.au.setVisibility(8);
        }
        azV_(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bm() {
        if (this.x != null) {
            bj();
            this.x.b();
            o();
            this.x.setProgress(0);
        }
    }

    private void bi() {
        CustomTitleBar customTitleBar = this.p;
        if (customTitleBar != null) {
            customTitleBar.setTitleText("");
            this.p.setRightButtonVisibility(8);
            this.p.setRightSoftkeyVisibility(8);
            this.p.setRightThirdKeyVisibility(8);
            this.p.setRightFourKeyVisibility(8);
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.ap = true;
        frq frqVar = this.l;
        if (frqVar != null) {
            frqVar.b();
        }
        if (u()) {
            cu();
            bl();
        }
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter != null) {
            ipresenter.onResume();
        }
    }

    private void cu() {
        WorkoutRecord workoutRecord = this.br;
        if (workoutRecord != null) {
            CourseApi courseApi = this.o;
            if (courseApi == null) {
                LogUtil.h("Suggestion_TrainDetail", "updateWorkoutDownloadTag: mCourseApi is null.");
                return;
            }
            boolean z = courseApi.getCourseMediaFilesLength(this.ck, workoutRecord.acquireVersion()) == 0;
            this.av = z;
            ReleaseLogUtil.e("Suggestion_TrainDetail", "refreshProgressButton", Boolean.valueOf(z), "true is down");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.ap = false;
        this.aq = false;
        LogUtil.a("Suggestion_TrainDetail", "TrainDetailOnStop");
    }

    private boolean u() {
        return this.ar && (ffy.c(this.aa, this.br, this.x, this.f) && this.x.getVisibility() != 0);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void updateViewController() {
        bl();
        frp frpVar = this.m;
        if (frpVar != null) {
            frpVar.a();
        }
        frq frqVar = this.l;
        if (frqVar != null) {
            frqVar.d();
        }
        this.ar = true;
        LogUtil.a("Suggestion_TrainDetail", "initData:", Long.valueOf(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bl() {
        if (this.av) {
            if (SportSupportUtil.a()) {
                this.f.setText(al());
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
        } else {
            if (SportSupportUtil.a()) {
                this.f.setText(aq());
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            if (!CommonUtil.aa(getApplicationContext())) {
                c(R$string.IDS_motiontrack_offlinemap_connectting_error, 0);
            }
        }
        e(getResources().getString(R.string._2130848439_res_0x7f022ab7));
        if (nsn.p()) {
            this.f.setVisibility(8);
        }
        cd();
    }

    private void cd() {
        if (isLongVideoCourse()) {
            if (TextUtils.isEmpty(al())) {
                this.f.setText(aq());
            } else {
                this.f.setText(al());
            }
            FitWorkout fitWorkout = this.aa;
            if (fitWorkout == null || !fitWorkout.isLongExplainVideoCourse()) {
                return;
            }
            this.f.setVisibility(8);
        }
    }

    public void m() {
        LogUtil.a("Suggestion_TrainDetail", "showSendResultDialog()");
        View inflate = ((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.sug_activity_push_fail, (ViewGroup) null);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.d(R.string._2130848491_res_0x7f022aeb).czg_(inflate).cze_(com.huawei.ui.commonui.R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ck() {
        if (ffy.c(this.d)) {
            if (koq.b(this.d.acquireMotions())) {
                ReleaseLogUtil.e("Suggestion_TrainDetail", "startCoachActivity mCoachData.acquireMotions() empty");
                c(R.string._2130848740_res_0x7f022be4, 0);
                return;
            }
            ReleaseLogUtil.e("Suggestion_TrainDetail", "startCoachActivity");
            mmp buildCommonParameter = CourseControlManager.buildCommonParameter(this.cf, this.br.acquirePlanId(), this.k.c());
            buildCommonParameter.d(this.r != this.bp.size() - 1);
            CourseControlManager.startCoachActivity(this.j, this.aa, buildCommonParameter, this.as);
            ch();
            return;
        }
        ReleaseLogUtil.d("Suggestion_TrainDetail", "startCoachActivity mCoachData is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void co() {
        if (ffy.c(this.d)) {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "startLongCoachActivity is screen recording = ", Integer.valueOf(this.aa.getAntiScreenRecording()));
            mmp buildCommonParameter = CourseControlManager.buildCommonParameter(this.cf, this.br.acquirePlanId(), this.k.c());
            buildCommonParameter.i(this.ba);
            buildCommonParameter.m(this.bc);
            buildCommonParameter.d(this.r != this.bp.size() - 1);
            CourseControlManager.startLongCoachActivity(this.j, this.aa, buildCommonParameter);
            ch();
            return;
        }
        LogUtil.h("Suggestion_TrainDetail", "startLongCoachActivity mCoachData is null");
    }

    private void ch() {
        if (this.o == null) {
            LogUtil.h("Suggestion_TrainDetail", "startCourseAfterCheck: mCourseApi is null.");
            return;
        }
        if (this.r == this.bp.size() - 1) {
            finish();
        }
        this.r++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        if (this.o == null) {
            LogUtil.h("Suggestion_TrainDetail", "recordTrainEvent: mCourseApi is null.");
            return;
        }
        if (this.aa == null || this.cf == null) {
            LogUtil.h("Suggestion_TrainDetail", "recordTrainEvent: mFitWorkout or mTrainDetailIntentData is null.");
            return;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveWorkoutId(this.aa.acquireId());
        workoutRecord.saveVersion(this.aa.accquireVersion());
        workoutRecord.saveCourseBelongType(this.cf.c());
        workoutRecord.saveWorkoutPackageId(this.cf.x());
        if (i2 == 0) {
            this.o.postUserCourse(workoutRecord, 0);
            return;
        }
        if (!Utils.o()) {
            this.o.postUserCourse(workoutRecord, 4);
        }
        if (this.aa.isLongExplainVideoCourse()) {
            this.o.postUserCourse(workoutRecord, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: cj, reason: merged with bridge method [inline-methods] */
    public void g() {
        ReleaseLogUtil.e("Suggestion_TrainDetail", "startOrDownload enter");
        if (j()) {
            bk();
        } else if (isLongVideoCourse()) {
            b(true, this.aa.getWorkoutActionProperty(), null);
        } else {
            an();
        }
    }

    private void bk() {
        SportServiceApi sportServiceApi = (SportServiceApi) Services.a("CoursePlanService", SportServiceApi.class);
        if (sportServiceApi != null) {
            sportServiceApi.setLastUseOrWatchCourse("last_use_run_course", System.currentTimeMillis(), this.aa);
        }
        LogUtil.a("Suggestion_TrainDetail", "startOrDownload mIsRunWorkout():", "showDeviceDialog(CONNECT_OR_BINDING_TYPE)");
        if (f() && !EnvironmentInfo.k()) {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "startOrDownload is heartRate workout");
            this.k.d(new IBaseResponseCallback() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.10
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if ((obj instanceof Integer) && ((Integer) obj).intValue() == 0) {
                        TrainDetail.this.p();
                    }
                }
            });
        } else {
            ReleaseLogUtil.d("Suggestion_TrainDetail", "not treadmill or heartRate workout");
            an();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        ReleaseLogUtil.e("Suggestion_TrainDetail", "downLoadCourseMedias is downloaded ", Boolean.valueOf(this.av));
        if (this.aa == null) {
            LogUtil.h("Suggestion_TrainDetail", "downLoadCourseMedias() mFitWorkout is null");
            return;
        }
        if (this.av) {
            ggr.a();
            if (j()) {
                t();
                return;
            } else {
                q();
                return;
            }
        }
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            c(R.string._2130841884_res_0x7f02111c, 0);
            return;
        }
        if (this.c.getVisibility() == 0) {
            LogUtil.a("Suggestion_TrainDetail", "AI Courses: ", Boolean.valueOf(this.ag));
            this.c.setVisibility(8);
            this.x.setVisibility(0);
        }
        FitWorkout fitWorkout = this.aa;
        ggr.c(fitWorkout, i(fitWorkout), this.ag);
        fnq.b(this.aa);
        cr();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        LogUtil.a("Suggestion_TrainDetail", "downLoadLongSrt start");
        if (new File(squ.k(this.bc)).exists()) {
            s();
            return;
        }
        if (this.o == null) {
            LogUtil.h("Suggestion_TrainDetail", "downLoadLongSrt: mCourseApi is null.");
        } else if (!CommonUtil.aa(BaseApplication.getContext())) {
            c(R.string._2130841884_res_0x7f02111c, 0);
        } else {
            e(getResources().getString(R.string._2130848445_res_0x7f022abd));
            this.o.downloadSubtitles(this.bc, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    LogUtil.a("Suggestion_TrainDetail", "downLoadLongSrt, onFailure. errorCode = ", Integer.valueOf(i2), ", errorInfo = ", str);
                    TrainDetail trainDetail = TrainDetail.this;
                    trainDetail.e(trainDetail.getResources().getString(R.string._2130848439_res_0x7f022ab7));
                    TrainDetail.this.s();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    LogUtil.a("Suggestion_TrainDetail", "downLoadLongSrt, onSuccess. ");
                    TrainDetail trainDetail = TrainDetail.this;
                    trainDetail.e(trainDetail.getResources().getString(R.string._2130848439_res_0x7f022ab7));
                    TrainDetail.this.s();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cp() {
        int i2;
        ReleaseLogUtil.e("Suggestion_TrainDetail", "startTrackActivity");
        if (this.aa == null) {
            LogUtil.b("Suggestion_TrainDetail", "mFitWorkout is null.");
            return;
        }
        WorkoutRecord workoutRecord = this.br;
        if (workoutRecord != null) {
            LogUtil.a("Suggestion_TrainDetail", "planId:", workoutRecord.acquirePlanId());
            this.bd = this.br.acquirePlanId();
            i2 = this.br.acquireWorkoutOrder();
        } else {
            i2 = 1;
        }
        mmp buildCommonParameter = CourseControlManager.buildCommonParameter(this.cf, this.bd, this.k.c());
        buildCommonParameter.i(i2);
        CourseControlManager.startTrackActivity(this.j, this.aa, buildCommonParameter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cr() {
        if (!NetworkUtil.m()) {
            ae();
        } else {
            cm();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.k.e()) {
            return;
        }
        an();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.k.d(105);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.k.d(106);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        this.k.a(110);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        this.k.d(111);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bx() {
        new CustomTextAlertDialog.Builder(this).b(this.j.getString(R.string._2130848356_res_0x7f022a64)).e(this.j.getString(R.string.IDS_FitnessAdvice_remind_user_to_stop_sport_in_device)).cyU_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cg() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.j);
        builder.e(R$string.IDS_hwh_motiontrack_ingnore_link_attention);
        builder.czC_(com.huawei.ui.commonui.R$string.IDS_apphelp_pwindows_continue_button, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_TrainDetail", "ignore link and continue");
                if (TrainDetail.this.j()) {
                    TrainDetail.this.ah.sendEmptyMessage(110);
                } else if (TrainDetail.this.isLongVideoCourse()) {
                    TrainDetail.this.ah.sendEmptyMessage(106);
                } else if (!TrainDetail.this.ag || TrainDetail.this.aa.getIsAi() != 1) {
                    TrainDetail.this.ah.sendEmptyMessage(105);
                } else {
                    LogUtil.a("Suggestion_TrainDetail", "is ai interactivetraining");
                    TrainDetail.this.ah.sendEmptyMessage(111);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(com.huawei.ui.commonui.R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_TrainDetail", "ignore link and cancel");
                TrainDetail.this.o();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    private void ae() {
        if (CommonUtil.aa(arx.b())) {
            ReleaseLogUtil.e("Suggestion_TrainDetail", "doInWifi download in mobile network");
            if (this.o == null) {
                LogUtil.h("Suggestion_TrainDetail", "doInWifi: mCourseApi is null.");
                return;
            }
            HealthDownLoadWidget healthDownLoadWidget = this.x;
            if (healthDownLoadWidget == null) {
                LogUtil.h("Suggestion_TrainDetail", "doInWifi: mDownloadWidget is null.");
                return;
            }
            Object tag = healthDownLoadWidget.getTag();
            if ((tag instanceof Boolean) && !((Boolean) tag).booleanValue()) {
                LogUtil.a("Suggestion_TrainDetail", "Only one dialog is allowed.");
                return;
            }
            int round = Math.round(this.o.getCourseMediaFilesLength(this.ck, this.br.acquireVersion()) / 1048576.0f);
            String valueOf = String.valueOf(round);
            if (round <= 0) {
                round = 1;
                valueOf = getString(R.string._2130848724_res_0x7f022bd4, new Object[]{UnitUtil.e(1, 1, 0)});
            }
            String d2 = ffy.d(this, R.string._2130848488_res_0x7f022ae8, ffy.b(R.plurals._2130903484_res_0x7f0301bc, round, valueOf));
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
            builder.e(d2).czC_(R.string._2130848403_res_0x7f022a93, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TrainDetail.this.ag();
                    TrainDetail.this.cm();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TrainDetail.this.ag();
                    int modelType = mwt.d().getModelType();
                    if (TrainDetail.this.aa.getIsAi() == 1 && modelType != -1) {
                        LogUtil.a("Suggestion_TrainDetail", "doInWifi AI Courses: ", Integer.valueOf(TrainDetail.this.aa.getIsAi()));
                        TrainDetail.this.c.setVisibility(0);
                        TrainDetail.this.x.setVisibility(8);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            ag();
            NoTitleCustomAlertDialog e2 = builder.e();
            this.cj = e2;
            e2.setCanceledOnTouchOutside(false);
            if (isFinishing()) {
                return;
            }
            this.cj.show();
            return;
        }
        ReleaseLogUtil.d("Suggestion_TrainDetail", "doInWifi not connect");
        ag();
        cm();
    }

    private void y() {
        float f2 = this.w;
        if (f2 > 0.0f) {
            this.x.setProgress((int) (f2 * 100.0f));
            this.x.c(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cm() {
        if (this.o == null) {
            LogUtil.h("Suggestion_TrainDetail", "toDownload: mCourseApi is null.");
            return;
        }
        if ((this.x.getTag() instanceof Boolean) && ((Boolean) this.x.getTag()).booleanValue()) {
            this.be = 0;
            this.x.setTag(false);
            LogUtil.a("Suggestion_TrainDetail", "toDownload:", Integer.valueOf(this.x.getVisibility()), " ", Integer.valueOf(this.v.getVisibility()), " ", Integer.valueOf(this.c.getVisibility()), " ", Integer.valueOf(this.n.getVisibility()));
            azW_(this.v, 0);
            azW_(this.x, 0);
            azW_(this.c, 8);
            azW_(this.n, 8);
            e(getResources().getString(R.string._2130848445_res_0x7f022abd));
            y();
            this.u = new e(this);
            this.o.downloadCourseMediaFiles(new fey.d().e(this.ck).c(this.aa.getTimbre()).d(this.br.acquireVersion()).d(this.cf.v()).a(), this.u);
        }
    }

    /* loaded from: classes4.dex */
    static class e extends UiCallback<String> {
        private WeakReference<TrainDetail> b;
        private boolean d = false;

        e(TrainDetail trainDetail) {
            this.b = null;
            this.b = new WeakReference<>(trainDetail);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_TrainDetail", "down fail-- errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
            TrainDetail trainDetail = this.b.get();
            if (trainDetail == null) {
                LogUtil.h("Suggestion_TrainDetail", "DownloadUiCallback onFailure null mTrainDetail ");
                return;
            }
            trainDetail.o();
            if (!trainDetail.isFinishing() && ((trainDetail.cj == null || !trainDetail.cj.isShowing()) && (trainDetail.az == null || !trainDetail.az.isShowing()))) {
                trainDetail.d(str);
            }
            trainDetail.w = trainDetail.x.getProgress() / 100.0f;
            trainDetail.ce = 1.0f - trainDetail.w;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onProgress(long j, long j2) {
            TrainDetail trainDetail = this.b.get();
            if (trainDetail != null) {
                trainDetail.e(trainDetail.getResources().getString(R.string._2130848445_res_0x7f022abd));
                float f = ((trainDetail.ce * (j / j2)) + trainDetail.w) - 0.001f;
                if (f < 0.0f) {
                    f = 1.0E-5f;
                }
                if (f < 1.0f || f == 1.0f) {
                    f *= 100.0f;
                }
                int i = (int) f;
                trainDetail.bq = trainDetail.x.getProgress();
                if (trainDetail.bq != i && !trainDetail.am) {
                    trainDetail.am = true;
                    i = trainDetail.bq;
                    trainDetail.be = i;
                }
                int abs = Math.abs(i - trainDetail.be);
                trainDetail.be = i;
                trainDetail.x.c(abs);
                return;
            }
            LogUtil.h("Suggestion_TrainDetail", "DownloadUiCallback onProgress null mTrainDetail ");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public boolean isCanceled() {
            if (this.b.get() == null) {
                ReleaseLogUtil.d("Suggestion_TrainDetail", "DownloadUiCallback isCanceled trainDetail == null ");
                return true;
            }
            return this.d;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            LogUtil.c("Suggestion_TrainDetail", "down success--");
            TrainDetail trainDetail = this.b.get();
            if (trainDetail != null) {
                if (trainDetail.o != null) {
                    LogUtil.a("Suggestion_TrainDetail", "DownloadUiCallback mIsForeGround ", Boolean.valueOf(trainDetail.ap));
                    if (trainDetail.ap) {
                        if (trainDetail.j()) {
                            trainDetail.t();
                        } else {
                            if (trainDetail.ag) {
                                trainDetail.v();
                            } else {
                                trainDetail.q();
                            }
                            LogUtil.a("Suggestion_TrainDetail", "onSuccess selectClick: ", Boolean.valueOf(trainDetail.ag));
                        }
                    } else {
                        trainDetail.o();
                    }
                    trainDetail.bq = 0;
                    trainDetail.w = 0.0f;
                    trainDetail.av = true;
                    trainDetail.x.setTag(true);
                    trainDetail.a(0);
                    fot.a().a(trainDetail.aa);
                    trainDetail.bl();
                    return;
                }
                LogUtil.h("Suggestion_TrainDetail", "DownloadUiCallback, onSuccess: mCourseApi is null.");
                return;
            }
            ReleaseLogUtil.d("Suggestion_TrainDetail", "DownloadUiCallback onSuccess trainDetail == null ");
        }
    }

    public void o() {
        this.x.setTag(true);
        this.x.d();
        e(getResources().getString(R.string._2130848439_res_0x7f022ab7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        this.x.setIdleText(str.toUpperCase(Locale.getDefault()));
    }

    private void bj() {
        float f2 = this.bq / 100.0f;
        this.w = f2;
        if (this.av) {
            this.w = 1.0f;
        } else if (f2 > 0.001f) {
            LogUtil.a("Suggestion_TrainDetail", "save download progress: ", Float.valueOf(f2));
            this.ce = 1.0f - this.w;
        }
        mod.d(this.ck, this.w);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (isFinishing()) {
            return;
        }
        String string = getResources().getString(R.string._2130848405_res_0x7f022a95);
        String string2 = getResources().getString(R.string._2130848404_res_0x7f022a94);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.j);
        builder.e(string2).czE_(string, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrainDetail.this.ag();
                if (!TrainDetail.this.isLongVideoCourse()) {
                    TrainDetail.this.cr();
                } else {
                    TrainDetail trainDetail = TrainDetail.this;
                    trainDetail.b(true, trainDetail.aa.getWorkoutActionProperty(), null);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrainDetail.this.ag();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ag();
        NoTitleCustomAlertDialog e2 = builder.e();
        this.cj = e2;
        e2.setCanceledOnTouchOutside(false);
        this.cj.show();
    }

    public void e() {
        WorkoutRecord workoutRecord = this.br;
        if (workoutRecord == null) {
            LogUtil.h("Suggestion_TrainDetail", "collect() mRecord is null.");
            return;
        }
        if (this.o == null) {
            LogUtil.h("Suggestion_TrainDetail", "collect() mCourseApi is null.");
            return;
        }
        String acquireWorkoutId = workoutRecord.acquireWorkoutId();
        if (this.an) {
            this.o.uncollectCourse(acquireWorkoutId);
            this.p.setRightSoftkeyBackground(getDrawable(R.drawable._2131429872_res_0x7f0b09f0), nsf.h(R.string._2130848479_res_0x7f022adf));
            this.an = false;
            c(R.string._2130848479_res_0x7f022adf, 1);
            gge.b("1130023", 2, acquireWorkoutId);
            return;
        }
        this.o.collectCourse(acquireWorkoutId, this.br.acquireVersion());
        this.p.setRightSoftkeyBackground(getDrawable(R.drawable._2131429873_res_0x7f0b09f1), nsf.h(R.string._2130847793_res_0x7f022831));
        this.an = true;
        c(R.string._2130848725_res_0x7f022bd5, 1);
        gge.b("1130023", 1, acquireWorkoutId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, int i3) {
        Toast.makeText(BaseApplication.getContext(), i2, i3).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ac() {
        String b2 = ash.b("COLLECT_FIST_KEY");
        boolean isFinishing = isFinishing();
        LogUtil.c("Suggestion_TrainDetail", "collectFist isFinishing = ", Boolean.valueOf(isFinishing));
        if ("1".equals(b2) || isFinishing) {
            return;
        }
        PopupWindow popupWindow = new PopupWindow(View.inflate(this.j, R.layout.sug_fitness_collecte_toast, null), -2, -2, true);
        this.bl = popupWindow;
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        View contentView = this.bl.getContentView();
        contentView.measure(0, 0);
        PopupWindow popupWindow2 = this.bl;
        CustomTitleBar customTitleBar = this.p;
        popupWindow2.showAsDropDown(customTitleBar, (customTitleBar.getWidth() - contentView.getMeasuredWidth()) - ((Integer) getSafeRegionWidth().second).intValue(), 0);
        ash.a("COLLECT_FIST_KEY", "1");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.ll_fitness_getdata_error) {
            CommonUtil.q(this);
        } else {
            LogUtil.h("Suggestion_TrainDetail", "onClick not find this id");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.cj;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        NewViewDetailInfo newViewDetailInfo = this.bg;
        if (newViewDetailInfo != null) {
            newViewDetailInfo.a();
        }
        e eVar = this.u;
        if (eVar != null) {
            eVar.d = true;
        }
        PopupWindow popupWindow = this.bl;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.bl = null;
        }
        CustomAlertDialog customAlertDialog = this.bi;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
            this.bi = null;
        }
        fmq fmqVar = this.ch;
        if (fmqVar != null) {
            fmqVar.c();
        }
        if (CourseEquipmentConnectionTipsUtil.d(this.aa, this.cf.i()) != 0) {
            JumpConnectHelper.c().i();
        }
        unregisterReceiver(this.cl);
        e(this.j);
        CourseApi courseApi = this.o;
        if (courseApi != null) {
            courseApi.cancelDownloadCourseMediaFiles(new fey.d().e(this.ck).d(this.cf.v()).a());
        }
        fot.a().c();
        bj();
        Handler handler = this.ah;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        ggr.d(this.cf.a(), System.currentTimeMillis() - this.bu);
        if (this.ad > 0) {
            ((DistributedApi) Services.c("DistributedService", DistributedApi.class)).unregister(this.ad);
        }
        TrainDetailContract.Ipresenter ipresenter = this.bj;
        if (ipresenter != null) {
            ipresenter.onDestroy();
        }
        ((DistributedApi) Services.c("DistributedService", DistributedApi.class)).releaseDeviceManager();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        fmq fmqVar = this.ch;
        if (fmqVar != null && fmqVar.b()) {
            this.ch.d();
            return;
        }
        frq frqVar = this.l;
        if (frqVar == null || frqVar.e()) {
            return;
        }
        if (this.cf.aa()) {
            bv();
        } else {
            k();
        }
    }

    private void k() {
        fqy fqyVar = this.cf;
        if (fqyVar != null && fqyVar.ad()) {
            moveTaskToBack(true);
        }
        RelativeLayout relativeLayout = this.cc;
        njn.cvi_(relativeLayout.getChildAt(relativeLayout.getChildCount() - 1), new IBaseResponseCallback() { // from class: fmc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                TrainDetail.this.e(i2, obj);
            }
        });
    }

    public /* synthetic */ void e(int i2, Object obj) {
        if (i2 == 0) {
            super.onBackPressed();
        }
    }

    /* loaded from: classes4.dex */
    public class WifiReceiver extends BroadcastReceiver {
        public WifiReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (TrainDetail.this.x == null) {
                return;
            }
            if (!TrainDetail.this.al && !TrainDetail.this.av && CommonUtil.aa(BaseApplication.getContext())) {
                TrainDetail.this.mLoadingView.setVisibility(8);
                TrainDetail.this.z.setVisibility(8);
                if (TrainDetail.this.m != null) {
                    TrainDetail.this.m.c(0);
                }
                if (TrainDetail.this.q != null) {
                    TrainDetail.this.q.d(0);
                }
                if (!ffy.c(TrainDetail.this.aa)) {
                    LogUtil.a("Suggestion_TrainDetail", "onReceive");
                    TrainDetail.this.showLoading();
                    TrainDetail.this.initData();
                }
            }
            TrainDetail.this.al = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bv() {
        WorkoutRecord workoutRecord;
        View inflate = ((LayoutInflater) this.j.getSystemService("layout_inflater")).inflate(R.layout.sug_fitness_after_run_dialog, (ViewGroup) null);
        final HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.sug_fitness_not_remind_box);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.sug_fitness_sketch_tip);
        if (this.aa == null && ((workoutRecord = this.br) == null || TextUtils.isEmpty(workoutRecord.acquireWorkoutName()))) {
            fis.d().e();
            finish();
            return;
        }
        WorkoutRecord workoutRecord2 = this.br;
        if (workoutRecord2 == null || TextUtils.isEmpty(workoutRecord2.acquireWorkoutName())) {
            healthTextView.setText(String.format(getString(R.string._2130848473_res_0x7f022ad9), this.aa.acquireName()));
        } else {
            healthTextView.setText(String.format(getString(R.string._2130848473_res_0x7f022ad9), this.br.acquireWorkoutName()));
        }
        CustomViewDialog e2 = new CustomViewDialog.Builder(this.j).a(getString(R.string._2130848356_res_0x7f022a64)).czg_(inflate).czf_(getString(R.string._2130848474_res_0x7f022ada).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: fmn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAk_(healthCheckBox, view);
            }
        }).czd_(getString(R$string.IDS_plugin_fitnessadvice_cancal).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: fmo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrainDetail.this.aAl_(view);
            }
        }).e();
        this.bw = e2;
        e2.show();
    }

    public /* synthetic */ void aAk_(HealthCheckBox healthCheckBox, View view) {
        if (healthCheckBox.isChecked()) {
            LogUtil.a("Suggestion_TrainDetail", "not remind:", Boolean.valueOf(healthCheckBox.isChecked()));
            SharedPreferenceManager.e(this.j, Integer.toString(20002), "show_sketch_after_track", "false", new StorageParams());
        }
        fis.d().e();
        finish();
        this.bw.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aAl_(View view) {
        this.bw.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        frq frqVar = this.l;
        if (frqVar != null) {
            frqVar.aFe_(configuration);
        }
    }

    /* loaded from: classes4.dex */
    static class g implements Runnable {
        WeakReference<TrainDetail> e;

        /* synthetic */ g(TrainDetail trainDetail, AnonymousClass1 anonymousClass1) {
            this(trainDetail);
        }

        private g(TrainDetail trainDetail) {
            this.e = new WeakReference<>(trainDetail);
        }

        @Override // java.lang.Runnable
        public void run() {
            TrainDetail trainDetail = this.e.get();
            if (trainDetail != null) {
                trainDetail.ar();
            } else {
                LogUtil.h("Suggestion_TrainDetail", "RequestWorkoutTask activity is null");
            }
        }
    }

    /* loaded from: classes4.dex */
    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<TrainDetail> f3113a;
        private FitWorkout d;

        /* synthetic */ c(TrainDetail trainDetail, FitWorkout fitWorkout, AnonymousClass1 anonymousClass1) {
            this(trainDetail, fitWorkout);
        }

        private c(TrainDetail trainDetail, FitWorkout fitWorkout) {
            this.f3113a = new WeakReference<>(trainDetail);
            this.d = fitWorkout;
        }

        @Override // java.lang.Runnable
        public void run() {
            TrainDetail trainDetail = this.f3113a.get();
            if (trainDetail != null) {
                trainDetail.b(this.d);
            } else {
                LogUtil.h("Suggestion_TrainDetail", "DownloadWorkoutTask activity is null");
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.JumpConnectHelper.JumpActivityHandleInterface
    public void startLongCoachAfter() {
        CourseControlManager.startLongCoachAfterAction(this.aa, CourseControlManager.buildCommonParameter(this.cf, this.bd, this.k.c()));
        ch();
        JumpConnectHelper.c().b().b("Suggestion_TrainDetail");
        JumpConnectHelper.c().i();
    }

    @Override // com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil.DevicesConnectDialogCallback
    public void devicesConnectDialogNavigation() {
        ReleaseLogUtil.e("Suggestion_TrainDetail", "devicesConnectDialogNavigation");
        if (isLongVideoCourse()) {
            co();
        } else {
            ck();
        }
    }

    @Override // com.huawei.health.suggestion.util.CourseEquipmentConnectionTipsUtil.DevicesConnectDialogCallback
    public void devicesConnectDialogPositive() {
        ReleaseLogUtil.e("Suggestion_TrainDetail", "devicesConnectDialogPositive");
        mmp buildCommonParameter = CourseControlManager.buildCommonParameter(this.cf, this.br.acquirePlanId(), this.k.c());
        buildCommonParameter.i(this.ba);
        buildCommonParameter.m(this.bc);
        CourseControlManager.devicesConnectDialogPositive(this.j, this.aa, buildCommonParameter, this.bp);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void changeVipState(boolean z) {
        this.at = z;
        fmq fmqVar = this.ch;
        if (fmqVar != null) {
            fmqVar.j();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void changBuyState(boolean z) {
        this.ak = z;
        fmq fmqVar = this.ch;
        if (fmqVar != null) {
            fmqVar.j();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview
    public void bindProductCourseSummary(ProductSummary productSummary, ProductSummary productSummary2, TradeViewInfo tradeViewInfo) {
        this.e.a(productSummary);
        this.e.e(productSummary2);
        this.e.d(tradeViewInfo);
        fmq fmqVar = this.ch;
        if (fmqVar != null) {
            fmqVar.j();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.VipViewShowProvider
    public fpy getAuditionCourseData() {
        this.e.a(this.at);
        this.e.b(this.ak);
        this.e.d(fpq.b(this.aa));
        return this.e;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Iview, com.huawei.health.suggestion.ui.fitness.mvp.VipViewShowProvider
    public void notifyActivityResult(int i2, int i3, Intent intent) {
        LogUtil.a("Suggestion_TrainDetail", "notifyActivityResult requestCode", Integer.valueOf(i2), "resultCode:", Integer.valueOf(i3));
        if (this.ch != null && i3 == 0 && i2 == 100001) {
            this.e.b(true);
            this.ch.h();
        }
    }

    /* loaded from: classes4.dex */
    public class NetStateChangeReceiver extends BroadcastReceiver {
        public NetStateChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (NetworkUtil.m()) {
                return;
            }
            TrainDetail.this.ah.sendMessage(TrainDetail.this.ah.obtainMessage(113));
        }
    }

    private void a(Context context) {
        context.registerReceiver(this.bb, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    private void e(Context context) {
        NetStateChangeReceiver netStateChangeReceiver = this.bb;
        if (netStateChangeReceiver != null) {
            context.unregisterReceiver(netStateChangeReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        if (!this.aq) {
            LogUtil.a("Suggestion_TrainDetail", "not start download");
            return;
        }
        if (this.o != null) {
            LogUtil.a("Suggestion_TrainDetail", "cancel download");
            this.o.cancelDownloadCourseMediaFiles(new fey.d().e(this.ck).d(this.cf.v()).a());
        }
        if (bg()) {
            LogUtil.a("Suggestion_TrainDetail", "cancel stopPlayVideo");
            this.ch.f();
        }
        if (!CommonUtil.aa(this)) {
            bz();
        } else {
            ce();
        }
    }

    private void ce() {
        String replace;
        ReleaseLogUtil.e("Suggestion_TrainDetail", "showNetStateDownloadDialog enter");
        if (bg()) {
            replace = BaseApplication.getContext().getResources().getString(R.string._2130848734_res_0x7f022bde);
        } else {
            replace = ffy.d(this, R.string._2130848488_res_0x7f022ae8, "").replace(" ", "");
        }
        int i2 = bg() ? R.string._2130845280_res_0x7f021e60 : R.string._2130848403_res_0x7f022a93;
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(replace).czC_(i2, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrainDetail.this.ah();
                if (TrainDetail.this.bg()) {
                    TrainDetail.this.cl();
                } else {
                    TrainDetail.this.cm();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrainDetail.this.ah();
                if (TrainDetail.this.o != null) {
                    LogUtil.a("Suggestion_TrainDetail", "cancel download");
                    TrainDetail.this.o.cancelDownloadCourseMediaFiles(new fey.d().e(TrainDetail.this.ck).d(TrainDetail.this.cf.v()).a());
                    TrainDetail.this.bm();
                }
                if (TrainDetail.this.bg()) {
                    LogUtil.a("Suggestion_TrainDetail", "cancel stopPlayVideo");
                    TrainDetail.this.ch.f();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ah();
        NoTitleCustomAlertDialog e2 = builder.e();
        this.az = e2;
        e2.setCanceledOnTouchOutside(false);
        if (isFinishing()) {
            return;
        }
        this.az.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.az;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
        }
    }

    private void bz() {
        if (isFinishing()) {
            return;
        }
        String string = getResources().getString(R$string.IDS_hwh_home_healthshop_setting_net_work);
        String string2 = getResources().getString(R.string._2130839508_res_0x7f0207d4);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.j);
        builder.e(string2).czE_(string, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrainDetail.this.ah();
                TrainDetail.this.startActivity(new Intent("android.settings.SETTINGS"));
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.TrainDetail.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrainDetail.this.ah();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ah();
        NoTitleCustomAlertDialog e2 = builder.e();
        this.az = e2;
        e2.setCanceledOnTouchOutside(false);
        this.az.show();
    }
}
