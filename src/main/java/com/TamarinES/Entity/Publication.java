package com.TamarinES.Entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
// >bin\logstash.bat -f logstash.conf
@Document(indexName = "doc_publication", type = "publication")
public class Publication {
	
	@Id
	private int idpub;
	
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
	
	public Integer user;
	
	private Float minPrice;
	
	private Float maxPrice;

	public int getIdpub() {
		return idpub;
	}

	public void setIdpub(int idpub) {
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

	@JsonIgnore
	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	@JsonIgnore
	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public String toString() {
		return "Publication [idpub=" + idpub + ", departure_date=" + departure_date + ", arrival_date=" + arrival_date
				+ ", departure_town=" + departure_town + ", arrival_town=" + arrival_town + ", type_packet="
				+ type_packet + ", local_action=" + local_action + ", price=" + price + ", publication_date="
				+ publication_date + ", div_weight=" + div_weight + ", weight=" + weight + ", type_publication="
				+ type_publication + ", user=" + user + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}

}
