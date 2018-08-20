package com.TamarinES.Metier.Impl;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.TamarinES.Metier.PublicationMetier;
import com.TamarinES.Utility.Convertion;


@Service
public class PublicationMetierImpl implements PublicationMetier{

    @Autowired
    private ElasticsearchTemplate esTemplate;
    
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Publication> getPubs() {
		SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .build()
                .setPageable(PageRequest.of(0, 10000))
                .addSort(new Sort(Sort.Direction.ASC, "idpub"));

        return getPubList(getAllQuery);
	}

	@Override
	public Publication getPubById(int pubId) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(QueryBuilders.matchQuery("idpub", pubId)).build();
        List<Publication> pub = esTemplate.queryForList(searchQuery, Publication.class);
        if(!pub.isEmpty()) {
            return pub.get(0);
        }
        return null;
	}

	@Override
	public List<Publication> getPubsLast() {
		
		LocalDate baseDate = LocalDate.now().minusDays(7);
		Date date = java.sql.Date.valueOf(baseDate);
		String dateFormated = df.format(date);

		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.rangeQuery("publication_date").from(dateFormated).to("now"))
                .build()
                .setPageable(PageRequest.of(0, 10000))
                .addSort(new Sort(Sort.Direction.ASC, "publication_date"));
		
		return getPubList(searchQuery);
	}

	@Override
	public List<Publication> getPubsByUser(Integer userId) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(QueryBuilders.matchQuery("user", userId))
                .build()
                .setPageable(PageRequest.of(0, 10000));

        return getPubList(searchQuery);
	}

	@Override
	public List<Publication> getPubsByParams(Publication publication) {

		Criteria criteria = new Criteria();
		System.out.println(publication.toString());
		
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
		 * Si les prix Maximum et Minimum sont 
		 * rensignés on vérifi s'ils sont bien 
		 * supérieur à zéro dans le cas contraire 
		 * on les ajoute pas à la requête
		*/
		if (Convertion.checkFloatGreatThanZero(publication.getMinPrice()) && 
			Convertion.checkFloatGreatThanZero(publication.getMaxPrice())
			) {
			criteria.and(new Criteria("price").between(publication.getMinPrice(), publication.getMaxPrice()));
		} 
		
		/*
		 * Si le poids est rensigné on 
		 * verifie s'il est bien supérieur à zéro
		 * dans le cas contraire on l'ajoute pas 
		 * à la requête
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
		if (Convertion.checkDateNotNull(publication.getArrival_date()) && 
			Convertion.checkDateNotNull(publication.getDeparture_date())
			) {			
			criteria.and(new Criteria("departure_date")
					.between(
							df.format(publication.getDeparture_date()), 
							df.format(publication.getArrival_date()))
					);
			
			criteria.and(new Criteria("arrival_date")
					.between(
							df.format(publication.getDeparture_date()), 
							df.format(publication.getArrival_date()))
					);

		} // Si seule la Date départ est renseignée 
		else if (Convertion.checkDateNotNull(publication.getDeparture_date()) && 
				!Convertion.checkDateNotNull(publication.getArrival_date())
				) {
			
			criteria.and(new Criteria("departure_date")
					.greaterThanEqual(df.format(publication.getDeparture_date())));
			
		} // Si seule la Date arrivée est renseignée
		else if (!Convertion.checkDateNotNull(publication.getDeparture_date()) && 
				Convertion.checkDateNotNull(publication.getArrival_date())
				) {
			criteria.and(new Criteria("arrival_date")
					.greaterThanEqual(df.format(publication.getArrival_date())));
		}
		
		CriteriaQuery criteriaQuery = new CriteriaQuery(criteria)
				.setPageable(PageRequest.of(0, 10000));
		
		Page<Publication> pubPage = esTemplate.queryForPage(criteriaQuery, Publication.class);
		List<Publication> samplePub = pubPage.getContent();
		
		return samplePub;
	}
	
	public List<Publication> getPubList(SearchQuery searchQuery) {
		Page<Publication> pubPage = esTemplate.queryForPage(searchQuery, Publication.class);
		List<Publication> samplePub = pubPage.getContent();
		
		return samplePub;
	}

}
