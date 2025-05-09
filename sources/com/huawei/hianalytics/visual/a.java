package com.huawei.hianalytics.visual;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.TransportHandlerFactory;
import com.huawei.hianalytics.core.transport.net.Response;
import com.huawei.hianalytics.framework.utils.JsonUtils;
import com.huawei.hianalytics.util.HwSfpPolicyUtils;
import com.huawei.hianalytics.visual.view.model.config.AbConfigResponse;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {
    public static String a(String str, String str2, String str3) {
        Response execute = TransportHandlerFactory.create(str2, null, str.getBytes(StandardCharsets.UTF_8), 1).execute(str3);
        return execute.getHttpCode() != 200 ? "" : execute.getContent();
    }

    public static void b() {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.a$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                a.a();
            }
        });
    }

    public static List<AbConfigResponse.AbConfigResult.FeatureConfigValue> c(String str) {
        AbConfigResponse abConfigResponse = (AbConfigResponse) JsonUtils.toObjectNoException(str, AbConfigResponse.class, AbConfigResponse.AbConfigResult.class, AbConfigResponse.AbConfigResult.FeatureConfigValue.class);
        if (abConfigResponse == null) {
            return new ArrayList();
        }
        AbConfigResponse.AbConfigResult result = abConfigResponse.getResult();
        if (result == null) {
            return new ArrayList();
        }
        List<AbConfigResponse.AbConfigResult.FeatureConfigValue> featureConfigValues = result.getFeatureConfigValues();
        return (featureConfigValues == null || featureConfigValues.isEmpty()) ? new ArrayList() : featureConfigValues;
    }

    public static String a(Context context, String str) {
        GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
        grsBaseInfo.setSerCountry(GrsApp.getInstance().getIssueCountryCode(context));
        try {
            return new GrsClient(context, grsBaseInfo).synGetGrsUrl("com.huawei.hianalytics.sdk", str);
        } catch (NullPointerException e) {
            HiLog.e("GrsUtils", "getRemoteUrl NullPointerException:" + e.getMessage());
            return "";
        }
    }

    public static boolean a(View view) {
        if (view == null || !Pattern.matches("^([A-Za-z]|[0-9])", o0.c(view))) {
            return false;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup == null) {
                return false;
            }
            if (viewGroup.getTag(R.id.hianalytics_keyboard_view_tag) == null) {
                int indexOfChild = viewGroup.indexOfChild(view);
                int childCount = viewGroup.getChildCount();
                if (childCount > 1) {
                    for (int i = 0; i < childCount; i++) {
                        if (i != indexOfChild && Pattern.matches("^([A-Za-z]|[0-9])", o0.e(viewGroup.getChildAt(i)))) {
                            viewGroup.setTag(R.id.hianalytics_keyboard_view_tag, Boolean.TRUE);
                        }
                    }
                    return b(viewGroup);
                }
                return b(viewGroup);
            }
            return true;
        }
        return b((View) view.getParent());
    }

    public static void b(Context context, HAAutoConfigOptions hAAutoConfigOptions) {
        String str;
        String str2;
        String str3;
        String createABReqDefaultJson = JsonUtils.createABReqDefaultJson();
        if (context == null) {
            HiLog.e("JsCodeUtils", "get h5 js code url but context is null");
            str3 = null;
        } else {
            String jsSdkUrlAndVersion = hAAutoConfigOptions.getJsSdkUrlAndVersion();
            if (TextUtils.isEmpty(jsSdkUrlAndVersion)) {
                HiLog.d("JsCodeUtils", "get h5 js sdk url or version is null, get url from grs");
                str2 = a(context, "H5JSCODEURL");
                if (!TextUtils.isEmpty(str2)) {
                    str = "ha_2.2.8-500";
                    str3 = str2 + "/cch5/apms/{jssdk_version}/h5/autotracker.min.js".replace("{jssdk_version}", str);
                }
                str3 = "";
            } else {
                String[] split = jsSdkUrlAndVersion.split("\\|");
                if (split.length != 2) {
                    HiLog.e("JsCodeUtils", "please set right h5 js sdk url or version");
                    str3 = "";
                } else {
                    String str4 = split[0];
                    str = split[1];
                    str2 = str4;
                    str3 = str2 + "/cch5/apms/{jssdk_version}/h5/autotracker.min.js".replace("{jssdk_version}", str);
                }
            }
        }
        if (TextUtils.isEmpty(str3)) {
            HiLog.d("JsCodeRequestBackend", "url is inValid");
            return;
        }
        String a2 = a(createABReqDefaultJson, str3, "GET");
        if (TextUtils.isEmpty(a2)) {
            HiLog.e("JsCodeRequestBackend", "fail to request h5 js code");
            return;
        }
        HiLog.d("JsCodeRequestBackend", "success to request h5 js code");
        String str5 = b.a().g().getFilesDir() + File.separator + "haJsSdk";
        String str6 = str5 + File.separator + "haJsCode.js";
        b(str5, "haJsCode.js", a2);
        HwSfpPolicyUtils.setPublicProtectionLevel(b.a().g(), str6, 0);
    }

    public static void a() {
        JSONObject jSONObject = new JSONObject();
        b0 b0Var = (b0) d0.a("app_install");
        if (b0Var == null) {
            HiLog.d("AppInstallUtils", "fail to collect app install event");
        } else {
            if (b0Var.a().booleanValue()) {
                return;
            }
            b.a().a("$AppInstall", jSONObject);
            b0Var.a(Boolean.TRUE);
        }
    }

    public static void a(final Context context, final HAAutoConfigOptions hAAutoConfigOptions) {
        m0.a().execute(new Runnable() { // from class: com.huawei.hianalytics.visual.a$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                a.b(context, hAAutoConfigOptions);
            }
        });
    }

    public static void b(String str, String str2, String str3) {
        if (!a(str)) {
            return;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(str, str2), false), StandardCharsets.UTF_8));
            try {
                bufferedWriter.write(str3);
                bufferedWriter.close();
            } finally {
            }
        } catch (IOException e) {
            HiLog.e("FileUtils", "write file failed. Cause: " + e.getMessage());
        }
    }

    public static void b(String str) {
        File file;
        File[] listFiles;
        if (TextUtils.isEmpty(str) || (listFiles = (file = new File(str)).listFiles()) == null || listFiles.length == 0 || listFiles.length <= 10) {
            return;
        }
        Arrays.sort(listFiles, new Comparator() { // from class: com.huawei.hianalytics.visual.a$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return a.a((File) obj, (File) obj2);
            }
        });
        listFiles[0].delete();
        File[] listFiles2 = file.listFiles();
        if (listFiles2 == null || listFiles2.length == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (File file2 : listFiles2) {
            if (currentTimeMillis - file2.lastModified() > 86400000) {
                file2.delete();
            }
        }
    }

    public static String a(String str, String str2) {
        FileInputStream fileInputStream;
        if (!a(str)) {
            return "";
        }
        File file = new File(str, str2);
        StringBuilder sb = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException e) {
            HiLog.e("FileUtils", "read file failed. Cause: " + e.getMessage());
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                long j = 0;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            j += readLine.length();
                            if (j <= 1048576) {
                                if (readLine.length() > 0) {
                                    sb.append(readLine);
                                }
                            } else {
                                throw new IOException("read file failed. Cause: the input stream length is too long");
                            }
                        } else {
                            bufferedReader.close();
                            inputStreamReader.close();
                            fileInputStream.close();
                            break;
                        }
                    } finally {
                    }
                }
                return sb.toString().trim();
            } finally {
            }
        } finally {
        }
    }

    public static boolean b(View view) {
        ViewGroup viewGroup;
        if (view == null || !(view.getParent() instanceof ViewGroup) || (viewGroup = (ViewGroup) view.getParent()) == null) {
            return false;
        }
        if (viewGroup.getTag(R.id.hianalytics_keyboard_view_tag) != null) {
            return true;
        }
        int childCount = viewGroup.getChildCount();
        if (childCount <= 1) {
            return false;
        }
        int indexOfChild = viewGroup.indexOfChild(view);
        for (int i = 0; i < childCount; i++) {
            if (i != indexOfChild) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getTag(R.id.hianalytics_keyboard_view_tag) == null) {
                    if (childAt instanceof ViewGroup) {
                        ViewGroup viewGroup2 = (ViewGroup) childAt;
                        int childCount2 = viewGroup2.getChildCount();
                        for (int i2 = 0; i2 < childCount2; i2++) {
                            if (Pattern.matches("^([A-Za-z]|[0-9])", o0.e(viewGroup2.getChildAt(i2)))) {
                                viewGroup2.setTag(R.id.hianalytics_keyboard_view_tag, Boolean.TRUE);
                                viewGroup.setTag(R.id.hianalytics_keyboard_view_tag, Boolean.TRUE);
                            }
                        }
                    } else if (Pattern.matches("^([A-Za-z]|[0-9])", o0.c(childAt))) {
                        childAt.setTag(R.id.hianalytics_keyboard_view_tag, Boolean.TRUE);
                        viewGroup.setTag(R.id.hianalytics_keyboard_view_tag, Boolean.TRUE);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ int a(File file, File file2) {
        if (file.lastModified() == file2.lastModified()) {
            return file.compareTo(file2);
        }
        return file.lastModified() - file2.lastModified() <= 0 ? -1 : 1;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }
}
