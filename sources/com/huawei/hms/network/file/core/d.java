package com.huawei.hms.network.file.core;

import com.huawei.hms.network.file.api.Callback;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.api.IRequestManager;
import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.task.i;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class d<R extends Request> implements IRequestManager<R> {
    Set<Long> b;
    com.huawei.hms.network.file.core.task.c c;
    f e;
    GlobalRequestConfig f;
    volatile boolean g;
    volatile boolean h;

    /* renamed from: a, reason: collision with root package name */
    final ConcurrentHashMap<Long, i> f5627a = new ConcurrentHashMap<>();
    Result d = Result.RESULT_SUCCESS;

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result start(R r, Callback callback) {
        synchronized (this) {
            if (r == null) {
                return new Result(Constants.ErrorCode.REQUEST_NULL);
            }
            if (!this.f5627a.containsKey(Long.valueOf(r.getId())) && !b(r.getId())) {
                i iVar = new i(r, this.c, callback, this, this.e);
                Result k = iVar.k();
                if (k.getCode() == Constants.ErrorCode.SUCCESS.getErrorCode()) {
                    this.f5627a.put(Long.valueOf(r.getId()), iVar);
                    a(Long.valueOf(r.getId()));
                }
                return k;
            }
            FLogger.i("RequestManagerCore", "start will resume the request in DB:" + r.getId(), new Object[0]);
            return a(r, callback);
        }
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result.STATUS getRequestStatus(long j) {
        synchronized (this) {
            if (this.f5627a.containsKey(Long.valueOf(j))) {
                return Utils.getMapedStatus(this.f5627a.get(Long.valueOf(j)).d());
            }
            e<R> c = this.c.c(j);
            if (c == null) {
                return Result.STATUS.INVALID;
            }
            if (c.b() == e.a.PROCESS.ordinal()) {
                return Result.STATUS.PAUSE;
            }
            return Utils.getMapedStatus(e.a.values()[c.b()]);
        }
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public R getRequest(long j) {
        synchronized (this) {
            if (this.f5627a.containsKey(Long.valueOf(j))) {
                return (R) this.f5627a.get(Long.valueOf(j)).c();
            }
            e<R> c = this.c.c(j);
            if (c == null) {
                return null;
            }
            return c.a();
        }
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public List<R> getAllRequests() {
        synchronized (this) {
            List<R> a2 = this.c.a(true);
            if (a2 != null && a2.size() > 0) {
                return a2;
            }
            Collection<i> values = this.f5627a.values();
            if (values.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<i> it = values.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().c());
            }
            return arrayList;
        }
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result destoryRequests() {
        Result result;
        synchronized (this) {
            List<R> a2 = this.c.a(false);
            if (!Utils.isEmpty(a2)) {
                Iterator<R> it = a2.iterator();
                while (it.hasNext()) {
                    cancelRequest(it.next().getId());
                }
            }
            result = this.d;
        }
        return result;
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result closeThreadPools() {
        Result result;
        synchronized (this) {
            this.e.e();
            result = this.d;
        }
        return result;
    }

    @Override // com.huawei.hms.network.file.api.IRequestManager
    public Result cancelRequest(long j) {
        synchronized (this) {
            Result result = this.d;
            if (this.f5627a.containsKey(Long.valueOf(j))) {
                Result a2 = this.f5627a.get(Long.valueOf(j)).a();
                if (a2.getCode() == Result.SUCCESS) {
                    this.f5627a.remove(Long.valueOf(j));
                }
                return a2;
            }
            if (b(j)) {
                this.c.a(j);
                return result;
            }
            return new Result(Constants.ErrorCode.REQUEST_NO_EXIST);
        }
    }

    public void a(com.huawei.hms.network.file.core.task.c cVar) {
        this.c = cVar;
        this.b = Collections.synchronizedSet(new HashSet());
        if (cVar != null) {
            f.f().execute(new Runnable() { // from class: com.huawei.hms.network.file.core.d$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    d.this.b();
                }
            });
        }
    }

    public void a(Request request) {
        synchronized (this) {
            if (request != null) {
                if (this.f5627a.containsKey(Long.valueOf(request.getId()))) {
                    this.f5627a.remove(Long.valueOf(request.getId()));
                }
            }
        }
    }

    public Result a(R r, Callback callback) {
        boolean z;
        i iVar;
        synchronized (this) {
            if (r == null) {
                return new Result(Constants.ErrorCode.REQUEST_NULL);
            }
            long id = r.getId();
            if (this.f5627a.containsKey(Long.valueOf(id))) {
                iVar = this.f5627a.get(Long.valueOf(id));
                if (iVar.d() != e.a.PAUSE) {
                    return new Result(Constants.ErrorCode.REQUEST_STATUS_ERROR);
                }
                z = false;
            } else {
                if (!b(id)) {
                    return new Result(Constants.ErrorCode.REQUEST_NO_EXIST);
                }
                e<R> c = this.c.c(id);
                if (c == null) {
                    return new Result(Constants.ErrorCode.REQUEST_NO_EXIST);
                }
                a(Long.valueOf(r.getId()));
                if (c.b() > e.a.PAUSE.ordinal()) {
                    return new Result(Constants.ErrorCode.REQUEST_STATUS_ERROR);
                }
                i iVar2 = new i(r, this.c, callback, this, this.e);
                this.f5627a.put(Long.valueOf(r.getId()), iVar2);
                z = true;
                iVar = iVar2;
            }
            iVar.a((i) r, callback, z);
            return this.d;
        }
    }

    public Result a(long j) {
        synchronized (this) {
            Constants.ErrorCode c = c(j);
            if (c != Constants.ErrorCode.SUCCESS) {
                return new Result(c);
            }
            return this.f5627a.get(Long.valueOf(j)).i();
        }
    }

    public GlobalRequestConfig a() {
        return this.f;
    }

    private Constants.ErrorCode c(long j) {
        return !this.f5627a.containsKey(Long.valueOf(j)) ? Constants.ErrorCode.REQUEST_NO_EXIST : this.f5627a.get(Long.valueOf(j)).d() != e.a.PROCESS ? Constants.ErrorCode.REQUEST_STATUS_ERROR : Constants.ErrorCode.SUCCESS;
    }

    private boolean b(long j) {
        if (this.g && this.b.contains(Long.valueOf(j))) {
            return true;
        }
        if (this.h) {
            return false;
        }
        FLogger.i("RequestManagerCore", "isIdExistInCache get request in db", new Object[0]);
        return this.c.c(j) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        FLogger.i("RequestManagerCore", "start getCachedRequestID async", new Object[0]);
        Set<Long> a2 = this.c.a(100);
        if (!Utils.isEmpty(a2)) {
            Iterator<Long> it = a2.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }
        this.g = true;
        if (this.b.size() < 100) {
            this.h = true;
        }
        FLogger.i("RequestManagerCore", "get db data success for count:" + this.b.size() + "/100; isGetCacheComplete:" + this.h, new Object[0]);
    }

    private void a(Long l) {
        this.b.add(l);
    }

    public d(GlobalRequestConfig globalRequestConfig) {
        this.e = new f(globalRequestConfig);
        this.f = globalRequestConfig;
        FLogger.i("RequestManagerCore", "RequestManagerCore fileManager version:8.0.1.307", new Object[0]);
    }
}
