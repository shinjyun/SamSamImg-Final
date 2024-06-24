package com.samsam.img.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Img {
	@Id
	private Integer img_number;
	private String img_upload;
	private String img_update;
	private String img_url;
}
