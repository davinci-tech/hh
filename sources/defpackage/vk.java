package defpackage;

import com.huawei.ads.adsrec.e1;
import com.huawei.ads.fund.util.StringUtils;
import java.util.Map;

/* loaded from: classes2.dex */
public class vk {
    public static boolean b(String str, String str2, Map<String, String> map) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        if (map == null || StringUtils.isBlank(map.get(str2))) {
            for (String str3 : e1.b) {
                if (str.equals(str3)) {
                    return true;
                }
            }
        } else {
            for (String str4 : map.get(str2).split(",")) {
                if (str.equals(str4)) {
                    return true;
                }
            }
        }
        return false;
    }
}
