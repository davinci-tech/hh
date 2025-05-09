package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DataSourceViewAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.rre;
import defpackage.rsn;
import defpackage.scg;
import health.compact.a.CommonUtil;

/* loaded from: classes7.dex */
public class BloodOxygenDetailAdapter extends DataSourceViewAdapter {
    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DataSourceViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dQi_ */
    public DataSourceViewAdapter.e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_blood_oxygen_detail_item, viewGroup, false));
    }

    class b extends DataSourceViewAdapter.e {
        private HealthTextView d;

        b(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.privacy_detail_point);
        }

        @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DataSourceViewAdapter.e
        void b(PrivacyDataModel privacyDataModel) {
            super.b(privacyDataModel);
            String dataTitle = privacyDataModel.getDataTitle();
            String dataDesc = privacyDataModel.getDataDesc();
            Context context = BaseApplication.getContext();
            if (context.getString(R$string.IDS_privacy_highland_risk).equals(dataTitle)) {
                int a2 = CommonUtil.a(dataDesc, 10);
                this.e.setTextColor(scg.b(a2));
                this.e.setText(rre.b(a2));
                this.d.setBackground(rsn.dQF_(a2));
                this.d.setText(scg.h(a2));
                this.d.setVisibility(0);
                return;
            }
            if (context.getString(R$string.IDS_hw_show_main_permission_blood_oxygen).equals(dataTitle)) {
                int a3 = CommonUtil.a(dataDesc, 10);
                this.e.setTextColor(scg.c(a3));
                this.e.setText(rre.d(a3));
                this.d.setVisibility(8);
                return;
            }
            this.e.setText(dataDesc);
            this.e.setTextColor(context.getColor(R.color._2131299241_res_0x7f090ba9));
            this.d.setVisibility(8);
        }
    }
}
