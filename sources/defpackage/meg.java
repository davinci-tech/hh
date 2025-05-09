package defpackage;

import android.text.TextUtils;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class meg {
    private static boolean a(int i, int i2) {
        if (i > i2) {
            return true;
        }
        return i == i2 && i2 != 0;
    }

    public static int b(int i, int i2) {
        return (i2 != 0 && i2 <= 1) ? i + 1 : i2;
    }

    public static String c(String str, String str2) {
        if (str2 == null) {
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (a(d(str), d(str2))) {
            return str;
        }
        List<String> c = c(str2.split(","));
        List<String> c2 = c(str.split(","));
        return c2.containsAll(c) ? str : c.containsAll(c2) ? str2 : b(c2, c);
    }

    private static List<String> c(String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        arrayList.addAll(Arrays.asList(strArr));
        return arrayList;
    }

    public static String b(List<String> list, List<String> list2) {
        if (list == null || list2 == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            if (list2.indexOf(str) != -1) {
                arrayList.add(str);
            }
            b(str, sb, sb2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            list2.remove((String) it.next());
        }
        arrayList.clear();
        Iterator<String> it2 = list2.iterator();
        while (it2.hasNext()) {
            b(it2.next(), sb, sb2);
        }
        return d(sb, sb2);
    }

    private static String d(StringBuilder sb, StringBuilder sb2) {
        c(sb, sb2);
        return sb.toString();
    }

    private static StringBuilder c(StringBuilder sb, StringBuilder sb2) {
        if (sb2.length() == 0) {
            return sb;
        }
        if (sb.length() == 0) {
            sb.append((CharSequence) sb2);
            return sb;
        }
        sb.append("," + ((Object) sb2));
        return sb;
    }

    private static void b(String str, StringBuilder sb, StringBuilder sb2) {
        if (str.contains("A")) {
            if (sb.length() > 0) {
                sb.append("," + str);
                return;
            }
            sb.append(str);
            return;
        }
        if (sb2.length() > 0) {
            sb2.append("," + str);
            return;
        }
        sb2.append(str);
    }

    private static int d(String str) {
        Iterator it = Arrays.asList(str.split(",")).iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((String) it.next()).contains("A")) {
                i++;
            }
        }
        return i;
    }

    public static int a(AchieveInfo achieveInfo) {
        return (achieveInfo == null || TextUtils.isEmpty(achieveInfo.getHuid()) || achieveInfo.acquireDataType() == -1 || achieveInfo.getUserLevel() == -1 || achieveInfo.getUserPoint() == -1 || achieveInfo.getSyncTimestamp() == -1) ? -1 : 0;
    }
}
