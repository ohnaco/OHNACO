package com.prossafy101.ohnaco.repository;

import com.prossafy101.ohnaco.entity.todo.CommitDto;
import org.springframework.data.repository.CrudRepository;

public interface CommitRepository extends CrudRepository<CommitDto, String> {
}
