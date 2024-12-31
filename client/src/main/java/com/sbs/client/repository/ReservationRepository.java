package com.sbs.client.repository;

import com.sbs.client.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByClientUserId(long userId);

    List<Reservation> findAllByCompanyUserId(long userId);
}
