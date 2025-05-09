package defpackage;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public class trs {
    public static void a(tpq tpqVar) throws tnx {
        if (tpqVar == null) {
            tov.d("NotifyParamCheckUtil", "checkStyleAndButtonMatch Notification is null");
        }
        int e = tpqVar.e();
        HashMap<Integer, String> b = tpqVar.b();
        switch (e) {
            case 50:
                c(b);
                break;
            case 51:
                d(b);
                break;
            case 52:
                a(b);
                break;
            case 53:
                e(b);
                break;
        }
    }

    private static void e(HashMap<Integer, String> hashMap) throws tnx {
        b(hashMap);
        Set<Integer> keySet = hashMap.keySet();
        c(keySet, 3);
        HashSet hashSet = new HashSet();
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        b(keySet, hashSet);
        e(12, hashMap.get(2));
        e(12, hashMap.get(3));
        e(12, hashMap.get(4));
    }

    private static void a(HashMap<Integer, String> hashMap) throws tnx {
        b(hashMap);
        Set<Integer> keySet = hashMap.keySet();
        c(keySet, 2);
        HashSet hashSet = new HashSet();
        hashSet.add(2);
        hashSet.add(3);
        b(keySet, hashSet);
        e(12, hashMap.get(2));
        e(12, hashMap.get(3));
    }

    private static void d(HashMap<Integer, String> hashMap) throws tnx {
        b(hashMap);
        Set<Integer> keySet = hashMap.keySet();
        c(keySet, 1);
        HashSet hashSet = new HashSet();
        hashSet.add(2);
        b(keySet, hashSet);
        e(12, hashMap.get(2));
    }

    private static void c(HashMap<Integer, String> hashMap) throws tnx {
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        tov.d("NotifyParamCheckUtil", "checkNoButtonStyle styles and buttons not match");
        throw new tnx(5);
    }

    private static void b(Set<Integer> set, HashSet<Integer> hashSet) throws tnx {
        if (new HashSet(set).containsAll(hashSet)) {
            return;
        }
        tov.d("NotifyParamCheckUtil", "checkInputButtonIndex keySet index not match");
        throw new tnx(5);
    }

    private static void c(Set<Integer> set, int i) throws tnx {
        if (set.size() == i) {
            return;
        }
        tov.d("NotifyParamCheckUtil", "checkInputIndexSize button indexSize not match");
        throw new tnx(5);
    }

    private static void b(HashMap<Integer, String> hashMap) throws tnx {
        if (hashMap == null || hashMap.isEmpty()) {
            tov.d("NotifyParamCheckUtil", "checkHashMapIsEmpty hashMap is null or hashMap is Empty");
            throw new tnx(5);
        }
    }

    private static void e(int i, String str) throws tnx {
        try {
            if (TextUtils.isEmpty(str) || str.getBytes("UTF-8").length > i) {
                tov.d("NotifyParamCheckUtil", "checkBtnContentLength content length not match");
                throw new tnx(5);
            }
        } catch (UnsupportedEncodingException unused) {
            tov.d("NotifyParamCheckUtil", "checkBtnContentLength UnsupportedEncodingException");
            throw new tnx(5);
        }
    }

    public static void c(int i) {
        if (i != -1) {
            return;
        }
        tov.d("NotifyParamCheckUtil", "checkNotifTemplateId templateId type is error");
        throw new tnx(5);
    }
}
