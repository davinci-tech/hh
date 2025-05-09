package com.huawei.ui.main.stories.health.views.charteye;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes7.dex */
public class ScrollChartParentView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10272a;
    private ImageView b;
    private Context c;
    private HealthTextView d;
    private String e;
    private String g;

    public ScrollChartParentView(Context context, String str, String str2, String str3, String str4) {
        super(context);
        this.c = context;
        this.e = str4;
        this.g = str2;
        a(str, str3);
    }

    public void d(String str) {
        HealthTextView healthTextView = this.d;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
    }

    public ImageView getArrowView() {
        return this.b;
    }

    public HealthTextView getTitle() {
        return this.f10272a;
    }

    public HealthTextView getDataView() {
        return this.d;
    }

    public void setTextColor(View view, int i) {
        if (view == null) {
            LogUtil.h("ScrollChartParentView_bloodSugar", "view is null");
        } else if (view instanceof HealthTextView) {
            ((HealthTextView) view).setTextColor(ContextCompat.getColor(this.c, i));
        }
    }

    private void a(String str) {
        if (LanguageUtil.o(this.c) || LanguageUtil.p(this.c)) {
            if (str.equals(getResources().getString(R$string.IDS_bloodsugar_continue))) {
                this.f10272a.setAutoTextInfo(12, 1, 2);
            }
            if (str.equals(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_fingertips_desc))) {
                this.d.setAutoTextInfo(10, 1, 2);
            }
        }
    }

    private void a(String str, String str2) {
        LogUtil.c("ScrollChartParentView_bloodSugar", str2, "initView type is ", this.e);
        String str3 = this.e;
        if (str3 != null && str3.equals("BLOOD_SUGAR_FINGER_TIP")) {
            LayoutInflater.from(getContext()).inflate(R.layout.item_blood_sugar_charteye, (ViewGroup) this, true);
            this.f10272a = (HealthTextView) findViewById(R.id.data_title);
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.data_unit);
            this.d = healthTextView;
            b(this.f10272a, healthTextView);
            this.f10272a.setText(str);
            this.f10272a.setAllCaps(true);
            a(str2);
            this.d.setText(str2);
            if (LanguageUtil.aw(this.c)) {
                this.d.setTextSize(0, this.c.getResources().getDimension(R.dimen._2131363717_res_0x7f0a0785));
            }
            if (TextUtils.isEmpty(str2)) {
                this.d.setVisibility(4);
                return;
            } else {
                this.d.setVisibility(0);
                this.d.setAllCaps(true);
                return;
            }
        }
        LayoutInflater.from(getContext()).inflate(R.layout.item_blood_sugar_list_card, (ViewGroup) this, true);
        this.f10272a = (HealthTextView) findViewById(R.id.data_list_title);
        this.d = (HealthTextView) findViewById(R.id.data_list_unit);
        this.b = (ImageView) findViewById(R.id.drop_down_arrow);
        b(this.f10272a, this.d);
        this.f10272a.setText(str);
        this.f10272a.setAllCaps(true);
        a(str);
        this.d.setText(str2);
        this.d.setAllCaps(true);
    }

    public String getType() {
        return this.g;
    }

    public void setType(String str) {
        this.g = str;
    }

    private void b(HealthTextView healthTextView, HealthTextView healthTextView2) {
        if (nsn.r()) {
            nsn.b(healthTextView);
            nsn.b(healthTextView2);
        }
    }
}
