package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.adapter.CustomActionDialogAdapter;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class CustomActionDialogAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final Context c;
    private List<b> d;

    public CustomActionDialogAdapter(Context context) {
        this.c = context;
    }

    public void c(List<String> list) {
        if (koq.b(list)) {
            return;
        }
        this.d = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.d.add(new b(it.next()));
        }
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList(0);
        if (koq.b(this.d)) {
            return arrayList;
        }
        for (b bVar : this.d) {
            if (bVar.d) {
                arrayList.add(bVar.e);
            }
        }
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dCA_, reason: merged with bridge method [inline-methods] */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(this.c).inflate(R.layout.item_dialog_custom_action, viewGroup, false));
        myViewHolder.b.setHighlightColor(this.c.getColor(R$color.blood_pressure_subtab_item_text));
        return myViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        if (koq.b(this.d, i)) {
            return;
        }
        final b bVar = this.d.get(i);
        myViewHolder.d.setText(bVar.e);
        myViewHolder.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.health.adapter.CustomActionDialogAdapter$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CustomActionDialogAdapter.dCz_(CustomActionDialogAdapter.b.this, compoundButton, z);
            }
        });
        myViewHolder.b.setChecked(bVar.d);
    }

    static /* synthetic */ void dCz_(b bVar, CompoundButton compoundButton, boolean z) {
        bVar.d = z;
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<b> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        HealthCheckBox b;
        HealthTextView d;

        public MyViewHolder(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.action_text);
            this.b = (HealthCheckBox) view.findViewById(R.id.check_box);
        }
    }

    static class b {
        private boolean d;
        private final String e;

        b(String str) {
            this.e = str;
        }
    }
}
