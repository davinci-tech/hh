package defpackage;

import com.huawei.wisesecurity.kfs.validation.constrains.KfsSize;

/* loaded from: classes7.dex */
public class tvp {
    public static void a(KfsSize kfsSize) throws ttr {
        int min = kfsSize.min();
        int max = kfsSize.max();
        if (min < 0) {
            throw new ttr("min can't be negative");
        }
        if (max < 0) {
            throw new ttr("max can't be negative");
        }
        if (max < min) {
            throw new ttr("max should be bigger than min");
        }
    }
}
