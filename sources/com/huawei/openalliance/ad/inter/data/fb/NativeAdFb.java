package com.huawei.openalliance.ad.inter.data.fb;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.ByteVector;
import com.google.flatbuffers.Constants;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.StringVector;
import com.google.flatbuffers.Table;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.openalliance.ad.inter.data.fb.AdSourceFb;
import com.huawei.openalliance.ad.inter.data.fb.AdvertiserInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.AssetFb;
import com.huawei.openalliance.ad.inter.data.fb.ContentExtFb;
import com.huawei.openalliance.ad.inter.data.fb.FeedbackInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.ImageInfoFb;
import com.huawei.openalliance.ad.inter.data.fb.ImpEXFb;
import com.huawei.openalliance.ad.inter.data.fb.MonitorFb;
import com.huawei.openalliance.ad.inter.data.fb.OmFb;
import com.huawei.up.model.UserInfomation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class NativeAdFb extends Table {
    public ByteVector workingKeyVector(ByteVector byteVector) {
        int __offset = __offset(172);
        if (__offset != 0) {
            return byteVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public ByteVector workingKeyVector() {
        return workingKeyVector(new ByteVector());
    }

    public int workingKeyLength() {
        int __offset = __offset(172);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ByteBuffer workingKeyInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 172, 1);
    }

    public ByteBuffer workingKeyAsByteBuffer() {
        return __vector_as_bytebuffer(172, 1);
    }

    public byte workingKey(int i) {
        int __offset = __offset(172);
        if (__offset != 0) {
            return this.bb.get(__vector(__offset) + i);
        }
        return (byte) 0;
    }

    public ByteBuffer whyThisAdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 32, 1);
    }

    public ByteBuffer whyThisAdAsByteBuffer() {
        return __vector_as_bytebuffer(32, 1);
    }

    public String whyThisAd() {
        int __offset = __offset(32);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer webConfigInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 120, 1);
    }

    public ByteBuffer webConfigAsByteBuffer() {
        return __vector_as_bytebuffer(120, 1);
    }

    public String webConfig() {
        int __offset = __offset(120);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public VideoInfoFb videoInfo(VideoInfoFb videoInfoFb) {
        int __offset = __offset(100);
        if (__offset != 0) {
            return videoInfoFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public VideoInfoFb videoInfo() {
        return videoInfo(new VideoInfoFb());
    }

    public ByteBuffer userIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 46, 1);
    }

    public ByteBuffer userIdAsByteBuffer() {
        return __vector_as_bytebuffer(46, 1);
    }

    public String userId() {
        int __offset = __offset(46);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int useGaussianBlur() {
        int __offset = __offset(150);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer uniqueIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 52, 1);
    }

    public ByteBuffer uniqueIdAsByteBuffer() {
        return __vector_as_bytebuffer(52, 1);
    }

    public String uniqueId() {
        int __offset = __offset(52);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer transparencyTplUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 188, 1);
    }

    public ByteBuffer transparencyTplUrlAsByteBuffer() {
        return __vector_as_bytebuffer(188, 1);
    }

    public String transparencyTplUrl() {
        int __offset = __offset(188);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean transparencySwitch() {
        int __offset = __offset(186);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public ByteBuffer titleInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 80, 1);
    }

    public ByteBuffer titleAsByteBuffer() {
        return __vector_as_bytebuffer(80, 1);
    }

    public String title() {
        int __offset = __offset(80);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer templateUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 196, 1);
    }

    public ByteBuffer templateUrlAsByteBuffer() {
        return __vector_as_bytebuffer(196, 1);
    }

    public String templateUrl() {
        int __offset = __offset(196);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer templateStyleInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA, 1);
    }

    public ByteBuffer templateStyleAsByteBuffer() {
        return __vector_as_bytebuffer(MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA, 1);
    }

    public String templateStyle() {
        int __offset = __offset(MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer templateIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 158, 1);
    }

    public ByteBuffer templateIdAsByteBuffer() {
        return __vector_as_bytebuffer(158, 1);
    }

    public String templateId() {
        int __offset = __offset(158);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public TemplateDataFb templateData(TemplateDataFb templateDataFb) {
        int __offset = __offset(MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY);
        if (__offset != 0) {
            return templateDataFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public TemplateDataFb templateData() {
        return templateData(new TemplateDataFb());
    }

    public ByteBuffer taskinfoInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, OldToNewMotionPath.SPORT_TYPE_TENNIS, 1);
    }

    public ByteBuffer taskinfoAsByteBuffer() {
        return __vector_as_bytebuffer(OldToNewMotionPath.SPORT_TYPE_TENNIS, 1);
    }

    public String taskinfo() {
        int __offset = __offset(OldToNewMotionPath.SPORT_TYPE_TENNIS);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer taskIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public ByteBuffer taskIdAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public String taskId() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public StringVector subDescriptionVector(StringVector stringVector) {
        int __offset = __offset(202);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector subDescriptionVector() {
        return subDescriptionVector(new StringVector());
    }

    public int subDescriptionLength() {
        int __offset = __offset(202);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public String subDescription(int i) {
        int __offset = __offset(202);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public ByteBuffer styleInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 206, 1);
    }

    public ByteBuffer styleExtInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, a.z, 1);
    }

    public ByteBuffer styleExtAsByteBuffer() {
        return __vector_as_bytebuffer(a.z, 1);
    }

    public String styleExt() {
        int __offset = __offset(a.z);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer styleAsByteBuffer() {
        return __vector_as_bytebuffer(206, 1);
    }

    public String style() {
        int __offset = __offset(206);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public long startTime() {
        int __offset = __offset(22);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public long startShowtime() {
        int __offset = __offset(56);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public ByteBuffer splashMediaPathInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 144, 1);
    }

    public ByteBuffer splashMediaPathAsByteBuffer() {
        return __vector_as_bytebuffer(144, 1);
    }

    public String splashMediaPath() {
        int __offset = __offset(144);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer slotIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 126, 1);
    }

    public ByteBuffer slotIdAsByteBuffer() {
        return __vector_as_bytebuffer(126, 1);
    }

    public String slotId() {
        int __offset = __offset(126);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean shown() {
        int __offset = __offset(118);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public int showLandingPageTitleFlag() {
        int __offset = __offset(114);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer showIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public ByteBuffer showIdAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public String showId() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int showAppElement() {
        int __offset = __offset(184);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int sdkMonitor() {
        int __offset = __offset(a.C);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int requestType() {
        int __offset = __offset(30);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer requestIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 28, 1);
    }

    public ByteBuffer requestIdAsByteBuffer() {
        return __vector_as_bytebuffer(28, 1);
    }

    public String requestId() {
        int __offset = __offset(28);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int recallSource() {
        int __offset = __offset(194);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ImageInfoFb.Vector rawImageInfosVector(ImageInfoFb.Vector vector) {
        int __offset = __offset(88);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public ImageInfoFb.Vector rawImageInfosVector() {
        return rawImageInfosVector(new ImageInfoFb.Vector());
    }

    public int rawImageInfosLength() {
        int __offset = __offset(88);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ImageInfoFb rawImageInfos(ImageInfoFb imageInfoFb, int i) {
        int __offset = __offset(88);
        if (__offset != 0) {
            return imageInfoFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public ImageInfoFb rawImageInfos(int i) {
        return rawImageInfos(new ImageInfoFb(), i);
    }

    public PromoteInfoFb promoteInfo(PromoteInfoFb promoteInfoFb) {
        int __offset = __offset(192);
        if (__offset != 0) {
            return promoteInfoFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public PromoteInfoFb promoteInfo() {
        return promoteInfo(new PromoteInfoFb());
    }

    public ByteBuffer originWhiteListInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 178, 1);
    }

    public ByteBuffer originWhiteListAsByteBuffer() {
        return __vector_as_bytebuffer(178, 1);
    }

    public String originWhiteList() {
        int __offset = __offset(178);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer originParamFromServerInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 174, 1);
    }

    public ByteBuffer originParamFromServerAsByteBuffer() {
        return __vector_as_bytebuffer(174, 1);
    }

    public String originParamFromServer() {
        int __offset = __offset(174);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer originJssdkAllowListInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 180, 1);
    }

    public ByteBuffer originJssdkAllowListAsByteBuffer() {
        return __vector_as_bytebuffer(180, 1);
    }

    public String originJssdkAllowList() {
        int __offset = __offset(180);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int originCreativeType() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public OmFb.Vector omVector(OmFb.Vector vector) {
        int __offset = __offset(48);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public OmFb.Vector omVector() {
        return omVector(new OmFb.Vector());
    }

    public int omLength() {
        int __offset = __offset(48);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public OmFb om(OmFb omFb, int i) {
        int __offset = __offset(48);
        if (__offset != 0) {
            return omFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public OmFb om(int i) {
        return om(new OmFb(), i);
    }

    public StringVector noReportEventListVector(StringVector stringVector) {
        int __offset = __offset(124);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector noReportEventListVector() {
        return noReportEventListVector(new StringVector());
    }

    public int noReportEventListLength() {
        int __offset = __offset(124);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public String noReportEventList(int i) {
        int __offset = __offset(124);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public MonitorFb.Vector monitorsVector(MonitorFb.Vector vector) {
        int __offset = __offset(176);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public MonitorFb.Vector monitorsVector() {
        return monitorsVector(new MonitorFb.Vector());
    }

    public int monitorsLength() {
        int __offset = __offset(176);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public MonitorFb monitors(MonitorFb monitorFb, int i) {
        int __offset = __offset(176);
        if (__offset != 0) {
            return monitorFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public MonitorFb monitors(int i) {
        return monitors(new MonitorFb(), i);
    }

    public int minEffectiveVideoPlayProgress() {
        int __offset = __offset(60);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public long minEffectiveShowTime() {
        int __offset = __offset(92);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public int minEffectiveShowRatio() {
        int __offset = __offset(94);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer metaDataInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 108, 1);
    }

    public ByteBuffer metaDataAsByteBuffer() {
        return __vector_as_bytebuffer(108, 1);
    }

    public String metaData() {
        int __offset = __offset(108);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer marketAppIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 98, 1);
    }

    public ByteBuffer marketAppIdAsByteBuffer() {
        return __vector_as_bytebuffer(98, 1);
    }

    public String marketAppId() {
        int __offset = __offset(98);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer logo2TextInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 66, 1);
    }

    public ByteBuffer logo2TextAsByteBuffer() {
        return __vector_as_bytebuffer(66, 1);
    }

    public String logo2Text() {
        int __offset = __offset(66);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer landpgDlInteractionCfgInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 50, 1);
    }

    public ByteBuffer landpgDlInteractionCfgAsByteBuffer() {
        return __vector_as_bytebuffer(50, 1);
    }

    public String landpgDlInteractionCfg() {
        int __offset = __offset(50);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer landingPageTypeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, OldToNewMotionPath.SPORT_TYPE_PILATES, 1);
    }

    public ByteBuffer landingPageTypeAsByteBuffer() {
        return __vector_as_bytebuffer(OldToNewMotionPath.SPORT_TYPE_PILATES, 1);
    }

    public String landingPageType() {
        int __offset = __offset(OldToNewMotionPath.SPORT_TYPE_PILATES);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer landWebUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 90, 1);
    }

    public ByteBuffer landWebUrlAsByteBuffer() {
        return __vector_as_bytebuffer(90, 1);
    }

    public String landWebUrl() {
        int __offset = __offset(90);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer labelInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 20, 1);
    }

    public ByteBuffer labelAsByteBuffer() {
        return __vector_as_bytebuffer(20, 1);
    }

    public String label() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean isVastAd() {
        int __offset = __offset(62);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean isShowStartReported() {
        int __offset = __offset(142);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public int isPreload() {
        int __offset = __offset(54);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public StringVector invalidcontentidsVector(StringVector stringVector) {
        int __offset = __offset(140);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector invalidcontentidsVector() {
        return invalidcontentidsVector(new StringVector());
    }

    public int invalidcontentidsLength() {
        int __offset = __offset(140);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public String invalidcontentids(int i) {
        int __offset = __offset(140);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int interactiontype() {
        int __offset = __offset(76);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer intentInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 24, 1);
    }

    public ByteBuffer intentAsByteBuffer() {
        return __vector_as_bytebuffer(24, 1);
    }

    public String intent() {
        int __offset = __offset(24);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ImageInfoFb.Vector imageInfosVector(ImageInfoFb.Vector vector) {
        int __offset = __offset(86);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public ImageInfoFb.Vector imageInfosVector() {
        return imageInfosVector(new ImageInfoFb.Vector());
    }

    public int imageInfosLength() {
        int __offset = __offset(86);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ImageInfoFb imageInfos(ImageInfoFb imageInfoFb, int i) {
        int __offset = __offset(86);
        if (__offset != 0) {
            return imageInfoFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public ImageInfoFb imageInfos(int i) {
        return imageInfos(new ImageInfoFb(), i);
    }

    public ImageInfoFb icon(ImageInfoFb imageInfoFb) {
        int __offset = __offset(84);
        if (__offset != 0) {
            return imageInfoFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public ImageInfoFb icon() {
        return icon(new ImageInfoFb());
    }

    public ByteBuffer hwChannelIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 70, 1);
    }

    public ByteBuffer hwChannelIdAsByteBuffer() {
        return __vector_as_bytebuffer(70, 1);
    }

    public String hwChannelId() {
        int __offset = __offset(70);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean hasReportShowEventDuringShowTime() {
        int __offset = __offset(128);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public boolean hasReportInteractiveImp() {
        int __offset = __offset(200);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public int fileCachePriority() {
        int __offset = __offset(38);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public FeedbackInfoFb.Vector feedbackInfoListVector(FeedbackInfoFb.Vector vector) {
        int __offset = __offset(156);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public FeedbackInfoFb.Vector feedbackInfoListVector() {
        return feedbackInfoListVector(new FeedbackInfoFb.Vector());
    }

    public int feedbackInfoListLength() {
        int __offset = __offset(156);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public FeedbackInfoFb feedbackInfoList(FeedbackInfoFb feedbackInfoFb, int i) {
        int __offset = __offset(156);
        if (__offset != 0) {
            return feedbackInfoFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public FeedbackInfoFb feedbackInfoList(int i) {
        return feedbackInfoList(new FeedbackInfoFb(), i);
    }

    public ImpEXFb.Vector extVector(ImpEXFb.Vector vector) {
        int __offset = __offset(152);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public ImpEXFb.Vector extVector() {
        return extVector(new ImpEXFb.Vector());
    }

    public int extLength() {
        int __offset = __offset(152);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ImpEXFb ext(ImpEXFb impEXFb, int i) {
        int __offset = __offset(152);
        if (__offset != 0) {
            return impEXFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public ImpEXFb ext(int i) {
        return ext(new ImpEXFb(), i);
    }

    public long endTime() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public boolean encryptInContentRrd() {
        int __offset = __offset(170);
        return __offset == 0 || this.bb.get(__offset + this.bb_pos) != 0;
    }

    public ByteBuffer encodedeMonitorsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 110, 1);
    }

    public ByteBuffer encodedeMonitorsAsByteBuffer() {
        return __vector_as_bytebuffer(110, 1);
    }

    public String encodedeMonitors() {
        int __offset = __offset(110);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer encodedParamFromServerInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 78, 1);
    }

    public ByteBuffer encodedParamFromServerAsByteBuffer() {
        return __vector_as_bytebuffer(78, 1);
    }

    public String encodedParamFromServer() {
        int __offset = __offset(78);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer encodeWhiteListInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, UserInfomation.WEIGHT_DEFAULT_ENGLISH, 1);
    }

    public ByteBuffer encodeWhiteListAsByteBuffer() {
        return __vector_as_bytebuffer(UserInfomation.WEIGHT_DEFAULT_ENGLISH, 1);
    }

    public String encodeWhiteList() {
        int __offset = __offset(UserInfomation.WEIGHT_DEFAULT_ENGLISH);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer encodeJssdkAllowListInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 134, 1);
    }

    public ByteBuffer encodeJssdkAllowListAsByteBuffer() {
        return __vector_as_bytebuffer(134, 1);
    }

    public String encodeJssdkAllowList() {
        int __offset = __offset(134);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public boolean directReturnVideoAd() {
        int __offset = __offset(136);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public ByteBuffer descriptionInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 82, 1);
    }

    public ByteBuffer descriptionAsByteBuffer() {
        return __vector_as_bytebuffer(82, 1);
    }

    public String description() {
        int __offset = __offset(82);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public DefaultTemplateFb defaultTemplate(DefaultTemplateFb defaultTemplateFb) {
        int __offset = __offset(168);
        if (__offset != 0) {
            return defaultTemplateFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public DefaultTemplateFb defaultTemplate() {
        return defaultTemplate(new DefaultTemplateFb());
    }

    public int customExposureType() {
        int __offset = __offset(58);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer customDataInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 44, 1);
    }

    public ByteBuffer customDataAsByteBuffer() {
        return __vector_as_bytebuffer(44, 1);
    }

    public String customData() {
        int __offset = __offset(44);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer ctrlSwitchsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 122, 1);
    }

    public ByteBuffer ctrlSwitchsAsByteBuffer() {
        return __vector_as_bytebuffer(122, 1);
    }

    public String ctrlSwitchs() {
        int __offset = __offset(122);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer ctrlExtInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 182, 1);
    }

    public ByteBuffer ctrlExtAsByteBuffer() {
        return __vector_as_bytebuffer(182, 1);
    }

    public String ctrlExt() {
        int __offset = __offset(182);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer ctaInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public ByteBuffer ctaAsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public String cta() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer cshareUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 198, 1);
    }

    public ByteBuffer cshareUrlAsByteBuffer() {
        return __vector_as_bytebuffer(198, 1);
    }

    public String cshareUrl() {
        int __offset = __offset(198);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int creativeType() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public ByteBuffer contentIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ByteBuffer contentIdAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public String contentId() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ContentExtFb.Vector contentExtsVector(ContentExtFb.Vector vector) {
        int __offset = __offset(154);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public ContentExtFb.Vector contentExtsVector() {
        return contentExtsVector(new ContentExtFb.Vector());
    }

    public int contentExtsLength() {
        int __offset = __offset(154);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ContentExtFb contentExts(ContentExtFb contentExtFb, int i) {
        int __offset = __offset(154);
        if (__offset != 0) {
            return contentExtFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public ContentExtFb contentExts(int i) {
        return contentExts(new ContentExtFb(), i);
    }

    public AdvertiserInfoFb.Vector complianceVector(AdvertiserInfoFb.Vector vector) {
        int __offset = __offset(72);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public AdvertiserInfoFb.Vector complianceVector() {
        return complianceVector(new AdvertiserInfoFb.Vector());
    }

    public int complianceLength() {
        int __offset = __offset(72);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public AdvertiserInfoFb compliance(AdvertiserInfoFb advertiserInfoFb, int i) {
        int __offset = __offset(72);
        if (__offset != 0) {
            return advertiserInfoFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public AdvertiserInfoFb compliance(int i) {
        return compliance(new AdvertiserInfoFb(), i);
    }

    public boolean clicked() {
        int __offset = __offset(74);
        return (__offset == 0 || this.bb.get(__offset + this.bb_pos) == 0) ? false : true;
    }

    public IntVector clickActionListVector(IntVector intVector) {
        int __offset = __offset(116);
        if (__offset != 0) {
            return intVector.__assign(__vector(__offset), this.bb);
        }
        return null;
    }

    public IntVector clickActionListVector() {
        return clickActionListVector(new IntVector());
    }

    public int clickActionListLength() {
        int __offset = __offset(116);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public ByteBuffer clickActionListInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 116, 4);
    }

    public ByteBuffer clickActionListAsByteBuffer() {
        return __vector_as_bytebuffer(116, 4);
    }

    public int clickActionList(int i) {
        int __offset = __offset(116);
        if (__offset != 0) {
            return this.bb.getInt(__vector(__offset) + (i * 4));
        }
        return 0;
    }

    public BiddingInfoFb biddingInfo(BiddingInfoFb biddingInfoFb) {
        int __offset = __offset(204);
        if (__offset != 0) {
            return biddingInfoFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public BiddingInfoFb biddingInfo() {
        return biddingInfo(new BiddingInfoFb());
    }

    public ByteBuffer bannerRefSettingInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 148, 1);
    }

    public ByteBuffer bannerRefSettingAsByteBuffer() {
        return __vector_as_bytebuffer(148, 1);
    }

    public String bannerRefSetting() {
        int __offset = __offset(148);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public AudioInfoFb audioInfo(AudioInfoFb audioInfoFb) {
        int __offset = __offset(112);
        if (__offset != 0) {
            return audioInfoFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public AudioInfoFb audioInfo() {
        return audioInfo(new AudioInfoFb());
    }

    public AssetFb.Vector assetsVector(AssetFb.Vector vector) {
        int __offset = __offset(160);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public AssetFb.Vector assetsVector() {
        return assetsVector(new AssetFb.Vector());
    }

    public int assetsLength() {
        int __offset = __offset(160);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public AssetFb assets(AssetFb assetFb, int i) {
        int __offset = __offset(160);
        if (__offset != 0) {
            return assetFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public AssetFb assets(int i) {
        return assets(new AssetFb(), i);
    }

    public ByteBuffer appPromotionChannelInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 96, 1);
    }

    public ByteBuffer appPromotionChannelAsByteBuffer() {
        return __vector_as_bytebuffer(96, 1);
    }

    public String appPromotionChannel() {
        int __offset = __offset(96);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer appLanguageInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 40, 1);
    }

    public ByteBuffer appLanguageAsByteBuffer() {
        return __vector_as_bytebuffer(40, 1);
    }

    public String appLanguage() {
        int __offset = __offset(40);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public AppInfoFb appInfo(AppInfoFb appInfoFb) {
        int __offset = __offset(106);
        if (__offset != 0) {
            return appInfoFb.__assign(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public AppInfoFb appInfo() {
        return appInfo(new AppInfoFb());
    }

    public ByteBuffer appCountryInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 42, 1);
    }

    public ByteBuffer appCountryAsByteBuffer() {
        return __vector_as_bytebuffer(42, 1);
    }

    public String appCountry() {
        int __offset = __offset(42);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int apiVer() {
        int __offset = __offset(MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public long apiRecordStartTime() {
        int __offset = __offset(146);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public int adType() {
        int __offset = __offset(26);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public AdSourceFb.Vector adSourcesVector(AdSourceFb.Vector vector) {
        int __offset = __offset(64);
        if (__offset != 0) {
            return vector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public AdSourceFb.Vector adSourcesVector() {
        return adSourcesVector(new AdSourceFb.Vector());
    }

    public int adSourcesLength() {
        int __offset = __offset(64);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public AdSourceFb adSources(AdSourceFb adSourceFb, int i) {
        int __offset = __offset(64);
        if (__offset != 0) {
            return adSourceFb.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb);
        }
        return null;
    }

    public AdSourceFb adSources(int i) {
        return adSources(new AdSourceFb(), i);
    }

    public ByteBuffer adSignInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 12, 1);
    }

    public ByteBuffer adSignAsByteBuffer() {
        return __vector_as_bytebuffer(12, 1);
    }

    public String adSign() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer adRealCallerPkgNameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 190, 1);
    }

    public ByteBuffer adRealCallerPkgNameAsByteBuffer() {
        return __vector_as_bytebuffer(190, 1);
    }

    public String adRealCallerPkgName() {
        int __offset = __offset(190);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public StringVector adCloseKeyWordsVector(StringVector stringVector) {
        int __offset = __offset(102);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector adCloseKeyWordsVector() {
        return adCloseKeyWordsVector(new StringVector());
    }

    public StringVector adCloseKeyWordsTypeVector(StringVector stringVector) {
        int __offset = __offset(104);
        if (__offset != 0) {
            return stringVector.__assign(__vector(__offset), 4, this.bb);
        }
        return null;
    }

    public StringVector adCloseKeyWordsTypeVector() {
        return adCloseKeyWordsTypeVector(new StringVector());
    }

    public int adCloseKeyWordsTypeLength() {
        int __offset = __offset(104);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public String adCloseKeyWordsType(int i) {
        int __offset = __offset(104);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public int adCloseKeyWordsLength() {
        int __offset = __offset(102);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public String adCloseKeyWords(int i) {
        int __offset = __offset(102);
        if (__offset != 0) {
            return __string(__vector(__offset) + (i * 4));
        }
        return null;
    }

    public ByteBuffer adChoiceUrlInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 34, 1);
    }

    public ByteBuffer adChoiceUrlAsByteBuffer() {
        return __vector_as_bytebuffer(34, 1);
    }

    public String adChoiceUrl() {
        int __offset = __offset(34);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer adChoiceIconInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 36, 1);
    }

    public ByteBuffer adChoiceIconAsByteBuffer() {
        return __vector_as_bytebuffer(36, 1);
    }

    public String adChoiceIcon() {
        int __offset = __offset(36);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer abilityDetailInfoInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 68, 1);
    }

    public ByteBuffer abilityDetailInfoAsByteBuffer() {
        return __vector_as_bytebuffer(68, 1);
    }

    public String abilityDetailInfo() {
        int __offset = __offset(68);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public NativeAdFb __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public static void startWorkingKeyVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(1, i, 1);
    }

    public static void startSubDescriptionVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startRawImageInfosVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startOmVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startNoReportEventListVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startNativeAdFb(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(104);
    }

    public static void startMonitorsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startInvalidcontentidsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startImageInfosVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startFeedbackInfoListVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startExtVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startContentExtsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startComplianceVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startClickActionListVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startAssetsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startAdSourcesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startAdCloseKeyWordsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startAdCloseKeyWordsTypeVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static NativeAdFb getRootAsNativeAdFb(ByteBuffer byteBuffer, NativeAdFb nativeAdFb) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return nativeAdFb.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static NativeAdFb getRootAsNativeAdFb(ByteBuffer byteBuffer) {
        return getRootAsNativeAdFb(byteBuffer, new NativeAdFb());
    }

    public static int endNativeAdFb(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static int createWorkingKeyVector(FlatBufferBuilder flatBufferBuilder, byte[] bArr) {
        return flatBufferBuilder.createByteVector(bArr);
    }

    public static int createWorkingKeyVector(FlatBufferBuilder flatBufferBuilder, ByteBuffer byteBuffer) {
        return flatBufferBuilder.createByteVector(byteBuffer);
    }

    public static int createSubDescriptionVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createRawImageInfosVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createOmVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createNoReportEventListVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createNativeAdFb(FlatBufferBuilder flatBufferBuilder, int i, int i2, long j, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, long j3, int i25, int i26, boolean z, int i27, int i28, int i29, int i30, int i31, boolean z2, int i32, int i33, int i34, int i35, int i36, int i37, int i38, int i39, long j4, int i40, int i41, int i42, int i43, int i44, int i45, int i46, int i47, int i48, int i49, int i50, int i51, boolean z3, int i52, int i53, int i54, int i55, boolean z4, int i56, int i57, int i58, boolean z5, int i59, int i60, boolean z6, int i61, long j5, int i62, int i63, int i64, int i65, int i66, int i67, int i68, int i69, int i70, int i71, int i72, boolean z7, int i73, int i74, int i75, int i76, int i77, int i78, int i79, boolean z8, int i80, int i81, int i82, int i83, int i84, int i85, boolean z9, int i86, int i87, int i88, int i89, int i90) {
        flatBufferBuilder.startTable(104);
        addApiRecordStartTime(flatBufferBuilder, j5);
        addMinEffectiveShowTime(flatBufferBuilder, j4);
        addStartShowtime(flatBufferBuilder, j3);
        addStartTime(flatBufferBuilder, j2);
        addEndTime(flatBufferBuilder, j);
        addSdkMonitor(flatBufferBuilder, i90);
        addStyleExt(flatBufferBuilder, i89);
        addStyle(flatBufferBuilder, i88);
        addBiddingInfo(flatBufferBuilder, i87);
        addSubDescription(flatBufferBuilder, i86);
        addCshareUrl(flatBufferBuilder, i85);
        addTemplateUrl(flatBufferBuilder, i84);
        addRecallSource(flatBufferBuilder, i83);
        addPromoteInfo(flatBufferBuilder, i82);
        addAdRealCallerPkgName(flatBufferBuilder, i81);
        addTransparencyTplUrl(flatBufferBuilder, i80);
        addShowAppElement(flatBufferBuilder, i79);
        addCtrlExt(flatBufferBuilder, i78);
        addOriginJssdkAllowList(flatBufferBuilder, i77);
        addOriginWhiteList(flatBufferBuilder, i76);
        addMonitors(flatBufferBuilder, i75);
        addOriginParamFromServer(flatBufferBuilder, i74);
        addWorkingKey(flatBufferBuilder, i73);
        addDefaultTemplate(flatBufferBuilder, i72);
        addApiVer(flatBufferBuilder, i71);
        addTemplateStyle(flatBufferBuilder, i70);
        addTemplateData(flatBufferBuilder, i69);
        addAssets(flatBufferBuilder, i68);
        addTemplateId(flatBufferBuilder, i67);
        addFeedbackInfoList(flatBufferBuilder, i66);
        addContentExts(flatBufferBuilder, i65);
        addExt(flatBufferBuilder, i64);
        addUseGaussianBlur(flatBufferBuilder, i63);
        addBannerRefSetting(flatBufferBuilder, i62);
        addSplashMediaPath(flatBufferBuilder, i61);
        addInvalidcontentids(flatBufferBuilder, i60);
        addLandingPageType(flatBufferBuilder, i59);
        addEncodeJssdkAllowList(flatBufferBuilder, i58);
        addEncodeWhiteList(flatBufferBuilder, i57);
        addTaskinfo(flatBufferBuilder, i56);
        addSlotId(flatBufferBuilder, i55);
        addNoReportEventList(flatBufferBuilder, i54);
        addCtrlSwitchs(flatBufferBuilder, i53);
        addWebConfig(flatBufferBuilder, i52);
        addClickActionList(flatBufferBuilder, i51);
        addShowLandingPageTitleFlag(flatBufferBuilder, i50);
        addAudioInfo(flatBufferBuilder, i49);
        addEncodedeMonitors(flatBufferBuilder, i48);
        addMetaData(flatBufferBuilder, i47);
        addAppInfo(flatBufferBuilder, i46);
        addAdCloseKeyWordsType(flatBufferBuilder, i45);
        addAdCloseKeyWords(flatBufferBuilder, i44);
        addVideoInfo(flatBufferBuilder, i43);
        addMarketAppId(flatBufferBuilder, i42);
        addAppPromotionChannel(flatBufferBuilder, i41);
        addMinEffectiveShowRatio(flatBufferBuilder, i40);
        addLandWebUrl(flatBufferBuilder, i39);
        addRawImageInfos(flatBufferBuilder, i38);
        addImageInfos(flatBufferBuilder, i37);
        addIcon(flatBufferBuilder, i36);
        addDescription(flatBufferBuilder, i35);
        addTitle(flatBufferBuilder, i34);
        addEncodedParamFromServer(flatBufferBuilder, i33);
        addInteractiontype(flatBufferBuilder, i32);
        addCompliance(flatBufferBuilder, i31);
        addHwChannelId(flatBufferBuilder, i30);
        addAbilityDetailInfo(flatBufferBuilder, i29);
        addLogo2Text(flatBufferBuilder, i28);
        addAdSources(flatBufferBuilder, i27);
        addMinEffectiveVideoPlayProgress(flatBufferBuilder, i26);
        addCustomExposureType(flatBufferBuilder, i25);
        addIsPreload(flatBufferBuilder, i24);
        addUniqueId(flatBufferBuilder, i23);
        addLandpgDlInteractionCfg(flatBufferBuilder, i22);
        addOm(flatBufferBuilder, i21);
        addUserId(flatBufferBuilder, i20);
        addCustomData(flatBufferBuilder, i19);
        addAppCountry(flatBufferBuilder, i18);
        addAppLanguage(flatBufferBuilder, i17);
        addFileCachePriority(flatBufferBuilder, i16);
        addAdChoiceIcon(flatBufferBuilder, i15);
        addAdChoiceUrl(flatBufferBuilder, i14);
        addWhyThisAd(flatBufferBuilder, i13);
        addRequestType(flatBufferBuilder, i12);
        addRequestId(flatBufferBuilder, i11);
        addAdType(flatBufferBuilder, i10);
        addIntent(flatBufferBuilder, i9);
        addLabel(flatBufferBuilder, i8);
        addTaskId(flatBufferBuilder, i7);
        addOriginCreativeType(flatBufferBuilder, i6);
        addCreativeType(flatBufferBuilder, i5);
        addAdSign(flatBufferBuilder, i4);
        addCta(flatBufferBuilder, i3);
        addContentId(flatBufferBuilder, i2);
        addShowId(flatBufferBuilder, i);
        addHasReportInteractiveImp(flatBufferBuilder, z9);
        addTransparencySwitch(flatBufferBuilder, z8);
        addEncryptInContentRrd(flatBufferBuilder, z7);
        addIsShowStartReported(flatBufferBuilder, z6);
        addDirectReturnVideoAd(flatBufferBuilder, z5);
        addHasReportShowEventDuringShowTime(flatBufferBuilder, z4);
        addShown(flatBufferBuilder, z3);
        addClicked(flatBufferBuilder, z2);
        addIsVastAd(flatBufferBuilder, z);
        return endNativeAdFb(flatBufferBuilder);
    }

    public static int createMonitorsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createInvalidcontentidsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createImageInfosVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createFeedbackInfoListVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createExtVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createContentExtsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createComplianceVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createClickActionListVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createAssetsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createAdSourcesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createAdCloseKeyWordsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createAdCloseKeyWordsTypeVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void addWorkingKey(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(84, i, 0);
    }

    public static void addWhyThisAd(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(14, i, 0);
    }

    public static void addWebConfig(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(58, i, 0);
    }

    public static void addVideoInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(48, i, 0);
    }

    public static void addUserId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(21, i, 0);
    }

    public static void addUseGaussianBlur(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(73, i, 0);
    }

    public static void addUniqueId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(24, i, 0);
    }

    public static void addTransparencyTplUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(92, i, 0);
    }

    public static void addTransparencySwitch(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(91, z, false);
    }

    public static void addTitle(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(38, i, 0);
    }

    public static void addTemplateUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(96, i, 0);
    }

    public static void addTemplateStyle(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(80, i, 0);
    }

    public static void addTemplateId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(77, i, 0);
    }

    public static void addTemplateData(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(79, i, 0);
    }

    public static void addTaskinfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(63, i, 0);
    }

    public static void addTaskId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static void addSubDescription(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(99, i, 0);
    }

    public static void addStyleExt(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(102, i, 0);
    }

    public static void addStyle(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(101, i, 0);
    }

    public static void addStartTime(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(9, j, 0L);
    }

    public static void addStartShowtime(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(26, j, 0L);
    }

    public static void addSplashMediaPath(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(70, i, 0);
    }

    public static void addSlotId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(61, i, 0);
    }

    public static void addShown(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(57, z, false);
    }

    public static void addShowLandingPageTitleFlag(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(55, i, 0);
    }

    public static void addShowId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addShowAppElement(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(90, i, 0);
    }

    public static void addSdkMonitor(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(103, i, 0);
    }

    public static void addRequestType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(13, i, 0);
    }

    public static void addRequestId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(12, i, 0);
    }

    public static void addRecallSource(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(95, i, 0);
    }

    public static void addRawImageInfos(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(42, i, 0);
    }

    public static void addPromoteInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(94, i, 0);
    }

    public static void addOriginWhiteList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(87, i, 0);
    }

    public static void addOriginParamFromServer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(85, i, 0);
    }

    public static void addOriginJssdkAllowList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(88, i, 0);
    }

    public static void addOriginCreativeType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(6, i, 0);
    }

    public static void addOm(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(22, i, 0);
    }

    public static void addNoReportEventList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(60, i, 0);
    }

    public static void addMonitors(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(86, i, 0);
    }

    public static void addMinEffectiveVideoPlayProgress(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(28, i, 0);
    }

    public static void addMinEffectiveShowTime(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(44, j, 0L);
    }

    public static void addMinEffectiveShowRatio(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(45, i, 0);
    }

    public static void addMetaData(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(52, i, 0);
    }

    public static void addMarketAppId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(47, i, 0);
    }

    public static void addLogo2Text(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(31, i, 0);
    }

    public static void addLandpgDlInteractionCfg(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(23, i, 0);
    }

    public static void addLandingPageType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(67, i, 0);
    }

    public static void addLandWebUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(43, i, 0);
    }

    public static void addLabel(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static void addIsVastAd(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(29, z, false);
    }

    public static void addIsShowStartReported(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(69, z, false);
    }

    public static void addIsPreload(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(25, i, 0);
    }

    public static void addInvalidcontentids(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(68, i, 0);
    }

    public static void addInteractiontype(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(36, i, 0);
    }

    public static void addIntent(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(10, i, 0);
    }

    public static void addImageInfos(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(41, i, 0);
    }

    public static void addIcon(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(40, i, 0);
    }

    public static void addHwChannelId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(33, i, 0);
    }

    public static void addHasReportShowEventDuringShowTime(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(62, z, false);
    }

    public static void addHasReportInteractiveImp(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(98, z, false);
    }

    public static void addFileCachePriority(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(17, i, 0);
    }

    public static void addFeedbackInfoList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(76, i, 0);
    }

    public static void addExt(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(74, i, 0);
    }

    public static void addEndTime(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(2, j, 0L);
    }

    public static void addEncryptInContentRrd(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(83, z, true);
    }

    public static void addEncodedeMonitors(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(53, i, 0);
    }

    public static void addEncodedParamFromServer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(37, i, 0);
    }

    public static void addEncodeWhiteList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(64, i, 0);
    }

    public static void addEncodeJssdkAllowList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(65, i, 0);
    }

    public static void addDirectReturnVideoAd(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(66, z, false);
    }

    public static void addDescription(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(39, i, 0);
    }

    public static void addDefaultTemplate(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(82, i, 0);
    }

    public static void addCustomExposureType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(27, i, 0);
    }

    public static void addCustomData(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(20, i, 0);
    }

    public static void addCtrlSwitchs(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(59, i, 0);
    }

    public static void addCtrlExt(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(89, i, 0);
    }

    public static void addCta(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addCshareUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(97, i, 0);
    }

    public static void addCreativeType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(5, i, 0);
    }

    public static void addContentId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addContentExts(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(75, i, 0);
    }

    public static void addCompliance(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(34, i, 0);
    }

    public static void addClicked(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(35, z, false);
    }

    public static void addClickActionList(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(56, i, 0);
    }

    public static void addBiddingInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(100, i, 0);
    }

    public static void addBannerRefSetting(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(72, i, 0);
    }

    public static void addAudioInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(54, i, 0);
    }

    public static void addAssets(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(78, i, 0);
    }

    public static void addAppPromotionChannel(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(46, i, 0);
    }

    public static void addAppLanguage(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(18, i, 0);
    }

    public static void addAppInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(51, i, 0);
    }

    public static void addAppCountry(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(19, i, 0);
    }

    public static void addApiVer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(81, i, 0);
    }

    public static void addApiRecordStartTime(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(71, j, 0L);
    }

    public static void addAdType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(11, i, 0);
    }

    public static void addAdSources(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(30, i, 0);
    }

    public static void addAdSign(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addAdRealCallerPkgName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(93, i, 0);
    }

    public static void addAdCloseKeyWordsType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(50, i, 0);
    }

    public static void addAdCloseKeyWords(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(49, i, 0);
    }

    public static void addAdChoiceUrl(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(15, i, 0);
    }

    public static final class Vector extends BaseVector {
        public NativeAdFb get(NativeAdFb nativeAdFb, int i) {
            return nativeAdFb.__assign(NativeAdFb.__indirect(__element(i), this.bb), this.bb);
        }

        public NativeAdFb get(int i) {
            return get(new NativeAdFb(), i);
        }

        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public static void addAdChoiceIcon(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(16, i, 0);
    }

    public static void addAbilityDetailInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(32, i, 0);
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_23_5_26();
    }
}
