package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewScrollInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.MedalAllShareInfoDesc;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.NewMedalTabDataBean;
import com.huawei.pluginachievement.ui.AchieveMedalNewActivity;
import com.huawei.pluginachievement.ui.adapter.ShareMedalItemDecoration;
import com.huawei.pluginachievement.ui.adapter.ShareMedalRecyclerAdapter;
import com.huawei.pluginachievement.ui.listenerinterface.MedalFragmentFreshListener;
import com.huawei.pluginachievement.ui.views.MedalHorizontalRecyclerViewAdapter;
import com.huawei.pluginachievement.ui.views.SpeedRecyclerView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.up.model.UserInfomation;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.med;
import defpackage.meh;
import defpackage.mfg;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mjp;
import defpackage.mkw;
import defpackage.mkx;
import defpackage.mlb;
import defpackage.mlg;
import defpackage.mlt;
import defpackage.mlu;
import defpackage.nqf;
import defpackage.nqx;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class AchieveMedalNewActivity extends BaseActivity implements View.OnClickListener, MedalFragmentFreshListener {
    private Bitmap aa;
    private SpeedRecyclerView ab;
    private HealthSubTabWidget ac;
    private LinearLayout ad;
    private LinearLayout ae;
    private HealthTextView af;
    private HealthViewPager ag;
    private LinearLayout ah;
    private HealthScrollView aj;
    private HealthRecycleView ak;
    private LinearLayout al;
    private List<String> ao;
    private Map<String, ArrayList<MedalInfoDesc>> as;
    private LinearLayout c;
    private Map<String, ArrayList<String>> d;
    private ShareMedalRecyclerAdapter e;
    private RelativeLayout f;
    private ImageView g;
    private nqx h;
    private mlt i;
    private Context l;
    private int m;
    private CustomTitleBar n;
    private int o;
    private HealthTextView p;
    private MedalHorizontalRecyclerViewAdapter r;
    private HiUserInfo s;
    private RelativeLayout u;
    private FrameLayout v;
    private HealthTextView w;
    private ImageView x;
    private ScrollView y;
    private ShareMedalItemDecoration z;
    private String b = "";

    /* renamed from: a, reason: collision with root package name */
    private String f8399a = "";
    private boolean am = false;
    private Handler k = new b(this);
    private boolean t = false;
    private ArrayList<MedalAllShareInfoDesc> an = new ArrayList<>(5);
    private Map<Integer, Integer> ai = new HashMap(16);
    private List<MedalInfoDesc> q = new ArrayList(5);
    private Boolean j = null;

    static /* synthetic */ int e(AchieveMedalNewActivity achieveMedalNewActivity) {
        int i = achieveMedalNewActivity.o;
        achieveMedalNewActivity.o = i + 1;
        return i;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null && Build.VERSION.SDK_INT == 29 && EnvironmentInfo.g()) {
            bundle.setClassLoader(getClassLoader());
        }
        super.onCreate(bundle);
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "oncreate");
        if (CommonUtil.bh() || SystemInfo.h()) {
            if (isLargerThanEmui910(CommonUtil.r()) || CommonUtil.az() || SystemInfo.h()) {
                getWindow().getDecorView().setSystemUiVisibility(9216);
                setStatusBarColor();
            }
            getWindow().setNavigationBarColor(getResources().getColor(R.color._2131298069_res_0x7f090715));
        }
        setContentView(R.layout.achieve_medal_new_layout);
        clearBackgroundDrawable();
        cancelAdaptRingRegion();
        this.l = this;
        f();
        e();
        n();
        nqf.d(this.l);
        med.d();
    }

    private void f() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.medal_titlebar);
        this.n = customTitleBar;
        customTitleBar.setRightButtonVisibility(4);
        this.n.setRightTextButtonVisibility(4);
        this.n.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
        this.n.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalNewActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!mjp.c()) {
                    AchieveMedalNewActivity.this.x();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.n.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalNewActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchieveMedalNewActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.n.setLeftButtonVisibility(0);
        if (LanguageUtil.bc(this.l)) {
            this.n.setRightButtonDrawable(nrz.cKn_(this.l, R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
            this.n.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429734_res_0x7f0b0966), nsf.h(R.string._2130850617_res_0x7f023339));
        } else {
            this.n.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429733_res_0x7f0b0965), nsf.h(R.string._2130850617_res_0x7f023339));
            this.n.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.n.setTitleTextColor(getResources().getColor(R.color._2131297371_res_0x7f09045b));
        this.n.setTitleText(BaseApplication.getContext().getResources().getString(R.string._2130840688_res_0x7f020c70));
    }

    private NewMedalFragment a(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString("kind", (i >= this.ao.size() || i < 0) ? "" : this.ao.get(i));
        NewMedalFragment newMedalFragment = new NewMedalFragment();
        newMedalFragment.setArguments(bundle);
        return newMedalFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (TextUtils.isEmpty(str) || this.as == null || koq.b(this.ao)) {
            return;
        }
        ArrayList<MedalInfoDesc> arrayList = this.as.get(this.l.getResources().getString(R.string._2130840758_res_0x7f020cb6));
        if (arrayList != null && arrayList.size() >= 4) {
            if (Utils.o()) {
                this.n.setRightButtonVisibility(4);
            } else {
                this.n.setRightButtonVisibility(0);
            }
            this.an.clear();
            Iterator<MedalInfoDesc> it = arrayList.iterator();
            while (it.hasNext()) {
                MedalInfoDesc next = it.next();
                this.an.add(new MedalAllShareInfoDesc(next.acquireMedalId(), next.acquireText(), next.acquireMedalTypeLevel()));
            }
            return;
        }
        this.n.setRightButtonVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        ArrayList<MedalInfoDesc> arrayList;
        if (this.as == null || koq.b(this.ao) || (arrayList = this.as.get(this.l.getResources().getString(R.string._2130840758_res_0x7f020cb6))) == null || arrayList.size() < 4) {
            return;
        }
        if (!this.am) {
            this.am = true;
            l();
        }
        j();
    }

    public void c(int i, int i2) {
        this.ai.put(Integer.valueOf(i), Integer.valueOf(i2));
        if (i == this.ag.getCurrentItem()) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.height = i2;
            this.ag.setLayoutParams(layoutParams);
        }
    }

    public void b(int i, int i2, int i3) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.height = i3;
        this.ag.setLayoutParams(layoutParams);
        c(i, i3);
    }

    private void n() {
        this.ao = new ArrayList(5);
        this.d = new HashMap(5);
        this.as = new HashMap(5);
        g();
    }

    private <T> void c(int i, T t, int i2) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        this.k.sendMessageDelayed(obtain, i2);
    }

    private void g() {
        NewMedalTabDataBean b2 = mjp.b();
        if (b2 != null) {
            this.ao = b2.getTabNewList();
            this.d = b2.getFirstTabRelationship();
            this.as = b2.getSecondTabRelationship();
            this.q = a(b2.getMedalInfoDesc());
            Handler handler = this.k;
            if (handler != null) {
                handler.sendEmptyMessage(2);
            }
        }
        mjp.e(new AchieveCallback() { // from class: mis
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public final void onResponse(int i, Object obj) {
                AchieveMedalNewActivity.this.c(i, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "getData#getInitData");
        if (obj instanceof NewMedalTabDataBean) {
            NewMedalTabDataBean newMedalTabDataBean = (NewMedalTabDataBean) obj;
            this.ao = newMedalTabDataBean.getTabNewList();
            this.d = newMedalTabDataBean.getFirstTabRelationship();
            this.as = newMedalTabDataBean.getSecondTabRelationship();
            this.q = a(newMedalTabDataBean.getMedalInfoDesc());
            meh.c(BaseApplication.getContext()).w();
            Handler handler = this.k;
            if (handler != null) {
                handler.sendEmptyMessage(2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        Map<String, ArrayList<MedalInfoDesc>> map;
        if (koq.b(this.ao) || (map = this.as) == null || map.size() == 0) {
            this.y.setVisibility(8);
            if (this.o != 1) {
                this.ad.setVisibility(0);
            }
            this.o = 0;
            return;
        }
        this.y.setVisibility(0);
        this.ad.setVisibility(8);
        if (koq.b(this.q)) {
            this.v.setVisibility(8);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, nsn.c(this.l, 80.0f), 0, 0);
            this.ac.setLayoutParams(layoutParams);
        } else {
            this.v.setVisibility(0);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.setMargins(0, nsn.c(this.l, 0.0f), 0, 0);
            this.ac.setLayoutParams(layoutParams2);
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "initView ", Integer.valueOf(this.ao.size()));
        d(this.ao.get(0));
        if (this.o == 1) {
            q();
            o();
            int i = 0;
            while (i < this.ao.size()) {
                this.h.c(this.ac.c(this.ao.get(i)), a(i), i == 0);
                i++;
            }
        } else {
            this.r.d(this.q, this.ao);
            mlt mltVar = this.i;
            if (mltVar != null) {
                mltVar.e(this.q, this.w, this.p);
            }
            for (int i2 = 0; i2 < this.ao.size(); i2++) {
                Fragment item = this.h.getItem(i2);
                if (item instanceof NewMedalFragment) {
                    ((NewMedalFragment) item).e();
                }
            }
        }
        c(3, Boolean.valueOf(this.o == 1), 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (this.ak == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "shareBitmapPreInit recyclerViewMedalList is null, return!");
        } else {
            m();
            this.ak.post(new Runnable() { // from class: mit
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveMedalNewActivity.this.h();
                }
            });
        }
    }

    public /* synthetic */ void h() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: miv
            @Override // java.lang.Runnable
            public final void run() {
                AchieveMedalNewActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "shareBitmapPreInit enter!");
        Bitmap cgH_ = mfp.cgH_(this.ah, this.aj, this.al, this.m);
        this.aa = cgH_;
        this.t = true;
        Object[] objArr = new Object[2];
        objArr[0] = "shareBitmapPreInit finish! mShareBitmap isExists:";
        objArr[1] = Boolean.valueOf(cgH_ != null);
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", objArr);
    }

    private void m() {
        LinearLayout linearLayout = this.c;
        if (linearLayout != null) {
            linearLayout.setVisibility((mfg.c(1) && mcx.e()) ? 0 : 8);
        }
    }

    private List<MedalInfoDesc> a(List<MedalInfoDesc> list) {
        if (list == null) {
            return list;
        }
        int size = list.size();
        this.m = size;
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "getGainMedalLocations mGainMedalNum = ", Integer.valueOf(size));
        return list.size() > 10 ? list.subList(0, 10) : list;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.c("PLGACHIEVE_AchieveMedalNewActivity", "onResume");
    }

    private void q() {
        this.y.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalNewActivity.3
            @Override // android.view.View.OnScrollChangeListener
            public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                if (i2 <= 0) {
                    AchieveMedalNewActivity.this.n.setBackgroundColor(Color.argb(0, 255, 255, 255));
                } else if (i2 <= 0 || i2 > 100) {
                    AchieveMedalNewActivity.this.n.setBackgroundColor(Color.argb(255, 0, 0, 0));
                } else {
                    AchieveMedalNewActivity.this.n.setBackgroundColor(Color.argb((int) ((i2 / 100.0f) * 255.0f), 0, 0, 0));
                }
                ViewScrollInstrumentation.scrollChangeOnView(view, i, i2, i3, i4);
            }
        });
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.ag.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalNewActivity.4
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (koq.b(AchieveMedalNewActivity.this.ao, i)) {
                    LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "onPageSelected position error:", Integer.valueOf(i));
                    return;
                }
                AchieveMedalNewActivity achieveMedalNewActivity = AchieveMedalNewActivity.this;
                achieveMedalNewActivity.d((String) achieveMedalNewActivity.ao.get(i));
                if (AchieveMedalNewActivity.this.ai.size() > i) {
                    LinearLayout.LayoutParams layoutParams2 = layoutParams;
                    AchieveMedalNewActivity achieveMedalNewActivity2 = AchieveMedalNewActivity.this;
                    layoutParams2.height = achieveMedalNewActivity2.d((Integer) achieveMedalNewActivity2.ai.get(Integer.valueOf(i)));
                } else {
                    layoutParams.height = mlu.f(AchieveMedalNewActivity.this.l);
                    LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "onPageSelected layoutParams.height:", Integer.valueOf(layoutParams.height));
                }
                AchieveMedalNewActivity.this.ag.setLayoutParams(layoutParams);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public Map<String, ArrayList<String>> d() {
        return this.d;
    }

    public Map<String, ArrayList<MedalInfoDesc>> b() {
        return this.as;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(String str) {
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "changeOneData enter");
        if (TextUtils.isEmpty(str) && this.as == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "changeOneData medalId =", str);
        for (Map.Entry<String, ArrayList<MedalInfoDesc>> entry : this.as.entrySet()) {
            String key = entry.getKey();
            ArrayList<MedalInfoDesc> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                MedalInfoDesc medalInfoDesc = value.get(i);
                if (str.equals(medalInfoDesc.acquireMedalId()) && medalInfoDesc.acquireIsNewConfig() > 0) {
                    LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "changeOneData secondTab =", key, " medalId=", str);
                    medalInfoDesc.saveIsNewConfig(0);
                    value.set(i, medalInfoDesc);
                    this.as.put(key, value);
                    a(key);
                    return;
                }
            }
        }
    }

    private void a(String str) {
        List<String> list;
        for (Map.Entry<String, ArrayList<String>> entry : this.d.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue().contains(str) && this.h != null && (list = this.ao) != null) {
                Fragment item = this.h.getItem(list.indexOf(key));
                if (item instanceof NewMedalFragment) {
                    ((NewMedalFragment) item).e();
                }
            }
        }
    }

    @Override // com.huawei.pluginachievement.ui.listenerinterface.MedalFragmentFreshListener
    public void onFragmentFreshListener(String str) {
        e(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "shareAllMedals isSharePreInitFinish :", Boolean.valueOf(this.t));
        if (this.t) {
            if (PermissionUtil.c()) {
                s();
                return;
            } else {
                PermissionUtil.b(this.l, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(this.l) { // from class: com.huawei.pluginachievement.ui.AchieveMedalNewActivity.5
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        AchieveMedalNewActivity.this.s();
                    }
                });
                return;
            }
        }
        this.t = true;
    }

    private void j() {
        if (this.ak == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "medalAllShareInfoDescs size =", Integer.valueOf(this.an.size()));
        ShareMedalItemDecoration shareMedalItemDecoration = this.z;
        if (shareMedalItemDecoration != null) {
            this.ak.removeItemDecoration(shareMedalItemDecoration);
        }
        ShareMedalItemDecoration shareMedalItemDecoration2 = new ShareMedalItemDecoration(this.l, this.an.size());
        this.z = shareMedalItemDecoration2;
        int a2 = shareMedalItemDecoration2.a();
        this.e = new ShareMedalRecyclerAdapter(this, this.an);
        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) this, a2, 1, false);
        this.ak.setIsScroll(false);
        this.ak.setLayoutManager(gridLayoutManager);
        this.ak.addItemDecoration(this.z);
        this.ak.setHasFixedSize(true);
        this.ak.setNestedScrollingEnabled(false);
        this.ak.setAdapter(this.e);
    }

    public void e() {
        this.ac = (HealthSubTabWidget) findViewById(R.id.achieve_medal_scrollable_tablayout);
        this.ag = (HealthViewPager) findViewById(R.id.my_medeal_viewpager);
        this.h = new nqx(this, this.ag, this.ac);
        this.w = (HealthTextView) findViewById(R.id.medal_name);
        if (nsn.s()) {
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.medal_gain_time_three_fold_fonts);
            this.p = healthTextView;
            healthTextView.setVisibility(0);
        } else {
            this.p = (HealthTextView) findViewById(R.id.medal_gain_time);
        }
        ScrollView scrollView = (ScrollView) findViewById(R.id.medal_new_scrollview);
        this.y = scrollView;
        scrollView.setNestedScrollingEnabled(false);
        this.x = (ImageView) findViewById(R.id.medal_light);
        this.u = (RelativeLayout) findViewById(R.id.medal_show_layout);
        this.ae = (LinearLayout) findViewById(R.id.subTabLayout);
        this.ad = (LinearLayout) findViewById(R.id.no_medal_layout);
        this.v = (FrameLayout) findViewById(R.id.medal_show_frame);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mlu.c(this), mlu.d(this));
        layoutParams.gravity = 1;
        this.u.setLayoutParams(layoutParams);
        if (nsn.ag(this.l)) {
            t();
        }
        this.f8399a = this.l.getResources().getString(R.string._2130840758_res_0x7f020cb6);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mlu.c(this), mlu.d(this));
        layoutParams.gravity = 1;
        this.u.setLayoutParams(layoutParams);
        t();
    }

    private void t() {
        LinearLayout linearLayout = this.ae;
        if (linearLayout == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "setCenterCardLayout() mViewPager is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        int i = 0;
        if (nsn.ag(this.l)) {
            i = (int) (r1.c() + new HealthColumnSystem(this.l, 0).g() + r1.a());
        }
        if (layoutParams2 != null) {
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i;
            this.ae.setLayoutParams(layoutParams2);
        }
    }

    private void l() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.all_share_layout);
        if (viewStub == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "initShareLayout ViewStub is loaded fail.");
            return;
        }
        RelativeLayout relativeLayout = (RelativeLayout) viewStub.inflate();
        this.f = relativeLayout;
        this.g = (ImageView) mfm.cgM_(relativeLayout, R.id.head_imageview);
        this.af = (HealthTextView) mfm.cgM_(this.f, R.id.name_textview);
        this.ah = (LinearLayout) mfm.cgM_(this.f, R.id.medal_all_ll_one);
        this.aj = (HealthScrollView) mfm.cgM_(this.f, R.id.medal_all_scrollview);
        this.al = (LinearLayout) mfm.cgM_(this.f, R.id.medal_information);
        this.ak = (HealthRecycleView) mfm.cgM_(this.f, R.id.medal_all_rv);
        this.c = (LinearLayout) mfm.cgM_(this.f, R.id.account_layout);
        if (Utils.o()) {
            this.al.setVisibility(8);
        }
        ImageView imageView = (ImageView) mfm.cgM_(this.f, R.id.vip_image);
        if (mkw.e()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        a();
    }

    private void o() {
        ViewStub viewStub = (ViewStub) findViewById(R.id.speedRecyclerView);
        if (viewStub == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "initRecyclerView ViewStub is loaded fail.");
            return;
        }
        this.ab = (SpeedRecyclerView) viewStub.inflate();
        this.ab.setLayoutManager(new LinearLayoutManager(this, 0, false));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.bottomMargin = mlu.g(this);
        layoutParams.addRule(12);
        this.ab.setLayoutParams(layoutParams);
        MedalHorizontalRecyclerViewAdapter medalHorizontalRecyclerViewAdapter = new MedalHorizontalRecyclerViewAdapter(this.l, this.q, this.ao);
        this.r = medalHorizontalRecyclerViewAdapter;
        this.ab.setAdapter(medalHorizontalRecyclerViewAdapter);
        e(this.ab);
        if (this.i == null) {
            this.i = new mlt();
        }
        this.i.ckI_(this.ab, this.x, this.w, this.p, this.q);
    }

    private void e(SpeedRecyclerView speedRecyclerView) {
        if (koq.b(this.q)) {
            return;
        }
        speedRecyclerView.smoothScrollToPosition(0);
        this.w.setText(this.q.get(0).acquireText());
        this.p.setText(mlb.m(this.q.get(0).acquireGainTime()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "enter share");
        if (!mcx.d(this.l)) {
            mlg.e(this.l);
            return;
        }
        if (mcv.d(this).getAdapter() != null) {
            HashMap hashMap = new HashMap(5);
            hashMap.put("name", this.b);
            hashMap.put("className", this.f8399a);
            Bitmap chH_ = chH_();
            if (chH_ != null) {
                int cgy_ = mfp.cgy_(chH_);
                LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "initView all medal share bitmap size =", Integer.valueOf(cgy_));
                if ((cgy_ / 1024) / 1024 >= 1) {
                    mcx.d(this.l, mfp.cgF_(this.l, chH_, "all_medal_share.jpg"), AnalyticsValue.SUCCESSES_SHARE_1100011.value(), hashMap);
                    LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "shareAll shareImageByPath.");
                } else {
                    mcx.cfN_(this.l, chH_, AnalyticsValue.SUCCESSES_SHARE_1100011.value(), hashMap);
                }
                this.j = Boolean.valueOf(mcx.e());
            } else {
                LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "screenCut is null");
            }
            if (mcv.e()) {
                ixx d = ixx.d();
                HashMap hashMap2 = new HashMap(5);
                hashMap2.put("type", 11);
                d.d(this.l, AnalyticsValue.SUCCESSES_SHARE_1100011.value(), hashMap2, 0);
                return;
            }
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "achieveAdapter is null");
    }

    private Bitmap chH_() {
        Boolean bool;
        if (this.aa == null || ((bool = this.j) != null && bool.booleanValue() != mcx.e())) {
            m();
            LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "cutShareScreen: isShowUserInfo -> " + this.j);
            this.aa = mfp.cgH_(this.ah, this.aj, this.al, this.m);
        }
        return this.aa;
    }

    public void a() {
        if (Utils.o()) {
            return;
        }
        w();
    }

    private void w() {
        if ("1".equals(SharedPreferenceManager.b(this.l, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
            LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "accountMigrate: isThirdLogin == 1 and return!");
            y();
        } else {
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new c(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, UserInfomation userInfomation) {
        if (i != 0) {
            this.k.sendEmptyMessage(1);
            return;
        }
        if (userInfomation == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "get userInfo obtain null objData");
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = userInfomation;
        obtain.what = 0;
        this.k.sendMessage(obtain);
    }

    private void y() {
        HiHealthManager.d(this.l).fetchUserData(new e(this));
    }

    static class b extends BaseHandler<AchieveMedalNewActivity> {
        b(AchieveMedalNewActivity achieveMedalNewActivity) {
            super(achieveMedalNewActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: chI_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchieveMedalNewActivity achieveMedalNewActivity, Message message) {
            int i = message.what;
            if (i == 0) {
                if (message.obj instanceof UserInfomation) {
                    achieveMedalNewActivity.b((UserInfomation) message.obj);
                    return;
                }
                return;
            }
            if (i == 1) {
                LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "MSG_GET_USER_INFO_FAIL");
                return;
            }
            if (i == 2) {
                AchieveMedalNewActivity.e(achieveMedalNewActivity);
                achieveMedalNewActivity.p();
                return;
            }
            if (i == 3) {
                achieveMedalNewActivity.k();
                if (!(message.obj instanceof Boolean) || ((Boolean) message.obj).booleanValue()) {
                    return;
                }
                sendEmptyMessageDelayed(6, 100L);
                return;
            }
            if (i == 4) {
                achieveMedalNewActivity.r();
            } else {
                if (i != 6) {
                    return;
                }
                achieveMedalNewActivity.v();
            }
        }
    }

    static class e implements HiCommonListener {
        private WeakReference<AchieveMedalNewActivity> b;

        e(AchieveMedalNewActivity achieveMedalNewActivity) {
            this.b = new WeakReference<>(achieveMedalNewActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            AchieveMedalNewActivity achieveMedalNewActivity = this.b.get();
            if (achieveMedalNewActivity != null && !achieveMedalNewActivity.isDestroyed()) {
                achieveMedalNewActivity.s = mkx.ckg_(obj, achieveMedalNewActivity.s, achieveMedalNewActivity.k, 4);
            } else {
                LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "AchieveMedalNewActivity is destoryed");
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "fetchUserData onFailure");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(UserInfomation userInfomation) {
        if (userInfomation != null) {
            String name = userInfomation.getName();
            if (TextUtils.isEmpty(name)) {
                this.af.setText(mcx.b());
            } else {
                this.af.setText(name);
            }
            String picPath = userInfomation.getPicPath();
            if (!TextUtils.isEmpty(picPath)) {
                Bitmap cIe_ = nrf.cIe_(this.l, picPath);
                if (cIe_ != null) {
                    this.g.setImageBitmap(cIe_);
                    return;
                } else {
                    e(userInfomation);
                    LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "handleWhenGetUserInfoSuccess() bitmap is null");
                    return;
                }
            }
            LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "handle WhenGetUserInfoSuccess()! headImgPath is null! ");
            this.g.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "handleWhenGetUserInfoSuccess()! userInfo is null! ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        HiUserInfo hiUserInfo = this.s;
        if (hiUserInfo == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "accountMigrate: setUserNameFromLocal mHiUserInfo == null");
            return;
        }
        this.af.setText(TextUtils.isEmpty(hiUserInfo.getName()) ? mcx.b() : this.s.getName());
        String b2 = b(this.s.getHeadImgUrl());
        if (!TextUtils.isEmpty(b2)) {
            Bitmap cIe_ = nrf.cIe_(this.l, b2);
            if (cIe_ != null) {
                this.g.setImageBitmap(cIe_);
                return;
            }
            return;
        }
        this.g.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "accountMigrate: setUserNameFromLocal() headImgPath is null! ");
    }

    private void e(UserInfomation userInfomation) {
        if (userInfomation.getPortraitUrl() == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "Missing the headImage url!");
        }
    }

    private String b(String str) {
        String str2;
        if (str == null || str.isEmpty()) {
            return "";
        }
        String[] split = str.split("/");
        if (split.length > 0) {
            str2 = this.l.getFilesDir() + "/photos/headimage/" + split[split.length - 1];
        } else {
            str2 = this.l.getFilesDir() + "/photos/headimage/" + str;
        }
        if (new File(str2).exists()) {
            LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "accountMigrate: getHeadImageFromLocal file.exists() yes");
            return str2;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalNewActivity", "accountMigrate: getHeadImageFromLocal file.exists() no");
        File[] listFiles = new File(this.l.getFilesDir() + "/photos/headimage").listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return str2;
        }
        try {
            return listFiles[listFiles.length - 1].getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalNewActivity", "getHeadImageFromLocal IOException");
            return str2;
        }
    }

    static class c implements BaseResponseCallback<UserInfomation> {
        private final WeakReference<AchieveMedalNewActivity> e;

        c(AchieveMedalNewActivity achieveMedalNewActivity) {
            this.e = new WeakReference<>(achieveMedalNewActivity);
        }

        @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, UserInfomation userInfomation) {
            if (userInfomation == null) {
                LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "refreshUserInfo getUserInfo userInfomation is null");
                return;
            }
            WeakReference<AchieveMedalNewActivity> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("PLGACHIEVE_AchieveMedalNewActivity", "refreshUserInfo getUserInfo mReference is null");
                return;
            }
            AchieveMedalNewActivity achieveMedalNewActivity = weakReference.get();
            if (achieveMedalNewActivity != null) {
                achieveMedalNewActivity.d(i, userInfomation);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
