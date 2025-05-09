package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.ads.jsb.inner.data.JsbCallBackData;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ac extends z {

    static class a implements IRewardAdStatusListener {

        /* renamed from: a, reason: collision with root package name */
        RewardItem f6553a;
        private String b;
        private RemoteCallResultCallback<String> c;

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onRewarded() {
            j.a(this.c, this.b, 1000, new JsbCallBackData(this.f6553a, false, "reward.cb.reward"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdShown() {
            j.a(this.c, this.b, 1000, new JsbCallBackData(null, false, "reward.cb.show"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdError(int i, int i2) {
            j.a(this.c, this.b, 1000, new JsbCallBackData(null, false, "reward.cb.error"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdCompleted() {
            j.a(this.c, this.b, 1000, new JsbCallBackData(null, false, "reward.cb.completed"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdClosed() {
            j.a(this.c, this.b, 1000, new JsbCallBackData(null, true, "reward.cb.close"));
        }

        @Override // com.huawei.openalliance.ad.inter.listeners.IRewardAdStatusListener
        public void onAdClicked() {
            j.a(this.c, this.b, 1000, new JsbCallBackData(null, false, "reward.cb.click"));
        }

        a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, RewardItem rewardItem) {
            this.f6553a = rewardItem;
            this.c = remoteCallResultCallback;
            this.b = str;
        }
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        if (ho.a()) {
            ho.a("JsbStartRewardAdActivity", "paramContent: %s", str);
        }
        ContentRecord b = b(context, str);
        if (b == null) {
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
            return;
        }
        ho.b("JsbStartRewardAdActivity", "show reward ad: %s, apiVer: %s", b.m(), Integer.valueOf(b.aO()));
        com.huawei.openalliance.ad.inter.data.h a2 = pn.a(context, b);
        if (a2 == null) {
            ho.a("JsbStartRewardAdActivity", "reward is null, start activity failed");
            a(remoteCallResultCallback, this.f7108a, 3002, null, true);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(JsbMapKeyNames.H5_CUSTOM_DATA);
            String optString2 = jSONObject.optString(JsbMapKeyNames.H5_USER_ID);
            boolean optBoolean = jSONObject.optBoolean("muted", true);
            boolean optBoolean2 = jSONObject.optBoolean(JsbMapKeyNames.H5_REWARD_DATA_ALERTS, true);
            int optInt = jSONObject.optInt("audioFocusType", 1);
            if (!TextUtils.isEmpty(optString)) {
                a2.setCustomData(optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                a2.setUserId(optString2);
            }
            if (optInt == 1 || optInt == 2 || optInt == 0) {
                a2.setAudioFocusType(optInt);
            }
            a2.setMute(optBoolean);
            a2.setMobileDataAlertSwitch(optBoolean2);
        } catch (Throwable unused) {
            ho.a("JsbStartRewardAdActivity", "content parse error");
        }
        a2.a(new a(remoteCallResultCallback, this.f7108a, a2.getRewardItem()));
        bx.a(a(context), a2);
        b(remoteCallResultCallback, false);
    }

    public ac() {
        super("pps.activity.reward");
    }
}
