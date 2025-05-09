package com.huawei.featureuserprofile.todo.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.TodoTaskInterface;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceb;
import defpackage.gka;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class TodoCardInnerViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthDivider f2051a;
    private ImageView b;
    private LinearLayout c;
    private Context d;
    private LinearLayout e;
    private LinearLayout h;
    private HealthTextView i;
    private HealthTextView j;

    public TodoCardInnerViewHolder(Context context, View view) {
        super(view);
        this.d = context;
        this.h = (LinearLayout) view.findViewById(R.id.root_view);
        this.i = (HealthTextView) view.findViewById(R.id.tv_today_todo);
        this.j = (HealthTextView) view.findViewById(R.id.tv_allday_todo_name);
        this.f2051a = (HealthDivider) view.findViewById(R.id.linview);
        this.e = (LinearLayout) view.findViewById(R.id.activity_container_ll_top);
        this.c = (LinearLayout) view.findViewById(R.id.activity_container_ll_bottom);
        this.b = (ImageView) view.findViewById(R.id.iv_arrow);
        if (LanguageUtil.bc(this.d)) {
            this.b.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
    }

    public void a(gka gkaVar) {
        TodoTaskInterface o;
        if (gkaVar == null) {
            LogUtil.h("TodoCardInnerViewHolder", "data == null");
            return;
        }
        LogUtil.a("TodoCardInnerViewHolder", "refreshTodoCardViewHolder");
        this.i.setText(gkaVar.n());
        this.j.setText(TextUtils.isEmpty(gkaVar.a()) ? gkaVar.f() : gkaVar.a());
        this.e.setVisibility(8);
        this.c.setVisibility(8);
        if (gkaVar.k() == 1792 && (o = gkaVar.o()) != null && (o instanceof ceb)) {
            c((ceb) o, gkaVar);
        }
    }

    private void c(ceb cebVar, gka gkaVar) {
        if (cebVar.l() == 2 && cebVar.m() == 1) {
            e(cebVar);
        }
    }

    private void e(ceb cebVar) {
        int i;
        if (cebVar == null) {
            LogUtil.h("TodoCardInnerViewHolder", "historyFinished activity is null,return");
            return;
        }
        int g = cebVar.g();
        int r = cebVar.r();
        int i2 = r - g;
        int i3 = 0;
        this.e.setVisibility(0);
        this.e.removeAllViews();
        if (r > 0 && r <= 10) {
            while (i3 < r) {
                if (i3 < i2) {
                    vE_(this.e, R.drawable._2131429859_res_0x7f0b09e3);
                } else {
                    vE_(this.e, R.drawable._2131429858_res_0x7f0b09e2);
                }
                i3++;
            }
            i = 73;
        } else if (r > 10) {
            this.c.setVisibility(0);
            this.c.removeAllViews();
            for (int i4 = 0; i4 < 10; i4++) {
                if (i4 < i2) {
                    vE_(this.e, R.drawable._2131429859_res_0x7f0b09e3);
                } else {
                    vE_(this.e, R.drawable._2131429858_res_0x7f0b09e2);
                }
            }
            while (i3 < r - 10) {
                if (i3 < i2 - 10) {
                    vE_(this.c, R.drawable._2131429859_res_0x7f0b09e3);
                } else {
                    vE_(this.c, R.drawable._2131429858_res_0x7f0b09e2);
                }
                i3++;
            }
            i = 94;
        } else {
            LogUtil.h("TodoCardInnerViewHolder", "no branch!");
            i = 56;
        }
        this.h.setLayoutParams(new LinearLayout.LayoutParams(-1, nrr.e(this.d, i)));
    }

    public void a() {
        this.f2051a.setVisibility(8);
    }

    private void vE_(LinearLayout linearLayout, int i) {
        ImageView imageView = new ImageView(this.d);
        imageView.setImageResource(i);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(nrr.e(this.d, 16.0f), nrr.e(this.d, 16.0f));
        if (nsn.ag(this.d)) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Context context = this.d;
            if (context instanceof Activity) {
                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            }
            layoutParams.setMarginEnd((displayMetrics.widthPixels - nrr.e(this.d, 232.0f)) / 9);
        } else {
            layoutParams.setMarginEnd(nrr.e(this.d, 14.0f));
        }
        imageView.setLayoutParams(layoutParams);
        linearLayout.addView(imageView);
    }
}
