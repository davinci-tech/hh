package defpackage;

import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.devicelink.bean.CloverProgress;
import com.huawei.basichealthmodel.devicelink.bean.NoticesBean;
import com.huawei.basichealthmodel.devicelink.bean.ResponseMsgBody;
import com.huawei.basichealthmodel.devicelink.bean.TaskBean;
import com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelNoticeBody;
import com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelReportMessage;
import com.huawei.basichealthmodel.devicelink.pbjava.report.HealthModelTaskBody;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bae {
    public static ResponseMsgBody b(int i, int i2, String str) {
        LogUtil.a("HealthLife_DeviceTaskUtils", "getResponseBean resultCode = ", Integer.valueOf(i2));
        ResponseMsgBody responseMsgBody = new ResponseMsgBody();
        responseMsgBody.setResultCode(i2);
        responseMsgBody.setResultDes(str);
        responseMsgBody.setMsgType(i);
        return responseMsgBody;
    }

    public static HealthModelReportMessage.Report b(ResponseMsgBody responseMsgBody) {
        HealthModelReportMessage.Report.Builder newBuilder = HealthModelReportMessage.Report.newBuilder();
        if (responseMsgBody == null) {
            return newBuilder.build();
        }
        newBuilder.setMsgVer((float) responseMsgBody.getMsgVer());
        int msgType = responseMsgBody.getMsgType();
        newBuilder.setMsgType(msgType);
        int i = 0;
        if (msgType == 2001) {
            HealthModelTaskBody.TaskBody.e i2 = HealthModelTaskBody.TaskBody.i();
            List<TaskBean> tasks = responseMsgBody.getTasks();
            if (koq.c(tasks)) {
                while (i < tasks.size()) {
                    i2.b(i, d(HealthModelTaskBody.TaskBody.c.e(), tasks.get(i)));
                    i++;
                }
            }
            i2.d(responseMsgBody.getResultCode());
            i2.d(responseMsgBody.getResultDes());
            i2.c(responseMsgBody.getRecordDay());
            i2.e(responseMsgBody.getAllTasks());
            i2.c(responseMsgBody.getCompletedTasks());
            CloverProgress cloverProgress = responseMsgBody.getCloverProgress();
            if (cloverProgress != null) {
                i2.a(ByteString.copyFrom(e(cloverProgress)));
            } else {
                LogUtil.h("HealthLife_DeviceTaskUtils", "cloverProgress is null");
            }
            newBuilder.setMsgBody(ByteString.copyFrom(i2.build().toByteArray()));
        } else if (msgType == 2002 || msgType == 2003) {
            HealthModelNoticeBody.NoticeBody.b g = HealthModelNoticeBody.NoticeBody.g();
            List<NoticesBean> noticesBean = responseMsgBody.getNoticesBean();
            if (koq.c(noticesBean)) {
                while (i < noticesBean.size()) {
                    g.d(i, a(HealthModelNoticeBody.NoticeBody.e.e(), noticesBean.get(i)));
                    i++;
                }
            } else {
                LogUtil.h("HealthLife_DeviceTaskUtils", "noticesBean is null");
            }
            newBuilder.setMsgBody(ByteString.copyFrom(g.build().toByteArray()));
        } else {
            LogUtil.h("HealthLife_DeviceTaskUtils", "covertResponse msgType is undefined");
        }
        return newBuilder.build();
    }

    public static HealthModelReportMessage.Report e(ResponseMsgBody responseMsgBody, int i, List<TaskBean> list) {
        HealthModelReportMessage.Report.Builder newBuilder = HealthModelReportMessage.Report.newBuilder();
        if (responseMsgBody == null) {
            return newBuilder.build();
        }
        newBuilder.setMsgVer((float) responseMsgBody.getMsgVer());
        newBuilder.setMsgType(responseMsgBody.getMsgType());
        HealthModelTaskBody.TaskBody.e i2 = HealthModelTaskBody.TaskBody.i();
        i2.d(responseMsgBody.getResultCode());
        i2.d(responseMsgBody.getResultDes());
        i2.c(responseMsgBody.getRecordDay());
        i2.e(responseMsgBody.getAllTasks());
        i2.c(responseMsgBody.getCompletedTasks());
        CloverProgress cloverProgress = responseMsgBody.getCloverProgress();
        if (cloverProgress != null) {
            i2.a(ByteString.copyFrom(e(cloverProgress)));
        } else {
            LogUtil.h("HealthLife_DeviceTaskUtils", "cloverProgress is null");
        }
        c(list, i, i2, c(newBuilder.build().toByteArray()));
        newBuilder.setMsgBody(ByteString.copyFrom(i2.build().toByteArray()));
        return newBuilder.build();
    }

    private static void c(List<TaskBean> list, int i, HealthModelTaskBody.TaskBody.e eVar, int i2) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_DeviceTaskUtils", "buildReportTask unPackingTaskList is empty");
            return;
        }
        LogUtil.a("HealthLife_DeviceTaskUtils", "buildReportTask unPackingTaskList size=", Integer.valueOf(list.size()));
        Iterator<TaskBean> it = list.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            HealthModelTaskBody.TaskBody.c d = d(HealthModelTaskBody.TaskBody.c.e(), it.next());
            if (i <= 0) {
                eVar.b(i3, d);
                i3++;
                it.remove();
            } else {
                int c = c(eVar.build().toByteArray()) + i2;
                if (c >= i || c + c(d.toByteArray()) >= i) {
                    return;
                }
                eVar.b(i3, d);
                i3++;
                it.remove();
            }
        }
    }

    private static byte[] e(CloverProgress cloverProgress) {
        HealthModelTaskBody.TaskBody.d.a d = HealthModelTaskBody.TaskBody.d.d();
        d.c(cloverProgress.getTop());
        d.b(cloverProgress.getRight());
        d.a(cloverProgress.getLeft());
        return d.build().toByteArray();
    }

    private static HealthModelTaskBody.TaskBody.c d(HealthModelTaskBody.TaskBody.c.a aVar, TaskBean taskBean) {
        if (taskBean == null) {
            return aVar.build();
        }
        aVar.b(taskBean.getTaskId());
        aVar.e(taskBean.getTaskTarget());
        aVar.d(taskBean.getTargetDes());
        aVar.c(taskBean.getTaskValue());
        aVar.c(taskBean.getStatus());
        aVar.e(taskBean.getRestStatus());
        aVar.b(taskBean.getTimestamp());
        aVar.a(taskBean.getTaskType());
        if (azi.c(taskBean.getTaskId())) {
            aVar.b(taskBean.getTaskName());
            aVar.g(taskBean.getUnit());
            aVar.a((float) taskBean.getProgress());
            aVar.a(taskBean.getStatusDes());
        } else {
            aVar.b(nsf.h(R$string.IDS_not_support));
            aVar.a("--/--");
        }
        return aVar.build();
    }

    private static HealthModelNoticeBody.NoticeBody.e a(HealthModelNoticeBody.NoticeBody.e.C0045e c0045e, NoticesBean noticesBean) {
        if (noticesBean == null) {
            return c0045e.build();
        }
        c0045e.d(noticesBean.getRecordDay());
        c0045e.a(noticesBean.getTaskId());
        c0045e.b(noticesBean.getTaskName());
        c0045e.d(noticesBean.getMsg());
        return c0045e.build();
    }

    private static String d(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toHexString(b & 255));
        }
        return sb.toString();
    }

    public static int c(byte[] bArr) {
        String d = d(bArr);
        if (TextUtils.isEmpty(d)) {
            return 0;
        }
        return d.length();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static NoticesBean a(String str, int i, int i2) {
        char c;
        String h;
        LogUtil.a("HealthLife_DeviceTaskUtils", "getCelebratingNoticesBean scenario ", str, " recordDay ", Integer.valueOf(i), " id ", Integer.valueOf(i2));
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("HealthLife_DeviceTaskUtils", "getCelebratingNoticesBean scenario ", str);
            return null;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -2142636936:
                if (str.equals("celebrating_messages_yesterday_perfect")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1587627490:
                if (str.equals("celebrating_messages_today_energy")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1045222601:
                if (str.equals("celebrating_messages_today_perfect")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -99007299:
                if (str.equals("celebrating_messages_yesterday_energy")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            h = nsf.h(R$string.IDS_celebrating_1_2);
        } else if (c == 1) {
            h = nsf.h(R$string.IDS_celebrating_2_1);
        } else if (c == 2) {
            h = nsf.h(R$string.IDS_celebrating_3_5);
        } else {
            if (c != 3) {
                return null;
            }
            h = nsf.h(R$string.IDS_celebrating_0_2);
        }
        if (TextUtils.isEmpty(h)) {
            ReleaseLogUtil.a("HealthLife_DeviceTaskUtils", "getCelebratingNoticesBean content ", h);
            return null;
        }
        NoticesBean noticesBean = new NoticesBean();
        noticesBean.setRecordDay(i);
        noticesBean.setMsg(h);
        noticesBean.setTaskId(i2);
        if (i2 == 1 || i2 == 100001) {
            noticesBean.setTaskName(azi.i());
        }
        return noticesBean;
    }

    public static NoticesBean d(int i) {
        NoticesBean noticesBean = new NoticesBean();
        noticesBean.setTaskId(5000);
        noticesBean.setRecordDay(i);
        noticesBean.setTaskName(azi.i());
        noticesBean.setMsg(nsf.h(R$string.IDS_health_week_report_wear_remind_new));
        return noticesBean;
    }

    public static NoticesBean a() {
        NoticesBean noticesBean = new NoticesBean();
        noticesBean.setTaskId(5001);
        noticesBean.setTaskName(azi.i());
        noticesBean.setRecordDay(DateFormatUtil.b(System.currentTimeMillis()));
        noticesBean.setMsg(nsf.h(R$string.IDS_weekly_report_reviewed_new));
        return noticesBean;
    }

    public static void a(NoticesBean noticesBean, int i, int i2, ResponseCallback<ResponseMsgBody> responseCallback) {
        LogUtil.a("HealthLife_DeviceTaskUtils", "activeSendMsgToDevice noticesBean ", noticesBean, " type ", Integer.valueOf(i), " action ", Integer.valueOf(i2), " callback ", responseCallback);
        if (noticesBean == null) {
            ReleaseLogUtil.a("HealthLife_DeviceTaskUtils", "activeSendMsgToDevice noticesBean is null");
            return;
        }
        ResponseMsgBody responseMsgBody = new ResponseMsgBody();
        responseMsgBody.setMsgType(i);
        responseMsgBody.setNoticesBean(Collections.singletonList(noticesBean));
        avm.a().b(i2, responseMsgBody, responseCallback);
    }
}
