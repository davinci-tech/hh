package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.eeu;
import defpackage.nrz;
import defpackage.rju;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class BloodPressureGroupAdapter extends GroupDataAdapter {
    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter
    public View dQv_(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_pressure_child_item, viewGroup, false);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter
    public GroupDataAdapter.b dQu_(View view) {
        return new e(view);
    }

    class e extends GroupDataAdapter.b {
        private ImageView d;
        private ImageView i;
        private HealthTextView o;

        e(View view) {
            super(view);
            this.o = (HealthTextView) view.findViewById(R.id.hw_show_value_status);
            this.d = (ImageView) view.findViewById(R.id.hw_show_child_left_img);
            ImageView imageView = (ImageView) view.findViewById(R.id.privacy_measure_abnormal);
            this.i = imageView;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.privacy.template.model.adapter.BloodPressureGroupAdapter.e.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    View inflate = LayoutInflater.from(view2.getContext()).inflate(R.layout.privacy_bubble_layout, (ViewGroup) null);
                    TextView textView = (TextView) inflate.findViewById(R.id.privacy_content);
                    if (view2.getTag() instanceof Integer) {
                        textView.setText(rju.c.b(((Integer) view2.getTag()).intValue()));
                    }
                    PopupWindow popupWindow = new PopupWindow(view2.getContext());
                    popupWindow.setContentView(inflate);
                    popupWindow.setBackgroundDrawable(null);
                    popupWindow.setFocusable(true);
                    inflate.measure(0, 0);
                    popupWindow.showAsDropDown(view2, (-(inflate.getMeasuredWidth() - view2.getWidth())) / 2, view2.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532));
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }

        @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter.b
        protected void c(PrivacyDataModel privacyDataModel) {
            super.c(privacyDataModel);
            int i = privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC);
            int i2 = privacyDataModel.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC);
            this.o.setText(eeu.b(i, i2));
            this.o.setTextColor(eeu.d(eeu.c(i, i2)));
            int parseInt = Integer.parseInt(privacyDataModel.getString("iconResource"));
            if (LanguageUtil.bc(BaseApplication.e())) {
                BitmapDrawable cKn_ = nrz.cKn_(BaseApplication.e(), parseInt);
                if (cKn_ != null) {
                    this.d.setImageDrawable(cKn_);
                }
            } else {
                this.d.setImageResource(parseInt);
            }
            int i3 = privacyDataModel.getInt("measureAbnormal");
            if (i3 == 2) {
                this.i.setBackgroundResource(R.drawable._2131429839_res_0x7f0b09cf);
                this.i.setTag(2);
                return;
            }
            if (i3 == 3) {
                this.i.setBackgroundResource(R.drawable._2131429836_res_0x7f0b09cc);
                this.i.setTag(3);
                return;
            }
            if (i3 == 4) {
                this.i.setBackgroundResource(R.drawable._2131429835_res_0x7f0b09cb);
                this.i.setTag(4);
                return;
            }
            if (i3 == 5) {
                this.i.setBackgroundResource(R.drawable._2131429838_res_0x7f0b09ce);
                this.i.setTag(5);
            } else if (i3 == 9) {
                this.i.setBackgroundResource(R.drawable.ic_bp_abnomal_heart_rate);
                this.i.setTag(9);
            } else if (i3 == 10) {
                this.i.setBackgroundResource(R.drawable._2131429837_res_0x7f0b09cd);
                this.i.setTag(10);
            } else {
                this.i.setBackground(null);
            }
        }
    }
}
