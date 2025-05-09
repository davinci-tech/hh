package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.device.api.ResourceManagerApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.userlabelmgr.model.UpdateUserLabel;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class dxu {
    private static dxu d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<HealthDevice.HealthDeviceKind> f11900a;
    private UpdateUserLabel b;
    private Context c;
    private String g;

    private dxu() {
        Context context = BaseApplication.getContext();
        this.c = context;
        this.g = LoginInit.getInstance(context).getAccountInfo(1011);
    }

    public static dxu e() {
        dxu dxuVar;
        synchronized (e) {
            if (d == null) {
                d = new dxu();
            }
            dxuVar = d;
        }
        return dxuVar;
    }

    public void a() {
        LogUtil.a("ThirdPartyDeviceLabelHelper", "registerCallback");
        this.b = new UpdateUserLabel() { // from class: dxu.2
            @Override // com.huawei.health.userlabelmgr.model.UpdateUserLabel
            public void onUpdate() {
                dxu.this.c();
            }
        };
        dxw.a(this.c).b(this.b);
    }

    public void d() {
        LogUtil.a("ThirdPartyDeviceLabelHelper", "unRegisterCallback");
        if (this.b != null) {
            dxw.a(this.c).e(this.b);
        }
    }

    public void c() {
        this.f11900a = new ArrayList<>(10);
        this.f11900a.addAll(((ResourceManagerApi) Services.c("EcologyDevice", ResourceManagerApi.class)).getProductKinds(cei.b().getBondedProductsForDeviceOnly(HealthDevice.HealthDeviceKind.HDK_UNKNOWN)));
        LogUtil.a("ThirdPartyDeviceLabelHelper", "generateThirdPartyDeviceLabels() mUserDeviceList = ", Integer.valueOf(this.f11900a.size()));
        h();
        b();
        j();
    }

    private void j() {
        String b = SharedPreferenceManager.b(this.c, "BIND_WEIGHT", "bind_weight_time");
        long currentTimeMillis = System.currentTimeMillis();
        if (TextUtils.isEmpty(b)) {
            dxw.a(this.c).b("health_sport_device_time_third_party", "", this.g);
            return;
        }
        try {
            currentTimeMillis = Long.parseLong(b);
        } catch (NumberFormatException unused) {
            LogUtil.b("ThirdPartyDeviceLabelHelper", "numberFormatException");
        }
        a(currentTimeMillis);
    }

    private void h() {
        ArrayList<HealthDevice.HealthDeviceKind> arrayList = this.f11900a;
        if (arrayList == null || arrayList.size() == 0) {
            dxw.a(this.c).b("health_sport_device_type_third_party", "SportDeviceModel_0", this.g);
        } else {
            dxw.a(this.c).b("health_sport_device_type_third_party", "SportDeviceModel_1", this.g);
        }
    }

    private void b() {
        ArrayList<HealthDevice.HealthDeviceKind> arrayList = this.f11900a;
        if (arrayList == null) {
            LogUtil.a("ThirdPartyDeviceLabelHelper", "generateThirdPartyDeviceModelNumberLabel mUserDeviceList is null");
            return;
        }
        Iterator<HealthDevice.HealthDeviceKind> it = arrayList.iterator();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (it.hasNext()) {
            HealthDevice.HealthDeviceKind next = it.next();
            LogUtil.a("ThirdPartyDeviceLabelHelper", "generateThirdPartyDeviceModelNumberLabel kind = ", next);
            int i = AnonymousClass5.d[next.ordinal()];
            if (i == 1) {
                z = true;
            } else if (i == 2) {
                z2 = true;
            } else if (i == 3) {
                z3 = true;
            } else if (i == 4) {
                z4 = true;
            } else if (i != 5) {
                LogUtil.a("ThirdPartyDeviceLabelHelper", "generateThirdPartyDeviceModelNumberLabel classification is unKnow");
            } else {
                z5 = true;
            }
        }
        dxw.a(this.c).b("health_sport_device_up_third_party", a(z, z2, z3, z4, z5), this.g);
    }

    /* renamed from: dxu$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[HealthDevice.HealthDeviceKind.values().length];
            d = iArr;
            try {
                iArr[HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[HealthDevice.HealthDeviceKind.HDK_HEART_RATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[HealthDevice.HealthDeviceKind.HDK_WEIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                d[HealthDevice.HealthDeviceKind.HDK_ECG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private String a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        StringBuilder sb = new StringBuilder(16);
        if (z) {
            sb.append("SportDeviceType_13");
        } else {
            sb.append("SportDeviceType_12");
        }
        if (z2) {
            sb.append(",SportDeviceType_15");
        } else {
            sb.append(",SportDeviceType_14");
        }
        if (z3) {
            sb.append(",SportDeviceType_17");
        } else {
            sb.append(",SportDeviceType_16");
        }
        if (z4) {
            sb.append(",SportDeviceType_1");
        } else {
            sb.append(",SportDeviceType_0");
        }
        if (z5) {
            sb.append(",SportDeviceType_11");
        } else {
            sb.append(",SportDeviceType_10");
        }
        LogUtil.a("ThirdPartyDeviceLabelHelper", "getDeviceTypeNumLabelValue() labelValue:", sb.toString());
        return sb.toString();
    }

    private void a(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        String str = currentTimeMillis <= 2592000000L ? "SportDeviceTime_0" : (currentTimeMillis <= 2592000000L || currentTimeMillis > 7776000000L) ? (currentTimeMillis <= 7776000000L || currentTimeMillis > 10368000000L) ? "SportDeviceTime_3" : "SportDeviceTime_2" : "SportDeviceTime_1";
        LogUtil.c("ThirdPartyDeviceLabelHelper", "calculateWeightActiveLabel:", str);
        dxw.a(this.c).b("health_sport_device_time_third_party", str, this.g);
    }
}
