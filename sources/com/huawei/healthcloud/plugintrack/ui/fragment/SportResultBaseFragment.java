package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import defpackage.hjw;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class SportResultBaseFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    protected hjw f3732a = null;
    protected View b;
    protected Context c;

    protected int c() {
        return 4155;
    }

    protected void f() {
        int c;
        if (k() && (c = c()) != -1) {
            final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
            Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(c);
            resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: hio
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    SportResultBaseFragment.this.e(marketingApi, (Map) obj);
                }
            });
            resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: hiq
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    SportResultBaseFragment.this.e(exc);
                }
            });
        }
    }

    public /* synthetic */ void e(MarketingApi marketingApi, Map map) {
        LinearLayout linearLayout = (LinearLayout) this.b.findViewById(R.id.layout_marketing);
        List<View> marketingViewList = marketingApi.getMarketingViewList(getContext(), marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map));
        if (koq.b(marketingViewList)) {
            return;
        }
        linearLayout.setVisibility(0);
        Iterator<View> it = marketingViewList.iterator();
        while (it.hasNext()) {
            linearLayout.addView(it.next());
        }
    }

    public /* synthetic */ void e(Exception exc) {
        String d = d();
        Object[] objArr = new Object[2];
        objArr[0] = "initMarketing onFailure ";
        objArr[1] = exc == null ? "" : exc.getMessage();
        LogUtil.b(d, objArr);
    }

    protected boolean o() {
        if (getActivity() instanceof TrackDetailActivity) {
            return false;
        }
        ReleaseLogUtil.c("Track_SportResultBaseFragment", "activity type error");
        return true;
    }

    protected void h() {
        this.f3732a = ((TrackDetailActivity) getActivity()).e();
        this.c = getActivity();
    }

    protected boolean k() {
        hjw hjwVar = this.f3732a;
        return (hjwVar == null || hjwVar.e() == null) ? false : true;
    }

    protected boolean m() {
        hjw hjwVar = this.f3732a;
        return (hjwVar == null || hjwVar.d() == null) ? false : true;
    }

    protected String d() {
        return "Track_SportResultBaseFragment";
    }
}
