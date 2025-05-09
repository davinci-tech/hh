package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.repository.api.AuthInfoRepository;
import com.huawei.wearengine.scope.ScopeInfo;
import com.huawei.wearengine.scope.ScopeInfoResponse;
import com.huawei.wearengine.scope.ScopeManager;
import com.huawei.wearengine.scope.ScopeServerRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes7.dex */
public class toe {
    private static final String[] b;
    private static volatile toe d;
    private static final Set<String> e;
    private AuthInfoRepository f;
    private ExecutorService h = Executors.newSingleThreadExecutor();
    private static List<String> c = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, String> f17275a = new ConcurrentHashMap();

    static {
        String[] strArr = {"com.huawei.deveco.assistant", "com.plagh.heartstudy", "com.study.vascular", "com.study.respiratory"};
        b = strArr;
        e = new HashSet(Arrays.asList(strArr));
        c.add(Permission.DEVICE_MANAGER.getName());
        c.add(Permission.NOTIFY.getName());
        f17275a.put(tqo.f17349a.d(), Permission.DEVICE_MANAGER.getName());
        f17275a.put(tqo.d.d(), Permission.NOTIFY.getName());
        f17275a.put(tqo.e.d(), Permission.SENSOR.getName());
        f17275a.put(tqo.c.d(), Permission.MOTION_SENSOR.getName());
        f17275a.put(tqo.g.d(), Permission.WEAR_USER_STATUS.getName());
        f17275a.put(tqo.b.d(), Permission.DEVICE_SN.getName());
    }

    private toe(AuthInfoRepository authInfoRepository) {
        this.f = authInfoRepository;
    }

    public static toe c(AuthInfoRepository authInfoRepository) {
        if (d == null) {
            synchronized (toe.class) {
                if (d == null) {
                    d = new toe(authInfoRepository);
                }
            }
        }
        return d;
    }

    public static List<String> c() {
        return new ArrayList(Arrays.asList(b));
    }

    public int a(final Context context, final int i, final String str) {
        if (TextUtils.isEmpty(str)) {
            tos.d("PreAuthManger", "preGrantPermission clientPkgName is empty");
            return 12;
        }
        if (context == null) {
            tos.d("PreAuthManger", "preGrantPermission context is null");
            return 12;
        }
        tos.a("PreAuthManger", "preGrantPermission start:" + str);
        if (e.contains(str)) {
            tos.a("PreAuthManger", "preGrantPermission caller is special app");
            return 0;
        }
        Future submit = this.h.submit(new Callable<Integer>() { // from class: toe.2
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Integer call() {
                return Integer.valueOf(toe.this.c(context, i, str));
            }
        });
        try {
            int intValue = ((Integer) submit.get(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)).intValue();
            tos.a("PreAuthManger", "preGrantPermission result:" + intValue);
            return intValue;
        } catch (InterruptedException unused) {
            tos.e("PreAuthManger", "preGrantPermission InterruptedException");
            return 12;
        } catch (ExecutionException unused2) {
            tos.e("PreAuthManger", "preGrantPermission ExecutionException");
            return 12;
        } catch (TimeoutException unused3) {
            tos.e("PreAuthManger", "preGrantPermission TimeoutException");
            submit.cancel(true);
            return 12;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(Context context, int i, String str) {
        String a2 = tri.a(context);
        if (TextUtils.isEmpty(a2)) {
            tos.e("PreAuthManger", "preGrantPermissionInThread usr_id is null, not login in Huawei Health!");
            return 3;
        }
        int j = tri.j(context, str);
        if (j == 0) {
            tos.e("PreAuthManger", "preGrantPermissionInThread appId is invalid");
            return 12;
        }
        AuthInfoRepository authInfoRepository = this.f;
        if (authInfoRepository == null) {
            tos.e("PreAuthManger", "preGrantPermissionInThread mAuthInfoRepository is null");
            return 12;
        }
        if (c(authInfoRepository.getAuth(i, a2, j))) {
            return 0;
        }
        List<String> d2 = d(j);
        if (d2 == null || d2.isEmpty()) {
            tos.a("PreAuthManger", "preGrantPermissionInThread permission intersection is empty");
            return 8;
        }
        tos.a("PreAuthManger", "preGrantPermissionInThread start update db");
        Iterator<AuthInfo> it = e(d2, context, i, a2, j).iterator();
        while (it.hasNext()) {
            this.f.updateAuth(it.next());
        }
        return 0;
    }

    private List<AuthInfo> e(List<String> list, Context context, int i, String str, int i2) {
        if (list == null || list.isEmpty()) {
            tos.e("PreAuthManger", "createAuthInfoList permissionTypes is empty");
            return Collections.emptyList();
        }
        if (context == null) {
            tos.e("PreAuthManger", "createAuthInfoList context is null");
            return Collections.emptyList();
        }
        String e2 = tri.e(i, str, i2);
        ArrayList arrayList = new ArrayList();
        for (String str2 : list) {
            AuthInfo authInfo = new AuthInfo();
            authInfo.setKey(e2 + "_" + str2);
            authInfo.setAppUid(i);
            authInfo.setUserId(str);
            authInfo.setAppId(i2);
            authInfo.setPermission(str2);
            authInfo.setOpenStatus(2);
            arrayList.add(authInfo);
            tos.b("PreAuthManger", "createPerAuthInfoList authInfo:" + authInfo.toString());
        }
        return arrayList;
    }

    private boolean c(List<AuthInfo> list) {
        if (list == null || list.isEmpty()) {
            tos.a("PreAuthManger", "isContainNormalPermission authInfoList is empty");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<AuthInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPermission());
        }
        arrayList.retainAll(c);
        tos.b("PreAuthManger", "isContainNormalPermission permissionList is:" + Arrays.toString(arrayList.toArray(new String[0])));
        if (arrayList.isEmpty()) {
            tos.a("PreAuthManger", "isContainNormalPermission permissionList is empty");
            return false;
        }
        tos.a("PreAuthManger", "isContainNormalPermission already grant permission");
        return true;
    }

    private List<String> d(int i) {
        List<String> e2 = e(i);
        if (e2 == null || e2.isEmpty()) {
            tos.d("PreAuthManger", "getNormalPermissionFromScope permissionsFromScope is empty");
            return Collections.emptyList();
        }
        e2.retainAll(c);
        tos.b("PreAuthManger", "getNormalPermissionFromScope intersection is:" + Arrays.toString(e2.toArray(new String[0])));
        return e2;
    }

    private List<String> e(int i) {
        if (i == 0) {
            tos.e("PreAuthManger", "getPermissionByScope appId is invalid");
            return Collections.emptyList();
        }
        String valueOf = String.valueOf(i);
        ScopeServerRequest scopeServerRequest = new ScopeServerRequest(valueOf);
        Context a2 = tot.a();
        String url = scopeServerRequest.getUrl(tri.e(a2));
        ScopeManager scopeManager = new ScopeManager(a2);
        scopeManager.setScopeServerUrl(valueOf, url);
        ScopeInfoResponse scope = scopeManager.getScope(valueOf, "wearEngine");
        if (scope == null) {
            tos.e("PreAuthManger", "getPermissionByScope scopeInfoResponse is null");
            return Collections.emptyList();
        }
        tos.b("PreAuthManger", "getPermissionByScope scopeInfoResponse is :" + scope.toString());
        return a(scope);
    }

    private List<String> a(ScopeInfoResponse scopeInfoResponse) {
        List<ScopeInfo> scopes = scopeInfoResponse.getScopes();
        if (scopes == null || scopes.isEmpty()) {
            tos.e("PreAuthManger", "getPermissionByScopeInfoResponse scopeInfoList is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ScopeInfo scopeInfo : scopes) {
            tos.b("PreAuthManger", "getPermissionByScopeInfoResponse scopeInfo is :" + scopeInfo.toString());
            List<String> permissions = scopeInfo.getPermissions();
            if (permissions != null && !permissions.isEmpty()) {
                arrayList.add(a(permissions));
            }
        }
        return arrayList;
    }

    private String a(List<String> list) {
        for (Map.Entry<String, String> entry : f17275a.entrySet()) {
            if (list.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }
}
