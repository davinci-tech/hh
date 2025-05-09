package com.huawei.ui.homewear21.home.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.nsy;
import defpackage.pbr;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeGeneralCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater c;
    private List<pbr> d;
    private WearHomeListener e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public WearHomeGeneralCardAdapter(Context context, List<pbr> list) {
        this.d = list;
        this.c = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WearHomeGeneralCardViewHolder(this.c.inflate(R.layout.wear_home_general_card_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.a("WearHomeGeneralCardAdapter", "onBindViewHolder mWearHomeGeneralBeans.size() = ", Integer.valueOf(this.d.size()));
        if (i < this.d.size() && (viewHolder instanceof WearHomeGeneralCardViewHolder)) {
            b((WearHomeGeneralCardViewHolder) viewHolder, i);
        }
    }

    private void b(WearHomeGeneralCardViewHolder wearHomeGeneralCardViewHolder, final int i) {
        pbr pbrVar = this.d.get(i);
        if (pbrVar.g()) {
            wearHomeGeneralCardViewHolder.f.setVisibility(0);
        } else {
            wearHomeGeneralCardViewHolder.f.setVisibility(8);
        }
        wearHomeGeneralCardViewHolder.e.setText(pbrVar.e());
        if (TextUtils.isEmpty(pbrVar.d())) {
            wearHomeGeneralCardViewHolder.f9666a.setVisibility(8);
        } else {
            wearHomeGeneralCardViewHolder.f9666a.setVisibility(0);
            wearHomeGeneralCardViewHolder.f9666a.setText(pbrVar.d());
        }
        if (!TextUtils.isEmpty(pbrVar.c())) {
            wearHomeGeneralCardViewHolder.c.setVisibility(0);
            wearHomeGeneralCardViewHolder.c.setText(pbrVar.c());
        } else {
            wearHomeGeneralCardViewHolder.c.setVisibility(8);
        }
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            wearHomeGeneralCardViewHolder.b.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            wearHomeGeneralCardViewHolder.b.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        if (pbrVar.b() == 65 || pbrVar.b() == 55) {
            wearHomeGeneralCardViewHolder.d.setEnabled(true);
            wearHomeGeneralCardViewHolder.d.setAlpha(1.0f);
        } else {
            wearHomeGeneralCardViewHolder.d.setEnabled(pbrVar.a());
            if (pbrVar.a()) {
                wearHomeGeneralCardViewHolder.d.setAlpha(1.0f);
            } else {
                wearHomeGeneralCardViewHolder.d.setAlpha(0.38f);
            }
        }
        wearHomeGeneralCardViewHolder.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homewear21.home.adapter.WearHomeGeneralCardAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WearHomeGeneralCardAdapter.this.e != null) {
                    WearHomeGeneralCardAdapter.this.e.onItemClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void d(List<pbr> list) {
        this.d = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    public void e(WearHomeListener wearHomeListener) {
        this.e = wearHomeListener;
    }

    public static class WearHomeGeneralCardViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9666a;
        ImageView b;
        HealthTextView c;
        LinearLayout d;
        HealthTextView e;
        View f;

        public WearHomeGeneralCardViewHolder(View view) {
            super(view);
            this.f = nsy.cMd_(view, R.id.update_red_hot);
            this.d = (LinearLayout) nsy.cMd_(view, R.id.general_card);
            this.e = (HealthTextView) nsy.cMd_(view, R.id.description);
            this.f9666a = (HealthTextView) nsy.cMd_(view, R.id.concretely_description);
            this.c = (HealthTextView) nsy.cMd_(view, R.id.status);
            this.b = (ImageView) nsy.cMd_(view, R.id.icon);
        }
    }
}
