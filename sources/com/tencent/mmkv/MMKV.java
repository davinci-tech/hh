package com.tencent.mmkv;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.util.Log;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class MMKV implements SharedPreferences, SharedPreferences.Editor {
    private static final int ASHMEM_MODE = 8;
    private static final int BACKUP_MODE = 16;
    private static final int CONTEXT_MODE_MULTI_PROCESS = 4;
    public static final int MULTI_PROCESS_MODE = 2;
    public static final int SINGLE_PROCESS_MODE = 1;
    private static final Set<Long> checkedHandleSet;
    private static MMKVHandler gCallbackHandler;
    private static MMKVContentChangeNotification gContentChangeNotify;
    private static boolean gWantLogReDirecting;
    private static final MMKVLogLevel[] index2LogLevel;
    private static boolean isProcessModeCheckerEnabled;
    private static final EnumMap<MMKVLogLevel, Integer> logLevel2Index;
    private static final HashMap<String, Parcelable.Creator<?>> mCreators;
    private static final EnumMap<MMKVRecoverStrategic, Integer> recoverIndex;
    private static String rootDir;
    private final long nativeHandle;

    /* loaded from: classes2.dex */
    public interface LibLoader {
        void loadLibrary(String str);
    }

    private native long actualSize(long j);

    public static native long backupAllToDirectory(String str);

    public static native boolean backupOneToDirectory(String str, String str2, String str3);

    private static native boolean checkProcessMode(long j);

    private native boolean containsKey(long j, String str);

    private native long count(long j);

    private static native long createNB(int i);

    private native boolean decodeBool(long j, String str, boolean z);

    private native byte[] decodeBytes(long j, String str);

    private native double decodeDouble(long j, String str, double d);

    private native float decodeFloat(long j, String str, float f);

    private native int decodeInt(long j, String str, int i);

    private native long decodeLong(long j, String str, long j2);

    private native String decodeString(long j, String str, String str2);

    private native String[] decodeStringSet(long j, String str);

    private static native void destroyNB(long j, int i);

    private native boolean encodeBool(long j, String str, boolean z);

    private native boolean encodeBytes(long j, String str, byte[] bArr);

    private native boolean encodeDouble(long j, String str, double d);

    private native boolean encodeFloat(long j, String str, float f);

    private native boolean encodeInt(long j, String str, int i);

    private native boolean encodeLong(long j, String str, long j2);

    private native boolean encodeSet(long j, String str, String[] strArr);

    private native boolean encodeString(long j, String str, String str2);

    private static native long getDefaultMMKV(int i, String str);

    private static native long getMMKVWithAshmemFD(String str, int i, int i2, String str2);

    private static native long getMMKVWithID(String str, int i, String str2, String str3);

    private static native long getMMKVWithIDAndSize(String str, int i, int i2, String str2);

    public static native boolean isFileValid(String str, String str2);

    private static native void jniInitialize(String str, String str2, int i);

    public static native void onExit();

    public static native int pageSize();

    private native void removeValueForKey(long j, String str);

    public static native long restoreAllFromDirectory(String str);

    public static native boolean restoreOneMMKVFromDirectory(String str, String str2, String str3);

    private static native void setCallbackHandler(boolean z, boolean z2);

    private static native void setLogLevel(int i);

    private static native void setWantsContentChangeNotify(boolean z);

    private native void sync(boolean z);

    private native long totalSize(long j);

    private native int valueSize(long j, String str, boolean z);

    public static native String version();

    private native int writeValueToNB(long j, String str, long j2, int i);

    public native String[] allKeys();

    public native int ashmemFD();

    public native int ashmemMetaFD();

    public native void checkContentChangedByOuterProcess();

    public native void checkReSetCryptKey(String str);

    public native void clearAll();

    public native void clearMemoryCache();

    public native void close();

    public native String cryptKey();

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return this;
    }

    public native void lock();

    public native String mmapID();

    public native boolean reKey(String str);

    public native void removeValuesForKeys(String[] strArr);

    public native void trim();

    public native boolean tryLock();

    public native void unlock();

    static {
        EnumMap<MMKVRecoverStrategic, Integer> enumMap = new EnumMap<>((Class<MMKVRecoverStrategic>) MMKVRecoverStrategic.class);
        recoverIndex = enumMap;
        enumMap.put((EnumMap<MMKVRecoverStrategic, Integer>) MMKVRecoverStrategic.OnErrorDiscard, (MMKVRecoverStrategic) 0);
        enumMap.put((EnumMap<MMKVRecoverStrategic, Integer>) MMKVRecoverStrategic.OnErrorRecover, (MMKVRecoverStrategic) 1);
        EnumMap<MMKVLogLevel, Integer> enumMap2 = new EnumMap<>((Class<MMKVLogLevel>) MMKVLogLevel.class);
        logLevel2Index = enumMap2;
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelDebug, (MMKVLogLevel) 0);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelInfo, (MMKVLogLevel) 1);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelWarning, (MMKVLogLevel) 2);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelError, (MMKVLogLevel) 3);
        enumMap2.put((EnumMap<MMKVLogLevel, Integer>) MMKVLogLevel.LevelNone, (MMKVLogLevel) 4);
        index2LogLevel = new MMKVLogLevel[]{MMKVLogLevel.LevelDebug, MMKVLogLevel.LevelInfo, MMKVLogLevel.LevelWarning, MMKVLogLevel.LevelError, MMKVLogLevel.LevelNone};
        checkedHandleSet = new HashSet();
        rootDir = null;
        isProcessModeCheckerEnabled = true;
        mCreators = new HashMap<>();
        gWantLogReDirecting = false;
    }

    public static String initialize(Context context) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", null, MMKVLogLevel.LevelInfo);
    }

    public static String initialize(Context context, MMKVLogLevel mMKVLogLevel) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", null, mMKVLogLevel);
    }

    public static String initialize(Context context, LibLoader libLoader) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", libLoader, MMKVLogLevel.LevelInfo);
    }

    public static String initialize(Context context, LibLoader libLoader, MMKVLogLevel mMKVLogLevel) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", libLoader, mMKVLogLevel);
    }

    public static String initialize(Context context, String str) {
        return initialize(context, str, null, MMKVLogLevel.LevelInfo);
    }

    public static String initialize(Context context, String str, MMKVLogLevel mMKVLogLevel) {
        return initialize(context, str, null, mMKVLogLevel);
    }

    public static String initialize(Context context, String str, LibLoader libLoader) {
        return initialize(context, str, libLoader, MMKVLogLevel.LevelInfo);
    }

    public static String initialize(Context context, String str, LibLoader libLoader, MMKVLogLevel mMKVLogLevel) {
        if ((context.getApplicationInfo().flags & 2) == 0) {
            disableProcessModeChecker();
        } else {
            enableProcessModeChecker();
        }
        return doInitialize(str, context.getCacheDir().getAbsolutePath(), libLoader, mMKVLogLevel);
    }

    private static String doInitialize(String str, String str2, LibLoader libLoader, MMKVLogLevel mMKVLogLevel) {
        if (libLoader != null) {
            libLoader.loadLibrary("mmkv");
        } else {
            System.loadLibrary("mmkv");
        }
        jniInitialize(str, str2, logLevel2Int(mMKVLogLevel));
        rootDir = str;
        return str;
    }

    @Deprecated
    public static String initialize(String str) {
        return doInitialize(str, str + "/.tmp", null, MMKVLogLevel.LevelInfo);
    }

    @Deprecated
    public static String initialize(String str, MMKVLogLevel mMKVLogLevel) {
        return doInitialize(str, str + "/.tmp", null, mMKVLogLevel);
    }

    @Deprecated
    public static String initialize(String str, LibLoader libLoader) {
        return doInitialize(str, str + "/.tmp", libLoader, MMKVLogLevel.LevelInfo);
    }

    @Deprecated
    public static String initialize(String str, LibLoader libLoader, MMKVLogLevel mMKVLogLevel) {
        return doInitialize(str, str + "/.tmp", libLoader, mMKVLogLevel);
    }

    public static String getRootDir() {
        return rootDir;
    }

    /* renamed from: com.tencent.mmkv.MMKV$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tencent$mmkv$MMKVLogLevel;

        static {
            int[] iArr = new int[MMKVLogLevel.values().length];
            $SwitchMap$com$tencent$mmkv$MMKVLogLevel = iArr;
            try {
                iArr[MMKVLogLevel.LevelDebug.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelWarning.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelError.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelNone.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel[MMKVLogLevel.LevelInfo.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static int logLevel2Int(MMKVLogLevel mMKVLogLevel) {
        int i = AnonymousClass1.$SwitchMap$com$tencent$mmkv$MMKVLogLevel[mMKVLogLevel.ordinal()];
        if (i == 1) {
            return 0;
        }
        int i2 = 2;
        if (i != 2) {
            i2 = 3;
            if (i != 3) {
                i2 = 4;
                if (i != 4) {
                    return 1;
                }
            }
        }
        return i2;
    }

    public static void setLogLevel(MMKVLogLevel mMKVLogLevel) {
        setLogLevel(logLevel2Int(mMKVLogLevel));
    }

    public static MMKV mmkvWithID(String str) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(str, 1, null, null), str, 1);
    }

    public static MMKV mmkvWithID(String str, int i) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(str, i, null, null), str, i);
    }

    public static MMKV mmkvWithID(String str, int i, String str2) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(str, i, str2, null), str, i);
    }

    public static MMKV mmkvWithID(String str, String str2) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(str, 1, null, str2), str, 1);
    }

    public static MMKV mmkvWithID(String str, int i, String str2, String str3) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getMMKVWithID(str, i, str2, str3), str, i);
    }

    public static MMKV backedUpMMKVWithID(String str, int i, String str2, String str3) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        int i2 = i | 16;
        return checkProcessMode(getMMKVWithID(str, i2, str2, str3), str, i2);
    }

    public static MMKV mmkvWithAshmemID(Context context, String str, int i, int i2, String str2) throws RuntimeException {
        MMKV mmkv;
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        String processNameByPID = MMKVContentProvider.getProcessNameByPID(context, Process.myPid());
        if (processNameByPID == null || processNameByPID.length() == 0) {
            simpleLog(MMKVLogLevel.LevelError, "process name detect fail, try again later");
            throw new IllegalStateException("process name detect fail, try again later");
        }
        if (processNameByPID.contains(":")) {
            Uri contentUri = MMKVContentProvider.contentUri(context);
            if (contentUri == null) {
                simpleLog(MMKVLogLevel.LevelError, "MMKVContentProvider has invalid authority");
                throw new IllegalStateException("MMKVContentProvider has invalid authority");
            }
            simpleLog(MMKVLogLevel.LevelInfo, "getting parcelable mmkv in process, Uri = " + contentUri);
            Bundle bundle = new Bundle();
            bundle.putInt("KEY_SIZE", i);
            bundle.putInt("KEY_MODE", i2);
            if (str2 != null) {
                bundle.putString("KEY_CRYPT", str2);
            }
            Bundle call = context.getContentResolver().call(contentUri, "mmkvFromAshmemID", str, bundle);
            if (call != null) {
                call.setClassLoader(ParcelableMMKV.class.getClassLoader());
                ParcelableMMKV parcelableMMKV = (ParcelableMMKV) call.getParcelable("KEY");
                if (parcelableMMKV != null && (mmkv = parcelableMMKV.toMMKV()) != null) {
                    simpleLog(MMKVLogLevel.LevelInfo, mmkv.mmapID() + " fd = " + mmkv.ashmemFD() + ", meta fd = " + mmkv.ashmemMetaFD());
                    return mmkv;
                }
            }
        }
        simpleLog(MMKVLogLevel.LevelInfo, "getting mmkv in main process");
        long mMKVWithIDAndSize = getMMKVWithIDAndSize(str, i, i2 | 8, str2);
        if (mMKVWithIDAndSize != 0) {
            return new MMKV(mMKVWithIDAndSize);
        }
        throw new IllegalStateException("Fail to create an Ashmem MMKV instance [" + str + "]");
    }

    public static MMKV defaultMMKV() throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getDefaultMMKV(1, null), "DefaultMMKV", 1);
    }

    public static MMKV defaultMMKV(int i, String str) throws RuntimeException {
        if (rootDir == null) {
            throw new IllegalStateException("You should Call MMKV.initialize() first.");
        }
        return checkProcessMode(getDefaultMMKV(i, str), "DefaultMMKV", i);
    }

    private static MMKV checkProcessMode(long j, String str, int i) throws RuntimeException {
        String str2;
        if (j == 0) {
            throw new RuntimeException("Fail to create an MMKV instance [" + str + "] in JNI");
        }
        if (!isProcessModeCheckerEnabled) {
            return new MMKV(j);
        }
        Set<Long> set = checkedHandleSet;
        synchronized (set) {
            if (!set.contains(Long.valueOf(j))) {
                if (!checkProcessMode(j)) {
                    if (i == 1) {
                        str2 = "Opening a multi-process MMKV instance [" + str + "] with SINGLE_PROCESS_MODE!";
                    } else {
                        str2 = ("Opening an MMKV instance [" + str + "] with MULTI_PROCESS_MODE, ") + "while it's already been opened with SINGLE_PROCESS_MODE by someone somewhere else!";
                    }
                    throw new IllegalArgumentException(str2);
                }
                set.add(Long.valueOf(j));
            }
        }
        return new MMKV(j);
    }

    public static void enableProcessModeChecker() {
        synchronized (checkedHandleSet) {
            isProcessModeCheckerEnabled = true;
        }
        Log.i("MMKV", "Enable checkProcessMode()");
    }

    public static void disableProcessModeChecker() {
        synchronized (checkedHandleSet) {
            isProcessModeCheckerEnabled = false;
        }
        Log.i("MMKV", "Disable checkProcessMode()");
    }

    public boolean encode(String str, boolean z) {
        return encodeBool(this.nativeHandle, str, z);
    }

    public boolean decodeBool(String str) {
        return decodeBool(this.nativeHandle, str, false);
    }

    public boolean decodeBool(String str, boolean z) {
        return decodeBool(this.nativeHandle, str, z);
    }

    public boolean encode(String str, int i) {
        return encodeInt(this.nativeHandle, str, i);
    }

    public int decodeInt(String str) {
        return decodeInt(this.nativeHandle, str, 0);
    }

    public int decodeInt(String str, int i) {
        return decodeInt(this.nativeHandle, str, i);
    }

    public boolean encode(String str, long j) {
        return encodeLong(this.nativeHandle, str, j);
    }

    public long decodeLong(String str) {
        return decodeLong(this.nativeHandle, str, 0L);
    }

    public long decodeLong(String str, long j) {
        return decodeLong(this.nativeHandle, str, j);
    }

    public boolean encode(String str, float f) {
        return encodeFloat(this.nativeHandle, str, f);
    }

    public float decodeFloat(String str) {
        return decodeFloat(this.nativeHandle, str, 0.0f);
    }

    public float decodeFloat(String str, float f) {
        return decodeFloat(this.nativeHandle, str, f);
    }

    public boolean encode(String str, double d) {
        return encodeDouble(this.nativeHandle, str, d);
    }

    public double decodeDouble(String str) {
        return decodeDouble(this.nativeHandle, str, 0.0d);
    }

    public double decodeDouble(String str, double d) {
        return decodeDouble(this.nativeHandle, str, d);
    }

    public boolean encode(String str, String str2) {
        return encodeString(this.nativeHandle, str, str2);
    }

    public String decodeString(String str) {
        return decodeString(this.nativeHandle, str, null);
    }

    public String decodeString(String str, String str2) {
        return decodeString(this.nativeHandle, str, str2);
    }

    public boolean encode(String str, Set<String> set) {
        return encodeSet(this.nativeHandle, str, set == null ? null : (String[]) set.toArray(new String[0]));
    }

    public Set<String> decodeStringSet(String str) {
        return decodeStringSet(str, (Set<String>) null);
    }

    public Set<String> decodeStringSet(String str, Set<String> set) {
        return decodeStringSet(str, set, HashSet.class);
    }

    public Set<String> decodeStringSet(String str, Set<String> set, Class<? extends Set> cls) {
        String[] decodeStringSet = decodeStringSet(this.nativeHandle, str);
        if (decodeStringSet == null) {
            return set;
        }
        try {
            Set<String> newInstance = cls.newInstance();
            newInstance.addAll(Arrays.asList(decodeStringSet));
            return newInstance;
        } catch (IllegalAccessException | InstantiationException unused) {
            return set;
        }
    }

    public boolean encode(String str, byte[] bArr) {
        return encodeBytes(this.nativeHandle, str, bArr);
    }

    public byte[] decodeBytes(String str) {
        return decodeBytes(str, (byte[]) null);
    }

    public byte[] decodeBytes(String str, byte[] bArr) {
        byte[] decodeBytes = decodeBytes(this.nativeHandle, str);
        return decodeBytes != null ? decodeBytes : bArr;
    }

    public boolean encode(String str, Parcelable parcelable) {
        if (parcelable == null) {
            return encodeBytes(this.nativeHandle, str, null);
        }
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return encodeBytes(this.nativeHandle, str, marshall);
    }

    public <T extends Parcelable> T decodeParcelable(String str, Class<T> cls) {
        return (T) decodeParcelable(str, cls, null);
    }

    public <T extends Parcelable> T decodeParcelable(String str, Class<T> cls, T t) {
        byte[] decodeBytes;
        Parcelable.Creator<?> creator;
        if (cls == null || (decodeBytes = decodeBytes(this.nativeHandle, str)) == null) {
            return t;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(decodeBytes, 0, decodeBytes.length);
        obtain.setDataPosition(0);
        try {
            String cls2 = cls.toString();
            HashMap<String, Parcelable.Creator<?>> hashMap = mCreators;
            synchronized (hashMap) {
                creator = hashMap.get(cls2);
                if (creator == null && (creator = (Parcelable.Creator) cls.getField("CREATOR").get(null)) != null) {
                    hashMap.put(cls2, creator);
                }
            }
            if (creator != null) {
                return (T) creator.createFromParcel(obtain);
            }
            throw new Exception("Parcelable protocol requires a non-null static Parcelable.Creator object called CREATOR on class " + cls2);
        } catch (Exception e) {
            simpleLog(MMKVLogLevel.LevelError, e.toString());
            return t;
        } finally {
            obtain.recycle();
        }
    }

    public int getValueSize(String str) {
        return valueSize(this.nativeHandle, str, false);
    }

    public int getValueActualSize(String str) {
        return valueSize(this.nativeHandle, str, true);
    }

    public boolean containsKey(String str) {
        return containsKey(this.nativeHandle, str);
    }

    public long count() {
        return count(this.nativeHandle);
    }

    public long totalSize() {
        return totalSize(this.nativeHandle);
    }

    public long actualSize() {
        return actualSize(this.nativeHandle);
    }

    public void removeValueForKey(String str) {
        removeValueForKey(this.nativeHandle, str);
    }

    public void sync() {
        sync(true);
    }

    public void async() {
        sync(false);
    }

    public static boolean isFileValid(String str) {
        return isFileValid(str, null);
    }

    public int importFromSharedPreferences(SharedPreferences sharedPreferences) {
        Map<String, ?> all = sharedPreferences.getAll();
        if (all == null || all.size() <= 0) {
            return 0;
        }
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                if (value instanceof Boolean) {
                    encodeBool(this.nativeHandle, key, ((Boolean) value).booleanValue());
                } else if (value instanceof Integer) {
                    encodeInt(this.nativeHandle, key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    encodeLong(this.nativeHandle, key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    encodeFloat(this.nativeHandle, key, ((Float) value).floatValue());
                } else if (value instanceof Double) {
                    encodeDouble(this.nativeHandle, key, ((Double) value).doubleValue());
                } else if (value instanceof String) {
                    encodeString(this.nativeHandle, key, (String) value);
                } else if (value instanceof Set) {
                    encode(key, (Set<String>) value);
                } else {
                    simpleLog(MMKVLogLevel.LevelError, "unknown type: " + value.getClass());
                }
            }
        }
        return all.size();
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException("Intentionally Not Supported. Use allKeys() instead, getAll() not implement because type-erasure inside mmkv");
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        return decodeString(this.nativeHandle, str, str2);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putString(String str, String str2) {
        encodeString(this.nativeHandle, str, str2);
        return this;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        return decodeStringSet(str, set);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
        encode(str, set);
        return this;
    }

    public SharedPreferences.Editor putBytes(String str, byte[] bArr) {
        encode(str, bArr);
        return this;
    }

    public byte[] getBytes(String str, byte[] bArr) {
        return decodeBytes(str, bArr);
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        return decodeInt(this.nativeHandle, str, i);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putInt(String str, int i) {
        encodeInt(this.nativeHandle, str, i);
        return this;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        return decodeLong(this.nativeHandle, str, j);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putLong(String str, long j) {
        encodeLong(this.nativeHandle, str, j);
        return this;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        return decodeFloat(this.nativeHandle, str, f);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putFloat(String str, float f) {
        encodeFloat(this.nativeHandle, str, f);
        return this;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        return decodeBool(this.nativeHandle, str, z);
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor putBoolean(String str, boolean z) {
        encodeBool(this.nativeHandle, str, z);
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor remove(String str) {
        removeValueForKey(str);
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    public SharedPreferences.Editor clear() {
        clearAll();
        return this;
    }

    @Override // android.content.SharedPreferences.Editor
    @Deprecated
    public boolean commit() {
        sync(true);
        return true;
    }

    @Override // android.content.SharedPreferences.Editor
    @Deprecated
    public void apply() {
        sync(false);
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        return containsKey(str);
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException("Intentionally Not implement in MMKV");
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException("Intentionally Not implement in MMKV");
    }

    public static MMKV mmkvWithAshmemFD(String str, int i, int i2, String str2) throws RuntimeException {
        long mMKVWithAshmemFD = getMMKVWithAshmemFD(str, i, i2, str2);
        if (mMKVWithAshmemFD == 0) {
            throw new RuntimeException("Fail to create an ashmem MMKV instance [" + str + "] in JNI");
        }
        return new MMKV(mMKVWithAshmemFD);
    }

    public static NativeBuffer createNativeBuffer(int i) {
        long createNB = createNB(i);
        if (createNB <= 0) {
            return null;
        }
        return new NativeBuffer(createNB, i);
    }

    public static void destroyNativeBuffer(NativeBuffer nativeBuffer) {
        destroyNB(nativeBuffer.pointer, nativeBuffer.size);
    }

    public int writeValueToNativeBuffer(String str, NativeBuffer nativeBuffer) {
        return writeValueToNB(this.nativeHandle, str, nativeBuffer.pointer, nativeBuffer.size);
    }

    public static void registerHandler(MMKVHandler mMKVHandler) {
        gCallbackHandler = mMKVHandler;
        if (mMKVHandler.wantLogRedirecting()) {
            setCallbackHandler(true, true);
            gWantLogReDirecting = true;
        } else {
            setCallbackHandler(false, true);
            gWantLogReDirecting = false;
        }
    }

    public static void unregisterHandler() {
        gCallbackHandler = null;
        setCallbackHandler(false, false);
        gWantLogReDirecting = false;
    }

    private static int onMMKVCRCCheckFail(String str) {
        MMKVRecoverStrategic mMKVRecoverStrategic = MMKVRecoverStrategic.OnErrorDiscard;
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null) {
            mMKVRecoverStrategic = mMKVHandler.onMMKVCRCCheckFail(str);
        }
        simpleLog(MMKVLogLevel.LevelInfo, "Recover strategic for " + str + " is " + mMKVRecoverStrategic);
        Integer num = recoverIndex.get(mMKVRecoverStrategic);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static int onMMKVFileLengthError(String str) {
        MMKVRecoverStrategic mMKVRecoverStrategic = MMKVRecoverStrategic.OnErrorDiscard;
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null) {
            mMKVRecoverStrategic = mMKVHandler.onMMKVFileLengthError(str);
        }
        simpleLog(MMKVLogLevel.LevelInfo, "Recover strategic for " + str + " is " + mMKVRecoverStrategic);
        Integer num = recoverIndex.get(mMKVRecoverStrategic);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static void mmkvLogImp(int i, String str, int i2, String str2, String str3) {
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null && gWantLogReDirecting) {
            mMKVHandler.mmkvLog(index2LogLevel[i], str, i2, str2, str3);
            return;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$tencent$mmkv$MMKVLogLevel[index2LogLevel[i].ordinal()];
        if (i3 == 1) {
            Log.d("MMKV", str3);
            return;
        }
        if (i3 == 2) {
            Log.w("MMKV", str3);
        } else if (i3 == 3) {
            Log.e("MMKV", str3);
        } else {
            if (i3 != 5) {
                return;
            }
            Log.i("MMKV", str3);
        }
    }

    private static void simpleLog(MMKVLogLevel mMKVLogLevel, String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[r0.length - 1];
        Integer num = logLevel2Index.get(mMKVLogLevel);
        mmkvLogImp(num == null ? 0 : num.intValue(), stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), stackTraceElement.getMethodName(), str);
    }

    public static void registerContentChangeNotify(MMKVContentChangeNotification mMKVContentChangeNotification) {
        gContentChangeNotify = mMKVContentChangeNotification;
        setWantsContentChangeNotify(mMKVContentChangeNotification != null);
    }

    public static void unregisterContentChangeNotify() {
        gContentChangeNotify = null;
        setWantsContentChangeNotify(false);
    }

    private static void onContentChangedByOuterProcess(String str) {
        MMKVContentChangeNotification mMKVContentChangeNotification = gContentChangeNotify;
        if (mMKVContentChangeNotification != null) {
            mMKVContentChangeNotification.onContentChangedByOuterProcess(str);
        }
    }

    private MMKV(long j) {
        this.nativeHandle = j;
    }
}
