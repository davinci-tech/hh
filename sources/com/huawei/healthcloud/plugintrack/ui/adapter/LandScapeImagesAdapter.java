package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.koq;
import defpackage.nrf;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class LandScapeImagesAdapter extends RecyclerView.Adapter<LandScapeImagesViewHolder> {
    private static int d = 12;
    private Context c;
    private List<String> e;

    public LandScapeImagesAdapter(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        this.c = context;
        arrayList.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: bds_, reason: merged with bridge method [inline-methods] */
    public LandScapeImagesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LandScapeImagesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clocking_rank_land_scape_images_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(LandScapeImagesViewHolder landScapeImagesViewHolder, final int i) {
        LogUtil.c("LandScapeAdapter", "onBindViewHolder");
        if (koq.b(this.e)) {
            LogUtil.c("LandScapeAdapter", "mLandScapeImagesList is null");
        } else {
            landScapeImagesViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.LandScapeImagesAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RunningRouteUtils.aXj_(RunningRouteUtils.aXp_((String) LandScapeImagesAdapter.this.e.get(i)), LandScapeImagesAdapter.this.c);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            nrf.c(this.e.get(i), landScapeImagesViewHolder.b, d);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public class LandScapeImagesViewHolder extends RecyclerView.ViewHolder {
        private HealthImageView b;

        public LandScapeImagesViewHolder(View view) {
            super(view);
            this.b = (HealthImageView) view.findViewById(R.id.land_scape_iv);
        }
    }
}
