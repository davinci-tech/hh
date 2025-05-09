package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MultiGridsTemplate;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.dataprovider.HotListProvider;
import com.huawei.ui.main.R$string;
import defpackage.eil;
import defpackage.fbh;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrv;
import defpackage.nsn;
import defpackage.otb;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class HotListProvider extends BaseKnitDataProvider<List<SingleGridContent>> {

    /* renamed from: a, reason: collision with root package name */
    private long f9605a;
    private Set<String> b = new HashSet();
    private ResourceBriefInfo c;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<SingleGridContent> list) {
        b(context, hashMap, list);
    }

    private void b(Context context, HashMap<String, Object> hashMap, List<SingleGridContent> list) {
        if (list == null) {
            return;
        }
        this.f9605a = System.currentTimeMillis();
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R$string.IDS_global_search_hot_list));
        hashMap.put("ITEMS", list);
        int i = -1;
        for (SingleGridContent singleGridContent : list) {
            if (!this.b.contains(singleGridContent.getTheme())) {
                this.b.add(singleGridContent.getTheme());
                fbh.b(context, singleGridContent);
                i++;
                a(singleGridContent, 1, i);
            }
        }
        b(context, (Map<String, Object>) hashMap, (Object) list);
    }

    private void b(Context context, Map<String, Object> map, Object obj) {
        if (koq.e(obj, SingleGridContent.class)) {
            map.put("CLICK_EVENT_LISTENER", new AnonymousClass5((List) obj, context));
        }
    }

    /* renamed from: com.huawei.ui.homehealth.search.dataprovider.HotListProvider$5, reason: invalid class name */
    public class AnonymousClass5 implements OnClickSectionListener {
        final /* synthetic */ List c;
        final /* synthetic */ Context d;

        public static /* synthetic */ void a(int i, Object obj) {
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

        AnonymousClass5(List list, Context context) {
            this.c = list;
            this.d = context;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
            if (nsn.o()) {
                LogUtil.a("HotListProvider", "click too fast");
                return;
            }
            if (koq.b(this.c, i)) {
                return;
            }
            if (!LoginInit.getInstance(this.d).getIsLogined()) {
                LoginInit.getInstance(this.d).browsingToLogin(new IBaseResponseCallback() { // from class: osz
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj) {
                        HotListProvider.AnonymousClass5.a(i2, obj);
                    }
                }, "");
                return;
            }
            SingleGridContent singleGridContent = (SingleGridContent) this.c.get(i);
            if (singleGridContent == null) {
                return;
            }
            fbh.c(this.d, singleGridContent);
            HotListProvider.this.a(singleGridContent, 2, i);
            String linkValue = singleGridContent.getLinkValue();
            LogUtil.a("HotListProvider", "open link: ", linkValue);
            MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi != null) {
                marketRouterApi.router(HotListProvider.this.d(linkValue, singleGridContent, i + 1));
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, final SectionBean sectionBean) {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(4038);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.HotListProvider.4
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                LogUtil.a("HotListProvider", "requestMarketResource onSuccess");
                otb.d(sectionBean, map);
                Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                otb.c(sectionBean, filterMarketingRules);
                if (filterMarketingRules.get(4038) != null) {
                    List c = HotListProvider.this.c(filterMarketingRules.get(4038));
                    sectionBean.e(koq.b(c) ? null : c);
                } else {
                    LogUtil.h("HotListProvider", "resultMap does not contain search default page resource id");
                    sectionBean.e((Object) null);
                }
            }
        });
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.HotListProvider.2
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                LogUtil.b("HotListProvider", "requestMarketResource onFailure");
                sectionBean.e((Object) null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SingleGridContent> c(ResourceResultInfo resourceResultInfo) {
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
            if (currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime() && resourceBriefInfo.getContentType() == 44) {
                String content = resourceBriefInfo.getContent().getContent();
                LogUtil.a("HotListProvider", "hot list content: ", content);
                if (TextUtils.isEmpty(content)) {
                    continue;
                } else {
                    this.c = resourceBriefInfo;
                    MultiGridsTemplate multiGridsTemplate = (MultiGridsTemplate) nrv.b(content, MultiGridsTemplate.class);
                    if (multiGridsTemplate != null) {
                        List<SingleGridContent> gridContents = multiGridsTemplate.getGridContents();
                        if (koq.c(gridContents)) {
                            Iterator<SingleGridContent> it = gridContents.iterator();
                            while (it.hasNext()) {
                                eil.c(it.next(), resourceBriefInfo);
                            }
                        }
                        return gridContents;
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SingleGridContent singleGridContent, int i, int i2) {
        if (this.c == null || singleGridContent == null) {
            LogUtil.a("HotListProvider", "setMarketingBiEvent mResourceBriefInfo or content is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourcePositionId", 4038);
        hashMap.put("resourceId", this.c.getResourceId());
        hashMap.put("resourceName", this.c.getResourceName());
        hashMap.put("itemCardName", singleGridContent.getTheme());
        hashMap.put("itemId", singleGridContent.getDynamicDataId());
        hashMap.put("smartRecommend", Boolean.valueOf(this.c.getSmartRecommend()));
        hashMap.put("algId", "");
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        if (i == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put("durationTime", Long.valueOf(currentTimeMillis - this.f9605a));
            this.f9605a = currentTimeMillis;
        }
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(String str, SingleGridContent singleGridContent, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        sb.append(4038);
        String sb2 = sb.toString();
        if (this.c == null) {
            return sb2;
        }
        return sb2 + "&resourceName=" + this.c.getResourceName() + "&resourceId=" + this.c.getResourceId() + "&pullOrder=" + i + "&itemId=" + singleGridContent.getDynamicDataId();
    }
}
