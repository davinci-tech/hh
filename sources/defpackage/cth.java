package defpackage;

import com.huawei.operation.utils.Constants;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class cth extends ctc {
    private static final long serialVersionUID = 6242432055193497137L;

    /* renamed from: a, reason: collision with root package name */
    private List<c> f11459a;
    private String b;
    private String c;
    private int d;
    private a e;

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public a a() {
        return this.e;
    }

    public void b(a aVar) {
        this.e = aVar;
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public void d(List<c> list) {
        this.f11459a = list;
    }

    public static class a implements Serializable {
        private static final long serialVersionUID = -563906258663973150L;

        /* renamed from: a, reason: collision with root package name */
        private String f11460a;
        private String b;
        private String c;
        private String d;
        private String e;
        private Integer f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;

        public String d() {
            return this.g;
        }

        public void g(String str) {
            this.g = str;
        }

        public void j(String str) {
            this.h = str;
        }

        public String e() {
            return this.f11460a;
        }

        public void e(String str) {
            this.f11460a = str;
        }

        public String b() {
            return this.i;
        }

        public void h(String str) {
            this.i = str;
        }

        public String c() {
            return this.j;
        }

        public void i(String str) {
            this.j = str;
        }

        public void c(String str) {
            this.b = str;
        }

        public String a() {
            return this.e;
        }

        public void a(String str) {
            this.e = str;
        }

        public void b(String str) {
            this.d = str;
        }

        public void d(String str) {
            this.c = str;
        }

        public void f(String str) {
            this.k = str;
        }

        public void d(Integer num) {
            this.f = num;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("DevInfo{sn =");
            String str = this.g;
            Object obj = Constants.NULL;
            stringBuffer.append(str == null ? Constants.NULL : cpw.d(str)).append("'model =");
            String str2 = this.h;
            if (str2 == null) {
                str2 = Constants.NULL;
            }
            stringBuffer.append(str2).append("'devType =");
            String str3 = this.f11460a;
            if (str3 == null) {
                str3 = Constants.NULL;
            }
            stringBuffer.append(str3).append("'prodId =");
            String str4 = this.i;
            stringBuffer.append(str4 == null ? Constants.NULL : cpw.d(str4)).append("'hiv =");
            String str5 = this.b;
            if (str5 == null) {
                str5 = Constants.NULL;
            }
            stringBuffer.append(str5).append("'mac =");
            String str6 = this.e;
            stringBuffer.append(str6 == null ? Constants.NULL : cpw.d(str6)).append("'fwv =");
            String str7 = this.d;
            if (str7 == null) {
                str7 = Constants.NULL;
            }
            stringBuffer.append(str7).append("'hwv =");
            String str8 = this.c;
            if (str8 == null) {
                str8 = Constants.NULL;
            }
            stringBuffer.append(str8).append("'swv =");
            String str9 = this.k;
            if (str9 == null) {
                str9 = Constants.NULL;
            }
            stringBuffer.append(str9).append("'protType =");
            Integer num = this.f;
            if (num != null) {
                obj = num;
            }
            stringBuffer.append(obj).append("'}");
            return stringBuffer.toString();
        }
    }

    public static class c implements Serializable {
        private static final long serialVersionUID = 2699365370799290470L;
        private String b;
        private String c;

        public void e(String str) {
            this.b = str;
        }

        public void d(String str) {
            this.c = str;
        }
    }

    @Override // defpackage.ctc
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CoapDiscoverDeviceEntityModel{errorCode =");
        stringBuffer.append(b());
        StringBuffer append = stringBuffer.append("devInfo =");
        Object obj = this.e;
        Object obj2 = Constants.NULL;
        if (obj == null) {
            obj = Constants.NULL;
        }
        append.append(obj).append("'mode =");
        stringBuffer.append(this.d).append("'services =");
        List<c> list = this.f11459a;
        if (list != null) {
            obj2 = list;
        }
        stringBuffer.append(obj2).append("'}");
        return stringBuffer.toString();
    }
}
