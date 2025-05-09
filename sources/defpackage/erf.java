package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.SearchCondition;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes3.dex */
public class erf implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int f12187a;

    @SerializedName("searchCondition")
    private SearchCondition b;

    @SerializedName("workoutRank")
    private Integer c;

    @SerializedName(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE)
    private int d;

    @SerializedName("pageNo")
    private int e;

    private erf(d dVar) {
        this.e = dVar.d;
        this.f12187a = dVar.b;
        this.d = dVar.e;
        this.c = dVar.f12188a;
        this.b = dVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bc();
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private Integer f12188a;
        private int b;
        private SearchCondition c;
        private int d;
        private int e;

        public d c(int i) {
            this.d = i;
            return this;
        }

        public d e(int i) {
            this.b = i;
            return this;
        }

        public d a(int i) {
            this.e = i;
            return this;
        }

        public d b(Integer num) {
            this.f12188a = num;
            return this;
        }

        public d d(SearchCondition searchCondition) {
            this.c = searchCondition;
            return this;
        }

        public erf b() {
            return new erf(this);
        }
    }
}
