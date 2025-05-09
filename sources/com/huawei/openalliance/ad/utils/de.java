package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.beans.tags.TagCfgModel;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.constant.TagConstants;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.fo;
import com.huawei.openalliance.ad.gh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ipc.CallResult;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.ms;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class de {
    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str) {
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray(TagConstants.TAG_LIST_KEY);
            if (optJSONArray != null && optJSONArray.length() != 0) {
                ho.a("TagSyncUtil", "save tag data: %s", str);
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (!cz.b(optJSONObject.toString())) {
                        gh a2 = fo.a(context);
                        String optString = optJSONObject.optString("type");
                        a2.a(optString, optJSONObject.optString("value"));
                        a2.a(optString, optJSONObject.optLong("updateTime"));
                        a2.a(optString, optJSONObject.optInt(TagConstants.TRIGGER_MODE));
                    }
                }
                return;
            }
            ho.a("TagSyncUtil", "tag array is empty");
        } catch (Throwable th) {
            ho.c("TagSyncUtil", "saveTagData error: %s", th.getClass().getSimpleName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> b(Context context, List<TagCfgModel> list) {
        ArrayList arrayList = new ArrayList();
        for (TagCfgModel tagCfgModel : list) {
            String a2 = tagCfgModel.a();
            long e = fo.a(context).e(a2);
            long parseInt = Integer.parseInt(tagCfgModel.c());
            ho.a("TagSyncUtil", "sync tag: %s, interval: %s", a2, Long.valueOf(parseInt));
            if (parseInt < 0) {
                ho.b("TagSyncUtil", "sync tag interval less than zero");
            } else if (ao.c() - e > parseInt * 60000) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    public static void a(final Context context) {
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.de.1
            @Override // java.lang.Runnable
            public void run() {
                String str;
                List<TagCfgModel> ck = fh.b(context).ck();
                if (bg.a(ck)) {
                    str = "no tag need to sync";
                } else {
                    List<String> b = de.b(context, ck);
                    if (!bg.a(b)) {
                        fo.a(context).a(b, ao.c());
                        ho.a("TagSyncUtil", "do sync tag");
                        try {
                            ms.a(context).a(RTCMethods.QUERY_USER_TAG, bg.a(b, ","), new RemoteCallResultCallback<String>() { // from class: com.huawei.openalliance.ad.utils.de.1.1
                                @Override // com.huawei.openalliance.ad.ipc.RemoteCallResultCallback
                                public void onRemoteCallResult(String str2, CallResult<String> callResult) {
                                    if (callResult == null || callResult.getCode() != 200) {
                                        return;
                                    }
                                    ho.b("TagSyncUtil", "sync tag success");
                                    if (cz.b(callResult.getData())) {
                                        ho.a("TagSyncUtil", "sync tag data is empty");
                                    } else {
                                        de.b(context, callResult.getData());
                                    }
                                }
                            }, String.class);
                            return;
                        } catch (Throwable th) {
                            ho.c("TagSyncUtil", "sync tag failed: %s", th.getClass().getSimpleName());
                            return;
                        }
                    }
                    str = "interval failed, no need to sync tag";
                }
                ho.b("TagSyncUtil", str);
            }
        });
    }
}
