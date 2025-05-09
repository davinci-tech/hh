package defpackage;

import android.content.Context;
import android.opengl.GLES20;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class nke {

    /* renamed from: a, reason: collision with root package name */
    private int f15342a;
    private ConcurrentHashMap<String, Integer> d = new ConcurrentHashMap<>();

    public nke(Context context, String str) {
        int glGetUniformLocation;
        String b = nkm.b(str);
        nkd nkdVar = new nkd();
        nkdVar.b(njx.d(context, str));
        String str2 = b + File.separator + nkdVar.e();
        String str3 = b + File.separator + nkdVar.a();
        njw.c("Material", "vertexFile=" + str2 + " fragmentFile=" + str3);
        this.f15342a = nkb.b(context, str2, str3);
        for (Map.Entry<String, String> entry : nkdVar.b().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.equals("attribute")) {
                glGetUniformLocation = GLES20.glGetAttribLocation(this.f15342a, key);
            } else {
                glGetUniformLocation = value.equals("uniform") ? GLES20.glGetUniformLocation(this.f15342a, key) : -1;
            }
            this.d.put(key, Integer.valueOf(glGetUniformLocation));
        }
        nkdVar.d();
    }

    public int c(String str) {
        Integer num = this.d.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public int e() {
        return this.f15342a;
    }

    public void d() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = this.d;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
            this.d = null;
        }
    }
}
