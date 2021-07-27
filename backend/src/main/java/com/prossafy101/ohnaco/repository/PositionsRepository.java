package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.user.Positions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository<Positions, Integer> {
    Positions findByPositionname(String position);
}
