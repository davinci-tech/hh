package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.openalliance.ad.activity.AdComplainActivity;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.FeedbackInfo;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class o extends j {
    private com.huawei.openalliance.ad.views.feedback.h d;

    public static class a implements com.huawei.openalliance.ad.views.feedback.h {

        /* renamed from: a, reason: collision with root package name */
        private Context f7347a;
        private ContentRecord b;
        private FeedbackInfo c;
        private String d;
        private RemoteCallResultCallback<String> e;

        @Override // com.huawei.openalliance.ad.views.feedback.h
        public void b() {
            if (this.f7347a == null) {
                return;
            }
            j.a(this.e, this.d, 1000, 1, true);
            c();
        }

        @Override // com.huawei.openalliance.ad.views.feedback.h
        public void a() {
            Context context = this.f7347a;
            if (context == null) {
                return;
            }
            o.b(context, this.b, 3, this.c);
            j.a(this.e, this.d, 1000, 0, true);
            c();
        }

        private void c() {
            this.f7347a = null;
            this.b = null;
            this.c = null;
            this.e = null;
            this.d = null;
        }

        a(Context context, ContentRecord contentRecord, FeedbackInfo feedbackInfo, RemoteCallResultCallback<String> remoteCallResultCallback, String str) {
            this.f7347a = context.getApplicationContext();
            this.b = contentRecord;
            this.c = feedbackInfo;
            this.e = remoteCallResultCallback;
            this.d = str;
        }
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        int i;
        if (ho.a()) {
            ho.a("JsbFeedBackClick", "start");
        }
        JSONObject jSONObject = new JSONObject(str);
        ContentRecord b = b(context, str);
        int optInt = jSONObject.optInt(JsbMapKeyNames.FEEDBACK_TYPE, -111111);
        if (b != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject(JsbMapKeyNames.FEEDBACK_INFO);
            FeedbackInfo feedbackInfo = (FeedbackInfo) com.huawei.openalliance.ad.utils.be.b(optJSONObject.toString(), FeedbackInfo.class, new Class[0]);
            if (optJSONObject == null || feedbackInfo == null) {
                return;
            }
            Long valueOf = Long.valueOf(com.huawei.openalliance.ad.utils.cz.a(optJSONObject.optString("id"), -111111L));
            if (valueOf.longValue() != -111111) {
                feedbackInfo.a(valueOf.longValue());
                if (optInt != 3) {
                    b(context, b, optInt, feedbackInfo);
                    a(remoteCallResultCallback, this.f7108a, 1000, null, true);
                    return;
                } else {
                    a aVar = new a(context, b, feedbackInfo, remoteCallResultCallback, this.f7108a);
                    this.d = aVar;
                    a(context, b, feedbackInfo, aVar);
                    return;
                }
            }
            ho.b("JsbFeedBackClick", "invalid id");
            i = 4001;
        } else {
            ho.a("JsbFeedBackClick", "ad not exist");
            i = 3002;
        }
        a(remoteCallResultCallback, this.f7108a, i, null, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Context context, ContentRecord contentRecord, int i, FeedbackInfo feedbackInfo) {
        try {
            ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
            ouVar.a(contentRecord);
            ArrayList arrayList = new ArrayList();
            arrayList.add(feedbackInfo);
            ho.b("JsbFeedBackClick", "itemType is %s.", Integer.valueOf(i));
            if (i != 1) {
                if (i == 2) {
                    com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.o.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Toast.makeText(context, R.string._2130851093_res_0x7f023515, 0).show();
                        }
                    });
                    ouVar.a(arrayList);
                } else if (i != 3) {
                    ho.a("JsbFeedBackClick", "invalid feedback type");
                }
            }
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.o.2
                @Override // java.lang.Runnable
                public void run() {
                    Toast.makeText(context, R.string._2130851094_res_0x7f023516, 0).show();
                }
            });
            ouVar.b(arrayList);
        } catch (Throwable th) {
            ho.c("JsbFeedBackClick", "itemClickAction error: %s", th.getClass().getSimpleName());
        }
    }

    private boolean a(Context context, ContentRecord contentRecord, FeedbackInfo feedbackInfo, com.huawei.openalliance.ad.views.feedback.h hVar) {
        ho.b("JsbFeedBackClick", "click complain");
        if (feedbackInfo == null) {
            return false;
        }
        try {
            Intent intent = new Intent(context, (Class<?>) AdComplainActivity.class);
            intent.putExtra(MapKeyNames.COMPLAIN_H5_TITLE, feedbackInfo.getLabel());
            AdComplainActivity.a(new com.huawei.openalliance.ad.views.feedback.a(context, contentRecord, hVar));
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            com.huawei.openalliance.ad.utils.dd.a(context, intent);
            return true;
        } catch (Throwable th) {
            ho.c("JsbFeedBackClick", "start ac failed: %s", th.getClass().getSimpleName());
            return true;
        }
    }

    public o() {
        super("pps.feedback.click");
    }
}
