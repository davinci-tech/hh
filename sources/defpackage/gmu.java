package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class gmu implements Serializable {
    private static final long serialVersionUID = 1552433626961149315L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("medical_conditions")
    private String f12868a;

    @SerializedName("allergies")
    private String b;

    @SerializedName("medications")
    private String c;

    @SerializedName("blood_type")
    private int d;

    @SerializedName("emergency_contacts")
    private ArrayList<e> e;

    @SerializedName("name")
    private String f;

    @SerializedName("organ_donor")
    private int i;

    @SerializedName("address")
    private String j;

    public void b(String str) {
        this.f = str;
    }

    public String h() {
        return this.f;
    }

    public String f() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public int e() {
        return this.d;
    }

    public String b() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String a() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.d = i;
    }

    public int g() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public String c() {
        return this.f12868a;
    }

    public void e(String str) {
        this.f12868a = str;
    }

    public ArrayList<e> d() {
        return this.e;
    }

    public void c(ArrayList<e> arrayList) {
        this.e = arrayList;
    }

    public String toString() {
        return "EmergencyInfo{personalName='" + this.f + "', personalAddress='" + this.j + "', bloodType=" + this.d + ", allergies='" + this.b + "', medications='" + this.c + "', organDonor=" + this.i + ", medicalConditionsAndAdditionalInfo='" + this.f12868a + "', contactList=" + this.e + '}';
    }

    public static class e implements Serializable {
        private static final long serialVersionUID = -1676005475288730958L;

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("contactIndex")
        private int f12869a;

        @SerializedName("contactPhoto")
        private byte[] b;

        @SerializedName("contactName")
        private String d;

        @SerializedName("phoneNumber")
        private String e;

        public int e() {
            return this.f12869a;
        }

        public void a(int i) {
            this.f12869a = i;
        }

        public String c() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public String d() {
            return this.e;
        }

        public void e(String str) {
            this.e = str;
        }

        public byte[] b() {
            byte[] bArr = this.b;
            return bArr == null ? new byte[0] : (byte[]) bArr.clone();
        }

        public void c(byte[] bArr) {
            if (bArr == null) {
                this.b = new byte[0];
            } else {
                this.b = (byte[]) bArr.clone();
            }
        }

        public String toString() {
            return "Contact{contactIndex=" + this.f12869a + ", contactName='" + this.d + "', phoneNumber='" + this.e + "', contactPhoto=" + Arrays.toString(this.b) + '}';
        }
    }
}
