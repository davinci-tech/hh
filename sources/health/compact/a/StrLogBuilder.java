package health.compact.a;

import android.os.Process;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Calendar;

/* loaded from: classes.dex */
class StrLogBuilder {
    private StringBuilder b = new StringBuilder(560);
    private StringBuilder e;

    StrLogBuilder(boolean z) {
        if (z) {
            this.e = new StringBuilder(560);
        }
    }

    public void d(String str) {
        if (this.b.capacity() > 560) {
            this.b = new StringBuilder(560);
        } else {
            StringBuilder sb = this.b;
            sb.delete(0, sb.length());
        }
        StringBuilder sb2 = this.e;
        if (sb2 != null) {
            if (sb2.capacity() > 560) {
                this.e = new StringBuilder(560);
            } else {
                StringBuilder sb3 = this.e;
                sb3.delete(0, sb3.length());
            }
            e(this.e);
            StringBuilder sb4 = this.e;
            sb4.append(str);
            sb4.append("|");
        }
    }

    public int b(String str, String str2, Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return this.b.length();
        }
        if (str != null) {
            this.b.append(str);
        }
        if (str2 != null) {
            StringBuilder sb = this.b;
            sb.append(str2);
            sb.append("|");
        }
        for (Object obj : objArr) {
            this.b.append(obj);
        }
        return this.b.length();
    }

    public String c(int i, int i2) {
        return this.b.substring(i, i2);
    }

    public void d(StringBuffer stringBuffer, String str) {
        StringBuilder sb = this.e;
        if (sb == null || stringBuffer == null) {
            return;
        }
        int length = sb.length();
        if (str != null) {
            StringBuilder sb2 = this.e;
            sb2.append(str);
            sb2.append(System.lineSeparator());
        } else {
            StringBuilder sb3 = this.e;
            sb3.append((CharSequence) this.b);
            sb3.append(System.lineSeparator());
        }
        stringBuffer.append((CharSequence) this.e);
        StringBuilder sb4 = this.e;
        sb4.delete(length, sb4.length());
    }

    private void e(StringBuilder sb) {
        Calendar calendar = Calendar.getInstance();
        sb.append(calendar.get(1));
        c(sb, calendar.get(2) + 1, "", false);
        c(sb, calendar.get(5), Constants.LINK, false);
        c(sb, calendar.get(11), ":", false);
        c(sb, calendar.get(12), ":", false);
        c(sb, calendar.get(13), ".", false);
        c(sb, calendar.get(14), "|", true);
        sb.append(Process.myTid());
        sb.append("|");
    }

    private void c(StringBuilder sb, int i, String str, boolean z) {
        if (z && i < 100) {
            sb.append("0");
        }
        if (i < 10) {
            sb.append("0");
        }
        sb.append(i);
        sb.append(str);
    }
}
