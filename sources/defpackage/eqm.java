package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import java.util.List;

/* loaded from: classes7.dex */
public class eqm implements IRequest {

    @SerializedName("list")
    private List<d> b;

    private eqm(c cVar) {
        this.b = cVar.d;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("version")
        private String f12168a;

        @SerializedName("courseBelongType")
        private Integer b;

        @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
        private String c;

        private d(c cVar) {
            this.c = cVar.c;
            this.f12168a = cVar.f12169a;
            this.b = cVar.d;
        }

        public static final class c {

            /* renamed from: a, reason: collision with root package name */
            private String f12169a;
            private String c;
            private Integer d;

            public c c(String str) {
                this.c = str;
                return this;
            }

            public c d(String str) {
                this.f12169a = str;
                return this;
            }

            public c b(Integer num) {
                this.d = num;
                return this;
            }

            public d b() {
                return new d(this);
            }
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.l();
    }

    public static final class c {
        private List<d> d;

        public c c(List<d> list) {
            this.d = list;
            return this;
        }

        public eqm a() {
            return new eqm(this);
        }
    }
}
