package com.operative.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public abstract class MongoEntity {
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonProperty(value = "lastModifiedDate")
	private LocalDateTime lastUpdatedDate;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdDate;

	public abstract String getId();

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
