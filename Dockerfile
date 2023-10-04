FROM arm64v8/openjdk
COPY target/ecommerce-*.jar /ecommerce.jar
CMD ["java","-jar","ecommerce.jar"]