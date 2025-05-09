package com.amap.api.col.p0003sl;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes8.dex */
public final class et extends ContextThemeWrapper {
    private static final String[] d = {"android.widget", "android.webkit", "android.app"};

    /* renamed from: a, reason: collision with root package name */
    private Resources f1027a;
    private LayoutInflater b;
    private ClassLoader c;
    private a e;
    private LayoutInflater.Factory f;

    public et(Context context, int i, ClassLoader classLoader) {
        super(context, i);
        this.e = new a();
        this.f = new LayoutInflater.Factory() { // from class: com.amap.api.col.3sl.et.1
            @Override // android.view.LayoutInflater.Factory
            public final View onCreateView(String str, Context context2, AttributeSet attributeSet) {
                return et.this.a(str, context2, attributeSet);
            }
        };
        this.f1027a = eu.a();
        this.c = classLoader;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        Resources resources = this.f1027a;
        return resources != null ? resources : super.getResources();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if ("layout_inflater".equals(str)) {
            if (this.b == null) {
                LayoutInflater layoutInflater = (LayoutInflater) super.getSystemService(str);
                if (layoutInflater != null) {
                    this.b = layoutInflater.cloneInContext(this);
                }
                this.b.setFactory(this.f);
                this.b = this.b.cloneInContext(this);
            }
            return this.b;
        }
        return super.getSystemService(str);
    }

    public final class a {

        /* renamed from: a, reason: collision with root package name */
        public HashSet<String> f1029a = new HashSet<>();
        public HashMap<String, Constructor<?>> b = new HashMap<>();

        public a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View a(java.lang.String r11, android.content.Context r12, android.util.AttributeSet r13) {
        /*
            r10 = this;
            com.amap.api.col.3sl.et$a r0 = r10.e
            java.util.HashSet<java.lang.String> r0 = r0.f1029a
            boolean r0 = r0.contains(r11)
            r1 = 0
            if (r0 == 0) goto Lc
            return r1
        Lc:
            com.amap.api.col.3sl.et$a r0 = r10.e
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<?>> r0 = r0.b
            java.lang.Object r0 = r0.get(r11)
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            if (r0 != 0) goto L84
            r2 = 1
            r3 = 0
            java.lang.String r4 = "api.navi"
            boolean r4 = r11.contains(r4)     // Catch: java.lang.Throwable -> L62
            if (r4 == 0) goto L29
            java.lang.ClassLoader r4 = r10.c     // Catch: java.lang.Throwable -> L62
            java.lang.Class r4 = r4.loadClass(r11)     // Catch: java.lang.Throwable -> L62
            goto L50
        L29:
            java.lang.String[] r4 = com.amap.api.col.p0003sl.et.d     // Catch: java.lang.Throwable -> L62
            int r5 = r4.length     // Catch: java.lang.Throwable -> L62
            r6 = r3
        L2d:
            if (r6 >= r5) goto L4f
            r7 = r4[r6]     // Catch: java.lang.Throwable -> L62
            java.lang.ClassLoader r8 = r10.c     // Catch: java.lang.Throwable -> L4c
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4c
            r9.<init>()     // Catch: java.lang.Throwable -> L4c
            r9.append(r7)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r7 = "."
            r9.append(r7)     // Catch: java.lang.Throwable -> L4c
            r9.append(r11)     // Catch: java.lang.Throwable -> L4c
            java.lang.String r7 = r9.toString()     // Catch: java.lang.Throwable -> L4c
            java.lang.Class r4 = r8.loadClass(r7)     // Catch: java.lang.Throwable -> L4c
            goto L50
        L4c:
            int r6 = r6 + 1
            goto L2d
        L4f:
            r4 = r1
        L50:
            if (r4 != 0) goto L53
            goto L63
        L53:
            java.lang.Class<android.view.ViewStub> r5 = android.view.ViewStub.class
            if (r4 == r5) goto L63
            java.lang.ClassLoader r5 = r4.getClassLoader()     // Catch: java.lang.Throwable -> L63
            java.lang.ClassLoader r6 = r10.c     // Catch: java.lang.Throwable -> L63
            if (r5 == r6) goto L60
            goto L63
        L60:
            r5 = r2
            goto L64
        L62:
            r4 = r1
        L63:
            r5 = r3
        L64:
            if (r5 != 0) goto L6e
            com.amap.api.col.3sl.et$a r12 = r10.e
            java.util.HashSet<java.lang.String> r12 = r12.f1029a
            r12.add(r11)
            return r1
        L6e:
            r5 = 2
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch: java.lang.Throwable -> L84
            java.lang.Class<android.content.Context> r6 = android.content.Context.class
            r5[r3] = r6     // Catch: java.lang.Throwable -> L84
            java.lang.Class<android.util.AttributeSet> r3 = android.util.AttributeSet.class
            r5[r2] = r3     // Catch: java.lang.Throwable -> L84
            java.lang.reflect.Constructor r0 = r4.getConstructor(r5)     // Catch: java.lang.Throwable -> L84
            com.amap.api.col.3sl.et$a r2 = r10.e     // Catch: java.lang.Throwable -> L84
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<?>> r2 = r2.b     // Catch: java.lang.Throwable -> L84
            r2.put(r11, r0)     // Catch: java.lang.Throwable -> L84
        L84:
            if (r0 == 0) goto L91
            java.lang.Object[] r11 = new java.lang.Object[]{r12, r13}     // Catch: java.lang.Throwable -> L91
            java.lang.Object r11 = r0.newInstance(r11)     // Catch: java.lang.Throwable -> L91
            android.view.View r11 = (android.view.View) r11     // Catch: java.lang.Throwable -> L91
            r1 = r11
        L91:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.et.a(java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }
}
