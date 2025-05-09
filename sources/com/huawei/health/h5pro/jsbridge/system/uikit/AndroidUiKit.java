package com.huawei.health.h5pro.jsbridge.system.uikit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;
import com.huawei.uikit.hwdatepicker.widget.HwDatePicker;
import com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog;
import com.huawei.uikit.hwtimepicker.widget.HwTimePicker;
import com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog;

/* loaded from: classes3.dex */
public class AndroidUiKit implements UiKit {

    /* renamed from: a, reason: collision with root package name */
    public Context f2438a;

    @Override // com.huawei.health.h5pro.jsbridge.system.uikit.UiKit
    public void pickTime(final TimeCallback timeCallback) {
        new HwTimePickerDialog((Activity) this.f2438a, new HwTimePickerDialog.OnButtonClickCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.uikit.AndroidUiKit.2
            @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog.OnButtonClickCallback
            public void onPositiveButtonClick(HwTimePicker hwTimePicker) {
                timeCallback.onSelected(hwTimePicker.getHour(), hwTimePicker.getMinute());
            }

            @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog.OnButtonClickCallback
            public void onNegativeButtonClick(HwTimePicker hwTimePicker) {
                timeCallback.onUnselected();
            }
        }).show();
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.uikit.UiKit
    public void pickDate(final DateCallback dateCallback) {
        HwDatePickerDialog hwDatePickerDialog = new HwDatePickerDialog((Activity) this.f2438a, new HwDatePickerDialog.OnButtonClickCallback() { // from class: com.huawei.health.h5pro.jsbridge.system.uikit.AndroidUiKit.1
            @Override // com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog.OnButtonClickCallback
            public void onPositiveButtonClick(HwDatePicker hwDatePicker) {
                dateCallback.onSelected(hwDatePicker.getYear(), hwDatePicker.getMonth(), hwDatePicker.getDayOfMonth());
            }

            @Override // com.huawei.uikit.hwdatepicker.widget.HwDatePickerDialog.OnButtonClickCallback
            public void onNegativeButtonClick(HwDatePicker hwDatePicker) {
                dateCallback.onUnselected();
            }
        });
        hwDatePickerDialog.d(false);
        hwDatePickerDialog.show();
    }

    @Override // com.huawei.health.h5pro.jsbridge.system.uikit.UiKit
    public void pickData(String[] strArr, final DataCallback dataCallback) {
        final HwAdvancedNumberPicker hwAdvancedNumberPicker = new HwAdvancedNumberPicker(this.f2438a);
        hwAdvancedNumberPicker.setDisplayedValues(strArr);
        hwAdvancedNumberPicker.setMinValue(0);
        hwAdvancedNumberPicker.setMaxValue(strArr.length - 1);
        new AlertDialog.Builder(this.f2438a).setView(hwAdvancedNumberPicker).setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.uikit.AndroidUiKit.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dataCallback.onSelected(hwAdvancedNumberPicker.getValue());
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.huawei.health.h5pro.jsbridge.system.uikit.AndroidUiKit.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dataCallback.onUnselected();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).create().show();
    }

    public AndroidUiKit(Context context) {
        this.f2438a = context;
    }
}
