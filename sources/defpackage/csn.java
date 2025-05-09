package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class csn {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("MultcastUitl", "src is empty");
            return false;
        }
        return Pattern.compile("239\\.[456][0]\\.[0-9]{1,3}\\.[0-9]{1,3}").matcher(str.replaceAll("/", "")).matches();
    }

    public static int[] e(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return null;
        }
        int length = iArr.length * 8;
        int i = length / 3;
        if (length % 3 != 0) {
            i++;
        }
        int[] iArr2 = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 3;
            int e = e(i3, iArr);
            int e2 = e(i3 + 1, iArr);
            int e3 = e(i3 + 2, iArr);
            if (i2 >= 176) {
                break;
            }
            iArr2[i2] = ((e3 << 2) | (i2 << 3) | (e2 << 1) | e) + 49;
        }
        LogUtil.a("MultcastUitl", "OctData len ", Integer.valueOf(i));
        return iArr2;
    }

    private static int e(int i, int[] iArr) {
        int i2 = i / 8;
        int i3 = i % 8;
        int[] iArr2 = {1, 2, 4, 8, 16, 32, 64, 128};
        if (i >= iArr.length * 8 || i < 0 || iArr.length <= i2) {
            return 0;
        }
        return (iArr[i2] & iArr2[i3]) >> i3;
    }
}
