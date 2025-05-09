package com.huawei.health.interactor;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.TrackData;
import defpackage.koq;
import defpackage.mct;
import defpackage.mcv;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class AchieveMedalsBehaviorTriggedByUi extends AchieveMedalsInteractors {
    private static long e;

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2504a;

    public AchieveMedalsBehaviorTriggedByUi(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.huawei.health.interactor.AchieveMedalsInteractors
    public void behave() {
        LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "enter behave");
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(4, new HiSubscribeListener() { // from class: com.huawei.health.interactor.AchieveMedalsBehaviorTriggedByUi.5
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                AchieveMedalsBehaviorTriggedByUi.this.f2504a = list;
                LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "register wear single run data success");
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
                LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "wear single run data onChange");
                if (SystemClock.elapsedRealtime() - AchieveMedalsBehaviorTriggedByUi.e < 5000) {
                    return;
                }
                long unused = AchieveMedalsBehaviorTriggedByUi.e = SystemClock.elapsedRealtime();
                if (i != 4) {
                    return;
                }
                AchieveMedalsBehaviorTriggedByUi.this.b();
            }
        });
    }

    public void b() {
        readSingleTrackData(new IBaseResponseCallback() { // from class: com.huawei.health.interactor.AchieveMedalsBehaviorTriggedByUi.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "register single track data to read finish and onResponse enter");
                if (koq.e(obj, HiHealthData.class)) {
                    List<HiHealthData> list = (List) obj;
                    LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "thie size of singleMovementRecords is ", Integer.valueOf(list.size()));
                    ArrayList<TrackData> searchSingleRunLongest = AchieveMedalsBehaviorTriggedByUi.this.searchSingleRunLongest(list);
                    if (searchSingleRunLongest.size() != 0) {
                        AchieveMedalsBehaviorTriggedByUi.this.e();
                        mcv d = mcv.d(AchieveMedalsBehaviorTriggedByUi.this.mContext);
                        if (d.getAdapter() == null) {
                            mcv.d(AchieveMedalsBehaviorTriggedByUi.this.mContext).setAdapter(new PluginAchieveAdapterImpl());
                        }
                        LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "toReadSingleTrackData sendTrackData size= ", Integer.valueOf(searchSingleRunLongest.size()));
                        d.getAdapter().sendTrackData(AchieveMedalsBehaviorTriggedByUi.this.mContext, searchSingleRunLongest);
                        return;
                    }
                    LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "no single run track record!");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        mct.b(this.mContext, "_syncWearData", "CHANGE");
    }

    public boolean d() {
        return TextUtils.isEmpty(mct.b(this.mContext, "_syncWearData"));
    }

    @Override // com.huawei.health.interactor.AchieveMedalsInteractors
    public void unRegister() {
        if (koq.c(this.f2504a)) {
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.f2504a, new HiUnSubscribeListener() { // from class: dzf
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("TiggeredByWear_AchieveMedalsInteractors", "isSuccess=", Boolean.valueOf(z));
                }
            });
        }
    }
}
