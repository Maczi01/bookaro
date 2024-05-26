package com.codeart.bookaro.catalog.domain;

public record Book(Long id,
                   String title,
                   String author,
                   Integer year) {

}
