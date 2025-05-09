package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import com.huawei.ui.main.stories.health.views.healthdata.VerticalTextView;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class qto extends qta {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f16584a;
    private cfe ac;
    private ImageView b;
    private LinearLayout c;
    private ImageView d;
    private ImageView f;
    private HealthTextView g;
    private HealthTextView h;
    private ImageView i;
    private HealthTextView j;
    private HealthTextView k;
    private LinearLayout l;
    private HealthTextView m;
    private ImageView n;
    private ImageView o;
    private ImageView p;
    private ImageView q;
    private ImageView r;
    private HealthTextView s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private BodyReportRecycleItem w;
    private Context x;
    private ImageView y;

    public qto(Context context, BodyReportRecycleItem bodyReportRecycleItem) {
        super(context, bodyReportRecycleItem);
        this.x = context;
        this.w = bodyReportRecycleItem;
        if (bodyReportRecycleItem != null) {
            this.ac = bodyReportRecycleItem.b();
        }
    }

    @Override // defpackage.qta
    public String b() {
        if (this.x == null) {
            return super.b();
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hw_weight_body_type_interpretation);
    }

    @Override // defpackage.qta
    public View dIW_() {
        Context context = this.x;
        if (context == null) {
            return super.dIW_();
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.body_shape_predication_layout, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.fragment_weight_body_data_body_type_fat);
        VerticalTextView verticalTextView = (VerticalTextView) inflate.findViewById(R.id.fragment_weight_body_data_body_type_fat_other);
        String language = this.x.getResources().getConfiguration().locale.getLanguage();
        if (LanguageUtil.j(this.x) || "ja".equalsIgnoreCase(language)) {
            verticalTextView.setVisibility(8);
            healthTextView.setVisibility(0);
        } else {
            verticalTextView.setVisibility(0);
            verticalTextView.setDirection(!LanguageUtil.bc(this.x) ? 1 : 0);
            healthTextView.setVisibility(8);
        }
        this.r = (ImageView) inflate.findViewById(R.id.body_type_img_01);
        this.y = (ImageView) inflate.findViewById(R.id.body_type_img_02);
        this.i = (ImageView) inflate.findViewById(R.id.body_type_img_03);
        this.d = (ImageView) inflate.findViewById(R.id.body_type_img_04);
        this.q = (ImageView) inflate.findViewById(R.id.body_type_img_05);
        this.p = (ImageView) inflate.findViewById(R.id.body_type_img_06);
        this.o = (ImageView) inflate.findViewById(R.id.body_type_img_07);
        this.n = (ImageView) inflate.findViewById(R.id.body_type_img_08);
        this.f = (ImageView) inflate.findViewById(R.id.body_type_img_09);
        this.v = (HealthTextView) inflate.findViewById(R.id.body_type_tv_01);
        this.u = (HealthTextView) inflate.findViewById(R.id.body_type_tv_02);
        this.h = (HealthTextView) inflate.findViewById(R.id.body_type_tv_03);
        this.j = (HealthTextView) inflate.findViewById(R.id.body_type_tv_04);
        this.t = (HealthTextView) inflate.findViewById(R.id.body_type_tv_05);
        this.s = (HealthTextView) inflate.findViewById(R.id.body_type_tv_06);
        this.k = (HealthTextView) inflate.findViewById(R.id.body_type_tv_07);
        this.m = (HealthTextView) inflate.findViewById(R.id.body_type_tv_08);
        this.g = (HealthTextView) inflate.findViewById(R.id.body_type_tv_09);
        this.l = (LinearLayout) inflate.findViewById(R.id.fragment_weight_body_data_root_view);
        this.c = (LinearLayout) inflate.findViewById(R.id.fragment_weight_body_data_body_shape_content);
        this.b = (ImageView) inflate.findViewById(R.id.body_analysis_report_shape_show_img);
        this.f16584a = (HealthTextView) inflate.findViewById(R.id.body_analysis_report_shape_show_tv);
        g();
        a();
        return inflate;
    }

    private void g() {
        this.v.setText(qqy.a(1));
        this.u.setText(qqy.a(2));
        this.h.setText(qqy.a(3));
        this.j.setText(qqy.a(4));
        this.t.setText(qqy.a(5));
        this.s.setText(qqy.a(6));
        this.k.setText(qqy.a(7));
        this.m.setText(qqy.a(8));
        this.g.setText(qqy.a(9));
        int e = qsj.e(this.ac);
        if (!dph.d(e) || e == 0) {
            return;
        }
        c(e);
    }

    private void c(int i) {
        switch (i) {
            case 1:
                this.r.setImageResource(R.mipmap._2131820829_res_0x7f11011d);
                this.v.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
                break;
            case 2:
                this.y.setImageResource(R.mipmap._2131820831_res_0x7f11011f);
                this.u.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
                break;
            case 3:
                this.i.setImageResource(R.mipmap._2131820833_res_0x7f110121);
                this.h.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
                break;
            case 4:
                this.d.setImageResource(R.mipmap._2131820835_res_0x7f110123);
                this.j.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
                break;
            case 5:
                this.q.setImageResource(R.mipmap._2131820837_res_0x7f110125);
                this.t.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
                break;
            case 6:
                this.p.setImageResource(R.mipmap._2131820839_res_0x7f110127);
                this.s.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
                break;
            default:
                a(i);
                break;
        }
    }

    private void a(int i) {
        if (i == 7) {
            this.o.setImageResource(R.mipmap._2131820841_res_0x7f110129);
            this.k.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
        } else if (i == 8) {
            this.n.setImageResource(R.mipmap._2131820843_res_0x7f11012b);
            this.m.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
        } else if (i == 9) {
            this.f.setImageResource(R.mipmap._2131820845_res_0x7f11012d);
            this.g.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298156_res_0x7f09076c));
        } else {
            LogUtil.h("BodyReportPhysiquePredicationView", "currentSelectionOther bodyType does not exist");
        }
    }

    private void a() {
        cfe cfeVar = this.ac;
        if (cfeVar == null) {
            LogUtil.h("BodyReportPhysiquePredicationView", "initBodyShape WeightBean is null");
            return;
        }
        if ("b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(cfeVar.getWeightScaleProductId()) && this.ac.e() < 18) {
            this.l.setGravity(17);
            this.c.setVisibility(8);
            return;
        }
        this.l.setGravity(51);
        this.c.setVisibility(0);
        int d = doj.d(this.ac.an(), this.ac.f());
        if (dph.c(d)) {
            this.f16584a.setText(qqy.d(d));
            this.b.setImageResource(qrf.b(d));
        }
    }

    @Override // defpackage.qta
    public BodyReportRecycleItem d() {
        BodyReportRecycleItem bodyReportRecycleItem = this.w;
        return bodyReportRecycleItem == null ? super.d() : bodyReportRecycleItem;
    }
}
