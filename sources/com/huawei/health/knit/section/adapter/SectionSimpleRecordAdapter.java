package com.huawei.health.knit.section.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dqj;
import defpackage.ecd;
import defpackage.eiv;
import defpackage.koq;
import defpackage.nsy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class SectionSimpleRecordAdapter extends RecyclerView.Adapter<SectionSimpleRecordHolder> {

    /* renamed from: a, reason: collision with root package name */
    private int f2592a;
    private Set<String> e = new HashSet();
    private List<View.OnClickListener> f;
    private List<String> g;
    private int h;
    private static final String c = BaseApplication.e().getString(R$string.IDS_hw_personal_cetenr_help_customer_service);
    private static final String b = BaseApplication.e().getString(R$string.IDS_vip_redeem_membership_code);
    private static final String d = BaseApplication.e().getString(R$string.IDS_vip_auto_renew_management);

    public SectionSimpleRecordAdapter(int i) {
        this.h = i;
    }

    public void b(ecd ecdVar) {
        if (ecdVar != null) {
            this.g = ecdVar.c();
            this.f = ecdVar.b();
            this.f2592a = ecdVar.e();
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aeh_, reason: merged with bridge method [inline-methods] */
    public SectionSimpleRecordHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SectionSimpleRecordHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_simple_record_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionSimpleRecordHolder sectionSimpleRecordHolder, int i) {
        LogUtil.a("SectionSimpleRecordAdapter", "SectionSimpleRecordAdapter onBindViewHolder");
        if (koq.d(this.g, i) && sectionSimpleRecordHolder.g != null) {
            sectionSimpleRecordHolder.g.setText(this.g.get(i));
        }
        if (koq.d(this.f, i) && sectionSimpleRecordHolder.h != null) {
            sectionSimpleRecordHolder.h.setOnClickListener(this.f.get(i));
        }
        nsy.cMm_(sectionSimpleRecordHolder.d, eiv.alY_());
        if (i == this.g.size() - 1) {
            sectionSimpleRecordHolder.e.setVisibility(8);
            sectionSimpleRecordHolder.f2593a.setVisibility(0);
        }
        b(sectionSimpleRecordHolder, i);
    }

    private void b(SectionSimpleRecordHolder sectionSimpleRecordHolder, int i) {
        if (koq.b(this.g, i)) {
            return;
        }
        sectionSimpleRecordHolder.c = this.g.get(i);
        ViewTreeVisibilityListener.Zy_(sectionSimpleRecordHolder.itemView, new ViewTreeVisibilityListener(sectionSimpleRecordHolder.itemView, sectionSimpleRecordHolder));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.g.size();
    }

    public class SectionSimpleRecordHolder extends RecyclerView.ViewHolder implements ViewTreeVisibilityListener.ViewTreeListenee {

        /* renamed from: a, reason: collision with root package name */
        private View f2593a;
        private String c;
        private final ImageView d;
        private HealthDivider e;
        private HealthTextView g;
        private RelativeLayout h;

        public SectionSimpleRecordHolder(View view) {
            super(view);
            this.g = (HealthTextView) view.findViewById(R.id.content_title);
            this.h = (RelativeLayout) view.findViewById(R.id.item_content_rl);
            this.e = (HealthDivider) view.findViewById(R.id.list_content_line);
            this.f2593a = view.findViewById(R.id.bottom_padding);
            this.d = (ImageView) view.findViewById(R.id.list_content_arrow_right);
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void checkVisibilityAndSetBiEvent() {
            String str;
            if (!ViewTreeVisibilityListener.Zx_(this.itemView) || hasSetBiEvent() || (str = this.c) == null) {
                return;
            }
            LogUtil.a("SectionSimpleRecordAdapter", "visible to user: ", str);
            biEvent();
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public boolean hasSetBiEvent() {
            return this.c != null && SectionSimpleRecordAdapter.this.e.contains(this.c);
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void biEvent() {
            if (TextUtils.equals(this.c, SectionSimpleRecordAdapter.c)) {
                int i = SectionSimpleRecordAdapter.this.h;
                if (i == 1) {
                    dqj.s();
                } else if (i != 2) {
                    return;
                } else {
                    dqj.m();
                }
            } else if (!TextUtils.equals(this.c, SectionSimpleRecordAdapter.b)) {
                if (!TextUtils.equals(this.c, SectionSimpleRecordAdapter.d)) {
                    return;
                } else {
                    dqj.j();
                }
            } else {
                dqj.i();
            }
            SectionSimpleRecordAdapter.this.e.add(this.c);
        }
    }
}
