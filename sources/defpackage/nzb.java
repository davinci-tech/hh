package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class nzb {

    @SerializedName("title")
    private String c;

    @SerializedName("permissions")
    private List<b> d;

    @SerializedName("description")
    private String e;

    public String e() {
        return this.c;
    }

    public List<b> d() {
        return this.d;
    }

    public String a() {
        return this.e;
    }

    public String toString() {
        return "PermissionsNote{title='" + this.c + "', description='" + this.e + "', permissions=" + this.d + '}';
    }

    public static class b {

        @SerializedName("name")
        private String c;

        @SerializedName("description")
        private String e;

        public String a() {
            return this.c;
        }

        public String c() {
            return this.e;
        }

        public String toString() {
            return "PermissionsBean{name='" + this.c + "', description='" + this.e + "'}";
        }
    }
}
