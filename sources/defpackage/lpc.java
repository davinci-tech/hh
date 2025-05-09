package defpackage;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.google.flatbuffers.reflection.BaseType;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class lpc {
    private static final String e = "AkaSimAuth";

    /* renamed from: a, reason: collision with root package name */
    private final Context f14824a;
    private final String b;

    public lpc(Context context, String str) {
        this.f14824a = context;
        this.b = str;
    }

    private byte[] e(String str) {
        if (str != null && loq.b.booleanValue()) {
            loq.c(e, "Get rand and autn--data =" + str + "  dataLength=" + str.length());
        }
        byte[] decode = Base64.decode(str, 0);
        if (decode.length < 48) {
            loq.c(e, "bytesData len = " + decode.length + ", illegal.");
            return new byte[0];
        }
        byte[] bArr = new byte[16];
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = decode[i + 12];
            bArr2[i] = decode[i + 32];
        }
        return lpg.d(bArr, bArr2);
    }

    private String a(String str, int i) {
        if (str != null && this.f14824a != null) {
            byte[] e2 = e(str);
            if (e2.length < 32) {
                loq.c(e, "auth failed,data == null or wrong dataLength");
            } else {
                byte[] bArr = new byte[17];
                byte[] bArr2 = new byte[17];
                int i2 = 0;
                bArr[0] = BaseType.Union;
                bArr2[0] = BaseType.Union;
                while (i2 < 16) {
                    int i3 = i2 + 1;
                    bArr[i3] = e2[i2];
                    bArr2[i3] = e2[i2 + 16];
                    i2 = i3;
                }
                int c = lop.c(this.f14824a, i);
                TelephonyManager telephonyManager = (TelephonyManager) this.f14824a.getSystemService(TelephonyManager.class);
                if (telephonyManager == null) {
                    loq.b(e, "telephonyManagerClass is null");
                    return null;
                }
                TelephonyManager createForSubscriptionId = telephonyManager.createForSubscriptionId(c);
                if (createForSubscriptionId == null) {
                    return null;
                }
                String iccAuthentication = createForSubscriptionId.getIccAuthentication(2, 129, Base64.encodeToString(lpg.d(bArr, bArr2), 2));
                if (!TextUtils.isEmpty(iccAuthentication)) {
                    return iccAuthentication;
                }
                loq.c(e, "devAuth failed,devAuthResponse is empty");
                return null;
            }
        }
        return null;
    }

    public String d(String str, int i) {
        String str2 = e;
        loq.d(str2, "requestDevAkaAuth data = " + str + ", slotId = " + i);
        byte[] decode = Base64.decode(str, 0);
        byte[] e2 = e(decode);
        String a2 = a(str, i);
        loq.d(str2, "b64Result = " + a2);
        if (TextUtils.isEmpty(a2)) {
            return "";
        }
        byte[] b = b(a2);
        byte[] c = c(lpg.a(lpg.d(c(e(i), d(a2), a(a2))), 160));
        if (!c(e2, lnh.e(e(decode, null), c))) {
            loq.c(str2, "checkMac=: false");
            return "";
        }
        byte[] d = d(b, decode[1]);
        byte[] e3 = lnh.e(d, c);
        return Base64.encodeToString(e(d, e3 != null ? Arrays.copyOfRange(e3, 0, 16) : null), 2);
    }

    private boolean c(byte[] bArr, byte[] bArr2) {
        byte[] copyOfRange = bArr2 != null ? Arrays.copyOfRange(bArr2, 0, 16) : null;
        if (bArr != null && copyOfRange != null) {
            loq.c(e, "checkMac is true");
            return Arrays.equals(bArr, copyOfRange);
        }
        loq.c(e, "checkMac is false");
        return false;
    }

    private byte[] e(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return new byte[0];
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length);
        int i = 8;
        while (true) {
            if (i >= bArr.length) {
                break;
            }
            if (bArr[i] != 11) {
                i += bArr[i + 1] * 4;
            } else if (bArr2 == null) {
                Arrays.fill(copyOfRange, i + 4, i + 20, (byte) 0);
            } else {
                for (int i2 = 0; i2 < bArr2.length; i2++) {
                    copyOfRange[i + 4 + i2] = bArr2[i2];
                }
            }
        }
        return copyOfRange;
    }

    public byte[] e(byte[] bArr) {
        if (bArr == null) {
            return new byte[0];
        }
        int i = 8;
        while (i < bArr.length) {
            if (bArr[i] == 11) {
                return Arrays.copyOfRange(bArr, i + 4, i + 20);
            }
            i += bArr[i + 1] * 4;
        }
        return new byte[0];
    }

    private byte[] d(byte[] bArr, byte b) {
        byte[] bArr2 = new byte[20];
        bArr2[0] = BaseType.Float;
        bArr2[1] = 5;
        int length = bArr.length;
        byte[] bArr3 = new byte[length + 4];
        bArr3[0] = 3;
        bArr3[1] = (byte) ((length + 7) / 4);
        byte[] e2 = lpg.e(bArr.length * 8);
        bArr3[2] = e2[2];
        bArr3[3] = e2[3];
        System.arraycopy(bArr, 0, bArr3, 4, bArr.length);
        byte[] e3 = lpg.e((bArr2[1] * 4) + 8 + (bArr3[1] * 4));
        return lpg.d(new byte[]{2, b, e3[2], e3[3], 23, 1, 0, 0}, lpg.d(bArr3, bArr2));
    }

    public byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            loq.c(e, "Process response failed, authResult is empty, return");
            return new byte[0];
        }
        byte[] decode = Base64.decode(str, 0);
        String str2 = e;
        loq.c(str2, "AKA-auth byteAuthResult.length=" + decode.length + "byteAuthResult[0]=" + ((int) decode[0]));
        if (decode.length > 4 && decode[0] == -37) {
            loq.c(str2, "AKA-auth success");
            int i = decode[1] + 2;
            if (i >= decode.length) {
                loq.c(str2, " bad response, no CK data");
                return new byte[0];
            }
            if (decode[i] + i >= decode.length) {
                loq.c(str2, " bad response, no IK data");
                return new byte[0];
            }
            return Arrays.copyOfRange(decode, 2, i);
        }
        if (decode[0] == -36 && decode.length >= 2) {
            loq.c(str2, "SQN Synchronisation failure");
            return new byte[0];
        }
        loq.d(str2, "unexpected condition.");
        return new byte[0];
    }

    public byte[] d(String str) {
        if (TextUtils.isEmpty(str)) {
            loq.c(e, "hexAuthResponse is null ,return");
            return new byte[0];
        }
        byte[] decode = Base64.decode(str, 0);
        if (decode.length > 4 && decode[0] == -37) {
            byte b = decode[1];
            int i = b + 2;
            if (i >= decode.length) {
                loq.c(e, " bad response,no CK data");
                return new byte[0];
            }
            byte b2 = decode[i];
            if (i + b2 >= decode.length) {
                loq.c(e, " bad response,no IK data");
                return new byte[0];
            }
            int i2 = b + 3;
            return Arrays.copyOfRange(decode, i2, b2 + i2);
        }
        if (decode[0] == -36 && decode.length >= 2) {
            loq.c(e, "SQN Synchronisation failure");
            return new byte[0];
        }
        loq.d(e, "unexpected condition.");
        return new byte[0];
    }

    public byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            loq.c(e, "hexAuthResponse is null ,return");
            return new byte[0];
        }
        byte[] decode = Base64.decode(str, 0);
        if (decode.length > 4 && decode[0] == -37) {
            byte b = decode[1];
            int i = b + 2;
            if (i >= decode.length) {
                return new byte[0];
            }
            byte b2 = decode[i];
            if (i + b2 >= decode.length) {
                return new byte[0];
            }
            int i2 = b + 3 + b2;
            byte b3 = decode[i2];
            int i3 = i2 + 1;
            return Arrays.copyOfRange(decode, i3, b3 + i3);
        }
        if (decode[0] == -36 && decode.length >= 2) {
            return new byte[0];
        }
        loq.d(e, "unexpected condition.");
        return new byte[0];
    }

    private byte[] c(String str, byte[] bArr, byte[] bArr2) {
        try {
            return lpg.a(lpg.d(lpg.d(str.getBytes("UTF-8"), bArr2), bArr));
        } catch (UnsupportedEncodingException unused) {
            return new byte[0];
        }
    }

    private byte[] c(byte[] bArr) {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 16, 32);
        loq.c(e, "bytesAuthKey.length = " + copyOfRange.length + "bytesAuthKey = " + lnh.b(copyOfRange));
        return copyOfRange;
    }

    public String e(int i) {
        Context context = this.f14824a;
        if (context != null) {
            int c = lop.c(context, i);
            TelephonyManager telephonyManager = (TelephonyManager) this.f14824a.getSystemService(TelephonyManager.class);
            if (telephonyManager == null) {
                loq.b(e, "telephonyManagerClass is null");
                return "";
            }
            TelephonyManager createForSubscriptionId = telephonyManager.createForSubscriptionId(c);
            if (createForSubscriptionId == null) {
                loq.b(e, "telephonyManager is null");
                return "";
            }
            String simOperator = createForSubscriptionId.getSimOperator();
            if (TextUtils.isEmpty(simOperator) || simOperator.length() < 3) {
                loq.b(e, "mccMnc is invalid");
            } else {
                String substring = simOperator.substring(0, 3);
                String substring2 = simOperator.substring(3);
                if (substring2.length() == 3) {
                    return "0" + this.b + "@nai.epc.mnc" + substring2 + ".mcc" + substring + ".3gppnetwork.org";
                }
                return "0" + this.b + "@nai.epc.mnc0" + substring2 + ".mcc" + substring + ".3gppnetwork.org";
            }
        }
        return "";
    }
}
