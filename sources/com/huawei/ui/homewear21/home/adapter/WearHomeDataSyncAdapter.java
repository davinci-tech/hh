package com.huawei.ui.homewear21.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.pef;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeDataSyncAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9654a;
    private WearHomeListener d;
    private List<pef> e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return 0;
    }

    public WearHomeDataSyncAdapter(Context context, List<pef> list) {
        this.f9654a = context;
        this.e = list;
    }

    public void a(WearHomeListener wearHomeListener) {
        this.d = wearHomeListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DataSyncHolder(LayoutInflater.from(this.f9654a).inflate(R.layout.activity_device_data_sync_image_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= this.e.size()) {
            LogUtil.h("WearHomeDataSyncAdapter", "onBindViewHolder position Out Of Bounds");
        } else if (!(viewHolder instanceof DataSyncHolder)) {
            LogUtil.h("WearHomeDataSyncAdapter", "onBindViewHolder viewHolder not instanceof DataSyncHolder");
        } else {
            d((DataSyncHolder) viewHolder, i);
        }
    }

    private void d(DataSyncHolder dataSyncHolder, final int i) {
        final pef pefVar = this.e.get(i);
        dataSyncHolder.b.setImageDrawable(pefVar.dmo_());
        dataSyncHolder.c.setText(pefVar.c());
        if (LanguageUtil.bc(this.f9654a)) {
            dataSyncHolder.d.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            dataSyncHolder.d.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        if (i == this.e.size() - 1) {
            dataSyncHolder.e.setVisibility(8);
        }
        if (pefVar.e()) {
            dataSyncHolder.d.setVisibility(8);
            dataSyncHolder.h.setVisibility(0);
            dataSyncHolder.h.setChecked(pefVar.b());
            dataSyncHolder.h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homewear21.home.adapter.WearHomeDataSyncAdapter.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    LogUtil.a("WearHomeDataSyncAdapter", "onCheckedChanged isChecked:", Boolean.valueOf(z));
                    pefVar.c(z);
                    if (WearHomeDataSyncAdapter.this.d != null) {
                        WearHomeDataSyncAdapter.this.d.onItemClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            });
            return;
        }
        dataSyncHolder.d.setVisibility(0);
        dataSyncHolder.h.setVisibility(8);
        dataSyncHolder.f9657a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homewear21.home.adapter.WearHomeDataSyncAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.a(800)) {
                    if (WearHomeDataSyncAdapter.this.d != null) {
                        WearHomeDataSyncAdapter.this.d.onItemClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public static class DataSyncHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private RelativeLayout f9657a;
        private ImageView b;
        private HealthTextView c;
        private ImageView d;
        private View e;
        private HealthSwitchButton h;

        public DataSyncHolder(View view) {
            super(view);
            this.b = (ImageView) nsy.cMd_(view, R.id.data_sync_item_icon);
            this.f9657a = (RelativeLayout) nsy.cMd_(view, R.id.data_sync_device_relative);
            this.c = (HealthTextView) nsy.cMd_(view, R.id.data_sync_app_name);
            this.d = (ImageView) nsy.cMd_(view, R.id.data_sync_arrow_icon);
            this.h = (HealthSwitchButton) nsy.cMd_(view, R.id.data_sync_switch_button);
            this.e = nsy.cMd_(view, R.id.data_sync_item_line);
        }
    }
}
