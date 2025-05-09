package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.StringVector;
import com.huawei.openalliance.ad.beans.metadata.Ad30;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.beans.metadata.AdTypeEvent;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.AudioInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.ContentExt;
import com.huawei.openalliance.ad.beans.metadata.DefaultTemplate;
import com.huawei.openalliance.ad.beans.metadata.FlowControl;
import com.huawei.openalliance.ad.beans.metadata.ImageInfo;
import com.huawei.openalliance.ad.beans.metadata.ImpEX;
import com.huawei.openalliance.ad.beans.metadata.InstallConfig;
import com.huawei.openalliance.ad.beans.metadata.InteractCfg;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Monitor;
import com.huawei.openalliance.ad.beans.metadata.NegativeFb;
import com.huawei.openalliance.ad.beans.metadata.Om;
import com.huawei.openalliance.ad.beans.metadata.ParamFromServer;
import com.huawei.openalliance.ad.beans.metadata.Permission;
import com.huawei.openalliance.ad.beans.metadata.Precontent;
import com.huawei.openalliance.ad.beans.metadata.PromoteInfo;
import com.huawei.openalliance.ad.beans.metadata.ShareInfo;
import com.huawei.openalliance.ad.beans.metadata.Skip;
import com.huawei.openalliance.ad.beans.metadata.TextState;
import com.huawei.openalliance.ad.beans.metadata.VideoInfo;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateV3;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Data;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Image;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.ImageExt;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Title;
import com.huawei.openalliance.ad.beans.metadata.v3.openrtb.Video;
import com.huawei.openalliance.ad.constant.LabelPosition;
import com.huawei.openalliance.ad.inter.data.DeviceAiParam;
import com.huawei.openalliance.ad.inter.data.RewardItem;
import com.huawei.openalliance.ad.vv;
import com.huawei.openalliance.ad.vx;
import com.huawei.openalliance.ad.vy;
import com.huawei.openalliance.ad.vz;
import com.huawei.openalliance.ad.wb;
import com.huawei.openalliance.ad.we;
import com.huawei.openalliance.ad.wi;
import com.huawei.openalliance.ad.wm;
import com.huawei.openalliance.ad.wn;
import com.huawei.openalliance.ad.wq;
import com.huawei.openalliance.ad.wr;
import com.huawei.openalliance.ad.wt;
import com.huawei.openalliance.ad.wu;
import com.huawei.openalliance.ad.wv;
import com.huawei.openalliance.ad.wx;
import com.huawei.openalliance.ad.wy;
import com.huawei.openalliance.ad.xd;
import com.huawei.openalliance.ad.xe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class dx {
    public static int a(int i, int i2) {
        return i == -1111 ? i2 : i;
    }

    private static long a(long j, long j2) {
        return j == -1111 ? j2 : j;
    }

    public static List<ImpEX> b(wn.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(b(aVar.a(i)));
        }
        return arrayList;
    }

    public static ImpEX b(wn wnVar) {
        if (wnVar == null) {
            return null;
        }
        ImpEX impEX = new ImpEX();
        impEX.a(wnVar.a());
        impEX.b(wnVar.b());
        return impEX;
    }

    public static int b(int i) {
        return a(i, 0);
    }

    public static float b(float f) {
        if (com.huawei.openalliance.ad.utils.bm.a(f, -1111.0f)) {
            return 0.0f;
        }
        return f;
    }

    private static boolean a(BaseVector baseVector) {
        return baseVector == null || baseVector.length() == 0;
    }

    public static Map<String, String> a(vy.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < length; i++) {
            vy a2 = aVar.a(i);
            hashMap.put(a2.a(), a2.b());
        }
        return hashMap;
    }

    private static List<TextState> a(xe.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<TemplateV3> a(xd.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<Precontent> a(wy.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    private static List<Permission> a(wx.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int length = aVar.length();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<NegativeFb> a(wv.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<MotionData> a(wu.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<Monitor> a(wt.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<MediaFile> a(wr.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<Om> a(wq.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<ContentExt> a(wn.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<ImageInfo> a(wm.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<ImpEX> a(wi.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<Content> a(we.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<Asset> a(wb.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<AdTypeEvent> a(vz.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    private static List<AdSource> a(vx.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<Ad30> a(vv.a aVar) {
        if (a((BaseVector) aVar)) {
            return null;
        }
        int length = aVar.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(a(aVar.a(i)));
        }
        return arrayList;
    }

    public static List<String> a(StringVector stringVector) {
        if (a((BaseVector) stringVector)) {
            return null;
        }
        int length = stringVector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(stringVector.get(i));
        }
        return arrayList;
    }

    public static List<Integer> a(IntVector intVector) {
        if (a((BaseVector) intVector)) {
            return null;
        }
        int length = intVector.length();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(Integer.valueOf(b(intVector.get(i))));
        }
        return arrayList;
    }

    public static Integer a(int i) {
        if (i == -1111) {
            return null;
        }
        return Integer.valueOf(i);
    }

    public static Float a(float f) {
        if (com.huawei.openalliance.ad.utils.bm.a(f, -1111.0f)) {
            return null;
        }
        return Float.valueOf(f);
    }

    public static RewardItem a(xa xaVar) {
        if (xaVar == null) {
            return null;
        }
        RewardItem rewardItem = new RewardItem();
        rewardItem.a(xaVar.a());
        rewardItem.a(b(xaVar.b()));
        return rewardItem;
    }

    public static DeviceAiParam a(wh whVar) {
        if (whVar == null) {
            return null;
        }
        DeviceAiParam deviceAiParam = new DeviceAiParam();
        deviceAiParam.a(whVar.b());
        deviceAiParam.b(whVar.c());
        deviceAiParam.c(whVar.d());
        deviceAiParam.d(whVar.a());
        deviceAiParam.e(whVar.e());
        deviceAiParam.f(whVar.f());
        return deviceAiParam;
    }

    public static Video a(xg xgVar) {
        if (xgVar == null) {
            return null;
        }
        Video video = new Video();
        video.a(xgVar.a());
        video.a(b(xgVar.d()));
        video.b(b(xgVar.e()));
        video.a(a(xgVar.g()));
        video.b(b(xgVar.c()));
        video.b(xgVar.b());
        video.c(xgVar.f());
        return video;
    }

    public static Title a(xf xfVar) {
        if (xfVar == null) {
            return null;
        }
        Title title = new Title();
        title.a(xfVar.a());
        title.a(b(xfVar.b()));
        return title;
    }

    public static ImageExt a(wk wkVar) {
        if (wkVar == null) {
            return null;
        }
        ImageExt imageExt = new ImageExt();
        imageExt.a(a(wkVar.b()));
        imageExt.a(wkVar.a());
        imageExt.b(wkVar.c());
        imageExt.a(b(wkVar.d()));
        return imageExt;
    }

    private static Image a(wl wlVar) {
        if (wlVar == null) {
            return null;
        }
        Image image = new Image();
        image.a(wlVar.a());
        image.a(b(wlVar.b()));
        image.b(b(wlVar.c()));
        image.a(a(wlVar.d()));
        return image;
    }

    public static Data a(wf wfVar) {
        if (wfVar == null) {
            return null;
        }
        Data data = new Data();
        data.a(b(wfVar.a()));
        data.a(wfVar.c());
        data.b(b(wfVar.b()));
        return data;
    }

    public static TemplateV3 a(xd xdVar) {
        if (xdVar == null) {
            return null;
        }
        TemplateV3 templateV3 = new TemplateV3();
        templateV3.a(xdVar.a());
        templateV3.a(a(xdVar.c()));
        templateV3.b(xdVar.b());
        templateV3.c(xdVar.d());
        templateV3.d(xdVar.e());
        return templateV3;
    }

    public static TemplateData a(xc xcVar) {
        if (xcVar == null) {
            return null;
        }
        TemplateData templateData = new TemplateData();
        templateData.a(xcVar.a());
        templateData.b(xcVar.b());
        templateData.a(a(xcVar.c()));
        templateData.c(xcVar.d());
        return templateData;
    }

    public static MotionData a(wu wuVar) {
        if (wuVar == null) {
            return null;
        }
        MotionData motionData = new MotionData();
        motionData.a(a(wuVar.a()));
        motionData.b(wuVar.b());
        motionData.a(b(wuVar.c()));
        motionData.b(b(wuVar.d()));
        motionData.a(wuVar.e());
        motionData.c(b(wuVar.f()));
        motionData.c(wuVar.g());
        motionData.d(b(wuVar.h()));
        return motionData;
    }

    public static Asset a(wb wbVar) {
        if (wbVar == null) {
            return null;
        }
        Asset asset = new Asset();
        asset.a(b((int) wbVar.a()));
        asset.a(wbVar.b());
        asset.c(wbVar.g());
        asset.a(a(wbVar.f()));
        asset.a(a(wbVar.d()));
        asset.a(a(wbVar.e()));
        asset.a(a(wbVar.c()));
        return asset;
    }

    public static VideoInfo a(xh xhVar) {
        if (xhVar == null) {
            return null;
        }
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.a(xhVar.a());
        videoInfo.a(b((int) xhVar.b()));
        videoInfo.b(b((int) xhVar.c()));
        videoInfo.b(xhVar.d() == null ? "y" : xhVar.d());
        videoInfo.c(xhVar.e() == null ? "n" : xhVar.e());
        videoInfo.c(a(xhVar.f(), 200));
        videoInfo.d(xhVar.g());
        videoInfo.e(b(xhVar.i()));
        videoInfo.d(a(xhVar.h(), 1));
        videoInfo.a(a(xhVar.j()));
        videoInfo.b(a(xhVar.k()));
        videoInfo.f(a(xhVar.l(), 0));
        videoInfo.a(a(xhVar.m()));
        videoInfo.e(xhVar.n() != null ? xhVar.n() : "y");
        videoInfo.a(b(xhVar.o()));
        return videoInfo;
    }

    public static TextState a(xe xeVar) {
        if (xeVar == null) {
            return null;
        }
        TextState textState = new TextState();
        textState.a(b(xeVar.a()));
        textState.b(b(xeVar.b()));
        textState.a(xeVar.c());
        textState.b(xeVar.d());
        textState.c(b(xeVar.e()));
        return textState;
    }

    public static Skip a(wd wdVar) {
        if (wdVar == null) {
            return null;
        }
        Skip skip = new Skip();
        skip.a(wdVar.a());
        skip.a(b(wdVar.b()));
        skip.b(b(wdVar.c()));
        return skip;
    }

    private static ShareInfo a(xb xbVar) {
        if (xbVar == null) {
            return null;
        }
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.a(xbVar.a());
        shareInfo.b(xbVar.b());
        shareInfo.c(xbVar.c());
        shareInfo.d(xbVar.d());
        return shareInfo;
    }

    private static PromoteInfo a(wz wzVar) {
        if (wzVar == null) {
            return null;
        }
        PromoteInfo promoteInfo = new PromoteInfo();
        promoteInfo.a(b(wzVar.a()));
        promoteInfo.a(wzVar.b());
        return promoteInfo;
    }

    public static Precontent a(wy wyVar) {
        if (wyVar == null) {
            return null;
        }
        Precontent precontent = new Precontent();
        precontent.a(wyVar.a());
        precontent.b(wyVar.b());
        precontent.a(b(wyVar.c()));
        precontent.a(a(wyVar.d()));
        precontent.a(a(wyVar.g()));
        precontent.c(wyVar.e());
        precontent.b(a(wyVar.f()));
        precontent.a(a(wyVar.h()));
        precontent.b(a(wyVar.m()));
        precontent.c(a(wyVar.j()));
        precontent.a(a(wyVar.i()));
        precontent.d(a(wyVar.l()));
        precontent.c(a(wyVar.k()));
        return precontent;
    }

    private static Permission a(wx wxVar) {
        if (wxVar == null) {
            return null;
        }
        Permission permission = new Permission();
        permission.a(wxVar.a());
        permission.b(wxVar.b());
        return permission;
    }

    private static ParamFromServer a(ww wwVar) {
        if (wwVar == null) {
            return null;
        }
        ParamFromServer paramFromServer = new ParamFromServer();
        paramFromServer.b(wwVar.a());
        paramFromServer.a(wwVar.c());
        paramFromServer.c(wwVar.b());
        return paramFromServer;
    }

    public static Om a(wq wqVar) {
        if (wqVar == null) {
            return null;
        }
        Om om = new Om();
        om.a(wqVar.a());
        om.b(wqVar.b());
        om.c(wqVar.c());
        return om;
    }

    public static NegativeFb a(wv wvVar) {
        if (wvVar == null) {
            return null;
        }
        NegativeFb negativeFb = new NegativeFb();
        negativeFb.a(a(wvVar.a()));
        negativeFb.a(wvVar.b());
        negativeFb.a(b(wvVar.c()));
        return negativeFb;
    }

    public static Monitor a(wt wtVar) {
        if (wtVar == null) {
            return null;
        }
        Monitor monitor = new Monitor();
        monitor.a(wtVar.a());
        monitor.a(a(wtVar.b()));
        monitor.a(a(wtVar.c(), 1));
        return monitor;
    }

    private static MetaData a(ws wsVar) {
        if (wsVar == null) {
            return null;
        }
        MetaData metaData = new MetaData();
        metaData.b(wsVar.c());
        metaData.b(wsVar.d());
        metaData.c(wsVar.e());
        metaData.g(a(wsVar.f()));
        metaData.a(a(wsVar.g()));
        metaData.b(a(wsVar.h()));
        metaData.a(wsVar.i());
        metaData.d(wsVar.j());
        metaData.a(a(wsVar.k(), 500L));
        metaData.a(wsVar.l());
        metaData.a(a(wsVar.F()));
        metaData.b(a(wsVar.G()));
        metaData.a(a(wsVar.m()));
        metaData.e(wsVar.n());
        metaData.f(wsVar.o());
        metaData.g(wsVar.p());
        metaData.h(wsVar.q());
        metaData.a(a(wsVar.r()));
        metaData.a(a(wsVar.s()));
        metaData.a(a(wsVar.t()));
        metaData.a(a(wsVar.u()));
        metaData.a(a(wsVar.L()));
        metaData.i(wsVar.v());
        metaData.b(a(wsVar.w()));
        metaData.a(a(wsVar.x()));
        metaData.c(b(wsVar.E()));
        metaData.o(wsVar.I());
        metaData.p(wsVar.H());
        metaData.q(wsVar.a());
        metaData.f(a(wsVar.J()));
        metaData.c(a(wsVar.b()));
        metaData.j(wsVar.y());
        metaData.k(wsVar.z());
        metaData.l(wsVar.A());
        metaData.m(wsVar.B());
        metaData.e(a(wsVar.C()));
        metaData.c(a(wsVar.D()));
        metaData.d(a(wsVar.K()));
        return metaData;
    }

    public static MediaFile a(wr wrVar) {
        if (wrVar == null) {
            return null;
        }
        MediaFile mediaFile = new MediaFile();
        mediaFile.a(wrVar.a() == null ? "video/mp4" : wrVar.a());
        mediaFile.a(b(wrVar.b()));
        mediaFile.b(b(wrVar.c()));
        mediaFile.a(a(wrVar.d()));
        mediaFile.b(wrVar.e());
        mediaFile.c(wrVar.f());
        mediaFile.c(a(wrVar.g(), 0));
        mediaFile.d(a(wrVar.h(), 0));
        mediaFile.e(a(wrVar.i(), 1));
        return mediaFile;
    }

    public static InteractCfg a(wp wpVar) {
        if (wpVar == null) {
            return null;
        }
        InteractCfg interactCfg = new InteractCfg();
        interactCfg.a(a(wpVar.a()));
        interactCfg.c(a(wpVar.b()));
        interactCfg.d(a(wpVar.c()));
        interactCfg.e(a(wpVar.d()));
        interactCfg.b(a(wpVar.e()));
        interactCfg.f(a(wpVar.f()));
        interactCfg.a(wpVar.g());
        interactCfg.b(wpVar.h());
        interactCfg.c(wpVar.i());
        interactCfg.d(wpVar.j());
        interactCfg.e(wpVar.k());
        return interactCfg;
    }

    private static InstallConfig a(wo woVar) {
        if (woVar == null) {
            return null;
        }
        InstallConfig installConfig = new InstallConfig();
        installConfig.b(woVar.a());
        installConfig.a(woVar.b());
        installConfig.c(woVar.c());
        return installConfig;
    }

    public static ImpEX a(wi wiVar) {
        if (wiVar == null) {
            return null;
        }
        ImpEX impEX = new ImpEX();
        impEX.a(wiVar.a());
        impEX.b(wiVar.b());
        return impEX;
    }

    public static ImageInfo a(wm wmVar) {
        if (wmVar == null) {
            return null;
        }
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.c(wmVar.a());
        imageInfo.a(b(wmVar.b()));
        imageInfo.b(b(wmVar.c()));
        imageInfo.a(wmVar.d());
        imageInfo.b(wmVar.f() == null ? "img" : wmVar.f());
        imageInfo.c(b((int) wmVar.e()));
        imageInfo.d(b(wmVar.g()));
        return imageInfo;
    }

    public static FlowControl a(wj wjVar) {
        if (wjVar == null) {
            return null;
        }
        FlowControl flowControl = new FlowControl();
        flowControl.a(b(wjVar.a()));
        flowControl.a(a(wjVar.b()));
        return flowControl;
    }

    public static DefaultTemplate a(wg wgVar) {
        if (wgVar == null) {
            return null;
        }
        DefaultTemplate defaultTemplate = new DefaultTemplate();
        defaultTemplate.a(wgVar.a());
        defaultTemplate.a(a(wgVar.b()));
        return defaultTemplate;
    }

    public static ContentExt a(wn wnVar) {
        if (wnVar == null) {
            return null;
        }
        ContentExt contentExt = new ContentExt();
        contentExt.a(wnVar.a());
        contentExt.b(wnVar.b());
        return contentExt;
    }

    public static Content a(we weVar) {
        if (weVar == null) {
            return null;
        }
        Content content = new Content();
        content.c(weVar.a());
        content.d(weVar.i());
        content.b(a(weVar.h(), 1));
        content.a(a(weVar.b()));
        content.b(a(weVar.j(), -1L));
        content.c(a(weVar.c(), 0));
        content.a(a(weVar.d(), 1));
        content.e(com.huawei.openalliance.ad.utils.be.b(a(weVar.e())));
        content.a(weVar.g());
        content.g(weVar.o() == null ? "tr" : weVar.o());
        content.a(a(weVar.p()));
        content.i(0);
        content.g(b(weVar.H()));
        content.b(com.huawei.openalliance.ad.utils.be.b(a(weVar.f())));
        content.u(com.huawei.openalliance.ad.utils.be.b(a(weVar.Y())));
        content.b(a(weVar.k()));
        content.c(a(weVar.l()));
        content.d(a(weVar.m()));
        content.f(weVar.q() == null ? "" : weVar.q());
        content.h(weVar.r() == null ? LabelPosition.LOWER_LEFT : weVar.r());
        content.d(b(weVar.s()));
        content.e(a(weVar.t()));
        content.i(weVar.u());
        content.j(weVar.v());
        content.e(b(weVar.x()));
        content.f(a(weVar.w()));
        content.k(weVar.y());
        content.l(weVar.z());
        content.a(a(weVar.C()));
        content.m(weVar.B());
        content.n(weVar.D());
        content.b(a(weVar.J()));
        content.c(a(weVar.K()));
        content.o(weVar.M());
        content.h(b(weVar.E()));
        content.p(weVar.A());
        content.g(a(weVar.N()));
        content.h(a(weVar.I()));
        content.a(a(weVar.n()));
        content.q(weVar.L());
        content.a(a(weVar.O()));
        content.i(a(weVar.P()));
        content.d(a(weVar.S()));
        content.j(a(weVar.R()));
        content.a(a(weVar.Q()));
        content.r(weVar.T());
        content.s(weVar.U());
        content.t(weVar.W());
        content.a(a(weVar.V()));
        content.a(a(weVar.X()));
        content.v(weVar.Z());
        content.w(weVar.aa());
        content.x(weVar.ab());
        content.y(weVar.G());
        content.a(a(weVar.F()));
        content.z(weVar.ac());
        content.A(weVar.ad());
        content.a(Integer.valueOf(weVar.ae()));
        return content;
    }

    private static AudioInfo a(wc wcVar) {
        if (wcVar == null) {
            return null;
        }
        AudioInfo audioInfo = new AudioInfo();
        audioInfo.a(wcVar.b());
        audioInfo.a(b((int) wcVar.c()));
        audioInfo.b(b((int) wcVar.a()));
        audioInfo.b(wcVar.d());
        audioInfo.c(wcVar.e());
        return audioInfo;
    }

    private static ApkInfo a(wa waVar) {
        if (waVar == null) {
            return null;
        }
        ApkInfo apkInfo = new ApkInfo();
        apkInfo.i(waVar.g());
        apkInfo.p(waVar.t());
        apkInfo.d(waVar.a());
        apkInfo.f(waVar.f());
        apkInfo.g(waVar.Q());
        apkInfo.h(waVar.R());
        apkInfo.b(waVar.b());
        apkInfo.c(waVar.o());
        apkInfo.a(a(waVar.c()));
        apkInfo.e(waVar.d());
        apkInfo.a(waVar.e());
        apkInfo.a(a(waVar.h()));
        apkInfo.j(waVar.j());
        apkInfo.k(waVar.P());
        apkInfo.a(a(waVar.k()));
        apkInfo.l(waVar.m() == null ? "1" : waVar.m());
        apkInfo.m(waVar.n() == null ? "0" : waVar.n());
        apkInfo.a(b(waVar.v()));
        apkInfo.o(waVar.q());
        apkInfo.b(b(waVar.r()));
        apkInfo.q(waVar.s());
        apkInfo.c(b(waVar.u()));
        apkInfo.n(waVar.z());
        apkInfo.r(waVar.x());
        apkInfo.s(waVar.y());
        apkInfo.d(a(waVar.w(), 1));
        apkInfo.e(b(waVar.E()));
        apkInfo.t(waVar.F());
        apkInfo.u(waVar.I());
        apkInfo.f(a(waVar.J(), 1));
        apkInfo.v(waVar.A());
        apkInfo.w(waVar.B());
        apkInfo.x(waVar.p());
        apkInfo.a(a(waVar.i()));
        apkInfo.y(waVar.D());
        apkInfo.z(waVar.l());
        apkInfo.A(waVar.C());
        apkInfo.g(a(waVar.G(), 1));
        apkInfo.h(b(waVar.K()));
        apkInfo.b(a(waVar.H()));
        apkInfo.B(waVar.M());
        apkInfo.C(waVar.N());
        apkInfo.D(waVar.N());
        apkInfo.E(waVar.O());
        apkInfo.F(waVar.L());
        return apkInfo;
    }

    public static AdTypeEvent a(vz vzVar) {
        if (vzVar == null) {
            return null;
        }
        AdTypeEvent adTypeEvent = new AdTypeEvent();
        adTypeEvent.a(b(vzVar.b()));
        adTypeEvent.a(a(vzVar.a()));
        return adTypeEvent;
    }

    private static AdSource a(vx vxVar) {
        if (vxVar == null) {
            return null;
        }
        AdSource adSource = new AdSource();
        adSource.a(vxVar.a());
        adSource.b(vxVar.b());
        adSource.a(b(vxVar.c()));
        return adSource;
    }

    public static Ad30 a(vv vvVar) {
        if (vvVar == null) {
            return null;
        }
        Ad30 ad30 = new Ad30();
        ad30.a(vvVar.a());
        ad30.a(a(vvVar.b(), -1));
        ad30.a(a(vvVar.d()));
        ad30.b(a(vvVar.f(), -1));
        ad30.b(vvVar.c());
        ad30.b(b(vvVar.e()));
        ad30.c(com.huawei.openalliance.ad.utils.be.b(a(vvVar.g())));
        ad30.a(a(vvVar.h()));
        return ad30;
    }

    private static long a(long j) {
        return a(j, 0L);
    }
}
