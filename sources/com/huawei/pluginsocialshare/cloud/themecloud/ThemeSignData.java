package com.huawei.pluginsocialshare.cloud.themecloud;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;
import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.w9;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.IRequest;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import defpackage.lql;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LogUtil;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes6.dex */
public class ThemeSignData implements IRequest, ParamsFactory {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("resultcode")
    private int f8524a;

    @SerializedName("resultinfo")
    private String c;
    private Context d;

    @SerializedName(HwPayConstant.KEY_SIGN)
    private String e;

    public ThemeSignData(Context context) {
        if (context == null) {
            this.d = BaseApplication.e();
        } else {
            this.d = context.getApplicationContext();
        }
    }

    public String b() {
        return this.e;
    }

    public int d() {
        return this.f8524a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ThemeSignFactory{sign=");
        stringBuffer.append(this.e);
        stringBuffer.append("resultinfo=").append(this.c);
        stringBuffer.append(", resultcode=").append(this.f8524a);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(this.d).getUrl("domainThemeCloud") + "/servicesupport/theme/tis/service/getFrontParam.do";
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put(j2.v, "gzip, deflate");
        hashMap.put("Connection", w9.j);
        hashMap.put(ProfileRequestConstants.X_APPID_KEY, "10013");
        String str = "1.0";
        try {
            Matcher matcher = Pattern.compile("^[0-9.,x]*").matcher(BaseApplication.a());
            if (matcher.find()) {
                str = matcher.group(0).replace(".", "");
            }
        } catch (PatternSyntaxException e) {
            LogUtil.e("ThemeSignFactory", "getHeaders ex=", LogUtil.a(e));
        }
        hashMap.put("versionCode", str);
        String a2 = a(20);
        LogUtil.c("ThemeSignFactory", "random request :", a2);
        hashMap.put(WatchFaceConstant.X_CLIENTTRACEID, a2);
        hashMap.put("x-hc", GRSManager.a(this.d).getCommonCountryCode());
        hashMap.put("x-devicemodel", Build.MODEL);
        return hashMap;
    }

    @Override // com.huawei.networkclient.ParamsFactory
    public Map<String, Object> getBody(Object obj) {
        Map<String, Object> d = lql.d(obj);
        if (d == null) {
            d = new HashMap<>();
        }
        d.put(JsbMapKeyNames.H5_USER_ID, LoginInit.getInstance(this.d).getDeviceId());
        d.put("firmware", Build.VERSION.RELEASE);
        String valueOf = String.valueOf(b(this.d));
        d.put("screen", valueOf + "*" + valueOf);
        d.put("phoneType", Build.MODEL);
        String commonCountryCode = GRSManager.a(this.d).getCommonCountryCode();
        d.put("local", CommonUtil.x() + "_" + commonCountryCode);
        d.put("isoCode", commonCountryCode);
        d.put("mcc", a(this.d, commonCountryCode));
        d.put("type", "5");
        return d;
    }

    private String a(Context context, String str) {
        String[] stringArray = context.getResources().getStringArray(R.array._2130968633_res_0x7f040039);
        com.huawei.hwlogsmodel.LogUtil.a("ThemeSignFactory", "getMobileCountryCode stringCountryId:", str);
        String str2 = "";
        if (stringArray == null) {
            return "";
        }
        int length = stringArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String[] split = stringArray[i].split(",");
            if (split.length > 1 && split[1].trim().equals(str.trim())) {
                str2 = split[0];
                break;
            }
            i++;
        }
        LogUtil.c("ThemeSignFactory", "getMobileCountryCode resourceCountryCode:", str2);
        return str2;
    }

    private static int b(Context context) {
        Point point = new Point();
        Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            return point.x;
        }
        ((WindowManager) systemService).getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    private String a(int i) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(secureRandom.nextInt(10));
        }
        return sb.toString();
    }
}
