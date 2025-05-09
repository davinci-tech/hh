package com.huawei.hiai.awareness.client;

import android.app.PendingIntent;

/* loaded from: classes4.dex */
public class AwarenessRequest {
    public static final String MESSAGE_TYPE = "context_awareness_request";
    private AwarenessEnvelope envelope;

    private AwarenessRequest() {
    }

    public static AwarenessRequest checkFence(AwarenessFence awarenessFence) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.CHECK_FENCE).putArg(AwarenessFence.MESSAGE_TYPE, awarenessFence);
        return awarenessRequest;
    }

    @Deprecated
    public static AwarenessRequest registerFence(AwarenessFence awarenessFence, PendingIntent pendingIntent) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.REGISTER_FENCE_BY_PENDING_INTENT).putArg(AwarenessFence.MESSAGE_TYPE, awarenessFence).putArg(Field.PENDING_OPERATION, pendingIntent);
        return awarenessRequest;
    }

    public static AwarenessRequest registerFence(AwarenessFence awarenessFence, OnEnvelopeReceiver onEnvelopeReceiver) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.REGISTER_FENCE_BY_LISTENER).putArg(AwarenessFence.MESSAGE_TYPE, awarenessFence).putArg(Field.ON_ENVELOPE_RECEIVER, onEnvelopeReceiver.asBinder());
        return awarenessRequest;
    }

    public static AwarenessRequest registerFence(AwarenessFence awarenessFence, String str) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.REGISTER_FENCE_BY_SERVICE).putArg(AwarenessFence.MESSAGE_TYPE, awarenessFence).putArg(Field.NOTIFY_SERVICE_NAME, str);
        return awarenessRequest;
    }

    @Deprecated
    public static AwarenessRequest unregisterFence(AwarenessFence awarenessFence) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.UNREGISTER_FENCE).putArg(AwarenessFence.MESSAGE_TYPE, awarenessFence);
        return awarenessRequest;
    }

    public static AwarenessRequest unregisterFence(String str) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.UNREGISTER_FENCE_WITH_ID).putArg(Field.FENCE_ID, str);
        return awarenessRequest;
    }

    public static AwarenessRequest getSnapshot(AwarenessSnapshot awarenessSnapshot, OnEnvelopeReceiver onEnvelopeReceiver) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.GET_SNAPSHOT).putArg(AwarenessSnapshot.MESSAGE_TYPE, awarenessSnapshot).putArg(Field.ON_ENVELOPE_RECEIVER, onEnvelopeReceiver.asBinder());
        return awarenessRequest;
    }

    public static AwarenessRequest getProfileLabel(ProfileLabel profileLabel, OnEnvelopeReceiver onEnvelopeReceiver) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.GET_PROFILE_LABEL).putArg(ProfileLabel.MESSAGE_TYPE, profileLabel).putArg(Field.ON_ENVELOPE_RECEIVER, onEnvelopeReceiver.asBinder());
        return awarenessRequest;
    }

    public static AwarenessRequest buildQuery(String str, QueryObject queryObject, OnEnvelopeReceiver onEnvelopeReceiver) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(str).putArg(Field.QUERY_OBJECT, queryObject).putArg(Field.ON_ENVELOPE_RECEIVER, onEnvelopeReceiver.asBinder());
        return awarenessRequest;
    }

    public static AwarenessRequest disconnect() {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.DISCONNECT);
        return awarenessRequest;
    }

    public static AwarenessRequest executeCommand(AwarenessEnvelope awarenessEnvelope) {
        AwarenessRequest awarenessRequest = new AwarenessRequest();
        awarenessRequest.envelope = AwarenessEnvelope.create(MessageType.EXECUTE_COMMAND).putArg(Field.COMMAND, awarenessEnvelope);
        return awarenessRequest;
    }

    public AwarenessRequest addOnResultListener(OnResultListener onResultListener) {
        this.envelope.putArg(Field.ON_RESULT_LISTENER, onResultListener.asBinder());
        return this;
    }

    public AwarenessRequest setPackageName(String str) {
        this.envelope.putArg("package_name", str);
        return this;
    }

    public AwarenessRequest setServiceName(String str) {
        this.envelope.putArg("service_name", str);
        return this;
    }

    public String getMessageType() {
        return this.envelope.getMessageType();
    }

    public AwarenessEnvelope toEnvelope() {
        return this.envelope;
    }

    /* loaded from: classes8.dex */
    public class MessageType {
        public static final String CHECK_FENCE = "check_fence";
        public static final String DISABLE_CAPTURE_TASK = "disable_capture_task";
        public static final String DISCONNECT = "disconnect";
        public static final String ENABLE_CAPTURE_TASK = "enable_capture_task";
        public static final String EXECUTE_COMMAND = "execute_command";
        public static final String GET_PROFILE_LABEL = "get_profile_label";
        public static final String GET_SNAPSHOT = "get_snapshot";
        public static final String REGISTER_FENCE_BY_DATA_ABILITY = "register_fence_by_data_ability";
        public static final String REGISTER_FENCE_BY_LISTENER = "register_fence_by_listener";
        public static final String REGISTER_FENCE_BY_PENDING_INTENT = "register_fence_by_pending_intent";
        public static final String REGISTER_FENCE_BY_SERVICE = "register_fence_by_service";
        public static final String SHORT_TERM_MEMORY_MSG = "short_term_memory";
        public static final String UNREGISTER_FENCE = "unregister_fence";
        public static final String UNREGISTER_FENCE_WITH_ID = "unregister_fence_with_id";

        private MessageType() {
        }
    }

    /* loaded from: classes8.dex */
    public class Field {
        public static final String COMMAND = "command";
        public static final String FENCE_ID = "fence_id";
        public static final String NOTIFY_PROVIDER_NAME = "notify_provider_name";
        public static final String NOTIFY_SERVICE_NAME = "notify_service_name";
        public static final String ON_ENVELOPE_RECEIVER = "on_envelope_receiver";
        public static final String ON_RESULT_LISTENER = "on_result_listener";
        public static final String PACKAGE_NAME = "package_name";
        public static final String PENDING_OPERATION = "pending_operation";
        public static final String QUERY_OBJECT = "query_object";
        public static final String SERVICE_NAME = "service_name";

        private Field() {
        }
    }
}
