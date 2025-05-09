package defpackage;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.flatbuffers.reflection.BaseType;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes5.dex */
class low {
    private static Map<String, String> b = new HashMap();
    private static final String d = "GBAUtils";

    low() {
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        byte[] f14818a;
        byte[] b;
        byte[] c;
        byte[] d;
        byte[] e;

        a() {
        }
    }

    static byte[] e(a aVar, String str, String str2, los losVar) {
        if (aVar == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || losVar == null) {
            return null;
        }
        try {
            return e(aVar.e, a(aVar.b, String.format("%1$s@ims.mnc%2$s.mcc%3$s.3gppnetwork.org", str, lop.b(str), lop.a(str)).getBytes("utf-8"), c(str2.getBytes("utf-8"), new byte[]{1, 0, 1, (byte) (losVar.b >> 8), (byte) (losVar.b & 255)})));
        } catch (UnsupportedEncodingException unused) {
            loq.c(d, "calculateKsNaf -> UnsupportedEncodingException");
            return null;
        }
    }

    static String e(String str, String str2) {
        if (loq.b.booleanValue()) {
            loq.c(d, "secret:data = " + str + ":" + str2);
        }
        String d2 = d(str + ":" + str2);
        if (loq.b.booleanValue()) {
            loq.c(d, "response = " + d2 + " resopnse length = " + d2.length());
        }
        return d2;
    }

    static String e(String str, String str2, String str3, String str4) {
        String str5;
        String str6;
        StringBuilder sb = new StringBuilder();
        if (str4.equals("GET")) {
            if (loq.b.booleanValue()) {
                loq.c(d, "GET password = " + str3);
            }
            sb.append(str);
            sb.append(":");
            sb.append(str2);
            sb.append(":");
            str5 = sb.toString();
            str6 = d(str5, a(str3));
        } else if (str4.equals("POST")) {
            String encodeToString = Base64.encodeToString(a(str3), 2);
            if (loq.b.booleanValue()) {
                loq.c(d, "POST password = " + encodeToString);
            }
            str5 = str + ":" + str2 + ":" + encodeToString;
            str6 = d(str5);
        } else {
            str5 = null;
            str6 = null;
        }
        if (loq.b.booleanValue()) {
            loq.c(d, "Secret->H(A1) = " + str6 + ", A1(username:realm:password) = " + str5);
        }
        return str6;
    }

    static String b(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        String d2 = d(str4);
        sb.append(str);
        sb.append(":00000001:");
        sb.append(str2);
        sb.append(":");
        sb.append(str3);
        sb.append(":");
        sb.append(d2);
        String sb2 = sb.toString();
        if (loq.b.booleanValue()) {
            loq.c(d, "Data(nonce:nc:cnonce:qop:H(A2)) = " + sb2);
        }
        return sb2;
    }

    static String c() {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("MD5").digest(Long.toString(System.currentTimeMillis()).getBytes()), 0).trim();
        } catch (NoSuchAlgorithmException unused) {
            loq.c(d, "Can't find MD5 enter algorithm exception");
            return null;
        }
    }

    static a b(Context context, String str, int i) {
        if (str == null || context == null) {
            loq.c(d, " data == null || context == null");
            return null;
        }
        int i2 = 0;
        byte[] decode = Base64.decode(str, 0);
        if (decode.length < 32) {
            loq.c(d, "auth failed,data == null or wrong dataLength");
            return null;
        }
        byte[] bArr = new byte[17];
        byte[] bArr2 = new byte[16];
        byte[] bArr3 = new byte[17];
        bArr[0] = BaseType.Union;
        bArr3[0] = BaseType.Union;
        while (i2 < 16) {
            int i3 = i2 + 1;
            byte b2 = decode[i2];
            bArr[i3] = b2;
            bArr2[i2] = b2;
            bArr3[i3] = decode[i2 + 16];
            i2 = i3;
        }
        if (loq.b.booleanValue()) {
            loq.c(d, "RAND =" + a(bArr) + ", AUTN =" + a(bArr3));
        }
        a c = c(d(context, i, Base64.encodeToString(c(bArr, bArr3), 2)));
        if (c != null) {
            c.b = bArr2;
        }
        return c;
    }

    private static String d(Context context, int i, String str) {
        int c = Build.VERSION.SDK_INT >= 29 ? lop.c(context, i) : i;
        String str2 = d;
        loq.c(str2, "calculateAuthRespChallenge slotId = " + i + ",subId = " + c);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        String str3 = "";
        if (telephonyManager == null) {
            loq.b(str2, "getSimAuthResult telephonyManagerClass is null");
            return "";
        }
        TelephonyManager createForSubscriptionId = telephonyManager.createForSubscriptionId(c);
        if (createForSubscriptionId != null) {
            loq.c(str2, "telephonyManager telephonyManager = " + createForSubscriptionId + ", subID = " + c);
            str3 = createForSubscriptionId.getIccAuthentication(2, 129, str);
            loq.c(str2, "getIccAuthentication result = " + str3 + ",subID = " + c);
        }
        if (loq.b.booleanValue()) {
            loq.c(str2, "getIccAuthentication result = " + str3 + ",subID = " + c);
        }
        return str3;
    }

    private static a c(String str) {
        a aVar = null;
        if (TextUtils.isEmpty(str)) {
            loq.c(d, "hexAuthResponse is null ,return");
            return null;
        }
        byte[] decode = Base64.decode(str, 0);
        if (decode[0] == 65499) {
            aVar = new a();
            String str2 = d;
            Log.v(str2, "successful 3G authentication ");
            int i = decode[1];
            aVar.c = new byte[i];
            System.arraycopy(decode, 2, aVar.c, 0, i);
            int i2 = decode[i + 2];
            aVar.f14818a = new byte[i2];
            System.arraycopy(decode, i + 3, aVar.f14818a, 0, i2);
            int i3 = i + i2;
            int i4 = decode[i3 + 3];
            aVar.d = new byte[i4];
            System.arraycopy(decode, i3 + 4, aVar.d, 0, i4);
            aVar.e = a(aVar.f14818a, aVar.d);
            if (loq.b.booleanValue()) {
                loq.c(str2, "ik:" + a(aVar.d) + " ck:" + a(aVar.f14818a) + " res:" + a(aVar.c) + " ks:" + a(aVar.e));
            }
        }
        return aVar;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        if (loq.b.booleanValue()) {
            loq.c(d, "enter generateKs ck=" + a(bArr) + ", ik=" + a(bArr2));
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        if (loq.b.booleanValue()) {
            loq.c(d, "leave generateKs, ks=" + a(bArr3));
        }
        return bArr3;
    }

    private static byte[] c(byte[] bArr, byte[] bArr2) {
        int length = bArr.length + bArr2.length;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        if (loq.b.booleanValue()) {
            loq.c(d, "byte_3=" + Arrays.toString(bArr3) + ", length=" + length);
        }
        return bArr3;
    }

    static String e(String str) {
        if (str == null) {
            loq.c(d, "Parameter name may not be null");
            return null;
        }
        Map<String, String> map = b;
        if (map == null) {
            loq.c(d, "mParams is null");
            return null;
        }
        return map.get(str.toLowerCase());
    }

    private static byte[] e(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        try {
            if (loq.b.booleanValue()) {
                loq.c(d, "enter hmacSha256 key=" + a(bArr) + ", data=" + a(bArr2));
            }
            Mac mac = Mac.getInstance("HMAC-SHA256");
            mac.init(new SecretKeySpec(bArr, "HMAC-SHA256"));
            bArr3 = mac.doFinal(bArr2);
            if (loq.b.booleanValue()) {
                loq.c(d, "leave hmacSha256, ret=" + Base64.encodeToString(bArr3, 2));
            }
        } catch (Exception unused) {
            loq.c(d, "hmacSha256 Exception");
        }
        return bArr3;
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (loq.b.booleanValue()) {
            loq.c(d, "enter generateS rand=" + Base64.encodeToString(bArr, 2) + ", impi=" + Base64.encodeToString(bArr2, 2) + ", nafId=" + Base64.encodeToString(bArr3, 2));
        }
        byte[] b2 = b((short) bArr.length);
        byte[] b3 = b((short) bArr2.length);
        byte[] b4 = b((short) bArr3.length);
        int length = bArr.length + 9 + b2.length + bArr2.length + b3.length + bArr3.length + b4.length;
        byte[] bArr4 = new byte[length];
        System.arraycopy(new byte[]{1}, 0, bArr4, 0, 1);
        System.arraycopy(new byte[]{103, 98, 97, 45, 109, 101}, 0, bArr4, 1, 6);
        System.arraycopy(new byte[]{0, 6}, 0, bArr4, 7, 2);
        System.arraycopy(bArr, 0, bArr4, 9, bArr.length);
        int length2 = bArr.length + 9;
        System.arraycopy(b2, 0, bArr4, length2, b2.length);
        int length3 = length2 + b2.length;
        System.arraycopy(bArr2, 0, bArr4, length3, bArr2.length);
        int length4 = length3 + bArr2.length;
        System.arraycopy(b3, 0, bArr4, length4, b3.length);
        int length5 = length4 + b3.length;
        System.arraycopy(bArr3, 0, bArr4, length5, bArr3.length);
        System.arraycopy(b4, 0, bArr4, length5 + bArr3.length, b4.length);
        if (loq.b.booleanValue()) {
            loq.c(d, "leave generateS, len=" + length + ", s=" + a(bArr4));
        }
        return bArr4;
    }

    static void b(String str) {
        if (str == null) {
            loq.c(d, "challengeStr is null");
            return;
        }
        int indexOf = str.indexOf(32);
        if (indexOf == -1 && loq.b.booleanValue()) {
            loq.c(d, "Invalid challenge: " + str);
        }
        b.clear();
        List c = new lpb().c(str.substring(indexOf + 1, str.length()), ',');
        int size = c.size();
        for (int i = 0; i < size; i++) {
            lov lovVar = (lov) c.get(i);
            b.put(lovVar.e().toLowerCase(), lovVar.b());
        }
    }

    private static String d(String str) {
        try {
            if (loq.b.booleanValue()) {
                loq.c(d, "encoderByMd5 str =" + str);
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes("US-ASCII"));
            return a(messageDigest.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    private static String d(String str, byte[] bArr) {
        try {
            byte[] bytes = str.getBytes();
            int length = bytes.length;
            int length2 = bArr.length + length;
            byte[] bArr2 = new byte[length2];
            for (int i = 0; i < length2; i++) {
                if (i < length) {
                    bArr2[i] = bytes[i];
                } else {
                    bArr2[i] = bArr[i - length];
                }
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr2);
            return a(messageDigest.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static byte[] a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (c(charArray[i2 + 1]) | (c(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte c(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private static byte[] b(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }
}
