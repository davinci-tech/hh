package defpackage;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes6.dex */
public class nmw implements IValueFormatter, IAxisValueFormatter {
    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return skh.b(Math.round(f));
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return skh.b(Math.round(f));
    }
}
