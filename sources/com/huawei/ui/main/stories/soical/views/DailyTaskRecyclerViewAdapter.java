package com.huawei.ui.main.stories.soical.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.openalliance.ad.views.PPSNativeView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.stories.soical.NewSocialFragment;
import defpackage.koq;
import defpackage.mdf;
import defpackage.nkx;
import defpackage.pgq;
import defpackage.rxq;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class DailyTaskRecyclerViewAdapter extends RecyclerView.Adapter<d> {

    /* renamed from: a, reason: collision with root package name */
    private ViewGroup f10500a;
    private final List<ImageView> b;
    private final List<MessageObject> c;
    private final Context d;
    private final WeakReference<NewSocialFragment> e;
    private final rxq h;
    private LayoutInflater j;

    public DailyTaskRecyclerViewAdapter(NewSocialFragment newSocialFragment, List<ImageView> list, List<MessageObject> list2, rxq rxqVar) {
        this.e = new WeakReference<>(newSocialFragment);
        this.b = list;
        this.h = rxqVar;
        this.c = list2;
        this.d = newSocialFragment.getActivity();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dTG_, reason: merged with bridge method [inline-methods] */
    public d onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.j == null) {
            this.j = LayoutInflater.from(viewGroup.getContext());
        }
        if (this.f10500a == null) {
            this.f10500a = viewGroup;
        }
        d dVar = new d(this.j.inflate(R.layout.layout_new_social_cardview, (ViewGroup) null));
        dVar.c();
        return dVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(d dVar, int i) {
        NewSocialFragment newSocialFragment = this.e.get();
        if (newSocialFragment == null || !newSocialFragment.isVisible() || newSocialFragment.isRemoving() || newSocialFragment.isDetached()) {
            return;
        }
        d(dVar, i, this.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.c(this.b)) {
            return this.b.size();
        }
        return 0;
    }

    static class d extends RecyclerView.ViewHolder {
        PPSNativeView b;

        d(View view) {
            super(view);
        }

        void c() {
            if (this.itemView instanceof PPSNativeView) {
                this.b = (PPSNativeView) this.itemView;
            }
        }
    }

    private void d(d dVar, int i, List<ImageView> list) {
        MessageObject messageObject;
        if (koq.b(list, i)) {
            notifyItemRemoved(i);
            notifyItemRangeChanged(i, getItemCount());
            return;
        }
        View dTr_ = this.h.dTr_(list.get(i).getDrawable(), this.f10500a, i, dVar.b);
        dTr_.setTag(R.layout.layout_new_social_cardview, Long.valueOf(System.currentTimeMillis()));
        if (koq.b(this.c, i)) {
            this.h.dTy_(dTr_);
            return;
        }
        if (this.c.get(i).getNativeAd() != null || (messageObject = this.c.get(i)) == null || TextUtils.isEmpty(messageObject.getMsgId())) {
            return;
        }
        if (messageObject instanceof pgq) {
            pgq pgqVar = (pgq) messageObject;
            TextView textView = (TextView) dTr_.findViewById(R.id.card_txt_btn);
            if (pgqVar.b()) {
                mdf d2 = pgqVar.d();
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(this.d.getResources().getDimensionPixelSize(R.dimen._2131362906_res_0x7f0a045a));
                gradientDrawable.setColor(Color.parseColor(d2.v()));
                textView.setBackgroundDrawable(gradientDrawable);
                this.h.dTz_(d2, textView);
            }
            textView.setVisibility(pgqVar.b() ? 0 : 8);
        }
        this.h.b(messageObject, 0L, 1);
        Context context = this.d;
        if (context instanceof BaseActivity) {
            dTr_.setOnClickListener(nkx.cwZ_(new rxq.e(this.h, messageObject), (BaseActivity) context, this.h.e().isNotSupportBrowseUrl(messageObject.getDetailUri()), AnalyticsValue.SOCIAL_1070004.value()));
        } else {
            dTr_.setOnClickListener(new rxq.e(this.h, messageObject));
        }
    }
}
