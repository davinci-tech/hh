package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes9.dex */
public class kuj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("deviceUniqueId")
    private String f14602a;

    @SerializedName("dataSourceId")
    private String b;

    @SerializedName("appId")
    private String c;

    @SerializedName("appBundleName")
    private String d;

    @SerializedName("dataSourceFilter")
    private b e;

    public String a() {
        return this.b;
    }

    public String c() {
        return this.f14602a;
    }

    public String b() {
        return this.d;
    }

    public b d() {
        return this.e;
    }

    public static class b {

        @SerializedName("startTime")
        private long b;

        @SerializedName("endTime")
        private long c;

        @SerializedName("dataType")
        private List<kun> e;

        public long a() {
            return this.b;
        }

        public long c() {
            return this.c;
        }

        public List<kun> e() {
            return this.e;
        }
    }

    public String toString() {
        return "DataSourceOptions{mDataSourceId='" + this.b + "', mDeviceUniqueId='" + this.f14602a + "', mAppBundleName='" + this.d + "', mAppId='" + this.c + "'}";
    }
}
