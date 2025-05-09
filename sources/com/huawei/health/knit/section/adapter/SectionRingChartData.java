package com.huawei.health.knit.section.adapter;

import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionRingChartData;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class SectionRingChartData {
    private static final DataFormatter b = new DataFormatter() { // from class: ebx
        @Override // com.huawei.health.knit.section.adapter.SectionRingChartData.DataFormatter
        public final String format(double d2) {
            return SectionRingChartData.d(d2);
        }
    };
    private static final DataFormatter d = new DataFormatter() { // from class: ebz
        @Override // com.huawei.health.knit.section.adapter.SectionRingChartData.DataFormatter
        public final String format(double d2) {
            return SectionRingChartData.a(d2);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2588a;
    private List<Integer> c;
    private List<String> h;
    private List<Double> j;
    private List<Float> e = new ArrayList();
    private boolean g = false;
    private boolean f = false;

    public interface DataFormatter {
        String format(double d);
    }

    public static /* synthetic */ String d(double d2) {
        int i = (int) d2;
        int i2 = i / 60;
        int i3 = i % 60;
        try {
            String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(i3, 1, 0));
            String quantityString2 = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0));
            if (i2 != 0) {
                if (i3 == 0) {
                    quantityString = quantityString2;
                } else if (LanguageUtil.y(BaseApplication.getContext())) {
                    quantityString = quantityString2 + " و " + quantityString;
                } else {
                    quantityString = BaseApplication.getContext().getString(R$string.IDS_two_parts, quantityString2, quantityString);
                }
            }
            return quantityString;
        } catch (IllegalFormatConversionException e) {
            LogUtil.h("SectionRingChartData", "DEFAULT_VALUE_FORMATTER IllegalFormatConversionException = ", e);
            return "";
        }
    }

    public static /* synthetic */ String a(double d2) {
        return String.format(Locale.ENGLISH, "%.1f", Double.valueOf(d2 * 100.0d)) + "%";
    }

    public void d(List<Integer> list) {
        this.c = list;
    }

    public List<Integer> c() {
        return this.c;
    }

    public void b(List<Integer> list) {
        this.f2588a = list;
    }

    public List<Integer> d() {
        return this.f2588a;
    }

    public void a(List<String> list) {
        this.h = list;
    }

    public List<String> j() {
        return this.h;
    }

    public void c(List<Double> list) {
        this.j = list;
        h();
    }

    public List<String> f() {
        ArrayList arrayList = new ArrayList();
        Iterator<Double> it = this.j.iterator();
        while (it.hasNext()) {
            arrayList.add(b.format(it.next().doubleValue()));
        }
        return arrayList;
    }

    public List<String> b() {
        ArrayList arrayList = new ArrayList();
        Iterator<Float> it = this.e.iterator();
        while (it.hasNext()) {
            arrayList.add(d.format(it.next().floatValue()));
        }
        return arrayList;
    }

    private void h() {
        Iterator<Double> it = this.j.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            d2 += it.next().doubleValue();
        }
        Iterator<Double> it2 = this.j.iterator();
        while (it2.hasNext()) {
            this.e.add(Float.valueOf((float) (it2.next().doubleValue() / d2)));
        }
    }

    public List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        int size = this.j.size();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int intValue = this.j.get(i3).intValue();
            if (this.e.get(i3).floatValue() <= 0.0f || this.e.get(i3).floatValue() >= 0.016f) {
                i += intValue;
            } else {
                i2++;
            }
            arrayList.add(Integer.valueOf(intValue));
        }
        int round = Math.round(i / (62.499996f - i2));
        for (int i4 = 0; i4 < size; i4++) {
            if (this.e.get(i4).floatValue() > 0.0f && this.e.get(i4).floatValue() < 0.016f) {
                arrayList.set(i4, Integer.valueOf(round));
                this.c.set(i4, this.f2588a.get(i4));
            }
        }
        return arrayList;
    }

    public int e() {
        Iterator<Integer> it = a().iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        return i;
    }

    public void d(boolean z) {
        this.g = z;
    }

    public boolean i() {
        return this.g;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public boolean g() {
        return this.f;
    }
}
