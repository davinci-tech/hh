package com.huawei.featureuserprofile.sos.preference;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.featureuserprofile.sos.activity.EditContactActivity;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes8.dex */
public class EntryContactPreference extends Preference {
    private Context c;

    public EntryContactPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        setLayoutResource(R.layout.preference_item_entry);
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        if (view == null) {
            LogUtil.e("EntryContactPreference", "onBindView view is null");
            return;
        }
        super.onBindView(view);
        BaseActivity.setViewSafeRegion(true, (LinearLayout) view.findViewById(R.id.ll_contact_preference));
        ImageView imageView = (ImageView) view.findViewById(R.id.preference_image);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.single_text);
        imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131429889_res_0x7f0b0a01));
        healthTextView.setText(getContext().getString(R.string._2130848875_res_0x7f022c6b));
        healthTextView.setTextColor(ContextCompat.getColor(getContext(), R.color._2131299236_res_0x7f090ba4));
        if (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams.setMarginStart(getContext().getResources().getDimensionPixelSize(R.dimen._2131361976_res_0x7f0a00b8));
            healthTextView.setLayoutParams(layoutParams);
        }
        ImageView imageView2 = (ImageView) view.findViewById(R.id.arrow);
        if (LanguageUtil.bc(this.c)) {
            imageView2.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            imageView2.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
        if (getKey().equals("entry_contact_edit")) {
            setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.huawei.featureuserprofile.sos.preference.EntryContactPreference.1
                @Override // android.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(Preference preference) {
                    if (EntryContactPreference.this.c == null) {
                        LogUtil.e("EntryContactPreference", "onPreferenceClick context is null");
                        return false;
                    }
                    try {
                        EntryContactPreference.this.c.startActivity(new Intent(EntryContactPreference.this.c, (Class<?>) EditContactActivity.class));
                        return true;
                    } catch (ActivityNotFoundException e) {
                        LogUtil.e("EntryContactPreference", "ActivityNotFoundException", e.getMessage());
                        return true;
                    }
                }
            });
        }
    }
}
