package com.huawei.ui.commonui.timepicker;

import android.app.Activity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import com.huawei.uikit.hwtimepicker.widget.HwTimePicker;
import com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog;
import defpackage.sns;
import java.lang.reflect.Field;

/* loaded from: classes6.dex */
public class HealthTimePickerDialog extends sns {

    /* loaded from: classes9.dex */
    public enum ButtonId {
        LEFT,
        RIGHT
    }

    public interface TimeSelectedListener {
        void onTimeSelected(int i, int i2);
    }

    public HealthTimePickerDialog(Activity activity, HwTimePickerDialog.OnButtonClickCallback onButtonClickCallback) {
        super(activity, onButtonClickCallback);
        cGT_(activity);
    }

    public HealthTimePickerDialog(Activity activity, final TimeSelectedListener timeSelectedListener) {
        super(activity, new HwTimePickerDialog.OnButtonClickCallback() { // from class: com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.2
            @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog.OnButtonClickCallback
            public void onNegativeButtonClick(HwTimePicker hwTimePicker) {
            }

            @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog.OnButtonClickCallback
            public void onPositiveButtonClick(HwTimePicker hwTimePicker) {
                TimeSelectedListener.this.onTimeSelected(hwTimePicker.getHour(), hwTimePicker.getMinute());
            }
        });
        cGT_(activity);
    }

    private void cGT_(Activity activity) {
        int color = ContextCompat.getColor(activity, R$color.common_colorAccent);
        a(color);
        a(false);
        d(color);
    }

    @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog, android.app.Dialog
    public void show() {
        super.show();
        d();
    }

    public void d() {
        HwTextView hwTextView = (HwTextView) findViewById(R.id.hwtimepicker_title);
        if (hwTextView != null) {
            hwTextView.setGravity(GravityCompat.START);
        }
    }

    public void c(ButtonId buttonId, String str) {
        b(buttonId, -1, str);
    }

    public void e(ButtonId buttonId, int i) {
        b(buttonId, i, null);
    }

    private void b(ButtonId buttonId, int i, String str) {
        Field[] declaredFields = HwTimePickerDialog.class.getDeclaredFields();
        int e = e(buttonId);
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getType() == HwTextView.class) {
                try {
                    HwTextView hwTextView = (HwTextView) field.get(this);
                    if (hwTextView == null) {
                        return;
                    }
                    if (hwTextView.getId() == e) {
                        if (i != -1) {
                            hwTextView.setTextColor(i);
                        }
                        if (str != null) {
                            hwTextView.setText(str);
                            return;
                        }
                        return;
                    }
                    continue;
                } catch (IllegalAccessException e2) {
                    LogUtil.a("HealthTimePickerDialog", "Failed to find cancle button, ", e2.getCause());
                }
            }
        }
    }

    private int e(ButtonId buttonId) {
        return buttonId == ButtonId.LEFT ? R.id.hwtimepicker_negative_btn : R.id.hwtimepicker_positive_btn;
    }
}
