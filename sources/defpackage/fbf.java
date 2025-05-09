package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.recognizekit.base.RecognizeBiApi;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class fbf implements RecognizeBiApi {

    /* renamed from: a, reason: collision with root package name */
    private static int f12420a;
    private static final String b = AnalyticsValue.RECOGNIZE_FOOD_EVENT_VALUE.value();

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onEnterEvent(int i) {
        f12420a = i;
        HashMap hashMap = new HashMap();
        hashMap.put("from", i + "");
        hashMap.put("event", "1");
        hashMap.put("scannedType", "");
        hashMap.put("result", "");
        hashMap.put("type", "");
        ixx.d().d(BaseApplication.e(), b, hashMap, 0);
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onTipsEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("from", f12420a + "");
        hashMap.put("event", "2");
        hashMap.put("scannedType", "");
        hashMap.put("result", "");
        hashMap.put("type", "");
        ixx.d().d(BaseApplication.e(), b, hashMap, 0);
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onAlbumEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("from", f12420a + "");
        hashMap.put("event", "3");
        hashMap.put("scannedType", "");
        hashMap.put("result", "");
        hashMap.put("type", "");
        ixx.d().d(BaseApplication.e(), b, hashMap, 0);
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onRecognizeEvent(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("from", f12420a + "");
        hashMap.put("event", "4");
        hashMap.put("scannedType", z ? "2" : "1");
        hashMap.put("result", "");
        hashMap.put("type", "");
        ixx.d().d(BaseApplication.e(), b, hashMap, 0);
    }

    @Override // com.huawei.health.recognizekit.base.RecognizeBiApi
    public void onRecognizeResultEvent(boolean z, boolean z2) {
        HashMap hashMap = new HashMap();
        hashMap.put("from", f12420a + "");
        hashMap.put("event", HealthZonePushReceiver.SOS_LOCATION_NOTIFY);
        hashMap.put("scannedType", z ? "2" : "1");
        hashMap.put("result", z2 ? "1" : "2");
        hashMap.put("type", "");
        ixx.d().d(BaseApplication.e(), b, hashMap, 0);
    }
}
