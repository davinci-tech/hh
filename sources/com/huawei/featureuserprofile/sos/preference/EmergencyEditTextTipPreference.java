package com.huawei.featureuserprofile.sos.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class EmergencyEditTextTipPreference extends EmergencyEditTextPreference {
    public EmergencyEditTextTipPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.preference_item_double);
    }

    @Override // com.huawei.featureuserprofile.sos.preference.EmergencyEditTextPreference
    public void vm_(View view) {
        BaseActivity.setViewSafeRegion(true, (LinearLayout) view.findViewById(R.id.ll_edit_preference));
        String key = getKey();
        if (TextUtils.isEmpty(key)) {
            LogUtil.d("EmergencyEditTextTipPreference", "initBindView keyName empty");
        } else {
            vl_(key, (ImageView) view.findViewById(R.id.preference_image), (HealthTextView) view.findViewById(R.id.double_title), (HealthTextView) view.findViewById(R.id.double_summary));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void vl_(String str, ImageView imageView, HealthTextView healthTextView, HealthTextView healthTextView2) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1147692044:
                if (str.equals("address")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 81679390:
                if (str.equals("allergies")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1838387076:
                if (str.equals("medications")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2111429926:
                if (str.equals("medical_conditions")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            setDialogTitle(R.string._2130848853_res_0x7f022c55);
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131429970_res_0x7f0b0a52));
            healthTextView2.setText(getContext().getString(R.string._2130848853_res_0x7f022c55));
            if ("".equals(getText())) {
                healthTextView.setText(getContext().getString(R.string._2130848854_res_0x7f022c56));
                return;
            } else {
                healthTextView.setText(getText());
                return;
            }
        }
        if (c == 1) {
            setDialogTitle(R.string._2130848855_res_0x7f022c57);
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131429704_res_0x7f0b0948));
            healthTextView2.setText(getContext().getString(R.string._2130848855_res_0x7f022c57));
            if ("".equals(getText())) {
                healthTextView.setText(getContext().getString(R.string._2130848856_res_0x7f022c58));
                return;
            } else {
                healthTextView.setText(getText());
                return;
            }
        }
        if (c == 2) {
            setDialogTitle(R.string._2130848857_res_0x7f022c59);
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131430187_res_0x7f0b0b2b));
            healthTextView2.setText(getContext().getString(R.string._2130848857_res_0x7f022c59));
            if ("".equals(getText())) {
                healthTextView.setText(getContext().getString(R.string._2130848858_res_0x7f022c5a));
                return;
            } else {
                healthTextView.setText(getText());
                return;
            }
        }
        if (c == 3) {
            setDialogTitle(R.string._2130848859_res_0x7f022c5b);
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131430188_res_0x7f0b0b2c));
            healthTextView2.setText(getContext().getString(R.string._2130848859_res_0x7f022c5b));
            if ("".equals(getText())) {
                healthTextView.setText(getContext().getString(R.string._2130848860_res_0x7f022c5c));
                return;
            } else {
                healthTextView.setText(getText());
                return;
            }
        }
        LogUtil.c("EmergencyEditTextTipPreference", "initBindView default");
    }
}
