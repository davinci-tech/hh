package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class cfc {

    /* renamed from: a, reason: collision with root package name */
    private static cfc f674a;
    private BroadcastReceiver d = new BroadcastReceiver() { // from class: cfc.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.a("HonorDeviceModelListData", "sConnectStateChangedReceiver intent is null");
                return;
            }
            LogUtil.c("HonorDeviceModelListData", "sConnectStateChangedReceiver action: ", intent.getAction());
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                cfc.this.a();
            }
        }
    };
    private List<String> e;
    private static final Object c = new Object();
    private static final String[] b = {"Aragorn-B19", "Aragorn-B29", "Aragorn-B39", "Aragorn-B59", "Crius-B59", "Crius-B39", "Hes-B39", "Hes-B59", "Minos-B39", "Hebe-B39", "Kanon-B39"};

    private cfc() {
        BroadcastManagerUtil.bFC_(BaseApplication.e(), this.d, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), bin.d, null);
        this.e = b();
    }

    public static cfc e() {
        cfc cfcVar;
        synchronized (c) {
            if (f674a == null) {
                f674a = new cfc();
            }
            cfcVar = f674a;
        }
        return cfcVar;
    }

    public List<String> c() {
        return this.e;
    }

    public void a() {
        LogUtil.c("HonorDeviceModelListData", "updateModelList");
        ThreadPoolManager.d().d("HonorDeviceModelListData", new Runnable() { // from class: cfc.5
            @Override // java.lang.Runnable
            public void run() {
                cfc cfcVar = cfc.this;
                cfcVar.e = cfcVar.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> b() {
        ArrayList arrayList = new ArrayList(Arrays.asList(b));
        String d = blz.d("honor_device_model_list", "");
        if (!TextUtils.isEmpty(d)) {
            String[] split = d.split(";");
            if (split.length != 0) {
                arrayList.addAll(Arrays.asList(split));
            }
        }
        LogUtil.c("HonorDeviceModelListData", "currentModelList: ", arrayList);
        return arrayList;
    }
}
