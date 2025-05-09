package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.HorizontalAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.eak;
import defpackage.eej;
import defpackage.nrf;
import java.util.List;

/* loaded from: classes3.dex */
public class HorizontalAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private OnItemClickListener f2521a;
    private List<eak> b;
    private Context c;
    private int d = Integer.MAX_VALUE;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public HorizontalAdapter(Context context, List<eak> list) {
        this.c = context;
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aca_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.c).inflate(R.layout.section1_1list_02_item_02, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final int size = i % this.b.size();
        eak eakVar = this.b.get(size);
        viewHolder.i.setText(eakVar.i());
        viewHolder.b.setText(eakVar.f());
        viewHolder.b.setTextSize(1, 10.0f);
        viewHolder.d.setText(eej.agV_(eakVar.h(), eakVar.c(), this.c.getResources().getDimensionPixelSize(R.dimen._2131362869_res_0x7f0a0435)));
        viewHolder.d.setTextSize(1, 12.0f);
        String j = eakVar.j();
        if (j != null) {
            SpannableString spannableString = new SpannableString(j);
            spannableString.setSpan(new StrikethroughSpan(), 0, j.length(), 17);
            viewHolder.e.setText(spannableString);
            viewHolder.e.setTextSize(1, 10.0f);
        }
        String d = eakVar.d();
        if (!TextUtils.isEmpty(d) && d.startsWith("http")) {
            float f = (int) ((this.c.getResources().getDisplayMetrics().density * 8.0f) + 0.5f);
            nrf.cIK_(eakVar.d(), viewHolder.c, f, f, 0.0f, 0.0f);
        }
        viewHolder.f2522a.setOnClickListener(new View.OnClickListener() { // from class: ean
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HorizontalAdapter.this.abZ_(viewHolder, size, view);
            }
        });
    }

    public /* synthetic */ void abZ_(ViewHolder viewHolder, int i, View view) {
        OnItemClickListener onItemClickListener = this.f2521a;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(viewHolder.f2522a, i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public void c(List<eak> list) {
        LogUtil.a("HorizontalAdapter", "setItemData: ", Integer.valueOf(list.size()));
        this.b = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthCardView f2522a;
        HealthTextView b;
        ImageView c;
        HealthTextView d;
        HealthTextView e;
        HealthTextView i;

        public ViewHolder(View view) {
            super(view);
            this.i = (HealthTextView) view.findViewById(R.id.title);
            this.b = (HealthTextView) view.findViewById(R.id.provider);
            this.d = (HealthTextView) view.findViewById(R.id.text1);
            this.e = (HealthTextView) view.findViewById(R.id.text2);
            this.f2522a = (HealthCardView) view.findViewById(R.id.section1_1_list_02_item);
            this.c = (ImageView) view.findViewById(R.id.image);
        }
    }

    public void c(OnItemClickListener onItemClickListener) {
        this.f2521a = onItemClickListener;
    }
}
