# Springboot External Configuration
This is sample project for understanding external configuration of springboot.

## Annotations

### @Value
This is used to access properties configured in application.yml file.
If your application.yml file contains property like,
```
custom.properties.app.name: SpringbootExternalConfiguration
```
Then you can access this property in your component as,
```java
	@Value("${custom.properties.app.name}")
	private String applicationName;
```

## Application Properties

### Configuring random properties

```
my.secret: ${random.value}
my.number: ${random.int}
my.bignumber: ${random.long}
my.uuid: ${random.uuid}
my.number.less.than.ten: ${random.int(10)}
my.number.in.range: ${random.int[1,5]}
```

## Commandline arguments

### Passingg property from commandline
```
java -jar springboot-external-configuration-0.0.1-SNAPSHOT.jar --custom.properties.app.name="Something"
```
This commandline property will override your application.yml property.
We can avoid setting from command line using
```
SpringApplication.setAddCommandLineProperties(false)
```

### Property loading order
[Check here](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files)

### Changing application.yml/properties name
You can change this name and provide new name from commandline.

```
java -jar springboot-external-configuration-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:/custom.yml
```

### Profile based properties
Profile based properties can be configured using application-{profile-name}.yml. Like application-dev.yml.


### Placeholders in properties
Placeholder can be used as below.

```
prefix: Prefixxxxxx
postfix: Postfixxxxxxx
appended.property: ${prefix} is prefix and ${postfix} is postfix.
```

### Reading list from yml

Consider yml file is like

```
server:
  names:
    - server1
    - server2
```
Then we can read these properties like,

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
public class PropertyConfiguration {
	List<String> names = new ArrayList<String>();

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

}
```
Note that getter and setter methods are required.

### Multiprofile yml
We can handle multiple profiles in single yml file.
[Check here](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-multi-profile-yaml)

### Reading type safe properties
[Check here](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-typesafe-configuration-properties)

### Relaxed property binding
[Check here](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-relaxed-binding)

### Reading map from yml
Consider yml file

```
mapexample:
  map: 
    "[name1]": "Brijesh"
    "[name2]": "Rajesh"
    "[name3]": "Paresh" 
```
Can be accessed using below java code.

```java
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mapexample")
public class PropertyToMap {
	Map<String, String> map = new HashMap<String, String>();

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
```

### Mapping complex types
[Check here](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-complex-type-merge)

### @ConfigurationProperty vs @Value
[Check here](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-vs-value)

