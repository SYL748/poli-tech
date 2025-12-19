package com.magic.MagicServer.facts.state.repository;

import com.magic.MagicServer.facts.state.entity.MailAndDropboxByStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for mail_and_dropbox_by_state table (GUI-15)
 * Provides data access methods for state mail and dropbox voting data
 */
@Repository
public interface MailAndDropboxByStateRepository extends JpaRepository<MailAndDropboxByStateEntity, Integer> {

	/**
	 * Find mail and dropbox data by state ID
	 * @param stateId The state identifier
	 * @return Optional containing MailAndDropboxByStateEntity if found
	 */
	Optional<MailAndDropboxByStateEntity> findByStateId(Integer stateId);
}

