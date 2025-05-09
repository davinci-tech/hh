package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.store.AbstrctSharedPreferences;
import com.huawei.haf.store.SharedPreferencesAdapter;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.openalliance.ad.constant.SpKeys;
import com.tencent.mmkv.MMKV;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogUtil;
import health.compact.a.MmkvUtil;
import health.compact.a.StorageParams;
import health.compact.a.WhiteBoxManager;

/* loaded from: classes.dex */
final class byz extends SharedPreferencesAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f550a;
    private final MMKV b;

    byz(boolean z) {
        super(null, false);
        this.f550a = z;
        this.b = MmkvUtil.e(d(z), c(z));
    }

    @Override // com.huawei.haf.store.SharedPreferencesAdapter, android.content.SharedPreferences
    public String getString(String str, String str2) {
        String d2;
        Context e = BaseApplication.e();
        if (!this.f550a) {
            d2 = KeyValDbManager.b(e).e(str);
        } else {
            String string = this.b.getString(str, null);
            d2 = string == null ? KeyValDbManager.b(e).d(str, b(str)) : string;
        }
        return d2 == null ? str2 : d2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private StorageParams b(String str) {
        char c;
        str.hashCode();
        int i = 0;
        switch (str.hashCode()) {
            case -2041082371:
                if (str.equals("server_token")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1866251127:
                if (str.equals("cached_wear_place_info")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 25209764:
                if (str.equals("device_id")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 625233472:
                if (str.equals("service_country_code")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1481071862:
                if (str.equals(SpKeys.BELONG_COUNTRY_CODE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1913693945:
                if (str.equals(HealthAccessTokenUtil.KEY_AT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            i = 14;
        } else if (c == 1 || c == 2 || c == 3 || c == 4 || c == 5) {
            i = 1;
        } else {
            LogUtil.a("KeyValDbAdapter", "Migration adaptation may be required here. key=", str);
        }
        return new StorageParams(i);
    }

    @Override // com.huawei.haf.store.SharedPreferencesAdapter, android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new d(this.b);
    }

    static String c(boolean z) {
        if (z) {
            return d();
        }
        return null;
    }

    private static String d() {
        WhiteBoxManager d2 = WhiteBoxManager.d();
        return d2.d(1, 34) + d2.d(1, 1034) + d2.d(1, 2034);
    }

    /* loaded from: classes3.dex */
    static class d extends AbstrctSharedPreferences.AbstractEditor {
        private final SharedPreferences.Editor b;

        d(SharedPreferences.Editor editor) {
            this.b = editor;
        }

        @Override // com.huawei.haf.store.AbstrctSharedPreferences.AbstractEditor
        public boolean update(boolean z, boolean z2, Bundle bundle) {
            if (z2) {
                this.b.clear();
            }
            if (bundle.isEmpty()) {
                return true;
            }
            Cd_(BaseApplication.e(), bundle);
            return true;
        }

        private void Cd_(Context context, Bundle bundle) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj == null) {
                    KeyValDbManager.b(context).c(str);
                } else if (obj instanceof String) {
                    this.b.putString(str, (String) obj);
                } else {
                    LogUtil.a("KeyValDbAdapter", "Non string is not supported, key=", str);
                }
            }
        }
    }

    private static String d(boolean z) {
        return z ? "keyvaldb_encrypt" : "keyvaldb_unencrypt";
    }
}
