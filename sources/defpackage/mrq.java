package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes8.dex */
public class mrq {
    public static int b(int i) {
        if (i == 9) {
            return R.string._2130847848_res_0x7f022868;
        }
        switch (i) {
            case 19:
                return R.string._2130847846_res_0x7f022866;
            case 20:
            case 21:
            case 22:
                return R.string._2130847847_res_0x7f022867;
            default:
                return R.string._2130847845_res_0x7f022865;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int c(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1629) {
            if (str.equals(HealthZonePushReceiver.SLEEP_SCORE_NOTIFY)) {
                c = 5;
            }
            c = 65535;
        } else if (hashCode == 1660) {
            if (str.equals(HealthZonePushReceiver.FAMILY_CARE_NOTIFY)) {
                c = 6;
            }
            c = 65535;
        } else if (hashCode == 1753) {
            if (str.equals("70")) {
                c = '\t';
            }
            c = 65535;
        } else if (hashCode == 1784) {
            if (str.equals("80")) {
                c = '\n';
            }
            c = 65535;
        } else if (hashCode == 1815) {
            if (str.equals("90")) {
                c = 11;
            }
            c = 65535;
        } else if (hashCode == 48625) {
            if (str.equals("100")) {
                c = '\f';
            }
            c = 65535;
        } else if (hashCode == 48656) {
            if (str.equals(MessageConstant.CERTIFICATE)) {
                c = '\r';
            }
            c = 65535;
        } else if (hashCode == 1691) {
            if (str.equals("50")) {
                c = 7;
            }
            c = 65535;
        } else if (hashCode != 1692) {
            switch (hashCode) {
                case 1571:
                    if (str.equals("14")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1572:
                    if (str.equals("15")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1573:
                    if (str.equals("16")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1574:
                    if (str.equals("17")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1575:
                    if (str.equals("18")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals("51")) {
                c = '\b';
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return R.mipmap._2131821062_res_0x7f110206;
            case 1:
                return R.mipmap._2131821053_res_0x7f1101fd;
            case 2:
                return R.mipmap._2131820856_res_0x7f110138;
            case 3:
                return R.mipmap._2131820933_res_0x7f110185;
            case 4:
                return R.mipmap._2131821008_res_0x7f1101d0;
            case 5:
                return R.mipmap._2131820810_res_0x7f11010a;
            case 6:
                return R.mipmap._2131820852_res_0x7f110134;
            case 7:
                return R.mipmap._2131821107_res_0x7f110233;
            case '\b':
                return R.mipmap._2131820901_res_0x7f110165;
            case '\t':
                return R.mipmap._2131821009_res_0x7f1101d1;
            case '\n':
                return R.mipmap._2131820817_res_0x7f110111;
            case 11:
                return R.mipmap._2131820818_res_0x7f110112;
            case '\f':
                return R.mipmap._2131820809_res_0x7f110109;
            case '\r':
                return R.mipmap._2131820847_res_0x7f11012f;
            default:
                return R.mipmap._2131821041_res_0x7f1101f1;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int d(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 1629) {
            if (str.equals(HealthZonePushReceiver.SLEEP_SCORE_NOTIFY)) {
                c = 6;
            }
            c = 65535;
        } else if (hashCode == 1660) {
            if (str.equals(HealthZonePushReceiver.FAMILY_CARE_NOTIFY)) {
                c = 7;
            }
            c = 65535;
        } else if (hashCode == 1753) {
            if (str.equals("70")) {
                c = '\n';
            }
            c = 65535;
        } else if (hashCode == 1784) {
            if (str.equals("80")) {
                c = 11;
            }
            c = 65535;
        } else if (hashCode == 1815) {
            if (str.equals("90")) {
                c = '\f';
            }
            c = 65535;
        } else if (hashCode == 48625) {
            if (str.equals("100")) {
                c = '\r';
            }
            c = 65535;
        } else if (hashCode == 48656) {
            if (str.equals(MessageConstant.CERTIFICATE)) {
                c = 14;
            }
            c = 65535;
        } else if (hashCode == 1691) {
            if (str.equals("50")) {
                c = '\b';
            }
            c = 65535;
        } else if (hashCode != 1692) {
            switch (hashCode) {
                case 1571:
                    if (str.equals("14")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1572:
                    if (str.equals("15")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1573:
                    if (str.equals("16")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1574:
                    if (str.equals("17")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1575:
                    if (str.equals("18")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1576:
                    if (str.equals("19")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals("51")) {
                c = '\t';
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return R.string._2130843304_res_0x7f0216a8;
            case 1:
                return R.string._2130848489_res_0x7f022ae9;
            case 2:
                return R.string._2130843307_res_0x7f0216ab;
            case 3:
                return R.string._2130842559_res_0x7f0213bf;
            case 4:
                return R.string._2130842510_res_0x7f02138e;
            case 5:
            default:
                return R.string._2130843308_res_0x7f0216ac;
            case 6:
                return R.string._2130841426_res_0x7f020f52;
            case 7:
                return R.string._2130843305_res_0x7f0216a9;
            case '\b':
                return R.string._2130843306_res_0x7f0216aa;
            case '\t':
                return R.string._2130847845_res_0x7f022865;
            case '\n':
                return R.string._2130845630_res_0x7f021fbe;
            case 11:
                return R.string._2130845025_res_0x7f021d61;
            case '\f':
                return R.string._2130845356_res_0x7f021eac;
            case '\r':
                return R.string._2130845609_res_0x7f021fa9;
            case 14:
                return R.string._2130847595_res_0x7f02276b;
        }
    }

    public static String c(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        if (i < 0 || i2 < 0 || length <= i + i2) {
            return "***";
        }
        StringBuilder sb = new StringBuilder(str);
        sb.replace(i, length - i2, "***");
        return sb.toString();
    }

    public static List<MessageObject> c(List<MessageObject> list) {
        Collections.sort(list, new Comparator<MessageObject>() { // from class: mrq.3
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(MessageObject messageObject, MessageObject messageObject2) {
                return Long.compare(messageObject.getReceiveTime(), messageObject2.getReceiveTime());
            }
        });
        return list;
    }

    public static List<MessageObject> d(List<MessageObject> list) {
        Collections.sort(list, new Comparator<MessageObject>() { // from class: mrq.5
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(MessageObject messageObject, MessageObject messageObject2) {
                return Long.compare(messageObject2.getReceiveTime(), messageObject.getReceiveTime());
            }
        });
        return list;
    }
}
