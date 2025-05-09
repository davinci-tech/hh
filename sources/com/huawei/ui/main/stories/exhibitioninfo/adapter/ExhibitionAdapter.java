package com.huawei.ui.main.stories.exhibitioninfo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.RoundedImageView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.exhibitioninfo.adapter.ExhibitionAdapter;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ExhibitionAdapter extends RecyclerView.Adapter<ExhibitionViewHolder> {
    private boolean c = false;
    private final OnReportSelectChangeListener f;
    private final LayoutInflater g;
    private List<HashMap<String, Bitmap>> h;
    private final Context i;
    private static final String b = BaseApplication.e().getString(R$string.IDS_report_electrocardiogram_analysis);
    private static final String e = BaseApplication.e().getString(R$string.IDS_report_oxygen_sugar_pressure);

    /* renamed from: a, reason: collision with root package name */
    private static final String f9718a = BaseApplication.e().getString(R$string.IDS_report_indoor_running);
    private static final String d = BaseApplication.e().getString(R$string.IDS_report_body_composition_analysis);

    public interface OnReportSelectChangeListener {
        void setSelectReport(String str, boolean z);
    }

    public ExhibitionAdapter(Context context, List<HashMap<String, Bitmap>> list, OnReportSelectChangeListener onReportSelectChangeListener) {
        this.i = context;
        this.g = LayoutInflater.from(context);
        this.h = list;
        this.f = onReportSelectChangeListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dpq_, reason: merged with bridge method [inline-methods] */
    public ExhibitionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ExhibitionViewHolder(this.g.inflate(R.layout.item_report_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final ExhibitionViewHolder exhibitionViewHolder, int i) {
        if (i >= this.h.size()) {
            return;
        }
        HashMap<String, Bitmap> hashMap = this.h.get(i);
        String str = b;
        if (hashMap.containsKey(str)) {
            exhibitionViewHolder.g.setText(str);
            Glide.with(this.i).load(hashMap.get(str)).into(exhibitionViewHolder.c);
        } else {
            String str2 = e;
            if (hashMap.containsKey(str2)) {
                exhibitionViewHolder.g.setText(str2);
                Glide.with(this.i).load(hashMap.get(str2)).into(exhibitionViewHolder.c);
            } else {
                String str3 = f9718a;
                if (hashMap.containsKey(str3)) {
                    exhibitionViewHolder.g.setText(str3);
                    Glide.with(this.i).load(hashMap.get(str3)).into(exhibitionViewHolder.c);
                } else {
                    HealthTextView healthTextView = exhibitionViewHolder.g;
                    String str4 = d;
                    healthTextView.setText(str4);
                    Glide.with(this.i).load(hashMap.get(str4)).into(exhibitionViewHolder.c);
                }
            }
        }
        exhibitionViewHolder.f9719a = this.c;
        if (exhibitionViewHolder.f9719a) {
            exhibitionViewHolder.b.setVisibility(0);
            exhibitionViewHolder.d.setVisibility(4);
        } else {
            exhibitionViewHolder.b.setVisibility(4);
            exhibitionViewHolder.d.setVisibility(0);
        }
        exhibitionViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: pgu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ExhibitionAdapter.this.dpp_(exhibitionViewHolder, view);
            }
        });
        this.f.setSelectReport(exhibitionViewHolder.g.getText().toString(), exhibitionViewHolder.f9719a);
    }

    public /* synthetic */ void dpp_(ExhibitionViewHolder exhibitionViewHolder, View view) {
        b(exhibitionViewHolder);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.h.size();
    }

    public void b(ExhibitionViewHolder exhibitionViewHolder) {
        if (exhibitionViewHolder.f9719a) {
            exhibitionViewHolder.b.setVisibility(4);
            exhibitionViewHolder.d.setVisibility(0);
            exhibitionViewHolder.f9719a = false;
        } else {
            exhibitionViewHolder.b.setVisibility(0);
            exhibitionViewHolder.d.setVisibility(4);
            exhibitionViewHolder.f9719a = true;
        }
        this.f.setSelectReport(exhibitionViewHolder.g.getText().toString(), exhibitionViewHolder.f9719a);
    }

    public void b() {
        this.c = !this.c;
    }

    public void e(List<HashMap<String, Bitmap>> list) {
        this.h = list;
    }

    public static class ExhibitionViewHolder extends RecyclerHolder {

        /* renamed from: a, reason: collision with root package name */
        boolean f9719a;
        RoundedImageView b;
        RoundedImageView c;
        RoundedImageView d;
        RelativeLayout e;
        HealthTextView g;

        public ExhibitionViewHolder(View view) {
            super(view);
            this.f9719a = false;
            this.e = (RelativeLayout) view.findViewById(R.id.id_main);
            this.c = (RoundedImageView) view.findViewById(R.id.report_data_item);
            this.b = (RoundedImageView) view.findViewById(R.id.report_is_select);
            this.d = (RoundedImageView) view.findViewById(R.id.report_not_select);
            this.g = (HealthTextView) view.findViewById(R.id.report_type);
        }
    }
}
