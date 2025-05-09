package com.huawei.ui.homehealth.dialogcarddata;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import defpackage.nsn;

/* loaded from: classes6.dex */
public class DialogCardViewHolder extends CardViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9420a;
    private HealthTextView aa;
    private RelativeLayout ab;
    private HealthTextView ac;
    private HealthTextView ad;
    private RelativeLayout ag;
    private HealthTextView ai;
    private HealthTextView b;
    private int c;
    private HealthColumnSystem d;
    private RelativeLayout e;
    private RelativeLayout f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private RelativeLayout m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private RelativeLayout t;
    private HealthTextView u;
    private LinearLayout v;
    private HealthTextView w;
    private LinearLayout x;
    private RelativeLayout y;
    private HealthTextView z;

    public DialogCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        this.c = 0;
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(getContext(), 1);
        this.d = healthColumnSystem;
        this.c = healthColumnSystem.f();
        daD_(view);
        daE_(view, context);
    }

    private void daD_(View view) {
        if (this.itemView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.removeAllViews();
            if (ah()) {
                viewGroup.addView(View.inflate(view.getContext(), R.layout.layout_dialog_card_container_cd, null));
            } else if (nsn.r()) {
                viewGroup.addView(View.inflate(view.getContext(), R.layout.layout_dialog_card_container_large_font_scale, null));
            } else {
                viewGroup.addView(View.inflate(view.getContext(), R.layout.layout_dialog_card_container, null));
            }
        }
    }

    public boolean ad() {
        this.d.e(getContext());
        if (this.c == this.d.f()) {
            return false;
        }
        this.c = this.d.f();
        if (this.itemView == null) {
            return false;
        }
        daD_(this.itemView);
        daE_(this.itemView, this.itemView.getContext());
        return true;
    }

    private void daE_(View view, Context context) {
        this.x = (LinearLayout) view.findViewById(R.id.home_item_step_card_layout);
        this.e = (RelativeLayout) view.findViewById(R.id.hw_health_home_dameon_killed_layout);
        this.y = (RelativeLayout) view.findViewById(R.id.rl_oppo_vivo_help);
        this.b = (HealthTextView) this.e.findViewById(R.id.img_show_tips_close);
        this.q = (HealthTextView) this.e.findViewById(R.id.img_show_tips_go);
        this.n = (HealthTextView) this.e.findViewById(R.id.img_hw_show_tips_close);
        this.k = (HealthTextView) this.e.findViewById(R.id.hw_show_tips_go_setting);
        this.ab = (RelativeLayout) this.e.findViewById(R.id.show_tips_btn_layout);
        this.h = (HealthTextView) this.y.findViewById(R.id.tv_go_to);
        this.s = (HealthTextView) this.y.findViewById(R.id.tv_ignore);
        this.t = (RelativeLayout) view.findViewById(R.id.rl_nps);
        this.ac = (HealthTextView) view.findViewById(R.id.tv_title);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.tv_content);
        this.f9420a = healthTextView;
        healthTextView.setText(getContext().getString(R.string._2130838101_res_0x7f020255));
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.tv_join);
        this.p = healthTextView2;
        healthTextView2.setText(getContext().getString(R.string._2130838102_res_0x7f020256));
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.tv_not_join);
        this.r = healthTextView3;
        healthTextView3.setText(getContext().getString(R.string._2130838103_res_0x7f020257));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.sync_cloud_data_fail_layout);
        this.v = linearLayout;
        this.w = (HealthTextView) linearLayout.findViewById(R.id.sync_ignore_tv);
        HealthTextView healthTextView4 = (HealthTextView) this.v.findViewById(R.id.sync_try_again_tv);
        this.ad = healthTextView4;
        healthTextView4.setText(getContext().getString(R.string._2130837642_res_0x7f02008a));
        this.ag = (RelativeLayout) view.findViewById(R.id.layout_wechat_bind);
        this.ai = (HealthTextView) view.findViewById(R.id.tv_wechat_join);
        this.z = (HealthTextView) view.findViewById(R.id.tv_wechat_ignore);
        this.m = (RelativeLayout) view.findViewById(R.id.layout_hi_ai_plugin);
        this.l = (HealthTextView) view.findViewById(R.id.tv_hi_ai_title);
        this.i = (HealthTextView) view.findViewById(R.id.tv_cancel);
        this.aa = (HealthTextView) view.findViewById(R.id.tv_download);
        this.f = (RelativeLayout) view.findViewById(R.id.layout_ecg_diagram);
        this.j = (HealthTextView) view.findViewById(R.id.ecg_data_view_btn);
        this.g = (HealthTextView) view.findViewById(R.id.ecg_data_ignore_btn);
        this.u = (HealthTextView) view.findViewById(R.id.notice_message11);
        this.o = (HealthTextView) view.findViewById(R.id.notice_title);
    }

    private boolean ah() {
        return this.c >= 8;
    }

    public LinearLayout daH_() {
        return this.x;
    }

    public LinearLayout daK_() {
        return this.v;
    }

    public HealthTextView q() {
        return this.w;
    }

    public HealthTextView u() {
        return this.ad;
    }

    public HealthTextView p() {
        return this.u;
    }

    public HealthTextView g() {
        return this.o;
    }

    public HealthTextView d() {
        return this.b;
    }

    public HealthTextView f() {
        return this.n;
    }

    public HealthTextView k() {
        return this.k;
    }

    public HealthTextView o() {
        return this.q;
    }

    public RelativeLayout daL_() {
        return this.ab;
    }

    public HealthTextView i() {
        return this.h;
    }

    public HealthTextView n() {
        return this.s;
    }

    public RelativeLayout daF_() {
        return this.e;
    }

    public RelativeLayout daJ_() {
        return this.y;
    }

    public View daI_() {
        return this.t;
    }

    public HealthTextView x() {
        return this.ac;
    }

    public HealthTextView m() {
        return this.p;
    }

    public HealthTextView l() {
        return this.r;
    }

    public RelativeLayout daN_() {
        return this.ag;
    }

    public HealthTextView ac() {
        return this.ai;
    }

    public HealthTextView aa() {
        return this.z;
    }

    public RelativeLayout daM_() {
        return this.m;
    }

    public HealthTextView v() {
        return this.i;
    }

    public HealthTextView y() {
        return this.aa;
    }

    public HealthTextView j() {
        return this.l;
    }

    public RelativeLayout daG_() {
        return this.f;
    }

    public HealthTextView c() {
        return this.j;
    }

    public HealthTextView b() {
        return this.g;
    }
}
