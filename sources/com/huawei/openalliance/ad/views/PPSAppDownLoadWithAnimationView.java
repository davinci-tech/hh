package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.util.AttributeSet;
import com.huawei.health.R;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes5.dex */
public class PPSAppDownLoadWithAnimationView extends PPSAppDetailView {
    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected void b(Context context) {
        this.b = (AppDownloadButton) findViewById(R.id.app_download_btn);
    }

    @Override // com.huawei.openalliance.ad.views.PPSAppDetailView
    protected int a(Context context) {
        return ao.n(context) ? R.layout.hiad_app_download_elderly_font_with_animation_template : R.layout.hiad_app_download_with_animation_template;
    }

    public PPSAppDownLoadWithAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PPSAppDownLoadWithAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PPSAppDownLoadWithAnimationView(Context context) {
        super(context);
    }
}
