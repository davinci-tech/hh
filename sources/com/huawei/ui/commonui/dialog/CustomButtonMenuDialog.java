package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class CustomButtonMenuDialog extends BaseDialog {
    private Context b;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final Context f8799a;
        private int b;
        private int d;
        private final List<Integer> c = new ArrayList();
        private final List<View.OnClickListener> e = new ArrayList();

        public Builder(Context context) {
            this.f8799a = context;
        }

        public Builder c(int i) {
            this.b = i;
            return this;
        }

        public Builder a(int i) {
            this.d = i;
            return this;
        }

        public Builder cyw_(int i, View.OnClickListener onClickListener) {
            this.c.add(Integer.valueOf(i));
            this.e.add(onClickListener);
            return this;
        }

        public CustomButtonMenuDialog b() {
            return new CustomButtonMenuDialog(this.f8799a, this);
        }
    }

    private CustomButtonMenuDialog(Context context, Builder builder) {
        super(context, R.style.CustomDialog);
        this.b = context;
        d(builder);
    }

    private void d(Builder builder) {
        View inflate = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.commonui_btn_menu_dialog, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.commonui_dialog_title);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.commonui_dialog_content);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.commonui_btn_menu_dialog_btn_container);
        if (nsn.r()) {
            HealthScrollView healthScrollView = (HealthScrollView) inflate.findViewById(R.id.text_scrollview);
            healthScrollView.setVisibility(0);
            HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.commonui_dialog_content_large_mode);
            inflate.findViewById(R.id.commonui_dialog_content).setVisibility(8);
            healthScrollView.getLayoutParams().height = (int) (nsn.j() * (nsn.s() ? 0.25d : 0.15d));
            healthTextView2 = healthTextView3;
        }
        if (builder.b != 0) {
            healthTextView.setText(builder.b);
        }
        if (builder.d != 0) {
            healthTextView2.setText(builder.d);
        }
        ArrayList arrayList = new ArrayList();
        String str = "";
        for (int i = 0; i < builder.c.size(); i++) {
            int intValue = ((Integer) builder.c.get(i)).intValue();
            if (intValue != 0) {
                arrayList.add(this.b.getString(intValue));
                if (arrayList.get(i).length() > str.length()) {
                    str = arrayList.get(i);
                }
            }
        }
        if (!nsn.t()) {
            d(str, arrayList);
        }
        for (int i2 = 0; i2 < builder.c.size(); i2++) {
            HealthButton healthButton = new HealthButton(this.b);
            healthButton.setBackground(ContextCompat.getDrawable(this.b, R$drawable.button_background_menu_dialog));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            if (i2 > 0) {
                layoutParams.topMargin = this.b.getResources().getDimensionPixelSize(R.dimen._2131362478_res_0x7f0a02ae);
            }
            healthButton.setLayoutParams(layoutParams);
            healthButton.setText(arrayList.get(i2));
            healthButton.setMaxLines(2);
            final View.OnClickListener onClickListener = (View.OnClickListener) builder.e.get(i2);
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.CustomButtonMenuDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickListener.onClick(view);
                    CustomButtonMenuDialog.this.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            linearLayout.addView(healthButton);
        }
        setContentView(inflate);
    }

    private void d(String str, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            int length = str.length() - list.get(i).length();
            StringBuilder sb = new StringBuilder();
            if (length > 1) {
                list.set(i, d(length, sb, new StringBuilder(list.get(i))));
            }
        }
    }

    private String d(int i, StringBuilder sb, StringBuilder sb2) {
        return nsn.a(i, sb, sb2, true);
    }
}
