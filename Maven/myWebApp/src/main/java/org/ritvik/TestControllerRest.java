package org.ritvik;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TestControllerRest")
public class TestControllerRest {

	@Autowired
	private ApplicationContext appContext;

	private Logger logger = Logger.getLogger(TestControllerRest.class);

	@RequestMapping(value = "/Employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Employee>> getEmployee() {
		logger.info("TestControllerRest getEmployee called...");

		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee(1, "A"));
		list.add(new Employee(2, "B"));
		list.add(new Employee(3, "C"));

		logger.info("Employee List is : " + list.toString());

		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}
}
