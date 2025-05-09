package defpackage;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.PaceMapRecyclerViewAdapter;
import com.huawei.healthcloud.plugintrack.ui.view.PaceChartView;
import com.huawei.hianalytics.core.transport.net.Response;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import health.compact.a.UnitUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hml {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f13252a;
    private HealthTextView b;
    private Context c;
    private PaceChartView d;
    private HealthRecycleView e;
    private int g;
    private View h;
    private int i = 0;

    public hml(Context context, int i) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }
        this.c = context;
        this.g = i;
    }

    public void b(int i) {
        this.i = i;
    }

    public View bkV_() {
        View inflate = View.inflate(this.c, R.layout.track_share_viewholder_pace, null);
        this.e = (HealthRecycleView) inflate.findViewById(R.id.track_pace_recycler_view);
        this.d = (PaceChartView) inflate.findViewById(R.id.track_share_pace_chart);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.track_share_pace_chaer_uint);
        this.b = healthTextView;
        healthTextView.setVisibility(8);
        this.f13252a = (HealthTextView) inflate.findViewById(R.id.track_share_pace_unit);
        this.e.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.c);
        linearLayoutManager.setOrientation(1);
        this.e.setLayoutManager(linearLayoutManager);
        this.e.setNestedScrollingEnabled(false);
        this.f13252a.setText(String.format(this.c.getString(R.string._2130839866_res_0x7f02093a), hji.a(this.i, this.c)));
        if (this.g == 101) {
            ((HealthTextView) inflate.findViewById(R.id.track_share_pace_title)).setTextColor(gwh.b);
            ((HealthTextView) inflate.findViewById(R.id.track_share_pace_unit)).setTextColor(gwh.b);
            this.d.d();
        }
        this.h = inflate;
        return inflate;
    }

    public void d(hjw hjwVar, Map<Integer, Float> map) {
        if (this.h == null || hjwVar == null || map == null) {
            return;
        }
        MotionPathSimplify e = hjwVar.e();
        float requestAvgPace = e.requestAvgPace();
        if (hji.a(e)) {
            if (UnitUtil.h()) {
                requestAvgPace = ((float) UnitUtil.d(requestAvgPace, 2)) / 5.0f;
            }
        } else if (UnitUtil.h()) {
            requestAvgPace = (float) UnitUtil.d(requestAvgPace, 3);
        }
        String e2 = hji.e(requestAvgPace);
        Float[] e3 = gvv.e(map);
        float floatValue = e3[0].floatValue();
        float floatValue2 = e3[1].floatValue();
        String a2 = hji.a(floatValue);
        List<Map.Entry<Integer, Float>> d = hji.d(map, floatValue);
        float d2 = hji.d(map, hjwVar);
        float b = gvv.b(this.c, r2.getResources().getDisplayMetrics().widthPixels * 1.0f) + Response.Code.UNKNOWN_ERROR;
        float f = gvv.a(hjwVar) ? 110.0f : 80.0f;
        Map<Double, Double> requestPartTimeMap = e.requestPartTimeMap();
        PaceMapRecyclerViewAdapter paceMapRecyclerViewAdapter = new PaceMapRecyclerViewAdapter(this.c, d, a2, e2, floatValue, floatValue2, b, f, d2, this.g == 101, (requestPartTimeMap == null || !requestPartTimeMap.containsKey(Double.valueOf(21.0975d))) ? 0.0d : requestPartTimeMap.get(Double.valueOf(21.0975d)).doubleValue(), (requestPartTimeMap == null || !requestPartTimeMap.containsKey(Double.valueOf(42.195d))) ? 0.0d : requestPartTimeMap.get(Double.valueOf(42.195d)).doubleValue(), e.requestSportType(), false);
        paceMapRecyclerViewAdapter.c(true);
        this.e.setAdapter(paceMapRecyclerViewAdapter);
    }
}
