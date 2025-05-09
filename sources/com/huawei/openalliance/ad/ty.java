package com.huawei.openalliance.ad;

import android.app.Activity;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.UUID;

/* loaded from: classes5.dex */
public class ty implements to {
    @Override // com.huawei.openalliance.ad.to
    public boolean a() {
        return tv.a("com.tencent.mm.opensdk.openapi.WXAPIFactory");
    }

    @Override // com.huawei.openalliance.ad.to
    public void a(Activity activity, ts tsVar, tu tuVar) {
        ho.b("WeiXinShare", "start WeXin share");
        String a2 = tuVar.a();
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(tn.a(activity), a2, true);
        createWXAPI.registerApp(a2);
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = tsVar.d();
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        wXMediaMessage.title = tv.a(tsVar.b(), 512);
        wXMediaMessage.description = tv.a(tsVar.c(), 1024);
        wXMediaMessage.thumbData = tv.a(activity, tsVar, 32768);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = UUID.randomUUID().toString();
        req.message = wXMediaMessage;
        req.scene = a(tuVar);
        createWXAPI.sendReq(req);
    }

    private static int a(tu tuVar) {
        return !tuVar.b().booleanValue() ? 1 : 0;
    }
}
