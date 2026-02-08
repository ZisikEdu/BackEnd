package org.zisik.edu.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zisik.edu.user.domain.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByAccountId(Long accountId);
}
