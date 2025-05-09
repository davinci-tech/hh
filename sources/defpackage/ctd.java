package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.health.device.wifi.entity.builder.BaseBuilder;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/* loaded from: classes3.dex */
public class ctd extends BaseBuilder {
    private static final long serialVersionUID = 7967385940897486810L;
    private ctj e;
    private int j;
    private Long m;
    private String o;

    /* renamed from: a, reason: collision with root package name */
    private byte[] f11456a = new byte[0];
    private byte[] h = new byte[16];
    private byte[] i = new byte[16];
    private byte[] f = null;
    private byte[] g = null;
    private byte[] n = null;
    private byte[] b = {67, 2};
    private byte[] d = {-70, 99, 108, 111, 117, 100, 83, 101, 116, 117, 112};
    private byte[] c = {BaseType.Array, 50};

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public ctc makeResponseEntity(String str) {
        return null;
    }

    static {
        cty.b();
    }

    public ctd(ctj ctjVar, String str, Long l, int i) {
        this.e = null;
        this.o = "";
        super.setUri("/cloudSetup");
        super.setDefaultTime(PreConnectManager.CONNECT_INTERNAL);
        this.e = ctjVar;
        this.o = str;
        this.m = l;
        this.j = i;
        cpw.c(true, "CoapIdentifyCodeBuilder", "mOptSeq: ", Integer.valueOf(i));
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public byte[] makeRequestByte() {
        return a();
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public ctc makeResponseEntity(byte[] bArr) {
        ctj ctjVar = new ctj();
        if (bArr == null || bArr.length < 32) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "first makeResponseEntity para is not right!");
            return ctjVar;
        }
        if (this.h == null || this.i == null) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "second makeResponseEntity para is not right!");
            return ctjVar;
        }
        cpw.c(true, "CoapIdentifyCodeBuilder", "makeResponseEntity: ", ctv.a(bArr));
        try {
            int length = bArr.length - 32;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            String b = ctu.b(bArr2, this.h, this.i);
            cpw.c(true, "CoapIdentifyCodeBuilder", "decrypt String: ", b);
            return c(b, ctjVar);
        } catch (UnsupportedEncodingException e) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "makeResponseEntity: ", e.getMessage());
            return ctjVar;
        } catch (NumberFormatException e2) {
            cpw.e(true, "CoapIdentifyCodeBuilder", e2.getMessage());
            return ctjVar;
        } catch (InvalidAlgorithmParameterException e3) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "makeResponseEntity: ", e3.getMessage());
            return ctjVar;
        } catch (InvalidKeyException e4) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "makeResponseEntity: ", e4.getMessage());
            return ctjVar;
        } catch (BadPaddingException e5) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "makeResponseEntity: ", e5.getMessage());
            return ctjVar;
        } catch (IllegalBlockSizeException e6) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "makeResponseEntity: ", e6.getMessage());
            return ctjVar;
        }
    }

    private ctc c(String str, ctj ctjVar) {
        Map<String, Object> c = JsonParser.c(str);
        if (c.isEmpty()) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "jsonMap is isEmpty!");
            return ctjVar;
        }
        cpw.c(true, "CoapIdentifyCodeBuilder", "jsonMap ", c.toString());
        if (c.containsKey("errcode")) {
            ctjVar.c(nsn.e(c.get("errcode").toString()));
        } else {
            cpw.e(true, "CoapIdentifyCodeBuilder", "ERROR_CODE is null!");
        }
        return ctjVar;
    }

    public void e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "snAppRandom1 or snDeviceRandom is not right!");
            return;
        }
        if (str.length() != 16 || str2.length() != 16) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "snAppRandom1 or snDeviceRandom length is not right!");
            return;
        }
        byte[] d = ctv.d(str);
        byte[] d2 = ctv.d(str2);
        if (d.length == 0 || d2.length == 0) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "bSn1 or bSn2 is null! ");
            return;
        }
        byte[] bArr = new byte[d.length + d2.length];
        this.f = bArr;
        System.arraycopy(d, 0, bArr, 0, d.length);
        System.arraycopy(d2, 0, this.f, d.length, d2.length);
        byte[] bArr2 = new byte[0];
        try {
            bArr2 = ctv.e(this.f, 1, 32);
        } catch (InvalidKeyException e) {
            cpw.c(true, "CoapIdentifyCodeBuilder", e.getMessage());
        } catch (NoSuchAlgorithmException e2) {
            cpw.c(true, "CoapIdentifyCodeBuilder", e2.getMessage());
        }
        if (bArr2.length != 32) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "get digest error! ");
            return;
        }
        byte[] bArr3 = this.h;
        System.arraycopy(bArr2, 0, bArr3, 0, bArr3.length);
        int length = this.h.length;
        byte[] bArr4 = this.i;
        System.arraycopy(bArr2, length, bArr4, 0, bArr4.length);
    }

    public byte[] a() {
        if (this.e == null || this.h == null) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "mEntityModel or mPskKey is empty!");
            return this.f11456a;
        }
        if (this.i == null || this.f == null) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "mPskIv or mSalt is empty!");
            return this.f11456a;
        }
        HashMap hashMap = new HashMap(16);
        if (this.e.c() != null) {
            hashMap.put("code", this.e.c());
        }
        if (this.e.d() != null) {
            hashMap.put("devId", this.e.d());
        }
        if (this.e.a() != null) {
            hashMap.put("psk", this.e.a());
        }
        if (this.e.e() != null) {
            hashMap.put("cloudUrl", this.e.e());
        }
        String jSONObject = JsonParser.b(hashMap).toString();
        cpw.c(true, "CoapIdentifyCodeBuilder", "data = ", jSONObject);
        return c(jSONObject);
    }

    private byte[] c(String str) {
        try {
            byte[] c = ctu.c(str, this.h, this.i);
            if (c == null) {
                cpw.e(true, "CoapIdentifyCodeBuilder", "encryptResult is null!");
                return this.f11456a;
            }
            return a(cua.d(this.h, this.f, 1, 32), c);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException e) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "makeRequestStream: ", e.getMessage());
            return this.f11456a;
        }
    }

    private byte[] a(byte[] bArr, byte[] bArr2) {
        if (bArr.length == 0) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "digest1 is null!");
            return this.f11456a;
        }
        cpw.c(true, "CoapIdentifyCodeBuilder", "digest1 = ", ctv.a(bArr));
        byte[] c = c(bArr2);
        if (c == null) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "macCant is null!");
            return this.f11456a;
        }
        byte[] b = cua.b(bArr, c);
        if (b == null) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "mac is null!");
            return this.f11456a;
        }
        cpw.c(true, "CoapIdentifyCodeBuilder", "mac = ", ctv.a(b));
        byte[] bArr3 = new byte[bArr2.length + b.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(b, 0, bArr3, bArr2.length, b.length);
        cpw.c(true, "CoapIdentifyCodeBuilder", "makeRequestStream: ", ctv.a(bArr2) + ctv.a(b));
        return bArr3;
    }

    private byte[] c(byte[] bArr) {
        byte[] bArr2;
        byte[] bArr3;
        if (bArr == null) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "getCoapFrame payload is null!");
            return this.f11456a;
        }
        String str = this.o;
        if (str == null || this.m == null) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "getCoapFrame para is wroing!");
            return this.f11456a;
        }
        int length = str.getBytes(Charset.forName("UTF-8")).length;
        if (length > 16) {
            cpw.e(true, "CoapIdentifyCodeBuilder", "mSessId.len is wrong!");
            return this.f11456a;
        }
        if (length >= 13) {
            bArr2 = new byte[length + 4];
            bArr3 = new byte[]{-19, 6, -25, (byte) (length - 13)};
        } else {
            bArr2 = new byte[length + 3];
            bArr3 = new byte[]{(byte) (length | 224), 6, -25};
        }
        System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
        System.arraycopy(this.o.getBytes(Charset.forName("UTF-8")), 0, bArr2, bArr3.length, length);
        String hexString = Long.toHexString(this.m.longValue() + 1);
        if (hexString.length() % 2 != 0) {
            hexString = "0" + hexString;
        }
        int length2 = hexString.length() / 2;
        byte[] bArr4 = new byte[length2 + 2];
        byte[] d = ctv.d("5" + String.valueOf(length2));
        byte[] d2 = ctv.d(hexString);
        if (d.length != 0 && d2.length != 0) {
            System.arraycopy(d, 0, bArr4, 0, d.length);
            System.arraycopy(d2, 0, bArr4, d.length, length2);
        }
        bArr4[length2 + 1] = -1;
        this.g = ctv.b(2);
        this.n = ctv.b(3);
        return e(bArr2, bArr4, bArr);
    }

    private byte[] e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = this.b;
        byte[] bArr5 = new byte[bArr4.length + this.g.length + this.n.length + this.d.length + this.c.length + bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
        byte[] bArr6 = this.g;
        System.arraycopy(bArr6, 0, bArr5, this.b.length, bArr6.length);
        byte[] bArr7 = this.n;
        System.arraycopy(bArr7, 0, bArr5, this.b.length + this.g.length, bArr7.length);
        byte[] bArr8 = this.d;
        System.arraycopy(bArr8, 0, bArr5, this.b.length + this.g.length + this.n.length, bArr8.length);
        byte[] bArr9 = this.c;
        System.arraycopy(bArr9, 0, bArr5, this.b.length + this.g.length + this.n.length + this.d.length, bArr9.length);
        System.arraycopy(bArr, 0, bArr5, this.b.length + this.g.length + this.n.length + this.d.length + this.c.length, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, this.b.length + this.g.length + this.n.length + this.d.length + this.c.length + bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr5, this.b.length + this.g.length + this.n.length + this.d.length + this.c.length + bArr.length + bArr2.length, bArr3.length);
        cpw.c(true, "CoapIdentifyCodeBuilder", "macCant = ", ctv.a(bArr5));
        return bArr5;
    }

    public String b() {
        return this.o;
    }

    public Long c() {
        return this.m;
    }

    public byte[] d() {
        byte[] bArr = this.n;
        return bArr == null ? this.f11456a : (byte[]) bArr.clone();
    }

    public byte[] e() {
        byte[] bArr = this.g;
        return bArr == null ? this.f11456a : (byte[]) bArr.clone();
    }

    @Override // com.huawei.health.device.wifi.entity.builder.BaseBuilder
    public String makeRequestStream() {
        return "";
    }
}
