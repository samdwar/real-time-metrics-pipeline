# real-time-metrics-pipeline POC

🚀 A complete real-time observability stack built with:

- **Spring Boot** – emits mock service logs every few seconds.
- **Fluent Bit** – collects and forwards logs.
- **Kafka (KRaft)** – acts as the transport buffer.
- **ClickHouse** – stores structured metrics data.
- **Grafana** – visualizes logs and metrics with custom dashboards.

---

## 📦 Components

| Component     | Role                                  | Port |
|---------------|----------------------------------------|------|
| Spring Boot   | Emits JSON logs                        | 8080 |
| Fluent Bit    | Parses and forwards logs               | —    |
| Kafka (KRaft) | Transport layer (no Zookeeper needed)  | 9092 |
| ClickHouse    | Fast OLAP database                     | 8123 |
| Grafana       | Dashboard for real-time metrics        | 3000 |

---

## 🔧 Setup

1. Clone the repo:
   ```bash
   git clone git@github.com:samdwar/real-time-metrics-pipeline.git
   cd real-time-metrics-pipeline
   ```

2. Run the entire stack:
   ```bash
   docker-compose up --build
   ```

3. Access services:
   - Grafana: http://localhost:3000
   - ClickHouse: http://localhost:8123
   - Spring Boot App: emits logs automatically

---

## 📊 Grafana Dashboard (Recommended Panels)

1. **Total Requests Over Time**
2. **Requests by Region**
3. **Status Code Distribution**
4. **Avg Response Time**
5. **Top Services**

---

## 📁 Directory Structure

```
real-time-metrics-pipeline/
├── docker-compose.yml
├── fluent-bit/
│   └── fluent-bit.conf
├── spring-boot-log-emitter/
│   └── (Java sources)
├── grafana/
│   └── dashboard.json
└── README.md
```

---

## 📅 Timestamp Format

All log timestamps must follow:
```
"yyyy-MM-dd HH:mm:ss"
```

Fluent Bit parser must use:
```ini
Time_Key    timestamp
Time_Format %Y-%m-%d %H:%M:%S
```

---

## 🧪 Example Log (JSON)

```json
{
  "timestamp": "2025-07-06 18:51:17",
  "service": "orders-api",
  "status": 200,
  "region": "us-west-1",
  "response_time_ms": 145,
  "user_id": "user-123"
}
```

---

## 📌 Notes

- This project uses Kafka KRaft (no Zookeeper)
- Spring Boot logs are written to stdout
- Fluent Bit reads via tail or forward input
- ClickHouse table: `service_logs`
<img width="1448" alt="image" src="https://github.com/user-attachments/assets/3cf70378-1a8f-46fb-9b3b-0cea83ad4d23" />

Grafana Dashboard
<img width="1505" alt="image" src="https://github.com/user-attachments/assets/0d1158e7-bb6d-4e74-b62c-bde5ecf56470" />

---

## 🛡 Security & Monitoring (Optional)

- Add authentication to Grafana/ClickHouse
- Monitor Kafka and Fluent Bit with Prometheus

---

## 📄 License

MIT
