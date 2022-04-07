package com.nazarov.movieland.currency_converter.repository;

import com.nazarov.movieland.currency_converter.entity.Currency;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Modifying
    @Transactional
    @Query(
            value = "update currency set rate = ?1 where r030 = ?2",
            nativeQuery = true
    )
    void refreshRate(double rate, Long r030);

    @Query("select u from Currency u where name = ?1")
    Currency getByName(String name);

    @Query("select u from Currency u where id = ?1")
    Currency getById(Long id);
}
