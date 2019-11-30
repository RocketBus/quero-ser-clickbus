package br.com.clickbus.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
	@Column(insertable = false, updatable = false)
	private int id;
	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;
	@NotNull
	private String name;
	@NotNull
	private String slug;
	@ColumnDefault("NOW()")
	@NotNull
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@Column(insertable = false)
	private LocalDateTime updatedAt;
	
    @PrePersist
    protected void setCreated() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void setUpdated() {
        this.updatedAt = LocalDateTime.now();
    }
	
}
