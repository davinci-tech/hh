package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class olf implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("playRecord")
    private long f15762a;

    @SerializedName("duration")
    private long b;

    @SerializedName("timestamp")
    private long c;

    @SerializedName("timeZone")
    private String d;

    @SerializedName("type")
    private int e;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String i;

    public void c(long j) {
        this.c = j;
    }

    public void e(int i) {
        this.e = i;
    }

    public void d(String str) {
        this.i = str;
    }

    public void e(long j) {
        this.f15762a = j;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(long j) {
        this.b = j;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/audio/audioWorkout/setWorkoutPlay";
    }
}
