package com.huawei.ui.main.stories.health.views.healthdata;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.views.healthdata.MarketingView;
import health.compact.a.Services;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class MarketingView extends LinearLayout {
    public MarketingView(Context context) {
        super(context);
    }

    public MarketingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MarketingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void e(int i) {
        LogUtil.a("MarketingView", "init marketing layout, positionId is ", Integer.valueOf(i));
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.b("MarketingView", "marketing api is null return");
        } else {
            marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener() { // from class: qsz
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    MarketingView.this.d(marketingApi, (Map) obj);
                }
            });
        }
    }

    public /* synthetic */ void d(MarketingApi marketingApi, Map map) {
        removeAllViews();
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules == null) {
            LogUtil.b("MarketingView", "filter result is null return");
            return;
        }
        List<View> marketingViewList = marketingApi.getMarketingViewList(getContext(), filterMarketingRules);
        if (marketingViewList == null) {
            LogUtil.b("MarketingView", "view list is null");
            return;
        }
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            addView(it.next());
        }
    }
}
