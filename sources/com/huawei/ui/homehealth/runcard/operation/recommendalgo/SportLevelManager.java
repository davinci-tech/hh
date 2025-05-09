package com.huawei.ui.homehealth.runcard.operation.recommendalgo;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.runcard.operation.operationpositions.RecommedRunCourseInterators;
import com.huawei.ui.homehealth.runcard.operation.recommendalgo.model.SportLevel;
import com.huawei.up.model.UserInfomation;
import defpackage.koq;
import defpackage.kpm;
import defpackage.oqb;
import defpackage.oqj;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes9.dex */
public class SportLevelManager {

    /* renamed from: a, reason: collision with root package name */
    private SportLevelCallback f9540a;
    private d c;
    private SportLevel d;
    private UserInfomation j;
    private StorageParams g = new StorageParams();
    private String b = Integer.toString(20002);
    private Handler e = new Handler() { // from class: com.huawei.ui.homehealth.runcard.operation.recommendalgo.SportLevelManager.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i == 1001) {
                SportLevelManager.this.dfB_(message);
            } else {
                if (i != 1002) {
                    return;
                }
                SportLevelManager.this.dfA_(message);
            }
        }
    };

    public interface SportLevelCallback {
        void onResponse(SportLevel sportLevel);
    }

    public SportLevelManager() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), this.b, "LAST_SPORT_LEVEL");
        if (TextUtils.isEmpty(b)) {
            this.d = new SportLevel(-1, 0);
        } else {
            try {
                this.d = (SportLevel) new Gson().fromJson(b, SportLevel.class);
            } catch (JsonSyntaxException e) {
                LogUtil.h("Track_SportLevelManager", "getLastSportLevel ", e.getMessage());
                try {
                    this.d = new SportLevel(Integer.parseInt(b), 0);
                } catch (NumberFormatException e2) {
                    LogUtil.h("Track_SportLevelManager", "getLastSportLevel e1", e2.getMessage());
                    this.d = new SportLevel(-1, 0);
                }
            }
        }
        this.c = new d(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dfA_(Message message) {
        LogUtil.a("Track_SportLevelManager", "handleRunHistoryMsg");
        oqb oqbVar = new oqb();
        if (this.f9540a != null) {
            if (koq.e(message.obj, HiHealthData.class)) {
                this.f9540a.onResponse(a(oqbVar.b((List) message.obj, this.j)));
            } else {
                LogUtil.h("Track_SportLevelManager", "Has not sport level");
                this.f9540a.onResponse(a(new SportLevel(-1, 0)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dfB_(Message message) {
        LogUtil.a("Track_SportLevelManager", "handleVo2MaxMsg");
        oqj oqjVar = new oqj();
        SportLevel sportLevel = new SportLevel(-1, 0);
        try {
            if (koq.e(message.obj, HiHealthData.class)) {
                sportLevel = oqjVar.a((List) message.obj, this.j);
            }
        } catch (ClassCastException e) {
            LogUtil.h("Track_SportLevelManager", e.getMessage());
        }
        if (sportLevel != null && sportLevel.acquireLevel() != -1) {
            LogUtil.a("Track_SportLevelManager", "handleVo2MaxMsg sportLevel is ", sportLevel);
            SportLevelCallback sportLevelCallback = this.f9540a;
            if (sportLevelCallback != null) {
                sportLevelCallback.onResponse(a(sportLevel));
                return;
            }
            return;
        }
        LogUtil.a("Track_SportLevelManager", "handleVo2MaxMsg no Vo2Max ,get Run data ");
        a();
    }

    static class d implements HiAggregateListener {
        WeakReference<Handler> c;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        d(Handler handler) {
            this.c = null;
            this.c = new WeakReference<>(handler);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeakReference<Handler> weakReference = this.c;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.h("Track_SportLevelManager", "readVo2Max handler is null");
                return;
            }
            Handler handler = this.c.get();
            if (handler == null) {
                LogUtil.h("Track_SportLevelManager", "MyHiAggregateListener handler is null");
                return;
            }
            Message obtainMessage = handler.obtainMessage(1001);
            if (list == null) {
                LogUtil.h("Track_SportLevelManager", "readVo2Max datas is null");
                obtainMessage.obj = null;
            } else {
                LogUtil.a("Track_SportLevelManager", "readVo2Max ", list);
                obtainMessage.obj = list;
            }
            handler.sendMessage(obtainMessage);
        }
    }

    private void a() {
        LogUtil.a("Track_SportLevelManager", "readRunData");
        long currentTimeMillis = System.currentTimeMillis();
        kpm.c().c(currentTimeMillis - 2592000000L, currentTimeMillis, 258, false, (IBaseResponseCallback) new c(this));
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<SportLevelManager> c;

        c(SportLevelManager sportLevelManager) {
            this.c = new WeakReference<>(sportLevelManager);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void d(int r3, java.lang.Object r4) {
            /*
                r2 = this;
                java.lang.String r3 = "RunDataCallBack onResponse"
                java.lang.Object[] r3 = new java.lang.Object[]{r3}
                java.lang.String r0 = "Track_SportLevelManager"
                com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
                java.lang.Class<com.huawei.hihealth.HiHealthData> r3 = com.huawei.hihealth.HiHealthData.class
                boolean r3 = defpackage.koq.e(r4, r3)     // Catch: java.lang.ClassCastException -> L16
                if (r3 == 0) goto L24
                java.util.List r4 = (java.util.List) r4     // Catch: java.lang.ClassCastException -> L16
                goto L25
            L16:
                r3 = move-exception
                java.lang.String r4 = "parseTrackSimplifyData "
                java.lang.String r3 = r3.getMessage()
                java.lang.Object[] r3 = new java.lang.Object[]{r4, r3}
                com.huawei.hwlogsmodel.LogUtil.h(r0, r3)
            L24:
                r4 = 0
            L25:
                java.lang.ref.WeakReference<com.huawei.ui.homehealth.runcard.operation.recommendalgo.SportLevelManager> r3 = r2.c
                java.lang.Object r3 = r3.get()
                com.huawei.ui.homehealth.runcard.operation.recommendalgo.SportLevelManager r3 = (com.huawei.ui.homehealth.runcard.operation.recommendalgo.SportLevelManager) r3
                if (r3 == 0) goto L43
                android.os.Handler r0 = com.huawei.ui.homehealth.runcard.operation.recommendalgo.SportLevelManager.dfz_(r3)
                r1 = 1002(0x3ea, float:1.404E-42)
                android.os.Message r0 = r0.obtainMessage(r1)
                r0.obj = r4
                android.os.Handler r3 = com.huawei.ui.homehealth.runcard.operation.recommendalgo.SportLevelManager.dfz_(r3)
                r3.sendMessage(r0)
                goto L4c
            L43:
                java.lang.String r3 = "mWeakReference.get() is null"
                java.lang.Object[] r3 = new java.lang.Object[]{r3}
                com.huawei.hwlogsmodel.LogUtil.h(r0, r3)
            L4c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.homehealth.runcard.operation.recommendalgo.SportLevelManager.c.d(int, java.lang.Object):void");
        }
    }

    public SportLevel a(SportLevel sportLevel) {
        if (sportLevel == null) {
            LogUtil.a("Track_SportLevelManager", "updateSportLevel currentLevel is null");
            sportLevel = new SportLevel(-1, 0);
        }
        SportLevel sportLevel2 = this.d;
        if (sportLevel2 == null) {
            LogUtil.a("Track_SportLevelManager", "updateSportLevel mLastSportLevel is null");
            return sportLevel;
        }
        if (sportLevel2.acquireLevel() != sportLevel.acquireLevel()) {
            RecommedRunCourseInterators.a();
        }
        LogUtil.a("Track_SportLevelManager", "updateSportLevel ", sportLevel, " ", this.d);
        this.d = sportLevel;
        SharedPreferenceManager.e(BaseApplication.getContext(), this.b, "LAST_SPORT_LEVEL", new Gson().toJson(this.d), this.g);
        return sportLevel;
    }
}
