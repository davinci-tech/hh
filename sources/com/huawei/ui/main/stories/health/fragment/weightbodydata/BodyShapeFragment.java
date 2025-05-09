package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.cfe;
import defpackage.doj;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qrf;

/* loaded from: classes6.dex */
public class BodyShapeFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10194a;
    private HealthTextView d;
    private HealthCardView e;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getLayoutId() {
        return R.layout.fragment_weight_body_data_body_shape;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initView(View view) {
        this.d = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_shape_text);
        this.f10194a = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_shape);
        this.e = (HealthCardView) view.findViewById(R.id.fragment_weight_body_shape_card_view);
        initResultViewAndAboutView(view);
        e();
    }

    private void e() {
        int d;
        if (this.mWeightBean != null) {
            d = doj.d(this.mWeightBean.an(), this.mWeightBean.f());
        } else {
            d = doj.d(MultiUsersManager.INSTANCE.getMainUser().c(), this.mAiBodyShapeBean.c());
        }
        String d2 = qqy.d(d);
        this.e.setVisibility(0);
        this.d.setText(d2);
        this.d.setVisibility(0);
        this.f10194a.setImageResource(qrf.d(d));
        this.f10194a.setVisibility(0);
        initResult(qqy.b(d), qqy.c(d));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initAbout(qrd.d(0), qrd.d(1));
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        cfe cfeVar = this.mWeightBean;
        return 27;
    }
}
