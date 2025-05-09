package defpackage;

import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartView;
import com.huawei.ui.main.stories.health.views.healthdata.bloodsugarview.BloodSugarDetectionDistributionView;
import com.huawei.ui.main.stories.template.BaseComponent;
import java.util.Map;

/* loaded from: classes6.dex */
public class qmc extends ryq {
    public void a(String str, Map<Long, IStorageModel> map) {
        if (koq.c(this.f16970a)) {
            for (BaseComponent baseComponent : this.f16970a) {
                if (baseComponent instanceof BloodSugarDetectionDistributionView) {
                    ((BloodSugarDetectionDistributionView) baseComponent).setBloodSugarData(str, map);
                    return;
                }
            }
        }
    }

    public void d() {
        if (koq.c(this.f16970a)) {
            for (BaseComponent baseComponent : this.f16970a) {
                if (baseComponent instanceof BloodSugarLineChartView) {
                    ((BloodSugarLineChartView) baseComponent).updatePopGuide();
                    return;
                }
            }
        }
    }
}
