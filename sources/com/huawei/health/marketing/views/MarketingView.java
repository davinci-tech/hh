package com.huawei.health.marketing.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.views.MarketingView;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class MarketingView extends LinearLayout {
    private IBaseResponseCallback e;

    public MarketingView(Context context) {
        super(context);
    }

    public MarketingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MarketingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void d(int i) {
        LogUtil.a("MarketingView", "init marketing layout");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.b("MarketingView", "marketing api is null return");
        } else {
            marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener() { // from class: ekd
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    MarketingView.this.e(marketingApi, (Map) obj);
                }
            });
        }
    }

    public /* synthetic */ void e(MarketingApi marketingApi, Map map) {
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
        for (View view : marketingViewList) {
            LogUtil.a("MarketingView", "add view success");
            addView(view);
        }
        IBaseResponseCallback iBaseResponseCallback = this.e;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, "");
        }
    }

    public void setInvalidateCallback(IBaseResponseCallback iBaseResponseCallback) {
        this.e = iBaseResponseCallback;
    }
}
