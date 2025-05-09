package com.huawei.ui.homehealth.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.model.KnitSectionConfig;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.device.activity.adddevice.OneKeyScanActivity;
import com.huawei.ui.homehealth.search.template.SearchResultConfig;
import com.huawei.ui.homehealth.search.template.SearchSubTabConfig;
import com.huawei.ui.homehealth.search.utils.SearchResultPageResTrigger;
import com.huawei.ui.main.R$string;
import defpackage.fbh;
import defpackage.fbo;
import defpackage.ixu;
import defpackage.koq;
import defpackage.kps;
import defpackage.nqx;
import defpackage.nsy;
import defpackage.nyq;
import defpackage.otb;
import defpackage.ote;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class SearchResultFragment extends BaseFragment {
    private static final Map<Integer, String> e = new HashMap<Integer, String>() { // from class: com.huawei.ui.homehealth.search.SearchResultFragment.3
        {
            put(200, BaseApplication.getContext().getResources().getString(R$string.IDS_overall_search));
            put(201, BaseApplication.getContext().getResources().getString(R$string.IDS_configured_page_attribute_course));
            put(Integer.valueOf(com.huawei.hms.kit.awareness.barrier.internal.e.a.C), BaseApplication.getContext().getResources().getString(R$string.IDS_action_lib));
            put(Integer.valueOf(com.huawei.hms.kit.awareness.barrier.internal.e.a.z), BaseApplication.getContext().getResources().getString(R$string.IDS_decompression));
            put(Integer.valueOf(com.huawei.hms.kit.awareness.barrier.internal.e.a.A), BaseApplication.getContext().getResources().getString(R$string.IDS_health_headlines_title));
            put(202, BaseApplication.getContext().getResources().getString(R$string.IDS_social_information));
            put(203, BaseApplication.getContext().getResources().getString(R$string.IDS_device_title_use));
            put(204, BaseApplication.getContext().getResources().getString(com.huawei.health.servicesui.R$string.IDS_take_part_in_activities));
            put(205, BaseApplication.getContext().getResources().getString(R$string.IDS_attribute_merchandise));
            put(206, BaseApplication.getContext().getResources().getString(R$string.IDS_function_search));
            put(Integer.valueOf(com.huawei.hms.kit.awareness.barrier.internal.e.a.w), BaseApplication.getContext().getResources().getString(R$string.IDS_search_material));
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private CountDownLatch f9588a;
    private List<KnitSectionConfig> b;
    private RelativeLayout f;
    private LinearLayout g;
    private SearchResultConfig k;
    private nqx l;
    private View m;
    private View o;
    private HealthViewPager r;
    private HealthSubTabWidget t;
    private Map<Integer, Integer> n = new HashMap();
    private Map<Integer, Fragment> d = new HashMap();
    private Map<Integer, List<KnitSectionConfig>> c = new HashMap();
    private Set<Integer> s = new HashSet();
    private b i = new b(this);
    private boolean h = false;
    private boolean j = false;
    private ote p = new ote(this);

    public static SearchResultFragment d() {
        Activity activity = BaseApplication.getActivity();
        if (activity instanceof GlobalSearchActivity) {
            return ((GlobalSearchActivity) activity).a();
        }
        return null;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.k = (SearchResultConfig) arguments.getParcelable("SEARCH_RESULT_CONFIG");
        }
        LogUtil.a("SearchResultFragment", "onCreate");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("SearchResultFragment", "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_search_result, viewGroup, false);
        this.o = inflate;
        this.t = (HealthSubTabWidget) inflate.findViewById(R.id.search_category_tab);
        this.r = (HealthViewPager) this.o.findViewById(R.id.search_result_vp);
        this.g = (LinearLayout) this.o.findViewById(R.id.search_result_main_layout);
        this.f = (RelativeLayout) this.o.findViewById(R.id.search_result_loading_layout);
        this.m = this.o.findViewById(R.id.search_result_empty_layout);
        this.l = new nqx(getChildFragmentManager(), this.r, this.t);
        this.j = true;
        this.r.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.ui.homehealth.search.SearchResultFragment.2
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (SearchResultFragment.this.h) {
                    return;
                }
                Iterator it = SearchResultFragment.this.n.keySet().iterator();
                while (it.hasNext()) {
                    int intValue = ((Integer) it.next()).intValue();
                    if (((Integer) SearchResultFragment.this.n.get(Integer.valueOf(intValue))).intValue() == i) {
                        fbh.d(com.huawei.haf.application.BaseApplication.e(), GlobalSearchActivity.d(), intValue);
                        return;
                    }
                }
            }
        });
        d(this.k);
        return this.o;
    }

    public void d(SearchResultConfig searchResultConfig) {
        if (searchResultConfig == null) {
            LogUtil.a("SearchResultFragment", "SearchResultConfig is null");
            return;
        }
        List<SearchSubTabConfig> tabConfigList = searchResultConfig.getTabConfigList();
        if (koq.b(tabConfigList)) {
            LogUtil.a("SearchResultFragment", "subTabConfigs is empty");
            return;
        }
        for (SearchSubTabConfig searchSubTabConfig : tabConfigList) {
            if (searchSubTabConfig != null) {
                int pageType = searchSubTabConfig.getPageType();
                String str = e.get(Integer.valueOf(pageType));
                LogUtil.a("SearchResultFragment", "pageType: ", Integer.valueOf(pageType), " str: ", str);
                if (!TextUtils.isEmpty(str)) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("IS_OVERALL", pageType == 200);
                    KnitFragment newInstance = KnitFragment.newInstance(kps.e(searchSubTabConfig), new SearchResultPageResTrigger(getContext(), pageType).setExtra(bundle));
                    if (pageType != 203) {
                        newInstance.setIsAllowResumeRefresh(false);
                    }
                    a(newInstance, pageType, searchSubTabConfig);
                }
            }
        }
        a();
    }

    private void a(final KnitFragment knitFragment, final int i, SearchSubTabConfig searchSubTabConfig) {
        knitFragment.setScrollViewTopBoundaryListener(new HealthScrollView.ScrollChangeToBoundaryListener() { // from class: com.huawei.ui.homehealth.search.SearchResultFragment.4
            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrollToChangeAlpha(float f) {
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrolledToBottom() {
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrolledTop() {
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrollStateChange(int i2) {
                if (knitFragment.getUserVisibleHint()) {
                    if (i2 == 1 || i2 == 2) {
                        ObserverManagerUtil.c(fbh.d(i), "");
                    }
                }
            }
        });
        this.d.put(Integer.valueOf(i), knitFragment);
        this.r.setOffscreenPageLimit(this.d.size() - 1);
        if (i == 200) {
            e(searchSubTabConfig);
        }
    }

    private void e(SearchSubTabConfig searchSubTabConfig) {
        ArrayList<KnitSectionConfig> sectionConfigList = searchSubTabConfig.getSectionConfigList();
        this.b = sectionConfigList;
        if (sectionConfigList == null) {
            LogUtil.b("SearchResultFragment", "mDefaultOverallSectionConfigs is null");
            return;
        }
        for (KnitSectionConfig knitSectionConfig : sectionConfigList) {
            try {
                Integer valueOf = Integer.valueOf(knitSectionConfig.getExtra());
                if (this.c.get(valueOf) == null) {
                    this.c.put(valueOf, new ArrayList());
                }
                this.c.get(valueOf).add(knitSectionConfig);
            } catch (NumberFormatException unused) {
                LogUtil.a("SearchResultFragment", "String to Integer Exception: ", knitSectionConfig.getExtra());
            }
        }
    }

    public void a() {
        LogUtil.a("SearchResultFragment", "refreshChildFragments");
        boolean z = this.h;
        if (z || !this.j) {
            LogUtil.a("SearchResultFragment", "mIsLoading: ", Boolean.valueOf(z), " mIsCreated: ", Boolean.valueOf(this.j));
            return;
        }
        i();
        h();
        this.s.clear();
        this.p.a();
        this.f9588a = new CountDownLatch(ote.b() ? 1 : 2);
        this.p.d(GlobalSearchActivity.d());
        if (!ote.b()) {
            LogUtil.a("SearchResultFragment", "isLessonAggregateSearch is false");
            this.p.e(GlobalSearchActivity.d());
        }
        ThreadPoolManager.d().execute(new c(this.i, this.f9588a));
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<Handler> f9589a;
        private WeakReference<CountDownLatch> d;

        c(Handler handler, CountDownLatch countDownLatch) {
            this.f9589a = new WeakReference<>(handler);
            this.d = new WeakReference<>(countDownLatch);
        }

        @Override // java.lang.Runnable
        public void run() {
            Handler handler = this.f9589a.get();
            CountDownLatch countDownLatch = this.d.get();
            if (handler == null || countDownLatch == null) {
                LogUtil.h("SearchResultFragment", "mHandler or mCountDownLatch = null");
                return;
            }
            try {
                if (!countDownLatch.await(ote.b() ? PreConnectManager.CONNECT_INTERNAL : 20000L, TimeUnit.MILLISECONDS)) {
                    LogUtil.b("SearchResultFragment", "loading search result timeout");
                }
            } catch (InterruptedException e) {
                LogUtil.b("SearchResultFragment", "loadData failed, cause InterruptedException happened: " + e.getCause());
            }
            handler.sendMessage(handler.obtainMessage(102));
        }
    }

    public void c(int i) {
        Integer num = this.n.get(Integer.valueOf(i));
        if (num == null || num.intValue() < 0 || num.intValue() >= this.l.getCount()) {
            return;
        }
        this.r.setCurrentItem(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        c();
        this.n.clear();
        if (koq.b(this.s)) {
            l();
            return;
        }
        j();
        LogUtil.a("SearchResultFragment", "mShowTabSet after parse overall search result: ", this.s);
        if (g()) {
            this.s.remove(200);
        }
        if (LoginInit.getInstance(getContext()).isKidAccount()) {
            this.s.remove(204);
            this.s.remove(202);
        }
        fbh.b(getContext(), GlobalSearchActivity.d(), this.s);
        int[] b2 = b();
        LogUtil.a("SearchResultFragment", "reorderedType: ", Arrays.toString(b2));
        ArrayList<KnitSectionConfig> d = d(b2);
        if (d != null) {
            b(d);
        }
        int length = b2.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i3 = b2[i];
            if (this.s.contains(Integer.valueOf(i3))) {
                String str = e.get(Integer.valueOf(i3));
                Fragment fragment = this.d.get(Integer.valueOf(i3));
                if (str != null && fragment != null) {
                    if (fragment instanceof KnitFragment) {
                        KnitFragment knitFragment = (KnitFragment) fragment;
                        if (!knitFragment.getIsAllowResumeRefresh()) {
                            knitFragment.refreshAllSections();
                        }
                    }
                    if (!EnvironmentInfo.k() || !str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_device_title_use))) {
                        this.l.c(this.t.c(str), fragment, i2 == 0);
                        this.n.put(Integer.valueOf(i3), Integer.valueOf(i2));
                        if (i2 == 0) {
                            LogUtil.a("SearchResultFragment", "first tab send delayed bi message, pageType: ", Integer.valueOf(i3));
                            this.i.postDelayed(new a(i3), 500L);
                        }
                        i2++;
                    }
                }
            }
            i++;
        }
        if (i2 == 0) {
            l();
        } else {
            nsy.cMA_(this.t, i2 <= 1 ? 8 : 0);
        }
    }

    static class a implements Runnable {
        private final int b;

        a(int i) {
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ObserverManagerUtil.c(fbh.d(this.b), "");
        }
    }

    private void j() {
        Integer num;
        Object e2 = this.p.e(200);
        if (koq.e(e2, GlobalSearchResult.class)) {
            for (GlobalSearchResult globalSearchResult : new ArrayList((List) e2)) {
                if (globalSearchResult != null && globalSearchResult.getCategoryId() != null && !koq.b(globalSearchResult.getContent()) && (num = fbo.c.get(globalSearchResult.getCategoryId())) != null) {
                    int intValue = num.intValue();
                    if (intValue == 206) {
                        List<GlobalSearchContent> c2 = otb.c(globalSearchResult.getContent(), true);
                        List<GlobalSearchContent> c3 = otb.c(globalSearchResult.getContent(), false);
                        if (!koq.b(c2)) {
                            this.s.add(206);
                        }
                        if (!koq.b(c3)) {
                            this.s.add(201);
                        }
                    } else if (intValue != 301) {
                        this.s.add(num);
                    }
                }
            }
        }
    }

    private boolean g() {
        return this.s.contains(200) && this.s.size() <= 2 && !d(301);
    }

    private boolean d(int i) {
        Integer num;
        Object e2 = this.p.e(200);
        if (!koq.e(e2, GlobalSearchResult.class)) {
            return false;
        }
        for (GlobalSearchResult globalSearchResult : (List) e2) {
            if (globalSearchResult != null && globalSearchResult.getCategoryId() != null && !koq.b(globalSearchResult.getContent()) && (num = fbo.c.get(globalSearchResult.getCategoryId())) != null && num.intValue() == i) {
                return !koq.b(globalSearchResult.getContent());
            }
        }
        return false;
    }

    private void c() {
        while (this.l.getCount() > 0) {
            this.l.a(r0.getCount() - 1);
        }
    }

    private void b(ArrayList<KnitSectionConfig> arrayList) {
        Map<Integer, Fragment> map = this.d;
        if (map == null || !(map.get(200) instanceof KnitFragment)) {
            LogUtil.b("SearchResultFragment", "mFragmentMap is null or overall page is not knit fragment");
            return;
        }
        KnitFragment knitFragment = (KnitFragment) this.d.get(200);
        String pageData = knitFragment.getPageData();
        if (pageData == null && knitFragment.getArguments() != null) {
            pageData = knitFragment.getArguments().getString(KnitFragment.KEY_PAGE_DATA);
        }
        SearchSubTabConfig searchSubTabConfig = (SearchSubTabConfig) ixu.e(pageData, new TypeToken<SearchSubTabConfig>() { // from class: com.huawei.ui.homehealth.search.SearchResultFragment.5
        });
        ArrayList<KnitSectionConfig> sectionConfigList = searchSubTabConfig != null ? searchSubTabConfig.getSectionConfigList() : null;
        if (sectionConfigList == null || sectionConfigList.toString().equals(arrayList.toString())) {
            LogUtil.a("SearchResultFragment", "original section configs is null or is equal to reordered section configs");
            return;
        }
        LogUtil.a("SearchResultFragment", "section configs changed, original section configs: ", sectionConfigList, " ,reordered section configs: ", arrayList);
        searchSubTabConfig.setSectionConfigList(arrayList);
        String e2 = kps.e(searchSubTabConfig);
        Bundle bundle = new Bundle();
        bundle.putBoolean("IS_OVERALL", true);
        this.d.put(200, KnitFragment.newInstance(e2, new SearchResultPageResTrigger(getContext(), 200).setExtra(bundle)));
    }

    private ArrayList<KnitSectionConfig> d(int[] iArr) {
        if (this.c == null || this.b == null) {
            return null;
        }
        ArrayList<KnitSectionConfig> arrayList = new ArrayList<>();
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            if (this.c.containsKey(Integer.valueOf(i))) {
                arrayList.addAll(this.c.get(Integer.valueOf(i)));
                hashSet.addAll(this.c.get(Integer.valueOf(i)));
            }
        }
        for (KnitSectionConfig knitSectionConfig : this.b) {
            if (!hashSet.contains(knitSectionConfig)) {
                arrayList.add(knitSectionConfig);
                hashSet.add(knitSectionConfig);
            }
        }
        return arrayList;
    }

    private int[] b() {
        int[] a2 = fbo.a();
        LogUtil.a("SearchResultFragment", "isFromHint, should reorder page type");
        String d = GlobalSearchActivity.d();
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (int i : a2) {
            if (d(i, d)) {
                arrayList.add(Integer.valueOf(i));
                hashSet.add(Integer.valueOf(i));
            }
        }
        for (int i2 : a2) {
            if (!hashSet.contains(Integer.valueOf(i2))) {
                arrayList.add(Integer.valueOf(i2));
                hashSet.add(Integer.valueOf(i2));
            }
        }
        return CommonUtil.b(arrayList);
    }

    private boolean d(int i, String str) {
        if (i == 200) {
            return true;
        }
        List<GlobalSearchContent> c2 = this.p.c(i);
        if (i == 206) {
            c2 = otb.c(c2, true);
        }
        LogUtil.a("SearchResultFragment", "isPriorityPageType resultList= ", HiJsonUtil.e(c2));
        LogUtil.a("SearchResultFragment", "isPriorityPageType resultSize= ", Integer.valueOf(c2.size()), " pageType= ", Integer.valueOf(i));
        if (koq.b(c2)) {
            return false;
        }
        int min = Math.min(c2.size(), 3);
        for (int i2 = 0; i2 < min; i2++) {
            GlobalSearchContent globalSearchContent = c2.get(i2);
            if (globalSearchContent != null && TextUtils.equals(str, otb.d(globalSearchContent, i))) {
                LogUtil.a("SearchResultFragment", "isPriorityPageType is true ");
                return true;
            }
        }
        return false;
    }

    public Object b(int i) {
        return this.p.e(i);
    }

    public List<GlobalSearchContent> a(int i) {
        return this.p.c(i);
    }

    public void b(int i, boolean z) {
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.what = z ? 101 : 100;
        obtainMessage.obj = Integer.valueOf(i);
        this.i.sendMessage(obtainMessage);
    }

    public void a(nyq nyqVar) {
        if (nyqVar == null) {
            return;
        }
        Intent intent = new Intent(getContext(), (Class<?>) OneKeyScanActivity.class);
        List<String> s = nyqVar.s();
        if (s instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
        }
        startActivity(intent);
    }

    private void l() {
        LogUtil.a("SearchResultFragment", "showNoDataLayout");
        nsy.cMA_(this.m, 0);
    }

    private void i() {
        LogUtil.a("SearchResultFragment", "hideNoDataLayout");
        nsy.cMA_(this.m, 8);
    }

    private void h() {
        if (this.h) {
            return;
        }
        this.g.setVisibility(8);
        this.f.setVisibility(0);
        this.h = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.h) {
            this.f.setVisibility(8);
            this.g.setVisibility(0);
            this.h = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CountDownLatch countDownLatch = this.f9588a;
        if (countDownLatch == null || !this.h) {
            return;
        }
        countDownLatch.countDown();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("SearchResultFragment", "onDestroy");
        Map<Integer, Fragment> map = this.d;
        if (map != null) {
            for (Map.Entry<Integer, Fragment> entry : map.entrySet()) {
                int intValue = entry.getKey().intValue();
                Fragment value = entry.getValue();
                ObserverManagerUtil.e(fbh.d(intValue));
                if (value instanceof KnitFragment) {
                    ((KnitFragment) value).setScrollViewTopBoundaryListener(null);
                }
            }
            this.d.clear();
        }
        HealthViewPager healthViewPager = this.r;
        if (healthViewPager != null) {
            healthViewPager.clearOnPageChangeListeners();
        }
        b bVar = this.i;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
    }

    static class b extends BaseHandler<SearchResultFragment> {
        b(SearchResultFragment searchResultFragment) {
            super(searchResultFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dgP_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SearchResultFragment searchResultFragment, Message message) {
            if (searchResultFragment == null || message == null) {
                LogUtil.a("SearchResultFragment", "obj or message is null");
                return;
            }
            if (searchResultFragment.getActivity() != null && !searchResultFragment.getActivity().isFinishing() && !searchResultFragment.getActivity().isDestroyed()) {
                switch (message.what) {
                    case 100:
                        searchResultFragment.e();
                        break;
                    case 101:
                        Object obj = message.obj;
                        if (obj instanceof Integer) {
                            searchResultFragment.s.add((Integer) obj);
                            searchResultFragment.e();
                            break;
                        }
                        break;
                    case 102:
                        searchResultFragment.m();
                        searchResultFragment.f();
                        break;
                }
                return;
            }
            LogUtil.a("SearchResultFragment", "activity is destroyed or being finishing");
        }
    }
}
