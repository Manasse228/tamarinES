package com.TamarinES.Metier;

import java.util.List;

import com.TamarinES.Entity.Publication;


public interface PublicationMetier {

	public List<Publication> getPubs();

	public Publication getPubById(int pubId);

	public List<Publication> getPubsLast();
	
	public List<Publication> getPubsByUser(Integer userId);

	public List<Publication> getPubsByParams(Publication publication);

}
