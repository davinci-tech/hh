package com.huawei.health.knit.section.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.health.marketing.datatype.RecommendCardBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dqj;
import defpackage.eie;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nsy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class SectionRecommendRecyclesAdapter extends RecyclerView.Adapter<SectionRecommendRecyclesHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Context f2581a;
    private Set<String> c = new HashSet();
    private List<RecommendCardBean> d;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public SectionRecommendRecyclesAdapter(Context context) {
        LogUtil.a("SectionRecommendRecyclesAdapter", "SectionRecommendRecyclesAdapter");
        this.f2581a = context;
    }

    public void e(List<RecommendCardBean> list) {
        if (koq.b(list)) {
            return;
        }
        this.d = list;
        LogUtil.a("SectionRecommendRecyclesAdapter", "mCardBeanList ", list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: adZ_, reason: merged with bridge method [inline-methods] */
    public SectionRecommendRecyclesHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("SectionRecommendRecyclesAdapter", "onBindViewHolder");
        return new SectionRecommendRecyclesHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.section_recycle_recommend_card_layout_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SectionRecommendRecyclesHolder sectionRecommendRecyclesHolder, int i) {
        LogUtil.a("SectionRecommendRecyclesAdapter", "onBindViewHolder position ", Integer.valueOf(i));
        if (!koq.d(this.d, i)) {
            LogUtil.a("SectionRecommendRecyclesAdapter", "onBindViewHolder error, position not inBounds");
            return;
        }
        a(sectionRecommendRecyclesHolder, i);
        RecommendCardBean recommendCardBean = this.d.get(i);
        a(sectionRecommendRecyclesHolder, recommendCardBean);
        d(sectionRecommendRecyclesHolder, recommendCardBean);
        b(sectionRecommendRecyclesHolder, recommendCardBean);
        d(sectionRecommendRecyclesHolder, i, recommendCardBean);
        e(sectionRecommendRecyclesHolder, recommendCardBean);
    }

    private void a(SectionRecommendRecyclesHolder sectionRecommendRecyclesHolder, int i) {
        int b = nrr.b(this.f2581a);
        int b2 = nrr.b(this.f2581a);
        ViewGroup.LayoutParams layoutParams = sectionRecommendRecyclesHolder.itemView.getLayoutParams();
        if (!(layoutParams instanceof RecyclerView.LayoutParams)) {
            LogUtil.b("layoutParams error", new Object[0]);
            return;
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        layoutParams2.setMarginStart(b);
        if (i != getItemCount() - 1) {
            b2 = 0;
        }
        layoutParams2.setMarginEnd(b2);
        sectionRecommendRecyclesHolder.itemView.setLayoutParams(layoutParams2);
    }

    private void b(final SectionRecommendRecyclesHolder sectionRecommendRecyclesHolder, RecommendCardBean recommendCardBean) {
        if (recommendCardBean.getButtonColor() != null) {
            final GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius(nrf.d);
            gradientDrawable.setColor(recommendCardBean.getButtonColor().intValue());
            sectionRecommendRecyclesHolder.c.setBackground(gradientDrawable);
            final GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setCornerRadius(nrf.d);
            gradientDrawable2.setColor(ColorUtils.blendARGB(recommendCardBean.getButtonColor().intValue(), BaseApplication.e().getColor(R.color.emui_clickeffic_default_color), 0.1f));
            sectionRecommendRecyclesHolder.c.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.knit.section.adapter.SectionRecommendRecyclesAdapter.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        sectionRecommendRecyclesHolder.c.setBackground(gradientDrawable2);
                    }
                    if (action != 1 && action != 3) {
                        return false;
                    }
                    sectionRecommendRecyclesHolder.c.setBackground(gradientDrawable);
                    return false;
                }
            });
        }
    }

    private void a(SectionRecommendRecyclesHolder sectionRecommendRecyclesHolder, RecommendCardBean recommendCardBean) {
        if (sectionRecommendRecyclesHolder == null || recommendCardBean == null) {
            LogUtil.b("SectionRecommendRecyclesAdapter", "recommendCardBean or holder is null");
            return;
        }
        if (recommendCardBean.getTitleColor() != null) {
            sectionRecommendRecyclesHolder.f.setTextColor(recommendCardBean.getTitleColor().intValue());
        }
        LogUtil.a("SectionRecommendRecyclesAdapter", "bind text title = ", recommendCardBean.getTitle());
        nsy.cMs_(sectionRecommendRecyclesHolder.f, nsy.a(recommendCardBean.getTitle()), true);
        nsy.cMs_(sectionRecommendRecyclesHolder.g, recommendCardBean.getSubtitle(), true);
        nsy.cMs_(sectionRecommendRecyclesHolder.h, recommendCardBean.getSubtitleDataLine(), true);
        nsy.cMs_(sectionRecommendRecyclesHolder.c, recommendCardBean.getButtonText(), true);
    }

    private void d(SectionRecommendRecyclesHolder sectionRecommendRecyclesHolder, RecommendCardBean recommendCardBean) {
        if (sectionRecommendRecyclesHolder == null || recommendCardBean == null) {
            LogUtil.b("SectionRecommendRecyclesAdapter", "recommendCardBean or holder is null");
            return;
        }
        LogUtil.a("SectionRecommendRecyclesAdapter", "recommendCardBean CardId is ", recommendCardBean.getCardId());
        if (recommendCardBean.getBackGround() != null) {
            sectionRecommendRecyclesHolder.f2584a.setImageBitmap(recommendCardBean.getBackGround());
        }
    }

    private void d(SectionRecommendRecyclesHolder sectionRecommendRecyclesHolder, final int i, final RecommendCardBean recommendCardBean) {
        if (recommendCardBean == null || sectionRecommendRecyclesHolder == null) {
            LogUtil.b("SectionRecommendRecyclesAdapter", "recommendCardBean is null");
        } else {
            nsy.cMn_(sectionRecommendRecyclesHolder.c, new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionRecommendRecyclesAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (recommendCardBean.getClickSectionListener() != null) {
                        recommendCardBean.getClickSectionListener().onClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            nsy.cMn_(sectionRecommendRecyclesHolder.itemView, new View.OnClickListener() { // from class: com.huawei.health.knit.section.adapter.SectionRecommendRecyclesAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (recommendCardBean.getClickSectionListener() != null) {
                        recommendCardBean.getClickSectionListener().onClick(i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void e(SectionRecommendRecyclesHolder sectionRecommendRecyclesHolder, RecommendCardBean recommendCardBean) {
        if (sectionRecommendRecyclesHolder.itemView == null) {
            return;
        }
        LogUtil.a("SectionRecommendRecyclesAdapter", "bindViewTreeObserver");
        sectionRecommendRecyclesHolder.e = recommendCardBean;
        ViewTreeVisibilityListener.Zz_(sectionRecommendRecyclesHolder.itemView, new ViewTreeVisibilityListener(sectionRecommendRecyclesHolder.itemView, sectionRecommendRecyclesHolder), 200L);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    public class SectionRecommendRecyclesHolder extends RecyclerView.ViewHolder implements ViewTreeVisibilityListener.ViewTreeListenee {

        /* renamed from: a, reason: collision with root package name */
        private ImageView f2584a;
        private HealthButton c;
        private HealthCardView d;
        private RecommendCardBean e;
        private HealthTextView f;
        private HealthTextView g;
        private HealthTextView h;

        public SectionRecommendRecyclesHolder(View view) {
            super(view);
            this.d = (HealthCardView) view.findViewById(R.id.section_recommend_card_view);
            this.f2584a = (ImageView) view.findViewById(R.id.recommend_card_background);
            this.f = (HealthTextView) view.findViewById(R.id.card_title);
            this.g = (HealthTextView) view.findViewById(R.id.card_subtitle);
            this.h = (HealthTextView) view.findViewById(R.id.card_subtitle_data_part);
            this.c = (HealthButton) view.findViewById(R.id.start_button);
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void checkVisibilityAndSetBiEvent() {
            RecommendCardBean recommendCardBean;
            if (!ViewTreeVisibilityListener.Zx_(this.itemView) || !eie.alG_(this.itemView) || hasSetBiEvent() || (recommendCardBean = this.e) == null) {
                return;
            }
            LogUtil.a("SectionRecommendRecyclesAdapter", "visible to user: ", recommendCardBean.getCardId());
            biEvent();
            SectionRecommendRecyclesAdapter.this.c.add(this.e.getCardId());
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public boolean hasSetBiEvent() {
            return this.e != null && SectionRecommendRecyclesAdapter.this.c.contains(this.e.getCardId());
        }

        @Override // com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
        public void biEvent() {
            if (this.e == null) {
                return;
            }
            dqj.a(BaseApplication.e(), 0, this.e);
        }
    }
}
