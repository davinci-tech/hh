package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class eql implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String f12167a;

    @SerializedName("courseBelongType")
    private Integer d;

    @SerializedName("operType")
    private Integer e;

    private eql(a aVar) {
        this.f12167a = aVar.b;
        this.e = aVar.e;
        this.d = aVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.g();
    }

    public static final class a {
        private String b;
        private Integer c;
        private Integer e;

        public a c(String str) {
            this.b = str;
            return this;
        }

        public a a(Integer num) {
            this.e = num;
            return this;
        }

        public a c(Integer num) {
            this.c = num;
            return this;
        }

        public eql e() {
            return new eql(this);
        }
    }
}
