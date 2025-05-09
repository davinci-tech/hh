package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.rre;
import defpackage.rsn;
import health.compact.a.UnitUtil;

/* loaded from: classes7.dex */
public class BloodOxygenDayAdapter extends DayDataViewAdapter {
    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dQm_ */
    public DayDataViewAdapter.e onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_blood_oxygen_child_item, viewGroup, false);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.privacy_item_rl);
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMarginStart(0);
            layoutParams2.setMarginEnd(0);
        }
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.setBackgroundResource(R.drawable._2131431106_res_0x7f0b0ec2);
        return new d(inflate);
    }

    class d extends DayDataViewAdapter.e {
        private HealthTextView f;

        d(View view) {
            super(view);
            this.f = (HealthTextView) view.findViewById(R.id.privacy_extra_data);
        }

        @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter.e
        protected void b(PrivacyDataModel privacyDataModel) {
            int i = privacyDataModel.getInt("bloodOxygenKey");
            this.j.setText(rsn.dQG_(this.f.getContext(), "[\\d%]", rsn.e(privacyDataModel.getInt("bloodOxygenCardKey")) + " " + UnitUtil.e(i, 2, 0), rsn.a(i), rsn.e()));
            this.d.setText(privacyDataModel.getDataDesc());
            int i2 = privacyDataModel.getInt("altitudeKey");
            if (rsn.h(i2)) {
                this.f.setText(this.f.getResources().getString(R$string.IDS_hw_health_blood_oxygen_elevation) + " " + rre.e(i2));
                this.f.setVisibility(0);
                return;
            }
            this.f.setVisibility(8);
        }
    }
}
