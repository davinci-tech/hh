package com.huawei.ui.main.stories.devicecapacity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.wearengine.auth.HiAppInfo;
import defpackage.nrf;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes9.dex */
public class ThirdPartAppAdapter extends RecyclerView.Adapter<AppViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private List<HiAppInfo> f9700a;
    private ItemClickListener b = null;
    private Context c;

    public interface ItemClickListener {
        void onItemClick(View view, int i);
    }

    public ThirdPartAppAdapter(List<HiAppInfo> list, Context context) {
        this.f9700a = list;
        this.c = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: doz_, reason: merged with bridge method [inline-methods] */
    public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AppViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wear_engine_third_part_auth_app_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final AppViewHolder appViewHolder, final int i) {
        List<HiAppInfo> list = this.f9700a;
        if (list == null || list.isEmpty() || i >= this.f9700a.size()) {
            LogUtil.c("ThirdPartAppAdapter", "onBindViewHolder isOutOfBounds");
            return;
        }
        if (this.f9700a.get(i) != null) {
            HiAppInfo hiAppInfo = this.f9700a.get(i);
            appViewHolder.d.setText(hiAppInfo.getAppName());
            appViewHolder.e.setImageBitmap(nrf.cHM_(hiAppInfo.getByteDraw()));
            appViewHolder.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.devicecapacity.ThirdPartAppAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ThirdPartAppAdapter.this.b != null) {
                        ThirdPartAppAdapter.this.b.onItemClick(appViewHolder.e, i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            if (i == this.f9700a.size() - 1) {
                appViewHolder.f9701a.setVisibility(8);
            }
            if (LanguageUtil.bc(this.c)) {
                appViewHolder.c.setBackground(this.c.getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            } else {
                appViewHolder.c.setBackground(this.c.getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<HiAppInfo> list = this.f9700a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        View f9701a;
        LinearLayout b;
        ImageView c;
        HealthTextView d;
        ImageView e;

        public AppViewHolder(View view) {
            super(view);
            this.b = (LinearLayout) view.findViewById(R.id.wear_engine_third_party_app_name_text_layout_id);
            this.e = (ImageView) view.findViewById(R.id.wear_engine_third_party_app_icon_id);
            this.d = (HealthTextView) view.findViewById(R.id.wear_engine_third_party_app_name_text_id);
            this.f9701a = view.findViewById(R.id.wear_engine_third_party_app_view_id);
            this.c = (ImageView) view.findViewById(R.id.wear_engine_third_party_app_arrow_gray_id);
        }
    }

    public void c(ItemClickListener itemClickListener) {
        this.b = itemClickListener;
    }
}
