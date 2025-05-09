package com.huawei.featureuserprofile.healthrecord.hicloud;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.framework.network.restclient.hwhttp.trans.MultipartBody;
import com.huawei.hms.network.restclient.anno.Body;
import com.huawei.hms.network.restclient.anno.DELETE;
import com.huawei.hms.network.restclient.anno.GET;
import com.huawei.hms.network.restclient.anno.HeaderMap;
import com.huawei.hms.network.restclient.anno.POST;
import com.huawei.hms.network.restclient.anno.Url;
import defpackage.brn;
import java.util.Map;

/* loaded from: classes7.dex */
public interface HiCloudApi {
    @DELETE
    Submit<String> deleteFile(@Url String str, @HeaderMap Map<String, String> map);

    @GET
    Submit<ResponseBody> downloadFile(@Url String str, @HeaderMap Map<String, String> map);

    @GET
    Submit<ResponseBody> downloadImage(@Url String str, @HeaderMap Map<String, String> map);

    @POST
    Submit<brn> uploadFileByName(@Url String str, @HeaderMap Map<String, String> map, @Body MultipartBody multipartBody);
}
