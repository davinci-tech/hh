package com.huawei.hianalytics.framework;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.storage.IStorageHandler;
import com.huawei.hianalytics.framework.config.CipherType;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.framework.config.ICollectorConfig;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hms.ads.uiengineloader.aj;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Deflater;

/* loaded from: classes4.dex */
public class b {
    public static String a(String str, IMandatoryParameters iMandatoryParameters) {
        if (iMandatoryParameters == null) {
            return "";
        }
        byte[] loadWorkKey = iMandatoryParameters.getLoadWorkKey();
        String cipherType = iMandatoryParameters.getCipherType();
        cipherType.hashCode();
        if (!cipherType.equals(CipherType.AES_GCM)) {
            return !cipherType.equals(CipherType.AES_CBC) ? "" : AesCbc.encrypt(str, loadWorkKey);
        }
        UUID randomUUID = UUID.randomUUID();
        long mostSignificantBits = randomUUID.getMostSignificantBits();
        long leastSignificantBits = randomUUID.getLeastSignificantBits();
        byte[] bArr = new byte[12];
        for (int i = 0; i < 8; i++) {
            int i2 = (7 - i) * 8;
            bArr[i] = (byte) ((mostSignificantBits >>> i2) & 255);
            int i3 = i + 8;
            if (i3 < 12) {
                bArr[i3] = (byte) ((leastSignificantBits >>> i2) & 255);
            }
        }
        return HexUtil.byteArray2HexStr(bArr) + AesGcm.encrypt(str, loadWorkKey, bArr);
    }

    public static ICollectorConfig b(String str) {
        return d.e.b.get(str);
    }

    public static IStorageHandler c(String str) {
        e a2 = a(str);
        if (a2 != null) {
            return a2.f3859a;
        }
        return null;
    }

    public static IStoragePolicy d(String str) {
        return d.e.d.get(str);
    }

    public static String a(String str, String str2) {
        String collectUrl = d.e.b.get(str).getCollectUrl(str2);
        if (TextUtils.isEmpty(collectUrl)) {
            return "";
        }
        return ("maint".equals(str2) ? "{url}/common/hmshimaintqrt" : "{url}/common/hmshioperqrt").replace("{url}", collectUrl);
    }

    public static e a(String str) {
        return d.e.c.get(str);
    }

    public static Field[] a(Class cls) {
        Field[] a2 = cls.getSuperclass() != null ? a(cls.getSuperclass()) : null;
        Field[] declaredFields = cls.getDeclaredFields();
        if (a2 == null || a2.length <= 0) {
            return declaredFields;
        }
        Field[] fieldArr = new Field[declaredFields.length + a2.length];
        System.arraycopy(a2, 0, fieldArr, 0, a2.length);
        System.arraycopy(declaredFields, 0, fieldArr, a2.length, declaredFields.length);
        return fieldArr;
    }

    public static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        Deflater deflater;
        boolean finished;
        byte[] bArr2 = new byte[0];
        Deflater deflater2 = null;
        r2 = null;
        Deflater deflater3 = null;
        Deflater deflater4 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    deflater = new Deflater();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream;
                deflater4 = deflater2;
            }
        } catch (Exception unused2) {
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            deflater.setInput(bArr);
            deflater.finish();
            byte[] bArr3 = new byte[1024];
            while (true) {
                finished = deflater.finished();
                if (finished) {
                    break;
                }
                byteArrayOutputStream.write(bArr3, 0, deflater.deflate(bArr3));
            }
            bArr2 = byteArrayOutputStream.toByteArray();
            deflater.end();
            deflater2 = finished;
        } catch (Exception unused3) {
            deflater3 = deflater;
            HiLog.e(aj.f4374a, "getBitZip exception");
            deflater2 = deflater3;
            if (deflater3 != null) {
                deflater3.end();
                deflater2 = deflater3;
            }
            a(byteArrayOutputStream);
            return bArr2;
        } catch (Throwable th3) {
            th = th3;
            deflater4 = deflater;
            if (deflater4 != null) {
                deflater4.end();
            }
            a(byteArrayOutputStream);
            throw th;
        }
        a(byteArrayOutputStream);
        return bArr2;
    }

    public static void a(Boolean bool, ICallback iCallback, IStorageHandler iStorageHandler, Event event, int i) {
        String evtid;
        StringBuilder sb;
        try {
            iStorageHandler.insert(event);
        } catch (Exception e) {
            if (iCallback == null) {
                return;
            }
            if (bool.booleanValue()) {
                evtid = event.getEvtid();
                sb = new StringBuilder("storage fail. Cause: ");
            } else {
                evtid = event.getEvtid();
                sb = new StringBuilder("resultCode is ");
                sb.append(i);
                sb.append(" and storage fail. Cause: ");
            }
            sb.append(e.getMessage());
            iCallback.onResult(evtid, 402, sb.toString());
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
                HiLog.sw(aj.f4374a, "close OutputStream error");
            }
        }
    }

    public static void a(ICallback iCallback, IStorageHandler iStorageHandler, Event event) {
        try {
            iStorageHandler.insert(event);
        } catch (Exception e) {
            if (iCallback == null) {
                return;
            }
            iCallback.onResult(event.getEvtid(), 401, "storage fail. Cause: " + e.getMessage());
        }
    }

    public static Class a(Field field, int i) {
        Class<?> cls;
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        if (actualTypeArguments.length <= i) {
            return null;
        }
        try {
            Type type = actualTypeArguments[i];
            if (type instanceof Class) {
                cls = (Class) type;
            } else {
                String obj = type.toString();
                int indexOf = obj.indexOf("class ");
                if (indexOf < 0) {
                    indexOf = 0;
                }
                int indexOf2 = obj.indexOf(HiDataFilter.DataFilterExpression.LESS_THAN);
                if (indexOf2 < 0) {
                    indexOf2 = obj.length();
                }
                cls = Class.forName(obj.substring(indexOf, indexOf2));
            }
            return cls;
        } catch (Exception unused) {
            HiLog.w("ReflectAPI", "getType Exception");
            return null;
        }
    }

    public static Class a(Field field) {
        int i;
        if (Map.class.isAssignableFrom(field.getType())) {
            i = 1;
        } else {
            if (!List.class.isAssignableFrom(field.getType())) {
                return null;
            }
            i = 0;
        }
        return a(field, i);
    }
}
