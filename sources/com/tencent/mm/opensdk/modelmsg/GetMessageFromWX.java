package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.utils.Log;

/* loaded from: classes10.dex */
public final class GetMessageFromWX {

    public static class Req extends BaseReq {
        public String country;
        public String lang;
        public String username;

        @Override // com.tencent.mm.opensdk.modelbase.BaseReq
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.mm.opensdk.modelbase.BaseReq
        public int getType() {
            return 3;
        }

        @Override // com.tencent.mm.opensdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_getmessage_req_lang", this.lang);
            bundle.putString("_wxapi_getmessage_req_country", this.country);
        }

        @Override // com.tencent.mm.opensdk.modelbase.BaseReq
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.lang = bundle.getString("_wxapi_getmessage_req_lang");
            this.country = bundle.getString("_wxapi_getmessage_req_country");
        }

        public Req(Bundle bundle) {
            fromBundle(bundle);
        }

        public Req() {
        }
    }

    public static class Resp extends BaseResp {
        private static final String TAG = "MicroMsg.SDK.GetMessageFromWX.Resp";
        public WXMediaMessage message;

        @Override // com.tencent.mm.opensdk.modelbase.BaseResp
        public int getType() {
            return 3;
        }

        @Override // com.tencent.mm.opensdk.modelbase.BaseResp
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putAll(WXMediaMessage.Builder.toBundle(this.message));
        }

        @Override // com.tencent.mm.opensdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.message = WXMediaMessage.Builder.fromBundle(bundle);
        }

        @Override // com.tencent.mm.opensdk.modelbase.BaseResp
        public boolean checkArgs() {
            WXMediaMessage wXMediaMessage = this.message;
            if (wXMediaMessage != null) {
                return wXMediaMessage.checkArgs();
            }
            Log.e(TAG, "checkArgs fail, message is null");
            return false;
        }

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        public Resp() {
        }
    }

    private GetMessageFromWX() {
    }
}
