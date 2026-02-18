package raulgarcia.zone_fit_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raulgarcia.zone_fit_spring.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
