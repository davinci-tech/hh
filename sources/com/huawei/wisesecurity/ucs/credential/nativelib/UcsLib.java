package com.huawei.wisesecurity.ucs.credential.nativelib;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.ucs.credential.entity.EcKeyPair;
import defpackage.twb;
import defpackage.twc;
import defpackage.twf;
import defpackage.two;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class UcsLib {
    private static final String LIB_NAME = "ucs-credential";
    private static final long NATIVE_VERIFY_SIGNATURE_FAIL = 60000;
    private static final String TAG = "UcsLib";
    private static volatile boolean flag = false;
    private static volatile boolean updateRootKeyFlag = false;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final Object CA_LOCK = new Object();

    public static class OutputParam {
        public byte[] bytes = null;
        public byte[] secondBytes = null;
        public byte[] thirdBytes = null;
    }

    private static native long getSoVersion();

    private static native long nativeCheckPkgNameCertFP(Context context, byte[] bArr, byte[] bArr2, OutputParam outputParam);

    private static native long nativeDecryptKek(byte[] bArr, int i, OutputParam outputParam);

    private static native long nativeDecryptKekWithEc(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4, OutputParam outputParam);

    private static native long nativeGenReqJws(Context context, byte[] bArr, byte[] bArr2, long j, byte[] bArr3, OutputParam outputParam);

    private static native long nativeGenerateEcKeyPair(Context context, OutputParam outputParam);

    private static native long nativeGetPkgNameCertFP(Context context, OutputParam outputParam);

    private static native long updateRootKey(byte[] bArr, int i, OutputParam outputParam);

    public static void ucsUpdateRootKey(byte[] bArr, int i) throws twc {
        synchronized (CA_LOCK) {
            OutputParam newOutputParam = newOutputParam();
            long updateRootKey = updateRootKey(bArr, i, newOutputParam);
            updateRootKeyFlag = updateRootKey == 0;
            if (updateRootKey != 0) {
                String errorMessage = getErrorMessage(newOutputParam, "Fail to updateRootKey");
                twb.e(TAG, errorMessage, new Object[0]);
                throw new twc(1009L, errorMessage);
            }
        }
    }

    public static long ucsGetSoVersion() {
        return getSoVersion();
    }

    public static OutputParam newOutputParam() {
        return new OutputParam();
    }

    public static String loadLibrary() {
        String str;
        synchronized (UcsLib.class) {
            str = "";
            if (!flag) {
                try {
                    System.loadLibrary(LIB_NAME);
                    twb.a(TAG, "load lib {0} success", LIB_NAME);
                    flag = true;
                } catch (Throwable th) {
                    StringBuilder e = twf.e("load lib ucs-credential error : ");
                    e.append(th.getMessage());
                    str = e.toString();
                    twb.e(TAG, str, new Object[0]);
                }
            }
        }
        return str;
    }

    public static boolean isRootKeyUpdated() {
        return updateRootKeyFlag;
    }

    public static List<String> getPkgNameCertFP(Context context) throws twc {
        ArrayList arrayList = new ArrayList();
        OutputParam newOutputParam = newOutputParam();
        long nativeGetPkgNameCertFP = nativeGetPkgNameCertFP(context, newOutputParam);
        if (nativeGetPkgNameCertFP != 0) {
            String errorMessage = getErrorMessage(newOutputParam, "Fail to getPkgNameCertFP");
            throw two.e(TAG, errorMessage, new Object[0], nativeGetPkgNameCertFP, errorMessage);
        }
        arrayList.add(new String(newOutputParam.bytes, StandardCharsets.UTF_8));
        arrayList.add(new String(newOutputParam.secondBytes, StandardCharsets.UTF_8));
        return arrayList;
    }

    private static String getErrorMessage(OutputParam outputParam, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(", ");
        byte[] bArr = outputParam.thirdBytes;
        sb.append(bArr != null ? new String(bArr, StandardCharsets.UTF_8) : "errorLog is null.");
        return sb.toString();
    }

    public static EcKeyPair generateEcKeyPair(Context context) throws twc {
        OutputParam newOutputParam = newOutputParam();
        long nativeGenerateEcKeyPair = nativeGenerateEcKeyPair(context, newOutputParam);
        if (nativeGenerateEcKeyPair == 0) {
            return EcKeyPair.newBuilder().publicKey(newOutputParam.bytes).privateKey(newOutputParam.secondBytes).build();
        }
        String errorMessage = getErrorMessage(newOutputParam, "Fail to nativeGenerateEcKeyPair");
        throw two.e(TAG, errorMessage, new Object[0], nativeGenerateEcKeyPair, errorMessage);
    }

    public static byte[] genReqJws(Context context, String str, String str2, int i, int i2) throws twc {
        String str3 = str == null ? "" : str;
        if (context == null) {
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, "context cannot empty..");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, "packageName cannot empty..");
        }
        OutputParam newOutputParam = newOutputParam();
        long nativeGenReqJws = nativeGenReqJws(context, str3.getBytes(StandardCharsets.UTF_8), str2.getBytes(StandardCharsets.UTF_8), i, String.valueOf(i2).getBytes(StandardCharsets.UTF_8), newOutputParam);
        if (nativeGenReqJws == 0) {
            return newOutputParam.bytes;
        }
        String errorMessage = getErrorMessage(newOutputParam, "Fail to genReqJws");
        throw two.e(TAG, errorMessage, new Object[0], nativeGenReqJws, errorMessage);
    }

    public static byte[] decryptKekWithEc(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws twc {
        OutputParam newOutputParam = newOutputParam();
        long nativeDecryptKekWithEc = nativeDecryptKekWithEc(bArr, i, bArr2, bArr3, bArr4, newOutputParam);
        if (nativeDecryptKekWithEc == 0) {
            return newOutputParam.bytes;
        }
        String errorMessage = getErrorMessage(newOutputParam, "Fail to decryptKekWithEc");
        throw two.e(TAG, errorMessage, new Object[0], nativeDecryptKekWithEc, errorMessage);
    }

    public static byte[] decryptKek(byte[] bArr, int i) throws twc {
        OutputParam newOutputParam = newOutputParam();
        long nativeDecryptKek = nativeDecryptKek(bArr, i, newOutputParam);
        if (nativeDecryptKek == 0) {
            return newOutputParam.bytes;
        }
        String errorMessage = getErrorMessage(newOutputParam, "Fail to decryptKek");
        throw two.e(TAG, errorMessage, new Object[0], nativeDecryptKek, errorMessage);
    }

    public static boolean checkPkgNameCertFP(Context context, String str, String str2, StringBuilder sb) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "appPkgName is null.";
        } else {
            if (!TextUtils.isEmpty(str2)) {
                OutputParam newOutputParam = newOutputParam();
                long nativeCheckPkgNameCertFP = nativeCheckPkgNameCertFP(context, str.getBytes(StandardCharsets.UTF_8), str2.getBytes(StandardCharsets.UTF_8), newOutputParam);
                if (nativeCheckPkgNameCertFP != 0) {
                    sb.append(getErrorMessage(newOutputParam, ""));
                }
                return nativeCheckPkgNameCertFP == 0;
            }
            str3 = "appCertFP is null.";
        }
        sb.append(str3);
        return false;
    }

    public static void checkNativeLibrary() throws twc {
        if (flag) {
            return;
        }
        String loadLibrary = loadLibrary();
        if (flag) {
            return;
        }
        throw new twc(1004L, "UCS load library error : " + loadLibrary);
    }
}
