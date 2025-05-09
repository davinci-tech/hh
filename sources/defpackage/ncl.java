package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.skinner.attrentry.SkinAttr;
import com.huawei.skinner.facade.template.ILogger;
import com.huawei.skinner.internal.IInterceptorGroup;
import com.huawei.skinner.internal.ISkinAttrGroup;
import com.huawei.skinner.internal.ISkinAttrRoot;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class ncl {

    /* renamed from: a, reason: collision with root package name */
    private static final nde f15248a = new nde();
    private static boolean b = false;
    private static final String c = "SkinAttrFactory";
    private static final Map<c, Class<? extends SkinAttr>> d;
    private static boolean e;
    private static boolean f;

    static {
        ndh ndhVar = new ndh();
        d = ndhVar;
        b = false;
        f = false;
        e = false;
        ndhVar.put(new c("textAppearance", TextView.class), ncj.class);
        ndhVar.put(new c(TemplateStyleRecord.STYLE, View.class), nck.class);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0098 A[Catch: all -> 0x0177, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x002c, B:8:0x0033, B:10:0x0037, B:11:0x003e, B:13:0x0048, B:14:0x00ed, B:16:0x0117, B:17:0x011e, B:19:0x0124, B:24:0x016c, B:26:0x0170, B:31:0x0155, B:35:0x0054, B:37:0x005a, B:40:0x0061, B:42:0x006e, B:44:0x0089, B:46:0x0098, B:47:0x00a2, B:49:0x00bd, B:50:0x00c1, B:52:0x00c7, B:54:0x00d5, B:57:0x00dd, B:65:0x00e3, B:68:0x007c), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bd A[Catch: all -> 0x0177, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x002c, B:8:0x0033, B:10:0x0037, B:11:0x003e, B:13:0x0048, B:14:0x00ed, B:16:0x0117, B:17:0x011e, B:19:0x0124, B:24:0x016c, B:26:0x0170, B:31:0x0155, B:35:0x0054, B:37:0x005a, B:40:0x0061, B:42:0x006e, B:44:0x0089, B:46:0x0098, B:47:0x00a2, B:49:0x00bd, B:50:0x00c1, B:52:0x00c7, B:54:0x00d5, B:57:0x00dd, B:65:0x00e3, B:68:0x007c), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e3 A[Catch: all -> 0x0177, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x002c, B:8:0x0033, B:10:0x0037, B:11:0x003e, B:13:0x0048, B:14:0x00ed, B:16:0x0117, B:17:0x011e, B:19:0x0124, B:24:0x016c, B:26:0x0170, B:31:0x0155, B:35:0x0054, B:37:0x005a, B:40:0x0061, B:42:0x006e, B:44:0x0089, B:46:0x0098, B:47:0x00a2, B:49:0x00bd, B:50:0x00c1, B:52:0x00c7, B:54:0x00d5, B:57:0x00dd, B:65:0x00e3, B:68:0x007c), top: B:3:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(android.content.Context r12) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ncl.d(android.content.Context):void");
    }

    private static Set<String> c(Context context) {
        try {
            return new HashSet(context.getSharedPreferences("hw_skinner", 0).getStringSet("SKINNER_ROOT_MAP", new HashSet()));
        } catch (Exception e2) {
            ncs.d.error(c, "getCachedModuleRoots()", e2);
            return null;
        }
    }

    private static Set<String> e(Context context) {
        Set<String> set = null;
        try {
            set = nda.c(context, "com.huawei.skinner.peanut");
            if (!set.isEmpty()) {
                context.getSharedPreferences("hw_skinner", 0).edit().putStringSet("SKINNER_ROOT_MAP", set).apply();
            } else {
                set = new HashSet();
            }
            ncz.e(context);
        } catch (Exception e2) {
            ncs.d.error(c, "getModuleRootsAndSave()", e2);
        }
        return set;
    }

    public static SkinAttr a(String str, int i, String str2, String str3, Class cls) {
        SkinAttr newInstance;
        Class<? extends SkinAttr> cls2 = d.get(new c(str, cls));
        SkinAttr skinAttr = null;
        if (cls2 == null) {
            return null;
        }
        try {
            newInstance = cls2.newInstance();
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InstantiationException e3) {
            e = e3;
        }
        try {
            newInstance.attrName = str;
            newInstance.attrValueRefId = i;
            newInstance.attrValueRefName = str2;
            newInstance.attrValueTypeName = str3;
            return newInstance;
        } catch (IllegalAccessException | InstantiationException e4) {
            e = e4;
            skinAttr = newInstance;
            ncs.d.error(c, "get() Error", e);
            return skinAttr;
        }
    }

    public static boolean d(String str, Class<? extends View> cls) {
        if (d.get(new c(str, cls)) != null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        c(cls, arrayList);
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                f15248a.remove((String) it.next());
            }
        }
        if (d.get(new c(str, cls)) != null) {
            return true;
        }
        ncs.d.info(c, "isSupportedAttr(): Not support , attrname：" + str + "; View: " + cls.getName());
        return false;
    }

    public static void c(ISkinAttrRoot iSkinAttrRoot) {
        if (iSkinAttrRoot != null) {
            f = true;
            iSkinAttrRoot.loadGroup(f15248a);
        }
    }

    private static void c(Class<? extends View> cls, List<String> list) {
        String canonicalName = cls.getCanonicalName();
        String[] b2 = f15248a.b(canonicalName);
        if (b2 != null && b2.length > 0) {
            for (String str : b2) {
                try {
                    Object newInstance = Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (newInstance instanceof ISkinAttrGroup) {
                        ((ISkinAttrGroup) newInstance).loadSkinAttr(d);
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
                    ncs.d.error(c, "registerSkinAttrGroup() Error", e2);
                }
            }
            list.add(canonicalName);
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass.equals(Object.class)) {
            return;
        }
        c(superclass, list);
    }

    private static void b() {
        f = false;
    }

    private static void d(String str) {
        ILogger iLogger = ncs.d;
        String str2 = c;
        iLogger.info(str2, "register(className) register class start, classname:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Object newInstance = Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance instanceof ISkinAttrRoot) {
                c((ISkinAttrRoot) newInstance);
            } else if (newInstance instanceof IInterceptorGroup) {
                ((IInterceptorGroup) newInstance).loadInterceptor();
            } else {
                ncs.d.info(str2, "register(className) register failed, class name: " + str + " should implements ISkinAttrRoot");
            }
        } catch (Exception e2) {
            ncs.d.error(c, "register(className) Error", e2);
        }
    }

    public static class c implements Serializable {
        private static final long serialVersionUID = 2;
        private final String b;
        private final Class e;

        public c(String str, Class<? extends View> cls) {
            this.b = str;
            this.e = cls;
        }

        public int hashCode() {
            return this.b.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                if (cVar.e.isAssignableFrom(this.e) || this.e.isAssignableFrom(cVar.e)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "AFAK( attrName: " + this.b + " viewWidget: " + this.e;
        }
    }
}
