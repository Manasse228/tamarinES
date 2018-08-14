package com.TamarinES.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TamarinES.Entity.Publication;
import com.TamarinES.Metier.PublicationMetier;
import com.TamarinES.Utility.Convertion;



@RestController
public class PublicationRestService {
	
	@Autowired
	private PublicationMetier publicationMetier;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/*
	 * Get all publications
	 */
	@RequestMapping(value = "/pubList", method = RequestMethod.GET)
	public List<Publication> listPubs() {
		return publicationMetier.getPubs();
	}
	
	/*
	 * Get Publication By Identifiant
	 */
	@RequestMapping(value = "/pubById", method = RequestMethod.GET)
	public Publication findById(@RequestParam(value = "idPub", required = false, defaultValue = "") Integer idPub){
		return publicationMetier.getPubById(idPub);
	}
	
	/*
	 * Get last publication publication_date > (now-7)
	 */
	@RequestMapping(value = "/lastPubList", method = RequestMethod.GET)
	public List<Publication> getListLastPub() {
		return publicationMetier.getPubsLast();
	}
	
	/*
	 * Get Publication By User Identifiant
	 */
	@RequestMapping(value = "/pubByUserId", method = RequestMethod.GET)
	public List<Publication> getListPubByUser(@RequestParam(value = "idUser", required = false, defaultValue = "") Integer idUser){
		return publicationMetier.getPubsByUser(idUser);
	}
	
	/*
	 * Get list publication by parameters
	 */
	@RequestMapping(value = "/listPubByParameters", method = RequestMethod.GET)
	public List<Publication> getListPubByParams(
			@RequestParam(value = "dateDep",     required = false, defaultValue = "")     String dateDep,
			@RequestParam(value = "dateArr",     required = false, defaultValue = "")     String dateArr,
			@RequestParam(value = "actionLocal", required = false, defaultValue = "")     Boolean actionLocal,
			@RequestParam(value = "villeArr",    required = false, defaultValue = "")     String villeArr,
			@RequestParam(value = "villeDepart", required = false, defaultValue = "")     String villeDepart,
			@RequestParam(value = "prixInf",     required = false, defaultValue = "0.0f") Float prixInf,
			@RequestParam(value = "prixSup",     required = false, defaultValue = "0.0f") Float prixSup,
			@RequestParam(value = "typePub",     required = false, defaultValue = "")     String typePub,
			@RequestParam(value = "typeColis",   required = false, defaultValue = "")     String typeColis,
			@RequestParam(value = "poids",       required = false, defaultValue = "0.0f") Float poids,
			@RequestParam(value = "userId",      required = false, defaultValue = "")     Integer userId,
			@RequestParam(value = "poidsDiv",    required = false, defaultValue = "true") Boolean poidsDiv) {
		Publication publication = new Publication();

		checkPubParams(publication, dateDep, dateArr, userId);

		publication.setArrival_town(villeArr);
		publication.setDeparture_town(villeDepart);
		publication.setType_packet(typeColis);
		publication.setType_publication(typePub);
		publication.setLocal_action(actionLocal);
		publication.setDiv_weight(poidsDiv);
		publication.setWeight(poids);
		publication.setMinPrice(prixInf);
		publication.setMaxPrice(prixSup);

		return publicationMetier.getPubsByParams(publication);
	}
	
	public Publication checkPubParams(
			Publication publication, 
			String dateDep, 
			String dateArr, 
			Integer userId
			) {
		try {
			if (Convertion.checkStringNotNullNotEmpty(dateDep)) {
				Date dtDep = format.parse(dateDep);
				publication.setDeparture_date(dtDep);
			}

			if (Convertion.checkStringNotNullNotEmpty(dateArr)) {
				Date dtArr = format.parse(dateArr);
				publication.setArrival_date(dtArr);
			}

			if (userId != null) {
				publication.setUser(userId);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return publication;
	}

}
