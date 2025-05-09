package defpackage;

import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.health.healthmodel.bean.PictureBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginmgr.EzPluginType;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CommonUtil;
import health.compact.a.GrsDownloadPluginUrl;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bad {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f299a = {"health_model_config", "health_model_semantic", "health_model_update_guide", "health_model_guide_img", "health_model_task_img"};
    private static final byte[] c = new byte[0];
    private static volatile bad e;
    private final ThreadPoolManager f = ThreadPoolManager.d();
    private final mrx d = new mrx(EzPluginType.HEALTH_MODEL_TYPE, null);
    private boolean b = Utils.o();

    private bad() {
    }

    public static bad b() {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new bad();
                }
            }
        }
        return e;
    }

    public void b(String str) {
        LogUtil.a("HealthLife_DownloadUtils", "startDownload");
        if (azi.ah()) {
            return;
        }
        if (azi.ab()) {
            azi.a(System.currentTimeMillis());
            e(str);
            return;
        }
        for (String str2 : f299a) {
            d(str2, false);
        }
    }

    private String f(String str) {
        return mrv.d + "health_model_res" + File.separator + str + File.separator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public void e(final String str) {
        if (HandlerExecutor.c()) {
            azi.b(this.f, "HealthModelUpdateIndexFile", new Runnable() { // from class: bag
                @Override // java.lang.Runnable
                public final void run() {
                    bad.this.e(str);
                }
            });
        } else {
            LogUtil.c("HealthLife_DownloadUtils", "updateIndexFile url ", this.d.e(new GrsDownloadPluginUrl().getDownloadPluginUrl(null, true), (String) null));
            this.d.e(new b(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, final String str2) {
        if (d(str)) {
            this.d.e(str, new PullListener() { // from class: baf
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    bad.this.d(str, str2, msqVar, msoVar);
                }
            });
        }
    }

    /* synthetic */ void d(String str, String str2, msq msqVar, mso msoVar) {
        if (msoVar == null) {
            LogUtil.h("HealthLife_DownloadUtils", "updatePluginInfo onPullingChange result is null");
            azi.a(0L);
            return;
        }
        int i = msoVar.i();
        if (i != 1) {
            if (i == 0) {
                return;
            }
            LogUtil.h("HealthLife_DownloadUtils", "updatePluginInfo onPullingChange error uuid ", str, " status ", Integer.valueOf(i));
            azi.a(0L);
            a(str, i);
            if ("health_model_config".equals(str)) {
                awb.e(false);
            }
            aza.a(str2, str, i);
            return;
        }
        msa c2 = this.d.c(str);
        if (c2 == null) {
            LogUtil.h("HealthLife_DownloadUtils", "updatePluginInfo onPullingChange pluginInfo is null ", str);
            azi.a(0L);
        } else {
            LogUtil.a("HealthLife_DownloadUtils", "updatePluginInfo onPullingChange uuid ", str);
            bao.c(str, c2.h());
            d(str, true);
        }
        a(str, nsn.e("0"));
        aza.a(str2, str, 1);
    }

    private boolean d(String str) {
        msa c2 = this.d.c(str);
        if (c2 == null) {
            LogUtil.h("HealthLife_DownloadUtils", "pluginInfo is null ", str);
            return false;
        }
        String a2 = bao.a(str);
        return azi.h(f(str)) || TextUtils.isEmpty(a2) || !a2.equals(c2.h());
    }

    private void a(String str, boolean z) {
        if (z) {
            c(str, true);
        } else if (TextUtils.isEmpty(c(str))) {
            LogUtil.h("HealthLife_DownloadUtils", "refreshSharedPreferenceValue value is empty scenario ", str);
            c(str, false);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str, boolean z) {
        char c2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_DownloadUtils", "initSharedPreferenceValue uuid is empty");
            return;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1752888737:
                if (str.equals("health_model_semantic")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1241681381:
                if (str.equals("health_model_config")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 49611807:
                if (str.equals("health_model_update_guide")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 351738503:
                if (str.equals("health_model_guide_img")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 915426978:
                if (str.equals("health_model_task_img")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            for (String str2 : bcc.b()) {
                a(str2, z);
            }
            return;
        }
        if (c2 == 1) {
            if (z) {
                awb.e(true);
            }
            a("health_model_task_config.json", z);
        } else {
            if (c2 == 2) {
                a("1001", z);
                return;
            }
            if (c2 == 3) {
                a("1000", z);
                return;
            }
            if (c2 == 4) {
                a("1", z);
                a("2", z);
                a("80", z);
                a("100", z);
                return;
            }
            LogUtil.c("HealthLife_DownloadUtils", "initSharedPreferenceValue uuid ", str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(String str, int i) {
        char c2;
        OperationKey operationKey;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_DownloadUtils", "reportErrorCode uuid is empty");
            return;
        }
        switch (str.hashCode()) {
            case -1752888737:
                if (str.equals("health_model_semantic")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -1241681381:
                if (str.equals("health_model_config")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -695464593:
                if (str.equals("health_model_config/challenge")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 49611807:
                if (str.equals("health_model_update_guide")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 351738503:
                if (str.equals("health_model_guide_img")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 915426978:
                if (str.equals("health_model_task_img")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0 || c2 == 1) {
            operationKey = OperationKey.HEALTH_APP_CONFIGURATION_FILES_DOWNLOAD_85070009;
        } else if (c2 == 2) {
            operationKey = OperationKey.HEALTH_APP_SEMANTIC_LIBRARY_DOWNLOAD_85070007;
        } else {
            operationKey = OperationKey.HEALTH_APP_PICTURE_DOWNLOAD_85070008;
        }
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(operationKey.value(), i);
    }

    public String e() {
        return f("health_model_semantic");
    }

    public String c() {
        return f("health_model_config/challenge");
    }

    private void c(final String str, final boolean z) {
        azi.b(this.f, "HealthModelGetResources_" + str, new Runnable() { // from class: bai
            @Override // java.lang.Runnable
            public final void run() {
                bad.this.b(str, z);
            }
        });
    }

    /* synthetic */ void b(String str, boolean z) {
        String j = j(str);
        if (TextUtils.isEmpty(j)) {
            LogUtil.h("HealthLife_DownloadUtils", "setSharedPreferenceValue directoryName is empty");
            return;
        }
        String g = g(str);
        if (TextUtils.isEmpty(g)) {
            LogUtil.h("HealthLife_DownloadUtils", "setSharedPreferenceValue fileName is empty");
            return;
        }
        String str2 = f(j) + g;
        if (azi.h(str2)) {
            LogUtil.h("HealthLife_DownloadUtils", "setSharedPreferenceValue filePath is invalid file isRetry ", Boolean.valueOf(z), " scenario ", str, " filePath ", str2);
            if (z) {
                b("HealthLife_DownloadUtils");
                return;
            }
            return;
        }
        String t = CommonUtil.t(str2);
        if (TextUtils.isEmpty(t)) {
            LogUtil.h("HealthLife_DownloadUtils", "setSharedPreferenceValue json is empty isRetry ", Boolean.valueOf(z), " scenario ", str);
            if (z) {
                b("HealthLife_DownloadUtils");
                return;
            }
            return;
        }
        bao.c(h(str), t);
        LogUtil.a("HealthLife_DownloadUtils", "setSharedPreferenceValue json ", t);
    }

    private String h(String str) {
        String j = j(str);
        if (TextUtils.isEmpty(j)) {
            LogUtil.h("HealthLife_DownloadUtils", "getSharedPreferenceKey directoryName is empty");
            return "";
        }
        String g = g(str);
        if (TextUtils.isEmpty(g)) {
            LogUtil.h("HealthLife_DownloadUtils", "getSharedPreferenceKey fileName is empty");
            return "";
        }
        return j + Constants.LINK + g + Constants.LINK + this.b;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String j(String str) {
        char c2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_DownloadUtils", "getDirectoryName scenario is empty");
            return "";
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1788230669:
                if (str.equals("health_model_task_config.json")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 49:
                if (str.equals("1")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 50:
                if (str.equals("2")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1784:
                if (str.equals("80")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 48625:
                if (str.equals("100")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1507423:
                if (str.equals("1000")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1507424:
                if (str.equals("1001")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return "health_model_config";
            case 1:
            case 2:
            case 3:
            case 4:
                return "health_model_task_img";
            case 5:
                return "health_model_guide_img";
            case 6:
                return "health_model_update_guide";
            default:
                return "health_model_semantic";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String g(String str) {
        char c2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_DownloadUtils", "getImageFileName scenario is empty");
            return "";
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 50:
                if (str.equals("2")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1784:
                if (str.equals("80")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 48625:
                if (str.equals("100")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1507423:
                if (str.equals("1000")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1507424:
                if (str.equals("1001")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        return (c2 == 0 || c2 == 1 || c2 == 2 || c2 == 3) ? "task_image_config.json" : c2 != 4 ? c2 != 5 ? str : "update_config.json" : "join_config.json";
    }

    private ArrayList<ImageBean> a(JSONArray jSONArray, String str, String str2) {
        boolean z = "1000".equals(str2) || "1001".equals(str2);
        boolean isEmpty = TextUtils.isEmpty(str2);
        ArrayList<ImageBean> arrayList = new ArrayList<>(16);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("imageScenario");
                if (z || isEmpty || str2.equals(optString)) {
                    ImageBean imageBean = new ImageBean();
                    imageBean.setVersionRange(optJSONObject.optString("versionRange"));
                    imageBean.setVersionRangeBeta(optJSONObject.optString("versionRangeBeta"));
                    imageBean.setId(nsn.e(optJSONObject.optString("id")));
                    imageBean.setOrder(nsn.e(optJSONObject.optString("order")));
                    imageBean.setImageScenario(optString);
                    imageBean.setPictureList(e(optJSONObject, str));
                    imageBean.setTextList(bcc.d(optJSONObject));
                    arrayList.add(imageBean);
                }
            }
        }
        return arrayList;
    }

    private ArrayList<PictureBean> e(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray("pictureList");
        if (optJSONArray == null) {
            LogUtil.h("HealthLife_DownloadUtils", "getPictureBeanList jsonArray is null");
            return new ArrayList<>(16);
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            LogUtil.h("HealthLife_DownloadUtils", "getPictureBeanList length ", Integer.valueOf(length));
            return new ArrayList<>(16);
        }
        ArrayList<PictureBean> arrayList = new ArrayList<>(16);
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            String optString = optJSONObject.optString(ContentResource.FILE_NAME);
            if (!TextUtils.isEmpty(optString)) {
                PictureBean pictureBean = new PictureBean();
                pictureBean.setPath(str + optString);
                pictureBean.setSize(optJSONObject.optLong("size"));
                pictureBean.setMode(optJSONObject.optInt(Wpt.MODE));
                pictureBean.setScenario(optJSONObject.optInt(ParsedFieldTag.KAKA_TASK_SCENARIO));
                arrayList.add(pictureBean);
            }
        }
        return arrayList;
    }

    public String c(String str) {
        String h = h(str);
        if (TextUtils.isEmpty(h)) {
            LogUtil.h("HealthLife_DownloadUtils", "getSharedPreferenceValue key is empty");
            return "";
        }
        return CommonUtil.z(bao.a(h));
    }

    public ArrayList<ImageBean> a(String str) {
        String c2 = c(str);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("HealthLife_DownloadUtils", "getImageList value is empty");
            c(str, true);
            return new ArrayList<>(16);
        }
        ArrayList<ImageBean> arrayList = new ArrayList<>(16);
        try {
            arrayList = a(new JSONArray(c2), f(j(str)), str);
        } catch (JSONException e2) {
            LogUtil.b("HealthLife_DownloadUtils", "setImageList exception ", LogAnonymous.b((Throwable) e2));
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthLife_DownloadUtils", "getImageList imageBeanList is empty");
            c(str, true);
            return new ArrayList<>(16);
        }
        LogUtil.c("HealthLife_DownloadUtils", "getImageList imageBeanList ", arrayList);
        return arrayList;
    }

    static class b implements PullListener {

        /* renamed from: a, reason: collision with root package name */
        private final String f300a;
        private final WeakReference<bad> c;

        private b(bad badVar, String str) {
            this.c = new WeakReference<>(badVar);
            this.f300a = str;
        }

        @Override // com.huawei.pluginmgr.filedownload.PullListener
        public void onPullingChange(msq msqVar, mso msoVar) {
            bad badVar = this.c.get();
            if (badVar == null || msoVar == null) {
                LogUtil.h("HealthLife_DownloadUtils", "MyPullListener onPullingChange downloadUtils ", badVar, " pullResult ", msoVar);
                return;
            }
            int i = msoVar.i();
            if (i != 1) {
                if (i != 0) {
                    LogUtil.h("HealthLife_DownloadUtils", "mUpdateIndexFileListener onPullingChange status ", Integer.valueOf(i));
                    azi.a(0L);
                    awb.e(false);
                    aza.a(this.f300a, "health_model_index", i);
                    return;
                }
                return;
            }
            aza.a(this.f300a, "health_model_index", 1);
            if (!koq.b(badVar.d.b())) {
                badVar.d.a(msoVar.a());
                for (String str : bad.f299a) {
                    badVar.d(str, this.f300a);
                }
                return;
            }
            LogUtil.h("HealthLife_DownloadUtils", "mUpdateIndexFileListener onPullingChange PluginIndexInfoList is empty");
            azi.a(0L);
        }
    }
}
