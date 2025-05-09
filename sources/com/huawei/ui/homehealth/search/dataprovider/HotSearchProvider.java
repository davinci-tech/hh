package com.huawei.ui.homehealth.search.dataprovider;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleTextContent;
import com.huawei.health.marketing.datatype.TextLinkTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.dataprovider.HotSearchProvider;
import com.huawei.ui.main.R$string;
import defpackage.fbh;
import defpackage.koq;
import defpackage.nrv;
import defpackage.otb;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class HotSearchProvider extends BaseKnitDataProvider<List<SingleTextContent>> {
    private Set<String> c = new HashSet();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<SingleTextContent> list) {
        b(context, hashMap, list);
    }

    private void b(Context context, HashMap<String, Object> hashMap, List<SingleTextContent> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (SingleTextContent singleTextContent : list) {
            arrayList.add(singleTextContent.getTheme());
            String marketingIcon = singleTextContent.getMarketingIcon();
            if (marketingIcon == null) {
                marketingIcon = "";
            }
            arrayList2.add(marketingIcon);
            if (!this.c.contains(singleTextContent.getTheme())) {
                this.c.add(singleTextContent.getTheme());
                fbh.a(context, singleTextContent);
            }
        }
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R$string.IDS_global_search_hot_search));
        hashMap.put("IS_LOAD_DEFAULT_VIEW", false);
        hashMap.put("ITEM_TITLE", arrayList);
        hashMap.put("RIGHT_ICON_TEXT", arrayList2);
        e(context, hashMap, list);
    }

    /* renamed from: com.huawei.ui.homehealth.search.dataprovider.HotSearchProvider$3, reason: invalid class name */
    public class AnonymousClass3 implements OnClickSectionListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f9608a;
        final /* synthetic */ Context e;

        public static /* synthetic */ void d(int i, Object obj) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        AnonymousClass3(List list, Context context) {
            this.f9608a = list;
            this.e = context;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
            if (koq.b(this.f9608a, i)) {
                return;
            }
            SingleTextContent singleTextContent = (SingleTextContent) this.f9608a.get(i);
            fbh.e(this.e, singleTextContent);
            String linkType = singleTextContent.getLinkType() != null ? singleTextContent.getLinkType() : "search";
            linkType.hashCode();
            if (!linkType.equals("normal")) {
                if (linkType.equals("search")) {
                    Activity activity = BaseApplication.getActivity();
                    if (activity instanceof GlobalSearchActivity) {
                        ((GlobalSearchActivity) activity).d(str);
                        return;
                    }
                    return;
                }
                return;
            }
            if (!LoginInit.getInstance(this.e).getIsLogined()) {
                LoginInit.getInstance(this.e).browsingToLogin(new IBaseResponseCallback() { // from class: osw
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        HotSearchProvider.AnonymousClass3.d(i2, obj);
                    }
                }, "");
                return;
            }
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(singleTextContent.getLinkValue());
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void e(Context context, Map<String, Object> map, List<SingleTextContent> list) {
        map.put("CLICK_EVENT_LISTENER", new AnonymousClass3(list, context));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, final SectionBean sectionBean) {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(9002);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.HotSearchProvider.4
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                LogUtil.a("HotSearchProvider", "requestMarketResource onSuccess");
                otb.d(sectionBean, map);
                Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                otb.c(sectionBean, filterMarketingRules);
                if (filterMarketingRules.get(9002) != null) {
                    List e = HotSearchProvider.this.e(filterMarketingRules.get(9002));
                    Collections.sort(e, new Comparator<SingleTextContent>() { // from class: com.huawei.ui.homehealth.search.dataprovider.HotSearchProvider.4.4
                        @Override // java.util.Comparator
                        /* renamed from: a, reason: merged with bridge method [inline-methods] */
                        public int compare(SingleTextContent singleTextContent, SingleTextContent singleTextContent2) {
                            return singleTextContent.getPriority() - singleTextContent2.getPriority();
                        }
                    });
                    sectionBean.e(koq.b(e) ? null : e);
                    return;
                }
                sectionBean.e((Object) null);
            }
        });
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.HotSearchProvider.1
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                sectionBean.e((Object) null);
                LogUtil.b("HotSearchProvider", "requestMarketResource onFailure");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SingleTextContent> e(ResourceResultInfo resourceResultInfo) {
        TextLinkTemplate textLinkTemplate;
        ArrayList arrayList = new ArrayList();
        if (resourceResultInfo == null) {
            return arrayList;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (koq.b(resources)) {
            return arrayList;
        }
        for (ResourceBriefInfo resourceBriefInfo : resources) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime() && resourceBriefInfo.getContentType() == 35) {
                String content = resourceBriefInfo.getContent().getContent();
                LogUtil.a("HotSearchProvider", "text link content: ", content);
                if (!TextUtils.isEmpty(content) && (textLinkTemplate = (TextLinkTemplate) nrv.b(content, TextLinkTemplate.class)) != null && !koq.b(textLinkTemplate.getGridContents())) {
                    return textLinkTemplate.getGridContents();
                }
            }
        }
        return arrayList;
    }
}
