package com.huawei.ui.homehealth.runcard.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import defpackage.cad;

/* loaded from: classes6.dex */
public class PaceRangeDistanceAdapter extends RecyclerView.Adapter<a> {

    /* renamed from: a, reason: collision with root package name */
    private SparseArray<cad> f9537a;
    private LayoutInflater c;
    private OnItemClickListener e;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public PaceRangeDistanceAdapter(Context context, SparseArray<cad> sparseArray) {
        this.f9537a = sparseArray;
        this.c = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dfw_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this.c.inflate(R.layout.custom_list_item_single_choice, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, int i) {
        SparseArray<cad> sparseArray;
        if (aVar == null || (sparseArray = this.f9537a) == null) {
            LogUtil.h("Track_PaceRangeDistanceAdapter", "onBindViewHolder holder null or mDistanceList null");
            return;
        }
        boolean z = i != sparseArray.size() - 1;
        int keyAt = this.f9537a.keyAt(i);
        aVar.e(this.f9537a.get(keyAt), z);
        aVar.d(this.e, keyAt);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        SparseArray<cad> sparseArray = this.f9537a;
        if (sparseArray == null) {
            LogUtil.h("Track_PaceRangeDistanceAdapter", "mDistanceList null");
            return 0;
        }
        return sparseArray.size();
    }

    public void d(OnItemClickListener onItemClickListener) {
        this.e = onItemClickListener;
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private CheckedTextView f9538a;
        private HealthDivider c;
        private View d;

        a(View view) {
            super(view);
            this.d = view;
            this.f9538a = (CheckedTextView) view.findViewById(R.id.text1);
            this.c = (HealthDivider) view.findViewById(R.id.divide_line);
        }

        public void e(cad cadVar, boolean z) {
            if (cadVar == null) {
                LogUtil.h("Track_PaceRangeDistanceAdapter", "onBindDataForView distanceEntity null");
                return;
            }
            this.f9538a.setChecked(cadVar.f());
            this.f9538a.setText(cadVar.a());
            if (z) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
        }

        public void d(final OnItemClickListener onItemClickListener, final int i) {
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.adapter.PaceRangeDistanceAdapter.a.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OnItemClickListener onItemClickListener2 = onItemClickListener;
                    if (onItemClickListener2 != null) {
                        onItemClickListener2.onItemClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }
}
