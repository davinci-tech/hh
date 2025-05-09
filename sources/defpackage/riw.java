package defpackage;

import android.content.Context;
import com.huawei.health.main.api.MainNavigationApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import java.util.HashMap;

@ApiDefine(uri = MainNavigationApi.class)
@Singleton
/* loaded from: classes7.dex */
public class riw implements MainNavigationApi {
    @Override // com.huawei.health.main.api.MainNavigationApi
    public void goToAchieveKaka(Context context) {
        sas.e(context);
        sas.a(context, 3);
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DISCOVER_PAGE_CLICKED.value(), hashMap, 0);
    }
}
