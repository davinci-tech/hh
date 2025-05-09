package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle;
import com.huawei.health.suggestion.protobuf.CourseProto;
import com.huawei.health.suggestion.protobuf.CourseStateProto;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dty extends dud implements DataReceiveCallback {
    private ExtendHandler b;
    private boolean e = false;

    static class b {
        private static final dty e = new dty();
    }

    public static dty b() {
        return b.e;
    }

    class c implements CourseLinkageLifecycle {
        private DeviceInfo c;

        c() {
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void start(int i, Bundle bundle) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "AppCommandListener app start, sportType:", Integer.valueOf(i));
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "CourseLinkageReverse");
            this.c = deviceInfo;
            if (dwh.b(deviceInfo)) {
                cuk.b().registerDeviceSampleListener("hw.course.linkage", dty.b());
                dty.this.ZT_(i, bundle);
                dty.this.ZU_(bundle, this.c);
                return;
            }
            dty.this.c.start(i, bundle);
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void resume(int i) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "AppCommandListener app resume, sportType:", Integer.valueOf(i));
            dty.this.f11837a = i;
            dty.this.c.resume(i);
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void pause(int i) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "AppCommandListener app pause, sportType:", Integer.valueOf(i));
            dty.this.c.pause(i);
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void stop(int i) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "AppCommandListener app stop, sportType:", Integer.valueOf(i));
            dty.this.e = false;
            dty.this.c.stop(i);
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void chapterForward(CourseStateProto.CourseState courseState) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "mCourseLinkageLifecycle app chapterForward");
            dwh.a(courseState, 5, this.c);
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void chapterBackward(CourseStateProto.CourseState courseState) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "mCourseLinkageLifecycle app chapterBackward");
            dwh.a(courseState, 6, this.c);
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void chapterBreak(CourseStateProto.CourseState courseState) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "mCourseLinkageLifecycle app chapterBreak");
            dwh.a(courseState, 7, this.c);
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void volumeAdjust(CourseStateProto.CourseState courseState) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "mCourseLinkageLifecycle app volumeAdjust");
            dwh.a(courseState, 8, this.c);
        }
    }

    @Override // defpackage.dud
    protected void a() {
        ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "registerDeviceConnectListener");
        super.a();
    }

    public void d() {
        c cVar = new c();
        ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "registerAppCommandListener, appCommandListener: ", cVar);
        CoachController.d().d(CoachController.StatusSource.APP, cVar);
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "onDataReceived courseLinkage");
        if (deviceInfo == null || cvrVar == null) {
            ReleaseLogUtil.d("HWhealthLinkage_CourseLinkageReverse", "onDataReceived device or message is null.");
            return;
        }
        String d = d(cvrVar);
        if (TextUtils.isEmpty(d)) {
            return;
        }
        List<cwd> b2 = b(d);
        int a2 = a(b2);
        if (a2 == 4) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "onDataReceived app start");
            this.e = true;
            i();
            return;
        }
        if (a2 == 5) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "onDataReceived wear chapterForward");
            CoachController.d().b(CoachController.StatusSource.NEW_LINK_WEAR, 0);
            return;
        }
        if (a2 == 6) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "onDataReceived wear chapterBackward");
            CoachController.d().c(CoachController.StatusSource.NEW_LINK_WEAR, 0);
        } else {
            if (a2 == 8) {
                ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "onDataReceived wear volumeAdjust");
                CourseStateProto.CourseState d2 = d(b2);
                if (d2 == null) {
                    ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "onDataReceived wear volumeAdjust, courseState null");
                    return;
                } else {
                    CoachController.d().d(CoachController.StatusSource.NEW_LINK_WEAR, d2.toBuilder());
                    return;
                }
            }
            ReleaseLogUtil.d("HWhealthLinkage_CourseLinkageReverse", "onDataReceived message is nothing.");
        }
    }

    private String d(cvr cvrVar) {
        if (!(cvrVar instanceof cvq)) {
            ReleaseLogUtil.d("HWhealthLinkage_CourseLinkageReverse", "getConfigData message is error.");
            return "";
        }
        List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
        if (koq.b(configInfoList)) {
            ReleaseLogUtil.d("HWhealthLinkage_CourseLinkageReverse", "getConfigData sampleConfigList is empty");
            return "";
        }
        cvn cvnVar = configInfoList.get(0);
        if (cvnVar == null) {
            return "";
        }
        String d = cvx.d(cvnVar.b());
        ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "getConfigData configData = ", d);
        return d;
    }

    private CourseStateProto.CourseState d(List<cwd> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "parseConfigDataTlv0x03 tlvs isEmpty");
            return null;
        }
        try {
            return CourseStateProto.CourseState.parseFrom(cvx.a(list.get(0).c()));
        } catch (InvalidProtocolBufferException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_CourseLinkageReverse", "parseConfigDataTlv0x03 protoException: ", ExceptionUtils.d(e2));
            return null;
        }
    }

    private List<cwd> b(String str) {
        ArrayList arrayList = new ArrayList(10);
        try {
            return new cwl().a(str).e();
        } catch (cwg e2) {
            ReleaseLogUtil.c("HWhealthLinkage_CourseLinkageReverse", "getConfigDataTlv TlvException: ", ExceptionUtils.d(e2));
            return arrayList;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0048, code lost:
    
        if (r3.equals(com.huawei.hms.support.hianalytics.HiAnalyticsConstant.KeyAndValue.NUMBER_01) == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int a(java.util.List<defpackage.cwd> r10) {
        /*
            r9 = this;
            boolean r0 = defpackage.koq.b(r10)
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            java.lang.String r2 = "HWhealthLinkage_CourseLinkageReverse"
            if (r0 == 0) goto L14
            java.lang.String r10 = "getCommandId tlvs isEmpty"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r10)
            return r1
        L14:
            r0 = 0
            java.lang.Object r10 = r10.get(r0)
            cwd r10 = (defpackage.cwd) r10
            java.lang.String r3 = r10.e()
            r3.hashCode()
            int r4 = r3.hashCode()
            r5 = 2
            r6 = 1
            java.lang.String r7 = "02"
            java.lang.String r8 = "01"
            switch(r4) {
                case 1537: goto L44;
                case 1538: goto L3b;
                case 1539: goto L30;
                default: goto L2f;
            }
        L2f:
            goto L4a
        L30:
            java.lang.String r0 = "03"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L39
            goto L4a
        L39:
            r0 = r5
            goto L4b
        L3b:
            boolean r0 = r3.equals(r7)
            if (r0 != 0) goto L42
            goto L4a
        L42:
            r0 = r6
            goto L4b
        L44:
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L4b
        L4a:
            r0 = -1
        L4b:
            if (r0 == 0) goto L70
            if (r0 == r6) goto L5e
            if (r0 == r5) goto L5b
            java.lang.String r10 = "getCommandId nothing"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r10)
            return r1
        L5b:
            r10 = 8
            return r10
        L5e:
            int r10 = r10.b()
            if (r10 != 0) goto L66
            r10 = 4
            return r10
        L66:
            java.lang.String r10 = "getCommandId not start"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r10)
            return r1
        L70:
            java.lang.String r10 = r10.c()
            boolean r0 = r8.equals(r10)
            if (r0 == 0) goto L7c
            r10 = 5
            return r10
        L7c:
            boolean r10 = r7.equals(r10)
            if (r10 == 0) goto L84
            r10 = 6
            return r10
        L84:
            java.lang.String r10 = "getCommandId not charpter"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dty.a(java.util.List):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ZU_(Bundle bundle, DeviceInfo deviceInfo) {
        if (bundle == null) {
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "optionalParam is null");
            i();
            return;
        }
        if (bundle.containsKey("CourseStateProto") && (bundle.getSerializable("CourseProto") instanceof CourseProto.CourseStorageInfo)) {
            dwh.a((CourseProto.CourseStorageInfo) bundle.getSerializable("CourseProto"), deviceInfo);
        }
        if (bundle.containsKey("CourseStateProto") && (bundle.getSerializable("CourseStateProto") instanceof CourseStateProto.CourseState)) {
            dwh.d((CourseStateProto.CourseState) bundle.getSerializable("CourseStateProto"), deviceInfo);
        }
        this.b = HandlerCenter.yt_(new e(), "CourseLinkageReverse");
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.b.sendMessage(obtain, 2000L);
        ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "startSportWithReverseControl sendMessage");
    }

    class e implements Handler.Callback {
        private e() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "mHasAcceptStartReply: ", Boolean.valueOf(dty.this.e));
                if (!dty.this.e) {
                    dty.this.i();
                    ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", "StartHandlerCallback startSport");
                }
                return true;
            }
            LogUtil.h("CourseLinkageReverse", "no support message : ", Integer.valueOf(message.what));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ZT_(int i, Bundle bundle) {
        this.f11837a = i;
        if (bundle != null) {
            this.d = bundle.getLong("startSportTime");
            ReleaseLogUtil.e("HWhealthLinkage_CourseLinkageReverse", " startTime:", Long.valueOf(this.d));
        }
    }

    @Override // defpackage.dud
    public void c() {
        this.e = false;
        ExtendHandler extendHandler = this.b;
        if (extendHandler != null) {
            extendHandler.quit(false);
        }
        super.c();
    }
}
