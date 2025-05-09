package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.ui.view.ShowProgress;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class hoi {

    /* renamed from: a, reason: collision with root package name */
    private View f13298a;
    private HealthTextView b;
    private HealthTextView c;
    private Context d;
    private ShowProgress e;
    private View f;
    private View g;
    private int h;
    private HealthTextView i;
    private View j;
    private ShowProgress k;
    private HealthTextView l;
    private HealthTextView m;
    private ShowProgress n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView s;

    public hoi(Context context, int i) {
        this.d = context;
        this.h = i;
    }

    public View bnG_() {
        View bnE_;
        int i = this.h;
        if (i == 100) {
            bnE_ = bnE_();
        } else if (i == 101) {
            bnE_ = bnD_();
        } else {
            LogUtil.a("Track_TrainViewHolder", "buildView unknow style");
            bnE_ = new View(this.d);
        }
        this.f = bnE_;
        return bnE_;
    }

    private View bnD_() {
        View inflate = View.inflate(this.d, R.layout.track_share_viewholder_train_new, null);
        this.n = (ShowProgress) inflate.findViewById(R.id.track_detail_sp_xg);
        this.e = (ShowProgress) inflate.findViewById(R.id.track_detail_sp_anaerobic_xg);
        this.k = (ShowProgress) inflate.findViewById(R.id.track_detail_sp_ox);
        this.s = (HealthTextView) inflate.findViewById(R.id.track_ox_msg);
        this.q = (HealthTextView) inflate.findViewById(R.id.sug_detail_ox);
        this.p = (HealthTextView) inflate.findViewById(R.id.sug_detail_ox_unit);
        this.c = (HealthTextView) inflate.findViewById(R.id.tv_track_detail_xg_title);
        this.l = (HealthTextView) inflate.findViewById(R.id.track_detail_xg);
        this.o = (HealthTextView) inflate.findViewById(R.id.track_detail_effectshow);
        this.b = (HealthTextView) inflate.findViewById(R.id.tv_track_detail_anaerobic_xg_title);
        this.i = (HealthTextView) inflate.findViewById(R.id.track_detail_anaerobic_xg);
        this.m = (HealthTextView) inflate.findViewById(R.id.track_detail_anaerobic_effectshow);
        this.g = inflate.findViewById(R.id.track_train_divider);
        this.f13298a = inflate.findViewById(R.id.track_aerobic_train_divider);
        this.j = inflate.findViewById(R.id.track_train_divider_two);
        bnF_(inflate);
        this.n.c(gwh.b);
        this.e.c(gwh.b);
        this.k.c(gwh.b);
        this.g.setBackgroundColor(gwh.e);
        this.f13298a.setBackgroundColor(gwh.e);
        this.j.setBackgroundColor(gwh.e);
        return inflate;
    }

    private void bnF_(View view) {
        d((HealthTextView) view.findViewById(R.id.tv_track_detail_xg_title), gwh.b);
        d((HealthTextView) view.findViewById(R.id.tv_track_detail_anaerobic_xg_title), gwh.b);
        d((HealthTextView) view.findViewById(R.id.tv_track_detail_ox_title), gwh.b);
        d(this.l, this.d.getResources().getColor(R.color._2131296871_res_0x7f090267));
        d(this.o, gwh.b);
        d(this.i, this.d.getResources().getColor(R.color._2131296871_res_0x7f090267));
        d(this.m, gwh.b);
        d(this.q, this.d.getResources().getColor(R.color._2131296871_res_0x7f090267));
        d(this.p, gwh.b);
        d(this.s, gwh.b);
        d((HealthTextView) view.findViewById(R.id.pluginmotiontrack_detail_title_train_textview), gwh.b);
        d((HealthTextView) view.findViewById(R.id.track_detail_ritire_duration_name), gwh.b);
        d((HealthTextView) view.findViewById(R.id.track_detail_ritire_duration), this.d.getResources().getColor(R.color._2131296871_res_0x7f090267));
    }

    private View bnE_() {
        View inflate = View.inflate(this.d, R.layout.track_share_viewholder_train_new, null);
        this.n = (ShowProgress) inflate.findViewById(R.id.track_detail_sp_xg);
        this.e = (ShowProgress) inflate.findViewById(R.id.track_detail_sp_anaerobic_xg);
        this.k = (ShowProgress) inflate.findViewById(R.id.track_detail_sp_ox);
        this.c = (HealthTextView) inflate.findViewById(R.id.tv_track_detail_xg_title);
        this.l = (HealthTextView) inflate.findViewById(R.id.track_detail_xg);
        this.o = (HealthTextView) inflate.findViewById(R.id.track_detail_effectshow);
        this.b = (HealthTextView) inflate.findViewById(R.id.tv_track_detail_anaerobic_xg_title);
        this.i = (HealthTextView) inflate.findViewById(R.id.track_detail_anaerobic_xg);
        this.m = (HealthTextView) inflate.findViewById(R.id.track_detail_anaerobic_effectshow);
        this.q = (HealthTextView) inflate.findViewById(R.id.sug_detail_ox);
        this.p = (HealthTextView) inflate.findViewById(R.id.sug_detail_ox_unit);
        this.s = (HealthTextView) inflate.findViewById(R.id.track_ox_msg);
        View findViewById = inflate.findViewById(R.id.track_train_divider);
        this.g = findViewById;
        findViewById.setBackgroundColor(gwh.d);
        View findViewById2 = inflate.findViewById(R.id.track_aerobic_train_divider);
        this.f13298a = findViewById2;
        findViewById2.setBackgroundColor(gwh.d);
        View findViewById3 = inflate.findViewById(R.id.track_train_divider_two);
        this.j = findViewById3;
        findViewById3.setBackgroundColor(gwh.d);
        return inflate;
    }

    private void d(HealthTextView healthTextView, int i) {
        healthTextView.setTextColor(i);
    }

    public void d(int i, float f, float f2, int i2, int i3) {
        if (this.f == null) {
            return;
        }
        boolean bc = LanguageUtil.bc(BaseApplication.getContext());
        e(f, bc, i3);
        d(f2, bc, i3);
        c(i, bc);
        a(i2);
    }

    private void c(int i, boolean z) {
        if (i > 0) {
            Integer[] c = hji.c(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getLocalUserInfo());
            this.k.b(i).b(0).c(false).d(gwh.n, gwh.l, gwh.j, gwh.i, gwh.g, gwh.k, gwh.f).d(c).a(z);
            this.p.setVisibility(0);
            if (this.h == 101) {
                this.q.setTextColor(this.d.getResources().getColor(R.color._2131296871_res_0x7f090267));
                this.p.setTextColor(gwh.b);
                this.q.setText(UnitUtil.e(i, 1, 0));
            } else {
                this.q.setTextColor(gwh.h);
                this.p.setTextColor(gwh.c);
                this.q.setText(UnitUtil.e(i, 1, 0));
            }
            HealthTextView healthTextView = this.s;
            Context context = this.d;
            healthTextView.setText(context.getString(R.string._2130839788_res_0x7f0208ec, hji.a(context, c, i, false)));
            return;
        }
        this.f.findViewById(R.id.track_train_vo2max).setVisibility(8);
        this.j.setVisibility(8);
    }

    private void e(float f, boolean z, int i) {
        if (f >= 1.0f) {
            this.l.setText(UnitUtil.e(f, 1, 1));
            float h = hji.h(f);
            this.n.b(h).c(false).d(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f).a(hji.a(this.d, h, i)).a(z);
            if (i == 0) {
                this.c.setText(R.string._2130842764_res_0x7f02148c);
                return;
            } else {
                this.c.setText(R.string._2130844088_res_0x7f0219b8);
                return;
            }
        }
        this.f.findViewById(R.id.track_train_train).setVisibility(8);
        this.g.setVisibility(8);
    }

    private void d(float f, boolean z, int i) {
        if (f >= 1.0f) {
            this.i.setText(UnitUtil.e(f, 1, 1));
            float h = hji.h(f);
            this.e.b(h).c(false).d(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f).a(hji.a(this.d, h, i)).a(z);
            if (i == 0) {
                this.b.setText(R.string._2130842731_res_0x7f02146b);
                return;
            } else {
                this.b.setText(R.string._2130844089_res_0x7f0219b9);
                return;
            }
        }
        this.f.findViewById(R.id.track_anaerobic_train_train).setVisibility(8);
        this.f13298a.setVisibility(8);
    }

    private void a(int i) {
        Context context = this.d;
        if (context != null && i > 0) {
            String quantityString = context.getResources().getQuantityString(R.plurals._2130903133_res_0x7f03005d, i, Integer.valueOf(i));
            View view = this.f;
            if (view != null) {
                ((HealthTextView) view.findViewById(R.id.track_detail_ritire_duration)).setText(hji.bgN_("\\d", quantityString, R.style.track_detail_time_b, R.style.track_detail_time_n, this.d));
                return;
            }
            return;
        }
        View view2 = this.f;
        if (view2 != null) {
            view2.findViewById(R.id.track_detail_ritire).setVisibility(8);
        }
    }
}
