package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.listener.IViewCreatedCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.stories.fitness.activity.coresleep.FavoritesProvider;
import defpackage.bzs;
import defpackage.cdy;
import defpackage.koq;
import defpackage.kxv;
import defpackage.pfe;
import defpackage.ppl;
import defpackage.pqi;
import defpackage.qrp;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class FavoritesProvider extends BaseKnitDataProvider {
    private int b;
    private WeakReference<Context> c;
    private SectionBean i;
    private String e = "";
    private List<Integer> g = Collections.synchronizedList(new ArrayList(1));

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, Object> f9773a = new HashMap<>();
    private Object d = new Object();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        this.i = sectionBean;
        d();
        this.f9773a.clear();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void c(Context context, SectionBean sectionBean) {
        this.c = new WeakReference<>(context);
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.FavoritesProvider.1
            @Override // java.lang.Runnable
            public void run() {
                FavoritesProvider.this.b();
            }
        }, 1000L);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("FavoritesProvider", "resume");
        b();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("FavoritesProvider", "parseParams");
        hashMap.putAll(this.f9773a);
        LogUtil.a("FavoritesProvider", "outParams: " + hashMap.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        pqi.c(1, new e());
    }

    private void d() {
        LogUtil.a("FavoritesProvider", "configureService");
        if (Utils.o() && qrp.e("FavoritesProvider", e()) && !bzs.e().switchToMarketingTwo()) {
            LogUtil.a("FavoritesProvider", "isOversea");
            this.f9773a.put("CONFIG_CALL_BACK", new IViewCreatedCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.FavoritesProvider.5
                @Override // com.huawei.health.knit.section.listener.IViewCreatedCallback
                public void onLayoutUpdate(final LinearLayout linearLayout) {
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.FavoritesProvider.5.5
                            @Override // java.lang.Runnable
                            public void run() {
                                pfe.doh_(1, linearLayout, null);
                            }
                        });
                    } else {
                        pfe.doh_(1, linearLayout, null);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Object obj) {
        String str;
        LogUtil.a("FavoritesProvider", "setSleepFavoritesCard, obj: ", obj);
        ppl pplVar = obj instanceof ppl ? (ppl) obj : null;
        if (pplVar != null) {
            LogUtil.a("FavoritesProvider", "model: ", pplVar.toString());
            str = kxv.a(pplVar.toString());
        } else {
            str = "";
        }
        String str2 = this.e;
        if (str2 != null && str2.equals(str) && !this.f9773a.isEmpty()) {
            LogUtil.a("FavoritesProvider", "setSleepFavoritesCard same content");
            return false;
        }
        this.e = str;
        if (Utils.o()) {
            CopyOnWriteArrayList<cdy> a2 = pqi.a(pplVar);
            LogUtil.a("FavoritesProvider", "isOversea favorites list size ", Integer.valueOf(a2.size()));
            a(a2);
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.h("FavoritesProvider", "setSleepFavoritesCard marketingApi is null");
            return false;
        }
        Map<Integer, ResourceResultInfo> b = pqi.b(pplVar);
        if (pplVar == null || b.isEmpty()) {
            this.g.add(Integer.valueOf(this.b));
        } else {
            this.b = pplVar.c();
            this.g.clear();
        }
        this.f9773a.put("FAVORITES_CALL_BACK", new AnonymousClass2(marketingApi, b));
        return true;
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.coresleep.FavoritesProvider$2, reason: invalid class name */
    public class AnonymousClass2 implements IViewCreatedCallback {
        final /* synthetic */ MarketingApi d;
        final /* synthetic */ Map e;

        AnonymousClass2(MarketingApi marketingApi, Map map) {
            this.d = marketingApi;
            this.e = map;
        }

        @Override // com.huawei.health.knit.section.listener.IViewCreatedCallback
        public void onLayoutUpdate(final LinearLayout linearLayout) {
            LogUtil.a("FavoritesProvider", "onLayoutUpdate");
            if (Looper.myLooper() != Looper.getMainLooper()) {
                final MarketingApi marketingApi = this.d;
                final Map map = this.e;
                HandlerExecutor.a(new Runnable() { // from class: plg
                    @Override // java.lang.Runnable
                    public final void run() {
                        FavoritesProvider.AnonymousClass2.this.dqI_(linearLayout, marketingApi, map);
                    }
                });
            } else {
                FavoritesProvider favoritesProvider = FavoritesProvider.this;
                favoritesProvider.dqH_(linearLayout, this.d.getMarketingViewList(favoritesProvider.e(), this.e), FavoritesProvider.this.g);
            }
        }

        public /* synthetic */ void dqI_(LinearLayout linearLayout, MarketingApi marketingApi, Map map) {
            FavoritesProvider favoritesProvider = FavoritesProvider.this;
            favoritesProvider.dqH_(linearLayout, marketingApi.getMarketingViewList(favoritesProvider.e(), map), FavoritesProvider.this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dqH_(ViewGroup viewGroup, List<View> list, List<Integer> list2) {
        if (viewGroup == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ColumnLinearLayout) {
                hashMap.put(Integer.valueOf(((ColumnLinearLayout) childAt).getResourceBriefPriority()), childAt);
            }
        }
        viewGroup.removeAllViews();
        if (!koq.b(list)) {
            for (View view : list) {
                if (view instanceof ColumnLinearLayout) {
                    hashMap.put(Integer.valueOf(((ColumnLinearLayout) view).getResourceBriefPriority()), view);
                }
            }
        }
        if (!koq.b(list2)) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (hashMap.containsKey(list2.get(i2))) {
                    hashMap.remove(list2.get(i2));
                }
            }
        }
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList, new Comparator() { // from class: pld
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return FavoritesProvider.a((Integer) obj, (Integer) obj2);
            }
        });
        LogUtil.a("FavoritesProvider", "layoutMap: " + hashMap.toString());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            LogUtil.a("FavoritesProvider", "add view");
            viewGroup.addView((View) hashMap.get(Integer.valueOf(intValue)));
        }
    }

    public static /* synthetic */ int a(Integer num, Integer num2) {
        return num2.intValue() - num.intValue();
    }

    private void a(final CopyOnWriteArrayList<cdy> copyOnWriteArrayList) {
        LogUtil.a("FavoritesProvider", "setSleepFavoritesCard enter");
        this.f9773a.put("FAVORITES_CALL_BACK", new IViewCreatedCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.FavoritesProvider.3
            @Override // com.huawei.health.knit.section.listener.IViewCreatedCallback
            public void onLayoutUpdate(final LinearLayout linearLayout) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.FavoritesProvider.3.4
                        @Override // java.lang.Runnable
                        public void run() {
                            pfe.dos_(1, linearLayout, copyOnWriteArrayList);
                        }
                    });
                } else {
                    pfe.dos_(1, linearLayout, copyOnWriteArrayList);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context e() {
        WeakReference<Context> weakReference = this.c;
        if (weakReference == null) {
            return BaseApplication.e();
        }
        return weakReference.get();
    }

    static class e implements CommonUiBaseResponse {
        private final WeakReference<FavoritesProvider> d;

        private e(FavoritesProvider favoritesProvider) {
            this.d = new WeakReference<>(favoritesProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            FavoritesProvider favoritesProvider = this.d.get();
            if (favoritesProvider == null) {
                return;
            }
            if (i != 0 || obj == null) {
                LogUtil.b("FavoritesProvider", "response error");
                return;
            }
            LogUtil.a("FavoritesProvider", "FavoritesUiResponse");
            if (favoritesProvider.a(obj)) {
                favoritesProvider.i.e(favoritesProvider.d);
            }
        }
    }
}
