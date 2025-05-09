package com.huawei.hms.maps.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.ads.dynamic.a;
import com.huawei.hms.feature.dynamic.DynamicModule;
import com.huawei.hms.framework.common.EmuiUtil;
import com.huawei.hms.framework.common.SystemPropUtils;
import com.huawei.openalliance.ad.constant.IntentFailError;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class DynamicUtil {

    /* renamed from: a, reason: collision with root package name */
    private static volatile DynamicModule f5031a = null;
    private static int b = 0;
    private static boolean c = false;
    private static boolean d = false;
    private static final List<String> e = Arrays.asList("CRR-UL20", "TAG-CL00", "TIT-CL10", "KIW-AL10", "KIW-TL00H", "TAG-TL00", "CRR-CL20", "KIW-TL00", "KIW-UL00", "KIW-CL00", "TAG-AL00", "NXT-AL10", "NXT-TL00", "NXT-CL00", "NXT-DL00", "KIW-AL20", "VNS-AL00", "VNS-CL00", "EVA-AL00", "EVA-AL10", "EVA-CL00", "EVA-DL00", "EVA-TL00", "PLE-703L", "KNT-AL10", "VNS-TL00", "NEM-UL10", "NEM-TL00H", "KNT-AL20", "CAM-TL00H", "KNT-TL10", "VIE-AL10", "NEM-TL00", "VNS-DL00", "CAM-AL00", "CAM-CL00", "KNT-UL10", "CAM-UL00", "PLE-703LT", "CAM-TL00", "FRD-AL00", "FRD-AL10", "NEM-AL10", "CUN-TL00", "EDI-AL10", "FRD-DL00", "FRD-TL00", "EDI-DL00", "MLA-AL00", "MLA-AL10", "CUN-AL00", "MLA-TL00", "MLA-UL00", "NCE-AL00", "NCE-AL10", "BGO-DL09", "CAZ-AL00", "CAZ-AL10", "NCE-TL00", "CAZ-TL10", "NTS-AL00", "BLN-AL00", "BGO-DL09", "MLA-TL10", "BLN-TL10", "MHA-AL00", "MHA-TL00", "DIG-AL00", "BLN-TL00", "DIG-TL10", "CAZ-TL20", "LON-AL00", "PRA-AL00", "BLN-AL20", "PRA-TL10", "PRA-TL00", "PRA-AL00x", "NCE-TL10", "WAS-AL00", "WAS-TL10", "DUK-AL20", "DUK-TL30", "VTR-TL00", "VTR-AL00", "VKY-AL00", "PRA-TL10", "KOB-L09", "TRT-TL10", "TRT-AL00", "BLN-AL30", "BLN-AL40", "AGS-L09", "VKY-TL00", "TRT-AL00A", "SLA-AL00", "BAH-AL00", "BAC-AL00", "SLA-TL10", "BAC-TL00", "TRT-TL10A", "DLI-AL10", "DLI-TL20", "BZA-L00", "BZK-L00", "STF-AL10", "STF-AL00", "STF-TL10", "CPN-AL00", "PIC-AL00", "PIC-TL00", "MYA-AL10", "MYA-TL10", "K2-G00", "JMM-AL00", "BG2-U01", "JMM-TL00", "JMM-AL10", "JMM-TL10", "HDN-L09", "RNE-AL00", "BND-AL10", "BND-TL10", "LEO-DLXXU", "ALP-TL00", "ALP-AL00", "BLA-TL00", "BLA-AL00", "HWI-AL00", "HWI-TL00", "LLD-AL10", "LLD-AL00", "BKL-AL20", "BKL-AL00", "BKL-TL10", "FIG-AL00", "FIG-AL10", "FIG-TL00", "FIG-TL10", "LLD-TL10", "BND-AL00", "SHT-AL09", "CMR-AL09", "CMR-AL19", "WS5280", "LND-AL30", "LND-AL40", "LDN-AL00", "AUM-TL00", "AUM-AL20", "AUM-AL00", "LDN-TL00", "LND-TL40", "LND-TL30", "AUM-TL20", "FLA-AL00", "FLA-TL10", "LDN-TL20", "LDN-AL20", "EML-TL00", "EML-AL00", "ATU-AL10", "LDN-AL10", "LDN-TL10", "FLA-TL00", "FLA-AL10", "CLT-TL00", "CLT-AL00", "FLA-AL20", "ATU-TL10", "ANE-AL00", "ANE-TL00", "NEO-AL00", "CLT-AL01", "CLT-TL01", "COL-TL10", "COL-AL00", "COL-AL10", "COL-TL00", "LEO-DLXXU", "DUA-AL00", "DUA-TL00", "DRA-AL00", "DRA-TL00", "LLD-AL20", "LLD-AL30", "MON-AL19B", "LEO-DL00E", "COR-TL10", "COR-AL10", "COR-AL00", "PAR-TL00", "PAR-AL00", "BZT-AL00", "BZT-AL10", "INE-TL00", "INE-AL00", "CLT-AL00l", "BAH2-AL00", "RVL-AL09", "BAH2-AL10", "SNE-AL00", "SIM-AL00", "ARE-AL00", "AGS2-AL00", "AGS2-AL00HN", "JSN-AL00", "JSN-AL00a", "JSN-TL00", "JKM-AL00", "BKK-AL10", "HMA-AL00", "HMA-TL00", "BKK-AL00", "JKM-TL00", "LYA-TL00", "LYA-AL00", "BKK-TL00", "ARE-TL00", "ARE-AL10", "JKM-AL00a", "LEO-DLXXU", "LYA-AL10", "LYA-AL00P", "EVR-AL00", "ARS-AL00", "EVR-TL00", "TNY-AL00", "TNY-TL00", "ARS-TL00", "HRY-AL00", "HRY-TL00", "HRY-AL00a", "JKM-AL00b", "DUB-TL00", "DUB-AL00", "DUB-AL20", "INE-AL00r", "JAT-TL00", "JAT-AL00", "LEO-DL00E", "VCE-AL00", "VCE-TL00", "HDL-AL09", "PCT-AL10", "PCT-TL10", "Elf-G00", "MRD-AL00", "MRD-TL00", "POT-AL00a", "POT-AL00", "POT-TL00a", "MAR-AL00", "MAR-TL00", "JDN2-AL00HN", "KSA-AL00", "ELE-AL00", "ELE-TL00", "VOG-AL00", "VOG-AL10", "VOG-TL00", "KSA-TL00", "PAK-AL09", "LEO-DLXXU", "SIM-AL00", "JDN2-AL00", "DUB-AL00a", "DUB-TL00a", "HRY-AL00Ta", "HRY-TL00T", "ELE-AL00", "LYA-AL00", "HRY-AL00T", "VOG-AL00", "LYA-AL10", "VOG-AL10", "HMA-AL00", "LEO-DL00E", "YAL-AL00", "JSN-AL00a", "ALX-AL10", "YAL-TL00", "JKM-AL00b", "MRD-AL00", "MAR-AL00", "JAT-AL00", "DUB-AL00", "DUB-AL00a", "DUB-TL00a", "MAR-TL00", "GLK-AL00", "BZT-AL00", "BZT-AL10", "BAH2-AL10", "GLK-TL00", "SEA-AL10", "SEA-TL10", "SCM-AL09", "DUB-AL00", "DUB-TL00", "DUB-AL00a", "AGS2-AL00HN", "AGS2-AL00", "DUB-TL00a", "DUB-TL00", "DUB-AL00", "YAL-AL10", "VRD-AL09", "HLK-AL10", "YAL-TL10", "KSA-AL00", "KSA-TL00", "SEA-AL00", "CSN-AL00", "B316-855", "ELE-AL00m", "VOG-AL00m", "HLK-AL00", "SPN-AL00", "SPN-TL00", "JAT-AL00", "MRD-AL00", "HLK-TL00", "GLK-AL00", "GLK-TL00", "SEA-AL10", "VRD-AL10", "EVR-AN00", "POT-AL10", "SEA-TL10", "SEA-TL00", "YAL-AL00", "JDN2-AL00", "JDN2-AL50", "YAL-AL00", "H112-372", "TAH-AN00", "JDN2-AL50HN", "YAL-AL10", "JAT-TL00", "MRD-TL00", "STK-AL00", "STK-TL00", "JDN2-AL00HN", "SIM-AL00", "LEO-DLXXU", "HRY-AL00Ta", "YAL-AL50", "HDL-AL09", "YAL-AL10", "YAL-TL50", "HRY-AL00a", "HLK-AL00", "HLK-AL10", "ELE-AL00", "HRY-AL00T", "POT-AL00a", "RVL-AL09", "HRY-AL00Ta", "TAS-AL00", "TAS-TL00", "LIO-AL00", "LIO-TL00", "POT-AL10", "HLK-TL00", "ASK-AL00x", "ASK-TL00x", "ELF-G10", "ELF-G00", "ART-AL00x", "ART-TL00x", "ELE-TL00", "EVR-AN00", "AMN-AL10", "KSA-AL10", "AMN-TL10", "LRA-AL00", "AQM-AL00", "LEO-DLXXU", "AQM-TL00", "LIO-TN00", "LIO-AN00P", "LIO-AN00", "TAS-TN00", "TAS-AN00", "BZW-AL00", "BZW-AL10", "MRX-AL09", "WLZ-AN00", "OXF-AN00", "WLZ-AL10", "GLK-LX1U", "OXF-AN10", "LIO-TN00", "LIO-AN00P", "LIO-AN00", "MRX-AL19", "JNY-AL10", "OXF-AN00", "OXF-AN10", "WLZ-AN00", "ASK-AL00x", "ART-AL00x", "ASK-TL00x", "ASK-AL00x", "JNY-AL10", "TAH-AN00m", "VOG-AL00", "EVR-AN00", "MOA-TL00", "MOA-AL00", "MED-TL00", "MED-AL00", "TAH-AN00", "ANA-AN00", "ANA-TN00", "HLK-AL00a", "ELS-AN00", "ELS-TN00", "ART-AL00m", "BAH3-AL00", "MED-AL00", "H122-373", "BMH-AN10", "EBG-AN00", "EBG-AN10", "JEF-AN00", "BZT3-AL00", "CDY-AN90", "MED-AL20", "MOA-AL20", "AKA-AL10", "CDY-AN00", "CDY-TN00", "EBG-TN00", "AQM-AL10", "AGS-L09", "JER-AN10", "CDY-TN90", "JEF-TN00", "ELS-AN10", "ELS-TN10", "ANA-AN00", "ANA-TN00", "NEO-AL10", "ELS-AN00", "ELS-TN00", "MOA-AL00", "JER-TN10", "BMH-TN10", "TEL-AN00", "TEL-AN00a", "OXF-AN00", "WLZ-AN00", "OXF-AN10", "LEO-DL00E", "LEO-DLXXU", "TEL-TN00", "CDY-AN90", "TAH-AN00m", "BZD-AL00", "CDY-AN00", "BMH-AN10", "OXP-AN00", "FRL-AN00a", "JER-AN10", "JEF-AN00", "MRX-AN19", "EBG-AN00", "BMH-TN10", "CDY-TN90", "CDY-TN00", "AKA-AL10", "AGS3-AL00", "JEF-TN00", "AGS3-AL09HN", "ART-AL00x", "ASK-AL00x", "JER-TN10", "DVC-AN00", "SIM-AL00", "TEL-AN00", "KKG-AN00", "MXW-AN00", "MXW-TN00", "TNNH-AN00", "DVC-AN20", "CDY-AN20", "CDY-AN95", "CDY-TN20", "CDY-TN95", "ELS-AN10", "BMH-AN20", "BMH-TN20", "DVC-TN20", "JEF-AN20", "JEF-TN20", "JER-AN20", "JER-TN20", "ASK-AL20", "TEL-AN10", "TEL-TN10", "NIK-AL00", "OXP-AN00", "DVC-AN00", "ANA-AN00", "WKG-AN00", "KRJ-AN00", "DVC-AN20", "TNN-AN00", "WKG-TN00", "TAH-AN00m", "MXW-AN00", "MXW-TN00", "SCMR-AL09", "DVC-TN20", "AGR-AL09HN", "BZC-AL00", "NZA-AN00", "NZA-TN00", "FRL-AN00a", "ANA-AN00", "ANA-TN00", "ELS-AN00", "ELS-TN00", "LIO-AN00", "LIO-AN00P", "LIO-TN00", "TAS-AN00", "TAS-TN00", "FRL-TN00", "DVC-AN20", "DVC-TN20", "NOH-AN00", "LIO-AL00m", "NOP-AN00", "WKG-AN00", "DVC-AN00", "MXW-AN00", "MXW-TN00", "BAH3-AN10", "BZD-AL10", "CDY-AN90", "OXF-AN00", "OXF-AN10", "WLZ-AN00", "NZA-AN00", "BMH-AN10", "CDY-AN00", "EBG-AN00", "EBG-AN10", "JEF-AN00", "JER-AN10", "WKG-TN00", "LEO-DL00E", "CND-AN00", "NEO-AL10", "BMH-TN10", "OCE-AN00", "TEL-AN00", "TEL-AN00a", "TEL-TN00", "ELS-AN10", "HJC-AN00", "HJC-TN00", "JSC-AN00", "JSC-TN00", "NZA-TN00", "BZA-L00", "AGS-L09", "CDY-TN00", "CDY-TN90", "JEF-TN00", "JER-TN10", "CND-TN00", "EBG-TN00", "LIO-AN00m", "NOH-AN00", "TET-AN00", "TET-AN10", "SIM-AL00", "MRX-AN19", "MXW-TN00", "MXW-AN00", "DVC-TN20", "DVC-AN20", "DVC-AN00", "KKG-AN00", "ANG-AN00", "EVR-AN00", "TAH-AN00", "OCE-AN10", "CDL-AN50", "JEF-AN20", "JEF-TN20", "TEL-AN10", "BRQ-AN00", "BMH-AN20", "BMH-TN20", "CDY-AN20", "CDY-AN95", "CDY-TN20", "CDY-TN95", "JER-AN20", "JER-TN20", "TNN-AN00", "TNNH-AN00", "YOK-AN10", "TAH-AN00m", "WKG-TN00", "WKG-AN00", "HJC-AN90", "NZA-AN00", "NZA-TN00", "PPA-AL20", "TET-AN00", "KRJ-AN00", "NIK-AL00", "ANA-AN00", "ANA-TN00", "ELS-AN00", "ELS-TN00", "LIO-AN00", "LIO-AN00P", "LIO-TN00", "TAS-AN00", "TAS-TN00", "DNN-AL00", "FRL-AN00a", "FRL-TN00", "PKU-AL40", "LEO-DL00E", "SIM-AL00", "NEO-AL10", "BAH3-AN10", "TEL-TN00", "TEL-AN00a", "TEL-AN00", "OXF-AN10", "OXF-AN00", "WLZ-AN00", "JER-AN10", "CDY-AN90", "CDY-AN00", "EBG-AN00", "EBG-AN10", "ELS-AN10", "NOP-AN00", "NOH-AN00", "OCE-AN00", "LIO-AN00m", "JER-TN10", "JEF-TN00", "EBG-TN00", "CND-AN00", "CDY-TN90", "BMH-TN10", "CDY-TN00", "OCE-AN50", "NOH-AN01", "ANA-AL00", "MRX-AN19", "HJC-TN00", "HJC-AN00", "JSC-AN00", "OXP-AN00", "DVC-AN00", "DVC-TN20", "JSC-TN00", "DVC-AN20", "MXW-AN00", "MXW-TN00");

    public static boolean isAndroidN() {
        return false;
    }

    public static void setDynamicModule(DynamicModule dynamicModule) {
        f5031a = dynamicModule;
    }

    public static boolean isMagic6OrAbove() {
        return getEmuiVersionCode() >= 32;
    }

    public static boolean isLowEmuiVersion() {
        return getEmuiVersionCode() > 0 && getEmuiVersionCode() <= 11;
    }

    public static boolean isHuaweiPhone(Context context) {
        if (c) {
            return d;
        }
        try {
            boolean b2 = b();
            LogM.d("DynamicUtil", "isFlyingSkyPhone : " + b2);
            if (!b2) {
                boolean hasSystemFeature = context.getPackageManager().hasSystemFeature("com.huawei.software.features.handset");
                if (!hasSystemFeature) {
                    if (!TextUtils.equals("HUAWEI", Build.BRAND) && !isHonorPhone(context) && !a()) {
                        hasSystemFeature = false;
                    }
                    hasSystemFeature = true;
                }
                d = hasSystemFeature;
            }
            c = true;
        } catch (Exception unused) {
            LogM.e("DynamicUtil", "Failed to judge phone.");
        }
        LogM.i("DynamicUtil", "isHuaweiPhone : " + d);
        return d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0023, code lost:
    
        if (isMagic6OrAbove() != false) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isHonorPhone(android.content.Context r3) {
        /*
            java.lang.String r0 = "DynamicUtil"
            r1 = 0
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: java.lang.Exception -> L2a
            java.lang.String r2 = "com.hihonor.software.features.handset"
            boolean r3 = r3.hasSystemFeature(r2)     // Catch: java.lang.Exception -> L2a
            if (r3 != 0) goto L1c
            java.lang.String r3 = "HONOR"
            java.lang.String r2 = android.os.Build.BRAND     // Catch: java.lang.Exception -> L2a
            boolean r3 = android.text.TextUtils.equals(r3, r2)     // Catch: java.lang.Exception -> L2a
            if (r3 == 0) goto L1a
            goto L1c
        L1a:
            r3 = r1
            goto L1d
        L1c:
            r3 = 1
        L1d:
            if (r3 == 0) goto L28
            boolean r2 = isMagic6OrAbove()     // Catch: java.lang.Exception -> L26
            if (r2 == 0) goto L28
            goto L2f
        L26:
            r1 = r3
            goto L2a
        L28:
            r1 = r3
            goto L2f
        L2a:
            java.lang.String r3 = "Failed to judge phone."
            com.huawei.hms.maps.utils.LogM.e(r0, r3)
        L2f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r2 = "isHonorPhone : "
            r3.<init>(r2)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.huawei.hms.maps.utils.LogM.i(r0, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.maps.utils.DynamicUtil.isHonorPhone(android.content.Context):boolean");
    }

    public static void handlerDynamicLoadAfterLogic(String str, Context context) {
        if (!isHuaweiPhone(context)) {
            DynamicModule.enable3rdPhone(str, true);
        }
        if (isLowEmuiVersion()) {
            DynamicModule.enableLowEMUI(str, true);
        }
    }

    public static int getMagicVersionCode() {
        String str;
        try {
            Class<?> cls = Class.forName("com.hihonor.android.os.Build$VERSION");
            Field field = cls.getField(Build.VERSION.SDK_INT >= 31 ? "MAGIC_SDK_INT" : EmuiUtil.EMUI_SDK_INT);
            field.setAccessible(true);
            b = field.getInt(cls);
        } catch (ClassNotFoundException unused) {
            str = "ClassNotFoundException";
            LogM.e("DynamicUtil", str);
            LogM.i("DynamicUtil", "MagicVersionCodeValue: " + b);
            return b;
        } catch (IllegalAccessException unused2) {
            str = "IllegalAccessException";
            LogM.e("DynamicUtil", str);
            LogM.i("DynamicUtil", "MagicVersionCodeValue: " + b);
            return b;
        } catch (NoSuchFieldException unused3) {
            str = "NoSuchFieldException";
            LogM.e("DynamicUtil", str);
            LogM.i("DynamicUtil", "MagicVersionCodeValue: " + b);
            return b;
        } catch (Exception unused4) {
            str = IntentFailError.UNKNOWN_EXCEPTION;
            LogM.e("DynamicUtil", str);
            LogM.i("DynamicUtil", "MagicVersionCodeValue: " + b);
            return b;
        }
        LogM.i("DynamicUtil", "MagicVersionCodeValue: " + b);
        return b;
    }

    public static int getEmuiVersionCode() {
        int i = b;
        if (i != 0) {
            return i;
        }
        b = a(EmuiUtil.BUILDEX_VERSION);
        LogM.i("DynamicUtil", "emuiVersionCodeValue: " + b);
        if (b == 0) {
            b = getMagicVersionCode();
        }
        return b;
    }

    public static DynamicModule getDynamicModule() {
        return f5031a;
    }

    private static boolean b() {
        String property = SystemPropUtils.getProperty("get", "ro.build.2b2c.partner.ext_channel", "android.os.SystemProperties", "UNKNOWN");
        LogM.i("DynamicUtil", "isFlyingSkyDevice extChannel: " + property);
        return !TextUtils.isEmpty(property) && property.startsWith(a.t);
    }

    private static boolean a() {
        LogM.d("DynamicUtil", "the mobile model is " + Build.MODEL);
        return e.contains(Build.MODEL);
    }

    private static int a(String str) {
        String str2;
        try {
            Class<?> cls = Class.forName(str);
            Field declaredField = cls.getDeclaredField(EmuiUtil.EMUI_SDK_INT);
            AccessibleObject.setAccessible(new Field[]{declaredField}, true);
            Object obj = declaredField.get(cls);
            if (obj != null && (obj instanceof Integer)) {
                return ((Integer) obj).intValue();
            }
        } catch (ClassCastException unused) {
            str2 = "ClassCastException: ";
            LogM.e("DynamicUtil", str2);
            return 0;
        } catch (ClassNotFoundException unused2) {
            str2 = "ClassNotFoundException: ";
            LogM.e("DynamicUtil", str2);
            return 0;
        } catch (IllegalAccessException unused3) {
            str2 = "IllegalAccessException: ";
            LogM.e("DynamicUtil", str2);
            return 0;
        } catch (NoSuchFieldException unused4) {
            str2 = "NoSuchFieldException: ";
            LogM.e("DynamicUtil", str2);
            return 0;
        } catch (Exception unused5) {
            str2 = IntentFailError.UNKNOWN_EXCEPTION;
            LogM.e("DynamicUtil", str2);
            return 0;
        }
        return 0;
    }
}
