package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.jq;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class kk {
    public static void a(Context context, kd kdVar, String str, int i, int i2, String str2) {
        kdVar.f1251a = it.c(context, str);
        kdVar.d = i;
        kdVar.b = i2;
        kdVar.c = str2;
    }

    public static kd a(WeakReference<kd> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            weakReference = new WeakReference<>(new kd());
        }
        return weakReference.get();
    }

    static byte[] a(jq jqVar, String str) {
        InputStream inputStream;
        Throwable th;
        jq.b bVar;
        byte[] bArr = new byte[0];
        try {
            bVar = jqVar.a(str);
            if (bVar == null) {
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                return bArr;
            }
            try {
                inputStream = bVar.a();
                if (inputStream == null) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    return bArr;
                }
                try {
                    bArr = new byte[inputStream.available()];
                    inputStream.read(bArr);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th6) {
                            th6.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Throwable th7) {
                    th = th7;
                    try {
                        iv.c(th, "sui", "rdS");
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th8) {
                                th8.printStackTrace();
                            }
                        }
                        if (bVar != null) {
                            try {
                                bVar.close();
                            } catch (Throwable th9) {
                                th9.printStackTrace();
                            }
                        }
                        return bArr;
                    } finally {
                    }
                }
            } catch (Throwable th10) {
                th = th10;
                inputStream = null;
            }
        } catch (Throwable th11) {
            inputStream = null;
            th = th11;
            bVar = null;
        }
    }

    public static String a() {
        return ia.a(System.currentTimeMillis());
    }

    public static String a(Context context, hz hzVar) {
        StringBuilder sb = new StringBuilder();
        try {
            String f = hr.f(context);
            sb.append("\"sim\":\"");
            sb.append(f);
            sb.append("\",\"sdkversion\":\"");
            sb.append(hzVar.c());
            sb.append("\",\"product\":\"");
            sb.append(hzVar.a());
            sb.append("\",\"ed\":\"");
            sb.append(hzVar.d());
            sb.append("\",\"nt\":\"");
            sb.append(hr.d(context));
            sb.append("\",\"np\":\"");
            sb.append(hr.b(context));
            sb.append("\",\"mnc\":\"");
            sb.append(hr.c(context));
            sb.append("\",\"ant\":\"");
            sb.append(hr.e(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    public static String a(String str, String str2, int i, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append(",\"timestamp\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"detail\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    public static String a(String str, String str2, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append(",\"timestamp\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\",\"et\":\"1\",\"classname\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"detail\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }
}
