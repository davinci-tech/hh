package com.huawei.ui.commonui.errortip;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;

/* loaded from: classes6.dex */
public class NetworkErrorTipBar extends ErrorTipBar {
    public NetworkErrorTipBar(Context context) {
        super(context);
        e(context);
    }

    public NetworkErrorTipBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        e(context);
    }

    public NetworkErrorTipBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e(context);
    }

    private void e(Context context) {
        c();
        setJumpSetting(context);
    }

    private void c() {
        setTipText(R$string.IDS_hwh_home_group_network_disconnection);
        setLeadBtnText(R$string.IDS_hwh_home_healthshop_setting_net_work);
    }

    private void setJumpSetting(final Context context) {
        setOnClicked(new View.OnClickListener() { // from class: com.huawei.ui.commonui.errortip.NetworkErrorTipBar.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    context.startActivity(new Intent("android.settings.SETTINGS"));
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("NetworkErrorTipBar", "ActivityNotFoundException", e.getMessage());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }
}
