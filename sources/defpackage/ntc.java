package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.operation.utils.CloudParamKeys;
import java.util.List;

/* loaded from: classes6.dex */
public class ntc extends CloudCommonReponse {

    @SerializedName("globalConfig")
    private b e;

    public b a() {
        return this.e;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "OneClickGrayBean{, mGlobalConfig=" + this.e + '}';
    }

    public static final class b {

        @SerializedName("grayingConfig")
        private e d;

        public e e() {
            return this.d;
        }

        public String toString() {
            return "GlobalConfig{mGrayConfig=" + this.d + '}';
        }
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("startTime")
        private long f15478a;

        @SerializedName(CloudParamKeys.CLIENT_TYPE)
        private int b;

        @SerializedName("endTime")
        private long c;

        @SerializedName("pageTypes")
        private List<Integer> d;

        @SerializedName("eventSubject")
        private String e;

        @SerializedName(DBSessionCommon.COLUMN_TIME_ZONE)
        private String f;

        public long d() {
            return this.f15478a;
        }

        public long c() {
            return this.c;
        }

        public int a() {
            return this.b;
        }

        public List<Integer> b() {
            return this.d;
        }

        public String toString() {
            return "GrayingConfig{mStartTime=" + this.f15478a + ", mEndTime=" + this.c + ", mClientType=" + this.b + ", mPagesTypeList=" + this.d + ", mEventSubject='" + this.e + "', mTimezone='" + this.f + "'}";
        }
    }
}
