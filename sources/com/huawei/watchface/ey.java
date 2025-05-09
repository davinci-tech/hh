package com.huawei.watchface;

import android.Manifest;
import com.huawei.watchface.utils.HwLog;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes7.dex */
public class ey {

    /* renamed from: a, reason: collision with root package name */
    private static ey f11023a;
    private final Set<String> b = new HashSet();
    private final Set<String> c = new HashSet();
    private final List<WeakReference<ez>> d = new ArrayList();

    private ey() {
        b();
    }

    public static ey a() {
        ey eyVar;
        synchronized (ey.class) {
            if (f11023a == null) {
                f11023a = new ey();
            }
            eyVar = f11023a;
        }
        return eyVar;
    }

    private void b() {
        String str;
        synchronized (this) {
            for (Field field : Manifest.permission.class.getFields()) {
                try {
                    str = (String) field.get("");
                } catch (IllegalAccessException e) {
                    HwLog.e("PermissionsManager", "Could not access the field" + HwLog.printException((Exception) e));
                    str = null;
                }
                this.c.add(str);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
    
        if (r1.c.contains(r3) == false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(android.content.Context r2, java.lang.String r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 == 0) goto L21
            boolean r0 = com.huawei.watchface.utils.CommonUtils.isAndroid13()     // Catch: java.lang.Throwable -> L1e
            if (r0 == 0) goto Le
            int r2 = androidx.core.content.ContextCompat.checkSelfPermission(r2, r3)     // Catch: java.lang.Throwable -> L1e
            goto L12
        Le:
            int r2 = androidx.core.content.PermissionChecker.checkSelfPermission(r2, r3)     // Catch: java.lang.Throwable -> L1e
        L12:
            if (r2 == 0) goto L1c
            java.util.Set<java.lang.String> r2 = r1.c     // Catch: java.lang.Throwable -> L1e
            boolean r2 = r2.contains(r3)     // Catch: java.lang.Throwable -> L1e
            if (r2 != 0) goto L21
        L1c:
            r2 = 1
            goto L22
        L1e:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L21:
            r2 = 0
        L22:
            monitor-exit(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.ey.a(android.content.Context, java.lang.String):boolean");
    }

    public void a(String[] strArr, int[] iArr) {
        int i;
        synchronized (this) {
            if (strArr == null || iArr == null) {
                return;
            }
            int length = strArr.length;
            if (iArr.length < length) {
                length = iArr.length;
            }
            Iterator<WeakReference<ez>> it = this.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ez ezVar = it.next().get();
                while (i < length) {
                    i = (ezVar == null || ezVar.a(strArr[i], iArr[i])) ? 0 : i + 1;
                    it.remove();
                    break;
                }
            }
            while (i < length) {
                this.b.remove(strArr[i]);
                i++;
            }
        }
    }
}
