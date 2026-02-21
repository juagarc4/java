package raulgarcia.zone_fit_spring_swing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raulgarcia.zone_fit_spring_swing.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
