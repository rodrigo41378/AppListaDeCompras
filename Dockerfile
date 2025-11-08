# ===== Build Stage =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o arquivo de configuração do Maven e baixa dependências
COPY pom.xml .
RUN mvn -q -B dependency:go-offline
# Copia o código-fonte e compila o projeto
COPY src ./src
RUN mvn -q -B package -DskipTests
# ===== Run Stage =====
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copia o artefato gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Expõe a porta padrão da aplicação
EXPOSE 8080
# Comando de inicialização
ENTRYPOINT ["java", "-jar", "/app/app.jar"]