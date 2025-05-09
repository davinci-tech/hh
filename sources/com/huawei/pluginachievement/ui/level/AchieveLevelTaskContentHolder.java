package com.huawei.pluginachievement.ui.level;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.ui.kakatask.AchieveKaKaTaskClickListener;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.mfm;
import defpackage.mkg;
import defpackage.mkj;
import defpackage.mle;
import defpackage.nsn;
import java.util.Locale;
import org.slf4j.Marker;

/* loaded from: classes9.dex */
public class AchieveLevelTaskContentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final Integer[] b;

    /* renamed from: a, reason: collision with root package name */
    private AchieveKaKaTaskClickListener f8446a;
    private int c;
    private int d;
    private View e;
    private LinearLayout f;
    private LinearLayout g;
    private ImageView h;
    private HealthButton i;
    private String j;
    private HealthButton k;
    private HealthTextView l;
    private ImageView m;
    private HealthButton n;
    private HealthTextView o;
    private String p;

    static {
        Integer valueOf = Integer.valueOf(R.mipmap._2131821393_res_0x7f110351);
        Integer valueOf2 = Integer.valueOf(R.mipmap._2131821397_res_0x7f110355);
        Integer valueOf3 = Integer.valueOf(R.mipmap._2131821394_res_0x7f110352);
        Integer valueOf4 = Integer.valueOf(R.mipmap._2131821396_res_0x7f110354);
        b = new Integer[]{valueOf, valueOf2, valueOf3, valueOf4, Integer.valueOf(R.mipmap._2131821395_res_0x7f110353), Integer.valueOf(R.mipmap._2131821392_res_0x7f110350), valueOf4};
    }

    AchieveLevelTaskContentHolder(View view, AchieveKaKaTaskClickListener achieveKaKaTaskClickListener, int i) {
        super(view);
        this.p = "";
        this.j = "0";
        this.c = 0;
        this.d = i;
        this.g = (LinearLayout) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_ll);
        this.o = (HealthTextView) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_text);
        this.m = (ImageView) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_tips_icon);
        this.h = (ImageView) mfm.cgM_(view, R.id.achieve_task_kaka_content_buy_tips_icon);
        this.f = (LinearLayout) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_kaka_desc);
        this.l = (HealthTextView) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_kaka_value);
        this.i = (HealthButton) mfm.cgM_(view, R.id.achieve_task_kaka_content_finished_iv);
        this.e = mfm.cgM_(view, R.id.content_line);
        HealthButton healthButton = (HealthButton) mfm.cgM_(view, R.id.achieve_task_kaka_content_tag_button);
        this.k = healthButton;
        healthButton.setText(BaseApplication.getContext().getResources().getString(R.string._2130840766_res_0x7f020cbe));
        d();
        this.k.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) mfm.cgM_(view, R.id.achieve_task_kaka_content_tag_emphasize_button);
        this.n = healthButton2;
        healthButton2.setText(BaseApplication.getContext().getResources().getString(R.string._2130840767_res_0x7f020cbf));
        this.n.setOnClickListener(this);
        this.f8446a = achieveKaKaTaskClickListener;
    }

    private void d() {
        Drawable drawable = BaseApplication.getContext().getResources().getDrawable(b[this.d - 1].intValue());
        if (drawable == null) {
            return;
        }
        this.k.setBackground(drawable);
        this.i.setBackground(drawable);
        this.i.setAlpha(0.4f);
    }

    private void e(mkj mkjVar) {
        this.p = mkjVar.e();
        String c = mkjVar.c();
        this.j = mkjVar.f();
        if (TextUtils.isEmpty(c)) {
            this.m.setVisibility(8);
        } else {
            this.m.setVisibility(0);
            this.m.setOnClickListener(this);
        }
        if (a(mkjVar)) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
        this.g.setVisibility(0);
        this.o.setVisibility(0);
        this.o.setText(mkjVar.a());
        this.k.setText(mkjVar.g());
        this.n.setText(mkjVar.h());
        this.l.setVisibility(0);
        String format = String.format(Locale.ROOT, BaseApplication.getContext().getString(R.string._2130840775_res_0x7f020cc7), Integer.valueOf(mkjVar.b()));
        this.l.setText(Marker.ANY_NON_NULL_MARKER + format);
        if ("0".equals(this.j)) {
            this.i.setVisibility(8);
            if (mkjVar.n() != 10001) {
                this.k.setVisibility(0);
                this.n.setVisibility(8);
                return;
            }
            return;
        }
        if ("1".equals(this.j)) {
            this.i.setVisibility(0);
            this.k.setVisibility(8);
            this.n.setVisibility(8);
        } else {
            LogUtil.c("AchieveLevelTaskContentHolder", "status:", this.j);
            this.i.setVisibility(0);
            this.k.setVisibility(8);
            this.n.setVisibility(8);
        }
    }

    private boolean a(mkj mkjVar) {
        int n = mkjVar.n();
        return n == 20009 || n == 30016 || n == 30017 || mkjVar.k() == 1;
    }

    public void b(mkg mkgVar, int i) {
        if (mkgVar == null) {
            LogUtil.h("AchieveLevelTaskContentHolder", "initView kakaLocationRelationshipData is null.");
            return;
        }
        mkj b2 = mkgVar.b();
        if (b2 != null) {
            this.c = i;
            e(b2);
        } else {
            this.g.setVisibility(8);
            this.i.setVisibility(8);
            this.k.setVisibility(8);
            this.n.setVisibility(8);
        }
        if (mkgVar.c()) {
            this.e.setVisibility(8);
        }
        if (mle.h(this.p)) {
            this.f.setVisibility(8);
            this.k.setText(BaseApplication.getContext().getResources().getString(R.string._2130840768_res_0x7f020cc0));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(1000)) {
            LogUtil.h("AchieveLevelTaskContentHolder", "button click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HealthButton healthButton = this.k;
        if (view == healthButton || view == this.n) {
            e(this.j, this.p, this.c);
        } else if (view == this.m) {
            e("3", this.p, this.c);
        } else {
            LogUtil.c("AchieveLevelTaskContentHolder", "status:", healthButton);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(String str, String str2, int i) {
        this.f8446a.onTaskClick(str, str2, i);
    }
}
