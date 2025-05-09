package defpackage;

import android.text.TextUtils;
import com.huawei.operation.utils.Constants;

/* loaded from: classes6.dex */
public class mcq {
    public static boolean a(String str) {
        return TextUtils.isEmpty(str) || TextUtils.equals(Constants.NULL, str) || TextUtils.equals("NULL", str);
    }
}
