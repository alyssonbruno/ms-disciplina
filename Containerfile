# ── Estágio 1: compilar com Maven ──────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app

# Baixa dependências primeiro (aproveita o cache das camadas)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código e compila (sem rodar testes)
COPY src ./src
RUN mvn package -DskipTests -B

# ── Estágio 2: imagem final só com o JRE ───────────────────────────────────
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
