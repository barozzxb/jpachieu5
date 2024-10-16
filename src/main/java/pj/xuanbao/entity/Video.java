package pj.xuanbao.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Automatic get-setter and constructor
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VideoId")
	private int videoId;

	@Column(name="Active")
	private int active;
	
	@Column(name="Description", columnDefinition = "NVARCHAR(500) NULL")
	private String description;

	@Column(name="Poster")
	private String poster;

	@Column(name="Title", columnDefinition = "NVARCHAR(500)")
	private String title;

	@Column(name="Views")
	private int views;
	
	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;
}
