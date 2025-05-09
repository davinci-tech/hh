package com.huawei.ui.main.stories.history.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.view.AiFitnessPlanEnergyReplacementCard;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.AthleticAbilityEmptyActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.RqNoNetworkActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.RunningDataActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.StateIndexActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxActivity;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelCurrentData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.huawei.ui.main.stories.history.adapter.SportHistoryExpandableListAdapter;
import com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment;
import com.huawei.ui.main.stories.history.fragment.SportHistoryScrollListener;
import com.huawei.ui.main.stories.history.view.MonthTitleItem;
import defpackage.ary;
import defpackage.ase;
import defpackage.fhu;
import defpackage.gge;
import defpackage.ghb;
import defpackage.gnm;
import defpackage.gnp;
import defpackage.gso;
import defpackage.gtc;
import defpackage.gts;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.gxy;
import defpackage.gyg;
import defpackage.gze;
import defpackage.hjt;
import defpackage.hkc;
import defpackage.hln;
import defpackage.hps;
import defpackage.hqa;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdl;
import defpackage.knu;
import defpackage.koq;
import defpackage.kor;
import defpackage.kpm;
import defpackage.kwm;
import defpackage.kww;
import defpackage.kwy;
import defpackage.kxb;
import defpackage.mcv;
import defpackage.nrw;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.qkh;
import defpackage.qrv;
import defpackage.rdg;
import defpackage.rdj;
import defpackage.rdn;
import defpackage.rdo;
import defpackage.rdr;
import defpackage.rdu;
import defpackage.rdx;
import defpackage.ruf;
import defpackage.scu;
import defpackage.sdm;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.StorageResult;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes7.dex */
public class SportHistoryListFragment extends Fragment {

    /* renamed from: a, reason: collision with root package name */
    private static long f10304a = -1;
    private static final Object b = new Object();
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private FrameLayout ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private volatile boolean ai;
    private RelativeLayout aj;
    private int ak;
    private long al;
    private final Map<Integer, Integer> am;
    private LinearLayout an;
    private f ao;
    private int ap;
    private int aq;
    private HashMap<Long, Long> ar;
    private ImageView as;
    private int at;
    private i au;
    private l av;
    private ImageView aw;
    private int ax;
    private View ay;
    private String[] az;
    private String ba;
    private int bb;
    private float bc;
    private HealthTextView bd;
    private HealthTextView be;
    private RunningStateIndexData bf;
    private String bg;
    private HealthTextView bh;
    private ExpandableListView bi;
    private int bj;
    private SportHistoryScrollListener bk;
    private int bl;
    private m bm;
    private HealthTextView bn;
    private int bo;
    private String bp;
    private HealthTextView bq;
    private View br;
    private final int bs;
    private List<rdo> bt;
    private IBaseResponseCallback bu;
    private String bv;
    private HealthTextView bw;
    private View bx;
    private Handler by;
    private int bz;
    private View c;
    private HealthTextView ca;
    private long cb;
    private HealthTextView cc;
    private int cd;
    private final List<MonthTitleItem.e> ce;
    private String cf;
    private HealthTextView cg;
    private TextView ci;
    private LinearLayout cj;
    private LinearLayout ck;
    private final rdx cl;
    private int d;
    private CopyOnWriteArrayList<rdo> e;
    private g f;
    private int g;
    private ViewGroup h;
    private HealthButton i;
    private Context j;
    private b k;
    private int l;
    private long m;
    private int n;
    private int o;
    private SportHistoryExpandableListAdapter p;
    private String q;
    private ExtendHandler r;
    private int s;
    private long t;
    private ViewStub u;
    private View v;
    private int w;
    private FrameLayout x;
    private HealthSpinner y;
    private int z;

    public interface IMultiResponseCallback {
        void onNotify(int[] iArr, Object[] objArr);
    }

    public SportHistoryListFragment(int i2) {
        this.bs = jdl.d();
        this.af = false;
        this.e = new CopyOnWriteArrayList<>();
        this.bt = new ArrayList(10);
        this.r = null;
        this.av = new l(this);
        this.k = new b();
        this.ao = new f(this);
        this.au = new i(this);
        this.bg = "--";
        this.ba = "--";
        this.bv = "--";
        this.bp = "--";
        this.cf = "--";
        this.al = 0L;
        this.ag = true;
        this.g = 0;
        this.o = 0;
        this.ah = false;
        this.ak = -1;
        this.ai = false;
        this.z = 0;
        this.n = -1;
        this.l = -1;
        this.t = 0L;
        this.m = 0L;
        this.ad = false;
        this.f = new g(this);
        this.bm = new m();
        this.aq = -1;
        this.ap = 0;
        this.ar = new HashMap<>();
        this.bl = 0;
        this.ce = new ArrayList(10);
        this.cl = new rdx();
        this.ax = 0;
        this.am = new HashMap();
        this.g = i2;
    }

    public SportHistoryListFragment() {
        this.bs = jdl.d();
        this.af = false;
        this.e = new CopyOnWriteArrayList<>();
        this.bt = new ArrayList(10);
        this.r = null;
        this.av = new l(this);
        this.k = new b();
        this.ao = new f(this);
        this.au = new i(this);
        this.bg = "--";
        this.ba = "--";
        this.bv = "--";
        this.bp = "--";
        this.cf = "--";
        this.al = 0L;
        this.ag = true;
        this.g = 0;
        this.o = 0;
        this.ah = false;
        this.ak = -1;
        this.ai = false;
        this.z = 0;
        this.n = -1;
        this.l = -1;
        this.t = 0L;
        this.m = 0L;
        this.ad = false;
        this.f = new g(this);
        this.bm = new m();
        this.aq = -1;
        this.ap = 0;
        this.ar = new HashMap<>();
        this.bl = 0;
        this.ce = new ArrayList(10);
        this.cl = new rdx();
        this.ax = 0;
        this.am = new HashMap();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("Track_SportHistoryListFragment", "onCreateView");
        this.j = getActivity();
        if (bundle != null) {
            int i2 = bundle.getInt(BleConstants.SPORT_TYPE);
            this.g = i2;
            LogUtil.a("Track_SportHistoryListFragment", "mCurSportType:", Integer.valueOf(i2));
        }
        t();
        r();
        View inflate = layoutInflater.inflate(R.layout.fragment_sport_history_list, viewGroup, false);
        dLm_(inflate);
        q();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.a("Track_SportHistoryListFragment", "onSaveInstanceState");
        bundle.putInt(BleConstants.SPORT_TYPE, this.g);
    }

    private void r() {
        LogUtil.a("Track_SportHistoryListFragment", "initData");
        this.bl = this.bs + 1;
        this.r.postTask(new Runnable() { // from class: rdd
            @Override // java.lang.Runnable
            public final void run() {
                SportHistoryListFragment.this.e();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("input_sport_history_start_time");
        LocalBroadcastManager.getInstance(this.j).registerReceiver(this.f, intentFilter);
        hjt.d();
    }

    public /* synthetic */ void e() {
        gwg.b(this.j);
    }

    private void q() {
        int i2 = this.g;
        if (i2 == 0) {
            b(i2);
        }
    }

    private void dLm_(View view) {
        LogUtil.a("Track_SportHistoryListFragment", "initView");
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.list_sport_record_simplify);
        this.bi = expandableListView;
        BaseActivity.cancelLayoutById(expandableListView);
        SportHistoryExpandableListAdapter sportHistoryExpandableListAdapter = new SportHistoryExpandableListAdapter(getContext(), this);
        this.p = sportHistoryExpandableListAdapter;
        this.bi.setAdapter(sportHistoryExpandableListAdapter);
        View inflate = LayoutInflater.from(this.j).inflate(R.layout.layout_sport_history_list_header, (ViewGroup) null);
        dLl_(inflate);
        this.bi.addHeaderView(inflate);
        this.bi.setSelector(new ColorDrawable(0));
        SportHistoryScrollListener sportHistoryScrollListener = new SportHistoryScrollListener(new SportHistoryScrollListener.SportScrollCallback() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.4
            @Override // com.huawei.ui.main.stories.history.fragment.SportHistoryScrollListener.SportScrollCallback
            public void onResponse(long j2, boolean z) {
                if (koq.c(SportHistoryListFragment.this.e) && z) {
                    long j3 = j2 - 1;
                    LogUtil.a("Track_SportHistoryListFragment", "search by scroll ", Long.valueOf(j3));
                    SportHistoryListFragment.this.z = -2;
                    SportHistoryListFragment.this.c(jdl.s(j2), j3);
                }
            }

            @Override // com.huawei.ui.main.stories.history.fragment.SportHistoryScrollListener.SportScrollCallback
            public void onScroll(int i2) {
                if (SportHistoryListFragment.this.ax != i2) {
                    SportHistoryListFragment.this.o(i2);
                    SportHistoryListFragment.this.ax = i2;
                }
            }
        }, this.e);
        this.bk = sportHistoryScrollListener;
        this.bi.setOnScrollListener(sportHistoryScrollListener);
        ScrollUtil.cKx_(this.bi, getActivity().getWindow().getDecorView(), IEventListener.EVENT_ID_DEVICE_DISCONN_SUCC);
        this.an = (LinearLayout) view.findViewById(R.id.no_sport_history_layout);
        this.i = (HealthButton) view.findViewById(R.id.sport_history_to_exercise);
        this.aj = (RelativeLayout) view.findViewById(R.id.hw_sport_history_loading);
        ((HealthProgressBar) view.findViewById(R.id.hw_sport_history_loading_view)).setLayerType(1, null);
        as();
        am();
    }

    private void x() {
        this.am.clear();
        int i2 = 0;
        for (int i3 = 0; i3 < this.e.size(); i3++) {
            i2++;
            this.am.put(Integer.valueOf(i2), Integer.valueOf(i3));
            rdo rdoVar = this.e.get(i3);
            if (rdoVar.a()) {
                for (int i4 = 0; i4 < rdoVar.d(); i4++) {
                    i2++;
                    this.am.put(Integer.valueOf(i2), Integer.valueOf(-i3));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(int i2) {
        Integer num;
        if (getActivity() instanceof SportHistoryActivity) {
            SportHistoryActivity sportHistoryActivity = (SportHistoryActivity) getActivity();
            if (i2 == 0) {
                sportHistoryActivity.c(8);
            } else {
                sportHistoryActivity.c(0);
            }
            if (this.am.containsKey(Integer.valueOf(i2)) && (num = this.am.get(Integer.valueOf(i2))) != null) {
                if (num.intValue() < 0) {
                    int abs = Math.abs(num.intValue());
                    int i3 = this.bj;
                    if (abs == i3) {
                        return;
                    }
                    int i4 = i3 - 1;
                    if (this.ax <= i2 || i4 < 0) {
                        return;
                    }
                    n(i4);
                    return;
                }
                n(num.intValue());
            }
        }
    }

    private void n(int i2) {
        if (i2 < this.e.size()) {
            rdo rdoVar = this.e.get(i2);
            if (!(getActivity() instanceof SportHistoryActivity) || rdoVar == null || this.p == null) {
                return;
            }
            ((SportHistoryActivity) getActivity()).b(rdoVar.i(), this.p.b(rdoVar));
            this.bj = i2;
        }
    }

    private void dLl_(View view) {
        this.ck = (LinearLayout) view.findViewById(R.id.ll_year_title);
        this.aw = (ImageView) view.findViewById(R.id.iv_arrow_left);
        this.as = (ImageView) view.findViewById(R.id.iv_arrow_right);
        Drawable dLj_ = dLj_(R.drawable._2131427844_res_0x7f0b0204);
        if (dLj_ != null) {
            this.aw.setBackground(dLj_);
            this.as.setBackground(dLj_);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_year_title);
        this.ci = textView;
        if (this.bl > this.bs) {
            this.as.setVisibility(4);
            this.ci.setText(R$string.IDS_hw_show_sport_history_overview);
        } else {
            textView.setText(getString(R$string.IDS_history_overview_year, Integer.valueOf(this.bs)));
        }
        this.cj = (LinearLayout) view.findViewById(R.id.year_history_title);
        this.c = view.findViewById(R.id.v_bottom_spacing);
        this.x = (FrameLayout) view.findViewById(R.id.ll_fitness_class_header);
        this.ae = (FrameLayout) view.findViewById(R.id.item_rq_quick_entry);
        this.y = (HealthSpinner) view.findViewById(R.id.hs_fitness_class_spinner);
        this.cc = (HealthTextView) view.findViewById(R.id.vo2max_quick_entry_value);
        this.cg = (HealthTextView) view.findViewById(R.id.vo2max_quick_entry_unit);
        this.bx = view.findViewById(R.id.sport_ability_vo2max_icon);
        this.ca = (HealthTextView) view.findViewById(R.id.vo2max_quick_entry_no_record);
        this.bw = (HealthTextView) view.findViewById(R.id.state_quick_entry_value);
        this.bq = (HealthTextView) view.findViewById(R.id.state_quick_entry_level);
        this.br = view.findViewById(R.id.sport_ability_state_icon);
        this.bn = (HealthTextView) view.findViewById(R.id.state_quick_entry_no_record);
        this.be = (HealthTextView) view.findViewById(R.id.running_quick_entry_value);
        this.bh = (HealthTextView) view.findViewById(R.id.running_quick_entry_level);
        this.ay = view.findViewById(R.id.sport_ability_run_icon);
        this.bd = (HealthTextView) view.findViewById(R.id.running_quick_entry_no_record);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.vo2max_layout);
        HealthCardView healthCardView2 = (HealthCardView) view.findViewById(R.id.state_layout);
        HealthCardView healthCardView3 = (HealthCardView) view.findViewById(R.id.running_layout);
        this.cj.setOnClickListener(new View.OnClickListener() { // from class: rcp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.this.dLA_(view2);
            }
        });
        view.findViewById(R.id.iv_arrow_left).setOnClickListener(new View.OnClickListener() { // from class: rcr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.this.dLB_(view2);
            }
        });
        view.findViewById(R.id.iv_arrow_right).setOnClickListener(new View.OnClickListener() { // from class: rcq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.this.dLC_(view2);
            }
        });
        if (nsn.r()) {
            dLv_(view);
        }
        healthCardView.setOnClickListener(new View.OnClickListener() { // from class: rcu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.this.dLD_(view2);
            }
        });
        healthCardView2.setOnClickListener(new View.OnClickListener() { // from class: rct
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.this.dLE_(view2);
            }
        });
        healthCardView3.setOnClickListener(new View.OnClickListener() { // from class: rcs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.this.dLF_(view2);
            }
        });
        this.u = (ViewStub) view.findViewById(R.id.golf_year_brief_stub);
    }

    public /* synthetic */ void dLA_(View view) {
        FragmentActivity activity = getActivity();
        if (activity instanceof SportHistoryActivity) {
            ((SportHistoryActivity) activity).d(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dLB_(View view) {
        w();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dLC_(View view) {
        v();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dLD_(View view) {
        ak();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dLE_(View view) {
        an();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dLF_(View view) {
        aj();
        ViewClickInstrumentation.clickOnView(view);
    }

    private Drawable dLj_(int i2) {
        if (!LanguageUtil.bc(this.j)) {
            return null;
        }
        BitmapDrawable cKn_ = nrz.cKn_(this.j, i2);
        LogUtil.a("Track_SportHistoryListFragment", "decode  ", cKn_);
        return cKn_;
    }

    private void dLv_(View view) {
        ((HealthTextView) view.findViewById(R.id.vo2max_title)).setTextSize(1, 24.0f);
        ((HealthTextView) view.findViewById(R.id.state_title)).setTextSize(1, 24.0f);
        ((HealthTextView) view.findViewById(R.id.running_title)).setTextSize(1, 24.0f);
        this.cg.setTextSize(1, 24.0f);
        this.cc.setTextSize(1, 40.0f);
        this.ca.setTextSize(1, 24.0f);
        this.bw.setTextSize(1, 40.0f);
        this.bq.setTextSize(1, 24.0f);
        this.bn.setTextSize(1, 24.0f);
        this.be.setTextSize(1, 40.0f);
        this.bh.setTextSize(1, 24.0f);
        this.bd.setTextSize(1, 24.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        ReleaseLogUtil.e("Track_SportHistoryListFragment", "setPropertyValueAndStyle  mVo2maxValue ", Integer.valueOf(this.cd), "mStateValue ", this.bv, "mStateLevel ", this.bp, "mRunningValue ", this.bg, "mRunningLevel ", this.ba);
        int i2 = this.cd;
        if (i2 > 0) {
            this.cc.setText(UnitUtil.e(i2, 1, 0));
            this.cg.setText(this.cf);
            this.cc.setVisibility(0);
            this.cg.setVisibility(0);
            this.ca.setVisibility(8);
            this.bx.getBackground().setTint(this.bz);
            this.ab = true;
        } else {
            this.ca.setVisibility(0);
            this.cc.setVisibility(8);
            this.cg.setVisibility(8);
            this.ca.setVisibility(0);
            this.ab = false;
        }
        if ("--".equals(this.bv) || "--".equals(this.bp)) {
            this.bw.setVisibility(8);
            this.bq.setVisibility(8);
            this.bn.setVisibility(0);
            this.ac = false;
        } else {
            this.bw.setText(this.bv);
            this.bq.setText(this.bp);
            this.bw.setVisibility(0);
            this.bq.setVisibility(0);
            this.br.getBackground().setTint(this.bo);
            this.bn.setVisibility(8);
            this.ac = true;
        }
        if ("--".equals(this.bg) || "--".equals(this.ba)) {
            this.be.setVisibility(8);
            this.bh.setVisibility(8);
            this.bd.setVisibility(0);
            this.aa = false;
        } else {
            this.be.setText(this.bg);
            this.bh.setText(this.ba);
            this.be.setVisibility(0);
            this.bh.setVisibility(0);
            this.ay.getBackground().setTint(this.bb);
            this.bd.setVisibility(8);
            this.aa = true;
        }
        al();
    }

    private void al() {
        if (!this.ab && !this.ac && !this.aa) {
            this.ae.setVisibility(8);
            return;
        }
        if (this.ae.getVisibility() == 8) {
            this.ae.setVisibility(0);
            this.c.setVisibility(0);
            this.an.setVisibility(8);
            d(this.ab, 1);
            d(this.ac, 2);
            d(this.aa, 3);
        }
    }

    private void w() {
        if (nsn.a(500)) {
            LogUtil.h("Track_SportHistoryListFragment", "click too fast");
            return;
        }
        int i2 = this.bl;
        if (i2 <= 2015) {
            this.bl = 2014;
            this.aw.setVisibility(4);
        } else {
            this.bl = i2 - 1;
        }
        this.ci.setText(getString(R$string.IDS_history_overview_year, Integer.valueOf(this.bl)));
        this.as.setVisibility(0);
        i(this.bl);
        af();
    }

    private void v() {
        if (nsn.a(500)) {
            LogUtil.h("Track_SportHistoryListFragment", "click too fast");
            return;
        }
        int i2 = this.bl;
        int i3 = this.bs;
        if (i2 >= i3) {
            this.bl = i3 + 1;
            this.as.setVisibility(4);
            this.ci.setText(R$string.IDS_hw_show_sport_history_overview);
        } else {
            this.bl = i2 + 1;
            this.ci.setText(getString(R$string.IDS_history_overview_year, Integer.valueOf(this.bl)));
        }
        this.aw.setVisibility(0);
        i(this.bl);
        af();
    }

    private void af() {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.g));
        ixx.d().d(getContext(), AnalyticsValue.SPORT_RECORD_YEAR_SWITCH_2040185.value(), hashMap, 0);
    }

    private void d(boolean z, int i2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("isHaveData", Boolean.valueOf(z));
        hashMap.put("dataType", Integer.valueOf(i2));
        ixx.d().d(getContext(), AnalyticsValue.SPORT_RECORD_ATHLETIC_ABILITY_CARD_2040191.value(), hashMap, 0);
    }

    private void ak() {
        if (nsn.a(500)) {
            LogUtil.h("Track_SportHistoryListFragment", "click too fast");
            return;
        }
        HealthDatasActivity.b(1, 1);
        Intent intent = new Intent();
        if (this.ab) {
            intent.setClass(this.j, Vo2maxActivity.class);
            intent.putExtra("vo2max_value", this.cd);
            intent.putExtra("running_level_data", this.bf);
            intent.putExtra("vo2max_time", this.cb);
            intent.putExtra("from_sport_list_fragment", true);
            startActivityForResult(intent, 1001);
            return;
        }
        intent.setClass(this.j, AthleticAbilityEmptyActivity.class);
        intent.putExtra("athletic_ability_empty_flag", "vo_to_max_no_record");
        gnm.aPB_(this.j, intent);
    }

    private void aj() {
        if (nsn.a(500)) {
            LogUtil.h("Track_SportHistoryListFragment", "click too fast");
            return;
        }
        HealthDatasActivity.b(3, 1);
        if (!gyg.b(this.j)) {
            Intent intent = new Intent(this.j, (Class<?>) RqNoNetworkActivity.class);
            intent.putExtra("title_name", getString(R$string.IDS_data_running_index_title_outside));
            gnm.aPB_(this.j, intent);
            return;
        }
        Intent intent2 = new Intent();
        if (this.aa) {
            intent2.setClass(this.j, RunningDataActivity.class);
            intent2.putExtra("running_level_data", this.bf);
            intent2.putExtra("KEY_RUNNING_FLAG", "RQ_RUNNING");
            intent2.putExtra("vo2max_value", this.cd);
            startActivityForResult(intent2, 1001);
            return;
        }
        intent2.setClass(this.j, AthleticAbilityEmptyActivity.class);
        intent2.putExtra("athletic_ability_empty_flag", "running_no_record");
        gnm.aPB_(this.j, intent2);
    }

    private void an() {
        if (nsn.a(500)) {
            LogUtil.h("Track_SportHistoryListFragment", "click too fast");
            return;
        }
        HealthDatasActivity.b(2, 1);
        if (!gyg.b(this.j)) {
            Intent intent = new Intent(this.j, (Class<?>) RqNoNetworkActivity.class);
            intent.putExtra("title_name", this.j.getString(R$string.IDS_data_state_index_title));
            gnm.aPB_(this.j, intent);
            return;
        }
        Intent intent2 = new Intent();
        if (this.ac) {
            intent2.setClass(this.j, StateIndexActivity.class);
            intent2.putExtra("state_index_level_data", this.bf);
        } else {
            intent2.setClass(this.j, AthleticAbilityEmptyActivity.class);
            intent2.putExtra("athletic_ability_empty_flag", "state_no_record");
        }
        gnm.aPB_(this.j, intent2);
    }

    private void aa() {
        boolean l2 = Utils.l();
        if (l2 || this.g != 258) {
            ReleaseLogUtil.e("Track_SportHistoryListFragment", "requestRqData mIsOverseaBrowseMode ", Boolean.valueOf(l2));
            return;
        }
        m();
        int c2 = DateFormatUtil.c(System.currentTimeMillis(), TimeZone.getDefault());
        new hqa().a(this.j, c2, c2, 0, new j(this));
        qkh.c().c(new h(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        RunningLevelCurrentData runningLevelCurrentData = this.bf.getRunningLevelCurrentData();
        if (runningLevelCurrentData == null || runningLevelCurrentData.getLastCurrentRunLevel() <= 0.0f) {
            LogUtil.b("Track_SportHistoryListFragment", "userRunLevelData == null or lastCurrentRunLevel <= 0");
            return;
        }
        this.bc = runningLevelCurrentData.getLastCurrentRunLevel();
        au();
        this.bg = UnitUtil.e(UnitUtil.a(runningLevelCurrentData.getLastCurrentRunLevel(), 1), 1, 1);
        this.bv = UnitUtil.e(UnitUtil.a(runningLevelCurrentData.getLastCondition(), 1), 1, 1);
        this.bp = ruf.g(runningLevelCurrentData.getLastCondition());
        this.bo = ruf.i(runningLevelCurrentData.getLastCondition());
    }

    private void au() {
        if (this.w == 0) {
            this.ba = ruf.f(this.bc);
            this.bb = ruf.b(this.bc);
        } else {
            this.ba = ruf.e(this.bc);
            this.bb = ruf.d(this.bc);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av() {
        this.bz = ruf.b(qrv.a(this.w, this.d), this.cd);
    }

    private void m() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005);
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1006);
        this.w = CommonUtil.e(accountInfo, 0);
        int e2 = CommonUtil.e(accountInfo2, 0);
        this.d = e2 != 0 ? (DateFormatUtil.b(System.currentTimeMillis()) - e2) / 10000 : 0;
    }

    public void e(int i2, int i3) {
        if (i2 == this.w && i3 == this.d) {
            return;
        }
        this.w = i2;
        this.d = i3;
        au();
        av();
        ai();
    }

    static class j implements IBaseResponseCallback {
        private WeakReference<SportHistoryListFragment> b;

        j(SportHistoryListFragment sportHistoryListFragment) {
            this.b = new WeakReference<>(sportHistoryListFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            SportHistoryListFragment sportHistoryListFragment = this.b.get();
            if (sportHistoryListFragment != null && i == 200 && (obj instanceof GetRunLevelRsp)) {
                sportHistoryListFragment.bf = ruf.c((GetRunLevelRsp) obj);
                sportHistoryListFragment.u();
                sportHistoryListFragment.by.sendEmptyMessage(11);
            }
        }
    }

    static class h implements IBaseResponseCallback {
        private WeakReference<SportHistoryListFragment> c;

        h(SportHistoryListFragment sportHistoryListFragment) {
            this.c = new WeakReference<>(sportHistoryListFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            SportHistoryListFragment sportHistoryListFragment = this.c.get();
            if (sportHistoryListFragment != null && i == 0 && (obj instanceof Vo2maxDetail)) {
                Vo2maxDetail vo2maxDetail = (Vo2maxDetail) obj;
                sportHistoryListFragment.cd = vo2maxDetail.getVo2maxValue();
                sportHistoryListFragment.cb = vo2maxDetail.getTimestamp();
                sportHistoryListFragment.cf = sportHistoryListFragment.j.getString(R$string.IDS_hwh_health_vo2max_unit);
                sportHistoryListFragment.av();
                sportHistoryListFragment.by.sendEmptyMessage(11);
            }
        }
    }

    public HealthSpinner a() {
        return this.y;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1 && i2 == 1001 && intent != null) {
            e(intent.getIntExtra("KEY_RUNNING_GENDER", 1), intent.getIntExtra("KEY_RUNNING_AGE", 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        LogUtil.a("Track_SportHistoryListFragment", "startLoadingImage");
        this.aj.setVisibility(0);
    }

    private void d(long j2) {
        this.by.sendEmptyMessageDelayed(111, j2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Handler handler = this.by;
        if (handler != null && !this.ag) {
            handler.sendEmptyMessage(112);
        }
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a("Track_SportHistoryListFragment", "onPause");
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        aq();
        LogUtil.a("Track_SportHistoryListFragment", "onStop");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aq() {
        LogUtil.a("Track_SportHistoryListFragment", "stopLoadingImage");
        RelativeLayout relativeLayout = this.aj;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        z();
    }

    private void z() {
        IBaseResponseCallback iBaseResponseCallback = this.bu;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a("Track_SportHistoryListFragment", "onDestroy");
        super.onDestroy();
        ap();
        LocalBroadcastManager.getInstance(this.j).unregisterReceiver(this.f);
        ExpandableListView expandableListView = this.bi;
        if (expandableListView != null) {
            expandableListView.setOnGroupClickListener(null);
            this.bi.setOnChildClickListener(null);
            this.bi.setOnItemLongClickListener(null);
            this.bi = null;
        }
    }

    private void t() {
        LogUtil.a("Track_SportHistoryListFragment", "initThread");
        this.r = HandlerCenter.yt_(new e(this), "TrackSportHistoryFragThread");
        this.by = new o(this);
    }

    private void ap() {
        LogUtil.a("Track_SportHistoryListFragment", "stopThread");
        this.r.quit(false);
    }

    public void b(int i2) {
        this.g = i2;
        ReleaseLogUtil.e("Track_SportHistoryListFragment", "changeSportType mPreSportType:", Integer.valueOf(this.aq), " mCurSportType", Integer.valueOf(this.g), " getIsSubShow() ", Boolean.valueOf(d()), " mCurSubSportType ", Integer.valueOf(this.o));
        int i3 = this.aq;
        int i4 = this.g;
        if (i3 != i4) {
            if (i3 == 10001 && d() && this.af) {
                c(false);
            }
            this.aq = this.g;
            if (getActivity() instanceof SportHistoryActivity) {
                ((SportHistoryActivity) getActivity()).e(i2);
            }
            ab();
            ac();
            int i5 = this.g;
            if (i5 != 0) {
                this.az = g(i5);
                return;
            }
            return;
        }
        if (i4 == 10001 && d() && this.o != 0) {
            FragmentActivity activity = getActivity();
            if (activity instanceof SportHistoryActivity) {
                ((SportHistoryActivity) activity).a(0);
            }
        }
    }

    private void i(int i2) {
        this.bl = i2;
        if (this.at != i2) {
            this.at = i2;
            ab();
            ac();
        }
    }

    private void ab() {
        h();
        long a2 = this.bl > this.bs ? 0L : jdl.a(System.currentTimeMillis(), this.bl - this.bs);
        long a3 = this.bl >= this.bs ? jdl.a(System.currentTimeMillis()) : jdl.b(a2);
        int i2 = this.ap;
        if (i2 != 0) {
            a(this.g, i2, a2, a3);
            return;
        }
        c(this.g, a2, a3);
        c(a2, a3);
        e(a2, a3);
    }

    private void e(final long j2, final long j3) {
        ExtendHandler extendHandler = this.r;
        if (extendHandler != null) {
            extendHandler.postTask(new Runnable() { // from class: rdc
                @Override // java.lang.Runnable
                public final void run() {
                    SportHistoryListFragment.this.b(j2, j3);
                }
            });
        }
    }

    public /* synthetic */ void b(long j2, long j3) {
        if (this.g == 258) {
            aa();
        }
        scu.dWa_(j2, j3, this.g, this.bl, this.by, 13);
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        this.bu = iBaseResponseCallback;
    }

    public void a(int i2) {
        this.o = i2;
        LogUtil.a("Track_SportHistoryListFragment", "changeSubSportType mPreSportType:", Integer.valueOf(this.aq));
        if (this.ap == this.o) {
            return;
        }
        h();
        this.ap = this.o;
        long a2 = this.bl > this.bs ? 0L : jdl.a(System.currentTimeMillis(), this.bl - this.bs);
        a(this.g, i2, a2, this.bl >= this.bs ? jdl.a(System.currentTimeMillis()) : jdl.b(a2));
    }

    public void d(int i2) {
        this.o = i2;
        LogUtil.a("Track_SportHistoryListFragment", "updateSubSportType mPreSportType:", Integer.valueOf(this.aq));
        int i3 = this.ap;
        int i4 = this.o;
        if (i3 == i4) {
            return;
        }
        this.ap = i4;
    }

    private void ac() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: rde
            @Override // java.lang.Runnable
            public final void run() {
                SportHistoryListFragment.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        LogUtil.a("Track_SportHistoryListFragment", "requestYearData()");
        long a2 = this.bl > this.bs ? 0L : jdl.a(System.currentTimeMillis(), this.bl - this.bs);
        long b2 = this.bl >= this.bs ? jdl.b(System.currentTimeMillis()) : jdl.b(a2);
        this.ce.clear();
        this.bm.c(this.g, a2, b2, this.au);
    }

    private void c(int i2, long j2, long j3) {
        LogUtil.a("Track_SportHistoryListFragment", "requestMonthData");
        this.bm.a(i2, 0, j2, j3, d(), this.ao);
    }

    private void a(int i2, int i3, long j2, long j3) {
        LogUtil.a("Track_SportHistoryListFragment", "requestSubMonthData");
        this.bm.a(i2, i3, j2, j3, d(), this.ao);
        c(j2, j3);
    }

    public void a(long j2, long j3) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    if (SportHistoryListFragment.this.bt.size() == 0) {
                        SportHistoryListFragment.this.ah();
                        SportHistoryListFragment.this.aq();
                    }
                }
            });
            LogUtil.a("Track_SportHistoryListFragment", "refreshSportData get simply ");
            c(j2, j3);
            return;
        }
        ReleaseLogUtil.d("Track_SportHistoryListFragment", "refreshSportData is null,stop refresh");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j2, long j3) {
        kwy d2 = new kwy.a().a(j2).e(j3).d(this.g).a(this.o).b(15).d();
        this.bm.a(d2, new k(d2, this));
    }

    private int b(List<rdo> list) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            i2 += list.get(i3).j();
            if (i2 >= 16) {
                return i3;
            }
        }
        return list.size() - 1;
    }

    static class e extends BaseHandlerCallback<SportHistoryListFragment> {
        public e(SportHistoryListFragment sportHistoryListFragment) {
            super(sportHistoryListFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: dLM_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(SportHistoryListFragment sportHistoryListFragment, Message message) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            int i = message.what;
            if (i == 3) {
                SportHistoryListFragment.dLh_(message, sportHistoryListFragment, planApi);
                return true;
            }
            if (i == 10) {
                sportHistoryListFragment.dLg_(message.getData());
                if (planApi == null) {
                    return true;
                }
                planApi.deletePlanExerciseRecord(String.valueOf(sportHistoryListFragment.m));
                return true;
            }
            if (i == 14) {
                LogUtil.a("Track_SportHistoryListFragment", "FOR MSG_DOWNLOAD_DEVICE_SOURCE ");
                ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByDeviceType(String.valueOf(message.obj));
                return true;
            }
            if (i == 201) {
                if (message.obj instanceof RelativeSportData) {
                    RelativeSportData relativeSportData = (RelativeSportData) message.obj;
                    hps.a(relativeSportData.getStartTime(), relativeSportData.getEndTime(), sportHistoryListFragment.av);
                }
                LogUtil.a("Track_SportHistoryListFragment", "FOR MSG_TRIATHLON_DETAIL_TRACK");
                return true;
            }
            if (i == 5) {
                sportHistoryListFragment.dLq_(message);
                sportHistoryListFragment.ar();
                return true;
            }
            if (i == 6) {
                sportHistoryListFragment.dLr_(message);
                return true;
            }
            if (i != 7) {
                return false;
            }
            sportHistoryListFragment.dLs_(message);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dLh_(Message message, SportHistoryListFragment sportHistoryListFragment, PlanApi planApi) {
        if (sportHistoryListFragment == null || message == null) {
            return;
        }
        sportHistoryListFragment.d(message.arg1, sportHistoryListFragment.t, sportHistoryListFragment.m);
        sportHistoryListFragment.dLi_(message, sportHistoryListFragment);
        sportHistoryListFragment.o();
        if (planApi != null) {
            planApi.deletePlanExerciseRecord(sportHistoryListFragment.t + "_" + sportHistoryListFragment.m);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, long j2, long j3) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (!Utils.o()) {
            hashMap.put("startTime", Long.valueOf(j2));
            hashMap.put("endTime", Long.valueOf(j3));
        }
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(i2));
        if (i2 != 10001) {
            hashMap.put("trackType", Integer.valueOf(rdu.d(this.e, this.n, this.l)));
        }
        ixx.d().d(this.j, AnalyticsValue.BI_TRACK_SPORT_DELETE_SPORT_KEY.value(), hashMap, 0);
    }

    private void dLi_(Message message, SportHistoryListFragment sportHistoryListFragment) {
        if (message.arg1 == 512 && (message.obj instanceof Map)) {
            Map map = (Map) message.obj;
            for (Map.Entry entry : map.entrySet()) {
                Long[] lArr = (Long[]) map.get(entry.getKey());
                if (lArr == null || lArr.length < 2 || lArr[0] == null || lArr[1] == null) {
                    return;
                } else {
                    sportHistoryListFragment.d(((Integer) entry.getKey()).intValue(), lArr[0].longValue(), lArr[1].longValue());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        ObserverManagerUtil.c("observer_sport_list_data_change", new Object[0]);
    }

    static class o extends BaseHandler<SportHistoryListFragment> {
        o(SportHistoryListFragment sportHistoryListFragment) {
            super(sportHistoryListFragment);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dLS_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SportHistoryListFragment sportHistoryListFragment, Message message) {
            int i = message.what;
            if (i == 1) {
                dLQ_(sportHistoryListFragment, message);
                return;
            }
            if (i == 2) {
                LogUtil.a("Track_SportHistoryListFragment", "MSG_SHOW_DETAIL_TRACK");
                dLR_(sportHistoryListFragment, message);
                return;
            }
            if (i != 3) {
                if (i == 4) {
                    LogUtil.a("Track_SportHistoryListFragment", "MSG_REFRESH_VIEW_AFTER_INSERT");
                    sportHistoryListFragment.a(message.obj);
                    return;
                }
                if (i == 9) {
                    LogUtil.a("Track_SportHistoryListFragment", "MSG_COPY_SIMPLIFY_DATA_TO_UI_DATA");
                    sportHistoryListFragment.b(message.obj);
                    return;
                }
                if (i != 10) {
                    if (i == 12) {
                        LogUtil.a("Track_SportHistoryListFragment", "MSG_COPY_MONTH_DATA_TO_UI_DATA");
                        sportHistoryListFragment.e(message.obj);
                        return;
                    }
                    if (i == 111) {
                        sportHistoryListFragment.as();
                        sportHistoryListFragment.ag = false;
                        return;
                    } else {
                        if (i == 112) {
                            if (sportHistoryListFragment.ag) {
                                return;
                            }
                            sportHistoryListFragment.aq();
                            if (sportHistoryListFragment.bi != null) {
                                sportHistoryListFragment.bi.setClickable(true);
                            }
                            sportHistoryListFragment.ag = true;
                            return;
                        }
                        dLP_(sportHistoryListFragment, message);
                        return;
                    }
                }
            }
            dLN_(sportHistoryListFragment, message);
        }

        private void dLP_(SportHistoryListFragment sportHistoryListFragment, Message message) {
            int i = message.what;
            if (i == 8) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "MSG_REFRESH_YEAR_TITLE_DATA_TO_UI");
                sportHistoryListFragment.ad();
            } else if (i == 11) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "MSG_REFRESH_RQ_DATA_TO_UI");
                sportHistoryListFragment.ai();
            } else {
                if (i != 13) {
                    return;
                }
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "MSG_REFRESH_GOLF_BRIEF_TO_UI");
                sportHistoryListFragment.a((List<rdg>) (message.obj != null ? (List) message.obj : null));
            }
        }

        private void dLN_(SportHistoryListFragment sportHistoryListFragment, Message message) {
            Object[] objArr = new Object[3];
            objArr[0] = "msg type = ";
            objArr[1] = Integer.valueOf(message.what);
            objArr[2] = message.what == 3 ? "MSG_DELETE_TRACK" : "MSG_DELETE_FITNESS_RECORD";
            LogUtil.a("Track_SportHistoryListFragment", objArr);
            sportHistoryListFragment.l();
            sportHistoryListFragment.b(message.arg1, sportHistoryListFragment.t, sportHistoryListFragment.m);
            sportHistoryListFragment.ae();
        }

        private void d(SportHistoryListFragment sportHistoryListFragment, boolean z) {
            sportHistoryListFragment.c.setVisibility(8);
            sportHistoryListFragment.cj.setVisibility(8);
            sportHistoryListFragment.ae.setVisibility(8);
            if (z) {
                sportHistoryListFragment.ck.setVisibility(8);
            } else {
                sportHistoryListFragment.ck.setVisibility(0);
            }
            if (sportHistoryListFragment.g == 10001) {
                sportHistoryListFragment.x.setVisibility(0);
                sportHistoryListFragment.c.setVisibility(0);
            } else {
                sportHistoryListFragment.x.setVisibility(8);
            }
            if (sportHistoryListFragment.g == 258) {
                sportHistoryListFragment.ai();
            }
            if (sportHistoryListFragment.g == 286) {
                a(sportHistoryListFragment);
            }
        }

        private void a(SportHistoryListFragment sportHistoryListFragment) {
            if (sportHistoryListFragment.v == null) {
                return;
            }
            sportHistoryListFragment.v.setVisibility(8);
        }

        private void dLQ_(SportHistoryListFragment sportHistoryListFragment, Message message) {
            if (message.arg1 == 10001 && message.arg2 == 137) {
                sportHistoryListFragment.af = true;
            }
            LogUtil.a("Track_SportHistoryListFragment", "MSG_SHOW_NO_DATA_LAYOUT");
            sportHistoryListFragment.an.setVisibility(0);
            d(sportHistoryListFragment, false);
            sportHistoryListFragment.p.a();
            sportHistoryListFragment.aq();
        }

        private void dLR_(SportHistoryListFragment sportHistoryListFragment, Message message) {
            if (message == null) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "msg is null");
                return;
            }
            if (hasMessages(2)) {
                removeMessages(2);
            }
            if (!(message.obj instanceof l)) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "msg is not instance of ReadTrackDetailCallback");
                return;
            }
            l lVar = (l) message.obj;
            knu b = lVar.b();
            if (b == null) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "pathData is null");
                return;
            }
            gso.e().init(BaseApplication.getContext());
            MotionPathSimplify b2 = b.b();
            Map d = lVar.d();
            ArrayList arrayList = new ArrayList(10);
            if (d != null) {
                int size = d.size();
                for (int i = 0; i < size; i++) {
                    if (d.get(Integer.valueOf(i)) != null) {
                        arrayList.add((gxy) d.get(Integer.valueOf(i)));
                    }
                }
            }
            gso.e().aTq_(dLO_(sportHistoryListFragment, b.d(), b2, arrayList), b2);
            lVar.d = null;
            lVar.b = true;
            lVar.a().clear();
            lVar.d().clear();
            if (sportHistoryListFragment.h != null) {
                sportHistoryListFragment.h.setBackgroundColor(ContextCompat.getColor(com.huawei.haf.application.BaseApplication.e(), R.color._2131296665_res_0x7f090199));
                sportHistoryListFragment.h = null;
            }
        }

        private Bundle dLO_(SportHistoryListFragment sportHistoryListFragment, String str, MotionPathSimplify motionPathSimplify, List<gxy> list) {
            boolean z;
            gso e = gso.e();
            if (motionPathSimplify.requestSportType() != 512) {
                list = Collections.EMPTY_LIST;
            }
            Bundle aTo_ = e.aTo_(str, motionPathSimplify, list, Boolean.FALSE.booleanValue(), Boolean.FALSE.booleanValue());
            if (motionPathSimplify.requestSportType() == 512) {
                return aTo_;
            }
            FragmentActivity activity = sportHistoryListFragment.getActivity();
            if (activity instanceof SportHistoryActivity) {
                z = ((SportHistoryActivity) activity).a();
                LogUtil.c("Track_SportHistoryListFragment", "SportHistoryActivity ExitApp:", Boolean.valueOf(z));
            } else {
                z = false;
            }
            aTo_.putBoolean("ExitApp", z);
            return aTo_;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(java.lang.Object r3) {
        /*
            r2 = this;
            java.lang.Class<rdo> r0 = defpackage.rdo.class
            boolean r0 = defpackage.koq.e(r3, r0)     // Catch: java.lang.ClassCastException -> Lb
            if (r0 == 0) goto L1b
            java.util.List r3 = (java.util.List) r3     // Catch: java.lang.ClassCastException -> Lb
            goto L1c
        Lb:
            r3 = move-exception
            java.lang.String r0 = "copyMonthDataToUiData "
            java.lang.String r3 = r3.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[]{r0, r3}
            java.lang.String r0 = "Track_SportHistoryListFragment"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r3)
        L1b:
            r3 = 0
        L1c:
            if (r3 != 0) goto L2d
            java.lang.String r3 = "copyMonthDataToUiData sportHistoryExpandableGroupData is null"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r0 = "Track_SportHistoryListFragment"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r3)
            r2.ah()
            return
        L2d:
            java.lang.Object r0 = com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.b
            monitor-enter(r0)
            java.util.concurrent.CopyOnWriteArrayList r1 = new java.util.concurrent.CopyOnWriteArrayList     // Catch: java.lang.Throwable -> L5c
            r1.<init>()     // Catch: java.lang.Throwable -> L5c
            r1.addAll(r3)     // Catch: java.lang.Throwable -> L5c
            r2.b(r1)     // Catch: java.lang.Throwable -> L5c
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5c
            r2.i()
            r2.n()
            r2.k()
            java.util.concurrent.CopyOnWriteArrayList<rdo> r3 = r2.e
            int r3 = r3.size()
            if (r3 != 0) goto L5b
            java.lang.String r3 = "mAllTypeGroupData data is null"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r0 = "Track_SportHistoryListFragment"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
            r2.ah()
        L5b:
            return
        L5c:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5c
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.e(java.lang.Object):void");
    }

    private void b(CopyOnWriteArrayList<rdo> copyOnWriteArrayList) {
        ArrayList arrayList = new ArrayList();
        Iterator<rdo> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            rdo next = it.next();
            Iterator<rdo> it2 = this.e.iterator();
            while (true) {
                if (it2.hasNext()) {
                    rdo next2 = it2.next();
                    if (jdl.d(next2.h(), next.h())) {
                        next2.a(next.c());
                        break;
                    }
                } else {
                    arrayList.add(next);
                    break;
                }
            }
        }
        this.e.addAll(arrayList);
    }

    private void i() {
        if (this.g != 0) {
            return;
        }
        if (this.e.size() == 0) {
            LogUtil.b("Track_SportHistoryListFragment", "mAllTypeGroupData data is null");
            return;
        }
        Iterator<rdo> it = this.e.iterator();
        while (it.hasNext()) {
            rdo next = it.next();
            if (next == null) {
                LogUtil.b("Track_SportHistoryListFragment", "dealWithOnlySportType  groupData is null");
                return;
            }
            List<String> b2 = next.b();
            if (koq.b(b2)) {
                LogUtil.b("Track_SportHistoryListFragment", "allRequestString is null");
                return;
            }
            HashSet<Integer> c2 = rdu.c(b2);
            if (koq.c(c2) && c2.size() == 1) {
                a(next, c2);
            } else {
                next.e(0);
            }
        }
    }

    private void a(rdo rdoVar, HashSet<Integer> hashSet) {
        Iterator<Integer> it = hashSet.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            if (next != null && !TextUtils.isEmpty(next.toString())) {
                rdoVar.e(CommonUtils.h(next.toString()));
                return;
            }
        }
    }

    private void h() {
        this.e.clear();
        this.cl.a(0.0d);
        this.cl.b(0.0d);
        this.cl.d(0.0d);
        this.cl.c(0.0d);
        k();
    }

    private void k() {
        this.p.c(this.e, this.g);
        this.p.notifyDataSetChanged();
        x();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(java.lang.Object r4) {
        /*
            r3 = this;
            java.lang.String r0 = "Track_SportHistoryListFragment"
            java.lang.Class<rdr> r1 = defpackage.rdr.class
            boolean r1 = defpackage.koq.e(r4, r1)     // Catch: java.lang.ClassCastException -> Ld
            if (r1 == 0) goto L1b
            java.util.List r4 = (java.util.List) r4     // Catch: java.lang.ClassCastException -> Ld
            goto L1c
        Ld:
            r4 = move-exception
            java.lang.String r1 = "copySingleDataToUiData "
            java.lang.String r4 = r4.getMessage()
            java.lang.Object[] r4 = new java.lang.Object[]{r1, r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r4)
        L1b:
            r4 = 0
        L1c:
            if (r4 != 0) goto L2b
            java.lang.String r4 = "copySingleDataToUiData tmp is null"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r4)
            r3.ah()
            return
        L2b:
            int r1 = r3.z
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "before->mInitExpandIndex:"
            java.lang.Object[] r1 = new java.lang.Object[]{r2, r1}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)
            r3.d(r4)
            r3.i()
            int r4 = r3.z
            r0 = -2
            if (r4 != r0) goto L4c
            r4 = -1
            r3.z = r4
            r3.k()
            return
        L4c:
            r3.n()
            r3.ax()
            r3.k()
            r3.aq()
            r4 = 0
            r3.ah = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.b(java.lang.Object):void");
    }

    private void ax() {
        if (this.e == null) {
            return;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (this.e.get(i2) != null) {
                if (this.e.get(i2).a()) {
                    f(i2);
                } else {
                    j(i2);
                }
            }
        }
        if (this.bi != null) {
            if (this.e.size() > 0) {
                this.bi.setVisibility(0);
                this.an.setVisibility(8);
            } else {
                this.an.setVisibility(0);
            }
        }
    }

    private void d(List<rdr> list) {
        if (list == null) {
            return;
        }
        for (rdr rdrVar : list) {
            if (rdrVar != null && (rdrVar.l() == null || this.g != 0)) {
                long s = jdl.s(rdrVar.v());
                if (b(s)) {
                    rdo rdoVar = new rdo(new rdj(new HashMap(), 0, s));
                    rdoVar.d(rdrVar);
                    this.e.add(rdoVar);
                } else {
                    Iterator<rdo> it = this.e.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            rdo next = it.next();
                            if (jdl.d(next.h(), s)) {
                                next.d(rdrVar);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean b(long j2) {
        Iterator<rdo> it = this.e.iterator();
        while (it.hasNext()) {
            if (jdl.d(it.next().h(), j2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLs_(Message message) {
        LogUtil.a("Track_SportHistoryListFragment", "parseYearData");
        if (message == null) {
            LogUtil.a("Track_SportHistoryListFragment", "msg is null");
        } else if (message.obj instanceof Object[]) {
            c((Object[]) message.obj);
            LogUtil.a("Track_SportHistoryListFragment", "parseYearData update ui");
            at();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void at() {
        Handler handler = this.by;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(8);
            this.by.removeMessages(8);
            this.by.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        if (this.p == null) {
            return;
        }
        this.ck.setVisibility(0);
        this.c.setVisibility(8);
        this.x.setVisibility(8);
        this.ae.setVisibility(8);
        int i2 = this.g;
        if (i2 == 10001) {
            this.x.setVisibility(0);
            this.c.setVisibility(0);
        } else if (i2 == 258) {
            ai();
        } else {
            LogUtil.h("Track_SportHistoryListFragment", "refreshYearTitle() sportType = ", Integer.valueOf(i2));
        }
        if (koq.b(this.ce)) {
            this.cj.setVisibility(8);
        } else {
            this.cj.setVisibility(0);
            this.p.dKd_(new ArrayList(this.ce), this.cj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<rdg> list) {
        View view;
        if ((this.g != 286 || list == null || this.bl < jdl.d()) && (view = this.v) != null) {
            view.setVisibility(8);
            return;
        }
        if (this.v == null) {
            if (this.g != 286) {
                return;
            } else {
                this.v = this.u.inflate();
            }
        }
        this.v.setVisibility(0);
        scu.dVY_(this.j, list, this.v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ar() {
        int i2 = this.g;
        if (i2 == 0 || RunningRouteUtils.a(i2)) {
            kpm.c().c(new c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b1 A[LOOP:0: B:38:0x00ab->B:40:0x00b1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dLq_(android.os.Message r9) {
        /*
            r8 = this;
            java.lang.String r0 = "parseMonthData"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Track_SportHistoryListFragment"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            if (r9 != 0) goto L17
            java.lang.String r9 = "msg is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r9)
            return
        L17:
            java.lang.Object r0 = r9.obj
            boolean r0 = r0 instanceof java.lang.Object[]
            if (r0 != 0) goto L1e
            return
        L1e:
            java.lang.Object r9 = r9.obj
            java.lang.Object[] r9 = (java.lang.Object[]) r9
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r2 = 0
            r3 = 0
            r4 = r9[r2]     // Catch: java.lang.ClassCastException -> L6c
            if (r4 == 0) goto L3a
            java.lang.Class<com.huawei.hihealth.HiHealthData> r5 = com.huawei.hihealth.HiHealthData.class
            boolean r4 = defpackage.koq.e(r4, r5)     // Catch: java.lang.ClassCastException -> L6c
            if (r4 == 0) goto L3a
            r2 = r9[r2]     // Catch: java.lang.ClassCastException -> L6c
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.ClassCastException -> L6c
            goto L3b
        L3a:
            r2 = r3
        L3b:
            r4 = 1
            r5 = r9[r4]     // Catch: java.lang.ClassCastException -> L67
            if (r5 == 0) goto L4d
            java.lang.Class<com.huawei.basefitnessadvice.model.FitnessTrackRecord> r6 = com.huawei.basefitnessadvice.model.FitnessTrackRecord.class
            boolean r5 = defpackage.koq.e(r5, r6)     // Catch: java.lang.ClassCastException -> L67
            if (r5 == 0) goto L4d
            r4 = r9[r4]     // Catch: java.lang.ClassCastException -> L67
            java.util.List r4 = (java.util.List) r4     // Catch: java.lang.ClassCastException -> L67
            r3 = r4
        L4d:
            int r4 = r9.length     // Catch: java.lang.ClassCastException -> L67
            r5 = 2
            if (r4 <= r5) goto L7e
            r4 = r9[r5]     // Catch: java.lang.ClassCastException -> L67
            if (r4 == 0) goto L7e
            boolean r6 = r4 instanceof java.util.Map     // Catch: java.lang.ClassCastException -> L67
            if (r6 == 0) goto L7e
            java.util.Map r4 = (java.util.Map) r4     // Catch: java.lang.ClassCastException -> L67
            boolean r4 = r4.isEmpty()     // Catch: java.lang.ClassCastException -> L67
            if (r4 != 0) goto L7e
            r9 = r9[r5]     // Catch: java.lang.ClassCastException -> L67
            r8.c(r9, r0)     // Catch: java.lang.ClassCastException -> L67
            goto L7e
        L67:
            r9 = move-exception
            r7 = r3
            r3 = r2
            r2 = r7
            goto L6e
        L6c:
            r9 = move-exception
            r2 = r3
        L6e:
            java.lang.String r4 = "MSG_DEAL_MONTH_DATA "
            java.lang.String r9 = r9.getMessage()
            java.lang.Object[] r9 = new java.lang.Object[]{r4, r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r9)
            r7 = r3
            r3 = r2
            r2 = r7
        L7e:
            if (r2 != 0) goto L8c
            if (r3 != 0) goto L8c
            java.lang.String r9 = "data is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r9)
            return
        L8c:
            java.util.List<rdo> r9 = r8.bt
            r9.clear()
            java.util.ArrayList r9 = new java.util.ArrayList
            r4 = 10
            r9.<init>(r4)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r4)
            r8.d(r2, r9, r0)
            r8.d(r3, r5)
            java.util.List r9 = defpackage.rdu.a(r9, r5)
            java.util.Iterator r9 = r9.iterator()
        Lab:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto Lc2
            java.lang.Object r0 = r9.next()
            rdj r0 = (defpackage.rdj) r0
            rdo r2 = new rdo
            r2.<init>(r0)
            java.util.List<rdo> r0 = r8.bt
            r0.add(r2)
            goto Lab
        Lc2:
            java.util.List<rdo> r9 = r8.bt
            int r9 = r9.size()
            if (r9 != 0) goto Ld7
            java.lang.String r9 = "parseMonthData mAllTypeGroupData is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r9)
            r8.ah()
            return
        Ld7:
            r8.y()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.dLq_(android.os.Message):void");
    }

    private void c(Object obj, Map<Long, Double> map) {
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            long s = jdl.s(((Long) entry.getKey()).longValue());
            if (map.containsKey(Long.valueOf(s))) {
                map.put(Long.valueOf(s), Double.valueOf(Math.max(((Double) entry.getValue()).doubleValue(), map.get(Long.valueOf(s)).doubleValue())));
            } else {
                map.put(Long.valueOf(s), (Double) entry.getValue());
            }
        }
    }

    private void y() {
        this.z = b(this.bt);
        Handler handler = this.by;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(12);
            obtainMessage.obj = this.bt;
            this.by.sendMessage(obtainMessage);
        }
    }

    private void d(List<HiHealthData> list, List<rdj> list2, Map<Long, Double> map) {
        if (koq.b(list)) {
            return;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            d(list2, it.next(), map);
        }
    }

    private void d(List<rdj> list, HiHealthData hiHealthData, Map<Long, Double> map) {
        Map<String, Double> hashMap = new HashMap<>();
        long s = jdl.s(hiHealthData.getStartTime());
        int i2 = 0;
        if (koq.c(list)) {
            Iterator<rdj> it = list.iterator();
            while (it.hasNext()) {
                rdj next = it.next();
                if (s == next.c()) {
                    hashMap = next.d();
                    i2 = next.b();
                    it.remove();
                }
            }
        }
        try {
            list.add(new rdj(hashMap, d(hiHealthData, map, i2, hashMap, s), s));
        } catch (NumberFormatException e2) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", e2.getMessage());
        }
    }

    private int d(HiHealthData hiHealthData, Map<Long, Double> map, int i2, Map<String, Double> map2, long j2) {
        int i3;
        int i4 = hiHealthData.getInt("hihealth_type");
        String[] a2 = hkc.a(this.j, this.g);
        if (this.g == 0) {
            b(hiHealthData, map2);
        }
        if (this.g == 286) {
            if (i4 == 30001) {
                a2 = hkc.a(this.j, HeartRateThresholdConfig.HEART_RATE_LIMIT);
                i4 = 220;
            }
            i3 = i2 + ((int) hiHealthData.getDouble("Track_" + i4 + 5));
        } else {
            i3 = i2;
        }
        int i5 = this.g;
        if (i5 == 0 && i4 == 30001) {
            e(hiHealthData, map2);
            int i6 = (int) hiHealthData.getDouble("Track_Count_Sum");
            map2.put("Track_Count_Sum", Double.valueOf(i6));
            i3 = i6;
        } else if (i5 != 0 && i5 != 286 && i4 != 288 && i4 != 289) {
            i3 = (int) hiHealthData.getDouble("Track_" + i4 + 5);
        }
        d(hiHealthData, map2, i4, a2);
        if (i4 != 287 && i4 != 291) {
            return i3;
        }
        int i7 = (int) hiHealthData.getDouble("Track_2875");
        e(map, map2, j2);
        return i7;
    }

    private void e(Map<Long, Double> map, Map<String, Double> map2, long j2) {
        map2.remove("Track_29122");
        if (map == null || !map.containsKey(Long.valueOf(j2))) {
            return;
        }
        map2.put("Track_28722", map.get(Long.valueOf(j2)));
    }

    private void b(HiHealthData hiHealthData, Map<String, Double> map) {
        double d2 = hiHealthData.getDouble("Track_Duration_Sum");
        double d3 = hiHealthData.getDouble("Track_Count_Sum");
        double d4 = hiHealthData.getDouble("Track_Calorie_Sum");
        if (map.containsKey("Track_Duration_Sum")) {
            map.put("Track_Duration_Sum", Double.valueOf(map.get("Track_Duration_Sum").doubleValue() + d2));
            return;
        }
        if (map.containsKey("Track_Count_Sum")) {
            map.put("Track_Count_Sum", Double.valueOf(map.get("Track_Count_Sum").doubleValue() + d3));
        } else {
            if (!map.containsKey("Track_Calorie_Sum")) {
                map.put("Track_Duration_Sum", Double.valueOf(d2));
                map.put("Track_Count_Sum", Double.valueOf(d3));
                map.put("Track_Calorie_Sum", Double.valueOf(d4));
                return;
            }
            map.put("Track_Calorie_Sum", Double.valueOf(map.get("Track_Calorie_Sum").doubleValue() + d4));
        }
    }

    private void d(HiHealthData hiHealthData, Map<String, Double> map, int i2, String[] strArr) {
        String[] strArr2;
        int i3 = i2;
        if (i3 == 30001) {
            return;
        }
        int i4 = hiHealthData.getInt("hihealth_type");
        if (i3 == 288 || i3 == 289) {
            map.put("Track_" + i3 + 5, Double.valueOf(hiHealthData.getDouble("stat5")));
            map.put("Track_" + i3 + 4, Double.valueOf(hiHealthData.getDouble("stat4")));
            return;
        }
        if (this.g == 0 && (i4 == 287 || i4 == 291)) {
            strArr2 = new String[strArr.length + 2];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            System.arraycopy(new String[]{"Track_28721", "Track_28722"}, 0, strArr2, strArr.length, 2);
        } else {
            strArr2 = strArr;
        }
        for (String str : strArr2) {
            if (str != null && hiHealthData.getDouble(str) > 0.0d) {
                if (str.length() == 5) {
                    i3 = i4 == 291 ? i4 : rdu.d(i3);
                    map.put("Track_" + i3 + str.charAt(4), Double.valueOf(hiHealthData.getDouble(str)));
                } else {
                    e(i4, hiHealthData, map, str);
                }
            }
        }
    }

    private void e(int i2, HiHealthData hiHealthData, Map<String, Double> map, String str) {
        String str2;
        if (i2 == 291) {
            String valueOf = String.valueOf(287);
            if (str.contains(valueOf)) {
                str2 = str.replace(valueOf, String.valueOf(291));
                map.put(str2, Double.valueOf(hiHealthData.getDouble(str)));
            }
        }
        str2 = str;
        map.put(str2, Double.valueOf(hiHealthData.getDouble(str)));
    }

    private void e(HiHealthData hiHealthData, Map<String, Double> map) {
        for (String str : gts.b(this.j).d()) {
            double d2 = hiHealthData.getDouble(str);
            if (hiHealthData.getDouble(str) > 0.0d) {
                map.put(str, Double.valueOf(d2));
            }
        }
    }

    private void a(Map<String, Double> map, long j2, double d2, int i2) {
        map.put("Track_100014", Double.valueOf(j2));
        map.put("Track_100013", Double.valueOf(d2));
        map.put("Track_100015", Double.valueOf(i2));
    }

    private void d(List<FitnessTrackRecord> list, List<rdj> list2) {
        if (koq.b(list)) {
            ReleaseLogUtil.e("Track_SportHistoryListFragment", "fitnessList is null");
            return;
        }
        for (FitnessTrackRecord fitnessTrackRecord : list) {
            try {
                long acquireMonthZeroTime = fitnessTrackRecord.acquireMonthZeroTime();
                long acquireSumExerciseTime = fitnessTrackRecord.acquireSumExerciseTime();
                int acquireSumExerciseTimes = fitnessTrackRecord.acquireSumExerciseTimes();
                double acquireSumCalorie = fitnessTrackRecord.acquireSumCalorie();
                HashMap hashMap = new HashMap();
                a(hashMap, acquireSumExerciseTime, acquireSumCalorie, acquireSumExerciseTimes);
                list2.add(new rdj(hashMap, acquireSumExerciseTimes, jdl.s(acquireMonthZeroTime)));
            } catch (NumberFormatException e2) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dLr_(android.os.Message r8) {
        /*
            r7 = this;
            java.lang.String r0 = "parseTrackSimplifyData"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Track_SportHistoryListFragment"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            kww r8 = r7.dLk_(r8)
            if (r8 != 0) goto L12
            return
        L12:
            java.lang.Object[] r0 = r8.e()
            r2 = 0
            r3 = 0
            r4 = r0[r2]     // Catch: java.lang.ClassCastException -> L3b
            java.lang.Class<com.huawei.hihealth.HiHealthData> r5 = com.huawei.hihealth.HiHealthData.class
            boolean r4 = defpackage.koq.e(r4, r5)     // Catch: java.lang.ClassCastException -> L3b
            if (r4 == 0) goto L27
            r2 = r0[r2]     // Catch: java.lang.ClassCastException -> L3b
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.ClassCastException -> L3b
            goto L28
        L27:
            r2 = r3
        L28:
            r4 = 1
            r5 = r0[r4]     // Catch: java.lang.ClassCastException -> L39
            java.lang.Class<com.huawei.health.sport.model.WorkoutRecord> r6 = com.huawei.health.sport.model.WorkoutRecord.class
            boolean r5 = defpackage.koq.e(r5, r6)     // Catch: java.lang.ClassCastException -> L39
            if (r5 == 0) goto L4a
            r0 = r0[r4]     // Catch: java.lang.ClassCastException -> L39
            java.util.List r0 = (java.util.List) r0     // Catch: java.lang.ClassCastException -> L39
            r3 = r0
            goto L4a
        L39:
            r0 = move-exception
            goto L3d
        L3b:
            r0 = move-exception
            r2 = r3
        L3d:
            java.lang.String r4 = "parseTrackSimplifyData "
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r4, r0}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r0)
        L4a:
            java.util.ArrayList r0 = new java.util.ArrayList
            r4 = 10
            r0.<init>(r4)
            defpackage.rdt.e(r2, r0)
            kwy r2 = r8.d()
            int r2 = r2.e()
            java.util.List r0 = defpackage.rdu.c(r0, r3, r2)
            kwy r2 = r8.d()
            long r2 = r2.a()
            com.huawei.hwcommonmodel.utils.DateFormatUtil$DateFormatType r4 = com.huawei.hwcommonmodel.utils.DateFormatUtil.DateFormatType.DATE_FORMAT_6
            java.lang.String r2 = com.huawei.hwcommonmodel.utils.DateFormatUtil.b(r2, r4)
            boolean r3 = defpackage.koq.b(r0)
            if (r3 == 0) goto L8c
            r7.a(r2)
            java.lang.String r8 = "parseTrackSimplifyData datas size is 0"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r8)
            java.util.List<rdo> r8 = r7.bt
            int r8 = r8.size()
            if (r8 != 0) goto L8b
            r7.ah()
        L8b:
            return
        L8c:
            int r3 = r0.size()
            kwy r8 = r8.d()
            int r8 = r8.e()
            if (r3 >= r8) goto La9
            r7.a(r2)
            java.lang.String r8 = "parseTrackSimplifyData data requestMonth = "
            java.lang.String r3 = " loadingEnd"
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r2, r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r8)
            goto Lb2
        La9:
            java.lang.String r8 = "parseTrackSimplifyData data loading"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
        Lb2:
            r7.e(r0)
            android.content.Context r8 = r7.j
            java.util.HashMap<java.lang.Long, java.lang.Long> r1 = r7.ar
            com.huawei.login.ui.login.LoginInit r2 = com.huawei.login.ui.login.LoginInit.getInstance(r8)
            java.lang.String r2 = r2.getUsetId()
            rcv r3 = new rcv
            r3.<init>()
            defpackage.jfc.d(r8, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.dLr_(android.os.Message):void");
    }

    public /* synthetic */ void e(List list, StorageResult storageResult) {
        c((List<rdr>) list);
    }

    private void e(List<rdr> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.c("Track_SportHistoryListFragment", "sportHistorySingleData is empty");
            return;
        }
        if (!ase.f()) {
            ReleaseLogUtil.e("Track_SportHistoryListFragment", "not support new ai fitness plan, return");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (rdr rdrVar : list) {
            if (DateUtils.isToday(rdrVar.n())) {
                arrayList.add(new kwm(rdrVar.v(), rdrVar.n()));
            }
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.c("Track_SportHistoryListFragment", "getCoursePlanId, getCurrentPlan : planApi is null.");
        } else {
            planApi.checkSportRecordOutPlanPunch(new d(list), arrayList);
        }
    }

    static final class d extends UiCallback<List<kwm>> {
        private final List<rdr> d;
        private final WeakReference<SportHistoryListFragment> e;

        private d(SportHistoryListFragment sportHistoryListFragment, List<rdr> list) {
            this.e = new WeakReference<>(sportHistoryListFragment);
            this.d = list;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Track_SportHistoryListFragment", "errorCode, ", Integer.valueOf(i), " errorInfo, ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<kwm> list) {
            ReleaseLogUtil.e("Track_SportHistoryListFragment", "checkSportRecordOutPlanPunch data is ", ghb.e(list));
            SportHistoryListFragment sportHistoryListFragment = this.e.get();
            if (sportHistoryListFragment == null) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "activity is null, return");
                return;
            }
            if (koq.b(list)) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "data is empty");
                return;
            }
            int i = 0;
            int i2 = 0;
            for (rdr rdrVar : this.d) {
                Iterator<kwm> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (it.next().d(rdrVar.v(), rdrVar.n())) {
                        i = (int) (i + rdrVar.a());
                        if (i2 == 0) {
                            i2 = rdrVar.s();
                        }
                    }
                }
            }
            sportHistoryListFragment.d(i, list, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i2, final List<kwm> list, final int i3) {
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.b("Track_SportHistoryListFragment", "activity is null, return");
        } else {
            HandlerExecutor.e(new Runnable() { // from class: rcw
                @Override // java.lang.Runnable
                public final void run() {
                    SportHistoryListFragment.this.dLH_(i2, i3, list, activity);
                }
            });
        }
    }

    public /* synthetic */ void dLH_(int i2, final int i3, List list, Activity activity) {
        ReleaseLogUtil.e("Track_SportHistoryListFragment", "showAiFitnessPlanEnergyReplacementDialog calorie:", Integer.valueOf(i2), " sportType:", Integer.valueOf(i3), " timeRangeList size:", Integer.valueOf(list.size()));
        d(i3, 0);
        View inflate = View.inflate(BaseApplication.getContext(), R.layout.ai_fitness_plan_dialog, null);
        AiFitnessPlanEnergyReplacementCard aiFitnessPlanEnergyReplacementCard = (AiFitnessPlanEnergyReplacementCard) inflate.findViewById(R.id.ai_fitness_plan_energy_replace_layout);
        aiFitnessPlanEnergyReplacementCard.setCalorieConsumption(i2);
        aiFitnessPlanEnergyReplacementCard.setJumpBtnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SportHistoryListFragment.this.d(i3, 1);
                ase.c(9, 1, 1, 1);
                ary.a().e("PLAN_UPDATE");
                fhu.e().d("SP" + i3);
                PluginSuggestion.getInstance().jumpToPlanTab();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(activity);
        inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        builder.czh_(inflate, 0, 0);
        builder.c(false);
        builder.e().show();
        ase.d(activity, (List<kwm>) list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2, int i3) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (i2 == 0) {
            hashMap.put(BleConstants.SPORT_TYPE, "SP10001");
        } else {
            hashMap.put(BleConstants.SPORT_TYPE, "SP" + i2);
        }
        hashMap.put("from", 2);
        hashMap.put("event", Integer.valueOf(i3));
        gge.e(AnalyticsValue.INT_PLAN_1120055.value(), hashMap);
    }

    private void a(String str) {
        if (koq.b(this.e)) {
            return;
        }
        Iterator<rdo> it = this.e.iterator();
        while (it.hasNext()) {
            rdo next = it.next();
            if (next.c() != null && DateFormatUtil.b(next.c().c(), DateFormatUtil.DateFormatType.DATE_FORMAT_6).equals(str)) {
                next.a(true);
                return;
            }
        }
    }

    private kww dLk_(Message message) {
        if (!(message.obj instanceof kww)) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "getValidSportRecordResponseModel failed message.obj error.");
            return null;
        }
        kww kwwVar = (kww) message.obj;
        if (kwwVar.e() == null || kwwVar.e().length < 2) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "getValidSportRecordResponseModel failed with data error.");
            return null;
        }
        if (kwwVar.d() != null && kwwVar.d().f() == this.g && kwwVar.d().i() == this.o) {
            return kwwVar;
        }
        ReleaseLogUtil.d("Track_SportHistoryListFragment", "getValidSportRecordResponseModel failed. responseModel:", kwwVar.d(), " mCurSportType:", Integer.valueOf(this.g), " mCurSubSportType:", Integer.valueOf(this.o));
        return null;
    }

    private void c(List<rdr> list) {
        if (this.ar.size() != 0) {
            for (rdr rdrVar : list) {
                if (rdrVar != null && this.ar.containsKey(Long.valueOf(rdrVar.v()))) {
                    rdrVar.c(true);
                    LogUtil.a("Track_SportHistoryListFragment", "copyDataToUi historySingleData setIsNewNotifyTrack(true)");
                }
            }
        }
        i();
        Handler handler = this.by;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(9);
            obtainMessage.obj = list;
            this.by.sendMessage(obtainMessage);
        }
    }

    private void n() {
        SportHistoryExpandableListAdapter sportHistoryExpandableListAdapter = this.p;
        if (sportHistoryExpandableListAdapter == null) {
            LogUtil.a("Track_SportHistoryListFragment", "expandOrCollapseGroup mExpandableListAdapter is null");
            return;
        }
        if (this.z > sportHistoryExpandableListAdapter.getGroupCount()) {
            this.z = this.p.getGroupCount() - 1;
        }
        if (this.z != -1) {
            for (int i2 = 0; i2 <= this.z; i2++) {
                f(i2);
            }
            this.z = -1;
            return;
        }
        int i3 = this.ak;
        if (i3 != -1 && i3 < this.e.size()) {
            f(this.ak);
            this.ak = -1;
        }
    }

    private void am() {
        LogUtil.a("Track_SportHistoryListFragment", "setTouchListener");
        this.bi.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.10
            @Override // android.widget.ExpandableListView.OnGroupClickListener
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i2, long j2) {
                boolean dLp_ = SportHistoryListFragment.this.dLp_(view, i2);
                ViewClickInstrumentation.groupClickOnExpandableListView(expandableListView, view, i2);
                return dLp_;
            }
        });
        f();
        p();
        this.i.setOnClickListener(new View.OnClickListener() { // from class: rcn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportHistoryListFragment.this.dLG_(view);
            }
        });
        this.bi.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment$$ExternalSyntheticLambda11
            @Override // android.widget.ExpandableListView.OnGroupCollapseListener
            public final void onGroupCollapse(int i2) {
                SportHistoryListFragment.this.c(i2);
            }
        });
        this.bi.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment$$ExternalSyntheticLambda13
            @Override // android.widget.ExpandableListView.OnGroupExpandListener
            public final void onGroupExpand(int i2) {
                SportHistoryListFragment.this.e(i2);
            }
        });
    }

    public /* synthetic */ void dLG_(View view) {
        try {
            LogUtil.a("Track_SportHistoryListFragment", "jump to sport-run page");
            Intent intent = new Intent();
            intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.setFlags(268435456);
            intent.setData(Uri.parse("huaweischeme://healthapp/track?sportType=2&targetType=m&targetValue=5000"));
            gnm.aPB_(this.j, intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("Track_SportHistoryListFragment", e2.getMessage());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void c(int i2) {
        jcf.bEk_(this.bi, nsf.h(R$string.accessibility_collapsed));
    }

    /* synthetic */ void e(int i2) {
        jcf.bEk_(this.bi, nsf.h(R$string.accessibility_expanded));
    }

    public static /* synthetic */ void dLo_(View view, View view2) {
        view.findViewById(R.id.month_history_title).setOnClickListener(null);
        ViewClickInstrumentation.clickOnView(view2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dLp_(final View view, int i2) {
        if (view == null) {
            return true;
        }
        view.findViewById(R.id.month_history_title).setOnClickListener(new View.OnClickListener() { // from class: rcm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.dLo_(view, view2);
            }
        });
        if (view.findViewById(R.id.month_history_title) != null) {
            view.findViewById(R.id.month_history_title).setOnClickListener(null);
        }
        if (this.ah) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "onGroupClick loading group dara,wait");
            return true;
        }
        if (koq.b(this.e, i2)) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "mAllTypeGroupData is out of bound");
            return true;
        }
        rdo rdoVar = this.e.get(i2);
        if (rdoVar == null) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "groupData is null");
            return true;
        }
        LogUtil.a("Track_SportHistoryListFragment", "groupPosition: ", Integer.valueOf(i2), " size: ", Integer.valueOf(rdoVar.d()));
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        if (rdoVar.d() > 0) {
            if (k(i2) && rdoVar.a()) {
                LogUtil.a("Track_SportHistoryListFragment", "to search more");
                ag();
            }
            LogUtil.a("Track_SportHistoryListFragment", "Has load");
            if (rdoVar.a()) {
                j(i2);
                view.findViewById(R.id.month_history_title).setVisibility(8);
                view.findViewById(R.id.history_father_layout).setBackground(this.j.getDrawable(R.drawable._2131428606_res_0x7f0b04fe));
                hashMap.put("type", 1);
            } else {
                this.bk.e(rdoVar.h());
                f(i2);
                if (this.g == 0) {
                    view.findViewById(R.id.month_history_title).setVisibility(0);
                }
                hashMap.put("type", 0);
            }
            x();
            ixx.d().d(getContext(), AnalyticsValue.MOTION_TRACK_1040020.value(), hashMap, 0);
            return true;
        }
        a(i2, rdoVar, hashMap);
        return true;
    }

    private boolean k(int i2) {
        int i3 = i2 + 1;
        if (koq.b(this.e, i3)) {
            LogUtil.h("Track_SportHistoryListFragment", "mAllTypeGroupData is out of bound");
            return false;
        }
        rdo rdoVar = this.e.get(i3);
        return rdoVar.a() && rdoVar.d() != rdoVar.j();
    }

    private void ag() {
        c(0L, jdl.s(System.currentTimeMillis()) - 1);
    }

    private void a(int i2, rdo rdoVar, Map<String, Object> map) {
        this.bk.e(rdoVar.h());
        this.z = -1;
        a(rdoVar.h(), jdl.a(rdoVar.h()));
        this.ah = true;
        this.ak = i2;
        ao();
        map.put("type", 0);
        ixx.d().d(this.j, AnalyticsValue.MOTION_TRACK_1040020.value(), map, 0);
    }

    private void f() {
        this.bi.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.6
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i2, int i3, long j2) {
                if (nsn.a(1200)) {
                    LogUtil.a("Track_SportHistoryListFragment", "isClickFast");
                    ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i2, i3);
                    return false;
                }
                if (!gwg.h() && !gwe.d(SportHistoryListFragment.this.j)) {
                    if (Utils.o()) {
                        nrw.b(SportHistoryListFragment.this.j, -1);
                    } else {
                        nrw.b(SportHistoryListFragment.this.j, 1);
                    }
                }
                SportHistoryListFragment.this.g(i2, i3);
                if (SportHistoryListFragment.this.b(i2, i3) && SportHistoryListFragment.this.s()) {
                    LogUtil.b("Track_SportHistoryListFragment", "not install google services");
                    if (CommonUtil.bx()) {
                        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(SportHistoryListFragment.this.j);
                        builder.e(SportHistoryListFragment.this.j.getString(R$string.IDS_hwh_motiontrack_show_map_type_no_gms)).czC_(R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.6.3
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                ViewClickInstrumentation.clickOnView(view2);
                            }
                        });
                        builder.e().show();
                        ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i2, i3);
                        return false;
                    }
                }
                if (!koq.b(SportHistoryListFragment.this.e, i2) && i3 >= 0) {
                    SportHistoryListFragment.this.dLy_(i2, i3, view);
                    ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i2, i3);
                    return true;
                }
                ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i2, i3);
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i2, int i3) {
        rdr a2;
        if (koq.b(this.e, i2) || this.e.get(i2) == null || (a2 = this.e.get(i2).a(i3)) == null) {
            return true;
        }
        return a2.m() && a2.x() != 264;
    }

    private int a(int i2, int i3) {
        rdr a2;
        if (koq.b(this.e, i2) || this.e.get(i2) == null || (a2 = this.e.get(i2).a(i3)) == null) {
            return 199;
        }
        return a2.y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean s() {
        gwg.b(this.j);
        return !gwg.c(this.j) && (gwg.a() == 2 || (!CommonUtil.bh() && Utils.o()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLy_(int i2, int i3, View view) {
        rdr a2;
        if (koq.b(this.e, i2) || this.e.get(i2) == null || (a2 = this.e.get(i2).a(i3)) == null) {
            return;
        }
        if (a2.x() == 283 && a2.ae() == 5) {
            int g2 = a2.g();
            LogUtil.a("Track_SportHistoryListFragment", "deviceType:", Integer.valueOf(g2));
            m(g2);
        }
        if (a2.r() == 1) {
            gtc.b(this.j, a2.t());
        } else if (a2.x() == 286) {
            gso.e().e(a2.v(), a2.n());
        } else {
            dLn_(view, a2);
        }
    }

    private void dLn_(View view, rdr rdrVar) {
        d(100L);
        this.bi.setClickable(false);
        this.by.sendEmptyMessageDelayed(112, 5000L);
        this.by.removeMessages(2);
        this.av.b = true;
        this.av.d = null;
        this.av.a().clear();
        this.av.d().clear();
        long v = rdrVar.v();
        long n = rdrVar.n();
        if (rdrVar.ag()) {
            LogUtil.a("Track_SportHistoryListFragment", "historySingleData setIsNewNotifyTrack(false) remove");
            this.h = (ViewGroup) view.findViewById(R.id.hw_show_health_running_history_child_item_layout);
            this.ar.remove(Long.valueOf(v));
            rdrVar.c(false);
        }
        hps.a(v, n, this.av);
        if (rdrVar.x() == 512) {
            this.by.obtainMessage(2).obj = this.av;
        }
        e(rdrVar);
    }

    private void m(int i2) {
        int i3 = this.s;
        this.s = i2;
        if (i3 == i2) {
            LogUtil.a("deviceType is same as last time", new Object[0]);
        } else {
            sdm.c(sdm.a(i2));
        }
    }

    private void e(rdr rdrVar) {
        if (rdrVar.u() != 7) {
            LogUtil.a("Track_SportHistoryListFragment", "recordBiClick track source do not need BiClick");
            return;
        }
        if (rdrVar.x() == 264) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            ixx.d().d(this.j, AnalyticsValue.HEALTH_SPORT_LOOK_TV_RUN_RECORD_2040088.value(), hashMap, 0);
        }
        if (rdrVar.x() == 265) {
            HashMap hashMap2 = new HashMap(1);
            hashMap2.put("click", 1);
            ixx.d().d(this.j, AnalyticsValue.HEALTH_SPORT_LOOK_TV_BIKE_RECORD_2040090.value(), hashMap2, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i2, int i3) {
        ExtendHandler extendHandler;
        String c2 = c(i2, i3);
        if (!CommonUtil.aa(BaseApplication.getContext()) || c2 == null || (extendHandler = this.r) == null) {
            return;
        }
        extendHandler.removeMessages(14);
        Message obtain = Message.obtain();
        obtain.what = 14;
        obtain.obj = c2;
        this.r.sendMessage(obtain);
    }

    private String c(int i2, int i3) {
        int a2 = a(i2, i3);
        String str = (a2 == 3 || a2 == 4) ? "SMART_BAND" : a2 != 5 ? null : "SMART_WATCH";
        LogUtil.a("Track_SportHistoryListFragment", "deviceType", str);
        return str;
    }

    private void p() {
        this.bi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.8
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                rdr a2;
                if (ExpandableListView.getPackedPositionType(j2) != 1 || !(adapterView instanceof ExpandableListView)) {
                    ReleaseLogUtil.d("Track_SportHistoryListFragment", "ExpandableListView delete click is error");
                    return false;
                }
                long expandableListPosition = ((ExpandableListView) adapterView).getExpandableListPosition(i2);
                int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
                int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
                SportHistoryListFragment.this.n = packedPositionGroup;
                SportHistoryListFragment.this.l = packedPositionChild;
                if (SportHistoryListFragment.this.n < 0 || SportHistoryListFragment.this.l < 0 || (a2 = ((rdo) SportHistoryListFragment.this.e.get(SportHistoryListFragment.this.n)).a(SportHistoryListFragment.this.l)) == null) {
                    return false;
                }
                boolean z = ((rdo) SportHistoryListFragment.this.e.get(SportHistoryListFragment.this.n)).d() - 1 == SportHistoryListFragment.this.l;
                boolean ag = a2.ag();
                View findViewById = view.findViewById(R.id.hw_show_health_running_history_child_item_layout);
                if (a2.l() != null) {
                    SportHistoryListFragment.this.dLz_(z, findViewById, ag);
                    SportHistoryListFragment.this.dLu_(z, findViewById, ag);
                } else if (a2.r() == 0) {
                    SportHistoryListFragment.this.dLw_(z, findViewById, ag);
                    SportHistoryListFragment.this.dLu_(z, findViewById, ag);
                } else if (a2.r() == 1) {
                    SportHistoryListFragment.this.dLx_(z, findViewById, ag);
                    SportHistoryListFragment.this.dLu_(z, findViewById, ag);
                } else {
                    LogUtil.a("Track_SportHistoryListFragment", "wrong long press");
                }
                LogUtil.a("Track_SportHistoryListFragment", "long click, groupPosition is ", Integer.valueOf(packedPositionGroup), ", childPosition is ", Integer.valueOf(packedPositionChild));
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLu_(boolean z, View view, boolean z2) {
        if (view == null || this.p == null) {
            LogUtil.h("Track_SportHistoryListFragment", "childView == ", view);
            return;
        }
        view.setClickable(!view.isClickable());
        if (view.isClickable()) {
            this.p.dKe_(z, view, R.drawable._2131428601_res_0x7f0b04f9, R.drawable._2131428602_res_0x7f0b04fa);
        } else if (z2) {
            this.p.dKe_(z, view, R.drawable._2131428600_res_0x7f0b04f8, R.drawable._2131428604_res_0x7f0b04fc);
        } else {
            this.p.dKe_(z, view, R.drawable._2131428599_res_0x7f0b04f7, R.drawable._2131428603_res_0x7f0b04fb);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLz_(final boolean z, final View view, final boolean z2) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.j);
        builder.e(R$string.IDS_hw_health_show_healthdata_triathlon_item_delete_tips).czE_(getString(R$string.IDS_hw_common_ui_dialog_confirm).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("Track_SportHistoryListFragment", "it is positive");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: rco
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                SportHistoryListFragment.this.dLL_(z, view, z2, dialogInterface);
            }
        });
        e2.show();
    }

    public /* synthetic */ void dLL_(boolean z, View view, boolean z2, DialogInterface dialogInterface) {
        dLu_(z, view, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLx_(final boolean z, final View view, final boolean z2) {
        LogUtil.a("Track_SportHistoryListFragment", "showDeleteFitnessRecordDialog enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.j);
        builder.e(getString(R$string.IDS_hw_health_show_healthdata_delete)).czE_(getString(R$string.IDS_hw_common_ui_dialog_confirm).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("Track_SportHistoryListFragment", "it is positive");
                if (SportHistoryListFragment.this.n >= 0 && SportHistoryListFragment.this.l >= 0) {
                    if (SportHistoryListFragment.this.g()) {
                        rdr a2 = ((rdo) SportHistoryListFragment.this.e.get(SportHistoryListFragment.this.n)).a(SportHistoryListFragment.this.l);
                        if (SportHistoryListFragment.this.r != null) {
                            Message obtain = Message.obtain();
                            obtain.what = 10;
                            if (a2 != null) {
                                obtain.arg1 = a2.t();
                                Bundle bundle = new Bundle();
                                bundle.putString("deleteWorkoutId", a2.ad());
                                bundle.putLong("deleteExerciseTime", a2.n());
                                obtain.setData(bundle);
                                SportHistoryListFragment.this.m = a2.n();
                                long unused = SportHistoryListFragment.f10304a = a2.n();
                            }
                            SportHistoryListFragment.this.r.sendMessage(obtain);
                        }
                        if (SportHistoryListFragment.this.by != null) {
                            Message obtainMessage = SportHistoryListFragment.this.by.obtainMessage(10);
                            if (a2 != null) {
                                obtainMessage.arg1 = a2.t();
                            }
                            SportHistoryListFragment.this.by.sendMessage(obtainMessage);
                        }
                    }
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czA_(getString(R$string.IDS_hw_show_cancel), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("Track_SportHistoryListFragment", "it is negative");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: rcy
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                SportHistoryListFragment.this.dLK_(z, view, z2, dialogInterface);
            }
        });
        e2.show();
    }

    public /* synthetic */ void dLK_(boolean z, View view, boolean z2, DialogInterface dialogInterface) {
        dLu_(z, view, z2);
    }

    private void ao() {
        ExtendHandler extendHandler = this.r;
        if (extendHandler == null) {
            LogUtil.a("Track_SportHistoryListFragment", "mExtendHandler == null");
        } else {
            extendHandler.postTask(new Runnable() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    SportHistoryListFragment.this.ah = false;
                }
            }, 2000L);
        }
    }

    private void d(int i2, long j2, long j3) {
        LogUtil.a("Track_SportHistoryListFragment", "deleteSportHistoryDataFromData");
        if (this.k == null) {
            LogUtil.a("Track_SportHistoryListFragment", "deleteData ", "deleteDataResponseCallback == null");
            this.k = new b();
        }
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimeInterval(j2, j3);
        hiDataDeleteOption.setTypes(new int[]{30001});
        HiHealthManager.d(BaseApplication.getContext()).deleteHiHealthData(hiDataDeleteOption, new a(this, i2, j2, j3));
    }

    static class a implements HiDataOperateListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SportHistoryListFragment> f10309a;
        private final long b;
        private final int c;
        private final long e;

        a(SportHistoryListFragment sportHistoryListFragment, int i, long j, long j2) {
            this.f10309a = new WeakReference<>(sportHistoryListFragment);
            this.c = i;
            this.e = j;
            this.b = j2;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            SportHistoryListFragment sportHistoryListFragment = this.f10309a.get();
            if (sportHistoryListFragment == null) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "sportHistoryListFragment == null");
                return;
            }
            if (i == 0) {
                LogUtil.a("Track_SportHistoryListFragment", "deleteSportHistoryData delete successful");
                sportHistoryListFragment.k.d(0, null);
                sportHistoryListFragment.a(this.e, this.c);
                int i2 = this.c;
                if (i2 == 257 || i2 == 281) {
                    LogUtil.a("Track_SportHistoryListFragment", "walk removeCard: ", "movement");
                    gnp.d("movement", BaseApplication.getContext(), this.b);
                }
                int i3 = this.c;
                if (i3 == 258 || i3 == 264) {
                    LogUtil.a("Track_SportHistoryListFragment", "run removeCard: ", "movement_run");
                    gnp.d("movement_run", BaseApplication.getContext(), this.b);
                }
                sportHistoryListFragment.d(true);
                return;
            }
            LogUtil.a("Track_SportHistoryListFragment", "deleteSportHistoryData delete failed ,type", Integer.valueOf(i));
            sportHistoryListFragment.k.d(100001, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j2, int i2) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("startTime", Long.valueOf(j2));
        hashMap.put("type", Integer.valueOf(i2));
        mcv.d(BaseApplication.getContext()).c(BaseApplication.getContext(), String.valueOf(1), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLg_(Bundle bundle) {
        LogUtil.a("Track_SportHistoryListFragment", "deleteFitnessRecordDatabase");
        if (bundle == null) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "bundle recordApi is null.");
            return;
        }
        if (this.k == null) {
            LogUtil.a("Track_SportHistoryListFragment", "deleteData ", "deleteFitnessRecordDatabase == null");
            this.k = new b();
        }
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "deleteFitnessRecordDatabase recordApi is null.");
        } else {
            recordApi.deleteFitnessRecord(bundle.getString("deleteWorkoutId", ""), bundle.getLong("deleteExerciseTime"), this.k);
        }
    }

    private void o() {
        LogUtil.a("Track_SportHistoryListFragment", "deleteFitnessRecordDatabase");
        if (this.k == null) {
            LogUtil.a("Track_SportHistoryListFragment", "deleteData ", "deleteFitnessRecordDatabase == null");
            this.k = new b();
        }
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "deleteFitnessRecordDatabase recordApi is null.");
        } else {
            recordApi.deleteFitnessRecord(this.q, this.m, this.k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (koq.b(this.e, this.n)) {
            return;
        }
        rdo rdoVar = this.e.get(this.n);
        if (rdoVar.a(this.l) == null) {
            return;
        }
        c(rdoVar);
        int c2 = rdoVar.c(this.l, this.g);
        LogUtil.a("Track_SportHistoryListFragment", "after delete size = ", Integer.valueOf(c2));
        if (c2 == 0) {
            this.e.remove(this.n);
            if (this.e.size() == 0) {
                LogUtil.a("Track_SportHistoryListFragment", "after delete all data gone, no data");
                ah();
                return;
            }
        }
        i();
        k();
        if (c2 == 0) {
            for (int i2 = this.n; i2 < this.e.size(); i2++) {
                if (this.e.get(i2).a()) {
                    f(i2);
                } else {
                    j(i2);
                }
            }
        }
    }

    private void c(rdo rdoVar) {
        HashMap hashMap = new HashMap();
        int x = rdoVar.a(this.l).x();
        int d2 = rdu.d(x);
        if (x == 289 || x == 288) {
            return;
        }
        if ((x == 287 || x == 291) && this.g != 0) {
            d(rdoVar, hashMap, d2);
            return;
        }
        a(rdoVar, hashMap, d2);
        j();
        at();
    }

    private void a(rdo rdoVar, Map<String, Double> map, int i2) {
        String str;
        String str2;
        String str3;
        if (i2 == 10001) {
            str = "Track_" + i2 + 4;
            str2 = "Track_" + i2 + 3;
        } else {
            str = "Track_Duration_Sum";
            str2 = "Track_Calorie_Sum";
        }
        map.put(str, Double.valueOf(this.cl.c() * 60000.0d));
        map.put(str2, Double.valueOf(this.cl.a() * 1000.0d));
        if (rdu.b(rdoVar.a(this.l), i2)) {
            str3 = "Track_" + i2 + 2;
        } else {
            str3 = "Track_Distance_Sum";
        }
        if (UnitUtil.h()) {
            map.put(str3, Double.valueOf(this.cl.d() / 6.21371204033494E-4d));
        } else {
            map.put(str3, Double.valueOf(this.cl.d() * 1000.0d));
        }
        rdu.b(rdoVar.a(this.l), i2, map);
        rdx rdxVar = this.cl;
        rdxVar.d(rdxVar.b() - 1.0d);
        this.cl.c(c(map, str) / 60000.0d);
        this.cl.b(c(map, str2) / 1000.0d);
        if (UnitUtil.h()) {
            this.cl.a(c(map, str3) * 6.21371204033494E-4d);
        } else {
            this.cl.a(c(map, str3) / 1000.0d);
        }
    }

    private double c(Map<String, Double> map, String str) {
        if (map == null || !map.containsKey(str) || map.get(str) == null) {
            return 0.0d;
        }
        return map.get(str).doubleValue();
    }

    private void d(rdo rdoVar, Map<String, Double> map, int i2) {
        final String str = "Track_" + i2 + 22;
        final String str2 = "Track_" + i2 + 21;
        final String str3 = "Track_" + i2 + 5;
        map.put(str, Double.valueOf(this.cl.d()));
        map.put(str2, Double.valueOf(this.cl.c()));
        map.put(str3, Double.valueOf(this.cl.b()));
        long a2 = this.bl > this.bs ? 0L : jdl.a(System.currentTimeMillis(), this.bl - this.bs);
        rdu.c(rdoVar.a(this.l), map, a2, this.bl >= this.bs ? jdl.w(System.currentTimeMillis()) : jdl.b(a2), 6, new IBaseResponseCallback() { // from class: rcl
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i3, Object obj) {
                SportHistoryListFragment.this.b(str2, str, str3, i3, obj);
            }
        });
    }

    public /* synthetic */ void b(String str, String str2, String str3, int i2, Object obj) {
        if (obj instanceof Map) {
            Map<String, Double> map = (Map) obj;
            this.cl.c(c(map, str));
            this.cl.a(c(map, str2));
            this.cl.d(c(map, str3) - 1.0d);
            j();
            at();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        if (this.n < 0 || this.l < 0) {
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "wrong delete position");
            return false;
        }
        if (this.e.size() != 0) {
            int size = this.e.size();
            int i2 = this.n;
            if (size > i2) {
                int d2 = this.e.get(i2).d();
                if (d2 != 0 && d2 > this.l) {
                    return true;
                }
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "deleteSportHistoryDataFromView() childCount is empty or outIndexOf size = ", Integer.valueOf(d2), " childP = ", Integer.valueOf(this.l));
                return false;
            }
        }
        ReleaseLogUtil.d("Track_SportHistoryListFragment", "deleteSportHistoryDataFromView() mAllTypeGroupData is empty or outIndexOf size = ", Integer.valueOf(this.e.size()), " groupP = ", Integer.valueOf(this.n));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        Handler handler = this.by;
        if (handler == null) {
            LogUtil.a("Track_SportHistoryListFragment", "mUpdateUiHandler == null");
            return;
        }
        Message obtainMessage = handler.obtainMessage(1, this.g, this.o);
        this.by.removeMessages(1);
        this.by.sendMessage(obtainMessage);
    }

    static class b implements IBaseResponseCallback {
        private b() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                LogUtil.a("Track_SportHistoryListFragment", "delete successful");
                if (SportHistoryListFragment.f10304a != -1) {
                    LogUtil.a("Track_SportHistoryListFragment", "fitness removeCard: ", Long.valueOf(SportHistoryListFragment.f10304a));
                    gnp.d("fitness", BaseApplication.getContext(), SportHistoryListFragment.f10304a);
                    long unused = SportHistoryListFragment.f10304a = -1L;
                    return;
                }
                return;
            }
            LogUtil.a("Track_SportHistoryListFragment", "delete failed");
        }
    }

    public boolean d() {
        return this.ai;
    }

    public void c(boolean z) {
        this.ai = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dLw_(final boolean z, final View view, final boolean z2) {
        LogUtil.a("Track_SportHistoryListFragment", "showDeleteDialog ", "enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.j);
        builder.e(getString(R$string.IDS_hw_health_show_healthdata_delete)).czE_(getString(R$string.IDS_hw_common_ui_dialog_confirm).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: rcz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SportHistoryListFragment.this.dLI_(view2);
            }
        }).czA_(getString(R$string.IDS_hw_show_cancel), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("Track_SportHistoryListFragment", "it is negative");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: rcx
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                SportHistoryListFragment.this.dLJ_(z, view, z2, dialogInterface);
            }
        });
        e2.show();
    }

    public /* synthetic */ void dLI_(View view) {
        LogUtil.a("Track_SportHistoryListFragment", "it is positive");
        if (g()) {
            int i2 = this.n;
            if (i2 < 0 || this.l < 0) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            rdr a2 = this.e.get(i2).a(this.l);
            if (a2 == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.t = a2.v();
            this.m = a2.n();
            this.q = a2.ai();
            if (this.r != null) {
                Message obtain = Message.obtain();
                obtain.what = 3;
                obtain.arg1 = a2.x();
                HashMap hashMap = new HashMap(16);
                if (d(a2, hashMap)) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                } else {
                    obtain.obj = hashMap;
                    this.r.sendMessage(obtain);
                }
            }
            Handler handler = this.by;
            if (handler == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            Message obtainMessage = handler.obtainMessage(3);
            obtainMessage.arg1 = a2.x();
            if (a2.s() != 287) {
                this.by.sendMessage(obtainMessage);
            } else {
                this.by.sendMessageDelayed(obtainMessage, 100L);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dLJ_(boolean z, View view, boolean z2, DialogInterface dialogInterface) {
        dLu_(z, view, z2);
    }

    private boolean d(rdr rdrVar, Map<Integer, Long[]> map) {
        if (rdrVar.x() != 512) {
            return false;
        }
        List<RelativeSportData> f2 = rdrVar.f();
        if (koq.b(f2)) {
            return true;
        }
        for (RelativeSportData relativeSportData : f2) {
            int sportType = relativeSportData.getSportType();
            map.put(Integer.valueOf(sportType), new Long[]{Long.valueOf(relativeSportData.getStartTime()), Long.valueOf(relativeSportData.getEndTime())});
        }
        return false;
    }

    static class g extends BroadcastReceiver {
        WeakReference<SportHistoryListFragment> e;

        g(SportHistoryListFragment sportHistoryListFragment) {
            this.e = new WeakReference<>(sportHistoryListFragment);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SportHistoryListFragment sportHistoryListFragment;
            WeakReference<SportHistoryListFragment> weakReference = this.e;
            if (weakReference == null || (sportHistoryListFragment = weakReference.get()) == null) {
                return;
            }
            if (intent == null || intent.getExtras() == null) {
                LogUtil.a("Track_SportHistoryListFragment", "mBroadcastReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("Track_SportHistoryListFragment", "onReceive: action = ", action);
            SportHistoryListFragment.dLt_(intent, sportHistoryListFragment, action);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dLt_(Intent intent, final SportHistoryListFragment sportHistoryListFragment, String str) {
        if ("input_sport_history_start_time".equals(str)) {
            if (sportHistoryListFragment.ad) {
                LogUtil.a("Track_SportHistoryListFragment", "inserting return");
                return;
            }
            ExtendHandler extendHandler = sportHistoryListFragment.r;
            if (extendHandler != null) {
                sportHistoryListFragment.ad = true;
                extendHandler.postTask(new Runnable() { // from class: rdb
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportHistoryListFragment.this.ad = false;
                    }
                }, 500L);
            }
            Handler handler = sportHistoryListFragment.by;
            if (handler != null) {
                handler.removeMessages(4);
                Message obtainMessage = sportHistoryListFragment.by.obtainMessage(4);
                obtainMessage.obj = intent;
                sportHistoryListFragment.by.sendMessage(obtainMessage);
            }
            sportHistoryListFragment.d(false);
            sportHistoryListFragment.ae();
        }
    }

    private void dLf_(Intent intent, rdn rdnVar) {
        int intExtra = intent.getIntExtra(BleConstants.SPORT_TYPE, 0);
        int intExtra2 = intent.getIntExtra("chiefType", 0);
        if (intExtra2 == 0) {
            rdnVar.d(intent.getIntExtra("distance", 0), intExtra2);
        } else {
            rdnVar.d(intent.getIntExtra("calorie", 0), intExtra2);
        }
        if (intExtra == 260 || Math.abs(intent.getFloatExtra("pace", 0.0f)) <= 1.0E-6d) {
            rdnVar.a(0.0f);
        } else if (kxb.c(intExtra)) {
            rdnVar.a((1.0f / intent.getFloatExtra("pace", 0.0f)) * 3600.0f);
        } else {
            rdnVar.a(intent.getFloatExtra("pace", 0.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        LogUtil.a("Track_SportHistoryListFragment", "dealInsertTrackData");
        if (obj instanceof Intent) {
            Intent intent = (Intent) obj;
            if (intent.getLongExtra("startTime", -1L) == -1) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "dealInsertTrackData intent is null or content wrong");
                return;
            }
            int intExtra = intent.getIntExtra(BleConstants.SPORT_TYPE, 0);
            int i2 = this.g;
            if (i2 == 0 || intExtra == i2 || (intExtra == 264 && i2 == 258)) {
                long longExtra = intent.getLongExtra("startTime", System.currentTimeMillis());
                if (this.bl == jdl.ab(longExtra) || this.bl == this.bs + 1) {
                    rdn rdnVar = new rdn();
                    dLf_(intent, rdnVar);
                    rdnVar.b(longExtra);
                    rdnVar.e(intent.getLongExtra("endTime", System.currentTimeMillis()));
                    rdnVar.c(intent.getLongExtra("duration", 0L));
                    int intExtra2 = intent.getIntExtra("calorie", 0);
                    rdnVar.d(intExtra2);
                    rdnVar.h(intent.getIntExtra("trackType", 0));
                    rdnVar.a(intent.getIntExtra("deviceType", 32));
                    rdnVar.g(intExtra);
                    rdnVar.e(2);
                    rdnVar.d(false);
                    rdr rdrVar = new rdr(0, rdnVar);
                    int intExtra3 = intent.getIntExtra("distance", 0);
                    rdo rdoVar = new rdo();
                    c(rdnVar, intExtra2, intExtra3);
                    d(rdoVar, intExtra3, longExtra, intExtra2, rdrVar);
                    return;
                }
                return;
            }
            LogUtil.a("Track_SportHistoryListFragment", "sportType is not suitable");
            return;
        }
        ReleaseLogUtil.d("Track_SportHistoryListFragment", "insertIntent not instanceof Intent");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        SportHistoryActivity sportHistoryActivity = (SportHistoryActivity) getActivity();
        if (sportHistoryActivity == null) {
            ReleaseLogUtil.c("Track_SportHistoryListFragment", "sportHistoryActivity is null.");
            return;
        }
        sportHistoryActivity.h();
        if (z) {
            sportHistoryActivity.f();
        }
    }

    private void c(rdn rdnVar, int i2, int i3) {
        rdx rdxVar = this.cl;
        rdxVar.c(rdxVar.c() + ((rdnVar.f() * 1.0d) / 60000.0d));
        rdx rdxVar2 = this.cl;
        rdxVar2.d(rdxVar2.b() + 1.0d);
        rdx rdxVar3 = this.cl;
        rdxVar3.b(rdxVar3.a() + ((i2 * 1.0d) / 1000.0d));
        if (UnitUtil.h()) {
            rdx rdxVar4 = this.cl;
            rdxVar4.a(rdxVar4.d() + (i3 * 1.0d * 6.21371204033494E-4d));
        } else {
            rdx rdxVar5 = this.cl;
            rdxVar5.a(rdxVar5.d() + ((i3 * 1.0d) / 1000.0d));
        }
        j();
        at();
    }

    private void d(rdo rdoVar, int i2, long j2, double d2, rdr rdrVar) {
        if (this.e.size() == 0) {
            b(rdoVar, i2, j2, d2, rdrVar);
            return;
        }
        long s = jdl.s(j2);
        int c2 = c(s);
        if (c2 == this.e.size()) {
            LogUtil.a("Track_SportHistoryListFragment", "no that month, new and insert it at the last");
            rdoVar.a(i2, jdl.s(j2), d2, rdrVar.x(), rdrVar.k());
            rdoVar.d(rdrVar);
            this.e.add(rdoVar);
        } else if (this.e.get(c2).h() == s) {
            LogUtil.a("Track_SportHistoryListFragment", "insert track to an exsiting month");
            rdo rdoVar2 = this.e.get(c2);
            if (rdoVar2 == null || rdoVar2.d() == 0) {
                a(jdl.s(s), jdl.a(s));
                return;
            }
            int d3 = d(j2, rdoVar2);
            if (d3 == rdoVar2.d()) {
                rdoVar2.d(rdrVar);
            } else {
                rdoVar2.d(d3, rdrVar);
            }
            rdoVar2.a(i2, rdoVar2.h(), d2, rdrVar.x(), rdrVar.k());
        } else {
            LogUtil.a("Track_SportHistoryListFragment", "no that month");
            rdoVar.a(i2, jdl.s(j2), d2, rdrVar.x(), rdrVar.k());
            rdoVar.d(rdrVar);
            this.e.add(c2, rdoVar);
        }
        i();
        f(c2);
        while (true) {
            c2++;
            if (c2 < this.e.size()) {
                if (this.e.get(c2).a()) {
                    f(c2);
                } else {
                    j(c2);
                }
            } else {
                k();
                return;
            }
        }
    }

    private void b(rdo rdoVar, int i2, long j2, double d2, rdr rdrVar) {
        LogUtil.a("Track_SportHistoryListFragment", "before insert no data");
        rdoVar.a(i2, jdl.s(j2), d2, rdrVar.x(), rdrVar.k());
        rdoVar.d(rdrVar);
        this.e.add(rdoVar);
        k();
        f(0);
        ExpandableListView expandableListView = this.bi;
        if (expandableListView != null) {
            expandableListView.setVisibility(0);
        }
        this.an.setVisibility(8);
    }

    private int c(long j2) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (this.e.get(i2).h() <= j2) {
                return i2;
            }
        }
        return this.e.size();
    }

    private int d(long j2, rdo rdoVar) {
        for (int i2 = 0; i2 < rdoVar.d(); i2++) {
            if (rdoVar.a(i2) != null && j2 > rdoVar.a(i2).v()) {
                return i2;
            }
        }
        return rdoVar.d();
    }

    private void f(int i2) {
        if (this.bi == null || !koq.d(this.e, i2)) {
            return;
        }
        this.bi.expandGroup(i2, false);
        this.e.get(i2).b(true);
    }

    private void j(int i2) {
        if (this.bi == null || i2 >= this.e.size() || i2 < 0) {
            return;
        }
        this.bi.collapseGroup(i2);
        this.e.get(i2).b(false);
    }

    private void c(Object[] objArr) {
        Object obj;
        if (this.g == 287) {
            try {
                Object obj2 = objArr[0];
                if (obj2 != null && koq.e(obj2, HiHealthData.class)) {
                    List<HiHealthData> list = (List) objArr[0];
                    if (koq.b(list)) {
                        return;
                    } else {
                        h(list);
                    }
                }
                if (objArr.length > 2 && (obj = objArr[2]) != null && (obj instanceof Map) && !((Map) obj).isEmpty()) {
                    for (Map.Entry entry : ((Map) objArr[2]).entrySet()) {
                        rdx rdxVar = this.cl;
                        rdxVar.a(Math.max(rdxVar.d(), ((Double) entry.getValue()).doubleValue()));
                    }
                }
            } catch (ClassCastException e2) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "MSG_DEAL_MONTH_DATA ", e2.getMessage());
            }
            j();
            return;
        }
        for (Object obj3 : objArr) {
            if (koq.e(obj3, HiHealthData.class)) {
                g((List<HiHealthData>) obj3);
            }
            if (koq.e(obj3, FitnessTrackRecord.class)) {
                i((List<FitnessTrackRecord>) obj3);
            }
        }
        aw();
        j();
    }

    private void h(List<HiHealthData> list) {
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                f2 += hiHealthData.getFloat(d("Track_Diving_Count", ""));
                f3 += hiHealthData.getFloat(d("Track_Diving_Duration_Sum", ""));
            }
        }
        this.cl.d(f2);
        this.cl.c(f3);
    }

    private void i(List<FitnessTrackRecord> list) {
        LogUtil.a("Track_SportHistoryListFragment", "fitnessTrackRecords size:", Integer.valueOf(list.size()));
        for (FitnessTrackRecord fitnessTrackRecord : list) {
            rdx rdxVar = this.cl;
            rdxVar.c(rdxVar.c() + fitnessTrackRecord.acquireSumExerciseTime());
            rdx rdxVar2 = this.cl;
            rdxVar2.d(rdxVar2.b() + fitnessTrackRecord.acquireSumExerciseTimes());
            rdx rdxVar3 = this.cl;
            rdxVar3.b(rdxVar3.a() + fitnessTrackRecord.acquireSumCalorie());
        }
    }

    private void g(List<HiHealthData> list) {
        double d2 = 0.0d;
        int i2 = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.containsKey("Track_Duration_Sum")) {
                rdx rdxVar = this.cl;
                rdxVar.c(rdxVar.c() + hiHealthData.getFloat("Track_Duration_Sum"));
            }
            if (hiHealthData.containsKey("Track_Count_Sum")) {
                rdx rdxVar2 = this.cl;
                rdxVar2.d(rdxVar2.b() + hiHealthData.getInt("Track_Count_Sum"));
            }
            if (hiHealthData.containsKey("Track_Calorie_Sum")) {
                rdx rdxVar3 = this.cl;
                rdxVar3.b(rdxVar3.a() + hiHealthData.getFloat("Track_Calorie_Sum"));
            }
            if (hiHealthData.containsKey("hihealth_type")) {
                rdx rdxVar4 = this.cl;
                rdxVar4.c(rdxVar4.c() + hiHealthData.getFloat(d("Duration_Sum", "")));
                rdx rdxVar5 = this.cl;
                rdxVar5.d(rdxVar5.b() + hiHealthData.getInt(d("Count_Sum", "Abnormal_Count_Sum")));
                rdx rdxVar6 = this.cl;
                rdxVar6.b(rdxVar6.a() + hiHealthData.getFloat(d("Calorie_Sum", "")));
                rdx rdxVar7 = this.cl;
                rdxVar7.a(rdxVar7.d() + hiHealthData.getInt(d("Distance_Sum", "")));
            }
            int i3 = hiHealthData.getInt("hihealth_type");
            if (i3 == 30001) {
                hln c2 = hln.c(BaseApplication.getContext());
                String[] b2 = c2.b(c2.d(HeartRateThresholdConfig.HEART_RATE_LIMIT).getSportDataStatics());
                rdx rdxVar8 = this.cl;
                rdxVar8.d(rdxVar8.b() + hiHealthData.getInt(b2[0]));
                rdx rdxVar9 = this.cl;
                rdxVar9.c(rdxVar9.c() + hiHealthData.getFloat(b2[2]));
                rdx rdxVar10 = this.cl;
                rdxVar10.b(rdxVar10.a() + hiHealthData.getFloat(b2[3]));
            }
            if (i3 == 288 || i3 == 289) {
                i2 += hiHealthData.getInt("Track_Diving_Count");
                d2 += hiHealthData.getInt("Track_Diving_Time_Sum");
            }
        }
        rdx rdxVar11 = this.cl;
        rdxVar11.d(rdxVar11.b() - i2);
        rdx rdxVar12 = this.cl;
        rdxVar12.c(rdxVar12.c() - d2);
    }

    private String d(String str, String str2) {
        String[] strArr = this.az;
        String str3 = "";
        if (strArr != null && strArr.length != 0 && !TextUtils.isEmpty(str)) {
            for (String str4 : this.az) {
                if (str4.contains(str) && (TextUtils.isEmpty(str2) || !str4.contains(str2))) {
                    str3 = str4;
                }
            }
        }
        return str3;
    }

    private String[] g(int i2) {
        hln c2 = hln.c(this.j);
        if (c2.d(i2) == null || c2.d(i2).getSportDataStatics() == null) {
            LogUtil.b("Track_SportHistoryListFragment", "can not find sport type in json");
            return null;
        }
        return c2.b(c2.d(i2).getSportDataStatics());
    }

    private void aw() {
        if (UnitUtil.h()) {
            rdx rdxVar = this.cl;
            rdxVar.a(rdxVar.d() * 6.21371204033494E-4d);
        } else {
            rdx rdxVar2 = this.cl;
            rdxVar2.a(rdxVar2.d() / 1000.0d);
        }
        rdx rdxVar3 = this.cl;
        rdxVar3.c(rdxVar3.c() / 60000.0d);
        rdx rdxVar4 = this.cl;
        rdxVar4.b(rdxVar4.a() / 1000.0d);
    }

    private void j() {
        String b2;
        String h2;
        double d2;
        String string;
        this.ce.clear();
        int i2 = this.g;
        if (i2 == 287) {
            if (UnitUtil.h()) {
                d2 = UnitUtil.e(this.cl.d() / 10.0d, 1);
                string = this.j.getResources().getString(R$string.IDS_motiontrack_history_max_deep_inch_unit);
            } else {
                d2 = this.cl.d() / 10.0d;
                string = this.j.getResources().getString(R$string.IDS_motiontrack_history_max_deep_unit);
            }
            this.ce.add(new MonthTitleItem.e(UnitUtil.e(d2, 1, (d2 == 0.0d ? 1 : 0) ^ 1), string));
            double c2 = this.cl.c() / 60.0d;
            this.ce.add(new MonthTitleItem.e(UnitUtil.e(c2, 1, (c2 > 0.0d ? 1 : (c2 == 0.0d ? 0 : -1)) == 0 ? 0 : 2), this.j.getResources().getString(R$string.IDS_motiontrack_history_sum_time_unit)));
            this.ce.add(new MonthTitleItem.e(UnitUtil.e(this.cl.b(), 1, 0), this.j.getResources().getString(R$string.IDS_diving_time)));
            return;
        }
        if (hkc.e(i2)) {
            if (UnitUtil.h()) {
                h2 = nsf.h(R$string.IDS_band_data_sport_distance_unit_en);
            } else {
                h2 = nsf.h(R$string.IDS_band_data_sport_distance_unit);
            }
            this.ce.add(new MonthTitleItem.e(UnitUtil.e(this.cl.d(), 1, 2), h2));
        }
        if (this.cl.c() >= 6.0d) {
            b2 = UnitUtil.e(this.cl.c() / 60.0d, 1, 1);
        } else {
            b2 = nsf.b(R$string.IDS_hw_health_blood_oxygen_measure_interval_lower_than, UnitUtil.e(0.1d, 1, 1));
        }
        this.ce.add(new MonthTitleItem.e(b2, BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_sport_history_duration_unit)));
        this.ce.add(new MonthTitleItem.e(UnitUtil.e(this.cl.b(), 1, 0), BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_sport_history_times_unit)));
        if (this.g != 222) {
            this.ce.add(new MonthTitleItem.e(UnitUtil.e(this.cl.a(), 1, 0), nsf.h(R$string.IDS_track_total_calorie_kcal)));
        }
    }

    static class k implements IMultiResponseCallback {
        WeakReference<SportHistoryListFragment> c;
        kwy e;

        k(kwy kwyVar, SportHistoryListFragment sportHistoryListFragment) {
            this.c = new WeakReference<>(sportHistoryListFragment);
            this.e = kwyVar;
        }

        @Override // com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.IMultiResponseCallback
        public void onNotify(int[] iArr, Object[] objArr) {
            LogUtil.a("Track_SportHistoryListFragment", "Simply cb errorCode : ", Arrays.toString(iArr), "Simply cb datas : ", Arrays.toString(objArr));
            SportHistoryListFragment sportHistoryListFragment = this.c.get();
            if (sportHistoryListFragment != null) {
                if (sportHistoryListFragment.r != null) {
                    sportHistoryListFragment.r.removeMessages(6);
                    Message obtain = Message.obtain();
                    obtain.what = 6;
                    obtain.obj = new kww(this.e, objArr);
                    sportHistoryListFragment.r.sendMessage(obtain);
                    return;
                }
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "mExtendHandler is null");
                return;
            }
            ReleaseLogUtil.d("Track_SportHistoryListFragment", "ReadTrackSimplifyListCallback sportHistory mWeakReference is null");
        }
    }

    static class i implements IMultiResponseCallback {
        WeakReference<SportHistoryListFragment> d;

        i(SportHistoryListFragment sportHistoryListFragment) {
            this.d = new WeakReference<>(sportHistoryListFragment);
        }

        @Override // com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.IMultiResponseCallback
        public void onNotify(int[] iArr, Object[] objArr) {
            SportHistoryListFragment sportHistoryListFragment = this.d.get();
            if (sportHistoryListFragment == null) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "ReadSportYearDataCallback sportHistory mWeakReference is null");
                return;
            }
            if (d(objArr)) {
                sportHistoryListFragment.at();
                return;
            }
            if (sportHistoryListFragment.r == null) {
                LogUtil.a("Track_SportHistoryListFragment", "Year mExtendHandler == null");
                sportHistoryListFragment.at();
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 7;
            sportHistoryListFragment.r.removeMessages(7);
            obtain.obj = objArr;
            sportHistoryListFragment.r.sendMessage(obtain);
        }

        private boolean d(Object[] objArr) {
            if (objArr == null) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "Year illegal input data");
                return true;
            }
            LogUtil.a("Track_SportHistoryListFragment", "Year cb data : ", Integer.valueOf(objArr.length));
            if (objArr.length < 2) {
                LogUtil.h("Track_SportHistoryListFragment", "Year data length < 2");
                return true;
            }
            if (objArr[0] != null) {
                return false;
            }
            Object obj = objArr[1];
            if (obj == null) {
                if (objArr.length > 2 && objArr[2] != null) {
                    LogUtil.h("Track_SportHistoryListFragment", "Year data diving");
                    return false;
                }
                LogUtil.h("Track_SportHistoryListFragment", "Year data 0 or 1 is null");
                return true;
            }
            if (obj instanceof Collection) {
                return koq.b((Collection) obj);
            }
            return false;
        }
    }

    static class f implements IMultiResponseCallback {
        WeakReference<SportHistoryListFragment> c;

        f(SportHistoryListFragment sportHistoryListFragment) {
            this.c = new WeakReference<>(sportHistoryListFragment);
        }

        @Override // com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.IMultiResponseCallback
        public void onNotify(int[] iArr, Object[] objArr) {
            if (objArr == null) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "illegal input data");
                return;
            }
            LogUtil.a("Track_SportHistoryListFragment", "Month cb errorCode : ", Integer.valueOf(iArr.length));
            LogUtil.a("Track_SportHistoryListFragment", "Month cb data : ", Integer.valueOf(objArr.length));
            SportHistoryListFragment sportHistoryListFragment = this.c.get();
            if (sportHistoryListFragment == null) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "ReadMonthDataCallbackEx sportHistory mWeakReference is null");
                return;
            }
            if (objArr.length < 2) {
                LogUtil.h("Track_SportHistoryListFragment", "datas.length==0");
                sportHistoryListFragment.ah();
                return;
            }
            if (objArr[0] != null || objArr[1] != null) {
                if (sportHistoryListFragment.r == null) {
                    ReleaseLogUtil.e("Track_SportHistoryListFragment", "mExtendHandler == null");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 5;
                sportHistoryListFragment.r.removeMessages(5);
                obtain.obj = objArr;
                sportHistoryListFragment.r.sendMessage(obtain);
                return;
            }
            LogUtil.h("Track_SportHistoryListFragment", "data 0 or 1 is null");
            sportHistoryListFragment.ah();
        }
    }

    static class l implements IBaseResponseCallback {
        private WeakReference<SportHistoryListFragment> c;
        private boolean b = true;
        private knu d = null;
        private Map<Integer, gxy> e = new HashMap(16);

        /* renamed from: a, reason: collision with root package name */
        private Map<Integer, Long> f10311a = new HashMap(16);

        l(SportHistoryListFragment sportHistoryListFragment) {
            this.c = new WeakReference<>(sportHistoryListFragment);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public knu b() {
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<Integer, Long> a() {
            return this.f10311a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<Integer, gxy> d() {
            return this.e;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            SportHistoryListFragment sportHistoryListFragment = this.c.get();
            if (sportHistoryListFragment == null || !(obj instanceof knu)) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "ReadTrackDetailCallback sportHistory mWeakReference is null");
                return;
            }
            LogUtil.a("Track_SportHistoryListFragment", "err_code = ", Integer.valueOf(i));
            if (sportHistoryListFragment.by == null) {
                ReleaseLogUtil.e("Track_SportHistoryListFragment", "mUpdateUiHandler is null");
                return;
            }
            LogUtil.a("Track_SportHistoryListFragment", "MSG_SHOW_DETAIL_TRACK");
            knu knuVar = (knu) obj;
            gso.e().init(BaseApplication.getContext());
            MotionPathSimplify b = knuVar.b();
            if (b == null) {
                ReleaseLogUtil.c("Track_SportHistoryListFragment", "simplifyMotionPath is null");
                return;
            }
            if (b.requestSportType() == 512) {
                this.f10311a.clear();
                this.d = knuVar;
                List<RelativeSportData> requestChildSportItems = b.requestChildSportItems();
                if (requestChildSportItems == null || requestChildSportItems.size() > 3) {
                    return;
                } else {
                    a(sportHistoryListFragment, requestChildSportItems);
                }
            }
            d(knuVar, sportHistoryListFragment, knuVar, b);
        }

        private void a(SportHistoryListFragment sportHistoryListFragment, List<RelativeSportData> list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    gxy gxyVar = new gxy();
                    LogUtil.a("Track_SportHistoryListFragment", BleConstants.SPORT_TYPE, Integer.valueOf(list.get(i).getSportType()), "HasDetailInfo", Boolean.valueOf(list.get(i).isHasDetailInfo()));
                    gxyVar.c(list.get(i));
                    this.e.put(Integer.valueOf(i), gxyVar);
                    if (list.get(i).isHasDetailInfo()) {
                        this.f10311a.put(Integer.valueOf(i), Long.valueOf(list.get(i).getStartTime()));
                        Message obtain = Message.obtain();
                        obtain.what = 201;
                        obtain.obj = list.get(i);
                        sportHistoryListFragment.r.sendMessage(obtain);
                        LogUtil.a("Track_SportHistoryListFragment", "MSG_TRIATHLON_DETAIL_TRACK__2");
                    }
                }
            }
        }

        private void d(knu knuVar, SportHistoryListFragment sportHistoryListFragment, knu knuVar2, MotionPathSimplify motionPathSimplify) {
            long requestStartTime = motionPathSimplify.requestStartTime();
            ArrayList arrayList = new ArrayList(10);
            for (Map.Entry<Integer, Long> entry : this.f10311a.entrySet()) {
                if (entry.getKey() != null && this.f10311a.get(entry.getKey()) != null && requestStartTime == this.f10311a.get(entry.getKey()).longValue()) {
                    b(knuVar2, motionPathSimplify, this.e.get(entry.getKey()), this.e.get(entry.getKey()).d());
                    arrayList.add(entry.getKey());
                }
            }
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.f10311a.remove((Integer) it.next());
                }
            }
            LogUtil.a("Track_SportHistoryListFragment", "mSportStartTimeMap size", Integer.valueOf(this.f10311a.size()));
            boolean z = this.f10311a.size() == 0;
            this.b = z;
            if (z) {
                if (this.d == null) {
                    this.d = knuVar;
                }
                sportHistoryListFragment.by.removeMessages(2);
                Message obtainMessage = sportHistoryListFragment.by.obtainMessage(2);
                obtainMessage.obj = this;
                sportHistoryListFragment.by.sendMessage(obtainMessage);
                this.f10311a.clear();
                LogUtil.a("Track_SportHistoryListFragment", "for MSG_SHOW_DETAIL_TRACK");
            }
        }

        private void b(knu knuVar, MotionPathSimplify motionPathSimplify, gxy gxyVar, RelativeSportData relativeSportData) {
            if (gxyVar == null) {
                return;
            }
            gxyVar.e(motionPathSimplify);
            gxyVar.c(motionPathSimplify.requestFatherSportItem());
            gxyVar.d(knuVar.d());
            gxyVar.c(relativeSportData);
        }
    }

    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SportHistoryListFragment> f10310a;

        private c(SportHistoryListFragment sportHistoryListFragment) {
            this.f10310a = new WeakReference<>(sportHistoryListFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Track_SportHistoryListFragment", "errorCode is ", Integer.valueOf(i), "objData is ", obj);
            if (!(obj instanceof MotionPathSimplify)) {
                LogUtil.h("Track_SportHistoryListFragment", "no sport data");
                return;
            }
            SportHistoryListFragment sportHistoryListFragment = this.f10310a.get();
            if (sportHistoryListFragment == null || sportHistoryListFragment.getActivity() == null) {
                ReleaseLogUtil.c("Track_SportHistoryListFragment", "SportHistoryListFragment is null in InnerSimplifyDataCallback");
            } else {
                new gze(sportHistoryListFragment.getActivity(), (MotionPathSimplify) obj, 1).c();
            }
        }
    }

    public static class m {
        public void a(kwy kwyVar, IMultiResponseCallback iMultiResponseCallback) {
            LogUtil.a("Track_SportHistoryListFragment", "getDetailSportData start ", Integer.valueOf(kwyVar.f()));
            int[] iArr = {0, 1};
            RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
            if (recordApi == null) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "SportDataRequestManager recordApi is null.");
                return;
            }
            kwy d = new kwy.a().a(kwyVar.a()).e(kwyVar.c()).b(kwyVar.e()).a(kwyVar.i()).d();
            if (kwyVar.f() == 0) {
                a aVar = new a(iArr, 2, iMultiResponseCallback);
                a.e eVar = new a.e(0, aVar);
                a.e eVar2 = new a.e(1, aVar);
                kor.a().b(kwyVar, eVar);
                LogUtil.a("Track_SportHistoryListFragment", "getDetailSportData form health platform");
                recordApi.acquireDetailFitnessRecords(d, eVar2);
                LogUtil.a("Track_SportHistoryListFragment", "getDetailSportData from suggestion db");
                return;
            }
            if (kwyVar.f() == 10001) {
                recordApi.acquireDetailFitnessRecords(d, new a.e(1, new a(iArr, 1, iMultiResponseCallback)));
                return;
            }
            kor.a().b(kwyVar, new a.e(0, new a(iArr, 1, iMultiResponseCallback)));
            LogUtil.a("Track_SportHistoryListFragment", "getDetailSportData form health platform");
        }

        public void a(int i, int i2, long j, long j2, boolean z, IMultiResponseCallback iMultiResponseCallback) {
            LogUtil.a("Track_SportHistoryListFragment", "getMonthData ", Integer.valueOf(i));
            int[] iArr = {0, 1, 2};
            RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
            if (recordApi == null) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "getMonthData recordApi is null.");
                return;
            }
            if (i == 0) {
                a aVar = new a(iArr, 2, iMultiResponseCallback);
                a.e eVar = new a.e(0, aVar);
                a.e eVar2 = new a.e(1, aVar);
                gts.b(BaseApplication.getContext()).e(j, j2, 5, i, eVar);
                LogUtil.a("Track_SportHistoryListFragment", "acquire data form health platform");
                recordApi.acquireSummaryFitnessRecord(new kwy.a().a(j).e(j2).c(5).d(), eVar2);
                LogUtil.a("Track_SportHistoryListFragment", "acquire data form fitness ");
                return;
            }
            if (i == 10001) {
                recordApi.acquireSummaryFitnessRecord(new kwy.a().a(j).e(j2).a(z ? i2 : 0).c(5).d(), new a.e(1, new a(iArr, 1, iMultiResponseCallback)));
                return;
            }
            if (i == 287) {
                a aVar2 = new a(iArr, 2, iMultiResponseCallback);
                a.e eVar3 = new a.e(0, aVar2);
                a.e eVar4 = new a.e(2, aVar2);
                gts.b(BaseApplication.getContext()).e(j, j2, 5, i, eVar3);
                kor.a().b(j, j2, 5, eVar4);
                LogUtil.a("Track_SportHistoryListFragment", "acquire data form SPORT_TYPE_FREE_DIVING ");
                return;
            }
            gts.b(BaseApplication.getContext()).e(j, j2, 5, i, new a.e(0, new a(iArr, 1, iMultiResponseCallback)));
            LogUtil.a("Track_SportHistoryListFragment", "acquire data form health platform 2 ");
        }

        public void c(int i, long j, long j2, IMultiResponseCallback iMultiResponseCallback) {
            LogUtil.a("Track_SportHistoryListFragment", "getYearData ", Integer.valueOf(i));
            int[] iArr = {0, 1, 2};
            RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
            if (recordApi == null) {
                ReleaseLogUtil.d("Track_SportHistoryListFragment", "getYearData recordApi is null.");
                return;
            }
            kwy d = new kwy.a().a(j).e(j2).c(6).d();
            if (i == 0) {
                a aVar = new a(iArr, 2, iMultiResponseCallback);
                a.e eVar = new a.e(0, aVar);
                a.e eVar2 = new a.e(1, aVar);
                kor.a().d(j, j2, 6, eVar);
                LogUtil.a("Track_SportHistoryListFragment", "getYearData() acquire data form health platform");
                recordApi.acquireSummaryFitnessRecord(d, eVar2);
                LogUtil.a("Track_SportHistoryListFragment", "getYearData() acquire data form fitness ");
                return;
            }
            if (i == 10001) {
                recordApi.acquireSummaryFitnessRecord(d, new a.e(1, new a(iArr, 1, iMultiResponseCallback)));
                LogUtil.a("Track_SportHistoryListFragment", "getYearData() acquire data form fitness 2");
                return;
            }
            if (i == 287) {
                a aVar2 = new a(iArr, 2, iMultiResponseCallback);
                a.e eVar3 = new a.e(0, aVar2);
                a.e eVar4 = new a.e(2, aVar2);
                kor.a().c(j, j2, 6, i, eVar3);
                kor.a().b(j, j2, 6, eVar4);
                LogUtil.a("Track_SportHistoryListFragment", "getYearData() acquire data form SPORT_TYPE_FREE_DIVING ");
                return;
            }
            kor.a().c(j, j2, 6, i, new a.e(0, new a(iArr, 1, iMultiResponseCallback)));
            LogUtil.a("Track_SportHistoryListFragment", "getYearData() acquire data form health platform 2 ");
        }

        static class a {
            private static final Object c = new Object();

            /* renamed from: a, reason: collision with root package name */
            private Object[] f10312a;
            private IMultiResponseCallback b;
            private int d;
            private int[] e;

            a(int[] iArr, int i, IMultiResponseCallback iMultiResponseCallback) {
                if (iArr != null) {
                    synchronized (c) {
                        this.d = i;
                    }
                    this.e = new int[iArr.length];
                    for (int i2 = 0; i2 < this.d; i2++) {
                        this.e[i2] = -2018;
                    }
                    this.f10312a = new Object[iArr.length];
                    this.b = iMultiResponseCallback;
                }
            }

            public void e(int i, int i2, Object obj) {
                boolean z;
                IMultiResponseCallback iMultiResponseCallback;
                synchronized (c) {
                    this.e[i] = i2;
                    this.f10312a[i] = obj;
                    int i3 = this.d - 1;
                    this.d = i3;
                    z = i3 <= 0;
                    LogUtil.a("Track_SportHistoryListFragment", "tryToNotifyData req type: ", Integer.valueOf(i), " request count ", Integer.valueOf(this.d));
                }
                if (!z || (iMultiResponseCallback = this.b) == null) {
                    return;
                }
                iMultiResponseCallback.onNotify(this.e, this.f10312a);
            }

            static class e implements IBaseResponseCallback {
                private int c;
                private a e;

                e(int i, a aVar) {
                    this.c = i;
                    this.e = aVar;
                }

                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("Track_SportHistoryListFragment", "SimpleCallback onResponse ", Integer.valueOf(i));
                    a aVar = this.e;
                    if (aVar != null) {
                        aVar.e(this.c, i, obj);
                    }
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
