package defpackage;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.SpeedMapRecyclerViewAdapter;
import com.huawei.healthcloud.plugintrack.ui.view.SpeedChartView;
import com.huawei.hianalytics.core.transport.net.Response;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import health.compact.a.UnitUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hnd {

    /* renamed from: a, reason: collision with root package name */
    private Context f13264a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private int f;
    private HealthRecycleView g;
    private View h;
    private SpeedChartView i;
    private HealthTextView j;

    public hnd(Context context, int i) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }
        this.f13264a = context;
        this.f = i;
    }

    public View blB_() {
        View inflate = View.inflate(this.f13264a, R.layout.track_share_viewholder_speed, null);
        this.i = (SpeedChartView) inflate.findViewById(R.id.track_share_pace_chart);
        this.g = (HealthRecycleView) inflate.findViewById(R.id.track_pace_recycler_view);
        this.c = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_unit);
        this.b = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace);
        this.d = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace_unit);
        this.e = (HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace);
        this.j = (HealthTextView) inflate.findViewById(R.id.track_share_pace_unit);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        this.g.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.f13264a);
        linearLayoutManager.setOrientation(1);
        this.g.setLayoutManager(linearLayoutManager);
        this.g.setNestedScrollingEnabled(false);
        if (UnitUtil.h()) {
            this.j.setText(String.format(this.f13264a.getString(R.string._2130839866_res_0x7f02093a), this.f13264a.getString(R.string._2130844079_res_0x7f0219af)));
        } else {
            this.j.setText(String.format(this.f13264a.getString(R.string._2130839866_res_0x7f02093a), this.f13264a.getString(R.string._2130844078_res_0x7f0219ae)));
        }
        if (this.f == 101) {
            ((HealthTextView) inflate.findViewById(R.id.track_share_pace_title)).setTextColor(gwh.b);
            ((HealthTextView) inflate.findViewById(R.id.hw_show_pace_averagepace_title)).setTextColor(gwh.b);
            ((HealthTextView) inflate.findViewById(R.id.hw_show_pace_fastpace_title)).setTextColor(gwh.b);
            ((HealthTextView) inflate.findViewById(R.id.track_share_pace_unit)).setTextColor(gwh.b);
            this.i.e();
            d(this.b, this.f13264a.getResources().getColor(R.color._2131296871_res_0x7f090267));
            d(this.e, this.f13264a.getResources().getColor(R.color._2131296871_res_0x7f090267));
        }
        this.h = inflate;
        return inflate;
    }

    public void a(hjw hjwVar, Map<Integer, Float> map) {
        float requestAvgPace;
        if (this.h == null || hjwVar == null || map == null) {
            return;
        }
        if (UnitUtil.h()) {
            requestAvgPace = hjwVar.e().requestAvgPace() * 1.609344f;
        } else {
            requestAvgPace = hjwVar.e().requestAvgPace();
        }
        String o = hji.o(requestAvgPace);
        Float[] e = gvv.e(map);
        float floatValue = e[0].floatValue();
        float floatValue2 = e[1].floatValue();
        List<Map.Entry<Integer, Float>> d = hji.d(map, floatValue);
        String o2 = hji.o(floatValue);
        float d2 = hji.d(map, hjwVar);
        Context context = this.f13264a;
        SpeedMapRecyclerViewAdapter speedMapRecyclerViewAdapter = new SpeedMapRecyclerViewAdapter(this.f13264a, d, o2, o, floatValue, floatValue2, gvv.b(context, context.getResources().getDisplayMetrics().widthPixels * 1.0f) + Response.Code.UNKNOWN_ERROR, gvv.a(hjwVar) ? 120.0f : 80.0f, d2, this.f == 101, hjwVar);
        speedMapRecyclerViewAdapter.b(true);
        this.g.setAdapter(speedMapRecyclerViewAdapter);
    }

    private void d(HealthTextView healthTextView, int i) {
        healthTextView.setTextColor(i);
    }
}
