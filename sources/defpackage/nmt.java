package defpackage;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class nmt implements IValueFormatter, IAxisValueFormatter {
    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        if (UnitUtil.h()) {
            f = (float) UnitUtil.d(f, 3);
        }
        return nqo.b(f, true);
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        if (UnitUtil.h()) {
            f = (float) UnitUtil.d(f, 3);
        }
        return nqo.b(f, true);
    }
}
