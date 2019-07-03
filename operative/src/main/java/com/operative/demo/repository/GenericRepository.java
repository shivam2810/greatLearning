package com.operative.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import com.operative.demo.model.MongoEntity;

public abstract class GenericRepository<T extends MongoEntity, ID extends Serializable> {
	@Autowired
	MongoConfig mongoTemplate;

	public abstract Class<T> getEntityClass();

	public T save(T entity) {
		getMongoTemlate().save(entity);
		return entity;
	}

	public MongoOperations getMongoTemlate() {
		return mongoTemplate.getMongoTemplate();
	}

	public List<T> find(final Query query) {
		return getMongoTemlate().find(query, getEntityClass());
	}

	public T findOne(final Query query) {
		return getMongoTemlate().findOne(query, getEntityClass());
	}

	public T findById(final ID id) {
		return getMongoTemlate().findById(id, getEntityClass());
	}
}
