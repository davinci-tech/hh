package defpackage;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes9.dex */
public class otj {
    public static void b(Context context, String str) {
        List<String> b = b(context);
        b.remove(str);
        b.add(0, str);
        while (b.size() > 0 && b.size() > 10) {
            b.remove(b.size() - 1);
        }
        d(context, b);
    }

    public static List<String> b(Context context) {
        List list = (List) kps.e(SharedPreferenceManager.b(context, String.valueOf(12000), "recent_search"), new TypeToken<List<String>>() { // from class: otj.5
        }.getType());
        if (list == null) {
            list = new ArrayList();
        }
        return c(list);
    }

    public static void d(Context context, List<String> list) {
        SharedPreferenceManager.e(context, String.valueOf(12000), "recent_search", kps.a(list, new TypeToken<List<String>>() { // from class: otj.1
        }.getType()), new StorageParams());
    }

    public static List<String> c(List<String> list) {
        if (list == null) {
            return new ArrayList();
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!hashSet.contains(str)) {
                hashSet.add(str);
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
