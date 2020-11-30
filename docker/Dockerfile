FROM openjdk:15-jdk-alpine
RUN apk update
RUN apk add curl
RUN apk add jq
RUN wget https://releases.hashicorp.com/vault/1.6.0/vault_1.6.0_linux_amd64.zip -O vault160.zip
RUN unzip vault*.zip
RUN chmod +x vault
RUN mv vault /bin/vault
# token for reading role id
ENV VTOKEN=s.H2xyNhkuPsNFXy7FQ9IQ5jEf
ENV VADDR=http://192.168.100.121:8200
RUN VAULT_ADDR=$VADDR VAULT_TOKEN=$VTOKEN vault read -format=json auth/approle/role/kv-full/role-id | jq -r '.data.role_id' > /role-id
RUN ls /
ARG JAR_FILE=app/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]