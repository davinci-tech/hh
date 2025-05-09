package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;

/* loaded from: classes4.dex */
public class fff {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("infoType")
    private int f12482a;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int b;

    @SerializedName("operType")
    private int c;

    @SerializedName("type")
    private int d;

    @SerializedName("pageNo")
    private int e;

    private fff(e eVar) {
        this.e = eVar.f12483a;
        this.b = eVar.e;
        this.c = eVar.b;
        this.f12482a = eVar.c;
        this.d = eVar.d;
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private int f12483a;
        private int b;
        private int c;
        private int d;
        private int e;

        public e d(int i) {
            this.f12483a = i;
            return this;
        }

        public e e(int i) {
            this.e = i;
            return this;
        }

        public e a(int i) {
            this.b = i;
            return this;
        }

        public e b(int i) {
            this.c = i;
            return this;
        }

        public e c(int i) {
            this.d = i;
            return this;
        }

        public fff a() {
            return new fff(this);
        }
    }
}
