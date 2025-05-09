package com.huawei.health.suggestion.ui.run.holder;

import android.view.View;
import android.widget.CheckedTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.divider.HealthDivider;

/* loaded from: classes4.dex */
public class RadioViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private View f3368a;
    private HealthDivider d;
    private CheckedTextView e;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public RadioViewHolder(View view) {
        super(view);
        this.f3368a = view;
        this.e = (CheckedTextView) view.findViewById(R.id.choiceRadio);
        this.d = (HealthDivider) view.findViewById(R.id.divide_line);
    }

    public void d(String str, boolean z, boolean z2) {
        this.e.setChecked(z);
        this.e.setText(str);
        if (z2) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
    }

    public void d(final OnItemClickListener onItemClickListener, final int i) {
        this.f3368a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.holder.RadioViewHolder.3
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
