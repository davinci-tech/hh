package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.m.u.c;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.OsType;
import defpackage.kl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class kn {

    /* renamed from: a, reason: collision with root package name */
    public String f14437a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f = "";
    public String g = "";
    public String h;
    public String i;
    public String j;

    public kn(Context context, boolean z) {
        context = context != null ? context.getApplicationContext() : context;
        this.f14437a = e();
        this.b = c(context);
        this.e = c(z ? 0L : kl.a.e(context));
        this.c = b();
        this.j = b(context);
        this.i = Constants.LINK;
        this.h = Constants.LINK;
    }

    public static String d() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "12345678uuid";
        }
    }

    private void e(String str, String str2, String str3) {
        synchronized (this) {
            ma.d("mspl", String.format("event %s %s %s", str, str2, str3));
            String str4 = TextUtils.isEmpty(this.f) ? "" : "^";
            StringBuilder sb = new StringBuilder();
            sb.append(str4);
            Object[] objArr = new Object[4];
            objArr[0] = TextUtils.isEmpty(str) ? Constants.LINK : b(str);
            objArr[1] = b(str2);
            objArr[2] = b(str3);
            objArr[3] = b(c());
            sb.append(String.format("%s,%s,%s,-,-,-,-,-,-,-,-,-,-,%s", objArr));
            this.f += sb.toString();
        }
    }

    public void b(String str, String str2, Throwable th) {
        b(str, str2, b(th));
    }

    public void d(String str, String str2, String str3) {
        b(str, str2, str3);
    }

    private void b(String str, String str2, String str3) {
        synchronized (this) {
            ma.b("mspl", String.format("err %s %s %s", str, str2, str3));
            String str4 = TextUtils.isEmpty(this.g) ? "" : "^";
            StringBuilder sb = new StringBuilder();
            sb.append(str4);
            Object[] objArr = new Object[4];
            objArr[0] = str;
            objArr[1] = str2;
            objArr[2] = TextUtils.isEmpty(str3) ? Constants.LINK : b(str3);
            objArr[3] = b(c());
            sb.append(String.format("%s,%s,%s,%s", objArr));
            this.g += sb.toString();
        }
    }

    public static String e() {
        return String.format("%s,%s", d(), new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    public void c(String str, String str2, Throwable th, String str3) {
        b(str, str2, str3 + ": " + b(th));
    }

    public static String c(String str) {
        String str2;
        String str3;
        if (str == null) {
            str = "";
        }
        String[] split = str.split("&");
        String str4 = null;
        if (split != null) {
            str3 = null;
            String str5 = null;
            for (String str6 : split) {
                String[] split2 = str6.split("=");
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase("partner")) {
                        str4 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("out_trade_no")) {
                        str3 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("trade_no")) {
                        str5 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase("biz_content")) {
                        try {
                            JSONObject jSONObject = new JSONObject(md.c(lv.c(), split2[1]));
                            if (TextUtils.isEmpty(str3)) {
                                str3 = jSONObject.getString("out_trade_no");
                            }
                        } catch (Throwable unused) {
                        }
                    } else if (split2[0].equalsIgnoreCase("app_id") && TextUtils.isEmpty(str4)) {
                        str4 = split2[1];
                    }
                }
            }
            str2 = str4;
            str4 = str5;
        } else {
            str2 = null;
            str3 = null;
        }
        return String.format("%s,%s,-,%s,-,-,-", b(str4), b(str3), b(str2));
    }

    public void c(String str, String str2, String str3) {
        e("", str, str2 + "|" + str3);
    }

    public void e(String str, String str2) {
        e("", str, str2);
    }

    public static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                int i = 0;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString()).append(" 》 ");
                    i++;
                    if (i > 5) {
                        break;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return stringBuffer.toString();
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? Constants.LINK : str;
    }

    public static String c() {
        return new SimpleDateFormat("HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace(com.huawei.operation.utils.Constants.LEFT_BRACKET_ONLY, "（").replace(com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY, "）").replace(",", "，").replace("^", AwarenessConstants.SECOND_ACTION_SPLITE_TAG).replace("#", "＃");
    }

    public String d(String str) {
        String c = c(str);
        this.d = c;
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.f14437a, c, this.b, this.e, this.c, this.j, this.i, a(this.f), a(this.g), this.h);
    }

    public static String c(Context context) {
        String str;
        String str2 = Constants.LINK;
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                String packageName = applicationContext.getPackageName();
                try {
                    PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(packageName, 64);
                    str2 = packageInfo.versionName + "|" + aU_(packageInfo);
                } catch (Throwable unused) {
                }
                str = str2;
                str2 = packageName;
            } catch (Throwable unused2) {
            }
            return String.format("%s,%s,-,-,-", b(str2), b(str));
        }
        str = Constants.LINK;
        return String.format("%s,%s,-,-,-", b(str2), b(str));
    }

    public static String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", b(c.e(context)), OsType.ANDROID, b(Build.VERSION.RELEASE), b(Build.MODEL), Constants.LINK, "0", b(c.c(context).b()), "gw", b(me.a(null, context)));
    }

    public static String aU_(PackageInfo packageInfo) {
        Signature[] signatureArr;
        String str;
        String d;
        if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length == 0) {
            return "0";
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.signatures.length);
            for (Signature signature : packageInfo.signatures) {
                try {
                    d = md.d((lv) null, signature.toByteArray());
                } catch (Throwable unused) {
                }
                if (TextUtils.isEmpty(d)) {
                    str = "?";
                    sb.append(Constants.LINK);
                    sb.append(str);
                } else {
                    str = md.h(d).substring(0, 8);
                    sb.append(Constants.LINK);
                    sb.append(str);
                }
            }
            return sb.toString();
        } catch (Throwable unused2) {
            return "?";
        }
    }

    public static String c(long j) {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,%s,-", b("15.8.14"), b("h.a.3.8.14"), AwarenessConstants.SECOND_ACTION_SPLITE_TAG + j);
    }

    public static String b() {
        return String.format("%s,%s,-,-,-", b(lu.b(lw.c().d()).a()), b(lw.c().e()));
    }
}
