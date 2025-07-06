
#!/bin/bash
mkdir -p /app/logs
while true; do
  TIMESTAMP=$(date -Iseconds)
  STATUS=$((RANDOM % 2 == 0 ? 200 : 500))
  REGION=$(shuf -n 1 -e us-east eu-west ap-south)
  RT=$((RANDOM % 1000))
  USER_ID="user-$((RANDOM % 1000))"
  echo "{\"timestamp\":\"$TIMESTAMP\",\"service\":\"orders-api\",\"status\":$STATUS,\"region\":\"$REGION\",\"response_time_ms\":$RT,\"user_id\":\"$USER_ID\"}" >> /app/logs/app.log
  sleep 5
done
