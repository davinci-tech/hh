package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.card.WearHomeBaseCard;
import com.huawei.ui.homewear21.home.holder.WearHomeVipHolder;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class pdw extends WearHomeBaseCard {

    /* renamed from: a, reason: collision with root package name */
    private WearHomeVipHolder f16086a;

    public pdw(Context context, WearHomeActivity wearHomeActivity) {
        this.mContext = context;
        this.mActivity = wearHomeActivity;
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.f16086a = new WearHomeVipHolder(layoutInflater.inflate(R.layout.wear_home_vip_layout, viewGroup, false));
        e();
        return this.f16086a;
    }

    private void e() {
        LogUtil.a("WearHomeVipCard", "initVipMarketing");
        final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
        this.f16086a.dmH_().removeAllViews();
        marketingApi.getResourceResultInfo(4087).addOnSuccessListener(new OnSuccessListener() { // from class: pdx
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                pdw.this.e(marketingApi, (Map) obj);
            }
        });
    }

    /* synthetic */ void e(MarketingApi marketingApi, Map map) {
        List<View> marketingViewList = marketingApi.getMarketingViewList(this.mContext, marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map));
        ReleaseLogUtil.e("R_WearHomeVipCard", "viewList.size = ", Integer.valueOf(marketingViewList.size()));
        if (koq.b(marketingViewList)) {
            this.f16086a.dmH_().setVisibility(8);
            return;
        }
        this.f16086a.dmH_().setVisibility(0);
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            this.f16086a.dmH_().addView(it.next());
        }
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void deviceConnectionChange(int i) {
        LogUtil.a("WearHomeVipCard", "deviceConnectionChange");
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onResume() {
        LogUtil.a("WearHomeVipCard", "onResume");
    }

    @Override // com.huawei.ui.homewear21.home.card.WearHomeBaseCard
    public void onDestroy() {
        LogUtil.a("WearHomeVipCard", "onDestroy");
    }
}
