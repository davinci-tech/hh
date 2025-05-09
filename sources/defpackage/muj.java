package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import com.huawei.networkclient.ResultCallback;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.pluginsocialshare.cloud.bean.DataCallback;
import com.huawei.pluginsocialshare.cloud.suggestcloud.ShareDataCloudFactory;
import com.huawei.pluginsocialshare.cloud.themecloud.ThemeCloudApi;
import com.huawei.pluginsocialshare.cloud.themecloud.ThemeDataFactory;
import com.huawei.pluginsocialshare.cloud.themecloud.ThemeSignData;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class muj {

    /* renamed from: a, reason: collision with root package name */
    private static String f15178a = "";
    private static volatile ThemeCloudApi d;
    private static List<fec> c = new ArrayList();
    private static ShareDataCloudFactory e = new ShareDataCloudFactory(BaseApplication.e());
    private static ConcurrentHashMap<String, String> b = new ConcurrentHashMap<>();

    public static void e(mui muiVar, ResultCallback<muk> resultCallback) {
        lqi.d().b(muiVar.getUrl(), e.getHeaders(), lql.b(e.getBody(muiVar)), muk.class, resultCallback);
    }

    private static String e() throws IOException {
        if ("".equals(f15178a)) {
            if (d == null) {
                d = (ThemeCloudApi) lqi.d().b(ThemeCloudApi.class);
            }
            ThemeSignData themeSignData = new ThemeSignData(null);
            Response<ThemeSignData> execute = d.getThemeSign(themeSignData.getUrl(), themeSignData.getHeaders(), themeSignData.getBody(null)).execute();
            if (execute.isOK()) {
                ThemeSignData body = execute.getBody();
                if (body.d() == 0) {
                    return body.b();
                }
            } else {
                LogUtil.b("TAG_CloudDataManager", "getSign network error");
            }
            return "";
        }
        return f15178a;
    }

    public static List<fec> d(int i, boolean z) throws IOException {
        mum body;
        if (d == null) {
            d = (ThemeCloudApi) lqi.d().b(ThemeCloudApi.class);
        }
        mup mupVar = new mup();
        String valueOf = String.valueOf(mvs.b(i));
        mupVar.a(e());
        mupVar.e(valueOf);
        mupVar.c(String.valueOf(mvs.d(i)));
        LogUtil.a("TAG_CloudDataManager", "cursor = ", valueOf, " isAdd = ", Boolean.valueOf(z));
        mupVar.b("0");
        if (nsn.ag(BaseApplication.e())) {
            mupVar.d(HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY);
        } else {
            mupVar.d("10");
        }
        ThemeDataFactory themeDataFactory = new ThemeDataFactory(null);
        Response<mum> execute = d.getThemeList(mupVar.getUrl(), themeDataFactory.getHeaders(), lql.b(themeDataFactory.getBody(mupVar))).execute();
        if (!execute.isOK() || (body = execute.getBody()) == null) {
            return null;
        }
        if (body.c() != null && ("0".equals(body.c()) || "3".equals(body.c()))) {
            if ("0".equals(body.c())) {
                mvs.c(i, Integer.parseInt(body.d()));
            }
            LogUtil.a("TAG_CloudDataManager", "rsp:", body.c());
            List b2 = b(body.b(), i, z);
            if (b2 == null) {
                b2 = new ArrayList();
            }
            b(b2);
            return c;
        }
        LogUtil.h("TAG_CloudDataManager", "getThemeList responseCode :", body.c());
        return null;
    }

    private static List<fec> b(List<mun> list, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        fec a2 = mvs.a(i);
        for (mun munVar : list) {
            if (munVar != null && (a2 == null || !String.valueOf(a2.getId()).equals(munVar.b()))) {
                mut mutVar = new mut();
                mul a3 = munVar.a();
                if (a3 != null) {
                    mutVar.c(a3.a());
                    mutVar.setImageSize(Integer.parseInt(a3.d()));
                }
                List<muo> c2 = munVar.c();
                if (!koq.b(c2)) {
                    mutVar.setUrl(c2.get(0).c());
                }
                String str = mus.f15185a + File.separator + d(mutVar.c());
                if (mwa.d(str)) {
                    mutVar.d(true);
                }
                mutVar.setPath(str);
                mutVar.setId(Integer.parseInt(munVar.b()));
                mutVar.setType(2);
                mutVar.setWeight(10000);
                mutVar.setValidityType(1);
                mutVar.setType(2);
                mutVar.setSportTypes(String.valueOf(i));
                LogUtil.a("TAG_CloudDataManager", "parseBackgroundInfoList info id= ", Integer.valueOf(Integer.parseInt(munVar.b())), "info weight= ", Integer.valueOf(mutVar.getWeight()));
                arrayList.add(mutVar);
            }
        }
        if (a2 != null && !z) {
            a2.setWeight(10001);
            arrayList.add(a2);
        }
        return arrayList;
    }

    private static void b(List<fec> list) {
        c.clear();
        c.addAll(list);
    }

    public static void e(String str, String str2, final DataCallback dataCallback) {
        String c2 = CommonUtil.c(str2);
        if (c2 == null || dataCallback == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            dataCallback.onFailure(9999, "url is null");
            return;
        }
        if (!new File(c2).exists()) {
            HashMap hashMap = new HashMap();
            String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
            String c3 = CommonUtil.c(BaseApplication.e());
            if (accountInfo != null) {
                hashMap.put("x-huid", accountInfo);
            }
            hashMap.put("x-version", c3);
            File file = new File(str2);
            if (e(file)) {
                b.put(str2, str);
                muh muhVar = new muh(str, hashMap, file, new ProgressListener<File>() { // from class: muj.1
                    @Override // com.huawei.networkclient.ProgressListener
                    public void onFail(Throwable th) {
                    }

                    @Override // com.huawei.networkclient.ProgressListener
                    public void onProgress(long j, long j2, boolean z) {
                    }

                    @Override // com.huawei.networkclient.ProgressListener
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onFinish(File file2) {
                        try {
                            if (!file2.exists() || file2.length() <= 0) {
                                return;
                            }
                            LogUtil.a("TAG_CloudDataManager", "exit:", file2.getAbsolutePath());
                            muj.b.remove(file2.getAbsolutePath());
                            DataCallback.this.onSuccess(muj.d(0, file2.getAbsolutePath()));
                        } catch (JSONException e2) {
                            LogUtil.b("TAG_CloudDataManager", "download fail Exception:", ExceptionUtils.d(e2));
                        }
                    }
                }, dataCallback);
                LogUtil.a("TAG_CloudDataManager", "downloading ...");
                lqi.d().d(muhVar);
                return;
            }
            return;
        }
        dataCallback.onSuccess(new JSONObject());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject d(int i, String str) throws JSONException {
        return new JSONObject().put("resultCode", i).put("data", str);
    }

    private static boolean e(File file) {
        if (file.getParentFile() == null) {
            return false;
        }
        boolean exists = file.getParentFile().exists();
        return !exists ? file.getParentFile().mkdirs() : exists;
    }

    public static void d() {
        LogUtil.a("TAG_CloudDataManager", "cancel download");
        HashMap hashMap = new HashMap();
        hashMap.putAll(b);
        b.clear();
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            try {
                lqi.d().d((String) ((Map.Entry) it.next()).getValue());
            } catch (ConcurrentModificationException e2) {
                LogUtil.h("TAG_CloudDataManager", "cancelDownloadFile e:", e2.getMessage());
            }
        }
    }

    private static String d(String str) {
        if (StringUtils.g(str)) {
            return "";
        }
        String[] split = str.split("\\?")[0].split("/");
        return split.length > 0 ? split[split.length - 1] : "";
    }
}
