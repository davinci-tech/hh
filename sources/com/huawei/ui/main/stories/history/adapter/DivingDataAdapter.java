package com.huawei.ui.main.stories.history.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportDataStaticsInfo;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import defpackage.hln;
import defpackage.koq;
import defpackage.rdw;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class DivingDataAdapter extends RecyclerView.Adapter<c> {

    /* renamed from: a, reason: collision with root package name */
    private OnItemClickListener f10295a;
    private int b;
    private Context c;
    private List<rdw> d = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public DivingDataAdapter(Context context, int i) {
        this.c = context;
        this.b = i;
        d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dJV_, reason: merged with bridge method [inline-methods] */
    public c onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.item_diving_data, viewGroup, false);
        if (i == 0 && (inflate.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) inflate.getLayoutParams();
            layoutParams.setMarginStart(inflate.getContext().getResources().getDimensionPixelSize(R.dimen._2131362625_res_0x7f0a0341));
            inflate.setLayoutParams(layoutParams);
        }
        return new c(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(c cVar, final int i) {
        if (koq.d(this.d, i)) {
            cVar.b.setText(this.d.get(i).b());
            e(cVar, i);
            a(cVar, i);
            cVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.history.adapter.DivingDataAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DivingDataAdapter.this.f10295a != null) {
                        LogUtil.a("Track_DivingDataAdapter", "onClick position is ", Integer.valueOf(i));
                        DivingDataAdapter.this.f10295a.onItemClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    private void e(c cVar, int i) {
        double d = this.d.get(i).d() / 60.0d;
        if (Math.abs(d) < 1.0E-10d) {
            cVar.e.setText("- -");
        } else {
            cVar.e.setText(UnitUtil.e(d, 1, 2));
        }
        cVar.j.setText(this.c.getString(R$string.IDS_diving_duration));
        cVar.d.setText(this.c.getString(R$string.IDS_motiontrack_detail_fm_heart_min));
    }

    private void a(c cVar, int i) {
        LogUtil.a("Track_DivingDataAdapter", "init color position = ", Integer.valueOf(i), ", color = ", Integer.valueOf(this.d.get(i).c()));
        if (this.d.get(i).e()) {
            this.b = i;
            cVar.b.setTextColor(ContextCompat.getColor(this.c, R.color._2131299238_res_0x7f090ba6));
            cVar.e.setTextColor(ContextCompat.getColor(this.c, R.color._2131299238_res_0x7f090ba6));
            cVar.j.setTextColor(ContextCompat.getColor(this.c, R.color._2131299238_res_0x7f090ba6));
            cVar.d.setTextColor(ContextCompat.getColor(this.c, R.color._2131299238_res_0x7f090ba6));
            cVar.c.setCardBackgroundColor(this.c.getColor(this.d.get(i).c()));
            return;
        }
        cVar.b.setTextColor(ContextCompat.getColor(this.c, R.color._2131299236_res_0x7f090ba4));
        cVar.e.setTextColor(ContextCompat.getColor(this.c, R.color._2131299236_res_0x7f090ba4));
        cVar.j.setTextColor(ContextCompat.getColor(this.c, R.color._2131299236_res_0x7f090ba4));
        cVar.d.setTextColor(ContextCompat.getColor(this.c, R.color._2131299236_res_0x7f090ba4));
        cVar.c.setCardBackgroundColor(this.c.getColor(R.color._2131296666_res_0x7f09019a));
    }

    private void d() {
        rdw rdwVar = new rdw(this.c.getString(R$string.IDS_freediving_tip), 0.0d, this.b == 0, R.color._2131299282_res_0x7f090bd2);
        rdw rdwVar2 = new rdw(this.c.getString(R$string.IDS_scuba_diving), 0.0d, this.b == 1, R.color._2131299282_res_0x7f090bd2);
        this.d.add(rdwVar);
        this.d.add(rdwVar2);
    }

    public void d(int i, List<HiHealthData> list, List<HiHealthData> list2) {
        if (koq.b(this.d, this.b) || koq.b(this.d, i)) {
            LogUtil.h("Track_DivingDataAdapter", "mIndex or cardPosition is out of bound");
            return;
        }
        int i2 = this.b;
        if (i != i2) {
            this.d.get(i2).b(false);
        }
        this.d.get(i).b(true);
        d(list, list2);
        notifyDataSetChanged();
    }

    private void d(List<HiHealthData> list, List<HiHealthData> list2) {
        this.d.get(0).a(a(287, list));
        this.d.get(1).a(a(291, list2));
    }

    private double a(int i, List<HiHealthData> list) {
        double d = 0.0d;
        if (koq.b(list)) {
            LogUtil.h("Track_DivingDataAdapter", "sportDataStatics is null with ", Integer.valueOf(i));
            return 0.0d;
        }
        HwSportTypeInfo d2 = hln.c(BaseApplication.e()).d(i);
        if (d2 == null) {
            LogUtil.h("Track_DivingDataAdapter", "hwSportTypeInfo is null");
            return 0.0d;
        }
        List<HwSportDataStaticsInfo> sportDataStatics = d2.getSportDataStatics();
        if (koq.b(sportDataStatics)) {
            LogUtil.h("Track_DivingDataAdapter", "sportDataStaticsList is empty ", Integer.valueOf(i));
            return 0.0d;
        }
        HwSportDataStaticsInfo hwSportDataStaticsInfo = sportDataStatics.get(0);
        if (hwSportDataStaticsInfo == null) {
            LogUtil.h("Track_DivingDataAdapter", "sportDataStatics is null with ", Integer.valueOf(i));
            return 0.0d;
        }
        for (Map.Entry<String, String> entry : hwSportDataStaticsInfo.getItemDataTypeStringMap().entrySet()) {
            if (entry.getKey() != null && entry.getValue().equals("Track_Diving_Duration_Sum")) {
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData != null) {
                        d += hiHealthData.getDouble(entry.getValue());
                    }
                }
            }
        }
        return d;
    }

    public void e(OnItemClickListener onItemClickListener) {
        this.f10295a = onItemClickListener;
    }

    class c extends RecyclerView.ViewHolder {
        private HealthTextView b;
        private HealthCardView c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView j;

        c(View view) {
            super(view);
            this.c = (HealthCardView) view.findViewById(R.id.item_diving_data_card);
            this.b = (HealthTextView) view.findViewById(R.id.diving_card_item_string);
            this.j = (HealthTextView) view.findViewById(R.id.diving_card_item_value_name);
            this.e = (HealthTextView) view.findViewById(R.id.diving_card_item_value);
            this.d = (HealthTextView) view.findViewById(R.id.diving_card_item_unit);
        }
    }
}
