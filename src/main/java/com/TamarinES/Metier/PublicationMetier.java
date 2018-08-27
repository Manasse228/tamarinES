package com.TamarinES.Metier;

import java.util.List;

import com.TamarinES.Entity.PublicationDto;


public interface PublicationMetier {

	public List<PublicationDto> getPubs();

	public PublicationDto getPubById(int pubId);

	public List<PublicationDto> getPubsLast();
	
	public List<PublicationDto> getPubsByUser(Integer userId);

	public List<PublicationDto> getPubsByParams(PublicationDto publicationDto);

}
