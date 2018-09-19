package com.TamarinES.Metier.Impl;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.TamarinES.Entity.Publication;
import com.TamarinES.Entity.PublicationDto;
import com.TamarinES.Metier.PublicationMetier;
import com.TamarinES.Utility.Convertion;

@Service
public class PublicationMetierImpl implements PublicationMetier {

	@Autowired
	private ElasticsearchTemplate esTemplate;

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	PageRequest pageRequest = new PageRequest(0, 10000);

	@Override
	public List<PublicationDto> getPubs() {
		SearchQuery getAllQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build()
				.setPageable(pageRequest).addSort(new Sort(Sort.Direction.ASC, "idpub"));
				//.setPageable(PageRequest.of(0, 10000)).addSort(new Sort(Sort.Direction.ASC, "idpub"));

		return convertListEntityToDto(getPubList(getAllQuery));
	}

	@Override
	public PublicationDto getPubById(int pubId) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.matchQuery("idpub", pubId))
				.build();
		List<Publication> pub = esTemplate.queryForList(searchQuery, Publication.class);
		
		if (!pub.isEmpty()) {
			return convertEntityToDto(pub.get(0));
		}
		return null;
	}

	@Override
	public List<PublicationDto> getPubsLast() {

		LocalDate baseDate = LocalDate.now().minusDays(7);
		Date date = java.sql.Date.valueOf(baseDate);
		String dateFormated = df.format(date);

		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.rangeQuery("publication_date").from(dateFormated).to("now")).build()
				.setPageable(pageRequest).addSort(new Sort(Sort.Direction.ASC, "publication_date"));
				//.setPageable(PageRequest.of(0, 10000)).addSort(new Sort(Sort.Direction.ASC, "publication_date"));

		return convertListEntityToDto(getPubList(searchQuery));
	}

	@Override
	public List<PublicationDto> getPubsByUser(Integer userId) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.matchQuery("user", userId))
				.build().setPageable(pageRequest);
				//.build().setPageable(PageRequest.of(0, 10000));
		return convertListEntityToDto(getPubList(searchQuery));
	}

	@Override
	public List<PublicationDto> getPubsByParams(PublicationDto publicationDto) {

		Criteria criteria = new Criteria();
		Publication publication = convertDtoToEntity(publicationDto);
		
		// Action local
		if ((Boolean) publication.getLocal_action() != null) {
			criteria.and(new Criteria("local_action").is(publication.getLocal_action()));
		}

		// Divide Weight is allow
		if ((Boolean) publication.getDiv_weight() != null) {
			criteria.and(new Criteria("div_weight").is(publication.getDiv_weight()));
		}

		// Ville arrivée
		if (Convertion.checkStringNotNullNotEmpty(publication.getArrival_town())) {
			criteria.and(new Criteria("arrival_town").expression(publication.getArrival_town()));
		}

		// Ville départ
		if (Convertion.checkStringNotNullNotEmpty(publication.getDeparture_town())) {
			criteria.and(new Criteria("departure_town").expression(publication.getDeparture_town()));
		}

		// Type publication
		if (Convertion.checkStringNotNullNotEmpty(publication.getType_publication())) {
			criteria.and(new Criteria("type_publication").expression(publication.getType_publication()));
		}

		/*
		 * Si les prix Maximum et Minimum sont rensignés on vérifi s'ils sont bien
		 * supérieur à zéro dans le cas contraire on les ajoute pas à la requête
		 */
		if (Convertion.checkFloatGreatThanZero(publication.getMinPrice())
				&& Convertion.checkFloatGreatThanZero(publication.getMaxPrice())) {
			criteria.and(new Criteria("price").between(publication.getMinPrice(), publication.getMaxPrice()));
		}

		/*
		 * Si le poids est rensigné on verifie s'il est bien supérieur à zéro dans le
		 * cas contraire on l'ajoute pas à la requête
		 */
		if (Convertion.checkFloatGreatThanZero(publication.getWeight())) {
			criteria.and(new Criteria("weight").greaterThanEqual(publication.getWeight()));
		}

		// Vérifier si le user est renseigné
		if (publication.getUser() != null) {
			criteria.and(new Criteria("user").expression(publication.getUser().toString()));
		}

		/*
		 * Si la Date départ et la Date Arrivée sont renseignées
		 */
		if (Convertion.checkDateNotNull(publication.getArrival_date())
				&& Convertion.checkDateNotNull(publication.getDeparture_date())) {
			criteria.and(new Criteria("departure_date").between(df.format(publication.getDeparture_date()),
					df.format(publication.getArrival_date())));

			criteria.and(new Criteria("arrival_date").between(df.format(publication.getDeparture_date()),
					df.format(publication.getArrival_date())));

		} // Si seule la Date départ est renseignée
		else if (Convertion.checkDateNotNull(publication.getDeparture_date())
				&& !Convertion.checkDateNotNull(publication.getArrival_date())) {
			//criteria.and(new Criteria("departure_date").greaterThanEqual(df.format(publication.getDeparture_date())));
			criteria.and(new Criteria("departure_date").expression(df.format(publication.getDeparture_date())));

		} // Si seule la Date arrivée est renseignée
		else if (!Convertion.checkDateNotNull(publication.getDeparture_date())
				&& Convertion.checkDateNotNull(publication.getArrival_date())) {
			//criteria.and(new Criteria("arrival_date").greaterThanEqual(df.format(publication.getArrival_date())));
			criteria.and(new Criteria("arrival_date").expression(df.format(publication.getArrival_date())));
		}

		//CriteriaQuery criteriaQuery = new CriteriaQuery(criteria).setPageable(PageRequest.of(0, 10000));
		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria).setPageable(pageRequest);

		Page<Publication> pubPage = esTemplate.queryForPage(criteriaQuery, Publication.class);
		List<Publication> samplePub = pubPage.getContent();

		return convertListEntityToDto(samplePub);
	}

	public List<Publication> getPubList(SearchQuery searchQuery) { 
		Page<Publication> pubPage = esTemplate.queryForPage(searchQuery, Publication.class);
		List<Publication> samplePub = pubPage.getContent();
		return samplePub;
	}
	
	public PublicationDto convertEntityToDto(Publication publication) {
		PublicationDto publicationDto = new PublicationDto();

		publicationDto.setIdPub(publication.getIdpub());
		publicationDto.setActive(publication.getActive());
		publicationDto.setArrivalDate(publication.getArrival_date());
		publicationDto.setArrivalTown(publication.getArrival_town());
		publicationDto.setBirthDay(publication.getBirth_day());
		publicationDto.setDepartureDate(publication.getDeparture_date());
		publicationDto.setDepartureTown(publication.getDeparture_town());
		publicationDto.setDivWeight(publication.getDiv_weight());
		publicationDto.setEmail(publication.getEmail());
		publicationDto.setFirstName(publication.getFirst_name());
		publicationDto.setIdPub(publication.getIdpub());
		publicationDto.setIdUser(publication.getUser_id());
		publicationDto.setLastName(publication.getLast_name());
		publicationDto.setLocalAction(publication.getLocal_action());
		publicationDto.setMaxPrice(publication.getMaxPrice());
		publicationDto.setMinPrice(publication.getMinPrice());
		publicationDto.setNote(publication.getNote());
		publicationDto.setPhoneNumber(publication.getPhone_number());
		publicationDto.setPhoto(publication.getPhoto());
		publicationDto.setPrice(publication.getPrice());
		publicationDto.setPseudo(publication.getPseudo());
		publicationDto.setPublicationDate(publication.getPublication_date());
		//publicationDto.setStatutPublication(publication.get);
		//publicationDto.setTotalPrice(publication.get);
		publicationDto.setTypePacket(publication.getType_packet());
		publicationDto.setTypePublication(publication.getType_publication());
		publicationDto.setVerif(publication.getVerif());
		publicationDto.setWeight(publication.getWeight());

		return publicationDto;
	}
	
	public Publication convertDtoToEntity(PublicationDto publicationDto) {
		Publication publication = new Publication();
		
		publication.setIdpub(publicationDto.getIdPub());
		publication.setActive(publicationDto.getActive());
		publication.setArrival_date(publicationDto.getArrivalDate());
		publication.setArrival_town(publicationDto.getArrivalTown());
		publication.setBirth_day(publicationDto.getBirthDay());
		publication.setDeparture_date(publicationDto.getDepartureDate());
		publication.setDeparture_town(publicationDto.getDepartureTown());
		publication.setDiv_weight(publicationDto.getDivWeight());
		publication.setEmail(publicationDto.getEmail());
		publication.setFirst_name(publicationDto.getFirstName());
		publication.setIdpub(publicationDto.getIdPub());
		publication.setLast_name(publicationDto.getLastName());
		publication.setLocal_action(publicationDto.getLocalAction());
		publication.setMaxPrice(publicationDto.getMaxPrice());
		publication.setMinPrice(publicationDto.getMinPrice());
		publication.setNote(publicationDto.getNote());
		publication.setPhone_number(publicationDto.getPhoneNumber());
		publication.setPhoto(publicationDto.getPhoto());
		publication.setPrice(publicationDto.getPrice());
		publication.setPseudo(publicationDto.getPseudo());
		publication.setPublication_date(publicationDto.getPublicationDate());
		publication.setType_packet(publicationDto.getTypePacket());
		publication.setType_publication(publicationDto.getTypePublication());
		publication.setUser(publicationDto.getIdUser());
		publication.setVerif(publicationDto.getVerif());
		publication.setWeight(publicationDto.getWeight());
		publication.setUser_id(publicationDto.getIdUser());
		
		return publication;
	}
	
	public List<Publication> convertListDtoToEntity(List<PublicationDto> listPublicationDto){
		List<Publication> listPub = new ArrayList<>();
		
		for (PublicationDto pubDto : listPublicationDto) {
			listPub.add(convertDtoToEntity(pubDto));
		}
		
		return listPub;
	}
	
	public List<PublicationDto> convertListEntityToDto(List<Publication> listPublication){
		ArrayList<PublicationDto> listPubDto = new ArrayList<>();
		
		for (Publication pub : listPublication) {
			listPubDto.add(convertEntityToDto(pub));
		}
		
		return listPubDto;
	}

}