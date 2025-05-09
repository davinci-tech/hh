package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.operation.utils.Constants;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nkr {
    private static List<String> c;

    /* renamed from: a, reason: collision with root package name */
    private boolean f15349a;
    private View b;

    static {
        ArrayList arrayList = new ArrayList(10);
        c = arrayList;
        arrayList.add("com.huawei.health.MainActivity");
        c.add(ComponentInfo.PluginHealthModel_A_1);
        c.add("com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity");
        c.add("com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity");
        c.add("com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity");
        c.add("com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity");
        c.add("com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity");
        c.add("com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity");
        c.add("com.huawei.health.h5pro.core.H5ProWebViewActivity");
        c.add(Constants.KNIT_SLEEP_DETAIL_ACTIVITY);
        c.add("com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity");
        c.add(Constants.SPORT_HISTORY);
        c.add("com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity");
        c.add("com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity");
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final nkr f15350a = new nkr();
    }

    private nkr() {
    }

    public static nkr d() {
        return d.f15350a;
    }

    public void cwX_(View view) {
        this.b = view;
    }

    public View cwW_() {
        return this.b;
    }

    public void b(boolean z) {
        this.f15349a = z;
    }

    public boolean c(String str) {
        return this.f15349a && c.contains(str);
    }

    public void b() {
        View view = this.b;
        if (view != null && (view.getParent() instanceof ViewGroup)) {
            LogUtil.c("MiniPlayerProvider", "remove minibar from old parent view");
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
    }
}
