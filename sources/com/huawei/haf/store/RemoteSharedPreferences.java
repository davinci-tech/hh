package com.huawei.haf.store;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.store.AbstrctSharedPreferences;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class RemoteSharedPreferences extends AbstrctSharedPreferences implements ExtendSharedPreferences {

    /* renamed from: a, reason: collision with root package name */
    private ContentObserver f2156a;
    private final Uri e;

    RemoteSharedPreferences(String str) {
        this.e = SharedStoreManager.zY_(str, null);
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        Bundle call = zW_().call(this.e, "getAll", (String) null, (Bundle) null);
        if (call == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap(call.size());
        for (String str : call.keySet()) {
            Object obj = call.get(str);
            if (obj instanceof String[]) {
                hashMap.put(str, new HashSet(Arrays.asList((String[]) obj)));
            } else {
                hashMap.put(str, obj);
            }
        }
        return hashMap;
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        Bundle call = zW_().call(this.e, "getString", str, (Bundle) null);
        return call != null ? call.getString(str, str2) : str2;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        String[] stringArray;
        Bundle call = zW_().call(this.e, "getStringSet", str, (Bundle) null);
        return (call == null || (stringArray = call.getStringArray(str)) == null) ? set : new HashSet(Arrays.asList(stringArray));
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        Bundle call = zW_().call(this.e, "getInt", str, (Bundle) null);
        return call != null ? call.getInt(str, i) : i;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        Bundle call = zW_().call(this.e, "getLong", str, (Bundle) null);
        return call != null ? call.getLong(str, j) : j;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        Bundle call = zW_().call(this.e, "getFloat", str, (Bundle) null);
        return call != null ? call.getFloat(str, f) : f;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        Bundle call = zW_().call(this.e, "getBoolean", str, (Bundle) null);
        return call != null ? call.getBoolean(str, z) : z;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        Bundle call = zW_().call(this.e, "contains", str, (Bundle) null);
        if (call != null) {
            return call.getBoolean(str, false);
        }
        return false;
    }

    @Override // com.huawei.haf.store.ExtendSharedPreferences
    public void addStringSet(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        zW_().call(this.e, "addStringSet", str, bundle);
    }

    @Override // com.huawei.haf.store.ExtendSharedPreferences
    public void removeStringSet(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        zW_().call(this.e, "removeStringSet", str, bundle);
    }

    @Override // com.huawei.haf.store.ExtendSharedPreferences
    public boolean containsStringSet(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        Bundle call = zW_().call(this.e, "containsStringSet", str, bundle);
        if (call != null) {
            return call.getBoolean(str, false);
        }
        return false;
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new EditorImpl();
    }

    @Override // com.huawei.haf.store.AbstrctSharedPreferences
    protected void updateContentObserver(boolean z) {
        if (!z) {
            if (this.f2156a != null) {
                zW_().call(this.e, "unregister", (String) null, (Bundle) null);
                zW_().unregisterContentObserver(this.f2156a);
                this.f2156a = null;
                return;
            }
            return;
        }
        if (this.f2156a == null) {
            this.f2156a = new ContentObserverImpl();
            zW_().registerContentObserver(this.e, true, this.f2156a);
            zW_().call(this.e, "register", (String) null, (Bundle) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentResolver zW_() {
        return BaseApplication.e().getContentResolver();
    }

    class ContentObserverImpl extends ContentObserver {
        ContentObserverImpl() {
            super(null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null) {
                return;
            }
            String a2 = a(uri.toString());
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            RemoteSharedPreferences.this.onContentChange(a2);
        }

        private String a(String str) {
            String uri = RemoteSharedPreferences.this.e.toString();
            String substring = str.length() > uri.length() ? str.substring(uri.length() + 1) : null;
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            return substring;
        }
    }

    class EditorImpl extends AbstrctSharedPreferences.AbstractEditor {
        private EditorImpl() {
        }

        @Override // com.huawei.haf.store.AbstrctSharedPreferences.AbstractEditor
        protected boolean update(boolean z, boolean z2, Bundle bundle) {
            String str = z ? "commit" : "apply";
            Bundle call = RemoteSharedPreferences.this.zW_().call(RemoteSharedPreferences.this.e, str, z2 ? "CLEAR" : null, new Bundle(bundle));
            if (call == null) {
                return false;
            }
            return call.getBoolean(str, false);
        }
    }
}
