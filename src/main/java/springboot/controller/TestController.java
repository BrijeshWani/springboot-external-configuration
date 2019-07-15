package springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.property_config.PropertyConfiguration;
import springboot.property_config.PropertyToMap;

@RestController
@RequestMapping("/external-config")
public class TestController {

	@Value("${custom.properties.app.name}")
	private String applicationName;

	@Value("${my.secret}")
	private String secret;

	@Value("${my.number}")
	private int randomNumber;

	@Value("${my.bignumber}")
	private long bigNumber;

	@Value("${my.uuid}")
	private String uuid;

	@Value("${my.number.less.than.ten}")
	private int numberLtTen;

	@Value("${my.number.in.range}")
	private int numberInRange;

	@Value("${appended.property}")
	private String appendedProperty;

	@Autowired
	private PropertyConfiguration propertyConfiguration;

	@Autowired
	private PropertyToMap propertyToMap;

	@GetMapping("/get-name")
	public @ResponseBody ResponseEntity<String> test() {
		return ResponseEntity.status(HttpStatus.OK).body(applicationName);
	}

	@GetMapping("/get-appended-property")
	public @ResponseBody ResponseEntity<String> getAppendedProperty() {
		return ResponseEntity.status(HttpStatus.OK).body(appendedProperty);
	}

	@GetMapping("/get-property-list")
	public @ResponseBody ResponseEntity<List<String>> getPropertyList() {
		return ResponseEntity.status(HttpStatus.OK).body(propertyConfiguration.getNames());
	}

	@GetMapping("/get-property-map")
	public @ResponseBody ResponseEntity<Map<String, String>> getPropertyMap() {
		return ResponseEntity.status(HttpStatus.OK).body(propertyToMap.getMap());
	}

	@GetMapping("/get-random-values")
	public @ResponseBody ResponseEntity<Map<String, Object>> getRandomValues() {
		Map<String, Object> randomValues = new HashMap<String, Object>();
		randomValues.put("my.secret", secret);
		randomValues.put("my.number", randomNumber);
		randomValues.put("my.bignumber", bigNumber);
		randomValues.put("my.uuid", uuid);
		randomValues.put("my.number.less.than.ten", numberLtTen);
		randomValues.put("my.number.in.range", numberInRange);
		return ResponseEntity.status(HttpStatus.OK).body(randomValues);
	}
}
