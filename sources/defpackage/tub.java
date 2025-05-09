package defpackage;

import android.text.TextUtils;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsMax;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsMin;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotEmpty;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;

/* loaded from: classes7.dex */
public final class tub {
    public static String d(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : str;
    }

    public static String c(KfsNotEmpty kfsNotEmpty, String str) {
        return d(kfsNotEmpty.message(), str + " can't be empty");
    }

    public static String c(KfsSize kfsSize, String str) {
        return d(kfsSize.message(), str + " len must between [" + kfsSize.min() + ", " + kfsSize.max() + "]");
    }

    public static String e(KfsMin kfsMin, String str) {
        return d(kfsMin.message(), str + " must >= " + kfsMin.value());
    }

    public static String a(KfsMax kfsMax, String str) {
        return d(kfsMax.message(), str + " must <= " + kfsMax.value());
    }
}
