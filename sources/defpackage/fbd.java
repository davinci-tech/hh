package defpackage;

import com.huawei.health.recognizekit.base.RecognizeBiApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class fbd implements RecognizeBiApi {
    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onAlbumEvent() {
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onRecognizeEvent(boolean z) {
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onTipsEvent() {
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onEnterEvent(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", 2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SNACK_CONTROL_ENTRANCE.value(), hashMap, 0);
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onRecognizeResultEvent(boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("result", 2);
        hashMap.put("event", 2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SNACK_CONTROL_ACTION.value(), hashMap, 0);
    }
}
