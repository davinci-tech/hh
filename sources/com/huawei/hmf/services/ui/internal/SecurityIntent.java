package com.huawei.hmf.services.ui.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public final class SecurityIntent {
    private final Intent mIntent;

    public static SecurityIntent from(Intent intent) {
        return new SecurityIntent(intent);
    }

    private SecurityIntent(Intent intent) {
        this.mIntent = intent;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public Bundle getBundleExtra(String str) {
        try {
            return this.mIntent.getBundleExtra(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean getBooleanExtra(String str, boolean z) {
        try {
            return this.mIntent.getBooleanExtra(str, z);
        } catch (Throwable unused) {
            return false;
        }
    }

    public String getStringExtra(String str) {
        try {
            return this.mIntent.getStringExtra(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public int getIntExtra(String str, int i) {
        try {
            return this.mIntent.getIntExtra(str, i);
        } catch (Throwable unused) {
            return i;
        }
    }

    public long getLongExtra(String str, long j) {
        try {
            return this.mIntent.getLongExtra(str, j);
        } catch (Throwable unused) {
            return j;
        }
    }

    public CharSequence getCharSequenceExtra(String str) {
        try {
            return this.mIntent.getCharSequenceExtra(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public float getFloatExtra(String str, float f) {
        try {
            return this.mIntent.getFloatExtra(str, f);
        } catch (Throwable unused) {
            return f;
        }
    }

    public Serializable getSerializableExtra(String str) {
        try {
            return this.mIntent.getSerializableExtra(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public Parcelable[] getParcelableArrayExtra(String str) {
        try {
            return this.mIntent.getParcelableArrayExtra(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String str) {
        try {
            return this.mIntent.getParcelableArrayListExtra(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public <T extends Parcelable> T getParcelableExtra(String str) {
        try {
            return (T) this.mIntent.getParcelableExtra(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public int getFlags() {
        return this.mIntent.getFlags();
    }

    public Bundle getExtras() {
        try {
            return this.mIntent.getExtras();
        } catch (Throwable unused) {
            return new Bundle();
        }
    }

    public void removeExtra(String str) {
        try {
            this.mIntent.removeExtra(str);
        } catch (Throwable unused) {
        }
    }

    public String getAction() {
        return this.mIntent.getAction();
    }
}
