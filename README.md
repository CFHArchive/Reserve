# Reserve

A common API to let plugin developers integrate with economy plugins. Permissions support coming soon.


# Maven

If order to include Reserve into your project via Maven use the following repository & dependency lines in your POM file.

```xml
<repositories>
         <repository>
             <id>reserve-repo</id>
             <url>https://dl.bintray.com/theneweconomy/java/</url>
         </repository>
 </repositories>
 <dependencies>
      <dependency>
             <groupId>net.tnemc</groupId>
             <artifactId>Reserve</artifactId>
             <version>0.1.0.1</version>
             <scope>provided</scope>
       </dependency>
 </dependencies>
 `