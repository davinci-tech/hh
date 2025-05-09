package com.huawei.ui.homewear21.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class WearHomeWatchFaceHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9677a;
    private View b;
    private View c;
    private LinearLayout d;
    private View e;
    private final View f;
    private View g;
    private ImageView h;
    private View i;
    private HealthTextView j;
    private LinearLayout m;

    public WearHomeWatchFaceHolder(View view) {
        super(view);
        this.f9677a = (RelativeLayout) nsy.cMd_(view, R.id.card_watchface_view);
        this.m = (LinearLayout) nsy.cMd_(view, R.id.ll_watchface_more);
        this.h = (ImageView) nsy.cMd_(view, R.id.im_watchface_more);
        this.j = (HealthTextView) nsy.cMd_(view, R.id.tv_watchface_more);
        this.d = (LinearLayout) nsy.cMd_(view, R.id.linear_layout_recommend_watch_face);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.h.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.h.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.c = nsy.cMd_(view, R.id.rl_watchface0);
        this.i = nsy.cMd_(view, R.id.rl_watchface1);
        this.f = nsy.cMd_(view, R.id.rl_watchface2);
        this.e = nsy.cMd_(view, R.id.rl_watchface3);
        this.b = nsy.cMd_(view, R.id.rl_watchface4);
        this.g = nsy.cMd_(view, R.id.rl_watchface5);
    }

    public RelativeLayout dmI_() {
        return this.f9677a;
    }

    public LinearLayout dmK_() {
        return this.m;
    }

    public HealthTextView a() {
        return this.j;
    }

    public View dmJ_() {
        return this.d;
    }
}
