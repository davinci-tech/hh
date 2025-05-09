package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicHistoryCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationCallbackInterface;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jjj {

    /* renamed from: a, reason: collision with root package name */
    private static jfq f13891a;
    private static MusicHistoryCallbackInterface b;
    private static int c;
    private static IBaseResponseCallback d;
    private static IBaseResponseCallback e;
    private static NegotiationCallbackInterface g;
    private static List<jka> f = new ArrayList(16);
    private static boolean h = false;
    private static Handler j = new Handler(Looper.getMainLooper()) { // from class: jjj.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                LogUtil.a("MusicManagerUtils", "handleMessage timeout.");
                jjj.d(false);
            } else {
                LogUtil.h("MusicManagerUtils", "Invalid music message.");
            }
        }
    };

    static /* synthetic */ int a() {
        int i = c;
        c = i + 1;
        return i;
    }

    public static void c(jfq jfqVar) {
        if (jfqVar == null) {
            LogUtil.h("MusicManagerUtils", "setConfigAndLock hwDeviceConfigManager is null");
        } else {
            f13891a = jfqVar;
        }
    }

    public static void b(cwd cwdVar, List<String> list) {
        int i;
        ArrayList arrayList = new ArrayList(16);
        String c2 = cwdVar.c();
        int i2 = 2;
        int c3 = jds.c(c2.length() >= 2 ? c2.substring(0, 2) : null, 16);
        arrayList.add(Integer.valueOf(c3));
        while ((c3 & 128) != 0 && c2.length() >= (i = i2 + 2)) {
            c3 = jds.c(c2.substring(i2, i), 16);
            arrayList.add(Integer.valueOf(c3));
            i2 = i;
        }
        DeviceInfo d2 = jpt.d("MusicManagerUtils");
        if (d2 != null && blp.a(10000, d2.getDeviceModel())) {
            list.add("MP3");
            list.add("AAC");
        } else {
            c(arrayList, list);
        }
    }

    private static void c(List<Integer> list, List<String> list2) {
        if (list.size() >= 1) {
            b(list, list2, 0);
        }
        if (list.size() >= 2) {
            if ((list.get(1).intValue() & 1) != 0) {
                list2.add("PCM");
            }
            if ((list.get(1).intValue() & 2) != 0) {
                list2.add("APE");
            }
            if ((list.get(1).intValue() & 4) != 0) {
                list2.add("M4A");
            }
            if ((list.get(1).intValue() & 8) != 0) {
                list2.add("FLAC");
            }
            if ((list.get(1).intValue() & 16) != 0) {
                list2.add("OPUS");
            }
            if ((list.get(1).intValue() & 32) != 0) {
                list2.add("OGG");
            }
            if ((list.get(1).intValue() & 64) != 0) {
                list2.add("BUTT");
            }
        }
        if (list.size() >= 3) {
            if ((list.get(2).intValue() & 1) != 0) {
                list2.add("AMR");
            }
            if ((list.get(2).intValue() & 2) != 0) {
                list2.add("IMY");
            }
        }
    }

    private static void b(List<Integer> list, List<String> list2, int i) {
        if ((list.get(0).intValue() & 1) != i) {
            list2.add("MP3");
        }
        if ((list.get(0).intValue() & 2) != i) {
            list2.add("WAV");
        }
        if ((list.get(0).intValue() & 4) != i) {
            list2.add("AAC");
        }
        if ((list.get(0).intValue() & 8) != i) {
            list2.add("SBC");
        }
        if ((list.get(0).intValue() & 16) != i) {
            list2.add("MSBC");
        }
        if ((list.get(0).intValue() & 32) != i) {
            list2.add("HWA");
        }
        if ((list.get(0).intValue() & 64) != i) {
            list2.add("CVSD");
        }
    }

    private static void b(cwd cwdVar, NegotiationData negotiationData, NegotiationData.TypeStruct typeStruct) {
        LogUtil.a("MusicManagerUtils", "handleMusicSampleRateMessage enter");
        ArrayList arrayList = new ArrayList(16);
        String e2 = cvx.e(cwdVar.c());
        if (!TextUtils.isEmpty(e2)) {
            LogUtil.a("MusicManagerUtils", "getMusicType():", Integer.valueOf(negotiationData.getMusicType()), " ; sampleRateString:", e2);
            typeStruct.setSampleRate(e2);
            String[] split = e2.split(",");
            if (split.length > 0) {
                if (!"FFFF".equals(split[0])) {
                    for (String str : split) {
                        arrayList.add(Integer.valueOf(jds.c(str, 10)));
                    }
                }
            }
            if (negotiationData.getMusicType() == 0) {
                negotiationData.setMp3SampleRate(arrayList);
                return;
            }
            return;
        }
        LogUtil.h("MusicManagerUtils", "handleMusicSampleRateMessage sampleRateString is empty.");
    }

    private static void a(cwd cwdVar, NegotiationData.TypeStruct typeStruct) {
        LogUtil.a("MusicManagerUtils", "handleMusicBitrateRateMessage enter");
        int c2 = jds.c(cwdVar.c(), 16);
        if (c2 >= 0) {
            LogUtil.a("MusicManagerUtils", "handleMusicBitrateRateMessage, bitrate :", Integer.valueOf(c2));
            typeStruct.setMusicBitrate(c2);
        } else {
            LogUtil.h("MusicManagerUtils", "handleMusicBitrateRateMessage bitrate value illegal.");
        }
    }

    private static void e(cwd cwdVar, NegotiationData.TypeStruct typeStruct) {
        LogUtil.a("MusicManagerUtils", "handleMusicChannelCountMessage enter");
        int c2 = jds.c(cwdVar.c(), 16);
        if (c2 >= 0) {
            LogUtil.a("MusicManagerUtils", "handleMusicChannelCountMessage, channelCount:", Integer.valueOf(c2));
            typeStruct.setMusicChannelCount(c2);
        } else {
            LogUtil.h("MusicManagerUtils", "handleMusicChannelCountMessage channelCount value illegal.");
        }
    }

    private static void d(cwd cwdVar, NegotiationData.TypeStruct typeStruct) {
        LogUtil.a("MusicManagerUtils", "handleMusicEncryptedBitrateMessage enter");
        int c2 = jds.c(cwdVar.c(), 16);
        if (c2 >= 0) {
            LogUtil.a("MusicManagerUtils", "handleMusicEncryptedBitrateMessage, encryptedBitrate :", Integer.valueOf(c2));
            typeStruct.setMusicEncryptedBitrate(c2);
        } else {
            LogUtil.h("MusicManagerUtils", "handleMusicEncryptedBitrateMessage channelCount value illegal.");
        }
    }

    private static void a(cwd cwdVar, NegotiationData negotiationData, NegotiationData.TypeStruct typeStruct) {
        LogUtil.a("MusicManagerUtils", "handleMusicSampleRateMessage enter");
        int c2 = jds.c(cwdVar.c(), 16);
        typeStruct.setMusicEncode(c2);
        LogUtil.a("MusicManagerUtils", "musicEncode:", Integer.valueOf(c2));
        if (c2 == 0) {
            negotiationData.setAacSupportAdts(true);
        }
    }

    public static void a(MusicStruct musicStruct) {
        if (musicStruct == null) {
            LogUtil.h("MusicManagerUtils", "sendMusicFileResponse musicStruct is null");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(9);
        StringBuilder sb = new StringBuilder(16);
        k(musicStruct, sb);
        n(musicStruct, sb);
        l(musicStruct, sb);
        d(musicStruct, sb);
        o(musicStruct, sb);
        m(musicStruct, sb);
        j(musicStruct, sb);
        f(musicStruct, sb);
        b(musicStruct, sb);
        c(musicStruct, sb);
        e(musicStruct, sb);
        g(musicStruct, sb);
        h(musicStruct, sb);
        q(musicStruct, sb);
        i(musicStruct, sb);
        a(musicStruct, sb);
        String sb2 = sb.toString();
        LogUtil.a("MusicManagerUtils", "package sendMusicFileRequest Command :", sb2);
        byte[] a2 = cvx.a(sb2);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        if (f13891a == null) {
            ReleaseLogUtil.d("R_MusicManagerUtils", "sendMusicFileResponse deviceConfigManager is null");
            jjd.b(BaseApplication.getContext()).e();
        }
        f13891a.b(deviceCommand);
    }

    public static void b(jjz jjzVar) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(10);
        StringBuilder sb = new StringBuilder(16);
        c(jjzVar, sb);
        a(jjzVar, sb);
        b(jjzVar, sb);
        e(jjzVar, sb);
        d(jjzVar, sb);
        String sb2 = sb.toString();
        LogUtil.a("MusicManagerUtils", "package sendMusicAccountInfo Command :", sb2);
        byte[] a2 = cvx.a(sb2);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        if (f13891a == null) {
            LogUtil.h("MusicManagerUtils", "sendMusicAccountInfo deviceConfigManager is null");
            jjd.b(BaseApplication.getContext()).e();
        }
        f13891a.b(deviceCommand);
    }

    public static void a(NegotiationData negotiationData, NegotiationCallbackInterface negotiationCallbackInterface) {
        if (negotiationCallbackInterface == null) {
            LogUtil.h("MusicManagerUtils", "getNegotiation is null");
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(13);
        StringBuilder sb = new StringBuilder(16);
        String e2 = cvx.e(1);
        String d2 = cvx.d(0);
        String e3 = cvx.e(2);
        String d3 = cvx.d(0);
        String e4 = cvx.e(3);
        String d4 = cvx.d(0);
        String e5 = cvx.e(4);
        String d5 = cvx.d(0);
        String e6 = cvx.e(5);
        String d6 = cvx.d(0);
        sb.append(e2);
        sb.append(d2);
        sb.append(e3);
        sb.append(d3);
        sb.append(e4);
        sb.append(d4);
        sb.append(e5);
        sb.append(d5);
        sb.append(e6);
        sb.append(d6);
        String sb2 = sb.toString();
        LogUtil.a("MusicManagerUtils", "package sendMusicAccountInfo Command :", sb2);
        byte[] a2 = cvx.a(sb2);
        deviceCommand.setDataLen(a2.length);
        deviceCommand.setDataContent(a2);
        if (f13891a == null) {
            LogUtil.h("MusicManagerUtils", "getNegotiation deviceConfigManager is null");
            jjd.b(BaseApplication.getContext()).e();
        }
        f13891a.b(deviceCommand);
        g = negotiationCallbackInterface;
        LogUtil.c("MusicManagerUtils", "negotiationData:", negotiationData);
    }

    public static void a(MusicHistoryCallbackInterface musicHistoryCallbackInterface, int i, String str, Object obj) {
        if (musicHistoryCallbackInterface == null || obj == null) {
            return;
        }
        if (h) {
            LogUtil.a("MusicManagerUtils", "MusicHistory is transfering.");
            musicHistoryCallbackInterface.getHistoryRecordsBusy(i, str, 1);
        } else {
            LogUtil.a("MusicManagerUtils", "getMusicHistory");
            b = musicHistoryCallbackInterface;
            h();
            b(i, str, obj);
        }
    }

    public static void a(List<cwd> list, List<cwe> list2, int i) {
        switch (i) {
            case 11:
                j.removeMessages(1);
                if (list != null) {
                    b(list);
                    break;
                }
                break;
            case 12:
                j.removeMessages(1);
                if (list != null && list2 != null) {
                    b(list, list2);
                    break;
                }
                break;
            case 13:
                if (list != null) {
                    d(list, list2);
                    break;
                }
                break;
            default:
                LogUtil.h("MusicManagerUtils", "handleOnlineMusic invalid command:", Integer.valueOf(i));
                break;
        }
    }

    private static void h() {
        d(true);
        c = 0;
        f.clear();
    }

    private static void b(List<cwd> list, List<cwe> list2) {
        LogUtil.a("MusicManagerUtils", "MusicConstants.COMMAND_ID_HISTORY_INFO_RECORD_FRAME_INDEX");
        jkc jkcVar = new jkc();
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.e(), 16);
            if (c2 != 1 && c2 != 2) {
                if (c2 == 3) {
                    jkcVar.e(jds.c(cwdVar.c(), 16));
                } else {
                    LogUtil.h("MusicManagerUtils", "HandleMusicHistory type:", Integer.valueOf(jds.c(cwdVar.e(), 16)));
                }
            }
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                e(it2.next(), arrayList);
            }
        }
        jkcVar.b(arrayList);
        e.d(100000, jkcVar);
    }

    private static void e(cwe cweVar, List<jka> list) {
        if (cweVar == null || list == null) {
            return;
        }
        jka jkaVar = new jka();
        jkaVar.c("");
        for (cwd cwdVar : cweVar.e()) {
            switch (jds.c(cwdVar.e(), 16)) {
                case 6:
                    jkaVar.c(cvx.e(cwdVar.c()));
                    break;
                case 7:
                    jkaVar.d(jds.c(cwdVar.c(), 16));
                    break;
                case 8:
                    jkaVar.a(jds.c(cwdVar.c(), 16));
                    break;
                case 9:
                    jkaVar.b(jds.c(cwdVar.c(), 16));
                    break;
                default:
                    LogUtil.h("MusicManagerUtils", "MusicHistoryList type:", Integer.valueOf(jds.c(cwdVar.e(), 16)));
                    break;
            }
        }
        if ("".equals(jkaVar.b())) {
            return;
        }
        list.add(jkaVar);
        LogUtil.a("MusicManagerUtils", "musicStruct:", jkaVar.toString());
    }

    private static void b(final int i, final String str, final Object obj) {
        if (obj == null) {
            d(false);
        } else {
            e(new IBaseResponseCallback() { // from class: jjj.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj2) {
                    int i3 = 0;
                    if (obj2 == null) {
                        LogUtil.h("MusicManagerUtils", "getHistoryFrameCount objData is null");
                        jjj.d(false);
                        return;
                    }
                    try {
                        i3 = Integer.parseInt(obj2.toString());
                    } catch (NumberFormatException e2) {
                        LogUtil.b("MusicManagerUtils", "getHistoryFrameCount objData exception:", e2.getMessage());
                    }
                    LogUtil.a("MusicManagerUtils", "getHistoryFrameCount onResponse(int error, Object objData) historyFrameCount:", Integer.valueOf(i3));
                    jjj.a(i3, i, str, obj);
                }
            }, i, str, obj);
        }
    }

    private static void e(IBaseResponseCallback iBaseResponseCallback, int i, String str, Object obj) {
        LogUtil.a("MusicManagerUtils", "sendHistoryFrameCount");
        if (obj == null) {
            d(false);
            return;
        }
        synchronized (obj) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(11);
            StringBuilder sb = new StringBuilder(16);
            e(i, sb);
            c(str, sb);
            String sb2 = sb.toString();
            LogUtil.a("MusicManagerUtils", "sendHistoryFrameCount packageMusicCommand :", sb2);
            byte[] a2 = cvx.a(sb2);
            deviceCommand.setDataLen(a2.length);
            deviceCommand.setDataContent(a2);
            if (f13891a == null) {
                LogUtil.h("MusicManagerUtils", "sendHistoryFrameCount deviceConfigManager is null");
                jjd.b(BaseApplication.getContext()).e();
            }
            f13891a.b(deviceCommand);
            j.sendEmptyMessageDelayed(1, PreConnectManager.CONNECT_INTERNAL);
            d = iBaseResponseCallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final int i, final int i2, final String str, final Object obj) {
        LogUtil.a("MusicManagerUtils", "Enter getHistoryInfo(): musicHistoryFrameCount:", Integer.valueOf(i), " historyFrameCurrentIndex: ", Integer.valueOf(c));
        int i3 = c;
        if (i3 >= i) {
            LogUtil.a("MusicManagerUtils", "musicHistoryStructList.size:", Integer.valueOf(f.size()));
            b.onGetHistoryRecords(i2, str, f);
            c = 0;
            d(false);
            return;
        }
        e(i3, i2, str, obj, new IBaseResponseCallback() { // from class: jjj.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i4, Object obj2) {
                if (obj2 == null) {
                    LogUtil.h("MusicManagerUtils", "Enter getHistoryInfo() objData is null");
                    jjj.d(false);
                } else {
                    if (obj2 instanceof jkc) {
                        jjj.f.addAll(((jkc) obj2).b());
                        jjj.a();
                        jjj.a(i, i2, str, obj);
                        return;
                    }
                    LogUtil.h("MusicManagerUtils", "objData is not instanceof HistoryFrameStruct");
                }
            }
        });
    }

    private static void e(int i, int i2, String str, Object obj, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("MusicManagerUtils", "sendGetHistoryInfo musicFrameIndex: ", Integer.valueOf(i));
        synchronized (obj) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(37);
            deviceCommand.setCommandID(12);
            StringBuilder sb = new StringBuilder(16);
            a(i2, sb);
            d(str, sb);
            d(i, sb);
            String sb2 = sb.toString();
            LogUtil.a("MusicManagerUtils", "package packageMusicCommand :", sb2);
            byte[] a2 = cvx.a(sb2);
            deviceCommand.setDataLen(a2.length);
            deviceCommand.setDataContent(a2);
            if (f13891a == null) {
                LogUtil.h("MusicManagerUtils", "sendGetHistoryInfo deviceConfigManager is null");
                jjd.b(BaseApplication.getContext()).e();
            }
            f13891a.b(deviceCommand);
            j.sendEmptyMessageDelayed(1, PreConnectManager.CONNECT_INTERNAL);
            e = iBaseResponseCallback;
        }
    }

    private static void k(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        String a2 = cvx.a(musicStruct.getMusicIndex());
        String d2 = cvx.d(a2.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d2);
        sb.append(a2);
    }

    private static void n(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicName() == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicName is null");
            return;
        }
        String c2 = cvx.c(musicStruct.getMusicName());
        if (c2.length() > 240) {
            c2 = c2.substring(0, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
            LogUtil.a("MusicManagerUtils", "musicNameValue out of size");
        }
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(3));
        sb.append(d2);
        sb.append(c2);
    }

    private static void l(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        String c2 = cvx.c(musicStruct.getMusicSinger());
        if (c2.length() > 120) {
            c2 = c2.substring(0, 120);
            LogUtil.a("MusicManagerUtils", "musicSingerValue out of size");
        }
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(4));
        sb.append(d2);
        sb.append(c2);
    }

    private static void d(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicAlbum() == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicAlbum is null");
            return;
        }
        String c2 = cvx.c(musicStruct.getMusicAlbum());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(5));
        sb.append(d2);
        sb.append(c2);
    }

    private static void o(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicId() == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicId is null");
            return;
        }
        String c2 = cvx.c(musicStruct.getMusicId());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(6));
        sb.append(d2);
        sb.append(c2);
    }

    private static void m(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicType() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicIndex is null");
            return;
        }
        String e2 = cvx.e(musicStruct.getMusicType());
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(7));
        sb.append(d2);
        sb.append(e2);
    }

    private static void j(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicCopyright() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicCopyright is null");
            return;
        }
        String e2 = cvx.e(musicStruct.getMusicCopyright());
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(8));
        sb.append(d2);
        sb.append(e2);
    }

    private static void f(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicEncryption() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicEncryption is null");
            return;
        }
        String e2 = cvx.e(musicStruct.getMusicEncryption());
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(9));
        sb.append(d2);
        sb.append(e2);
    }

    private static void b(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicKey() == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicKey is null");
            return;
        }
        String musicKey = musicStruct.getMusicKey();
        String d2 = cvx.d(musicKey.length() / 2);
        sb.append(cvx.e(10));
        sb.append(d2);
        sb.append(musicKey);
    }

    private static void c(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicIv() == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicIv is null");
            return;
        }
        String musicIv = musicStruct.getMusicIv();
        String d2 = cvx.d(musicIv.length() / 2);
        sb.append(cvx.e(11));
        sb.append(d2);
        sb.append(musicIv);
    }

    private static void e(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getAccountName() == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getAccountName is null");
            return;
        }
        String c2 = cvx.c(musicStruct.getAccountName());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(12));
        sb.append(d2);
        sb.append(c2);
    }

    private static void g(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicAppType() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicAppType is null");
            return;
        }
        String e2 = cvx.e(musicStruct.getMusicAppType());
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(13));
        sb.append(d2);
        sb.append(e2);
    }

    private static void h(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            return;
        }
        if (musicStruct.getMusicDuration() == -1) {
            LogUtil.h("MusicManagerUtils", "assembleMusicDuration.getMusicDuration is null");
            return;
        }
        String b2 = cvx.b(musicStruct.getMusicDuration());
        String d2 = cvx.d(b2.length() / 2);
        sb.append(cvx.e(14));
        sb.append(d2);
        sb.append(b2);
    }

    private static void q(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            LogUtil.h("MusicManagerUtils", "assemblePackageName musicStruct or musicFileInfoListHex is null");
            return;
        }
        if (TextUtils.isEmpty(musicStruct.getPackageName())) {
            LogUtil.h("MusicManagerUtils", "assemblePackageName getPackageName is null");
            return;
        }
        String c2 = cvx.c(musicStruct.getPackageName());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(15));
        sb.append(d2);
        sb.append(c2);
    }

    private static void i(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            LogUtil.h("MusicManagerUtils", "assembleMusicFolderName musicStruct or musicFileInfoListHex is null");
            return;
        }
        if (TextUtils.isEmpty(musicStruct.getMusicFolder())) {
            LogUtil.h("MusicManagerUtils", "assembleMusicFolderName getMusicFolder is null");
            return;
        }
        String c2 = cvx.c(musicStruct.getMusicFolder());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(16));
        sb.append(d2);
        sb.append(c2);
    }

    private static void a(MusicStruct musicStruct, StringBuilder sb) {
        if (musicStruct == null || sb == null) {
            LogUtil.h("MusicManagerUtils", "assembleMusicAlbumId musicStruct or musicFileInfoListHex is null");
            return;
        }
        if (TextUtils.isEmpty(musicStruct.getMusicAlbumId())) {
            LogUtil.h("MusicManagerUtils", "assembleMusicFolderName getMusicAlbumId is null");
            return;
        }
        String c2 = cvx.c(musicStruct.getMusicAlbumId());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(17));
        sb.append(d2);
        sb.append(c2);
    }

    private static void c(jjz jjzVar, StringBuilder sb) {
        if (jjzVar == null || sb == null) {
            return;
        }
        if (jjzVar.c() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicDuration is null");
            return;
        }
        String e2 = cvx.e(jjzVar.c());
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d2);
        sb.append(e2);
    }

    private static void a(jjz jjzVar, StringBuilder sb) {
        if (jjzVar == null || sb == null) {
            return;
        }
        if (jjzVar.e() == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getAccountInfo is null");
            return;
        }
        String c2 = cvx.c(jjzVar.e());
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(2));
        sb.append(d2);
        sb.append(c2);
    }

    private static void b(jjz jjzVar, StringBuilder sb) {
        if (jjzVar == null || sb == null) {
            return;
        }
        if (jjzVar.d() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getAccountDeadline is null");
            return;
        }
        String b2 = cvx.b(jjzVar.d());
        String d2 = cvx.d(b2.length() / 2);
        sb.append(cvx.e(3));
        sb.append(d2);
        sb.append(b2);
    }

    private static void e(jjz jjzVar, StringBuilder sb) {
        if (jjzVar == null || sb == null) {
            return;
        }
        if (jjzVar.a() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getLastLoginTime is null");
            return;
        }
        String b2 = cvx.b(jjzVar.a());
        String d2 = cvx.d(b2.length() / 2);
        sb.append(cvx.e(4));
        sb.append(d2);
        sb.append(b2);
    }

    private static void d(jjz jjzVar, StringBuilder sb) {
        if (jjzVar == null || sb == null) {
            return;
        }
        if (jjzVar.b() == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getAccountNotifyType is null");
            return;
        }
        String e2 = cvx.e(jjzVar.b());
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(5));
        sb.append(d2);
        sb.append(e2);
    }

    private static void e(int i, StringBuilder sb) {
        if (sb == null) {
            return;
        }
        if (i == -1) {
            LogUtil.h("MusicManagerUtils", "assembleHistoryFrameAppType is null");
            return;
        }
        String e2 = cvx.e(i);
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d2);
        sb.append(e2);
    }

    private static void c(String str, StringBuilder sb) {
        if (sb == null) {
            return;
        }
        if (str == null) {
            LogUtil.h("MusicManagerUtils", "assembleHistoryFrameAccountInfo is null");
            return;
        }
        String c2 = cvx.c(str);
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(2));
        sb.append(d2);
        sb.append(c2);
    }

    private static void b(List<cwd> list) {
        int i = 0;
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.e(), 16);
            if (c2 != 1 && c2 != 2) {
                if (c2 == 3) {
                    i = jds.c(cwdVar.c(), 16);
                } else {
                    LogUtil.h("MusicManagerUtils", "handleHistoryFrameCountMessage invalid type:", Integer.valueOf(jds.c(cwdVar.e(), 16)));
                }
            }
        }
        LogUtil.a("MusicManagerUtils", "frameCount:", Integer.valueOf(i));
        d.d(100000, Integer.valueOf(i));
    }

    private static void d(List<cwd> list, List<cwe> list2) {
        if (list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        int i = -1;
        int i2 = -1;
        int i3 = 0;
        int i4 = -1;
        for (cwd cwdVar : list) {
            int c2 = jds.c(cwdVar.e(), 16);
            if (c2 == 1) {
                i3 = jds.c(cwdVar.c(), 16);
            } else if (c2 == 2) {
                b(cwdVar, arrayList);
            } else if (c2 == 3) {
                i = jds.c(cwdVar.c(), 16);
            } else if (c2 == 4) {
                i4 = jds.c(cwdVar.c(), 16);
            } else if (c2 == 5) {
                i2 = jds.c(cwdVar.c(), 16);
            } else {
                LogUtil.h("MusicManagerUtils", "HandleNegotiationMessage invalid type:", Integer.valueOf(jds.c(cwdVar.e(), 16)));
            }
        }
        NegotiationData negotiationData = new NegotiationData();
        a(list2, negotiationData);
        negotiationData.setMusicFormatList(arrayList);
        negotiationData.setStorageSpace(i3);
        LogUtil.a("MusicManagerUtils", "maxMusicNumber:", Integer.valueOf(i));
        negotiationData.setMaxMusicNumber(i);
        LogUtil.a("MusicManagerUtils", "handleNegotiationMessage:", arrayList.toString(), "storageSpace: ", Integer.valueOf(i3));
        negotiationData.setMaxFolderNumber(i4);
        negotiationData.setMusicQuality(i2);
        LogUtil.a("MusicManagerUtils", "maxFolderNumber:", Integer.valueOf(i4), "musicQuality:", Integer.valueOf(i2));
        NegotiationCallbackInterface negotiationCallbackInterface = g;
        if (negotiationCallbackInterface != null) {
            negotiationCallbackInterface.onGetNegotiationData(negotiationData, 100000);
        } else {
            LogUtil.h("MusicManagerUtils", "negotiationCallbackInterface is null");
        }
    }

    private static void a(List<cwe> list, NegotiationData negotiationData) {
        ArrayList arrayList = new ArrayList(16);
        if (list != null) {
            Iterator<cwe> it = list.iterator();
            while (it.hasNext()) {
                Iterator<cwe> it2 = it.next().a().iterator();
                while (it2.hasNext()) {
                    b(it2.next(), negotiationData, arrayList);
                }
            }
        } else {
            LogUtil.h("MusicManagerUtils", "tlvFatherList is null");
        }
        negotiationData.setTypeStructList(arrayList);
    }

    private static void b(cwe cweVar, NegotiationData negotiationData, List<NegotiationData.TypeStruct> list) {
        if (cweVar == null || negotiationData == null) {
            LogUtil.h("MusicManagerUtils", "handleMusicTypeTlvFather, tlvFather or negotiationData is null.");
            return;
        }
        NegotiationData.TypeStruct typeStruct = new NegotiationData.TypeStruct();
        for (cwd cwdVar : cweVar.e()) {
            switch (jds.c(cwdVar.e(), 16)) {
                case 8:
                    negotiationData.setMusicType(jds.c(cwdVar.c(), 16));
                    typeStruct.setMusicType(jds.c(cwdVar.c(), 16));
                    LogUtil.a("MusicManagerUtils", "negotiationData getMusicType", Integer.valueOf(negotiationData.getMusicType()));
                    break;
                case 9:
                    b(cwdVar, negotiationData, typeStruct);
                    break;
                case 10:
                    a(cwdVar, negotiationData, typeStruct);
                    break;
                case 11:
                    a(cwdVar, typeStruct);
                    break;
                case 12:
                    e(cwdVar, typeStruct);
                    break;
                case 13:
                    d(cwdVar, typeStruct);
                    break;
                default:
                    LogUtil.h("MusicManagerUtils", "handleMusicTypeTlvFather type:", Integer.valueOf(jds.c(cwdVar.e(), 16)));
                    break;
            }
        }
        list.add(typeStruct);
    }

    private static void a(int i, StringBuilder sb) {
        if (sb == null) {
            return;
        }
        if (i == -1) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getMusicDuration is null");
            return;
        }
        String e2 = cvx.e(i);
        String d2 = cvx.d(e2.length() / 2);
        sb.append(cvx.e(1));
        sb.append(d2);
        sb.append(e2);
    }

    private static void d(String str, StringBuilder sb) {
        if (sb == null) {
            return;
        }
        if (str == null) {
            LogUtil.h("MusicManagerUtils", "musicStruct.getAccountInfo is null");
            return;
        }
        String c2 = cvx.c(str);
        String d2 = cvx.d(c2.length() / 2);
        sb.append(cvx.e(2));
        sb.append(d2);
        sb.append(c2);
    }

    private static void d(int i, StringBuilder sb) {
        if (sb == null) {
            return;
        }
        if (i == -1) {
            LogUtil.h("MusicManagerUtils", "assembleHistoryFrameIndex.getMusicDuration is null");
            return;
        }
        String a2 = cvx.a(i);
        String d2 = cvx.d(a2.length() / 2);
        sb.append(cvx.e(3));
        sb.append(d2);
        sb.append(a2);
    }

    public static boolean j() {
        DeviceCapability d2 = cvs.d();
        if (d2 == null) {
            return false;
        }
        LogUtil.a("MusicManagerUtils", "isSupportSendSwitchStatus deviceCapability is not null");
        return d2.isSupportSendSwitchStatus();
    }

    public static void d(boolean z) {
        h = z;
    }

    public static boolean b() {
        DeviceInfo d2 = jpt.d("MusicManagerUtils");
        if (d2 == null) {
            LogUtil.h("MusicManagerUtils", "isDeviceDismissNewFolder deviceInfo is null");
            return false;
        }
        return cwi.c(d2, 64);
    }

    public static boolean e() {
        DeviceInfo d2 = jpt.d("MusicManagerUtils");
        if (d2 == null) {
            LogUtil.h("MusicManagerUtils", "isDeviceDismissOnlineMusic deviceInfo is null");
            return false;
        }
        return cwi.c(d2, 65);
    }

    public static boolean g() {
        DeviceInfo d2 = jpt.d("MusicManagerUtils");
        if (d2 == null) {
            LogUtil.h("MusicManagerUtils", "isSupportAuthorizationStatus deviceInfo is null");
            return false;
        }
        return cwi.c(d2, 66);
    }

    public static boolean e(DeviceInfo deviceInfo) {
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "MusicManagerUtils");
        if (a2 != null && !a2.isEmpty()) {
            DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
            return deviceCapability != null && deviceCapability.isSupportMusicInfoList();
        }
        LogUtil.h("MusicManagerUtils", "isDeviceSupportCurFunction: queryDeviceCapability is null. ");
        return false;
    }

    public static boolean d() {
        DeviceInfo a2 = jpt.a("MusicManagerUtils");
        return a2 != null && cvz.c(a2) && a2.getProductType() == 74;
    }
}
