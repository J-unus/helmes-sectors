# Helmes sectors

### Running the application locally
#### IntelliJ IDEA

* Install the **JDK17** as project SDK and Gradle JVM(Settings>Build..>Gradle>Gradle JVM)
* Install the **Lombok plugin** in **File | Settings | Plugins | Browse repositories...**
* Enable **Annotation processing** in **File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors**
* To run the project tap `ctrl` twice and type `gradle bootRun` in the 'Run Anything' window
* Web content available at `http://localhost:9100`

### H2 console
##### Database console available at `http://localhost:9100/h2-console` with login -
* Generic H2 (Embedded)
* org.h2.Driver
* JDBC URL: jdbc:h2:mem:testdb
* User Name: sa
* password empty