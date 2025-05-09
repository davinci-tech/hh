package com.huawei.agconnect.credential.obs;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.BackendService;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class z {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1787a = "AGCHost";
    private static final z b = new z();
    private final Map<String, List<ag>> c = new HashMap();
    private final Map<String, ag> d = new HashMap();
    private final AtomicBoolean e = new AtomicBoolean(false);
    private String f;
    private final Handler g;

    public void a(String str) {
        if ("agconnect-credential".equals(str)) {
            return;
        }
        this.f = str;
    }

    List<ag> a(AGConnectInstance aGConnectInstance) {
        List<ag> singletonList;
        List<ag> list;
        String identifier = aGConnectInstance.getIdentifier();
        if (this.c.containsKey(identifier)) {
            list = this.c.get(identifier);
        } else {
            ag agVar = new ag(aGConnectInstance.getOptions().getString("agcgw/url"), aGConnectInstance.getOptions().getString("agcgw/backurl"));
            ag agVar2 = this.d.get(identifier);
            if (agVar2 != null) {
                singletonList = Arrays.asList(agVar2, agVar);
            } else {
                ag b2 = ag.b(identifier);
                this.d.put(identifier, b2);
                singletonList = !b2.g() ? Collections.singletonList(agVar) : Arrays.asList(b2, agVar);
            }
            this.c.put(identifier, singletonList);
            list = singletonList;
        }
        b(aGConnectInstance);
        return list;
    }

    private void b(final AGConnectInstance aGConnectInstance) {
        Logger.i(f1787a, "getCachedHostAsync");
        ag agVar = this.d.get(aGConnectInstance.getIdentifier());
        long j = agVar != null ? agVar.validTime : 0L;
        if (!a(j)) {
            Logger.i(f1787a, "isTTLValid false：" + j);
        } else if (this.e.get()) {
            Logger.i(f1787a, "requestOnce is true");
        } else {
            this.e.set(true);
            this.g.postDelayed(new Runnable() { // from class: com.huawei.agconnect.credential.obs.z.1
                @Override // java.lang.Runnable
                public void run() {
                    Logger.i(z.f1787a, "getCachedHostAsync#start");
                    au auVar = new au(aGConnectInstance);
                    auVar.a(z.this.f);
                    BackendService.sendRequest(auVar, 1, av.class, new BackendService.Options.Builder().app(aGConnectInstance).clientToken(false).build()).addOnCompleteListener(TaskExecutors.immediate(), new OnCompleteListener<av>() { // from class: com.huawei.agconnect.credential.obs.z.1.1
                        @Override // com.huawei.hmf.tasks.OnCompleteListener
                        public void onComplete(Task<av> task) {
                            int nextInt = new Random().nextInt(172801) + 86400;
                            Logger.i(z.f1787a, "getCachedHostAsync success, default ttl=" + nextInt);
                            if (task.isSuccessful()) {
                                av result = task.getResult();
                                z.this.a(aGConnectInstance, result);
                                try {
                                    nextInt = Integer.parseInt(result.d());
                                } catch (NumberFormatException unused) {
                                    Logger.i(z.f1787a, "parse ttl failed, use default instead");
                                }
                            }
                            z.this.a(nextInt, aGConnectInstance.getIdentifier());
                        }
                    });
                }
            }, 5000L);
        }
    }

    private boolean a(long j) {
        return Calendar.getInstance().getTime().after(new Date(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AGConnectInstance aGConnectInstance, av avVar) {
        String a2 = a(aGConnectInstance, avVar.b());
        String a3 = a(aGConnectInstance, avVar.c());
        String identifier = aGConnectInstance.getIdentifier();
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(a3)) {
            ag.c(identifier);
            return;
        }
        Logger.d(f1787a, "cached main host:");
        Logger.d(f1787a, "cached backup host:");
        ag agVar = new ag(a2, a3);
        this.d.put(identifier, agVar);
        agVar.a(identifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, i);
        long time = calendar.getTime().getTime();
        ag agVar = this.d.get(str);
        if (agVar != null) {
            agVar.validTime = time;
        }
        Logger.i(f1787a, "updateTTL:" + time);
        ag.a(str, time);
    }

    private String a(AGConnectInstance aGConnectInstance, List<as> list) {
        for (as asVar : list) {
            if (asVar.a().equalsIgnoreCase(aGConnectInstance.getOptions().getRoutePolicy().getRouteName())) {
                return asVar.b();
            }
        }
        return null;
    }

    public static z a() {
        return b;
    }

    private z() {
        HandlerThread handlerThread = new HandlerThread("subThread");
        handlerThread.start();
        this.g = new Handler(handlerThread.getLooper());
    }
}
