package defpackage;

import com.huawei.skinner.attrentry.SkinAttr;
import defpackage.ncl;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ndh extends HashMap<ncl.c, Class<? extends SkinAttr>> {
    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Class<? extends SkinAttr> put(ncl.c cVar, Class<? extends SkinAttr> cls) {
        Class<? extends SkinAttr> cls2 = get(cVar);
        if (cls2 != null) {
            try {
                if (cls.newInstance().getPriority() > cls2.newInstance().getPriority()) {
                    return (Class) super.put(cVar, cls);
                }
                return null;
            } catch (IllegalAccessException e) {
                ncx.e("SkinAttrPriorityMap.put: IllegalAccessException: " + e.getMessage());
                return null;
            } catch (InstantiationException e2) {
                ncx.e("SkinAttrPriorityMap.put: InstantiationException: " + e2.getMessage());
                return null;
            }
        }
        return (Class) super.put(cVar, cls);
    }
}
