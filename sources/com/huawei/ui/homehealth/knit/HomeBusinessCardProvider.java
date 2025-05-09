package com.huawei.ui.homehealth.knit;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.knit.HomeBusinessCardProvider;
import defpackage.koq;
import defpackage.nrv;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class HomeBusinessCardProvider extends BaseKnitDataProvider {
    private SectionBean b;
    private ColumnLinearLayout c;
    private Context d;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("HomeBusinessCardProvider", "loadDefaultData");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("HomeBusinessCardProvider", "loadData");
        this.d = context;
        this.b = sectionBean;
        e();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("HomeBusinessCardProvider", "parseParams");
        ColumnLinearLayout columnLinearLayout = this.c;
        if (columnLinearLayout == null) {
            LogUtil.a("HomeBusinessCardProvider", "parsePcolumnLinearLayout is null");
        } else {
            hashMap.put("BUSINESS_HOME", columnLinearLayout);
        }
    }

    public void e() {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(4043);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: ond
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                HomeBusinessCardProvider.this.d(marketingApi, (Map) obj);
            }
        });
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: onc
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LogUtil.b("HomeBusinessCardProvider", "requestMarketResource onFailure");
            }
        });
    }

    public /* synthetic */ void d(MarketingApi marketingApi, Map map) {
        if (map == null || map.isEmpty()) {
            LogUtil.b("HomeBusinessCardProvider", "marketingResponse is invalid");
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules == null || filterMarketingRules.isEmpty()) {
            LogUtil.b("HomeBusinessCardProvider", "filterResultInfoMap is invalid");
        } else if (filterMarketingRules.get(4043) != null) {
            e(filterMarketingRules.get(4043));
        }
    }

    public void e(ResourceResultInfo resourceResultInfo) {
        boolean z = true;
        for (ResourceBriefInfo resourceBriefInfo : resourceResultInfo.getResources()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime() && resourceBriefInfo.getContentType() == 47) {
                String content = resourceBriefInfo.getContent().getContent();
                if (!TextUtils.isEmpty(content)) {
                    GridTemplate gridTemplate = (GridTemplate) nrv.b(content, GridTemplate.class);
                    if (gridTemplate == null || koq.b(gridTemplate.getGridContents())) {
                        z = false;
                    } else {
                        ColumnLinearLayout columnLinearLayout = new ColumnLinearLayout(BaseApplication.e());
                        this.c = columnLinearLayout;
                        columnLinearLayout.e(4043, resourceBriefInfo, gridTemplate);
                        this.b.e(new Object());
                    }
                }
            }
        }
        if (z) {
            return;
        }
        this.b.e((Object) null);
    }
}
