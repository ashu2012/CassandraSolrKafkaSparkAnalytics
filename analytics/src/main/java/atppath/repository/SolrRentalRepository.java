package atppath.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Distance;
import org.springframework.data.solr.core.geo.Point;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import atppath.model.Rental;

public interface SolrRentalRepository extends SolrCrudRepository<Rental, String> {

  //Derived Query will be "q=popularity:<popularity>&start=<page.number>&rows=<page.size>"
  Page<Rental> findByPopularity(Integer popularity, Pageable page);

  //Will execute count prior to determine total number of elements
  //Derived Query will be "q=name:<name>*&start=0&rows=<result of count query for q=name:<name>>"
  List<Rental> findByNameStartingWith(String name);

  List<Rental> findByCityStartingWith(String city);
  
  //Derived Query will be "q=inStock:true&start=<page.number>&rows=<page.size>"
  Page<Rental> findByAvailableTrue(Pageable page);

  //Derived Query will be "q=inStock:<inStock>&start=<page.number>&rows=<page.size>"
  @Query("inStock:?0")
  Page<Rental> findByAvailableUsingAnnotatedQuery(boolean inStock, Pageable page);

  //Will execute count prior to determine total number of elements
  //Derived Query will be "q=inStock:false&start=0&rows=<result of count query for q=inStock:false>&sort=name desc"
  List<Rental> findByAvailableFalseOrderByNameDesc();

  //Execute faceted search 
  //Query will be "q=name:<name>&facet=true&facet.field=cat&facet.limit=20&start=<page.number>&rows=<page.size>"
  @Query(value = "city:?0")
  @Facet(fields = { "roomsNumber" , "hasGarden"}, limit=20)
  FacetPage<Rental> findByCityAndFacetOnCategory(String name, Pageable page);

  //Boosting criteria
  //Query will be "q=name:<name>^2 OR description:<description>&start=<page.number>&rows=<page.size>"
  Page<Rental> findByNameOrDescription(@Boost(2) String name, String description, Pageable page);

  //Highlighting results
  //Query will be "q=name:(<name...>)&hl=true&hl.fl=*"
  @Highlight
  HighlightPage<Rental> findByNameIn(Collection<String> name, Pageable page);

  //Spatial Search
  //Query will be "q=location:[<bbox.start.latitude>,<bbox.start.longitude> TO <bbox.end.latitude>,<bbox.end.longitude>]"
  Page<Rental> findByLocationNear(Box bbox);

  //Spatial Search
  //Query will be "q={!geofilt pt=<location.latitude>,<location.longitude> sfield=location d=<distance.value>}"
  Page<Rental> findByLocationWithin(Point location, Distance distance);

}