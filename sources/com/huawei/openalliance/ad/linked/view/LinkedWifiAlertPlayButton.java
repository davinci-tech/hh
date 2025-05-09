package com.huawei.openalliance.ad.linked.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.views.ProgressButton;

/* loaded from: classes9.dex */
public class LinkedWifiAlertPlayButton extends ProgressButton {
    private Context o;
    private com.huawei.openalliance.ad.linked.view.a p;
    private a q;

    public interface a {
        void a();
    }

    @Override // com.huawei.openalliance.ad.views.ProgressButton, com.huawei.hms.ads.uiengine.common.IProgressButton
    public void setTextColor(int i) {
        super.setTextColor(i);
    }

    public void setText(int i) {
        super.setText(this.o.getResources().getString(i));
    }

    public void setCoverListener(a aVar) {
        this.q = aVar;
    }

    @Override // com.huawei.openalliance.ad.views.ProgressButton, android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar = this.q;
        if (aVar != null) {
            aVar.a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.openalliance.ad.views.ProgressButton, com.huawei.hms.ads.uiengine.common.IProgressButton
    public CharSequence getText() {
        return super.getText();
    }

    public com.huawei.openalliance.ad.linked.view.a getStyle() {
        return this.p;
    }

    private void a(Context context) {
        this.o = context;
        this.p = new com.huawei.openalliance.ad.linked.view.a(context);
    }

    public LinkedWifiAlertPlayButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    public LinkedWifiAlertPlayButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public LinkedWifiAlertPlayButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LinkedWifiAlertPlayButton(Context context) {
        super(context);
        a(context);
    }
}
