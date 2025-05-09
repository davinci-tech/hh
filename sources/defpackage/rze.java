package defpackage;

import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rze {
    private static Map<String, Class<? extends BasePresenter>> b = new HashMap<String, Class<? extends BasePresenter>>(16) { // from class: rze.2
        private static final long serialVersionUID = -351216119762982286L;

        {
            put("health_no_device_presenter", ryz.class);
            put("health_common_content_presenter", ryq.class);
            put("health_common_activity_presenter", qle.class);
            put("health_day_content_presenter", qmc.class);
            put("health_week_content_presenter", qmc.class);
            put("health_month_content_presenter", qmc.class);
            put("temperature_activity_presenter", qow.class);
        }
    };

    public static <T extends BasePresenter> T d(String str, BaseView baseView) {
        return (T) rzm.b(b.get(str), baseView);
    }
}
