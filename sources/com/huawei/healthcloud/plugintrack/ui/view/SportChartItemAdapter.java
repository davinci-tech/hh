package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.view.CustomTrackChartTitleBar;
import com.huawei.healthcloud.plugintrack.ui.view.SportChartItemAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.button.HealthButton;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class SportChartItemAdapter extends RecyclerView.Adapter<a> {

    /* renamed from: a, reason: collision with root package name */
    private final Context f3796a;
    private IBaseResponseCallback b;
    private List<CustomTrackChartTitleBar.e> d = new ArrayList();

    public SportChartItemAdapter(Context context) {
        this.f3796a = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: bjt_, reason: merged with bridge method [inline-methods] */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(LayoutInflater.from(this.f3796a).inflate(R.layout.layout_sport_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(a aVar, final int i) {
        List<CustomTrackChartTitleBar.e> list = this.d;
        if (list == null || i >= list.size()) {
            aVar.c.setVisibility(4);
            return;
        }
        CustomTrackChartTitleBar.e eVar = this.d.get(i);
        aVar.c.setText(eVar.c);
        a(i, aVar.c);
        if (eVar.e || (eVar.b && eVar.f3778a)) {
            aVar.c.setTextColor(this.f3796a.getResources().getColor(R.color._2131296559_res_0x7f09012f));
            aVar.c.setBackgroundResource(R.drawable._2131427677_res_0x7f0b015d);
        } else if (eVar.b) {
            aVar.c.setTextColor(this.f3796a.getResources().getColor(R$color.textColorTertiary));
            aVar.c.setBackgroundResource(R.drawable._2131427676_res_0x7f0b015c);
        }
        if (eVar.e) {
            return;
        }
        aVar.c.setOnClickListener(new View.OnClickListener() { // from class: hls
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportChartItemAdapter.this.bjs_(i, view);
            }
        });
    }

    public /* synthetic */ void bjs_(int i, View view) {
        this.b.d(i, null);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int i, HealthButton healthButton) {
        float dimension;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, nsn.c(this.f3796a, 28.0f));
        if (this.d.size() - 1 > i) {
            dimension = this.f3796a.getResources().getDimension(R.dimen._2131362564_res_0x7f0a0304);
        } else {
            dimension = this.f3796a.getResources().getDimension(R.dimen._2131362563_res_0x7f0a0303);
        }
        layoutParams.setMargins(0, 0, (int) dimension, (int) this.f3796a.getResources().getDimension(R.dimen._2131364542_res_0x7f0a0abe));
        healthButton.setLayoutParams(layoutParams);
        healthButton.setMinWidth(0);
        healthButton.setMinHeight(0);
        healthButton.setTextSize(0, this.f3796a.getResources().getDimensionPixelSize(R.dimen._2131365063_res_0x7f0a0cc7));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size() + 1;
    }

    public void a(List<CustomTrackChartTitleBar.e> list, IBaseResponseCallback iBaseResponseCallback) {
        this.d = list;
        this.b = iBaseResponseCallback;
        notifyDataSetChanged();
    }

    static final class a extends RecyclerView.ViewHolder {
        HealthButton c;

        public a(View view) {
            super(view);
            this.c = (HealthButton) view.findViewById(R.id.recycle_button);
        }
    }
}
