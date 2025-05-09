package com.huawei.health.knit.section.listener;

import android.content.Context;
import android.view.View;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentManager;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.knit.section.model.MarketingIdInfo;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dpf;
import defpackage.ead;
import defpackage.koq;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class MemberCenterPageResTrigger extends BasePageResTrigger {
    private static final String TAG = "MemberCenterPageResTrigger";

    public MemberCenterPageResTrigger(Context context, int i, MarketingIdInfo marketingIdInfo) {
        super(context, i, marketingIdInfo);
    }

    @Override // com.huawei.health.knit.section.listener.BasePageResTrigger
    protected void loadMarketingTwo(final Consumer<List<SectionBean>> consumer, FragmentManager fragmentManager) {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.mResPosId));
        ArrayList<Integer> integerArrayList = this.mExtra != null ? this.mExtra.getIntegerArrayList(BasePageResTrigger.KEY_EXTRA_RES_POS_ID_LIST) : null;
        final boolean z = this.mExtra != null && this.mExtra.getBoolean(BasePageResTrigger.KET_EXTRA_IS_SET_BI_ON_VISIBLE, false);
        if (!koq.b(integerArrayList)) {
            arrayList.addAll(integerArrayList);
        }
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(arrayList);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.health.knit.section.listener.MemberCenterPageResTrigger.3
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                if (map == null) {
                    LogUtil.b(MemberCenterPageResTrigger.TAG, "no section map for " + arrayList);
                    consumer.accept(null);
                    return;
                }
                Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                if (filterMarketingRules == null) {
                    LogUtil.b(MemberCenterPageResTrigger.TAG, "no section map for " + arrayList, ", after ruling");
                    consumer.accept(null);
                    return;
                }
                ObserverManagerUtil.c("NEW_SOCIAL_SEARCH_TEMPLATE", dpf.b(marketingApi.filterMarketingRules(filterMarketingRules).get(4001)));
                ArrayList arrayList2 = new ArrayList();
                MemberCenterPageResTrigger.this.mMarketingViewList = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    int intValue = ((Integer) it.next()).intValue();
                    ResourceResultInfo resourceResultInfo2 = filterMarketingRules.get(Integer.valueOf(intValue));
                    if (resourceResultInfo2 == null) {
                        LogUtil.b(MemberCenterPageResTrigger.TAG, "no resourceResultInfo for " + intValue);
                    } else {
                        List<ResourceBriefInfo> resources = resourceResultInfo2.getResources();
                        if (koq.b(resources)) {
                            LogUtil.b(MemberCenterPageResTrigger.TAG, "no briefInfoList for " + intValue);
                        } else {
                            Context context = MemberCenterPageResTrigger.this.mContextRef.get();
                            if (context == null) {
                                LogUtil.b(MemberCenterPageResTrigger.TAG, "loadMarketingTwo failed, cause context is null!");
                                return;
                            }
                            List<View> marketingViewList = marketingApi.getMarketingViewList(context, filterMarketingRules);
                            MemberCenterPageResTrigger.this.mMarketingViewList.addAll(marketingViewList);
                            List<SectionBean> a2 = ead.a(resources, marketingViewList, z, MemberCenterPageResTrigger.this.getPageType(), MemberCenterPageResTrigger.this.mResPosId);
                            LogUtil.a(MemberCenterPageResTrigger.TAG, "request successfully sectionBeans size = ", Integer.valueOf(a2.size()));
                            arrayList2.addAll(a2);
                        }
                    }
                }
                LogUtil.a(MemberCenterPageResTrigger.TAG, "bean list total size: ", Integer.valueOf(arrayList2.size()));
                koq.b(arrayList2);
                consumer.accept(arrayList2);
            }
        });
        if (this.mFailureListener != null) {
            resourceResultInfo.addOnFailureListener(this.mFailureListener);
        }
    }
}
