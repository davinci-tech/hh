package com.huawei.ui.main.stories.health.fragment.weightbodydata;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import com.huawei.ui.main.stories.health.fragment.weightbodydata.BodyTypeFragment;
import com.huawei.ui.main.stories.health.views.healthdata.VerticalTextView;
import defpackage.dph;
import defpackage.qqy;
import defpackage.qrd;
import defpackage.qsj;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Date;

/* loaded from: classes6.dex */
public class BodyTypeFragment extends WeightBodyDataFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10195a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private HealthTextView e;
    private ImageView f;
    private ImageView g;
    private HealthTextView h;
    private HealthTextView i;
    private ImageView j;
    private HealthTextView k;
    private ImageView l;
    private ImageView m;
    private HealthTextView n;
    private HealthTextView o;
    private int p;
    private ImageView q;
    private HealthTextView r;
    private HealthTextView s;
    private ImageView t;
    private VerticalTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private HealthTextView x;

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getBiType() {
        return 13;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public int getLayoutId() {
        return R.layout.fragment_weight_body_data_body_type;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initSpecification() {
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initView(View view) {
        this.d = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type);
        this.w = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_status);
        this.x = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_status_description);
        this.v = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_fat);
        this.u = (VerticalTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_fat_other);
        this.t = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_01);
        this.q = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_02);
        this.b = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_03);
        this.c = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_04);
        this.m = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_05);
        this.l = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_06);
        this.j = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_07);
        this.g = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_08);
        this.f = (ImageView) view.findViewById(R.id.fragment_weight_body_data_body_type_img_09);
        this.s = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_01);
        this.r = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_02);
        this.f10195a = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_03);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_04);
        this.e = healthTextView;
        healthTextView.setSplittable(true);
        this.n = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_05);
        this.k = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_06);
        this.o = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_07);
        this.h = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_08);
        this.i = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_tv_09);
        ((HealthSubHeader) view.findViewById(R.id.fragment_weight_body_type_sub_header)).setSubHeaderBackgroundColor(getColor(R.color._2131299296_res_0x7f090be0));
        initResultViewAndAboutView(view);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_weight_body_data_body_type_fat_option_card);
        ImageView imageView = (ImageView) view.findViewById(R.id.health_body_type_progressbar);
        if (LanguageUtil.bc(this.mContext)) {
            imageView.setScaleX(-1.0f);
        }
        dEB_(linearLayout, imageView);
        ((HealthTextView) view.findViewById(R.id.fragment_weight_body_data_body_type_abscissa_title)).setSplittable(true);
    }

    private void dEB_(final LinearLayout linearLayout, final ImageView imageView) {
        imageView.post(new Runnable() { // from class: qjr
            @Override // java.lang.Runnable
            public final void run() {
                BodyTypeFragment.dEA_(linearLayout, imageView);
            }
        });
    }

    public static /* synthetic */ void dEA_(LinearLayout linearLayout, ImageView imageView) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        int min = Math.min(imageView.getWidth(), imageView.getHeight());
        layoutParams.width = min;
        layoutParams.height = min;
        layoutParams.gravity = 17;
        linearLayout.setGravity(17);
        linearLayout.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initData() {
        String string;
        this.s.setText(qqy.a(1));
        this.r.setText(qqy.a(2));
        this.f10195a.setText(qqy.a(3));
        this.e.setText(qqy.a(4));
        this.n.setText(qqy.a(5));
        this.k.setText(qqy.a(6));
        this.o.setText(qqy.a(7));
        this.h.setText(qqy.a(8));
        this.i.setText(qqy.a(9));
        int e = qsj.e(this.mWeightBean);
        this.p = e;
        if (dph.d(e)) {
            if (this.mIsShowChange && !this.mIsOversea && CommonUtil.bv()) {
                int i = this.p;
                if (i == 7 || i == 4 || i == 8) {
                    string = this.mResources.getString(R$string.IDS_hw_weight_body_type_interpretation_negative);
                } else {
                    string = this.mResources.getString(R$string.IDS_hw_weight_body_type_interpretation_positive);
                }
                this.x.setText(String.format(string, UnitUtil.a(new Date(this.mStartTime), 16), UnitUtil.a(new Date(this.mWeightBean.au()), 16), qqy.a(this.mStartType), qqy.a(this.p)));
                this.x.setVisibility(0);
            }
            this.w.setText(qqy.a(this.p));
            a();
        }
    }

    private void a() {
        switch (this.p) {
            case 1:
                this.t.setImageResource(R.mipmap._2131820829_res_0x7f11011d);
                this.d.setImageResource(R.mipmap._2131820829_res_0x7f11011d);
                this.s.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
                break;
            case 2:
                this.q.setImageResource(R.mipmap._2131820831_res_0x7f11011f);
                this.d.setImageResource(R.mipmap._2131820831_res_0x7f11011f);
                this.r.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
                break;
            case 3:
                this.b.setImageResource(R.mipmap._2131820833_res_0x7f110121);
                this.d.setImageResource(R.mipmap._2131820833_res_0x7f110121);
                this.f10195a.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
                break;
            case 4:
                this.c.setImageResource(R.mipmap._2131820835_res_0x7f110123);
                this.d.setImageResource(R.mipmap._2131820835_res_0x7f110123);
                this.e.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
                break;
            case 5:
                this.m.setImageResource(R.mipmap._2131820837_res_0x7f110125);
                this.d.setImageResource(R.mipmap._2131820837_res_0x7f110125);
                this.n.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
                break;
            case 6:
                this.l.setImageResource(R.mipmap._2131820839_res_0x7f110127);
                this.d.setImageResource(R.mipmap._2131820839_res_0x7f110127);
                this.k.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
                break;
            default:
                b();
                break;
        }
    }

    private void b() {
        int i = this.p;
        if (i == 7) {
            this.j.setImageResource(R.mipmap._2131820841_res_0x7f110129);
            this.d.setImageResource(R.mipmap._2131820841_res_0x7f110129);
            this.o.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
        } else if (i == 8) {
            this.g.setImageResource(R.mipmap._2131820843_res_0x7f11012b);
            this.d.setImageResource(R.mipmap._2131820843_res_0x7f11012b);
            this.h.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
        } else {
            if (i == 9) {
                this.f.setImageResource(R.mipmap._2131820845_res_0x7f11012d);
                this.d.setImageResource(R.mipmap._2131820845_res_0x7f11012d);
                this.i.setTextColor(ContextCompat.getColor(this.mContext, R.color._2131299236_res_0x7f090ba4));
                return;
            }
            LogUtil.h("HealthWeight_BodyTypeFragment", "currentSelectionOther mBodyTypeValue does not exist");
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment
    public void initAnalysisAndInterpretation() {
        initResult(qqy.e(1, this.p), qqy.e(2, this.p));
        initAbout(qrd.c(0), qrd.c(1));
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (LanguageUtil.j(this.mContext) || "ja".equalsIgnoreCase(this.mResources.getConfiguration().locale.getLanguage())) {
            this.u.setVisibility(8);
            this.v.setVisibility(0);
        } else {
            this.u.setVisibility(0);
            this.u.setDirection(!LanguageUtil.bc(this.mContext) ? 1 : 0);
            this.v.setVisibility(8);
        }
    }
}
