package com.huawei.ui.homehealth.search;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.searchview.HealthSearchView;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.adapter.SearchSuggestAdapter;
import com.huawei.ui.homehealth.search.template.SearchResultConfig;
import com.huawei.ui.homehealth.search.template.SearchSubTabConfig;
import defpackage.dpf;
import defpackage.eiw;
import defpackage.fbh;
import defpackage.ixu;
import defpackage.jcf;
import defpackage.koq;
import defpackage.kps;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.otf;
import defpackage.otj;
import defpackage.rxy;
import health.compact.a.IoUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class GlobalSearchActivity extends BaseActivity {
    private static final Object b = new Object();
    private static String c;
    private static InputBoxTemplate d;
    private View f;
    private Context i;
    private KnitFragment j;
    private rxy<otf> k;
    private SearchSubTabConfig l;
    private SearchResultFragment m;
    private String n;
    private String o;
    private String p;
    private SearchResultConfig q;
    private HealthSearchView r;
    private HealthRecycleView s;
    private SearchSuggestAdapter t;
    private View u;
    private boolean e = false;
    private boolean g = true;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9585a = true;
    private boolean h = true;

    public static /* synthetic */ void e(int i, Object obj) {
    }

    public static String b() {
        InputBoxTemplate inputBoxTemplate = d;
        if (inputBoxTemplate == null) {
            LogUtil.c("GlobalSearchActivity", "getLessonSubCategory mInputBoxTemplate is null");
            return "";
        }
        return inputBoxTemplate.getLessonSubCategory();
    }

    public static String d() {
        String str;
        synchronized (b) {
            str = c;
        }
        return str;
    }

    public static void e(String str) {
        synchronized (b) {
            c = str;
        }
    }

    private void d(boolean z) {
        if (z) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = supportFragmentManager.getFragments();
            if (koq.b(fragments)) {
                return;
            }
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            Iterator<Fragment> it = fragments.iterator();
            while (it.hasNext()) {
                beginTransaction.remove(it.next());
            }
            beginTransaction.commitNow();
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        String str;
        CharSequence query = this.r.getQuery();
        if (!this.f9585a && (str = c) != null && query != null && str.contentEquals(query)) {
            bundle.putString("lastSearchedQueryText", c);
        }
        bundle.putString("lastInputQueryText", query != null ? query.toString() : null);
        super.onSaveInstanceState(bundle);
    }

    private void dgO_(Bundle bundle) {
        if (bundle != null) {
            this.o = bundle.getString("lastSearchedQueryText", null);
            this.n = bundle.getString("lastInputQueryText", null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        nsn.cLf_(this, bundle);
        super.onCreate(bundle);
        LogUtil.d("GlobalSearchActivity", "onCreate");
        d(bundle != null);
        this.i = this;
        setContentView(R.layout.activity_global_search);
        q();
        fbh.a(BaseApplication.e());
        dgO_(bundle);
    }

    private void q() {
        nsn.cLH_(this, R.color._2131296690_res_0x7f0901b2);
        this.u = findViewById(R.id.statusbar_panel);
        t();
        n();
        l();
        s();
        g();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        LogUtil.d("GlobalSearchActivity", "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        t();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        e();
    }

    private void t() {
        RelativeLayout.LayoutParams cLc_ = nsn.cLc_(this.i);
        View view = this.u;
        if (view == null || cLc_ == null) {
            return;
        }
        view.setLayoutParams(cLc_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.f == null) {
            this.f = findViewById(R.id.superui_fragment_container);
        }
        jcf.bEE_(this.f, i);
    }

    private void u() {
        KnitFragment knitFragment = this.j;
        if (knitFragment == null) {
            return;
        }
        b(this.m, knitFragment);
        this.f9585a = true;
        LogUtil.d("GlobalSearchActivity", "switched to default page");
        c(1);
    }

    private void v() {
        SearchResultFragment searchResultFragment = this.m;
        if (searchResultFragment == null || this.j == null) {
            ReleaseLogUtil.a("GlobalSearchActivity", "switchToDefaultPage mResultFragment or mDefaultFragment is null");
            return;
        }
        if (!this.f9585a) {
            searchResultFragment.a();
            return;
        }
        searchResultFragment.a();
        b(this.j, this.m);
        this.f9585a = false;
        LogUtil.d("GlobalSearchActivity", "switched to result page");
        c(1);
    }

    private void g() {
        if (this.j == null) {
            return;
        }
        getSupportFragmentManager().beginTransaction().add(R.id.superui_fragment_container, this.j).commit();
    }

    private void b(Fragment fragment, Fragment fragment2) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!fragment2.isAdded()) {
            beginTransaction.hide(fragment).add(R.id.superui_fragment_container, fragment2).commitAllowingStateLoss();
        } else {
            beginTransaction.hide(fragment).show(fragment2).commitAllowingStateLoss();
        }
    }

    private void l() {
        this.r = (HealthSearchView) findViewById(R.id.global_search_view);
        if (nsn.s()) {
            this.r.getLayoutParams().width = -2;
        }
        this.r.requestFocus();
        p();
        f();
        k();
        dpf.Yu_(this.r, new TextView.OnEditorActionListener() { // from class: com.huawei.ui.homehealth.search.GlobalSearchActivity.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                CharSequence query = GlobalSearchActivity.this.r.getQuery();
                if (TextUtils.isEmpty(query)) {
                    GlobalSearchActivity.this.j();
                    return true;
                }
                fbh.b(BaseApplication.e(), query.toString());
                GlobalSearchActivity.this.d(query.toString());
                return true;
            }
        });
        this.r.setOnQueryTextListener(new b(this));
    }

    private void f() {
        ((ImageView) findViewById(R.id.global_search_back)).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.search.GlobalSearchActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GlobalSearchActivity.this.i();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void k() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.global_search_button);
        if (nsn.r()) {
            healthTextView.setTextSize(1, 28.0f);
        }
        nsy.cMn_(healthTextView, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.search.GlobalSearchActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(GlobalSearchActivity.this.r.getQuery())) {
                    fbh.b(BaseApplication.e(), GlobalSearchActivity.this.r.getQuery().toString());
                    GlobalSearchActivity globalSearchActivity = GlobalSearchActivity.this;
                    globalSearchActivity.d(globalSearchActivity.r.getQuery().toString());
                } else {
                    LogUtil.d("GlobalSearchActivity", "queryText is empty");
                    GlobalSearchActivity.this.j();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        InputBoxTemplate inputBoxTemplate = d;
        if (inputBoxTemplate == null) {
            LogUtil.c("GlobalSearchActivity", "doDefaultSearch inputBoxTemplate is null");
            return;
        }
        String linkType = inputBoxTemplate.getLinkType() != null ? d.getLinkType() : "search";
        linkType.hashCode();
        if (!linkType.equals("normal")) {
            if (linkType.equals("search")) {
                fbh.e(BaseApplication.e(), d);
                d(d.getTheme());
                return;
            }
            return;
        }
        if (!LoginInit.getInstance(BaseApplication.e()).getIsLogined()) {
            LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: osn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    GlobalSearchActivity.e(i, obj);
                }
            }, "");
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            fbh.e(BaseApplication.e(), d);
            marketRouterApi.router(d.getLinkValue());
        }
    }

    private void p() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        InputBoxTemplate inputBoxTemplate = (InputBoxTemplate) intent.getParcelableExtra("BUNDLE_KEY_INPUT_BOX");
        d = inputBoxTemplate;
        dpf.b(this.r, inputBoxTemplate);
        if (d != null) {
            fbh.c(BaseApplication.e(), d);
        }
    }

    private void n() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.search_suggest_recycler_view);
        this.s = healthRecycleView;
        healthRecycleView.a(false);
        this.s.setLayoutManager(new HealthLinearLayoutManager(this.i));
        SearchSuggestAdapter searchSuggestAdapter = new SearchSuggestAdapter(this.i, new ArrayList());
        this.t = searchSuggestAdapter;
        this.s.setAdapter(searchSuggestAdapter);
    }

    public void d(String str) {
        LogUtil.d("GlobalSearchActivity", "search text: ", str);
        e();
        h();
        if (TextUtils.isEmpty(str) || !str.equals(this.r.getQuery().toString())) {
            this.g = false;
            this.r.setQuery(str, false);
        }
        if (TextUtils.isEmpty(c(str))) {
            ReleaseLogUtil.a("GlobalSearchActivity", "doSearch validQuery is null");
            return;
        }
        e(str);
        otj.b(BaseApplication.e(), str);
        c();
        v();
    }

    private void s() {
        r();
        o();
        m();
    }

    private void r() {
        a(this.i, "GlobalSearchPageConstructor.json");
        rxy<otf> rxyVar = this.k;
        if (rxyVar == null) {
            return;
        }
        otf b2 = rxyVar.b();
        this.l = b2.b();
        this.q = b2.d();
    }

    private void a(Context context, String str) {
        InputStream inputStream = null;
        AssetManager assets = context != null ? context.getAssets() : null;
        if (assets == null) {
            return;
        }
        try {
            try {
                inputStream = assets.open(str);
                this.k = (rxy) ixu.e(inputStream, new TypeToken<rxy<otf>>() { // from class: com.huawei.ui.homehealth.search.GlobalSearchActivity.4
                });
            } catch (IOException e) {
                LogUtil.e("GlobalSearchActivity", "search template assets load failed: ", LogAnonymous.b((Throwable) e));
            }
        } finally {
            IoUtils.e(inputStream);
        }
    }

    private void o() {
        SearchSubTabConfig searchSubTabConfig = this.l;
        if (searchSubTabConfig == null) {
            return;
        }
        KnitFragment newInstance = KnitFragment.newInstance(kps.e(searchSubTabConfig), new BasePageResTrigger(this, 0, null));
        this.j = newInstance;
        newInstance.setIsAllowResumeRefresh(false);
    }

    private void m() {
        if (this.q == null) {
            return;
        }
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("SEARCH_RESULT_CONFIG", this.q);
        searchResultFragment.setArguments(bundle);
        this.m = searchResultFragment;
    }

    public void c() {
        KnitFragment knitFragment = this.j;
        if (knitFragment != null) {
            knitFragment.refreshAllSections();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        HealthRecycleView healthRecycleView = this.s;
        if (healthRecycleView != null && this.e && healthRecycleView.getVisibility() == 8) {
            this.s.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.e = false;
        HealthRecycleView healthRecycleView = this.s;
        if (healthRecycleView == null || healthRecycleView.getVisibility() != 0) {
            return;
        }
        this.s.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        HealthRecycleView healthRecycleView = this.s;
        if (healthRecycleView != null && healthRecycleView.getVisibility() == 0) {
            h();
        } else if (!this.f9585a) {
            u();
        } else {
            super.onBackPressed();
        }
    }

    public SearchResultFragment a() {
        return this.m;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        i();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!this.h) {
            e();
        } else {
            this.h = false;
        }
        String str = this.o;
        if (str != null) {
            d(str);
            this.o = null;
        }
        this.n = null;
    }

    private void e() {
        HealthSearchView healthSearchView = this.r;
        if (healthSearchView == null || healthSearchView.getParent() == null) {
            return;
        }
        LogUtil.d("GlobalSearchActivity", "clearSearchViewFocus ");
        try {
            this.r.clearFocus();
        } catch (IllegalArgumentException e) {
            LogUtil.e("GlobalSearchActivity", "clearSearchViewFocus exception", LogAnonymous.b((Throwable) e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String[] split = str.split("[^\\w\\-+\\u4e00-\\u9fa5]+");
        if (split.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            sb.append(str2 + " ");
        }
        return sb.toString().trim();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.j = null;
        this.m = null;
    }

    static class d extends UiCallback<List<String>> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<GlobalSearchActivity> f9587a;

        d(GlobalSearchActivity globalSearchActivity) {
            this.f9587a = new WeakReference<>(globalSearchActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.a("GlobalSearchActivity", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final List<String> list) {
            final GlobalSearchActivity globalSearchActivity = this.f9587a.get();
            if (globalSearchActivity == null || globalSearchActivity.isFinishing()) {
                ReleaseLogUtil.a("GlobalSearchActivity", "SearchSuggestUiCallback onSuccess mWeakReference is null");
                return;
            }
            LogUtil.d("GlobalSearchActivity", "search suggest request result: ", list);
            if (globalSearchActivity.e && TextUtils.equals(globalSearchActivity.p, globalSearchActivity.r.getQuery())) {
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.search.GlobalSearchActivity.d.1
                    @Override // java.lang.Runnable
                    public void run() {
                        globalSearchActivity.t.b(globalSearchActivity.p);
                        globalSearchActivity.t.c(list);
                        if (globalSearchActivity.e && !koq.b(list)) {
                            fbh.d(BaseApplication.e(), list.size());
                        }
                        globalSearchActivity.x();
                        globalSearchActivity.c(4);
                    }
                });
            }
        }
    }

    static class b implements SearchView.OnQueryTextListener {
        private final WeakReference<GlobalSearchActivity> b;

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        b(GlobalSearchActivity globalSearchActivity) {
            this.b = new WeakReference<>(globalSearchActivity);
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            GlobalSearchActivity globalSearchActivity = this.b.get();
            ReleaseLogUtil.b("GlobalSearchActivity", "onQueryTextChange newText=", str);
            if (globalSearchActivity != null && !globalSearchActivity.isFinishing()) {
                if (str != null && str.equals(globalSearchActivity.n)) {
                    return false;
                }
                if (!globalSearchActivity.g) {
                    globalSearchActivity.g = true;
                    return false;
                }
                String c = GlobalSearchActivity.c(str);
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(c)) {
                    globalSearchActivity.t.d();
                    globalSearchActivity.h();
                    return false;
                }
                if (c.equals(globalSearchActivity.p)) {
                    return false;
                }
                globalSearchActivity.p = c;
                fbh.a(BaseApplication.e(), globalSearchActivity.p);
                globalSearchActivity.e = true;
                eiw.c(globalSearchActivity.p, new d(globalSearchActivity));
                return true;
            }
            ReleaseLogUtil.a("GlobalSearchActivity", "GlobalSearchQueryListener onQueryTextChange GlobalSearchActivity is null");
            return false;
        }
    }
}
