package com.huawei.health.h5pro.service.theme;

import android.content.res.Resources;
import android.util.TypedValue;
import androidx.core.view.ViewCompat;
import com.amap.api.col.p0003sl.it;
import com.huawei.health.h5pro.service.H5ProServiceLiveTerm;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@H5ProService(liveTerm = H5ProServiceLiveTerm.WEB_VIEW, name = "theme")
/* loaded from: classes.dex */
public class ThemeSvc {
    public Resources b;
    public static final int[] c = new int[0];

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f2454a = new int[0];

    @H5ProMethod
    public ArrayList<Map<String, String>> getAttrs() {
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = c;
            if (i2 >= iArr.length) {
                break;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(it.k, "--" + this.b.getResourceEntryName(iArr[i2]));
            hashMap.put(FitRunPlayAudio.PLAY_TYPE_V, String.format("#%06X", Integer.valueOf(Integer.valueOf(this.b.getColor(iArr[i2])).intValue() & ViewCompat.MEASURED_SIZE_MASK)));
            arrayList.add(hashMap);
            i2++;
        }
        while (true) {
            int[] iArr2 = f2454a;
            if (i >= iArr2.length) {
                return arrayList;
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put(it.k, "--" + this.b.getResourceEntryName(iArr2[i]));
            hashMap2.put(FitRunPlayAudio.PLAY_TYPE_V, Float.valueOf(e(this.b.getDimension(iArr2[i]))).toString() + "px");
            arrayList.add(hashMap2);
            i++;
        }
    }

    private float e(float f) {
        return TypedValue.applyDimension(1, f, this.b.getDisplayMetrics());
    }

    public ThemeSvc(Resources resources) {
        this.b = resources;
    }
}
