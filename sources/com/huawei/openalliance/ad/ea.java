package com.huawei.openalliance.ad;

import com.google.flatbuffers.FlatBufferBuilder;
import com.huawei.openalliance.ad.inter.data.fb.NativeAdFb;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class ea {
    public static byte[] a(com.huawei.openalliance.ad.inter.data.e eVar) {
        String str;
        if (eVar == null) {
            str = "toFlatBuffersBytes native ad is null";
        } else {
            try {
                FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(0);
                int[] j = eb.j(eVar.m(), flatBufferBuilder);
                int[] k = eb.k(eVar.t(), flatBufferBuilder);
                int[] i = eb.i(eVar.getCompliance(), flatBufferBuilder);
                int a2 = eb.a(eVar.getIcon(), flatBufferBuilder);
                int[] h = eb.h(eVar.getImageInfos(), flatBufferBuilder);
                int[] h2 = eb.h(eVar.getRawImageInfos(), flatBufferBuilder);
                int a3 = eb.a(eVar.getVideoInfo(), flatBufferBuilder);
                int a4 = eb.a(eVar.getAppInfo(), flatBufferBuilder);
                int a5 = eb.a(eVar.getAudioInfo(), flatBufferBuilder);
                int[] f = eb.f(eVar.aj(), flatBufferBuilder);
                int[] e = eb.e(eVar.ak(), flatBufferBuilder);
                int[] d = eb.d(eVar.getFeedbackInfoList(), flatBufferBuilder);
                int[] c = eb.c(eVar.X(), flatBufferBuilder);
                flatBufferBuilder.finish(NativeAdFb.createNativeAdFb(flatBufferBuilder, flatBufferBuilder.createString(ec.a(eVar.getShowId())), flatBufferBuilder.createString(ec.a(eVar.getContentId())), eVar.getEndTime(), flatBufferBuilder.createString(ec.a(eVar.getCta())), flatBufferBuilder.createString(ec.a(eVar.getAdSign())), eVar.getCreativeType(), eVar.w(), flatBufferBuilder.createString(ec.a(eVar.getTaskId())), flatBufferBuilder.createString(ec.a(eVar.getLabel())), eVar.getStartTime(), flatBufferBuilder.createString(ec.a(eVar.getIntent())), eVar.e(), flatBufferBuilder.createString(ec.a(eVar.f())), eVar.g(), flatBufferBuilder.createString(ec.a(eVar.getWhyThisAd())), flatBufferBuilder.createString(ec.a(eVar.getAdChoiceUrl())), flatBufferBuilder.createString(ec.a(eVar.getAdChoiceIcon())), eVar.h() == null ? 5 : eVar.h().intValue(), flatBufferBuilder.createString(ec.a(eVar.i())), flatBufferBuilder.createString(ec.a(eVar.j())), flatBufferBuilder.createString(ec.a(eVar.k())), flatBufferBuilder.createString(ec.a(eVar.l())), NativeAdFb.createOmVector(flatBufferBuilder, j), flatBufferBuilder.createString(ec.a(eVar.n())), flatBufferBuilder.createString(ec.a(eVar.getUniqueId())), eVar.o(), eVar.p(), eVar.q() == null ? 0 : eVar.q().intValue(), eVar.r() == null ? 0 : eVar.r().intValue(), eVar.s(), NativeAdFb.createAdSourcesVector(flatBufferBuilder, k), flatBufferBuilder.createString(ec.a(eVar.u())), flatBufferBuilder.createString(ec.a(eVar.getAbilityDetailInfo())), flatBufferBuilder.createString(ec.a(eVar.getHwChannelId())), NativeAdFb.createComplianceVector(flatBufferBuilder, i), eVar.isClicked(), eVar.getInterActionType(), flatBufferBuilder.createString(ec.a(eVar.K())), flatBufferBuilder.createString(ec.a(eVar.getTitle())), flatBufferBuilder.createString(ec.a(eVar.getDescription())), a2, NativeAdFb.createImageInfosVector(flatBufferBuilder, h), NativeAdFb.createRawImageInfosVector(flatBufferBuilder, h2), flatBufferBuilder.createString(ec.a(eVar.getLandWebUrl())), eVar.getMinEffectiveShowTime(), eVar.getMinEffectiveShowRatio(), flatBufferBuilder.createString(ec.a(eVar.L())), flatBufferBuilder.createString(ec.a(eVar.M())), a3, NativeAdFb.createAdCloseKeyWordsVector(flatBufferBuilder, ec.a(eVar.getAdCloseKeyWords(), flatBufferBuilder)), NativeAdFb.createAdCloseKeyWordsTypeVector(flatBufferBuilder, ec.a(eVar.N(), flatBufferBuilder)), a4, flatBufferBuilder.createString(ec.a(eVar.O())), flatBufferBuilder.createString(ec.a(eVar.P())), a5, eVar.Q(), NativeAdFb.createClickActionListVector(flatBufferBuilder, ec.a(eVar.R())), eVar.S(), flatBufferBuilder.createString(ec.a(eVar.T())), flatBufferBuilder.createString(ec.a(eVar.getCtrlSwitchs())), NativeAdFb.createNoReportEventListVector(flatBufferBuilder, ec.a(eVar.U(), flatBufferBuilder)), flatBufferBuilder.createString(ec.a(eVar.getSlotId())), eVar.V(), flatBufferBuilder.createString(ec.a(eVar.ac())), flatBufferBuilder.createString(ec.a(eVar.ad())), flatBufferBuilder.createString(ec.a(eVar.ae())), eVar.ar(), flatBufferBuilder.createString(ec.a(eVar.af())), NativeAdFb.createInvalidcontentidsVector(flatBufferBuilder, ec.a(eVar.getInvalidContentIds(), flatBufferBuilder)), eVar.ag(), flatBufferBuilder.createString(ec.a(eVar.D())), eVar.as(), flatBufferBuilder.createString(ec.a(eVar.ai())), eVar.at(), NativeAdFb.createExtVector(flatBufferBuilder, f), NativeAdFb.createContentExtsVector(flatBufferBuilder, e), NativeAdFb.createFeedbackInfoListVector(flatBufferBuilder, d), flatBufferBuilder.createString(ec.a(eVar.W())), NativeAdFb.createAssetsVector(flatBufferBuilder, c), eb.a(eVar.Y(), flatBufferBuilder), flatBufferBuilder.createString(ec.a(eVar.Z())), eVar.aa() == null ? 0 : eVar.aa().intValue(), eb.a(eVar.ab(), flatBufferBuilder), eVar.al(), flatBufferBuilder.createByteVector(eVar.am()), flatBufferBuilder.createString(ec.a(eVar.an())), NativeAdFb.createMonitorsVector(flatBufferBuilder, eb.a(eVar.ao(), flatBufferBuilder)), flatBufferBuilder.createString(ec.a(eVar.ap())), flatBufferBuilder.createString(ec.a(eVar.aq())), flatBufferBuilder.createString(ec.a(eVar.c())), eVar.y() != null ? eVar.y().intValue() : 0, eVar.isTransparencyOpen(), flatBufferBuilder.createString(ec.a(eVar.getTransparencyTplUrl())), flatBufferBuilder.createString(ec.a(eVar.A())), eb.a(eVar.getPromoteInfo(), flatBufferBuilder), eVar.x(), flatBufferBuilder.createString(ec.a(eVar.au())), flatBufferBuilder.createString(ec.a(eVar.av())), eVar.b(), NativeAdFb.createSubDescriptionVector(flatBufferBuilder, ec.a(eVar.getSubDescription(), flatBufferBuilder)), eb.a(eVar.getBiddingInfo(), flatBufferBuilder), flatBufferBuilder.createString(ec.a(eVar.aw())), flatBufferBuilder.createString(ec.a(eVar.ax())), eVar.a().intValue()));
                return flatBufferBuilder.sizedByteArray();
            } catch (Throwable th) {
                str = "toFlatBuffersBytes error:" + th.getClass().getSimpleName();
            }
        }
        ho.c("FBSerializeUtil", str);
        return null;
    }

    public static com.huawei.openalliance.ad.inter.data.e a(byte[] bArr) {
        String str;
        if (bArr == null || bArr.length == 0) {
            str = "data is empty";
        } else {
            try {
                NativeAdFb rootAsNativeAdFb = NativeAdFb.getRootAsNativeAdFb(ByteBuffer.wrap(bArr));
                if (rootAsNativeAdFb == null) {
                    ho.c("FBSerializeUtil", "fromFlatBuffersBytes native ad is null");
                    return null;
                }
                com.huawei.openalliance.ad.inter.data.e eVar = new com.huawei.openalliance.ad.inter.data.e();
                eVar.h(rootAsNativeAdFb.showId());
                eVar.c(rootAsNativeAdFb.contentId());
                eVar.b(rootAsNativeAdFb.endTime());
                eVar.b(rootAsNativeAdFb.cta());
                eVar.d(rootAsNativeAdFb.adSign());
                eVar.a(rootAsNativeAdFb.creativeType());
                eVar.e(rootAsNativeAdFb.originCreativeType());
                eVar.e(rootAsNativeAdFb.taskId());
                eVar.f(rootAsNativeAdFb.label());
                eVar.a(rootAsNativeAdFb.startTime());
                eVar.g(rootAsNativeAdFb.intent());
                eVar.b(rootAsNativeAdFb.adType());
                eVar.i(rootAsNativeAdFb.requestId());
                eVar.c(rootAsNativeAdFb.requestType());
                eVar.j(rootAsNativeAdFb.whyThisAd());
                eVar.k(rootAsNativeAdFb.adChoiceUrl());
                eVar.l(rootAsNativeAdFb.adChoiceIcon());
                eVar.b(Integer.valueOf(rootAsNativeAdFb.fileCachePriority()));
                eVar.n(rootAsNativeAdFb.appLanguage());
                eVar.o(rootAsNativeAdFb.appCountry());
                eVar.setCustomData(rootAsNativeAdFb.customData());
                eVar.setUserId(rootAsNativeAdFb.userId());
                eVar.a(dz.a(rootAsNativeAdFb.omVector()));
                eVar.p(rootAsNativeAdFb.landpgDlInteractionCfg());
                eVar.m(rootAsNativeAdFb.uniqueId());
                eVar.d(rootAsNativeAdFb.isPreload());
                eVar.c(rootAsNativeAdFb.startShowtime());
                eVar.c(Integer.valueOf(rootAsNativeAdFb.customExposureType()));
                eVar.d(Integer.valueOf(rootAsNativeAdFb.minEffectiveVideoPlayProgress()));
                eVar.c(rootAsNativeAdFb.isVastAd());
                eVar.b(dz.a(rootAsNativeAdFb.adSourcesVector()));
                eVar.q(rootAsNativeAdFb.logo2Text());
                eVar.r(rootAsNativeAdFb.abilityDetailInfo());
                eVar.s(rootAsNativeAdFb.hwChannelId());
                eVar.c(dz.a(rootAsNativeAdFb.complianceVector()));
                eVar.f(rootAsNativeAdFb.clicked());
                eVar.j(rootAsNativeAdFb.interactiontype());
                eVar.z(rootAsNativeAdFb.encodedParamFromServer());
                eVar.A(rootAsNativeAdFb.title());
                eVar.B(rootAsNativeAdFb.description());
                eVar.a(dz.a(rootAsNativeAdFb.icon()));
                eVar.e(dz.a(rootAsNativeAdFb.imageInfosVector()));
                eVar.l(dz.a(rootAsNativeAdFb.rawImageInfosVector()));
                eVar.C(rootAsNativeAdFb.landWebUrl());
                eVar.d(rootAsNativeAdFb.minEffectiveShowTime());
                eVar.k(rootAsNativeAdFb.minEffectiveShowRatio());
                eVar.D(rootAsNativeAdFb.appPromotionChannel());
                eVar.E(rootAsNativeAdFb.marketAppId());
                eVar.a(dz.a(rootAsNativeAdFb.videoInfo()));
                eVar.f(ec.a(rootAsNativeAdFb.adCloseKeyWordsVector()));
                eVar.g(ec.a(rootAsNativeAdFb.adCloseKeyWordsTypeVector()));
                eVar.a(dz.a(rootAsNativeAdFb.appInfo()));
                eVar.F(rootAsNativeAdFb.metaData());
                eVar.G(rootAsNativeAdFb.encodedeMonitors());
                eVar.a(dz.a(rootAsNativeAdFb.audioInfo()));
                eVar.l(rootAsNativeAdFb.showLandingPageTitleFlag());
                eVar.h(ec.a(rootAsNativeAdFb.clickActionListVector()));
                eVar.g(rootAsNativeAdFb.shown());
                eVar.H(rootAsNativeAdFb.webConfig());
                eVar.I(rootAsNativeAdFb.ctrlSwitchs());
                eVar.i(ec.a(rootAsNativeAdFb.noReportEventListVector()));
                eVar.J(rootAsNativeAdFb.slotId());
                eVar.h(rootAsNativeAdFb.hasReportShowEventDuringShowTime());
                eVar.M(rootAsNativeAdFb.taskinfo());
                eVar.N(rootAsNativeAdFb.encodeWhiteList());
                eVar.O(rootAsNativeAdFb.encodeJssdkAllowList());
                eVar.i(rootAsNativeAdFb.directReturnVideoAd());
                eVar.P(rootAsNativeAdFb.landingPageType());
                eVar.k(ec.a(rootAsNativeAdFb.invalidcontentidsVector()));
                eVar.j(rootAsNativeAdFb.isShowStartReported());
                eVar.w(rootAsNativeAdFb.splashMediaPath());
                eVar.e(rootAsNativeAdFb.apiRecordStartTime());
                eVar.Q(rootAsNativeAdFb.bannerRefSetting());
                eVar.m(rootAsNativeAdFb.useGaussianBlur());
                eVar.m(dz.a(rootAsNativeAdFb.extVector()));
                eVar.n(dz.a(rootAsNativeAdFb.contentExtsVector()));
                eVar.o(dz.a(rootAsNativeAdFb.feedbackInfoListVector()));
                eVar.K(rootAsNativeAdFb.templateId());
                eVar.j(dz.a(rootAsNativeAdFb.assetsVector()));
                eVar.a(dz.a(rootAsNativeAdFb.templateData()));
                eVar.L(rootAsNativeAdFb.templateStyle());
                eVar.f(Integer.valueOf(rootAsNativeAdFb.apiVer()));
                eVar.a(dz.a(rootAsNativeAdFb.defaultTemplate()));
                eVar.k(rootAsNativeAdFb.encryptInContentRrd());
                eVar.a(ec.a(rootAsNativeAdFb.workingKeyVector()));
                eVar.R(rootAsNativeAdFb.originParamFromServer());
                eVar.p(dz.a(rootAsNativeAdFb.monitorsVector()));
                eVar.S(rootAsNativeAdFb.originWhiteList());
                eVar.T(rootAsNativeAdFb.originJssdkAllowList());
                eVar.a(rootAsNativeAdFb.ctrlExt());
                eVar.e(Integer.valueOf(rootAsNativeAdFb.showAppElement()));
                eVar.t(rootAsNativeAdFb.transparencyTplUrl());
                eVar.d(rootAsNativeAdFb.transparencySwitch());
                eVar.u(rootAsNativeAdFb.adRealCallerPkgName());
                eVar.a(dz.a(rootAsNativeAdFb.promoteInfo()));
                eVar.f(rootAsNativeAdFb.recallSource());
                eVar.V(rootAsNativeAdFb.cshareUrl());
                eVar.U(rootAsNativeAdFb.templateUrl());
                eVar.a(dz.a(rootAsNativeAdFb.biddingInfo()));
                eVar.a(rootAsNativeAdFb.hasReportInteractiveImp());
                eVar.d(ec.a(rootAsNativeAdFb.subDescriptionVector()));
                eVar.W(rootAsNativeAdFb.style());
                eVar.X(rootAsNativeAdFb.styleExt());
                eVar.a(Integer.valueOf(rootAsNativeAdFb.sdkMonitor()));
                return eVar;
            } catch (Throwable th) {
                str = "fromFlatBuffersBytes error:" + th.getClass().getSimpleName();
            }
        }
        ho.c("FBSerializeUtil", str);
        return null;
    }
}
