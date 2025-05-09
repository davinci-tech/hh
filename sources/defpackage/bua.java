package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes3.dex */
public class bua extends CloudCommonReponse {

    @SerializedName("followUsers")
    private List<Long> b;

    @SerializedName("familyCareRelations")
    private List<b> d;

    public List<Long> e() {
        return this.b;
    }

    public List<b> a() {
        return this.d;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("devId")
        private String f506a;

        @SerializedName("emergencyName")
        private String b;

        @SerializedName("emergencyPhoneNum")
        private String c;

        @SerializedName("followUser")
        private long d;

        @SerializedName("createTime")
        private long e;

        @SerializedName("status")
        private int f;

        @SerializedName("followedUser")
        private long g;

        @SerializedName("locationTime")
        private long i;

        @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
        private long j;

        public long a() {
            return this.d;
        }

        public String d() {
            return this.b;
        }

        public String c() {
            return this.c;
        }
    }
}
