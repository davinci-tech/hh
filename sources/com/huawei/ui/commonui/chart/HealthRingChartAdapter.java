package com.huawei.ui.commonui.chart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.oval.HealthOval;
import defpackage.koq;
import defpackage.nkz;
import defpackage.nld;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class HealthRingChartAdapter {
    private static final DataFormatter d = new DataFormatter() { // from class: nlb
        @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
        public final String format(nkz nkzVar) {
            String format;
            format = String.format(Locale.ENGLISH, "%.1f", Double.valueOf(nkzVar.i()));
            return format;
        }
    };
    private static final DataFormatter e = new DataFormatter() { // from class: nlc
        @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
        public final String format(nkz nkzVar) {
            return HealthRingChartAdapter.b(nkzVar);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private Context f8787a;
    private List<nkz> b;
    private int[] c;
    private boolean f;
    private DataListener g;
    private int h;
    private int[] i;
    private int[] j;
    private boolean k;
    private DataFormatter l;
    private boolean m;
    private int n;
    private DataFormatter o;
    private float q;

    public interface DataFormatter {
        String format(nkz nkzVar);
    }

    interface DataListener {
        void onDataUpdate();
    }

    public static /* synthetic */ String b(nkz nkzVar) {
        return String.format(Locale.ENGLISH, "%.1f", Float.valueOf(nkzVar.e() * 100.0f)) + "%";
    }

    public HealthRingChartAdapter(Context context, nld nldVar) {
        this.b = new ArrayList();
        this.k = false;
        this.m = false;
        this.o = d;
        this.l = e;
        this.n = 0;
        this.q = 0.0f;
        this.f = false;
        this.f8787a = context;
        if (nldVar != null) {
            this.k = nldVar.a();
            this.m = nldVar.b();
            this.f = nldVar.d();
        }
    }

    public HealthRingChartAdapter(Context context, nld nldVar, List<nkz> list) {
        this(context, nldVar);
        c(list);
    }

    public final void c(List<nkz> list) {
        List<nkz> list2 = this.b;
        if (list2 == null) {
            LogUtil.b("HealthRingChartAdapter", "setData mData null");
            return;
        }
        list2.clear();
        this.b.addAll(list);
        h();
        double d2 = 0.0d;
        for (int i = 0; i < this.b.size(); i++) {
            nkz nkzVar = this.b.get(i);
            double i2 = nkzVar.i();
            d2 += i2;
            this.i[i] = (int) i2;
            this.c[i] = nkzVar.d();
            this.j[i] = nkzVar.c();
        }
        for (nkz nkzVar2 : this.b) {
            if (Math.abs(d2) < 1.0E-6d) {
                return;
            }
            float i3 = (float) (nkzVar2.i() / d2);
            nkzVar2.e(i3);
            nkzVar2.e(Math.round(i3 * 100.0f));
        }
        i();
        DataListener dataListener = this.g;
        if (dataListener != null) {
            dataListener.onDataUpdate();
        }
    }

    private void h() {
        int size = this.b.size();
        int[] iArr = this.i;
        if (iArr == null || iArr.length != size) {
            this.i = new int[size];
        }
        int[] iArr2 = this.c;
        if (iArr2 == null || iArr2.length != size) {
            this.c = new int[size];
        }
        int[] iArr3 = this.j;
        if (iArr3 == null || iArr3.length != size) {
            this.j = new int[size];
        }
    }

    private void i() {
        int size = this.b.size();
        Iterator<nkz> it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().a();
        }
        if (i == 100) {
            return;
        }
        int i2 = 100 - i;
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            nkz nkzVar = this.b.get(size);
            if (Math.abs((nkzVar.e() * 100.0f) - nkzVar.a()) > 1.0E-5d) {
                if (i2 > 0) {
                    nkzVar.e(nkzVar.a() + 1);
                    i2--;
                } else {
                    if (i2 >= 0) {
                        return;
                    }
                    nkzVar.e(nkzVar.a() - 1);
                    i2++;
                }
            }
        }
    }

    void e(List<Integer> list) {
        if (list == null || this.b == null) {
            LogUtil.h("HealthRingChartAdapter", "update values failed, cause titles or mData is null!");
            return;
        }
        int min = Math.min(list.size(), this.b.size());
        for (int i = 0; i < min; i++) {
            nkz nkzVar = this.b.get(i);
            Integer num = list.get(i);
            if (nkzVar != null) {
                nkzVar.d(num.intValue());
            }
        }
        DataListener dataListener = this.g;
        if (dataListener != null) {
            dataListener.onDataUpdate();
        }
    }

    b cxE_(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.f8787a).inflate(R.layout.health_ring_chart_item_layout, viewGroup, false);
        viewGroup.addView(inflate);
        return new b(inflate);
    }

    void b(b bVar, int i) {
        if (koq.b(this.b, i)) {
            LogUtil.h("HealthRingChartAdapter", "onBindViewHolder is out of bounds");
            return;
        }
        if (this.f) {
            c(bVar.e);
            c(bVar.b);
            c(bVar.d);
        }
        nkz nkzVar = this.b.get(i);
        bVar.f8788a.setFillColor(nkzVar.c());
        bVar.f8788a.invalidate();
        if (this.n != 0) {
            bVar.e.setTextColor(this.n);
        }
        bVar.e.setText(nkzVar.b());
        DataFormatter dataFormatter = this.o;
        String format = dataFormatter != null ? dataFormatter.format(nkzVar) : null;
        if (!this.k) {
            bVar.b.setVisibility(8);
        } else {
            bVar.b.setVisibility(0);
            bVar.b.setText(format);
        }
        if (i == this.b.size() - 1 && !Double.isInfinite(1.0f - this.q) && !Double.isNaN(1.0f - this.q)) {
            nkzVar.e(new BigDecimal(1.0f - this.q).setScale(this.h, 4).floatValue());
        }
        if (!Double.isInfinite(nkzVar.e()) && !Double.isNaN(nkzVar.e())) {
            this.q += new BigDecimal(nkzVar.e()).setScale(this.h, 4).floatValue();
        }
        DataFormatter dataFormatter2 = this.l;
        String format2 = dataFormatter2 != null ? dataFormatter2.format(nkzVar) : null;
        if (i == this.b.size() - 1) {
            this.q = 0.0f;
        }
        if (!this.m) {
            bVar.d.setVisibility(8);
        } else {
            bVar.d.setVisibility(0);
            bVar.d.setText(format2);
        }
    }

    int e() {
        List<nkz> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    int[] d() {
        return this.c;
    }

    int[] c() {
        return this.j;
    }

    int[] b() {
        int size = this.b.size();
        int[] iArr = new int[size];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            nkz nkzVar = this.b.get(i3);
            if (nkzVar != null) {
                if (nkzVar.e() <= 0.0f || nkzVar.e() >= 0.016f) {
                    int i4 = (int) nkzVar.i();
                    iArr[i3] = i4;
                    i += i4;
                } else {
                    i2++;
                }
            }
        }
        int round = Math.round(i / (62.499996f - i2));
        for (int i5 = 0; i5 < size; i5++) {
            nkz nkzVar2 = this.b.get(i5);
            if (nkzVar2 != null && nkzVar2.e() > 0.0f && nkzVar2.e() < 0.016f) {
                iArr[i5] = round;
                int c = nkzVar2.c();
                nkzVar2.c(c);
                this.c[i5] = c;
            }
        }
        return iArr;
    }

    private void c(HealthTextView healthTextView) {
        healthTextView.setAutoTextSize(1, 10.0f);
        healthTextView.setAutoTextInfo(10, 1, 1);
    }

    public boolean a() {
        return this.f;
    }

    public void d(int i) {
        this.n = i;
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        HealthOval f8788a;
        HealthTextView b;
        HealthTextView d;
        HealthTextView e;

        b(View view) {
            this.f8788a = (HealthOval) view.findViewById(R.id.ring_chart_legend_oval);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.ring_chart_legend_title);
            this.e = healthTextView;
            if (LanguageUtil.p(healthTextView.getContext())) {
                this.e.setTextSize(1, 11.0f);
            }
            this.b = (HealthTextView) view.findViewById(R.id.ring_chart_item_value);
            this.d = (HealthTextView) view.findViewById(R.id.ring_chart_item_percent);
            if (nsn.s()) {
                e(this.e);
                e(this.b);
                e(this.d);
            }
        }

        private void e(HealthTextView healthTextView) {
            healthTextView.setAutoTextSize(1, 24.0f);
            healthTextView.setAutoTextInfo(24, 1, 1);
        }
    }

    public void a(DataFormatter dataFormatter) {
        this.o = dataFormatter;
    }

    public void b(DataFormatter dataFormatter) {
        a(dataFormatter, 1);
    }

    public void a(DataFormatter dataFormatter, int i) {
        this.l = dataFormatter;
        this.h = i + 2;
    }

    void a(DataListener dataListener) {
        this.g = dataListener;
    }
}
