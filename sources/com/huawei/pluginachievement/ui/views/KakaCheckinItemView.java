package com.huawei.pluginachievement.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.UnitUtil;
import org.slf4j.Marker;

/* loaded from: classes8.dex */
public class KakaCheckinItemView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8464a;
    private HealthTextView b;
    private ImageView d;
    private Context e;

    public KakaCheckinItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.kaka_checkin_item_layout, this);
        this.d = (ImageView) inflate.findViewById(R.id.kaka_checkin_status_image);
        this.b = (HealthTextView) inflate.findViewById(R.id.kaka_add_value);
        this.f8464a = (HealthTextView) inflate.findViewById(R.id.kaka_checkin_date);
    }

    public void setCheckeinStatus(int i, boolean z) {
        if (this.e == null) {
            return;
        }
        b(i, z);
        this.b.setText(Marker.ANY_NON_NULL_MARKER + UnitUtil.e(i, 1, 0));
    }

    private void b(int i, boolean z) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        if (z) {
            dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516);
            dimensionPixelSize2 = this.e.getResources().getDimensionPixelSize(R.dimen._2131363026_res_0x7f0a04d2);
            if (i < 6) {
                this.d.setBackgroundResource(R.mipmap._2131821334_res_0x7f110316);
            } else {
                this.d.setBackgroundResource(R.mipmap._2131821333_res_0x7f110315);
            }
        } else if (i >= 10) {
            dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131362860_res_0x7f0a042c);
            dimensionPixelSize2 = this.e.getResources().getDimensionPixelSize(R.dimen._2131363039_res_0x7f0a04df);
            this.d.setBackgroundResource(R.mipmap._2131821331_res_0x7f110313);
        } else {
            dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131363039_res_0x7f0a04df);
            dimensionPixelSize2 = this.e.getResources().getDimensionPixelSize(R.dimen._2131363039_res_0x7f0a04df);
            this.d.setBackgroundResource(R.mipmap._2131821332_res_0x7f110314);
        }
        if (this.d.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.d.getLayoutParams();
            layoutParams.setMargins(0, dimensionPixelSize, 0, 0);
            this.d.setLayoutParams(layoutParams);
        }
        if (this.b.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams2.setMargins(0, dimensionPixelSize2, 0, 0);
            this.b.setLayoutParams(layoutParams2);
        }
    }

    public void setDate(String str) {
        this.f8464a.setText(str);
    }

    public void setDateTextColor(int i) {
        this.f8464a.setTextColor(this.e.getResources().getColor(i));
        this.f8464a.setBackgroundResource(R.drawable.kaka_check_text_background);
    }
}
