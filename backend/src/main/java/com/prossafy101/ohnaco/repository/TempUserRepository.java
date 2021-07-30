package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.user.TempUserDto;
import org.springframework.data.repository.CrudRepository;

public interface TempUserRepository extends CrudRepository<TempUserDto, String> {
}
