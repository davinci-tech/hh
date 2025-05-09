package defpackage;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.huawei.haf.store.AbstrctSharedPreferences;
import com.huawei.haf.store.SharedPreferencesAdapter;
import com.tencent.mmkv.MMKV;
import health.compact.a.LogUtil;
import health.compact.a.MmkvUtil;

/* loaded from: classes.dex */
final class byy extends SharedPreferencesAdapter {
    private final MMKV c;

    byy(String str, boolean z) {
        super(null, false);
        this.c = MmkvUtil.e(str, byz.c(z));
    }

    @Override // com.huawei.haf.store.SharedPreferencesAdapter, android.content.SharedPreferences
    public String getString(String str, String str2) {
        return this.c.getString(str, str2);
    }

    @Override // com.huawei.haf.store.SharedPreferencesAdapter, android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new c(this.c);
    }

    /* loaded from: classes3.dex */
    static class c extends AbstrctSharedPreferences.AbstractEditor {
        private final SharedPreferences.Editor d;

        c(SharedPreferences.Editor editor) {
            this.d = editor;
        }

        @Override // com.huawei.haf.store.AbstrctSharedPreferences.AbstractEditor
        public boolean update(boolean z, boolean z2, Bundle bundle) {
            if (z2) {
                this.d.clear();
            }
            if (bundle.isEmpty()) {
                return true;
            }
            Ce_(bundle);
            return true;
        }

        private void Ce_(Bundle bundle) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj == null) {
                    this.d.remove(str);
                } else if (obj instanceof String) {
                    this.d.putString(str, (String) obj);
                } else {
                    LogUtil.a("MmvkAdapter", "Non string is not supported, key=", str);
                }
            }
        }
    }
}
