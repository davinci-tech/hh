package defpackage;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.EzPluginPullerCallback;
import com.huawei.pluginmgr.filedownload.PullListener;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.IoUtils;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class msj {
    private static final Object d = new Object();
    private static msj e;
    private Context c = BaseApplication.getContext();

    private msj() {
    }

    public static msj a() {
        msj msjVar;
        synchronized (d) {
            if (e == null) {
                e = new msj();
            }
            msjVar = e;
        }
        return msjVar;
    }

    public void b(msq msqVar) {
        msn.c().c(msqVar);
    }

    public msq c(String str) {
        return msn.c().e(str);
    }

    public void a(String str, mru mruVar, String str2, boolean z, PullListener pullListener) {
        LogUtil.a("EzPlugin_EzPluginPuller", "pullIndexFile pull json task");
        msq b = b(str, mruVar, str2, z, pullListener);
        b.i(d(str, b, true));
        if (msr.d.containsValue(str) || "device_index_all".equals(str) || "plugin_index".equals(str) || "index_sport_intensity".equals(b.c())) {
            c(str, mruVar, b);
            b.g("GET");
        }
        msn.c().a(b);
    }

    private msq b(String str, mru mruVar, String str2, boolean z, PullListener pullListener) {
        msq msqVar = new msq();
        msqVar.a(str2);
        msqVar.h(mruVar.d());
        msqVar.e(mruVar.b());
        msqVar.b(pullListener);
        msqVar.d(0);
        msqVar.c(z);
        msqVar.b(str);
        return msqVar;
    }

    private void c(String str, mru mruVar, msq msqVar) {
        String d2 = mruVar.d();
        if ("device_index_all".equals(str)) {
            d2 = mruVar.d() + "device_index_all";
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1003), d2);
        if (TextUtils.isEmpty(b)) {
            return;
        }
        msqVar.d(b);
    }

    private void e(String str, String str2, String str3, String str4, PullListener pullListener) {
        LogUtil.a("EzPlugin_EzPluginPuller", "pullDescriptionFile pull description task");
        msq msqVar = new msq();
        msqVar.i(d(b(str, str2, str3, pullListener, msqVar), msqVar, false));
        msqVar.e(str4);
        msn.c().a(msqVar);
    }

    private String b(String str, String str2, String str3, PullListener pullListener, msq msqVar) {
        msqVar.a(str3);
        msqVar.h(str2);
        msqVar.b(pullListener);
        msqVar.d(0);
        String str4 = str + "_description";
        msqVar.b(str4);
        return str4;
    }

    private String d(String str, msq msqVar, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!e(jSONObject, msqVar, z, true)) {
                jSONObject.put(RecommendConstants.FILE_ID, str);
                jSONObject.put(RecommendConstants.VER, "0");
            }
        } catch (JSONException unused) {
            LogUtil.b("EzPlugin_EzPluginPuller", "getDownloadParam JSONException");
        }
        return jSONObject.toString().trim();
    }

    private boolean e(JSONObject jSONObject, msq msqVar, boolean z, boolean z2) throws JSONException {
        String j = msqVar.j();
        if (TextUtils.isEmpty(j)) {
            return false;
        }
        int indexOf = j.indexOf("/commonAbility/configCenter/");
        msqVar.d(indexOf > 0);
        if (!z || !msqVar.h()) {
            return false;
        }
        msqVar.g("POST");
        a(jSONObject, msqVar, j, indexOf, z2);
        a(jSONObject);
        return true;
    }

    private void a(JSONObject jSONObject, msq msqVar, String str, int i, boolean z) throws JSONException {
        jSONObject.put("ts", System.currentTimeMillis());
        jSONObject.put("source", 1);
        int i2 = i + 28;
        msqVar.h(str.substring(0, i2) + "getConfigInfo");
        int indexOf = str.indexOf("?", i2);
        if (indexOf > 0) {
            jSONObject.put("configId", str.substring(i2, indexOf));
            if (z) {
                d(jSONObject, str.substring(indexOf + 1));
                return;
            }
            return;
        }
        jSONObject.put("configId", str.substring(i2));
    }

    private void d(JSONObject jSONObject, String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = str.split("=");
        if (split.length == 0 || split.length % 2 != 0) {
            LogUtil.h("EzPlugin_EzPluginPuller", "not support queryString=\"", str, "\"");
            return;
        }
        HashMap hashMap = new HashMap(split.length / 2);
        for (int i = 0; i < split.length; i += 2) {
            hashMap.put(split[i], split[i + 1]);
        }
        jSONObject.put("matchRules", new JSONObject(hashMap));
    }

    private void a(JSONObject jSONObject) throws JSONException {
        String e2 = msn.e();
        if (TextUtils.isEmpty(e2)) {
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("Device-ID", e2);
        jSONObject.put("greyRules", new JSONObject(hashMap));
    }

    public void b(String str, String str2, String str3, msc mscVar, PullListener pullListener) {
        LogUtil.a("EzPlugin_EzPluginPuller", "fileUrlJson is ", str3);
        a(str, new mru(str, str2, str3, mscVar), null, pullListener);
    }

    public void a(mru mruVar, String str, PullListener pullListener) {
        a(mruVar.c(), mruVar, str, pullListener);
    }

    public void a(String str, mru mruVar, PullListener pullListener) {
        a(str, mruVar, d(mruVar.c()), pullListener);
    }

    private void a(final String str, mru mruVar, String str2, final PullListener pullListener) {
        b(str, mruVar, str2, new PullListener() { // from class: msj.2
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar != null && msoVar.i() == 1) {
                    LogUtil.a("EzPlugin_EzPluginPuller", "onPullingChange onResult is 200");
                    String e2 = msqVar.e();
                    if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
                        e2 = msj.this.d(str);
                    }
                    msj.this.d(e2, pullListener, msqVar, msoVar);
                    return;
                }
                PullListener pullListener2 = pullListener;
                if (pullListener2 != null) {
                    pullListener2.onPullingChange(msqVar, msoVar);
                }
            }
        });
    }

    private void b(String str, mru mruVar, String str2, PullListener pullListener) {
        msq msqVar = new msq();
        msc a2 = mruVar.a();
        msqVar.h(mruVar.j());
        msqVar.e(mruVar.b());
        if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
            String str3 = str + "_new_v1.1";
            msqVar.i(d(str3, msqVar, false));
            msqVar.a(mrv.e);
            msqVar.b(str3);
        } else {
            msqVar.i(d(str, msqVar, false));
            msqVar.b(str);
        }
        b(mruVar, str2, pullListener, msqVar, a2);
    }

    private void b(mru mruVar, String str, PullListener pullListener, msq msqVar, msc mscVar) {
        if (TextUtils.isEmpty(str)) {
            str = d(mruVar.c());
        }
        msqVar.a(str);
        msqVar.c(mscVar.b());
        msqVar.b(pullListener);
        msqVar.d(CommonUtil.m(this.c, mscVar.d()));
        msqVar.b(mscVar.c());
        msqVar.f(mruVar.c());
        if (e() > mscVar.c()) {
            LogUtil.a("EzPlugin_EzPluginPuller", "addTask plugin task");
            msn.c().a(msqVar);
        } else {
            LogUtil.a("EzPlugin_EzPluginPuller", "addTask this torage is not enough");
            mso msoVar = new mso();
            msoVar.b(-9);
            pullListener.onPullingChange(msqVar, msoVar);
        }
    }

    public void c(String str, String str2, PullListener pullListener, String str3) {
        LogUtil.a("EzPlugin_EzPluginPuller", "downloadDescription eneter downlodDescription.fileUrlJson is ", str3);
        b(str, str2, mrv.d + str + File.separator + "description.json", str3, pullListener);
    }

    public void b(String str, String str2, final String str3, String str4, final PullListener pullListener) {
        e(str, str2, str3, str4, new PullListener() { // from class: msj.1
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                msj.this.a(msqVar, msoVar, str3, pullListener, (EzPluginPullerCallback) null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(msq msqVar, mso msoVar, String str, PullListener pullListener, EzPluginPullerCallback ezPluginPullerCallback) {
        if (msoVar == null) {
            LogUtil.h("EzPlugin_EzPluginPuller", "downloadDescriptionFile onPullingChange result is null");
            pullListener.onPullingChange(msqVar, null);
            return;
        }
        int i = msoVar.i();
        if (i != 1) {
            if (i == 0) {
                return;
            }
            pullListener.onPullingChange(msqVar, msoVar);
            return;
        }
        String b = b(this.c, str);
        LogUtil.c("EzPlugin_EzPluginPuller", "downloadDescriptionFile descriptionJson success");
        msc d2 = msb.d(b);
        String o = msqVar.o();
        if (ezPluginPullerCallback == null) {
            EzPluginManager.a().d(d2, o);
        } else {
            ezPluginPullerCallback.setEzPluginInfoCache(d2, o);
        }
        msoVar.a(d2.c());
        pullListener.onPullingChange(msqVar, msoVar);
    }

    private String b(Context context, String str) {
        FileInputStream fileInputStream;
        LogUtil.a("EzPlugin_EzPluginPuller", "enter readFileToData: filePath = ", str);
        FileInputStream fileInputStream2 = null;
        if (str == null || context == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(1024);
        try {
            try {
                fileInputStream = new FileInputStream(new File(str));
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            fileInputStream = fileInputStream2;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                sb.append(new String(bArr, 0, read, "UTF-8"));
            }
            IoUtils.e(fileInputStream);
        } catch (IOException unused2) {
            fileInputStream2 = fileInputStream;
            LogUtil.b("EzPlugin_EzPluginPuller", "readFileToData IOException");
            IoUtils.e(fileInputStream2);
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileInputStream);
            throw th;
        }
        return sb.toString();
    }

    public String d(String str) {
        if ("6d5416d9-2167-41df-ab10-c492e152b44f".equals(str)) {
            return mrv.e;
        }
        return mrv.d + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, PullListener pullListener, msq msqVar, mso msoVar) {
        FileOutputStream fileOutputStream;
        String valueOf;
        LogUtil.a("EzPlugin_EzPluginPuller", "createDoneMarkFile enter create done file, path is ", str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File file = new File(str + File.separator + "done");
                if (!file.getParentFile().exists()) {
                    LogUtil.a("EzPlugin_EzPluginPuller", "createDoneMarkFile isDirMade is ", Boolean.valueOf(file.mkdir()));
                }
                if (!file.exists()) {
                    LogUtil.a("EzPlugin_EzPluginPuller", "createDoneMarkFile isNewFileCreated is ", Boolean.valueOf(file.createNewFile()));
                }
                valueOf = String.valueOf(new Date().getTime() / 1000);
                LogUtil.a("EzPlugin_EzPluginPuller", "createDoneMarkFile timeStamp is =", valueOf);
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            th = th;
            fileOutputStream = fileOutputStream2;
        }
        try {
            byte[] bytes = valueOf.getBytes("UTF-8");
            fileOutputStream.write(bytes, 0, bytes.length);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
            }
            IoUtils.e(fileOutputStream);
        } catch (IOException unused2) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("EzPlugin_EzPluginPuller", "createDoneMarkFile createDoneFile IOException");
            msoVar.b(-1);
            if (pullListener != null) {
                pullListener.onPullingChange(msqVar, msoVar);
            }
            IoUtils.e(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileOutputStream);
            throw th;
        }
    }

    public static long e() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(CommonUtil.j((Context) null).getPath());
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        } catch (IllegalArgumentException unused) {
            LogUtil.b("EzPlugin_EzPluginPuller", "getAvailCount Exception");
            return -1L;
        }
    }
}
