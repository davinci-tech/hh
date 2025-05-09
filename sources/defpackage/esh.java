package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esh implements IRequest {

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String e;

    private esh(e eVar) {
        this.e = eVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bn();
    }

    public static final class e {
        private String c;

        public e a(String str) {
            this.c = str;
            return this;
        }

        public esh b() {
            return new esh(this);
        }
    }
}
