package com.TamarinES.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "doc_publication", type = "publication")
public class Publication {

	@Id
	private Integer idpub;
	private Date departure_date;
	private Date arrival_date;
	private String departure_town;
	private String arrival_town;
	private String type_packet;
	private Boolean local_action;
	private Float price;
	private Date publication_date;
	private Boolean div_weight;
	private Float weight;
	private String type_publication;
	private Integer user;
	private Float minPrice;
	private Float maxPrice;

	private Integer user_id;
	private Boolean active;
	private Date birth_day;
	private String email;
	private String first_name;
	private String last_name;
	private Integer note;
	private String phone_number;
	private String pseudo;
	private String photo;
	private Boolean verif;

	public Publication() {
		super();
	}

	public Integer getIdpub() {
		return idpub;
	}

	public void setIdpub(Integer idpub) {
		this.idpub = idpub;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public Date getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getDeparture_town() {
		return departure_town;
	}

	public void setDeparture_town(String departure_town) {
		this.departure_town = departure_town;
	}

	public String getArrival_town() {
		return arrival_town;
	}

	public void setArrival_town(String arrival_town) {
		this.arrival_town = arrival_town;
	}

	public String getType_packet() {
		return type_packet;
	}

	public void setType_packet(String type_packet) {
		this.type_packet = type_packet;
	}

	//local_action getDiv_weight
	public Boolean getLocal_action() {
		return local_action;
	}

	public void setLocal_action(Boolean local_action) {
		this.local_action = local_action;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}

	public Boolean getDiv_weight() {
		return div_weight;
	}

	public void setDiv_weight(Boolean div_weight) {
		this.div_weight = div_weight;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getType_publication() {
		return type_publication;
	}

	public void setType_publication(String type_publication) {
		this.type_publication = type_publication;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getBirth_day() {
		return birth_day;
	}

	public void setBirth_day(Date birth_day) {
		this.birth_day = birth_day;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getVerif() {
		return verif;
	}

	public void setVerif(Boolean verif) {
		this.verif = verif;
	}

	@Override
	public String toString() {
		return "Publication [idpub=" + idpub + ", departure_date=" + departure_date + ", arrival_date=" + arrival_date
				+ ", departure_town=" + departure_town + ", arrival_town=" + arrival_town + ", type_packet="
				+ type_packet + ", local_action=" + local_action + ", price=" + price + ", publication_date="
				+ publication_date + ", div_weight=" + div_weight + ", weight=" + weight + ", type_publication="
				+ type_publication + ", user=" + user + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", user_id=" + user_id + ", active=" + active + ", birth_day=" + birth_day + ", email=" + email
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", note=" + note + ", phone_number="
				+ phone_number + ", pseudo=" + pseudo + ", photo=" + photo + ", verif=" + verif + "]";
	}

	
	
}
