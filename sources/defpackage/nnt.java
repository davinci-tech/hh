package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthXAxisRenderer;
import com.huawei.ui.commonui.linechart.common.UnixChartType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class nnt extends HwHealthXAxisRenderer {
    private UnixChartType d;
    private int e;

    public nnt(Context context, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler, XAxis xAxis, Transformer transformer) {
        super(context, hwHealthBaseBarLineChart, viewPortHandler, xAxis, transformer);
        this.e = -2147483647;
        this.d = UnixChartType.WEEK;
    }

    public void a(UnixChartType unixChartType) {
        this.d = unixChartType;
    }

    public void d(int i, int i2) {
        this.e = i;
        this.mAxis.setAxisMinimum(this.e);
        this.mAxis.setAxisMaximum(this.e + i2);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthXAxisRenderer, com.github.mikephil.charting.renderer.XAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxisValues(float f, float f2) {
        if (this.d == UnixChartType.YEAR) {
            d(f, f2);
            computeSize();
            return;
        }
        if (this.d == UnixChartType.DAY) {
            c(f, f2);
            computeSize();
            return;
        }
        if (this.d == UnixChartType.ALL) {
            b(f, f2);
            computeSize();
            return;
        }
        double minutes = TimeUnit.DAYS.toMinutes(1L);
        double d = minutes / 2.0d;
        int rawOffset = (TimeZone.getDefault().getRawOffset() / 3600000) * 60;
        double h = (((nom.h((int) f) + rawOffset) - d) * 1.0d) / minutes;
        int ceil = (int) Math.ceil(h);
        int floor = (((int) Math.floor((((nom.h((int) f2) + rawOffset) - d) * 1.0d) / minutes)) - ceil) + 1;
        if (this.d == UnixChartType.WEEK) {
            this.mAxis.mEntryCount = floor;
            this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
            for (int i = 0; i < this.mAxis.mEntryCount; i++) {
                this.mAxis.mEntries[i] = nom.f((int) ((((ceil + i) * minutes) - rawOffset) + d));
            }
        } else if (this.d == UnixChartType.MONTH) {
            e(minutes, d, rawOffset, ceil, floor);
        }
        computeSize();
    }

    private void e(double d, double d2, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < i3; i4++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(((int) ((((i2 + i4) * d) - i) + d2)) * 60000);
            if (calendar.get(7) == nop.c()) {
                arrayList.add(Float.valueOf(nom.f(r5)));
            }
        }
        this.mAxis.mEntryCount = arrayList.size();
        this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
        for (int i5 = 0; i5 < this.mAxis.mEntryCount; i5++) {
            this.mAxis.mEntries[i5] = ((Float) arrayList.get(i5)).floatValue();
        }
    }

    private void c(float f, float f2) {
        Calendar calendar = Calendar.getInstance();
        long millis = TimeUnit.MINUTES.toMillis(nom.h((int) f));
        calendar.setTimeInMillis(millis);
        if (calendar.get(11) == 23) {
            calendar.add(12, 5);
        }
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        int i = calendar.get(11);
        int m = jdl.m(jdl.t(millis));
        if (i == 0 || i == m) {
            this.mAxis.mDecimals = 0;
            this.mAxis.mEntryCount = 5;
            this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
            calendar.set(11, 0);
            this.mAxis.mEntries[0] = nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeInMillis()));
            calendar.set(11, 6);
            this.mAxis.mEntries[1] = nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeInMillis()));
            calendar.set(11, 12);
            this.mAxis.mEntries[2] = nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeInMillis()));
            calendar.set(11, 18);
            this.mAxis.mEntries[3] = nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeInMillis()));
            calendar.set(11, 0);
            calendar.add(5, 1);
            this.mAxis.mEntries[4] = nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar.getTimeInMillis()));
        }
    }

    private void d(float f, float f2) {
        int i = (int) f;
        Calendar.getInstance().setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h(i)));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h((int) f2)));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h(i)));
        calendar2.set(5, 15);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (calendar2.get(1) == calendar.get(1) && calendar2.get(2) == calendar.get(2)) {
                break;
            }
            arrayList.add(Float.valueOf(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar2.getTimeInMillis()))));
            calendar2.add(2, 1);
        }
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(calendar2.getTimeInMillis());
        if (nom.f(minutes) <= f2) {
            arrayList.add(Float.valueOf(nom.f(minutes)));
        }
        this.mAxis.mEntryCount = arrayList.size();
        this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
        for (int i2 = 0; i2 < this.mAxis.mEntryCount; i2++) {
            this.mAxis.mEntries[i2] = ((Float) arrayList.get(i2)).floatValue();
        }
    }

    private void b(float f, float f2) {
        int i = (int) f;
        Calendar.getInstance().setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h(i)));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h((int) f2)));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(TimeUnit.MINUTES.toMillis(nom.h(i)));
        calendar2.set(2, 5);
        calendar2.set(5, 30);
        ArrayList arrayList = new ArrayList();
        while (calendar2.get(1) <= calendar.get(1)) {
            arrayList.add(Float.valueOf(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(calendar2.getTimeInMillis()))));
            calendar2.add(1, 1);
        }
        this.mAxis.mEntryCount = arrayList.size();
        this.mAxis.mEntries = new float[this.mAxis.mEntryCount];
        for (int i2 = 0; i2 < this.mAxis.mEntryCount; i2++) {
            this.mAxis.mEntries[i2] = ((Float) arrayList.get(i2)).floatValue();
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthXAxisRenderer, com.github.mikephil.charting.renderer.XAxisRenderer
    public void drawLabels(Canvas canvas, float f, MPPointF mPPointF) {
        if ((this.mAxis instanceof nnu) && ((nnu) this.mAxis).e()) {
            return;
        }
        cBI_(canvas, f, mPPointF);
    }
}
