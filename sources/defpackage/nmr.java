package defpackage;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class nmr implements IValueFormatter, IAxisValueFormatter {
    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return UnitUtil.d(Math.round(f));
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return UnitUtil.d(Math.round(f));
    }
}
