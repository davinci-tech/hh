package defpackage;

import android.content.Context;
import android.util.Log;
import com.huawei.appgallery.markethomecountrysdk.b.a.a.e;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class afy {
    private static e b;
    private static final Map<Integer, Class<? extends e>> e;

    public static e b(Context context) {
        synchronized (afy.class) {
            e eVar = b;
            if (eVar != null) {
                return eVar;
            }
            Log.i("DeviceImplFactory", "deviceType: " + afz.e(context));
            Class<? extends e> cls = e.get(Integer.valueOf(afz.e(context)));
            if (cls == null) {
                afx afxVar = new afx();
                b = afxVar;
                return afxVar;
            }
            try {
                b = cls.newInstance();
            } catch (Throwable unused) {
                b = new afx();
                Log.e("DeviceImplFactory", "createDeviceInfo error and create default phone deviceinfo");
            }
            return b;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(3, aga.class);
        hashMap.put(1, agg.class);
        hashMap.put(2, agd.class);
        hashMap.put(0, afx.class);
        hashMap.put(4, afx.class);
        hashMap.put(7, agb.class);
    }
}
