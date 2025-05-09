package com.huawei.ui.homehealth.threecirclecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import com.huawei.ui.homehealth.threecirclecard.TwoModelCardViewHolder;
import defpackage.oum;
import defpackage.oun;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class TwoModelCardViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ThreeCircleCardViewHolder f9629a;
    private ThreeCircleCardViewHolder b;
    private LinearLayout c;
    private RelativeLayout d;
    private String e;
    private LinearLayout g;
    private RelativeLayout h;

    public void c() {
    }

    public TwoModelCardViewHolder(View view, Context context, boolean z, String str, final ThreeCircleCardData threeCircleCardData, final ThreeLeafCardData threeLeafCardData, final LayoutInflater layoutInflater) {
        super(view, context, z);
        LogUtil.d("TwoModelCardViewHolder", "TwoModelCardViewHolder start");
        this.e = str;
        this.c = (LinearLayout) view.findViewById(R.id.three_circle);
        this.g = (LinearLayout) view.findViewById(R.id.three_leaf);
        oum.b = false;
        if ("threeCircleCard".equals(oun.a())) {
            this.f9629a = (ThreeCircleCardViewHolder) threeCircleCardData.getCardViewHolder(this.c, layoutInflater);
        } else {
            this.b = (ThreeCircleCardViewHolder) threeLeafCardData.getCardViewHolder(this.g, layoutInflater);
        }
        b();
        e(this.e);
        HandlerExecutor.d(new Runnable() { // from class: ovu
            @Override // java.lang.Runnable
            public final void run() {
                TwoModelCardViewHolder.this.diC_(threeLeafCardData, layoutInflater, threeCircleCardData);
            }
        }, 1000L);
        LogUtil.d("TwoModelCardViewHolder", "TwoModelCardViewHolder end");
    }

    public /* synthetic */ void diC_(ThreeLeafCardData threeLeafCardData, LayoutInflater layoutInflater, ThreeCircleCardData threeCircleCardData) {
        LogUtil.d("TwoModelCardViewHolder", "TwoModelCardViewHolder load other card");
        if ("threeCircleCard".equals(oun.a())) {
            ThreeCircleCardViewHolder threeCircleCardViewHolder = (ThreeCircleCardViewHolder) threeLeafCardData.getCardViewHolder(this.g, layoutInflater);
            this.b = threeCircleCardViewHolder;
            this.h = threeCircleCardViewHolder.did_();
        } else {
            ThreeCircleCardViewHolder threeCircleCardViewHolder2 = (ThreeCircleCardViewHolder) threeCircleCardData.getCardViewHolder(this.c, layoutInflater);
            this.f9629a = threeCircleCardViewHolder2;
            this.d = threeCircleCardViewHolder2.did_();
        }
        oum.b = true;
        LogUtil.d("TwoModelCardViewHolder", "TwoModelCardViewHolder load other card end");
    }

    public void e() {
        diB_(this.d);
        diB_(this.h);
        b();
    }

    public void d(String str) {
        if ("threeCircleCard".equals(str)) {
            this.g.setVisibility(4);
        } else if ("threeLeafCard".equals(str)) {
            this.c.setVisibility(4);
        } else {
            LogUtil.e("TwoModelCardViewHolder", "card type error");
        }
    }

    public void e(String str) {
        if ("threeCircleCard".equals(str)) {
            this.c.setVisibility(0);
            this.g.setVisibility(8);
        } else if ("threeLeafCard".equals(str)) {
            this.c.setVisibility(8);
            this.g.setVisibility(0);
        } else {
            LogUtil.e("TwoModelCardViewHolder", "card type error");
        }
    }

    private void b() {
        ThreeCircleCardViewHolder threeCircleCardViewHolder = this.f9629a;
        if (threeCircleCardViewHolder != null) {
            this.d = threeCircleCardViewHolder.did_();
        }
        ThreeCircleCardViewHolder threeCircleCardViewHolder2 = this.b;
        if (threeCircleCardViewHolder2 != null) {
            this.h = threeCircleCardViewHolder2.did_();
        }
    }

    private void diB_(RelativeLayout relativeLayout) {
        if (relativeLayout == null || !(relativeLayout.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();
        layoutParams.height = -2;
        relativeLayout.setLayoutParams(layoutParams);
    }

    private void d() {
        if ("threeCircleCard".equals(this.e)) {
            this.c.setVisibility(0);
            this.g.setVisibility(4);
        } else if ("threeLeafCard".equals(this.e)) {
            this.c.setVisibility(4);
            this.g.setVisibility(0);
        } else {
            LogUtil.e("TwoModelCardViewHolder", "card type error");
        }
    }

    public void b(String str) {
        LogUtil.d("TwoModelCardViewHolder", "notifyModelChanged, targetType: ", str);
        this.e = str;
        d();
    }
}
