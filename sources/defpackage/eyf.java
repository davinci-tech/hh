package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class eyf {
    public static List<String> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!TextUtils.isEmpty(str) && str.contains("/")) {
                int lastIndexOf = str.lastIndexOf("/");
                if (lastIndexOf == -1) {
                    return arrayList;
                }
                arrayList.add(str.substring(0, lastIndexOf));
            }
        }
        return arrayList;
    }

    public static List<String> d(List<String> list) {
        String substring;
        int lastIndexOf;
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!TextUtils.isEmpty(str) && str.contains("/")) {
                int lastIndexOf2 = str.lastIndexOf("/");
                if (lastIndexOf2 == -1 || (lastIndexOf = (substring = str.substring(0, lastIndexOf2)).lastIndexOf("/")) == -1) {
                    return arrayList;
                }
                String substring2 = substring.substring(0, lastIndexOf);
                int i = lastIndexOf + 1;
                String substring3 = i <= substring.length() ? substring.substring(i) : "";
                arrayList.add(substring2);
                arrayList.add(substring3);
            }
        }
        return arrayList;
    }
}
