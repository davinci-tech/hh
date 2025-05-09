package defpackage;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.text.DecimalFormat;

/* loaded from: classes6.dex */
public class nmx implements IAxisValueFormatter {
    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return new DecimalFormat("#").format(Math.round(f / 60.0f));
    }
}
