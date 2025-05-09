package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.activity.ComplianceActivity;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class aa extends z {
    private ContentRecord d;
    private int[] e;
    private int[] f;

    static class a implements com.huawei.openalliance.ad.activity.d {

        /* renamed from: a, reason: collision with root package name */
        private String f6551a;
        private RemoteCallResultCallback<String> b;

        @Override // com.huawei.openalliance.ad.activity.d
        public void b() {
            ho.b("JsbStartComplianceActivity", "onActivityFinish");
            j.a(this.b, this.f6551a, 1000, 5002, false);
            ComplianceActivity.l();
        }

        @Override // com.huawei.openalliance.ad.activity.d
        public void a() {
            ho.b("JsbStartComplianceActivity", "onActivityShow");
            j.a(this.b, this.f6551a, 1000, 5001, false);
        }

        public a(RemoteCallResultCallback<String> remoteCallResultCallback, String str) {
            this.b = remoteCallResultCallback;
            this.f6551a = str;
        }
    }

    public boolean d(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            if (ho.a()) {
                ho.a("JsbStartComplianceActivity", "parseParam: %s", str);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                ContentRecord b = b(context, str);
                this.d = b;
                if (b != null && !com.huawei.openalliance.ad.utils.bg.a(b.aS())) {
                    int optInt = jSONObject.optInt("anchorViewX", -1);
                    int optInt2 = jSONObject.optInt("anchorViewY", -1);
                    if (-1 != optInt && -1 != optInt2) {
                        int optInt3 = jSONObject.optInt("anchorWidth", -1);
                        int optInt4 = jSONObject.optInt("anchorHeight", -1);
                        if (-1 != optInt3 && -1 != optInt4) {
                            int[] iArr = this.e;
                            iArr[0] = optInt;
                            iArr[1] = optInt2;
                            int[] iArr2 = this.f;
                            iArr2[0] = optInt3;
                            iArr2[1] = optInt4;
                            if (ho.a()) {
                                ho.a("JsbStartComplianceActivity", "parse param complete, anchor loc (%s, %s), anchor size (%s, %s)", Integer.valueOf(optInt), Integer.valueOf(optInt2), Integer.valueOf(optInt3), Integer.valueOf(optInt4));
                            }
                            return true;
                        }
                        ho.c("JsbStartComplianceActivity", "invalid anchor size");
                        return false;
                    }
                    ho.c("JsbStartComplianceActivity", "invalid anchor loc");
                    return false;
                }
                ho.c("JsbStartComplianceActivity", "content is null or compliance is null.");
                return false;
            } catch (Throwable th) {
                ho.c("JsbStartComplianceActivity", "parse param ex: %s", th.getClass().getSimpleName());
            }
        }
        return false;
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        try {
            if (d(context, str)) {
                ComplianceActivity.a(new a(remoteCallResultCallback, this.f7108a));
                ComplianceActivity.a(context, this.e, this.f, this.d, true);
            }
        } catch (Throwable th) {
            ho.c("JsbStartComplianceActivity", "execute ex: %s", th.getClass().getSimpleName());
        }
    }

    public aa() {
        super("pps.advertiserinfo.show");
        this.e = new int[2];
        this.f = new int[2];
    }
}
