package com.example.lionprintfirstproject.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.Locale;
import java.util.function.Function;

@UtilityClass
public class QueryUtils {

    public <E, X> Specification<E> equals(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.equal(function.apply(root), value);
    }

    public <E> Specification<E> is(Function<Root<E>, Expression<Boolean>> function, Boolean value) {
        return Boolean.TRUE.equals(value) ? isTrue(function) : isFalse(function);
    }

    public <E> Specification<E> isTrue(Function<Root<E>, Expression<Boolean>> function) {
        return (root, query, builder) -> builder.isTrue(function.apply(root));
    }

    public <E> Specification<E> isFalse(Function<Root<E>, Expression<Boolean>> function) {
        return (root, query, builder) -> builder.isFalse(function.apply(root));
    }

    public <E, X extends Comparable<X>> Specification<E> gte(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(function.apply(root), value);
    }

    public <E, X extends Comparable<X>> Specification<E> lte(Function<Root<E>, Expression<X>> function, X value) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(function.apply(root), value);
    }

    public <E, X> Specification<E> in(Function<Root<E>, Expression<X>> function, Collection<X> values) {
        return (root, query, builder) -> {
            CriteriaBuilder.In<X> inPredicate = builder.in(function.apply(root));
            for (X value : values) {
                inPredicate = inPredicate.value(value);
            }
            return inPredicate;
        };
    }

    public <E> Specification<E> like(Function<Root<E>, Expression<String>> function, String value) {
        return (root, query, builder) -> builder.like(builder.upper(function.apply(root)), wrapLikeQuery(value));
    }

    public <E, X extends Comparable<X>> Specification<E> between(Function<Root<E>, Expression<X>> function, X start, X end) {
        return gte(function, start).and(lte(function, end));
    }

    private String wrapLikeQuery(String txt) {
        return "%" + txt.toUpperCase(Locale.getDefault()) + "%";
    }

}
