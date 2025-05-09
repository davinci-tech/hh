package com.huawei.featureuserprofile.sos.preference;

import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.featureuserprofile.sos.interf.OnPreferenceChangedListener;
import com.huawei.featureuserprofile.sos.interf.ReloadablePreferenceInterface;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.btz;
import health.compact.a.HwEncryptUtil;
import health.compact.a.StorageParams;
import java.security.GeneralSecurityException;

/* loaded from: classes7.dex */
public class EmergencyEditTextPreference extends EditTextPreference implements ReloadablePreferenceInterface {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2032a;
    private String b;
    private String c;
    private OnPreferenceChangedListener d;
    private Context e;

    public EmergencyEditTextPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.preference_item);
        this.b = getKey();
        this.e = context;
    }

    @Override // com.huawei.featureuserprofile.sos.interf.ReloadablePreferenceInterface
    public void reloadFromPreference() {
        setText(getText());
    }

    @Override // android.preference.Preference
    public CharSequence getSummary() {
        String text = getText();
        return TextUtils.isEmpty(text) ? super.getSummary() : text;
    }

    @Override // android.preference.EditTextPreference
    public void setText(String str) {
        try {
            String b = HwEncryptUtil.c(getContext()).b(2, str);
            boolean z = !TextUtils.equals(this.c, str);
            if (z || !this.f2032a) {
                this.c = str;
                this.f2032a = true;
                EmergencyInfoManager.c().setSharedPreference(btz.c() + this.b, b, new StorageParams(1));
                if (z) {
                    notifyDependencyChange(shouldDisableDependents());
                    notifyChanged();
                    OnPreferenceChangedListener onPreferenceChangedListener = this.d;
                    if (onPreferenceChangedListener != null) {
                        onPreferenceChangedListener.onPreferenceChanged(this.b, this.c);
                    }
                }
            }
        } catch (GeneralSecurityException unused) {
            LogUtil.b("EmergencyEditTextPreference", "setText catch GeneralSecurityException");
        }
    }

    @Override // android.preference.EditTextPreference
    public String getText() {
        try {
            return HwEncryptUtil.c(getContext()).a(2, EmergencyInfoManager.c().getSharedPreference(btz.c() + this.b));
        } catch (GeneralSecurityException unused) {
            LogUtil.b("EmergencyEditTextPreference", "getText catch GeneralSecurityException");
            return "";
        }
    }

    @Override // android.preference.EditTextPreference, android.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        if (obj instanceof String) {
            setText(z ? getText() : (String) obj);
        } else {
            setText(z ? getText() : "");
        }
    }

    @Override // android.preference.Preference
    protected void onBindView(View view) {
        if (view == null) {
            LogUtil.h("EmergencyEditTextPreference", "onBindView view is null");
        } else {
            super.onBindView(view);
            vm_(view);
        }
    }

    protected void vm_(View view) {
        BaseActivity.setViewSafeRegion(true, (LinearLayout) view.findViewById(R.id.ll_name_preference));
        ImageView imageView = (ImageView) view.findViewById(R.id.preference_image);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.single_text);
        setDialogTitle(R.string._2130848852_res_0x7f022c54);
        imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable._2131430210_res_0x7f0b0b42));
        if ("".equals(getText())) {
            healthTextView.setText(getContext().getString(R.string._2130848852_res_0x7f022c54));
            healthTextView.setTextColor(ContextCompat.getColor(getContext(), R.color._2131298850_res_0x7f090a22));
        } else {
            healthTextView.setText(getText());
            healthTextView.setTextColor(ContextCompat.getColor(getContext(), R.color._2131299236_res_0x7f090ba4));
        }
    }

    public void a(OnPreferenceChangedListener onPreferenceChangedListener) {
        this.d = onPreferenceChangedListener;
    }

    @Override // android.preference.EditTextPreference, android.preference.DialogPreference
    protected void showDialog(Bundle bundle) {
        View inflate = View.inflate(this.e, R.layout.commonui_dialog_single_edit, null);
        final HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit);
        healthEditText.setImeOptions(268435456);
        healthEditText.setSelectAllOnFocus(true);
        if (this.e.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager) {
            WindowManager windowManager = (WindowManager) this.e.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            healthEditText.setMaxHeight((displayMetrics.heightPixels * 2) / 3);
        }
        CharSequence hint = getEditText() == null ? null : getEditText().getHint();
        if (!TextUtils.isEmpty(hint)) {
            healthEditText.setHint(hint.toString());
        }
        String text = getText();
        if (!TextUtils.isEmpty(text)) {
            healthEditText.setText(text);
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.e);
        builder.a(getTitle() != null ? getTitle().toString() : null).czg_(inflate).cze_(R.string._2130848893_res_0x7f022c7d, new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.preference.EmergencyEditTextPreference.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmergencyEditTextPreference.this.setText(healthEditText.getText().toString());
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130848866_res_0x7f022c62, new View.OnClickListener() { // from class: com.huawei.featureuserprofile.sos.preference.EmergencyEditTextPreference.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
        e.getWindow().setSoftInputMode(32);
    }
}
