package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DataSourceViewAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.eeu;

/* loaded from: classes7.dex */
public class BloodPressureDetailAdapter extends DataSourceViewAdapter {
    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DataSourceViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dQi_ */
    public DataSourceViewAdapter.e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_blood_pressure_detail_item, viewGroup, false));
    }

    class b extends DataSourceViewAdapter.e {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f10413a;
        private TextView i;

        b(View view) {
            super(view);
            this.f10413a = (ImageView) view.findViewById(R.id.privacy_title_icon);
            this.i = (TextView) view.findViewById(R.id.privacy_value_status);
        }

        @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DataSourceViewAdapter.e
        void b(PrivacyDataModel privacyDataModel) {
            super.b(privacyDataModel);
            String dataTitle = privacyDataModel.getDataTitle();
            Context context = BaseApplication.getContext();
            if (context.getString(R$string.IDS_hw_show_main_home_page_bloodpressure).equals(dataTitle)) {
                int i = privacyDataModel.getInt(BleConstants.BLOODPRESSURE_SYSTOLIC);
                int i2 = privacyDataModel.getInt(BleConstants.BLOODPRESSURE_DIASTOLIC);
                this.i.setText(eeu.b(i, i2));
                this.i.setTextColor(eeu.d(eeu.c(i, i2)));
            } else {
                this.i.setText("");
            }
            if (context.getString(R$string.IDS_privacy_measure_abnormal).equals(dataTitle)) {
                this.c.setTextColor(context.getColor(R.color._2131296671_res_0x7f09019f));
                int i3 = privacyDataModel.getInt("measureAbnormal");
                if (i3 == 2) {
                    this.f10413a.setImageResource(R.drawable._2131429839_res_0x7f0b09cf);
                    return;
                }
                if (i3 == 3) {
                    this.f10413a.setImageResource(R.drawable._2131429836_res_0x7f0b09cc);
                    return;
                }
                if (i3 == 4) {
                    this.f10413a.setImageResource(R.drawable._2131429835_res_0x7f0b09cb);
                    return;
                }
                if (i3 == 5) {
                    this.f10413a.setImageResource(R.drawable._2131429838_res_0x7f0b09ce);
                    return;
                }
                if (i3 == 9) {
                    this.f10413a.setImageResource(R.drawable.ic_bp_abnomal_heart_rate);
                    return;
                } else if (i3 == 10) {
                    this.f10413a.setImageResource(R.drawable._2131429837_res_0x7f0b09cd);
                    return;
                } else {
                    this.f10413a.setImageDrawable(null);
                    return;
                }
            }
            this.c.setTextColor(context.getColor(R.color._2131299236_res_0x7f090ba4));
        }
    }
}
