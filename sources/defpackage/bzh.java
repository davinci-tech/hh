package defpackage;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class bzh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("awake_count")
    private int f560a;

    @SerializedName("awake_sleep_duration")
    private long b;

    @SerializedName("deep_sleep_duration")
    private long c;

    @SerializedName("begin_time")
    private long d;

    @SerializedName("age")
    private int e;

    @SerializedName("fallasleep_duration")
    private long f;

    @SerializedName("interupt_details")
    private List<Object> g;

    @SerializedName("end_time")
    private long h;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int i;

    @SerializedName("env_noise")
    private b j;

    @SerializedName("light_sleep_duration")
    private long k;

    @SerializedName("sleepMerge")
    private a l;

    @SerializedName("period_details")
    private List<e> m;

    @SerializedName("interup_duration")
    private long n;

    @SerializedName("rem_sleep_duration")
    private long o;

    @SerializedName("unknow_sleep_duration")
    private long p;

    @SerializedName("valid_sleep_duration")
    private long q;

    @SerializedName("symptoms")
    private d r;

    @SerializedName("total_duration")
    private long s;

    @SerializedName(JsUtil.SCORE)
    private int t;

    @SerializedName("voice_infos")
    private List<c> v;

    /* loaded from: classes8.dex */
    public class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("sym1_lvl")
        private int f561a;

        @SerializedName("envdb")
        private int b;

        @SerializedName("envdblevel")
        private int c;

        @SerializedName("sym1_idx")
        private int d;

        @SerializedName("interuptlevel")
        private int e;

        @SerializedName("starnum")
        private int f;

        @SerializedName("totalsleeptime")
        private int g;

        @SerializedName("sym2_idx")
        private int h;

        @SerializedName("sym2_lvl")
        private int i;

        @SerializedName("totalscore")
        private int j;

        public String toString() {
            return "SleepMerge{totalscore=" + this.j + ", starNumber=" + this.f + ", totalSleepTime=" + this.g + ", envDb=" + this.b + ", envDbLevel=" + this.c + ", interruptLevel=" + this.e + ", firstSymptomsId=" + this.d + ", firstSymptomsLevel=" + this.f561a + ", secondSymptomsId=" + this.h + ", secondSymptomsLevel=" + this.i + '}';
        }
    }

    public class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("sym2_idx")
        private int f564a;

        @SerializedName("sym2_lvl")
        private int b;

        @SerializedName("sym1_lvl")
        private int d;

        @SerializedName("sym1_idx")
        private int e;

        public String toString() {
            return "SleepSymptoms{firstSymptomsId=" + this.e + ", firstSymptomsLevel=" + this.d + ", secondSymptomsId=" + this.f564a + ", secondSymptomsLevel=" + this.b + '}';
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("env_db_avg")
        private int f562a;

        @SerializedName("env_db_infos")
        private int[] b;

        @SerializedName("snore_avgdb")
        private int c;

        @SerializedName("env_db_min")
        private int d;

        @SerializedName("env_db_max")
        private int e;

        @SerializedName("snore_duration")
        private long f;

        @SerializedName("snore_max_db")
        private int g;

        @SerializedName("snore_count")
        private int i;

        public int[] e() {
            int[] iArr = this.b;
            return iArr == null ? new int[0] : (int[]) iArr.clone();
        }

        public int d() {
            return this.f562a;
        }

        public int c() {
            return this.e;
        }

        public int a() {
            return this.d;
        }

        public String toString() {
            return "EnvNoiseInfo{envDbInfos=" + Arrays.toString(this.b) + ", envDbAvg=" + this.f562a + ", envDbMax=" + this.e + ", envDbMin=" + this.d + ", snoreCount=" + this.i + ", snoreAvgDb=" + this.c + ", snoreMaxDb=" + this.g + ", snoreDuration=" + this.f + '}';
        }
    }

    /* loaded from: classes8.dex */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("period_duration")
        private long f565a;

        @SerializedName("period_type")
        private long d;

        public long c() {
            return this.d;
        }

        public long e() {
            return this.f565a;
        }

        public String toString() {
            return "PeriodInfo{periodType=" + this.d + ", periodDuration=" + this.f565a + '}';
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("voice_type")
        private int f563a;

        @SerializedName("voice_start_time")
        private long b;

        @SerializedName("voice_filepath")
        private String d;

        @SerializedName("voice_duration")
        private int e;

        public long e() {
            return this.b;
        }

        public int a() {
            return this.e;
        }

        public String d() {
            return this.d;
        }

        public int c() {
            return this.f563a;
        }

        public String toString() {
            return "SleepMonitorReportVoice{voiceStartTime=" + this.b + ", voiceDuration=" + this.e + ", voiceFilepath='" + this.d + "', voiceType=" + this.f563a + '}';
        }

        public int hashCode() {
            String str = this.d;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (this.d == null && cVar.d() == null) {
                return true;
            }
            if (this.d != null || cVar.d() == null) {
                return d().equals(cVar.d());
            }
            return false;
        }
    }

    public long c() {
        return this.d;
    }

    public long b() {
        return this.h;
    }

    public long h() {
        return this.q;
    }

    public int d() {
        return this.f560a;
    }

    public int i() {
        return this.t;
    }

    public List<c> g() {
        return this.v;
    }

    public List<e> a() {
        return this.m;
    }

    public b e() {
        return this.j;
    }

    public d f() {
        return this.r;
    }

    public String toString() {
        return "SleepMonitorReport{age=" + this.e + ", gender=" + this.i + ", beginTime=" + this.d + ", endTime=" + this.h + ", totalDuration=" + this.s + ", fallSleepDuration=" + this.f + ", interruptDuration=" + this.n + ", deepSleepDuration=" + this.c + ", lightSleepDuration=" + this.k + ", remSleepDuration=" + this.o + ", awakeSleepDuration=" + this.b + ", unknowSleepDuration=" + this.p + ", validSleepDuration=" + this.q + ", awakeCount=" + this.f560a + ", sleepScore=" + this.t + ", interruptDetails=" + this.g + ", voiceInfos=" + this.v + ", periodDetails=" + this.m + ", envNoise=" + this.j + ", symptoms=" + this.r + '}';
    }

    public static bzh a(String str) {
        bzh bzhVar = new bzh();
        try {
            return (bzh) HiJsonUtil.e(str, bzh.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.h("SleepMonitorReport", "SleepMonitorReport fromJson is jsonSyntaxException");
            return bzhVar;
        }
    }
}
