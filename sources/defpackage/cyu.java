package defpackage;

import com.huawei.health.ecologydevice.fitness.factory.RopeDataFactory;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class cyu {
    private static final Map<String, RopeDataFactory> c = new HashMap();

    private static void a(String str) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 721504305) {
            if (str.equals("RopeControlSubPackage")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 1151864826) {
            if (hashCode == 1960780819 && str.equals("RopeControlSingle")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("RopeConfigSubPackage")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            c.put("RopeControlSubPackage", new cyx());
        } else if (c2 == 1) {
            c.put("RopeConfigSubPackage", new cyr());
        } else {
            if (c2 != 2) {
                return;
            }
            c.put("RopeControlSingle", new cyy());
        }
    }

    public static RopeDataFactory d(String str) {
        LogUtil.a("CommandHandlerFactory", "type = ", str);
        Map<String, RopeDataFactory> map = c;
        if (map.get(str) == null) {
            a(str);
        }
        return map.get(str);
    }
}
