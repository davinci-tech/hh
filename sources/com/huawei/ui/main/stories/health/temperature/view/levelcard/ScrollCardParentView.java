package com.huawei.ui.main.stories.health.temperature.view.levelcard;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsk;
import defpackage.nsn;

/* loaded from: classes7.dex */
public class ScrollCardParentView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private Context f10253a;
    private HealthTextView b;
    private HealthTextView c;
    private String d;
    private HealthTextView e;

    public ScrollCardParentView(Context context, String str, String str2, String str3, String str4) {
        super(context);
        this.f10253a = context;
        d(str, str2, str4);
        this.d = str3;
    }

    public void a(String str) {
        HealthTextView healthTextView = this.b;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public HealthTextView getUnitTextView() {
        return this.c;
    }

    public HealthTextView getTitle() {
        return this.e;
    }

    public HealthTextView getDataTextView() {
        return this.b;
    }

    public void setTextColor(View view, int i) {
        if (view == null) {
            LogUtil.h("ScrollCardParentView_temperature", "view is null");
        } else if (view instanceof HealthTextView) {
            ((HealthTextView) view).setTextColor(ContextCompat.getColor(this.f10253a, i));
        }
    }

    private void d(String str, String str2, String str3) {
        LayoutInflater.from(getContext()).inflate(R.layout.item_temperature_cardeye, (ViewGroup) this, true);
        this.e = (HealthTextView) findViewById(R.id.eye_card_temperature_title);
        this.b = (HealthTextView) findViewById(R.id.eye_card_temperature_data);
        this.c = (HealthTextView) findViewById(R.id.eye_card_temperature_unit);
        this.e.setText(str);
        this.b.setText(str3);
        this.c.setText(str2);
        if (nsn.r()) {
            this.e.setTextSize(1, 25.0f);
            this.b.setTextSize(1, 35.0f);
            this.c.setTextSize(1, 21.0f);
        }
        if (TextUtils.isEmpty(str2)) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
        }
        Typeface cKN_ = nsk.cKN_();
        this.e.setTypeface(cKN_);
        this.b.setTypeface(cKN_);
    }

    public String getType() {
        return this.d;
    }
}
