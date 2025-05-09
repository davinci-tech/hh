package defpackage;

import com.huawei.bone.ui.setting.voip.VoipCallHandle;

/* loaded from: classes8.dex */
public class bdp {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static VoipCallHandle e(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1897170512:
                if (str.equals("org.telegram.messenger")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1547699361:
                if (str.equals("com.whatsapp")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1521143749:
                if (str.equals("jp.naver.line.android")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -662003450:
                if (str.equals("com.instagram.android")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 908140028:
                if (str.equals("com.facebook.orca")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                return new bdv();
            }
            if (c != 2 && c != 3) {
                if (c == 4) {
                    return new bdq();
                }
                return new bdt();
            }
        }
        return new bdr();
    }
}
