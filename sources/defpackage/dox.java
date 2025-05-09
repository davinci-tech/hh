package defpackage;

import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.request.SleepAudioInfoApi;
import com.huawei.health.playercontroller.api.PlayerEventApi;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ParamsFactory;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import defpackage.evd;
import defpackage.evh;
import defpackage.evj;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class dox implements PlayerEventApi {
    private evl b;
    private SleepAudioInfoApi d;
    private ParamsFactory e;

    @Override // com.huawei.health.playercontroller.api.PlayerEventApi
    public void onPlayerStartPlayEvent(final evl evlVar, long j, int i, long j2) {
        this.b = null;
        if (evlVar == null) {
            LogUtil.h("CoursePlayerEventImpl", "onPlayerStartPlayEvent eventParamInfo is invalid");
        } else {
            LogUtil.a("CoursePlayerEventImpl", "onPlayerStartPlayEvent");
            ThreadPoolManager.d().execute(new Runnable() { // from class: dpa
                @Override // java.lang.Runnable
                public final void run() {
                    dox.this.a(evlVar);
                }
            });
        }
    }

    @Override // com.huawei.health.playercontroller.api.PlayerEventApi
    public void onPlayerProgressChange(final evl evlVar, long j, final int i) {
        if (evlVar == null) {
            LogUtil.h("CoursePlayerEventImpl", "onPlayerProgressChange eventParamInfo is invalid");
        } else {
            LogUtil.a("CoursePlayerEventImpl", "onPlayerProgressChange progress:", Long.valueOf(j), " playDuration:", Integer.valueOf(i));
            ThreadPoolManager.d().execute(new Runnable() { // from class: dpc
                @Override // java.lang.Runnable
                public final void run() {
                    dox.this.d(i, evlVar);
                }
            });
        }
    }

    /* synthetic */ void d(int i, evl evlVar) {
        if (i < 180) {
            return;
        }
        a(evlVar, i);
    }

    @Override // com.huawei.health.playercontroller.api.PlayerEventApi
    public void onPlayerPauseEvent(final evl evlVar, final long j, final int i, final long j2) {
        if (evlVar == null) {
            LogUtil.h("CoursePlayerEventImpl", "onPlayerPauseEvent eventParamInfo is invalid");
        } else {
            LogUtil.a("CoursePlayerEventImpl", "onPlayerPauseEvent progress:", Long.valueOf(j), " playDuration:", Integer.valueOf(i));
            ThreadPoolManager.d().execute(new Runnable() { // from class: doz
                @Override // java.lang.Runnable
                public final void run() {
                    dox.this.a(evlVar, j, i, j2);
                }
            });
        }
    }

    /* synthetic */ void a(evl evlVar, long j, int i, long j2) {
        d(evlVar.d(), j, false);
        e(evlVar, i, j2);
        b(evlVar.d(), i);
    }

    @Override // com.huawei.health.playercontroller.api.PlayerEventApi
    public void onPlayerStopEvent(final evl evlVar, final long j, final int i, final long j2, boolean z) {
        if (evlVar == null) {
            LogUtil.h("CoursePlayerEventImpl", "onPlayerStopEvent eventParamInfo is invalid");
        } else if (z) {
            LogUtil.h("CoursePlayerEventImpl", "onPlayerStopEvent isReportedOnPause, return");
        } else {
            LogUtil.a("CoursePlayerEventImpl", "onPlayerStopEvent progress:", Long.valueOf(j), " playDuration:", Integer.valueOf(i));
            ThreadPoolManager.d().execute(new Runnable() { // from class: dot
                @Override // java.lang.Runnable
                public final void run() {
                    dox.this.e(evlVar, j, i, j2);
                }
            });
        }
    }

    /* synthetic */ void e(evl evlVar, long j, int i, long j2) {
        d(evlVar.d(), j, false);
        e(evlVar, i, j2);
        b(evlVar.d(), i);
    }

    @Override // com.huawei.health.playercontroller.api.PlayerEventApi
    public void onPlayerFinishedMediaEvent(final evl evlVar, final long j, final int i, final long j2) {
        if (evlVar == null) {
            LogUtil.h("CoursePlayerEventImpl", "onPlayerStopEvent eventParamInfo is invalid");
        } else {
            LogUtil.a("CoursePlayerEventImpl", "onPlayerFinishedMediaEvent, progress:", Long.valueOf(j), " playDuration:", Integer.valueOf(i));
            ThreadPoolManager.d().execute(new Runnable() { // from class: doy
                @Override // java.lang.Runnable
                public final void run() {
                    dox.this.c(evlVar, j, i, j2);
                }
            });
        }
    }

    /* synthetic */ void c(evl evlVar, long j, int i, long j2) {
        d(evlVar.d(), j, true);
        e(evlVar, i, j2);
        b(evlVar.d(), i);
        a(evlVar, i);
    }

    @Override // com.huawei.health.playercontroller.api.PlayerEventApi
    public void onPlayStatusChangeFromNotification(evl evlVar, int i) {
        if (evlVar == null) {
            LogUtil.h("CoursePlayerEventImpl", "onPlayStatusChangeFromNotification eventParamInfo is invalid");
            return;
        }
        int i2 = CommonUtil.ac(BaseApplication.e()) ? 8 : 7;
        int i3 = i == 3 ? 0 : 1;
        if (evlVar.j() > 0) {
            c(evlVar, i2, i3);
        } else {
            d(evlVar, i2, i3);
        }
    }

    private void b(int i, int i2) {
        ReleaseLogUtil.b("CoursePlayerEventImpl", "reportFinishKakaTask audioId:", Integer.valueOf(i), " playDuration:", Integer.valueOf(i2));
        HashMap hashMap = new HashMap(10);
        hashMap.put(KakaConstants.SLEEP_MUSIC_ID, Integer.valueOf(i));
        hashMap.put(KakaConstants.SLEEP_MUSIC_DURATION, Integer.valueOf(i2 * 1000));
        bzw.e().finishKakaTask(BaseApplication.e(), 30009, hashMap);
    }

    private void d(int i, long j, boolean z) {
        ReleaseLogUtil.b("R_CoursePlayerEventImpl", "saveSleepPlayRecord");
        evh b = new evh.a().a(i).d(j).b(z).b();
        if (this.e == null) {
            this.e = new beb();
        }
        Map<String, String> headers = this.e.getHeaders();
        String b2 = lql.b(this.e.getBody(b));
        LogUtil.a("CoursePlayerEventImpl", "saveSleepPlayRecord apply url: ", b.getUrl(), " apply headers:", headers.toString(), " apply body: ", b2);
        if (this.d == null) {
            this.d = (SleepAudioInfoApi) lqi.d().b(SleepAudioInfoApi.class);
        }
        try {
            ReleaseLogUtil.b("R_CoursePlayerEventImpl", "saveSleepPlayRecord response code is: ", Integer.valueOf(this.d.saveSleepPlayRecord(b.getUrl(), this.e.getHeaders(), b2).execute().getCode()));
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_CoursePlayerEventImpl", "saveSleepPlayRecord exception");
        }
    }

    private void e(evl evlVar, int i, long j) {
        ReleaseLogUtil.b("R_CoursePlayerEventImpl", "addSleepPlayLog");
        evd a2 = new evd.b().d(evlVar.d()).e(i).a(j).c(System.currentTimeMillis()).b(jdl.q(System.currentTimeMillis())).a();
        if (this.e == null) {
            this.e = new beb();
        }
        Map<String, String> headers = this.e.getHeaders();
        String b = lql.b(this.e.getBody(a2));
        LogUtil.a("CoursePlayerEventImpl", "addSleepPlayLog apply url: ", a2.getUrl(), " apply headers:", headers.toString(), " apply body: ", b);
        if (this.d == null) {
            this.d = (SleepAudioInfoApi) lqi.d().b(SleepAudioInfoApi.class);
        }
        try {
            ReleaseLogUtil.b("R_CoursePlayerEventImpl", "addSleepPlayLog response code is: ", Integer.valueOf(this.d.addSleepPlayLog(a2.getUrl(), this.e.getHeaders(), b).execute().getCode()));
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_CoursePlayerEventImpl", "addSleepPlayLog exception");
        }
    }

    private void a(evl evlVar, int i) {
        if (this.b == null || evlVar.d() != this.b.d()) {
            List<evk> i2 = evlVar.i();
            if (koq.b(i2)) {
                LogUtil.c("CoursePlayerEventImpl", "resourceTypes is empty");
                return;
            }
            ReleaseLogUtil.b("CoursePlayerEventImpl", "saveWorkoutRecord enter");
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setDeviceUuid("-1");
            hiHealthData.setStartTime(System.currentTimeMillis());
            hiHealthData.setEndTime(System.currentTimeMillis());
            final int value = i2.get(0).d() == 5 ? DicDataTypeUtil.DataType.MINDFULNESS_TYPE.value() : DicDataTypeUtil.DataType.COURSE_RECORD.value();
            hiHealthData.setType(value);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("trainTime", i);
                jSONObject.put("lessonID", String.valueOf(evlVar.d()));
                jSONObject.put("lessonName", evlVar.e());
                jSONObject.put("resourceType", nrv.a(evlVar.i()));
                jSONObject.put("lessonType", evlVar.a());
                hiHealthData.setMetaData(jSONObject.toString());
                hiHealthData.setSequenceData("{}");
                HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
                hiDataInsertOption.addData(hiHealthData);
                HiHealthManager.d(BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: dpb
                    @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                    public final void onResult(int i3, Object obj) {
                        dox.this.c(value, i3, obj);
                    }
                });
                this.b = evlVar;
            } catch (JSONException e) {
                ReleaseLogUtil.c("CoursePlayerEventImpl", "value JSONException ", ExceptionUtils.d(e));
            }
        }
    }

    /* synthetic */ void c(int i, int i2, Object obj) {
        ReleaseLogUtil.b("CoursePlayerEventImpl", "error code is ", Integer.valueOf(i2), " object is ", obj);
        if (i2 == 0) {
            a(i);
        }
    }

    private void a(int i) {
        ReleaseLogUtil.b("CoursePlayerEventImpl", "syncHiHealthData is network connected : ", Boolean.valueOf(CommonUtil.aa(BaseApplication.e())));
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(i);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setForceSync(true);
        LogUtil.a("CoursePlayerEventImpl", "type is ", Integer.valueOf(i));
        hiSyncOption.setSyncId(System.currentTimeMillis());
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(evl evlVar) {
        ReleaseLogUtil.b("R_CoursePlayerEventImpl", "postBehavior");
        if (evlVar.j() <= 0) {
            return;
        }
        evj e = new evj.a().e(4).a(String.valueOf(evlVar.d())).b(String.valueOf(evlVar.j())).a(3).e();
        if (this.e == null) {
            this.e = new beb();
        }
        Map<String, String> headers = this.e.getHeaders();
        String b = lql.b(this.e.getBody(e));
        LogUtil.a("CoursePlayerEventImpl", "postBehavior apply url: ", e.getUrl(), " apply headers:", headers.toString(), " apply body: ", b);
        if (this.d == null) {
            this.d = (SleepAudioInfoApi) lqi.d().b(SleepAudioInfoApi.class);
        }
        try {
            ReleaseLogUtil.b("R_CoursePlayerEventImpl", "postBehavior response code is: ", Integer.valueOf(this.d.postBehavior(e.getUrl(), this.e.getHeaders(), b).execute().getCode()));
        } catch (JsonSyntaxException | IOException | IllegalStateException unused) {
            ReleaseLogUtil.c("R_CoursePlayerEventImpl", "postBehavior exception");
        }
    }

    private void d(evl evlVar, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", evlVar.e());
        hashMap.put("click", 1);
        hashMap.put("audioType", Integer.valueOf(evlVar.a()));
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("audioId", Integer.valueOf(evlVar.d()));
        hashMap.put("BIType", 2);
        hashMap.put("status", Integer.valueOf(i2));
        hashMap.put("vipMode", Integer.valueOf(evlVar.f()));
        hashMap.put("resourceLabel", evlVar.h());
        hashMap.put("isSeries", false);
        hashMap.put("isAuditoryClip", false);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.SLEEP_RECORD_1090095.value(), hashMap, 0);
    }

    private void c(evl evlVar, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("SleepingType", Integer.valueOf(evlVar.a()));
        hashMap.put("decompressingType", Integer.valueOf(evlVar.c()));
        hashMap.put("resourceMode", Integer.valueOf(evlVar.f()));
        hashMap.put("resourceName", evlVar.g());
        hashMap.put("name", evlVar.e());
        hashMap.put("id", Integer.valueOf(evlVar.j()));
        hashMap.put("event", 2);
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("status", Integer.valueOf(i2));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.SERIES_COURSE_RECORD_1130078.value(), hashMap, 0);
    }
}
