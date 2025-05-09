package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.pluginfitnessadvice.FitWorkout;

/* loaded from: classes8.dex */
public class gdz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("commodityFlag")
    private int f12775a;

    @SerializedName("duration")
    private int b;

    @SerializedName("difficulty")
    private int c;

    @SerializedName("id")
    private String e;

    @SerializedName("topicPreviewPicUrl")
    private String f;

    @SerializedName("version")
    private String g;

    @SerializedName("publishDate")
    private long i;

    @SerializedName("name")
    private String j;

    @SerializedName("intervals")
    private int h = -4;

    @SerializedName(WorkoutRecord.Extend.COURSE_DEFINE_TYPE)
    private int d = 0;

    public String f() {
        return this.e;
    }

    public String h() {
        return this.j;
    }

    public String i() {
        return this.f;
    }

    public int a() {
        return this.b;
    }

    public int j() {
        return this.h;
    }

    public int c() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public int d() {
        return this.f12775a;
    }

    public long g() {
        return this.i;
    }

    public FitWorkout e() {
        FitWorkout fitWorkout = new FitWorkout();
        fitWorkout.saveId(this.e);
        fitWorkout.saveName(this.j);
        fitWorkout.saveVersion(this.g);
        fitWorkout.saveDifficulty(this.c);
        fitWorkout.saveDuration(this.b);
        fitWorkout.setTopicPreviewPicUrl(this.f);
        fitWorkout.setIntervals(this.h);
        fitWorkout.setCourseDefineType(this.d);
        fitWorkout.setCommodityFlag(this.f12775a);
        return fitWorkout;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f12776a;
        private int b;
        private int c;
        private String d;
        private int e;
        private int f;
        private String g;
        private String h;
        private long i;
        private String j;

        public gdz d() {
            gdz gdzVar = new gdz();
            gdzVar.e = this.d;
            gdzVar.j = this.h;
            gdzVar.g = this.g;
            gdzVar.f = this.j;
            gdzVar.b = this.e;
            gdzVar.h = this.f;
            gdzVar.c = this.b;
            gdzVar.d = this.f12776a;
            gdzVar.f12775a = this.c;
            gdzVar.i = this.i;
            return gdzVar;
        }

        public a b(FitWorkout fitWorkout) {
            if (fitWorkout != null) {
                this.d = fitWorkout.acquireId();
                this.h = fitWorkout.acquireName();
                this.g = fitWorkout.accquireVersion();
                this.j = fitWorkout.getTopicPreviewPicUrl();
                this.e = fitWorkout.acquireDuration();
                this.f = fitWorkout.getIntervals();
                this.b = fitWorkout.acquireDifficulty();
                this.f12776a = fitWorkout.getCourseDefineType();
                this.c = fitWorkout.acquireCommodityFlag();
                this.i = fitWorkout.getPublishDate();
            }
            return this;
        }
    }
}
