package com.samsam.img.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Img {
	@Id
	@SequenceGenerator(name = "img_number", sequenceName = "IMGNUMBER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "img_number")
	private Integer img_number;
	private String img_upload;
	private String img_update;
	private String img_url;
	
	
}
