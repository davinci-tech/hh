package com.huawei.ui.main.stories.privacy.template.model.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import defpackage.koq;
import defpackage.nsn;
import defpackage.rsr;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class DayDataViewAdapter extends RecyclerView.Adapter<e> {
    private static final String b = "DayDataViewAdapter";
    private OnItemClickListener d;
    private OnLongClickListener h;
    private int f = 1;
    private List<Boolean> c = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private int f10415a = 0;
    private int g = 0;
    private List<PrivacyDataModel> e = new ArrayList(10);

    public interface OnItemClickListener {
        void onItemClickListener(int i);
    }

    public interface OnLongClickListener {
        void onLongClickListener(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dQm_, reason: merged with bridge method [inline-methods] */
    public e onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.privacy_list_child_item, viewGroup, false);
        inflate.findViewById(R.id.privacy_item_rl).setPadding(0, 0, 0, 0);
        return new e(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(e eVar, int i) {
        eVar.b(this.e.get(i));
        if (LanguageUtil.bc(eVar.itemView.getContext())) {
            eVar.b.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        eVar.i.setVisibility(i == this.e.size() + (-1) ? 4 : 0);
        b(eVar, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    private void b(e eVar, final int i) {
        eVar.c.setOnClickListener(new View.OnClickListener() { // from class: rrt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DayDataViewAdapter.this.dQj_(i, view);
            }
        });
        eVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: rrq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DayDataViewAdapter.this.dQk_(i, view);
            }
        });
        eVar.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: rrz
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return DayDataViewAdapter.this.dQl_(i, view);
            }
        });
        if (this.f == 2) {
            eVar.b.setVisibility(8);
            eVar.c.setVisibility(0);
            eVar.c.setChecked(this.c.get(i).booleanValue());
        } else {
            eVar.b.setVisibility(0);
            eVar.c.setVisibility(8);
        }
    }

    public /* synthetic */ void dQj_(int i, View view) {
        a(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dQk_(int i, View view) {
        a(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ boolean dQl_(int i, View view) {
        OnLongClickListener onLongClickListener = this.h;
        if (onLongClickListener == null) {
            return true;
        }
        onLongClickListener.onLongClickListener(i);
        return true;
    }

    private void a(int i) {
        OnItemClickListener onItemClickListener = this.d;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClickListener(i);
        }
    }

    public void d(List<PrivacyDataModel> list) {
        this.e.clear();
        this.e.addAll(list);
        this.f10415a = 0;
        this.g = this.e.size();
        d();
        notifyDataSetChanged();
    }

    public PrivacyDataModel d(int i) {
        if (koq.b(this.e, i)) {
            return new PrivacyDataModel();
        }
        return this.e.get(i);
    }

    public void e(int i) {
        this.f = i;
        if (i == 1) {
            c(false);
        } else {
            notifyDataSetChanged();
        }
    }

    public void c(boolean z) {
        if (z) {
            this.f10415a = this.g;
        } else {
            this.f10415a = 0;
        }
        for (int i = 0; i < this.c.size(); i++) {
            this.c.set(i, Boolean.valueOf(z));
        }
        notifyDataSetChanged();
    }

    public void b(int i) {
        if (koq.b(this.c, i)) {
            LogUtil.c(b, "switchCheckStatus mCheckList isOutOfBounds");
            return;
        }
        if (this.c.get(i).booleanValue()) {
            this.f10415a--;
        } else {
            this.f10415a++;
        }
        this.c.set(i, Boolean.valueOf(!r0.booleanValue()));
        notifyItemChanged(i);
    }

    public List<PrivacyDataModel> b() {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(this.c)) {
            return arrayList;
        }
        for (int i = 0; i < this.c.size(); i++) {
            if (this.c.get(i).booleanValue()) {
                arrayList.add(this.e.get(i));
            }
        }
        return arrayList;
    }

    public int e() {
        return this.f10415a;
    }

    public int c() {
        return this.g;
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.d = onItemClickListener;
    }

    public void d(OnLongClickListener onLongClickListener) {
        this.h = onLongClickListener;
    }

    public void d() {
        this.c.clear();
        if (koq.b(this.e)) {
            return;
        }
        for (int i = 0; i < this.e.size(); i++) {
            this.c.add(i, false);
        }
    }

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        protected ImageView f10416a;
        protected ImageView b;
        protected HealthCheckBox c;
        protected HealthTextView d;
        protected HealthDivider i;
        protected HealthTextView j;

        e(View view) {
            super(view);
            this.f10416a = (ImageView) view.findViewById(R.id.privacy_content_icon);
            this.j = (HealthTextView) view.findViewById(R.id.privacy_content_title);
            this.d = (HealthTextView) view.findViewById(R.id.privacy_time);
            this.i = (HealthDivider) view.findViewById(R.id.data_line);
            this.b = (ImageView) view.findViewById(R.id.content_item_arrow);
            this.c = (HealthCheckBox) view.findViewById(R.id.content_item_check);
        }

        protected void b(PrivacyDataModel privacyDataModel) {
            String string = privacyDataModel.getString("iconResource");
            if (TextUtils.isEmpty(string)) {
                this.f10416a.setVisibility(8);
            } else {
                e(privacyDataModel);
                this.f10416a.setVisibility(0);
                try {
                    this.f10416a.setBackground(rsr.dQH_(Integer.parseInt(string)));
                } catch (NumberFormatException e) {
                    LogUtil.e(DayDataViewAdapter.b, e.getMessage());
                }
            }
            this.j.setText(privacyDataModel.getDataTitle());
            this.d.setText(privacyDataModel.getDataDesc());
            a();
        }

        private void e(PrivacyDataModel privacyDataModel) {
            String string = privacyDataModel.getString("iconResource");
            if (nsn.p() && !TextUtils.isEmpty(string) && privacyDataModel.getPageType() == 103) {
                this.itemView.findViewById(R.id.privacy_data_layout).setVisibility(8);
                this.itemView.findViewById(R.id.privacy_data_layout_large).setVisibility(0);
                this.f10416a = (ImageView) this.itemView.findViewById(R.id.privacy_content_icon_large);
                this.j = (HealthTextView) this.itemView.findViewById(R.id.privacy_content_title_large);
                RelativeLayout relativeLayout = (RelativeLayout) this.itemView.findViewById(R.id.time_text_relativelayout);
                relativeLayout.setVisibility(0);
                this.itemView.findViewById(R.id.time_arrow_checkbox).setVisibility(8);
                this.d = (HealthTextView) relativeLayout.findViewById(R.id.privacy_time_large);
                this.b = (ImageView) relativeLayout.findViewById(R.id.content_item_arrow_large);
                this.c = (HealthCheckBox) relativeLayout.findViewById(R.id.content_item_check_large);
            }
        }

        private void a() {
            HealthTextView healthTextView = this.j;
            if (healthTextView == null) {
                return;
            }
            healthTextView.post(new Runnable() { // from class: com.huawei.ui.main.stories.privacy.template.model.adapter.DayDataViewAdapter.e.2
                @Override // java.lang.Runnable
                public void run() {
                    if (e.this.j == null || e.this.i == null) {
                        return;
                    }
                    int left = e.this.j.getLeft();
                    ViewGroup.LayoutParams layoutParams = e.this.i.getLayoutParams();
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).setMarginStart(left);
                    }
                    e.this.i.setLayoutParams(layoutParams);
                }
            });
        }
    }
}
