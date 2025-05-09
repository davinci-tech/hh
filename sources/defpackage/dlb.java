package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dlb {

    /* renamed from: a, reason: collision with root package name */
    private final a f11703a;
    private ktg c;
    private String d;
    private Location e;

    public dlb(a aVar) {
        this.f11703a = aVar;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f11704a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String j;

        public dlb c() {
            return new dlb(this);
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a f(String str) {
            this.j = str;
            return this;
        }

        public a c(String str) {
            this.d = str;
            return this;
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public a d(String str) {
            this.f11704a = str;
            return this;
        }

        public a e(String str) {
            this.e = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a() {
            return this.e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String d() {
            return this.b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String i() {
            return this.j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String b() {
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String e() {
            return this.f11704a;
        }
    }

    public void a() {
        LogUtil.a("UploadDataToHotaServer", "uploadData");
        String a2 = this.f11703a.a();
        if (!TextUtils.isEmpty(a2) && !dij.h(a2)) {
            LogUtil.a("UploadDataToHotaServer", "isIntelligentDevice is false");
        } else if (d()) {
            LogUtil.a("UploadDataToHotaServer", "device sn has been upload");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dla
                @Override // java.lang.Runnable
                public final void run() {
                    dlb.this.c();
                }
            });
        }
    }

    private void j() {
        OutputStream b = b();
        LogUtil.c("UploadDataToHotaServer", "send json: ", System.lineSeparator(), b.toString());
        String d = d(((VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class)).getUpdateUrl(false), b.toString());
        LogUtil.a("UploadDataToHotaServer", "receive json:", d);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("UploadDataToHotaServer", "receive is empty");
            return;
        }
        try {
            if ("1".equals(new JSONObject(d).getString("status"))) {
                i();
            }
        } catch (JSONException unused) {
            LogUtil.b("UploadDataToHotaServer", "uploadDataToHota jsonException");
        }
    }

    private OutputStream b() {
        LogUtil.a("UploadDataToHotaServer", "convertVersionFilterJsonToStream(bone) begin");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JSONObject jSONObject = new JSONObject();
        try {
            d(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("components", e());
            jSONObject2.putOpt("rules", jSONObject);
            try {
                byteArrayOutputStream.write(jSONObject2.toString().getBytes(StandardCharsets.UTF_8));
                byteArrayOutputStream.flush();
            } catch (IOException e) {
                LogUtil.b("UploadDataToHotaServer", "convertVersionFilterJsonToStream(bone) IOException ", e.getMessage());
            }
        } catch (JSONException unused) {
            LogUtil.b("UploadDataToHotaServer", "convertVersionFilterJsonToStream(bone), JSONException");
        }
        LogUtil.a("UploadDataToHotaServer", "convertVersionFilterJsonToStream(bone) leave");
        return byteArrayOutputStream;
    }

    private void d(JSONObject jSONObject) throws JSONException {
        jSONObject.put("FingerPrint", Build.FINGERPRINT);
        jSONObject.put("DeviceName", Build.MODEL);
        String e = this.f11703a.e();
        if (!TextUtils.isEmpty(e)) {
            e = e.replace(Constants.LINK, "");
        }
        jSONObject.put("deviceId", e);
        if (!TextUtils.isEmpty(this.d)) {
            jSONObject.put("extra_info", this.d);
        }
        jSONObject.put("Language", d(BaseApplication.getContext()));
        jSONObject.put("OS", "Android " + Build.VERSION.RELEASE);
    }

    private JSONArray e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DetailRect.CP_PACKAGE, this.f11703a.d());
        jSONObject.put("PackageVersionCode", this.f11703a.i());
        jSONObject.put("PackageVersionName", this.f11703a.b());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    private String d(Context context) {
        String str;
        String str2;
        if (context != null) {
            Configuration configuration = context.getResources().getConfiguration();
            str = configuration.locale.getLanguage();
            str2 = configuration.locale.getCountry();
        } else {
            str = "";
            str2 = "";
        }
        return (str + '-' + str2).toLowerCase(Locale.ENGLISH);
    }

    private String d(String str, String str2) {
        LogUtil.c("UploadDataToHotaServer", "postReq, urlString:", str);
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (d(str)) {
            return jeb.d(str, str2);
        }
        return jee.d(str, str2, true);
    }

    private boolean d(String str) {
        int indexOf;
        if (!TextUtils.isEmpty(str) && (indexOf = str.indexOf(":")) >= 0) {
            return "http".equalsIgnoreCase(str.substring(0, indexOf));
        }
        return false;
    }

    private boolean g() {
        return a(BaseApplication.getActivity()) && e(BaseApplication.getActivity());
    }

    private boolean a(Context context) {
        if (context != null) {
            return PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.GRANTED;
        }
        LogUtil.h("UploadDataToHotaServer", "checkGpsIsAcceptPermission, context is null");
        return false;
    }

    private boolean e(Context context) {
        boolean b = b(context);
        LogUtil.a("UploadDataToHotaServer", "gpsLocationisEnable isGpsEnable is ", Boolean.valueOf(b));
        return b;
    }

    private boolean b(Context context) {
        boolean z;
        boolean z2;
        if (context == null) {
            LogUtil.h("UploadDataToHotaServer", "context is null");
            return false;
        }
        Object systemService = context.getSystemService("location");
        if (systemService instanceof LocationManager) {
            LocationManager locationManager = (LocationManager) systemService;
            z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        } else {
            z = false;
            z2 = false;
        }
        return z2 || z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!g()) {
            LogUtil.a("UploadDataToHotaServer", "isAppHasLocationPermission is false");
            j();
            return;
        }
        if (this.c == null) {
            this.c = ktg.c();
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(HAWebViewInterface.NETWORK);
        arrayList.add(GeocodeSearch.GPS);
        Location bQQ_ = this.c.bQQ_(arrayList);
        this.e = bQQ_;
        if (bQQ_ == null) {
            this.e = this.c.bQR_();
        }
        arrayList.clear();
        if (this.e != null) {
            LogUtil.a("UploadDataToHotaServer", "get location != null");
            f();
        } else {
            LogUtil.a("UploadDataToHotaServer", "processLocationForQuery location is null");
            j();
        }
    }

    private void f() {
        List<Address> fromLocation;
        try {
            fromLocation = new Geocoder(BaseApplication.getContext(), Locale.getDefault()).getFromLocation(new BigDecimal(this.e.getLatitude()).setScale(3, 4).doubleValue(), new BigDecimal(this.e.getLongitude()).setScale(3, 4).doubleValue(), 1);
        } catch (IOException unused) {
            LogUtil.b("UploadDataToHotaServer", "location IOException");
        } catch (JSONException unused2) {
            LogUtil.b("UploadDataToHotaServer", "location JSONException");
        }
        if (koq.b(fromLocation)) {
            LogUtil.h("UploadDataToHotaServer", "location result is null");
            j();
            return;
        }
        if (fromLocation.get(0) instanceof Address) {
            Address address = fromLocation.get(0);
            if (address.getSubLocality() == null) {
                LogUtil.h("UploadDataToHotaServer", "location getSubLocality is null");
                j();
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("country", address.getCountryCode());
            jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, address.getAdminArea());
            jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, address.getLocality());
            jSONObject.put("area", address.getSubLocality());
            this.d = jSONObject.toString();
        }
        j();
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UploadDataToHotaServer", "sn is empty");
            return;
        }
        StringBuilder sb = new StringBuilder(str);
        if (str.length() < 7) {
            LogUtil.a("UploadDataToHotaServer", "sn invalids");
            return;
        }
        sb.replace(6, 7, "");
        sb.replace(0, 2, "");
        new a().d(sb.toString()).c().a();
    }

    private void i() {
        String d = knl.d(this.f11703a.e());
        if (TextUtils.isEmpty(d)) {
            LogUtil.a("UploadDataToHotaServer", "setSnToSharedPreference sn sha256 is empty");
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), d, "1", new StorageParams());
    }

    public boolean d() {
        String d = knl.d(this.f11703a.e());
        if (TextUtils.isEmpty(d)) {
            LogUtil.a("UploadDataToHotaServer", "getSnToSharedPreference sn sha256 is empty");
            return false;
        }
        return "1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), d));
    }
}
