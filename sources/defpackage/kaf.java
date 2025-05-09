package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kaf {
    public static String b(kaa kaaVar) {
        return kab.d(kaaVar);
    }

    public static List<String> d(List<Bean> list) {
        LogUtil.a("ContactVcardUtils", "generateVcards: start");
        if (list == null || list.isEmpty()) {
            LogUtil.h("ContactVcardUtils", "list is null or empty.");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        List b = kao.b(list, 100);
        StringBuilder sb = new StringBuilder();
        Iterator it = b.iterator();
        while (it.hasNext()) {
            for (Bean bean : (List) it.next()) {
                if (bean instanceof kaa) {
                    sb.append(b((kaa) bean));
                }
            }
            arrayList.add(sb.toString());
            sb.delete(0, sb.length());
        }
        LogUtil.a("ContactVcardUtils", "generateVcards: end, vcard size: ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }
}
