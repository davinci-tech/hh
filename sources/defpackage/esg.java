package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class esg implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("pageNum")
    private Integer f12219a;

    @SerializedName("startTime")
    private Long b;

    @SerializedName("smartScreenLogsFlag")
    private Integer d;

    @SerializedName("endTime")
    private Long e;

    private esg(b bVar) {
        this.b = bVar.e;
        this.e = bVar.d;
        this.f12219a = bVar.c;
        this.d = bVar.b;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.br();
    }

    public static final class b {
        private Integer b;
        private Integer c;
        private Long d;
        private Long e;

        public b e(Long l) {
            this.e = l;
            return this;
        }

        public b d(Long l) {
            this.d = l;
            return this;
        }

        public b d(Integer num) {
            this.c = num;
            return this;
        }

        public b c(Integer num) {
            this.b = num;
            return this;
        }

        public esg c() {
            return new esg(this);
        }
    }
}
