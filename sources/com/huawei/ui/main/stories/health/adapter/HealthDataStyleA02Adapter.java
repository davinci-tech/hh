package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.adapter.HealthDataStyleA02Adapter;
import com.huawei.ui.main.stories.health.util.BaseHealthClickListener;
import com.huawei.uikit.hwsubheader.widget.HwSubHeader;
import defpackage.jcf;
import defpackage.nsf;

/* loaded from: classes6.dex */
public class HealthDataStyleA02Adapter extends HwSubHeader.SubHeaderRecyclerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10122a;
    private int b;
    private BaseHealthClickListener e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 1;
    }

    @Override // com.huawei.uikit.hwsubheader.widget.HwSubHeader.SubHeaderRecyclerAdapter
    public int getItemType(int i) {
        return 1;
    }

    public HealthDataStyleA02Adapter(int i, boolean z) {
        this.b = i;
        this.f10122a = z;
    }

    public void b(BaseHealthClickListener baseHealthClickListener) {
        if (baseHealthClickListener != null) {
            this.e = baseHealthClickListener;
        }
    }

    @Override // com.huawei.uikit.hwsubheader.widget.HwSubHeader.SubHeaderRecyclerAdapter
    public View getHeaderViewAsPos(int i, Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.subheader_title_more_list, (ViewGroup) null, false);
        onBindViewHolder(new c(inflate), i);
        return inflate;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subheader_title_more_list, viewGroup, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof c) {
            final c cVar = (c) viewHolder;
            Resources resources = BaseApplication.getContext().getResources();
            int i2 = this.b;
            if (i2 == 0) {
                cVar.e.setText(resources.getString(R$string.IDS_hw_health_show_healthdata_bodyfat_rate));
                cVar.e.setTextColor(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131299236_res_0x7f090ba4));
                cVar.f10123a.setBackgroundResource(this.f10122a ? R.drawable._2131430275_res_0x7f0b0b83 : R.drawable._2131430269_res_0x7f0b0b7d);
                jcf.bEz_(cVar.f10123a, nsf.h(this.f10122a ? R$string.accessibility_close : R$string.IDS_contact_add));
                cVar.f10123a.setOnClickListener(new View.OnClickListener() { // from class: qgf
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HealthDataStyleA02Adapter.this.dCC_(cVar, view);
                    }
                });
            } else if (i2 == 1) {
                cVar.e.setText(resources.getString(R$string.IDS_hw_health_show_pulse_heart_bmp));
                cVar.f10123a.setOnClickListener(new View.OnClickListener() { // from class: qge
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HealthDataStyleA02Adapter.this.dCD_(cVar, view);
                    }
                });
            } else {
                LogUtil.h("HealthDataStyleA02Adapter", "other type");
            }
            cVar.d.setVisibility(8);
        }
    }

    public /* synthetic */ void dCC_(c cVar, View view) {
        this.e.setClickAdd();
        cVar.f10123a.setBackgroundResource(this.f10122a ? R.drawable._2131430269_res_0x7f0b0b7d : R.drawable._2131430275_res_0x7f0b0b83);
        jcf.bEz_(cVar.f10123a, nsf.h(this.f10122a ? R$string.IDS_contact_add : R$string.accessibility_close));
        this.f10122a = !this.f10122a;
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dCD_(c cVar, View view) {
        this.e.setClickAdd();
        cVar.f10123a.setBackgroundResource(this.f10122a ? R.drawable._2131430275_res_0x7f0b0b83 : R.drawable._2131430269_res_0x7f0b0b7d);
        jcf.bEz_(cVar.f10123a, nsf.h(this.f10122a ? R$string.accessibility_close : R$string.IDS_contact_add));
        this.f10122a = !this.f10122a;
        ViewClickInstrumentation.clickOnView(view);
    }

    public static class c extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f10123a;
        HealthTextView d;
        HealthTextView e;

        private c(View view) {
            super(view);
            this.e = (HealthTextView) view.findViewById(R.id.before_one_last_two_records_s);
            this.d = (HealthTextView) view.findViewById(R.id.before_one_more_layout);
            ImageView imageView = (ImageView) view.findViewById(R.id.hw_show_health_data_before_one_arrow);
            this.f10123a = imageView;
            imageView.setBackgroundResource(R.drawable._2131430269_res_0x7f0b0b7d);
        }
    }
}
