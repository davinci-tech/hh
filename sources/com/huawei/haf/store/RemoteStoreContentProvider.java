package com.huawei.haf.store;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.common.base.BaseContentProvider;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class RemoteStoreContentProvider extends BaseContentProvider {
    static final String CLEAR = "CLEAR";
    private final OnSharedPreferenceChangeListenerImpl mListener = new OnSharedPreferenceChangeListenerImpl();
    private volatile ExtendSharedPreferences mSharedPreferences;

    protected abstract String getName();

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.haf.common.base.BaseContentProvider, android.content.ContentProvider
    public final Bundle call(String str, String str2, Bundle bundle) {
        char c;
        initContentProvider();
        str.hashCode();
        switch (str.hashCode()) {
            case -1249367445:
                if (str.equals("getAll")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1249359687:
                if (str.equals("getInt")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -567445985:
                if (str.equals("contains")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -198897701:
                if (str.equals("getStringSet")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -75354382:
                if (str.equals("getLong")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 804029191:
                if (str.equals("getString")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1101572082:
                if (str.equals("getBoolean")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1953351846:
                if (str.equals("getFloat")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return fillRealAll();
            case 1:
                return fillRealInt(str2);
            case 2:
                return fillRealContains(str2);
            case 3:
                return fillRealStringSet(str2);
            case 4:
                return fillRealLong(str2);
            case 5:
                return fillRealString(str2);
            case 6:
                return fillRealBoolean(str2);
            case 7:
                return fillRealFloat(str2);
            default:
                return callExtends(str, str2, bundle);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Bundle callExtends(String str, String str2, Bundle bundle) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1616528590:
                if (str.equals("containsStringSet")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1354815177:
                if (str.equals("commit")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -690213213:
                if (str.equals("register")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -327193872:
                if (str.equals("addStringSet")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 93029230:
                if (str.equals("apply")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 836015164:
                if (str.equals("unregister")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1922308717:
                if (str.equals("removeStringSet")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (bundle != null) {
                    return fillRealContainsSet(str2, bundle.getString(str2));
                }
                return null;
            case 1:
                return commit(str, str2, bundle);
            case 2:
                this.mListener.a(true);
                return null;
            case 3:
                if (bundle != null) {
                    this.mSharedPreferences.addStringSet(str2, bundle.getString(str2));
                }
                return null;
            case 4:
                apply(str2, bundle);
                return null;
            case 5:
                this.mListener.a(false);
                return null;
            case 6:
                if (bundle != null) {
                    this.mSharedPreferences.removeStringSet(str2, bundle.getString(str2));
                }
                return null;
            default:
                return null;
        }
    }

    private void initContentProvider() {
        if (this.mSharedPreferences != null) {
            return;
        }
        synchronized (this.mListener) {
            if (this.mSharedPreferences == null) {
                this.mSharedPreferences = new LocalSharedPreferences(getName(), 0);
            }
        }
    }

    private Bundle commit(String str, String str2, Bundle bundle) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        if (CLEAR.equals(str2)) {
            edit.clear();
        }
        if (bundle != null) {
            AbstrctSharedPreferences.updateValues(edit, bundle);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean(str, edit.commit());
        return bundle2;
    }

    private void apply(String str, Bundle bundle) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        if (CLEAR.equals(str)) {
            edit.clear();
        }
        if (bundle != null) {
            AbstrctSharedPreferences.updateValues(edit, bundle);
        }
        edit.apply();
    }

    private Bundle fillRealAll() {
        Map<String, ?> all = this.mSharedPreferences.getAll();
        Bundle bundle = new Bundle(all.size());
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof Set) {
                Set set = (Set) value;
                bundle.putStringArray(key, (String[]) set.toArray(new String[set.size()]));
            } else if (value instanceof Integer) {
                bundle.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                bundle.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                bundle.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                bundle.putBoolean(key, ((Boolean) value).booleanValue());
            }
        }
        return bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.os.Bundle fillRealString(java.lang.String r3) {
        /*
            r2 = this;
            com.huawei.haf.store.ExtendSharedPreferences r0 = r2.mSharedPreferences
            boolean r0 = r0.contains(r3)
            r1 = 0
            if (r0 == 0) goto L10
            com.huawei.haf.store.ExtendSharedPreferences r0 = r2.mSharedPreferences     // Catch: java.lang.ClassCastException -> L10
            java.lang.String r0 = r0.getString(r3, r1)     // Catch: java.lang.ClassCastException -> L10
            goto L11
        L10:
            r0 = r1
        L11:
            if (r0 == 0) goto L1b
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r1.putString(r3, r0)
        L1b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.store.RemoteStoreContentProvider.fillRealString(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.os.Bundle fillRealStringSet(java.lang.String r4) {
        /*
            r3 = this;
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences
            boolean r0 = r0.contains(r4)
            r1 = 0
            if (r0 == 0) goto L10
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences     // Catch: java.lang.ClassCastException -> L10
            java.util.Set r0 = r0.getStringSet(r4, r1)     // Catch: java.lang.ClassCastException -> L10
            goto L11
        L10:
            r0 = r1
        L11:
            if (r0 == 0) goto L27
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            int r2 = r0.size()
            java.lang.String[] r2 = new java.lang.String[r2]
            java.lang.Object[] r0 = r0.toArray(r2)
            java.lang.String[] r0 = (java.lang.String[]) r0
            r1.putStringArray(r4, r0)
        L27:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.store.RemoteStoreContentProvider.fillRealStringSet(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.os.Bundle fillRealInt(java.lang.String r4) {
        /*
            r3 = this;
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences
            boolean r0 = r0.contains(r4)
            r1 = 0
            if (r0 == 0) goto L15
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences     // Catch: java.lang.ClassCastException -> L15
            r2 = 0
            int r0 = r0.getInt(r4, r2)     // Catch: java.lang.ClassCastException -> L15
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.ClassCastException -> L15
            goto L16
        L15:
            r0 = r1
        L16:
            if (r0 == 0) goto L24
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            int r0 = r0.intValue()
            r1.putInt(r4, r0)
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.store.RemoteStoreContentProvider.fillRealInt(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.os.Bundle fillRealLong(java.lang.String r5) {
        /*
            r4 = this;
            com.huawei.haf.store.ExtendSharedPreferences r0 = r4.mSharedPreferences
            boolean r0 = r0.contains(r5)
            r1 = 0
            if (r0 == 0) goto L16
            com.huawei.haf.store.ExtendSharedPreferences r0 = r4.mSharedPreferences     // Catch: java.lang.ClassCastException -> L16
            r2 = 0
            long r2 = r0.getLong(r5, r2)     // Catch: java.lang.ClassCastException -> L16
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch: java.lang.ClassCastException -> L16
            goto L17
        L16:
            r0 = r1
        L17:
            if (r0 == 0) goto L25
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            long r2 = r0.longValue()
            r1.putLong(r5, r2)
        L25:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.store.RemoteStoreContentProvider.fillRealLong(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.os.Bundle fillRealFloat(java.lang.String r4) {
        /*
            r3 = this;
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences
            boolean r0 = r0.contains(r4)
            r1 = 0
            if (r0 == 0) goto L15
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences     // Catch: java.lang.ClassCastException -> L15
            r2 = 0
            float r0 = r0.getFloat(r4, r2)     // Catch: java.lang.ClassCastException -> L15
            java.lang.Float r0 = java.lang.Float.valueOf(r0)     // Catch: java.lang.ClassCastException -> L15
            goto L16
        L15:
            r0 = r1
        L16:
            if (r0 == 0) goto L24
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            float r0 = r0.floatValue()
            r1.putFloat(r4, r0)
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.store.RemoteStoreContentProvider.fillRealFloat(java.lang.String):android.os.Bundle");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.os.Bundle fillRealBoolean(java.lang.String r4) {
        /*
            r3 = this;
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences
            boolean r0 = r0.contains(r4)
            r1 = 0
            if (r0 == 0) goto L15
            com.huawei.haf.store.ExtendSharedPreferences r0 = r3.mSharedPreferences     // Catch: java.lang.ClassCastException -> L15
            r2 = 0
            boolean r0 = r0.getBoolean(r4, r2)     // Catch: java.lang.ClassCastException -> L15
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch: java.lang.ClassCastException -> L15
            goto L16
        L15:
            r0 = r1
        L16:
            if (r0 == 0) goto L24
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            boolean r0 = r0.booleanValue()
            r1.putBoolean(r4, r0)
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.store.RemoteStoreContentProvider.fillRealBoolean(java.lang.String):android.os.Bundle");
    }

    private Bundle fillRealContains(String str) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(str, this.mSharedPreferences.contains(str));
        return bundle;
    }

    private Bundle fillRealContainsSet(String str, String str2) {
        Set<String> stringSet = TextUtils.isEmpty(str2) ? null : this.mSharedPreferences.getStringSet(str, null);
        Bundle bundle = new Bundle();
        bundle.putBoolean(str, stringSet != null && stringSet.contains(str2));
        return bundle;
    }

    class OnSharedPreferenceChangeListenerImpl implements SharedPreferences.OnSharedPreferenceChangeListener {
        private int e;

        private OnSharedPreferenceChangeListenerImpl() {
        }

        void a(boolean z) {
            synchronized (this) {
                if (z) {
                    int i = this.e + 1;
                    this.e = i;
                    if (i == 1) {
                        RemoteStoreContentProvider.this.mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
                    }
                    return;
                }
                int i2 = this.e;
                if (i2 == 1) {
                    this.e = 0;
                    RemoteStoreContentProvider.this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
                } else if (i2 > 1) {
                    this.e = i2 - 1;
                }
            }
        }

        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            RemoteStoreContentProvider.this.getContext().getContentResolver().notifyChange(SharedStoreManager.zY_(RemoteStoreContentProvider.this.getName(), str), null);
        }
    }
}
