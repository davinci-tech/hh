package defpackage;

import com.tencent.open.SocialConstants;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class suk {
    public static void e(String str, int i, String str2) {
        b(str, str2, true);
    }

    private static void b(final String str, final String str2, final boolean z) {
        svu.b().eXR_().post(new Runnable() { // from class: sui
            @Override // java.lang.Runnable
            public final void run() {
                suk.e(str2, z, str);
            }
        });
    }

    static /* synthetic */ void e(String str, boolean z, String str2) {
        int i;
        String substring;
        int length = str.length();
        if (length % 24576 == 0) {
            i = length / 24576;
        } else {
            i = (length / 24576) + 1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (z) {
            linkedHashMap.put("log", stt.c());
        }
        if (length == 0) {
            linkedHashMap.put("group_time", currentTimeMillis + "");
            linkedHashMap.put(SocialConstants.PARAM_APP_DESC, str);
            sun.e(0, str2, linkedHashMap);
            return;
        }
        int i2 = 0;
        while (i2 < i) {
            if (i2 == i - 1) {
                substring = str.substring(i2 * 24576, length);
            } else {
                substring = str.substring(i2 * 24576, (i2 + 1) * 24576);
            }
            linkedHashMap.put("group_time", currentTimeMillis + "");
            linkedHashMap.put("total_segment", i + "");
            StringBuilder sb = new StringBuilder();
            i2++;
            sb.append(i2);
            sb.append("");
            linkedHashMap.put("subindex", sb.toString());
            linkedHashMap.put(SocialConstants.PARAM_APP_DESC, substring);
            sun.e(0, str2, linkedHashMap);
        }
    }

    public static void e(String str, String str2) {
        e(str, 2, str2);
    }

    public static void d(String str) {
        e("RequestAndResponse_Message", 2, str);
    }
}
