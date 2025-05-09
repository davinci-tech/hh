package com.huawei.health.suggestion.ui.fitness.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.LoadMoreViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.ffy;
import defpackage.fnz;
import defpackage.fot;
import defpackage.gds;
import defpackage.ggm;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nsk;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FitSearchRecyAdapter extends RecyclerView.Adapter {
    private Context e;
    private int c = 0;
    private int d = 0;

    /* renamed from: a, reason: collision with root package name */
    private List<FitWorkout> f3145a = new ArrayList(10);
    private float b = fnz.e();

    public FitSearchRecyAdapter(Context context) {
        this.e = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.e);
        if (i == 0) {
            return new a(from.inflate(R.layout.sug_fitness_list_item, viewGroup, false));
        }
        return new LoadMoreViewHolder(from.inflate(R.layout.sug_his_loading_more, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) != 0 || i < 0 || i >= this.f3145a.size() || !(viewHolder instanceof a)) {
            return;
        }
        a aVar = (a) viewHolder;
        aVar.d(this.f3145a.get(i), this.b);
        if (i == 0 || (nsn.ag(this.e) && i == 1)) {
            aVar.f.setVisibility(0);
        } else {
            aVar.f.setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f3145a.size() + this.c;
    }

    public int b() {
        return this.f3145a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == this.f3145a.size() ? 1 : 0;
    }

    public void c() {
        int itemCount = getItemCount();
        if (itemCount == 0) {
            return;
        }
        this.f3145a.clear();
        this.c = 0;
        notifyItemRangeRemoved(0, itemCount);
    }

    public void b(List<FitWorkout> list) {
        int size = this.f3145a.size();
        if (koq.c(list)) {
            this.f3145a.addAll(gds.b(list));
            if (this.d == 1) {
                this.f3145a = gds.b(this.f3145a);
                fot.a().a(this.f3145a);
                notifyDataSetChanged();
                return;
            }
        }
        int itemCount = getItemCount();
        this.c = 0;
        int i = itemCount - size;
        if (i > 0) {
            notifyItemRangeChanged(size, i);
        }
    }

    public void a(int i) {
        this.d = i;
    }

    public void e() {
        this.c = 1;
        notifyItemRangeChanged(this.f3145a.size(), 1);
    }

    public void a() {
        b(null);
    }

    static class a extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f3146a;
        HealthTextView b;
        HealthTextView c;
        View d;
        HealthTextView e;
        private View f;
        HealthTextView g;
        HealthTextView h;
        HealthTextView i;
        private ImageView j;

        a(View view) {
            super(view);
            this.d = view;
            this.g = (HealthTextView) view.findViewById(R.id.tv_plan_peoples_num);
            this.h = (HealthTextView) view.findViewById(R.id.tv_fe_name);
            this.b = (HealthTextView) view.findViewById(R.id.tv_custom);
            this.e = (HealthTextView) view.findViewById(R.id.tv_level);
            this.i = (HealthTextView) view.findViewById(R.id.tv_parameter_num);
            this.c = (HealthTextView) view.findViewById(R.id.tv_Kcal);
            this.f3146a = (ImageView) view.findViewById(R.id.sug_img_item_pic);
            this.j = (ImageView) view.findViewById(R.id.new_imageView);
            this.f = view.findViewById(R.id.sug_view_space);
        }

        public void d(final FitWorkout fitWorkout, float f) {
            if (fitWorkout == null) {
                LogUtil.h("Suggestion_FitSearchRecyAdapter", "set workout == null");
                return;
            }
            if (!Utils.o()) {
                this.h.setMaxLines(1);
                this.h.setSingleLine(true);
                this.h.setEllipsize(TextUtils.TruncateAt.END);
            }
            if (fitWorkout.getCourseDefineType() == 1) {
                e(fitWorkout);
            } else {
                this.b.setVisibility(8);
                this.i.setVisibility(0);
                this.h.setTextColor(ContextCompat.getColor(arx.b(), R.color._2131299238_res_0x7f090ba6));
                this.h.setTypeface(nsk.cKP_());
                float b = moe.b(fitWorkout.acquireCalorie() * f);
                String b2 = ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) b, moe.i(b));
                if (fitWorkout.isLongExplainVideoCourse()) {
                    e(8, 0);
                } else {
                    e(0, this.i.getContext().getResources().getDimensionPixelSize(R.dimen._2131363575_res_0x7f0a06f7));
                }
                this.c.setText(b2);
                b(fitWorkout);
                this.e.setText(ggm.d(fitWorkout.acquireDifficulty()));
                HealthTextView healthTextView = this.i;
                healthTextView.setText(ffy.d(healthTextView.getContext(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
                nrf.cIS_(this.f3146a, fitWorkout.acquirePicture(), nrf.d, 0, R.drawable._2131427609_res_0x7f0b0119);
            }
            if (nsn.s()) {
                this.g.setVisibility(8);
            }
            this.h.setText(StringUtils.c((Object) fitWorkout.acquireName()));
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.adapter.FitSearchRecyAdapter.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    a.this.a(fitWorkout);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            d(fitWorkout);
        }

        private void e(int i, int i2) {
            this.e.setVisibility(i);
            this.c.setVisibility(i);
            if (this.i.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.i.getLayoutParams();
                layoutParams.setMarginStart(i2);
                layoutParams.baselineToBaseline = -1;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(FitWorkout fitWorkout) {
            if (nsn.o()) {
                LogUtil.h("Suggestion_FitSearchRecyAdapter", "mItemView click too fast.");
                return;
            }
            mmp mmpVar = new mmp(fitWorkout.acquireId());
            if (fitWorkout.getCourseDefineType() == 1) {
                mmpVar.g(1);
            }
            mmpVar.d("9");
            mod.b(arx.b(), fitWorkout, mmpVar);
        }

        private void d(FitWorkout fitWorkout) {
            ViewGroup.LayoutParams layoutParams = this.j.getLayoutParams();
            layoutParams.height = -2;
            c(fitWorkout);
            List<PriceTagBean> acquirePriceTagBeanList = fitWorkout.acquirePriceTagBeanList();
            if (fitWorkout.getCornerImgDisplay() == 1) {
                String b = mod.b(acquirePriceTagBeanList);
                if (!TextUtils.isEmpty(b)) {
                    layoutParams.height = (int) this.j.getResources().getDimension(R.dimen._2131362959_res_0x7f0a048f);
                    this.j.setVisibility(0);
                    nrf.cIK_(b, this.j, 0.0f, nrf.d, 0.0f, 0.0f);
                }
            }
            this.j.setLayoutParams(layoutParams);
        }

        private void b(FitWorkout fitWorkout) {
            if (Utils.j()) {
                this.g.setText(ffy.awT_(BaseApplication.getContext(), "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)), R.style.sug_reco_train_num, R.style.sug_reco_train_desc));
            } else {
                this.g.setVisibility(8);
            }
        }

        private void e(FitWorkout fitWorkout) {
            this.c.setVisibility(8);
            this.e.setVisibility(8);
            this.i.setVisibility(8);
            this.b.setVisibility(0);
            this.h.setTextColor(ContextCompat.getColor(arx.b(), R.color._2131299236_res_0x7f090ba4));
            this.h.setTypeface(nsk.cKO_());
            this.g.setTextColor(ContextCompat.getColor(arx.b(), R.color._2131299244_res_0x7f090bac));
            this.g.setTextSize(arx.b().getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6) / nsn.g(arx.b()));
            this.g.setText(fot.b(fitWorkout.getPublishDate()));
            long g = CommonUtils.g(fitWorkout.acquireId()) % 3;
            nrf.cIP_(this.f3146a, g == 0 ? R.drawable.pic_custom_yellow : g == 1 ? R.drawable.pic_custom_red : R.drawable.pic_custom_blue, nrf.d, 0, R.drawable._2131427609_res_0x7f0b0119);
        }

        private void c(FitWorkout fitWorkout) {
            if (fitWorkout.getCourseDefineType() == 1) {
                this.j.setVisibility(8);
                return;
            }
            if (LanguageUtil.j(this.itemView.getContext()) || LanguageUtil.p(this.itemView.getContext())) {
                if (fitWorkout.acquireStage() == 0 && fitWorkout.acquireIsSupportDevice() == 0) {
                    this.j.setImageResource(R.drawable.pic_corner_new_watchwear);
                    this.j.setVisibility(0);
                    return;
                } else if (fitWorkout.acquireStage() == 0) {
                    this.j.setImageResource(R.drawable._2131430906_res_0x7f0b0dfa);
                    this.j.setVisibility(0);
                    return;
                } else if (fitWorkout.acquireIsSupportDevice() == 0) {
                    this.j.setImageResource(R.drawable.pic_corner_watchwear);
                    this.j.setVisibility(0);
                    return;
                } else {
                    this.j.setVisibility(8);
                    return;
                }
            }
            this.j.setVisibility(8);
        }
    }
}
