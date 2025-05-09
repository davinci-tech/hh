package com.huawei.health.knit.section.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.eby;
import defpackage.eds;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionListRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private eby b;
    private List<eds> d;

    public SectionListRecordAdapter(eby ebyVar) {
        LogUtil.a("SectionListRecordAdapter", "SectionListRecordAdapter");
        this.b = ebyVar;
    }

    public void e(List<eds> list) {
        LogUtil.a("SectionListRecordAdapter", "setData");
        this.d = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("SectionListRecordAdapter", "onCreateViewHolder");
        if (i == -3) {
            if (nsn.p()) {
                return new SectionRiskRecordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_list_record_item_large_01, viewGroup, false));
            }
            return new SectionRiskRecordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_list_record_item_01, viewGroup, false));
        }
        if (i == -2) {
            if (nsn.r()) {
                return new SectionOxygenRecordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_list_record_item_large_02, viewGroup, false));
            }
            return new SectionOxygenRecordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_list_record_item_02, viewGroup, false));
        }
        if (i == -1) {
            if (nsn.r()) {
                return new SectionAltitudeRecordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_list_record_item_large_03, viewGroup, false));
            }
            return new SectionAltitudeRecordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_list_record_item_03, viewGroup, false));
        }
        LogUtil.a("SectionListRecordAdapter", "fail to create viewHolder");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("SectionListRecordAdapter", "onBindViewHolder");
        List<eds> list = this.d;
        if (list == null || list.size() == 0 || i < 0) {
            return;
        }
        eds edsVar = this.d.get(i);
        if (viewHolder instanceof SectionRiskRecordViewHolder) {
            d((SectionRiskRecordViewHolder) viewHolder, edsVar, i);
        }
        if (viewHolder instanceof SectionOxygenRecordViewHolder) {
            d((SectionOxygenRecordViewHolder) viewHolder, edsVar, i);
        }
        if (viewHolder instanceof SectionAltitudeRecordViewHolder) {
            b((SectionAltitudeRecordViewHolder) viewHolder, edsVar, i);
        }
    }

    private void d(SectionRiskRecordViewHolder sectionRiskRecordViewHolder, eds edsVar, final int i) {
        LogUtil.a("SectionListRecordAdapter", "setViewHolderValue_01");
        sectionRiskRecordViewHolder.l.setText(String.valueOf(this.b.f()));
        sectionRiskRecordViewHolder.i.setText(String.valueOf(edsVar.f()));
        sectionRiskRecordViewHolder.g.setImageResource(this.b.j().intValue());
        sectionRiskRecordViewHolder.d.setText(String.valueOf(this.b.b()));
        sectionRiskRecordViewHolder.c.setBackground(edsVar.agz_());
        nsy.cMr_(sectionRiskRecordViewHolder.c, String.valueOf(edsVar.j()));
        sectionRiskRecordViewHolder.e.setText(edsVar.agA_());
        sectionRiskRecordViewHolder.e.setTextColor(edsVar.g());
        sectionRiskRecordViewHolder.f2574a.setText(String.valueOf(this.b.h()));
        sectionRiskRecordViewHolder.j.setText(String.valueOf(edsVar.d()));
        sectionRiskRecordViewHolder.j.setTextColor(edsVar.a());
        sectionRiskRecordViewHolder.h.setText("");
        sectionRiskRecordViewHolder.h.setTextColor(edsVar.a());
        sectionRiskRecordViewHolder.f.setText(String.valueOf(this.b.i()));
        sectionRiskRecordViewHolder.o.setText(edsVar.agy_());
        final OnClickSectionListener a2 = this.b.a();
        if (sectionRiskRecordViewHolder.b == null || a2 == null) {
            return;
        }
        sectionRiskRecordViewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionListRecordAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a2.onClick(i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d(SectionOxygenRecordViewHolder sectionOxygenRecordViewHolder, eds edsVar, final int i) {
        LogUtil.a("SectionListRecordAdapter", "setViewHolderValue_02");
        sectionOxygenRecordViewHolder.b.setText(String.valueOf(this.b.d()));
        sectionOxygenRecordViewHolder.f2573a.setText(String.valueOf(edsVar.c()));
        sectionOxygenRecordViewHolder.f2573a.setTextColor(edsVar.a());
        sectionOxygenRecordViewHolder.d.setText(String.valueOf(edsVar.f()));
        sectionOxygenRecordViewHolder.e.setImageResource(this.b.e().intValue());
        final OnClickSectionListener a2 = this.b.a();
        if (sectionOxygenRecordViewHolder.c == null || a2 == null) {
            return;
        }
        sectionOxygenRecordViewHolder.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionListRecordAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a2.onClick(i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(SectionAltitudeRecordViewHolder sectionAltitudeRecordViewHolder, eds edsVar, final int i) {
        LogUtil.a("SectionListRecordAdapter", "setViewHolderValue_03");
        sectionAltitudeRecordViewHolder.f2572a.setText(String.valueOf(this.b.d()));
        sectionAltitudeRecordViewHolder.d.setText(String.valueOf(edsVar.c()));
        sectionAltitudeRecordViewHolder.d.setTextColor(edsVar.a());
        sectionAltitudeRecordViewHolder.c.setText(String.valueOf(this.b.c()));
        sectionAltitudeRecordViewHolder.e.setText(String.valueOf(edsVar.b()));
        sectionAltitudeRecordViewHolder.h.setText(String.valueOf(edsVar.f()));
        sectionAltitudeRecordViewHolder.f.setImageResource(this.b.e().intValue());
        final OnClickSectionListener a2 = this.b.a();
        if (sectionAltitudeRecordViewHolder.b == null || a2 == null) {
            return;
        }
        sectionAltitudeRecordViewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionListRecordAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a2.onClick(i);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        LogUtil.a("SectionListRecordAdapter", "getItemViewType");
        List<eds> list = this.d;
        if (list == null || list.size() == 0 || i < 0) {
            LogUtil.a("SectionListRecordAdapter", "position is invalid");
            return -4;
        }
        if (this.d.get(i).l() == 0) {
            return -3;
        }
        if (this.d.get(i).l() == 1) {
            return -2;
        }
        return this.d.get(i).l() == 2 ? -1 : -4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<eds> list = this.d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class SectionRiskRecordViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2574a;
        private RelativeLayout b;
        private HealthTextView c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView f;
        private ImageView g;
        private HealthTextView h;
        private HealthTextView i;
        private HealthTextView j;
        private HealthTextView l;
        private HealthTextView o;

        public SectionRiskRecordViewHolder(View view) {
            super(view);
            this.l = (HealthTextView) view.findViewById(R.id.privacy_content_title);
            this.i = (HealthTextView) view.findViewById(R.id.privacy_time);
            this.g = (ImageView) view.findViewById(R.id.content_item_arrow);
            this.d = (HealthTextView) view.findViewById(R.id.privacy_highland_risk);
            this.c = (HealthTextView) view.findViewById(R.id.section_button);
            this.e = (HealthTextView) view.findViewById(R.id.privacy_highland_risk_value);
            this.f2574a = (HealthTextView) view.findViewById(R.id.privacy_blood_oxygen);
            this.j = (HealthTextView) view.findViewById(R.id.privacy_blood_oxygen_value);
            this.h = (HealthTextView) view.findViewById(R.id.privacy_blood_oxygen_unit);
            this.f = (HealthTextView) view.findViewById(R.id.privacy_altitude_title);
            this.o = (HealthTextView) view.findViewById(R.id.privacy_altitude_value);
            this.b = (RelativeLayout) view.findViewById(R.id.privacy_item_content_rl);
        }
    }

    public static class SectionOxygenRecordViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2573a;
        private HealthTextView b;
        private LinearLayout c;
        private HealthTextView d;
        private ImageView e;

        public SectionOxygenRecordViewHolder(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.oxygen_history_item_instructions);
            this.f2573a = (HealthTextView) view.findViewById(R.id.oxygen_history_item_percent);
            this.d = (HealthTextView) view.findViewById(R.id.oxygen_right_time);
            this.e = (ImageView) view.findViewById(R.id.oxygen_right_icon);
            this.c = (LinearLayout) view.findViewById(R.id.oxygen_history_root_layout);
            SectionListRecordAdapter.adE_(this.b, 14);
        }
    }

    public static class SectionAltitudeRecordViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2572a;
        private LinearLayout b;
        private HealthTextView c;
        private HealthTextView d;
        private HealthTextView e;
        private ImageView f;
        private HealthTextView h;

        public SectionAltitudeRecordViewHolder(View view) {
            super(view);
            this.f2572a = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_item_instructions);
            this.d = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_item_percent);
            this.c = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_altitude);
            this.e = (HealthTextView) view.findViewById(R.id.blood_oxygen_history_height);
            this.h = (HealthTextView) view.findViewById(R.id.right_oxygen_time);
            this.f = (ImageView) view.findViewById(R.id.right_oxygen_icon);
            this.b = (LinearLayout) view.findViewById(R.id.blood_oxygen_history_root_layout);
            SectionListRecordAdapter.adE_(this.f2572a, 14);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void adE_(TextView textView, int i) {
        if (LanguageUtil.b(BaseApplication.e()) && CommonUtil.az()) {
            textView.setTextSize(2, i);
        }
    }
}
