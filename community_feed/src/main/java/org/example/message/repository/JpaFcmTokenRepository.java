package org.example.message.repository;

import org.example.message.repository.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {

}
