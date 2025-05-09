package defpackage;

import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.R$string;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.IllegalFormatConversionException;
import java.util.Locale;

/* loaded from: classes3.dex */
public class bbs {
    private static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            for (String str2 : BaseApplication.e().getResources().getStringArray(R.array._2130968693_res_0x7f040075)) {
                if (TextUtils.equals(str2, str)) {
                    return true;
                }
            }
            return false;
        } catch (Resources.NotFoundException e) {
            LogUtil.b("HealthLife_PlaceholderUtils", "checkPlaceholder exception is ", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    private static String a(String str, String str2, String str3) {
        boolean z;
        String[] split = str3.split("\\.");
        int length = split.length;
        if (length < 2) {
            LogUtil.h("HealthLife_PlaceholderUtils", "getPlaceholderForText length less than two");
            return "";
        }
        String str4 = split[length - 1];
        if (TextUtils.isEmpty(str4)) {
            LogUtil.h("HealthLife_PlaceholderUtils", "getPlaceholderForText name is empty");
            return "";
        }
        String str5 = split[length - 2];
        if ("string".equals(str5)) {
            z = true;
        } else {
            if (!"plurals".equals(str5)) {
                LogUtil.h("HealthLife_PlaceholderUtils", "getPlaceholderForText resource type not exist");
                return "";
            }
            z = false;
        }
        int b = nsf.b(str4, str5, z ? R$string.class : R$plurals.class);
        if (b <= 0) {
            LogUtil.h("HealthLife_PlaceholderUtils", "getPlaceholderForText id ", Integer.valueOf(b));
            return "";
        }
        String e = e(str, str2);
        boolean isEmpty = TextUtils.isEmpty(e);
        Object obj = e;
        if (isEmpty) {
            obj = c(str, str2);
        }
        try {
            boolean equals = "100".equals(str);
            int parseInt = equals ? Integer.parseInt(str2) : 100;
            Resources resources = BaseApplication.e().getResources();
            if (z) {
                Object[] objArr = new Object[1];
                Object obj2 = obj;
                if (equals) {
                    obj2 = Integer.valueOf(parseInt);
                }
                objArr[0] = obj2;
                return resources.getString(b, objArr);
            }
            int e2 = UnitUtil.e(parseInt);
            Object[] objArr2 = new Object[1];
            Object obj3 = obj;
            if (equals) {
                obj3 = Integer.valueOf(parseInt);
            }
            objArr2[0] = obj3;
            return resources.getQuantityString(b, e2, objArr2);
        } catch (Resources.NotFoundException | NumberFormatException | IllegalFormatConversionException e3) {
            LogUtil.b("HealthLife_PlaceholderUtils", "getPlaceholderForText exception is ", LogAnonymous.b(e3));
            return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static String e(String str, String str2) {
        char c;
        try {
            double parseDouble = Double.parseDouble(str2);
            str.hashCode();
            int hashCode = str.hashCode();
            int i = 1;
            int i2 = 0;
            switch (hashCode) {
                case 1567:
                    if (str.equals("10")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1568:
                    if (str.equals("11")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1569:
                    if (str.equals("12")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case 48625:
                            if (str.equals("100")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 48626:
                            if (str.equals("101")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 48627:
                            if (str.equals("102")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
            }
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        if (c != 3) {
                            if (c != 4) {
                                if (c != 5) {
                                    LogUtil.h("HealthLife_PlaceholderUtils", "getPlaceholderTextForNumberFormat default");
                                    return "";
                                }
                                i2 = 2;
                            }
                        }
                        return azi.c(parseDouble, i, i2);
                    }
                    i = 2;
                    i2 = i;
                    return azi.c(parseDouble, i, i2);
                }
                i2 = 1;
            }
            i = 2;
            return azi.c(parseDouble, i, i2);
        } catch (NumberFormatException e) {
            LogUtil.b("HealthLife_PlaceholderUtils", "getPlaceholderForNumberFormat exception is ", LogAnonymous.b((Throwable) e));
            return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String c(String str, String str2) {
        char c;
        String str3;
        str.hashCode();
        int i = 1;
        switch (str.hashCode()) {
            case 49586:
                if (str.equals("200")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 49587:
                if (str.equals(Constants.ERROR_SERVICE_ID_STR)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 49588:
                if (str.equals(Constants.ERROR_UNKNOWN)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            str3 = "yyyyMMdd HH:mm";
            i = 21;
        } else if (c == 1) {
            str3 = "yyyyMMdd";
            i = 20;
        } else {
            if (c != 2) {
                LogUtil.h("HealthLife_PlaceholderUtils", "getPlaceholderTextForTimeFormat default");
                return "";
            }
            str3 = "HH:mm";
        }
        try {
            return UnitUtil.a(new SimpleDateFormat(str3, Locale.ROOT).parse(str2), i);
        } catch (ParseException e) {
            LogUtil.b("HealthLife_PlaceholderUtils", "getPlaceholderForTimeFormat exception is ", LogAnonymous.b((Throwable) e));
            return "";
        }
    }

    public static String b(String str, String str2, String str3) {
        if (!c(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        if (!TextUtils.isEmpty(str3)) {
            String a2 = a(str, str2, str3);
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
        }
        String e = e(str, str2);
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        String c = c(str, str2);
        return !TextUtils.isEmpty(c) ? c : "";
    }
}
