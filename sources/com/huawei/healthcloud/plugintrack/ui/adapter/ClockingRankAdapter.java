package com.huawei.healthcloud.plugintrack.ui.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsy;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class ClockingRankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int d = 90;
    private List<d> b;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (i == 0 || i == 1 || i == 2) ? 1 : 2;
    }

    public ClockingRankAdapter(List<d> list) {
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new ClockingRankImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clocking_rank_adapter_item_image_layout, viewGroup, false));
        }
        return new ClockingRankNumViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clocking_rank_adapter_item_num_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.c("ClockingRankAdapter", "onBindViewHolder");
        if (koq.b(this.b)) {
            LogUtil.c("ClockingRankAdapter", "mClockingRankBeanList is null");
            return;
        }
        if (viewHolder instanceof ClockingRankImageViewHolder) {
            e((ClockingRankImageViewHolder) viewHolder, i);
        } else if (viewHolder instanceof ClockingRankNumViewHolder) {
            c((ClockingRankNumViewHolder) viewHolder, i);
        } else {
            LogUtil.c("ClockingRankAdapter", "no viewholder match");
        }
    }

    private void c(final ClockingRankNumViewHolder clockingRankNumViewHolder, int i) {
        clockingRankNumViewHolder.d.setText(String.valueOf(this.b.get(i).e));
        LogUtil.c("ClockingRankAdapter", "mUserName: ", this.b.get(i).d, ", mUserImage: ", this.b.get(i).c);
        if (TextUtils.isEmpty(this.b.get(i).c)) {
            clockingRankNumViewHolder.b.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
        } else {
            nrf.cIT_(clockingRankNumViewHolder.b, this.b.get(i).c, d, 0, new AsyncLoadDrawableCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter.1
                @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
                public void getDrawable(Drawable drawable) {
                    if (drawable == null) {
                        clockingRankNumViewHolder.b.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
                    }
                }
            });
        }
        RunningRouteUtils.d(clockingRankNumViewHolder.h, this.b.get(i).d);
        clockingRankNumViewHolder.e.setText(this.b.get(i).b);
        if (i == getItemCount() - 1) {
            nsy.cMA_(clockingRankNumViewHolder.c, 8);
        } else {
            nsy.cMA_(clockingRankNumViewHolder.c, 0);
        }
    }

    private void e(final ClockingRankImageViewHolder clockingRankImageViewHolder, int i) {
        clockingRankImageViewHolder.e.setImageResource(this.b.get(i).e);
        LogUtil.c("ClockingRankAdapter", "mUserName: ", this.b.get(i).d, ", mUserImage: ", this.b.get(i).c);
        if (TextUtils.isEmpty(this.b.get(i).c)) {
            clockingRankImageViewHolder.j.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
        } else {
            nrf.cIT_(clockingRankImageViewHolder.j, this.b.get(i).c, d, 0, new AsyncLoadDrawableCallback() { // from class: com.huawei.healthcloud.plugintrack.ui.adapter.ClockingRankAdapter.2
                @Override // com.huawei.ui.commonui.utils.AsyncLoadDrawableCallback
                public void getDrawable(Drawable drawable) {
                    if (drawable == null) {
                        clockingRankImageViewHolder.j.setImageDrawable(ContextCompat.getDrawable(BaseApplication.e(), R.mipmap._2131821050_res_0x7f1101fa));
                    }
                }
            });
        }
        RunningRouteUtils.d(clockingRankImageViewHolder.f, this.b.get(i).d);
        if (i == 0) {
            nsy.cMA_(clockingRankImageViewHolder.d, 0);
        } else {
            nsy.cMA_(clockingRankImageViewHolder.d, 8);
        }
        clockingRankImageViewHolder.b.setText(this.b.get(i).b);
        if (i == getItemCount() - 1) {
            nsy.cMA_(clockingRankImageViewHolder.f3688a, 8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public class ClockingRankImageViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f3688a;
        private HealthTextView b;
        private HealthImageView d;
        private ImageView e;
        private HealthTextView f;
        private ImageView j;

        public ClockingRankImageViewHolder(View view) {
            super(view);
            this.e = (ImageView) view.findViewById(R.id.rank_image);
            this.j = (ImageView) view.findViewById(R.id.user_image);
            this.f = (HealthTextView) view.findViewById(R.id.user_name);
            this.d = (HealthImageView) view.findViewById(R.id.mvp_icon);
            this.b = (HealthTextView) view.findViewById(R.id.clock_times);
            this.f3688a = (HealthDivider) view.findViewById(R.id.clock_rank_item_divider);
        }
    }

    public class ClockingRankNumViewHolder extends RecyclerView.ViewHolder {
        private ImageView b;
        private HealthDivider c;
        private HealthTextView d;
        private HealthTextView e;
        private HealthTextView h;

        public ClockingRankNumViewHolder(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.rank_num);
            this.b = (ImageView) view.findViewById(R.id.user_image);
            this.h = (HealthTextView) view.findViewById(R.id.user_name);
            this.e = (HealthTextView) view.findViewById(R.id.clock_times);
            this.c = (HealthDivider) view.findViewById(R.id.clock_rank_item_divider);
        }
    }

    public static class d {
        private String b;
        private String c;
        private String d;
        private int e;

        public d(int i, String str, String str2, String str3) {
            this.e = i;
            this.c = str;
            this.d = str2;
            this.b = str3;
        }

        public String d() {
            return this.c;
        }

        public void d(String str) {
            this.c = str;
        }

        public void e(String str) {
            this.d = str;
        }
    }
}
