package com.huawei.ui.main.stories.health.activity.healthdata;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.ixx;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class WeightDataActivity extends BaseActivity implements HealthViewPager.OnPageChangeListener {
    protected HealthSubTabWidget d;
    protected CustomTitleBar e;
    protected HealthViewPager g;
    protected ArrayList<Fragment> c = new ArrayList<>(16);

    /* renamed from: a, reason: collision with root package name */
    protected ArrayList<String> f10092a = new ArrayList<>(16);
    protected ArrayList<Integer> b = new ArrayList<>(16);

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_weight_body_data);
        d();
    }

    private void d() {
        this.g = (HealthViewPager) findViewById(R.id.activity_weight_body_data_custom_view_pager);
        this.d = (HealthSubTabWidget) findViewById(R.id.activity_weight_body_data_health_sub_tab_widget);
        this.e = (CustomTitleBar) findViewById(R.id.activity_weight_body_data_custom_title_bar);
        this.g.addOnPageChangeListener(this);
    }

    public HealthSubTabWidget b() {
        return this.d;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        ArrayList<Integer> arrayList = this.b;
        if (arrayList == null || arrayList.isEmpty() || !koq.d(this.b, i)) {
            return;
        }
        d(this.b.get(i).intValue());
    }

    protected void d(int i) {
        HashMap hashMap = new HashMap(8);
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), c(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.c = new ArrayList<>(16);
        this.f10092a = new ArrayList<>(16);
        this.b = new ArrayList<>(16);
        super.onDestroy();
    }

    protected String c() {
        return "";
    }
}
