package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import com.huawei.ui.main.stories.health.views.HealthBodyBarData;

/* loaded from: classes7.dex */
public class qtl extends qta {
    private View c;
    private cfe d;

    public qtl(Context context, BodyReportRecycleItem bodyReportRecycleItem) {
        super(context, bodyReportRecycleItem);
        if (bodyReportRecycleItem == null) {
            LogUtil.h("BodyReportPeerComparisonView", "BodyReportPeerComparisonView data is null");
            return;
        }
        cfe b = bodyReportRecycleItem.b();
        this.d = b;
        if (b == null) {
            LogUtil.h("BodyReportPeerComparisonView", "BodyReportPeerComparisonView mWeightBean is null");
        }
    }

    @Override // defpackage.qta
    public String b() {
        if (this.e == null) {
            return super.b();
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_weight_peer_comparison);
    }

    @Override // defpackage.qta
    public View dIW_() {
        if (this.e == null) {
            return super.dIW_();
        }
        this.c = LayoutInflater.from(this.e).inflate(R.layout.view_body_report_peer_comparison, (ViewGroup) null);
        a();
        return this.c;
    }

    private void a() {
        HealthBodyBarData healthBodyBarData = (HealthBodyBarData) this.c.findViewById(R.id.health_body_bar_data);
        if (this.e == null) {
            LogUtil.h("BodyReportPeerComparisonView", "setBodyBarData Context is null");
        } else if (this.d == null) {
            LogUtil.h("BodyReportPeerComparisonView", "setBodyBarData WeightBean is null");
        } else {
            qrj.a(this.e, this.d, healthBodyBarData);
        }
    }
}
