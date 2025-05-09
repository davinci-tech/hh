package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.motiontrack.model.runningroute.HotPathCityInfo;
import com.huawei.healthcloud.plugintrack.runningroute.manager.IGetPoiByLocationName;
import com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteLocationSearchActivity;
import com.huawei.healthcloud.plugintrack.ui.adapter.RunningRouteSearchSuggestAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import defpackage.dpf;
import defpackage.gza;
import defpackage.hjd;
import defpackage.nrv;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class RunningRouteLocationSearchActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f3662a;
    private Context b;
    private ConstraintLayout c;
    private HealthSearchView d;
    private RunningRouteSearchSuggestAdapter e;
    private View g;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d("RunningRouteLocationSearchActivity", "onCreate");
        this.b = this;
        setContentView(R.layout.activity_running_route_location_search);
        this.f3662a = getIntent().getStringExtra(DistrictSearchQuery.KEYWORDS_CITY);
        f();
        c();
        d();
        e();
        a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        LogUtil.d("RunningRouteLocationSearchActivity", "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        i();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HealthSearchView healthSearchView = this.d;
        if (healthSearchView != null) {
            healthSearchView.setOnQueryTextListener(null);
        }
    }

    private void f() {
        nsn.cLH_(this, R.color._2131296690_res_0x7f0901b2);
        this.g = findViewById(R.id.statusbar_panel);
        i();
        this.c = (ConstraintLayout) findViewById(R.id.search_result_empty_layout);
    }

    private void e() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.running_route_location_search_suggest_recycler_view);
        healthRecycleView.a(false);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this));
        RunningRouteSearchSuggestAdapter runningRouteSearchSuggestAdapter = new RunningRouteSearchSuggestAdapter(this, new ArrayList(), new ArrayList());
        this.e = runningRouteSearchSuggestAdapter;
        healthRecycleView.setAdapter(runningRouteSearchSuggestAdapter);
        healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteLocationSearchActivity.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                RunningRouteLocationSearchActivity.this.d.clearFocus();
            }
        });
    }

    private void d() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.running_route_location_search_button);
        if (nsn.r()) {
            healthTextView.setTextSize(1, getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446));
        }
        nsy.cMn_(healthTextView, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteLocationSearchActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(RunningRouteLocationSearchActivity.this.d.getQuery())) {
                    RunningRouteLocationSearchActivity runningRouteLocationSearchActivity = RunningRouteLocationSearchActivity.this;
                    runningRouteLocationSearchActivity.a(runningRouteLocationSearchActivity.d.getQuery());
                } else {
                    LogUtil.d("RunningRouteLocationSearchActivity", "queryText is empty");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d(HealthSearchView healthSearchView) {
        healthSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteLocationSearchActivity.4
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                nsy.cMA_(RunningRouteLocationSearchActivity.this.c, 8);
                if (TextUtils.isEmpty(str)) {
                    RunningRouteLocationSearchActivity.this.e.d();
                    return false;
                }
                gza b = gza.b();
                RunningRouteLocationSearchActivity runningRouteLocationSearchActivity = RunningRouteLocationSearchActivity.this;
                b.d(runningRouteLocationSearchActivity, str, runningRouteLocationSearchActivity.f3662a, new d(RunningRouteLocationSearchActivity.this, str));
                return true;
            }
        });
    }

    private void c() {
        this.d = (HealthSearchView) findViewById(R.id.running_route_location_search_view);
        if (nsn.s()) {
            this.d.getLayoutParams().width = -2;
        }
        this.d.setQueryHint(getString(R.string._2130840117_res_0x7f020a35));
        this.d.requestFocus();
        dpf.Yu_(this.d, new TextView.OnEditorActionListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteLocationSearchActivity.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                RunningRouteLocationSearchActivity.this.a(RunningRouteLocationSearchActivity.this.d.getQuery());
                return true;
            }
        });
        d(this.d);
    }

    private void i() {
        RelativeLayout.LayoutParams cLc_ = nsn.cLc_(this.b);
        View view = this.g;
        if (view == null || cLc_ == null) {
            return;
        }
        view.setLayoutParams(cLc_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && !this.e.b()) {
            nsy.cMA_(this.c, 0);
        }
        this.d.clearFocus();
    }

    private void a() {
        ImageView imageView = (ImageView) findViewById(R.id.running_route_location_search_back);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            imageView.setImageDrawable(nrz.cKm_(this, ContextCompat.getDrawable(getApplicationContext(), R.drawable._2131428438_res_0x7f0b0456)));
        } else {
            imageView.setImageResource(R.drawable._2131428438_res_0x7f0b0456);
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: hfb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunningRouteLocationSearchActivity.this.bcA_(view);
            }
        });
    }

    public /* synthetic */ void bcA_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        super.onBackPressed();
    }

    public void c(HotPathCityInfo hotPathCityInfo) {
        Intent intent = new Intent();
        intent.putExtra("SEARCH_JUMP_CITY_INFO", nrv.a(hotPathCityInfo));
        setResult(-1, intent);
        finish();
    }

    /* loaded from: classes8.dex */
    static class d implements IGetPoiByLocationName {
        private String b;
        private WeakReference<RunningRouteLocationSearchActivity> d;

        public d(RunningRouteLocationSearchActivity runningRouteLocationSearchActivity, String str) {
            this.d = new WeakReference<>(runningRouteLocationSearchActivity);
            this.b = str;
        }

        @Override // com.huawei.healthcloud.plugintrack.runningroute.manager.IGetPoiByLocationName
        public void onPoiListBack(final List<String> list, final List<hjd> list2, final List<String> list3) {
            WeakReference<RunningRouteLocationSearchActivity> weakReference = this.d;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.d("RunningRouteLocationSearchActivity", "GetPoiByLocationNameCallBack: mWeakRef is null");
            }
            final RunningRouteLocationSearchActivity runningRouteLocationSearchActivity = this.d.get();
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteLocationSearchActivity.d.5
                @Override // java.lang.Runnable
                public void run() {
                    runningRouteLocationSearchActivity.e.b(d.this.b);
                    runningRouteLocationSearchActivity.e.c(list, list3, list2);
                }
            });
        }
    }
}
