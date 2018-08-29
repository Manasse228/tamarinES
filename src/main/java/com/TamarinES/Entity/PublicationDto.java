package com.TamarinES.Entity;

import java.util.Date;

public class PublicationDto {
	
	private Integer idPub;
	private Date departureDate;
	private Date arrivalDate;
	private String departureTown;
	private String arrivalTown;
	private String typePacket;
	private Boolean localAction;
	private Float price;
	private Date publicationDate;
	private Boolean divWeight;
	private Float weight;
	private String typePublication;
	private Float minPrice;
	private Float maxPrice;
	private Float totalPrice;
	private String statutPublication;
	
	private Integer idUser;
	private String pseudo;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private Date birthDay;
	private String email;
	private String photo;
	private Boolean active;
	private Integer note;
	private Boolean verif;
	
	public PublicationDto() {
		super();
	}

	public Integer getIdPub() {
		return idPub;
	}
	
	public void setIdPub(Integer idPub) {
		this.idPub = idPub;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public String getDepartureTown() {
		return departureTown;
	}
	
	public void setDepartureTown(String departureTown) {
		this.departureTown = departureTown;
	}
	
	public String getArrivalTown() {
		return arrivalTown;
	}
	
	public void setArrivalTown(String arrivalTown) {
		this.arrivalTown = arrivalTown;
	}
	
	public String getTypePacket() {
		return typePacket;
	}
	
	public void setTypePacket(String typePacket) {
		this.typePacket = typePacket;
	}
	
	public Boolean getLocalAction() {
		return localAction;
	}
	
	public void setLocalAction(Boolean localAction) {
		this.localAction = localAction;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Date getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public Boolean getDivWeight() {
		return divWeight;
	}
	
	public void setDivWeight(Boolean divWeight) {
		this.divWeight = divWeight;
	}
	
	public Float getWeight() {
		return weight;
	}
	
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public String getTypePublication() {
		return typePublication;
	}
	
	public void setTypePublication(String typePublication) {
		this.typePublication = typePublication;
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
	
	public Float getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getStatutPublication() {
		return statutPublication;
	}
	
	public void setStatutPublication(String statutPublication) {
		this.statutPublication = statutPublication;
	}
	
	public Integer getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}
	
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Integer getNote() {
		return note;
	}
	
	public void setNote(Integer note) {
		this.note = note;
	}
	
	public Boolean getVerif() {
		return verif;
	}
	
	public void setVerif(Boolean verif) {
		this.verif = verif;
	}

	@Override
	public String toString() {
		return "PublicationDto [idPub=" + idPub + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate
				+ ", departureTown=" + departureTown + ", arrivalTown=" + arrivalTown + ", typePacket=" + typePacket
				+ ", localAction=" + localAction + ", price=" + price + ", publicationDate=" + publicationDate
				+ ", divWeight=" + divWeight + ", weight=" + weight + ", typePublication=" + typePublication
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", totalPrice=" + totalPrice
				+ ", statutPublication=" + statutPublication + ", idUser=" + idUser + ", pseudo=" + pseudo
				+ ", lastName=" + lastName + ", firstName=" + firstName + ", phoneNumber=" + phoneNumber + ", birthDay="
				+ birthDay + ", email=" + email + ", photo=" + photo + ", active=" + active + ", note=" + note
				+ ", verif=" + verif + "]";
	}
	

}
