package defpackage;

import android.os.Build;
import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.huawei.hwcloudmodel.hwwear.hag.model.body.AssemblyBodyBean;
import com.huawei.hwcloudmodel.hwwear.hag.model.body.EndpointBean;
import com.huawei.hwcloudmodel.hwwear.hag.model.body.HeaderBean;
import com.huawei.hwcloudmodel.hwwear.hag.model.body.InquireBean;
import com.huawei.hwcloudmodel.hwwear.hag.model.body.IntentAbilitiesBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class jbv {
    public static String b(double d, double d2, boolean z) {
        try {
            String json = new Gson().toJson(d(d, d2, z));
            LogUtil.a("HagUtils", "request weather body :", json);
            return json;
        } catch (JsonIOException unused) {
            LogUtil.b("HagUtils", "getAssemblyBodyJson() JsonIOException");
            return "";
        }
    }

    private static AssemblyBodyBean d(double d, double d2, boolean z) {
        AssemblyBodyBean assemblyBodyBean = new AssemblyBodyBean();
        assemblyBodyBean.setInquire(e(z));
        assemblyBodyBean.setHeader(d());
        assemblyBodyBean.setEndpoint(c(jbu.a(), d, d2));
        return assemblyBodyBean;
    }

    private static EndpointBean c(String str, double d, double d2) {
        EndpointBean.DeviceBean.LocationBean locationBean = new EndpointBean.DeviceBean.LocationBean();
        locationBean.setLocationSystem(AMapLocation.COORD_TYPE_WGS84);
        locationBean.setLatitude(String.valueOf(d));
        locationBean.setLongitude(String.valueOf(d2));
        EndpointBean.DeviceBean deviceBean = new EndpointBean.DeviceBean();
        deviceBean.setDeviceId(jbr.d());
        deviceBean.setTimezone(jbu.d());
        deviceBean.setLocation(locationBean);
        deviceBean.setPrdVer(CommonUtil.e(BaseApplication.getContext()));
        deviceBean.setDeviceType(jbu.b());
        deviceBean.setSysVer(Build.BRAND + "_" + Build.VERSION.RELEASE);
        deviceBean.setPhoneType(Build.MODEL);
        EndpointBean endpointBean = new EndpointBean();
        endpointBean.setLocale(str);
        endpointBean.setCountryCode(jbu.e());
        endpointBean.setRoamingCountryCode(jbu.e());
        endpointBean.setDevice(deviceBean);
        return endpointBean;
    }

    private static HeaderBean d() {
        HeaderBean headerBean = new HeaderBean();
        headerBean.setType("IntentRequest");
        return headerBean;
    }

    private static InquireBean e(boolean z) {
        IntentAbilitiesBean intentAbilitiesBean = new IntentAbilitiesBean();
        intentAbilitiesBean.setAbilityId("BUILTIN_WEATHER");
        if ("CN".equalsIgnoreCase(jbu.e())) {
            intentAbilitiesBean.setIntentName("hw.weather.search");
        } else {
            intentAbilitiesBean.setIntentName("CHECK_WEATHER");
        }
        if (z) {
            intentAbilitiesBean.setApiKey("weather-tidesInfo");
        } else {
            intentAbilitiesBean.setApiKey("weather-weatherInfo");
        }
        intentAbilitiesBean.setSlots(new IntentAbilitiesBean.SlotsBean());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(intentAbilitiesBean);
        InquireBean.IntentsBean intentsBean = new InquireBean.IntentsBean();
        intentsBean.setIntentSn("1");
        intentsBean.setChannel("3");
        intentsBean.setIntentCategoryId("100210011002");
        intentsBean.setIntentAbilities(arrayList);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList2.add(intentsBean);
        InquireBean inquireBean = new InquireBean();
        inquireBean.setSelectMode("DIRECT_EXECUTE");
        inquireBean.setIntents(arrayList2);
        return inquireBean;
    }
}
