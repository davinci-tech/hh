package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.tencent.open.SocialConstants;

/* loaded from: classes6.dex */
public class omd {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("title")
    private String f15781a;

    @SerializedName("createTime")
    private long b;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String c;

    @SerializedName("subTitle")
    private String d;

    @SerializedName(SocialConstants.PARAM_APP_DESC)
    private String e;

    public String a() {
        return this.f15781a;
    }

    public String d() {
        return this.e;
    }

    public String c() {
        return this.c;
    }

    public long b() {
        return this.b;
    }

    public String e() {
        return this.d;
    }

    public String toString() {
        return "AudioWorkoutRestBean{title='" + this.f15781a + "', desc='" + this.e + "', createTime=" + this.b + ", workoutId='" + this.c + "', subTitle='" + this.d + "'}";
    }
}
