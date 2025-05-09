package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionTopReminder extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f2748a;
    private Context b;
    private View c;
    private HealthTextView d;
    private ConstraintLayout e;

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void overrideParamsByOnlineData(SectionBean sectionBean, HashMap<String, Object> hashMap) {
    }

    public SectionTopReminder(Context context) {
        this(context, null);
    }

    public SectionTopReminder(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionTopReminder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        LogUtil.a("SectionTopReminder", "loadView");
        this.b = context;
        c();
        return this.c;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        char c;
        LogUtil.a("SectionTopReminder", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionTopReminder", "no need to bind");
            return;
        }
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            key.hashCode();
            int hashCode = key.hashCode();
            if (hashCode == -1937548587) {
                if (key.equals("RIGHT_BUTTON")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 263146085) {
                if (hashCode == 1675868304 && key.equals("CLICK_EVENT_LISTENER")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (key.equals("LEFT_TEXT")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c == 2) {
                        LogUtil.a("SectionTopReminder", "start to set click event");
                        setClickListenerEvent(value);
                    }
                } else if (this.d != null && (value instanceof String)) {
                    LogUtil.a("SectionTopReminder", "start to set left text");
                    this.d.setText(String.valueOf(value));
                }
            } else if (this.f2748a != null && (value instanceof String)) {
                LogUtil.a("SectionTopReminder", "start to set rightButton");
                this.f2748a.setText(String.valueOf(value));
            }
        }
    }

    private void setClickListenerEvent(Object obj) {
        if (obj instanceof OnClickSectionListener) {
            final OnClickSectionListener onClickSectionListener = (OnClickSectionListener) obj;
            LogUtil.a("SectionTopReminder", "start to set button click event");
            ConstraintLayout constraintLayout = this.e;
            if (constraintLayout != null) {
                constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionTopReminder.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("SectionTopReminder", "set subtitleClickEvent onClick");
                        onClickSectionListener.onClick("CLICK_VIEW");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
            HealthButton healthButton = this.f2748a;
            if (healthButton != null) {
                healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.section.view.SectionTopReminder.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LogUtil.a("SectionTopReminder", "set button onClick");
                        onClickSectionListener.onClick("ITEM_BUTTON_TEXT");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
            }
        }
    }

    private void c() {
        if (this.c == null) {
            LogUtil.h("SectionTopReminder", "initView mainView is null, start to inflate");
            this.c = LayoutInflater.from(this.b).inflate(R.layout.top_reminder_layout, (ViewGroup) this, false);
        }
        this.d = (HealthTextView) this.c.findViewById(R.id.left_text);
        this.f2748a = (HealthButton) this.c.findViewById(R.id.right_button);
        this.e = (ConstraintLayout) this.c.findViewById(R.id.whole_click_area);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.whole_click_area) {
            onClick("CLICK_VIEW");
        } else if (view.getId() == R.id.right_button) {
            onClick("ITEM_BUTTON_TEXT");
        } else {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionTopReminder";
    }
}
