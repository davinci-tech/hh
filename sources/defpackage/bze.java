package defpackage;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class bze {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(CommonConstant.KEY_GENDER)
    private int f554a;

    @SerializedName("age")
    private int b;

    @SerializedName("intrNum")
    private int c;

    @SerializedName("isAutoEnd")
    private boolean d;

    @SerializedName("interuptInfos")
    private List<Object> e;

    @SerializedName("sleepReportData")
    private String f;

    @SerializedName("sleepScore")
    private int g;

    @SerializedName("sdkVersion")
    private int h;

    @SerializedName("periodInfos")
    private List<Object> i;

    @SerializedName("sleepStat")
    private d j;

    @SerializedName(JsbMapKeyNames.H5_USER_ID)
    private String k;

    @SerializedName("VoiceInfoarray")
    private List<Object> l;

    @SerializedName("voiceStat")
    private e m;

    @SerializedName("symptoms")
    private c o;

    public d d() {
        return this.j;
    }

    public boolean a() {
        return this.d;
    }

    public String toString() {
        return "SleepMonitorInfo{userId='" + this.k + "', age=" + this.b + ", gender=" + this.f554a + ", sleepScore=" + this.g + ", intrNum=" + this.c + ", sleepStat=" + this.j + ", sleepPeriodInfos=" + this.i + ", interruptInfos=" + this.e + ", voiceInfoArray=" + this.l + ", sleepSymptoms=" + this.o + ", sleepReportData='" + this.f + "', voiceStat=" + this.m + ", sdkVersion=" + this.h + ", isAutoEnd=" + this.d + '}';
    }

    public class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("envdbmax")
        private int f557a;

        @SerializedName("envdb")
        private int[] b;

        @SerializedName("envdbavg")
        private int c;

        @SerializedName("counter")
        private long d;

        @SerializedName("envdball")
        private long e;

        @SerializedName("snoremaxdb")
        private int f;

        @SerializedName("snorelength")
        private long g;

        @SerializedName("snoreavgdb")
        private int h;

        @SerializedName("snorefreq")
        private int i;

        @SerializedName("envdbmin")
        private int j;

        @SerializedName("snorenum")
        private int n;

        public String toString() {
            return "SleepVoiceStat{envdbAll=" + this.e + ", counter=" + this.d + ", envdb=" + Arrays.toString(this.b) + ", envdbAvg=" + this.c + ", envdbMax=" + this.f557a + ", envdbMin=" + this.j + ", snoreNum=" + this.n + ", snoreAvgDb=" + this.h + ", snoreMaxDb=" + this.f + ", snoreLength=" + this.g + ", snoreFreq=" + this.i + '}';
        }
    }

    public class c {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("sym1_lvl")
        private int f555a;

        @SerializedName("sym1_idx")
        private int b;

        @SerializedName("sym2_lvl")
        private int c;

        @SerializedName("sym2_idx")
        private int d;

        public String toString() {
            return "SleepSymptoms{firstSymptomsId=" + this.b + ", firstSymptomsLevel=" + this.f555a + ", secondSymptomsId=" + this.d + ", secondSymptomsLevel=" + this.c + '}';
        }
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("awaketime")
        private int f556a;

        @SerializedName("deepdesc")
        private int b;

        @SerializedName("deepsleepnum")
        private int c;

        @SerializedName("awakecount")
        private int d;

        @SerializedName("deepsleeptime")
        private int e;

        @SerializedName("interupttime")
        private int f;

        @SerializedName("fallsleeptime")
        private int g;

        @SerializedName("dreamdesc")
        private int h;

        @SerializedName("lightsleeptime")
        private int i;

        @SerializedName("lightdesc")
        private int j;

        @SerializedName("unknowtime")
        private int k;

        @SerializedName("sleependtime")
        private long l;

        @SerializedName("remtime")
        private int m;

        @SerializedName("sleepbegintime")
        private long n;

        @SerializedName("totalsleeptime")
        private int o;

        @SerializedName("validsleeptime")
        private int t;

        public long e() {
            return this.n;
        }

        public long c() {
            return this.l;
        }

        public String toString() {
            return "SleepMonitorStat{sleepBeginTime=" + this.n + ", sleepEndTime=" + this.l + ", fallSleepTime=" + this.g + ", totalSleepTime=" + this.o + ", interruptTime=" + this.f + ", deepSleepTime=" + this.e + ", lightSleepTime=" + this.i + ", remTime=" + this.m + ", awakeTime=" + this.f556a + ", unknowTime=" + this.k + ", deepDesc=" + this.b + ", lightDesc=" + this.j + ", dreamDesc=" + this.h + ", validSleepTime=" + this.t + ", deepSleepNumber=" + this.c + ", awakeCount=" + this.d + '}';
        }
    }

    public static bze d(String str) {
        bze bzeVar = new bze();
        try {
            return (bze) HiJsonUtil.e(str, bze.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.h("SleepMonitorInfo", "SleepMonitorInfo fromJson is jsonSyntaxException");
            return bzeVar;
        }
    }
}
