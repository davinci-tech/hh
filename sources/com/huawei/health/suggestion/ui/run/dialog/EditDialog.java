package com.huawei.health.suggestion.ui.run.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/* loaded from: classes8.dex */
public class EditDialog extends DialogFragment {

    /* renamed from: a, reason: collision with root package name */
    private CustomViewDialog.Builder f3367a;
    private InputFilter b = new InputFilter() { // from class: com.huawei.health.suggestion.ui.run.dialog.EditDialog.3
        Pattern e = Pattern.compile("[🀀-🏿]|[🐀-\u1f7ff]|[☀-⟿]", 66);

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            if (!this.e.matcher(charSequence).find()) {
                return null;
            }
            LogUtil.c("Suggestion_EditDialog", "can not insert emoji");
            return "";
        }
    };
    private HealthEditText c;
    private String d;
    private CustomViewDialog e;
    private OnTextConformListener f;
    private HealthButton g;

    public interface OnTextConformListener {
        void onTextConform(String str);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.e == null) {
            this.e = b();
            a();
            d();
        }
        return this.e;
    }

    private CustomViewDialog b() {
        if (getArguments() == null) {
            LogUtil.h("Suggestion_EditDialog", "getArguments()==null");
            return null;
        }
        int i = getArguments().getInt("title");
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(getActivity());
        this.f3367a = builder;
        CustomViewDialog e = builder.d(i).czg_(aLn_()).czc_(R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.dialog.EditDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.dialog.EditDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EditDialog.this.f != null) {
                    EditDialog.this.f.onTextConform(EditDialog.this.c.getText().toString());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCanceledOnTouchOutside(false);
        this.g = this.f3367a.b();
        if (getActivity() != null) {
            this.g.setTextColor(getActivity().getResources().getColorStateList(R$color.text_color_btn));
        }
        return e;
    }

    private View aLn_() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.sug_edit_plan_name, (ViewGroup) new LinearLayout(getActivity()), false);
        HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.sug_edit_plan_name);
        this.c = healthEditText;
        aLm_(healthEditText);
        return inflate;
    }

    private void a() {
        HealthEditText healthEditText = this.c;
        if (healthEditText != null) {
            healthEditText.setText(this.d);
            Editable text = this.c.getText();
            if (text != null) {
                this.c.setSelection(text.length());
            }
        }
    }

    private void d() {
        this.c.addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.suggestion.ui.run.dialog.EditDialog.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable == null) {
                    return;
                }
                if (TextUtils.isEmpty(editable.toString())) {
                    EditDialog.this.g.setEnabled(false);
                } else {
                    EditDialog.this.g.setEnabled(true);
                }
            }
        });
    }

    private void aLm_(EditText editText) {
        ArrayList arrayList = new ArrayList(Arrays.asList(editText.getFilters()));
        arrayList.add(this.b);
        editText.setFilters((InputFilter[]) arrayList.toArray(new InputFilter[arrayList.size()]));
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
