package com.huawei.secure.android.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes6.dex */
public class SafeIntent extends Intent {
    @Deprecated
    public SafeIntent(Intent intent) {
        super(intent == null ? new Intent() : intent);
    }

    @Override // android.content.Intent
    public String getAction() {
        try {
            return super.getAction();
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public boolean[] getBooleanArrayExtra(String str) {
        try {
            return super.getBooleanArrayExtra(str);
        } catch (Exception unused) {
            return new boolean[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public boolean getBooleanExtra(String str, boolean z) {
        try {
            return super.getBooleanExtra(str, z);
        } catch (Exception unused) {
            return z;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public Bundle getBundleExtra(String str) {
        try {
            return super.getBundleExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public byte[] getByteArrayExtra(String str) {
        try {
            return super.getByteArrayExtra(str);
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public byte getByteExtra(String str, byte b) {
        try {
            return super.getByteExtra(str, b);
        } catch (Exception unused) {
            return b;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public char[] getCharArrayExtra(String str) {
        try {
            return super.getCharArrayExtra(str);
        } catch (Exception unused) {
            return new char[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public char getCharExtra(String str, char c) {
        try {
            return super.getCharExtra(str, c);
        } catch (Exception unused) {
            return c;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public CharSequence[] getCharSequenceArrayExtra(String str) {
        try {
            return super.getCharSequenceArrayExtra(str);
        } catch (Exception unused) {
            return new CharSequence[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public ArrayList<CharSequence> getCharSequenceArrayListExtra(String str) {
        try {
            return super.getCharSequenceArrayListExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public CharSequence getCharSequenceExtra(String str) {
        try {
            return super.getCharSequenceExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public Uri getData() {
        try {
            return super.getData();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public double[] getDoubleArrayExtra(String str) {
        try {
            return super.getDoubleArrayExtra(str);
        } catch (Exception unused) {
            return new double[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public double getDoubleExtra(String str, double d) {
        try {
            return super.getDoubleExtra(str, d);
        } catch (Exception unused) {
            return d;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public Bundle getExtras() {
        try {
            return super.getExtras();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public float[] getFloatArrayExtra(String str) {
        try {
            return super.getFloatArrayExtra(str);
        } catch (Exception unused) {
            return new float[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public float getFloatExtra(String str, float f) {
        try {
            return super.getFloatExtra(str, f);
        } catch (Exception unused) {
            return f;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public int[] getIntArrayExtra(String str) {
        try {
            return super.getIntArrayExtra(str);
        } catch (Exception unused) {
            return new int[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public int getIntExtra(String str, int i) {
        try {
            return super.getIntExtra(str, i);
        } catch (Exception unused) {
            return i;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public ArrayList<Integer> getIntegerArrayListExtra(String str) {
        try {
            return super.getIntegerArrayListExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public long[] getLongArrayExtra(String str) {
        try {
            return super.getLongArrayExtra(str);
        } catch (Exception unused) {
            return new long[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public long getLongExtra(String str, long j) {
        try {
            return super.getLongExtra(str, j);
        } catch (Exception unused) {
            return j;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public Parcelable[] getParcelableArrayExtra(String str) {
        try {
            return super.getParcelableArrayExtra(str);
        } catch (Exception unused) {
            return new Parcelable[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String str) {
        try {
            return super.getParcelableArrayListExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public <T extends Parcelable> T getParcelableExtra(String str) {
        try {
            return (T) super.getParcelableExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public String getScheme() {
        try {
            return super.getScheme();
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public Serializable getSerializableExtra(String str) {
        try {
            return super.getSerializableExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public short[] getShortArrayExtra(String str) {
        try {
            return super.getShortArrayExtra(str);
        } catch (Exception unused) {
            return new short[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public short getShortExtra(String str, short s) {
        try {
            return super.getShortExtra(str, s);
        } catch (Exception unused) {
            return s;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public String[] getStringArrayExtra(String str) {
        try {
            return super.getStringArrayExtra(str);
        } catch (Exception unused) {
            return new String[0];
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public ArrayList<String> getStringArrayListExtra(String str) {
        try {
            return super.getStringArrayListExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public String getStringExtra(String str) {
        try {
            return super.getStringExtra(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.content.Intent
    @Deprecated
    public boolean hasExtra(String str) {
        try {
            return super.hasExtra(str);
        } catch (Exception unused) {
            return false;
        }
    }
}
