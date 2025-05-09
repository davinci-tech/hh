package defpackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes7.dex */
public final class iv {

    /* renamed from: a, reason: collision with root package name */
    public String f13621a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String j;

    public final String toString() {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + this.d);
        stringBuffer.append("," + this.f13621a);
        stringBuffer.append("," + this.e);
        stringBuffer.append("," + this.b);
        if (mq.e(this.c) || this.c.length() < 20) {
            sb = new StringBuilder(",");
            str = this.c;
        } else {
            sb = new StringBuilder(",");
            str = this.c.substring(0, 20);
        }
        sb.append(str);
        stringBuffer.append(sb.toString());
        if (mq.e(this.f) || this.f.length() < 20) {
            sb2 = new StringBuilder(",");
            str2 = this.f;
        } else {
            sb2 = new StringBuilder(",");
            str2 = this.f.substring(0, 20);
        }
        sb2.append(str2);
        stringBuffer.append(sb2.toString());
        if (mq.e(this.j) || this.j.length() < 20) {
            sb3 = new StringBuilder(",");
            str3 = this.j;
        } else {
            sb3 = new StringBuilder(",");
            str3 = this.j.substring(0, 20);
        }
        sb3.append(str3);
        stringBuffer.append(sb3.toString());
        return stringBuffer.toString();
    }

    public iv(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.d = str;
        this.f13621a = str2;
        this.e = str3;
        this.b = str4;
        this.c = str5;
        this.f = str6;
        this.j = str7;
    }
}
