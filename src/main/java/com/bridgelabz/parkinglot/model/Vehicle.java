package com.bridgelabz.parkinglot.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Table
@Component
@ToString

public class Vehicle {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="vehicleId")
		private long id;

		@Column(unique = true)
		@NotNull
		private String registrationNo;
		@NotNull
		private String vehicleColor;

		@NotNull
		private Integer slotNo;
		
		
		@ManyToOne
		@JoinColumn(name = "userId")
		private Optional<User> vehicleUser;

	}
