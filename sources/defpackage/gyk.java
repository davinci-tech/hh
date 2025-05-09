package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.health.ITrackDataReport;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.healthcloud.plugintrack.open.TrackDataRemoteProxy;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class gyk implements ITrackDataReport {
    private static final String e = gvv.e(BaseApplication.getContext());
    TrackDataRemoteProxy.d c = null;
    private int g = 0;
    private int i = 0;
    private int h = 0;
    private int j = 0;
    private int b = 0;
    private float o = 0.0f;
    private int f = 0;
    private int s = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f13005a = 0;
    private int k = 0;
    private int m = 0;
    private double d = 0.0d;
    private int l = 0;
    private int n = 0;

    @Override // com.huawei.health.ITrackDataReport
    public void report(Bundle bundle) throws RemoteException {
        TrackDataRemoteProxy.d dVar = this.c;
        if (dVar != null) {
            dVar.report(aWH_());
        } else {
            LogUtil.a("Track_LogicalTrackData", "mLocalReportProxy is null");
        }
    }

    public Bundle aWH_() {
        if (this.l == 264) {
            this.g = -1;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(GeocodeSearch.GPS, this.g);
        bundle.putInt("distance", this.i);
        bundle.putInt("duration", this.h);
        bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, this.j);
        bundle.putInt("calorie", this.b);
        bundle.putFloat("speed", this.o);
        bundle.putInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, this.n);
        int i = this.n;
        if (i == 0) {
            bundle.putInt("timeTarget", this.s);
        } else if (i == 1) {
            bundle.putInt("distanceTarget", this.f);
        } else if (i == 2) {
            bundle.putInt("calorieTarget", this.f13005a);
        }
        bundle.putInt("pace", this.k);
        bundle.putDouble("altitude", this.d);
        bundle.putInt("sportState", this.m);
        bundle.putInt(BleConstants.SPORT_TYPE, this.l);
        if (UnitUtil.h()) {
            bundle.putString("disUnit", BaseApplication.getContext().getResources().getString(R.string._2130844081_res_0x7f0219b1));
        } else {
            bundle.putString("disUnit", BaseApplication.getContext().getResources().getString(R.string._2130844082_res_0x7f0219b2));
        }
        return bundle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.IInterface
    public IBinder asBinder() {
        return (IBinder) this;
    }

    public void b(TrackDataRemoteProxy.d dVar) {
        this.c = dVar;
    }

    public void e() {
        gtx c = gtx.c(BaseApplication.getContext());
        this.g = c.ac();
        this.i = 0;
        this.h = 0;
        this.j = 0;
        this.b = 0;
        this.o = 0.0f;
        Bundle aUd_ = c.aUd_();
        if (aUd_ == null) {
            LogUtil.h("Track_LogicalTrackData", "init(), sportDataBundle == null");
            return;
        }
        int i = aUd_.getInt(WorkoutRecord.Extend.COURSE_TARGET_TYPE, -1);
        this.n = i;
        if (i == 0) {
            this.s = aUd_.getInt(WorkoutRecord.Extend.COURSE_TARGET_VALUE);
        } else if (i == 1) {
            this.f = aUd_.getInt(WorkoutRecord.Extend.COURSE_TARGET_VALUE) * 1000;
        } else if (i == 2) {
            this.f13005a = aUd_.getInt(WorkoutRecord.Extend.COURSE_TARGET_VALUE);
        }
        this.k = 0;
        this.d = 0.0d;
        this.m = aUd_.getInt("sportState");
        this.l = aUd_.getInt(BleConstants.SPORT_TYPE);
    }

    public void aWI_(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        b(bundle.getLong("duration_digital"));
        i(bundle.getString("distance"));
        b(bundle.getString("speed"));
        c(bundle.getString(IndoorEquipManagerApi.KEY_HEART_RATE));
        d(bundle.getString("pace"));
        e(bundle.getString("calorie"));
        a("0");
        this.l = bundle.getInt(BleConstants.SPORT_TYPE);
    }

    private void i(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.i = Math.round(Float.parseFloat(str) * 1000.0f);
            } else {
                this.i = 0;
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_LogicalTrackData", "numberFormatException numberFormatException = ", e2.getMessage());
        }
    }

    private void b(long j) {
        if (j > 0) {
            this.h = Math.round(j / 1000.0f);
        } else {
            this.h = 0;
        }
    }

    private void c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.j = Integer.parseInt(str);
            } else {
                this.j = 0;
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_LogicalTrackData", "numberFormatException numberFormatException = ", e2.getMessage());
        }
    }

    private void e(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.b = Math.round(Float.parseFloat(str) * 1000.0f);
            } else {
                this.b = 0;
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_LogicalTrackData", "numberFormatException numberFormatException = ", e2.getMessage());
        }
    }

    private void b(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !e.equals(str)) {
                this.o = (Float.parseFloat(str) * 1000.0f) / 3600.0f;
            } else {
                this.o = 0.0f;
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_LogicalTrackData", "numberFormatException numberFormatException = ", e2.getMessage());
        }
    }

    private void d(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !e.equals(str)) {
                this.k = Integer.parseInt(str);
            } else {
                this.k = 0;
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_LogicalTrackData", "numberFormatException numberFormatException = ", e2.getMessage());
        }
    }

    private void a(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.d = Double.parseDouble(str);
            } else {
                this.d = 0.0d;
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_LogicalTrackData", "numberFormatException numberFormatException = ", e2.getMessage());
        }
    }

    public void a(int i) {
        this.g = i;
    }

    public void c(int i) {
        this.m = i;
        try {
            report(aWH_());
        } catch (RemoteException e2) {
            LogUtil.b("Track_LogicalTrackData", "ProcessFused()", e2.getMessage());
        }
    }

    public int b() {
        return this.m;
    }
}
