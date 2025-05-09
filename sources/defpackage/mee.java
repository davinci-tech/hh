package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mee {
    private static final Object b = new Object();
    private static volatile mee e;

    /* renamed from: a, reason: collision with root package name */
    private volatile List<AchieveObserver> f14916a = new ArrayList(8);
    private mec d;

    private mee(Context context) {
        this.d = mec.e(context);
    }

    public static mee b(Context context) {
        if (e == null) {
            synchronized (mee.class) {
                if (e == null) {
                    e = new mee(BaseApplication.getContext());
                }
            }
        }
        return e;
    }

    public boolean b(mcz mczVar) {
        if (mczVar == null) {
            return false;
        }
        if (!Utils.i()) {
            mczVar.setHuid("1");
        }
        if (CommonUtil.bu()) {
            mczVar.setHuid(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        }
        LogUtil.c("PLGACHIEVE_AchieveProvider", "insertData() : params =", Integer.valueOf(mczVar.acquireDataType()));
        IAchieveDBMgr d = this.d.d(mczVar.acquireDataType());
        return (d == null || d.insert(mczVar) == -1) ? false : true;
    }

    public boolean d(mcz mczVar) {
        if (mczVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveProvider", "deleteData achieveData is null");
            return false;
        }
        IAchieveDBMgr d = this.d.d(mczVar.acquireDataType());
        if (d == null) {
            return false;
        }
        if (!Utils.i()) {
            mczVar.setHuid("1");
        }
        return d.delete(mczVar) != -1;
    }

    public mcz b(int i, Map<String, String> map) {
        IAchieveDBMgr d = this.d.d(i);
        if (d == null || map == null || map.isEmpty()) {
            return null;
        }
        return d.query(map);
    }

    public List<mcz> d(int i, Map<String, String> map) {
        IAchieveDBMgr d = this.d.d(i);
        if (d == null || map == null || map.size() == 0) {
            return Collections.emptyList();
        }
        return d.queryAll(map);
    }

    public boolean a(mcz mczVar) {
        IAchieveDBMgr d;
        if (mczVar == null || (d = this.d.d(mczVar.acquireDataType())) == null) {
            return false;
        }
        if (!Utils.i()) {
            mczVar.setHuid("1");
        }
        return d.update(mczVar) != -1;
    }

    public void e(AchieveObserver achieveObserver) {
        synchronized (b) {
            if (achieveObserver != null) {
                if (this.f14916a != null) {
                    LogUtil.a("PLGACHIEVE_AchieveProvider", "addObserver ", Boolean.valueOf(!this.f14916a.contains(achieveObserver)));
                    if (!this.f14916a.contains(achieveObserver)) {
                        c(achieveObserver);
                    }
                    LogUtil.a("PLGACHIEVE_AchieveProvider", "addObserver list ", this.f14916a.toString());
                    return;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = "achieveObserver or mObserverList is null ";
            objArr[1] = Boolean.valueOf(this.f14916a == null);
            LogUtil.b("PLGACHIEVE_AchieveProvider", objArr);
        }
    }

    private void c(AchieveObserver achieveObserver) {
        if (achieveObserver == null || this.f14916a == null) {
            return;
        }
        String name = achieveObserver.getClass().getName();
        if (!name.contains("AchieveServiceObserver")) {
            this.f14916a.add(achieveObserver);
            return;
        }
        String obj = this.f14916a.toString();
        LogUtil.a("PLGACHIEVE_AchieveProvider", "addObserver ", Boolean.valueOf(!this.f14916a.contains(achieveObserver)), name);
        if (obj.contains(name)) {
            return;
        }
        this.f14916a.add(achieveObserver);
    }

    public void a(AchieveObserver achieveObserver) {
        synchronized (b) {
            if (this.f14916a == null) {
                LogUtil.b("PLGACHIEVE_AchieveProvider", "mObserverList is null!");
            } else {
                this.f14916a.remove(achieveObserver);
            }
        }
    }

    public void a() {
        synchronized (b) {
            if (this.f14916a != null && !this.f14916a.isEmpty()) {
                LogUtil.a("PLGACHIEVE_AchieveProvider", "removeAllObserver mObserverList= ", this.f14916a);
                Iterator it = new ArrayList(this.f14916a).iterator();
                while (it.hasNext()) {
                    AchieveObserver achieveObserver = (AchieveObserver) it.next();
                    if (achieveObserver != null) {
                        this.f14916a.remove(achieveObserver);
                    }
                }
                return;
            }
            LogUtil.b("PLGACHIEVE_AchieveProvider", "notifyAllObserver, mObserverList is null! ");
        }
    }

    public void c(int i, UserAchieveWrapper userAchieveWrapper) {
        ArrayList<AchieveObserver> arrayList = new ArrayList(8);
        synchronized (b) {
            if (koq.b(this.f14916a)) {
                LogUtil.h("PLGACHIEVE_AchieveProvider", "notifyAllObserver, mObserverList is null! ");
                return;
            }
            arrayList.addAll(this.f14916a);
            LogUtil.a("PLGACHIEVE_AchieveProvider", "notifyAllObserver observerList= ", arrayList);
            for (AchieveObserver achieveObserver : arrayList) {
                if (achieveObserver != null) {
                    achieveObserver.onDataChanged(i, userAchieveWrapper);
                }
            }
        }
    }
}
