package br.com.clickbus.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "places", indexes = {@Index(name = "places_index", columnList = "name")})
@Getter
@Setter
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String city;
	@NotNull
	private String slug;
	@NotNull
	private String state;
	@ColumnDefault("NOW()")
	@NotNull
	@Column(insertable = false, updatable = false)
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
