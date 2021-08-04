package uk.tw.energy.joienergy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.tw.energy.joienergy.domain.Meter;

@Repository
public interface MeterRepository extends JpaRepository<Meter, String> {}
