package defpackage;

import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.CloudApi;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import java.io.File;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pud implements CloudApi {
    @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.CloudApi
    public void downloadFile(String str, String str2, DataCallback dataCallback) {
        if (!new File(str2).exists()) {
            puf.b(str, str2, dataCallback);
        } else {
            dataCallback.onSuccess(new JSONObject());
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.CloudApi
    public void getDoctorBasicInfo(DataCallback dataCallback) {
        puc.d().c(null, puj.b(), false, dataCallback);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.CloudApi
    public void getDoctorImInfo(DataCallback dataCallback) {
        puc.d().c(null, puj.e(), false, dataCallback);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.CloudApi
    public void queryExclusiveGuardianStatus(Map<String, Integer> map, DataCallback dataCallback) {
        puc.d().c(map, puj.a(), false, dataCallback);
    }
}
