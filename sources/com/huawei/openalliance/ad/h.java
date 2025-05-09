package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class h extends j {
    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        int i;
        if (ho.a()) {
            ho.a("JsbAdClick", "start");
        }
        JSONObject jSONObject = new JSONObject(str);
        int optInt = jSONObject.optInt("adType", -1);
        ContentRecord b = b(context, str);
        if (b != null) {
            MetaData h = b.h();
            if (h != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("appId", h.m());
                hashMap.put("thirdId", h.l());
                if (optInt == 3 && b.R() != null) {
                    VideoInfo videoInfo = new VideoInfo(b.R());
                    hashMap.put(MapKeyNames.LINKED_CUSTOM_SHOW_ID, b.j());
                    hashMap.put(MapKeyNames.LINKED_CUSTOM_RETURN_VIDEO_DIRECT, videoInfo.f() ? "true" : "false");
                    hashMap.put(MapKeyNames.LINKED_CUSTOM_VIDEO_SOUND_SWITCH, videoInfo.getSoundSwitch());
                    hashMap.put(MapKeyNames.LINKED_CUSTOM_VIDEO_PROGRESS, String.valueOf(videoInfo.b()));
                    hashMap.put(MapKeyNames.LINKED_SPLASH_MEDIA_PATH, b.z());
                }
                ta a2 = sz.a(a(context), b, hashMap);
                if (a2.a()) {
                    MaterialClickInfo f = f(str);
                    Integer d = d(str);
                    boolean z = f.h() == null && f.i() == null;
                    if (d != null && 13 == d.intValue() && z) {
                        f.f(1);
                    }
                    if (a(context, b)) {
                        a(a2, context, b, optInt, d, f, jSONObject);
                    } else {
                        ho.b("JsbAdClick", "ad is not in whitelist");
                        i = IEventListener.EVENT_ID_DEVICE_CONN_FAIL;
                    }
                } else {
                    ho.b("JsbAdClick", "fail open land page");
                    i = 3003;
                }
            }
            i = 1000;
        } else {
            ho.b("JsbAdClick", "ad not exist");
            i = 3002;
        }
        a(remoteCallResultCallback, this.f7108a, i, null, true);
    }

    private void a(ta taVar, Context context, ContentRecord contentRecord, int i, Integer num, MaterialClickInfo materialClickInfo, JSONObject jSONObject) {
        ou ouVar = new ou(context, sc.a(context, i), contentRecord);
        b.a aVar = new b.a();
        aVar.b(taVar.c()).a(num).a(h(jSONObject.optString("versionCode"))).a(jSONObject.optString("versionCode")).a(materialClickInfo);
        ouVar.a(aVar.a());
    }

    public h() {
        super("pps.action.click");
    }
}
