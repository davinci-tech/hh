package health.compact.a;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDataFilter;
import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVHandler;
import com.tencent.mmkv.MMKVLogLevel;
import com.tencent.mmkv.MMKVRecoverStrategic;

/* loaded from: classes.dex */
public class MmkvUtil {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f13131a = false;
    private static final Object c = new Object();

    private MmkvUtil() {
    }

    public static MMKV e(String str, String str2) throws RuntimeException {
        d();
        return MMKV.mmkvWithID(str, 2, str2);
    }

    private static void d() {
        if (f13131a) {
            return;
        }
        synchronized (c) {
            if (f13131a) {
                return;
            }
            MMKV.initialize(BaseApplication.e());
            MMKV.registerHandler(new MMKVHandler() { // from class: health.compact.a.MmkvUtil.3
                @Override // com.tencent.mmkv.MMKVHandler
                public boolean wantLogRedirecting() {
                    return true;
                }

                @Override // com.tencent.mmkv.MMKVHandler
                public MMKVRecoverStrategic onMMKVCRCCheckFail(String str) {
                    ReleaseLogUtil.c("TimeEat_MmkvUtil", "onMMKVCRCCheckFail happened, mmapID = ", str);
                    return null;
                }

                @Override // com.tencent.mmkv.MMKVHandler
                public MMKVRecoverStrategic onMMKVFileLengthError(String str) {
                    ReleaseLogUtil.c("TimeEat_MmkvUtil", "onMMKVFileLengthError happened, mmapID = ", str);
                    return null;
                }

                @Override // com.tencent.mmkv.MMKVHandler
                public void mmkvLog(MMKVLogLevel mMKVLogLevel, String str, int i, String str2, String str3) {
                    String str4 = HiDataFilter.DataFilterExpression.LESS_THAN + str + ":" + i + "::" + str2 + "> " + str3;
                    int i2 = AnonymousClass2.e[mMKVLogLevel.ordinal()];
                    if (i2 == 1) {
                        LogUtil.d("TimeEat_MmkvUtil", "redirect logging MMKV", str4);
                        return;
                    }
                    if (i2 == 2) {
                        LogUtil.d("TimeEat_MmkvUtil", "redirect logging MMKV", str4);
                        return;
                    }
                    if (i2 == 3) {
                        LogUtil.d("TimeEat_MmkvUtil", "redirect logging MMKV", str4);
                    } else if (i2 == 4 || i2 == 5) {
                        LogUtil.d("TimeEat_MmkvUtil", "redirect logging MMKV", str4);
                    }
                }
            });
            f13131a = true;
        }
    }

    /* renamed from: health.compact.a.MmkvUtil$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[MMKVLogLevel.values().length];
            e = iArr;
            try {
                iArr[MMKVLogLevel.LevelDebug.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[MMKVLogLevel.LevelInfo.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[MMKVLogLevel.LevelWarning.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[MMKVLogLevel.LevelError.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[MMKVLogLevel.LevelNone.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
