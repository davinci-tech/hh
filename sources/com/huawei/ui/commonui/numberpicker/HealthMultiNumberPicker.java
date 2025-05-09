package com.huawei.ui.commonui.numberpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class HealthMultiNumberPicker extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private int f8914a;
    private final LinearLayout b;
    private final LinearLayout c;
    private boolean[] d;
    private Context e;
    private int[] f;
    private final FrameLayout g;
    private Map<Integer, String[]> h;
    private int i;
    private OnValueChangeListener j;
    private final LinearLayout l;

    public interface OnValueChangeListener {
        void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3);
    }

    public HealthMultiNumberPicker(Context context) {
        this(context, null);
    }

    public HealthMultiNumberPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = -1;
        this.e = context;
        View inflate = View.inflate(getContext(), R.layout.health_multi_number_picker_layout, this);
        this.b = (LinearLayout) inflate.findViewById(R.id.hw_health_multi_numberpicker_layout);
        this.g = (FrameLayout) inflate.findViewById(R.id.number_picker_root_layout);
        this.l = (LinearLayout) inflate.findViewById(R.id.unit_root_layout);
        this.c = (LinearLayout) inflate.findViewById(R.id.first_unit_layout);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.number_picker_parent);
        if (LanguageUtil.bc(context)) {
            relativeLayout.setLayoutDirection(0);
        }
    }

    public void setSelectorTextColor(int i) {
        this.i = i;
        int childCount = this.b.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.b.getChildAt(i2);
            if (!(childAt instanceof HealthNumberPicker)) {
                LogUtil.b("HealthMultiNumberPicker", "mMultiNumberpickerLayout did not contain HealthNumberPicker view");
                return;
            }
            ((HealthNumberPicker) childAt).setSelectorPaintColor(i);
        }
    }

    public void setPickerCount(int i, boolean[] zArr) {
        this.f8914a = i;
        if (zArr == null || zArr.length < i) {
            LogUtil.b("HealthMultiNumberPicker", "the looper flags have something wrong");
            return;
        }
        this.d = (boolean[]) zArr.clone();
        for (int i2 = 0; i2 < this.f8914a; i2++) {
            a(i2);
        }
    }

    public void setColonAndUnit(int i) {
        if (this.f8914a != i || i <= 0) {
            LogUtil.d("HealthMultiNumberPicker", "num is wrong");
            return;
        }
        if (i != 2 && i != 3) {
            LogUtil.d("HealthMultiNumberPicker", "NumberPicker count is not supported");
            return;
        }
        this.l.setVisibility(0);
        View inflate = View.inflate(getContext(), R.layout.health_multi_colon_layout, null);
        if (i == 3) {
            ((LinearLayout) inflate.findViewById(R.id.second_colon_layout)).setVisibility(0);
            LinearLayout linearLayout = this.c;
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
        }
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.colon_root_layout);
        if (linearLayout2.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout2.getLayoutParams();
            marginLayoutParams.setMarginStart(this.e.getResources().getDimensionPixelSize(R.dimen._2131363076_res_0x7f0a0504));
            marginLayoutParams.setMarginEnd(this.e.getResources().getDimensionPixelSize(R.dimen._2131363076_res_0x7f0a0504));
            linearLayout2.setLayoutParams(marginLayoutParams);
        }
        if (inflate instanceof LinearLayout) {
            this.g.addView((LinearLayout) inflate);
        }
    }

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        if (onValueChangeListener == null) {
            LogUtil.b("HealthMultiNumberPicker", "the listener of the picker has not been initialized");
        } else {
            this.j = onValueChangeListener;
        }
    }

    public void setDisplayedValues(int i, String[] strArr, int i2) {
        int i3;
        if (i >= this.f8914a) {
            LogUtil.b("HealthMultiNumberPicker", "the index of picker is out of bound");
            return;
        }
        if (i > 0) {
            this.b.removeViewAt(i);
            a(i);
        }
        View childAt = this.b.getChildAt(i);
        if (!(childAt instanceof HealthNumberPicker)) {
            LogUtil.b("HealthMultiNumberPicker", "mMultiNumberpickerLayout did not contain HealthNumberPicker view");
            return;
        }
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) childAt;
        if (strArr == null) {
            LogUtil.b("HealthMultiNumberPicker", "the values of picker is empty");
            return;
        }
        if (i2 < 0 || i2 >= strArr.length || strArr.length <= 0) {
            i2 = 0;
            i3 = 0;
        } else {
            int length = strArr.length;
            healthNumberPicker.setEnabled(true);
            i3 = length - 1;
        }
        if (strArr.length == 1 && strArr[0].equals("")) {
            healthNumberPicker.setEnabled(false);
            healthNumberPicker.setAlpha(1.0f);
        }
        b(healthNumberPicker, strArr, 0, i3, i2);
        d(healthNumberPicker, i, i2);
        if (this.h == null) {
            this.h = new HashMap();
        }
        this.h.put(Integer.valueOf(i), strArr);
    }

    private void d(HealthNumberPicker healthNumberPicker, int i, int i2) {
        healthNumberPicker.setWrapSelectorWheel(this.d[i]);
        this.f[i] = i2;
    }

    public void setDataContent(int i, Map<Integer, String[]> map, boolean[] zArr, int[] iArr) {
        if (i < 0) {
            LogUtil.b("HealthMultiNumberPicker", "the count of the picker can not be less than 0");
            return;
        }
        this.f8914a = i;
        if (map != null) {
            int size = map.size();
            int i2 = this.f8914a;
            if (size >= i2) {
                if (zArr == null || zArr.length < i2) {
                    LogUtil.b("HealthMultiNumberPicker", "the value of lopper is invalid");
                    return;
                }
                if (iArr == null || iArr.length < i2) {
                    LogUtil.b("HealthMultiNumberPicker", "the values of location have something wrong");
                    return;
                }
                this.h = map;
                this.d = (boolean[]) zArr.clone();
                for (int i3 = 0; i3 < this.f8914a; i3++) {
                    if (i3 >= iArr.length) {
                        iArr[i3] = 0;
                    }
                    e(i3, iArr[i3]);
                }
                return;
            }
        }
        LogUtil.b("HealthMultiNumberPicker", "the values of picker is invalid");
    }

    private HealthNumberPicker a(final int i) {
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) View.inflate(getContext(), R.layout.health_multi_number_picker_one_layout, null).findViewById(R.id.health_number_picker_one);
        healthNumberPicker.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        healthNumberPicker.setOnValueChangedListener(new HwAdvancedNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.2
            @Override // com.huawei.uikit.hwadvancednumberpicker.widget.HwAdvancedNumberPicker.OnValueChangeListener
            public void onValueChange(HwAdvancedNumberPicker hwAdvancedNumberPicker, int i2, int i3) {
                HealthMultiNumberPicker.this.f[i] = i3;
                if (HealthMultiNumberPicker.this.j != null) {
                    HealthMultiNumberPicker.this.j.onValueChange(i, HealthMultiNumberPicker.this, i2, i3);
                }
            }
        });
        if (this.f == null) {
            this.f = new int[this.f8914a];
        }
        int i2 = this.i;
        if (i2 != -1) {
            healthNumberPicker.setSelectorPaintColor(i2);
        }
        this.b.addView(healthNumberPicker, i);
        return healthNumberPicker;
    }

    private void e(int i, int i2) {
        Map<Integer, String[]> map = this.h;
        if (map == null || !map.containsKey(Integer.valueOf(i))) {
            LogUtil.b("HealthMultiNumberPicker", "the values of picker is not exist");
            return;
        }
        String[] strArr = this.h.get(Integer.valueOf(i));
        if (strArr == null || strArr.length == 0) {
            LogUtil.b("HealthMultiNumberPicker", "the picker's values are empty");
            return;
        }
        HealthNumberPicker a2 = a(i);
        a2.setWrapSelectorWheel(this.d[i]);
        b(a2, strArr, 0, strArr.length - 1, i2);
        boolean[] zArr = this.d;
        if (i >= zArr.length) {
            zArr[i] = false;
        }
        d(a2, i, i2);
    }

    private void b(HealthNumberPicker healthNumberPicker, String[] strArr, int i, int i2, int i3) {
        if (strArr == null || strArr.length == 0) {
            LogUtil.b("HealthMultiNumberPicker", "the picker's values are empty");
            return;
        }
        healthNumberPicker.setDisplayedValues(strArr);
        healthNumberPicker.setMinValue(i);
        healthNumberPicker.setMaxValue(i2);
        healthNumberPicker.setValue(i3);
    }

    public void a(int[] iArr, int i) {
        if (iArr == null || iArr.length < this.f8914a) {
            LogUtil.b("HealthMultiNumberPicker", "the values of location have something wrong");
            return;
        }
        this.f = (int[]) iArr.clone();
        for (int i2 = 0; i2 < this.f8914a; i2++) {
            int[] iArr2 = this.f;
            int i3 = iArr2[i2];
            if (i3 < 0 || i3 > i - 1) {
                iArr2[i2] = 0;
            }
            View childAt = this.b.getChildAt(i2);
            if (!(childAt instanceof HealthNumberPicker)) {
                LogUtil.b("HealthMultiNumberPicker", "mMultiNumberpickerLayout did not contain HealthNumberPicker view");
                return;
            }
            ((HealthNumberPicker) childAt).setValue(this.f[i2]);
        }
    }

    public int[] getSelectedLocations() {
        int[] iArr = this.f;
        if (iArr == null) {
            LogUtil.b("HealthMultiNumberPicker", "the value of selectedLocation is null");
            return new int[0];
        }
        return (int[]) iArr.clone();
    }

    public String[] e(int i) {
        Map<Integer, String[]> map = this.h;
        if (map == null) {
            LogUtil.b("HealthMultiNumberPicker", "the value of pickers is null");
            return new String[0];
        }
        return map.get(Integer.valueOf(i));
    }

    public String[] d(int i, int i2, String str) {
        if (i > i2) {
            LogUtil.b("HealthMultiNumberPicker", "the range of number has something wrong");
            return new String[0];
        }
        if (str == null) {
            str = "";
        }
        int i3 = (i2 - i) + 1;
        int[] iArr = new int[i3];
        for (int i4 = i; i4 <= i2; i4++) {
            iArr[i4 - i] = i4;
        }
        return b(i3, iArr, str);
    }

    public String[] c(int[] iArr, String str) {
        if (iArr == null) {
            LogUtil.b("HealthMultiNumberPicker", "numberList is empty");
            return new String[0];
        }
        if (str == null) {
            str = "";
        }
        return e(iArr.length, iArr, str);
    }

    public String d(double d, String str) {
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(UnitUtil.e(d, 1, 0));
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    private String[] b(int i, int[] iArr, String str) {
        if (i <= 0) {
            LogUtil.c("HealthMultiNumberPicker", "the length of number list is 0");
            return new String[0];
        }
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            String e = UnitUtil.e(iArr[i2], 1, 0);
            strArr[i2] = e;
            if (iArr[i2] < 10) {
                e = new DecimalFormat("00").format(iArr[i2]);
            }
            strArr[i2] = e;
        }
        return strArr;
    }

    private String[] e(int i, int[] iArr, String str) {
        if (i <= 0) {
            LogUtil.c("HealthMultiNumberPicker", "the length of number list is 0");
            return new String[0];
        }
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(UnitUtil.e(iArr[i2], 1, 0));
            stringBuffer.append(str);
            strArr[i2] = stringBuffer.toString();
        }
        return strArr;
    }
}
