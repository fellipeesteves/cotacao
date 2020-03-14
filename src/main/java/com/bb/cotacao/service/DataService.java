package com.bb.cotacao.service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Named("dataService")
@Singleton
public class DataService {
	@Inject
	EntityManager entityManager;

	static final Query queryRange(final Query query, final int offset, final int count) {
		if (count >= 0)
			query.setMaxResults(count);
		if (offset >= 0)
			query.setFirstResult(offset);
		return query;
	}

	public void flush() {
		entityManager.flush();
	}

	public <E> Optional<E> save(final E e) {
		entityManager.persist(e);
		return Optional.ofNullable(e);
	}

	public <E> Optional<E> update(final E e) {
		entityManager.merge(e);
		return Optional.ofNullable(e);
	}

	public <E> Optional<E> findById(final Class<E> clazz, final String id) {
		return Optional.ofNullable(entityManager.find(clazz, id));
	}

	public <E> void delete(final Class<E> clazz, final String id) {
		entityManager.remove(entityManager.find(clazz, id));
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findByRange(final Class<E> clazz, final String query, final int offset, final int count) {
		return queryRange(entityManager.createQuery(query, clazz), offset, count).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findByNamedQueryAndRange(final Class<E> clazz, final String query, final int offset,
			final int count) {
		return queryRange(entityManager.createNamedQuery(query, clazz), offset, count).getResultList();
	}

	public <E> List<E> findByNamedQuery(final Class<E> clazz, final String queryName) {
		return entityManager.createNamedQuery(queryName, clazz).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findByNamedQueryWithParams(final Class<E> clazz, final String queryName,
			final Object... parameters) {
		Query q = entityManager.createNamedQuery(queryName, clazz);
		for (int i = 0; i < parameters.length; i++) {
			q.setParameter(i + 1, parameters[i]);
		}
		return q.getResultList();
	}

	public <E> Optional<E> findUniqueByNamedQueryWithParams(final Class<E> clazz, final String queryName,
			Object... parameters) {
		List<E> list = findByNamedQueryWithParams(clazz, queryName, parameters);
		return (list.size() == 1) ? Optional.ofNullable(list.get(0)) : Optional.empty();
	}
}