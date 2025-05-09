package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.Map;

/* loaded from: classes3.dex */
public class ese implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(ParsedFieldTag.TASK_COMPLETE_TIME)
    private Long f12216a;

    @SerializedName("bestRecords")
    private Map<String, String> b;

    @SerializedName("id")
    private String d;

    private ese(b bVar) {
        this.b = bVar.f12217a;
        this.d = bVar.d;
        this.f12216a = bVar.c;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.be();
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private Map<String, String> f12217a;
        private Long c;
        private String d;

        public b a(Map<String, String> map) {
            this.f12217a = map;
            return this;
        }

        public b d(String str) {
            this.d = str;
            return this;
        }

        public b a(Long l) {
            this.c = l;
            return this;
        }

        public ese b() {
            return new ese(this);
        }
    }
}
