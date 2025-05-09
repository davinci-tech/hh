package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.HealthDictSearchContent;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import defpackage.ebs;
import defpackage.fbh;
import defpackage.koq;
import defpackage.nsn;
import defpackage.otb;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class HealthDictSearchProvider extends BaseKnitDataProvider<List<ebs>> {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f9603a;
    private String d;
    private String e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<ebs> list) {
        d(context, hashMap, list);
    }

    private void d(Context context, HashMap<String, Object> hashMap, List<ebs> list) {
        hashMap.put("TITLE", this.e);
        hashMap.put("SECTION_HEALTH_DICT_BEAN", list);
        b(context, hashMap);
        String d = GlobalSearchActivity.d();
        String str = this.e;
        hashMap.put("ITEM_BI_EVENT_MAP", otb.a(301, 200, d, str, str));
        hashMap.put("SUBTITLE", b());
    }

    private void b(final Context context, HashMap<String, Object> hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.HealthDictSearchProvider.3
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi == null || TextUtils.isEmpty(HealthDictSearchProvider.this.d)) {
                    return;
                }
                fbh.d(context, GlobalSearchActivity.d(), HealthDictSearchProvider.this.e, HealthDictSearchProvider.this.d);
                marketRouterApi.router(otb.d(HealthDictSearchProvider.this.d));
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        this.e = null;
        this.d = null;
        this.f9603a = null;
        List<GlobalSearchContent> d = otb.d();
        if (koq.b(d) || d.get(0) == null) {
            LogUtil.c("HealthDictSearchProvider", "loadData invalid content: ", d);
            sectionBean.e((Object) null);
            return;
        }
        GlobalSearchContent globalSearchContent = d.get(0);
        this.d = globalSearchContent.getGotourl();
        this.f9603a = globalSearchContent.getDisdepart();
        Map<String, List<ebs>> b = b(globalSearchContent.getTabs());
        for (Map.Entry<String, List<ebs>> entry : b.entrySet()) {
            String key = entry.getKey();
            List<ebs> value = entry.getValue();
            if (!TextUtils.isEmpty(key) && !koq.b(value)) {
                this.e = key;
                sectionBean.e(value);
                return;
            }
        }
        LogUtil.c("HealthDictSearchProvider", "invalid beanMap: ", b);
        sectionBean.e((Object) null);
    }

    private Map<String, List<ebs>> b(List<HealthDictSearchContent> list) {
        HashMap hashMap = new HashMap();
        if (koq.b(list)) {
            return hashMap;
        }
        for (HealthDictSearchContent healthDictSearchContent : list) {
            if (healthDictSearchContent == null || !nsn.c(healthDictSearchContent.getDisease(), healthDictSearchContent.getTab(), healthDictSearchContent.getMdText(), healthDictSearchContent.getEntry())) {
                LogUtil.d("HealthDictSearchProvider", "invalid content: ", healthDictSearchContent);
            } else {
                String disease = healthDictSearchContent.getDisease();
                if (!hashMap.containsKey(disease)) {
                    hashMap.put(disease, new ArrayList());
                }
                ebs ebsVar = new ebs(healthDictSearchContent.getTab(), healthDictSearchContent.getMdText());
                List list2 = (List) hashMap.get(disease);
                if (list2 != null) {
                    list2.add(ebsVar);
                }
            }
        }
        return hashMap;
    }

    private String b() {
        if (koq.b(this.f9603a)) {
            return "";
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_health_dict_department) + ": " + nsn.d(", ", this.f9603a);
    }
}
