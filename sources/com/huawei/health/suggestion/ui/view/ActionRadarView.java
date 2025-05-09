package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.view.RadarView;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ActionRadarView extends LinearLayout {
    private Context b;
    private RadarView e;

    public ActionRadarView(Context context) {
        this(context, null);
    }

    public ActionRadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionRadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        a();
    }

    private void a() {
        this.e = (RadarView) View.inflate(this.b, R.layout.action_radar_layout, this).findViewById(R.id.radar_view);
    }

    public void setData(double[] dArr, float f) {
        if (dArr == null || dArr.length != 6) {
            LogUtil.h("ActionRadarView", "scores is null or scores.length not equals 6");
            return;
        }
        this.e.setCount(6);
        this.e.setTextColorAndSize(getResources().getColor(R$color.textColorSecondary), getResources().getDimensionPixelSize(R.dimen._2131365063_res_0x7f0a0cc7));
        this.e.setNumberColorAndSize(getResources().getColor(R$color.textColorSecondary), getResources().getDimensionPixelSize(R.dimen._2131365061_res_0x7f0a0cc5));
        this.e.setIsActionRadar(true);
        String[] strArr = {getResources().getString(R$string.IDS_fitness_radar_coordination), getResources().getString(R$string.IDS_fitness_radar_technique), getResources().getString(R$string.IDS_fitness_radar_completeness), getResources().getString(R$string.IDS_fitness_radar_aesthetics), getResources().getString(R$string.IDS_fitness_radar_softness), getResources().getString(R$string.IDS_fitness_radar_balance)};
        double[] dArr2 = new double[6];
        for (int i = 0; i < 6; i++) {
            dArr2[i] = dArr[i] / 100.0d;
        }
        this.e.setPercentOfMap(dArr2, strArr);
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(Integer.valueOf(getResources().getColor(R$color.colorBackground)));
        arrayList.add(Integer.valueOf(getResources().getColor(R$color.colorBackground)));
        arrayList.add(Integer.valueOf(getResources().getColor(R$color.colorBackground)));
        arrayList.add(Integer.valueOf(getResources().getColor(R$color.colorBackground)));
        this.e.setColorList(arrayList);
        this.e.setAverageScore(Math.round(f));
    }
}
