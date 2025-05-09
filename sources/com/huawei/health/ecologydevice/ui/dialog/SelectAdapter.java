package com.huawei.health.ecologydevice.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.dialog.SelectAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SelectAdapter extends RecyclerView.Adapter<e> {

    /* renamed from: a, reason: collision with root package name */
    private final OnItemClickListener f2314a;
    private final Context b;
    private List<Map.Entry<String, String>> d;
    private String e;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SelectAdapter(Context context, List<Map.Entry<String, String>> list, OnItemClickListener onItemClickListener) {
        if (koq.c(list)) {
            this.d = list;
            this.e = list.get(0).getKey();
        } else {
            this.d = new ArrayList(16);
        }
        if (context == null) {
            this.b = BaseApplication.getContext();
        } else {
            this.b = context;
        }
        this.f2314a = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: TZ_, reason: merged with bridge method [inline-methods] */
    public e onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new e(LayoutInflater.from(this.b).inflate(R.layout.dialog_custom_select_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(e eVar, int i) {
        if (koq.b(this.d, i)) {
            LogUtil.h("SelectAdapter", "onBindViewHolder isOutOfBounds");
            return;
        }
        final String key = this.d.get(i).getKey();
        eVar.f2315a.setVisibility(i == this.d.size() + (-1) ? 8 : 0);
        eVar.e.setText(this.d.get(i).getValue());
        eVar.b.setChecked(key.equals(this.e));
        eVar.b.setOnClickListener(new View.OnClickListener() { // from class: dfl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectAdapter.this.TY_(key, view);
            }
        });
    }

    public /* synthetic */ void TY_(String str, View view) {
        if (!str.equals(this.e)) {
            this.f2314a.onItemClick(str);
            this.e = str;
            notifyDataSetChanged();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        View f2315a;
        HealthRadioButton b;
        HealthTextView e;

        e(View view) {
            super(view);
            this.f2315a = view.findViewById(R.id.divider_custom_select_item);
            this.e = (HealthTextView) view.findViewById(R.id.tv_name);
            this.b = (HealthRadioButton) view.findViewById(R.id.rb_select);
        }
    }
}
