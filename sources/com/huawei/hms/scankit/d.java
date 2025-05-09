package com.huawei.hms.scankit;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.hms.scankit.p.j0;
import com.huawei.hms.scankit.p.k1;
import com.huawei.hms.scankit.p.l1;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.v6;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes9.dex */
final class d extends Thread {

    /* renamed from: a, reason: collision with root package name */
    private Context f5710a;
    private final j0 b;
    private final Map<l1, Object> c;
    private Handler d;
    private a e;
    private Rect g;
    private boolean h = true;
    private final CountDownLatch f = new CountDownLatch(1);

    d(Context context, j0 j0Var, a aVar, Collection<BarcodeFormat> collection, Map<l1, ?> map, String str, v6 v6Var) {
        this.f5710a = context;
        this.e = aVar;
        this.b = j0Var;
        EnumMap enumMap = new EnumMap(l1.class);
        this.c = enumMap;
        if (map != null) {
            enumMap.putAll(map);
        }
        if (collection == null || collection.isEmpty()) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            collection = EnumSet.noneOf(BarcodeFormat.class);
            if (defaultSharedPreferences.getBoolean("preferences_decode_1D_product", true)) {
                collection.addAll(k1.f5815a);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_1D_industrial", true)) {
                collection.addAll(k1.c);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_QR", true)) {
                collection.addAll(k1.d);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_Data_Matrix", true)) {
                collection.addAll(k1.f);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_Aztec", false)) {
                collection.addAll(k1.e);
            }
            if (defaultSharedPreferences.getBoolean("preferences_decode_PDF417", false)) {
                collection.addAll(k1.g);
            }
        }
        enumMap.put((EnumMap) l1.POSSIBLE_FORMATS, (l1) collection);
        if (str != null) {
            enumMap.put((EnumMap) l1.CHARACTER_SET, (l1) str);
        }
        enumMap.put((EnumMap) l1.NEED_RESULT_POINT_CALLBACK, (l1) v6Var);
        o4.d("DecodeThread", "Hints: " + enumMap);
    }

    public void a(Rect rect) {
        this.g = rect;
    }

    public void b() {
        this.f5710a = null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.d = new c(this.f5710a, this.b, this.e, this.c, this.g, this.h);
        this.f.countDown();
        Looper.loop();
    }

    Handler a() {
        try {
            this.f.await();
        } catch (InterruptedException unused) {
            o4.b(TrackConstants$Events.EXCEPTION, "InterruptedException");
        }
        return this.d;
    }

    public void a(boolean z) {
        this.h = z;
    }
}
