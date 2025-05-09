package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public class ivm {
    private Context c = null;

    /* renamed from: a, reason: collision with root package name */
    private Executor f13630a = null;
    private Map<String, ICommonListener> b = new HashMap(16);
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: ivm.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                final HashMap hashMap = (HashMap) message.obj;
                ivm.this.f13630a.execute(new Runnable() { // from class: ivm.5.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ivm.this.e((HashMap<String, Object>) hashMap);
                    }
                });
            } else {
                if (i != 1) {
                    return;
                }
                final String str = message.obj instanceof String ? (String) message.obj : null;
                ivm.this.f13630a.execute(new Runnable() { // from class: ivm.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ivm.this.b(str);
                    }
                });
            }
        }
    };
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: ivm.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction()) || !"action_transcation_end".equals(intent.getAction())) {
                return;
            }
            HashMap hashMap = new HashMap(16);
            Bundle extras = intent.getExtras();
            if (extras != null) {
                hashMap.put("uuid", extras.getString("uuid"));
                hashMap.put("result", Integer.valueOf(extras.getInt("result")));
            }
            ivm.this.d.obtainMessage(0, hashMap).sendToTarget();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        String str = hashMap.get("uuid") instanceof String ? (String) hashMap.get("uuid") : null;
        Integer num = hashMap.get("result") instanceof Integer ? (Integer) hashMap.get("result") : null;
        ICommonListener iCommonListener = this.b.get(str);
        if (iCommonListener == null) {
            return;
        }
        this.b.remove(str);
        if (num != null && num.intValue() == 0) {
            LogUtil.a("HiTranscation", "transcation success:");
            ArrayList arrayList = new ArrayList(10);
            arrayList.add(false);
            c(iCommonListener, 0, arrayList);
            return;
        }
        LogUtil.a("HiTranscation", "transcation fail:");
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(false);
        b(iCommonListener, 14, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        this.b.remove(str);
    }

    public void e(Context context, Executor executor) {
        if (context == null) {
            return;
        }
        this.c = context;
        this.f13630a = executor;
        BroadcastManagerUtil.bFA_(context, this.e, new IntentFilter("action_transcation_end"), LocalBroadcast.c, null);
    }

    public void e() {
        Context context = this.c;
        if (context != null) {
            context.unregisterReceiver(this.e);
        }
    }

    public void d(String str, ICommonListener iCommonListener) {
        LogUtil.a("HiTranscation", "transcation begin");
        this.b.put(str, iCommonListener);
        this.d.sendMessageDelayed(this.d.obtainMessage(1, str), 20000L);
    }

    private void c(ICommonListener iCommonListener, int i, List list) {
        if (iCommonListener == null) {
            return;
        }
        try {
            iCommonListener.onSuccess(i, list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiTranscation", "ICommonListener setSuccess  exception = ", e.getMessage());
        } catch (Exception e2) {
            ReleaseLogUtil.d("HiH_HiTranscation", "ICommonListener setSuccess Exception", LogAnonymous.b((Throwable) e2));
        }
    }

    private void b(ICommonListener iCommonListener, int i, List list) {
        if (iCommonListener == null) {
            return;
        }
        try {
            iCommonListener.onFailure(i, list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiTranscation", "ICommonListener setFail  exception = ", e.getMessage());
        } catch (Exception e2) {
            ReleaseLogUtil.d("HiH_HiTranscation", "ICommonListener setSuccess Exception", LogAnonymous.b((Throwable) e2));
        }
    }
}
