package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dnf {
    private List<String> e = new ArrayList();

    public boolean c(final String str) {
        if (TextUtils.isEmpty(str) || koq.b(d(str))) {
            return true;
        }
        if (!eil.a(d(str))) {
            LogUtil.a("MarketingUserLabelsMgr", "RecommendSwitch is closed.");
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: dnf.5
            @Override // java.lang.Runnable
            public void run() {
                dnf dnfVar = dnf.this;
                dnfVar.e = dnfVar.e(str);
                LogUtil.a("MarketingUserLabelsMgr", "resourceUserLabels = ", str);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
            if (koq.b(this.e) || d(str).size() > this.e.size()) {
                LogUtil.a("MarketingUserLabelsMgr", "User didn't have enough label to match.Resource label size: ", Integer.valueOf(d(str).size()), "; User's label size: ", Integer.valueOf(this.e.size()));
                return false;
            }
            LogUtil.a("MarketingUserLabelsMgr", "mUserLabelList = ", this.e.toString());
            List<String> b = b(str);
            for (String str2 : this.e) {
                if (str2.contains("SportDeviceType")) {
                    if (!b(str2, b)) {
                        return false;
                    }
                } else if (str2.contains("SportDeviceTime")) {
                    if (d(str2, b)) {
                        return false;
                    }
                } else if (!b.contains(str2)) {
                    LogUtil.a("MarketingUserLabelsMgr", "User label didn't match. return false: ", str2);
                    return false;
                }
            }
            return true;
        } catch (InterruptedException unused) {
            LogUtil.b("MarketingUserLabelsMgr", "get user label InterruptedException");
            return false;
        }
    }

    private boolean b(String str, List<String> list) {
        try {
            for (String str2 : str.split(",")) {
                if (!list.contains(str2)) {
                    String str3 = str2.split("_")[1];
                    if (Integer.parseInt(str3) % 2 == 0) {
                        if (list.contains(str2.replace(str3, String.valueOf(Integer.parseInt(str3) + 1)))) {
                            return false;
                        }
                    } else if (list.contains(str2.replace(str3, String.valueOf(Integer.parseInt(str3) - 1)))) {
                        return false;
                    }
                }
            }
        } catch (NumberFormatException e) {
            LogUtil.b("MarketingUserLabelsMgr", "NumberFormatException", e.getMessage());
        }
        return true;
    }

    private boolean d(String str, List<String> list) {
        for (String str2 : str.split(",")) {
            if (!list.contains(str2)) {
                String str3 = str2.split("_")[1];
                try {
                    if (!a(list, str2, str3, Integer.parseInt(str3) % 4)) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    LogUtil.b("MarketingUserLabelsMgr", "NumberFormatException", e.getMessage());
                }
            }
        }
        return true;
    }

    private boolean a(List<String> list, String str, String str2, int i) {
        int i2 = -i;
        for (int i3 = i2; i3 < i2 + 4; i3++) {
            try {
                String replace = str.replace(str2, String.valueOf(Integer.parseInt(str2) + i3));
                if (list.contains(replace) && !replace.equals(str)) {
                    return false;
                }
            } catch (NumberFormatException e) {
                LogUtil.b("MarketingUserLabelsMgr", "NumberFormatException", e.getMessage());
                return true;
            }
        }
        return true;
    }

    private List<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            Iterator<String> keys = new JSONObject(str).keys();
            while (keys.hasNext()) {
                arrayList.add(keys.next().toString());
            }
            LogUtil.a("MarketingUserLabelsMgr", "getLabelKeys labelKeysList.size:", Integer.valueOf(arrayList.size()));
            return arrayList;
        } catch (JSONException unused) {
            LogUtil.b("MarketingUserLabelsMgr", "getLabelKeys resourceUserLabelList get fail");
            return arrayList;
        }
    }

    private List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (true) {
                if (!keys.hasNext()) {
                    LogUtil.a("MarketingUserLabelsMgr", "getLabelValues labelValuesList.size:", Integer.valueOf(arrayList.size()));
                    return arrayList;
                }
                JSONArray jSONArray = jSONObject.getJSONArray(keys.next().toString());
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("MarketingUserLabelsMgr", "getLabelValues resourceUserLabelList get fail");
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> e(String str) {
        return ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).getLabels(d(str), LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011));
    }
}
