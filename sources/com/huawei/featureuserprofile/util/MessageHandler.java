package com.huawei.featureuserprofile.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.featureuserprofile.common.IUserInfomationHandler;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.up.model.UserInfomation;
import defpackage.bvz;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public abstract class MessageHandler extends Handler {
    private static final int MSG_DESTROY = 4;
    private static final int MSG_INIT = 0;
    private static final int MSG_INIT_FINISH = 1;
    private static final int MSG_MODIFY_USERINFO = 2;
    private static final int MSG_MODIFY_USERINFO_FINISH = 3;
    public static final int MSG_USER_BEGIN = 5;
    private static final String TAG = "MessageHandler";
    private AtomicBoolean mBlock;
    private Context mContext;
    private Map<Integer, RunnableEx> mEscapeMsgs;
    private AtomicBoolean mInited;
    private Map<Integer, RunnableEx> mRunningMsgs;
    private IUserInfomationHandler mUserInfomationHandler;

    public MessageHandler(Context context, String str, IUserInfomationHandler iUserInfomationHandler) {
        super(Looper.getMainLooper());
        this.mInited = new AtomicBoolean(false);
        this.mBlock = new AtomicBoolean(false);
        this.mEscapeMsgs = new HashMap();
        this.mRunningMsgs = new HashMap();
        this.mContext = null;
        this.mUserInfomationHandler = null;
        final String str2 = str + TAG;
        this.mContext = context;
        this.mUserInfomationHandler = iUserInfomationHandler;
        addEscapeMsg(0, new RunnableEx() { // from class: com.huawei.featureuserprofile.util.MessageHandler.2
            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                LogUtil.c(str2, "processInit");
                MessageHandler.this.unBlockMsg();
                MessageHandler.this.mUserInfomationHandler.processInit(message);
            }
        });
        addRunningMsg(4, new RunnableEx() { // from class: com.huawei.featureuserprofile.util.MessageHandler.5
            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                MessageHandler.this.mInited.set(false);
                LogUtil.c(str2, "MSG_DESTROY,mInited.set(false)");
                MessageHandler.this.mUserInfomationHandler.processDestroy();
                MessageHandler.this.blockMsg();
            }
        });
        addRunningMsg(1, new RunnableEx() { // from class: com.huawei.featureuserprofile.util.MessageHandler.3
            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                MessageHandler.this.mInited.set(true);
                LogUtil.c(str2, "MSG_INIT_FINISH,mInited.set(true)");
            }
        });
        addRunningMsg(2, new RunnableEx() { // from class: com.huawei.featureuserprofile.util.MessageHandler.4
            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                Map map = (Map) message.obj;
                if ((map.get("userInfo") instanceof UserInfomation) && (map.get(ParamConstants.Param.CALLBACK) instanceof UserInfoMedia.UserInfoWriter.Callback)) {
                    MessageHandler.this.mUserInfomationHandler.processModifyUserInfo(message, (UserInfomation) map.get("userInfo"), (UserInfoMedia.UserInfoWriter.Callback) map.get(ParamConstants.Param.CALLBACK));
                }
            }
        });
        addRunningMsg(3, new RunnableEx() { // from class: com.huawei.featureuserprofile.util.MessageHandler.1
            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                if (((Integer) message.obj).intValue() == 0) {
                    bvz.e(MessageHandler.this.mContext, "com.huawei.bone.action.FITNESS_USERINFO_UPDATED");
                }
            }
        });
    }

    public void init(Context context) {
        LogUtil.c(TAG, "MSG_INIT sendToTarget");
        obtainMessage(0, context).sendToTarget();
    }

    public boolean checkInit() {
        LogUtil.c(TAG, "checkInit,mInited.get:", Boolean.valueOf(this.mInited.get()));
        return this.mInited.get();
    }

    public void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback) {
        HashMap hashMap = new HashMap();
        hashMap.put("userInfo", userInfomation);
        hashMap.put(ParamConstants.Param.CALLBACK, callback);
        obtainMessage(2, hashMap).sendToTarget();
    }

    public void postResult(int i, int i2) {
        LogUtil.a(TAG, "postResult arg what:", Integer.valueOf(i));
        if (i == 0) {
            obtainMessage(1, Integer.valueOf(i2)).sendToTarget();
        } else {
            if (i != 2) {
                return;
            }
            obtainMessage(3, Integer.valueOf(i2)).sendToTarget();
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        synchronized (this) {
            LogUtil.a(TAG, "handleMessage msg handle msg.what:", Integer.valueOf(message.what));
            if (!handleMessageSelf(message)) {
                super.handleMessage(message);
            }
        }
    }

    private boolean handleMessageSelf(Message message) {
        if (this.mEscapeMsgs.keySet().contains(Integer.valueOf(message.what))) {
            return routingEscapeMsg(message);
        }
        if (this.mBlock.get()) {
            return false;
        }
        return routingRunningMsg(message);
    }

    public void addEscapeMsg(int i, RunnableEx runnableEx) {
        synchronized (this) {
            LogUtil.a(TAG, "addEscapeMsg what:", Integer.valueOf(i));
            this.mEscapeMsgs.put(Integer.valueOf(i), runnableEx);
        }
    }

    public void addRunningMsg(int i, RunnableEx runnableEx) {
        synchronized (this) {
            LogUtil.a(TAG, "addRunningMsg what:", Integer.valueOf(i));
            this.mRunningMsgs.put(Integer.valueOf(i), runnableEx);
        }
    }

    public void blockMsg() {
        synchronized (this) {
            LogUtil.a(TAG, "blockMsg");
            this.mBlock.set(true);
        }
    }

    public void unBlockMsg() {
        synchronized (this) {
            LogUtil.a(TAG, "unBlockMsg");
            this.mBlock.set(false);
        }
    }

    private boolean routingEscapeMsg(Message message) {
        RunnableEx runnableEx = this.mEscapeMsgs.get(Integer.valueOf(message.what));
        if (runnableEx != null) {
            runnableEx.run(message);
            return true;
        }
        LogUtil.h(TAG, "routingEscapeMsg fail msg.what:", Integer.valueOf(message.what));
        return false;
    }

    private boolean routingRunningMsg(Message message) {
        RunnableEx runnableEx = this.mRunningMsgs.get(Integer.valueOf(message.what));
        if (runnableEx != null) {
            runnableEx.run(message);
            return true;
        }
        LogUtil.h(TAG, "routingRunningMsg fail:", Integer.valueOf(message.what));
        return false;
    }
}
