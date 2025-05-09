package defpackage;

import android.content.Context;
import com.github.mikephil.charting.components.Legend;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;

/* loaded from: classes6.dex */
public class nos {
    public static void a(Context context, Legend legend) {
        if (legend == null) {
            LogUtil.h("HealthChat_HwSingleLegendUtil", "custom legend == null");
            return;
        }
        legend.setTextColor(nrn.d(R$color.textColorSecondary));
        legend.setTextSize(10.0f);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
    }
}
