package com.huawei.ui.main.stories.userprofile.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.h5pro.preload.H5PreloadCountStrategy;
import com.huawei.health.h5pro.preload.H5PreloadIntervalStrategy;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.MemberMessage;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.preload.H5ProPkgPreloadSyncTask;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.AssetMessage;
import com.huawei.trade.datatype.DeviceBenefitQueryParam;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.recycleview.RecyclerItemDecoration;
import com.huawei.ui.commonui.scrollview.HealthBottomView;
import com.huawei.ui.commonui.scrollview.HealthNestedScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import com.huawei.ui.main.stories.userprofile.scroll.CustomHeadView;
import com.huawei.ui.main.stories.utils.UserInfoMockInteractor;
import com.huawei.up.api.UpApi;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.cfi;
import defpackage.efb;
import defpackage.eil;
import defpackage.glz;
import defpackage.gpn;
import defpackage.ixj;
import defpackage.ixx;
import defpackage.izx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.kyd;
import defpackage.mct;
import defpackage.mer;
import defpackage.mfg;
import defpackage.mlc;
import defpackage.mte;
import defpackage.mtl;
import defpackage.njn;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nrs;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.pqm;
import defpackage.rzo;
import defpackage.rzs;
import defpackage.rzt;
import defpackage.rzv;
import defpackage.rzw;
import defpackage.sae;
import defpackage.sah;
import defpackage.sao;
import defpackage.sas;
import defpackage.sat;
import defpackage.say;
import defpackage.sbb;
import defpackage.sbc;
import defpackage.sbd;
import defpackage.sbg;
import defpackage.sbk;
import defpackage.sch;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Marker;

/* loaded from: classes7.dex */
public class PersonalCenterFragment extends BaseFragment implements View.OnClickListener, PersonalCenterUiApi {
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private boolean f10524a;
    private LocalBroadcastManager aa;
    private MessageCenterApi ab;
    private CopyOnWriteArrayList<MessageObject> ac;
    private MessageObserver ad;
    private RelativeLayout ae;
    private ImageView af;
    private say ag;
    private sat ah;
    private HealthTextView ai;
    private sbb aj;
    private List<rzs> ak;
    private final Observer al;
    private OperationUtilsApi am;
    private sbg an;
    private HealthNestedScrollView ao;
    private PersonalCenterRecyclerViewAdapter ap;
    private HealthRecycleView aq;
    private View ar;
    private final UserProfileMgrApi as;
    private sbk at;
    private ExecutorService au;
    private final BroadcastReceiver av;
    private UserInfoMockInteractor ax;
    private CopyOnWriteArrayList<MessageObject> c;
    CommonCallback d;
    private boolean e;
    private Map<Integer, Integer> f;
    private HealthBottomView g;
    private HealthRecycleView h;
    private Context i;
    private int j;
    private PersonalGridAdapter k;
    private rzw l;
    private String m;
    private CustomHeadView n;
    private List<rzv> o;
    private boolean p;
    private int q;
    private GridLayoutManager r;
    private boolean s;
    private Handler t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private sbd z;

    public static /* synthetic */ void a(int i, Object obj) {
    }

    static class c extends BaseHandler<PersonalCenterFragment> {
        c(PersonalCenterFragment personalCenterFragment) {
            super(personalCenterFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dUx_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PersonalCenterFragment personalCenterFragment, Message message) {
            if (personalCenterFragment == null || message == null) {
                LogUtil.h("UIME_PersonalCenterFragment", "handleMessageWhenReferenceNotNull fragment or msg is null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                if (message.obj instanceof UserInfomation) {
                    personalCenterFragment.b((UserInfomation) message.obj);
                }
            } else {
                if (i == 1) {
                    LogUtil.a("UIME_PersonalCenterFragment", "MSG_GET_USERINFO_FAIL");
                    return;
                }
                if (i == 4) {
                    if (message.obj instanceof HiUserInfo) {
                        personalCenterFragment.d((HiUserInfo) message.obj);
                    }
                } else if (i != 80) {
                    PersonalCenterFragment.dUq_(personalCenterFragment, message);
                } else {
                    personalCenterFragment.d(message.obj);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dUq_(PersonalCenterFragment personalCenterFragment, Message message) {
        int i = message.what;
        if (i == 14) {
            personalCenterFragment.c();
        }
        if (i == 81) {
            personalCenterFragment.d(((Boolean) message.obj).booleanValue());
            return;
        }
        switch (i) {
            case 8:
                LogUtil.a("UIME_PersonalCenterFragment", "handleRedPoint show point");
                personalCenterFragment.u = true;
                sbd sbdVar = personalCenterFragment.z;
                sbdVar.a(sbdVar.m(), personalCenterFragment.u);
                personalCenterFragment.setBottomRedDotVisibility(2);
                break;
            case 9:
                personalCenterFragment.setBottomRedDotVisibility(1);
                personalCenterFragment.d(message.arg1);
                break;
            case 10:
                personalCenterFragment.cancelBottomRedDotVisible(1);
                personalCenterFragment.d(message.arg1);
                break;
            case 11:
                LogUtil.a("UIME_PersonalCenterFragment", "handleRedPoint show medalRedPoint");
                personalCenterFragment.ah();
                personalCenterFragment.x();
                break;
            default:
                dUp_(personalCenterFragment, message);
                break;
        }
    }

    public /* synthetic */ void a(int i, MessageChangeEvent messageChangeEvent) {
        LogUtil.a("UIME_PersonalCenterFragment", "PersonalCenterFragment MessageObserver onChange start flag = ", Integer.valueOf(i));
        List<String> modifyMessageObjectIds = messageChangeEvent.getModifyMessageObjectIds();
        List<String> removeMessageObjectIds = messageChangeEvent.getRemoveMessageObjectIds();
        if ((i == 0 && koq.c(modifyMessageObjectIds)) || koq.c(removeMessageObjectIds)) {
            LogUtil.a("UIME_PersonalCenterFragment", "PersonalCenterFragment MessageObserver Change");
            x();
        }
    }

    public PersonalCenterFragment() {
        this.d = new CommonCallback() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.5
            @Override // com.huawei.up.callback.CommonCallback
            public void onSuccess(Bundle bundle) {
                LogUtil.a("UIME_PersonalCenterFragment", "get new headImg and name from cloud success.");
                PersonalCenterFragment.this.at();
            }

            @Override // com.huawei.up.callback.CommonCallback
            public void onFail(int i) {
                LogUtil.a("UIME_PersonalCenterFragment", "get new headImg and name from cloud failure.");
            }
        };
        this.f10524a = true;
        this.f = new HashMap(10);
        this.q = 4;
        this.e = false;
        this.v = true;
        this.x = false;
        this.t = new c(this);
        this.n = null;
        this.al = new d(this);
        this.ac = new CopyOnWriteArrayList<>();
        this.c = new CopyOnWriteArrayList<>();
        this.y = true;
        this.o = new ArrayList(10);
        this.av = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.a("UIME_PersonalCenterFragment", "UserProfileActivity mUserProfileReceiver(): intent = ", intent.getAction());
                PersonalCenterFragment.this.as.notifyCallback();
                if ("com.huawei.plugin.account.login".equals(intent.getAction())) {
                    PersonalCenterFragment.this.aj();
                    PersonalCenterFragment.this.o();
                    return;
                }
                if ("com.huawei.bone.action.FITNESS_USERINFO_UPDATED".equals(intent.getAction())) {
                    if (!CommonUtil.bu()) {
                        PersonalCenterFragment.this.at();
                        return;
                    } else {
                        LogUtil.a("UIME_PersonalCenterFragment", "Store version data is updated.");
                        PersonalCenterFragment.this.ai();
                        return;
                    }
                }
                if ("com.huawei.bone.action.AIRULE_PARSE_SUCCESS".equals(intent.getAction())) {
                    PersonalCenterFragment.this.as();
                } else if ("com.huawei.plugin.account.logout".equals(intent.getAction())) {
                    PersonalCenterFragment.this.n.setAccountNickName(PersonalCenterFragment.this.i.getString(R$string.IDS_hwh_login_hwid_account));
                    PersonalCenterFragment.this.n.setHeadImageByResourceId(R.mipmap._2131821050_res_0x7f1101fa);
                }
            }
        };
        this.ad = new MessageObserver() { // from class: rzk
            @Override // com.huawei.pluginmessagecenter.service.MessageObserver
            public final void onChange(int i, MessageChangeEvent messageChangeEvent) {
                PersonalCenterFragment.this.a(i, messageChangeEvent);
            }
        };
        this.as = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        this.ab = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
    }

    public PersonalCenterFragment(HealthBottomView healthBottomView) {
        this();
        this.g = healthBottomView;
    }

    public void d(HealthBottomView healthBottomView) {
        this.g = healthBottomView;
        this.j = 0;
        ag();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreate(bundle);
        this.i = getActivity();
        this.w = Utils.o();
        this.p = Utils.i();
        this.s = LoginInit.getInstance(this.i).isBrowseMode();
        LogUtil.a("UIME_PersonalCenterFragment", "mIsOversea: ", Boolean.valueOf(this.w), "  mIsAllowLogin: ", Boolean.valueOf(this.p), "  mIsBrowseMode: ", Boolean.valueOf(this.s));
        this.x = CommonUtil.bu();
        if (this.w && !OperationUtils.isOperation(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010))) {
            this.q = 3;
        }
        mct.b(this.i, "data_pop_flag", "");
        mct.b(this.i, "data_downing_flag", "");
        LogUtil.a("UIME_PersonalCenterFragment", "onCreate, ", this);
        this.z = new sbd(this, this.t);
        this.ah = new sat(this, this.t);
        this.ag = new say(this, this.t);
        this.aj = new sbb(this.t, this.as);
        if (!this.w && !this.x) {
            this.an = new sbg();
        }
        if (this.s) {
            ixx.d().a("0", "0");
        } else {
            i();
            f();
            g();
        }
        ReleaseLogUtil.b("UIME_PersonalCenterFragment", "onCreate finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onCreateView(layoutInflater, viewGroup, bundle);
        sbk a2 = sbk.a(this.i);
        this.at = a2;
        a2.dUY_(this.t);
        this.ar = layoutInflater.inflate(R.layout.fragment_personal_center, viewGroup, false);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        this.ar.setPadding(((Integer) safeRegionWidth.first).intValue(), 0, ((Integer) safeRegionWidth.second).intValue(), 0);
        this.n = (CustomHeadView) this.ar.findViewById(R.id.account_center_customheadview);
        v();
        this.aq = (HealthRecycleView) this.ar.findViewById(R.id.account_center_rv);
        this.ap = new PersonalCenterRecyclerViewAdapter(this.i);
        this.aq.setLayoutManager(new HealthLinearLayoutManager(this.i));
        this.aq.setAdapter(this.ap);
        this.z.e(this.ap);
        if (this.aq.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.aq.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        this.aq.a(false);
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        q();
        dUs_(this.ar);
        s();
        this.z.s();
        this.ap.d(this.ak);
        this.ap.c(this.ag);
        this.n.setOnClickListener(this.i, this);
        if ("1".equals(SharedPreferenceManager.b(this.i, Integer.toString(10015), "if_need_set_account_login_entry"))) {
            this.n.setVisibility(0);
            if (this.w) {
                this.n.e(8);
            }
        }
        p();
        ReleaseLogUtil.b("UIME_PersonalCenterFragment", "onCreateView finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return this.ar;
    }

    private void q() {
        LogUtil.a("UIME_PersonalCenterFragment", "enter initHeadView.");
        String b2 = SharedPreferenceManager.b(this.i, String.valueOf(20000), "huawei_account_login_init");
        if (!this.p && this.w && TextUtils.isEmpty(b2)) {
            this.n.setVisibility(8);
            return;
        }
        this.n.setVisibility(0);
        if (this.w) {
            this.n.e(8);
        } else {
            this.n.e(0);
        }
    }

    private void v() {
        final CustomTitleBar customTitleBar = (CustomTitleBar) this.ar.findViewById(R.id.personal_tab_titlebar);
        customTitleBar.setOnClickListener(new View.OnClickListener() { // from class: rzq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PersonalCenterFragment.dUt_(view);
            }
        });
        customTitleBar.setLeftButtonVisibility(8);
        customTitleBar.setTitleText(this.i.getString(R$string.IDS_hw_show_main_home_page_mine));
        if (nrs.a(this.i)) {
            customTitleBar.setTitleSize(this.i.getResources().getDimension(R.dimen._2131362673_res_0x7f0a0371));
        } else {
            customTitleBar.setTitleSize(this.i.getResources().getDimension(R.dimen._2131365076_res_0x7f0a0cd4));
        }
        if (nsn.s()) {
            customTitleBar.setTitleSize(this.i.getResources().getDimension(R.dimen._2131363048_res_0x7f0a04e8));
        }
        customTitleBar.setTitleBarBackgroundColor(this.i.getResources().getColor(R.color._2131299296_res_0x7f090be0));
        customTitleBar.setTitleTextColor(this.i.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.ao = (HealthNestedScrollView) this.ar.findViewById(R.id.personal_scroll);
        final RelativeLayout relativeLayout = (RelativeLayout) this.ar.findViewById(R.id.top_title);
        final float f = nrt.a(BaseApplication.getContext()) ? 0.8f : 1.0f;
        final int color = ContextCompat.getColor(this.i, R.color._2131296690_res_0x7f0901b2);
        this.ao.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: rzp
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                PersonalCenterFragment.dUu_(relativeLayout, color, f, view, i, i2, i3, i4);
            }
        });
        customTitleBar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                customTitleBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                LinearLayout linearLayout = (LinearLayout) PersonalCenterFragment.this.ar.findViewById(R.id.personal_layout);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.topMargin = customTitleBar.getMeasuredHeight();
                LogUtil.a("UIME_PersonalCenterFragment", "personalLayout topMargin == ", Integer.valueOf(layoutParams.topMargin));
                linearLayout.setLayoutParams(layoutParams);
            }
        });
    }

    public static /* synthetic */ void dUt_(View view) {
        LogUtil.a("UIME_PersonalCenterFragment", "TitleBar clicked == ");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dUu_(RelativeLayout relativeLayout, int i, float f, View view, int i2, int i3, int i4, int i5) {
        int i6;
        if (i3 <= 0) {
            i6 = 0;
            relativeLayout.setBackgroundColor(Color.argb(0, (int) (Color.red(i) * f), (int) (Color.green(i) * f), (int) (Color.blue(i) * f)));
        } else {
            i6 = (i3 <= 0 || i3 > 100) ? 255 : (int) ((i3 / 100.0f) * 255.0f);
        }
        relativeLayout.setBackgroundColor(Color.argb(i6, (int) (Color.red(i) * f), (int) (Color.green(i) * f), (int) (Color.blue(i) * f)));
    }

    private void p() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.ar.findViewById(R.id.personal_center_gridView);
        this.h = healthRecycleView;
        boolean z = this.p;
        if (!z) {
            LogUtil.h("UIME_PersonalCenterFragment", "initGridView mIsAllowLogin == ", Boolean.valueOf(z));
            this.h.setVisibility(8);
            return;
        }
        healthRecycleView.setEnabled(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.i, 2);
        this.r = gridLayoutManager;
        gridLayoutManager.setOrientation(1);
        this.h.setLayoutManager(this.r);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        arrayList.add(0);
        int dimensionPixelSize = this.i.getResources().getDimensionPixelSize(R.dimen._2131362009_res_0x7f0a00d9);
        this.h.addItemDecoration(new RecyclerItemDecoration(dimensionPixelSize, dimensionPixelSize, arrayList));
        t();
        if (koq.b(this.o)) {
            LogUtil.h("UIME_PersonalCenterFragment", "mGridCardList is empty hide gridview");
            this.h.setVisibility(8);
        } else {
            PersonalGridAdapter personalGridAdapter = new PersonalGridAdapter(this.i, this.o);
            this.k = personalGridAdapter;
            this.h.setAdapter(personalGridAdapter);
        }
    }

    private void t() {
        boolean isKidAccount = LoginInit.getInstance(this.i).isKidAccount();
        this.o.clear();
        if ((LanguageUtil.j(this.i) || LanguageUtil.p(this.i)) && !isKidAccount && w()) {
            this.o.add(new sah(this.i, this));
        }
        if (efb.l()) {
            this.o.add(new sae(this.i, this));
        }
        if (this.p) {
            if (!isKidAccount && this.as.isActiveTargetSwitchOpen()) {
                this.o.add(new rzt(this.i, this));
            }
            this.o.add(new sao(this.i, this));
        }
        aq();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        sbd sbdVar = this.z;
        if (sbdVar == null || sbdVar.d() != null) {
            LogUtil.a("UIME_PersonalCenterFragment", "not need initFeedbackSdk");
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "enter initFeedbackSdk");
        if (this.t != null && !ixj.b().a() && !ixj.b().h()) {
            ixj.b().bCO_(false, this.t);
        } else {
            LogUtil.a("UIME_PersonalCenterFragment", "initData , phoneservicesdk is not initialized");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    private void aq() {
        LogUtil.a("UIME_PersonalCenterFragment", "gridview mGridCardList.size() = ", Integer.valueOf(this.o.size()));
        int size = this.o.size();
        boolean z = (size == 3 && !nsn.ag(this.i)) || nsn.r();
        Iterator<rzv> it = this.o.iterator();
        while (it.hasNext()) {
            it.next().e(z);
        }
        if (!nsn.ag(this.i) && size == 4) {
            size = 2;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "gridview column = ", Integer.valueOf(size));
        GridLayoutManager gridLayoutManager = this.r;
        if (gridLayoutManager != null) {
            gridLayoutManager.setSpanCount(size);
        }
        PersonalGridAdapter personalGridAdapter = this.k;
        if (personalGridAdapter != null) {
            personalGridAdapter.notifyDataSetChanged();
        }
    }

    @Override // com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi
    public void cancelBottomRedDotVisible(int i) {
        LogUtil.a("UIME_PersonalCenterFragment", "cancelBottomRedDotVisible position:", Integer.valueOf(i));
        this.f.remove(Integer.valueOf(i));
        ag();
    }

    @Override // com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi
    public void setBottomRedDotVisibility(int i) {
        LogUtil.a("UIME_PersonalCenterFragment", "setBottomRedDotVisibility position:", Integer.valueOf(i));
        this.f.put(Integer.valueOf(i), 1);
        ag();
    }

    private void ag() {
        LogUtil.a("UIME_PersonalCenterFragment", "redRot:", Integer.valueOf(this.f.size()), "last:", Integer.valueOf(this.j));
        if (this.g == null) {
            return;
        }
        if (this.f.size() == 0 && this.j != 0) {
            this.j = this.f.size();
            this.g.c(this.q, false);
        } else {
            if (this.f.size() == 0 || this.j != 0) {
                return;
            }
            this.j = this.f.size();
            this.g.c(this.q, true);
        }
    }

    @Override // com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi
    public void openMessageCenterPage() {
        if (this.i != null) {
            Bundle bundle = new Bundle();
            if (koq.c(this.ac)) {
                String e2 = HiJsonUtil.e(this.ac);
                bundle.putString("messages", e2);
                LogUtil.a("UIME_PersonalCenterFragment", "openMessageCenterPage mMessageObjects:", e2);
            }
            if (koq.c(this.c)) {
                bundle.putString("asset", HiJsonUtil.e(this.c));
            }
            AppRouter.b("/PluginMessageCenter/MessageCenterActivity").zF_(bundle).c(this.i);
        }
        e(AnalyticsValue.HEALTH_MINE_NOTICE_MSG_2040003.value());
    }

    @Override // com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi
    public void openMyAssetPage() {
        if (this.i != null) {
            LogUtil.a("UIME_PersonalCenterFragment", "openMyAssetPage ");
            if (CommonUtil.bu()) {
                AppRouter.b("/HWUserProfileMgr/MyAwardActivity").a(268435456).c(this.i);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("coupon", this.ah.e());
                bundle.putString("card_case", this.ah.d());
                bundle.putString("award", this.ah.b());
                bundle.putBoolean("point", this.ah.a());
                AppRouter.b("/HWUserProfileMgr/MyAssets").a(268435456).zF_(bundle).c(this.i);
            }
            e(AnalyticsValue.HEALTH_MY_AWARD_ENTER_2190004.value());
        }
    }

    @Override // com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi
    public void dealAssetMessage(List<AssetMessage> list) {
        List<MessageObject> e2 = njn.e(list, false);
        this.c.clear();
        if (koq.c(e2)) {
            this.c.addAll(e2);
            x();
        } else {
            LogUtil.h("UIME_PersonalCenterFragment", "assetMsgListQuery is empty");
        }
    }

    @Override // com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi
    public void updateBannerAd(String str, com.huawei.pluginachievement.manager.model.MessageObject messageObject) {
        Object[] objArr = new Object[4];
        boolean z = false;
        objArr[0] = "updateBannerAd: imgUrl = ";
        objArr[1] = Boolean.valueOf(str != null);
        objArr[2] = ", msgObj = ";
        objArr[3] = Boolean.valueOf(messageObject != null);
        LogUtil.a("UIME_PersonalCenterFragment", objArr);
        rzs c2 = this.z.c();
        if (c2 == null) {
            return;
        }
        boolean z2 = LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() || !this.p;
        if (str != null && messageObject != null && !z2) {
            z = true;
        }
        if (z) {
            if (this.am == null) {
                this.am = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
            }
            c2.dUA_(nkx.cwZ_(new say.a(this.ag), (BaseActivity) this.i, this.am.isNotSupportBrowseUrl(messageObject.getDetailUri()), AnalyticsValue.MARKETING_RESOURCE.value()));
            c2.c(str);
        } else {
            c2.c((Object) null);
            c2.dUA_(null);
        }
        LogUtil.a("UIME_PersonalCenterFragment", "updateBannerAd: is show = ", Boolean.valueOf(z));
        if (c2.i() != z) {
            c2.c(z);
            aa();
        } else if (z) {
            this.ap.c(c2);
        } else {
            LogUtil.a("UIME_PersonalCenterFragment", "need not updateBannerAd");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        LogUtil.a("UIME_PersonalCenterFragment", "setUserVisibleHint isVisibleToUser is ", Boolean.valueOf(z));
        if (this.i == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "setUserVisibleHint mContext is null");
            return;
        }
        if (this.ar == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "setUserVisibleHint mRootView is null");
            return;
        }
        if (z) {
            LogUtil.a("UIME_PersonalCenterFragment", "mIsFirst = ", Boolean.valueOf(this.v), " isRefresh = ", Boolean.valueOf(this.e));
            HealthNestedScrollView healthNestedScrollView = this.ao;
            if (healthNestedScrollView != null) {
                ScrollUtil.cKx_(healthNestedScrollView, getActivity().getWindow().getDecorView(), IEventListener.EVENT_ID_DEVICE_CONN_FAIL);
            }
            o();
            if (this.v) {
                this.e = true;
                n();
                r();
                ai();
                if (this.f10524a) {
                    this.f10524a = sas.d(this.i);
                }
                z();
            } else if (!this.s) {
                al();
            } else {
                LogUtil.a("UIME_PersonalCenterFragment", "BrowseMode, need not to update red point");
                m();
                return;
            }
            this.v = false;
            b();
            if (!this.s) {
                d();
                e();
            }
        }
        if (getView() != null) {
            eil.alV_(getActivity().getWindow().getDecorView(), z, IEventListener.EVENT_ID_DEVICE_CONN_FAIL, "Me");
        }
    }

    private void al() {
        this.n.c();
        sas.dUK_(this.t);
        i();
        f();
        g();
        j();
        ao();
        Iterator<rzv> it = this.o.iterator();
        while (it.hasNext()) {
            it.next().refreshCardData();
        }
    }

    private void r() {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            MarketingOption.Builder builder = new MarketingOption.Builder();
            builder.setContext(this.i);
            builder.setPageId(110);
            builder.setLayoutMap(new HashMap());
            marketingApi.requestMarketingResource(builder.build());
            HashMap hashMap = new HashMap();
            hashMap.put("open_specific_page", "Me");
            builder.setTriggerEventParams(hashMap);
            builder.setTypeId(49);
            marketingApi.triggerMarketingResourceEvent(builder.build());
            b(marketingApi, builder);
        }
        h();
    }

    private void b(MarketingApi marketingApi, MarketingOption.Builder builder) {
        builder.setTypeId(51);
        marketingApi.triggerMarketingResourceEvent(builder.build());
        builder.setTypeId(52);
        marketingApi.triggerMarketingResourceEvent(builder.build());
    }

    private void d() {
        mer.b(this.i).cgp_(this.t, 11);
    }

    private void e() {
        boolean z;
        if (this.z == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "checkUpateRedPointShow mListManager is null");
            return;
        }
        if (this.x || !kyd.c()) {
            LogUtil.a("UIME_PersonalCenterFragment", "is GP or Store version or update switch is close");
            z = false;
        } else {
            ae();
            LogUtil.a("UIME_PersonalCenterFragment", "checkUpateRedPointShow fetchBannerAd");
            h();
            z = true;
        }
        rzs a2 = this.z.a();
        if (a2 == null || a2.i() == z) {
            return;
        }
        a2.c(z);
        aa();
    }

    private void c(boolean z) {
        LogUtil.a("UIME_PersonalCenterFragment", "enter updateSuggestItem");
        if (!z) {
            LogUtil.h("UIME_PersonalCenterFragment", "updateSuggestItem() sdk init failed");
            return;
        }
        rzs l = this.z.l();
        if (l == null) {
            return;
        }
        boolean z2 = !this.s && (!(this.w || ixj.c() || CommonUtil.bp()) || (this.w && this.p && ixj.e() && !CommonUtil.bp()));
        if (l.i() != z2) {
            l.c(z2);
            aa();
        }
    }

    private void n() {
        ad();
        ac();
        ObserverManagerUtil.d(this.al, ObserveLabels.OPERATION_FUNCTION_ENTRANCE);
        this.f10524a = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        if (this.s) {
            LogUtil.a("UIME_PersonalCenterFragment", "BrowseMode, need not to update red point");
            m();
            return;
        }
        if (this.i == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "resumePersonalCenterFragment, context is null, return");
            this.s = LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode();
            return;
        }
        al();
        at();
        String b2 = SharedPreferenceManager.b(this.i, Integer.toString(10000), "rid_dot_dismiss");
        String b3 = SharedPreferenceManager.b(this.i, Integer.toString(10000), "onboarding_skip_current_time");
        String b4 = SharedPreferenceManager.b(this.i, Integer.toString(10000), "onboarding_skip");
        LogUtil.a("UIME_PersonalCenterFragment", "redDotVisible = ", b2, " skipTime = ", b3, " isSetOnBoarding =, ", b4);
        if (!"1".equals(b2) && !TextUtils.isEmpty(b3) && !"1".equals(b4)) {
            this.aj.b();
        } else {
            this.u = false;
            sbd sbdVar = this.z;
            sbdVar.a(sbdVar.m(), this.u);
            cancelBottomRedDotVisible(2);
        }
        y();
        ae();
        af();
        ag();
        an();
    }

    private void af() {
        boolean z;
        if (this.w || LoginInit.getInstance(this.i).isKidAccount()) {
            return;
        }
        if (!"true".equals(SharedPreferenceManager.b(this.i, Integer.toString(10000), "wechat_red_dot_show"))) {
            setBottomRedDotVisibility(128);
            LogUtil.a("UIME_PersonalCenterFragment", "showPrivacyManagerRedPoint is datashare and authrize");
            z = true;
        } else {
            cancelBottomRedDotVisible(128);
            z = false;
        }
        sbd sbdVar = this.z;
        sbdVar.a(sbdVar.g(), z);
        LogUtil.a("UIME_PersonalCenterFragment", "showPrivacyManagerRedPoint");
    }

    private void ae() {
        boolean z;
        sbd sbdVar = this.z;
        if (sbdVar == null || sbdVar.a() == null) {
            return;
        }
        boolean haveNewAppVersion = ((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).haveNewAppVersion(this.i);
        LogUtil.a("UIME_PersonalCenterFragment", "showAppUpdateNew: haveNewAppVersion = ", Boolean.valueOf(haveNewAppVersion));
        if (haveNewAppVersion) {
            setBottomRedDotVisibility(512);
            z = true;
        } else {
            cancelBottomRedDotVisible(512);
            z = false;
        }
        sbd sbdVar2 = this.z;
        sbdVar2.a(sbdVar2.a(), z);
    }

    private void an() {
        sbd sbdVar = this.z;
        if (sbdVar == null || sbdVar.n() == null || !pqm.c()) {
            return;
        }
        boolean a2 = SharedPreferenceManager.a(Integer.toString(10000), "service_express_card_red_point", true);
        LogUtil.a("UIME_PersonalCenterFragment", "ShowFaCardsRedPoint:", Boolean.valueOf(a2));
        if (a2) {
            setBottomRedDotVisibility(256);
        } else {
            cancelBottomRedDotVisible(256);
        }
        sbd sbdVar2 = this.z;
        sbdVar2.a(sbdVar2.n(), a2);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        this.s = LoginInit.getInstance(this.i).isBrowseMode();
        if (this.e && getUserVisibleHint()) {
            ai();
            e();
        }
        ReleaseLogUtil.b("UIME_PersonalCenterFragment", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("UIME_PersonalCenterFragment", "onConfigurationChanged enter");
        v();
        PersonalCenterRecyclerViewAdapter personalCenterRecyclerViewAdapter = this.ap;
        if (personalCenterRecyclerViewAdapter != null) {
            personalCenterRecyclerViewAdapter.c(true);
            this.ap.c(this.z.h());
            this.ap.c(this.z.c());
        }
        aq();
    }

    private void b() {
        if (this.n == null) {
            return;
        }
        String accountInfo = LoginInit.getInstance(this.i).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo) || CommonUtil.j(this.i, accountInfo) || "0".equals(LoginInit.getInstance(this.i).getAccountInfo(1011))) {
            return;
        }
        this.n.setVisibility(0);
        if (this.w) {
            this.n.e(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogUtil.a("UIME_PersonalCenterFragment", "judgeNoticeDot");
        synchronized (b) {
            ExecutorService executorService = this.au;
            if (executorService == null || executorService.isShutdown()) {
                this.au = Executors.newSingleThreadExecutor();
            }
            this.au.execute(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    PersonalCenterFragment.this.k();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        Handler handler = this.t;
        if (handler == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "handleNoticeDot handler is null");
            return;
        }
        if (this.ab == null) {
            this.ab = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        }
        int messageCenterUnreadCount = this.ab.getMessageCenterUnreadCount();
        if (mer.b(this.i).c()) {
            messageCenterUnreadCount++;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "handleNoticeDot unReadCount:", Integer.valueOf(messageCenterUnreadCount));
        if (koq.c(this.ac)) {
            Iterator<MessageObject> it = this.ac.iterator();
            while (it.hasNext()) {
                MessageObject next = it.next();
                LogUtil.a("UIME_PersonalCenterFragment", "handleNoticeDot messageObject:", next.toString());
                if (next.getReadFlag() == 0) {
                    messageCenterUnreadCount++;
                }
            }
        }
        if (koq.c(this.c)) {
            Iterator<MessageObject> it2 = this.c.iterator();
            while (it2.hasNext()) {
                MessageObject next2 = it2.next();
                LogUtil.a("UIME_PersonalCenterFragment", "handleNoticeDot messageObject:", next2.toString());
                if (next2.getReadFlag() == 0) {
                    messageCenterUnreadCount++;
                }
            }
        } else {
            LogUtil.h("UIME_PersonalCenterFragment", "handleNoticeDot mAssetMessageObjects is empty");
        }
        LogUtil.a("UIME_PersonalCenterFragment", "handleNoticeDot unReadCount:", Integer.valueOf(messageCenterUnreadCount));
        int i = messageCenterUnreadCount > 0 ? 9 : 10;
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.arg1 = messageCenterUnreadCount;
        handler.sendMessage(obtainMessage);
    }

    private void c() {
        sbd sbdVar = this.z;
        sbdVar.a(sbdVar.m(), this.u);
        cancelBottomRedDotVisible(2);
    }

    private void dUs_(View view) {
        ag();
        sbk sbkVar = this.at;
        if (sbkVar != null) {
            sbkVar.e();
        }
        dUv_(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        LogUtil.a("UIME_PersonalCenterFragment", "Enter initAchieveData");
        sas.d dVar = obj instanceof sas.d ? (sas.d) obj : null;
        if (dVar == null) {
            LogUtil.a("UIME_PersonalCenterFragment", "data null");
            return;
        }
        PersonalData e2 = dVar.e();
        if (!this.w && e2 != null) {
            d(sbc.d(e2), sbc.a(e2));
            c(sbc.c(e2));
        }
        if (dVar.c() != null) {
            ArrayList<String> c2 = dVar.c();
            LogUtil.a("UIME_PersonalCenterFragment", "mMedalLists length == ", Integer.valueOf(c2.size()));
            sbd sbdVar = this.z;
            sbdVar.a(sbdVar.h(), c2);
        }
    }

    private void d(int i, String str) {
        LogUtil.a("UIME_PersonalCenterFragment", "enter setUserRankLevel(): level = ", Integer.valueOf(i), ", describe = ", str);
        boolean z = !this.s && mlc.a();
        if (z) {
            setBottomRedDotVisibility(3);
            LogUtil.a("UIME_PersonalCenterFragment", "setUserRankLevel is datashare and authrize");
        } else {
            cancelBottomRedDotVisible(3);
        }
        this.n.b(i, z);
    }

    private void c(int i) {
        LogUtil.c("UIME_PersonalCenterFragment", "Enter setTotalCal cal:", Integer.valueOf(i));
        this.n.b(UnitUtil.e(i, 1, 0));
    }

    private void d(int i) {
        LogUtil.c("UIME_PersonalCenterFragment", "Enter setUnreadMessageNum unreadMessageNum:", Integer.valueOf(i));
        HealthTextView healthTextView = this.ai;
        if (healthTextView == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "setUnreadMessageNum, mNoticeMessageRedDot is null");
            return;
        }
        if (i <= 0) {
            healthTextView.setVisibility(8);
            jcf.bEz_(this.af, nsf.h(R$string.IDS_user_profile_message));
            return;
        }
        if (i >= 100) {
            healthTextView.setBackgroundResource(R.drawable._2131430877_res_0x7f0b0ddd);
            this.ai.setVisibility(0);
            String str = UnitUtil.e(99.0d, 1, 0) + Marker.ANY_NON_NULL_MARKER;
            this.ai.setText(str);
            jcf.bEz_(this.af, nsf.a(R.plurals._2130903526_res_0x7f0301e6, 99, str));
            return;
        }
        healthTextView.setBackgroundResource(R.drawable._2131430875_res_0x7f0b0ddb);
        this.ai.setVisibility(0);
        String e2 = UnitUtil.e(i, 1, 0);
        this.ai.setText(e2);
        jcf.bEz_(this.af, nsf.a(R.plurals._2130903526_res_0x7f0301e6, i, e2));
    }

    private void e(cfi cfiVar, String str) {
        if (cfiVar != null) {
            cfiVar.b(str);
        }
    }

    private void b(cfi cfiVar, String str) {
        if (cfiVar != null) {
            cfiVar.e(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HiUserInfo hiUserInfo) {
        if (hiUserInfo == null) {
            LogUtil.a("UIME_PersonalCenterFragment", "accountmigrate: setUserNameFromLocal hiUserInfo == null");
            return;
        }
        this.n.setAccountNickName(sbc.e(hiUserInfo));
        c(sbc.e(hiUserInfo));
        String a2 = sbc.a(hiUserInfo);
        LogUtil.a("UIME_PersonalCenterFragment", "accountmigrate: headImgPath = ", a2);
        LogUtil.c("UIME_PersonalCenterFragment", "accountfilepath = ", this.i.getFilesDir(), "/photos/headimage");
        String e2 = this.aj.e(a2);
        if (!TextUtils.isEmpty(e2)) {
            Bitmap cIe_ = nrf.cIe_(this.i, e2);
            this.as.sync(null);
            glz.b("PersonalCenterFragment");
            LogUtil.a("UIME_PersonalCenterFragment", "accountmigrate: setUserNameFromLocal headImgPath = ", e2);
            if (cIe_ != null) {
                this.n.setHeadImageByBitmap(cIe_);
                return;
            }
            return;
        }
        this.n.setAccountNickName(this.i.getString(R$string.IDS_hwh_login_hwid_account));
        this.n.setHeadImageByResourceId(R.mipmap._2131821050_res_0x7f1101fa);
        LogUtil.a("UIME_PersonalCenterFragment", "accountmigrate: setUserNameFromLocal() headImgPath is null! ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void at() {
        if ("1".equals(SharedPreferenceManager.b(this.i, Integer.toString(10015), "if_need_set_account_login_entry")) && !this.p) {
            LogUtil.a("UIME_PersonalCenterFragment", "updateUserName ifNeedSetAccountString.equals(\"1\")");
            this.n.e(8);
            this.n.setAccountNickName(this.i.getString(R$string.IDS_hwh_login_hwid_account));
            this.n.setHeadImageByResourceId(R.mipmap._2131821050_res_0x7f1101fa);
            return;
        }
        this.aj.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "handleWhenGetUserInfoSuccess()! userinfo is null! ");
            return;
        }
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        LogUtil.a("UIME_PersonalCenterFragment", "userinfo = ", userInfomation.toString());
        String c2 = sbc.c(userInfomation);
        if (TextUtils.isEmpty(c2)) {
            String accountName = new UpApi(this.i).getAccountName();
            if (!TextUtils.isEmpty(accountName)) {
                this.n.setAccountNickName(accountName);
                e(mainUser, accountName);
                c(accountName);
            }
        } else {
            e(mainUser, c2);
            this.n.setAccountNickName(c2);
        }
        if (CommonUtil.bu()) {
            this.ax = new UserInfoMockInteractor(this.i);
            LogUtil.c("UIME_PersonalCenterFragment", "isPicPath： ", userInfomation.getPicPath());
            userInfomation.setPicPath(izx.bEe_(this.i, "1", this.ax.dWn_()));
            this.ax = null;
            LogUtil.a("UIME_PersonalCenterFragment", "isPicPath： ", userInfomation.getPicPath());
        }
        b(mainUser, sbc.a(userInfomation));
        this.n.setHeadPictureView(userInfomation);
    }

    private void c(String str) {
        UserInfomation userInfo = this.as.getUserInfo();
        if (userInfo != null) {
            if (TextUtils.isEmpty(userInfo.getName())) {
                userInfo.setName(str);
                this.as.setUserInfo(userInfo, null);
                return;
            }
            return;
        }
        LogUtil.h("UIME_PersonalCenterFragment", "userInfomation is null or user name not null");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (nsn.a(500)) {
            LogUtil.a("UIME_PersonalCenterFragment", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "onClick");
        if (id == R.id.account_head_picture || id == R.id.account_nickname) {
            if (this.x) {
                LogUtil.a("UIME_PersonalCenterFragment", "storedemo no user");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (this.s) {
                LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: rzj
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        PersonalCenterFragment.a(i, obj);
                    }
                }, "");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if ("1".equals(SharedPreferenceManager.b(this.i, Integer.toString(10015), "if_need_set_account_login_entry")) && !this.p) {
                LogUtil.a("UIME_PersonalCenterFragment", "send broadcast to trriger checkLogin...");
                this.v = true;
                Intent intent = new Intent();
                intent.setAction("com.huawei.plugin.trigger.checklogin");
                if (LocalBroadcastManager.getInstance(this.i) != null) {
                    LocalBroadcastManager.getInstance(this.i).sendBroadcast(intent);
                }
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (CommonUtil.z(this.i)) {
                ThirdPartyLoginManager.getInstance().openAccountManager(getActivity());
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            String b2 = SharedPreferenceManager.b(this.i, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN);
            boolean checkIsInstallHuaweiAccount = HuaweiLoginManager.checkIsInstallHuaweiAccount(this.i);
            LogUtil.a("UIME_PersonalCenterFragment", "accountmigrate: hasHwid = ", Boolean.valueOf(checkIsInstallHuaweiAccount));
            if ("1".equals(b2) || !checkIsInstallHuaweiAccount) {
                LogUtil.a("UIME_PersonalCenterFragment", "accountmigrate: isthirdlogin == 1 and return!");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                LogUtil.a("UIME_PersonalCenterFragment", "health, jumpToHwIdAccountCenter, errorCode = ", Integer.valueOf(new UpApi(this.i).jumpToHwIdAccountCenter(this, 1)));
                e(AnalyticsValue.HEALTH_MINE_HEAD_IMG_2040002.value());
            }
        } else if (id == R.id.rl_message_new) {
            openMessageCenterPage();
        } else {
            LogUtil.a("UIME_PersonalCenterFragment", "id = ", Integer.valueOf(id));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("UIME_PersonalCenterFragment", "back from HuaweiAccountApk.requestCode = ", Integer.valueOf(i), ",resultCode = ", Integer.valueOf(i2));
        if (i != 1) {
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "refresh headImage and name.");
        this.as.sync(this.d);
        glz.b("PersonalCenterFragment");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("UIME_PersonalCenterFragment", "onDestroy, ", this);
        ak();
        am();
        ObserverManagerUtil.e(this.al, ObserveLabels.OPERATION_FUNCTION_ENTRANCE);
        super.onDestroy();
        synchronized (b) {
            ExecutorService executorService = this.au;
            if (executorService != null) {
                if (!executorService.isShutdown()) {
                    this.au.shutdownNow();
                }
                this.au = null;
            }
        }
        Handler handler = this.t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        sbk sbkVar = this.at;
        if (sbkVar != null) {
            sbkVar.dUY_(null);
            this.at = null;
        }
        RelativeLayout relativeLayout = this.ae;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(null);
            this.ae = null;
        }
        this.g = null;
        this.z.r();
        this.ab = null;
        this.ad = null;
        List<rzs> list = this.ak;
        if (list != null) {
            list.clear();
            this.ak = null;
        }
        rzw rzwVar = this.l;
        if (rzwVar != null) {
            rzwVar.d(true);
        }
        ixj.b().g();
    }

    private void ac() {
        LogUtil.a("UIME_PersonalCenterFragment", "Start register");
        if (this.ab == null || this.ad == null) {
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "registerADObserver");
        this.ab.registerMessageObserver(this.ad);
    }

    private void am() {
        if (this.ab == null || this.ad == null) {
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "unregisterADObserver");
        this.ab.unregisterMessageObserver(this.ad);
    }

    private void ad() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        intentFilter.addAction("com.huawei.bone.action.FITNESS_USERINFO_UPDATED");
        if (this.w) {
            intentFilter.addAction("com.huawei.bone.action.AIRULE_PARSE_SUCCESS");
        }
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
        this.aa = localBroadcastManager;
        localBroadcastManager.registerReceiver(this.av, intentFilter);
    }

    private void ak() {
        LocalBroadcastManager localBroadcastManager = this.aa;
        if (localBroadcastManager == null) {
            return;
        }
        try {
            localBroadcastManager.unregisterReceiver(this.av);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("UIME_PersonalCenterFragment", e2.getMessage());
        }
    }

    private void e(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(this.i, str, hashMap, 0);
    }

    private boolean w() {
        return (this.w || this.x) ? false : true;
    }

    private void ah() {
        if (w()) {
            if (mer.b(this.i).c()) {
                setBottomRedDotVisibility(4);
            } else {
                cancelBottomRedDotVisible(4);
            }
            ar();
        }
    }

    private void c(List<String> list) {
        LogUtil.a("UIME_PersonalCenterFragment", "updateMessageCenterForMedal");
        if (this.l == null) {
            this.l = new rzw(list, new IBaseResponseCallback() { // from class: rzn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    PersonalCenterFragment.this.c(i, obj);
                }
            });
            ThreadPoolManager.d().execute(this.l);
        } else {
            LogUtil.h("UIME_PersonalCenterFragment", "updateMessageCenterForMedal has processed thread");
        }
    }

    public /* synthetic */ void c(int i, Object obj) {
        this.l = null;
    }

    private void y() {
        d();
        if (!this.w && this.z.h() != null) {
            String b2 = mct.b(this.i, "my_medal_red_point");
            String b3 = mct.b(this.i, "_medalPngStatusDownload");
            LogUtil.a("UIME_PersonalCenterFragment", "isShowMyMedalRedPoint =", b2);
            ArrayList arrayList = new ArrayList(10);
            if (!b2.trim().isEmpty() && !"no".equals(b2)) {
                boolean z = false;
                for (String str : b2.split(",")) {
                    if (mfg.b("", str, this.i, b3)) {
                        arrayList.add(str);
                        z = true;
                    }
                }
                if (z) {
                    setBottomRedDotVisibility(16);
                    sbd sbdVar = this.z;
                    sbdVar.a(sbdVar.h(), true);
                    c(arrayList);
                }
            }
        }
        ap();
    }

    private void ap() {
        if (this.w || this.z.d() == null) {
            return;
        }
        String b2 = mct.b(this.i, "my_help_red_point");
        LogUtil.a("UIME_PersonalCenterFragment", "isShowMyHelpRedPoint =", b2);
        if (!b2.trim().isEmpty() || "no".equals(b2)) {
            return;
        }
        sbd sbdVar = this.z;
        sbdVar.a(sbdVar.d(), true);
        setBottomRedDotVisibility(32);
    }

    private void ao() {
        sbg sbgVar;
        if (this.w || (sbgVar = this.an) == null) {
            return;
        }
        sbgVar.dUZ_(this.t);
    }

    private boolean u() {
        return (((Activity) this.i).isFinishing() || ((Activity) this.i).isDestroyed()) ? false : true;
    }

    private void dUv_(View view) {
        this.ae = (RelativeLayout) nsy.cMd_(view, R.id.rl_message_new);
        this.ai = (HealthTextView) view.findViewById(R.id.txt_message_count_new);
        this.af = (ImageView) nsy.cMd_(view, R.id.iv_message);
        if (LanguageUtil.bc(this.i)) {
            this.af.setImageDrawable(nrz.cKn_(this.i, R.mipmap._2131820987_res_0x7f1101bb));
        }
        this.ae.setOnClickListener(nkx.cwZ_(this, (BaseActivity) this.i, true, AnalyticsValue.HEALTH_MINE_NOTICE_MSG_2040003.value()));
    }

    private void s() {
        LogUtil.a("UIME_PersonalCenterFragment", "initRecyclerData");
        if (this.aq == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "initRecyclerData mRecyclerView is null");
            return;
        }
        List<rzs> list = this.ak;
        if (list == null) {
            this.ak = new ArrayList(16);
        } else {
            list.clear();
        }
        if (!this.p && this.w) {
            this.ak = this.z.o();
        } else {
            this.ak = this.z.k();
        }
        as();
    }

    private void m() {
        this.n.setAccountNickName(this.i.getString(R$string.IDS_hwh_login_hwid_account));
        this.n.setHeadImageByResourceId(R.mipmap._2131821050_res_0x7f1101fa);
        this.n.c((UserMemberInfo) null);
        this.n.e(false, gpn.d());
        d(1, "");
        c(0);
        this.n.b(8);
        this.f.clear();
        ag();
        if (this.z.i() != null) {
            this.z.i().c(false);
        }
        if (this.z.l() != null) {
            this.z.l().c(false);
        }
        if (this.z.e() != null) {
            this.z.e().c(false);
        }
        if (!koq.b(this.ak)) {
            for (rzs rzsVar : this.ak) {
                rzsVar.b(false);
                if (rzsVar.f() != 5 && rzsVar.f() != 6) {
                    rzsVar.c((Object) null);
                }
            }
        }
        aa();
        this.ai.setVisibility(8);
        if (!this.w && !this.x) {
            b((Map<Integer, List<sch>>) null);
        }
        Iterator<rzv> it = this.o.iterator();
        while (it.hasNext()) {
            it.next().refreshCardData();
        }
    }

    private void av() {
        HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.4
            @Override // java.lang.Runnable
            public void run() {
                PersonalCenterFragment.this.as();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aj() {
        if (this.v) {
            this.v = false;
        }
        this.w = Utils.o();
        this.p = Utils.i();
        if (this.s) {
            LogUtil.a("UIME_PersonalCenterFragment", "updateAfterLoginSuccess");
            this.s = LoginInit.getInstance(this.i).isBrowseMode();
            ai();
        }
        s();
        aa();
        LogUtil.a("UIME_PersonalCenterFragment", "updateAfterLoginSuccess fetchBannerAd");
        h();
    }

    private void d(boolean z) {
        LogUtil.a("UIME_PersonalCenterFragment", "updateKakaCheckRed isTodayCheckedIn == ", Boolean.valueOf(z));
        this.y = z;
        if (z) {
            cancelBottomRedDotVisible(1024);
            ag();
        } else {
            setBottomRedDotVisibility(1024);
            ag();
        }
        ar();
    }

    private void ar() {
        if (!this.y || mer.b(this.i).c()) {
            this.n.b(0);
        } else {
            this.n.b(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        CustomHeadView customHeadView = this.n;
        if (customHeadView == null) {
            return;
        }
        customHeadView.e(LoginInit.getInstance(BaseApplication.getContext()).isKidAccount(), gpn.d());
    }

    private void b(List<mte> list) {
        if (this.n == null) {
            ReleaseLogUtil.a("UIME_PersonalCenterFragment", "refreshHeadView mCustomHeadView is null");
            return;
        }
        boolean e2 = mtl.e(list, "1");
        ReleaseLogUtil.b("UIME_PersonalCenterFragment", "refreshHeadView isEnableVipFunction ", Boolean.valueOf(e2));
        this.n.e(LoginInit.getInstance(BaseApplication.getContext()).isKidAccount(), e2);
    }

    private void aa() {
        if (this.ap != null) {
            this.z.s();
            this.ap.notifyDataSetChanged();
        }
    }

    private void h() {
        LogUtil.a("UIME_PersonalCenterFragment", " fetchBannerAd into");
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() || !this.p) {
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", " fetchBannerAd into valid");
        say sayVar = this.ag;
        if (sayVar != null) {
            sayVar.a();
        }
    }

    private void a(UserMemberInfo userMemberInfo) {
        if (this.n == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "mCustomHeadView is null");
            return;
        }
        av();
        if (this.n.c(userMemberInfo)) {
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "user is not VIP.");
    }

    private void g() {
        if (CommonUtil.bu()) {
            return;
        }
        LogUtil.a("UIME_PersonalCenterFragment", "getBenefitMessages start");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.10
            @Override // java.lang.Runnable
            public void run() {
                ((VipApi) Services.c("vip", VipApi.class)).getVipMessageList(System.currentTimeMillis(), 50L, new e(PersonalCenterFragment.this, 6), true);
            }
        });
    }

    private void i() {
        LogUtil.a("UIME_PersonalCenterFragment", "getVipInfo start");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.8
            @Override // java.lang.Runnable
            public void run() {
                ((VipApi) Services.c("vip", VipApi.class)).getVipInfo(new VipCallback() { // from class: com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment.8.4
                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onSuccess(Object obj) {
                        UserMemberInfo userMemberInfo;
                        if (obj == null) {
                            LogUtil.h("UIME_PersonalCenterFragment", "getVipInfo onSuccess result is null");
                            return;
                        }
                        if (obj instanceof UserMemberInfo) {
                            userMemberInfo = (UserMemberInfo) obj;
                            LogUtil.c("UIME_PersonalCenterFragment", "getVipInfo mUserMemberInfo = ", userMemberInfo.toString());
                        } else {
                            userMemberInfo = null;
                        }
                        Message obtainMessage = PersonalCenterFragment.this.t.obtainMessage();
                        obtainMessage.what = a.C;
                        obtainMessage.obj = userMemberInfo;
                        PersonalCenterFragment.this.t.sendMessage(obtainMessage);
                    }

                    @Override // com.huawei.health.vip.api.VipCallback
                    public void onFailure(int i, String str) {
                        LogUtil.h("UIME_PersonalCenterFragment", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
                    }
                });
                PersonalCenterFragment.this.l();
            }
        });
    }

    private void f() {
        LogUtil.a("UIME_PersonalCenterFragment", "getAssetsInfo start");
        Context context = BaseApplication.getContext();
        if (this.s || this.x || Utils.l() || LoginInit.getInstance(context).isKidAccount()) {
            LogUtil.a("UIME_PersonalCenterFragment", "not need getAssetsInfo");
            return;
        }
        if (koq.c(this.c)) {
            LogUtil.a("UIME_PersonalCenterFragment", "clear mMessageObjects");
            this.c.clear();
        }
        sat satVar = this.ah;
        if (satVar != null) {
            satVar.c();
            this.ah.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("UIME_PersonalCenterFragment", "enter getVipMessages");
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "getVipMessages VipApi is null");
        } else {
            vipApi.getVipMessageList(System.currentTimeMillis(), 50L, new e(this, 3), false);
        }
    }

    private void ab() {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.a("UIME_PersonalCenterFragment", "getDeviceBenefits payApi is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        if (CommonUtil.bh() && !TextUtils.isEmpty(this.m)) {
            arrayList.add(new DeviceBenefitQueryParam.Builder().setDeviceType(PhoneInfoUtils.getDeviceModel()).setDeviceSn(this.m).setBenefitType(DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_PERF_PURCHASE).setDeviceCategory(DeviceBenefitQueryParam.DeviceCategory.DEVICE_CATEGORY_PHONE.getCategory()).setNeedProductInfo(true).build());
        }
        arrayList.addAll(njn.e("UIME_PersonalCenterFragment", DeviceBenefitQueryParam.DeviceBenefitType.DEVICE_BENEFIT_TYPE_ALL));
        payApi.getAllDeviceBenefits(arrayList, new e(this, 4));
    }

    private void j() {
        if (!TextUtils.isEmpty(this.m) || !CommonUtil.bh()) {
            LogUtil.a("UIME_PersonalCenterFragment", "mDeviceSn is not empty or not huawei system");
            ab();
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rzi
                @Override // java.lang.Runnable
                public final void run() {
                    PersonalCenterFragment.this.a();
                }
            });
        }
    }

    public /* synthetic */ void a() {
        String a2 = njn.a(this.i);
        this.m = a2;
        LogUtil.a("UIME_PersonalCenterFragment", "getDeviceSn end is deviceSn empty", Boolean.valueOf(TextUtils.isEmpty(a2)));
        ab();
    }

    private void e(boolean z) {
        LogUtil.a("UIME_PersonalCenterFragment", "showMyAssetRedPoint isHaveRed:", Boolean.valueOf(z));
        if (z) {
            setBottomRedDotVisibility(2048);
            LogUtil.a("UIME_PersonalCenterFragment", "showMyAssetRedPoint true");
        } else {
            cancelBottomRedDotVisible(2048);
        }
        sbd sbdVar = this.z;
        sbdVar.a(sbdVar.b(), z);
        LogUtil.a("UIME_PersonalCenterFragment", "showMyAssetRedPoint");
    }

    private static void dUp_(PersonalCenterFragment personalCenterFragment, Message message) {
        int i = message.what;
        if (i == 16) {
            if (message.obj instanceof Boolean) {
                personalCenterFragment.c(((Boolean) message.obj).booleanValue());
            }
            return;
        }
        if (i == 37) {
            Bundle data = message.getData();
            rzo.b(data.getInt("bugTypeId"), data.getString("dtsNumber"), data.getString("fileLogId"));
            return;
        }
        switch (i) {
            case a.A /* 209 */:
                personalCenterFragment.updateBannerAd(message.obj instanceof String ? (String) message.obj : null, personalCenterFragment.ag.b());
                break;
            case a.C /* 210 */:
                personalCenterFragment.a(message.obj instanceof UserMemberInfo ? (UserMemberInfo) message.obj : null);
                break;
            case a.D /* 211 */:
                Object obj = message.obj;
                if (koq.e(obj, mte.class)) {
                    personalCenterFragment.b((List<mte>) obj);
                    break;
                }
                break;
            default:
                switch (i) {
                    case a.L /* 213 */:
                        if (message.obj instanceof Boolean) {
                            personalCenterFragment.e(((Boolean) message.obj).booleanValue());
                            break;
                        }
                        break;
                    case a.M /* 214 */:
                        List<DeviceBenefits> arrayList = new ArrayList<>(10);
                        if (koq.e(message.obj, DeviceBenefits.class)) {
                            arrayList = (List) message.obj;
                        }
                        personalCenterFragment.n.a(arrayList);
                        break;
                    case a.N /* 215 */:
                        personalCenterFragment.b(message.obj instanceof Map ? (Map) message.obj : null);
                        break;
                    default:
                        dUr_(personalCenterFragment, message);
                        break;
                }
        }
    }

    private static void dUr_(PersonalCenterFragment personalCenterFragment, Message message) {
        int i = message.what;
        if (i == 205) {
            String obj = message.obj.toString();
            LogUtil.c("UIME_PersonalCenterFragment", "MESSAGE_ID_BIND_TITLE jumpToHwPublic trdTicket = ", obj);
            if (personalCenterFragment.u()) {
                personalCenterFragment.at.b(personalCenterFragment.i, obj);
                return;
            }
            return;
        }
        if (i != 208) {
            switch (i) {
                case 201:
                    if (personalCenterFragment.u()) {
                        personalCenterFragment.at.c(message.obj.toString());
                        break;
                    }
                    break;
                case 202:
                    if (personalCenterFragment.u()) {
                        personalCenterFragment.at.c();
                    }
                    Toast.makeText(personalCenterFragment.i, personalCenterFragment.i.getResources().getString(R$string.IDS_confirm_network_whether_connected), 0).show();
                    break;
                case 203:
                    if (personalCenterFragment.u()) {
                        personalCenterFragment.at.c();
                    }
                    Toast.makeText(personalCenterFragment.i, personalCenterFragment.i.getResources().getString(R$string.IDS_update_server_bussy), 0).show();
                    break;
            }
            return;
        }
        if (personalCenterFragment == null || personalCenterFragment.at == null) {
            return;
        }
        personalCenterFragment.at.a((String) message.obj);
        if (personalCenterFragment.u()) {
            personalCenterFragment.at.c();
        }
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<PersonalCenterFragment> b;
        private int c;

        e(PersonalCenterFragment personalCenterFragment, int i) {
            this.b = new WeakReference<>(personalCenterFragment);
            this.c = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            PersonalCenterFragment personalCenterFragment = this.b.get();
            if (personalCenterFragment != null) {
                Handler handler = personalCenterFragment.t;
                if (handler == null) {
                    LogUtil.h("UIME_PersonalCenterFragment", "handler is null");
                    return;
                } else {
                    LogUtil.a("UIME_PersonalCenterFragment", "GetVipInfoResponseCallback errorCode = ", Integer.valueOf(i), " mResultType = ", Integer.valueOf(this.c));
                    dUw_(i, obj, personalCenterFragment, handler);
                    return;
                }
            }
            LogUtil.h("UIME_PersonalCenterFragment", "personalCenterFragment is null");
        }

        private void dUw_(int i, Object obj, PersonalCenterFragment personalCenterFragment, Handler handler) {
            Message obtainMessage = handler.obtainMessage();
            int i2 = this.c;
            if (i2 == 3) {
                if (i == 0 && (obj instanceof List)) {
                    List<MessageObject> e = gpn.e((List<MemberMessage>) obj, false);
                    personalCenterFragment.ac.clear();
                    if (koq.c(e)) {
                        personalCenterFragment.ac.addAll(e);
                        LogUtil.a("UIME_PersonalCenterFragment", "handleResultType mMessageObjects size", Integer.valueOf(personalCenterFragment.ac.size()));
                        personalCenterFragment.x();
                        return;
                    }
                    LogUtil.h("UIME_PersonalCenterFragment", "handleResultType getVipMessages is empty");
                    return;
                }
                LogUtil.h("UIME_PersonalCenterFragment", "handleResultType getVipMessages errorCode:", Integer.valueOf(i));
                return;
            }
            if (i2 == 4) {
                if (i == 0 && koq.e(obj, DeviceBenefits.class)) {
                    obtainMessage.obj = (List) obj;
                }
                obtainMessage.what = a.M;
                handler.sendMessage(obtainMessage);
                return;
            }
            if (i2 == 6) {
                if (i == 0 && (obj instanceof List)) {
                    PersonalCenterFragment.a((List<MemberMessage>) obj);
                    return;
                } else {
                    LogUtil.h("UIME_PersonalCenterFragment", "handleResultType getBenefitMessages errorCode:", Integer.valueOf(i));
                    return;
                }
            }
            LogUtil.h("UIME_PersonalCenterFragment", "handleResultType GetVipInfoResponseCallback error type");
        }
    }

    static class d implements Observer {
        private final WeakReference<PersonalCenterFragment> c;

        d(PersonalCenterFragment personalCenterFragment) {
            this.c = new WeakReference<>(personalCenterFragment);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            PersonalCenterFragment personalCenterFragment = this.c.get();
            if (personalCenterFragment == null) {
                ReleaseLogUtil.a("UIME_PersonalCenterFragment", "InnerObserver notify fragment is null");
                return;
            }
            LogUtil.a("UIME_PersonalCenterFragment", "InnerObserver notify label ", str, " objects ", objArr);
            if (TextUtils.isEmpty(str) || !str.equals(ObserveLabels.OPERATION_FUNCTION_ENTRANCE) || koq.b(objArr, 1)) {
                return;
            }
            Message obtainMessage = personalCenterFragment.t.obtainMessage();
            obtainMessage.what = a.D;
            obtainMessage.obj = objArr[1];
            personalCenterFragment.t.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List<MemberMessage> list) {
        List<MessageObject> e2 = gpn.e(list, false);
        if (koq.c(e2)) {
            LogUtil.a("UIME_PersonalCenterFragment", "getBenefitMessages size= ", Integer.valueOf(e2.size()));
            ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).insertMessages(e2);
        }
    }

    private void z() {
        H5ProPkgPreloadSyncTask.startTask(BaseApplication.getContext(), "com.huawei.health.h5.vip", new H5PreloadIntervalStrategy(3600000L));
        H5ProPkgPreloadSyncTask.startTask(BaseApplication.getContext(), "com.huawei.health.h5.groups", new H5PreloadCountStrategy(1));
        H5ProPkgPreloadSyncTask.startTask(BaseApplication.getContext(), "com.huawei.health.h5.my-annual-flag", new H5PreloadIntervalStrategy(3600000L));
        H5ProPkgPreloadSyncTask.startTask(BaseApplication.getContext(), KakaConstants.KAKA_H5_PACKAGE_NAME, new H5PreloadIntervalStrategy(86400000L));
    }

    private void b(Map<Integer, List<sch>> map) {
        LogUtil.a("UIME_PersonalCenterFragment", "handlePlanData");
        sbd sbdVar = this.z;
        if (sbdVar == null || sbdVar.f() == null || this.z.j() == null) {
            LogUtil.h("UIME_PersonalCenterFragment", "PlanPager or MyCourse item is null ");
            return;
        }
        if (map == null || map.size() == 0) {
            LogUtil.a("UIME_PersonalCenterFragment", "handlePlanData dataMaps is empty");
            this.z.f().c(false);
            this.z.j().c(true);
        } else {
            LogUtil.a("UIME_PersonalCenterFragment", "handlePlanData dataMaps is not empty");
            List<sch> list = map.containsKey(0) ? map.get(0) : null;
            List<sch> list2 = map.containsKey(1) ? map.get(1) : null;
            List<sch> list3 = map.containsKey(2) ? map.get(2) : null;
            if (koq.b(list) && koq.b(list2) && koq.b(list3)) {
                LogUtil.a("UIME_PersonalCenterFragment", "handlePlanData all tab has non data");
                this.z.f().c(false);
                this.z.j().c(true);
            } else {
                LogUtil.a("UIME_PersonalCenterFragment", "handlePlanData show plan tab");
                this.z.f().c(true);
                this.z.f().c(map);
                this.z.j().c(false);
            }
        }
        aa();
    }
}
