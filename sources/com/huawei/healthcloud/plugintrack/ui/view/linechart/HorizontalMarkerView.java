package com.huawei.healthcloud.plugintrack.ui.view.linechart;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.view.GravityCompat;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import defpackage.noy;
import defpackage.nrs;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class HorizontalMarkerView extends FrameLayout {
    private int c;
    private int e;

    public HorizontalMarkerView(Context context) {
        super(context);
        this.e = 0;
        this.c = 0;
    }

    public void e(String str, HwHealthMarkerView.a aVar) {
        if (str == null || aVar == null) {
            return;
        }
        if (this.e != 1) {
            removeAllViews();
            inflate(getContext(), R.layout.track_horizontal_markerview_one, this);
            this.e = 1;
        }
        d((HealthTextView) findViewById(R.id.time), str);
        e((HealthTextView) findViewById(R.id.data), (HealthTextView) findViewById(R.id.unit), aVar, aVar.d);
    }

    public void c(String str, String str2, String str3) {
        if (str == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        if (this.e != 1) {
            removeAllViews();
            inflate(getContext(), R.layout.track_horizontal_markerview_one, this);
            this.e = 1;
        }
        d((HealthTextView) findViewById(R.id.time), str);
        e((HealthTextView) findViewById(R.id.data), (HealthTextView) findViewById(R.id.unit), str2, str3);
    }

    public void a(String str, HwHealthMarkerView.a aVar, HwHealthMarkerView.a aVar2) {
        if (str == null || aVar == null || aVar2 == null) {
            return;
        }
        if (this.e != 2) {
            removeAllViews();
            inflate(getContext(), R.layout.track_horizontal_markerview_two, this);
            this.e = 2;
        }
        ImageView imageView = (ImageView) findViewById(R.id.legend1);
        ImageView imageView2 = (ImageView) findViewById(R.id.legend2);
        bkh_(imageView, aVar.e, aVar.b);
        bkh_(imageView2, aVar2.e, aVar2.b);
        d((HealthTextView) findViewById(R.id.time), str);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.data1);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.data2);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.unit1);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.unit2);
        e(healthTextView, healthTextView3, aVar, aVar.d);
        e(healthTextView2, healthTextView4, aVar2, aVar2.d);
        a();
    }

    public void a(String str, HwHealthMarkerView.a aVar, HwHealthMarkerView.a aVar2, HwHealthMarkerView.a aVar3) {
        if (str == null || aVar == null || aVar2 == null || aVar3 == null) {
            return;
        }
        if (this.e != 3) {
            removeAllViews();
            inflate(getContext(), R.layout.track_horizontal_markerview_three, this);
            this.c = 0;
            this.e = 3;
        }
        ImageView imageView = (ImageView) findViewById(R.id.legend1);
        ImageView imageView2 = (ImageView) findViewById(R.id.legend2);
        ImageView imageView3 = (ImageView) findViewById(R.id.legend3);
        bkh_(imageView, aVar.e, aVar.b);
        bkh_(imageView2, aVar2.e, aVar2.b);
        bkh_(imageView3, aVar3.e, aVar3.b);
        d((HealthTextView) findViewById(R.id.time), str);
        c(aVar, aVar2, aVar3);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.data1);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.data2);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.data3);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.unit1);
        HealthTextView healthTextView5 = (HealthTextView) findViewById(R.id.unit2);
        HealthTextView healthTextView6 = (HealthTextView) findViewById(R.id.unit3);
        e(healthTextView, healthTextView4, aVar, aVar.d);
        e(healthTextView2, healthTextView5, aVar2, aVar2.d);
        e(healthTextView3, healthTextView6, aVar3, aVar3.d);
        bkf_(this);
    }

    private void c(HwHealthMarkerView.a aVar, HwHealthMarkerView.a aVar2, HwHealthMarkerView.a aVar3) {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.desc1);
        if (aVar.e instanceof HwHealthLineDataSet) {
            d(healthTextView, ((HwHealthLineDataSet) aVar.e).c().d());
        } else if (aVar.e instanceof HwHealthBarDataSet) {
            d(healthTextView, ((HwHealthBarDataSet) aVar.e).d().d());
        }
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.desc2);
        if (aVar2.e instanceof HwHealthLineDataSet) {
            d(healthTextView2, ((HwHealthLineDataSet) aVar2.e).c().d());
        } else if (aVar2.e instanceof HwHealthBarDataSet) {
            d(healthTextView2, ((HwHealthBarDataSet) aVar2.e).d().d());
        }
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.desc3);
        if (aVar3.e instanceof HwHealthLineDataSet) {
            d(healthTextView3, ((HwHealthLineDataSet) aVar3.e).c().d());
        } else if (aVar3.e instanceof HwHealthBarDataSet) {
            d(healthTextView3, ((HwHealthBarDataSet) aVar3.e).d().d());
        }
    }

    private void a() {
        if (LanguageUtil.v(getContext())) {
            float convertDpToPixel = Utils.convertDpToPixel(20.0f);
            float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131365063_res_0x7f0a0cc7);
            ((HealthTextView) findViewById(R.id.time)).setTextSize(0, dimensionPixelSize);
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.data1);
            HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.data2);
            healthTextView.setTextSize(0, convertDpToPixel);
            healthTextView2.setTextSize(0, convertDpToPixel);
            HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.unit1);
            HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.unit2);
            healthTextView3.setTextSize(0, dimensionPixelSize);
            healthTextView4.setTextSize(0, dimensionPixelSize);
        }
    }

    private void bkf_(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.timer_layout);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.marker_data_Layout);
        linearLayout.measure(0, 0);
        linearLayout2.measure(0, 0);
        float c = nrs.c(getContext());
        if (linearLayout.getMeasuredWidth() + (linearLayout2.getMeasuredWidth() / 2.0f) + Utils.convertDpToPixel(8.0f) < c / 2.0f) {
            findViewById(R.id.marker_data_father_Layout).setPaddingRelative(0, 0, 0, 0);
            ViewGroup.LayoutParams layoutParams = linearLayout2.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).gravity = 17;
                return;
            }
            return;
        }
        if (linearLayout.getMeasuredWidth() + linearLayout2.getMeasuredWidth() + Utils.convertDpToPixel(8.0f) < c - getResources().getDimension(R.dimen._2131364634_res_0x7f0a0b1a)) {
            findViewById(R.id.marker_data_father_Layout).setPaddingRelative((int) (linearLayout.getMeasuredWidth() + Utils.convertDpToPixel(8.0f)), 0, 0, 0);
            ViewGroup.LayoutParams layoutParams2 = linearLayout2.getLayoutParams();
            if (layoutParams2 instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams2).gravity = GravityCompat.START;
                return;
            }
            return;
        }
        int i = this.c;
        if (i >= 2) {
            return;
        }
        int i2 = i + 1;
        this.c = i2;
        c(i2);
    }

    private void c(int i) {
        float convertDpToPixel;
        int dimensionPixelSize;
        if (i == 0) {
            convertDpToPixel = Utils.convertDpToPixel(24.0f);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6);
        } else if (i == 1) {
            convertDpToPixel = Utils.convertDpToPixel(20.0f);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131365063_res_0x7f0a0cc7);
        } else {
            if (i != 2) {
                return;
            }
            convertDpToPixel = Utils.convertDpToPixel(18.0f);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131365066_res_0x7f0a0cca);
        }
        float f = dimensionPixelSize;
        ((HealthTextView) findViewById(R.id.time)).setTextSize(0, f);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.desc1);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.desc2);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.desc3);
        healthTextView.setTextSize(0, f);
        healthTextView2.setTextSize(0, f);
        healthTextView3.setTextSize(0, f);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.data1);
        HealthTextView healthTextView5 = (HealthTextView) findViewById(R.id.data2);
        HealthTextView healthTextView6 = (HealthTextView) findViewById(R.id.data3);
        healthTextView4.setTextSize(0, convertDpToPixel);
        healthTextView5.setTextSize(0, convertDpToPixel);
        healthTextView6.setTextSize(0, convertDpToPixel);
        HealthTextView healthTextView7 = (HealthTextView) findViewById(R.id.unit1);
        HealthTextView healthTextView8 = (HealthTextView) findViewById(R.id.unit2);
        HealthTextView healthTextView9 = (HealthTextView) findViewById(R.id.unit3);
        healthTextView7.setTextSize(0, f);
        healthTextView8.setTextSize(0, f);
        healthTextView9.setTextSize(0, f);
        bkf_(this);
    }

    private void bkh_(ImageView imageView, HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, HwHealthBaseEntry hwHealthBaseEntry) {
        if (imageView == null || hwHealthBaseBarLineDataSet == null) {
            return;
        }
        if ((hwHealthBaseBarLineDataSet instanceof HwHealthBarDataSet) && ((HwHealthBarDataSet) hwHealthBaseBarLineDataSet).getDrawColorMode() == HwHealthBarDataSet.DrawColorMode.DATA_COLOR) {
            if (hwHealthBaseEntry instanceof HwHealthBarEntry) {
                bkg_(imageView, noy.b(((HwHealthBarEntry) hwHealthBaseEntry).acquireModel()));
                return;
            } else {
                bkg_(imageView, 0);
                return;
            }
        }
        bkg_(imageView, hwHealthBaseBarLineDataSet.getColor());
    }

    private void bkg_(ImageView imageView, int i) {
        float convertDpToPixel = Utils.convertDpToPixel(8.0f) / 2.0f;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel}, new RectF(0.0f, 0.0f, 0.0f, 0.0f), new float[]{convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel, convertDpToPixel}));
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        imageView.setBackgroundDrawable(shapeDrawable);
    }

    private void d(HealthTextView healthTextView, String str) {
        if (healthTextView == null || TextUtils.equals(healthTextView.getText(), str)) {
            return;
        }
        healthTextView.setText(str);
    }

    private void bkj_(HealthTextView healthTextView, SpannableString spannableString) {
        if (healthTextView == null || TextUtils.equals(healthTextView.getText(), spannableString)) {
            return;
        }
        healthTextView.setText(spannableString);
    }

    protected String d(float f, IAxisValueFormatter iAxisValueFormatter) {
        return iAxisValueFormatter == null ? "" : iAxisValueFormatter.getFormattedValue(f, null);
    }

    private void a(HealthTextView healthTextView, HwHealthMarkerView.a aVar) {
        if (aVar.f8900a) {
            int d = d(aVar.e);
            if (d > 0) {
                b(healthTextView, aVar, d);
                return;
            } else {
                d(healthTextView, d(aVar.b.getY(), aVar.c));
                return;
            }
        }
        d(healthTextView, "--");
    }

    private void b(HealthTextView healthTextView, HwHealthMarkerView.a aVar, int i) {
        if (aVar.e.getLabel().equals(getContext().getResources().getString(R$string.IDS_bolt_balance_left_right_touches))) {
            String e = UnitUtil.e(aVar.b.getY(), 2, 1);
            String e2 = UnitUtil.e(100.0f - aVar.b.getY(), 2, 1);
            String string = getResources().getString(R$string.IDS_bolt_balance_rate_left_right_value, e, e2);
            int indexOf = string.indexOf(e);
            int length = e.length();
            int lastIndexOf = string.lastIndexOf(e2);
            int length2 = e2.length();
            SpannableString spannableString = new SpannableString(string);
            if (LanguageUtil.b(getContext())) {
                bki_(0, lastIndexOf, spannableString);
                bki_(lastIndexOf + length2 + 1, indexOf, spannableString);
            } else {
                bki_(0, indexOf, spannableString);
                bki_(indexOf + length + 1, lastIndexOf, spannableString);
            }
            bkj_(healthTextView, spannableString);
            return;
        }
        d(healthTextView, UnitUtil.e(aVar.b.getY(), 1, i));
    }

    private void bki_(int i, int i2, SpannableString spannableString) {
        if (i < 0 || i >= i2 || i2 >= spannableString.length()) {
            return;
        }
        spannableString.setSpan(new AbsoluteSizeSpan(14, true), i, i2, 33);
    }

    private int d(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet) {
        if (hwHealthBaseBarLineDataSet != null && (hwHealthBaseBarLineDataSet instanceof HwHealthLineDataSet)) {
            return ((HwHealthLineDataSet) hwHealthBaseBarLineDataSet).acquireMarkViewTextDigitalCount();
        }
        return 0;
    }

    private void e(HealthTextView healthTextView, HealthTextView healthTextView2, HwHealthMarkerView.a aVar, String str) {
        boolean z = LanguageUtil.bp(getContext()) || LanguageUtil.y(getContext());
        boolean equals = str.equals(getContext().getResources().getString(R.string._2130842716_res_0x7f02145c));
        if (z && equals) {
            d(healthTextView, str);
            a(healthTextView2, aVar);
            healthTextView.setTextSize(0, b(14.0f));
            healthTextView2.setTextSize(0, b(24.0f));
            return;
        }
        a(healthTextView, aVar);
        d(healthTextView2, str);
    }

    private void e(HealthTextView healthTextView, HealthTextView healthTextView2, String str, String str2) {
        boolean z = LanguageUtil.bp(getContext()) || LanguageUtil.y(getContext());
        boolean equals = str2.equals(getContext().getResources().getString(R.string._2130842716_res_0x7f02145c));
        if (z && equals) {
            d(healthTextView, str2);
            d(healthTextView2, str);
            healthTextView.setTextSize(0, b(14.0f));
            healthTextView2.setTextSize(0, b(24.0f));
            return;
        }
        d(healthTextView, str);
        d(healthTextView2, str2);
    }

    private int b(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
