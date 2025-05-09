package com.huawei.health.marketing.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.templates.SingleArticleContent;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SingleArticleAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<SingleArticleContent> f2887a;
    private Context b;
    private long c;
    private int d;
    private ResourceBriefInfo e;

    public SingleArticleAdapter(Context context, List<SingleArticleContent> list, int i, ResourceBriefInfo resourceBriefInfo) {
        new ArrayList();
        this.b = context;
        this.f2887a = list;
        this.d = i;
        this.e = resourceBriefInfo;
    }

    public void c(List<SingleArticleContent> list) {
        this.f2887a = list;
        LogUtil.a("SingleArticleAdapter", "mSingleArticleContent size:", Integer.valueOf(list.size()));
        this.c = System.currentTimeMillis();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: apN_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_single_article, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if (koq.b(this.f2887a, i)) {
            LogUtil.h("SingleArticleAdapter", "mSingleArticleContent isOutOfBounds");
            return;
        }
        final SingleArticleContent singleArticleContent = this.f2887a.get(i);
        c(viewHolder, singleArticleContent);
        viewHolder.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.marketing.views.SingleArticleAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view) && !TextUtils.isEmpty(singleArticleContent.getLinkValue())) {
                    SingleArticleAdapter.this.c(singleArticleContent.getLinkValue());
                    SingleArticleAdapter.this.e(2, singleArticleContent, i);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.h("SingleArticleAdapter", "click too fast or url is empty");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        e(1, singleArticleContent, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogUtil.a("SingleArticleAdapter", "goToDetail linkValue: ", str);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        }
    }

    private void c(ViewHolder viewHolder, SingleArticleContent singleArticleContent) {
        nrf.cIU_(singleArticleContent.getPicture(), viewHolder.b, (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238));
        viewHolder.c.setText(singleArticleContent.getDescription());
        viewHolder.e.setText(singleArticleContent.getTheme());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f2887a.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, SingleArticleContent singleArticleContent, int i2) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("resourcePositionId", Integer.valueOf(this.d));
        hashMap.put("resourceId", this.e.getResourceId());
        hashMap.put("resourceName", this.e.getResourceName());
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("itemCardName", singleArticleContent.getTheme());
        hashMap.put("smartRecommend", Boolean.valueOf(this.e.getSmartRecommend()));
        hashMap.put("algId", "");
        if (i == 2) {
            hashMap.put("durationTime", Integer.valueOf((int) (System.currentTimeMillis() - this.c)));
            this.c = System.currentTimeMillis();
        }
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        LogUtil.a("SingleArticleAdapter", "marketing biEvent: mPositionId: ", Integer.valueOf(this.d), ", biMap: ", hashMap.toString());
        ixx.d().d(this.b, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView b;
        private HealthTextView c;
        private LinearLayout d;
        private HealthTextView e;

        ViewHolder(View view) {
            super(view);
            this.e = (HealthTextView) view.findViewById(R.id.item_title_text);
            this.c = (HealthTextView) view.findViewById(R.id.item_describe_text);
            this.b = (ImageView) view.findViewById(R.id.item_article_image);
            this.d = (LinearLayout) view.findViewById(R.id.item_article_layout);
        }
    }
}
