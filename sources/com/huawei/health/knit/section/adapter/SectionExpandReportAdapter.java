package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import defpackage.edo;
import defpackage.edp;
import defpackage.edt;
import defpackage.edx;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class SectionExpandReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<edp> f2561a;
    private Context b;
    private boolean d;
    private int e;
    private int j;
    private Set<RecyclerView.ViewHolder> i = new HashSet();
    private boolean c = false;

    public SectionExpandReportAdapter(Context context, edt edtVar, int i, boolean z) {
        this.d = false;
        this.b = context;
        this.e = edtVar.g();
        this.j = i;
        this.d = z;
        e(edtVar.d());
    }

    private void e(List<edp> list) {
        if (koq.b(list) || !VersionControlUtil.isSupportSleepManagement()) {
            this.f2561a = list;
            return;
        }
        int size = list.size();
        if (this.d) {
            if (size >= this.j) {
                list = new ArrayList(list.subList(0, this.j));
            }
            this.f2561a = list;
            return;
        }
        this.f2561a = size <= this.j ? Collections.EMPTY_LIST : new ArrayList(list.subList(this.j, size));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder expandReportExtViewHolder;
        if (i == 0) {
            expandReportExtViewHolder = new ExpandReportDetailViewHolder(LayoutInflater.from(this.b).inflate(R.layout.expand_report_recyclerview_item, viewGroup, false));
        } else if (i == 1) {
            if (nsn.t()) {
                expandReportExtViewHolder = new ExpandReportExtViewHolder(LayoutInflater.from(this.b).inflate(R.layout.sleep_detail_item_layout_large_mode, viewGroup, false));
            } else {
                expandReportExtViewHolder = new ExpandReportExtViewHolder(LayoutInflater.from(this.b).inflate(R.layout.expand_report_ext_item_layout, viewGroup, false));
            }
        } else {
            LogUtil.a("SectionExpandReportAdapter", "no match viewType");
            return null;
        }
        this.i.add(expandReportExtViewHolder);
        return expandReportExtViewHolder;
    }

    public void c(int i) {
        this.c = i != 0;
        for (RecyclerView.ViewHolder viewHolder : this.i) {
            if (!(viewHolder instanceof ExpandReportDetailViewHolder)) {
                if (!(viewHolder instanceof ExpandReportExtViewHolder)) {
                    LogUtil.b("SectionExpandReportAdapter", "changeRightArrowVisibility failed, viewHolder = ", viewHolder);
                } else {
                    nsy.cMA_(((ExpandReportExtViewHolder) viewHolder).d, i);
                }
            } else {
                nsy.cMA_(((ExpandReportDetailViewHolder) viewHolder).j, i);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ExpandReportDetailViewHolder) {
            c(viewHolder, i);
        } else if (viewHolder instanceof ExpandReportExtViewHolder) {
            e(viewHolder, i);
        } else {
            LogUtil.a("SectionExpandReportAdapter", "no match viewHold");
        }
    }

    private void c(RecyclerView.ViewHolder viewHolder, int i) {
        ExpandReportDetailViewHolder expandReportDetailViewHolder = (ExpandReportDetailViewHolder) viewHolder;
        if (this.f2561a != null && i == r0.size() - 1) {
            expandReportDetailViewHolder.f2562a.setVisibility(8);
        }
        if (!koq.d(this.f2561a, i)) {
            ReleaseLogUtil.d("R_Sleep_SectionExpandReportAdapter", "is not in Bounds:", Integer.valueOf(i));
            return;
        }
        edo edoVar = (edo) this.f2561a.get(i);
        if (!edoVar.g()) {
            ReleaseLogUtil.d("R_Sleep_SectionExpandReportAdapter", "can't visible");
            return;
        }
        expandReportDetailViewHolder.b.setText(edoVar.e());
        expandReportDetailViewHolder.c.setText(edoVar.d());
        expandReportDetailViewHolder.c.setMovementMethod(LinkMovementMethod.getInstance());
        expandReportDetailViewHolder.h.setText(edoVar.i());
        expandReportDetailViewHolder.h.setTextColor(edoVar.f());
        if (TextUtils.isEmpty(edoVar.d())) {
            expandReportDetailViewHolder.c.setVisibility(8);
        }
        expandReportDetailViewHolder.j.setBackgroundResource(edoVar.h());
        nsy.cMA_(expandReportDetailViewHolder.j, this.c ? 8 : 0);
        LinearLayout linearLayout = expandReportDetailViewHolder.e;
        LinearLayout linearLayout2 = expandReportDetailViewHolder.d;
        if (linearLayout == null || linearLayout2 == null) {
            return;
        }
        linearLayout.setOnClickListener(edoVar.agE_());
        if (i == 0 && this.e == edoVar.c() && edoVar.b()) {
            linearLayout.setBackgroundResource(R.drawable.button_background_sleep_top_abnormal);
            expandReportDetailViewHolder.f2562a.setVisibility(8);
            linearLayout2.setPadding(0, nrr.e(this.b, 12.0f), 0, 0);
        }
    }

    private void e(RecyclerView.ViewHolder viewHolder, int i) {
        ExpandReportExtViewHolder expandReportExtViewHolder = (ExpandReportExtViewHolder) viewHolder;
        if (koq.d(this.f2561a, i)) {
            edx edxVar = (edx) this.f2561a.get(i);
            expandReportExtViewHolder.e.setText(edxVar.d());
            expandReportExtViewHolder.c.setText(edxVar.j());
            if (edxVar.a() != -1) {
                expandReportExtViewHolder.d.setBackgroundResource(edxVar.a());
                nsy.cMA_(expandReportExtViewHolder.d, 0);
                nsy.cMA_(expandReportExtViewHolder.d, this.c ? 8 : 0);
            } else {
                nsy.cMA_(expandReportExtViewHolder.d, 8);
            }
            LinearLayout linearLayout = expandReportExtViewHolder.b;
            if (linearLayout != null) {
                linearLayout.setOnClickListener(edxVar.agG_());
                if (i == 0 && this.e == edxVar.c() && edxVar.b()) {
                    linearLayout.setBackgroundColor(BaseApplication.getContext().getResources().getColor(R.color._2131296367_res_0x7f09006f));
                }
            }
        }
        if (this.f2561a == null || i != r0.size() - 1) {
            return;
        }
        expandReportExtViewHolder.f2563a.setVisibility(8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return !(this.f2561a.get(i) instanceof edo) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<edp> list = this.f2561a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class ExpandReportDetailViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f2562a;
        private TextView b;
        private TextView c;
        private LinearLayout d;
        private LinearLayout e;
        private TextView h;
        private ImageView j;

        public ExpandReportDetailViewHolder(View view) {
            super(view);
            this.e = (LinearLayout) view.findViewById(R.id.total_sleep_layout_item);
            this.d = (LinearLayout) view.findViewById(R.id.expand_report_recyclerview_1_ll);
            this.b = (TextView) view.findViewById(R.id.item_left_top_text);
            this.c = (TextView) view.findViewById(R.id.item_left_bottom_text);
            this.h = (TextView) view.findViewById(R.id.item_right_text);
            this.j = (ImageView) view.findViewById(R.id.item_right_icon);
            this.f2562a = (HealthDivider) view.findViewById(R.id.recyclerview_divider_1);
        }
    }

    public static class ExpandReportExtViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f2563a;
        private LinearLayout b;
        private TextView c;
        private ImageView d;
        private TextView e;

        public ExpandReportExtViewHolder(View view) {
            super(view);
            this.b = (LinearLayout) view.findViewById(R.id.expand_report_ext_item_root_view);
            this.e = (TextView) view.findViewById(R.id.expand_report_ext_item_left_text);
            this.c = (TextView) view.findViewById(R.id.expand_report_ext_item_right_text);
            this.d = (ImageView) view.findViewById(R.id.expand_report_ext_item_right_image);
            this.f2563a = (HealthDivider) view.findViewById(R.id.expand_report_ext_item_divider);
        }
    }
}
