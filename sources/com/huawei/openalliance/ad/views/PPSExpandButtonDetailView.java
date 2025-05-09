package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes5.dex */
public class PPSExpandButtonDetailView extends PPSAppDetailView {
    private RelativeLayout e;

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected int getDetailStyle() {
        return 2;
    }

    public void setExtraViewVisibility(int i) {
        if (i == 0) {
            return;
        }
        if (this.d != null) {
            this.d.setVisibility(i);
        }
        this.e = (RelativeLayout) findViewById(R.id.app_download_btn_rl);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, R.id.app_description);
        if (i != 0 || ao.n(getContext())) {
            layoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen._2131363268_res_0x7f0a05c4), 0, getResources().getDimensionPixelSize(R.dimen._2131363268_res_0x7f0a05c4));
        } else {
            layoutParams.addRule(8);
            layoutParams.addRule(14);
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen._2131363268_res_0x7f0a05c4);
        }
        this.e.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected void b(Context context) {
        this.b = (AppDownloadButton) findViewById(R.id.app_download_btn);
        Resources resources = context.getResources();
        this.b.setFixedWidth(true);
        this.b.setTextColor(resources.getColor(R.color._2131297955_res_0x7f0906a3));
    }

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    public void a(int i) {
        ho.b("ExBtnDetailView", "updateDetailViewType: %s", Integer.valueOf(i));
        if (2 == i) {
            this.d.setVisibility(8);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.app_download_btn_rl);
            this.e = relativeLayout;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
            if (layoutParams == null) {
                ho.c("ExBtnDetailView", "param is null");
                return;
            }
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363359_res_0x7f0a061f);
            layoutParams.topMargin = dimensionPixelSize;
            layoutParams.bottomMargin = dimensionPixelSize;
            ho.a("ExBtnDetailView", "bottomMargin: %s", Integer.valueOf(layoutParams.bottomMargin));
            this.e.setLayoutParams(layoutParams);
        }
    }

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected int a(Context context) {
        return ao.n(context) ? this.c == 1 ? R.layout.hiad_landing_elderly_friendly_detail_half : R.layout.hiad_landing_elderly_friendly_detail : this.c == 1 ? R.layout.hiad_landing_expand_button_detail_half : R.layout.hiad_landing_expand_button_detail;
    }

    public PPSExpandButtonDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PPSExpandButtonDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PPSExpandButtonDetailView(Context context) {
        super(context);
    }
}
