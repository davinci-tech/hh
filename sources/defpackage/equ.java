package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import java.util.List;

/* loaded from: classes3.dex */
public class equ implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pageStart")
    private Integer f12180a;

    @SerializedName("courseCategory")
    private Integer b;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private Integer c;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE)
    private List<Integer> d;

    @SerializedName("operType")
    private Integer e;

    private equ(c cVar) {
        this.f12180a = cVar.c;
        this.c = cVar.b;
        this.e = cVar.d;
        this.d = cVar.e;
        this.b = cVar.f12181a;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.x();
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12181a;
        private Integer b;
        private Integer c;
        private Integer d;
        private List<Integer> e;

        public c c(Integer num) {
            this.c = num;
            return this;
        }

        public c a(Integer num) {
            this.b = num;
            return this;
        }

        public c e(Integer num) {
            this.d = num;
            return this;
        }

        public c a(List<Integer> list) {
            this.e = list;
            return this;
        }

        public c b(Integer num) {
            this.f12181a = num;
            return this;
        }

        public equ c() {
            return new equ(this);
        }
    }
}
