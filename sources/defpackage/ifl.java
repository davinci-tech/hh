package defpackage;

import android.content.ContentValues;

/* loaded from: classes7.dex */
public class ifl {

    /* renamed from: a, reason: collision with root package name */
    private ContentValues f13339a;

    public boolean c(int i) {
        return i == -1 || i == -2;
    }

    public ifl(ContentValues contentValues) {
        this.f13339a = contentValues;
    }

    public boolean g() {
        return this.f13339a.getAsBoolean("needCheckAppAuth").booleanValue();
    }

    public String i() {
        return this.f13339a.getAsString("packageName");
    }

    public String c() {
        return this.f13339a.getAsString("dataFilter");
    }

    public boolean f() {
        return d("withoutDefaultZero");
    }

    public String j() {
        return this.f13339a.getAsString("metadataFilter");
    }

    public String d() {
        return this.f13339a.getAsString("deleteDeviceUuid");
    }

    public int e() {
        return b("deleteType");
    }

    public int b() {
        return b("deleteLevel");
    }

    public int a() {
        return b("deleteInterval");
    }

    public int h() {
        return b("sequenceDataType");
    }

    public boolean k() {
        return d("lastDayDetail");
    }

    public boolean m() {
        return d("isGroupByTime");
    }

    private int b(String str) {
        if (!this.f13339a.containsKey(str)) {
            return -1;
        }
        if (this.f13339a.getAsInteger(str) == null) {
            return -2;
        }
        return this.f13339a.getAsInteger(str).intValue();
    }

    private boolean d(String str) {
        ContentValues contentValues = this.f13339a;
        if (contentValues == null || !contentValues.containsKey(str) || this.f13339a.getAsBoolean(str) == null) {
            return false;
        }
        return this.f13339a.getAsBoolean(str).booleanValue();
    }

    public static b d(boolean z) {
        return new b(z);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private ContentValues f13340a;

        private b(boolean z) {
            bwX_().put("needCheckAppAuth", Boolean.valueOf(z));
        }

        public b d(String str) {
            bwX_().put("packageName", str);
            return this;
        }

        public b e(String str) {
            bwX_().put("dataFilter", str);
            return this;
        }

        public b a(boolean z) {
            bwX_().put("withoutDefaultZero", Boolean.valueOf(z));
            return this;
        }

        public b a(String str) {
            bwX_().put("metadataFilter", str);
            return this;
        }

        public b c(Integer num) {
            bwX_().put("deleteType", num);
            return this;
        }

        public b b(String str) {
            bwX_().put("deleteDeviceUuid", str);
            return this;
        }

        public b a(Integer num) {
            bwX_().put("deleteLevel", num);
            return this;
        }

        public b d(Integer num) {
            bwX_().put("deleteInterval", num);
            return this;
        }

        public b b(Integer num) {
            bwX_().put("sequenceDataType", num);
            return this;
        }

        public b b(boolean z) {
            bwX_().put("lastDayDetail", Boolean.valueOf(z));
            return this;
        }

        public b c(boolean z) {
            bwX_().put("isGroupByTime", Boolean.valueOf(z));
            return this;
        }

        public ifl b() {
            return new ifl(this.f13340a);
        }

        private ContentValues bwX_() {
            if (this.f13340a == null) {
                this.f13340a = new ContentValues();
            }
            return this.f13340a;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiDataReadOp{");
        stringBuffer.append(this.f13339a);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
