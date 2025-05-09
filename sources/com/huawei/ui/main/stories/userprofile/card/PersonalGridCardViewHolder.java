package com.huawei.ui.main.stories.userprofile.card;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.rzs;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class PersonalGridCardViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f10531a;
    private Context b;
    private HealthTextView c;
    private ImageView d;
    private ImageView e;
    private boolean f;
    private HealthTextView g;
    private ImageView h;
    private HealthTextView i;
    private rzs j;
    private LinearLayout m;
    private HealthTextView n;
    private ImageView o;

    public PersonalGridCardViewHolder(View view, Context context, rzs rzsVar) {
        super(view);
        this.b = context;
        this.j = rzsVar;
    }

    public void b() {
        this.f10531a = (RelativeLayout) this.itemView.findViewById(R.id.item_channel_view_horizontal);
        this.i = (HealthTextView) this.itemView.findViewById(R.id.item_channel_text);
        this.c = (HealthTextView) this.itemView.findViewById(R.id.item_channel_desc);
        this.d = (ImageView) this.itemView.findViewById(R.id.channel_icon);
        this.e = (ImageView) this.itemView.findViewById(R.id.item_channel_red_point);
        this.m = (LinearLayout) this.itemView.findViewById(R.id.item_channel_view_vertical);
        this.n = (HealthTextView) this.itemView.findViewById(R.id.item_channel_text_vertical);
        this.g = (HealthTextView) this.itemView.findViewById(R.id.item_channel_desc_vertical);
        this.o = (ImageView) this.itemView.findViewById(R.id.channel_icon_vertical);
        this.h = (ImageView) this.itemView.findViewById(R.id.item_channel_red_point_vertical);
        if (this.f) {
            this.m.setVisibility(0);
            this.f10531a.setVisibility(8);
        } else {
            this.m.setVisibility(8);
            this.f10531a.setVisibility(0);
        }
        if (!LanguageUtil.h(this.b)) {
            this.c.setLines(2);
            this.g.setLines(2);
        } else {
            this.c.setLines(1);
            this.g.setLines(1);
        }
        d();
    }

    private void d() {
        rzs rzsVar = this.j;
        if (rzsVar == null) {
            LogUtil.h("PersonalGridCardViewHolder", "mListBean is empty");
            return;
        }
        this.i.setText(rzsVar.a());
        this.n.setText(this.j.a());
        if (this.j.d() instanceof String) {
            String str = (String) this.j.d();
            this.c.setText(str);
            this.g.setText(str);
        }
        if (LanguageUtil.bc(this.b) && this.j.e() == R.mipmap._2131821259_res_0x7f1102cb) {
            this.d.setImageDrawable(nrz.cKn_(this.b, this.j.e()));
            this.o.setImageDrawable(nrz.cKn_(this.b, this.j.e()));
        } else {
            this.d.setImageResource(this.j.e());
            this.o.setImageResource(this.j.e());
        }
        this.e.setVisibility(this.j.l() ? 0 : 8);
        this.h.setVisibility(this.j.l() ? 0 : 8);
        this.itemView.setOnClickListener(this.j.dUz_());
    }

    public void d(rzs rzsVar) {
        this.j = rzsVar;
        if ((rzsVar.d() instanceof String) && this.c != null) {
            String str = (String) rzsVar.d();
            this.c.setText(str);
            this.g.setText(str);
            LogUtil.a("PersonalGridCardViewHolder", "updateCardDesc = ", str);
        }
        if (!LanguageUtil.h(this.b)) {
            this.c.setLines(2);
            this.g.setLines(2);
            b(2);
        } else {
            this.c.setLines(1);
            this.g.setLines(1);
            b(1);
        }
        if (!nsn.t()) {
            this.c.setAutoTextInfo(9, 1, 1);
            this.g.setAutoTextInfo(9, 1, 1);
        }
        c(rzsVar.l());
    }

    public void c(boolean z) {
        ImageView imageView = this.e;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
            this.h.setVisibility(z ? 0 : 8);
        }
    }

    public void b(boolean z) {
        this.f = z;
        LinearLayout linearLayout = this.m;
        if (linearLayout == null) {
            LogUtil.h("PersonalGridCardViewHolder", "setIsVerticalLayout view not init ");
        } else if (z) {
            linearLayout.setVisibility(0);
            this.f10531a.setVisibility(8);
        } else {
            linearLayout.setVisibility(8);
            this.f10531a.setVisibility(0);
        }
    }

    private void b(int i) {
        if (this.f) {
            this.m.setMinimumHeight(this.b.getResources().getDimensionPixelOffset(i == 1 ? R.dimen._2131363133_res_0x7f0a053d : R.dimen._2131362873_res_0x7f0a0439));
        } else {
            this.f10531a.setMinimumHeight(this.b.getResources().getDimensionPixelOffset(i == 1 ? R.dimen._2131363085_res_0x7f0a050d : R.dimen._2131363105_res_0x7f0a0521));
        }
    }
}
