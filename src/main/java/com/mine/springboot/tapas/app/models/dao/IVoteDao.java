package com.mine.springboot.tapas.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.tapas.app.models.entity.Vote;

public interface IVoteDao extends CrudRepository<Vote, Long>{
	
	@Query("select v from Vote v left join fetch v.usuario u where u.id=?1")
	public Vote findVoteByUserId(Long id);
	
	@Query("select v from Vote v left join fetch v.usuario u left join fetch v.tapa t where u.id=?1 and t.id=?2")
	public Vote selectVoteByUserAndTapa(Long userId, Long tapaId);
}
