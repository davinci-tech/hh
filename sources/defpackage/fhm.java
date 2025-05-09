package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fhm {
    private fhi c;
    private fhf d = new fhf();

    public fhm(int i, int i2) {
        this.c = c(i, i2);
    }

    private fhn e() {
        try {
            fhn fhnVar = (fhn) ixu.d(BaseApplication.e().getAssets().open("SportServiceTemplate.json"), fhn.class);
            Object[] objArr = new Object[2];
            objArr[0] = "mDefaultConfig : ";
            objArr[1] = fhnVar == null ? 0 : fhnVar.a();
            ReleaseLogUtil.e("SportService_SportServiceTemplateManager", objArr);
            return fhnVar;
        } catch (IOException e) {
            ReleaseLogUtil.c("SportService_SportServiceTemplateManager", "loadDefaultCardSetConfig failed, ", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    private fhi c(int i, int i2) {
        fhn e = e();
        if (e == null) {
            return null;
        }
        fhi e2 = e.e();
        if (koq.b(e.b())) {
            return null;
        }
        for (fhi fhiVar : e.b()) {
            if (fhiVar != null && fhiVar.b(i, i2)) {
                fhiVar.d(e2);
                return fhiVar;
            }
        }
        return null;
    }

    public List<String> b() {
        fhi fhiVar = this.c;
        if (fhiVar == null || this.d == null) {
            return Collections.EMPTY_LIST;
        }
        if (koq.c(fhiVar.a())) {
            ArrayList arrayList = new ArrayList(this.c.a().size());
            for (fhg fhgVar : this.c.a()) {
                if (fhgVar != null) {
                    arrayList.add(this.d.e(fhgVar.b()));
                }
            }
            return arrayList;
        }
        return Collections.EMPTY_LIST;
    }

    public List<String> a() {
        fhi fhiVar = this.c;
        if (fhiVar == null || this.d == null) {
            return Collections.EMPTY_LIST;
        }
        if (koq.c(fhiVar.b())) {
            ArrayList arrayList = new ArrayList(this.c.b().size());
            for (fhg fhgVar : this.c.b()) {
                if (fhgVar != null) {
                    arrayList.add(this.d.a(fhgVar.b()));
                }
            }
            return arrayList;
        }
        return Collections.EMPTY_LIST;
    }

    public List<String> d() {
        fhi fhiVar = this.c;
        if (fhiVar == null || this.d == null) {
            return Collections.EMPTY_LIST;
        }
        if (koq.c(fhiVar.c())) {
            ArrayList arrayList = new ArrayList(this.c.c().size());
            for (fhg fhgVar : this.c.c()) {
                if (fhgVar != null) {
                    arrayList.add(this.d.c(fhgVar.b()));
                }
            }
            return arrayList;
        }
        return Collections.EMPTY_LIST;
    }

    public Map<SportComponentType, Class<?>> d(int i) {
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            arrayList.addAll(b());
        } else if (i == 2) {
            arrayList.addAll(a());
        } else {
            arrayList.addAll(d());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(arrayList.size());
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Class<?> cls = Class.forName((String) it.next());
                linkedHashMap.put((SportComponentType) cls.getAnnotation(SportComponentType.class), cls);
            }
        } catch (ClassNotFoundException e) {
            ReleaseLogUtil.c("SportService_SportServiceTemplateManager", "getConstructor failed", LogAnonymous.b((Throwable) e));
        }
        LogUtil.h("SportService_SportServiceTemplateManager", "classify:", Integer.valueOf(i), " data: ", linkedHashMap.toString());
        return linkedHashMap;
    }

    public <T> T c(Class<?> cls, Class<T> cls2) {
        if (cls == null || cls2 == null) {
            return null;
        }
        try {
            return cls2.cast(cls.newInstance());
        } catch (ClassCastException | IllegalAccessException | InstantiationException e) {
            ReleaseLogUtil.c("SportService_SportServiceTemplateManager", "getConstructor failed, clsName", LogAnonymous.b(e));
            return null;
        }
    }
}
