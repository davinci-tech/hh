package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLineChartView;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarLogoView;
import com.huawei.ui.main.stories.health.temperature.view.TemperatureBarChartView;
import com.huawei.ui.main.stories.health.temperature.view.TemperatureLineChartView;
import com.huawei.ui.main.stories.health.views.healthdata.bloodsugarview.BloodSugarDetectionDistributionView;
import com.huawei.ui.main.stories.template.BaseComponent;
import com.huawei.ui.main.stories.template.health.view.NoDataBuyDeviceView;
import com.huawei.ui.main.stories.template.health.view.NoDataNewsView;
import com.huawei.ui.main.stories.template.health.view.NoDataResolutionView;
import com.huawei.ui.main.stories.template.health.view.NoDataServiceView;
import com.huawei.ui.main.stories.template.health.view.RecommendActivityView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rzd {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, Class<? extends BaseComponent>> f16973a = new HashMap<String, Class<? extends BaseComponent>>(16) { // from class: rzd.1
        {
            put("no_device_resolution_view", NoDataResolutionView.class);
            put("no_device_buy_device_view", NoDataBuyDeviceView.class);
            put("no_device_news_view", NoDataNewsView.class);
            put("no_device_service_view", NoDataServiceView.class);
            put("no_device_recommend_activity_view", RecommendActivityView.class);
            put("blood_sugar_chart_view", BloodSugarLineChartView.class);
            put("blood_sugar_logo_view", BloodSugarLogoView.class);
            put("blood_sugar_statistics_view", BloodSugarDetectionDistributionView.class);
            put("temperature_bar_chart_view", TemperatureBarChartView.class);
            put("body_temperature_chart_view", TemperatureLineChartView.class);
        }
    };

    public static <T extends BaseComponent> T d(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        return (T) rzm.d(f16973a.get(str), context);
    }
}
