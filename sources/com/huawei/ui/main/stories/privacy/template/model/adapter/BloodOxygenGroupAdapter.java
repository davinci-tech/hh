package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.hidatamanager.util.LogUtils;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.koq;
import defpackage.rre;
import defpackage.rsn;
import defpackage.scg;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class BloodOxygenGroupAdapter extends GroupDataAdapter {
    private static final String e = "BloodOxygenGroupAdapter";

    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter
    public View dQv_(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_blood_oxygen_child_item, viewGroup, false);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter, android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        GroupDataAdapter.c cVar;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_list_father_item, viewGroup, false);
            cVar = new GroupDataAdapter.c(view);
            view.setTag(cVar);
        } else {
            Object tag = view.getTag();
            cVar = tag instanceof GroupDataAdapter.c ? (GroupDataAdapter.c) tag : null;
        }
        if (cVar != null) {
            HealthTextView healthTextView = cVar.c;
            if (koq.d(this.b, i)) {
                healthTextView.setText(this.b.get(i).e());
            } else {
                LogUtils.w(e, "getGroupView mGroupData isOutOfBounds");
            }
            if (z) {
                cVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
            } else {
                cVar.d.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
            }
        }
        return view;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter, android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        GroupDataAdapter.b bVar;
        int dimensionPixelOffset;
        if (view == null) {
            view = dQv_(viewGroup);
            bVar = dQu_(view);
            view.setTag(bVar);
        } else {
            Object tag = view.getTag();
            bVar = tag instanceof GroupDataAdapter.b ? (GroupDataAdapter.b) tag : null;
        }
        if (bVar != null) {
            if (koq.b(this.b, i)) {
                LogUtils.w(e, "getChildView mGroupData isOutOfBounds");
                return view;
            }
            List<PrivacyDataModel> d = this.b.get(i).d();
            if (koq.b(d, i2)) {
                LogUtils.w(e, "getChildView modelList isOutOfBounds");
                return view;
            }
            bVar.c(d.get(i2));
            if (LanguageUtil.bc(bVar.h.getContext())) {
                bVar.e.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
            if (bVar instanceof a) {
                Resources resources = bVar.h.getContext().getResources();
                a aVar = (a) bVar;
                if (aVar.n.getVisibility() == 8) {
                    dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen._2131362370_res_0x7f0a0242);
                } else {
                    dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen._2131363088_res_0x7f0a0510);
                }
                aVar.l.setMinHeight(dimensionPixelOffset);
            }
            bVar.h.setBackgroundResource(R.drawable._2131431106_res_0x7f0b0ec2);
            a(bVar, i2, i);
        }
        return view;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter
    public GroupDataAdapter.b dQu_(View view) {
        return new a(view);
    }

    class a extends GroupDataAdapter.b {
        private HealthTextView c;
        private HealthTextView i;
        private Context k;
        private ConstraintLayout l;
        private HealthTextView m;
        private HealthTextView n;
        private HealthTextView o;
        private RelativeLayout s;

        a(View view) {
            super(view);
            this.k = view.getContext();
            this.l = (ConstraintLayout) view.findViewById(R.id.privacy_data_layout);
            this.n = (HealthTextView) view.findViewById(R.id.privacy_extra_data);
            this.s = (RelativeLayout) view.findViewById(R.id.privacy_other_data_layout);
            this.o = (HealthTextView) view.findViewById(R.id.privacy_highland_risk_tab);
            this.m = (HealthTextView) view.findViewById(R.id.privacy_highland_risk_value);
            this.i = (HealthTextView) view.findViewById(R.id.privacy_blood_oxygen_value);
            this.c = (HealthTextView) view.findViewById(R.id.privacy_altitude_value);
        }

        @Override // com.huawei.ui.main.stories.privacy.template.model.adapter.GroupDataAdapter.b
        protected void c(PrivacyDataModel privacyDataModel) {
            super.c(privacyDataModel);
            int i = privacyDataModel.getInt("bloodOxygenCardKey");
            if (i == 1001) {
                a(privacyDataModel);
            } else if (i == 1002) {
                d(privacyDataModel);
            } else {
                e(privacyDataModel);
            }
        }

        private void a(PrivacyDataModel privacyDataModel) {
            this.s.setVisibility(0);
            this.b.setText(this.k.getString(R$string.IDS_hw_health_blood_oxygen_assessment));
            this.j.setText(privacyDataModel.getDataDesc());
            this.n.setVisibility(8);
            int i = privacyDataModel.getInt("lakeLouiseScoreKey");
            String b = rre.b(i);
            this.o.setText(scg.h(i));
            this.o.setBackground(rsn.dQF_(i));
            this.m.setText(rsn.dQG_(this.k, "[\\d]", b, rsn.d(i), rsn.i(i)));
            int i2 = privacyDataModel.getInt("bloodOxygenKey");
            this.i.setText(rsn.dQG_(this.k, "[\\d]", UnitUtil.e(i2, 2, 0), rsn.b(i2), rsn.c(i2)));
            int i3 = privacyDataModel.getInt("altitudeKey");
            if (i3 == Integer.MIN_VALUE) {
                this.c.setText("--");
            } else {
                this.c.setText(rsn.dQG_(this.k, "[\\d]", rre.e(i3), R.style.privacy_blood_oxygen_num_normal, R.style.privacy_blood_oxygen_unit_normal));
            }
        }

        private void d(PrivacyDataModel privacyDataModel) {
            this.s.setVisibility(8);
            int i = privacyDataModel.getInt("bloodOxygenKey");
            this.b.setText(rsn.dQG_(this.k, "[\\d%]", rsn.e(privacyDataModel.getInt("bloodOxygenCardKey")) + " " + UnitUtil.e(i, 2, 0), rsn.a(i), rsn.e()));
            this.j.setText(privacyDataModel.getDataDesc());
            int i2 = privacyDataModel.getInt("altitudeKey");
            if (rsn.h(i2)) {
                this.n.setText(this.n.getResources().getString(R$string.IDS_hw_health_blood_oxygen_elevation) + " " + rre.e(i2));
                this.n.setVisibility(0);
                return;
            }
            this.n.setVisibility(8);
        }

        private void e(PrivacyDataModel privacyDataModel) {
            this.s.setVisibility(8);
            this.b.setText(privacyDataModel.getDataTitle());
            this.j.setText(privacyDataModel.getDataDesc());
            this.n.setVisibility(8);
        }
    }
}
