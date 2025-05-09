package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class emq implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(JsbMapKeyNames.H5_LOC_LAT)
    private double f12097a;

    @SerializedName(JsbMapKeyNames.H5_LOC_LON)
    private double c;

    private emq(d dVar) {
        this.c = dVar.c;
        this.f12097a = dVar.f12098a;
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        private double f12098a;
        private double c;

        public d b(double d) {
            this.c = d;
            return this;
        }

        public d e(double d) {
            this.f12098a = d;
            return this;
        }

        public emq b() {
            return new emq(this);
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/getCityInfoWithGps";
    }

    public String toString() {
        return "GetCityInfoWithGpsReq{longitude=" + this.c + ", latitude=" + this.f12097a + '}';
    }
}
