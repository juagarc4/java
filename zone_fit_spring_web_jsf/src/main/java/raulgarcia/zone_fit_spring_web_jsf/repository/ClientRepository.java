package raulgarcia.zone_fit_spring_web_jsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raulgarcia.zone_fit_spring_web_jsf.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
