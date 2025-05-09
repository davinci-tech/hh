package com.huawei.ui.thirdpartservice.activity.healthkitthirdparty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAppAdapter;
import defpackage.koq;
import defpackage.rhx;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes8.dex */
public class ThirdPartAppAdapter extends RecyclerView.Adapter<AppViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private final List<HiAppInfo> f10546a;
    private Context c;
    ItemClickListener d = null;

    public interface ItemClickListener {
        void onItemClick(View view, int i);
    }

    public ThirdPartAppAdapter(List<HiAppInfo> list) {
        this.f10546a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dWY_, reason: merged with bridge method [inline-methods] */
    public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_third_part_app, viewGroup, false);
        this.c = viewGroup.getContext();
        return new AppViewHolder(inflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final AppViewHolder appViewHolder, final int i) {
        if (koq.b(this.f10546a, i)) {
            LogUtil.c("ThirdPartAppAdapter", "onBindViewHolder isOutOfBounds");
            return;
        }
        if (this.f10546a.get(i) != null) {
            HiAppInfo hiAppInfo = this.f10546a.get(i);
            if (hiAppInfo.getAppName() == null || !hiAppInfo.getAppName().startsWith("QuickApp_")) {
                appViewHolder.c.setText(hiAppInfo.getAppName());
            } else {
                appViewHolder.c.setText(hiAppInfo.getAppName().substring(9));
            }
            appViewHolder.b.setImageDrawable(rhx.dOE_(this.c, hiAppInfo));
        }
        if (LanguageUtil.bc(this.c)) {
            appViewHolder.d.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        if (i == this.f10546a.size() - 1) {
            appViewHolder.f10547a.setVisibility(8);
        }
        appViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: sfl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThirdPartAppAdapter.this.dWX_(appViewHolder, i, view);
            }
        });
    }

    public /* synthetic */ void dWX_(AppViewHolder appViewHolder, int i, View view) {
        ItemClickListener itemClickListener = this.d;
        if (itemClickListener != null) {
            itemClickListener.onItemClick(appViewHolder.b, i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<HiAppInfo> list = this.f10546a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f10547a;
        private ImageView b;
        private HealthTextView c;
        private ImageView d;
        private LinearLayout e;

        public AppViewHolder(View view) {
            super(view);
            this.e = (LinearLayout) view.findViewById(R.id.ll_third_part_item);
            this.b = (ImageView) view.findViewById(R.id.iv_app_icon);
            this.c = (HealthTextView) view.findViewById(R.id.htv_app_name);
            this.d = (ImageView) view.findViewById(R.id.myfitnesspal_arrow_gray);
            this.f10547a = (HealthDivider) view.findViewById(R.id.third_part_app_divider);
        }
    }

    public void d(ItemClickListener itemClickListener) {
        this.d = itemClickListener;
    }
}
