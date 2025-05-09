package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.dataprovider.FunctionSearchProvider;
import defpackage.eaw;
import defpackage.eiw;
import defpackage.fbh;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nsn;
import defpackage.otb;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class FunctionSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        e(context, hashMap, list);
    }

    private void e(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        boolean a2 = otb.a(this);
        hashMap.put("TITLE", context.getString(R.string._2130849781_res_0x7f022ff5));
        if (a2) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, 206);
            hashMap.put("ITEM_LIMIT", 3);
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Iterator<GlobalSearchContent> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            GlobalSearchContent next = it.next();
            if (next != null) {
                eaw eawVar = new eaw(next.getName(), next.getIconUrl());
                eawVar.c(next.getMemberShip() == 1 ? "VIP" : "");
                eawVar.d(otb.a(206, a2 ? 200 : 206, GlobalSearchActivity.d(), next.getName(), next.getName()));
                arrayList.add(eawVar);
                otb.b(next, hashSet);
            }
        }
        hashMap.put("SECTION21_9LIST_02BEAN", arrayList);
        hashMap.put("BI_OBSERVER_LABEL", fbh.d(a2 ? 200 : 206));
        hashMap.put("HIGHLIGHTED_TEXT", otb.b(hashSet));
        c(context, hashMap, list, a2);
    }

    /* renamed from: com.huawei.ui.homehealth.search.dataprovider.FunctionSearchProvider$2, reason: invalid class name */
    public class AnonymousClass2 implements OnClickSectionListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f9602a;
        final /* synthetic */ List c;
        final /* synthetic */ Context e;

        public static /* synthetic */ void c(int i, Object obj) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        AnonymousClass2(List list, Context context, boolean z) {
            this.c = list;
            this.e = context;
            this.f9602a = z;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
            MarketRouterApi marketRouterApi;
            if (nsn.o() || koq.b(this.c, i)) {
                return;
            }
            if (!LoginInit.getInstance(this.e).getIsLogined()) {
                LoginInit.getInstance(this.e).browsingToLogin(new IBaseResponseCallback() { // from class: osr
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        FunctionSearchProvider.AnonymousClass2.c(i2, obj);
                    }
                }, "");
                return;
            }
            GlobalSearchContent globalSearchContent = (GlobalSearchContent) this.c.get(i);
            if (globalSearchContent == null) {
                return;
            }
            String targetPage = globalSearchContent.getTargetPage();
            if (TextUtils.isEmpty(targetPage) || (marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)) == null) {
                return;
            }
            fbh.b(this.e, GlobalSearchActivity.d(), 206, globalSearchContent.getName(), globalSearchContent.getName(), this.f9602a);
            marketRouterApi.router(otb.d(targetPage));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void c(Context context, Map<String, Object> map, List<GlobalSearchContent> list, boolean z) {
        map.put("CLICK_EVENT_LISTENER", new AnonymousClass2(list, context, z));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.search.dataprovider.FunctionSearchProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSearchProvider.this.e(context, sectionBean);
                }
            });
        } else if (otb.a(this)) {
            otb.c(context, sectionBean, 206);
        } else {
            eiw.c(GlobalSearchActivity.d(), "function", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.FunctionSearchProvider.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("FunctionSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, 206);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    for (GlobalSearchResult globalSearchResult : list) {
                        if (globalSearchResult != null && "function".equals(globalSearchResult.getCategoryId()) && !koq.b(globalSearchResult.getContent())) {
                            List<GlobalSearchContent> c = otb.c(globalSearchResult.getContent(), true);
                            SectionBean sectionBean2 = sectionBean;
                            if (koq.b(c)) {
                                c = null;
                            }
                            sectionBean2.e(c);
                            return;
                        }
                    }
                    LogUtil.a("FunctionSearchProvider", "no functions found, show functions in the overall result");
                    otb.c(context, sectionBean, 206);
                }
            });
        }
    }
}
