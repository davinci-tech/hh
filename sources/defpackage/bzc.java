package defpackage;

import android.content.SharedPreferences;
import com.huawei.haf.store.KeyValueStoreFactory;
import com.tencent.mmkv.MMKV;
import health.compact.a.LogUtil;
import health.compact.a.MmkvUtil;

/* loaded from: classes.dex */
public final class bzc implements KeyValueStoreFactory {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.haf.store.KeyValueStoreFactory
    public SharedPreferences create(String str) {
        char c;
        LogUtil.c("HealthKeyValueFactory", "create name=", str);
        str.hashCode();
        switch (str.hashCode()) {
            case -2077061754:
                if (str.equals("keyvaldb_encrypt")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1117327015:
                if (str.equals("CommonInfo")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -969877599:
                if (str.equals("keyvaldb_encrypt_udsdevice")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -405779315:
                if (str.equals("keyvaldb_unencrypt")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return new byz(true);
        }
        if (c == 1) {
            return new byw();
        }
        if (c == 2) {
            return new byy(str, true);
        }
        if (c == 3) {
            return new byz(false);
        }
        return MmkvUtil.e(str, null);
    }

    @Override // com.huawei.haf.store.KeyValueStoreFactory
    public boolean close(String str, SharedPreferences sharedPreferences) {
        LogUtil.c("HealthKeyValueFactory", "close name=", str);
        if (!(sharedPreferences instanceof MMKV)) {
            return false;
        }
        ((MMKV) sharedPreferences).close();
        return true;
    }
}
