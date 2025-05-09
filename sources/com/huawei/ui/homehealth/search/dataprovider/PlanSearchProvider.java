package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
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
import com.huawei.ui.homehealth.search.dataprovider.PlanSearchProvider;
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
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class PlanSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        c(context, hashMap, list);
    }

    private void c(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        boolean a2 = otb.a(this);
        hashMap.put("TITLE", context.getString(R.string._2130848489_res_0x7f022ae9));
        if (a2) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, 201);
            hashMap.put("ITEM_LIMIT", 3);
        }
        c(context, hashMap, list, a2);
        b(context, hashMap, list, a2);
    }

    private void c(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        HashSet hashSet = new HashSet();
        for (GlobalSearchContent globalSearchContent : list) {
            if (globalSearchContent != null) {
                arrayList.add(100);
                arrayList2.add(globalSearchContent.getName());
                arrayList3.add(globalSearchContent.getDescription());
                arrayList4.add(globalSearchContent.getIconUrl());
                arrayList5.add(ContextCompat.getDrawable(context, R.drawable._2131431382_res_0x7f0b0fd6));
                arrayList6.add(globalSearchContent.getMemberShip() == 1 ? context.getString(R.string._2130847510_res_0x7f022716) : "");
                otb.b(globalSearchContent, hashSet);
            }
        }
        hashMap.put("ITEM_VIEW_TYPE", arrayList);
        hashMap.put("ITEM_TITLE", arrayList2);
        hashMap.put("ITEM_DESCRIPTION", arrayList3);
        hashMap.put("ITEM_IMAGE", arrayList4);
        hashMap.put("ITEM_STATUS_BACKGROUND", arrayList5);
        hashMap.put("ITEM_STATUS", arrayList6);
        hashMap.put("ITEM_BI_EVENT_MAP", otb.b(300, z ? 200 : 201, GlobalSearchActivity.d(), arrayList2, arrayList2));
        hashMap.put("BI_OBSERVER_LABEL", fbh.d(z ? 200 : 201));
        hashMap.put("HIGHLIGHTED_TEXT", otb.b(hashSet));
    }

    /* renamed from: com.huawei.ui.homehealth.search.dataprovider.PlanSearchProvider$4, reason: invalid class name */
    public class AnonymousClass4 implements OnClickSectionListener {
        final /* synthetic */ Context b;
        final /* synthetic */ boolean c;
        final /* synthetic */ List e;

        public static /* synthetic */ void b(int i, Object obj) {
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

        AnonymousClass4(List list, Context context, boolean z) {
            this.e = list;
            this.b = context;
            this.c = z;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
            MarketRouterApi marketRouterApi;
            if (nsn.o() || koq.b(this.e, i)) {
                return;
            }
            if (!LoginInit.getInstance(this.b).getIsLogined()) {
                LoginInit.getInstance(this.b).browsingToLogin(new IBaseResponseCallback() { // from class: osy
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        PlanSearchProvider.AnonymousClass4.b(i2, obj);
                    }
                }, "");
                return;
            }
            GlobalSearchContent globalSearchContent = (GlobalSearchContent) this.e.get(i);
            if (globalSearchContent == null) {
                return;
            }
            String targetPage = globalSearchContent.getTargetPage();
            if (TextUtils.isEmpty(targetPage) || (marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)) == null) {
                return;
            }
            fbh.b(this.b, GlobalSearchActivity.d(), 300, globalSearchContent.getName(), globalSearchContent.getName(), this.c);
            marketRouterApi.router(targetPage);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b(Context context, Map<String, Object> map, List<GlobalSearchContent> list, boolean z) {
        map.put("CLICK_EVENT_LISTENER", new AnonymousClass4(list, context, z));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.search.dataprovider.PlanSearchProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    PlanSearchProvider.this.e(context, sectionBean);
                }
            });
        } else if (otb.a(this)) {
            otb.c(context, sectionBean, 300);
        } else {
            eiw.c(GlobalSearchActivity.d(), "function", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.PlanSearchProvider.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("PlanSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, 300);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    for (GlobalSearchResult globalSearchResult : list) {
                        if (globalSearchResult != null && "function".equals(globalSearchResult.getCategoryId()) && !koq.b(globalSearchResult.getContent())) {
                            List<GlobalSearchContent> c = otb.c(globalSearchResult.getContent(), false);
                            SectionBean sectionBean2 = sectionBean;
                            if (koq.b(c)) {
                                c = null;
                            }
                            sectionBean2.e(c);
                            return;
                        }
                    }
                    LogUtil.a("PlanSearchProvider", "no plans found, show functions in the overall result");
                    otb.c(context, sectionBean, 300);
                }
            });
        }
    }
}
