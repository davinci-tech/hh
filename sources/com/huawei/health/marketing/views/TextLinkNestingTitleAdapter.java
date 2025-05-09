package com.huawei.health.marketing.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleTextContent;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class TextLinkNestingTitleAdapter extends RecyclerView.Adapter<TitleHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final Context f2894a;
    private final List<SingleTextContent> b = new ArrayList();

    public TextLinkNestingTitleAdapter(Context context) {
        this.f2894a = context;
    }

    public void d(List<SingleTextContent> list) {
        this.b.clear();
        if (list != null) {
            this.b.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<SingleTextContent> c() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: aqj_, reason: merged with bridge method [inline-methods] */
    public TitleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TitleHolder(LayoutInflater.from(this.f2894a).inflate(R.layout.text_link_nesting_title_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(TitleHolder titleHolder, int i) {
        SingleTextContent singleTextContent;
        if (koq.b(this.b, i) || (singleTextContent = this.b.get(i)) == null) {
            return;
        }
        titleHolder.c(singleTextContent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public static final class TitleHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f2895a;

        public TitleHolder(View view) {
            super(view);
            this.f2895a = (HealthTextView) view.findViewById(R.id.title);
        }

        public void c(SingleTextContent singleTextContent) {
            this.f2895a.setText(singleTextContent.getTheme());
            this.itemView.setOnClickListener(singleTextContent.getOnClickListener());
            b(singleTextContent.isSelected());
        }

        public void b(boolean z) {
            Context context = this.itemView.getContext();
            this.f2895a.setTextColor(ContextCompat.getColor(context, z ? R.color._2131296927_res_0x7f09029f : R.color._2131299236_res_0x7f090ba4));
            this.f2895a.setBackground(ContextCompat.getDrawable(context, z ? R.drawable.text_link_nesting_title_background_selected : R.drawable.text_link_nesting_title_background_unselected));
        }
    }
}
