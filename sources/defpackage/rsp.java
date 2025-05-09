package defpackage;

import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rsp {
    private static Map<String, Class> c = new HashMap<String, Class>(16) { // from class: rsp.1
        private static final long serialVersionUID = -7839806308839555166L;

        {
            put("privacy_model_activity_presenter", rsl.class);
            put("group_data_fragment_presenter", rsi.class);
            put("double_group_data_fragment_presenter", rsj.class);
            put("day_data_fragment_presenter", rsh.class);
        }
    };

    public static <T extends BasePresenter> T b(String str, BaseView baseView) {
        return (T) rzm.b(c.get(str), baseView);
    }
}
