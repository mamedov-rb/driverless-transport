CREATE TABLE message
(
    sender_id   uuid,
    chat_id     uuid,
    created_at  timestamp,
    payload    text,
    PRIMARY KEY ((sender_id, chat_id), created_at)
) WITH CLUSTERING ORDER BY (created_at ASC);