package defpackage;

import android.util.Log;
import com.huawei.openalliance.ad.constant.VastAttribute;

/* loaded from: classes9.dex */
public class sli {

    static class d {
        public boolean b;
        public boolean c;
        public boolean d;

        d() {
        }
    }

    private static d e(char c) {
        boolean z = true;
        boolean z2 = c == 'd' || c == 'L' || c == 'M' || c == 'y';
        boolean z3 = c == 'L' || c == 'M';
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
            z = false;
        }
        d dVar = new d();
        dVar.b = z2;
        dVar.d = z3;
        dVar.c = z;
        return dVar;
    }

    public static char[] e(String str) {
        char[] cArr = new char[3];
        if (str == null) {
            Log.w("ICU", "getDateFormatOrder method: parms pattern is null");
            return cArr;
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (i < length) {
            char charAt = str.charAt(i);
            d e = e(charAt);
            if (e.b) {
                if (i2 < 3 && i2 >= 0) {
                    if (charAt == 'd' && !z) {
                        cArr[i2] = 'd';
                        i2++;
                        z = true;
                    } else if (e.d && !z2) {
                        cArr[i2] = 'M';
                        i2++;
                        z2 = true;
                    } else if (charAt != 'y' || z3) {
                        Log.e("ICU", VastAttribute.ERROR);
                    } else {
                        cArr[i2] = 'y';
                        i2++;
                        z3 = true;
                    }
                }
            } else if (charAt == 'G') {
                Log.w("TAG", "ignore");
            } else {
                if (e.c) {
                    Log.w("ICU", "Bad pattern character '" + charAt + "' in " + str);
                    return cArr;
                }
                if (charAt == '\'') {
                    if (i < str.length() - 1) {
                        int i3 = i + 1;
                        if (str.charAt(i3) == '\'') {
                            i = i3;
                        }
                    }
                    int indexOf = str.indexOf(39, i + 1);
                    if (indexOf == -1) {
                        Log.w("ICU", "Bad quoting in " + str);
                        return cArr;
                    }
                    i = indexOf + 1;
                } else {
                    Log.w("TAG", "ignore");
                }
            }
            i++;
        }
        return cArr;
    }
}
