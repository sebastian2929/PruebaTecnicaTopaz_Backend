package com.prueba.libros.filter;

import com.prueba.libros.model.Libro;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Expression;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LibroFilter {

    public static Specification<Libro> apply(
            String field,
            String operator,
            String value
    ) {
        return (root, query, cb) -> {

            if (value == null || value.isBlank()) {
                return cb.conjunction();
            }

            if (field.equals("nombre") || field.equals("autor")) {

                Expression<String> exp =
                        cb.lower(root.get(field).as(String.class));
                String val = value.toLowerCase();

                return switch (operator) {
                    case "contains" ->
                        cb.like(exp, "%" + val + "%");

                    case "notContains" ->
                        cb.notLike(exp, "%" + val + "%");

                    case "equals" ->
                        cb.equal(exp, val);

                    case "notEquals" ->
                        cb.notEqual(exp, val);

                    case "starts" ->
                        cb.like(exp, val + "%");

                    case "ends" ->
                        cb.like(exp, "%" + val);

                    default ->
                        cb.conjunction();
                };
            }

            if (field.equals("numeroEjemplares")) {
                Integer number = Integer.valueOf(value);

                return switch (operator) {
                    case "equals" ->
                        cb.equal(root.get(field), number);

                    case "notEquals" ->
                        cb.notEqual(root.get(field), number);

                    default ->
                        cb.conjunction();
                };
            }

            if (field.equals("costo")) {
                BigDecimal decimal = new BigDecimal(value);

                return switch (operator) {
                    case "equals" ->
                        cb.equal(root.get(field), decimal);

                    case "notEquals" ->
                        cb.notEqual(root.get(field), decimal);

                    default ->
                        cb.conjunction();
                };
            }

            if (field.equals("fechaPublicacion")) {
                LocalDate date = LocalDate.parse(value);

                return switch (operator) {
                    case "equals" ->
                        cb.equal(root.get(field), date);

                    case "notEquals" ->
                        cb.notEqual(root.get(field), date);

                    default ->
                        cb.conjunction();
                };
            }

            return cb.conjunction();
        };
    }
}
