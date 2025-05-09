package com.huawei.health.h5pro.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProWebViewActivity;
import com.huawei.health.h5pro.utils.H5ProNetworkUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.phone.hwbutton.widget.HwButton;

/* loaded from: classes3.dex */
public class LoadErrorView extends RelativeLayout {
    private void XT_(View view) {
        ((TextView) view.findViewById(R.id.error_tip)).setText(R.string._2130842061_res_0x7f0211cd);
        if (getActivity() instanceof H5ProWebViewActivity) {
            HwButton hwButton = (HwButton) view.findViewById(R.id.setting_button);
            hwButton.setVisibility(0);
            hwButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.h5pro.view.LoadErrorView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    LoadErrorView.this.getContext().startActivity(new Intent("android.settings.SETTINGS"));
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }
    }

    private void d(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.load_fail, (ViewGroup) this, true);
        if (H5ProNetworkUtil.isNetworkAvailable(context)) {
            return;
        }
        XT_(inflate);
    }

    private Activity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    public LoadErrorView(Context context) {
        super(context);
        d(context);
    }
}
