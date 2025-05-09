package defpackage;

import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import java.util.Locale;

/* loaded from: classes3.dex */
public class bed {
    public static String a() {
        Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;
        if (locale == null) {
            LogUtil.h("Track_MultiLanguageMap", "acquireLanguagePostFix(), locale == null");
            return null;
        }
        return mtj.e(locale);
    }

    public static String b() {
        String b = b(a());
        if (b != null) {
            return b;
        }
        LogUtil.h("Track_MultiLanguageMap", "acquireLanguagePostFix get null");
        return ProfileRequestConstants.X_LANGUAGE_VALUE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String b(String str) {
        char c;
        if (str == null) {
            return null;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1945443668:
                if (str.equals("b+my+Qaag")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3129:
                if (str.equals("az")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3248:
                if (str.equals("eu")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3301:
                if (str.equals("gl")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3414:
                if (str.equals("ka")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3424:
                if (str.equals("kk")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3426:
                if (str.equals("km")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 3459:
                if (str.equals("lo")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW /* 3500 */:
                if (str.equals("my")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 3588:
                if (str.equals("pt")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 3670:
                if (str.equals("si")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 3749:
                if (str.equals("uz")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "my-ZG";
            case 1:
                return "az-AZ";
            case 2:
                return "eu-ES";
            case 3:
                return "gl-ES";
            case 4:
                return "ka-GE";
            case 5:
                return "kk-KZ";
            case 6:
                return "km-KH";
            case 7:
                return "lo-LA";
            case '\b':
                return "my-MM";
            case '\t':
                return "pt-PT";
            case '\n':
                return "si-LK";
            case 11:
                return "uz-UZ";
            default:
                return str;
        }
    }
}
