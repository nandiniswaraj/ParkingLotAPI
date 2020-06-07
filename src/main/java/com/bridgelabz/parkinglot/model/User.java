package com.bridgelabz.parkinglot.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String firstName;
    private String lastName;
	private String email;
	private String password;
    private String mobileNumber;
    private String role;
    private LocalDateTime regDate;
    @Column(name = "isVerify")
	@NotNull
	private boolean verify;


}
