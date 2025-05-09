package com.huawei.ui.main.stories.health.pressure.provider;

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
import com.huawei.ui.main.stories.health.pressure.provider.PressureFavoriteProvider;
import defpackage.efb;
import defpackage.koq;
import defpackage.kxv;
import defpackage.ppl;
import defpackage.pqi;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class PressureFavoriteProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private int f10212a;
    private WeakReference<Context> c;
    private SectionBean h;
    private String d = "";
    private List<Integer> b = Collections.synchronizedList(new ArrayList(1));
    private HashMap<String, Object> e = new HashMap<>();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        if (efb.t()) {
            this.c = new WeakReference<>(context);
            this.h = sectionBean;
            this.e.clear();
            b();
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("PressureFavoriteProvider", "resume");
        if (efb.t()) {
            this.e.clear();
            b();
        }
    }

    private void b() {
        pqi.c(3, new e());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        hashMap.putAll(this.e);
        LogUtil.a("PressureFavoriteProvider", "outParams: " + hashMap.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj) {
        LogUtil.a("PressureFavoriteProvider", "setSleepFavoritesCard");
        ppl pplVar = obj instanceof ppl ? (ppl) obj : null;
        String a2 = pplVar != null ? kxv.a(pplVar.toString()) : "";
        String str = this.d;
        if (str != null && str.equals(a2)) {
            LogUtil.a("PressureFavoriteProvider", "setSleepFavoritesCard same content");
            return;
        }
        this.d = a2;
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.h("PressureFavoriteProvider", "setSleepFavoritesCard marketingApi is null");
            return;
        }
        final Map<Integer, ResourceResultInfo> c = pqi.c(pplVar);
        if (pplVar == null || c.isEmpty()) {
            this.b.add(Integer.valueOf(this.f10212a));
        } else {
            this.f10212a = pplVar.c();
            this.b.clear();
        }
        this.e.put("FAVORITES_CALL_BACK", new IViewCreatedCallback() { // from class: com.huawei.ui.main.stories.health.pressure.provider.PressureFavoriteProvider.3
            @Override // com.huawei.health.knit.section.listener.IViewCreatedCallback
            public void onLayoutUpdate(final LinearLayout linearLayout) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.pressure.provider.PressureFavoriteProvider.3.3
                        @Override // java.lang.Runnable
                        public void run() {
                            PressureFavoriteProvider.this.dFN_(linearLayout, marketingApi.getMarketingViewList(PressureFavoriteProvider.this.a(), c), PressureFavoriteProvider.this.b);
                        }
                    });
                } else {
                    PressureFavoriteProvider pressureFavoriteProvider = PressureFavoriteProvider.this;
                    pressureFavoriteProvider.dFN_(linearLayout, marketingApi.getMarketingViewList(pressureFavoriteProvider.a(), c), PressureFavoriteProvider.this.b);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context a() {
        WeakReference<Context> weakReference = this.c;
        if (weakReference == null) {
            return BaseApplication.e();
        }
        return weakReference.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dFN_(ViewGroup viewGroup, List<View> list, List<Integer> list2) {
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
                hashMap.remove(list2.get(i2));
            }
        }
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList, new Comparator() { // from class: qmm
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return PressureFavoriteProvider.d((Integer) obj, (Integer) obj2);
            }
        });
        LogUtil.a("PressureFavoriteProvider", "layoutMap: " + hashMap.toString());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            LogUtil.a("PressureFavoriteProvider", "add view");
            viewGroup.addView((View) hashMap.get(Integer.valueOf(intValue)));
        }
    }

    public static /* synthetic */ int d(Integer num, Integer num2) {
        return num2.intValue() - num.intValue();
    }

    static class e implements CommonUiBaseResponse {
        private final WeakReference<PressureFavoriteProvider> d;

        private e(PressureFavoriteProvider pressureFavoriteProvider) {
            this.d = new WeakReference<>(pressureFavoriteProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            PressureFavoriteProvider pressureFavoriteProvider = this.d.get();
            if (pressureFavoriteProvider == null) {
                return;
            }
            LogUtil.a("PressureFavoriteProvider", "FavoritesUiResponse");
            pressureFavoriteProvider.e(obj);
            pressureFavoriteProvider.h.e(new Object());
        }
    }
}
