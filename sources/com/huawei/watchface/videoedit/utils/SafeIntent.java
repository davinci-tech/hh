package com.huawei.watchface.videoedit.utils;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import com.huawei.watchface.utils.HwLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes9.dex */
public class SafeIntent {
    private static final String TAG = "SafeIntent";
    private Intent mIntent;

    public SafeIntent(Intent intent) {
        if (intent != null) {
            this.mIntent = intent;
        } else {
            HwLog.e(TAG, "SafeIntent have some exceptions");
            this.mIntent = new Intent();
        }
    }

    public Intent getIntent(String str) {
        try {
            Intent.getIntent(str);
        } catch (Exception e) {
            HwLog.e(TAG, "getIntent have some exceptions" + HwLog.printException(e));
            this.mIntent = null;
        }
        return this.mIntent;
    }

    public Intent getIntentOld(String str) {
        try {
            return Intent.getIntentOld(str);
        } catch (Exception e) {
            HwLog.e(TAG, "getIntentOld have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public String getAction() {
        try {
            return this.mIntent.getAction();
        } catch (Exception e) {
            HwLog.e(TAG, "getAction have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public Uri getData() {
        try {
            return this.mIntent.getData();
        } catch (Exception e) {
            HwLog.e(TAG, "getData have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public String getDataString() {
        try {
            return this.mIntent.getDataString();
        } catch (Exception e) {
            HwLog.e(TAG, "getDataString have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public String getScheme() {
        try {
            return this.mIntent.getScheme();
        } catch (Exception e) {
            HwLog.e(TAG, "getScheme have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public String getType() {
        try {
            return this.mIntent.getType();
        } catch (Exception e) {
            HwLog.e(TAG, "getType have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public String resolveType(Context context) {
        try {
            return this.mIntent.resolveType(context);
        } catch (Exception e) {
            HwLog.e(TAG, "resolveType have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public String resolveType(ContentResolver contentResolver) {
        try {
            return this.mIntent.resolveType(contentResolver);
        } catch (Exception e) {
            HwLog.e(TAG, "resolveType have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public boolean hasCategory(String str) {
        try {
            return this.mIntent.hasCategory(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "hasCategory have some exceptions");
            return false;
        }
    }

    public String resolveTypeIfNeeded(ContentResolver contentResolver) {
        try {
            return this.mIntent.resolveTypeIfNeeded(contentResolver);
        } catch (Exception e) {
            HwLog.e(TAG, "resolveTypeIfNeeded have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public Set<String> getCategories() {
        try {
            return this.mIntent.getCategories();
        } catch (Exception e) {
            HwLog.e(TAG, "getCategories have some exceptions" + HwLog.printException(e));
            return new HashSet();
        }
    }

    public Intent getSelector() {
        try {
            return this.mIntent.getSelector();
        } catch (Exception e) {
            HwLog.e(TAG, "getSelector have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public ClipData getClipData() {
        try {
            return this.mIntent.getClipData();
        } catch (Exception e) {
            HwLog.e(TAG, "getClipData have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public boolean hasExtra(String str) {
        try {
            return this.mIntent.hasExtra(str);
        } catch (Exception e) {
            HwLog.e(TAG, "hasExtra have some exceptions" + HwLog.printException(e));
            return false;
        }
    }

    public boolean hasFileDescriptors() {
        try {
            return this.mIntent.hasFileDescriptors();
        } catch (Exception e) {
            HwLog.e(TAG, "hasFileDescriptors have some exceptions" + HwLog.printException(e));
            return false;
        }
    }

    public boolean getBooleanExtra(String str, boolean z) {
        try {
            return this.mIntent.getBooleanExtra(str, z);
        } catch (Exception e) {
            HwLog.e(TAG, "getBooleanExtra have some exceptions" + HwLog.printException(e));
            return z;
        }
    }

    public byte getByteExtra(String str, byte b) {
        try {
            return this.mIntent.getByteExtra(str, b);
        } catch (Exception e) {
            HwLog.e(TAG, "getByteExtra have some exceptions" + HwLog.printException(e));
            return b;
        }
    }

    public short getShortExtra(String str, short s) {
        try {
            return this.mIntent.getShortExtra(str, s);
        } catch (Exception e) {
            HwLog.e(TAG, "getShortExtra have some exceptions" + HwLog.printException(e));
            return s;
        }
    }

    public char getCharExtra(String str, char c) {
        try {
            return this.mIntent.getCharExtra(str, c);
        } catch (Exception e) {
            HwLog.e(TAG, "getCharExtra have some exceptions" + HwLog.printException(e));
            return c;
        }
    }

    public int getIntExtra(String str, int i) {
        try {
            return this.mIntent.getIntExtra(str, i);
        } catch (Exception e) {
            HwLog.e(TAG, "getIntExtra have some exceptions" + HwLog.printException(e));
            return i;
        }
    }

    public long getLongExtra(String str, long j) {
        try {
            return this.mIntent.getLongExtra(str, j);
        } catch (Exception e) {
            HwLog.e(TAG, "getLongExtra have some exceptions" + HwLog.printException(e));
            return j;
        }
    }

    public float getFloatExtra(String str, float f) {
        try {
            return this.mIntent.getFloatExtra(str, f);
        } catch (Exception e) {
            HwLog.e(TAG, "getFloatExtra have some exceptions" + HwLog.printException(e));
            return f;
        }
    }

    public double getDoubleExtra(String str, double d) {
        try {
            return this.mIntent.getDoubleExtra(str, d);
        } catch (Exception e) {
            HwLog.e(TAG, "getDoubleExtra have some exceptions" + HwLog.printException(e));
            return d;
        }
    }

    public String getStringExtra(String str) {
        try {
            return this.mIntent.getStringExtra(str);
        } catch (Exception e) {
            HwLog.e(TAG, "getStringExtra have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public CharSequence getCharSequenceExtra(String str) {
        try {
            return this.mIntent.getCharSequenceExtra(str);
        } catch (Exception e) {
            HwLog.e(TAG, "getCharSequenceExtra have some exceptions" + HwLog.printException(e));
            return null;
        }
    }

    public <T extends Parcelable> T getParcelableExtra(String str) {
        try {
            return (T) this.mIntent.getParcelableExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getParcelableExtra have some exceptions");
            return null;
        }
    }

    public Parcelable[] getParcelableArrayExtra(String str) {
        try {
            return this.mIntent.getParcelableArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getParcelableArrayExtra have some exceptions");
            return new Parcelable[0];
        }
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String str) {
        try {
            return this.mIntent.getParcelableArrayListExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getParcelableArrayListExtra have some exceptions");
            return new ArrayList<>();
        }
    }

    public Serializable getSerializableExtra(String str) {
        try {
            return this.mIntent.getSerializableExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getSerializableExtra have some exceptions");
            return null;
        }
    }

    public ArrayList<Integer> getIntegerArrayListExtra(String str) {
        try {
            return this.mIntent.getIntegerArrayListExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getIntegerArrayListExtra have some exceptions");
            return new ArrayList<>();
        }
    }

    public ArrayList<String> getStringArrayListExtra(String str) {
        try {
            return this.mIntent.getStringArrayListExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getStringArrayListExtra have some exceptions");
            return new ArrayList<>();
        }
    }

    public ArrayList<CharSequence> getCharSequenceArrayListExtra(String str) {
        try {
            return this.mIntent.getCharSequenceArrayListExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getCharSequenceArrayListExtra have some exceptions");
            return new ArrayList<>();
        }
    }

    public boolean[] getBooleanArrayExtra(String str) {
        try {
            return this.mIntent.getBooleanArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getBooleanArrayExtra have some exceptions");
            return new boolean[0];
        }
    }

    public byte[] getByteArrayExtra(String str) {
        try {
            return this.mIntent.getByteArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getByteArrayExtra have some exceptions");
            return new byte[0];
        }
    }

    public short[] getShortArrayExtra(String str) {
        try {
            return this.mIntent.getShortArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getShortArrayExtra have some exceptions");
            return new short[0];
        }
    }

    public char[] getCharArrayExtra(String str) {
        try {
            return this.mIntent.getCharArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getCharArrayExtra have some exceptions");
            return new char[0];
        }
    }

    public int[] getIntArrayExtra(String str) {
        try {
            return this.mIntent.getIntArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getIntArrayExtra have some exceptions");
            return new int[0];
        }
    }

    public long[] getLongArrayExtra(String str) {
        try {
            return this.mIntent.getLongArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getLongArrayExtra have some exceptions");
            return new long[0];
        }
    }

    public float[] getFloatArrayExtra(String str) {
        try {
            return this.mIntent.getFloatArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getFloatArrayExtra have some exceptions");
            return new float[0];
        }
    }

    public double[] getDoubleArrayExtra(String str) {
        try {
            return this.mIntent.getDoubleArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getDoubleArrayExtra have some exceptions");
            return new double[0];
        }
    }

    public String[] getStringArrayExtra(String str) {
        try {
            return this.mIntent.getStringArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getStringArrayExtra have some exceptions");
            return new String[0];
        }
    }

    public CharSequence[] getCharSequenceArrayExtra(String str) {
        try {
            return this.mIntent.getCharSequenceArrayExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getCharSequenceArrayExtra have some exceptions");
            return new CharSequence[0];
        }
    }

    public Bundle getBundleExtra(String str) {
        try {
            return this.mIntent.getBundleExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "getBundleExtra have some exceptions");
            return null;
        }
    }

    public Bundle getExtras() {
        try {
            return this.mIntent.getExtras();
        } catch (Exception unused) {
            HwLog.e(TAG, "getExtras have some exceptions");
            return null;
        }
    }

    public int getFlags() {
        try {
            return this.mIntent.getFlags();
        } catch (Exception unused) {
            HwLog.e(TAG, "getFlags have some exceptions");
            return 0;
        }
    }

    public String getPackage() {
        try {
            return this.mIntent.getPackage();
        } catch (Exception unused) {
            HwLog.e(TAG, "getPackage have some exceptions");
            return null;
        }
    }

    public ComponentName getComponent() {
        try {
            return this.mIntent.getComponent();
        } catch (Exception unused) {
            HwLog.e(TAG, "getComponent have some exceptions");
            return null;
        }
    }

    public Rect getSourceBounds() {
        try {
            return this.mIntent.getSourceBounds();
        } catch (Exception unused) {
            HwLog.e(TAG, "getSourceBounds have some exceptions");
            return null;
        }
    }

    public Intent putExtra(String str, String str2) {
        try {
            return this.mIntent.putExtra(str, str2);
        } catch (Exception unused) {
            HwLog.e(TAG, "putExtra have some exceptions");
            return this.mIntent;
        }
    }

    public Intent putExtra(String str, boolean z) {
        try {
            return this.mIntent.putExtra(str, z);
        } catch (Exception unused) {
            HwLog.e(TAG, "putExtra have some exceptions");
            return this.mIntent;
        }
    }

    public Intent putExtra(String str, int i) {
        try {
            return this.mIntent.putExtra(str, i);
        } catch (Exception unused) {
            HwLog.e(TAG, "putExtra have some exceptions");
            return this.mIntent;
        }
    }

    public void removeExtra(String str) {
        try {
            this.mIntent.removeExtra(str);
        } catch (Exception unused) {
            HwLog.e(TAG, "removeExtra have some exceptions");
        }
    }
}
