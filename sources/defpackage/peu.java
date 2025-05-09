package defpackage;

import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.health.vip.api.EquityApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import org.json.JSONObject;

@ApiDefine(uri = EquityApi.class)
@Singleton
/* loaded from: classes6.dex */
public class peu implements EquityApi {
    @Override // com.huawei.health.vip.api.EquityApi
    public void getDoctorBasicInfo(final DataCallback dataCallback) {
        pug.a().getDoctorBasicInfo(new com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback() { // from class: peu.3
            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onFailure(int i, String str) {
                dataCallback.onFailure(i, str);
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                dataCallback.onSuccess(jSONObject);
            }
        });
    }
}
