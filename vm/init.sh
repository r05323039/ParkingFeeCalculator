# 確保環境變數被替換到設定檔中，容器內直接寫絕對路徑，不需./
envsubst < /rootfs/setting.yml > /rootfs/application.yml

# 啟動 Spring Boot 應用
java -jar /app/parking-web-1.0.0.jar --spring.config.location=/rootfs/application.yml