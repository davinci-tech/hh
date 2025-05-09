package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class evj implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("courseBelongType")
    private int f12361a;

    @SerializedName("version")
    private String b;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String c;

    @SerializedName("operType")
    private int d;

    @SerializedName("workoutPackageId")
    private String e;

    private evj(a aVar) {
        this.c = aVar.b;
        this.e = aVar.c;
        this.f12361a = aVar.f12362a;
        this.b = aVar.e;
        this.d = aVar.d;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/postBhavior";
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private int f12362a;
        private String b;
        private String c;
        private int d;
        private String e;

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public a a(int i) {
            this.f12362a = i;
            return this;
        }

        public a e(int i) {
            this.d = i;
            return this;
        }

        public evj e() {
            return new evj(this);
        }
    }
}
