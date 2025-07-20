
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar o arquivo pom.xml e baixar dependências
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copiar o código fonte
COPY src ./src

# Construir a aplicação
RUN ./mvnw clean package -DskipTests

# Expor a porta (se necessário para futuras extensões)
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "target/catalogo-livros-0.0.1-SNAPSHOT.jar"]