package defpackage;

import android.util.Log;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes7.dex */
public class ja {
    public static boolean e = false;

    public static void e(String str, Object... objArr) {
        if (e) {
            Log.d("OpenId", b(str, objArr));
        }
    }

    public static String b(String str, Object... objArr) {
        if (str == null && objArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Object[] objArr2 = new Object[1];
        if (str == null) {
            str = Constants.LINK;
        }
        int i = 0;
        objArr2[0] = str;
        sb.append(String.format("[%s] ", objArr2));
        if (objArr != null) {
            int length = objArr.length;
            while (true) {
                int i2 = i + 1;
                if (i2 >= objArr.length) {
                    break;
                }
                sb.append(a(objArr[i], objArr[i2]));
                if (i2 < length - 1) {
                    sb.append(",");
                }
                i += 2;
            }
            if (i == objArr.length - 1) {
                sb.append(objArr[i]);
            }
        }
        return sb.toString();
    }

    public static String a(Object obj, Object obj2) {
        Object[] objArr = new Object[2];
        if (obj == null) {
            obj = "";
        }
        objArr[0] = obj;
        if (obj2 == null) {
            obj2 = "";
        }
        objArr[1] = obj2;
        return String.format("%s:%s", objArr);
    }
}
