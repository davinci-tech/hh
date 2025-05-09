package com.huawei.ui.main.stories.health.views.healthdata;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.nsn;
import defpackage.qqy;
import health.compact.a.UnitUtil;
import java.util.Date;

/* loaded from: classes7.dex */
public class BodyTypeCardView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private LayoutInflater f10273a;
    private Context b;
    private HealthTextView c;
    private ImageView d;
    private HealthTextView e;

    public BodyTypeCardView(Context context) {
        super(context);
    }

    public BodyTypeCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        LayoutInflater from = LayoutInflater.from(context);
        this.f10273a = from;
        View inflate = from.inflate(R.layout.layout_body_type_card_info, (ViewGroup) this, false);
        addView(inflate);
        dIK_(inflate);
    }

    private void dIK_(View view) {
        this.e = (HealthTextView) view.findViewById(R.id.hw_show_body_type_card_tv);
        this.d = (ImageView) view.findViewById(R.id.hw_show_body_type_card_img);
        this.c = (HealthTextView) view.findViewById(R.id.hw_show_body_type_card_tv_des);
        if (nsn.t()) {
            CardConstants.e(this.c);
        }
    }

    public void setBodyTypeDatas(int i, int i2, long j, long j2) {
        String string;
        String a2 = UnitUtil.a(new Date(j), 16);
        String a3 = UnitUtil.a(new Date(j2), 16);
        if (i2 == 7 || i2 == 4 || i2 == 8) {
            string = this.b.getResources().getString(R$string.IDS_hw_weight_body_type_interpretation_negative);
        } else {
            string = this.b.getResources().getString(R$string.IDS_hw_weight_body_type_interpretation_positive);
        }
        this.c.setText(String.format(string, a2, a3, qqy.a(i), qqy.a(i2)));
        this.e.setText(qqy.a(i2));
        CardConstants.dFe_(i2, this.d);
    }
}
