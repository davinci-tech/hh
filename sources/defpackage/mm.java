package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public final class mm {
    public static String b = "idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#";

    public static byte[] e() {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 48; i += 2) {
                sb.append("AsAgAtA5A6AdAgABABACADAfAsAdAfAsAgAaAgA3A5A6=8=0".charAt(i));
            }
            return mk.c(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str, String str2) {
        byte[] doFinal;
        try {
            PBEKeySpec c = c(str);
            int length = str2.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = Integer.valueOf(str2.substring(i2, i2 + 2), 16).byteValue();
            }
            byte[] e = e();
            if (length <= 16) {
                doFinal = null;
            } else {
                SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(c.getPassword(), Arrays.copyOf(bArr, 16), 10, 128)).getEncoded(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(2, secretKeySpec, new IvParameterSpec(e));
                doFinal = cipher.doFinal(bArr, 16, length - 16);
            }
        } catch (Exception unused) {
        }
        if (doFinal == null) {
            throw new Exception();
        }
        String str3 = new String(doFinal);
        if (mq.d(str3)) {
            return str3;
        }
        return null;
    }

    public static PBEKeySpec c(String str) {
        Class<?> cls = Class.forName(new String(mk.c("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object newInstance = cls.newInstance();
        byte[] bArr = new byte[16];
        Method method = cls.getMethod("nextBytes", bArr.getClass());
        method.setAccessible(true);
        method.invoke(newInstance, bArr);
        return new PBEKeySpec(str.toCharArray(), bArr, 10, 128);
    }

    public static String e(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b2 >> 4) & 15)).append("0123456789ABCDEF".charAt(b2 & BaseType.Obj));
        }
        return stringBuffer.toString();
    }

    public static String c(String str, String str2) {
        try {
            PBEKeySpec c = c(str);
            byte[] bytes = str2.getBytes();
            byte[] e = e();
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(c).getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(e));
            byte[] salt = c.getSalt();
            ByteBuffer allocate = ByteBuffer.allocate(salt.length + cipher.getOutputSize(bytes.length));
            allocate.put(salt);
            cipher.doFinal(ByteBuffer.wrap(bytes), allocate);
            return e(allocate.array());
        } catch (Exception unused) {
            return null;
        }
    }

    public static String b() {
        String str = new String();
        for (int i = 0; i < b.length() - 1; i += 4) {
            str = str + b.charAt(i);
        }
        return str;
    }
}
