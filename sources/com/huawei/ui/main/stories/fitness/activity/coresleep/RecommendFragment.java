package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.MultiGridsTemplate;
import com.huawei.health.marketing.datatype.RcmItem;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.health.adapter.SleepRecommendAdapter;
import defpackage.ccq;
import defpackage.nsn;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private MultiGridsTemplate f9785a;
    private SleepRecommendAdapter b;
    private Context c;
    private View d;
    private int e;
    private HealthSubHeader f;
    private LinearLayout g;
    private HealthRecycleView h;
    private List<RcmItem> i;
    private int j;

    public static RecommendFragment c(ResourceBriefInfo resourceBriefInfo, String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("topicId", str);
        bundle.putInt("positionId", i);
        if (resourceBriefInfo != null) {
            bundle.putInt("contentType", resourceBriefInfo.getContentType());
        }
        RecommendFragment recommendFragment = new RecommendFragment();
        recommendFragment.setArguments(bundle);
        return recommendFragment;
    }

    public void d(MultiGridsTemplate multiGridsTemplate, List<RcmItem> list) {
        this.f9785a = multiGridsTemplate;
        this.i = list;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.e = arguments.getInt("contentType");
            this.j = arguments.getInt("positionId");
        }
        this.c = getContext();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            this.d = layoutInflater.inflate(R.layout.series_course_recommend_fragment, viewGroup, false);
            c();
        }
        e();
        return this.d;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("RecommendFragment", "onConfigurationChanged");
    }

    private void c() {
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.d.findViewById(R.id.sub_header);
        this.f = healthSubHeader;
        b(healthSubHeader);
        this.f.setMoreTextVisibility(4);
        this.f.setRightArrayVisibility(0);
        this.f.setSubHeaderBackgroundColor(this.c.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        LinearLayout linearLayout = (LinearLayout) this.d.findViewById(R.id.root_view_layout);
        this.g = linearLayout;
        linearLayout.post(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.RecommendFragment.4
            @Override // java.lang.Runnable
            public void run() {
                RecommendFragment.this.g.setMinimumWidth(nsn.n());
            }
        });
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.d.findViewById(R.id.recommend_recycle_view);
        this.h = healthRecycleView;
        ccq.b(healthRecycleView);
        this.h.setLayoutManager(new LinearLayoutManager(this.c, 0, false));
        if (this.b == null) {
            this.b = new SleepRecommendAdapter(this.c);
        }
        this.h.setAdapter(this.b);
    }

    private void b(HealthSubHeader healthSubHeader) {
        ViewGroup.LayoutParams layoutParams = healthSubHeader.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginEnd(((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
            healthSubHeader.setLayoutParams(layoutParams2);
        }
    }

    public void e() {
        MultiGridsTemplate multiGridsTemplate = this.f9785a;
        if (multiGridsTemplate == null) {
            LogUtil.b("RecommendFragment", "updateData mData is null");
            return;
        }
        String name = multiGridsTemplate.getName();
        if (this.f9785a.getNameVisibility().booleanValue() && !TextUtils.isEmpty(name)) {
            this.f.setVisibility(0);
            this.f.setHeadTitleText(name);
        } else {
            this.f.setVisibility(4);
        }
        final String linkValue = this.f9785a.getLinkValue();
        if (TextUtils.isEmpty(linkValue)) {
            this.f.setRightArrayVisibility(4);
        } else {
            this.f.setRightArrayVisibility(0);
            this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.RecommendFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendFragment.this.a(linkValue);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        SleepRecommendAdapter sleepRecommendAdapter = this.b;
        if (sleepRecommendAdapter != null) {
            sleepRecommendAdapter.b(this.e, this.f9785a, this.j, this.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        MarketRouterApi marketRouterApi;
        LogUtil.a("RecommendFragment", "onClick");
        if (TextUtils.isEmpty(str) || nsn.o() || (marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)) == null) {
            return;
        }
        marketRouterApi.router(str);
    }
}
