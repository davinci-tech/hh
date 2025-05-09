package com.huawei.openalliance.ad.inter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.constant.SpKeys;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.gc;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.data.LinkedSplashAd;
import com.huawei.openalliance.ad.inter.listeners.IExSplashCallback;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.oz;
import com.huawei.openalliance.ad.utils.be;
import com.huawei.openalliance.ad.utils.dl;
import com.huawei.openalliance.ad.utils.m;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class b extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private Context f7028a;
    private gc b;

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        Log.d("ExLinkedSplashReceiver", "onReceive.");
        if (intent == null) {
            return;
        }
        try {
            if (Constants.ACTION_EXSPLASH_START_LINKED.equals(intent.getAction())) {
                ho.b("ExLinkedSplashReceiver", "receiver exlinkedsplash action");
                Long valueOf = Long.valueOf(intent.getLongExtra(SpKeys.EXSPLASH_SLOGAN_START_TIME, 0L));
                int intExtra = intent.getIntExtra(SpKeys.EXSPLASH_SLOGAN_SHOW_TIME, 0);
                String stringExtra = intent.getStringExtra(SpKeys.LINKED_CONTENT_ID);
                String stringExtra2 = intent.getStringExtra("linked_content_slotId");
                int intExtra2 = intent.getIntExtra(SpKeys.EXSPLASH_REDUNDANCY_TIME, 0);
                ho.a("ExLinkedSplashReceiver", "ExLinkedSplashReceiver, startTime: %s, showTime: %s, contentId: %s", valueOf, Integer.valueOf(intExtra), stringExtra);
                context.removeStickyBroadcast(intent);
                gc gcVar = this.b;
                if (gcVar != null) {
                    gcVar.g(valueOf.longValue());
                    this.b.h(intExtra);
                    this.b.p(stringExtra);
                    this.b.i(intExtra2);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(MapKeyNames.CONTENT_ID, stringExtra);
                jSONObject.put("package_name", this.f7028a.getPackageName());
                jSONObject.put(MapKeyNames.IS_OLD_FAT, true);
                if (!TextUtils.isEmpty(stringExtra2)) {
                    jSONObject.put("slotid", stringExtra2);
                }
                ms.a(context).a(RTCMethods.REQ_LINKED_VIDEO, jSONObject.toString(), new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.inter.b.1
                    @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                    public void onRemoteCallResult(String str, CallResult<String> callResult) {
                        String str2;
                        if (callResult.getCode() != 200) {
                            ho.c("ExLinkedSplashReceiver", "call reqExLinked failed");
                            b.this.a();
                            return;
                        }
                        ho.b("ExLinkedSplashReceiver", "reqExLinkedVideo success");
                        try {
                            ContentRecord a2 = b.this.a(new JSONObject(callResult.getData()));
                            if (a2 != null) {
                                a2.b(true);
                                final LinkedSplashAd a3 = oz.a(context, a2);
                                a3.e(true);
                                final IExSplashCallback f = HiAd.a(context).f();
                                if (f != null) {
                                    m.d(new Runnable() { // from class: com.huawei.openalliance.ad.inter.b.1.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            String str3;
                                            String str4;
                                            boolean canDisplayLinkedVideo = f.canDisplayLinkedVideo(a3);
                                            ho.b("ExLinkedSplashReceiver", "onReceive, isCanDisplay: %s", Boolean.valueOf(canDisplayLinkedVideo));
                                            if (canDisplayLinkedVideo) {
                                                return;
                                            }
                                            ho.c("ExLinkedSplashReceiver", "isCanDisplay false, start show normal splash. ");
                                            b.this.a();
                                            LinkedSplashAd linkedSplashAd = a3;
                                            if (linkedSplashAd != null) {
                                                str3 = linkedSplashAd.getContentId();
                                                str4 = a3.getSlotId();
                                            } else {
                                                str3 = null;
                                                str4 = null;
                                            }
                                            new com.huawei.openalliance.ad.analysis.c(context).a(context.getPackageName(), 1, str4, str3);
                                        }
                                    });
                                }
                                str2 = "exSplashCallback is null";
                            } else {
                                str2 = "content is null";
                            }
                            ho.c("ExLinkedSplashReceiver", str2);
                            b.this.a();
                        } catch (JSONException unused) {
                            ho.c("ExLinkedSplashReceiver", "reqLinkedVideo onRemoteCallResult JSONException ");
                        }
                    }
                }, String.class);
            }
        } catch (JSONException unused) {
            ho.c("ExLinkedSplashReceiver", "reqExLinkedVideo JSONException");
            a();
        } catch (Throwable th) {
            ho.c("ExLinkedSplashReceiver", "reqLinkedVideo exception: %s", th.getClass().getSimpleName());
        }
    }

    private void b(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("landpage_app_prompt");
        int optInt2 = jSONObject.optInt("splash_skip_area");
        String optString = jSONObject.optString("scheme_info");
        String optString2 = jSONObject.optString(MapKeyNames.GLOBAL_SWITCH);
        String optString3 = jSONObject.optString(MapKeyNames.LANDPAGE_APP_WHITE_LIST);
        String optString4 = jSONObject.optString(MapKeyNames.LANDPAGE_APP_PROMPT_MAP);
        String optString5 = jSONObject.optString(MapKeyNames.LANDPAGE_WEB_BLACK_LIST);
        if (ho.a()) {
            ho.a("ExLinkedSplashReceiver", "landPageAppPrompt:%s", Integer.valueOf(optInt));
            ho.a("ExLinkedSplashReceiver", "splashSkipArea=%s", Integer.valueOf(optInt2));
            ho.a("ExLinkedSplashReceiver", "schemeInfo=%s", dl.a(optString));
            ho.a("ExLinkedSplashReceiver", "globalSwitch=%s", dl.a(optString2));
            ho.a("ExLinkedSplashReceiver", "lpWhiteList=%s", dl.a(optString3));
            ho.a("ExLinkedSplashReceiver", "promptMapString=%s", dl.a(optString4));
            ho.a("ExLinkedSplashReceiver", "lpWebBlackList=%s", dl.a(optString5));
        }
        gc gcVar = this.b;
        if (gcVar != null) {
            gcVar.j(optInt);
            this.b.b(optInt2);
            this.b.q(optString2);
            if (!TextUtils.isEmpty(optString)) {
                List list = (List) be.b(optString, List.class, String.class);
                ho.a("ExLinkedSplashReceiver", " schemeInfo info=%s", Boolean.valueOf(list.isEmpty()));
                this.b.a(new HashSet(list));
            }
            if (!TextUtils.isEmpty(optString3)) {
                List<String> list2 = (List) be.b(optString3, List.class, String.class);
                if (TextUtils.isEmpty(optString4)) {
                    this.b.a(list2, (Map<String, Boolean>) null);
                } else {
                    this.b.a(list2, (Map<String, Boolean>) be.b(optString4, Map.class, String.class, Boolean.class));
                }
            }
            if (TextUtils.isEmpty(optString5)) {
                return;
            }
            this.b.b((List<String>) be.b(optString5, List.class, String.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ms.a(this.f7028a).a(RTCMethods.SHOW_SPLASH, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentRecord a(JSONObject jSONObject) {
        ContentRecord contentRecord;
        try {
            String optString = jSONObject.optString(MapKeyNames.PARAM_FROM_SERVER);
            String optString2 = jSONObject.optString(MapKeyNames.THIRD_MONITORS);
            String optString3 = jSONObject.optString(MapKeyNames.CONTENT_RECORD);
            String optString4 = jSONObject.optString(MapKeyNames.LANDPAGE_WHITELIST);
            if (ho.a()) {
                ho.a("ExLinkedSplashReceiver", " paramJsonObjString content= %s", dl.a(optString));
                ho.a("ExLinkedSplashReceiver", " thirdMonitors content= %s", dl.a(optString2));
                ho.a("ExLinkedSplashReceiver", " whiteList content= %s", dl.a(optString4));
            }
            contentRecord = (ContentRecord) be.b(optString3, ContentRecord.class, new Class[0]);
            try {
                if (ho.a()) {
                    ho.a("ExLinkedSplashReceiver", " adContent content= %s", dl.a(optString3));
                }
                if (contentRecord != null) {
                    contentRecord.l(optString);
                    if (!TextUtils.isEmpty(optString2)) {
                        contentRecord.c((List<Monitor>) be.b(optString2, List.class, Monitor.class));
                    }
                    contentRecord.w(optString4);
                    b(jSONObject);
                }
            } catch (Throwable th) {
                th = th;
                ho.c("ExLinkedSplashReceiver", "handleResponse exception: %s", th.getClass().getSimpleName());
                return contentRecord;
            }
        } catch (Throwable th2) {
            th = th2;
            contentRecord = null;
        }
        return contentRecord;
    }

    public b(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f7028a = applicationContext;
        this.b = fh.b(applicationContext);
    }
}
