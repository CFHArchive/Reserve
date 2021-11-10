# Reserve

The goal of the Reserve project is to provide a common set of APIs to allow for
painless compatibility between various spigot plugins.

# APIs Available
- Economy

# Future APIs
- Ban
- Permissions
- Land Protection
- Minigame

# Maven

If order to include Reserve into your project via Maven use the following repository & dependency lines in your POM file.

```xml
<repositories>
         <repository>
             <id>reserve-repo</id>
             <url>https://repo.codemc.io/repository/maven-public/</url>
         </repository>
 </repositories>
 <dependencies>
      <dependency>
            <groupId>net.tnemc</groupId>
            <artifactId>Reserve</artifactId>
            <version>0.1.5.3-SNAPSHOT-4</version>
            <scope>provided</scope>
      </dependency>
 </dependencies>
 `
