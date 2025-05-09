package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepAudioClearDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private Context f9975a;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final Context f9977a;
        private String e;
        private final List<Integer> d = new ArrayList();
        private final List<View.OnClickListener> c = new ArrayList();

        public Builder(Context context) {
            this.f9977a = context;
        }

        public Builder d(String str) {
            this.e = str;
            return this;
        }

        public Builder dwW_(int i, View.OnClickListener onClickListener) {
            this.d.add(Integer.valueOf(i));
            this.c.add(onClickListener);
            return this;
        }

        public SleepAudioClearDialog a() {
            return new SleepAudioClearDialog(this.f9977a, this);
        }
    }

    private SleepAudioClearDialog(Context context, Builder builder) {
        super(context, R.style.CustomDialog);
        this.f9975a = context;
        e(builder);
    }

    private void e(Builder builder) {
        View inflate = ((LayoutInflater) this.f9975a.getSystemService("layout_inflater")).inflate(R.layout.sleep_audio_clear_dialog, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.sleep_audio_clear_dialog_title);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.commonui_btn_menu_dialog_btn_container);
        if (builder.e != null) {
            healthTextView.setText(builder.e);
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < builder.d.size(); i++) {
            int intValue = ((Integer) builder.d.get(i)).intValue();
            if (intValue != 0) {
                arrayList.add(this.f9975a.getString(intValue));
            }
        }
        for (int i2 = 0; i2 < builder.d.size(); i2++) {
            HealthButton healthButton = new HealthButton(this.f9975a);
            healthButton.setBackground(ContextCompat.getDrawable(this.f9975a, R$drawable.button_background_menu_dialog));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            if (i2 > 0) {
                healthButton.setTextColor(this.f9975a.getResources().getColor(R$color.textColorSecondary));
                layoutParams.topMargin = this.f9975a.getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
            } else {
                healthButton.setTextColor(this.f9975a.getResources().getColor(R$color.textColorPrimaryActivated));
                layoutParams.topMargin = this.f9975a.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
            }
            healthButton.setLayoutParams(layoutParams);
            healthButton.setText((CharSequence) arrayList.get(i2));
            healthButton.setTextSize(this.f9975a.getResources().getDimensionPixelSize(R.dimen._2131362923_res_0x7f0a046b));
            final View.OnClickListener onClickListener = (View.OnClickListener) builder.c.get(i2);
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.SleepAudioClearDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickListener.onClick(view);
                    SleepAudioClearDialog.this.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            linearLayout.addView(healthButton);
        }
        setContentView(inflate);
    }
}
