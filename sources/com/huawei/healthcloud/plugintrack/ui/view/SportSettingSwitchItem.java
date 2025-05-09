package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;

/* loaded from: classes4.dex */
public class SportSettingSwitchItem extends FrameLayout {
    private HealthImageView b;
    private HealthTextView c;
    private HealthSwitchButton d;
    private HealthTextView e;

    public SportSettingSwitchItem(Context context) {
        super(context);
        c();
    }

    public SportSettingSwitchItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public SportSettingSwitchItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private void c() {
        inflate(getContext(), R.layout.item_sport_setting_switch, this);
        this.c = (HealthTextView) findViewById(R.id.title_txt);
        this.e = (HealthTextView) findViewById(R.id.tips_txt);
        this.d = (HealthSwitchButton) findViewById(R.id.switch_btn);
        this.b = (HealthImageView) findViewById(R.id.title_red_point);
    }

    public void setTitleText(int i) {
        this.c.setText(i);
    }

    public void setTipsTxt(int i) {
        this.e.setVisibility(0);
        this.e.setText(i);
    }

    public void setChecked(boolean z) {
        this.d.setChecked(z);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.d.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public void setRedPointShow(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
    }
}
