package com.huawei.ui.commonui.datepicker;

import android.app.Activity;
import com.huawei.ui.commonui.R$color;
import com.huawei.uikit.hwdatepicker.R$style;
import com.huawei.uikit.hwdatepicker.widget.HwDatePicker;
import com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog;
import defpackage.snn;
import java.util.GregorianCalendar;

/* loaded from: classes6.dex */
public class HealthDatePickerDialog extends snn {

    public interface DateSelectedListener {
        void onDateSelected(int i, int i2, int i3);
    }

    public HealthDatePickerDialog(Activity activity, final DateSelectedListener dateSelectedListener, GregorianCalendar gregorianCalendar) {
        super(activity, R$style.Widget_Emui_HwDatePickerDialogStyle, new HwDatePickerDialog.OnButtonClickCallback() { // from class: com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.5
            @Override // com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog.OnButtonClickCallback
            public void onNegativeButtonClick(HwDatePicker hwDatePicker) {
            }

            @Override // com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog.OnButtonClickCallback
            public void onPositiveButtonClick(HwDatePicker hwDatePicker) {
                DateSelectedListener.this.onDateSelected(hwDatePicker.getYear(), hwDatePicker.getMonth(), hwDatePicker.getDayOfMonth());
            }
        }, gregorianCalendar);
        setCanceledOnTouchOutside(true);
        d(false);
        a(true);
        c(false);
        a(activity.getResources().getColor(R$color.common_colorAccent));
    }

    @Override // com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog
    public void d(GregorianCalendar gregorianCalendar, GregorianCalendar gregorianCalendar2) {
        if (d() != null && !d().j()) {
            d().setIsShowAllYears(true);
        }
        super.d(gregorianCalendar, gregorianCalendar2);
    }
}
