package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class evd implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("startTimeStamp")
    private long f12356a;

    @SerializedName("audioId")
    private int b;

    @SerializedName("timeZone")
    private String c;

    @SerializedName(KakaConstants.SLEEP_MUSIC_DURATION)
    private int d;

    @SerializedName("endTimeStamp")
    private long e;

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private int f12357a;
        private long b;
        private String c;
        private long d;
        private int e;

        public b d(int i) {
            this.e = i;
            return this;
        }

        public b a(long j) {
            this.d = j;
            return this;
        }

        public b c(long j) {
            this.b = j;
            return this;
        }

        public b e(int i) {
            this.f12357a = i;
            return this;
        }

        public b b(String str) {
            this.c = str;
            return this;
        }

        public evd a() {
            return new evd(this);
        }
    }

    private evd(b bVar) {
        this.b = bVar.e;
        this.f12356a = bVar.d;
        this.e = bVar.b;
        this.d = bVar.f12357a;
        this.c = bVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("sportSuggestUrl") + "/user/addSleepPlayLog";
    }
}
