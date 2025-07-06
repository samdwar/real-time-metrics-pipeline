
CREATE TABLE IF NOT EXISTS kafka_service_logs (
    timestamp DateTime,
    service String,
    status UInt16,
    region String,
    response_time_ms UInt32,
    user_id String
) ENGINE = Kafka
SETTINGS kafka_broker_list = 'kafka:9092',
         kafka_topic_list = 'service-logs',
         kafka_group_name = 'clickhouse_consumer',
         kafka_format = 'JSONEachRow',
         kafka_num_consumers = 1;

CREATE TABLE IF NOT EXISTS service_logs (
    timestamp DateTime,
    service String,
    status UInt16,
    region String,
    response_time_ms UInt32,
    user_id String
) ENGINE = MergeTree()
ORDER BY (timestamp);

CREATE MATERIALIZED VIEW IF NOT EXISTS service_logs_mv
TO service_logs AS
SELECT * FROM kafka_service_logs;
