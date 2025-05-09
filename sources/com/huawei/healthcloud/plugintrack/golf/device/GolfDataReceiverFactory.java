package com.huawei.healthcloud.plugintrack.golf.device;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.healthcloud.plugintrack.golf.Utils;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseData;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseListInfo;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseMapInfo;
import com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadTaskUtils;
import com.huawei.healthcloud.plugintrack.golf.h5.GolfH5DownloadInterface;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.cbe;
import defpackage.nrv;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class GolfDataReceiverFactory {
    private static final int DEFAULT_INT_LENGTH = 4;
    private static final int DEFAULT_SUCCESS_CODE = 0;
    private static final String GOLF_SHAKE_RSP_RCVER_TAG = "GolfCourseMapPushShakeRspRcver";
    private static final Map<Integer, List<GolfCourseData>> LIST_MAP = Collections.synchronizedMap(new HashMap());
    private static final int SPORT_WATCH_TYPE = 0;
    private static final String TAG = "Track_GolfDataReceiverFactory";

    public static GolfDataReceiver createGolfDataReceiver(int i, int i2) {
        LogUtil.a(TAG, " createGolfDataReceiver type is: ", Integer.valueOf(i));
        if (i == GolfHiWearBusinessType.GOLF_GPS_INFO_SEND.getTypeValue()) {
            return createGPSReqRcver(i2);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_LIST_FILE.getTypeValue()) {
            return createCourseListRspRcver(i2);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_VERSION_CHECK.getTypeValue()) {
            return createCourseVersionReqRcver(i2);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_MAP_REQUEST.getTypeValue()) {
            return createCourseMapReqRcver(i2);
        }
        if (i == GolfHiWearBusinessType.GOLF_CANCEL_COURSE_MAP.getTypeValue()) {
            return createCancelCourseMapRcver(i2);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_CLUB_INFO_DEVICE.getTypeValue()) {
            return createClubReqReceiver(i2);
        }
        return null;
    }

    public static GolfDataReceiver createGolfDataReceiver(int i, int i2, GolfH5DownloadInterface.InvokerBundle invokerBundle, Object obj) {
        LogUtil.a(TAG, " createGolfDataReceiver ", Integer.valueOf(i));
        if (i == GolfHiWearBusinessType.GOLF_LOCAL_COURSE_LIST.getTypeValue()) {
            return createDeviceDownloadedCourseListRcver(i2, invokerBundle);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_PUSH_SHAKE.getTypeValue()) {
            return createCourseMapPushShakeRspRcver(i2, obj);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_FILE.getTypeValue()) {
            return createCourseMapRspRcver(i2, obj);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_DELETE.getTypeValue()) {
            return createCourseDelRspRcver(i2, invokerBundle);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_MAP_KEY.getTypeValue()) {
            return createCourseMapKeyRcver(i2, obj);
        }
        if (i == GolfHiWearBusinessType.GOLF_COURSE_CLUB_INFO.getTypeValue()) {
            return createSendGolfClubRspReceiver(i2, invokerBundle);
        }
        return null;
    }

    public static GolfDataReceiver createGolfDataReceiver(int i, int i2, GolfDataCallback golfDataCallback) {
        LogUtil.a(TAG, " createGolfDataReceiver ", Integer.valueOf(i));
        if (i == GolfHiWearBusinessType.GOLF_LOCAL_COURSE_LIST.getTypeValue()) {
            return createDeviceCourseListRcverForNative(i2, golfDataCallback);
        }
        return null;
    }

    private static GolfDataReceiver createCourseDelRspRcver(final int i, final GolfH5DownloadInterface.InvokerBundle invokerBundle) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.1
            static final String RECEIVER_TAG = "GolfCourseDelRspRcver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i3 = wrap.getInt();
                    ArrayList arrayList = new ArrayList();
                    for (int i4 = 0; i4 < i3; i4++) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(Integer.valueOf(wrap.getInt()));
                        arrayList2.add(Integer.valueOf(wrap.getInt()));
                        arrayList.add(nrv.a(arrayList2));
                    }
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, nrv.a(arrayList), " del success");
                    invokerBundle.getInvoker().onComplete(0, nrv.a(arrayList), invokerBundle.getCallbackId());
                }
            }
        };
    }

    private static GolfDataReceiver createCourseMapPushShakeRspRcver(final int i, final Object obj) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.2
            static final int STATE_DUP = 1;
            static final int STATE_ERR = 3;
            static final int STATE_INVALID_DEVICE = 5;
            static final int STATE_NO_SPACE = 2;
            static final int STATE_OK = 0;
            static final int STATE_ON_USE = 4;

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                Object obj2 = obj;
                if (!(obj2 instanceof GolfCourseMapInfo)) {
                    ReleaseLogUtil.c(GolfDataReceiverFactory.TAG, GolfDataReceiverFactory.GOLF_SHAKE_RSP_RCVER_TAG, " invalid info not GolfCourseMapInfo!");
                    return;
                }
                GolfCourseMapInfo golfCourseMapInfo = (GolfCourseMapInfo) obj2;
                if (!Utils.checkDevSendMsgRcverParams(GolfDataReceiverFactory.GOLF_SHAKE_RSP_RCVER_TAG, golfMsgHeader, bArr)) {
                    ReleaseLogUtil.c(GolfDataReceiverFactory.TAG, GolfDataReceiverFactory.GOLF_SHAKE_RSP_RCVER_TAG, "check Msg Params invalid!");
                    golfCourseMapInfo.deleteMapFile(GolfDataReceiverFactory.GOLF_SHAKE_RSP_RCVER_TAG);
                    GolfDownloadTaskUtils.getInstance().removeCourseId(golfCourseMapInfo.getCourseId());
                    return;
                }
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                int i3 = wrap.getInt();
                LogUtil.a(GolfDataReceiverFactory.TAG, GolfDataReceiverFactory.GOLF_SHAKE_RSP_RCVER_TAG, " handShake, rstValue is: ", Integer.valueOf(i3));
                if (i3 == 1) {
                    GolfDataReceiverFactory.dealHandShakeNoSend(golfCourseMapInfo, 0);
                    return;
                }
                if (i3 == 2 || i3 == 4 || i3 == 5) {
                    GolfDataReceiverFactory.dealHandShakeNoSend(golfCourseMapInfo, i3);
                } else if (i3 == 3) {
                    GolfDataReceiverFactory.dealHandShakeNoSend(golfCourseMapInfo, 1);
                } else {
                    GolfDeviceProxy.getInstance().sendMap(golfCourseMapInfo, i);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dealHandShakeNoSend(GolfCourseMapInfo golfCourseMapInfo, int i) {
        GolfH5DownloadInterface.InvokerBundle invoker = GolfDownloadTaskUtils.getInstance().getInvoker(golfCourseMapInfo.getCourseId());
        if (invoker != null) {
            invoker.onComplete(i, "", golfCourseMapInfo.getCourseId());
        } else {
            GolfDownloadTaskUtils.getInstance().removeBgUpdate(golfCourseMapInfo.getCourseId());
        }
        golfCourseMapInfo.deleteMapFile(GOLF_SHAKE_RSP_RCVER_TAG);
        GolfDownloadTaskUtils.getInstance().removeCourseId(golfCourseMapInfo.getCourseId());
    }

    private static GolfDataReceiver createCourseMapRspRcver(final int i, final Object obj) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.3
            static final String RECEIVER_TAG = "GolfCourseMapRspRcver";
            static final int UPDATE_ERR = 1;
            static final int UPDATE_MAX = 2;
            static final int UPDATE_SUCC = 0;

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (!(obj instanceof GolfCourseMapInfo)) {
                    ReleaseLogUtil.c(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " invalid info not GolfCourseMapInfo!");
                    return;
                }
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i3 = wrap.getInt();
                    Object[] objArr = new Object[5];
                    objArr[0] = RECEIVER_TAG;
                    objArr[1] = " device update map ";
                    objArr[2] = i3 == 0 ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
                    objArr[3] = " rstValue: ";
                    objArr[4] = Integer.valueOf(i3);
                    LogUtil.a(GolfDataReceiverFactory.TAG, objArr);
                    GolfCourseMapInfo golfCourseMapInfo = (GolfCourseMapInfo) obj;
                    GolfH5DownloadInterface.InvokerBundle invoker = GolfDownloadTaskUtils.getInstance().getInvoker(golfCourseMapInfo.getCourseId());
                    if (invoker == null) {
                        GolfDownloadTaskUtils.getInstance().removeBgUpdate(golfCourseMapInfo.getCourseId());
                        return;
                    }
                    boolean isBgUpdate = GolfDownloadTaskUtils.getInstance().isBgUpdate(golfCourseMapInfo.getCourseId());
                    if (i3 == 0) {
                        LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " invokerBundle.isBgUpdate() ", Boolean.valueOf(isBgUpdate), " courseIds = ", Arrays.toString(GolfDownloadTaskUtils.getInstance().getBgUpdateCourseIds()));
                        if (!isBgUpdate) {
                            invoker.onComplete(i3, "", golfCourseMapInfo.getCourseId());
                            return;
                        }
                        invoker.onSuccess(String.valueOf(100), golfCourseMapInfo.getCourseId(), 2);
                        GolfDownloadTaskUtils.getInstance().removeBgUpdate(golfCourseMapInfo.getCourseId());
                        if (GolfDownloadTaskUtils.getInstance().getBgUpdateCourseIds().length == 0) {
                            invoker.onComplete(i3, "", golfCourseMapInfo.getCourseId());
                            return;
                        }
                        return;
                    }
                    invoker.onFailure(1, "", golfCourseMapInfo.getCourseId());
                }
            }
        };
    }

    private static GolfDataReceiver createGPSReqRcver(final int i) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.4
            static final String RECEIVER_TAG = "GolfGPSReqRcver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    if (GolfDeviceProxy.getInstance().getWatchType() == 0) {
                        wrap.getInt();
                    }
                    GolfCourseListInfo golfCourseListByGPS = CloudHelper.getGolfCourseListByGPS(wrap.getDouble(), wrap.getDouble());
                    try {
                        GolfDeviceProxy.getInstance().sendCourseListFile(golfCourseListByGPS.getListInfos(), golfCourseListByGPS.getRspState(), i);
                    } catch (cbe unused) {
                        LogUtil.b(GolfDataReceiverFactory.TAG, "sendCourseListFile failed");
                    }
                }
            }
        };
    }

    private static GolfDataReceiver createCancelCourseMapRcver(final int i) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.5
            static final String RECEIVER_TAG = "cancelCourseMapRcver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i3 = wrap.getInt();
                    ReleaseLogUtil.e(GolfDataReceiverFactory.TAG, RECEIVER_TAG, "cancel map id: ", Integer.valueOf(i3));
                    GolfDownloadTaskUtils.getInstance().cancelDownloadTask(i3);
                    GolfDeviceProxy.getInstance().sendCancelCourseMap(i3);
                }
            }
        };
    }

    private static GolfDataReceiver createCourseListRspRcver(final int i) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.6
            static final String RECEIVER_TAG = "GolfCourseListRspRcver";
            static final int RSP_ERROR = 1;
            static final int RSP_MAX = 2;
            static final int RSP_SUCCESS = 0;

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " rstValue is [", Integer.valueOf(wrap.getInt()), "], msgId[", Integer.valueOf(i), "]");
                }
            }
        };
    }

    private static GolfDataReceiver createCourseVersionReqRcver(final int i) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.7
            static final String RECEIVER_TAG = "GolfCourseVersionReqRcver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (!Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    LogUtil.h(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " param is invalid");
                    return;
                }
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                int i3 = wrap.getInt();
                int i4 = wrap.getInt();
                LogUtil.a(RECEIVER_TAG, " courseId[", Integer.valueOf(i3), "], version[", Integer.valueOf(i4), ", msgId[", Integer.valueOf(i), "]");
                GolfDeviceProxy.getInstance().sendCourseVersionCheckRst(CloudHelper.checkCourseVersion(i3, i4, false), i);
            }
        };
    }

    private static GolfDataReceiver createCourseMapReqRcver(final int i) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.8
            static final String RECEIVER_TAG = "GolfCourseMapReqRcver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i3 = wrap.getInt();
                    int i4 = wrap.getInt();
                    int i5 = wrap.getInt();
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " courseId[", Integer.valueOf(i3), "], version[", Integer.valueOf(i4), "] , watchType[", Integer.valueOf(i5), "]");
                    GolfDownloadTaskUtils.getInstance().downloadHandler(i3, i5, i, null, false);
                }
            }
        };
    }

    private static GolfDataReceiver createDeviceDownloadedCourseListRcver(final int i, final GolfH5DownloadInterface.InvokerBundle invokerBundle) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.9
            static final String RECEIVER_TAG = "GolfDeviceDownloadedCourseListRcver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                List arrayList;
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " msgId: ", Integer.valueOf(i));
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i3 = wrap.getInt();
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " courseNum: ", Integer.valueOf(i3));
                    if (GolfDataReceiverFactory.LIST_MAP.containsKey(Integer.valueOf(i))) {
                        arrayList = (List) GolfDataReceiverFactory.LIST_MAP.get(Integer.valueOf(i));
                    } else {
                        arrayList = new ArrayList();
                    }
                    int length = (bArr.length - 4) / 8;
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " courseCount: ", Integer.valueOf(length));
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " list size: ", Integer.valueOf(arrayList.size()));
                    for (int i4 = 0; i4 < length; i4++) {
                        GolfCourseData golfCourseData = new GolfCourseData();
                        golfCourseData.setCourseId(wrap.getInt());
                        golfCourseData.setVersion(wrap.getInt());
                        arrayList.add(golfCourseData);
                    }
                    if (arrayList.size() != i3) {
                        GolfDataReceiverFactory.LIST_MAP.put(Integer.valueOf(i), arrayList);
                        LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " receive device course list updated");
                        return;
                    }
                    String e = HiJsonUtil.e(arrayList);
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, e);
                    GolfH5DownloadInterface.InvokerBundle invokerBundle2 = invokerBundle;
                    if (invokerBundle2 != null) {
                        invokerBundle2.getInvoker().onSuccess(e, invokerBundle.getCallbackId());
                    }
                    GolfDataReceiverFactory.LIST_MAP.remove(Integer.valueOf(i));
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " receive device course list finished");
                }
            }
        };
    }

    private static GolfDataReceiver createDeviceCourseListRcverForNative(final int i, final GolfDataCallback golfDataCallback) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.10
            static final String RECEIVER_TAG = "createDeviceCourseListRcverForNative";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                List<GolfCourseData> arrayList;
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " msgId: ", Integer.valueOf(i));
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i3 = wrap.getInt();
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " courseNum: ", Integer.valueOf(i3));
                    if (GolfDataReceiverFactory.LIST_MAP.containsKey(Integer.valueOf(i))) {
                        arrayList = (List) GolfDataReceiverFactory.LIST_MAP.get(Integer.valueOf(i));
                    } else {
                        arrayList = new ArrayList<>();
                    }
                    int length = (bArr.length - 4) / 8;
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " courseCount: ", Integer.valueOf(length));
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " list size: ", Integer.valueOf(arrayList.size()));
                    for (int i4 = 0; i4 < length; i4++) {
                        GolfCourseData golfCourseData = new GolfCourseData();
                        golfCourseData.setCourseId(wrap.getInt());
                        golfCourseData.setVersion(wrap.getInt());
                        arrayList.add(golfCourseData);
                    }
                    if (arrayList.size() != i3) {
                        GolfDataReceiverFactory.LIST_MAP.put(Integer.valueOf(i), arrayList);
                        LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " receive device course list updated");
                        return;
                    }
                    GolfDataCallback golfDataCallback2 = golfDataCallback;
                    if (golfDataCallback2 != null) {
                        golfDataCallback2.onReceived(arrayList);
                    }
                    GolfDataReceiverFactory.LIST_MAP.remove(Integer.valueOf(i));
                    LogUtil.a(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " receive device course list finished");
                }
            }
        };
    }

    private static GolfDataReceiver createCourseMapKeyRcver(final int i, final Object obj) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.11
            static final String RECEIVER_TAG = "courseMapKeyRcver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    if (wrap.getInt() == 0) {
                        Object obj2 = obj;
                        if (obj2 instanceof GolfCourseMapInfo) {
                            GolfDeviceProxy.getInstance().sendCourseMap((GolfCourseMapInfo) obj2, i);
                        }
                    }
                }
            }
        };
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory$12, reason: invalid class name */
    class AnonymousClass12 implements GolfDataReceiver {
        static final String RECEIVER_TAG = "createClubReqReceiver";
        final /* synthetic */ int val$msgId;

        AnonymousClass12(int i) {
            this.val$msgId = i;
        }

        @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
        public boolean isMatch(int i) {
            return this.val$msgId == i;
        }

        @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
        public void onDataReceived(GolfMsgHeader golfMsgHeader, int i, byte[] bArr) {
            if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory$12$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        GolfDeviceProxy.getInstance().sendGolfClubInfo(CloudHelper.getGolfClubListInfo(), null);
                    }
                });
            }
        }
    }

    private static GolfDataReceiver createClubReqReceiver(int i) {
        return new AnonymousClass12(i);
    }

    private static GolfDataReceiver createSendGolfClubRspReceiver(final int i, final GolfH5DownloadInterface.InvokerBundle invokerBundle) {
        return new GolfDataReceiver() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiverFactory.13
            static final String RECEIVER_TAG = "createSendGolfClubRspReceiver";

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public boolean isMatch(int i2) {
                return i == i2;
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataReceiver
            public void onDataReceived(GolfMsgHeader golfMsgHeader, int i2, byte[] bArr) {
                if (Utils.checkDevSendMsgRcverParams(RECEIVER_TAG, golfMsgHeader, bArr)) {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.order(ByteOrder.LITTLE_ENDIAN);
                    int i3 = wrap.getInt();
                    GolfH5DownloadInterface.InvokerBundle invokerBundle2 = invokerBundle;
                    if (invokerBundle2 != null) {
                        if (i3 == 0) {
                            invokerBundle2.getInvoker().onSuccess(Integer.valueOf(i3), invokerBundle.getCallbackId());
                        } else {
                            invokerBundle2.getInvoker().onFailure(1, "failure", invokerBundle.getCallbackId());
                        }
                    }
                    ReleaseLogUtil.e(GolfDataReceiverFactory.TAG, RECEIVER_TAG, " rstValue is [", Integer.valueOf(i3), "], msgId[", Integer.valueOf(i), "]");
                }
            }
        };
    }
}
