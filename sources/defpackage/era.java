package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class era implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("trainModeType")
    private int f12185a;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String e;

    public era(String str, int i) {
        this.e = str;
        this.f12185a = i;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.cl();
    }
}
