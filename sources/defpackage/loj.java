package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class loj {
    private static HashMap<String, String> e = new HashMap<>();
    private static ArrayList<String> c = new ArrayList<>();

    static class c {
        private static final loj b = new loj();
    }

    public static loj d() {
        return c.b;
    }

    private loj() {
    }

    public void e(Context context) {
        if (context == null) {
            return;
        }
        String[] stringArray = context.getResources().getStringArray(R.array._2130968653_res_0x7f04004d);
        String[] stringArray2 = context.getResources().getStringArray(R.array._2130968652_res_0x7f04004c);
        if (stringArray2.length != stringArray.length) {
            return;
        }
        int length = stringArray2.length;
        for (int i = 0; i < length; i++) {
            String str = stringArray2[i];
            if (!c.contains(str)) {
                c.add(str);
            }
            if (!e.containsKey(str)) {
                e.put(str, stringArray[i]);
            }
        }
    }

    public String a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String e2 = e(context, str);
        return !TextUtils.isEmpty(str) ? e.get(e2) : e2;
    }

    private String e(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (c.isEmpty()) {
            e(context);
        }
        int size = c.size();
        for (int i = 0; i < size; i++) {
            String str2 = c.get(i);
            if (str.matches(str2)) {
                return str2;
            }
        }
        return null;
    }
}
