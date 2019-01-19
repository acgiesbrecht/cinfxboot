package com.chortitzer.cin.cinfxboot.datasource.bascula.repository;

import com.chortitzer.cin.cinfxboot.datasource.bascula.domain.TblContribuyentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblContribuyentesRepository extends JpaRepository<TblContribuyentes, String> {
}
