package defpackage;

import com.huawei.health.heartratesetting.HeartRateSettingApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.ui.main.stories.settings.js.CustomDataService;

@ApiDefine(uri = HeartRateSettingApi.class)
@Singleton
/* loaded from: classes7.dex */
public class ruz implements HeartRateSettingApi {
    @Override // com.huawei.health.heartratesetting.HeartRateSettingApi
    public Class<?> getCustomDataApi() {
        return CustomDataService.class;
    }
}
