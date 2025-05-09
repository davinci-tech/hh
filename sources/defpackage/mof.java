package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.phoneservice.feedbackcommon.utils.AsCache;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes6.dex */
public class mof {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dayNumber")
    private int f15080a;

    @SerializedName("planTime")
    private long b;

    @SerializedName(ParsedFieldTag.TASK_COMPLETE_TIME)
    private long c;

    @SerializedName("planId")
    private String d;

    @SerializedName(AsCache.FEED_BACK_CACHE_FILE_NAME)
    private String e;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String f;

    @SerializedName("weekNumber")
    private int h;

    public String c() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.f = str;
    }

    public void e(long j) {
        this.c = j;
    }

    public void e(String str) {
        this.e = str;
    }

    public void c(int i) {
        this.h = i;
    }

    public void d(int i) {
        this.f15080a = i;
    }

    public void b(long j) {
        this.b = j;
    }

    public long e() {
        return this.b;
    }
}
