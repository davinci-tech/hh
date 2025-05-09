package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import java.io.File;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class czb {
    private final String b;

    public czb(String str) {
        this.b = str;
    }

    public czd c() {
        if (TextUtils.isEmpty(this.b)) {
            LogUtil.a("UxConfigParse", "getUxConfigInfo productId is empty");
            return null;
        }
        return a(a());
    }

    private String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(cos.e);
        sb.append(this.b);
        String c = CommonUtil.c(sb.toString());
        if (!TextUtils.isEmpty(c) && new File(c).exists()) {
            sb.append(File.separator);
        } else {
            sb = new StringBuilder();
            sb.append(cos.d);
        }
        sb.append(this.b);
        sb.append(File.separator);
        sb.append("UxConfig.json");
        return sb.toString();
    }

    private czd a(String str) {
        LogUtil.a("UxConfigParse", "loadUxConfigInfo");
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("UxConfigParse", "loadUxConfigInfo validPath is empty");
            return null;
        }
        File file = new File(c);
        if (!file.exists()) {
            LogUtil.h("UxConfigParse", "loadUxConfigInfo file not exists");
            return null;
        }
        JSONObject a2 = EzPluginManager.a(file);
        if (a2 == null) {
            LogUtil.h("UxConfigParse", "loadUxConfigInfo jsonObject is null");
            return null;
        }
        return (czd) new Gson().fromJson(a2.toString(), new TypeToken<czd>() { // from class: czb.1
        }.getType());
    }
}
