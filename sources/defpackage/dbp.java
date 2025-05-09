package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class dbp {
    public static cjv b(dcz dczVar) {
        LogUtil.a("ChangeClassType", " changeHuaweiDeviceClass()");
        cjv cjvVar = new cjv();
        cjvVar.c(dczVar);
        cjvVar.a(0);
        return cjvVar;
    }

    public static ArrayList<cjv> c(ArrayList<dcz> arrayList) {
        LogUtil.a("ChangeClassType", " changeHuaweiDeviceClassList()");
        ArrayList<cjv> arrayList2 = new ArrayList<>();
        if (arrayList == null) {
            return arrayList2;
        }
        Iterator<dcz> it = arrayList.iterator();
        while (it.hasNext()) {
            dcz next = it.next();
            cjv cjvVar = new cjv();
            cjvVar.c(next);
            cjvVar.a(0);
            cjvVar.a(next.h());
            arrayList2.add(cjvVar);
        }
        return arrayList2;
    }
}
