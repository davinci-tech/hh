package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.reportchart.CustomIndicatorView;
import com.huawei.ui.commonui.reportchart.PercentageView;
import com.huawei.ui.commonui.reportchart.SportCardCircleView;
import defpackage.koq;
import defpackage.mjx;
import defpackage.mkf;
import defpackage.mkl;
import defpackage.mkm;
import defpackage.mkp;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class ReportTypeCardView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f8470a;
    private LinearLayout b;
    private PieChart c;
    private LayoutInflater d;
    private LinearLayout e;

    public ReportTypeCardView(Context context) {
        this(context, null);
        this.f8470a = context;
        a();
    }

    public ReportTypeCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet == null) {
            return;
        }
        this.f8470a = context;
        a();
    }

    private void a() {
        Context context = this.f8470a;
        if (context == null) {
            return;
        }
        Object systemService = context.getSystemService("layout_inflater");
        if (!(systemService instanceof LayoutInflater)) {
            LogUtil.h("ReportTypeCardView", "initView mLayoutInflater invalid");
            return;
        }
        LayoutInflater layoutInflater = (LayoutInflater) systemService;
        this.d = layoutInflater;
        clo_(layoutInflater.inflate(R.layout.report_type_pie_view, this));
    }

    private void clo_(View view) {
        this.b = (LinearLayout) view.findViewById(R.id.exercise_type_pie_chart_label);
        this.c = (PieChart) view.findViewById(R.id.exercise_type_pie_chart);
        this.e = (LinearLayout) view.findViewById(R.id.exercise_sport_card);
    }

    public void setSportCardViewData(mkp mkpVar, List<mjx> list, int i) {
        b(mkpVar, list);
        setPieChartLabel(list);
        a(list, i);
    }

    private void b(mkp mkpVar, List<mjx> list) {
        if (koq.b(list)) {
            LogUtil.h("ReportTypeCardView", "setPieChartData reportSportTypeBeanList isEmpty.");
            return;
        }
        this.c.setUsePercentValues(true);
        this.c.getDescription().setEnabled(false);
        this.c.setDragDecelerationFrictionCoef(0.95f);
        setPieChartCenterText(mkpVar);
        this.c.setDrawHoleEnabled(true);
        this.c.setHoleColor(0);
        this.c.setTransparentCircleColor(-1);
        this.c.setTransparentCircleAlpha(110);
        this.c.setHoleRadius(72.0f);
        this.c.setTransparentCircleRadius(0.0f);
        this.c.setDrawCenterText(true);
        this.c.setDrawEntryLabels(false);
        this.c.setDrawRoundedSlices(true);
        this.c.setRotationEnabled(false);
        this.c.getLegend().setEnabled(false);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (mjx mjxVar : list) {
            arrayList.add(new PieEntry(mjxVar.i(), ""));
            arrayList2.add(Integer.valueOf(mjxVar.g()));
        }
        PieDataSet pieDataSet = new PieDataSet(arrayList, "");
        pieDataSet.setSliceSpace(0.0f);
        pieDataSet.setSelectionShift(5.0f);
        pieDataSet.setColors(arrayList2);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11.0f);
        pieData.setValueTextColor(-16777216);
        this.c.setData(pieData);
        this.c.highlightValues(null);
        this.c.invalidate();
    }

    private void setPieChartCenterText(mkp mkpVar) {
        String c = mkpVar.c();
        if (LanguageUtil.m(BaseApplication.e())) {
            String d = mkpVar.d();
            SpannableString spannableString = new SpannableString(c + d + mkpVar.b());
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.report_sport_indicate_name_text), 0, c.length(), 33);
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.report_sport_indicate_content_text), c.length(), c.length() + d.length(), 33);
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.report_sport_indicate_content_content), c.length() + d.length(), spannableString.length(), 33);
            this.c.setCenterText(spannableString);
            return;
        }
        this.c.setCenterTextColor(this.f8470a.getResources().getColor(R.color._2131296676_res_0x7f0901a4));
        this.c.setCenterText(c);
    }

    private void setPieChartLabel(List<mjx> list) {
        if (koq.b(list)) {
            LogUtil.h("ReportTypeCardView", "setPieChartLabel reportSportTypeBeanList isEmpty.");
            return;
        }
        int size = list.size();
        this.b.removeAllViews();
        View inflate = this.d.inflate(R.layout.pie_label_item, (ViewGroup) null);
        for (int i = 0; i < size; i++) {
            int i2 = i % 3;
            if (i2 == 0) {
                inflate = this.d.inflate(R.layout.pie_label_item, (ViewGroup) null);
            }
            TextView textView = (TextView) inflate.findViewById(R.id.pieLeftLabel);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.pieLeftIcon);
            TextView textView2 = (TextView) inflate.findViewById(R.id.pieMiddleLabel);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.pieMiddleIcon);
            TextView textView3 = (TextView) inflate.findViewById(R.id.pieRightLabel);
            ImageView imageView3 = (ImageView) inflate.findViewById(R.id.pieRightIcon);
            Drawable cjx_ = list.get(i).cjx_();
            if (i2 == 0) {
                imageView.setBackground(cjx_);
                textView.setText(list.get(i).h());
                this.b.addView(inflate);
            } else if (i2 == 1) {
                imageView2.setBackground(cjx_);
                textView2.setText(list.get(i).h());
            } else if (i2 == 2) {
                imageView3.setBackground(cjx_);
                textView3.setText(list.get(i).h());
            }
        }
    }

    private void a(List<mjx> list, int i) {
        if (koq.b(list)) {
            return;
        }
        this.e.removeAllViews();
        for (mjx mjxVar : list) {
            if (mjxVar.j() == -1) {
                a(mjxVar, i);
            } else {
                this.e.addView(cln_(mjxVar));
            }
        }
    }

    public static Drawable clu_(Drawable drawable, int i) {
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrap, i);
        return wrap;
    }

    private View cln_(mjx mjxVar) {
        View inflate = this.d.inflate(R.layout.include_sport_type_item, (ViewGroup) null);
        clp_(inflate, mjxVar);
        return inflate;
    }

    private void cls_(LinearLayout linearLayout, List<mkm> list) {
        if (koq.b(list)) {
            LogUtil.h("ReportTypeCardView", "setFitnessLabel reportFitnessList isEmpty.");
            return;
        }
        int size = list.size();
        linearLayout.removeAllViews();
        View inflate = this.d.inflate(R.layout.pie_label_item, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(nsn.c(this.f8470a, 5.0f), nsn.c(this.f8470a, 5.0f));
        layoutParams.setMargins(0, nsn.c(this.f8470a, 8.0f), 0, 0);
        for (int i = 0; i < size; i++) {
            int i2 = i % 3;
            if (i2 == 0) {
                inflate = this.d.inflate(R.layout.pie_label_item, (ViewGroup) null);
            }
            ImageView imageView = (ImageView) inflate.findViewById(R.id.pieLeftIcon);
            imageView.setLayoutParams(layoutParams);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.pieMiddleIcon);
            imageView2.setLayoutParams(layoutParams);
            ImageView imageView3 = (ImageView) inflate.findViewById(R.id.pieRightIcon);
            imageView3.setLayoutParams(layoutParams);
            Drawable clm_ = clm_(list.get(i));
            String d = list.get(i).d();
            if (i2 == 0) {
                imageView.setBackground(clm_);
                ((TextView) inflate.findViewById(R.id.pieLeftLabel)).setText(d);
                linearLayout.addView(inflate);
            } else if (i2 == 1) {
                imageView2.setBackground(clm_);
                ((TextView) inflate.findViewById(R.id.pieMiddleLabel)).setText(d);
            } else if (i2 == 2) {
                imageView3.setBackground(clm_);
                ((TextView) inflate.findViewById(R.id.pieRightLabel)).setText(d);
            }
        }
    }

    private Drawable clm_(mkm mkmVar) {
        Drawable drawable;
        if (mkmVar == null || (drawable = ContextCompat.getDrawable(this.f8470a, mkmVar.c())) == null) {
            return null;
        }
        return clu_(drawable, mkmVar.e());
    }

    private void a(mjx mjxVar, int i) {
        if (mjxVar instanceof mkf) {
            mkf mkfVar = (mkf) mjxVar;
            View inflate = this.d.inflate(R.layout.include_fitness_type_item, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.fitness_count)).setText(String.valueOf(mkfVar.i()));
            ((TextView) inflate.findViewById(R.id.total_fitness_time)).setText(mkfVar.o());
            ((TextView) inflate.findViewById(R.id.longest_fitness_time)).setText(mkfVar.k());
            TextView textView = (TextView) inflate.findViewById(R.id.sport_type_title_label);
            textView.setText(mkfVar.n());
            textView.setBackground(mkfVar.cjv_());
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.cardLayout);
            if (mkfVar.a() != 0) {
                ((GradientDrawable) relativeLayout.getBackground()).setColor(mkfVar.a());
            }
            ((ImageView) inflate.findViewById(R.id.circleView)).setImageDrawable(mkfVar.cjw_());
            int i2 = mkfVar.i();
            clq_((TextView) inflate.findViewById(R.id.fitness_desc), String.valueOf(i2), mkfVar.l(), mkfVar.m(), i);
            List<mkm> t = mkfVar.t();
            ArrayList arrayList = new ArrayList();
            for (mkm mkmVar : t) {
                arrayList.add(new PercentageView.a(mkmVar.b(), mkmVar.e()));
            }
            ((PercentageView) inflate.findViewById(R.id.report_percentage_view)).setData(arrayList);
            cls_((LinearLayout) inflate.findViewById(R.id.fitness_type_label), t);
            clr_((LinearLayout) inflate.findViewById(R.id.exercise_area_layout), mkfVar);
            this.e.addView(inflate);
        }
    }

    private void clq_(TextView textView, String str, String str2, String str3, int i) {
        String str4;
        CharSequence quantityString = BaseApplication.e().getResources().getQuantityString(R.plurals._2130903195_res_0x7f03009b, nsn.e(str), str, str2, str3);
        if (i == 0) {
            quantityString = BaseApplication.e().getResources().getQuantityString(R.plurals._2130903196_res_0x7f03009c, nsn.e(str), str, str2, str3);
            str4 = "本月完成健身课程";
        } else {
            str4 = "本周完成健身课程";
        }
        if (LanguageUtil.m(BaseApplication.e())) {
            String str5 = "“" + str2 + "”";
            String str6 = "“" + str3 + "”";
            SpannableString spannableString = new SpannableString(str4 + str + "次，偏爱" + str5 + "类健身课程，" + str6 + "是你锻炼次数最多的健身课程。");
            int length = str4.length();
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.achieve_fitness_content), 0, length, 33);
            int length2 = str.length() + length;
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.achieve_fitness_value), length, length2, 33);
            int i2 = length2 + 4;
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.achieve_fitness_content), length2, i2, 33);
            int length3 = str5.length() + i2;
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.achieve_fitness_value), i2, length3, 33);
            int i3 = length3 + 6;
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.achieve_fitness_content), length3, i3, 33);
            int length4 = str6.length() + i3;
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.achieve_fitness_value), i3, length4, 33);
            spannableString.setSpan(new TextAppearanceSpan(this.f8470a, R.style.achieve_fitness_content), length4, length4 + 14, 33);
            textView.setText(spannableString);
            return;
        }
        textView.setText(quantityString);
    }

    private void clr_(LinearLayout linearLayout, mkf mkfVar) {
        if (mkfVar == null || koq.b(mkfVar.r())) {
            LogUtil.h("ReportTypeCardView", "setPieChartData reportSportTypeBean is null || AreaList isEmpty.");
            return;
        }
        int size = mkfVar.r().size();
        linearLayout.removeAllViews();
        View inflate = this.d.inflate(R.layout.fitness_area_item, (ViewGroup) null);
        for (int i = 0; i < size; i++) {
            int i2 = i % 3;
            if (i2 == 0) {
                inflate = this.d.inflate(R.layout.fitness_area_item, (ViewGroup) null);
            }
            TextView textView = (TextView) inflate.findViewById(R.id.pieLeftLabel);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.pieLeftIcon);
            TextView textView2 = (TextView) inflate.findViewById(R.id.pieMiddleLabel);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.pieMiddleIcon);
            TextView textView3 = (TextView) inflate.findViewById(R.id.pieRightLabel);
            ImageView imageView3 = (ImageView) inflate.findViewById(R.id.pieRightIcon);
            int intValue = mkfVar.r().get(i).intValue();
            if (i2 == 0) {
                imageView.setBackgroundResource(intValue);
                textView.setText(mkfVar.q().get(i));
                linearLayout.addView(inflate);
            } else if (i2 == 1) {
                imageView2.setBackgroundResource(intValue);
                textView2.setText(mkfVar.q().get(i));
            } else if (i2 == 2) {
                imageView3.setBackgroundResource(intValue);
                textView3.setText(mkfVar.q().get(i));
            }
        }
    }

    private void clp_(View view, mjx mjxVar) {
        if (mjxVar instanceof mkl) {
            mkl mklVar = (mkl) mjxVar;
            TextView textView = (TextView) view.findViewById(R.id.sport_type_title_label);
            textView.setText(mklVar.n());
            textView.setBackground(mklVar.cjv_());
            if (mklVar.a() != 0) {
                Drawable background = view.findViewById(R.id.cardLayout).getBackground();
                if (background instanceof GradientDrawable) {
                    ((GradientDrawable) background).setColor(mklVar.a());
                }
            }
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.circleViewLayout);
            if (mklVar.cjw_() == null) {
                relativeLayout.setVisibility(0);
                ((ImageView) view.findViewById(R.id.sport_type_body_icon)).setImageDrawable(mklVar.cju_());
                ((SportCardCircleView) view.findViewById(R.id.sport_circle_view)).setCircle(mklVar.d());
            } else {
                relativeLayout.setVisibility(8);
                ((ImageView) view.findViewById(R.id.circleView)).setImageDrawable(mklVar.cjw_());
            }
            clt_((LinearLayout) view.findViewById(R.id.sport_item_layout), mklVar.m());
        }
    }

    private void clt_(LinearLayout linearLayout, List<mkp> list) {
        if (koq.b(list)) {
            LogUtil.h("ReportTypeCardView", "setLabelLayout sportItemList isEmpty.");
            return;
        }
        int size = list.size();
        linearLayout.removeAllViews();
        View inflate = this.d.inflate(R.layout.sport_label_item, (ViewGroup) null);
        for (int i = 0; i < size; i++) {
            int i2 = i % 2;
            if (i2 == 0) {
                inflate = this.d.inflate(R.layout.sport_label_item, (ViewGroup) null);
            }
            CustomIndicatorView customIndicatorView = (CustomIndicatorView) inflate.findViewById(R.id.sportLeftLabel);
            CustomIndicatorView customIndicatorView2 = (CustomIndicatorView) inflate.findViewById(R.id.sportRightLabel);
            if (i2 == 0) {
                a(customIndicatorView, list.get(i));
                linearLayout.addView(inflate);
            } else if (i2 == 1) {
                a(customIndicatorView2, list.get(i));
            }
        }
    }

    private void a(CustomIndicatorView customIndicatorView, mkp mkpVar) {
        if (mkpVar == null) {
            return;
        }
        if (!TextUtils.isEmpty(mkpVar.e())) {
            customIndicatorView.setIndicatorTitle(mkpVar.e());
        }
        if (!TextUtils.isEmpty(mkpVar.c())) {
            customIndicatorView.setIndicatorValue(mkpVar.c());
        }
        if (!TextUtils.isEmpty(mkpVar.d())) {
            customIndicatorView.setIndicatorUnit(mkpVar.d());
        }
        if (TextUtils.isEmpty(mkpVar.b())) {
            return;
        }
        customIndicatorView.setIndicatorDesc(mkpVar.b());
    }
}
