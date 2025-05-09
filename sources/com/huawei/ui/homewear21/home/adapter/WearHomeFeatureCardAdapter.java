package com.huawei.ui.homewear21.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homewear21.home.listener.WearHomeListener;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.pbo;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeFeatureCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private static final int f9659a = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362006_res_0x7f0a00d6);
    private LayoutInflater b;
    private Context c;
    private WearHomeListener d;
    private List<pbo> e;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public WearHomeFeatureCardAdapter(Context context, List<pbo> list) {
        this.c = context;
        this.e = list;
        this.b = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WearHomeFeatureCardViewHolder(this.b.inflate(R.layout.wear_home_feature_card_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i < this.e.size() && (viewHolder instanceof WearHomeFeatureCardViewHolder)) {
            a((WearHomeFeatureCardViewHolder) viewHolder, i);
        }
    }

    private void a(WearHomeFeatureCardViewHolder wearHomeFeatureCardViewHolder, final int i) {
        pbo pboVar = this.e.get(i);
        wearHomeFeatureCardViewHolder.d.setText(pboVar.e());
        if (TextUtils.isEmpty(pboVar.b())) {
            wearHomeFeatureCardViewHolder.d.setLines(2);
            wearHomeFeatureCardViewHolder.b.setVisibility(8);
        } else {
            wearHomeFeatureCardViewHolder.d.setLines(1);
            wearHomeFeatureCardViewHolder.b.setVisibility(0);
            wearHomeFeatureCardViewHolder.b.setText(pboVar.b());
        }
        a(pboVar.a(), wearHomeFeatureCardViewHolder);
        b(wearHomeFeatureCardViewHolder, pboVar);
        wearHomeFeatureCardViewHolder.f9661a.setEnabled(pboVar.j());
        if (pboVar.j()) {
            wearHomeFeatureCardViewHolder.f9661a.setAlpha(1.0f);
        } else {
            wearHomeFeatureCardViewHolder.f9661a.setAlpha(0.38f);
        }
        if (pboVar.i()) {
            wearHomeFeatureCardViewHolder.f.setVisibility(0);
        } else {
            wearHomeFeatureCardViewHolder.f.setVisibility(4);
        }
        wearHomeFeatureCardViewHolder.f9661a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homewear21.home.adapter.WearHomeFeatureCardAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WearHomeFeatureCardAdapter.this.d != null) {
                    WearHomeFeatureCardAdapter.this.d.onItemClick(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        if (pboVar.f()) {
            wearHomeFeatureCardViewHolder.e.setVisibility(0);
        } else {
            wearHomeFeatureCardViewHolder.e.setVisibility(4);
        }
    }

    private void b(WearHomeFeatureCardViewHolder wearHomeFeatureCardViewHolder, pbo pboVar) {
        if (TextUtils.isEmpty(pboVar.d())) {
            Drawable drawable = this.c.getResources().getDrawable(pboVar.c());
            drawable.setAutoMirrored(e(pboVar));
            wearHomeFeatureCardViewHolder.c.setImageDrawable(drawable);
        } else {
            LogUtil.a("WearHomeFeatureCardAdapter", "setIcon doctorImgUrl = ", pboVar.d());
            ViewGroup.LayoutParams layoutParams = wearHomeFeatureCardViewHolder.c.getLayoutParams();
            layoutParams.width = nsn.c(this.c, 24.0f);
            layoutParams.height = nsn.c(this.c, 24.0f);
            wearHomeFeatureCardViewHolder.c.setLayoutParams(layoutParams);
            nrf.cIS_(wearHomeFeatureCardViewHolder.c, pboVar.d(), f9659a, 0, pboVar.c());
        }
    }

    private boolean e(pbo pboVar) {
        if (pboVar.a() == 110 || pboVar.a() == 120) {
            return false;
        }
        if (pboVar.a() == 126 || pboVar.a() == 113) {
            return (LanguageUtil.bp(this.c) || LanguageUtil.y(this.c)) ? false : true;
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(int i, WearHomeFeatureCardViewHolder wearHomeFeatureCardViewHolder) {
        if (LanguageUtil.h(this.c)) {
            return;
        }
        if (i != 107) {
            if (i != 108) {
                if (i != 116) {
                    switch (i) {
                        case 103:
                        case 105:
                            break;
                        case 104:
                            wearHomeFeatureCardViewHolder.d.setLines(2);
                            break;
                        default:
                            switch (i) {
                            }
                    }
                }
                if (LanguageUtil.ba(this.c)) {
                    wearHomeFeatureCardViewHolder.d.setLines(3);
                    return;
                } else {
                    wearHomeFeatureCardViewHolder.d.setLines(2);
                    return;
                }
            }
            if (LanguageUtil.ae(this.c)) {
                wearHomeFeatureCardViewHolder.d.setLines(1);
                return;
            }
            LogUtil.c("WearHomeFeatureCardAdapter", "setTextSpecialFormat else.");
            return;
        }
        if (i == 121 && LanguageUtil.r(this.c)) {
            return;
        }
        wearHomeFeatureCardViewHolder.d.setTextSize(1, 9.0f);
        wearHomeFeatureCardViewHolder.d.setLines(4);
    }

    public void a(List<pbo> list) {
        this.e = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LogUtil.a("WearHomeFeatureCardAdapter", "Utils.isWidescreen(mContext)", Boolean.valueOf(nsn.ag(this.c)));
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.huawei.ui.homewear21.home.adapter.WearHomeFeatureCardAdapter.1
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    LogUtil.a("WearHomeFeatureCardAdapter", "Utils.isWidescreen(mContext):", Boolean.valueOf(nsn.ag(WearHomeFeatureCardAdapter.this.c)));
                    int spanCount = gridLayoutManager.getSpanCount();
                    LogUtil.a("WearHomeFeatureCardAdapter", "spanCount:", Integer.valueOf(spanCount), "mWearHomeFeatureBeans.size():", Integer.valueOf(WearHomeFeatureCardAdapter.this.e.size()));
                    return WearHomeFeatureCardAdapter.this.b(spanCount, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i, int i2) {
        return (i == 2 && this.e.size() % 2 != 0 && this.e.size() - 1 == i2) ? 2 : 1;
    }

    public void b(WearHomeListener wearHomeListener) {
        this.d = wearHomeListener;
    }

    public static class WearHomeFeatureCardViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        RelativeLayout f9661a;
        HealthTextView b;
        ImageView c;
        HealthTextView d;
        HealthTextView e;
        ImageView f;

        public WearHomeFeatureCardViewHolder(View view) {
            super(view);
            this.f9661a = (RelativeLayout) view.findViewById(R.id.feature_card);
            this.d = (HealthTextView) view.findViewById(R.id.description);
            this.b = (HealthTextView) view.findViewById(R.id.description_status);
            this.c = (ImageView) view.findViewById(R.id.icon);
            this.f = (ImageView) view.findViewById(R.id.detail_apps_red_dot);
            this.e = (HealthTextView) view.findViewById(R.id.beta);
        }
    }
}
