package com.huawei.haf.store;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.huawei.haf.common.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public abstract class AbstrctSharedPreferences implements SharedPreferences {
    private final WeakHashMap<SharedPreferences.OnSharedPreferenceChangeListener, Object> mListeners = new WeakHashMap<>();

    protected abstract void updateContentObserver(boolean z);

    @Override // android.content.SharedPreferences
    public final void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (onSharedPreferenceChangeListener != null) {
            synchronized (this.mListeners) {
                this.mListeners.put(onSharedPreferenceChangeListener, this);
                updateContentObserver(!this.mListeners.isEmpty());
            }
        }
    }

    @Override // android.content.SharedPreferences
    public final void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (onSharedPreferenceChangeListener != null) {
            synchronized (this.mListeners) {
                this.mListeners.remove(onSharedPreferenceChangeListener);
                updateContentObserver(!this.mListeners.isEmpty());
            }
        }
    }

    protected final void onContentChange(String str) {
        synchronized (this.mListeners) {
            if (this.mListeners.isEmpty()) {
                updateContentObserver(false);
                return;
            }
            Iterator it = new ArrayList(this.mListeners.keySet()).iterator();
            while (it.hasNext()) {
                ((SharedPreferences.OnSharedPreferenceChangeListener) it.next()).onSharedPreferenceChanged(this, str);
            }
        }
    }

    protected static void updateValues(SharedPreferences.Editor editor, Bundle bundle) {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj == null) {
                editor.remove(str);
            } else if (obj instanceof String) {
                editor.putString(str, (String) obj);
            } else if (obj instanceof String[]) {
                updateStringSet(editor, str, (String[]) obj);
            } else if (obj instanceof Integer) {
                editor.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                editor.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Float) {
                editor.putFloat(str, ((Float) obj).floatValue());
            } else if (obj instanceof Boolean) {
                editor.putBoolean(str, ((Boolean) obj).booleanValue());
            }
        }
    }

    private static void updateStringSet(SharedPreferences.Editor editor, String str, String[] strArr) {
        if (CollectionUtils.b(strArr)) {
            editor.remove(str);
        } else {
            editor.putStringSet(str, new HashSet(Arrays.asList(strArr)));
        }
    }

    public static abstract class AbstractEditor implements SharedPreferences.Editor {
        private final Bundle mModified = new Bundle();
        private boolean mIsClear = false;
        private final Object mEditorLock = new Object();

        protected abstract boolean update(boolean z, boolean z2, Bundle bundle);

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor putString(String str, String str2) {
            synchronized (this.mEditorLock) {
                this.mModified.putString(str, str2);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            synchronized (this.mEditorLock) {
                this.mModified.putStringArray(str, CollectionUtils.d(set) ? null : (String[]) set.toArray(new String[set.size()]));
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor putInt(String str, int i) {
            synchronized (this.mEditorLock) {
                this.mModified.putInt(str, i);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor putLong(String str, long j) {
            synchronized (this.mEditorLock) {
                this.mModified.putLong(str, j);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor putFloat(String str, float f) {
            synchronized (this.mEditorLock) {
                this.mModified.putFloat(str, f);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor putBoolean(String str, boolean z) {
            synchronized (this.mEditorLock) {
                this.mModified.putBoolean(str, z);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor remove(String str) {
            synchronized (this.mEditorLock) {
                this.mModified.putString(str, null);
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final SharedPreferences.Editor clear() {
            synchronized (this.mEditorLock) {
                this.mIsClear = true;
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public final boolean commit() {
            boolean updateValues;
            synchronized (this.mEditorLock) {
                updateValues = updateValues(true);
            }
            return updateValues;
        }

        @Override // android.content.SharedPreferences.Editor
        public final void apply() {
            synchronized (this.mEditorLock) {
                updateValues(false);
            }
        }

        private boolean updateValues(boolean z) {
            try {
                return update(z, this.mIsClear, this.mModified);
            } finally {
                this.mIsClear = false;
                this.mModified.clear();
            }
        }
    }
}
