package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.alipay.sdk.m.p.e;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwvoicekitmgr.HwControlLocalAppHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import health.compact.a.WhiteBoxManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kfl {
    private static int c = -1;
    private static int d = -1;

    public static String a(Context context) {
        Object systemService = context.getSystemService("media_session");
        List<MediaController> list = null;
        MediaSessionManager mediaSessionManager = systemService instanceof MediaSessionManager ? (MediaSessionManager) systemService : null;
        if (mediaSessionManager == null) {
            LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "mediaSessionManager is null");
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        try {
            list = mediaSessionManager.getActiveSessions(new ComponentName(context.getPackageName(), "com.huawei.bone.ui.setting.NotificationPushListener"));
        } catch (SecurityException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "getMediaPackage SecurityException");
        }
        if (list == null || list.isEmpty()) {
            return sb.toString();
        }
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getPackageName());
            if (i != list.size() - 1) {
                sb.append(Constants.LINK);
            }
        }
        return sb.toString();
    }

    public static String d(String str) {
        StringBuilder sb = new StringBuilder(16);
        WhiteBoxManager d2 = WhiteBoxManager.d();
        for (String str2 : str.split(Constants.LINK)) {
            sb.append(d2.d(1, jds.c(str2, 10)));
        }
        return sb.toString();
    }

    public static String d(JSONArray jSONArray) {
        String str = "";
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && optJSONObject.optJSONObject("header") != null && optJSONObject.optJSONObject("payload") != null && "NLPRecognizer".equals(optJSONObject.optJSONObject("header").optString("name"))) {
                str = optJSONObject.optJSONObject("payload").optString("domainId");
            }
        }
        return str;
    }

    public static void c(JSONObject jSONObject, kfm kfmVar) {
        JSONArray optJSONArray = jSONObject.optJSONArray("backUpAppPackageList");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        StringBuilder sb = new StringBuilder(10);
        sb.append("packageList:");
        for (int i = 0; i < optJSONArray.length(); i++) {
            String optString = optJSONArray.optString(i);
            arrayList.add(optString);
            sb.append(" ");
            sb.append(optString);
        }
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", sb.toString());
        kfmVar.b(arrayList);
    }

    public static void c(Context context, kfm kfmVar) {
        String c2 = kfmVar.c();
        String b = kfmVar.b();
        if (TextUtils.isEmpty(c2)) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "empty url.");
            return;
        }
        try {
            Intent intent = new Intent(!TextUtils.isEmpty(kfmVar.a()) ? kfmVar.a() : CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(c2));
            if (!TextUtils.isEmpty(b)) {
                intent.setPackage(b);
            }
            intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
            if (intent.resolveActivity(BaseApplication.getContext().getPackageManager()) == null) {
                bNy_(intent, kfmVar);
            }
            BaseApplication.getContext().startActivity(intent);
            a(context, b);
        } catch (ActivityNotFoundException | SecurityException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "cannot found activity to handle url: ", c2);
        }
    }

    public static void a(final Context context, final String str) {
        String a2 = a(context);
        if (!TextUtils.isEmpty(a2) && !a2.contains(str)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kfl.3
                @Override // java.lang.Runnable
                public void run() {
                    int i = 0;
                    while (i <= 20) {
                        try {
                            Thread.sleep(200L);
                        } catch (InterruptedException unused) {
                            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "sendOpenMusicCommand InterruptedException");
                        }
                        i++;
                        if (kfl.a(context).contains(str) || kfl.a(context).contains("com.android.mediacenter")) {
                            kfk.c(1);
                            return;
                        }
                    }
                    if (i > 20) {
                        kfk.e(255);
                    }
                }
            });
        } else {
            kfk.c(1);
        }
    }

    private static void bNy_(Intent intent, kfm kfmVar) {
        if (intent == null || kfmVar == null) {
            return;
        }
        List<String> d2 = kfmVar.d();
        if (d2 == null || d2.size() == 0) {
            LogUtil.h("HwVoiceKitManager_VoiceKitUtils", "backUpAppPackageList is invalid");
            return;
        }
        for (String str : d2) {
            intent.setPackage(str);
            if (intent.resolveActivity(BaseApplication.getContext().getPackageManager()) != null) {
                LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "backUpAppPackage is available: ", str);
                return;
            }
        }
    }

    private static String d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            int length = str.getBytes("UTF-8").length;
            if (length > i) {
                return str.substring(0, (int) (str.length() * (i / length)));
            }
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "cutText find UnsupportedEncodingException");
        }
        return str;
    }

    public static String b(String str) {
        return d(str, 1024);
    }

    public static String a(String str) {
        return d(str, 1024);
    }

    public static boolean d(JSONArray jSONArray, String str, HwControlLocalAppHelper hwControlLocalAppHelper) {
        JSONArray optJSONArray;
        boolean z = false;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("value")) != null && optJSONArray.length() != 0 && optJSONArray.optJSONObject(0) != null) {
                String optString = optJSONArray.optJSONObject(0).optString("normalValue");
                if (a("1012100200260000-1012100200270000-1012100200280000-1012100200290000".split(Constants.LINK), str)) {
                    z = e("{\"running\":\"1\",\"cycling\":\"3\",\"walking\":\"2\"}", optString, hwControlLocalAppHelper);
                }
                if (a("2001100300010000-2001100300020000".split(Constants.LINK), str)) {
                    z = e("{\"闹钟\":\"2\",\"支付宝|Alipay\":\"1\"}", optString, hwControlLocalAppHelper);
                }
                if (z) {
                    break;
                }
            }
        }
        return z;
    }

    public static boolean a(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean e(String str, String str2, HwControlLocalAppHelper hwControlLocalAppHelper) {
        try {
            Iterator<String> keys = new JSONObject(str).keys();
            while (keys.hasNext()) {
                if (hwControlLocalAppHelper.e(str2, keys.next())) {
                    return true;
                }
            }
            return false;
        } catch (JSONException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "isContainKey JSONException");
            return false;
        }
    }

    public static void d(JSONObject jSONObject, String str, String str2) {
        String str3;
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "processOnResult processEncyclopedia displayAsr:", str, ", displayText:", str2);
        JSONObject optJSONObject = jSONObject.optJSONObject("cardParams");
        if (optJSONObject == null) {
            LogUtil.h("HwVoiceKitManager_VoiceKitUtils", "processEncyclopedia cardParams is null");
            kfk.b(b(str), a(str2));
            return;
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("items");
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("dataSource");
        if (optJSONArray == null || optJSONArray.length() <= 0 || optJSONArray2 == null) {
            str3 = "";
        } else {
            str3 = optJSONArray.optJSONObject(0).optString("brief");
            LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "processOnResult brief : ", str3);
        }
        JSONArray optJSONArray3 = optJSONObject.optJSONArray("voicePlay");
        if (optJSONArray3 != null && optJSONArray3.length() > 0) {
            str3 = optJSONArray3.optJSONObject(0).optString(Constant.TEXT);
            LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "processOnResult translatorText : ", str3);
        }
        if (!TextUtils.isEmpty(str2) && !str3.equals(str2)) {
            str3 = str2 + System.lineSeparator() + str3;
        }
        String b = b(str);
        if (!TextUtils.isEmpty(str3)) {
            str2 = str3;
        }
        kfk.b(b, a(str2));
    }

    public static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("R_HwVoiceKitManager_VoiceKitUtils", "processPartialResult voiceResponse isEmpty");
            return;
        }
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("directives");
            if (optJSONArray != null) {
                String str2 = null;
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject = optJSONArray.getJSONObject(i);
                    if ("DisplayASR".equals(jSONObject.getJSONObject("header").optString("name"))) {
                        str2 = jSONObject.getJSONObject("payload").optString(Constant.TEXT);
                        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "processPartialResult DisplayASR: ", str2);
                    }
                }
                kfk.a(str2, -1);
            }
        } catch (JSONException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "processPartialResult JSONException");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0094, code lost:
    
        return (kfj.d) r1.get(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:?, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0084, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0082, code lost:
    
        if (r3 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0073, code lost:
    
        if (r3 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x008b, code lost:
    
        if (r1.size() <= r13) goto L42;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0099  */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static kfj.d a(android.content.Context r11, java.lang.String r12, int r13) {
        /*
            java.lang.String r0 = "HwVoiceKitManager_VoiceKitUtils"
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = 16
            r1.<init>(r2)
            android.content.ContentResolver r3 = r11.getContentResolver()
            r11 = 0
            r2 = 1
            r9 = 0
            android.net.Uri r4 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI     // Catch: java.lang.Throwable -> L76 android.database.SQLException -> L78
            java.lang.String[] r7 = new java.lang.String[]{r12}     // Catch: java.lang.Throwable -> L76 android.database.SQLException -> L78
            r5 = 0
            java.lang.String r6 = "contact_id = ?"
            r8 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L76 android.database.SQLException -> L78
            if (r3 == 0) goto L6a
            int r4 = r3.getCount()     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            if (r4 <= 0) goto L6a
        L26:
            boolean r4 = r3.moveToNext()     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            if (r4 == 0) goto L73
            kfj$d r4 = new kfj$d     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            r4.<init>()     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            java.lang.String r5 = "data1"
            int r5 = r3.getColumnIndex(r5)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            java.lang.String r5 = r3.getString(r5)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            java.lang.String r7 = ""
            java.lang.String r8 = " "
            if (r6 != 0) goto L49
            java.lang.String r5 = r5.replaceAll(r8, r7)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
        L49:
            java.lang.String r6 = "display_name"
            int r6 = r3.getColumnIndex(r6)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            java.lang.String r6 = r3.getString(r6)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            boolean r10 = android.text.TextUtils.isEmpty(r6)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            if (r10 != 0) goto L5d
            java.lang.String r6 = r6.replaceAll(r8, r7)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
        L5d:
            r4.d(r5)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            r4.a(r6)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            r4.b(r12)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            r1.add(r4)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            goto L26
        L6a:
            java.lang.Object[] r12 = new java.lang.Object[r2]     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            java.lang.String r4 = "getPhoneNumByContactId cursor is null"
            r12[r11] = r4     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
            com.huawei.hwlogsmodel.LogUtil.h(r0, r12)     // Catch: android.database.SQLException -> L79 java.lang.Throwable -> L95
        L73:
            if (r3 == 0) goto L87
            goto L84
        L76:
            r11 = move-exception
            goto L97
        L78:
            r3 = r9
        L79:
            java.lang.Object[] r12 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L95
            java.lang.String r2 = "getPhoneNumByContactId Exception"
            r12[r11] = r2     // Catch: java.lang.Throwable -> L95
            com.huawei.hwlogsmodel.LogUtil.b(r0, r12)     // Catch: java.lang.Throwable -> L95
            if (r3 == 0) goto L87
        L84:
            r3.close()
        L87:
            int r11 = r1.size()
            if (r11 <= r13) goto L94
            java.lang.Object r11 = r1.get(r13)
            r9 = r11
            kfj$d r9 = (kfj.d) r9
        L94:
            return r9
        L95:
            r11 = move-exception
            r9 = r3
        L97:
            if (r9 == 0) goto L9c
            r9.close()
        L9c:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kfl.a(android.content.Context, java.lang.String, int):kfj$d");
    }

    public static String a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("backUpAppPackageList");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                String optString = optJSONArray.optString(i);
                if (c(optString)) {
                    LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "backupPackage:", optString);
                    return optString;
                }
            }
        }
        return "";
    }

    public static boolean c(String str) {
        try {
            BaseApplication.getContext().getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void c(Context context, String str) {
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "startHwMusicPlay");
        if (TextUtils.isEmpty(str) || context == null) {
            LogUtil.h("HwVoiceKitManager_VoiceKitUtils", "startHwMusicPlay param isEmpty");
            return;
        }
        KeyEvent keyEvent = new KeyEvent(0, 126);
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(str);
        intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
        context.sendBroadcast(intent);
        KeyEvent keyEvent2 = new KeyEvent(1, 126);
        Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent2.setPackage(str);
        intent2.putExtra("android.intent.extra.KEY_EVENT", keyEvent2);
        context.sendBroadcast(intent2);
    }

    public static boolean d(JSONArray jSONArray, int i, HwControlLocalAppHelper hwControlLocalAppHelper) {
        boolean z = false;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("header");
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("payload");
                if (optJSONObject2 != null && optJSONObject3 != null && "NLPRecognizer".equals(optJSONObject2.optString("name"))) {
                    String optString = optJSONObject3.optString("domainId");
                    String optString2 = optJSONObject3.optString("subDomainId");
                    String optString3 = optJSONObject3.optString("intentId");
                    kfk.d(optString, optString2, optString3);
                    if (i < 1 && !"10171012".equals(optString2) && !"10021001".equals(optString2)) {
                        if (TextUtils.isEmpty(optString3)) {
                            continue;
                        } else if (!a("1001101300010000-1001101300020000-1001101300090000-1001101300100000-1001101300110000-1001101300120000-1001101300140000-1001101300150000-1002100800010000-1002100800020000-1002100800030000-1002100800040000-1002100800050000".split(Constants.LINK), optString3)) {
                            JSONArray optJSONArray = optJSONObject3.optJSONArray("slots");
                            if (optJSONArray != null && optJSONArray.length() != 0) {
                                z = d(optJSONArray, optString3, hwControlLocalAppHelper);
                            }
                        }
                    }
                    z = true;
                    break;
                }
            }
        }
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "isSupportCommand:", Boolean.valueOf(z));
        return z;
    }

    public static String e(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        if (context == null) {
            return jSONObject.toString();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("name", "SupportedLanguages");
            jSONObject3.put(e.j, CardMgrSdkConst.MSGScene.SYSTEM);
            jSONObject2.put("header", jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (String str2 : "en_GB-es_ES-fr_FR-de_DE-it_IT-ms_MY-pl_PL-ar_SA".split(Constants.LINK)) {
                jSONArray.put(str2.split("_")[0]);
            }
            jSONObject4.put("languageList", jSONArray);
            jSONObject2.put("payload", jSONObject4);
            String b = b(context);
            LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "get media package:", b);
            String d2 = d(context, b);
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(new JSONObject(d2));
            if (!str.equals(ProfileRequestConstants.X_LANGUAGE_VALUE)) {
                jSONArray2.put(jSONObject2);
            }
            jSONObject.put("contexts", jSONArray2);
        } catch (JSONException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "buildVoiceContext JSONException");
        }
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "buildVoiceContext contextObject:", jSONObject.toString());
        return jSONObject.toString();
    }

    public static String b(Context context) {
        if (context == null) {
            return "";
        }
        Object systemService = context.getSystemService("media_session");
        List<MediaController> list = null;
        MediaSessionManager mediaSessionManager = systemService instanceof MediaSessionManager ? (MediaSessionManager) systemService : null;
        if (mediaSessionManager == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        try {
            list = mediaSessionManager.getActiveSessions(new ComponentName(context.getPackageName(), "com.huawei.bone.ui.setting.NotificationPushListener"));
        } catch (SecurityException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "getMediaPackage SecurityException");
        }
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).getPackageName());
                if (i != list.size() - 1) {
                    sb.append(Constants.LINK);
                }
            }
        }
        return sb.toString();
    }

    public static String d(Context context, String str) {
        String str2 = "{\"header\":{\"namespace\":\"System\",\"name\":\"ClientContext\"},\"payload\":{\"screenLockerStatus\":false}}";
        if (TextUtils.isEmpty(str)) {
            return "{\"header\":{\"namespace\":\"System\",\"name\":\"ClientContext\"},\"payload\":{\"screenLockerStatus\":false}}";
        }
        PackageManager packageManager = context.getPackageManager();
        String str3 = str.split(Constants.LINK)[0];
        try {
            str2 = String.format(Locale.ENGLISH, "{\"header\":{\"namespace\":\"System\",\"name\":\"ClientContext\"},\"payload\":{\"screenLockerStatus\":false,\"playerApp\":{\"name\":\"%s\",\"packageName\":\"%s\",\"version\":\"%s\"}}}", packageManager.getApplicationLabel(packageManager.getApplicationInfo(str3, 128)).toString(), str3, packageManager.getPackageInfo(str3, 0).versionName);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HwVoiceKitManager_VoiceKitUtils", "updateClientContext NameNotFoundException");
        }
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "clientContext:", str2);
        return str2;
    }

    public static void d() {
        ReleaseLogUtil.e("R_HwVoiceKitManager_VoiceKitUtils", "enter saveDownloadPluginFlag");
        kfk.e(8);
        jpr.a("voiceAssistant");
        lsp.d().d("PluginHiAiEngine");
    }

    public static int a() {
        return d;
    }

    public static void d(int i) {
        d = i;
    }

    public static int b() {
        return c;
    }

    public static void c(int i) {
        c = i;
    }

    public static int e() {
        if (d == -1) {
            return -1;
        }
        Object systemService = BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO);
        int streamVolume = systemService instanceof AudioManager ? ((AudioManager) systemService).getStreamVolume(9) : -1;
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "getPhoneAiVolume: ", Integer.valueOf(streamVolume));
        return streamVolume;
    }

    public static void a(int i) {
        LogUtil.a("HwVoiceKitManager_VoiceKitUtils", "changePhoneAiVolume: ", Integer.valueOf(i));
        if (i < 1 || i > 15) {
            return;
        }
        Object systemService = BaseApplication.getContext().getSystemService(PresenterUtils.AUDIO);
        if (systemService instanceof AudioManager) {
            ((AudioManager) systemService).setStreamVolume(9, i, 0);
        }
    }
}
