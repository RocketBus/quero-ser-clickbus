package br.com.click.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.click.entity.Place;

public class PlaceSpecification implements Specification<Place> {

	private static final long serialVersionUID = 1L;
	private Place filter;

	public PlaceSpecification(Place filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Place> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p = cb.disjunction();

		if (filter.getName() != null) {
			p.getExpressions().add(cb.equal(root.get("name"), filter.getName()));
		}

		return p;
	}
}