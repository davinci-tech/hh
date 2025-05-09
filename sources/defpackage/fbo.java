package defpackage;

import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class fbo {
    public static final Map<String, Integer> c = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: fbo.4
        {
            put("all", 200);
            put("newlesson", 201);
            put("activity", 204);
            put("message", 202);
            put("vmall", 205);
            put("function", 206);
            put("yidian", 301);
            put(Constants.AD_MATERIAL_SUB_DIR, Integer.valueOf(a.w));
            put("actionlibrary", Integer.valueOf(a.C));
            put("stressreliefmusic", Integer.valueOf(a.z));
            put("healthheadlines", Integer.valueOf(a.A));
        }
    });

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f12426a = {200, 201, a.C, a.z, a.A, 203, 205, 202, 206, 204, a.w, 301};

    public static int[] a() {
        int[] iArr = f12426a;
        return Arrays.copyOf(iArr, iArr.length);
    }
}
