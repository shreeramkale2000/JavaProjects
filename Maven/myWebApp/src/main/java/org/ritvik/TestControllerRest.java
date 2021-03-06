package org.ritvik;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TestControllerRest")
public class TestControllerRest {

	private Logger logger = Logger.getLogger("org.ritvik.processLog");

	@RequestMapping(value = "/Employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		logger.debug("TestControllerRest getEmployee called...");
		Employee emp = new Employee(id, "Ritvik");

		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
}
