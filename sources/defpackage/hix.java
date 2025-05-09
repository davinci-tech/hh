package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.CombineChartTableLayout;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackChartViewHolder;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;

/* loaded from: classes4.dex */
public class hix {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f13182a;
    private ImageView b;
    private HwHealthBaseCombinedChart c;
    private boolean d = true;
    private TrackChartViewHolder e;
    private float f;
    private LinearLayout g;
    private float h;
    private hjs i;
    private HealthTableWidget j;
    private HealthDivider m;

    public boolean i() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public LinearLayout bgB_() {
        return this.g;
    }

    public void bgE_(View view, int i) {
        if (view != null) {
            this.g = (LinearLayout) view.findViewById(i);
        }
    }

    public void bgC_(View view, int i) {
        if (view != null) {
            CombineChartTableLayout combineChartTableLayout = (CombineChartTableLayout) view.findViewById(i);
            this.g = combineChartTableLayout.getChartView();
            this.m = combineChartTableLayout.getDiv();
            this.j = combineChartTableLayout.getTable();
        }
    }

    public HealthTableWidget j() {
        return this.j;
    }

    public HwHealthBaseCombinedChart e() {
        return this.c;
    }

    public void d(HwHealthBaseCombinedChart hwHealthBaseCombinedChart) {
        this.c = hwHealthBaseCombinedChart;
    }

    public HealthTextView d() {
        return this.f13182a;
    }

    public void d(HealthTextView healthTextView) {
        this.f13182a = healthTextView;
    }

    public void c(hjs hjsVar) {
        this.i = hjsVar;
    }

    public ImageView bgA_() {
        return this.b;
    }

    public void bgD_(ImageView imageView) {
        this.b = imageView;
    }

    public TrackChartViewHolder a() {
        return this.e;
    }

    public void a(TrackChartViewHolder trackChartViewHolder) {
        this.e = trackChartViewHolder;
    }

    public HealthDivider g() {
        return this.m;
    }

    public void bgF_(View view, int i) {
        if (view != null) {
            this.m = (HealthDivider) view.findViewById(i);
        }
    }

    public float h() {
        return this.f;
    }

    public void c(float f) {
        this.f = f;
    }

    public float f() {
        return this.h;
    }

    public void e(float f) {
        this.h = f;
    }
}
