package com.huawei.featureuserprofile.sos.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.PreferenceCategory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes8.dex */
public class EmergencyContactsPreference extends PreferenceCategory {
    public EmergencyContactsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!TextUtils.isEmpty(getTitle())) {
            setLayoutResource(R.layout.custom_preference_category);
        } else {
            LogUtil.h("EmergencyContactsPreference", "EmergencyContactsPreference not set title");
        }
    }

    @Override // android.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        if (typedArray == null) {
            LogUtil.h("EmergencyContactsPreference", "onGetDefaultValue typedArray is null");
            return "";
        }
        return typedArray.getString(i);
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        if (view == null) {
            LogUtil.h("EmergencyContactsPreference", "onBindView view is null");
            return;
        }
        super.onBindView(view);
        BaseActivity.setViewSafeRegion(true, (LinearLayout) view.findViewById(R.id.ll_category));
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.category_title);
        View findViewById = view.findViewById(R.id.divider);
        if (healthTextView != null) {
            healthTextView.setText(getTitle());
            healthTextView.setTextColor(ContextCompat.getColor(getContext(), R.color._2131299241_res_0x7f090ba9));
        }
        if (findViewById != null) {
            findViewById.setBackgroundColor(getContext().getResources().getColor(R.color._2131296691_res_0x7f0901b3));
        }
    }
}
