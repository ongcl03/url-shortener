package com.ongcl.urlshortener.repositories;

import com.ongcl.urlshortener.entities.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
}
