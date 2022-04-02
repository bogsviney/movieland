package com.nazarov.movieland.repository;

import com.nazarov.movieland.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
