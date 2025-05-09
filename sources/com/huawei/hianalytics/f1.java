package com.huawei.hianalytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.CommonHeaderEx;
import com.huawei.hianalytics.core.storage.DaoManager;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.core.transport.Utils;
import com.huawei.hianalytics.framework.datahandler.ReportTask;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class f1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3853a;

    /* renamed from: a, reason: collision with other field name */
    public final String f36a;

    /* renamed from: a, reason: collision with other field name */
    public final List<Event> f37a = new ArrayList();
    public final List<CommonHeaderEx> b = new ArrayList();

    public final void a(String str, Object obj, String str2) {
        String str3;
        if (obj instanceof String) {
            JSONArray jSONArray = new JSONArray((String) obj);
            int length = jSONArray.length();
            if (length > 5000) {
                HiLog.w("InitDataSupport", "array size is too long");
                return;
            }
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (System.currentTimeMillis() - Utils.parseStringToLong(jSONObject.getString(Event.EventConstants.RECORD_TIME)) <= 604800000) {
                    String decrypt = AesCbc.decrypt(jSONObject.optString("stat_v2_1".equals(str2) ? "content" : Event.EventConstants.PROPERTIES), p0.a().m576a());
                    if (TextUtils.isEmpty(decrypt)) {
                        HiLog.i("InitDataSupport", "decryptCbc content is empty");
                        str3 = "";
                    } else {
                        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(12);
                        str3 = generateSecureRandomStr + AesGcm.encrypt(decrypt, p0.a().m576a(), generateSecureRandomStr);
                    }
                    if (TextUtils.isEmpty(str3)) {
                        HiLog.i("InitDataSupport", "content is empty");
                    } else {
                        Event event = new Event();
                        event.formJson(jSONObject);
                        event.setServicetag(str);
                        event.setContent(str3);
                        event.setProcessname(j.d());
                        this.f37a.add(event);
                    }
                }
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (j.a("global_v2", "v2_1DataHandlerFlag", false)) {
            HiLog.i("InitDataSupport", "cached data by HASDKV2 has already handled. tag: " + this.f36a);
        } else {
            a("stat_v2_1");
            a("cached_v2_1");
            long a2 = j.a(this.f3853a, "common_nc");
            if (a2 != -1) {
                if (a2 > 5242880) {
                    HiLog.i("InitDataSupport", "sp stat file length > 5M, begin delete it");
                    if (j.b(this.f3853a, "common_nc")) {
                        HiLog.i("InitDataSupport", "sp stat file delete success");
                    }
                } else {
                    SharedPreferences a3 = j.a("common_nc");
                    Map<String, ?> all = a3 == null ? null : a3.getAll();
                    j.b(this.f3853a, "common_nc");
                    if (all != null && !all.isEmpty()) {
                        for (Map.Entry<String, ?> entry : all.entrySet()) {
                            String key = entry.getKey();
                            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty((String) entry.getValue())) {
                                CommonHeaderEx commonHeaderEx = new CommonHeaderEx();
                                commonHeaderEx.setEvtExHashCode(key);
                                commonHeaderEx.setCommonHeaderEx((String) entry.getValue());
                                this.b.add(commonHeaderEx);
                            }
                        }
                    }
                }
            }
            j.b(this.f3853a, "backup_event");
            if (!this.f37a.isEmpty()) {
                m a4 = m.a(this.f3853a);
                List<Event> list = this.f37a;
                a4.getClass();
                DaoManager.getInstance().insertEvents(list);
            }
            if (!this.b.isEmpty()) {
                m a5 = m.a(this.f3853a);
                List<CommonHeaderEx> list2 = this.b;
                a5.getClass();
                DaoManager.getInstance().insertHeaders(list2);
            }
            if (TextUtils.isEmpty("v2_1DataHandlerFlag") || TextUtils.isEmpty("global_v2")) {
                HiLog.e("SharedPreUtils", "spName or spKey is empty");
            } else {
                SharedPreferences a6 = j.a("global_v2");
                if (a6 != null) {
                    SharedPreferences.Editor edit = a6.edit();
                    edit.putBoolean("v2_1DataHandlerFlag", true);
                    edit.commit();
                }
            }
        }
        String str = i.a().m550a().i;
        String str2 = i.a().m550a().h;
        if (TextUtils.isEmpty(str)) {
            HiLog.i("InitDataSupport", "app ver is first save. tag: " + this.f36a);
        } else {
            if (str.equals(str2)) {
                return;
            }
            HiLog.i("InitDataSupport", "the appVers are different. tag: " + this.f36a);
            if (j.m567b(EnvUtils.getAppContext())) {
                TaskThread.getReportThread().addToQueue(new ReportTask("", "", str, 500, 0));
                return;
            }
            HiLog.sw("InitDataSupport", "the network is unavailable. tag: " + this.f36a);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:22|(3:24|(1:35)(3:26|27|(1:34)(3:29|30|(1:32)))|33)|36|37|39|33|20) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008c, code lost:
    
        com.huawei.hianalytics.core.log.HiLog.e("InitDataSupport", "cache data is not json format");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r7) {
        /*
            r6 = this;
            android.content.Context r0 = r6.f3853a
            long r0 = com.huawei.hianalytics.j.a(r0, r7)
            r2 = -1
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 != 0) goto Ld
            return
        Ld:
            r2 = 5242880(0x500000, double:2.590327E-317)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            java.lang.String r1 = "InitDataSupport"
            if (r0 <= 0) goto L29
            java.lang.String r0 = "sp stat file length > 5M, begin delete it"
            com.huawei.hianalytics.core.log.HiLog.i(r1, r0)
            android.content.Context r0 = r6.f3853a
            boolean r7 = com.huawei.hianalytics.j.b(r0, r7)
            if (r7 == 0) goto L28
            java.lang.String r7 = "sp stat file delete success"
            com.huawei.hianalytics.core.log.HiLog.i(r1, r7)
        L28:
            return
        L29:
            android.content.SharedPreferences r0 = com.huawei.hianalytics.j.a(r7)
            if (r0 != 0) goto L31
            r0 = 0
            goto L35
        L31:
            java.util.Map r0 = r0.getAll()
        L35:
            android.content.Context r2 = r6.f3853a
            com.huawei.hianalytics.j.b(r2, r7)
            if (r0 == 0) goto L92
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L43
            goto L92
        L43:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L4b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L92
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "_default_config_tag"
            boolean r5 = r4.equals(r3)
            if (r5 == 0) goto L66
            goto L84
        L66:
            java.lang.String r4 = "-"
            java.lang.String[] r3 = r3.split(r4)
            int r4 = r3.length
            r5 = 2
            if (r4 == r5) goto L71
            goto L4b
        L71:
            r4 = 0
            r4 = r3[r4]
            r5 = 1
            r3 = r3[r5]
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L4b
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L84
            goto L4b
        L84:
            java.lang.Object r2 = r2.getValue()     // Catch: org.json.JSONException -> L8c
            r6.a(r4, r2, r7)     // Catch: org.json.JSONException -> L8c
            goto L4b
        L8c:
            java.lang.String r2 = "cache data is not json format"
            com.huawei.hianalytics.core.log.HiLog.e(r1, r2)
            goto L4b
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.f1.a(java.lang.String):void");
    }

    public f1(Context context, String str) {
        this.f3853a = context;
        this.f36a = str;
    }
}
