package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes8.dex */
public class evh implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("duration")
    private long f12359a;

    @SerializedName("complete")
    private boolean b;

    @SerializedName("audioId")
    private int c;

    private evh(a aVar) {
        this.c = aVar.d;
        this.f12359a = aVar.f12360a;
        this.b = aVar.b;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private long f12360a;
        private boolean b;
        private int d;

        public a a(int i) {
            this.d = i;
            return this;
        }

        public a d(long j) {
            this.f12360a = j;
            return this;
        }

        public a b(boolean z) {
            this.b = z;
            return this;
        }

        public evh b() {
            return new evh(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("messageCenterUrl") + "/messageCenter/user/saveSleepPlayRecord";
    }
}
