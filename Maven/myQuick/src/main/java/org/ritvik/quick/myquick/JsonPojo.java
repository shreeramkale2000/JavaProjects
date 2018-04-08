package org.ritvik.quick.myquick;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "LastName", "FirstName" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPojo {
	@JsonProperty(value = "FirstName")
	private String firstName;

	@JsonProperty(value = "LastName")
	private String lastName;

	@JsonIgnore
	private String title;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
