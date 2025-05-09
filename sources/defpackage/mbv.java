package defpackage;

import android.text.TextUtils;
import com.huawei.picture.security.account.bean.AuthErrorBean;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mbv {
    private final List<AuthErrorBean> e;

    private mbv() {
        this.e = new CopyOnWriteArrayList();
    }

    static class b {
        private static final mbv c = new mbv();
    }

    public static mbv d() {
        return b.c;
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            mcj.c("AuthErrorHelper", "isNeedRefreshToken url empty: " + TextUtils.isEmpty(str) + " ;result empty: " + TextUtils.isEmpty(str2));
            return false;
        }
        try {
            String path = new URL(str).getPath();
            JSONObject jSONObject = new JSONObject(str2);
            for (AuthErrorBean authErrorBean : this.e) {
                if (path.equals(authErrorBean.getPath()) && jSONObject.optString(authErrorBean.getCodeKey()).equals(authErrorBean.getCodeValue()) && (TextUtils.isEmpty(authErrorBean.getMsgKey()) || TextUtils.isEmpty(authErrorBean.getMsgValue()) || jSONObject.optString(authErrorBean.getMsgKey()).equals(authErrorBean.getMsgValue()))) {
                    return true;
                }
            }
        } catch (Exception e) {
            mcj.d("AuthErrorHelper", "isNeedRefreshToken Exception: ", e);
        }
        return false;
    }

    public void d(List<AuthErrorBean> list) {
        e();
        if (mce.d(list)) {
            return;
        }
        mcj.c("AuthErrorHelper", "updateAuthErrorList authErrorStrList " + list.size());
        for (AuthErrorBean authErrorBean : list) {
            if (authErrorBean != null) {
                this.e.add(authErrorBean);
            }
        }
    }

    private void e() {
        this.e.clear();
    }
}
