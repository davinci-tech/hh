package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.model.SampleEvent;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class nbt {
    private ArrayList<String> b = new ArrayList<>(16);

    private boolean a(char c) {
        if (('0' > c || c > '9') && ('A' > c || c > 'Z')) {
            return ('a' <= c && c <= 'z') || '-' == c;
        }
        return true;
    }

    private void a(String str) {
        boolean contains = str.contains(SampleEvent.SEPARATOR);
        while (contains) {
            int indexOf = str.indexOf(SampleEvent.SEPARATOR);
            this.b.add(indexOf > 0 ? str.substring(0, indexOf) : "");
            str = str.substring(indexOf + 1);
            contains = str.contains(SampleEvent.SEPARATOR);
        }
        this.b.add(str);
    }

    public boolean e(String str) {
        LogUtil.a("QrCodeDataParse", "isQrCodeInvalid enter");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("QrCodeDataParse", "qrCode is empty");
            return true;
        }
        if (str.startsWith("LPA:")) {
            str = str.substring(4);
        }
        int length = str.length();
        LogUtil.c("QrCodeDataParse", "length:", Integer.valueOf(length), "result:", str);
        if (length <= 255 && str.startsWith("1$")) {
            a(str);
            LogUtil.a("QrCodeDataParse", "qrElementList:", this.b.toString());
            if (this.b.size() <= 5 && this.b.size() >= 3) {
                return (f(this.b.get(1)) && c(this.b.get(2))) ? false : true;
            }
            LogUtil.h("QrCodeDataParse", "qrCode is Invalid");
        }
        return true;
    }

    private boolean f(String str) {
        char charAt;
        String i = i(str);
        if (TextUtils.isEmpty(i) || !i.contains(".")) {
            return false;
        }
        for (int i2 = 0; i2 < i.length(); i2++) {
            char charAt2 = i.charAt(i2);
            if (charAt2 > '~' || charAt2 < '!') {
                return false;
            }
        }
        char charAt3 = i.charAt(0);
        return (charAt3 == '-' || charAt3 == '_' || charAt3 == '.' || (charAt = i.charAt(i.length() - 1)) == '-' || charAt == '_' || charAt == '.') ? false : true;
    }

    private boolean c(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean b(String str) {
        return h(d(str));
    }

    private String d(String str) {
        LogUtil.a("QrCodeDataParse", "getConfirmCode enter");
        if (e(str)) {
            return null;
        }
        if (this.b.size() != 5) {
            LogUtil.c("QrCodeDataParse", "element length is not equal max length");
            return null;
        }
        if (koq.d(this.b, 4)) {
            return this.b.get(4);
        }
        return null;
    }

    private boolean h(String str) {
        if (str == null) {
            return false;
        }
        return "1".equals(str);
    }

    private String i(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length() - 1;
        int i = 0;
        LogUtil.c("QrCodeDataParse", "start:", 0, "; lastElementIndex:", Integer.valueOf(length));
        while (i <= length && str.charAt(i) == ' ') {
            i++;
        }
        int i2 = length;
        while (i2 >= i && str.charAt(i2) == ' ') {
            i2--;
        }
        LogUtil.c("QrCodeDataParse", "start:", Integer.valueOf(i), "; end:", Integer.valueOf(i2));
        if (i == 0 && i2 == length) {
            return str;
        }
        if (i <= i2) {
            return str.substring(i, i2 + 1);
        }
        return null;
    }
}
