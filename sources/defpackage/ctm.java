package defpackage;

import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import defpackage.cth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ctm {
    private static final List<String> b = new ArrayList<String>() { // from class: ctm.2
        {
            add("117K");
            add("1808");
            add("117V");
            add("007B");
            add("M00D");
            add("M00F");
        }
    };

    public static void d(Map<String, Object> map, cth.a aVar) {
        if (map == null) {
            cpw.e(false, CommonUtil.TAG, "setJsonCoapData jsonMap is null.");
            return;
        }
        if (aVar == null) {
            cpw.e(false, CommonUtil.TAG, "setJsonCoapData info is null.");
            return;
        }
        if (map.containsKey("sn") && (map.get("sn") instanceof String)) {
            aVar.g((String) map.get("sn"));
        }
        if (map.containsKey("model") && (map.get("model") instanceof String)) {
            aVar.j((String) map.get("model"));
        }
        if (map.containsKey("devType") && (map.get("devType") instanceof String)) {
            aVar.e((String) map.get("devType"));
        }
        if (map.containsKey(ProfileRequestConstants.MANU) && (map.get(ProfileRequestConstants.MANU) instanceof String)) {
            aVar.i((String) map.get(ProfileRequestConstants.MANU));
        }
        if (map.containsKey("mac") && (map.get("mac") instanceof String)) {
            aVar.a((String) map.get("mac"));
        }
        if (map.containsKey(ProfileRequestConstants.PROT_TYPE)) {
            aVar.d(Integer.valueOf(nsn.e(map.get(ProfileRequestConstants.PROT_TYPE).toString())));
        }
        c(map, aVar);
    }

    public static void c(Map<String, Object> map, cth.a aVar) {
        if (map.containsKey(ProfileRequestConstants.HIV) && (map.get(ProfileRequestConstants.HIV) instanceof String)) {
            aVar.c((String) map.get(ProfileRequestConstants.HIV));
        }
        if (map.containsKey(ProfileRequestConstants.FWV) && (map.get(ProfileRequestConstants.FWV) instanceof String)) {
            aVar.b((String) map.get(ProfileRequestConstants.FWV));
        }
        if (map.containsKey(ProfileRequestConstants.HWV) && (map.get(ProfileRequestConstants.HWV) instanceof String)) {
            aVar.d((String) map.get(ProfileRequestConstants.HWV));
        }
        if (map.containsKey(ProfileRequestConstants.SWV) && (map.get(ProfileRequestConstants.SWV) instanceof String)) {
            aVar.f((String) map.get(ProfileRequestConstants.SWV));
        }
        if (map.containsKey("prodId") && (map.get("prodId") instanceof String)) {
            aVar.h((String) map.get("prodId"));
        }
    }

    public static List<ScanResult> b(List<ScanResult> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                int i2 = 0;
                while (i2 < (list.size() - i) - 1) {
                    int i3 = i2 + 1;
                    if (list.get(i2).level < list.get(i3).level) {
                        ScanResult scanResult = list.get(i2);
                        list.set(i2, list.get(i3));
                        list.set(i3, scanResult);
                    }
                    i2 = i3;
                }
            }
        }
        return list;
    }

    public static String e(String str) {
        return !TextUtils.isEmpty(str) ? (str.matches("^(0086)?[1][0-9]{10}$") || str.matches("^(0086)?[1][0-9]{2}(\\*){4}[0-9]{4}$")) ? str.substring(str.indexOf("0086") + 4, str.length()) : str : str;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, CommonUtil.TAG, "isWiFiScale hilinkProid is null");
            return false;
        }
        if (b.contains(str)) {
            return true;
        }
        cpw.a(false, CommonUtil.TAG, "isWiFiScale hilinkProid is other:", str);
        return false;
    }
}
