package com.operative.demo.queryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

public class BoardQueryBuilder {

	private final List<Criteria> criteriaList;
	public static final String BOARDID = "bId";
	public static final String PLAYER1 = "player1";
	public static final String PLAYER2 = "player2";

	public BoardQueryBuilder() {
		criteriaList = new ArrayList<>();
	}

	public BoardQueryBuilder boardId(final String boardId) {
		if (boardId != null) {
			return boardIds(Arrays.asList(boardId));
		} else {
			return this;
		}
	}

	public BoardQueryBuilder boardIds(final List<String> boardIds) {
		if (!CollectionUtils.isEmpty(boardIds)) {
			criteriaList.add(Criteria.where(BOARDID).in(boardIds));
		}
		return this;
	}

	public BoardQueryBuilder player2Id() {
		criteriaList.add(Criteria.where(PLAYER2).exists(false));
		return this;
	}

	public Query toQuery() {
		return createQuery();
	}

	private Query createQuery() {
		if (criteriaList.isEmpty()) {
			return new Query();
		}
		return new Query(toCriteria());
	}

	public Query toQuery(String... fieldNames) {
		final Query query = createQuery();
		for (final String fieldName : fieldNames) {
			query.fields().include(fieldName);
		}
		return query;
	}

	public Criteria toCriteria() {
		return new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
	}

}
