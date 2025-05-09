package defpackage;

import com.huawei.hihealth.util.HiJsonUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class iur {
    private Map<String, Double> b;

    public iur() {
        this.b = new HashMap();
    }

    public void b(String str, double d) {
        Map<String, Double> map = this.b;
        if (map == null) {
            return;
        }
        map.put(str, Double.valueOf(d));
    }

    public String b() {
        Map<String, Double> map = this.b;
        if (map == null) {
            return null;
        }
        return HiJsonUtil.d(map, Map.class);
    }

    public iur(String str) {
        this.b = (Map) HiJsonUtil.a(str, Map.class);
    }

    public Map<String, Double> c() {
        return this.b;
    }
}
