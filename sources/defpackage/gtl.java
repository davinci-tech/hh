package defpackage;

import android.content.Context;
import android.location.Location;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gtl {

    /* renamed from: a, reason: collision with root package name */
    private int f12927a;
    private Context d;
    private boolean b = true;
    private boolean c = true;
    private boolean e = true;

    public gtl(Context context, int i) {
        this.d = context;
        this.f12927a = i;
    }

    public hjd aTB_(Location location) {
        if (location == null) {
            return new hjd(0.0d, 0.0d);
        }
        if (this.c && HAWebViewInterface.NETWORK.equals(location.getProvider())) {
            aTz_(this.d, location);
            this.c = false;
        }
        if (this.b && a(location.getProvider())) {
            aTz_(this.d, location);
            this.b = false;
            this.c = false;
        }
        if (this.e) {
            double[] aUD_ = gwe.aUD_(this.d, location);
            return new hjd(aUD_[0], aUD_[1]);
        }
        return new hjd(location.getLatitude(), location.getLongitude());
    }

    private boolean a(String str) {
        return GeocodeSearch.GPS.equals(str) || "GpsMockProvider".equals(str);
    }

    private void aTz_(Context context, Location location) {
        int i = this.f12927a;
        if (i == 0) {
            aTA_(context, location);
            return;
        }
        if (i == 1) {
            this.e = true;
        } else if (i == 2) {
            this.e = false;
        } else {
            aTA_(context, location);
        }
    }

    private void aTA_(Context context, Location location) {
        if (context == null || location == null) {
            return;
        }
        int b = ktl.b(location.getLatitude(), location.getLongitude());
        if (b == 1) {
            LogUtil.a("Track_ConvertManger", "AREA 1");
            this.e = true;
            return;
        }
        if (b == 2) {
            LogUtil.a("Track_ConvertManger", "AREA 2");
            this.e = false;
        } else if (b == 3) {
            LogUtil.a("Track_ConvertManger", "AREA 3");
            gwg.b(context);
            this.e = false;
        } else {
            LogUtil.a("Track_ConvertManger", "isInChina default");
            this.e = true;
        }
    }
}
