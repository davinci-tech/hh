package com.huawei.ui.main.stories.fitness.activity.calorie;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import com.huawei.watchface.videoedit.gles.Constant;
import defpackage.pvj;
import defpackage.pvm;
import health.compact.a.LanguageUtil;
import java.util.Locale;

/* loaded from: classes9.dex */
public class CalorieConvertedFoodView extends ScrollChartObserverTotalDataView {

    /* renamed from: a, reason: collision with root package name */
    private Context f9768a;
    private HealthTextView b;
    private HealthTextView c;
    private RelativeLayout d;
    private HealthTextView e;
    private boolean f;
    private ImageView g;
    private pvj i;
    private HealthTextView j;

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView
    public void c(float f) {
        b(f);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void initView(String str, String str2) {
        inflate(getContext(), R.layout.calorie_converted_food_view, this);
        this.f = LanguageUtil.j(getContext());
        this.b = (HealthTextView) findViewById(R.id.fitness_detail_average_data_day_comment);
        this.d = (RelativeLayout) findViewById(R.id.fitness_detail_day_comment_zh);
        this.c = (HealthTextView) findViewById(R.id.fitness_detail_average_data_day_comment_zh);
        this.g = (ImageView) findViewById(R.id.fitness_detail_day_food);
        this.j = (HealthTextView) findViewById(R.id.fitness_detail_day_food_num);
        this.e = (HealthTextView) findViewById(R.id.fitness_detail_average_data_day_comment_less);
        this.b.setVisibility(0);
        this.i = new pvj(-1, true);
    }

    private void b(float f) {
        int round = Math.round(f) * 1000;
        CharSequence e = pvm.e(this.f9768a, round, this.i);
        LogUtil.a("CalorieConvertedFoodView", " updateComment total", Integer.valueOf(round), Constant.TEXT, e.toString());
        if (!this.f) {
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            this.b.setVisibility(0);
            this.b.setText(e);
            return;
        }
        int d = this.i.d();
        int i = d != 0 ? d != 1 ? d != 2 ? d != 3 ? -1 : R.drawable._2131431589_res_0x7f0b10a5 : R.drawable._2131431591_res_0x7f0b10a7 : R.drawable._2131431592_res_0x7f0b10a8 : R.drawable._2131431590_res_0x7f0b10a6;
        if (i < 0) {
            this.d.setVisibility(8);
            this.b.setVisibility(8);
            this.e.setVisibility(0);
            this.e.setText(e);
            return;
        }
        this.d.setVisibility(0);
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.c.setText(e);
        this.g.setImageResource(i);
        this.j.setText(String.format(Locale.ROOT, "X%1d", Integer.valueOf(this.i.b())));
    }
}
