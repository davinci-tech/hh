package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.content.Context;
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
import com.huawei.ui.main.stories.privacy.template.model.adapter.BloodPressureDoubleGroupAdapter;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.uikit.hwbubblelayout.widget.HwBubbleLayout;
import defpackage.eeu;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.rju;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class BloodPressureDoubleGroupAdapter extends DoubleGroupDataAdapter {
    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter
    public int d() {
        return R.layout.privacy_pressure_child_item;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter
    public DoubleGroupDataAdapter.e dQo_(View view) {
        return new c(view);
    }

    public class c extends DoubleGroupDataAdapter.e {
        ImageView b;
        HealthTextView c;
        ImageView d;

        c(View view) {
            super(view);
            this.c = (HealthTextView) view.findViewById(R.id.hw_show_value_status);
            this.d = (ImageView) view.findViewById(R.id.hw_show_child_left_img);
            ImageView imageView = (ImageView) view.findViewById(R.id.privacy_measure_abnormal);
            this.b = imageView;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: rrs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    BloodPressureDoubleGroupAdapter.c.dQf_(view2);
                }
            });
        }

        public static /* synthetic */ void dQf_(View view) {
            Context context = view.getContext();
            View inflate = LayoutInflater.from(context).inflate(R.layout.privacy_bubble_layout, (ViewGroup) null);
            if (inflate instanceof HwBubbleLayout) {
                HwBubbleLayout hwBubbleLayout = (HwBubbleLayout) inflate;
                TextView textView = (TextView) hwBubbleLayout.findViewById(R.id.privacy_content);
                if (view.getTag() instanceof Integer) {
                    textView.setText(rju.c.b(((Integer) view.getTag()).intValue()));
                }
                PopupWindow popupWindow = new PopupWindow(context);
                popupWindow.setContentView(hwBubbleLayout);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setFocusable(true);
                hwBubbleLayout.measure(0, 0);
                int measuredWidth = hwBubbleLayout.getMeasuredWidth();
                int measuredHeight = hwBubbleLayout.getMeasuredHeight();
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                if ((((nsn.j() - iArr[1]) - view.getHeight()) - (nsn.ab(context) ? nsn.q(context) : 0)) - measuredHeight < view.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532)) {
                    hwBubbleLayout.setArrowDirection(HwBubbleLayout.ArrowDirection.BOTTOM);
                    popupWindow.showAtLocation(view, 0, (int) ((iArr[0] + (view.getWidth() * 0.5f)) - (measuredWidth * 0.5f)), (iArr[1] - measuredHeight) - view.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532));
                } else {
                    hwBubbleLayout.setArrowDirection(HwBubbleLayout.ArrowDirection.TOP);
                    popupWindow.showAsDropDown(view, (int) ((-(measuredWidth - view.getWidth())) * 0.5f), view.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532));
                }
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DoubleGroupDataAdapter.e
        void e(PrivacyDataModel privacyDataModel) {
            super.e(privacyDataModel);
            int i = privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC);
            int i2 = privacyDataModel.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC);
            this.c.setText(eeu.b(i, i2));
            this.c.setTextColor(eeu.d(eeu.c(i, i2)));
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
                this.b.setBackgroundResource(R.drawable._2131429839_res_0x7f0b09cf);
                this.b.setTag(2);
                return;
            }
            if (i3 == 3) {
                this.b.setBackgroundResource(R.drawable._2131429836_res_0x7f0b09cc);
                this.b.setTag(3);
                return;
            }
            if (i3 == 4) {
                this.b.setBackgroundResource(R.drawable._2131429835_res_0x7f0b09cb);
                this.b.setTag(4);
                return;
            }
            if (i3 == 5) {
                this.b.setBackgroundResource(R.drawable._2131429838_res_0x7f0b09ce);
                this.b.setTag(5);
            } else if (i3 == 9) {
                this.b.setBackgroundResource(R.drawable.ic_bp_abnomal_heart_rate);
                this.b.setTag(9);
            } else if (i3 == 10) {
                this.b.setBackgroundResource(R.drawable._2131429837_res_0x7f0b09cd);
                this.b.setTag(10);
            } else {
                this.b.setBackground(null);
            }
        }
    }
}
