package com.huawei.health.vip.vipinfo;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import defpackage.gps;
import defpackage.gpt;
import java.util.Map;

/* loaded from: classes4.dex */
public interface VipInfoApi {
    @GET
    Submit<gps> getTransferBenefitsList(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<VipInfoRsp> getVipMemberInfo(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<gpt> getVipMemberType(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<VipMessageRsp> getVipMessage(@Url String str, @HeaderMap Map<String, String> map);

    @GET
    Submit<VipMessageListRsp> getVipMessageList(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<VipSharedInvitationListRsp> getVipSharedInvitationList(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @POST
    Submit<VipMessageReadRsp> setVipMessageRead(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);
}
