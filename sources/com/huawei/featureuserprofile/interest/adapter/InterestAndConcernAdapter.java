package com.huawei.featureuserprofile.interest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.jcf;
import defpackage.nsf;
import java.util.List;

/* loaded from: classes7.dex */
public class InterestAndConcernAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f1986a;
    private OnCheckBoxClickListener b;
    private List<Boolean> c;
    private Context d;
    private List<String> e;

    public interface OnCheckBoxClickListener {
        void onCheckBoxClick(boolean z, int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public InterestAndConcernAdapter(Context context, List<String> list, List<String> list2, List<Boolean> list3) {
        this.d = context;
        this.f1986a = list;
        this.e = list2;
        this.c = list3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: tP_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.d).inflate(R.layout.adapter_interstadnconcern_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.c.setText(this.e.get(i));
        viewHolder.f.setChecked(this.c.get(i).booleanValue());
        viewHolder.f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.featureuserprofile.interest.adapter.InterestAndConcernAdapter$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                InterestAndConcernAdapter.this.tO_(i, compoundButton, z);
            }
        });
        d(this.c.get(i).booleanValue(), viewHolder, i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.interest.adapter.InterestAndConcernAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (jcf.c()) {
                    if (InterestAndConcernAdapter.this.b != null) {
                        boolean booleanValue = ((Boolean) InterestAndConcernAdapter.this.c.get(i)).booleanValue();
                        boolean z = !booleanValue;
                        viewHolder.f.setChecked(z);
                        InterestAndConcernAdapter.this.b.onCheckBoxClick(z, i);
                        InterestAndConcernAdapter.this.d(z, viewHolder, i);
                        if (!booleanValue) {
                            jcf.bEk_(viewHolder.itemView.findViewById(R.id.interest_preference_item_linear), nsf.b(R.string._2130847330_res_0x7f022662, InterestAndConcernAdapter.this.e.get(i)));
                        } else {
                            jcf.bEk_(viewHolder.itemView.findViewById(R.id.interest_preference_item_linear), nsf.b(R.string._2130850674_res_0x7f023372, InterestAndConcernAdapter.this.e.get(i)));
                        }
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (i == 0 || i == this.f1986a.size()) {
            if (i == 0) {
                viewHolder.g.setVisibility(0);
                viewHolder.g.setSubHeaderBackgroundColor(this.d.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
            }
            viewHolder.b.setBackgroundResource(R.drawable.list_item_background_top_normal);
        }
        if (i == this.f1986a.size()) {
            viewHolder.f1987a.setVisibility(0);
            viewHolder.f1987a.setSubHeaderBackgroundColor(this.d.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        }
        if (i == this.f1986a.size() - 1 || i == this.e.size() - 1) {
            if (i == this.e.size() - 1) {
                viewHolder.e.setVisibility(0);
            }
            viewHolder.b.setBackgroundResource(R.drawable.list_item_background_bottom_normal);
            viewHolder.d.setVisibility(8);
        }
    }

    /* synthetic */ void tO_(int i, CompoundButton compoundButton, boolean z) {
        OnCheckBoxClickListener onCheckBoxClickListener = this.b;
        if (onCheckBoxClickListener != null) {
            onCheckBoxClickListener.onCheckBoxClick(z, i);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthSubHeader f1987a;
        LinearLayout b;
        HealthTextView c;
        HealthDivider d;
        LinearLayout e;
        HealthCheckBox f;
        HealthSubHeader g;

        public ViewHolder(View view) {
            super(view);
            this.b = (LinearLayout) view.findViewById(R.id.interest_preference_item_linear);
            this.c = (HealthTextView) view.findViewById(R.id.interest_preference_item_healthtextview);
            this.f = (HealthCheckBox) view.findViewById(R.id.interest_user_info_interest_item_checkbox);
            this.f1987a = (HealthSubHeader) view.findViewById(R.id.interest_health_concerns_item_healthsubheader);
            this.g = (HealthSubHeader) view.findViewById(R.id.interest_sport_interest_item_healthsunheader);
            this.e = (LinearLayout) view.findViewById(R.id.interest_prompt_text_item_linear);
            this.d = (HealthDivider) view.findViewById(R.id.interest_item_healthdivider);
        }
    }

    public void c(OnCheckBoxClickListener onCheckBoxClickListener) {
        this.b = onCheckBoxClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, ViewHolder viewHolder, int i) {
        if (z) {
            jcf.bEK_(viewHolder.itemView.findViewById(R.id.interest_preference_item_linear), this.e.get(i), true, CheckBox.class);
        } else {
            jcf.bEK_(viewHolder.itemView.findViewById(R.id.interest_preference_item_linear), nsf.b(R.string._2130850674_res_0x7f023372, this.e.get(i)), false, CheckBox.class);
        }
    }
}
