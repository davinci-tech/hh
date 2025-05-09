package com.huawei.ui.main.stories.history;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hms.common.data.DataHolder;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.spinner.HealthSpinner;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment;
import com.huawei.ui.main.stories.history.inputhistory.InputSportHistoryActivity;
import com.huawei.ui.main.stories.history.view.SportArrayAdapter;
import defpackage.hln;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kpm;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.rdf;
import defpackage.rds;
import defpackage.rdu;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class SportHistoryActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f10287a = false;
    private CustomTitleBar b;
    private HealthTextView f;
    private Animation g;
    private HealthTextView h;
    private Animation i;
    private LinearLayout k;
    private SportHistoryListFragment m;
    private String[] n;
    private HealthSpinner p;
    private HealthSpinner q;
    private CopyOnWriteArrayList<Integer> l = new CopyOnWriteArrayList<>();
    private ArrayList<Integer> s = new ArrayList<>();
    private int o = 0;
    private int t = 0;
    private boolean e = false;
    private boolean c = false;
    private boolean j = true;
    private Handler d = new Handler() { // from class: com.huawei.ui.main.stories.history.SportHistoryActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                SportHistoryActivity.this.e = true;
            } else if (i == 2) {
                SportHistoryActivity.this.m();
                return;
            } else if (i == 3) {
                if (SportHistoryActivity.this.e) {
                    return;
                } else {
                    SportHistoryActivity.this.e = true;
                }
            }
            if (SportHistoryActivity.this.e) {
                if (!SportHistoryActivity.this.l.contains(Integer.valueOf(SportHistoryActivity.this.o))) {
                    SportHistoryActivity.this.o = 0;
                }
                removeMessages(3);
                SportHistoryActivity.this.p();
                if (SportHistoryActivity.this.e() != 0) {
                    return;
                }
                SportHistoryActivity.this.m.b(SportHistoryActivity.this.o);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        t();
        if (!this.s.contains(Integer.valueOf(e()))) {
            LogUtil.h("Track_TrackSportHistoryActivity", "mSubSportType is ", Integer.valueOf(e()), " list is ", this.s);
            d(0);
            this.m.d(0);
        } else if (e() != 0) {
            this.m.d(e());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_TrackSportHistoryActivity", "onCreate");
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_track_sport_history);
        j();
        l();
        h();
        f();
        this.d.sendEmptyMessageDelayed(3, 5000L);
        ThreadPoolManager.d().execute(new Runnable() { // from class: rce
            @Override // java.lang.Runnable
            public final void run() {
                SportHistoryActivity.this.b();
            }
        });
        e(this.o);
    }

    public /* synthetic */ void b() {
        o();
        k();
        if (f10287a) {
            return;
        }
        HiHealthNativeApi.a(this).synCloud(HiSyncOption.getSyncOption(5, 2), null);
        f10287a = true;
    }

    private void k() {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(this).setPageId(120).build());
        }
    }

    private void l() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (supportFragmentManager.findFragmentById(R.id.frag_sport_history_list) == null) {
            SportHistoryListFragment sportHistoryListFragment = new SportHistoryListFragment(this.o);
            this.m = sportHistoryListFragment;
            beginTransaction.add(R.id.frag_sport_history_list, sportHistoryListFragment);
            beginTransaction.commit();
        } else if (supportFragmentManager.findFragmentById(R.id.frag_sport_history_list) instanceof SportHistoryListFragment) {
            this.m = (SportHistoryListFragment) supportFragmentManager.findFragmentById(R.id.frag_sport_history_list);
        }
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sport_history_titlebar);
        this.b = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getColor(R.color._2131296690_res_0x7f0901b2));
        this.p = this.b.getTitleSpinner();
        this.b.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430065_res_0x7f0b0ab1), nsf.h(R$string.IDS_contact_add));
        this.b.setRightButtonOnClickListener(new View.OnClickListener() { // from class: rby
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportHistoryActivity.this.dJQ_(view);
            }
        });
        this.b.setRightSoftkeyVisibility(0);
        this.b.setRightSoftkeyBackground(ContextCompat.getDrawable(this, R.drawable._2131430066_res_0x7f0b0ab2), nsf.h(R$string.IDS_hwh_motiontrack_sport_data_statistics));
        this.b.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: rcd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportHistoryActivity.this.dJR_(view);
            }
        });
        n();
    }

    public /* synthetic */ void dJQ_(View view) {
        d(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dJR_(View view) {
        d(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n() {
        this.k = (LinearLayout) findViewById(R.id.ll_sport_history_scroll_title);
        this.h = (HealthTextView) findViewById(R.id.htv_sport_history_cut_time);
        this.f = (HealthTextView) findViewById(R.id.htv_sport_history_cut_content);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        this.i = alphaAnimation;
        alphaAnimation.setDuration(150L);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.1f);
        this.g = alphaAnimation2;
        alphaAnimation2.setDuration(150L);
        this.g.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.ui.main.stories.history.SportHistoryActivity.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SportHistoryActivity.this.k.setVisibility(8);
            }
        });
    }

    public void c(int i) {
        LinearLayout linearLayout = this.k;
        if (linearLayout == null || linearLayout.getVisibility() == i) {
            return;
        }
        if (i == 0) {
            this.k.setVisibility(i);
            this.k.startAnimation(this.i);
        } else {
            this.k.startAnimation(this.g);
        }
    }

    public void b(String str, String str2) {
        if (this.h != null && !TextUtils.isEmpty(str)) {
            this.h.setText(str);
        }
        if (this.f == null || TextUtils.isEmpty(str2)) {
            return;
        }
        this.f.setText(str2);
    }

    public void d(boolean z) {
        if (this.c) {
            LogUtil.a("Track_TrackSportHistoryActivity", "don't click to fast");
            return;
        }
        this.d.postDelayed(new Runnable() { // from class: rcf
            @Override // java.lang.Runnable
            public final void run() {
                SportHistoryActivity.this.c();
            }
        }, 500L);
        this.c = true;
        if (z) {
            LogUtil.a("Track_TrackSportHistoryActivity", "onClick() add sport data");
            startActivity(new Intent(this, (Class<?>) InputSportHistoryActivity.class));
            b(AnalyticsValue.SPORT_RECORD_ADD_MOTION_RECORD_2040186.value());
            return;
        }
        Intent intent = new Intent(this, (Class<?>) SportDataActivity.class);
        if (koq.e(this.l.clone(), Integer.class)) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.addAll(this.l);
            intent.putIntegerArrayListExtra("type_list", arrayList);
            intent.putExtra(DataHolder.TYPE_INT, this.o);
            startActivity(intent);
        }
        b(AnalyticsValue.BI_TRACK_SHOW_STAT_1040035.value());
    }

    public /* synthetic */ void c() {
        this.c = false;
    }

    private void b(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.o));
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    public int e() {
        return this.t;
    }

    public void d(int i) {
        this.t = i;
    }

    public void h() {
        kpm.d(new c(this));
    }

    public void f() {
        if (this.o == 10001 && this.j) {
            kpm.e(new a(this));
            this.j = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (koq.b(this.l)) {
            ReleaseLogUtil.d("Track_TrackSportHistoryActivity", "mSportSortList is empty");
            return;
        }
        ArrayAdapter<String> dJP_ = dJP_(rdu.e(this.l, this));
        HealthSpinner healthSpinner = this.p;
        if (healthSpinner != null) {
            healthSpinner.setAdapter((SpinnerAdapter) dJP_);
            this.p.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.ui.main.stories.history.SportHistoryActivity.4
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    LogUtil.a("Track_TrackSportHistoryActivity", "onItemSelected");
                    SportHistoryActivity sportHistoryActivity = SportHistoryActivity.this;
                    sportHistoryActivity.o = rdu.d(i, sportHistoryActivity.l);
                    SportHistoryActivity.this.f();
                    SportHistoryActivity.this.i();
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                }
            });
            int c2 = rdu.c(this.o, this.l);
            LogUtil.a("Track_TrackSportHistoryActivity", "updateSpinner mSportType = ", Integer.valueOf(this.o), " selection = ", Integer.valueOf(c2));
            this.p.setSelection(c2, true);
            this.p.setClickable(false);
            SportHistoryListFragment sportHistoryListFragment = this.m;
            if (sportHistoryListFragment != null) {
                sportHistoryListFragment.d(new IBaseResponseCallback() { // from class: rcb
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        SportHistoryActivity.this.e(i, obj);
                    }
                });
            }
        }
    }

    public /* synthetic */ void e(int i, Object obj) {
        this.p.setClickable(true);
    }

    private void t() {
        ArrayList<Integer> arrayList;
        this.n = rdu.d(this.s, this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.health_commonui_spinner_item_appbar, this.n);
        arrayAdapter.setDropDownViewResource(R.layout.hwspinner_dropdown_item);
        HealthSpinner a2 = this.m.a();
        this.q = a2;
        if (a2 == null || (arrayList = this.s) == null || arrayList.size() <= 1) {
            return;
        }
        this.q.setAdapter((SpinnerAdapter) arrayAdapter);
        this.q.setSelection(rdu.c(e(), this.s), true);
        this.q.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.huawei.ui.main.stories.history.SportHistoryActivity.5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                LogUtil.a("Track_TrackSportHistoryActivity", "onItemSubSelected");
                SportHistoryActivity.this.a(i);
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
        if (this.o == 10001) {
            this.q.setVisibility(0);
        }
    }

    private void j() {
        g();
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra(BleConstants.SPORT_TYPE, 0);
            this.o = intExtra;
            if (intExtra == 264) {
                this.o = 258;
            } else if (intExtra == 266) {
                this.o = 262;
            } else if (intExtra == 10001) {
                d(intent.getIntExtra("subSportType", 0));
            } else if (intExtra == 282) {
                this.o = 257;
            } else if (intExtra == 283) {
                d(intent.getIntExtra("subSportType", 0));
            } else if (intExtra == 289 || intExtra == 288 || intExtra == 291) {
                this.o = 287;
            } else if (!hln.c(getApplicationContext()).e().contains(Integer.valueOf(this.o))) {
                this.o = 258;
            }
            if ("jumpFromFileSyncNotify".equals(intent.getStringExtra("entrance"))) {
                LogUtil.a("Track_TrackSportHistoryActivity", "BI JUMP_FROM_FILE_SYNC_NOTIFY");
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(this, AnalyticsValue.HEALTH_JUMP_SPORT_HISTORY_FROM_NOTIFY_1040064.value(), hashMap, 0);
            }
        }
    }

    private void g() {
        this.l.add(0);
        this.l.add(258);
        this.l.add(257);
        this.l.add(259);
        this.l.add(10001);
        this.l.add(283);
    }

    public void e(int i) {
        if (!rds.d(i) || i == 0) {
            this.b.setRightButtonVisibility(0);
        } else {
            this.b.setRightButtonVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.q == null) {
            this.q = this.m.a();
        }
        if (this.o == 10001 && this.s.size() > 1) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(8);
        }
        if (e() != 0) {
            this.m.d(e());
        }
        this.m.b(this.o);
        ThreadPoolManager.d().execute(new Runnable() { // from class: rbx
            @Override // java.lang.Runnable
            public final void run() {
                SportHistoryActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.o));
        hashMap.put("category", 0);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BI_TRACK_HISTORY_CHANGE_SPORT_TYPE_1040034.value(), hashMap, 0);
    }

    public void a(final int i) {
        d(this.s.get(i).intValue());
        LogUtil.a("Track_TrackSportHistoryActivity", Integer.valueOf(i), " ", Integer.valueOf(e()));
        this.m.a(e());
        ThreadPoolManager.d().execute(new Runnable() { // from class: rcc
            @Override // java.lang.Runnable
            public final void run() {
                SportHistoryActivity.this.b(i);
            }
        });
    }

    public /* synthetic */ void b(int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.o));
        hashMap.put("category", Integer.valueOf(e()));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BI_TRACK_HISTORY_CHANGE_SPORT_TYPE_1040034.value(), hashMap, 0);
        h(i);
    }

    private void h(int i) {
        String[] strArr = this.n;
        if (strArr == null || strArr.length <= i) {
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("dataType", this.n[i]);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SPORT_RECORD_CHANGE_FITNESS_TYPE_2040193.value(), hashMap, 0);
    }

    private void o() {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_FITNESS_DOT", String.valueOf(false), new StorageParams());
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "SPORT_CARD_RED_DOT_FOR_AUTO_TRACK", String.valueOf(false), new StorageParams());
        LogUtil.a("Track_TrackSportHistoryActivity", "set SHOW_FITNESS_RED_DOT false");
        String l = Long.toString(System.currentTimeMillis());
        String usetId = LoginInit.getInstance(BaseApplication.getContext()).getUsetId();
        StorageParams storageParams = new StorageParams();
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "in_sport_history_activity_time", l + "##" + usetId, storageParams);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.d.removeCallbacksAndMessages(null);
        rdf.e();
    }

    public boolean a() {
        if (getIntent() == null) {
            return false;
        }
        return getIntent().getBooleanExtra("ExitApp", false);
    }

    /* loaded from: classes7.dex */
    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<SportHistoryActivity> f10290a;

        c(SportHistoryActivity sportHistoryActivity) {
            this.f10290a = new WeakReference<>(sportHistoryActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            WeakReference<SportHistoryActivity> weakReference = this.f10290a;
            if (weakReference != null && weakReference.get() != null) {
                this.f10290a.get().b(i, obj);
            } else {
                ReleaseLogUtil.d("Track_TrackSportHistoryActivity", "mActivityReference == null ||  mActivityReference.get() == null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Object obj) {
        if (i == 0 && koq.e(obj, Integer.class)) {
            this.l.clear();
            g();
            this.l.addAll((List) obj);
            CopyOnWriteArrayList<Integer> c2 = rdu.c(this.l, this);
            this.l = c2;
            if (this.d != null) {
                ReleaseLogUtil.e("Track_TrackSportHistoryActivity", "mSportSortList.size()  ", Integer.valueOf(c2.size()), " mSportSortList == ", this.l.toString());
                this.d.sendMessage(this.d.obtainMessage(1));
                return;
            }
            return;
        }
        this.e = true;
        this.d.removeMessages(3);
    }

    /* loaded from: classes7.dex */
    public class a implements IBaseResponseCallback {
        private WeakReference<SportHistoryActivity> c;

        a(SportHistoryActivity sportHistoryActivity) {
            this.c = new WeakReference<>(sportHistoryActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            WeakReference<SportHistoryActivity> weakReference = this.c;
            if (weakReference == null || weakReference.get() == null) {
                ReleaseLogUtil.d("Track_TrackSportHistoryActivity", "mActivityReference == null ||  mActivityReference.get() == null");
                return;
            }
            final SportHistoryActivity sportHistoryActivity = this.c.get();
            if (sportHistoryActivity == null || sportHistoryActivity.m == null || sportHistoryActivity.s == null || i != 0 || !koq.e(obj, Integer.class)) {
                return;
            }
            ArrayList arrayList = (ArrayList) obj;
            if (arrayList.size() > 0) {
                sportHistoryActivity.m.c(true);
                sportHistoryActivity.s.add(0);
                sportHistoryActivity.s.addAll(arrayList);
                if (SportHistoryActivity.this.d != null) {
                    SportHistoryActivity.this.d.sendMessage(SportHistoryActivity.this.d.obtainMessage(2));
                    return;
                }
                return;
            }
            HandlerExecutor.a(new Runnable() { // from class: rcj
                @Override // java.lang.Runnable
                public final void run() {
                    SportHistoryActivity.a.d(SportHistoryActivity.this);
                }
            });
        }

        public static /* synthetic */ void d(SportHistoryActivity sportHistoryActivity) {
            sportHistoryActivity.d(0);
            sportHistoryActivity.m.a(0);
            sportHistoryActivity.m.c(false);
        }
    }

    private ArrayAdapter<String> dJP_(String[] strArr) {
        if (nsn.r()) {
            return new ArrayAdapter<>(this, R.layout.health_large_fonts_spinner_item_appbar, strArr);
        }
        return new SportArrayAdapter(this, R.layout.sport_spinner, strArr, this.l);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
