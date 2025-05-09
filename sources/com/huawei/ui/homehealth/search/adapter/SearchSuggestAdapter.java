package com.huawei.ui.homehealth.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import defpackage.dpf;
import defpackage.fbh;
import defpackage.koq;
import defpackage.nsy;
import java.util.List;

/* loaded from: classes9.dex */
public class SearchSuggestAdapter extends RecyclerView.Adapter<b> {
    private Context b;
    private String d;
    private List<String> e;

    public SearchSuggestAdapter(Context context, List<String> list) {
        this.b = context;
        this.e = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dgQ_, reason: merged with bridge method [inline-methods] */
    public b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(LayoutInflater.from(this.b).inflate(R.layout.search_suggest_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(b bVar, int i) {
        if (bVar == null) {
            return;
        }
        nsy.cMA_(bVar.d, i == getItemCount() + (-1) ? 8 : 0);
        if (!koq.d(this.e, i) || bVar.b == null) {
            return;
        }
        final String str = this.e.get(i);
        bVar.b.setText(dpf.Ym_(str, this.d));
        bVar.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.search.adapter.SearchSuggestAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                fbh.c(BaseApplication.getContext(), str);
                Activity activity = BaseApplication.getActivity();
                if (activity instanceof GlobalSearchActivity) {
                    ((GlobalSearchActivity) activity).d(str);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(List<String> list) {
        this.e.clear();
        this.e.addAll(list);
        notifyDataSetChanged();
    }

    public void d() {
        this.e.clear();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.e;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    static class b extends RecyclerView.ViewHolder {
        private HealthTextView b;
        private LinearLayout d;

        b(View view) {
            super(view);
            this.b = (HealthTextView) view.findViewById(R.id.search_suggest_view);
            this.d = (LinearLayout) view.findViewById(R.id.search_suggest_divider);
        }
    }
}
